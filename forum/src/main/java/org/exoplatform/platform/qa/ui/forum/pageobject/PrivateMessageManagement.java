package org.exoplatform.platform.qa.ui.forum.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;

import org.openqa.selenium.By;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class PrivateMessageManagement {
  private final TestBase       testBase;

  public PlatformPermission    per;

  public ManageAlert           alert;

  public Button                button;

  public ForumPermission       forumPerm;

  public PlatformBase          plf;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param dr
   */
  public PrivateMessageManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.forumPerm = new ForumPermission(testBase);
    this.plf = new PlatformBase(testBase);

  }

  /**
   * Go send message tab
   */
  public void goSendMessages() {
    evt.click(ELEMENT_TABS_SENT_MESSAGES);
  }

  /**
   * Go inbox tab
   */
  public void goInbox() {
    evt.click(ELEMENT_TABS_INBOX);
  }

  /**
   * Go compose tab
   */
  public void goComposeMessage() {
    evt.click(ELEMENT_TABS_COMPOSE_MESSAGE);
  }

  /**
   * Go to Inbox tab
   */
  public void gotoInboxTab() {
    evt.click(ELEMENT_TABS_INBOX);
  }

  /**
   * Write message
   *
   * @param contact
   * @param title
   * @param content
   */
  public void writeMessage(String contact, String title, String content) {
    evt.type(ELEMENT_SEND_TO_MESSAGE, contact, true);
    evt.type(ELEMENT_TITLE_MESSAGE, title, true);
    plf.inputFrame(ELEMENT_MESSAGE_CONTENT, content);
    evt.click(ELEMENT_SEND_BUTTON);
    button.ok();

  }

  /**
   * Write message to group
   *
   * @param groupPath
   * @param member
   * @param title
   * @param content
   */
  public void writeMessageToGroup(String groupPath, String member, String title, String content) {
    forumPerm.selectPermGroupMemberMes(groupPath, member);
    evt.type(ELEMENT_TITLE_MESSAGE, title, true);
    plf.inputFrame(ELEMENT_MESSAGE_CONTENT, content);
    evt.click(ELEMENT_SEND_BUTTON);
    button.ok();

  }

  /**
   * Check inbox message
   *
   * @param contact
   * @param title
   * @param content
   */
  public void checkInboxMessage(String contact, String title, String content) {
    evt.click(By.xpath(ELEMENT_TITLE_AUTHORS_INBOX.replace("{$title}", title).replace("{$author}", contact)));
    evt.waitForAndGetElement(By.xpath(ELEMENT_CONTENT_INBOX.replace("{$content}", content)));
  }

  /**
   * Check display of message
   *
   * @param title
   * @param author
   * @param isDisplay
   */
  public void checkDisplayOfMessage(String title, String author, boolean isDisplay) {
    if (isDisplay) {
      evt.waitForAndGetElement(ELEMENT_TITLE_AUTHORS_INBOX.replace("{$title}", title).replace("{$author}", author));
    } else {
      evt.waitForElementNotPresent(ELEMENT_TITLE_AUTHORS_INBOX.replace("{$title}", title).replace("{$author}", author));
    }
  }

  /**
   * check inbox message not found
   *
   * @param title
   */
  public void checkInboxMessageNotFound(String title) {
    evt.waitForElementNotPresent(By.xpath(ELEMENT_CONTACT_INBOX.replace("{$content}", title)));
  }

  /**
   * Reply a message
   *
   * @param contact
   * @param title
   * @param newTitle
   * @param content
   */
  public void replyMessage(String contact, String title, String newTitle, String content) {
    evt.click(By.xpath(ELEMENT_TITLE_AUTHORS_INBOX.replace("{$title}", title).replace("{$author}", contact)));
    evt.click(ELEMENT_REPLY);
    evt.type(ELEMENT_TITLE_MESSAGE, newTitle, true);
    plf.inputFrame(ELEMENT_MESSAGE_CONTENT, content);
    evt.click(ELEMENT_SEND_BUTTON);
    button.ok();
  }

  /**
   * Forward a message
   *
   * @param contact
   * @param title
   * @param newContact
   * @param newTitle
   * @param newContent
   */
  public void forwardMessage(String contact, String title, String newContact, String newTitle, String newContent) {
    evt.click(By.xpath(ELEMENT_FORWARD_MESSAGE.replace("{$title}", title).replace("{$contact}", contact)));

    evt.type(ELEMENT_SEND_TO_MESSAGE, newContact, true);
    if (newTitle != "")
      evt.type(ELEMENT_TITLE_MESSAGE, newTitle, true);

    if (newContent != "")
      plf.inputFrame(ELEMENT_MESSAGE_CONTENT, newContent);
    evt.click(ELEMENT_SEND_BUTTON);
    button.ok();
  }

  /**
   * Delete a message
   *
   * @param title
   * @param contact
   */
  public void deleteMessage(String title, String contact) {
    evt.click(By.xpath(ELEMENT_DELETE_MESSAGE.replace("{$title}", title).replace("{$contact}", contact)));
    evt.click(ELEMENT_CONFIRM);
    evt.waitForElementNotPresent(By.xpath(ELEMENT_TITLE_AUTHORS_INBOX.replace("{$title}", title).replace("{$author}", contact)));
  }

  /**
   * Open Select Group Form
   */

  public void openSelectGroupForm() {
    evt.click(ELEMENT_COMPOSE_NEW_MESSAGE_GROUP_SELECTOR);
  }

  /**
   * Open Select Membership Form
   */

  public void openSelectMembershipForm() {
    evt.click(ELEMENT_COMPOSE_NEW_MESSAGE_MEMBERSHIP_SELECTOR);
  }

  /**
   * Write message to group
   *
   * @param group
   */

  public void writeMessageToGroup(String group, String title, String content) {
    evt.click(ELEMENT_PRIVATE_MESSAGE_SELECT_GROUP.replace("${name}", group));
    evt.click(ELEMENT_PRIVATE_MESSAGE_SELECT_A_GROUP.replace("${name}", group));
    evt.type(ELEMENT_TITLE_MESSAGE, title, true);
    plf.inputFrame(ELEMENT_MESSAGE_CONTENT, content);
    evt.click(ELEMENT_SEND_BUTTON);
    evt.waitForMessage(ELEMENT_PRIVATE_MESSAGE_SEND_SUCCESSFULLY);
    button.ok();

  }

  /**
   * Write message to group
   *
   * @param group
   */

  public void writeMessageToMembership(String group, String membership, String title, String content) {
    evt.click(ELEMENT_PRIVATE_MESSAGE_SELECT_GROUP.replace("${name}", group));
    evt.click(ELEMENT_PRIVATE_MESSAGE_SELECT_A_MEMBERSHIP.replace("${membership}", membership));
    evt.type(ELEMENT_TITLE_MESSAGE, title, true);
    plf.inputFrame(ELEMENT_MESSAGE_CONTENT, content);
    evt.click(ELEMENT_SEND_BUTTON);
    evt.waitForMessage(ELEMENT_PRIVATE_MESSAGE_SEND_SUCCESSFULLY);
    button.ok();

  }

  /**
   * Go inbox tab
   */
  public void cancelPrivateMessage() {
    evt.click(ELEMENT_PRIVATE_MESSAGE_CANCEL_BUTTON);
  }
}
