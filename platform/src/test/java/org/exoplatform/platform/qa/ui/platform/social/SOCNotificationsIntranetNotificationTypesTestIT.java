package org.exoplatform.platform.qa.ui.platform.social;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ICON_LIKE_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import java.awt.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.*;
@Tag("social")
@Tag("sniff")
public class SOCNotificationsIntranetNotificationTypesTestIT extends Base {
  NavigationToolbar      navigationToolbar;
  AddUsers               addUsers;
  ManageLogInOut         manageLogInOut;
  ConnectionsManagement  connectionsManagement;
  MyNotificationsSetting myNotificationsSetting;
  HomePagePlatform       homePagePlatform;
  ActivityStream         activityStream;
  IntranetNotification   intranetNotification;
  NotificationActivity   notificationActivity;
  SpaceManagement        spaceManagement;
  UserPageBase           userPageBase;
  SpaceHomePage          spaceHomePage;
  SpaceSettingManagement spaceSettingManagement;
  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    homePagePlatform = new HomePagePlatform(this);
    activityStream = new ActivityStream(this);
    intranetNotification = new IntranetNotification(this);
    notificationActivity = new NotificationActivity(this);
    spaceManagement = new SpaceManagement(this);
    userPageBase = new UserPageBase(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:122999.</li>
   * <li>Test Case Name: Comment notification when a new comment is pushed.</li>
   * Precondition: - User A, User B and User B are connected - User A has posted
   * an activity - User B has commented on User A activity - The notification
   * "Someone comments on one of my activities" is activated in User A settings
   * Step Number: 1 Step Name: Step 1: Check comment notification of user 1 Step
   * Description: - Login with User A - Click the notifications icon in the top
   * navigation - Check the notification list (keep the notification unread) Input
   * Data: Expected Outcome: - A comment notification is displayed in the list
   * (from User B) Step Number: 2 Step Name: Step 2: Check comment notification of
   * user 2 Step Description: - With User C, comment the activity of User A -
   * Check the notifications list Input Data: Expected Outcome: - The Comment
   * notification is listed/merged in the same previous notification (step 1) -
   * The notification is displayed at the top of the list Step Number: 3 Step
   * Name: Step 3: Check comment notification in View All Step Description: -
   * Check notification message in the list and in View All Input Data: Expected
   * Outcome: - The notification message is : LAST_AVATAR $USER_LIST have
   * commented on your activity $ACTIVITY $DATE Where : - $LAST_AVATAR is the
   * thumbnail of User C - $USER_LIST is User B, User C - $ACTIVITY is the
   * activity message/title - $DATE is the date of the last notification of User C
   */
  @Test
  public void test01_CommentNotificationWhenANewNotificationIsPushed() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";
    String username5 = "usernamed" + getRandomString();
    String email5 = username5 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.NewUser_intranet);
    info("Add users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
    info("New User Intranet Notification");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username5 + " " + username5))
            .parent()
            .shouldHave(text(username5 + " " + username5 + " has joined eXo."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username5 + " " + username5)).parent().click();
    ELEMENT_CONTENT_NAME_PROFILE.find(byText(username5 + " " + username5)).waitUntil(visible,Configuration.openBrowserTimeoutMs).click();
    homePagePlatform.refreshUntil(ELEMENT_CONTENT_NAME_PROFILE,visible,1000);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byText(username5 + " " + username5)).parent().shouldHave(text(username5 + " " + username5 + " has joined eXo."));
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.NewUser_intranet);
    manageLogInOut.signIn(username1, password);
    info("Connect with 2 users");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);
    connectionsManagement.connectToAUser(username4);
    info("Enable like and new user notifications");
    homePagePlatform.goToHomePage();
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    info("Add a activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    info("Check connection request notification in notification list");
    manageLogInOut.signIn(username4, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
            .parent()
            .shouldHave(text(username1 + " " + username1 + " wants to connect with you"));
    info("Refuse Connection");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_ACCEPT_INVITATION_IN_NOTIFICATION)
            .should(Condition.exist);
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_CANCEL_INVITATION_IN_NOTIFICATION)
            .should(Condition.exist);
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_CANCEL_INVITATION_IN_NOTIFICATION)
            .click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).parent().waitUntil(Condition.not(Condition.exist),Configuration.openBrowserTimeoutMs);
    info("Check connection request notification in notification list");
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
            .parent()
            .shouldHave(text(username1 + " " + username1 + " wants to connect with you"));
    info("Accept Connection");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_ACCEPT_INVITATION_IN_NOTIFICATION)
            .should(Condition.exist);
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_CANCEL_INVITATION_IN_NOTIFICATION)
            .should(Condition.exist);
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_ACCEPT_INVITATION_IN_NOTIFICATION)
            .click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
            .parent()
            .shouldHave(text("You are connected with " + username1 + " " + username1))
            .click();
    ELEMENT_CONTENT_NAME_PROFILE.find(byText(username1 + " " + username1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    info("user1 comments in John's activity");
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment1);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    info("Check comment notification in the View All");
    refresh();
    homePagePlatform.goToHomePage();
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(comment1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    info("Check comment notification in activity Viewer");
    $(byText(activity)).parent().parent().parent().parent().find(byText(comment1)).should(Condition.exist);
    manageLogInOut.signIn(username2, password);
    info("user likes John's activity");
    activityStream.likeActivity(activity);
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity))
            .parent()
            .parent()
            .shouldHave(text(username2 + " " + username2 + " likes your activity."));
    info("user2 comments in John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    info("Relation Activity");
    $(byId("OfficeMiddle")).find(byText(username3+" "+username3)).parent().parent().parent().parent().find(byText("I'm now connected with 1 user(s)")).waitUntil(visible,Configuration.timeout);
    activityStream.commentActivity(activity, comment2);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment2)).should(Condition.exist);
    info("Check comment notification in the View All");
    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(comment2))
            .parent()
            .parent()
            .parent()
            .shouldHave(Condition.text(username3 + " " + username3 + " and " + username2 + " "
                    + username2 + " have commented a post."));
    info("Notification When A New Like Is Pushed");
    manageLogInOut.signIn(username3, password);
    activityStream.likeActivity(activity);
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).parent().parent().shouldHave(text(username3 + " " + username3
            + " and " + username2 + " " + username2 + " like your activity."));
    refresh();
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byText(activity)).parent().parent().shouldHave(text(username3 + " " + username3 + " and " + username2 + " " + username2
            + " like your activity."));
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);
  }
  /**
   * <li>Case ID:123001.</li>
   * <li>Test Case Name: Like Intranet Notification merged.</li> Precondition: -
   * User A is connected with User B, User C, User D and User E - User A has
   * posted an activity - User B has liked User A activity - User C has liked User
   * A activity - User D has liked User A activity - User E has liked User A
   * activity - The notification "Someone likes one of my activities" is activated
   * in the user settings Step Number: 1 Step Name: Step 1: Check notifications
   * list Step Description: - Login with User A - Click the notifications icon in
   * the top navigation - Check the notification list Input Data: Expected
   * Outcome: - A Like notification is displayed in the list - The activity
   * message is : $LAST_AVATAR $LAST2_USERS and $COUNT more like your activity.
   * $ACTIVITY $DATE Where : - $LAST_AVATAR is the thumbnail of User E -
   * $LAST2_USERS is User E and User D - $COUNT is 2 - $ACTIVITY is the activity
   * title/message - $DATE is the date of the activity Step Number: 2 Step Name:
   * Step 2: Read the notification Step Description: - Click the notification area
   * Input Data: Expected Outcome: - The activity is displayed in the activity
   * viewer with all comment expanded. Step Number: 3 Step Name: Step 3: Check
   * notification message Step Description: - Click [View All] from the
   * notifications list Input Data: Expected Outcome: - A Like notification is
   * displayed in View All page - The activity message is : $LAST_AVATAR
   * $LAST2_USERS and $COUNT more like your activity. $ACTIVITY $DATE Where : -
   * $LAST_AVATAR is the thumbnail of User E - $LAST2_USERS is User E and User D -
   * $COUNT is 2 - $ACTIVITY is the activity title/message - $DATE is the date of
   * the activity.
   */
  @Test
  public void test02_LikeIntranetNotificationMergedThenCommentNotificationWhenANewNotificationIsPushedThenWhenNotificationIsDisabled() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";
    String username5 = "usernamee" + getRandomString();
    String email5 = username5 + "@test.com";
    String username11 = "usernamea" + getRandomString();
    String email11 = username11 + "@test.com";
    String username22 = "usernameb" + getRandomString();
    String email22 = username22 + "@test.com";
    String username33 = "usernamec" + getRandomString();
    String email33 = username33 + "@test.com";
    String username44 = "usernamed" + getRandomString();
    String email44 = username44 + "@test.com";
    String username55 = "usernamee" + getRandomString();
    String email55 = username55 + "@test.com";
    String password = "123456";
    String comment1 = "comment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();
    String comment3 = "comment3" + getRandomNumber();
    String comment4 = "comment4" + getRandomNumber();
    String comment5 = "comment5" + getRandomNumber();
    String activity = "activity" + getRandomNumber();
    info("Add 4 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
    addUsers.addUser(username11, password, email11, username11, username11);
    addUsers.addUser(username22, password, email22, username22, username22);
    addUsers.addUser(username33, password, email33, username33, username33);
    addUsers.addUser(username44, password, email44, username44, username44);
    addUsers.addUser(username55, password, email55, username55, username55);

    manageLogInOut.signIn(username1, password);
    info("Connect with 4 users");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);
    connectionsManagement.connectToAUser(username4);
    connectionsManagement.connectToAUser(username5);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    info("Add a activity");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    info("user 1 likes John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    info("user 2 likes John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    info("user 3 likes John's activity");
    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    info("user 4 likes John's activity");
    manageLogInOut.signIn(username5, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).parent().parent().shouldHave(text(username5 + " " + username5 + ", "
            + username4 + " " + username4 + " and 2 more like your activity."));
    info("Check comment notification in activity Viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).click();
    $(byText(activity)).parent().parent().parent().find(ELEMENT_ICON_LIKE_ACTIVITY).parent().shouldHave(text("4"));
    refresh();
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byText(activity)).parent().parent().shouldHave(text(username5 + " " + username5 + ", " + username4 + " " + username4
            + " and 2 more like your activity."));
    info("Comment Notification When A New Notification Is Pushed");
    info("user1 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    activityStream.commentActivity(activity, comment1);
    info("user2 comments in John's activity");
    manageLogInOut.signIn(username3, password);
    activityStream.commentActivity(activity, comment2);
    info("user3 comments in John's activity");
    manageLogInOut.signIn(username4, password);
    activityStream.commentActivity(activity, comment3);
    info("user2 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    activityStream.commentActivity(activity, comment4);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    info("Check comment notification in activity Viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).click();
    $(byText(activity)).parent().parent().parent().parent().find(byText(comment1)).should(Condition.exist);
    $(byText(activity)).parent().parent().parent().parent().find(byText(comment2)).should(Condition.exist);
    $(byText(activity)).parent().parent().parent().parent().find(byText(comment3)).should(Condition.exist);
    $(byText(activity)).parent().parent().parent().parent().find(byText(comment4)).should(Condition.exist);
    info("Check comment notification in the View All");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byText(activity)).parent().parent().shouldHave(text(username2 + " " + username2 + ", " + username4 + " " + username4
            + " and 1 more have commented a post."));
    $(byText(comment4)).should(Condition.exist);
    info("Other User Comment On Activity When Notification Is Disabled");
    manageLogInOut.signIn(username2, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    sleep(1000);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToConnections();
    homePagePlatform.goToHomePage();
    ELEMENT_ALERT_NOTIFICATION.click();
    activityStream.commentActivity(activity, comment5);
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment5);
    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment5)).shouldNot(Condition.exist);
    info("Like Intranet Notification Merged When Notification Is Disabled");
    manageLogInOut.signIn(username11, password);
    info("Disable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with 4 users");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username22);
    connectionsManagement.connectToAUser(username33);
    connectionsManagement.connectToAUser(username44);
    connectionsManagement.connectToAUser(username55);
    info("Add a activity");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    info("user 1 likes John's activity");
    manageLogInOut.signIn(username22, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username11);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    info("Notification When A New Like Is Pushed When Notification Is Disabled");
    info("NOtCheck Like notification in intranet notification");
    manageLogInOut.signIn(username11, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNotBe(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    info("user 2 likes John's activity");
    manageLogInOut.signIn(username33, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username11);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    info("NOT Check Like notification in intranet notification");
    manageLogInOut.signIn(username11, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNotBe(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    info("user 3 likes John's activity");
    manageLogInOut.signIn(username44, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username11);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    info("user 4 likes John's activity");
    manageLogInOut.signIn(username55, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username11);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username11, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);
    addUsers.deleteUser(username11);
    addUsers.deleteUser(username22);
    addUsers.deleteUser(username33);
    addUsers.deleteUser(username44);
    addUsers.deleteUser(username55);
  }

  /**
   * <li>Case ID:122995.</li>
   * <li>Test Case Name: Mention Intranet notifications (in activity
   * message).</li>
   *
   * @throws AWTException Precondition: - User A has mentioned User B directly in
   *           the activity message* Step Number: 1 Step Name: Step 1: Step
   *           Description: - Login with User B - Click the notification icons in
   *           the top navigation - Check the notification list Input Data:
   *           Expected Outcome: - The Mention notification is displayed in the
   *           list - The notification message is : $AVATAR $USER has mentioned
   *           you $ACTIVITY $DATE Where : - $AVATAR is the thumbnail of User A -
   *           $USER is User A - $ACTIVITY is the activity title/message - $DATE
   *           is the date of the notification* Step Number: 2 Step Name: Step 2:
   *           Step Description: - Click the notification message Input Data:
   *           Expected Outcome: - The activity is displayed in the activity
   *           viewer with all comments expanded.* Step Number: 3 Step Name: Step
   *           3: Step Description: - Click [View All] Input Data: Expected
   *           Outcome: - The activity is displayed in View All page - The
   *           notification message is : $AVATAR $USER has mentioned you $ACTIVITY
   *           $DATE Where : - $AVATAR is the thumbnail of User A - $USER is User
   *           A - $ACTIVITY is the activity title/message - $DATE is the date of
   *           the notification.
   */
  @Tag("PLF-7988")
  @Test
  public void test03_MentionIntranetNotification_InactivityMessage_Then_InCommentThenCheckNotificationsAfterAttachLink() throws AWTException {
    String activity = "activity" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String comment = "comment" + getRandomNumber();
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    info("Mention Intranet Notification Inactivity Message");
    info("User accepts Request notification and mention John in activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.mentionUserActivity(username1, activity);
    activityStream.checkActivity(activity);
    info("Check Mention Intranet Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2))
            .parent()
            .shouldHave(text(username2 + " " + username2 + " has mentioned you."));
    info("check notification in activity viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2)).parent().click();
    $(byClassName("uiActivityLoader")).find(byText(username1 + " " + username1))
            .parent()
            .shouldHave(Condition.text(username1 + " " + username1 + " " + activity));
    $(byClassName("uiActivityLoader")).find(byText(username1 + " " + username1))
            .parent()
            .parent()
            .parent()
            .find(byText(username2 + " " + username2))
            .should(exist);
    info("Check notification in view all");
    refresh();
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(username1 + " " + username1))
            .parent()
            .shouldHave(Condition.text(username1 + " " + username1 + " " + activity));
    $(byId("UIIntranetNotificationsPortlet")).find(byText(username1 + " " + username1))
            .parent()
            .parent()
            .parent()
            .find(byText(username2 + " " + username2))
            .should(exist);
    info("Mention Intranet Notification In Comment");
    info("User accepts Request notification and mention John in comment");
    manageLogInOut.signIn(username2, password);
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    activityStream.addCommentWithMentionUser(activity, username1, comment);
    info("Check Mention Intranet Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2))
            .parent()
            .shouldHave(text(username2 + " " + username2 + " has mentioned you."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2)).parent().click();
    $(byClassName("uiActivityLoader")).find(byText(username1 + " " + username1))
            .parent()
            .shouldHave(Condition.text(username1 + " " + username1 + " " + comment));
    homePagePlatform.refreshUntil($(byClassName("uiActivityLoader")).find(byText(username1 + " " + username1)),visible,1000);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(activity)).should(exist);
    info("Check Notifications After Attach Link");
    info("User accepts Request notification and mention John in activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToHomePage();
    ELEMENT_ACTIVITY_COMPOSER_FILE_TAB.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.mentionUserActivity(username1, "");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2))
            .parent()
            .shouldHave(text(username2 + " " + username2 + " has mentioned you."));
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:123004.</li>
   * <li>Test Case Name: Post on my Space Intranet notification.</li>
   * Precondition: - User A and User B are members of Space 1 - User B has posted
   * in Space 1 - The notification "An activity is posted on one of my spaces" is
   * activated in the user settings* Step Number: 1 Step Name: Step 1: Check
   * notification list Step Description: - Login with User B - Click the
   * notifications icon in the top navigation - Check the notifications list Input
   * Data: Expected Outcome: - A Post on my Space notifications is displayed in
   * the list - The notification message is : $AVATAR $USER has posted an activity
   * in the $SPACE space. $ACTIVITY $DATE Where : - $AVATAR is the thumbnail of
   * User B - $USER is User B - $SPACE is Space 1 - $ACTIVITY is the activity
   * title / message - $DATE is the date of the notification Step Number: 2 Step
   * Name: Step 2: Check notification message Step Description: - Check the
   * notification message Input Data: Expected Outcome: - The activity is
   * displayed in the activity viewer with all comments expanded. Step Number: 3
   * Step Name: Step 3: Check View All Step Description: - Click [View All] in the
   * notifications list Input Data: Expected Outcome: - A Post on my Space
   * notifications is displayed in the View All page - The notification message is
   * : $AVATAR $USER has posted an activity in the $SPACE space. $ACTIVITY $DATE
   * Where : - $AVATAR is the thumbnail of User B - $USER is User B - $SPACE is
   * Space 1 - $ACTIVITY is the activity title / message - $DATE is the date of
   * the notification
   */
  @Test
  public void test04_PostOnMySpaceIntranetNotificationThenOnMyStreamIntranetNotificationThenOnMySpaceWhenNotificationIsDisabled() {
    // Setup data test
    String space = "space" + getRandomNumber();
    String space1 = "space" + getRandomNumber();
    String contentSpace = "contentSpace" + getRandomNumber();
    String activity = "activity" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    info("Post On My Space Intranet Notification");
    manageLogInOut.signIn(username1, password);
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Post_intranet);
    info("Add new space and ivite user");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    info("User accepts Request notification and mention John in activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    info("user posted an activity inspace");
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    info("Check Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().shouldHave(text(username2 + " " + username2
            + " has posted an activity in the " + space + " space."));
    info("check notification in activity viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).click();
    $(byText(activity)).should(exist);
    homePagePlatform.refreshUntil($(byText(activity)),visible,1000);
    info("Check in view all");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(space)).parent().shouldHave(text(username2 + " " + username2
            + " has posted an activity in the " + space + " space."));
    info("Post On My Stream Intranet Notification");
    info("Connect with user");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    info("User accepts Request notification and post an activity in John's activity stream");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    connectionsManagement.goToUserByFullName(username1 + " " + username1);
    userPageBase.goToActivityTab();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    info("Check Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2))
            .parent()
            .shouldHave(text(username2 + " " + username2 + " has posted on your activity stream."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2)).parent().click();
    info("Check in activity viewer");
    $(byText(activity)).should(exist);
    info("Check notification in view all");
    homePagePlatform.refreshUntil($(byText(activity)),visible,1000);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byText(activity)).parent().parent().parent().find(byText(username2 + " " + username2)).should(exist);
    $(byText(activity)).parent()
            .parent()
            .parent()
            .parent()
            .parent()
            .find(byAttribute("alt", username2 + " " + username2))
            .should(exist);
    info("Post On My Space Intranet Notification When Notification Is Disabled");
    manageLogInOut.signIn(username1, password);
    info("Disable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);
    info("Add new space and ivite user");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, contentSpace);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(contentSpace);
    $(byText(contentSpace)).parent().parent().find(byAttribute("alt", space1)).should(exist);
    $(byText(contentSpace)).parent().parent().find(byText("1 Member")).should(exist);
    info("User accepts Request notification and mention John in activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space1);
    info("user posted an activity inspace");
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    info("Not Check Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space1)).shouldNot(exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test05_CommentThenLikeThenIntranetNotificationThenMentionIntranetNotificationInactivityMessageThenInCommentThenPostOnMyStreamIntranetNotificationWhenNotificationIsDisabled() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";
    String activity = "activity" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String activity2 = "activity" + getRandomNumber();
    String activity3 = "activity" + getRandomNumber();
    String comment2 = "comment" + getRandomNumber();
    String password = "123456";

    info("New User Intranet Notification When Notification Is Disabled");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.NewUser_intranet);
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    sleep(1000);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).shouldNot(exist);
    info("Connection Request Intranet Notification When Notification Is Disabled");
    info("Disable the notifications");
    manageLogInOut.signIn(username4, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.ConnectionRequest_intranet);
    manageLogInOut.signIn(username3, password);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username4);
    info("Check connection request notification in notification list");
    manageLogInOut.signIn(username4, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username3 + " " + username3)).shouldNot(exist);
    manageLogInOut.signIn(username1, password);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    info("Disable the notifications");
    navigationToolbar.goToMyNotifications();
    sleep(1000);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Post_intranet);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Add a activity");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    info("Comment Intranet Notification When Notification Is Disabled");
    info("user comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment1);
    info("Not Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).shouldNot(exist);
    info("Like Intranet Notification When Notification Like IS DISABLED");
    info("user likes John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    info("Not Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    info("Mention Intranet Notification Inactivity Message When Notification Is Disabled");
    info("User accepts Request notification and mention John in activity");
    manageLogInOut.signIn(username2, password);
    activityStream.mentionUserActivity(username1, activity);
    activityStream.checkActivity(activity);
    info("Not Check Mention Intranet Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    info("Mention Intranet Notification In Comment When Notification Is Disabled");
    info("User accepts Request notification and mention John in comment");
    manageLogInOut.signIn(username2, password);
    activityStream.addActivity(activity2, "");
    activityStream.checkActivity(activity2);
    activityStream.addCommentWithMentionUser(activity2, username1, comment2);
    info("Check Mention Intranet Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment2)).shouldNot(exist);
    info("Post On My Stream Intranet Notification When Notification Is Disabled");
    info("User accepts Request notification and post an activity in John's activity stream");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.goToUserByFullName(username1 + " " + username1);
    userPageBase.goToActivityTab();
    activityStream.addActivity(activity3, "");
    activityStream.checkActivity(activity3);
    info("Not Check Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity3)).shouldNot(exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);

  }

  /**
   * <li>Case ID:122984.</li>
   * <li>Test Case Name: Space Invitation Intranet notification (Accept).</li>
   * Precondition: - User A is manager of the space 1 - User A invite User B to
   * join the space 1 - Space Invitation notification is activated in the settings
   * of User B Step Number: 1 Step Name: Step 1: Check notification list Step
   * Description: - Login with User B - Click Notifications icon - Check the
   * notifications list Input Data: Expected Outcome: - A Space Invitation
   * notifications is displayed in the list Step Number: 2 Step Name: Step 2:
   * Check notification message Step Description: - Check the notification message
   * Input Data: Expected Outcome: The notification message is : <br />
   * - $AVATAR<br />
   * - You're invited to join $SPACE space<br />
   * - [Accept] | [Refuse]<br />
   * - $DATE<br />
   * <br />
   * Where : <br />
   * - $AVATAR is the thumbnail of the space<br />
   * - $SPACE is space 1<br />
   * - $DATE is the date of the notification Step Number: 3 Step Name: Step 3:
   * Check View all Step Description: - Click [View All] from the bottom of the
   * notification list - Check View All page Input Data: Expected Outcome: - The
   * notification is displayed in the View All page Step Number: 4 Step Name: Step
   * 4: Accept Step Description: - Click [Accept] Input Data: Expected Outcome: -
   * The invitation is approved (User B is member of the space) - The
   * notifications message is updated to : $AVATAR You joined $SPACE space. $DATE
   */
  @Test
  public void test06_SpaceInvitationIntranetNotification_AcceptThenRefuseThenWhenNotificationIsDisabled() {
    // Setup data test
    String space = "space" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();
    String space3 = "space2" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Invitation_Intranet);
    info("Add new space and ivite an user");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    info("Space Invitation Intranet Notification Accept");
    info("Check in notification list");
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().shouldHave(text("You're invited to join " + space + " space."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().parent().find(ELEMENT_BUTTON_ACCEPT_INVITATION).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space))
            .parent()
            .waitUntil(have(Condition.text("You joined " + space + " space.")), Configuration.timeout);
    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(space)).parent().find(byText("You joined " + space + " space."));
    info("Space Invitation Intranet Notification Refuse");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space2)).parent().shouldHave(text("You're invited to join " + space2 + " space."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space2)).parent().parent().find(ELEMENT_BUTTON_CANCEL_INVITATION).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space2)).shouldNot(exist);

    info("Space Invitation Intranet Notification When Notification Is Disabled");
    manageLogInOut.signIn(username2, password);
    info("disable the notifications");
    navigationToolbar.goToMyNotifications();
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(1000);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.Space_Invitation_Intranet);
    info("Add new space and ivite an user");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space3, space3);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    info("Not Check in notification list");
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space3)).parent().shouldNot(exist);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }


  /**
   * <li>Case ID:122986.</li>
   * <li>Test Case Name: Space Join Request Intranet notification (Accept).</li>
   * Precondition: - User A requested to join Space 1 - User B is manager of Space
   * 1 - Space Join Request notification is activated in User's B settings Step
   * Number: 1 Step Name: Step 1: check notification list Step Description: -
   * Login with User B - Click the notification icon - Check the notification list
   * Expected Outcome: - The Space Join Request notification is displayed in the
   * list Step Number: 2 Step Name: Step 2: Check content of notification Step
   * Description: - Check the notification message Expected Outcome: - The
   * notification message is :<br />
   * - $AVATAR<br />
   * - $USER has requested access to $SPACE space.<br />
   * - [Accept] | [Refuse]<br />
   * - $DATE<br />
   * <br />
   * Where : <br />
   * - $AVATAR is the thumbnail of User A<br />
   * - $USER is User A<br />
   * - $DATE is the date of the notification Step Number: 3 Step Name: Step 3:
   * Accept from View all Step Description: - Click [View All] at the bottom of
   * the notification list - Click [Accept] Expected Outcome: - The request is
   * accepted (User A is member of Space 1) - The notification message is updated
   * to : $AVATAR $USER joined $SPACE space. $DATE
   */
  @Test
  @Tag("sabis")
  public void test07_SpaceJoinRequestIntranetNotification_AcceptThenRefuseThenWhenNotificationIsDisabled() {
    String space = "space" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Space Join Request Intranet Notification Accept");
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Join_Req_intranet);
    info("Add a new space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpace(space, space,"validation", "No", "");
    info("user requests to join space");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.sendARequestToASpace(space);
    info("check notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().waitUntil(visible,Configuration.openBrowserTimeoutMs).shouldHave(text(username2 + " " + username2
            + " has requested access to " + space + " space."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().parent().find(ELEMENT_BUTTON_ACCEPT_INVITATION).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space))
            .parent()
            .shouldHave(text(username2 + " " + username2 + " joined " + space + " space."));
    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(space))
            .parent()
            .shouldHave(text(username2 + " " + username2 + " joined " + space + " space."));

    info("Space Join Request Intranet Notification Refuse");
    manageLogInOut.signIn(username1, password);
    info("Add a new space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpace(space2, space2, "validation", "No", "");
    info("user requests to join space");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.sendARequestToASpace(space2);
    info("check notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space2)).parent().shouldHave(text(username2 + " " + username2
            + " has requested access to " + space2 + " space."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space2)).parent().parent().find(ELEMENT_BUTTON_CANCEL_INVITATION).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space2)).shouldNot(exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space2, false);

    info("Space Join Request Intranet Notification When Notification Is Disabled");
    info("Disable the notifications");
    homePagePlatform.goToHomePage();
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.Space_Join_Req_intranet);
    info("Add a new space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("user requests to join space");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.sendARequestToASpace(space);
    info("Not check notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).shouldNot(exist);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test08_CommentIntranetNotificationInSpaceThenWhenNotificationIsDisabled() {
    // Setup data test
    String space = "space" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String activity = "activity" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    getExoWebDriver().getWebDriver().navigate().refresh();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Add a activity");
    sleep(1000);
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    info("user comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.commentActivity(activity, comment1);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).should(Condition.exist);
    info("Check comment notification in activity Viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).click();
    $(byText(activity)).parent().parent().parent().parent().find(byText(comment1)).should(Condition.exist);
    info("Check comment notification in the View All");
    info("Check in view all");
    homePagePlatform.refreshUntil($(byText(activity)),visible,1000);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(comment1)).should(Condition.exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    info("Comment Intranet Notification In Space When Notification Is Disabled");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    getExoWebDriver().getWebDriver().navigate().refresh();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Add a activity");
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    info("user comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.commentActivity(activity, comment1);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).shouldNot(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /*
   * Precondition: - User A, User B and User B are connected - User A has posted
   * an activity - User B has commented on User A activity - The notification
   * "Someone comments on one of my activities" is activated in User A settings
   * Step Number: 1 Step Name: Step 1: Check comment notification of user 1 Step
   * Description: - Login with User A - Click the notifications icon in the top
   * navigation - Check the notification list (keep the notification unread) Input
   * Data: Expected Outcome: - A comment notification is displayed in the list
   * (from User B) Step Number: 2 Step Name: Step 2: Check comment notification of
   * user 2 Step Description: - With User C, comment the activity of User A -
   * Check the notifications list Input Data: Expected Outcome: - The Comment
   * notification is listed/merged in the same previous notification (step 1) -
   * The notification is displayed at the top of the lis Step Number: 3 Step Name:
   * Step 3: Check comment notification in View All Step Description: - Check
   * notification message in the list and in View All Input Data: Expected
   * Outcome: - The notification message is : LAST_AVATAR $USER_LIST have
   * commented on your activity $ACTIVITY $DATE Where : - $LAST_AVATAR is the
   * thumbnail of User C - $USER_LIST is User B, User C - $ACTIVITY is the
   * activity message/title - $DATE is the date of the last notification of User C
   */
  @Test
  public void test09_CommentNotificationWhenANewNotificationIsPushedInSpaceThenWhenNotificationIsDisabled() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();
    String comment3 = "comment3" + getRandomNumber();
    String comment4 = "comment4" + getRandomNumber();
    String space = "space" + getRandomNumber();
    info("Add 2 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    manageLogInOut.signIn(username1, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    getExoWebDriver().getWebDriver().navigate().refresh();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Add a activity");
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    spaceSettingManagement.inviteUser(username3, false, "");
    spaceSettingManagement.inviteUser(username4, false, "");
    info("user1 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    sleep(1000);
    activityStream.commentActivity(activity, comment1);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).should(Condition.exist);
    info("user2 comments in John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.commentActivity(activity, comment2);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byId("UINotificationPopoverToolbarPortlet")).find(byText(comment2))
            .parent()
            .parent()
            .parent()
            .shouldHave(Condition.text(username3 + " " + username3 + " and " + username2 + " "
                    + username2 + " have commented a post."));
    info("Check comment notification in the View All");
    intranetNotification.goToAllNotification();
    Assert.assertEquals($(byXpath("//div[@class=\"status\"]")).getText(),username3 + " " + username3 + " and " + username2 + " "
            + username2 + " have commented a post.");
    Assert.assertEquals($(byXpath("//div[@class=\"content\"]/p")).getText(),activity);
    Assert.assertTrue($(byXpath("//div[@class=\"commentNoHtml\"]")).waitUntil(visible,Configuration.openBrowserTimeoutMs).getText().contains(comment2));
    info("Comments Intranet Notification Merged In Space");
    info("user3 comments in John's activity");
    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.commentActivity(activity, comment3);
    info("user2 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    activityStream.commentActivity(activity, comment4);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    info("Check comment notification in activity Viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).click();
    $(byText(activity)).parent().parent().parent().parent().find(byText(comment1)).should(Condition.exist);
    $(byText(activity)).parent().parent().parent().parent().find(byText(comment2)).should(Condition.exist);
    $(byText(activity)).parent().parent().parent().parent().find(byText(comment3)).should(Condition.exist);
    $(byText(activity)).parent().parent().parent().parent().find(byText(comment4)).should(Condition.exist);
    info("Check comment notification in the View All");
    homePagePlatform.refreshUntil($(byText(activity)),visible,1000);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byText(activity)).parent().parent().shouldHave(text(username2 + " " + username2 + ", " + username4 + " " + username4
            + " and 1 more have commented a post."));
    $(byText(comment4)).should(Condition.exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    info("Comment Notification When A New Notification Is Pushed In Space When Notification Is Disabled");
    manageLogInOut.signIn(username1, password);
    info("Disable comment notifications");
    navigationToolbar.goToMyNotifications();
    sleep(1000);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Add a activity");
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    spaceSettingManagement.inviteUser(username3, false, "");
    spaceSettingManagement.inviteUser(username4, false, "");
    info("user1 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.commentActivity(activity, comment1);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).shouldNot(Condition.exist);
    info("user2 comments in John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    sleep(1000);
    activityStream.commentActivity(activity, comment2);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment2)).shouldNot(Condition.exist);
    info("Comments Intranet Notification Merged In Space When Notification Is Disabled");
    info("user3 comments in John's activity");
    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    sleep(1000);
    activityStream.commentActivity(activity, comment3);
    info("user2 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    activityStream.commentActivity(activity, comment4);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    info("Not Check comment notification in activity Viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).waitUntil(Condition.not(Condition.exist),Configuration.openBrowserTimeoutMs);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
  }

  @Test
  public void test10_LikeIntranetNotificationInSpaceThenWhenNotificationIsDisabled() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();
    String space = "space" + getRandomNumber();
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Add a activity");
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    info("user likes John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeActivity(activity);
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity))
            .parent()
            .parent()
            .shouldHave(text(username2 + " " + username2 + " likes your activity."));
    info("Check comment notification in activity Viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).click();
    $(byText(activity)).parent().parent().parent().find(ELEMENT_ICON_LIKE_ACTIVITY).parent().shouldHave(text("1"));
    homePagePlatform.refreshUntil($(byText(activity)).parent().parent().parent().find(ELEMENT_ICON_LIKE_ACTIVITY),visible,1000);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byText(activity)).parent().parent().parent().find(byText(username2 + " " + username2)).should(Condition.exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    info("Like Intranet Notification In Space When Notification Is Disabled");
    manageLogInOut.signIn(username1, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    sleep(1000);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Add a activity");
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    info("user likes John's activity");
    sleep(1000);
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeActivity(activity);
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    ;
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:123001.</li>
   * <li>Test Case Name: Like Intranet Notification merged.</li> Precondition: -
   * User A is connected with User B, User C, User D and User E - User A has
   * posted an activity - User B has liked User A activity - User C has liked User
   * A activity - User D has liked User A activity - User E has liked User A
   * activity - The notification "Someone likes one of my activities" is activated
   * in the user settings Step Number: 1 Step Name: Step 1: Check notifications
   * list Step Description: - Login with User A - Click the notifications icon in
   * the top navigation - Check the notification list Input Data: Expected
   * Outcome: - A Like notification is displayed in the list - The activity
   * message is : $LAST_AVATAR $LAST2_USERS and $COUNT more like your activity.
   * $ACTIVITY $DATE Where : - $LAST_AVATAR is the thumbnail of User E -
   * $LAST2_USERS is User E and User D - $COUNT is 2 - $ACTIVITY is the activity
   * title/message - $DATE is the date of the activity Step Number: 2 Step Name:
   * Step 2: Read the notification Step Description: - Click the notification area
   * Input Data: Expected Outcome: - The activity is displayed in the activity
   * viewer with all comment expanded. Step Number: 3 Step Name: Step 3: Check
   * notification message Step Description: - Click [View All] from the
   * notifications list Input Data: Expected Outcome: - A Like notification is
   * displayed in View All page - The activity message is : $LAST_AVATAR
   * $LAST2_USERS and $COUNT more like your activity. $ACTIVITY $DATE Where : -
   * $LAST_AVATAR is the thumbnail of User E - $LAST2_USERS is User E and User D -
   * $COUNT is 2 - $ACTIVITY is the activity title/message - $DATE is the date of
   * the activity.
   */
  @Test
  public void test11_LikeIntranetNotificationMergedInSpaceThenWhenNotificationIsDisabled() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";
    String username5 = "usernamee" + getRandomString();
    String email5 = username5 + "@test.com";
    String username11 = "usernamea" + getRandomString();
    String email11 = username11 + "@test.com";
    String username22 = "usernameb" + getRandomString();
    String email22 = username22 + "@test.com";
    String username33 = "usernamec" + getRandomString();
    String email33 = username33 + "@test.com";
    String username44 = "usernamed" + getRandomString();
    String email44 = username44 + "@test.com";
    String username55 = "usernamee" + getRandomString();
    String email55 = username55 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();
    String space = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();
    info("Add 4 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
    addUsers.addUser(username11, password, email11, username11, username11);
    addUsers.addUser(username22, password, email22, username22, username22);
    addUsers.addUser(username33, password, email33, username33, username33);
    addUsers.addUser(username44, password, email44, username44, username44);
    addUsers.addUser(username55, password, email55, username55, username55);
    manageLogInOut.signIn(username1, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Add a activity");
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    spaceSettingManagement.inviteUser(username3, false, "");
    spaceSettingManagement.inviteUser(username4, false, "");
    spaceSettingManagement.inviteUser(username5, false, "");
    info("user 1 likes John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeActivity(activity);
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity))
            .parent()
            .parent()
            .shouldHave(text(username2 + " " + username2 + " likes your activity."));
    info("user 2 likes John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeActivity(activity);
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).parent().parent().shouldHave(text(username3 + " " + username3
            + " and " + username2 + " " + username2 + " like your activity.")).waitUntil(visible,Configuration.timeout);
    $(byText(activity)).parent().parent().shouldHave(text(username3 + " " + username3 + " and " + username2 + " " + username2
            + " like your activity.")).waitUntil(visible,Configuration.timeout);
    info("user 3 likes John's activity");
    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeActivity(activity);
    info("user 4 likes John's activity");
    manageLogInOut.signIn(username5, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeActivity(activity);
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).parent().parent().shouldHave(text(username5 + " " + username5 + ", "
            + username4 + " " + username4 + " and 2 more like your activity."));
    info("Check comment notification in activity Viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).click();
    $(byText(activity)).parent().parent().parent().find(ELEMENT_ICON_LIKE_ACTIVITY).parent().shouldHave(text("4"));
    homePagePlatform.refreshUntil($(byText(activity)),visible,1000);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byText(activity)).parent().parent().shouldHave(text(username5 + " " + username5 + ", " + username4 + " " + username4
            + " and 2 more like your activity."));
    info("Like Intranet Notification Merged In Space When Notification Is Disabled");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(username11, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    info("Add a activity");
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username22, false, "");
    spaceSettingManagement.inviteUser(username33, false, "");
    spaceSettingManagement.inviteUser(username44, false, "");
    spaceSettingManagement.inviteUser(username55, false, "");
    info("user 1 likes John's activity");
    manageLogInOut.signIn(username22, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space2);
    activityStream.likeActivity(activity);
    info("Not Check Like notification in intranet notification");
    manageLogInOut.signIn(username11, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNotBe(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    info("user 2 likes John's activity");
    manageLogInOut.signIn(username33, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space2);
    activityStream.likeActivity(activity);
    info("not Check Like notification in intranet notification");
    manageLogInOut.signIn(username11, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNotBe(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    info("user 3 likes John's activity");
    manageLogInOut.signIn(username44, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space2);
    activityStream.likeActivity(activity);
    info("user 4 likes John's activity");
    manageLogInOut.signIn(username55, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space2);
    activityStream.likeActivity(activity);
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username11, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space2, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);
    addUsers.deleteUser(username11);
    addUsers.deleteUser(username22);
    addUsers.deleteUser(username33);
    addUsers.deleteUser(username44);
    addUsers.deleteUser(username55);
  }

  @Test
  public void test12_MentionIntranetNotification_InComment_Then_InactivityMessageInSpace() {
    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String space = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    info("Connect with user");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    sleep(1000);
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    info("Mention Intranet Notification Inactivity Message In Space");
    info("User accepts Request notification and mention John in comment");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.mentionUserActivity(username1, activity);
    activityStream.checkActivity(activity);
    info("Check Mention Intranet Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText("has mentioned you."))
            .parent()
            .shouldHave(text(username2 + " " + username2 + " has mentioned you."));
    info("check notification in activity viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2)).parent().click();
    $(byClassName("uiActivityLoader")).find(byText(username1 + " " + username1))
            .parent()
            .shouldHave(Condition.text(username1 + " " + username1 + " " + activity));
    $(byClassName("uiActivityLoader")).find(byText(username1 + " " + username1))
            .parent()
            .parent()
            .parent()
            .find(byText(username2 + " " + username2))
            .should(exist);
    info("Check notification in view all");
    homePagePlatform.refreshUntil($(byText(username1+" "+username1)),visible,1000);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(username1 + " " + username1))
            .parent()
            .shouldHave(Condition.text(username1 + " " + username1 + " " + activity));
    $(byId("UIIntranetNotificationsPortlet")).find(byText(username1 + " " + username1))
            .parent()
            .parent()
            .parent()
            .find(byText(username2 + " " + username2))
            .should(exist);
    info("Mention Intranet Notification In Comment In Space");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    activityStream.addCommentWithMentionUser(activity, username1, comment);
    info("Check Mention Intranet Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2))
            .parent()
            .shouldHave(text(username2 + " " + username2 + " has mentioned you."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2)).parent().click();
    $(byClassName("uiActivityLoader")).find(byText(username1 + " " + username1))
            .parent()
            .shouldHave(Condition.text(username1 + " " + username1 + " " + comment));
    homePagePlatform.refreshUntil($(byText(username1+" "+username1)),visible,1000);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(activity)).should(exist);
    manageLogInOut.signIn(username1, password);
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    info("Mention Intranet Notification Inactivity Message In Space When Notification Is Disabled");
    info("User accepts Request notification and mention John in activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space2);
    activityStream.mentionUserActivity(username1, activity);
    activityStream.checkActivity(activity);
    info("not Check Mention Intranet Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNotBe(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2)).shouldNot(exist);
    info("Mention Intranet Notification In Comment In Space When Notification Is Disabled");
    info("User accepts Request notification and mention John in comment");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space2);
    spaceManagement.accessToSearchedSpace();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    activityStream.addCommentWithMentionUser(activity, username1, comment);
    info("Check Mention Intranet Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2)).shouldNot(exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    spaceManagement.deleteSpace(space2, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test13_OtherUserPuplishActivityInSpaceThenWhenNotificationIsDisabled() {
    String space = "space" + getRandomNumber();
    String activity = "activity" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String username11 = "usernamea" + getRandomString();
    String email11 = username11 + "@test.com";
    String username22 = "usernameb" + getRandomString();
    String email22 = username22 + "@test.com";
    String username33 = "usernamec" + getRandomString();
    String email33 = username33 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username11, password, email11, username11, username11);
    addUsers.addUser(username22, password, email22, username22, username22);
    addUsers.addUser(username33, password, email33, username33, username33);
    manageLogInOut.signIn(username1, password);
    info("Add new space and ivite user");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    spaceSettingManagement.inviteUser(username3, false, "");
    manageLogInOut.signIn(username2, password);
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    manageLogInOut.signIn(username3, password);
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    manageLogInOut.signIn(username1, password);
    info("Enable the notifications");
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    activityStream.addActivity(activity, "");
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).parent().shouldHave(text(username1 + " "
            + username1 + " has posted an activity in the " + space + " space."));
    manageLogInOut.signIn(username3, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).parent().shouldHave(text(username1 + " "
            + username1 + " has posted an activity in the " + space + " space."));
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    info("Other User Puplish Activity In Space When Notification Is Disabled");
    manageLogInOut.signIn(username11, password);
    info("Add new space and ivite user");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username22, false, "");
    spaceSettingManagement.inviteUser(username33, false, "");
    manageLogInOut.signIn(username22, password);
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    manageLogInOut.signIn(username33, password);
    info("Disable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    manageLogInOut.signIn(username11, password);
    info("Enable the notifications");
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    activityStream.addActivity(activity, "");
    manageLogInOut.signIn(username22, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username11 + " " + username11)).parent().shouldHave(text(username11 + " "
            + username11 + " has posted an activity in the " + space + " space."));
    manageLogInOut.signIn(username33, password);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username11 + " " + username11)).shouldNot(exist);
    manageLogInOut.signIn(username11, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username11);
    addUsers.deleteUser(username22);
    addUsers.deleteUser(username33);
  }

}
