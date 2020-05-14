package org.exoplatform.platform.qa.ui.platform.ecms;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_CLOSE_DOCUMENT_PREVIEW;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;
/**
 * @author eXo
 */
@Tag("ecms")
@Tag("sniff")
public class EcmsSEPublishActivitiesTestIT extends Base {
  NavigationToolbar navigationToolbar;
  SiteExplorerHome siteExplorerHome;
  CreateNewDocument createNewDocument;
  HomePagePlatform homePagePlatform;
  ActivityStream activityStream;
  ManageLogInOut manageLogInOut;
  ManageAlert manageAlert;
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
  @Test
  @Tag("eabis")
  public void test01_CheckIntranetHomepageAfterAddingAFileContentThenAWebContentThenATagThenAfterPublishWebContentThenAfterEditingTitleOfContent() {
    info("Check intranet homepage after adding a File content");
    info("Get data test");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    String comment0 = "File has been updated.";
    String tag = "tag" + getRandomNumber();
    String secondTags = "secondTags" + getRandomNumber();
    info("Fnishing Getting data test");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(title, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    sleep(2000);
    $(byText(title)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    $(byText(title)).parent().parent().parent().parent().find(byText("Managed Sites")).should(Condition.visible);
    info("Check intranet homepage after Editing title of content");
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
            .find(byText(comment0))
            .should(Condition.visible);
    // delete data
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(title);
    info("Check intranet homepage after adding Web Content");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.selectNode("intranet");
    siteExplorerHome.goToAddNewContent();
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(title, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    activityStream.checkActivityAddWebContent(title, null, null);
    activityStream.checkActivityAddWebContent(title, "0", null);
    info("Check intranet homepage after adding tag to a content");
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
    info("Check intranet homepage after publishing a content");
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
    info("Check intranet homepage after deleting a content");
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_WEBCONTENT_TITLE.replace("{$title}", title))).should(Condition.not(Condition.visible));
  }
  @Test
  @Tag("eabis")
  public void test02_CheckIntranetHomepageAfterAddingACategoryforUploadFile() {
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String fileRecept = "intranet";
    info("Check intranet homepage after adding a category to an uploaded file");
    info("Upload a file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.uploadFile("testavatar.pdf");
    info("Select the file");
    refresh();
    siteExplorerHome.selectNode("testavatar.pdf");
    info("Add a category to the file");
    siteExplorerHome.addCategoryForNode("testavatar.pdf", "intranet");
    $(byXpath("//*[@id=\"UICategoryManager\"]/div[2]/button")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(Configuration.timeout);
    homePagePlatform.goToHomePage();
    $(byText("Category: intranet has been added.")).should(Condition.visible);
    info("Go to the activity and verify that the file's activity is shown");
    waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_FILE_UPLOAD_TITLE.replace("{$title}", "testavatar.pdf")));
    info("Update the File activity after moving a file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    info("Move the file");
    $(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}",
            "testavatar.pdf"))).dragAndDropTo($(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", fileRecept))));
    manageAlert.acceptAlert();
    info("Check the comment on the activity");
    homePagePlatform.goToHomePage();
    executeJavaScript("window.scrollBy(0,300)");
    $(byText("File has been moved to: /sites/intranet/" + "testavatar.pdf")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    info("Delete the file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData("testavatar.pdf");
    info("Check the activity");
    homePagePlatform.goToHomePage();
    $(byXpath(ELEMENT_ACTIVITY_FILE_UPLOAD_TITLE.replace("{$title}", "testavatar.pdf"))).shouldNot(Condition.visible);
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
    info("Update Content activity after moving a content");
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
    info("Delete the file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(title);
  }
}
