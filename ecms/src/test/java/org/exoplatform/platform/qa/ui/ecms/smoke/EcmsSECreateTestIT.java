package org.exoplatform.platform.qa.ui.ecms.smoke;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("ecms")
@Tag("smoke")
public class EcmsSECreateTestIT extends Base {
  ContentAdministration contentAdministration;

  NavigationToolbar navigationToolbar;

  SiteExplorerHome siteExplorerHome;

  CreateNewDocument createNewDoc;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    contentAdministration = new ContentAdministration(this);
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDoc = new CreateNewDocument(this);
  }

  /**
   * <li>Test Case Name: Edit File document.</li>
   */
  @Test
  public void test01_CreateEdit_FileDocument() {

    info("Create data test");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    info("Finished creating data test");

    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Edit File document
     * Input Data: - Create a File - Click Edit on action bar, perform to edit it -
     * Click Save & Close Expected Outcome: File is Edited successfully
     */
    info("Create a content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDoc.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDoc.addNewFile(name, content);
    createNewDoc.saveAndClose();

    info("Edit a content");
    siteExplorerHome.selectNode(name);
    siteExplorerHome.editDocument("", content2);
    createNewDoc.saveAndClose();

    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }

  /**
   * <li>Test Case Name: Edit Web Content document.</li>
   * <li>Pre-Condition: A Web Content document is already created.</li>
   */
  @Test
  public void test02_CreateEditWebContentDocument() {
    info("Create Web Content document");
    info("Create data test");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    info("Finished creating data test");
    /*
     * Step Number: 1 Step Name: Step 1: Edit Web Content document Step Description:
     * - Click Edit on action bar, perform to edit it - Click Save & Close Input
     * Data: Expected Outcome: The Web Content document is edited successfully
     */
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDoc.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDoc.addNewWebContent(name, content);
    createNewDoc.saveAndClose();

    info("Edit the content");
    siteExplorerHome.editDocument("", content2);
    createNewDoc.saveAndClose();

    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }

}
