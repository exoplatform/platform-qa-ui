package org.exoplatform.platform.qa.ui.ecms.smoke;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.ELEMENT_INCON_ADD_PATH;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_ADDNEWPAGE_BTNNEXT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.gatein.pageobject.*;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

/**
 * @date 15-January-2015
 * @author exo update 26/01/2015
 */

@Tag("ecms")
@Tag("smoke")
public class EcmsWCMTestIT extends Base {

  NavigationToolbar    navigationToolbar;

  SiteExplorerHome     siteExplorerHome;

  CreateNewDocument    createNewDoc;

  PageCreationWizard   pageCreationWizard;

  PortalManagePages    paMang;

  PortalManageSites    portalManageSites;

  NavigationManagement navigationManagement;

  ContentList          contentList;

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
  }

  /**
   * <li>Case ID:116571.</li>
   * <li>Test Case Name: Create Single Content Viewer page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test03_CreateSingleContentViewerPage() {
    info("Test 3: Create Single Content Viewer page");
    String content = "SingleContent" ;

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet", "Site Management");
    // Create node
    siteExplorerHome.goToAddNewContent();
    createNewDoc.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDoc.addNewWebContent(content, content);
    createNewDoc.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(content);
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
    // this.getExoWebDriver().getWebDriver().get(baseUrl+"/intranet");
    navigationToolbar.goToAddPage();

    pageCreationWizard.inputPageInfoStep1(content, true, "English", content, true, false);
    $(ELEMENT_ADDNEWPAGE_BTNNEXT).click();
    $(ELEMENT_ADDNEWPAGE_BTNNEXT).click();
    pageCreationWizard.addContentDetail("General Drives/Sites Management/intranet", content);

  }

}
