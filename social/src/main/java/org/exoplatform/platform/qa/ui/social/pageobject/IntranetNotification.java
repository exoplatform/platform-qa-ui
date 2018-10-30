package org.exoplatform.platform.qa.ui.social.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_NOTIFICATION_DROPDOWN;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class IntranetNotification {
  private final TestBase       testBase;

  SpaceHomePage                spaceHome;

  UserProfilePage              userPro;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase TestBase
   */
  public IntranetNotification(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.userPro = new UserProfilePage(testBase);
    this.spaceHome = new SpaceHomePage(testBase);
  }

  /**
   * function: go to all notification
   */
  public void goToAllNotification() {
    info("Go to all notification");
    if ($(ELEMENT_VIEW_ALL).is(Condition.visible)) {
      info("Click on View All button");
      $(ELEMENT_VIEW_ALL).click();
    } else {
      info("Open All page by link");
      open(Configuration.baseUrl+ "portal/intranet/allNotifications/");
    }
    $(ELEMENT_ALL_NOTIFICATIONS).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * function: go to notifications settings
   */
  public void goToNotificationSettings() {
    info("go to notification settings");

    evt.click(ELEMENT_NOTIFICATION_SETTINGS_LINK);
    info("Verify that Notification setting page is shown");
    evt.waitForAndGetElement(ELEMENT_NOTIFICATION_SETTINGS_TITLE);
  }

  /**
   * Open detail a Comment notification
   * 
   * @param activity is activity's name
   * @param isPopup =true if open from the popup =false if open from All
   *          Notification page
   */
  public void goToDetailCommentNotification(String activity, boolean isPopup) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if (evt.waitForAndGetElement(ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER, 3000, 0) != null)
          ;
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER, 5000, 0) != null) {
        info("Element " + ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER + " is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      if (isPopup) {
        info("View detail notification when comments an activity from the popup");
        evt.click(ELEMENT_NOTIFICATION_POPUP_COMMENT.replace("$activity", activity));
      } else {
        info("View detail notification when comments an activity from all notification page");
        evt.click(ELEMENT_NOTIFICATION_ALL_PAGE_COMMENT.replace("$activity", activity));
      }

    }
  }

  /**
   * Open a detail Request Connection to a new user
   * 
   * @param fullName is user's full name
   * @param isPopup =true if open from the pop up =false if open from all
   *          notification page
   */
  public void goToDetailRequestConnectionUser(String fullName, boolean isPopup) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if (evt.waitForAndGetElement(ELEMENT_UIBASICPROFILEPORTLET, 3000, 0) != null)
          ;
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_UIBASICPROFILEPORTLET, 5000, 0) != null) {
        info("Element " + ELEMENT_UIBASICPROFILEPORTLET + " is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      if (isPopup) {
        info("View detail of request connection to new user from the popup");
        evt.click(ELEMENT_NOTIFICATION_POPUP_REQUEST_CONNECT.replace("$name", fullName));
      } else {
        info("View detail of request connection to new user from all notification page");
        evt.click(ELEMENT_NOTIFICATION_ALL_PAGE_REQUEST_CONNECT.replace("$name", fullName));
      }

    }
  }

  /**
   * Open a detail Accept Request Connection to a new user
   * 
   * @param fullName is user's full name
   * @param isPopup =true if open from the pop up =false if open from all
   *          notification page
   */
  public void goToDetailAcceptRequestConnectionUser(String fullName, boolean isPopup) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if (evt.waitForAndGetElement(ELEMENT_UIBASICPROFILEPORTLET, 3000, 0) != null)
          ;
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_UIBASICPROFILEPORTLET, 5000, 0) != null) {
        info("Element " + ELEMENT_UIBASICPROFILEPORTLET + " is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      if (isPopup) {
        info("View detail of accept connection to new user from the pop up");
        evt.click(ELEMENT_NOTIFICATION_POPUP_ACCEPT_REQUEST_CONNECT.replace("$name", fullName));
      } else {
        info("View detail of accept connection to new user from all notification page");
        evt.click(ELEMENT_NOTIFICATION_ALL_PAGE_ACCEPT_REQUEST_CONNECT.replace("$name", fullName));
      }

    }
  }

  /**
   * Open a detail Accept Request Invitation to a new space
   * 
   * @param space is space's name
   * @param isPopup =true if open from the pop up =false if open from all
   *          notification page
   */
  public void goToDetailAcceptInvitationSpace(String space, boolean isPopup) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if (evt.waitForAndGetElement(ELEMENT_SPACE_MENU_ACTIVITY_STREAM, 3000, 0) != null)
          ;
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_SPACE_MENU_ACTIVITY_STREAM, 5000, 0) != null) {
        info("Element " + ELEMENT_SPACE_MENU_ACTIVITY_STREAM + " is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      if (isPopup) {
        info("View detail of accept invitation to new space from the popup");
        evt.click(ELEMENT_NOTIFICATION_POPUP_ACCEPT_INVITE_SPACE.replace("$space", space));
      } else {
        info("View detail of accept invitation to new space from all notification page");
        evt.click(ELEMENT_NOTIFICATION_ALL_PAGE_ACCEPT_INVITE_SPACE.replace("$space", space));
      }

    }
  }

  /**
   * Open a detail Like Notification
   * 
   * @param fullName is user's full name
   * @param isPopup =true if open from the pop up =false if open from all
   *          notification page
   */
  public void goToDetailLikeNotification(String fullName, boolean isPopup) {

      if (isPopup) {
        info("View detail notification when like an activity from the popup");
        $(byXpath(ELEMENT_NOTIFICATION_POPUP_LIKE.replace("$user", fullName))).click();

      } else {
        info("View detail notification when like an activity from all notification page");
        $(byXpath(ELEMENT_NOTIFICATION_ALL_PAGE_LIKE.replace("$user", fullName))).click();
      }
  }

  /**
   * Open a detail Mention Notification
   * 
   * @param fullName is user's full name
   * @param isPopup =true if open from the pop up =false if open from all
   *          notification page
   */
  public void goToDetailMentionNotification(String fullName, boolean isPopup) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if (evt.waitForAndGetElement(ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER, 3000, 0) != null)
          ;
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER, 5000, 0) != null) {
        info("Element " + ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER + " is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      if (isPopup) {
        info("View detail notification when mention a user in an activity from the popup");
        evt.click(ELEMENT_NOTIFICATION_POPUP_MENTION.replace("$name", fullName));
      } else {
        info("View detail notification when mention a user in an activity from all notification page");
        evt.click(ELEMENT_NOTIFICATION_ALL_PAGE_MENTION.replace("$name", fullName));
      }

    }
  }

  /**
   * Open a detail Post in My activity Notification
   * 
   * @param fullName is user's full name
   * @param isPopup =true if open from the pop up =false if open from all
   *          notification page
   */
  public void goToDetailPostInMyActivity(String fullName, boolean isPopup) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if (evt.waitForAndGetElement(ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER, 3000, 0) != null)
          ;
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER, 5000, 0) != null) {
        info("Element " + ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER + " is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      if (isPopup) {
        info("View detail notification when post an activity in friend's activity stream from the popup");
        evt.click(ELEMENT_NOTIFICATION_POPUP_POST_IN_MY_ACTIVITY.replace("$name", fullName));
      } else {
        info("View detail notification when post an activity in friend's activity stream from all notification page");
        evt.click(ELEMENT_NOTIFICATION_ALL_PAGE_POST_IN_MY_ACTIVITY.replace("$name", fullName));
      }

    }
  }

  /**
   * Open a detail Post in Space activity Notification
   * 
   * @param space is user's full name
   * @param isPopup =true if open from the pop up =false if open from all
   *          notification page
   */
  public void goToDetailPostInSpace(String space, boolean isPopup) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if (evt.waitForAndGetElement(ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER, 3000, 0) != null)
          ;
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER, 5000, 0) != null) {
        info("Element " + ELEMENT_NOTIFICATION_UI_ACTIVITY_LOADER + " is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      if (isPopup) {
        info("View detail notification when post an activity in space's activity stream from the popup");
        evt.click(ELEMENT_NOTIFICATION_POPUP_POST_IN_SPACE.replace("$space", space));
      } else {
        info("View detail notification when post an activity in space's activity stream from all notification page");
        evt.click(ELEMENT_NOTIFICATION_ALL_PAGE_POST_IN_SPACE.replace("$space", space));
      }

    }
  }

  /**
   * Open a detail Invitation to join a new space Notification
   * 
   * @param space is space's name
   * @param isPopup =true if open from the pop up =false if open from all
   *          notification page
   */
  public void goToDetailInvitationSpace(String space, boolean isPopup) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if (evt.waitForAndGetElement(ELEMENT_NOTIFICATION_UI_SPACE_ACCESS_PORTLET, 3000, 0) != null)
          ;
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_NOTIFICATION_UI_SPACE_ACCESS_PORTLET, 5000, 0) != null) {
        info("Element " + ELEMENT_NOTIFICATION_UI_SPACE_ACCESS_PORTLET + " is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      if (isPopup) {
        info("View detail notification when invited to join a space from the popup");
        evt.click(ELEMENT_NOTIFICATION_POPUP_INVITE_SPACE.replace("$name", space));
      } else {
        info("View detail notification when invited to join a space from all notification page");
        evt.click(ELEMENT_NOTIFICATION_ALL_PAGE_INVITE_SPACE.replace("$name", space));
      }

    }
  }

  /**
   * Open a detail Join a new space Notification
   * 
   * @param fullName is user's full name
   * @param isPopup =true if open from the pop up =false if open from all
   *          notification page
   */
  public void goToDetailJoinSpace(String fullName, boolean isPopup) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if ($(ELEMENT_SPACE_MENU_ACTIVITY_STREAM).is(Condition.visible))
          ;
        break;
      }
      if ($(ELEMENT_SPACE_MENU_ACTIVITY_STREAM).is(Condition.visible)) {
        info("Element " + ELEMENT_SPACE_MENU_ACTIVITY_STREAM + " is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      if (isPopup) {
        info("View detail notification when joined new space from the popup");
        $(byXpath(ELEMENT_NOTIFICATION_POPUP_JOIN_SPACE.replace("$name", fullName))).click();
      } else {
        info("View detail notification when joined new space from all notification page");
        $(byXpath(ELEMENT_NOTIFICATION_ALL_PAGE_JOIN_SPACE.replace("$name", fullName))).click();
      }

    }
  }

  /**
   * Open a detail request to join a new space Notification
   * 
   * @param fullName is user's full name
   * @param isPopup =true if open from the pop up =false if open from all
   *          notification page
   */
  public void goToDetailRequestJoinSpace(String fullName, boolean isPopup) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if ($(ELEMENT_SPACE_MENU_ACTIVITY_STREAM).is(Condition.visible))
          ;
        break;
      }
      if ($(ELEMENT_SPACE_MENU_ACTIVITY_STREAM).is(Condition.visible)) {
        info("Element " + ELEMENT_SPACE_MENU_ACTIVITY_STREAM + " is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      if (isPopup) {
        info("View detail of asking to join new space from the popup");
        $(byXpath(ELEMENT_NOTIFICATION_POPUP_REQUEST_JOIN_SPACE.replace("$name", fullName))).click();
      } else {
        info("View detail of asking to join new space from all notification page");
        $(byXpath(ELEMENT_NOTIFICATION_ALL_PAGE_REQUEST_JOIN_SPACE.replace("$name", fullName))).click();
      }

    }

  }

  /**
   * Open a detail new user to join Intranet Notification
   * 
   * @param fullName is user's full name
   * @param isPopup =true if open from the pop up =false if open from all
   *          notification page
   */
  public void goToDetailNewUserJoinIntranet(String fullName, boolean isPopup) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if (evt.waitForAndGetElement(ELEMENT_UIBASICPROFILEPORTLET, 3000, 0) != null)
          ;
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_UIBASICPROFILEPORTLET, 5000, 0) != null) {
        info("Element " + ELEMENT_UIBASICPROFILEPORTLET + " is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      if (isPopup) {
        info("View detail notification when new user joined to intranet from all notificaiton page");
        evt.click(ELEMENT_NOTIFICATION_POPUP_NEW_USER_JOIN_INTRANET.replace("$name", fullName));
      } else {
        info("View detail notification when new user joined to intranet from all notificaiton page");
        evt.click(ELEMENT_NOTIFICATION_ALL_PAGE_NEW_USER_JOIN_INTRANET.replace("$name", fullName));
      }

    }
  }

  /**
   * Accept an connection request in notification list
   * 
   * @param fullName is fullName of user that want to connect
   */
  public void acceptRqConnection(String fullName) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if (evt.waitForAndGetElement(ELEMENT_CONNECT_ACCEPT_BUTTON.replace("$name", fullName), 3000, 0) == null)
          ;
        break;
      }
      if ($(byText(fullName)).parent().parent().find(ELEMENT_BUTTON_ACCEPT_INVITATION).is(Condition.not(Condition.exist))) {
        break;
      }
      info("Retry...[" + repeat + "]");
      info("Click on Accept button");
      $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(fullName)).parent().parent().find(ELEMENT_BUTTON_ACCEPT_INVITATION).waitUntil(Condition.visible,Configuration.timeout).click();
      $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(fullName)).parent().parent().find(ELEMENT_BUTTON_ACCEPT_INVITATION).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    }
  }


  /**
   * Refuse an connection request in notificaiton list
   * 
   * @param fullName String
   */
  public void refuseRqConnection(String fullName) {

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if ($(byXpath(ELEMENT_CONNECT_REFUSE_BUTTON.replace("$name", fullName))).is(Condition.not(Condition.visible)))
          ;
        break;
      }
      if ($(byXpath(ELEMENT_CONNECT_REFUSE_BUTTON.replace("$name", fullName))).is(Condition.not(Condition.visible))) {
        info("Element " + ELEMENT_CONNECT_ACCEPT_BUTTON.replace("$name", fullName) + " isnot displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      info("Click on Refuse button");
      $(byXpath(ELEMENT_CONNECT_REFUSE_BUTTON.replace("$name", fullName))).waitUntil(Condition.visible,Configuration.timeout).click();
      $(byXpath(ELEMENT_CONNECT_REFUSE_BUTTON.replace("$name", fullName))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    }
  }

  /**
   * Check Accept and Refuse buttons are shown in Notification popup and page
   * 
   * @param name is here maybe as fullName of a user, space's name
   */
  public void checkBtnConnectJoinRequest(String name) {
    info("Verify that Accept button are shown on the popup");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(name)).parent().parent().find(ELEMENT_BUTTON_ACCEPT_INVITATION).should(Condition.exist);
    info("Verify that Refuse button are shown on the popup");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(name)).parent().parent().find(ELEMENT_BUTTON_CANCEL_INVITATION).should(Condition.exist);
  }

  /**
   * Check users that are shown their names in notification list
   * 
   * @param users is array of users
   * @param isPopUp =true, if the notification list is shown in Notification list
   *          popup =false, if the notification list is shown in All notification
   *          list
   */
  public void checkUsers(ArrayList<String> users, boolean isPopUp) {
    int lastIndex = users.size() - 1;
    info("users.size:" + users.size());
    if (isPopUp) {
      info("Verify that last user is shown in the popup");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_USER.replace("$user", users.get(lastIndex)), 2000, 2);
    } else {
      info("Verify that last user is shown in the page");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_ALL_USER.replace("$user", users.get(lastIndex)), 2000, 2);
    }

    if (users.size() > 2 && isPopUp == true) {
      info("Verify that second last user is shown in the popup");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_USER.replace("$user", users.get(lastIndex - 1)), 2000, 2);
    }

    if (users.size() > 2 && isPopUp == false) {
      info("Verify that second last user is shown in the page");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_ALL_USER.replace("$user", users.get(lastIndex - 1)), 2000, 2);
    }
  }

  /**
   * Check status of Activity Comment in notification list
   * 
   * @param users is array of users
   * @param status is activity's status as: has commented on your activity,...
   * @param isPopUp =true if want to check on notification list popup =false if
   *          want to check on notification list page
   */
  public void checkStatusAC(ArrayList<String> users, String status, boolean isPopUp) {
    int lastIndex = users.size() - 1;
    if (users.size() > 3 && isPopUp == true) {
      info("Verify the activity message for more " + (lastIndex - 2) + " users comments");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_COMMENTS_CONTENT.replace("$comment", status)
                                                                             .replace("$number", users.get(lastIndex - 1)),
                               2000,
                               2);
    }

    if (users.size() > 3 && isPopUp == false) {
      info("Verify the activity message for more " + (lastIndex - 2) + " users comments");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_ALL_COMMENTS_CONTENT.replace("$comment", status)
                                                                                 .replace("$number", users.get(lastIndex - 1)),
                               2000,
                               2);
    }

    if (users.size() < 3 && isPopUp == true) {
      info("Verify the activity message for 2 or 1 comment(s)");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_COMMENTS_CONTENT.replace("$comment", status), 2000, 2);
    }

    if (users.size() < 3 && isPopUp == false) {
      info("Verify the activity message for 2 or 1 comment(s)");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_ALL_COMMENTS_CONTENT.replace("$comment", status), 2000, 2);
    }
  }

  /**
   * Check status of Notifications
   * 
   * @param status is a status's content of Notifications as: Like, comment,
   *          connection,mention...
   * @param user is full name or name of the user
   */
  public void checkStatus(String status, String user) {
    info("Verify that the status is shown");
    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if ($(byXpath(ELEMENT_INTRANET_NOTIFICATION_STATUS.
                replace("$status",status).replace("$fullName",user))).is(Condition.visible));
        break;
      }
      if ($(byXpath(ELEMENT_INTRANET_NOTIFICATION_STATUS.
              replace("$status",status).replace("$fullName",user))).is(Condition.visible))
      {
        info("Element " + ELEMENT_INTRANET_NOTIFICATION_STATUS.replace("$status", status).replace("$fullName", user)
                + " is displayed");
        break;
      }

    }
  }


  /**
   * Check status of space notifications
   * 
   * @param status is a status's content of Notifications
   * @param space is space's name
   */
  public void checkStatusSpace(String status, String space) {
    info("Verify that the status is shown");
    $(byXpath(ELEMENT_INTRANET_NOTIFICATION_STATUS_SPACE.replace("$status", status).replace("$space", space))).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Check not available notification in notifcation list
   * 
   * @param status is a status's content of Notifications
   * @param user is full name or name of the user
   */
  public void checkNotPresentStatus(String status, String user) {
    info("Verify that the status is not shown");
    evt.waitForElementNotPresent(ELEMENT_INTRANET_NOTIFICATION_STATUS.replace("$status", status).replace("$fullName", user));
  }

  public void checkNotStatusSpace(String status, String space) {
    info("Verify that the status isnot shown");
    $(byXpath(ELEMENT_INTRANET_NOTIFICATION_STATUS_SPACE.replace("$status", status).replace("$space", space))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Check order of Notifications in the list
   * 
   * @param num is order's number in the list
   * @param status is the status of Notification
   * @param fullName is the full name of the user that send the notification
   */
  public void checkOrderNotifications(int num, String status, String fullName) {
    info("Check on the notificaiton has index as:" + num);
    $(byXpath(ELEMENT_INTRANET_NOTIFICATION_STATUS_ORDER.replace("$num", String.valueOf(num))
                                                                       .replace("$status", status)
                                                                       .replace("$fullName", fullName))).waitUntil(Condition.visible,Configuration.timeout);

  }

  /**
   * Check unread notification
   * 
   * @param status is a status of the notification
   * @param fullName is a full name of the user that send the notification
   */
  public void checkUnreadNotification(String status, String fullName) {
    info("Check:" + status + " of the user:" + fullName);
    $(byXpath(ELEMENT_INTRANET_NOTIFICATION_UNREAD.replace("$status", status).replace("$fullName", fullName))).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Check read notification
   * 
   * @param status is a status of the notification
   * @param fullName is a full name of the user that send the notification
   */
  public void checkReadNotification(String status, String fullName) {
    info("Check:" + status + " of the user:" + fullName);
    evt.waitForElementNotPresent(ELEMENT_INTRANET_NOTIFICATION_UNREAD.replace("$status", status).replace("$fullName", fullName));
  }

  /**
   * Mark all as Read Notifications
   */
  public void markAllAsRead() {
    info("Click on Mark all as Read link");
    $(ELEMENT_INTRANET_NOTIFICATION_MARK_ALL_AS_READ).click();
    $(ELEMENT_INTRANET_NOTIFICATION_MARK_ALL_AS_READ).waitUntil(Condition.not(Condition.visible), Configuration.timeout);

  }

  /**
   * Remove an notification by index
   * 
   * @param num is order's number in notification list
   */
  public void removeNotificationByIndex(int num) {
    info("click on remove icon of the notification with an index:" + num);
    evt.click(ELEMENT_INTRANET_NOTIFICATION_REMOVE_ICON.replace("$num", String.valueOf(num)));

  }

  /**
   * Check the number of badge notification
   * 
   * @param num is the number that is shown
   */
  public void checkBadgeNoti(int num) {
    info("Check number of badge notification");
      ELEMENT_ALERT_NOTIFICATION_EXIST.parent().shouldHave(Condition.text(String.valueOf(num)));
  }

  /**
   * Check not any the number of badge notification
   * 
   * @param num is the number that is shown
   */
  public void checkNotBadgeNoti(int num) {
    info("Check number of badge notification");
    evt.waitForElementNotPresent(ELEMENT_INTRANET_NOTIFICATION_BADGE_NUMBER.replace("$num", String.valueOf(num)));
  }

  /**
   * Check format of Activity's comment in Notification list
   * 
   * @param users is array of users
   * @param status is as: has commented on your activity,...
   * @param actTitle is the title of the activity that is commented
   * @param isPopUp =true, if the notification list is shown in Notification list
   *          popup =false, if the notification list is shown in All notification
   *          list
   */
  public void checkFormatStatusCommentNotification(ArrayList<String> users, String status, String actTitle, boolean isPopUp) {
    info("users.size():" + users.size());
    checkAvatarInStatus(users, isPopUp);
    checkUsers(users, isPopUp);
    checkStatusAC(users, status, isPopUp);
    checkActivityTitleInStatus(actTitle, isPopUp);
  }

  /**
   * Check Activity's title is shown in notification list
   * 
   * @param actTitle String
   * @param isPopUp =true if want to check on notification list popup =false if
   *          want to check on notification list page
   */
  public void checkActivityTitleInStatus(String actTitle, boolean isPopUp) {
    if (!actTitle.isEmpty() && isPopUp == true) {
      info("Verify the activity's title is shown in the popup");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_ACTIVITY_TITLE.replace("$title", actTitle), 2000, 2);
    } else {
      info("Verify the activity's title is shown in the page");
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_ALL_ACTIVITY_TITLE.replace("$title", actTitle), 2000, 2);
    }
  }

  /**
   * Check avatar of notification list
   * 
   * @param users is array of users
   * @param isPopUp =true, if the notification list is shown in Notification list
   *          popup =false, if the notification list is shown in All notification
   *          list
   */
  public void checkAvatarInStatus(ArrayList<String> users, boolean isPopUp) {
    int lastIndex = users.size() - 1;
    info("users.size():" + users.size());
    info("Verify that last user's avatar is shown in list");
    if (isPopUp)
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_AVATAR.replace("$lastUser", users.get(lastIndex)), 2000, 2);
    else
      evt.waitForAndGetElement(ELEMENT_INTRANET_NOTIFICATION_ALL_AVATAR.replace("$lastUser", users.get(lastIndex)), 2000, 2);
  }

  /**
   * Check avatar of notification list
   * 
   * @param user is the username
   * @param isPopUp =true, if the notification list is shown in Notification list
   *          popup =false, if the notification list is shown in All notification
   *          list
   */
  public void checkAvatarInStatus(String user, boolean isPopUp) {
    info("Verify that last user's avatar is shown in list");
    if (isPopUp)
      $(byXpath(ELEMENT_INTRANET_NOTIFICATION_AVATAR.replace("$lastUser", user))).waitUntil(Condition.visible,Configuration.timeout);
    else
      $(byXpath(ELEMENT_INTRANET_NOTIFICATION_ALL_AVATAR.replace("$lastUser", user))).waitUntil(Condition.visible,Configuration.timeout);
  }

}
