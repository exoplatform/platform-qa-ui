package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_SOURCE_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

@Tag("sniff")
@Tag("ecms")
public class EcmsSECreateTestIT extends Base {
  NavigationToolbar navigationToolbar;

  SiteExplorerHome  siteExplorerHome;

  ManageLogInOut    manageLogInOut;

  CreateNewDocument createNewDocument;

  HomePagePlatform  homePagePlatform;

  SpaceManagement   spaceManagement;

  CreateNewDocument createNewDoc;


  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    spaceManagement = new SpaceManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    siteExplorerHome = new SiteExplorerHome(this);
    manageLogInOut = new ManageLogInOut(this);
    createNewDocument = new CreateNewDocument(this);
    createNewDoc= new CreateNewDocument(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:116569.</li>
   * <li>Test Case Name: Create Content folder.</li>
   * <li>Case ID:116645.</li>
   * <li>Test Case Name: Delete Content folder.</li> Step Number: 1 Step Name: -
   * Step Description: Step 1: Delete Content folder Input Data: - Create a
   * content folder - Right click on a folder and choose Delete - Click Delete at
   * confirmation Expected Outcome: - Folder is deleted - A modal message appears
   * with Undo option. You can click undo to restore
   */

  @Test
  public void test01_Create_ContentFolder() {
    info("Test 1: Create Content folder");
    info("Create data test");
    String title = "title" + getRandomNumber();
    String folderType = "Content Folder";
    info("Finished creating data test");

    info("Create a content folder");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewFolder();
    info("Create new file document");
    siteExplorerHome.createFolder(title, folderType);

    info("Test 10: Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(title);
  }

  @Test
  public void test10_DeleteContentFolder() {
    info("Test 1: Create Content folder");
    info("Create data test");
    String title = "title" + getRandomNumber();
    String folderType = "Content Folder";
    info("Finished creating data test");

    info("Create a content folder");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewFolder();
    info("Create new file document");
    siteExplorerHome.createFolder(title, folderType);

    info("Test 10: Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(title);
  }

  /**
   * <li>Case ID:116572.</li>
   * <li>Test Case Name: Delete File document.</li>
   * <li>Case ID:116641.</li>
   * <li>Test Case Name: Create File document.</li>
   * <li>Case ID:116642.</li>
   * <li>Test Case Name: Edit File document.</li>
   * Step Number: 1 Step Name: - Step Description: Step 1: Edit File document
   * Input Data: - Create a File - Click Edit on action bar, perform to edit it -
   * Click Save & Close Expected Outcome: File is Edited successfully
   */
  @Test
  public void test02_Create_FileDocument() {
    info("Test 2: Delete File document");
    info("Create data test");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Finished creating data test");
    info("Create a content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(name, content);
    createNewDocument.saveAndClose();
    $(byText(name)).should(Condition.visible);
    switchTo().frame($(byClassName("ECMIframe")));
    $(byText(content)).should(Condition.visible);
    switchTo().defaultContent();
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }

  @Test
  public void test06_EditFileDocument() {
    info("Test 2: Delete File document");
    info("Create data test");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    info("Finished creating data test");
    info("Create a content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(name, content);
    createNewDocument.saveAndClose();
    $(byText(name)).should(Condition.visible);
    switchTo().frame($(byClassName("ECMIframe")));
    $(byText(content)).should(Condition.visible);
    switchTo().defaultContent();
    info("Edit a content");
    siteExplorerHome.selectNode(name);
    siteExplorerHome.editDocument("", content2);
    createNewDocument.saveAndClose();
    switchTo().frame($(byClassName("ECMIframe")));
    $(byText(content + content2)).should(Condition.visible);
    switchTo().defaultContent();
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }

  @Test
  public void test07_DeleteFileDocument() {
    info("Test 2: Delete File document");
    info("Create data test");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Finished creating data test");
    info("Create a content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(name, content);
    createNewDocument.saveAndClose();
    $(byText(name)).should(Condition.visible);
    switchTo().frame($(byClassName("ECMIframe")));
    $(byText(content)).should(Condition.visible);
    switchTo().defaultContent();
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }

  /**
   * <li>Case ID:116573.</li>
   * <li>Test Case Name: Create Web Content document.</li>
   * <li>Case ID:116643.</li>
   * <li>Test Case Name: Edit Web Content document.</li>
   * <li>Pre-Condition: A Web Content document is already created.</li>
   * <li>Case ID:116644.</li>
   * <li>Test Case Name: Delete Web Content document.</li>
   * <li>Pre-Condition: A Web Content document is already created.</li> Step
   * Number: 1 Step Name: Step 1: Edit Web Content document Step Description: -
   * Click Edit on action bar, perform to edit it - Click Save & Close Input Data:
   * Expected Outcome: The Web Content document is edited successfully
   */
  @Test
  public void test03_CreateWebContentDocument() {
    info("Test 3: Create Web Content document");
    info("Create data test");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Finished creating data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(name, content);
    createNewDocument.saveAndClose();
    $(byText(name)).should(Condition.visible);
    $(byText(content)).should(Condition.visible);
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }

  @Test
  public void test08_Edit_WebContentDocument() {
    info("Test 3: Create Web Content document");
    info("Create data test");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    info("Finished creating data test");

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(name, content);
    createNewDocument.saveAndClose();
    $(byText(name)).should(Condition.visible);
    $(byText(content)).should(Condition.visible);
    info("Edit the content");
    siteExplorerHome.selectNode(name);
    siteExplorerHome.editDocument("", content2);
    createNewDocument.saveAndClose();
    $(byText(name)).should(Condition.visible);
    $(byText(content + content2)).should(Condition.visible);
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }

  @Test
  public void test09_Delete_WebContentDocument() {
    info("Test 3: Create Web Content document");
    info("Create data test");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Finished creating data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(name, content);
    createNewDocument.saveAndClose();
    $(byText(name)).should(Condition.visible);
    $(byText(content)).should(Condition.visible);
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }

  /**
   * <li>Case ID:116579.</li>
   * <li>Test Case Name: Insert documents/medias in a web content by FCK Content
   * Selector.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Insert
   * documents/medias in a web content by FCK Content Selector Step Description: -
   * Login acme by Admin - Select Administration - -> Content - -> Sites Explorer
   * - Click New Content icon - Select template which allows rick text editor,i.e
   * Web content. - In Main Content tab, clickInsert Content Link icon - In FCK
   * Content selector, choose any path where has contents, medias to select some
   * content - Click Save & Close to switch to View mode Input Data: Expected
   * Outcome: The selected content/media is inserted to Main Content as a link.In
   * view mode, user can click this link to view/download file.
   */

  @Test
  public void test04_InsertDocumentsmediasInAWebContentByFCKContentSelector() {
    info("Test 4: Insert documents/medias in a web content by FCK Content Selector");
    info("Create data test");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String link = "google.fr";
    info("Finished creating data test");
    info("Add a new content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(title, content);
    createNewDocument.addLinkInWebContent(link);
    createNewDocument.saveAndClose();
    click(By.xpath("//*[contains(text(),'google.fr')]"));
    refresh();
    assertEquals(Selenide.title(), "Google");
    back();
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(title);
  }

  /**
   * <li>Case ID:116608.</li>
   * <li>Test Case Name: Upload a file in Content explorer.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * <li>Case ID:116646.</li>
   * <li>Test Case Name: Delete an uploaded file in content explorer.</li> Step
   * Number: 1 Step Name: - Step Description: Step 1: Open form to upload Input
   * Data: - Navigate Administration -> content -> content explorer - Click on
   * Upload icon on action bar or right click on the main pane then click Upload
   * Files Expected Outcome: - File Dialog open for user to choose files to upload
   * Step number: 2 Step Name: Step Description: Step 2: Upload files Input Data:
   * - Choose file you want to uploadYou can select many file by Press Control/
   * Shift then click files - Click Open Expected Outcome: - Progress bar appears
   * at the bottomUser can: + Cancel 1 file + Abort all + View tooltip of file
   * size + Mouse over to see containing folder - Files are uploaded successfully
   */
  @Test
  @Tag("eabis")
  public void test05_Upload_AFileInContentExplorer() {
    info("Test 5: Upload a file in Content explorer");

    info("Upload a file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.uploadFile("eXo-Platform.png", true);
    info("Delete a file");
    siteExplorerHome.deleteData("eXo-Platform.png");
  }

  @Test
  @Tag("eabis")
  public void test11_Upload_DeleteAFileInContentExplorer() {
    info("Test 5: Upload a file in Content explorer");

    info("Upload a file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.uploadFile("eXo-Platform.png", true);
    info("Delete a file");
    siteExplorerHome.deleteData("eXo-Platform.png");
  }

  /**
   * <li>Case ID:116676.</li>
   * <li>Test Case Name: Upload a file in Intranet/Document.</li>
   * <li>Case ID:116679.</li>
   * <li>Test Case Name: Delete an uploaded file in intranet/Document.</li>
   * <li>Pre-Condition: some uploaded files exist</li> Step Number: 1 Step Name: -
   * Step Description: Step 1: Open form to upload Input Data: - Login and goto
   * intranet - Click Document in left Navigation - Click on Upload icon on action
   * bar or right click on the main pane then click Upload Files Expected Outcome:
   * - File Dialog open for user to choose files to upload Step number: 2 Step
   * Name: Step Description: Step 2: Upload files Input Data: - Choose file you
   * want to uploadYou can select many file by Press Control/ Shift then click
   * files - Click Open Expected Outcome: - Progress bar appears at the bottomUser
   * can: + Cancel 1 file + Abort all + View tooltip of file size + Mouse over to
   * see containing folder - Files are uploaded successfully
   */
  @Test
  public void test12_Upload_AFileInIntranetDocument() {
    info("Test 12 Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("data/ecms/eXo-Platform.png");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "eXo-Platform.png"))).waitUntil(Condition.visible,
                                                                                           Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("eXo-Platform.png");

  }

  @Test
  @Tag("eabis")
  public void test15_DeleteAFileInIntranetDocument() {
    info("Test 12 Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("eXo-Platform.png");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "eXo-Platform.png"))).waitUntil(Condition.visible,
                                                                                           Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("eXo-Platform.png");

  }

  /**
   * <li>Case ID:116677.</li>
   * <li>Test Case Name: Upload a file in Space/Document.</li>
   * <li>Pre-Condition: - a Space exists</li>
   * <li>Post-Condition:</li>
   * <li>Case ID:116678.</li>
   * <li>Test Case Name: Delete an uploaded file in Space/Document.</li>
   * <li>Pre-Condition: - A space exists, and some files is upload in
   * Space/Document</li> Step Number: 1 Step Name: - Step Description: Step 1:
   * Open form to upload Input Data: - Log in and access a space - Click Document
   * Space application list - Click on Upload icon on action bar or right click on
   * the main pane then click Upload Files Expected Outcome: - File Dialog open
   * for user to choose files to upload Step number: 2 Step Name: Step
   * Description: Step 2: Upload files Input Data: - Choose file you want to
   * uploadYou can select many file by Press Control/ Shift then click files -
   * Click Open Expected Outcome: - Progress bar appears at the bottomUser can: +
   * Cancel 1 file + Abort all + View tooltip of file size + Mouse over to see
   * containing folder - Files are uploaded successfully
   */
  @Test
  public void test13_UploadFileInSpaceDocument() {
    info("Test 13 Upload a file in Space/Document");
    info("Create data test");
    String spaceName = "spaceName" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Finished creating data test");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, content);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToSpace(spaceName);
    refresh();
    siteExplorerHome.uploadFile("eXo-Platform.png");
    $(ELEMENT_ADDRESS_BAR_ICON_VIEW).click();
    $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();
    $(byXpath(ELEMENT_SPACE_DRIVE_NODE_TREE_FILE.replace("${file}", "eXo-Platform.png"))).waitUntil(Condition.visible,
                                                                                                    Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.deleteData("eXo-Platform.png");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceName, false);

  }

  @Test
  @Tag("eabis")
  public void test14_DeleteAFileInSpaceDocument() {
    info("Test 13 Upload a file in Space/Document");
    info("Create data test");
    String spaceName = "spaceName" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Finished creating data test");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, content);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToSpace(spaceName);
    refresh();
    siteExplorerHome.uploadFile("eXo-Platform.png");
    $(ELEMENT_ADDRESS_BAR_ICON_VIEW).click();
    $(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();
    $(byXpath(ELEMENT_SPACE_DRIVE_NODE_TREE_FILE.replace("${file}", "eXo-Platform.png"))).waitUntil(Condition.visible,
                                                                                                    Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.deleteData("eXo-Platform.png");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceName, false);
  }

  @Test
  @Tag("eabis")
  public void test12_1_Upload_AFileDOCInIntranetDocument() {
    info("Test 12 Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("key_word.doc");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "key_word.doc"))).waitUntil(Condition.visible,
                                                                                         Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("key_word.doc");

  }

  @Test
  @Tag("eabis")
  public void test12_2Upload_AFileDOCXInIntranetDocument() {
    info("Test 12 Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("exoTest.docx");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "exoTest.docx"))).waitUntil(Condition.visible,
                                                                                          Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("exoTest.docx");
  }

  @Test
  @Tag("eabis")
  public void test12_3Upload_AFileODTInIntranetDocument() {
    info("Test 12 Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("exoOdtTest.odt");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "exoOdtTest.odt"))).waitUntil(Condition.visible,
                                                                                         Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("exoOdtTest.odt");

  }

  @Test
  @Tag("eabis")
  public void test12_4Upload_AFilePDFInIntranetDocument() {
    info("Test 12 Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("testavatar.pdf");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "testavatar.pdf"))).waitUntil(Condition.visible,
                                                                                         Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("testavatar.pdf");

  }

  @Test
  @Tag("eabis")
  public void test12_5Upload_AFileRTFInIntranetDocument() {
    info("Test 12 Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("exoRtfTest.rtf");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "exoRtfTest.rtf"))).waitUntil(Condition.visible,
                                                                                         Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("exoRtfTest.rtf");

  }

  @Test
  @Tag("eabis")
  public void test12_6Upload_AFileXLSXInIntranetDocument() {
    info("Test 12 Upload a file in Intranet/Document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Upload a file");
    refresh();
    siteExplorerHome.uploadFile("exoXlsxTest.xlsx");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_GRID_LIST_CONTENT.replace("${file}", "exoXlsxTest.xlsx"))).waitUntil(Condition.visible,
                                                                                          Configuration.timeout);
    info("Delete the file");
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("exoXlsxTest.xlsx");

  }
  @Tag("EXOGTN-2345")
  @Test
  public void test13_CreateWebContentWithParticularCharacter(){
    info("Test 3: Create Web Content document");
    String name = "name" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDoc.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDoc.addNewWebContent(name,"&#128522;");
    $(ELEMENT_SOURCE_CONTENT).waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_CONTENT).waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_CONTENT).setValue("&#128522;");
    createNewDoc.saveAndClose();
    siteExplorerHome.verifyContentCreatedSuccessfully(name);
    siteExplorerHome.verifyWebContentInformationCreatedSuccessfully("\uD83D\uDE0A");
    info("Delete file document");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }
}
