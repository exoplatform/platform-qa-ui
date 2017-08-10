package org.exoplatform.platform.qa.ui.forum.pageobject;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

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
   * @param dr
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
   * @param category
   * @param forum
   */
  public void moveTopicToForum(String category, String forum) {
    info("if not found forum");
    if (evt.waitForAndGetElement(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}", forum), 3000, 0) == null) {
      info("Click on Category to expand tree");
      evt.click(ELEMENT_MOVE_POPUP_COLLASPE_NODE.replace("${category}", category));
      info("Select the forum");
      evt.click(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}", forum));
    } else {
      info("Select the forum");
      evt.click(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}", forum));
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
   * @param item
   */
  public void selectItemMoreActionMenuTopic(specifMoreActionMenuTopic item) {
    openMoreActionMenu();
    info("Select a link in More menu of the topic");
    switch (item) {
    case ADD_POLL:
      info("Click on Add poll button");
      evt.click(ELEMENT_ADD_POLL);
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
      break;
    case WATCHES:
      break;
    case LOCK:
      info("Click on lock topic button");
      evt.click(ELEMENT_LOCK_TOPIC);
      break;
    case UNLOCK:
      info("Click on unlock topic button");
      evt.click(ELEMENT_UNLOCK_TOPIC);
      break;
    case MOVE:
      info("Wait Move topic link is shown");
      evt.waitForAndGetElement(ELEMENT_MOVE_TOPIC);
      info("Click on move topic link");
      evt.click(ELEMENT_MOVE_TOPIC);
      break;
    case CLOSE:
      info("Click on Close");
      evt.click(ELEMENT_CLOSE_TOPIC);
      break;
    case OPEN:
      info("Click on Close");
      evt.click(ELEMENT_OPEN_TOPIC);
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
    evt.waitForAndGetElement(ELEMENT_MORE_ACTIONS_POLL);
    info("Click on More link");
    evt.click(ELEMENT_MORE_ACTIONS_POLL);
  }

  /**
   * Select a action in More Action menu of Poll portlet
   *
   * @param item
   */
  public void selectItemMoreActionMenuPoll(specifMoreActionMenuPoll item) {
    openMoreActionMenuPoll();
    info("Select a link in More menu of the poll");
    switch (item) {
    case EDIT:
      info("Click on Edit link");
      evt.click(ELEMENT_EDIT_POLL);
      break;
    case CLOSE:
      info("Click on Close link");
      evt.click(ELEMENT_CLOSE_POLL);
      break;
    case OPEN:
      info("Click on Open link");
      evt.click(ELEMENT_OPEN_POLL);
      break;
    case REMOVE:
      info("Click on Remove link");
      evt.click(ELEMENT_REMOVE_POLL);
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
      evt.waitForAndGetElement(ELEMENT_FORUM_POLL_GRIDCLOSE);
    } else {
      info("Open the poll");
      selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.OPEN);
      evt.waitForAndGetElement(ELEMENT_FORUM_POLL_GRID);
    }

  }

  /**
   * Post a reply
   *
   * @param title
   * @param content
   */
  public void postReply(String title, String content) {
    evt.click(ELEMENT_POST_REPLY);
    replyTopic(title, content);
  }

  /**
   * Input information for Reply form
   *
   * @param title
   * @param content
   */
  public void replyTopic(String title, String content) {
    if (!title.isEmpty())
      evt.type(ELEMENT_TITLE_POST, title, true);
    plf.inputFrame(ELEMENT_POST_CONTENT, content);
    evt.click(ELEMENT_POST_FORM_SUBMIT);
    info("Verify that the post is created");
    evt.waitForAndGetElement(ELEMENT_POST_IN_TOPIC.replace("{$title}", title).replace("{$content}", content));
  }

  /**
   * Edit a post
   *
   * @param title
   * @param newTitle
   * @param newContent
   */
  public void editPost(String title, String newTitle, String newContent) {
    evt.click(ELEMENT_EDIT_POST.replace("{$title}", title));
    if (!newTitle.isEmpty())
      evt.type(ELEMENT_TITLE_POST, newTitle, true);
    if (!newContent.isEmpty())
      plf.inputFrame(ELEMENT_POST_CONTENT, newContent);
    evt.click(ELEMENT_POST_FORM_SUBMIT);
  }

  /**
   * Quote a post
   *
   * @param title
   * @param newContent
   */
  public void quotePost(String title, String newContent) {
    evt.click(By.xpath(ELEMENT_QUOTE_POST.replace("{$title}", title)));

    if (newContent != "")
      plf.inputFrame(ELEMENT_POST_CONTENT, newContent);

    evt.click(ELEMENT_POST_FORM_SUBMIT);
  }

  /**
   * Create a private post
   *
   * @param titlePost
   * @param newTitle
   * @param content
   */
  public void privatePost(String titlePost, String newTitle, String content) {
    evt.click(By.xpath(ELEMENT_PRIVATE_POST.replace("{$title}", titlePost)));

    if (newTitle != "")
      evt.type(ELEMENT_TITLE_POST, newTitle, true);

    if (content != "")
      plf.inputFrame(ELEMENT_POST_CONTENT, content);

    evt.click(ELEMENT_POST_FORM_SUBMIT);
  }

  /**
   * Add poll
   *
   * @param question
   * @param option1
   * @param option2
   */
  public void addPoll(String question, String option1, String option2) {
    selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.ADD_POLL);
    info("Input a question to poll");
    evt.type(ELEMENT_POLL_QUESTION, question, true);
    info("Input an option 1 to poll");
    evt.type(ELEMENT_POLL_OPTIONS0, option1, true);
    info("Input an option 2 to poll");
    evt.type(ELEMENT_POLL_OPTIONS1, option2, true);
    info("Click on Submit button");
    evt.click(ELEMENT_POLL_SUBMIT);
    evt.waitForElementNotPresent(ELEMENT_POLL_SUBMIT);
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
      evt.waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE);
    } else {
      selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.UNLOCK);
      evt.waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
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
   * @param question
   * @param option1
   * @param option2
   */
  public void editPoll(String question, String option1, String option2) {
    selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.EDIT);
    info("Refresh the page");
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    evt.waitForAndGetElement(ELEMENT_POLL_POPUP_TITLE);
    info("Input a question to poll");
    evt.waitForAndGetElement(ELEMENT_POLL_QUESTION).clear();
    evt.type(ELEMENT_POLL_QUESTION, question, true);
    info("Input an option 1 to poll");
    evt.waitForAndGetElement(ELEMENT_POLL_OPTIONS0).clear();
    evt.type(ELEMENT_POLL_OPTIONS0, option1, true);
    info("Input an option 2 to poll");
    evt.waitForAndGetElement(ELEMENT_POLL_OPTIONS1).clear();
    evt.type(ELEMENT_POLL_OPTIONS1, option2, true);
    info("Click on Submit button");
    evt.click(ELEMENT_POLL_SUBMIT);
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
    selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.DELETE);
  }

  /**
   * Add a tag gor topic
   *
   * @param name
   */
  public void addATag(String name) {
    evt.click(ELEMENT_ACTIONBAR_TOPIC_TAG);
    evt.type(ELEMENT_ACTIONBAR_TOPIC_TAGNAME, name, true);
    evt.click(ELEMENT_FORUM_TOPIC_ADD_TAG);
    evt.waitForAndGetElement(ELEMENT_ACTIONBAR_TOPIC_TAGPRESENT.replace("${tag}", name));
  }

  /**
   * addPostSimple
   *
   * @param name
   * @param message
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
   * @param name
   * @param message
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
   * @param newTitle
   * @param newMessg
   * @param pathFile
   * @param fileName
   */
  public void replyTopic(String newTitle, String newMessg, String pathFile, String fileName) {
    info("Click on Post Reply button");
    // click(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
    evt.clickByJavascript(ELEMENT_TOPIC_POST_REPLY_BOTTOM, 2);
    info("Verify that the pop up is shown");
    evt.waitForAndGetElement(ELEMENT_TOPIC_NEW_POST_TITLE);
    info("Refresh the page");
    this.testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    if (!newTitle.isEmpty()) {
      info("Input the title:" + newTitle);
      evt.waitForAndGetElement(ELEMENT_TOPIC_NEW_POST_TITLE_FIELD).clear();
      evt.type(ELEMENT_TOPIC_NEW_POST_TITLE_FIELD, newTitle, true);
    }

    if (!newMessg.isEmpty()) {
      info("Input the message:" + newMessg);
      plf.inputFrame(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR, newMessg);
    }

    if (!pathFile.isEmpty() || !fileName.isEmpty()) {
      info("click on Attached file button");
      evt.click(ELEMENT_START_TOPIC_ATTACH_FILE);
      info("Verify that upload button is shown");
      evt.waitForAndGetElement(ELEMENT_UPLOAD_POPUP_FILE);
      info("Attached file");
      testBase.attachFile(pathFile, fileName);
      info("Verify that upload popup is closed");
      evt.waitForElementNotPresent(ELEMENT_UPLOAD_POPUP_FILE);
    }
    info("click on Submit button");
    // click(ELEMENT_SUBMIT_BUTTON);
    evt.clickByJavascript(ELEMENT_SUBMIT_BUTTON, 2);
    info("Verify that the replying is created");
    evt.waitForAndGetElement(ELEMENT_TOPIC_REPPLY_CONTENT.replace("${content}", newMessg));
    info("Reply topic successfully");
  }

  /**
   * Start a Topic By QuynhPT
   *
   * @param title
   * @param message
   */
  public void startTopic(String title, String message, String pathFile, String fileName) {
    info("Verify that the pop up is shown");
    $(ELEMENT_START_TOPIC_POPUP_TITLE_FILED).waitUntil(Condition.appears, Configuration.timeout);

    info("Input the title:" + title);

    $(ELEMENT_START_TOPIC_POPUP_TITLE_FILED).val(title);

    info("Input the message:" + message);
    $(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR).click();
    $(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR).sendKeys(message);

    info("click on Attached file button");
    $(ELEMENT_START_TOPIC_ATTACH_FILE).click();
    File file = $(By.className("file")).uploadFromClasspath("topic_attachment.txt");
    assertTrue(file.exists());
    $(ELEMENT_SAVE_BTN).click();

    $(ELEMENT_SUBMIT_BUTTON).click();
    $(ELEMENT_SUBMIT_BUTTON).waitUntil(Condition.disappear, Configuration.timeout);
    info("Verify that the topic is created");

    $(By.linkText(title)).should(exist);
    info("Start topic successfully");
  }

  /**
   * Rate a topic
   *
   * @param name
   */
  public void rateTopic(String name, String starType) {
    evt.click(ELEMENT_ACTIONBAR_TOPIC_RATE);
    evt.click(ELEMENT_FORUM_VOTE_MARK.replace("${star}", starType));

  }

  /**
   * Edit a topic
   *
   * @param newTitle
   * @param newContent
   */
  public void editTopic(String newTitle, String newContent) {
    selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.EDIT);
    if (!newTitle.isEmpty())
      $(ELEMENT_START_TOPIC_POPUP_TITLE_FILED).setValue(newTitle);
    if (!newContent.isEmpty())
      $(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR).click();
    $(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR).sendKeys(newContent);
    info("Click on Submit button");
    $(ELEMENT_SUBMIT_BUTTON).click();
    // click(ELEMENT_SUBMIT_BUTTON);
    info("All changes are saved");
  }

  /**
   * Check display of manage topic
   *
   * @param forum
   * @param topic
   * @param isDisplay
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
   * @param topic
   * @param isEnable
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
   * @param forum
   * @param topic
   * @param isEnable
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
   * @param topic
   * @param groupPath
   * @param member
   * @param isView
   * @param isPost
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
