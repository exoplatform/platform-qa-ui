package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_CANCEL_PRIVATE_MSG;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.*;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

/**
 * @author eXo
 */
@Tag("forum")
@Tag("sniff")
public class ForumPrivateMessageTestIT extends Base {
  HomePagePlatform         homePagePlatform;

  ForumForumManagement     forumForumManagement;

  ForumTopicManagement     forumTopicManagement;

  ForumCategoryManagement  forumCategoryManagement;

  ForumHomePage            forumHomePage;

  PrivateMessageManagement privateMessageManagement;

  ManageLogInOut           manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    forumForumManagement = new ForumForumManagement(this);
    forumTopicManagement = new ForumTopicManagement(this);
    forumHomePage = new ForumHomePage(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    privateMessageManagement = new PrivateMessageManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }



  /**
   * <li>Case ID:116755.</li>
   * <li>Test Case Name: Compose a private message.</li>
   * <li>Case ID:116756.</li>
   * <li>Test Case Name: Reply a message.</li>
   */
  @Test
  public void test02_ReplyMessage() {
    info("Test 1: Compose a private message");
    /*
     * Step Number: 1 Step Name: Compose a message Step Description: - Login as john
     * - Click on Private message - Click on tab Compose New message - Put value
     * into fields, set "Send to" field mary - Save Input Data: Expected Outcome: -
     * Message is sent to receivers - Message is listed at tab Sent Message
     */

    /*
     * Step number: 2 Step Name: Check inbox Step Description: - Login as mary -
     * Click on Private message - Click on tab Inbox Input Data: Expected Outcome: -
     * In tab Inbox, mary seemessage of john
     */
    String title = "" + getRandomNumber();
    String content = "" + getRandomNumber();
    String contact = DATA_USER2;

    homePagePlatform.goToForum();
    forumHomePage.goToPrivateMessage();
    privateMessageManagement.goComposeMessage();
    privateMessageManagement.writeMessage(contact, title, content);
    ELEMENT_CANCEL_PRIVATE_MSG.click();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);

    homePagePlatform.goToForum();
    forumHomePage.goToPrivateMessage();
    privateMessageManagement.checkInboxMessage("", title, content);

    info("Test 2: Reply a message");
    String newTitle = "newTitle" + getRandomNumber();

    privateMessageManagement.replyMessage("", title, newTitle, content);
    privateMessageManagement.deleteMessage(newTitle, "");
    ELEMENT_CANCEL_PRIVATE_MSG.click();
    manageLogInOut.signIn(username, password);

