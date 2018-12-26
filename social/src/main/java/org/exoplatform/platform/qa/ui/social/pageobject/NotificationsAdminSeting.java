package org.exoplatform.platform.qa.ui.social.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

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
   * Check the title of Admin notification page
   */
  public void verifyTilePage() {
    info("Verify the title of Notification Admin page");
    evt.waitForAndGetElement(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE);
    info("The page is shown");
  }

  /**
   * Verify that a notification belongs to a category
   * 
   * @param category is the category's name
   * @param type is notification's type
   */
  public void verifyNotiBelongToCategory(String category, notiMode type) {
    switch (type) {
    case NewUser:
      info("Verify that New user notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "NewUserPlugin"));
      info("The notification is shown with correct category");
      break;
    case ConnectionRequest:
      info("Verify that Connection Request notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category)
                                                          .replace("$notification", "RelationshipReceivedRequestPlugin"));
      info("The notification is shown with correct category");
      break;
    case Space_Join:
      info("Verify that Space Join Request notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "RequestJoinSpacePlugin"));
      info("The notification is shown with correct category");
      break;
    case Space_Invitation:
      info("Verify that Space invitation notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "SpaceInvitationPlugin"));
      info("The notification is shown with correct category");
      break;
    case Space_Post:
      info("Verify that Post on My Spaces notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category)
                                                          .replace("$notification", "PostActivitySpaceStreamPlugin"));
      info("The notification is shown with correct category");
      break;
    case AS_Post:
      info("Verify that Post on My Stream notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "PostActivityPlugin"));
      info("The notification is shown with correct category");
      break;
    case AS_Like:
      info("Verify that Like notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification", "LikePlugin"));
      info("The notification is shown with correct category");
      break;
    case AS_Mention:
      info("Verify that Mention notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "ActivityMentionPlugin"));
      info("The notification is shown with correct category");
      break;
    case AS_Comment:
      info("Verify that Comment notification belongs to " + category);
      evt.waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.replace("$category", category).replace("$notification",
                                                                                                  "ActivityCommentPlugin"));
      info("The notification is shown with correct category");
      break;
    }

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
   * Verify that notification's type is disabled all
   * 
   * @param op notiMode
   */
  public void veriftyNotificationTypeDisable(notiMode op) {
    switch (op) {
    case NewUser:
      info("Verify that New user is disabled");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_NEW_USER_DISBALE);
      break;
    case ConnectionRequest:
      info("Verify that Connection Request is disabled");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_CONNECTION_REQUEST_DISBALE);
      break;
    case AS_Comment:
      info("Verify that Activity Comment is disabled");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_COMMENT_DISBALE);
      break;
    case AS_Like:
      info("Verify that Activity like is disabled");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_LIKE_DISBALE);
      break;
    case AS_Mention:
      info("Verify that Activity mention is disabled");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_MENTION_DISBALE);
      break;
    case AS_Post:
      info("Verify that Activity post is disabled");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_POST_DISBALE);
      break;
    case Space_Invitation:
      info("Verify that Space invitation is disabled");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_SPACE_INVITATION_DISBALE);
      break;
    case Space_Join:
      info("Verify that Space join is disabled");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_SPACE_JOIN_DISBALE);
      break;
    case Space_Post:
      info("Verify that Space post is disabled");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_SPACE_POST_DISBALE);
      break;
    }

  }

  /**
   * Change information of Notification Sender area
   * 
   * @param name String
   * @param address String
   */
  public void changeNotificationSender(String name, String address) {
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());
    if (!name.isEmpty()) {
      info("Input name");
      evt.type(ELEMENT_ADMIN_NOTIFICATION_SENDER_NAME, name, true);
    }
    if (!address.isEmpty()) {
      info("Input address");
      evt.type(ELEMENT_ADMIN_NOTIFICATION_SENDER_ADDRESS, address, true);
    }
    info("Click on Save button");
    evt.click(ELEMENT_ADMIN_NOTIFICATION_SENDER_SAVE_BTN);
  }

  /**
   * Verify fail when input incorrect address in Notification Sender area
   */
  public void verifyFailChangeNotificationSender() {
    info("Verify error message when input invalid email");
    evt.waitForAndGetElement(ELEMENT_NOTIFICATION_SENDER_ERROR_MESSAGE_INVALID_EMAIL);
  }

  /**
   * Verify success when input correct name and address in Notification Sender
   * area
   */
  public void verifySuccessChangeNotificationSender(String name, String email) {
    info("Verify success message when input correct name and email");
    evt.waitForAndGetElement(ELEMENT_NOTIFICATION_SENDER_SUCCESS_MESSAGE.replace("$name", name).replace("$email", email));
  }

  /**
   * Reset all Notification by default
   */
  public void resetAllChangedNotifications() {
    resetNewUserNotification();
    resetConnectRequestNotification();
    resetSpaceJoinNotification();
    resetSpaceInvitationNotification();
    resetSpacePostNotification();
    resetActivityLikeNotification();
    resetActivityMentionNotification();
    resetActivityPostNotification();
    resetActivityCommentNotification();
    changeNotificationSender("eXo", "noreply@exoplatform.com");
  }

  /**
   * Reset New User Notification
   */
  public void resetNewUserNotification() {
    By newuser_email = ELEMENT_NEW_USER_EMAIL_NOTIFICATION_TITLE;
    By newuser_intranet = ELEMENT_NEW_USER_INTRANET_NOTIFICATION_TITLE;
    By newuser_disable = ELEMENT_ADMIN_NOTIFICATION_NEW_USER_DISBALE;

    if (evt.waitForAndGetElement(newuser_email, 3000, 0) == null || evt.waitForAndGetElement(newuser_intranet, 3000, 0) == null
        || evt.waitForAndGetElement(newuser_disable, 3000, 0) != null) {
      info("Reset New User Notifiction");
      evt.click(ELEMENT_NEW_USER_NOTIFICATION_EDIT_BTN);
      evt.check(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX, 2);
      evt.check(ELEMENT_NEW_USER_INTRANET_NOTIFICATION_CHECKBOX, 2);
      evt.click(ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN);
      evt.waitForAndGetElement(newuser_email);
      evt.waitForAndGetElement(newuser_intranet);
    }
  }

  /**
   * Reset Connection Request Notification
   */
  public void resetConnectRequestNotification() {
    By connect_email = ELEMENT_NEW_USER_EMAIL_NOTIFICATION_TITLE;
    By connect_intranet = ELEMENT_NEW_USER_INTRANET_NOTIFICATION_TITLE;
    By connect_disable = ELEMENT_ADMIN_NOTIFICATION_CONNECTION_REQUEST_DISBALE;

    if (evt.waitForAndGetElement(connect_email, 3000, 0) == null || evt.waitForAndGetElement(connect_intranet, 3000, 0) == null
        || evt.waitForAndGetElement(connect_disable, 3000, 0) != null) {
      info("Reset Connection Request Notifiction");
      evt.click(ELEMENT_CONNECTION_REQUEST_EDIT_BTN);
      evt.check(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_CHECKBOX, 2);
      evt.check(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_CHECKBOX, 2);
      evt.click(ELEMENT_CONNECTION_REQUEST_SAVE_BTN);
      evt.waitForAndGetElement(connect_email);
      evt.waitForAndGetElement(connect_intranet);
    }
  }

  /**
   * Reset Space Join Notification
   */
  public void resetSpaceJoinNotification() {
    By space_join_email = ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_TITLE;
    By space_join_intranet = ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_TITLE;
    By space_join_disable = ELEMENT_ADMIN_NOTIFICATION_SPACE_JOIN_DISBALE;

    if (evt.waitForAndGetElement(space_join_email, 3000, 0) == null
        || evt.waitForAndGetElement(space_join_intranet, 3000, 0) == null
        || evt.waitForAndGetElement(space_join_disable, 3000, 0) != null) {
      info("Reset Space Join Notifiction");
      evt.click(ELEMENT_SPACE_NOTIFICATION_JOIN_EDIT_BTN);
      evt.check(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_CHECKBOX, 2);
      evt.check(ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_CHECKBOX, 2);
      evt.click(ELEMENT_SPACE_JOIN_NOTIFICATION_SAVE_BTN);
      evt.waitForAndGetElement(space_join_email);
      evt.waitForAndGetElement(space_join_intranet);
    }
  }

  /**
   * Reset Space Invitation Notification
   */
  public void resetSpaceInvitationNotification() {
    By space_invitation_email = ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_TITLE;
    By space_invitation_intranet = ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_TITLE;
    By space_invitation_disable = ELEMENT_ADMIN_NOTIFICATION_SPACE_INVITATION_DISBALE;

    if (evt.waitForAndGetElement(space_invitation_email, 3000, 0) == null
        || evt.waitForAndGetElement(space_invitation_intranet, 3000, 0) == null
        || evt.waitForAndGetElement(space_invitation_disable, 3000, 0) != null) {
      info("Reset Space invitation Notifiction");
      evt.click(ELEMENT_SPACE_NOTIFICATION_INVITATION_EDIT_BTN);
      evt.check(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_CHECKBOX, 2);
      evt.check(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_CHECKBOX, 2);
      evt.click(ELEMENT_SPACE_INVITATION_NOTIFICATION_SAVE_BTN);
      evt.waitForAndGetElement(space_invitation_email);
      evt.waitForAndGetElement(space_invitation_intranet);
    }
  }

  /**
   * Reset Space Post Notification
   */
  public void resetSpacePostNotification() {
    By space_post_email = ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_TITLE;
    By space_post_intranet = ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_TITLE;
    By space_post_disable = ELEMENT_ADMIN_NOTIFICATION_SPACE_POST_DISBALE;

    if (evt.waitForAndGetElement(space_post_email, 3000, 0) == null
        || evt.waitForAndGetElement(space_post_intranet, 3000, 0) == null
        || evt.waitForAndGetElement(space_post_disable, 3000, 0) != null) {
      info("Reset Space post Notifiction");
      evt.click(ELEMENT_SPACE_NOTIFICATION_POST_EDIT_BTN);
      evt.check(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_CHECKBOX, 2);
      evt.check(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_CHECKBOX, 2);
      evt.click(ELEMENT_SPACE_POST_NOTIFICATION_SAVE_BTN);
      evt.waitForAndGetElement(space_post_email);
      evt.waitForAndGetElement(space_post_intranet);
    }
  }

  /**
   * Reset Activity Like Notification
   */
  public void resetActivityLikeNotification() {
    By as_like_email = ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_TITLE;
    By as_like_intranet = ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_TITLE;
    By as_like_disable = ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_LIKE_DISBALE;

    if (evt.waitForAndGetElement(as_like_email, 3000, 0) == null || evt.waitForAndGetElement(as_like_intranet, 3000, 0) == null
        || evt.waitForAndGetElement(as_like_disable, 3000, 0) != null) {
      info("Reset Activity Like Notifiction");
      evt.click(ELEMENT_ACTIVITY_LIKE_EDIT_BTN);
      evt.check(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_CHECKBOX, 2);
      evt.check(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_CHECKBOX, 2);
      evt.click(ELEMENT_ACTIVITY_LIKE_SAVE_BTN);
      evt.waitForAndGetElement(as_like_email);
      evt.waitForAndGetElement(as_like_intranet);
    }
  }

  /**
   * Reset Activity Mention Notification
   */
  public void resetActivityMentionNotification() {
    By as_mention_email = ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_TITLE;
    By as_mention_intranet = ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_TITLE;
    By as_mention_disable = ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_MENTION_DISBALE;

    if (evt.waitForAndGetElement(as_mention_email, 3000, 0) == null
        || evt.waitForAndGetElement(as_mention_intranet, 3000, 0) == null
        || evt.waitForAndGetElement(as_mention_disable, 3000, 0) != null) {
      info("Reset Activity Mention Notifiction");
      evt.click(ELEMENT_ACTIVITY_MENTION_EDIT_BTN);
      evt.check(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_CHECKBOX, 2);
      evt.check(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_CHECKBOX, 2);
      evt.click(ELEMENT_ACTIVITY_MENTION_SAVE_BTN);
      evt.waitForAndGetElement(as_mention_email);
      evt.waitForAndGetElement(as_mention_intranet);
    }
  }

  /**
   * Reset Activity post Notification
   */
  public void resetActivityPostNotification() {
    By as_post_email = ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_TITLE;
    By as_post_intranet = ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_TITLE;
    By as_post_disable = ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_POST_DISBALE;

    if (evt.waitForAndGetElement(as_post_email, 3000, 0) == null || evt.waitForAndGetElement(as_post_intranet, 3000, 0) == null
        || evt.waitForAndGetElement(as_post_disable, 3000, 0) != null) {
      info("Reset Activity post Notifiction");
      evt.click(ELEMENT_ACTIVITY_POST_EDIT_BTN);
      evt.check(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_CHECKBOX, 2);
      evt.check(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_CHECKBOX, 2);
      evt.click(ELEMENT_ACTIVITY_POST_SAVE_BTN);
      evt.waitForAndGetElement(as_post_email);
      evt.waitForAndGetElement(as_post_intranet);
    }
  }

  /**
   * Reset Activity comment Notification
   */
  public void resetActivityCommentNotification() {
    By as_comment_email = ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_TITLE;
    By as_comment_intranet = ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_TITLE;
    By as_comment_disable = ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_COMMENT_DISBALE;

    if (evt.waitForAndGetElement(as_comment_email, 3000, 0) == null
        || evt.waitForAndGetElement(as_comment_intranet, 3000, 0) == null
        || evt.waitForAndGetElement(as_comment_disable, 3000, 0) != null) {
      info("Reset Activity comment Notifiction");
      evt.click(ELEMENT_ACTIVITY_COMMENT_EDIT_BTN);
      evt.check(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_CHECKBOX, 2);
      evt.check(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_CHECKBOX, 2);
      evt.click(ELEMENT_ACTIVITY_COMMENT_SAVE_BTN);
      evt.waitForAndGetElement(as_comment_email);
      evt.waitForAndGetElement(as_comment_intranet);
    }
  }

  /**
   * Verify that a notification is enabled
   * 
   * @param type as NewUser_email, NewUser_intranet,...
   */
  public void verifyNotificationTypeEnable(notificationType type) {
    switch (type) {
    case NewUser_email:
      break;
    case NewUser_intranet:
      break;
    case AS_Comment_email:
      info("Verify that email for comment notification is shown");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_COMMENT_ENABLE_EMAIL);
      info("The notification is shown successfully");
      break;
    case AS_Comment_intranet:
      info("Verify that intranet for comment notification is shown");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_COMMENT_ENABLE_INTRANET);
      info("The notification is shown successfully");
      break;
    case AS_Like_email:
      info("Verify that email for like notification is shown");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_LIKE_ENABLE_EMAIL);
      info("The notification is shown successfully");
      break;
    case AS_Like_intranet:
      info("Verify that intranet for like notification is shown");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_LIKE_ENABLE_INTRANET);
      info("The notification is shown successfully");
      break;
    case AS_Mention_email:
      info("Verify that email for connection request notification is shown");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_MENTION_ENABLE_EMAIL);
      info("The notification is shown successfully");
      break;
    case AS_Mention_intranet:
      info("Verify that intranet for connection request notification is shown");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_MENTION_ENABLE_INTRANET);
      info("The notification is shown successfully");
      break;
    case AS_Post_email:
      break;
    case AS_Post_intranet:
      break;
    case ConnectionRequest_email:
      info("Verify that email for connection request notification is shown");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_CONNECTION_REQUEST_ENABLE_EMAIL);
      info("The notification is shown successfully");
      break;
    case ConnectionRequest_intranet:
      info("Verify that intranet for connection request notification is shown");
      evt.waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_CONNECTION_REQUEST_ENABLE_INTRANET);
      info("The notification is shown successfully");
      break;
    case Space_Invitation_email:
      break;
    case Space_Invitation_intranet:
      break;
    case Space_Join_email:
      break;
    case Space_Join_intranet:
      break;
    case Space_Post_email:
      break;
    case Space_Post_intranet:
      break;
    }
  }

  /**
   * Verify the label of notification's types
   * 
   * @param label as: Someone comments on one of my activities,...
   */
  public void verifyLabelNotificationType(String label) {
    info("Verify that the label of Comment notification is correct");
    evt.waitForAndGetElement(ELEMENT_NOTIFICATION_LABEL_NAME.replace("$label", label));
    info("the label is correct");

  }

  /**
   * Verify the name of notification's types
   * 
   * @param name as: Comment, Like on Activity
   */
  public void verifyNameNotificationType(String name) {
    info("Verify that the label of Comment notification is correct");
    evt.waitForAndGetElement(ELEMENT_NOTIFICATION_LABEL_NAME.replace("$label", name));
    info("the label is correct");
  }

  /**
   * Define notification's type as: New User for email, New User for intranet,
   * Connection Request for email.....
   */
  public enum notificationType {
    NewUser_email, NewUser_intranet, ConnectionRequest_email, ConnectionRequest_intranet, AS_Comment_email, AS_Comment_intranet, AS_Like_email, AS_Like_intranet, AS_Mention_email, AS_Mention_intranet, AS_Post_email, AS_Post_intranet, Space_Invitation_email, Space_Invitation_intranet, Space_Join_email, Space_Join_intranet, Space_Post_email, Space_Post_intranet,Edit_Activity,Edit_Comment;
  }

  /**
   * Define notification plugin is disable all
   */
  public enum notiMode {
    NewUser, ConnectionRequest, AS_Comment, AS_Like, AS_Mention, AS_Post, Space_Invitation, Space_Join, Space_Post;
  }

}
