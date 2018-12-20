package org.exoplatform.platform.qa.ui.ecms.smoke;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;

@Tag("ecms")
@Tag("smoke")
public class EcmsSECreateTestIT extends Base {
  ContentAdministration contentAdministration;

  NavigationToolbar     navigationToolbar;

  SiteExplorerHome      siteExplorerHome;

  CreateNewDocument     createNewDoc;

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
  @BugInPLF("NO ID")
  public void test06_EditFileDocument() {
    info("Test 2: Delete File document");
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
   * <li>Case ID:116641.</li>
   * <li>Test Case Name: Create File document.</li>
   */
  @Test
  @BugInPLF("NO ID")
  public void test02CreateFileDocument() {
    info("Test 2: Delete File document");
    info("Create data test");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
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
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }

  /**
   * <li>Case ID:116572.</li>
   * <li>Test Case Name: Delete File document.</li>
   */
  @Test
  @BugInPLF("NO ID")
  public void test07_DeleteFileDocument() {
    info("Test 2: Delete File document");
    info("Create data test");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
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
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }

  /**
   * <li>Case ID:116573.</li>
   * <li>Test Case Name: Create Web Content document.</li>
   */
  @Test
  public void test03_CreateWebContentDocument() {
    info("Test 3: Create Web Content document");
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
  public void test08_EditWebContentDocument() {
    info("Test 3: Create Web Content document");
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

  /**
   * <li>Test Case Name: Delete Web Content document.</li>
   * <li>Pre-Condition: A Web Content document is already created.</li>
   */
  @Test
  public void test09_DeleteWebContentDocument() {
    info("Test 3: Create Web Content document");
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
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }
}
