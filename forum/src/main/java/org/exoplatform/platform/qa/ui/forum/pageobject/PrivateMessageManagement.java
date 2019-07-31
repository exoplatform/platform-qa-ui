package org.exoplatform.platform.qa.ui.forum.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

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
   * @param testBase TestBase
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
    sleep(2000);
    $(ELEMENT_TABS_SENT_MESSAGES).click();
    sleep(Configuration.timeout);
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
    sleep(2000);
    $(ELEMENT_TABS_COMPOSE_MESSAGE).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(Configuration.timeout);
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
   * @param contact String
   * @param title String
   * @param content String
   */
  public void writeMessage(String contact, String title, String content) {
    refresh();
    sleep(2000);
    $(ELEMENT_SEND_TO_MESSAGE).setValue(contact);
    sleep(2000);
    $(ELEMENT_TITLE_MESSAGE).setValue(title);
    sleep(2000);
    plf.inputFrame(ELEMENT_MESSAGE_CONTENT, content);
    sleep(2000);
    $(ELEMENT_SEND_BUTTON).click();
    sleep(3000);
    ELEMENT_FORUM_OK_SEND_MSG.click();
    sleep(3000);

  }

  /**
   * Write message to group
   *
   * @param groupPath String
   * @param member String
   * @param title String
   * @param content String
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
   * @param contact String
   * @param title String
   * @param content String
   */
  public void checkInboxMessage(String contact, String title, String content) {
    sleep(2000);
    $(byText(title)).click();
    sleep(Configuration.timeout);
    $(byText(content)).scrollTo().waitUntil(Condition.appears, Configuration.timeout);
    sleep(2000);
  }

  /**
   * Check display of message
   *
   * @param title String
   * @param author String
   * @param isDisplay boolean
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
   * @param title String
   */
  public void checkInboxMessageNotFound(String title) {
    evt.waitForElementNotPresent(By.xpath(ELEMENT_CONTACT_INBOX.replace("{$content}", title)));
  }

  /**
   * Reply a message
   *
   * @param contact String
   * @param title String
   * @param newTitle String
   * @param content String
   */
  public void replyMessage(String contact, String title, String newTitle, String content) {
    $(byText(title)).click();
    $(ELEMENT_REPLY).click();
    refresh();
    $(ELEMENT_TITLE_MESSAGE).setValue(newTitle);
    plf.inputFrame(ELEMENT_MESSAGE_CONTENT, content);
    $(ELEMENT_SEND_BUTTON).click();
    ELEMENT_FORUM_OK_SEND_MSG.click();
  }

  /**
   * Forward a message
   *
   * @param contact String
   * @param title String
   * @param newContact String
   * @param newTitle String
   * @param newContent String
   */
  public void forwardMessage(String contact, String title, String newContact, String newTitle, String newContent) {
    $(byText(title)).parent().parent().parent().find(ELEMENT_BUTTON_FORWARD_MESSAGE).click();
    sleep(Configuration.timeout);
    $(ELEMENT_SEND_TO_MESSAGE).setValue(newContact);
    sleep(2000);
    if (newTitle != "")
      $(ELEMENT_TITLE_MESSAGE).setValue(newTitle);
     sleep(2000);
    if (newContent != "")
      plf.inputFrame(ELEMENT_MESSAGE_CONTENT, newContent);
    sleep(2000);
    $(ELEMENT_SEND_BUTTON).click();
    sleep(2000);
    ELEMENT_FORUM_OK_SEND_MSG.click();
    sleep(2000);
  }

  /**
   * Delete a message
   *
   * @param title String
   * @param contact String
   */
  public void deleteMessage(String title, String contact) {
    $(byText(title)).parent().parent().parent().find(ELEMENT_BUTTON_DELETE_MESSAGE).click();
    sleep(2000);
    $(ELEMENT_CONFIRM).click();
    sleep(Configuration.timeout);
    $(byText(title)).waitUntil(Condition.disappears, Configuration.timeout);
    sleep(2000);
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
   * @param group String
   *
   *  @param title String
   *  @param content String
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
   * @param group String
   * @param content String
   * @param membership String
   * @param title String
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
