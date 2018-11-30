package org.exoplatform.platform.qa.ui.platform.social.functional;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationActivity;

@Tag("functional")
@Tag("social")
public class SOCNotificationIntranetPostInSpaceTestIT extends Base {

  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  HomePagePlatform       homePagePlatform;

  UserAddManagement      userAddManagement;

  ActivityStream         activityStream;

  IntranetNotification   intranetNotification;

  MyNotificationsSetting myNotificationsSetting;

  NotificationActivity   notificationActivity;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    userAddManagement = new UserAddManagement(this);
    activityStream = new ActivityStream(this);
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    intranetNotification = new IntranetNotification(this);
    spaceHomePage = new SpaceHomePage(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    notificationActivity = new NotificationActivity(this);

  }

  /**
   * <li>Case ID:125134.</li>
   * <li>Test Case Name: Check Post on my Space notification.</li>
   * <li>Pre-Condition: - User A and User B are members of Space 1 - User B has
   * posted in Space 1 - The notification "An activity is posted on one of my
   * spaces" is activated in the user settings</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Check notification list Step Description:
   * - Login with User A - Click the notifications icon in the top navigation -
   * Check the notifications list Input Data: Expected Outcome: - A Post on my
   * Space notifications is displayed in the list
   */
  /*
   * Step number: 2 Step Name: Step 2 : Check notification message Step
   * Description: - Check the notification message Input Data: Expected Outcome:
   * The notification message is :$AVATAR$USER has posted an activity in the
   * $SPACE space.$ACTIVITY$DATEWhere : - $AVATAR is the thumbnail of User B -
   * $USER is User B - $SPACE is Space 1 - $ACTIVITY is the activity title /
   * message - $DATE is the date of the notification
   */
  @Test
  public void test01_CheckPostOnMySpaceNotification() {

    info("Test 1: Check Post on my Space notification");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.Space_Post_intranet);

    info("User A create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User A invites UserB to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User B login");
    manageLogInOut.signIn(username2, "123456");

    info("User B accepted to join the space");
    homePagePlatform.goToAllSpace();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(spaceName);

    info("User B added an new activity to the space");
    String activity = "activitya" + getRandomNumber();
    homePagePlatform.goToSpecificSpace(spaceName);
    activityStream.addActivity(activity, null);
    activityStream.checkActivity(activity);

    info("User A login");
    manageLogInOut.signIn(username1, password);
    String status = "has posted the activity";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatusSpace(status, spaceName);
    intranetNotification.checkActivityTitleInStatus(activity, true);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125135.</li>
   * <li>Test Case Name: Click Post on my Space notification.</li>
   * <li>Pre-Condition: - User A and User B are members of Space 1 - User B has
   * posted in Space 1 - The notification "An activity is posted on one of my
   * spaces" is activated in the user settings</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Check notification list Step Description:
   * - Login with User A - Click the notifications icon in the top navigation -
   * Check the notifications list Input Data: Expected Outcome: - A Post on my
   * Space notifications is displayed in the list
   */
  /*
   * Step number: 2 Step Name: Step 2 : View the notification Step Description: -
   * Click the notification area Input Data: Expected Outcome: The activity is
   * displayedin the activity viewer with all comments expanded
   */
  @Test
  public void test02_ClickPostOnMySpaceNotification() {

    info("Test 2: Click Post on my Space notification");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.Space_Post_intranet);

    info("User A create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User A invites UserB to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User B accepted to join the space");
    homePagePlatform.goToAllSpace();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(spaceName);

    info("User B added an new activity to the space");
    String activity = "activity" + getRandomNumber();
    homePagePlatform.goToSpecificSpace(spaceName);
    activityStream.addActivity(activity, null);
    activityStream.checkActivity(activity);

    info("User A login");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToDetailPostInSpace(spaceName, true);
    notificationActivity.checkTitleActivityExpand(activity);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }
  /**
   * <li>Case ID:125136.</li>
   * <li>Test Case Name: Check View All after receiving a Post on my Stream
   * notification.</li>
   * <li>Pre-Condition: - User A and User B are members of Space 1 - User B has
   * posted in Space 1 - The notification "An activity is posted on one of my
   * spaces" is activated in the user settings</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Check notification list Step Description:
   * - Login with User A - Click the notifications icon in the top navigation -
   * Check the notifications list Input Data: Expected Outcome: - A Post on my
   * Space notifications is displayed in the list
   */
  /*
   * Step number: 2 Step Name: Step 2 : Check in View all page Step Description: -
   * Go to View All Input Data: Expected Outcome: - Post of my Space notification
   * is displayed / available in the page
   */
  @Test
  public void test03_CheckViewAllAfterReceivingAPostOnMyStreamNotification() {
    info("Test 3: Check View All after receiving a Post on my Stream notification");

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.Space_Post_intranet);

    info("User A create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User A invites UserB to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User B accepted to join the space");
    homePagePlatform.goToAllSpace();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(spaceName);

    info("User B added an new activity to the space");
    String activity = "activity" + getRandomNumber();
    homePagePlatform.goToSpecificSpace(spaceName);
    activityStream.addActivity(activity, null);
    activityStream.checkActivity(activity);

    info("User A login");
    manageLogInOut.signIn(username1, password);
    String status = "has posted the activity ";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    intranetNotification.checkStatus(status, activity);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }
}
