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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
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
   * <li>Case ID:122997.</li>
   * <li>Test Case Name: Comment Intranet Notification.</li> Precondition: - User
   * A and User B are connected - User A has posted an activity - User B has
   * commented on User A activity - The notification "Someone comments on one of
   * my activities" is activated in User A settings Step Number: 1 Step Name: Step
   * 1: Check comment notification in the notification list Step Description: -
   * Login with User A - Click the notifications icon in the top navigation -
   * Check the notification list Input Data: Expected Outcome: - A comment
   * notification is displayed in the list - The activity message is : $AVATAR
   * $USER has commented on your activity $ACTIVITY $DATE Where : - $AVATAR is the
   * thumbnail of User B - $USER is User B - $ACTIVITY is the activity
   * title/message - $DATE is the date of the activity Step Number: 2 Step Name:
   * Step 2: Check comment notification in activity Viewer Step Description: -
   * Click the notification area Input Data: Expected Outcome: - The activity is
   * displayed in the activity viewer with all comment expanded. - The comment
   * that this notification is about is highlighted. Step Number: 3 Step Name:
   * Step 3: Check comment notification in the View All Step Description: - Click
   * [View All] Input Data: Expected Outcome: - The comment notification is
   * displayed in the View All page - The activity message is : $AVATAR $USER has
   * commented on your activity $ACTIVITY $DATE Where : - $AVATAR is the thumbnail
   * of User B - $USER is User B - $ACTIVITY is the activity title/message - $DATE
   * is the date of the activity
   */
  @Test
  public void test01_CommentIntranetNotification() {
    // Setup data test
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
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);

    info("Add a activity");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("user comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
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
    refresh();
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(comment1)).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
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
  public void test02_CommentNotificationWhenANewNotificationIsPushed() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";

    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";

    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();

    info("Add 2 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);

    info("Connect with 2 users");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);

    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    info("Add a activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("user1 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment1);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).should(Condition.exist);
    info("user2 comments in John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }

  /**
   * <li>Case ID:122998.</li>
   * <li>Test Case Name: Comments Intranet Notification merged.</li> Precondition:
   * - User A is connected with User B, User C and User D - User A has posted an
   * activity - User B has commented on User A activity - User C has commented on
   * User A activity - User D has commented on User A activity - User B has
   * commented again on User A activity - The notification "Someone comments on
   * one of my activities" is activated in User A settings Step Number: 1 Step
   * Name: Step 1: Check comment notification in notification list Step
   * Description: - Login with User A - Click the notifications icon in the top
   * navigation - Check the notification list Input Data: Expected Outcome: - A
   * comment notification is displayed in the list - The activity message is :
   * $LAST_AVATAR $LAST2_USERS and $COUNT more have commented on your activity
   * $ACTIVITY $DATE Where : - $LAST_AVATAR is the thumbnail of User B -
   * $LAST2_USERS is User D, User B - $COUNT is 2 - $ACTIVITY is the activity
   * title/message - $DATE is the date of the activity* Step Number: 2 Step Name:
   * Step 2: Check comment notification of in activity viewer Step Description: -
   * Click the notification area Input Data: Expected Outcome: - The activity is
   * displayed in the activity viewer with all comment expanded. Step Number: 3
   * Step Name: Step 3: Check comment notification of in View All Step
   * Description: - Click [View All] from the notification list Input Data:
   * Expected Outcome: - The comment notification is displayed in the View All
   * page - The activity message is : $LAST_AVATAR $LAST2_USERS and $COUNT more
   * have commented on your activity $ACTIVITY $DATE Where : - $LAST_AVATAR is the
   * thumbnail of User B - $LAST2_USERS is User D, User B - $COUNT is 2 -
   * $ACTIVITY is the activity title/message - $DATE is the date of the activity
   */
  @Test
  public void test03_CommentsIntranetNotificationMerged() {
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
    String comment2 = "comment2" + getRandomNumber();
    String comment3 = "comment3" + getRandomNumber();
    String comment4 = "comment4" + getRandomNumber();
    String password = "123456";

    info("Add 4 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    manageLogInOut.signIn(username1, password);

    info("Connect with 3 users");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);
    connectionsManagement.connectToAUser(username4);

    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);

    info("Add a activity");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("user1 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment1);

    info("user2 comments in John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment2);

    info("user3 comments in John's activity");
    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);

  }

  /**
   * <li>Case ID:122982.</li>
   * <li>Test Case Name: Connection Request Intranet notification (Accept).</li>
   * Precondition: - The notification "Someone sends me a connection request" is
   * activated in User Settings - User A sent a connection request to User B Step
   * Number: 1 Step Name: Step 1: Check notification list Step Description: -
   * Login with User B - Click the notifications icon - Check the notification
   * list Input Data: Expected Outcome: - The list includes a Connection Request
   * notifications. Step Number: 2 Step Name: Step 2: Check notification message
   * Step Description: - Check the notification message Input Data: Expected
   * Outcome: - The notification message is : - $AVATAR - $USER wants to connect
   * with you - $DATE - 2 actions : [Accept] | [Refuse] Where : - $AVATAR is
   * thumbnail of User A - $USER is User A - $DATE is the date of the notification
   * Step Number: 3 Step Name: Step 3: Check notification area Step Description: -
   * Click the notification area Input Data: Expected Outcome: - User B is
   * redirected to the profile of User A Step Number: 4 Step Name: Step 4: Accept
   * Step Description: - Click [Accept] Input Data: Expected Outcome: - The
   * connection request is accepted (User A and User B are connected) - The
   * notification message is updated to : $AVATAR You are connected with $USER
   * $DATE
   */
  @Test
  public void test04_ConnectionRequestIntranetNotification_Accept() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";

    info("Add a user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("Check connection request notification in notification list");
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
                                    .parent()
                                    .shouldHave(text(username1 + " " + username1 + " wants to connect with you"));
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
    ELEMENT_CONTENT_NAME_PROFILE.find(byText(username1 + " " + username1)).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  /**
   * <li>Case ID:122983.</li>
   * <li>Test Case Name: Connection Request Intranet notification (Accept).</li>
   * Precondition: - The notification "Someone sends me a connection request" is
   * activated in User Settings - User A sent a connection request to User B Step
   * Number: 1 Step Name: Step 1: Check notification list Step Description: -
   * Login with User B - Click the notifications icon - Check the notification
   * list Input Data: Expected Outcome: - The list includes a Connection Request
   * notifications. Step Number: 2 Step Name: Step 2: Check notification message
   * Step Description: - Check the notification message Input Data: Expected
   * Outcome: - The notification message is : - $AVATAR - $USER wants to connect
   * with you - $DATE - 2 actions : [Accept] | [Refuse] Where : - $AVATAR is
   * thumbnail of User A - $USER is User A - $DATE is the date of the notification
   * Step Number: 3 Step Name: Step 3: Check notification area Step Description: -
   * Click the notification area Input Data: Expected Outcome: - User B is
   * redirected to the profile of User A Step Number: 4 Step Name: Step 4: Accept
   * Step Description: - Click [Accept] Input Data: Expected Outcome: - The
   * connection request is accepted (User A and User B are connected) - The
   * notification message is updated to : $AVATAR You are connected with $USER
   * $DATE
   */
  @Test
  public void test05_ConnectionRequestIntranetNotification_Refuse() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";

    info("Add a user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("Check connection request notification in notification list");
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
                                    .parent()
                                    .shouldHave(text(username1 + " " + username1 + " wants to connect with you"));
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
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).parent().shouldNot(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  /**
   * <li>Case ID:123000.</li>
   * <li>Test Case Name: Like Intranet Notification.</li> Precondition: - User A
   * and User B are connected - User A has posted an activity - User B has liked
   * User A activity - The notification "Someone likes one of my activities" is
   * activated in the user settings Step Number: 1 Step Name: Step 1: Check
   * notifications list Step Description: - Login with User A - Click the
   * notifications icon in the top navigation - Check the notification list Input
   * Data: Expected Outcome: - A Like notification is displayed in the list - The
   * activity message is : $AVATAR $USER likes your activity. $ACTIVITY $DATE
   * Where : - $AVATAR is the thumbnail of User B - $USER is User B - $ACTIVITY is
   * the activity title/message - $DATE is the date of the activity Step Number: 2
   * Step Name: Step 2: Read the notification Step Description: - Click the
   * notification area Input Data: Expected Outcome: - The activity is displayed
   * in the activity viewer with all comment expanded. Step Number: 3 Step Name:
   * Step 3: Check notification message Step Description: - Click [View All] from
   * the notification list Input Data: Expected Outcome: - A Like notification is
   * displayed in the list - The activity message is : $AVATAR $USER likes your
   * activity. $ACTIVITY $DATE Where : - $AVATAR is the thumbnail of User B -
   * $USER is User B - $ACTIVITY is the activity title/message - $DATE is the date
   * of the activity
   */
  @Test
  public void test06_LikeIntranetNotification() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();

    info("Add User");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);

    info("Add a activity");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("user likes John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
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
    refresh();
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byText(activity)).parent().parent().parent().find(byText(username2 + " " + username2)).should(Condition.exist);
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
  public void test07_LikeIntranetNotificationMerged() {
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
    String password = "123456";

    String activity = "activity" + getRandomNumber();

    info("Add 4 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);
  }

  /**
   * <li>Case ID:123002.</li>
   * <li>Test Case Name: Like Notification when a new like is pushed.</li>
   * Precondition: - User A, User B, User C are connected - User A has posted an
   * activity - User B has liked User A activity - The notification "Someone likes
   * one of my activities" is activated in the user settings Step Number: 1 Step
   * Name: Step 1: Check notifications list Step Description: - Login with User A
   * - Click the notifications icon in the top navigation - Check the notification
   * list (keep the notification unread) Input Data: Expected Outcome: - A Like
   * notification is displayed in the list. Step Number: 2 Step Name: Step 2: Push
   * a new like notification Step Description: - With User C, like the activity of
   * User A - Check the notifications list Input Data: Expected Outcome: - The
   * Like notification is listed/merged in the same previous notification (step 1)
   * - The notification is displayed at the top of the list. Step Number: 3 Step
   * Name: Step 3: check the message of the Like notification Step Description: -
   * Check notification message in the notifications list and View All page Input
   * Data: Expected Outcome: - The notification message is : LAST_AVATAR
   * $USER_LIST like your activity $ACTIVITY $DATE Where : - $LAST_AVATAR is the
   * thumbnail of User C - $USER_LIST is User B, User C - $ACTIVITY is the
   * activity message/title - $DATE is the date of the last notification of User C
   */
  @Test
  public void test08_NotificationWhenANewLikeIsPushed() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();

    info("Add 2 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);
    info("Connect with 2 users");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);

    info("Add a activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("user 1 likes John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
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
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
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
  @Test
  public void test09_MentionIntranetNotification_InactivityMessage() throws AWTException {
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
    manageLogInOut.signIn(username1, password);

    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);

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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:122996.</li>
   * <li>Test Case Name: Mention Intranet notifications (in comment).</li>
   * 
   * @throws AWTException Precondition: - A wiki activity is generated (create a
   *           new page) or a content activity or an event activity - User A has
   *           mentioned User B directly in a comment Step Number: 1 Step Name:
   *           Step 1: Step Description: - Login with User B - Click the
   *           notification icons in the top navigation - Check the notification
   *           list Input Data: Expected Outcome: - The Mention notification is
   *           displayed in the list - The notification message is : $AVATAR $USER
   *           has mentioned you $ACTIVITY $DATE Where : - $AVATAR is the
   *           thumbnail of User A - $USER is User A - $ACTIVITY is the name of
   *           the wiki page - $DATE is the date of the notification. Step Number:
   *           2 Step Name: Step 2: Step Description: - Click the notification
   *           Input Data: Expected Outcome: - The activity is displayed in the
   *           activity viewer with all comments expanded. As the mention is made
   *           in a comment, this comment is highlighted. Step Number: 3 Step
   *           Name: Step 3: Step Description: - Go to [View All] Input Data:
   *           Expected Outcome: - The Mention notification is displayed in the
   *           page View All - The notification message is : $AVATAR $USER has
   *           mentioned you $ACTIVITY $DATE Where : - $AVATAR is the thumbnail of
   *           User A - $USER is User A - $ACTIVITY is the name of the wiki page -
   *           $DATE is the date of the notification.
   */
  @Test
  public void test10_MentionIntranetNotification_InComment() {
    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

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
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    info("Connect with user");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    info("User accepts Request notification and mention John in comment");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:121937.</li>
   * <li>Test Case Name: Relation Activity.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Invite another user Step
   * Description: - Login with User A and go to Intranet - Go to Connections -
   * Find user B and send a request Input Data: Expected Outcome: - Request is
   * sent to the user B* Step number: 2 Step Name: - Accept request Step
   * Description: - Login as user B and goto my Connection - Accept the request
   * from user A Input Data: Expected Outcome: - A Relation activity is displayed
   * to the activity stream
   */

  @Test
  public void test19_RelationActivity() {
    info("Test 19 Relation Activity");
    String username = "username" + getRandomString();
    String email = username + "@test.com";
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email, username, username);
    addUsers.addUser(username1, password, email1, username1, username1);

    manageLogInOut.signIn(username, password);

    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);

    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username);
    homePagePlatform.goToHomePage();
    $(byId("OfficeMiddle")).find(byText(username1+" "+username1)).parent().parent().parent().parent().find(byText("I'm now connected with 1 user(s)")).waitUntil(visible,Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username);
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:121938.</li>
   * <li>Test Case Name: Create a new space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Create a new Space Step
   * Description: - Goto homepage - Click [Join a space] on left Navigation -
   * Click [Add new space] button - Fill the information and click create - Check
   * homepage Input Data: Expected Outcome: - a new space is created - an activity
   * is added into activity stream - Informations displayed in the featured
   * content are : 1. Space's avatar 2. Space's description 3. Number of members.
   */
  @Test
  public void test20_CreateASpace() {
    info("Test 20 Create a new space");

    String space = "space" + getRandomNumber();
    String contentSpace = "contentSpace" + getRandomNumber();

    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, contentSpace);
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(contentSpace);
    $(byText(contentSpace)).parent().parent().find(byAttribute("alt", space)).should(exist);
    $(byText(contentSpace)).parent().parent().find(byText("1 Member")).should(exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  /**
   * <li>Case ID:123003.</li>
   * <li>Test Case Name: Post on my Stream Intranet notification.</li>
   * Precondition: - User A has posted an activity on User B activity stream - The
   * notification "Someone posts a message on my activity stream" is activated in
   * the user settings Step Number: 1 Step Name: Step 1: Check notification list
   * Step Description: - Login with User B - Click the notifications icon in the
   * top navigation - Check the notifications list Input Data: Expected Outcome: -
   * A Post on my Stream notifications is displayed in the list - The notification
   * message is : $AVATAR $USER has posted on your activity stream $ACTIVITY $DATE
   * Where : - $AVATAR is the thumbnail of User A - $USER is User A - $ACTIVITY is
   * the activity title / message - $DATE is the date of the notification.* Step
   * Number: 2 Step Name: Step 2: Click notification message Step Description: -
   * Click the notification message Input Data: Expected Outcome: - The activity
   * is displayed in the activity viewer with all comments expanded.* Step Number:
   * 3 Step Name: Step 3: Check View All Step Description: - Click [View All] in
   * the notification list Input Data: Expected Outcome: - A Post on my Stream
   * notifications is displayed in View All page - The notification message is :
   * $AVATAR $USER has posted on your activity stream $ACTIVITY $DATE Where : -
   * $AVATAR is the thumbnail of User A - $USER is User A - $ACTIVITY is the
   * activity title / message - $DATE is the date of the notification
   */
  @Test
  public void test11_PostOnMyStreamIntranetNotification() {
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
    manageLogInOut.signIn(username1, password);

    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Post_intranet);

    info("Connect with user");
    executeJavaScript("window.scrollBy(0,-5500)", "");
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
  public void test12_PostOnMySpaceIntranetNotification() {
    // Setup data test
    String space = "space" + getRandomNumber();
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
    manageLogInOut.signIn(username1, password);

    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);

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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

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
  public void test13_SpaceInvitationIntranetNotification_Accept() {
    // Setup data test
    String space = "space" + getRandomNumber();

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

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:122985.</li>
   * <li>Test Case Name: Space Invitation Intranet notification (Refuse).</li>
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
   * - $DATE is the date of the notification
   */
  @Test
  public void test14_SpaceInvitationIntranetNotification_Refuse() {
    // Setup data test
    String space = "space" + getRandomNumber();

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

    info("Check in notification list");
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().shouldHave(text("You're invited to join " + space + " space."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().parent().find(ELEMENT_BUTTON_CANCEL_INVITATION).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).shouldNot(exist);
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
  public void test15_SpaceJoinRequestIntranetNotification_Accept() {
    String space = "space" + getRandomNumber();

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
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().shouldHave(text(username2 + " " + username2
        + " has requested access to " + space + " space."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().parent().find(ELEMENT_BUTTON_ACCEPT_INVITATION).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space))
                                    .parent()
                                    .shouldHave(text(username2 + " " + username2 + " joined " + space + " space."));

    intranetNotification.goToAllNotification();
    $(byId("UIIntranetNotificationsPortlet")).find(byText(space))
                                             .parent()
                                             .shouldHave(text(username2 + " " + username2 + " joined " + space + " space."));
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:122987.</li>
   * <li>Test Case Name: Space Join Request Intranet notification (Refuse).</li>
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
   * - $DATE is the date of the notification
   */
  @Test
  @Tag("sabis")
  public void test16_SpaceJoinRequestIntranetNotification_Refuse() {
    String space = "space" + getRandomNumber();

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
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Join_Req_intranet);

    info("Add a new space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpace(space, space, "validation", "No", "");

    info("user requests to join space");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.sendARequestToASpace(space);

    info("check notification in notification list");
    manageLogInOut.signIn(username1, password);
    sleep(Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().shouldHave(text(username2 + " " + username2
        + " has requested access to " + space + " space."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().parent().find(ELEMENT_BUTTON_CANCEL_INVITATION).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).shouldNot(exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:122981.</li>
   * <li>Test Case Name: New User Intranet notification.</li> Precondition: - The
   * settings "Someone joins the social intranet" is activated in User Settings
   * for User A - A new user has joined the Intranet Step Number: 1 Step Name:
   * Step 1: Check notification list Step Description: - Login to the platform
   * with User A - Click the notifications icon - Check the list Input Data:
   * Expected Outcome: - A New User notification is displayed in the list. Step
   * Number: 2 Step Name: Step 2: Check notification message Step Description: -
   * Check the notification message Input Data: Expected Outcome: - The
   * notification message is : $AVATAR $USER has joined $PORTAL_NAME. $DATE Where
   * : - $USER is the new user - $AVATAR is the thumbnail of the new user - $DATE
   * is the date of the notification.
   */
  @Test
  @Tag("sabis")
  public void test17_NewUserIntranetNotification() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.NewUser_intranet);
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1))
                                    .parent()
                                    .shouldHave(text(username1 + " " + username1 + " has joined eXo."));
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).parent().click();
    ELEMENT_CONTENT_NAME_PROFILE.find(byText(username1 + " " + username1)).click();
    homePagePlatform.refreshUntil(ELEMENT_CONTENT_NAME_PROFILE,visible,1000);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    $(byText(username1 + " " + username1)).parent().shouldHave(text(username1 + " " + username1 + " has joined eXo."));
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.NewUser_intranet);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test01_2CommentIntranetNotificationWhenNotificationIsDisabled() {
    // Setup data test
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
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);

    info("Add a activity");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("user comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment1);

    info("noy Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);

    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).shouldNot(exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:123000.</li>
   * <li>Test Case Name: Like Intranet Notification.</li> Precondition: - User A
   * and User B are connected - User A has posted an activity - User B has liked
   * User A activity - The notification "Someone likes one of my activities" is
   * activated in the user settings Step Number: 1 Step Name: Step 1: Check
   * notifications list Step Description: - Login with User A - Click the
   * notifications icon in the top navigation - Check the notification list Input
   * Data: Expected Outcome: - A Like notification is displayed in the list - The
   * activity message is : $AVATAR $USER likes your activity. $ACTIVITY $DATE
   * Where : - $AVATAR is the thumbnail of User B - $USER is User B - $ACTIVITY is
   * the activity title/message - $DATE is the date of the activity
   */
  @Test
  public void test06_2_LikeIntranetNotificationWhenNotificationLikeIS_DISABLED() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();

    info("Add User");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);

    info("Add a activity");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("user likes John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);

    info("NOT Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test09_2_MentionIntranetNotification_InactivityMessageWhenNotificationIsDisabled() {
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
    manageLogInOut.signIn(username1, password);

    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);

    info("User accepts Request notification and mention John in activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.mentionUserActivity(username1, activity);
    activityStream.checkActivity(activity);

    info("Not Check Mention Intranet Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test10_2_MentionIntranetNotification_InCommentWhenNotificationIsDisabled() {
    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

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
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    info("Connect with user");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    info("User accepts Request notification and mention John in comment");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    activityStream.addCommentWithMentionUser(activity, username1, comment);
    info("Check Mention Intranet Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment)).shouldNot(exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test11_2_PostOnMyStreamIntranetNotificationWhenNotificationIsDisabled() {
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
    manageLogInOut.signIn(username1, password);

    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Post_intranet);

    info("Connect with user");
    executeJavaScript("window.scrollBy(0,-5500)", "");
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
    info("Not Check Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test12_2_PostOnMySpaceIntranetNotificationWhenNotificationIsDisabled() {
    // Setup data test
    String space = "space" + getRandomNumber();
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
    manageLogInOut.signIn(username1, password);

    info("Disable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);

    info("Add new space and ivite user");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User accepts Request notification and mention John in activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);

    info("user posted an activity inspace");
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("Not Check Notification in notification list");
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
  public void test13_2_SpaceInvitationIntranetNotificationWhenNotificationIsDisabled() {
    // Setup data test
    String space = "space" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username2, password);

    info("disable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.Space_Invitation_Intranet);

    info("Add new space and ivite an user");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("Not Check in notification list");
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(space)).parent().shouldNot(exist);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  @Tag("sabis")
  public void test15_2_SpaceJoinRequestIntranetNotificationWhenNotificationIsDisabled() {
    String space = "space" + getRandomNumber();

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

    info("Disable the notifications");
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
  public void test17_2_NewUserIntranetNotificationWhenNotificationIsDisabled() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Disable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.NewUser_intranet);
    info("Add user");
    navigationToolbar.goToIntranetNotification();
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).shouldNot(exist);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:122982.</li>
   * <li>Test Case Name: Connection Request Intranet notification (Accept).</li>
   */
  @Test
  public void test04_2_ConnectionRequestIntranetNotificationWhenNotificationIsDisabled() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    info("Add a user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    info("Disable the notifications");
    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.ConnectionRequest_intranet);
    manageLogInOut.signIn(username1, password);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("Check connection request notification in notification list");
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).shouldNot(exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test01_3_CommentIntranetNotificationInSpace() {
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
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test01_4_CommentIntranetNotificationInSpaceWhenNotoficationIsDisabled() {
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
  public void test02_2CommentNotificationWhenANewNotificationIsPushedInSpace() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";

    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";

    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();

    String space = "space" + getRandomNumber();

    info("Add 2 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
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

    info("user1 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
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
    sleep(2000);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout).click();
    $(byId("UINotificationPopoverToolbarPortlet")).find(byText(comment2))
            .parent()
            .parent()
            .parent()
            .shouldHave(Condition.text(username3 + " " + username3 + " and " + username2 + " "
                    + username2 + " have commented a post."));
    info("Check comment notification in the View All");
    intranetNotification.goToAllNotification();
    $(byClassName("contentSmall")).shouldHave(Condition.text(comment2)).shouldHave(Condition.text(username3 + " " + username3 + " and " + username2 + " " + username2 + " have commented a post."));
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }

  @Test
  public void test03_2CommentsIntranetNotificationMergedInSpace() {
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
    String comment2 = "comment2" + getRandomNumber();
    String comment3 = "comment3" + getRandomNumber();
    String comment4 = "comment4" + getRandomNumber();
    String password = "123456";
    String space = "space" + getRandomNumber();

    info("Add 4 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    manageLogInOut.signIn(username1, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
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
    activityStream.commentActivity(activity, comment1);

    info("user2 comments in John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.commentActivity(activity, comment2);

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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
  }

  @Test
  public void test02_3CommentNotificationWhenANewNotificationIsPushedInSpaceWhenNotificationIsDisabled() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";

    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";

    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();

    String space = "space" + getRandomNumber();
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);
    info("Disable comment notifications");
    navigationToolbar.goToMyNotifications();
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
    activityStream.commentActivity(activity, comment2);

    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment2)).shouldNot(Condition.exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }

  @Test
  @Tag("sabis")
  public void test03_3CommentsIntranetNotificationMergedInSpaceWhenNotificationIsDisabled() {
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
    String comment2 = "comment2" + getRandomNumber();
    String comment3 = "comment3" + getRandomNumber();
    String comment4 = "comment4" + getRandomNumber();
    String password = "123456";
    String space = "space" + getRandomNumber();

    info("Add 4 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    manageLogInOut.signIn(username1, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
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

    info("user2 comments in John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.commentActivity(activity, comment2);

    info("user3 comments in John's activity");
    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.commentActivity(activity, comment3);

    info("user2 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space);
    sleep(2000);
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    sleep(2000);
    activityStream.commentActivity(activity, comment4);
    info("Check comment notification in the notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNot(visible);
    navigationToolbar.goToIntranetNotification();
    info("Not Check comment notification in activity Viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
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
  public void test06_2LikeIntranetNotificationInSpace() {
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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  @Test
  public void test06_3LikeIntranetNotificationInSpaceWhenNotificationIsDisabled() {
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
   * in the user settings
   */
  @Test

  public void test07_1_LikeIntranetNotificationMergedWhenNotificationIsDisabled() {
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
    String password = "123456";

    String activity = "activity" + getRandomNumber();

    info("Add 4 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
    manageLogInOut.signIn(username1, password);
    info("Disable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with 4 users");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);
    connectionsManagement.connectToAUser(username4);
    connectionsManagement.connectToAUser(username5);
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
  public void test07_2_LikeIntranetNotificationMergedInSpace() {
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
    String password = "123456";

    String activity = "activity" + getRandomNumber();
    String space = "space" + getRandomNumber();

    info("Add 4 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
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

    info("user 2 likes John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeActivity(activity);

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
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);
  }

  @Test
  public void test07_3_LikeIntranetNotificationMergedInSpaceWhenNotificationIsDisabled() throws InterruptedException {
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
    String password = "123456";

    String activity = "activity" + getRandomNumber();
    String space = "space" + getRandomNumber();

    info("Add 4 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
    manageLogInOut.signIn(username1, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
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

    info("user 2 likes John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeActivity(activity);

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
  }

  /*
   * Precondition: - User A, User B, User C are connected - User A has posted an
   * activity - User B has liked User A activity - The notification
   * "Someone likes one of my activities" is activated in the user settings Step
   * Number: 1 Step Name: Step 1: Check notifications list Step Description: -
   * Login with User A - Click the notifications icon in the top navigation -
   * Check the notification list (keep the notification unread) Input Data:
   * Expected Outcome: - A Like notification is displayed in the list.
   */
  @Test
  public void test08_1_NotificationWhenANewLikeIsPushedWhenNotificationIsDisabled() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();

    info("Add 2 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);
    info("Connect with 2 users");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);
    info("disable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);

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

    info("NOtCheck Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNotBe(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    info("user 2 likes John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    info("NOT Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNotBe(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }

  /*
   * Precondition: - User A, User B, User C are connected - User A has posted an
   * activity - User B has liked User A activity - The notification
   * "Someone likes one of my activities" is activated in the user settings Step
   * Number: 1 Step Name: Step 1: Check notifications list Step Description: -
   * Login with User A - Click the notifications icon in the top navigation -
   * Check the notification list (keep the notification unread) Input Data:
   * Expected Outcome: - A Like notification is displayed in the list. Step
   * Number: 2 Step Name: Step 2: Push a new like notification Step Description: -
   * With User C, like the activity of User A - Check the notifications list Input
   * Data: Expected Outcome: - The Like notification is listed/merged in the same
   * previous notification (step 1) - The notification is displayed at the top of
   * the list. Step Number: 3 Step Name: Step 3: check the message of the Like
   * notification Step Description: - Check notification message in the
   * notifications list and View All page Input Data: Expected Outcome: - The
   * notification message is : LAST_AVATAR $USER_LIST like your activity $ACTIVITY
   * $DATE Where : - $LAST_AVATAR is the thumbnail of User C - $USER_LIST is User
   * B, User C - $ACTIVITY is the activity message/title - $DATE is the date of
   * the last notification of User C
   */
  @Test
  public void test08_2NotificationWhenANewLikeIsPushedInSpace() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();
    String space = "space" + getRandomNumber();

    info("Add 2 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
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
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }

  /*
   * Precondition: - User A, User B, User C are connected - User A has posted an
   * activity - User B has liked User A activity - The notification
   * "Someone likes one of my activities" is activated in the user settings Step
   * Number: 2 Step Name: Step 2: Push a new like notification Step Description: -
   * With User C, like the activity of User A - Check the notifications list Input
   * Data: Expected Outcome: - The Like notification is listed/merged in the same
   * previous notification (step 1) - The notification is displayed at the top of
   * the list.
   */
  @Test
  public void test08_3NotificationWhenANewLikeIsPushedInSpaceWhenNotificationIsDisabled() {
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    String activity = "activity" + getRandomNumber();
    String space = "space" + getRandomNumber();

    info("Add 2 users");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);
    info("Disable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Add a activity");
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    spaceSettingManagement.inviteUser(username3, false, "");
    info("user 1 likes John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeActivity(activity);

    info("Not Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNotBe(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);

    info("user 2 likes John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeActivity(activity);
    info("not Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNotBe(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).shouldNot(exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }

  @Test
  public void test09_3MentionIntranetNotification_InactivityMessageInSpace() throws AWTException {
    String activity = "activity" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String space = "space" + getRandomNumber();
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User accepts Request notification and mention John in activity");
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
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test09_4MentionIntranetNotification_InactivityMessageInSpaceWhenNotificationIsDisabled() throws AWTException {
    String activity = "activity" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String space = "space" + getRandomNumber();
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User accepts Request notification and mention John in activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.mentionUserActivity(username1, activity);
    activityStream.checkActivity(activity);

    info("not Check Mention Intranet Notification in notification list");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.shouldNotBe(visible);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2)).shouldNot(exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test10_3MentionIntranetNotification_InCommentInSpace() {
    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String space = "space" + getRandomNumber();
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
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    info("User accepts Request notification and mention John in comment");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
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
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test10_4MentionIntranetNotification_InCommentInSpaceWhenNotificationIsDisabled() {
    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String space = "space" + getRandomNumber();
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);
    info("Connect with user");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");
    info("User accepts Request notification and mention John in comment");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test18_OtherUserPuplishActivityInSpace() {
    String space = "space" + getRandomNumber();
    String activity = "activity" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);

  }

  @Test
  public void test18_1_OtherUserPuplishActivityInSpaceWhenNotificationIsDisabled() {
    String space = "space" + getRandomNumber();
    String activity = "activity" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
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
    info("Disable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.Space_Post_intranet);
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
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).shouldNot(exist);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test19_OtherUserCommentOnActivity() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";

    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernameb" + getRandomString();
    String email3 = username3 + "@test.com";
    String activity = "activity" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);

    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("user comments in John's activity");
    manageLogInOut.signIn(username2, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment1);

    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment1);
    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).should(Condition.exist);
    info("Check comment notification in activity Viewer");
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).click();
    $(byText(activity)).parent().parent().parent().parent().find(byText(comment1)).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);

  }

  @Test
  public void test19_1_OtherUserCommentOnActivityWhenNotificationIsDisabled() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernameb" + getRandomString();
    String email3 = username3 + "@test.com";
    String activity = "activity" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    info("user comments in John's activity");
    manageLogInOut.signIn(username2, password);
    info("Enable like and new user notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.disableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    ELEMENT_ALERT_NOTIFICATION.click();
    activityStream.commentActivity(activity, comment1);
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment1);
    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).shouldNot(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);

  }

  @Tag("PLF-7988")
  @Test
  public void test20_checkNotificationsAfterAttachLink() {
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

    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("Enable the notifications");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);

    info("User accepts Request notification and mention John in activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.mentionUserActivity(username1, "");
    manageLogInOut.signIn(username1, password);
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ALERT_NOTIFICATION.click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2))
                                    .parent()
                                    .shouldHave(text(username2 + " " + username2 + " has mentioned you."));
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }
}
