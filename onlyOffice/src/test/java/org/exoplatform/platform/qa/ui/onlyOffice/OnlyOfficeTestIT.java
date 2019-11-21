package org.exoplatform.platform.qa.ui.onlyOffice;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.onlyOffice.pageobject.OnlyOfficeActivityStream;
import org.exoplatform.platform.qa.ui.onlyOffice.pageobject.OnlyOfficeDocumentApplication;
import org.exoplatform.platform.qa.ui.onlyOffice.pageobject.OnlyOfficeEditingPage;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.onlyOffice.OnlyOfficeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;

public class OnlyOfficeTestIT extends Base {

  HomePagePlatform homePagePlatform;
  NavigationToolbar navigationToolbar;
  SiteExplorerHome siteExplorerHome;
  ManageLogInOut manageLogInOut;
  ConnectionsManagement connectionsManagement;
  ActivityStream activityStream;
  SpaceManagement spaceManagement;
  OnlyOfficeDocumentApplication onlyOfficeDocumentApplication;
  OnlyOfficeActivityStream onlyOfficeActivityStream;
  OnlyOfficeEditingPage onlyOfficeEditingPage;


  @BeforeEach
  public void setupBeforeMethod() {

    homePagePlatform = new HomePagePlatform(this);
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    manageLogInOut = new ManageLogInOut(this);
    spaceManagement = new SpaceManagement(this);
    activityStream = new ActivityStream(this);
    onlyOfficeDocumentApplication = new OnlyOfficeDocumentApplication(this);
    onlyOfficeActivityStream = new OnlyOfficeActivityStream(this);
    onlyOfficeEditingPage = new OnlyOfficeEditingPage(this);
    manageLogInOut.signInCas("root", "gtn");
    connectionsManagement = new ConnectionsManagement(this);
    if ($(ELEMENT_SKIP_BUTTON).is(exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
  }

  @Test
  /**
   * EditOnline_BTN_US01
   * Upload a document & click on Edit Online button in Document Application/View Document and check document is correctly opened
   */
  public void EditOnlineDocumentFromDocumentsApp() {
    String document = "OO_test";
    String extension = ".docx";
    String userName = PLFData.DATA_NAME_ROOT;
    info("Upload document");
    homePagePlatform.goToDocuments();
    siteExplorerHome.uploadFile(document + extension);
    info("Open document");
    siteExplorerHome.checkButtonDocument(document);
    siteExplorerHome.clickViewDocumentButton();
    switchTo().window(1);
    onlyOfficeDocumentApplication.clickEditOnine();
    switchTo().window(2);
    refresh();
    onlyOfficeEditingPage.checkOpeningDocumentWithEditOnline(document, extension, userName);
    switchTo().window(2).close();
    switchTo().window(1).close();
    switchTo().window(0);
    info("Delete document");
    siteExplorerHome.checkButtonDocument(document);
    siteExplorerHome.clickDeleteButtonDocument();
    manageLogInOut.signOut();
  }

  @Test
  /**
   * EditOnline_BTN_US02_(01)
   * Upload a document & click on Edit Online button from Activity Stream, from Document Preview and check document is correctly opened
   */
  public void EditOnlineDocumentFromActivityStreamAndPreview() {
    String document = "OO_test";
    String extension = ".docx";
    String userName = PLFData.DATA_NAME_ROOT;
    info("Edit Online from document from AS");
    activityStream.uploadFileFromAS(document + extension);
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_CHECK_FILE_IS_UPLOADED.replace("$doc", document + extension))), visible, timeout);
    onlyOfficeActivityStream.editOnlineFromAS();
    switchTo().window(1);
    onlyOfficeEditingPage.checkOpeningDocumentWithEditOnline(document, extension, userName);
    switchTo().window(1).close();
    switchTo().window(0);
    refresh();
    info("Edit Online from document preview");
    ELEMENT_OPEN_DOCUMENT_PREVIEW_FROM_AS.waitUntil(visible, 30000).doubleClick();
    onlyOfficeActivityStream.editOnlineFromPreview();
    switchTo().window(1);
    onlyOfficeEditingPage.checkOpeningDocumentWithEditOnline(document, extension, userName);
    switchTo().window(1).close();
    switchTo().window(0);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW_FROM_AS.waitUntil(visible, collectionsTimeout).click();
    activityStream.deleteDocumentFromAS(document + extension);
    manageLogInOut.signOut();
  }

  @Test
  /**
   * EditOnline_BTN_US02_(02)
   * Upload a document in a Space & click on Edit Online button from Space Activity Stream and check document is correctly opened
   */
  public void EditOnlineDocumentFromSpaceAS() {
    String document = "OO_test";
    String extension = ".docx";
    String userName = PLFData.DATA_NAME_ROOT;
    String spaceTestName = "space_1_" + getRandomNumber();
    info("Edit Online document from Space AS");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceTestName, "test", 5000);
    activityStream.uploadFileFromAS(document + extension);
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_CHECK_FILE_IS_UPLOADED.replace("$doc", document + extension))), visible, timeout);
    onlyOfficeActivityStream.editOnlineFromAS();
    switchTo().window(1);
    onlyOfficeEditingPage.checkOpeningDocumentWithEditOnline(document, extension, userName);
    switchTo().window(1).close();
    switchTo().window(0);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceTestName, false);
    manageLogInOut.signOut();
  }

  @Test
  /**
   * EditOnline_BTN_US02_(03)
   * Upload a document & click on Edit Online button in Document Application and check document is correctly opened
   */
  public void EditOnlineDocumentFromDocumentApplication() {
    String document = "OO_test";
    String extension = ".docx";
    String userName = PLFData.DATA_NAME_ROOT;
    info("Edit Online document from Document Application");
    homePagePlatform.goToDocuments();
    siteExplorerHome.uploadFile(document + extension);
    siteExplorerHome.checkButtonDocument(document);
    siteExplorerHome.clickViewDocumentButton();
    switchTo().window(1);
    onlyOfficeDocumentApplication.clickEditOnine();
    switchTo().window(2);
    onlyOfficeEditingPage.checkOpeningDocumentWithEditOnline(document, extension, userName);
    switchTo().window(2).close();
    switchTo().window(1).close();
    switchTo().window(0);
    siteExplorerHome.checkButtonDocument("OO_test");
    siteExplorerHome.clickDeleteButtonDocument();
    manageLogInOut.signOut();
  }

  @Test
  /**
   * EditOnline_BTN_US03_(01)
   * Check alignment of "Edit Online Button from AS"
   **/
  public void EditOnlineButtonAlignmentFromAS() {
    String document = "OO_test";
    String extension = ".docx";
    info("Alignment of Edit Online button in AS");
    activityStream.uploadFileFromAS(document + extension);
    refresh();
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_CHECK_FILE_IS_UPLOADED.replace("$doc", document + extension))), visible, timeout);
    onlyOfficeActivityStream.checkAlignmentElement(ELEMENT_DOWNLOAD_BUTTON_FROM_AS_TEXT, ELEMENT_OPEN_BUTTON_FROM_AS_TEXT, ELEMENT_EDIT_ONLINE_BUTTON_FROM_AS_TEXT);
    activityStream.deleteDocumentFromAS(document + extension);
    manageLogInOut.signOut();
  }

  @Test
  /**
   * EditOnline_BTN_US03_(02)
   * Check alignment of "Edit Online Button from Document Preview"
   */
  public void EditOnlineButtonAlignmentFromDocumentPreview() {
    String document = "OO_test";
    String extension = ".docx";
    info("Alignment of Edit Online button in AS");
    activityStream.uploadFileFromAS(document + extension);
    refresh();
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_CHECK_FILE_IS_UPLOADED.replace("$doc", document + extension))), visible, timeout);
    ELEMENT_OPEN_DOCUMENT_PREVIEW_FROM_AS.waitUntil(visible, 30000).doubleClick();
    ELEMENT_EDIT_ONLINE_BUTTON_FROM_PREVIEW_TEXT.waitUntil(visible, openBrowserTimeoutMs);
    onlyOfficeActivityStream.checkAlignmentElement(ELEMENT_DOWNLOAD_BUTTON_FROM_PREVIEW_TEXT, ELEMENT_OPEN_IN_DOCUMENT_FROM_PREVIEW_TEXT, ELEMENT_EDIT_ONLINE_BUTTON_FROM_PREVIEW_TEXT);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW_FROM_AS.waitUntil(visible, collectionsTimeout).click();
    refresh();
    activityStream.deleteDocumentFromAS(document + extension);
    manageLogInOut.signOut();
  }

  @Test
  /**
   * EditOnline_BTN_US03_(03)
   * Check alignment of "Edit Online Button from Document application"
   */
  public void EditOnlineButtonAlignmentFromDocumentApp() {
    String document = "OO_test";
    String extension = ".docx";
    info("Alignment of Edit Online button in DocumentApp");
    homePagePlatform.goToDocuments();
    siteExplorerHome.uploadFile(document + extension);
    siteExplorerHome.checkButtonDocument(document);
    siteExplorerHome.clickViewDocumentButton();
    switchTo().window(1);
    onlyOfficeActivityStream.checkAlignmentElement(ELEMENT_SHARE_BUTTON_FROM_DOCUMENT_APP, ELEMENT_EDIT_ONLINE_BUTTON_FROM_DOCUMENT_APP, ELEMENT_OPEN_BUTTON_FROM_DOCUMENT_APP);
    onlyOfficeActivityStream.checkAlignmentElement(ELEMENT_OPEN_BUTTON_FROM_DOCUMENT_APP, ELEMENT_UPLOAD_NEW_VERSION_BUTTON_FROM_DOCUMENT_APP, ELEMENT_PERMISSION_BUTTON_FROM_DOCUMENT_APP);
    onlyOfficeActivityStream.checkAlignmentElement(ELEMENT_PERMISSION_BUTTON_FROM_DOCUMENT_APP, ELEMENT_VERSION_BUTTON_FROM_DOCUMENT_APP, ELEMENT_EDIT_DOCUMENT_PROP_BUTTON_FROM_DOCUMENT_APP);
    switchTo().window(1).close();
    switchTo().window(0);
    siteExplorerHome.checkButtonDocument(document);
    siteExplorerHome.clickDeleteButtonDocument();
    manageLogInOut.signOut();
  }

  @Test
  /**
   * EditOnline_BTN_US05_(01)
   * Check that editing online with OO is possible for ".docx",".pptx" and ".xlsx" documents
   */
  public void EditOnlineSupportedFile() {
    String documentDocx = "docx_test";
    String extensionDocx = ".docx";
    String documentPptx = "pptx_test";
    String extensionPptx = ".pptx";
    String documentXlsx = "xlsx_test";
    String extensionXlsx = ".xlsx";
    String userName = PLFData.DATA_NAME_ROOT;
    info("Editing online of the documents");
    onlyOfficeActivityStream.editingDocumentWithOnlyOfficeFromAS(documentXlsx, extensionXlsx, userName);
    onlyOfficeActivityStream.editingDocumentWithOnlyOfficeFromAS(documentPptx, extensionPptx, userName);
    onlyOfficeActivityStream.editingDocumentWithOnlyOfficeFromAS(documentDocx, extensionDocx, userName);
    manageLogInOut.signOut();
  }

  @Test
  /**
   * EditOnline_BTN_US05_(02)
   * Check that editing online with OO is not possible for different documents
   */
  public void EditOnlineNotSupportedFile() {
    String documentDoc = "doc_test";
    String extensionDoc = ".doc";
    String documentPpt = "ppt_test";
    String extensionPpt = ".ppt";
    String documentXls = "xls_test";
    String extensionXls = ".xls";
    String pictureJpeg = "jpeg_test";
    String extensionJpeg = ".jpeg";
    String documentPdf = "pdf_test";
    String extensionPdf = ".pdf";
    String documentTxt = "txt_test";
    String extensionTxt = ".txt";
    String documentOdp = "odp_test";
    String extensionOdp = ".odp";
    String documentOds = "ods_test";
    String extensionOds = ".ods";
    String documentOdt = "odt_test";
    String extensionOdt = ".odt";
    info("No editing online of document ");
    onlyOfficeActivityStream.notEditingDocumentWithOnlyOfficeFromAS(documentXls,extensionXls);
    onlyOfficeActivityStream.notEditingDocumentWithOnlyOfficeFromAS(documentDoc,extensionDoc);
    onlyOfficeActivityStream.notEditingDocumentWithOnlyOfficeFromAS(documentPpt,extensionPpt);
    onlyOfficeActivityStream.notEditingDocumentWithOnlyOfficeFromAS(pictureJpeg,extensionJpeg);
    onlyOfficeActivityStream.notEditingDocumentWithOnlyOfficeFromAS(documentPdf,extensionPdf);
    onlyOfficeActivityStream.notEditingDocumentWithOnlyOfficeFromAS(documentTxt,extensionTxt);
    onlyOfficeActivityStream.notEditingDocumentWithOnlyOfficeFromAS(documentOdp,extensionOdp);
    onlyOfficeActivityStream.notEditingDocumentWithOnlyOfficeFromAS(documentOds,extensionOds);
    onlyOfficeActivityStream.notEditingDocumentWithOnlyOfficeFromAS(documentOdt,extensionOdt);
    manageLogInOut.signOut();
  }
}