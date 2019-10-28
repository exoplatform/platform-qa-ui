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
import static org.junit.jupiter.api.Assertions.assertEquals;


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
  /*
   * EditOnline_BTN_US01
   * Upload a document & click on Edit Online button in Document Application/View Document and check document is correctly opened
   */
  public void EditOnlineDocumentFromDocumentsApp() {
    String document = "OO_test";
    String userName = PLFData.DATA_NAME_ROOT;
    info("Upload document");
    homePagePlatform.goToDocuments();
    siteExplorerHome.uploadFile(document + ".docx");
    info("Open document");
    siteExplorerHome.checkButtonDocument(document);
    siteExplorerHome.clickViewDocumentButton();
    switchTo().window(1);
    onlyOfficeDocumentApplication.clickEditOnine();
    switchTo().window(2);
    refresh();
    onlyOfficeEditingPage.checkOpeningDocumentWithEditOnline(document, userName);
    switchTo().window(2).close();
    switchTo().window(1).close();
    switchTo().window(0);
    info("Delete document");
    siteExplorerHome.checkButtonDocument(document);
    siteExplorerHome.clickDeleteButtonDocument();
    manageLogInOut.signOut();
  }

  @Test
  /*
   * EditOnline_BTN_US02_(01)
   * Upload a document & click on Edit Online button from Activity Stream, from Document Preview and check document is correctly opened
   */
  public void EditOnlineDocumentFromActivityStreamAndPreview() {
    String document = "OO_test.docx";
    String check_document = "OO_test";
    String userName = PLFData.DATA_NAME_ROOT;

    info("Edit Online from document from AS");
    activityStream.uploadFileFromAS(document);
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_CHECK_FILE_IS_UPLOADED.replace("$doc", document))), exist, timeout);

    /*
    onlyOfficeActivityStream.editOnlineFromAS();
    switchTo().window(1);
    onlyOfficeEditingPage.checkOpeningDocumentWithEditOnline(check_document, userName);
    switchTo().window(1).close();
    switchTo().window(0);
    refresh();
    */

    info("Edit Online from document preview");
    $(".previews").waitUntil(exist, timeout).click();
    onlyOfficeActivityStream.editOnlineFromPreview();
    switchTo().window(1);
    onlyOfficeEditingPage.checkOpeningDocumentWithEditOnline(check_document, userName);

    switchTo().window(1).close();
    switchTo().window(0);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW_FROM_AS.waitUntil(visible, collectionsTimeout).click();
    activityStream.deleteDocumentFromAS(document);
    manageLogInOut.signOut();
  }

  @Test
  /*
   * EditOnline_BTN_US02_(02)
   * Upload a document in a Space & click on Edit Online button from Space Activity Stream and check document is correctly opened
   */
  public void EditOnlineDocumentFromSpaceAS() {
    String document = "OO_test.docx";
    String check_document = "OO_test";
    String userName = PLFData.DATA_NAME_ROOT;
    String spaceTestName = "space_1_" + getRandomNumber();
    info("Edit Online document from Space AS");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceTestName, "test", 5000);
    activityStream.uploadFileFromAS(document);
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_CHECK_FILE_IS_UPLOADED.replace("$doc", document))), visible, timeout);
    onlyOfficeActivityStream.editOnlineFromAS();
    switchTo().window(1);
    onlyOfficeEditingPage.checkOpeningDocumentWithEditOnline(check_document, userName);
    switchTo().window(1).close();
    switchTo().window(0);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceTestName, false);
    manageLogInOut.signOut();
  }

  @Test
  /*
   * EditOnline_BTN_US02_(03)
   * Upload a document & click on Edit Online button in Document Application and check document is correctly opened
   */
  public void EditOnlineDocumentFromDocumentApplication() {
    String document = "OO_test.docx";
    String check_document = "OO_test";
    String userName = PLFData.DATA_NAME_ROOT;
    info("Edit Online document from Document Application");
    homePagePlatform.goToDocuments();
    siteExplorerHome.uploadFile(document);
    siteExplorerHome.checkButtonDocument("OO_test");
    siteExplorerHome.clickViewDocumentButton();
    switchTo().window(1);
    onlyOfficeDocumentApplication.clickEditOnine();
    switchTo().window(2);
    onlyOfficeEditingPage.checkOpeningDocumentWithEditOnline(check_document, userName);
    switchTo().window(2).close();
    switchTo().window(1).close();
    switchTo().window(0);
    siteExplorerHome.checkButtonDocument("OO_test");
    siteExplorerHome.clickDeleteButtonDocument();
    manageLogInOut.signOut();
  }

  /*
   * EditOnline_BTN_US03_(01)
   * Check alignment of "Edit Online Button from AS"
   */
  @Test
  public void EditOnlineButtonAlignmentFromAS() {
    String document = "OO_test.docx";
    info("Alignment of Edit Online button in AS");
    activityStream.uploadFileFromAS(document);
    refresh();
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_CHECK_FILE_IS_UPLOADED.replace("$doc", document))), visible, timeout);
    onlyOfficeActivityStream.checkAlignmentButtonDocument(ELEMENT_DOWNLOAD_BUTTON_FROM_AS_TEXT, ELEMENT_OPEN_BUTTON_FROM_AS_TEXT, ELEMENT_EDIT_ONLINE_BUTTON_FROM_AS_TEXT);
    info("Alignment of Edit Online button in AS");
    ELEMENT_OPEN_DOCUMENT_PREVIEW_FROM_AS.waitUntil(visible, 30000).doubleClick();
    ELEMENT_EDIT_ONLINE_BUTTON_FROM_PREVIEW_TEXT.waitUntil(visible,openBrowserTimeoutMs);
    onlyOfficeActivityStream.checkAlignmentButtonDocument(ELEMENT_DOWNLOAD_BUTTON_FROM_PREVIEW_TEXT, ELEMENT_OPEN_IN_DOCUMENT_FROM_PREVIEW_TEXT, ELEMENT_EDIT_ONLINE_BUTTON_FROM_PREVIEW_TEXT);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW_FROM_AS.waitUntil(visible, collectionsTimeout).click();
    refresh();
    activityStream.deleteDocumentFromAS(document);
    manageLogInOut.signOut();
  }



}


