package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_CLOSE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_CLOSE_DOCUMENT_PREVIEW;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.ELEMENT_CLOSE_PUBLICATION_POPUP;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.ELEMENT_FILEFORM_BLANK_CONTENT2;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

/**
 * @author eXo
 */
@Tag("ecms")
@Tag("sniff")
public class EcmsSEPublishActivitiesTestIT extends Base {
  NavigationToolbar navigationToolbar;

  SiteExplorerHome  siteExplorerHome;

  CreateNewDocument createNewDocument;

  HomePagePlatform  homePagePlatform;

  ActivityStream    activityStream;

  ManageLogInOut    manageLogInOut;

  ManageAlert       manageAlert;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDocument = new CreateNewDocument(this);
    homePagePlatform = new HomePagePlatform(this);
    activityStream = new ActivityStream(this);
    manageLogInOut = new ManageLogInOut(this);
    manageAlert = new ManageAlert(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:116665.</li>
   * <li>Test Case Name: Check intranet homepage after adding a File content.</li>
   * Step Number: 1 Step Name: - Add a File Content Step Description: - Connect to
   * Intranet - Navigate Administration -> Content -> Sites Explorer - Click [New
   * content]: and choose File - Fill the info and click [Save & Close] - Back to
   * the Homepage Input Data: Expected Outcome: - A new Content activity is added
   * in the activity stream - Informations displayed in the featured content are
   * :1. File icon 2.File's name3.File description if exist 4.Version (if exist)
   * and file size
   */

  @Test
  @Tag("eabis")
  public void test01_CheckIntranetHomepageAfterAddingAFileContent() {

    info("Test 1: Check intranet homepage after adding a File content");
    info("Get data test");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Fnishing Getting data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(title, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    sleep(Configuration.timeout);
    $(byText(title)).waitUntil(Condition.visible,Configuration.timeout).should(Condition.visible);
    $(byText(title)).parent().parent().parent().parent().find(byText("Managed Sites")).should(Condition.visible);
    // delete data
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(title);
  }

  /**
   * <li>Case ID:116666.</li>
   * <li>Test Case Name: Check intranet homepage after adding Web Content.</li>
   * <li>Case ID:116671.</li>
   * <li>Test Case Name: Check intranet homepage after publishing a content.</li>
   * Step Number: 1 Step Name: - Add a FreeLayout Web Content Step Description: -
   * Connect to Intranet - Navigate Administration -> Content -> Sites Explorer -
   * Click [New content]: and choose Web Content - Fill the info and click [Save &
   * Close] - Back to the Homepage Input Data: Expected Outcome: - A new Content
   * activity is added in the activity stream - Informations displayed in the
   * featured content are :1. Icon corresponding to the content type2. Name of the
   * content3. First 4 lines of content's summary4. Type of the content5.Version6.
   * Current status Step number: 2 Step Name: Publish file content Step
   * Description: - Navigate Admin -> Content -> Content Explorer - Choose the
   * content and click [Publication] in Action ba - Change the status of the
   * shared content to Published - Back to the homepage Input Data: Expected
   * Outcome: - The content activity is updated in the activity stream with the
   * new summary - A comment is added: Document has been published.
   */
  @Test
  public void test03_CheckIntranetHomepageAfterAddingWebContent() {
    info("Test 3: Check intranet homepage after adding Web Content");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.selectNode("intranet");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(title, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    activityStream.checkActivityAddWebContent(title, null, null);
    // delete data
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(title);
  }

  @Test
  @Tag("eabis")
  public void test04_CheckIntranetHomepageAfterPublishWebContent() {
    info("Test 3: Check intranet homepage after adding Web Content");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.selectNode("intranet");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(title, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    sleep(Configuration.timeout);
    activityStream.checkActivityAddWebContent(title, null, null);
    info("Test 4: Check intranet homepage after publishing a content");
    String comment = "Document has been published.";
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.selectNode(title);
    siteExplorerHome.goToPublication();
    siteExplorerHome.changeStatusPulication("Published");
    sleep(Configuration.timeout);
    homePagePlatform.goToHomePage();
    refresh();
    activityStream.checkActivityAddWebContent(title, "1", "Published");
    $(byXpath(ELEMENT_ACTIVITY_COMMENT.replace("${title}", title)
                                      .replace("${comment}", comment))).waitUntil(Condition.visible, Configuration.timeout);
    // delete data
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(title);
  }

  /**
   * <li>Case ID:116668.</li>
   * <li>Test Case Name: Check intranet homepage after Editing title of
   * content.</li> Step Number: 1 Step Name: - Add Content Step Description: -
   * Connect to Intranet - Navigate Admin -> Content -> Content Explorer - Click
   * [New content]: and choose a template, for example: File - Fill the info and
   * click [Save & Close] - Back to the Homepage Input Data: Expected Outcome: - A
   * new Content activity is added in the activity stream - Informations displayed
   * in the featured content are :1. File icon 2.File's name3.File description if
   * exist 4.Version (if exist) and file size Step number: 2 Step Name: Edit title
   * of content Step Description: - Navigate Admin -> Content -> Content Explorer
   * - Navigate to the content and click Edit in Action Bar - Edit the title of
   * the shared content - Back to the homepage Input Data: Expected Outcome: - The
   * content activity is updated in the activity stream with the new title - A
   * comment is added:File has been updated.
   */
  @Test
  public void test06_CheckIntranetHomepageAfterEditingTitleOfContent() {
    info("Test 6: Check intranet homepage after Editing title of content");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    String comment = "File has been updated.";
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.selectNode("intranet");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(title, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    $(byText(title)).should(Condition.visible);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.selectNode("intranet");
    siteExplorerHome.selectNode(title);
    siteExplorerHome.editDocument("", newContent);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    $(byText(title)).parent()
                    .parent()
                    .parent()
                    .parent()
                    .parent()
                    .parent()
                    .parent()
                    .parent()
                    .parent()
                    .find(byText(comment))
                    .should(Condition.visible);
    // delete data
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(title);
  }

  /**
   * <li>Case ID:116669.</li>
   * <li>Test Case Name: Check intranet homepage after adding tag to a
   * content.</li> Step Number: 1 Step Name: - Add a Content Step Description: -
   * Connect to Intranet - Navigate Admin -> Content -> Content Explorer - Click
   * [New content]: and choose a template - Fill the info and click [Save & Close]
   * - Back to the Homepage Input Data: Expected Outcome: - A new Content activity
   * is added in the activity stream* Step number: 2 Step Name: - Add a tag to
   * content Step Description: - Connect to Intranet - Navigate Admin -> Content
   * -> Content Explorer - Navigate to the content - Click [Collbration] on Action
   * Bar and click [Add tag] - Back to the homepage Input Data: Expected Outcome:
   * - The content of the content activity isn't updated in the activity stream -
   * A comment is added: Tag: $value has been added.* Step number: 3 Step Name: -
   * Add more 2 tags Step Description: -Add two other tags by using common, then
   * click Add - Back to the homepage Input Data: Expected Outcome: - One comment
   * isadded the activity: Tag: $value, $value have been added.
   */
  @Test
  @Tag("eabis")
  public void test07_CheckIntranetHomepageAfterAddingTagToAContent() {
    info("Test 7: Check intranet homepage after adding tag to a content");
    info("Get data test");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String tag = "tag" + getRandomNumber();
    String secondTags = "secondTags" + getRandomNumber();
    info("Fnishing Getting data test");

    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.selectNode("intranet");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewFile(title, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    refresh();
    activityStream.checkActivityAddWebContent(title, null, null);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.selectNode(title);
    siteExplorerHome.addTag(tag);
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_COMMENT.replace("${title}", title)
                                      .replace("${comment}", "Tag: " + tag + " has been added."))).waitUntil(Condition.visible,
                                                                                                             Configuration.timeout);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.selectNode(title);
    siteExplorerHome.addTag(secondTags);
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_COMMENT.replace("${title}", title)
                                      .replace("${comment}", "Tag: " + secondTags + " has been added."))).waitUntil(
                                                                                                                    Condition.visible,
                                                                                                                    Configuration.timeout);
    // delete data
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(title);
  }

  /**
   * <li>Case ID:116670.</li>
   * <li>Test Case Name: Check intranet homepage after deleting a content.</li>
   * Step Number: 1 Step Name: - Add a Content Step Description: - Connect to
   * Intranet - Navigate Admin -> Content -> Content Explorer - Click [New
   * content]: and choose a template - Fill the info and click [Save & Close] -
   * Back to the Homepage Input Data: Expected Outcome: - A new Content activity
   * is added in the activity stream Step number: 2 Step Name: Delete content Step
   * Description: - Navigate Admin -> Content -> Content Explorer - Navigate to
   * the Content to be deleted, - Right click and select [Delete] - Click OK to
   * confirm - Back to the Homepage Input Data: Expected Outcome: - The Content
   * activity related to the content is removed from the activity stream
   */
  @Test
  public void test08_CheckIntranetHomepageAfterDeletingAContent() {
    info("Test 8: Check intranet homepage after deleting a content");
    info("Get data test");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Fnishing Getting data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.selectNode("intranet");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewFile(title, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    refresh();
    activityStream.checkActivityAddWebContent(title, null, null);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(title);
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_WEBCONTENT_TITLE.replace("{$title}", title))).should(Condition.not(Condition.visible));
  }

  /*
   * Step Number: 1 Step Name: Step Description: - Connect to Intranet - Navigate
   * Admin -> Content -> Content Explorer - Select a folder and click [Upload] in
   * Action Bar - Browse to select a file - Back to the Home page Input Data:
   * Expected Outcome: - A File activity is added to the activity stream
   */
  @Test
  @Tag("eabis")
  public void test09_CheckIntranetHomepageAfterUploadingAFile() {
    info("Test 9: Check intranet homepage after Uploading a file");

    info("Create data test");
    info("Finish creating data test");

    info("Upload a file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    sleep(Configuration.timeout);
    siteExplorerHome.uploadFile("testavatar.pdf");

    info("Go to the activity and verify that the file's activity is shown");
    homePagePlatform.goToHomePage();
    waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_FILE_UPLOAD_TITLE.replace("{$title}", "testavatar.pdf")));

    info("Delete the file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("testavatar.pdf");
  }

  /**
   * <li>Case ID:116673.</li>
   * <li>Test Case Name: Check intranet homepage after adding a category to an
   * uploaded file.</li> Step number: 2 Step Name: - Add a Category to the
   * uploaded file Step Description: - Connect to Intranet - Navigate Admin ->
   * Content -> Content Explorer - Select the uploaded file and click [Categories]
   * in Action Bar - Open [Select Catogory] Tab and Add a category to the file -
   * Click [Close] Button to finish adding category - Back to the Homepage Input
   * Data: Expected Outcome: - The content of theFile activity isn't updated in
   * the activity stream - A comment is added: Category: $value has been added.
   */
  @Test
  @Tag("eabis")
  public void test11_CheckIntranetHomepageAfterAddingACategoryforUploadFile() {
    info("Test 11: Check intranet homepage after adding a category to an uploaded file");
    info("Upload a file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.uploadFile("testavatar.pdf");
    info("Select the file");
    siteExplorerHome.selectNode("testavatar.pdf");
    info("Add a category to the file");
    siteExplorerHome.addCategoryForNode("testavatar.pdf", "intranet");
    $(byXpath("//*[@id=\"UICategoryManager\"]/div[2]/button")).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(Configuration.timeout);
    homePagePlatform.goToHomePage();
    $(byText("Category: intranet has been added.")).should(Condition.visible);
    info("Delete the file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("testavatar.pdf");

  }

  /**
   * <li>Case ID:116675.</li>
   * <li>Test Case Name: Check intranet homepage after deleting an uploaded
   * file.</li>
   */
  @Test
  @Tag("eabis")
  public void test12_CheckIntranetHomepageAfterDeletingAFile() {
    info("Test 12:Check intranet homepage after deleting an uploaded file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.uploadFile("testavatar.pdf");
    info("Delete the file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("testavatar.pdf");
    info("Check the activity");
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_FILE_UPLOAD_TITLE.replace("{$title}", "testavatar.pdf"))).shouldNot(Condition.visible);
  }

  /**
   * <li>Case ID:116680.</li>
   * <li>Test Case Name: Edit a content from the Content activity.</li> Step
   * Number: 1 Step Name: Step Description: ; - Connect to Intranet - From the
   * Content activity, click on the link "Edit" Input Data: Expected Outcome: -
   * The content explorer is opened to edit the content
   */

  public void test13_EditAContentFromTheContentActivity() {
    info("Test 13: Edit a content from the Content activity");

    info("Create data test");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    info("Finish creating data test");

    info("Create a new Content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewFile(title, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    refresh();
    activityStream.checkActivityAddWebContent(title, null, null);
    $(byText(title)).click();
    switchTo().frame($(ELEMENT_FILEFORM_BLANK_CONTENT2));
    $(byXpath("/html/body")).sendKeys(newContent);
    switchTo().defaultContent();
    createNewDocument.saveAndClose();

    info("Delete the file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(title);
  }

  /**
   * <li>Case ID:116681.</li>
   * <li>Test Case Name: View a content from the Content activity.</li>
   */
  @Test
  public void test14_ViewAContentFromTheContentActivity() {
    info("Test 14: View a content from the Content activity");
    info("Create data test");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("Finish creating data test");

    info("Create a new Content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewFile(title, content);
    createNewDocument.saveAndClose();

    info("View the content from the activity");
    homePagePlatform.goToHomePage();
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byXpath(ELEMENT_ACTIVITY_VIEW_A_NODE.replace("{$title}", title))).click();
    $(byText(content)).should(Condition.visible);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    info("Delete the file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(title);
  }

  /**
   * <li>Case ID:116684.</li>
   * <li>Test Case Name: Update the File activity after moving a file.</li> Step
   * Number: 1 Step Name: Step Description: - Connect to Intranet - Open Sites
   * explorer - Move the file - back to the Homepage Input Data: Expected Outcome:
   * - The content of the file content activity isn't updated in the activity
   * stream - A comment is added: File has been moved to: $valuewhere $value =
   * path of the file.d
   */
  @Test
  @Tag("eabis")
  public void test16_UpdateTheFileActivityAfterMovingAFile() {
    info("Test 16 Update the File activity after moving a file");
    info("Create data test");
    String fileRecept = "intranet";
    info("Finish creating data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.uploadFile("testavatar.pdf");
    info("Move the file");
    $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}",
                                                            "testavatar.pdf"))).dragAndDropTo($(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", fileRecept))));
    manageAlert.acceptAlert();
    info("Check the comment on the activity");
    homePagePlatform.goToHomePage();
    executeJavaScript("window.scrollBy(0,300)");
    $(byText("File has been moved to: /sites/intranet/" + "testavatar.pdf")).waitUntil(Condition.visible, Configuration.timeout);
    info("Delete the file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath(fileRecept, "Site Management");
    siteExplorerHome.deleteData("testavatar.pdf");
  }

  /**
   * <li>Case ID:116685.</li>
   * <li>Test Case Name: Update Content activity after moving a content.</li> Step
   * Number: 1 Step Name: Step Description: - Connect to Intranet - Open Site
   * explorer - Move the content - Back to the homepage Input Data: Expected
   * Outcome: - The content of the content activity isn't updated in the activity
   * stream - A comment is added: Publication has been moved to: $value$value =
   * path of the content.
   */
  @Test
  @Tag("eabis")
  public void test17_UpdateContentActivityAfterMovingAContent() {
    info("Test 17 Update Content activity after moving a content");

    info("Create data test");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String fileRecept = "intranet";
    info("Finish creating data test");

    info("Create a new Content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewFile(title, content);
    createNewDocument.saveAndClose();

    info("Move the content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}",
                                                            title))).dragAndDropTo($(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", fileRecept))));
    manageAlert.acceptAlert();

    info("Check the comment on the activity");
    homePagePlatform.goToHomePage();
    sleep(Configuration.timeout);
    $(byXpath(ELEMENT_ACTIVITY_COMMENT.replace("${title}", title).replace("${comment}",
                                                                          "Publication has been moved to: /sites/intranet/"
                                                                              + title))).waitUntil(Condition.visible,
                                                                                                   Configuration.timeout);
    sleep(Configuration.timeout);
    info("Delete the file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath(fileRecept, "Site Management");
    siteExplorerHome.deleteData(title);

  }
}
