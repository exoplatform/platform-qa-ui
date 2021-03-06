package org.exoplatform.platform.qa.ui.forum.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ForumForumManagement {

  private final TestBase       testBase;

  public ManageAlert           alert;

  public ForumHomePage         forumHP;

  public ForumPermission       forumPerm;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase TestBase
   */
  public ForumForumManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.forumHP = new ForumHomePage(testBase);
    this.forumPerm = new ForumPermission(testBase);
  }

  /**
   * Add a new forum By QuynhPT
   *
   * @param nameForum String
   * @param order String
   * @param description String
   */
  public void addForumSimple(String nameForum, String order, String description) {
    // TODO Auto-generated method stub
    info("Add forum simple");

    $(ELEMENT_ACTIONBAR_ADDFORUM).waitUntil(Condition.appears, Configuration.timeout);
    info("click on Add forum button");
    $(ELEMENT_ACTIONBAR_ADDFORUM).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("input the title for the forum");
    $(ELEMENT_ADDFORUM_POPUP_TITLE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).val(nameForum);
    info("check and input Oder field");
    $(ELEMENT_ADDFORUM_POPUP_ORDER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).val(order);
    info("check and input description");
    $(ELEMENT_ADDFORUM_POPUP_DESCRIPTION).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).val(description);
    info("Click on Save button");
    $(ELEMENT_ADDFORUM_POPUP_SAVE_BUTTON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Finish adding new forum");
  }

  /**
   * select a item in More Action menu for a forum By QuynhPT
   *
   * @param item specifMoreActionMenu
   */
  public void selectItemMoreActionMenu(specifMoreActionMenu item) {
    info("Wait More link is shown");
    $(ELEMENT_MORE_ACTION).should(Condition.exist);
    info("Click on More link");
    $(ELEMENT_MORE_ACTION).click();
    info("Select a link on More menu");
    switch (item) {
    case START_TOPIC:
      info("wait Start Topic button is shown");
      $(ELEMENT_START_TOPIC_BUTTON).waitUntil(Condition.appears, Configuration.timeout);
      info("click on Start Topic button");
      $(ELEMENT_START_TOPIC_BUTTON).click();
      info("Verify that the popup is shown");
      $(ELEMENT_START_TOPIC_POPUP_TITLE).should(Condition.exist);
      info("The popup is shown successfully");
      break;
    case EDIT:
      info("click on Edit link");
      $(ELEMENT_EDIT_FORUM).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_EDIT_FORUM).click();
      info("Verify that Edit popup is shown");
      $(ELEMENT_EDITFORUM_POPUP_TITLE).should(Condition.exist);
      info("The popup is shown successfully");
      break;
    case DELETE:
      info("click on Delete link");
      $(ELEMENT_DELETE_FORUM).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_DELETE_FORUM).click();

      info("Verify that Confirm popup is shown");
      $(byText("Are you sure you want to delete this forum ?")).waitUntil(Condition.appears, Configuration.timeout);
      info("Click on OK button of Confirm popup");
      $(ELEMENT_OK_DELETE).click();
      info("Finish deleting the forum");
      break;
    case WATCHES:
      break;
    case LOCK:
      $(ELEMENT_LOCK_FORUM).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_LOCK_FORUM).click();
      break;
    case UNLOCK:
      $(ELEMENT_UNLOCK_FORUM).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_UNLOCK_FORUM).click();
      break;
    case CLOSE:
      $(ELEMENT_CLOSE_FORUM).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_CLOSE_FORUM).click();
      break;
    case OPEN:
      $(ELEMENT_OPEN_FORUM).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_OPEN_FORUM).click();
      break;
    case EXPORT_FORUM:
      break;
    case MOVE:
      info("Wait Move link is shown");
      $(ELEMENT_MOVE_FORUM).waitUntil(Condition.appears, Configuration.timeout);
      info("Click on Move link");
      $(ELEMENT_MOVE_FORUM).click();
      info("Verify that Move popup is shown");
      $(ELEMENT_POPUP_MOVE_FORUM).should(Condition.exist);
      info("The popup is shown successfully");
      break;
    case BANNED_IPS:
      break;
    default:
      break;
    }
  }

  /**
   * Edit a forum
   *
   * @param newName String
   * @param order String
   * @param newDescription String
   */
  public void editForum(String newName, String order, String newDescription) {
    // TODO Auto-generated method stub
    selectItemMoreActionMenu(specifMoreActionMenu.EDIT);
    info("Input a new title");
    evt.type(ELEMENT_ADDFORUM_POPUP_TITLE, newName, true);
    info("check and input Oder field");
    if (!order.isEmpty())
      evt.type(ELEMENT_ADDFORUM_POPUP_ORDER, order, true);
    info("check and input description");
    if (!newDescription.isEmpty())
      evt.type(ELEMENT_ADDFORUM_POPUP_DESCRIPTION, newDescription, true);
    info("Click on Save button");
    evt.click(ELEMENT_ADDFORUM_POPUP_SAVE_BUTTON);

    info("Finish edting new forum");

  }

  /**
   * Open or Close a forum By QuynhPT
   *
   * @param isClose =true if a forum is closed =false if a forum is opened
   */
  public void closeAndOpen(boolean isClose) {
    if (isClose) {
      selectItemMoreActionMenu(specifMoreActionMenu.CLOSE);
      $(ELEMENT_FORUM_START_TOPIC_DISABLE).waitUntil(Condition.appear, Configuration.timeout);
    } else {
      selectItemMoreActionMenu(specifMoreActionMenu.OPEN);
      $(ELEMENT_FORUM_START_TOPIC_BUTTON).waitUntil(Condition.appear, Configuration.timeout);
    }
  }

  /**
   * Delete a forum in the list By QuynhPT
   *
   * @param name String
   */
  public void deleteForum(String name) {
    selectItemMoreActionMenu(specifMoreActionMenu.DELETE);
    info("Verify that the forum is deleted");
    // evt.waitForElementNotPresent(ELEMENT_FORUM_FORUM_NAME_LINK.replace("${name}",
    // name));
    $(byText(name)).shouldNot(Condition.exist);
  }

  /**
   * function: move a forum from a category to another category
   *
   * @param forum String
   * @param destination String
   */
  public void moveForum(String forum, String destination) {
    info("move forum to category " + destination);
    selectItemMoreActionMenu(specifMoreActionMenu.MOVE);
    evt.click(By.linkText(destination));
    evt.waitForElementNotPresent(ELEMENT_POPUP_MOVE_FORUM);
    forumHP.goToCategory(destination);
    $(byText(forum)).waitUntil(Condition.appears, Configuration.timeout);
    info("Move forum successfully");
  }

  /**
   * Open Start Topic popup By QuynhPT
   */
  public void goToStartTopic() {
    info("Go to start topic from more action");
    selectItemMoreActionMenu(specifMoreActionMenu.START_TOPIC);
  }

  /**
   * Lock or Unlock a forum By QuynhPT
   *
   * @param islock =true if a forum is locked =false if a forum is unlocked
   */
  public void lockAndUnlock(boolean islock) {
    if (islock) {
      selectItemMoreActionMenu(specifMoreActionMenu.LOCK);
      $(ELEMENT_FORUM_START_TOPIC_DISABLE).should(Condition.exist);
    } else {
      selectItemMoreActionMenu(specifMoreActionMenu.UNLOCK);
      $(ELEMENT_FORUM_START_TOPIC_BUTTON).should(Condition.exist);
    }
  }

  /**
   * list sublinks in More Action menu of Forum
   *
   * @author quynhpt
   */
  public enum specifMoreActionMenu {
    START_TOPIC, EDIT, LOCK, UNLOCK, CLOSE, OPEN, MOVE, DELETE, EXPORT_FORUM, WATCHES, BANNED_IPS;
  }
}
