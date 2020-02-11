package org.exoplatform.platform.qa.ui.forum.pageobject;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
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
   * Go compose tab
   */
  public void goComposeMessage() {
    sleep(2000);
    $(ELEMENT_TABS_COMPOSE_MESSAGE).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(Configuration.timeout);
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
    ELEMENT_FORUM_OK_SEND_MSG.waitUntil(visible,Configuration.timeout).click();
    sleep(3000);

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
   * Reply a message
   *
   * @param contact String
   * @param title String
   * @param newTitle String
   * @param content String
   */
  public void replyMessage(String contact, String title, String newTitle, String content) {
    $(byText(title)).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_REPLY).waitUntil(Condition.visible,2000).click();
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
}