    homePagePlatform.goToForum();
    forumHomePage.goToPrivateMessage();
    privateMessageManagement.checkInboxMessage("mary", newTitle, content);
    privateMessageManagement.deleteMessage(newTitle, "");
    ELEMENT_CANCEL_PRIVATE_MSG.click();

  }

  /**
   * <li>Case ID:116755.</li>
   * <li>Test Case Name: Compose a private message.</li>
   * <li>Case ID:116756.</li>
   * <li>Test Case Name: Reply a message.</li> Step Number: 1 Step Name: Compose a
   * message Step Description: - Login as john - Click on Private message - Click
   * on tab Compose New message - Put value into fields, set "Send to" field mary
   * - Save Input Data: Expected Outcome: - Message is sent to receivers - Message
   * is listed at tab Sent Message Step number: 2 Step Name: Check inbox Step
   * Description: - Login as mary - Click on Private message - Click on tab Inbox
   * Input Data: Expected Outcome: - In tab Inbox, mary seemessage of john
   */

  @Test
  public void test01_ComposeAPrivateMessage() {
    info("Test 1: Compose a private message");
    String title = "" + getRandomNumber();
    String content = "" + getRandomNumber();
    String contact = DATA_USER2;

    homePagePlatform.goToForum();
    forumHomePage.goToPrivateMessage();
    privateMessageManagement.goComposeMessage();
    privateMessageManagement.writeMessage(contact, title, content);
    ELEMENT_CANCEL_PRIVATE_MSG.click();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToForum();
    forumHomePage.goToPrivateMessage();
    privateMessageManagement.checkInboxMessage("", title, content);
    privateMessageManagement.deleteMessage(title, "");
    ELEMENT_CANCEL_PRIVATE_MSG.click();
    manageLogInOut.signIn(username, password);

  }

  /**
   * <li>Case ID:116757.</li>
   * <li>Test Case Name: Forward a message.</li> Step Number: 1 Step Name: Prepare
   * data: Send a message Step Description: - Login as mary - Send a private
   * message to john Input Data: Expected Outcome: Message is sent to john Step
   * number: 2 Step Name: Forward a message Step Description: - Click on tab "Sent
   * Message", click on icon Forward - Put value to fields - Save Input Data:
   * Expected Outcome: - In tab Sent Message, message of mary is shown -Forward
   * message is sent to receivers. In tab Sent message, this message is listed.
   */

  @Test
  public void test03_Forward_AMessage() {
    info("Test 3: Forward a message");

    String contact = "james";
    String contactForward = DATA_USER2;
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();

    homePagePlatform.goToForum();
    forumHomePage.goToPrivateMessage();
    privateMessageManagement.goComposeMessage();
    privateMessageManagement.writeMessage(contact, title, content);
    privateMessageManagement.goSendMessages();
    ELEMENT_CANCEL_PRIVATE_MSG.click();
    forumHomePage.goToPrivateMessage();
    privateMessageManagement.goSendMessages();
    privateMessageManagement.forwardMessage(contact, title, contactForward, "", "");
    privateMessageManagement.deleteMessage(title, "");
    ELEMENT_CANCEL_PRIVATE_MSG.click();
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToForum();
    forumHomePage.goToPrivateMessage();
    privateMessageManagement.checkInboxMessage("", "Forward:" + title, content);

    info("Test 4: Delete a message");
    privateMessageManagement.deleteMessage("Forward:" + title, "");
    ELEMENT_CANCEL_PRIVATE_MSG.click();
    manageLogInOut.signIn(username, password);
  }

  /**
   * <li>Case ID:116758.</li>
   * <li>Test Case Name: Delete a message.</li> Step Number: 1 Step Name: Prepare
   * data: Send a message Step Description: - Login as mary - Send a private
   * message to john Input Data: Expected Outcome: Message is sent to john Step
   * number: 2 Step Name: Forward a message Step Description: - Click on tab "Sent
   * Message", click on icon Forward - Put value to fields - Save Input Data:
   * Expected Outcome: - In tab Sent Message, message of mary is shown -Forward
   * message is sent to receivers. In tab Sent message, this message is listed.
   */

  @Test
  @Tag("fabis")
  public void test04elete_AMessage() {
    info("Test 3: Forward a message");

    String contact = "james";
    String contactForward = DATA_USER2;
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();

    homePagePlatform.goToForum();
    forumHomePage.goToPrivateMessage();
    privateMessageManagement.goComposeMessage();
    privateMessageManagement.writeMessage(contact, title, content);
    privateMessageManagement.goSendMessages();
    ELEMENT_CANCEL_PRIVATE_MSG.click();
    sleep(2000);
    forumHomePage.goToPrivateMessage();
    privateMessageManagement.goSendMessages();
    privateMessageManagement.forwardMessage(contact, title, contactForward, "", "");
    privateMessageManagement.deleteMessage(title, "");
    sleep(2000);
    ELEMENT_CANCEL_PRIVATE_MSG.click();
    sleep(Configuration.timeout);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToForum();
    forumHomePage.goToPrivateMessage();
    privateMessageManagement.checkInboxMessage("", "Forward:" + title, content);

    info("Test 4: Delete a message");
    privateMessageManagement.deleteMessage("Forward:" + title, "");
    ELEMENT_CANCEL_PRIVATE_MSG.click();
    manageLogInOut.signIn(username, password);
  }

}
