package org.exoplatform.platform.qa.ui.social.pageobject;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class NotificationsAdminSeting {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param testBase TestBase
   */
  public NotificationsAdminSeting(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Disable notification
   * 
   * @param notifToDisable notificationType
   */
  public void disableNotification(notificationType notifToDisable) {
    switch (notifToDisable) {
    case NewUser_email:
      info("Click on Edit button");
      evt.click(ELEMENT_NEW_USER_NOTIFICATION_EDIT_BTN);
      evt.uncheck(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case NewUser_intranet:
      info("Click on Edit button");
      evt.click(ELEMENT_NEW_USER_NOTIFICATION_EDIT_BTN);
      evt.uncheck(ELEMENT_NEW_USER_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN);
      info("Verify that intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_NEW_USER_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case ConnectionRequest_email:
      evt.click(ELEMENT_CONNECTION_REQUEST_EDIT_BTN);
      evt.uncheck(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_CONNECTION_REQUEST_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case ConnectionRequest_intranet:
      evt.click(ELEMENT_CONNECTION_REQUEST_EDIT_BTN);
      evt.uncheck(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_CONNECTION_REQUEST_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Comment_email:
      evt.click(ELEMENT_ACTIVITY_COMMENT_EDIT_BTN);
      evt.uncheck(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_COMMENT_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Comment_intranet:
      $(ELEMENT_ACTIVITY_COMMENT_EDIT_BTN).click();
      evt.uncheck(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      $(ELEMENT_ACTIVITY_COMMENT_SAVE_BTN).click();
      info("Verify that Intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Like_email:
      evt.click(ELEMENT_ACTIVITY_LIKE_EDIT_BTN);
      evt.uncheck(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_LIKE_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Like_intranet:
      evt.click(ELEMENT_ACTIVITY_LIKE_EDIT_BTN);
      evt.uncheck(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_LIKE_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Mention_email:
      evt.click(ELEMENT_ACTIVITY_MENTION_EDIT_BTN);
      evt.uncheck(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_MENTION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Mention_intranet:
      evt.click(ELEMENT_ACTIVITY_MENTION_EDIT_BTN);
      evt.uncheck(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_MENTION_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Post_email:
      evt.click(ELEMENT_ACTIVITY_POST_EDIT_BTN);
      evt.uncheck(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_POST_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Post_intranet:
      evt.click(ELEMENT_ACTIVITY_POST_EDIT_BTN);
      evt.uncheck(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_POST_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case Space_Join_email:
      evt.click(ELEMENT_SPACE_NOTIFICATION_JOIN_EDIT_BTN);
      evt.uncheck(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_SPACE_JOIN_NOTIFICATION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case Space_Join_intranet:
      evt.click(ELEMENT_SPACE_NOTIFICATION_JOIN_EDIT_BTN);
      evt.uncheck(ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_SPACE_JOIN_NOTIFICATION_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case Space_Invitation_email:
      evt.click(ELEMENT_SPACE_NOTIFICATION_INVITATION_EDIT_BTN);
      evt.uncheck(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_SPACE_INVITATION_NOTIFICATION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case Space_Invitation_intranet:
      evt.click(ELEMENT_SPACE_NOTIFICATION_INVITATION_EDIT_BTN);
      evt.uncheck(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_SPACE_INVITATION_NOTIFICATION_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case Space_Post_email:
      evt.click(ELEMENT_SPACE_NOTIFICATION_POST_EDIT_BTN);
      evt.uncheck(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_SPACE_POST_NOTIFICATION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case Space_Post_intranet:
      evt.click(ELEMENT_SPACE_NOTIFICATION_POST_EDIT_BTN);
      evt.uncheck(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_SPACE_POST_NOTIFICATION_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForElementNotPresent(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;

    }
  }

  /**
   * Enable notification
   * 
   * @param notifToDisable notificationType
   */
  public void enableNotification(notificationType notifToDisable) {
    switch (notifToDisable) {
    case NewUser_email:
      info("Click on Edit button");
      evt.click(ELEMENT_NEW_USER_NOTIFICATION_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case NewUser_intranet:
      info("Click on Edit button");
      $(ELEMENT_EDIT_NEWUSER_ICON).waitUntil(Condition.visible,Configuration.timeout).click();
      $(ELEMENT_EDIT_NEWUSER_ICON).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
      if($(ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX).is(Condition.not(Condition.checked)))
        $(ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX).parent().click();
      info("Click on Save button");
      $(ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN).click();
      $(ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
      info("Verify that intranet notification is shown");
      $(ELEMENT_NEW_USER_INTRANET_NOTIFICATION_TITLE).waitUntil(Condition.visible,Configuration.timeout);
      break;
    case ConnectionRequest_email:
      evt.click(ELEMENT_CONNECTION_REQUEST_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_CONNECTION_REQUEST_SAVE_BTN);
      info("Verify that email notification is shown");
      evt.waitForAndGetElement(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case ConnectionRequest_intranet:
      evt.click(ELEMENT_CONNECTION_REQUEST_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_CONNECTION_REQUEST_SAVE_BTN);
      info("Verify that Intranet notification is shown");
      evt.waitForAndGetElement(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Comment_email:
      evt.click(ELEMENT_ACTIVITY_COMMENT_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_COMMENT_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Comment_intranet:
      $(ELEMENT_ACTIVITY_COMMENT_EDIT_BTN).click();
      if ($(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_CHECKBOX_CHECKED).is(Condition.not(Condition.checked)))
        $(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_CHECKBOX).parent().click();
      info("Click on Save button");
      $(ELEMENT_ACTIVITY_COMMENT_SAVE_BTN).click();
      info("Verify that Intranet notification is hidded");
      $(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_TITLE).waitUntil(Condition.disappears,Configuration.timeout);
      break;
    case AS_Like_email:
      evt.click(ELEMENT_ACTIVITY_LIKE_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_LIKE_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Like_intranet:
      evt.click(ELEMENT_ACTIVITY_LIKE_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_LIKE_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Mention_email:
      evt.click(ELEMENT_ACTIVITY_MENTION_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_MENTION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Mention_intranet:
      evt.click(ELEMENT_ACTIVITY_MENTION_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_MENTION_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Post_email:
      evt.click(ELEMENT_ACTIVITY_POST_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_POST_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case AS_Post_intranet:
      evt.click(ELEMENT_ACTIVITY_POST_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_ACTIVITY_POST_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForAndGetElement(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case Space_Join_email:
      evt.click(ELEMENT_SPACE_NOTIFICATION_JOIN_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_SPACE_JOIN_NOTIFICATION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForAndGetElement(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case Space_Join_intranet:
      evt.click(ELEMENT_SPACE_NOTIFICATION_JOIN_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_SPACE_JOIN_NOTIFICATION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForAndGetElement(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    case Space_Invitation_email:
      evt.click(ELEMENT_SPACE_NOTIFICATION_INVITATION_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_SPACE_INVITATION_NOTIFICATION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForAndGetElement(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case Space_Invitation_intranet:
      $(ELEMENT_SPACE_NOTIFICATION_INVITATION_EDIT_BTN).click();
      if (evt.waitForAndGetElement(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        $(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_CHECKBOX).click();
      info("Click on Save button");
      $(ELEMENT_SPACE_INVITATION_NOTIFICATION_SAVE_BTN).click();
      info("Verify that Intranet notification is hidded");
      $(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_TITLE).waitUntil(Condition.disappears, Configuration.timeout);
      break;
    case Space_Post_email:
      evt.click(ELEMENT_SPACE_NOTIFICATION_POST_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_SPACE_POST_NOTIFICATION_SAVE_BTN);
      info("Verify that email notification is hidded");
      evt.waitForAndGetElement(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_TITLE, 3000, 1);
      break;
    case Space_Post_intranet:
      evt.click(ELEMENT_SPACE_NOTIFICATION_POST_EDIT_BTN);
      if (evt.waitForAndGetElement(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_CHECKBOX_CHECKED, 2000, 0) == null)
        evt.check(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_CHECKBOX, 2);
      info("Click on Save button");
      evt.click(ELEMENT_SPACE_POST_NOTIFICATION_SAVE_BTN);
      info("Verify that Intranet notification is hidded");
      evt.waitForAndGetElement(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_TITLE, 3000, 1);
      break;
    }
  }

  /**
   * Define notification's type as: New User for email, New User for intranet,
   * Connection Request for email.....
   */
  public enum notificationType {
    NewUser_email, NewUser_intranet, ConnectionRequest_email, ConnectionRequest_intranet, AS_Comment_email, AS_Comment_intranet, AS_Like_email, AS_Like_intranet, AS_Mention_email, AS_Mention_intranet, AS_Post_email, AS_Post_intranet, Space_Invitation_email, Space_Invitation_intranet, Space_Join_email, Space_Join_intranet, Space_Post_email, Space_Post_intranet,Edit_Activity,Edit_Comment;
  }
}
