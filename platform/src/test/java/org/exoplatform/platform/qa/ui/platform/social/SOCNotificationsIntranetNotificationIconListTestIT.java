package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
@Tag("social")
@Tag("sniff")
public class SOCNotificationsIntranetNotificationIconListTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  IntranetNotification   intranetNotification;

  ManageLogInOut         manageLogInOut;

  MyNotificationsSetting myNotificationsSetting;

  HomePagePlatform       homePagePlatform;

  ConnectionsManagement  connectionsManagement;

  ActivityStream         activityStream;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    intranetNotification = new IntranetNotification(this);
    manageLogInOut = new ManageLogInOut(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }

    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:122978.</li>
   * <li>Test Case Name: Check UI of the notifications list.</li> Step Number: 1
   * Step Name: Step 1: Check notification icon Step Description: - Login to
   * platform - Click the notifications icon Input Data: Expected Outcome: - The
   * notifications list is displayed Step Number: 2 Step Name: Step 2: Check UI of
   * notification list Step Description: - Check the UI of list Input Data:
   * Expected Outcome: - A link [Mark as read] is displayed at the top of the
   * notification popover - The notifications are displayed in the good order :
   * from the newest at the top to the latest at the bottom. - Unread
   * notifications should look differently to read notifications. - A button [View
   * All] is displayed at the bottom of the page* Precondition: The user has
   * received new notifications (read and unread) : 1/ Like notifications (read)
   * 2/ Comment notification (unread) 3/ Connection request (read) Settings of the
   * user match with with the above notifications
   */
  @Test
  public void test01_CheckUIOfTheNotificationsList() throws InterruptedException {
    // set Data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";

    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";

    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";

    String username4 = "usernameb" + getRandomString();
    String email4 = username4 + "@test.com";

    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String password = "123456";

    info("Add 3 new users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);

    info("Check the notification icon in the top navigation");
    waitForAndGetElement(ELEMENT_POSITION_OF_INTRANET_NOTIFICATION);

    info("Check number of notifications after add more notifications");
    ELEMENT_ALERT_NOTIFICATION_EXIST.waitUntil(visible, Configuration.openBrowserTimeoutMs);
    intranetNotification.checkBadgeNoti(3);

    info("Check notification list");
    ELEMENT_ALERT_NOTIFICATION_EXIST.waitUntil(visible, Configuration.openBrowserTimeoutMs).click();
    intranetNotification.checkNotBadgeNoti(3);

    navigationToolbar.goToAddUser();
    addUsers.addUser(username4, password, email4, username4, username4);
    intranetNotification.checkBadgeNoti(1);

    info("Login with user 1 and enable new user and like notifications");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.NewUser_intranet);
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);

    info("Connect to user 2");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("User 1 add a activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("user 2 comments in user 1's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment);

    info("user 2 likes user1's activity");
    activityStream.likeActivity(activity);

    info("Login with user 3 and connect to user 1");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);

    info("Sign in with user1 and read/unread notification");
    info("Read comment notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION_EXIST.waitUntil(visible, Configuration.timeout).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment)).waitUntil(Condition.visible, Configuration.timeout).click();
    sleep(1000);
    info("Read connection request notification");
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username3 + " " + username3)).click();
    ELEMENT_CONTENT_NAME_PROFILE.find(byText(username3 + " " + username3)).waitUntil(appears, Configuration.timeout);

    info("Check notification icon");
    navigationToolbar.goToIntranetNotification();
    info("A link [Mark as read] is displayed at the top of the notification popover");
    $(ELEMENT_NOTIFICATION_MARK_ALL_AS_READ_WITH_POSITION).should(Condition.exist);
    info("The notifications are displayed in the good order : from the newest at the top to the latest at the bottom.");
    $(ELEMENT_NOTIFICATION_DROPDOWN).waitUntil(visible, Configuration.timeout)
                                    .find(byClassName("unread"))
                                    .shouldHave(text(username2 + " " + username2 + " likes your activity."));
    assertEquals($(ELEMENT_NOTIFICATION_DROPDOWN).waitUntil(visible, Configuration.timeout)
                                                 .find(byClassName("unread"))
                                                 .getCssValue("background-color"),
                 "rgba(227, 236, 246, 1)");
    $(ELEMENT_NOTIFICATION_DROPDOWN).findAll(byClassName("media")).get(0).has(Condition.text(username3 + " " + username3));
    info("A button [View All] is displayed at the bottom of the page");
    $(ELEMENT_VIEW_ALL_BUTTON).waitUntil(visible,Configuration.openBrowserTimeoutMs);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }

  @Test
  public void test02_CheckMarlAllAsReadInAllNotifcationPageThenClickMarkAsRead(){
    String username11 = "usernamea" + getRandomString();
    String username1 = "usernamea" + getRandomString();
    String FirstNameA="FirstNameA"+getRandomString();
    String LastNameA="LastNameA"+getRandomString();
    String email1 = username1 + "@test.com";
    String email11 = username11 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamea" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernameb" + getRandomString();
    String email4 = username4 + "@test.com";

    String password="123456"+getRandomNumber();
    String activity="activity"+getRandomString();
    String comment="comment"+getRandomString();

    navigationToolbar.goToAddUser();
    addUsers.addUser(username11, password, email11, username11, username11);
    addUsers.addUser(username1, password, email1, FirstNameA, LastNameA);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);

    info("Check UI Of The Notifications List For The First Connection");
    info("Login with user and check notification icon");
    manageLogInOut.signIn(username3, password);
    navigationToolbar.goToIntranetNotification();
    info("The button [View All] is not displayed");
    waitForElementNotPresent(ELEMENT_VIEW_ALL_BUTTON);
    info("The link [Mark as read] is not displayed");
    waitForElementNotPresent(ELEMENT_NOTIFICATION_MARK_ALL_AS_READ_WITH_POSITION);
    info("The UI indicates there is no notification to display");
    waitForAndGetElement(ELEMENT_NO_NOTIFICATIONS);

    info("Log in with John and connect with user");
    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username3);
    info("Log in with user and check intranet notification when reciving new notification");
    manageLogInOut.signIn(username3, password);
    ELEMENT_ALERT_NOTIFICATION_EXIST.waitUntil(visible, Configuration.timeout).click();
    info("The button [View All] is displayed");
    waitForAndGetElement(ELEMENT_VIEW_ALL_BUTTON);
    info("The link [Mark as read] is displayed");
    waitForAndGetElement(ELEMENT_NOTIFICATION_MARK_ALL_AS_READ_WITH_POSITION);
    info("The notification generated (Connection request) is displayed in the list");
    intranetNotification.checkBtnConnectJoinRequest(username4 + " " + username4);

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToHomePage();
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.NewUser_intranet);
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);

    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(PLFData.DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment);
    activityStream.likeActivity(activity);

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    ELEMENT_ALERT_NOTIFICATION_EXIST.waitUntil(visible, Configuration.timeout).click();
    ELEMENT_ALL_NOTIFICATION.click();
    homePagePlatform.refreshUntil($(byClassName("notificationsActions")).find(byId("markAllReadLink")),visible,1000);
    ELEMENT_NOTIFICATION_ACTIONS.find(byId("markAllReadLink")).waitUntil(visible,Configuration.timeout).click();

    info("Click Mark As Read");
    info("Log in user 1 and enable new user and like notifications");
    manageLogInOut.signIn(username11, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.NewUser_intranet);
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);

    info("Connect to user 2");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("User 1 add a activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("user 2 comments in user 1's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username11);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment);

    info("user 2 likes user1's activity");
    activityStream.likeActivity(activity);

    info("Log in user 1 and check 2 notifications above is unread");
    manageLogInOut.signIn(username11, password);
    ELEMENT_ALERT_NOTIFICATION_EXIST.waitUntil(visible, Configuration.timeout).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).findAll(byClassName("unread")).shouldHave(CollectionCondition.size(2));

    info("Click [Mark all as read] button and verify result");
    intranetNotification.markAllAsRead();
    $(ELEMENT_NOTIFICATION_DROPDOWN).findAll(byClassName("clearfix")).shouldHave(CollectionCondition.size(2));

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username11);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);

  }

}
