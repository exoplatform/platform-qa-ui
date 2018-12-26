package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_PROFILE_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.xbill.DNS.Options.refresh;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationActivity;

@Tag("functional")
@Tag("social")

public class SOCNotificationIntranetViewAllNotificationsTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  UserAddManagement      userAddManagement;

  ActivityStream         activityStream;

  IntranetNotification   intranetNotification;

  MyNotificationsSetting myNotificationsSetting;

  NotificationActivity   notificationActivity;

  ConnectionsManagement  connectionsManagement;

  UserProfilePage        userProfilePage;

  UserAndGroupManagement userAndGroupManagement;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    userAddManagement = new UserAddManagement(this);
    activityStream = new ActivityStream(this);
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    intranetNotification = new IntranetNotification(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    notificationActivity = new NotificationActivity(this);
    userAddManagement = new UserAddManagement(this);
    connectionsManagement = new ConnectionsManagement(this);
    userProfilePage = new UserProfilePage(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
  }

  /**
   * <li>Case ID:125167.</li>
   * <li>Test Case Name: Check View All page.</li>
   * <li>Pre-Condition: The user has several notifications - Like - Comment -
   * Connection request - Post in My stream</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All page Step Description: -
   * Login - Click the notifications icon in the top navigation - Click View All
   * button Input Data: Expected Outcome: - The user is redirected to the View All
   * page
   */

  /*
   * Step number: 2 Step Name: Step 2 : Check title of the page Step Description:
   * - Check title of the View All page Input Data: Expected Outcome: - The title
   * of the page isAll Notifications
   */
  /*
   * Step number: 3 Step Name: Step 3 : Check content of the View All page Step
   * Description: - Check the content of the list Input Data: Expected Outcome:
   * The page displays the following notifications - Like - Comment - Connection
   * request - Post in My stream
   */
  @Test
  public void test01_CheckViewAllPage() throws Exception {
    info("Test 1: Check View All page");
    ArrayList<String> arrayUser = new ArrayList<String>();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    userAddManagement.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Like_intranet);
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Post_intranet);

    info("User A sent a connection request to User B");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("User A add an activity");
    String activity = "activity" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, null);
    activityStream.checkActivity(activity);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User A and User B are connected");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.acceptRqConnection(username1);

    info("Like UserA's activity");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(activity)), Condition.visible, 1000);
    activityStream.likeActivity(activity);

    info("Add a comment to UserA's activity");
    String comment = "comment" + getRandomNumber();
    ArrayList<String> comments = new ArrayList<String>();
    activityStream.commentActivity(activity, comment);

    info("Add comment to Comment list");
    comments.add(comment);

    info("User B add an activity in UserA's activity stream");
    manageLogInOut.signIn(username2, password);
    String Activity = "activity " + getRandomNumber();
    activityStream.addActivity(Activity, "");
    activityStream.checkActivity(Activity);

    info("User C login");
    manageLogInOut.signIn(username3, password);

    info("User C sent a connection request to User A");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);

    info("User A login");
    manageLogInOut.signIn(username1, password);

    info("The user is redirected to the View All page");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();

    String titlePage = title();
    if (titlePage.equals("All Notifications") == true)
      assert true;
    else
      assert false : "the page's title is not correct";

    String likeStatus = " likes your activity ";
    String commentStatus = "has commented on you activity ";
    String postStatus = "has posted the activity ";
    String connectStatus = " ";
    intranetNotification.checkStatus(likeStatus, username2);
    intranetNotification.checkStatus(commentStatus, username2);
    intranetNotification.checkStatus(postStatus, username2);
    intranetNotification.checkStatus(connectStatus, username2);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }

  /**
   * <li>Case ID:125169.</li>
   * <li>Test Case Name: Click a New User notification from View All page.</li>
   * <li>Pre-Condition: - User A has received a New User notification from User
   * B</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All Step Description: - Login
   * with User A - Go to View all Input Data: Expected Outcome: - The View All
   * page is displayed - The New User notification is available/displayed in the
   * page
   */

  /*
   * Step number: 2 Step Name: Step 2 : Check click on Notification Step
   * Description: - Click the New User notification Input Data: Expected Outcome:
   * - The user is redirected to the profile of User B
   */
  @Test
  public void test03_ClickANewUserNotificationFromViewAllPage() throws Exception {
    info("Test 3: Click a New User notification from View All page");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, "123456", email1, username1, username1);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.addUserAdmin(username1);
    manageLogInOut.signIn(username1, password);

    info("Go to My notification");
    navigationToolbar.goToMyNotifications();
    info("Enable new user notification");
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.NewUser_intranet);
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username2, password, email2, username2, username2);
    refresh();
    info("The user is redirected to the View All page");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    String connectStatus = "has joined";
    intranetNotification.checkStatus(connectStatus, username2);

    homePagePlatform.refreshUntil($(byText(username2 + " " + username2)), Condition.visible, 1000);
    intranetNotification.goToDetailNewUserJoinIntranet(username2, false);
    $(byXpath(ELEMENT_PROFILE_TITLE.replace("${fullName}", username2 + " " + username2))).waitUntil(Condition.visible,
                                                                                                    Configuration.timeout);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125170.</li>
   * <li>Test Case Name: Accept a Connection Request from View All.</li>
   * <li>Pre-Condition: User A sent a connection request to User B</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All Step Description: - Login
   * with User B - Click notifications icon in the top navigation - Go to View All
   * page Input Data: Expected Outcome: - A connection request notification is
   * displayed in the page
   */

  /*
   * Step number: 2 Step Name: Step 2 : Accept the Connection Request Step
   * Description: - Click [Accept] Input Data: Expected Outcome: - The connection
   * is approved, the 2 users are connected
   */
  /*
   * Step number: 3 Step Name: Step 3: Check the notification message Step
   * Description: - Check the notification message Input Data: Expected Outcome:
   * After clicking Accept, the notification message is updated to :$AVATARYou are
   * connected with $USER$DATEWhere : - $AVATAR is thumbnail of User A - $USER is
   * User A - $DATE is the date of the notification
   */
  /*
   * Step number: 4 Step Name: Step 4 : Click the notification Step Description: -
   * Click the notification area Input Data: Expected Outcome: - User B is
   * redirected to the profile of User A
   */
  @Test
  public void test04_AcceptAConnectionRequestFromViewAll() throws Exception {
    info("Test 4: Accept a Connection Request from View All");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String fullName1 = username1 + " " + username1;
    String password = "123456";

    info("Add new user");
    ArrayList<String> arrayUser = new ArrayList<String>();
    arrayUser.add(username2);
    arrayUser.add(username1);
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("User A sent a connection request to User B");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("The user is redirected to the View All page");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();

    info("A connection request notification is displayed in the page");
    String connectStatus = "wants to connect with you";
    intranetNotification.checkStatus(connectStatus, username1);

    info("Click [Accept]");
    homePagePlatform.refreshUntil($(byText(username1 + " " + username1)), Condition.visible, 1000);
    intranetNotification.acceptRqConnection(username1);

    info("The connection is approved, the 2 users are connected");
    homePagePlatform.goToConnections();
    connectionsManagement.verifyConnection(username1, true);

    info("Check format of the notification after accepted");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    String connectAcceptStatus = "You are connected with ";
    intranetNotification.checkStatus(connectAcceptStatus, username1);
    intranetNotification.checkAvatarInStatus(arrayUser, false);
    intranetNotification.checkUsers(arrayUser, false);

    info("User B is redirected to the profile of User A");
    homePagePlatform.refreshUntil($(byText(username1 + " " + username1)), Condition.visible, 1000);
    intranetNotification.goToDetailAcceptRequestConnectionUser(fullName1, false);
    $(byXpath(ELEMENT_PROFILE_TITLE.replace("${fullName}", fullName1))).waitUntil(Condition.visible, Configuration.timeout)
                                                                       .click();
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125171.</li>
   * <li>Test Case Name: Refuse a Connection Request from View All.</li>
   * <li>Pre-Condition: User A sent a connection request to User B</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All page Step Description: -
   * Login with User B - Click notifications icon - Click View All Input Data:
   * Expected Outcome: - View All page is displayed - A connection request
   * notification is displayed in the View All page
   */
  /*
   * Step number: 2 Step Name: Step 2 : Refuse Connection Request Step
   * Description: - Click [Refuse] Input Data: Expected Outcome: - The connection
   * is not approved, the 2 users are not connected - The notification message is
   * automatically hidden from the list
   */
  @Test
  public void test05_RefuseAConnectionRequestFromViewAll() throws Exception {
    info("Test 5: Refuse a Connection Request from View All");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    ArrayList<String> arrayUser = new ArrayList<String>();
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username1, password, email1, username1, username1);
    userAddManagement.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("User A sent a connection request to User B");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("User B login");
    manageLogInOut.signIn(username2, password);
    info("The user is redirected to the View All page");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    info("A connection request notification is displayed in the page");
    String connectStatus = "wants to connect with you";
    intranetNotification.checkStatus(connectStatus, username1);

    info("Click [Refuse]");
    String acceptStatus = "You are connected with";
    homePagePlatform.refreshUntil($(byText(username1 + " " + username1)), Condition.visible, 1000);
    intranetNotification.refuseRqConnection(username1);
    intranetNotification.checkNotPresentStatus(connectStatus, username1);
    intranetNotification.checkNotPresentStatus(acceptStatus, username1);

    info("The connection isnot approved, the 2 users are not connected");
    homePagePlatform.goToConnections();
    connectionsManagement.verifyConnection(username1, true);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }
}
