package org.exoplatform.platform.qa.ui.ecms.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.gatein.pageobject.*;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * @author exo update 26/01/2015
 * @date 15-January-2015
 */

@Tag("ecms")
@Tag("smoke")
public class EcmsWCMTestIT extends Base {

  NavigationToolbar navigationToolbar;

  SiteExplorerHome siteExplorerHome;

  CreateNewDocument createNewDoc;

  PageCreationWizard pageCreationWizard;

  PortalManagePages paMang;

  PortalManageSites portalManageSites;

  NavigationManagement navigationManagement;

  ContentList contentList;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDoc = new CreateNewDocument(this);
    pageCreationWizard = new PageCreationWizard(this);
    paMang = new PortalManagePages(this);
    portalManageSites = new PortalManageSites(this);
    navigationManagement = new NavigationManagement(this);
    contentList = new ContentList(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
}

  /**
   * <li>Case ID:116614.</li>
   * <li>Test Case Name: Edit Single Content Viewer page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test

  public void test01_CreateEditSingleContentViewerPage() {
    info("Test 10 Edit Single Content Viewer page");
    String title = "title" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    /*
     * Step Number: 1 Step Name: Step 1: Create Single Content Viewer page Step
     * Description: - Login acme by Admin/Web contributor - Choose Edit/Page/Add
     * Page+ Fill name+ Next+ Next+ Drag and drop Content/ Content Detail portlet to
     * this Page+ Click Edit icon to edit this porlet+ Select [Content Path] where
     * stores these web contents/documents+ Click icon in Action column in the right
     * to select one of them+ Click Save+ Click Close+ Click Finish icon in page
     * editor Input Data: Expected Outcome: - The selected web content/document is
     * displayed
     */
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");

    info("Create webcontent 1");
    siteExplorerHome.goToAddNewContent();
    createNewDoc.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDoc.addNewWebContent(content1, content1);
    createNewDoc.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content1);

    info("Select acme folder");
    siteExplorerHome.selectNode("intranet");

    info("Create webcontent2");
    siteExplorerHome.goToAddNewContent();
    createNewDoc.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDoc.addNewWebContent(content2, content2);
    createNewDoc.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content2);

    navigationToolbar.goToAddPage();
    pageCreationWizard.inputPageInfoStep1(title, true, "English", title, true, false);
    $(ELEMENT_ADDNEWPAGE_BTNNEXT).click();
    $(ELEMENT_ADDNEWPAGE_BTNNEXT).click();
    pageCreationWizard.addContentDetail("General Drives/Sites Management/intranet", content1);

    navigationToolbar.goToEditContent();
    refresh();
    /*
     * Step number: 2 Step Name: Step 2: Edit Single Content Viewer page Step
     * Description: - Login acme by Admin/Web contributor - Open page above - Click
     * Edit/ Page/ Page Layout+ Click Edit icon to edit this porlet+ Select [Content
     * Path] where stores these web contents/documents+ Click icon in Action column
     * in the right to select one of them+ Click Save+ Click Close+ Click Finish
     * icon in page editor Input Data: Expected Outcome: - The new selected web
     * content/document is displayed
     */
    $(byTitle("Portlet Mode")).click();
    $(byTitle("Edit")).click();
    $(ELEMENT_CONTENT_LIST_ADDPATH_BTN).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byXpath(ELEMENT_MULTIPLE_CONTENT_POPUP_FILE.replace("${content}", content2))).waitUntil(Condition.visible, Configuration.timeout).click();
    // scroll up
    executeJavaScript("window.scrollBy(0,-350);", "");
    $(byText("Done")).waitUntil(Condition.appears, Configuration.timeout);
    info("Delete created files");

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(content1, true);
    $(byText(content2)).waitUntil(Condition.appears, Configuration.timeout);
    siteExplorerHome.deleteData(content2, true);
    info("Delete create page");
    navigationToolbar.goToPotalPages();
    paMang.deletePage(title, "group");

  }

}
