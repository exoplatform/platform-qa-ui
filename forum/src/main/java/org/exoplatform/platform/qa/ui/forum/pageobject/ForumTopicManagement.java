package org.exoplatform.platform.qa.ui.forum.pageobject;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.interactions.Actions;

public class ForumTopicManagement {

  private final TestBase       testBase;

  public PlatformPermission    per;

  public ManageAlert           alert;

  public Button                button;

  public PlatformBase          plf;

  ForumPermission              forumPerm;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase TestBase
   */
  public ForumTopicManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.forumPerm = new ForumPermission(testBase);
    this.plf = new PlatformBase(testBase);
  }

  /**
   * Move a topic to a forum
   *
   * @param category String
   * @param forum String
   */
  public void moveTopicToForum(String category, String forum) {
    info("if not found forum");
    if($(byXpath(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}",forum))).is(not(visible))){
      info("Click on Category to expand tree");
      $(byXpath(ELEMENT_MOVE_POPUP_COLLASPE_NODE.replace("${category}",category))).click();
      info("Select the forum");
      $(byXpath(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}",forum))).click();
    }else{
      info("Select the forum");
      $(byXpath(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}",forum))).click();
    }
    info("Finish moving");

  }

  /**
   * Open More Action menu of topic By QuynhPT
   */
  public void openMoreActionMenu() {
    info("Wait More link is shown");
    $(ELEMENT_MORE_ACTION).waitUntil(appears, Configuration.timeout);
    info("Click on More link");
    $(ELEMENT_MORE_ACTION).click();
  }

  /**
   * select a item in More Action menu By QuynhPT
   *
   * @param item specifMoreActionMenuTopic
   */
  public void selectItemMoreActionMenuTopic(specifMoreActionMenuTopic item) {
    openMoreActionMenu();
    info("Select a link in More menu of the topic");
    switch (item) {
    case ADD_POLL:
      info("Click on Add poll button");
      $(ELEMENT_ADD_POLL).click();
      break;
    case EDIT:
      info("Click on Edit topic button");
      $(ELEMENT_EDIT_TOPIC).click();
      break;
    case DELETE:
      info("Click on Delete topic button");
      $(ELEMENT_DELETE_TOPIC).click();
      info("Verify that confirm popup is shown");
      $(byText("Are you sure you want to delete this topic ?")).waitUntil(appears, Configuration.timeout);
      info("Click on OK on Confirm popup");
      $(ELEMENT_OK_DELETE).click();
      $(ELEMENT_OK_DELETE).waitUntil(Condition.disappears, Configuration.timeout);
      break;
    case WATCHES:
      break;
    case LOCK:
      info("Click on lock topic button");
      $(ELEMENT_LOCK_TOPIC).click();
      break;
    case UNLOCK:
      info("Click on unlock topic button");
      $(ELEMENT_UNLOCK_TOPIC).click();
      break;
    case MOVE:
      info("Wait Move topic link is shown");
      $(ELEMENT_MOVE_TOPIC).waitUntil(Condition.appears, Configuration.timeout);
      info("Click on move topic link");
      $(ELEMENT_MOVE_TOPIC).click();
      $(byId("UIForumPopupWindow")).waitUntil(visible,Configuration.timeout);
      break;
    case CLOSE:
      info("Click on Close");
      $(ELEMENT_CLOSE_TOPIC).click();
      break;
    case OPEN:
      info("Click on Close");
      $(ELEMENT_OPEN_TOPIC).click();
      break;
    case STICK:
      break;
    case SPLIT:
      break;
    default:
      break;

    }
  }

  /**
   * Open More Action menu of poll portlet
   */
  public void openMoreActionMenuPoll() {
    info("Wait More link is shown");
    $(ELEMENT_MORE_ACTIONS_POLL).should(Condition.exist);
    info("Click on More link");
    $(ELEMENT_MORE_ACTIONS_POLL).click();
  }

  /**
   * Select a action in More Action menu of Poll portlet
   *
   * @param item specifMoreActionMenuPoll
   */
  public void selectItemMoreActionMenuPoll(specifMoreActionMenuPoll item) {
    openMoreActionMenuPoll();
    info("Select a link in More menu of the poll");
    switch (item) {
    case EDIT:
      info("Click on Edit link");
      $(ELEMENT_EDIT_POLL).click();
      break;
    case CLOSE:
      info("Click on Close link");
      $(ELEMENT_CLOSE_POLL).click();
      break;
    case OPEN:
      info("Click on Open link");
      $(ELEMENT_OPEN_POLL).click();
      break;
    case REMOVE:
      info("Click on Remove link");
      $(ELEMENT_REMOVE_POLL).click();
      break;
    default:
      break;

    }
  }

  /**
   * Close or open a poll
   *
   * @param isClose = true if the poll is closed = false if the poll is opened
   */
  public void closeOpenPoll(boolean isClose) {
    if (isClose) {
      info("Close the poll");
      selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.CLOSE);
      $(ELEMENT_FORUM_POLL_GRIDCLOSE).should(Condition.exist);
    } else {
      info("Open the poll");
      selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.OPEN);
      $(ELEMENT_FORUM_POLL_GRID).should(Condition.exist);
    }

  }

  /**
   * Post a reply
   *
   * @param title String
   * @param content String
   */
  public void postReply(String title, String content) {
    $(ELEMENT_POST_REPLY).click();
    replyTopic(title, content);
  }

  /**
   * Input information for Reply form
   *
   * @param title String
   * @param content String
   */
  public void replyTopic(String title, String content) {
    if (!title.isEmpty())
      $(ELEMENT_TITLE_POST).setValue(title);
    switchTo().frame(0);
    $(byXpath("/html/body")).sendKeys(content);
    switchTo().defaultContent();
    executeJavaScript("window.scrollBy(0,150)");
    $(ELEMENT_POST_FORM_SUBMIT).click();
    info("Verify that the post is created");
  }

  /**
   * Edit a post
   *
   * @param newTitle String
   * @param newContent String
   */
  public void editPost(String newTitle, String newContent) {
    $(byText(newContent)).parent().parent().parent().parent().find(byText("Edit")).click();
    if (!newTitle.isEmpty())
      $(ELEMENT_TITLE_POST).sendKeys(newTitle);
    if (!newContent.isEmpty())
      switchTo().frame(0);
    $(byXpath("/html/body")).sendKeys(newContent);
    switchTo().defaultContent();
    executeJavaScript("window.scrollBy(0,150)");
    $(ELEMENT_POST_FORM_SUBMIT).click();
  }

  /**
   * Quote a post
   *
   * @param title String
   * @param newContent String
   */
  public void quotePost(String title, String newContent) {
    $(byText(title)).parent().parent().parent().parent().find(byText("Quote")).click();
    if (newContent != "")
      switchTo().frame(0);
    $(byXpath("/html/body")).sendKeys(newContent);
    switchTo().defaultContent();
    executeJavaScript("window.scrollBy(0,150)");
    $(ELEMENT_POST_FORM_SUBMIT).click();
  }

  /**
   * Create a private post
   *
   * @param newTitle String
   * @param content String
   */
  public void privatePostfortopic(String newTitle, String content) {
    $(byText("Private")).click();
    if (newTitle != "")
      $(ELEMENT_TITLE_POST).setValue(newTitle);

    if (content != "")
      switchTo().frame(0);
    $(byXpath("/html/body")).sendKeys(content);
    switchTo().defaultContent();
    executeJavaScript("window.scrollBy(0,150)");
    $(ELEMENT_POST_FORM_SUBMIT).click();
  }

  public void privatePostFromPost(String newTitle, String content) {
    $(byText(content)).parent().parent().parent().parent().find(byText("Private")).click();
    if (newTitle != "")
      $(ELEMENT_TITLE_POST).setValue(newTitle);

    if (content != "") {
      switchTo().frame(0);
      $(byXpath("/html/body")).sendKeys(content);
      switchTo().defaultContent();
      executeJavaScript("window.scrollBy(0,1000)");
      executeJavaScript("window.scrollBy(0,1000)");
      ELEMENT_BUTTON_SUBMIT_POST.pressEnter();
    }
  }

  /**
   * Add poll
   *
   * @param question String
   * @param option1 String
   * @param option2 String
   */
  public void addPoll(String question, String option1, String option2) {
    selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.ADD_POLL);
    info("Input a question to poll");
    $(ELEMENT_POLL_QUESTION).val(question);
    info("Input an option 1 to poll");
    $(ELEMENT_POLL_OPTIONS0).val(option1);
    info("Input an option 2 to poll");
    $(ELEMENT_POLL_OPTIONS1).val(option2);
    info("Click on Submit button");
    $(ELEMENT_POLL_SUBMIT).click();
    info("Verify Poll is added");
    $(byText(option1)).should(Condition.exist);
    info("Finished adding poll");

  }

  /**
   * Lock or Unlock a topic By QuynhPT
   *
   * @param islock =true if a topic is locked =false if a topic is unlocked
   */
  public void lockUnlockTopic(boolean islock) {
    if (islock) {
      selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.LOCK);
      $(ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE).waitUntil(Condition.appears, Configuration.timeout);
    } else {
      selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.UNLOCK);
      $(ELEMENT_TOPIC_POST_REPLY_BOTTOM).waitUntil(Condition.appears, Configuration.timeout);
    }
  }

  /**
   * Close or Open a topic
   *
   * @param isClose =true if a topic is closed = false if a topic is opened
   */
  public void closeOpenTopic(boolean isClose) {
    if (isClose) {
      selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.CLOSE);
      evt.waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE);
    } else {
      selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.OPEN);
      evt.waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
    }

  }

  /**
   * Edit Poll
   *
   * @param question String
   * @param option1 String
   * @param option2 String
   */
  public void editPoll(String question, String option1, String option2) {
    info("Click on More Actions and Select Edit button");
    selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.EDIT);
    $(ELEMENT_FORUM_ADDPOLL_QUESTION).val(question);
    info("Input an option 1 to poll");
    $(ELEMENT_POLL_OPTIONS0).val(option1);
    info("Input an option 2 to poll");
    $(ELEMENT_POLL_OPTIONS1).val(option2);
    info("Click on Submit button");
    $(ELEMENT_POLL_SUBMIT).click();
    info("Finished adding poll");
  }

  /**
   * Delete the poll of the topic
   */
  public void deletePoll() {
    selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.REMOVE);
    info("Click on Ok button");
    evt.click(ELEMENT_OK_DELETE);
    info("Finish deleting poll");
  }

  /**
   * Delete a topic
   */
  public void deleteTopic() {
    info("Delete the topic");
    sleep(Configuration.timeout);
    selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.DELETE);
    sleep(Configuration.timeout);
  }

  /**
   * Add a tag gor topic
   *
   * @param name String
   */
  public void addATag(String name) {
    $(ELEMENT_ACTIONBAR_TOPIC_TAG).click();
    $(ELEMENT_ACTIONBAR_TOPIC_TAGNAME).val(name);
    $(ELEMENT_FORUM_TOPIC_ADD_TAG).click();
    $(byText(name)).should(Condition.exist);
  }

  /**
   * addPostSimple
   *
   * @param name String
   * @param message String
   */
  public void addPostSimple(String name, String message) {
    info("Add post simple");

    evt.click(ELEMENT_FORUM_ADDPOST);
    evt.waitForAndGetElement(ELEMENT_FORUM_POST_TITLE, testBase.getDefaultTimeout(), 1);
    evt.type(ELEMENT_FORUM_POST_TITLE, name, true);
    plf.inputFrame(ELEMENT_FORUM_MESSAGE, message);
    // switchToParentWindow();
    evt.waitForAndGetElement(ELEMENT_FORUM_SETTINGS_SUBMIT, testBase.getDefaultTimeout(), 1);
    evt.clickByJavascript(ELEMENT_FORUM_SETTINGS_SUBMIT, 2);
    // click(ELEMENT_FORUM_SETTINGS_SUBMIT);
  }

  /**
   * addTopicSimple
   *
   * @param name String
   * @param message String
   */
  public void addTopicSimple(String name, String message) {
    evt.click(ELEMENT_FORUM_ADDTOPIC);
    evt.type(ELEMENT_FORUM_TOPIC_TITLE, name, true);
    plf.inputFrame(ELEMENT_FORUM_MESSAGE, message);
    // switchToParentWindow();
    // click(ELEMENT_FORUM_SETTINGS_SUBMIT);
    do {
      evt.waitForAndGetElement(ELEMENT_FORUM_SETTINGS_SUBMIT, testBase.getDefaultTimeout(), 1);
      evt.clickByJavascript(ELEMENT_FORUM_SETTINGS_SUBMIT, 2);
    } while (evt.waitForAndGetElement(ELEMENT_FORUM_TOPIC_LINK.replace("${name}", name),
                                      testBase.getDefaultTimeout(),
                                      1) == null);
    info("The topic is created successfully");
  }

  /**
   * Reply the topic
   *
   * @param newTitle String
   * @param newMessg String
   * @param pathFile String
   * @param fileName String
   */
  public void replyTopic(String newTitle, String newMessg, String pathFile, String fileName) {
    info("Click on Post Reply button");
    // click(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
    $(ELEMENT_TOPIC_POST_REPLY_BOTTOM).click();
    info("Verify that the pop up is shown");
    $(ELEMENT_TOPIC_NEW_POST_TITLE).should(Condition.exist);
    info("Refresh the page");
    refresh();
    if (!newTitle.isEmpty()) {
      info("Input the title:" + newTitle);
      $(ELEMENT_TOPIC_NEW_POST_TITLE_FIELD).clear();
      $(ELEMENT_TOPIC_NEW_POST_TITLE_FIELD).val(newTitle);
    }

    if (!newMessg.isEmpty()) {
      info("Input the message:" + newMessg);
      plf.inputFrame(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR, newMessg);
    }

    if (!pathFile.isEmpty() || !fileName.isEmpty()) {
      info("click on Attached file button");
      $(ELEMENT_START_TOPIC_ATTACH_FILE).waitUntil(visible,Configuration.timeout).click();
      info("Verify that upload button is shown");
      $(ELEMENT_UPLOAD_POPUP_FILE).should(Condition.exist);
      info("Attached file");
      testBase.attachFile(pathFile, fileName);
      info("Verify that upload popup is closed");
      $(ELEMENT_UPLOAD_POPUP_FILE).shouldNot(Condition.exist);
    }
    info("click on Submit button");
    $(ELEMENT_SUBMIT_BUTTON).click();
    info("Verify that the replying is created");
    $(byText(newMessg)).should(Condition.exist);
    info("Reply topic successfully");
  }

  /**
   * Start a Topic By QuynhPT
   *
   * @param title String
   * @param message String
   * @param fileName String
   * @param pathFile String
   */
  public void startTopic(String title, String message, String pathFile, String... fileName) {
    info("Verify that the pop up is shown");
    $(ELEMENT_START_TOPIC_POPUP_TITLE_FILED).waitUntil(Condition.appears, Configuration.timeout);
    info("Input the title:" + title);
    $(ELEMENT_START_TOPIC_POPUP_TITLE_FILED).val(title);
    info("Input the message:" + message);
    $(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR).click();
    sleep(Configuration.timeout);
    switchTo().frame(0);
    $(byXpath("/html/body")).sendKeys(message);
    switchTo().defaultContent();
    info("click on Attached file button");
    sleep(Configuration.timeout);
    if(fileName[0]!=""){
    for (int i=0;i<=fileName.length-1;i++){
      $(ELEMENT_START_TOPIC_ATTACH_FILE).shouldBe(visible);
      Actions action = new Actions(this.testBase.getExoWebDriver().getWebDriver());
      $(ELEMENT_START_TOPIC_ATTACH_FILE).waitUntil(visible, Configuration.timeout);
      action.moveToElement($(ELEMENT_START_TOPIC_ATTACH_FILE)).click().perform();
      $(By.className("file")).uploadFromClasspath(fileName[i]);
      $(ELEMENT_SAVE_BTN).click();
    }}
    $(ELEMENT_SUBMIT_BUTTON).click();
    $(ELEMENT_SUBMIT_BUTTON).waitUntil(Condition.disappear, Configuration.timeout);
    info("Verify that the topic is created");
    $(By.linkText(title)).should(exist);
    info("Start topic successfully");
  }

  /**
   * Rate a topic
   *
   * @param name String
   */
  public void rateTopic(String name) {
    $(ELEMENT_MORE_ACTION).click();
    $(ELEMENT_TOPIC_RATE).click();
    $(ELEMENT_FORUM_VOTE_MARK).click();

  }

  /**
   * Edit a topic
   *
   * @param newTitle String
   * @param newContent String
   */
  public void editTopic(String newTitle, String newContent) {
    selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.EDIT);
    if (!newTitle.isEmpty())
      $(ELEMENT_START_TOPIC_POPUP_TITLE_FILED).setValue(newTitle);
    if (!newContent.isEmpty())
      plf.inputFrame(ELEMENT_POST_CONTENT, newContent);
    info("Click on Submit button");
    $(ELEMENT_SUBMIT_BUTTON).click();
    info("All changes are saved");
  }

  /**
   * Check display of manage topic
   *
   * @param forum String
   * @param topic String
   * @param isDisplay boolean
   */
  public void checkDisplayOfTopicManage(String forum, String topic, boolean isDisplay) {
    info("check display of manage topic");
    evt.click(ELEMENT_FORUM_TOPIC_LINK.replace("${name}", topic), 0, true);
    if (isDisplay) {
      evt.waitForAndGetElement(ELEMENT_MORE_ACTION);
      evt.waitForAndGetElement(ELEMENT_MODERATOR);
    } else {
      evt.waitForElementNotPresent(ELEMENT_MORE_ACTION);
      evt.waitForElementNotPresent(ELEMENT_MODERATOR);
    }
  }

  /**
   * Check enable of post reply
   *
   * @param topic String
   * @param isEnable boolean
   */
  public void checkEnableOfPostReply(String topic, boolean isEnable) {
    info("check enable of post reply");
    evt.click(ELEMENT_FORUM_TOPIC_LINK.replace("${name}", topic), 0, true);
    if (isEnable) {
      evt.waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
    } else {
      evt.waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE);
    }
  }

  /**
   * Check enable of view post
   *
   * @param forum String
   * @param topic String
   * @param isEnable boolean
   */
  public void checkEnableOfViewPost(String forum, String topic, boolean isEnable) {
    info("check enable of view post");
    evt.click(ELEMENT_FORUM_TOPIC_LINK.replace("${name}", forum), 0, true);
    if (isEnable) {
      evt.waitForAndGetElement(ELEMENT_START_TOPIC_BTN);
      evt.click(ELEMENT_FORUM_TOPIC_LINK.replace("${name}", topic), 0, true);
      evt.waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
    } else {
      evt.waitForElementNotPresent(ELEMENT_FORUM_TOPIC_LINK.replace("${name}", topic));
    }
  }

  /**
   * Edit permission of topic
   *
   * @param topic String
   * @param groupPath String
   * @param member String
   * @param isView boolean
   * @param isPost boolean
   */
  public void editPermOfTopic(String topic, String groupPath, String member, boolean isView, boolean isPost) {
    info("edit permission of topic:" + topic);
    evt.click(ELEMENT_FORUM_TOPIC_LINK.replace("${name}", topic), 0, true);
    selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.EDIT);
    forumPerm.selectPermGroupMemberInTopic(groupPath, member, isView, isPost);
    evt.click(ELEMENT_SUBMIT_BUTTON);
  }

  /**
   * Open permissions tab in Add/Edit Topic
   */
  public void goToPermissions() {
    info("Permissions page");
    evt.click(ELEMENT_TOPIC_PERMISSION_TAB);
  }

  /**
   * Select User in Permission tab
   */
  public void gotoUserSelectorInPermissionTab() {
    info("-- Go to wiki home page --");
    evt.click(ELEMENT_TOPIC_PERMISSION_TAB_USER_SELECTOR);
  }

  /**
   * function: Search user in User Selection Form in Topic Permission
   * @param searchOption String
   * @param user String
   */

  public void searchUser(String user, String searchOption) {
    info("--Search user " + user + "--");
    evt.type(ELEMENT_TOPIC_PERMISSION_INPUT_SEARCH_USER_NAME, user, true);
    evt.select(ELEMENT_TOPIC_PERMISSION_SELECT_SEARCH_OPTION, searchOption);
    evt.click(ELEMENT_TOPIC_PERMISSION_SEARCH_ICON);
    evt.waitForTextPresent(user);
  }

  public void searchUserNotFound(String user, String searchOption) {
    info("--Search user " + user + "--");
    evt.type(ELEMENT_TOPIC_PERMISSION_INPUT_SEARCH_USER_NAME, user, true);
    evt.select(ELEMENT_TOPIC_PERMISSION_SELECT_SEARCH_OPTION, searchOption);
    evt.click(ELEMENT_TOPIC_PERMISSION_SEARCH_ICON);
    evt.waitForTextNotPresent(user);
  }

  /**
   * Close User Selector page
   */
  public void closeUserSelector() {
    info("-- Go to User Selector page --");
    evt.click(ELEMENT_TOPIC_PERMISSION_CLOSE_USER_SELETOR);

  }

  /**
   * Cancel Add/Edit Forum form
   */
  public void cancelAddEditTopic() {
    info("Cancel Add or Edit Forum");
    evt.click(ELEMENT_TOPIC_CANCEL);
  }

  /**
   * list sublinks in More Action menu of Topic
   *
   * @author quynhpt
   */
  public enum specifMoreActionMenuTopic {
    EDIT, ADD_POLL, LOCK, UNLOCK, CLOSE, OPEN, STICK, SPLIT, MOVE, DELETE, WATCHES;
  }

  /**
   * list sublinks of MoreAction of Poll portlet
   *
   * @author quynhpt
   */
  public enum specifMoreActionMenuPoll {
    EDIT, CLOSE, OPEN, REMOVE;
  }
}
