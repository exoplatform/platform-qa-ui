package org.exoplatform.platform.qa.ui.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class TribeActivityStream {
  private final TestBase testBase;

  public Button button;

  public static HomePagePlatform homePagePlatform;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase testbase
   */

  public TribeActivityStream(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.button = new Button(testBase);
    this.homePagePlatform = new HomePagePlatform(testBase);

  }

  /**
   * Add text into activity text box
   *
   * @param text String
   */
  public void addText(String text) {
    info("----Add text into activity text box-----");
    SelenideElement frame = $(byXpath("(//*[@class='cke_wysiwyg_frame cke_reset'])[2]")).waitUntil(Condition.visible, openBrowserTimeoutMs);
    switchTo().frame(frame);
    ELEMENT_INPUT_ACTIVITY.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_INPUT_ACTIVITY.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys(text);
    switchTo().defaultContent();
  }

  /**
   * Add link activity
   *
   * @param link String
   */
  public void addLink(String link) {
    info("----Click on Link----");
    ELEMENT_TAB_ADD_LINK.click();

    info("----Input link into link box-----");
    ELEMENT_INPUT_LINK.setValue(link);

    info("----Click attach button-----");
    $(ELEMENT_COMPOSER_ATTACH_LINK_BUTTON).click();
    evt.waitForAndGetElement(By.id("LinkTitle"));
  }

  /**
   * Check if there is an activity in the stream
   *
   * @param name String
   */
  public void checkActivity(String name) {
    info("Verify that the activity of the name:" + name + " is shown");
    $(byText(name)).waitUntil(Condition.visible, openBrowserTimeoutMs);
    info("The activity of the name:" + name + " is shown successfully");
  }

  /**
   * Share activity
   */
  public void shareActivity() {
    info("----Click share button----");
    if (testBase.getBrowser().contains("iexplorer")) {
      info("mouse over and click");

      WebElement el = evt.waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON, 2000, 1, 2);
      el.sendKeys("\n");
    } else
      $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
  }

  /**
   * Add new activity for space
   *
   * @param text String
   * @param link String
   */
  public void addActivity(String text, String link) {
    info("-- Adding an activity--");

    if (text != "" && text != null) {
      info("----Add text into activity text box-----");
      addText(text);
    }
    if (link != "" && link != null) {
      info("----Add link into activity text box-----");
      addLink(link);
    }
    shareActivity();
    info("-- Verify that an activity has been added --");
    $(byText(text)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_COMPOSER_SHARE_BUTTON).shouldBe(Condition.disabled);
    info("The activity is shared success");
  }

  public void addMessage(String text) {
    info("----Add text into activity text box-----");
    sleep(2000);
    SelenideElement frame = $(byXpath("//*[@class='cke_wysiwyg_frame cke_reset']")).waitUntil(Condition.visible, openBrowserTimeoutMs);
    switchTo().frame(frame);
    ELEMENT_INPUT_ACTIVITY.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_INPUT_ACTIVITY.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys(text);
    switchTo().defaultContent();
  }

  public void addTribeActivity(String text, String link) {
    info("-- Adding an activity--");

    if (text != "" && text != null) {
      info("----Add text into activity text box-----");
      addMessage(text);
    }
    if (link != "" && link != null) {
      info("----Add link into activity text box-----");
      addLink(link);
    }
    postActivity();
    info("-- Verify that an activity has been added --");
    sleep(1000);
    $(byText(text)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).shouldBe(Condition.disabled);
    info("The activity is shared success");
  }


  public void postActivity() {
    info("----Click share button----");
    if (testBase.getBrowser().contains("iexplorer")) {
      info("mouse over and click");

      WebElement el = evt.waitForAndGetElement(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON, 2000, 1, 2);
      el.sendKeys("\n");
    } else
      $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).click();
  }

  public void likeActivity(String activity) {

    String id = $(byXpath("//*[@class='description' and text()='${activity}']/preceding::*[@class='heading']//*[@class='btn-group uiDropdownWithIcon actLink ']".replace("${activity}", activity))).waitUntil(Condition.visible, openBrowserTimeoutMs).getAttribute("id").split("dropDownEditActivity")[1];
    $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).click();
    $(byXpath(ELEMENT_UNLIKE_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout);
    /*
     * Step number: 2 Step Name: Check Likes part Step Description: - Check avatar -
     * Mouse over the avatar Input Data: Expected Outcome: - Avatar of liker is
     * added into likes part, the oldest liker is displayed at the right and the
     * newest at the left. - Profile pictures of users popup
     */

    ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
  }

  public void uploadFileActivityStreamDW(String attachedFile) {

    ELEMENT_DW_ADD_FILE_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);
    $(byClassName("file")).uploadFromClasspath(attachedFile);
    $(byXpath(ELEMENT_DW_CHECKING_ATTACHED_FILE.replace("${file}", attachedFile))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_DW_APPLY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();


  }

  public void previewAttachedFile(String attachedFile) {

    String activityId = $(byXpath("//*[@data-original-title='${attachedFile}']/following::*[@class='commentBox '][1]".replace("${attachedFile}", attachedFile))).getAttribute("id").split("CommentBlockBound")[1];
    executeJavaScript("window.scrollBy(0,300)");
    sleep(1000);
    $(byXpath(ELEMENT_DW_PREVIEW_HOVER_ON_UPLOADED_FILE.replace("{id}", activityId))).waitUntil(Condition.visible, openBrowserTimeoutMs).hover();
    $(byXpath(ELEMENT_DW_PREVIEW_UPLOADED_FILE.replace("{id}", activityId))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    sleep(Configuration.timeout);
    $(byText(attachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);

  }

  public void openAttachedFileInDocuments(String attachedFile) {

    ELEMENT_DW_ATTACHED_FILE_PREVIEW_DROPDOWN.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    ELEMENT_DW_ATTACHED_FILE_PREVIEW_OPEN_IN_DOCUMENTS.waitUntil(Condition.visible, openBrowserTimeoutMs).click();

  }

  public void editFileInOnlyOfficeFromDocumentsTab(String attachedFile) {

    ELEMENT_DW_DOCUMENT_EDIT_IN_ONLYOFFICE.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    sleep(2000);
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(Configuration.collectionsTimeout);

  }

  public static void checkOpeningDocumentWithEditOnlineDW(String documentName,String extension, String userName) {
    $(byText(documentName + extension)).waitUntil(exist, openBrowserTimeoutMs);
    $(".editor").waitUntil(visible, 60000).exists();
    switchTo().frame("frameEditor");
    $("#title-user-name").waitUntil(exactText(userName), 120000);
    $("#view-left-menu").parent().find(".tool-menu.left").waitUntil(visible, 120000);
  }

  public void verifyThatWordAttachedFileIsDisplayedInDocuments(String attachedFile) {

    $(byXpath("//*[@class='document-preview-default']//*[@class='nameDoc' and contains(text(),'${attachedFile}')]".replace("${attachedFile}",attachedFile)))
    .waitUntil(Condition.visible, openBrowserTimeoutMs)
    .isDisplayed();

  }

  public void verifyThatPictureAttachedFileIsDisplayedInDocuments(String attachedFile) {

    Assert.assertTrue($(byXpath("//*[@class='document-preview-default']//*[@onclick]")).getAttribute("onclick").contains(attachedFile));

  }

  public void deleteAttachedFileDW(String attachedFile) {

    String activityid = $(byXpath("//*[@data-original-title='${attachedFile}']/following::*[@class='commentBox '][1]".replace("${attachedFile}", attachedFile))).getAttribute("id").split("CommentBlockBound")[1];
    executeJavaScript("window.scrollBy(0,-300)");
    sleep(1000);
    $(By.id(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", activityid))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    $(By.id(ELEMENT_DELETE_ACTIVITY.replace("{id}", activityid))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
    $(byText(attachedFile)).waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);

  }

  public String getActivityIdDW(String activity) {

    executeJavaScript("window.scrollBy(0,150)");
    String id = $(byXpath("//*[@class='description']//*[text()='${activity}']/preceding::*[@class='heading']//*[@class='btn-group uiDropdownWithIcon actLink ']"
            .replace("${activity}", activity)))
            .waitUntil(Condition.visible, openBrowserTimeoutMs).getAttribute("id").split("dropDownEditActivity")[1];

    return id;
  }

  public void likeActivityDW(String activityId) {

    $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", activityId))).click();
    $(byXpath(ELEMENT_UNLIKE_BUTTON.replace("{id}", activityId))).waitUntil(Condition.appears, Configuration.timeout);

    ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
  }

  public void sendActivityKudosDW(String activity, String message) {

    $(byXpath("(//*[@class='description']//*[text()='${activity}']/following::*[@class='SendKudosButtonTemplate VuetifyApp']//button)[1]"
            .replace("${activity}", activity))).click();
    sleep(1000);
    ELEMENT_KUDOS_MESSAGE_DW.waitUntil(Condition.visible, openBrowserTimeoutMs).sendKeys(message);
    sleep(1000);
    ELEMENT_SEND_KUDOS_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(2000);
  }

  public void checkThatUserWholikesActivityIsDisplayedDW(String user, String activity) {

    $(byXpath("(//*[@class='description']//*[contains(text(),'${activity}')]/following::*[@class='listPeopleContent']//*[contains(text(),'${user}')])[1]"
            .replace("${user}", user)
            .replace("${activity}",activity)))
            .waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

  }

  public void checkThatUserWhoSendsAKudosIsDisplayedDW(String user, String activity, String kudosMessage, String userToSend) {

   Assert.assertEquals($(byXpath("(//*[@class='description']//*[contains(text(),'${activity}')]/following::*[contains(text(),'${user}')]/following::*[@class='contentComment'])[1]"
            .replace("${user}", user)
            .replace("${activity}",activity))).getText(), "Kudos à " + userToSend + " : " + kudosMessage) ;

  }

  public void checkThatUserWhoCommentsActivityIsDisplayedDW(String activity, String user, String comment) {

   $(byXpath("(//*[@class='description']//*[contains(text(),'${activity}')]/following::*[@class='author']/a[contains(text(),'${user}')]/following::*[@class='contentComment']/p[contains(text(),'${comment}')])[1]"
           .replace("${activity}", activity)
           .replace("${comment}",comment)
           .replace("${user}",user)))
           .waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

  }

  public void checkThatUserWhoReplyToActivityCommentIsDisplayedDW(String activity, String user, String comment, String reply) {

    $(byXpath("(//*[@class='description']//*[contains(text(),'${activity}')]/following::*[contains(text(),'${comment}')]/following::*[@class='commentRight']//*[@class='author']/a[contains(text(),'${user}')]/following::*[@class='contentComment']/p[contains(text(),'${reply}')])[1]"
            .replace("${activity}", activity)
            .replace("${comment}", comment)
            .replace("${user}",user)
            .replace("${reply}", reply)))
            .waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

  }

  public void checkActivityCommentViaNotificationDW(String user) {

    $(byXpath("//*[@class='listPeopleContent']//*[contains(text(),'${user}')]".replace("${user}", user)))
            .waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

  }

  public void checkThatUserWhoDislikesActivityIsNotDisplayedDW(String user, String activity) {

    $(byXpath("(//*[@class='description']//*[contains(text(),'${activity}')]/following::*[@class='listPeopleContent']//*[contains(text(),'${user}')])[1]"
            .replace("${user}", user)
            .replace("${activity}",activity)))
            .waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);

  }

  public void dislikeActivityDW(String activityId) {

    $(byXpath(ELEMENT_UNLIKE_BUTTON.replace("{id}", activityId))).click();
    $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", activityId))).waitUntil(Condition.appears, Configuration.timeout);

  }

  public void addActivityComment(String activityId, String comment) {

    sleep(2000);
    $(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", activityId))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    // insert comment
    $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", activityId))).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    sleep(3000);
    executeJavaScript("CKEDITOR.instances.CommentTextarea" + activityId + ".insertText(\"" + comment + "\")", "");
    sleep(3000);
    // click on the button comment
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", activityId))).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).pressEnter().waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);

    $(byText(comment)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);
    sleep(1000);
    executeJavaScript("window.scrollBy(0,-2000)");

  }

  public void unlikeActivity(String activity) {

    String id = $(byXpath("//*[@class='description' and text()='${activity}']/preceding::*[@class='heading']//*[@class='btn-group uiDropdownWithIcon actLink ']".replace("${activity}", activity))).waitUntil(Condition.visible, openBrowserTimeoutMs).getAttribute("id").split("dropDownEditActivity")[1];
    $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).click();
    $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout);
    /*
     * Step number: 2 Step Name: Check Likes part Step Description: - Check avatar -
     * Mouse over the avatar Input Data: Expected Outcome: - Avatar of liker is
     * added into likes part, the oldest liker is displayed at the right and the
     * newest at the left. - Profile pictures of users popup
     */

    ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
  }

  public void deleteactivity(String text) {
    // get the id of activity created
    info("-- Editing an activity--");
    homePagePlatform.refreshUntil($(byText(text)), Condition.visible, 500);
    String idActivity = $(byXpath("//*[@class='description' and text()='${activity}']/preceding::*[@class='heading']//*[@class='btn-group uiDropdownWithIcon actLink ']".replace("${activity}", text))).waitUntil(Condition.visible, openBrowserTimeoutMs).getAttribute("id").split("dropDownEditActivity")[1];
    $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
    $(byText(text)).waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
  }

  public void deleteactivityDW(String text) {
    // get the id of activity created
    info("-- Editing an activity--");
    homePagePlatform.refreshUntil($(byText(text)), Condition.visible, 500);
    executeJavaScript("window.scrollBy(0,-500)");
    String idActivity = $(byXpath("//*[@class='description']//*[text()='${activity}']/preceding::*[@class='heading']//*[@class='btn-group uiDropdownWithIcon actLink ']".replace("${activity}", text))).waitUntil(Condition.visible, openBrowserTimeoutMs).getAttribute("id").split("dropDownEditActivity")[1];
    $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
    $(byText(text)).waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
  }

  public void commentActivity(String text) {
    // get the id of activity created
    info("-- Editing an activity--");
    homePagePlatform.refreshUntil($(byText(text)), Condition.visible, 500);
    String idActivity = $(byText(text)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
    $(byText(text)).waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
  }


}
