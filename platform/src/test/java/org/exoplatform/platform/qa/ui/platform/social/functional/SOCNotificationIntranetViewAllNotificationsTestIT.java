package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TREE_WIKI_NAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;
import static org.xbill.DNS.Options.refresh;

import java.awt.*;
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
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;

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

  WikiManagement         wikiManagement;

  WikiHomePage           wikiHomePage;

  SourceTextEditor       sourceTextEditor;

  MyNotificationsSetting MyNotificationsSetting;

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
    wikiManagement = new WikiManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    manageLogInOut = new ManageLogInOut(this);
    wikiHomePage = new WikiHomePage(this);
    MyNotificationsSetting = new MyNotificationsSetting(this);
    userProfilePage = new UserProfilePage(this);

    ArrayList<String> arrayUser;
    ArrayList<String> comments;

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
    MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.NewUser_intranet);

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

  /**
   * <li>Case ID:125172.</li>
   * <li>Test Case Name: Accept a Space Invitation from View All.</li>
   * <li>Pre-Condition: - User A is manager of the space 1 - User A invite User B
   * to join the space 1</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All Step Description: - Login
   * with User B - Click Notifications icon - Click View All Input Data: Expected
   * Outcome: - View All page is displayed - A Space Invitation notifications is
   * displayed in the page
   */
  /*
   * Step number: 2 Step Name: Step 2 : Accept Space Invitation Step Description:
   * - Click [Accept] Input Data: Expected Outcome: - The invitation is approved
   * and User B is member of Space 1
   */
  /*
   * Step number: 3 Step Name: Step 3: Check notification message Step
   * Description: - Click the notification message Input Data: Expected Outcome:
   * The notification message is updated to : $AVATARYou joined $SPACE
   * space$DATEWhere : - $AVATAR is the thumbnail of the space - $SPACE is space 1
   * - $DATE is the date of the notification
   */
  @Test
  public void test06_AcceptASpaceInvitationFromViewAll() {
    info("Test 6: Accept a Space Invitation from View All");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String fullName2 = username2 + " " + username2;
    String password = "123456";

    info("Add new user");
    ArrayList<String> arrayUser = new ArrayList<String>();
    arrayUser.add(username2);
    arrayUser.add(username1);
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username1, password, email1, username1, username1);
    userAddManagement.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("Create a new space");
    String spaceName = "spaceNamea" + getRandomNumber();
    String spaceDes = "descriptiona" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("Invite UserB to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    refresh();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User B login");
    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();

    info("A Space Invitation notifications is displayed in the page");
    String inviteSpaceStatus = "You're invited to join";
    intranetNotification.checkStatusSpace(inviteSpaceStatus, spaceName);

    info("Click [Accept]");
    intranetNotification.acceptRqConnection(spaceName);

    info("User A login");
    manageLogInOut.signIn(username1, password);

    info("The invitation is approved and User B is member of Space 1");
    homePagePlatform.goToHomePage();
    homePagePlatform.goToSpecificSpace(spaceName);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("Check format of the notification after accepted");
    String acceptSpaceStatus = "";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    intranetNotification.checkStatus(acceptSpaceStatus, spaceName);
    intranetNotification.checkAvatarInStatus(arrayUser, false);
    intranetNotification.checkUsers(arrayUser, false);
    intranetNotification.checkStatusSpace(acceptSpaceStatus, spaceName);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceName, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125173.</li>
   * <li>Test Case Name: Refuse a Space Invitation from View All.</li>
   * <li>Pre-Condition: - User A is manager of the space 1 - User A invite User B
   * to join the space 1</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step Description: - Login with User B - Click
   * Notifications icon - Check the notifications list Input Data: Expected
   * Outcome: - A Space Invitation notifications is displayed in the list
   */
  /*
   * Step number: 2 Step Name: Step Description: - Click [Refuse] Input Data:
   * Expected Outcome: - User B is not member of Space 1
   */
  /*
   * Step number: 3 Step Name: Step Description: - Click the notification message
   * Input Data: Expected Outcome: - The notifications message is hidden from the
   * list
   */
  @Test
  public void test07_RefuseASpaceInvitationFromViewAll() throws Exception {

    info("Test 7: Refuse a Space Invitation from View All");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String fullName = username1 + " " + username1;
    String password = "123456";

    info("Add new user");
    ArrayList<String> arrayUser = new ArrayList<String>();
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("Create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("Invite UserB to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    refresh();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User B login");
    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();

    info("A Space Invitation notifications is displayed in the page");
    String inviteSpaceStatus = "You're invited to join";
    intranetNotification.checkStatusSpace(inviteSpaceStatus, spaceName);

    info("Click [Refuse]");
    intranetNotification.refuseRqConnection(spaceName);

    info("Check format of the notification after accepted");
    String acceptSpaceStatus = "";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    intranetNotification.checkNotStatusSpace(acceptSpaceStatus, spaceName);
    intranetNotification.checkNotStatusSpace(inviteSpaceStatus, spaceName);

    info("User A login");
    manageLogInOut.signIn(username1, password);

    info("User B isnot member of Space 1");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    spaceManagement.verifyMember(username1, false);
  }

  /**
   * <li>Case ID:125174.</li>
   * <li>Test Case Name: Accept a Space Join Request from View All.</li>
   * <li>Pre-Condition: - User A requested to join Space 1 - User B is manager of
   * Space 1</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View all Step Description: - Login
   * with User B - Click the notification icon - Click View All Input Data:
   * Expected Outcome: - The View All page is displayed - The Space Join Request
   * notification is displayed in the oage
   */
  /*
   * Step number: 2 Step Name: Step 2 : Accept Space Join Request Step
   * Description: - Click the button [Accept] Input Data: Expected Outcome: - User
   * A is accepted and member of the space
   */
  /*
   * Step number: 3 Step Name: Step 3 : Check notification message Step
   * Description: - Check the notification message Input Data: Expected Outcome:
   * The notification message is updated to :$AVATAR$USER joined $SPACE
   * space$DATEWhere: - $AVATAR is the thumbnail of User A - $USER is User A -
   * $SPACE is Space 1 - $DATE is the date of the notification
   */
  /*
   * Step number: 4 Step Name: Step 4 : Click the notification Step Description: -
   * Click the notification area Input Data: Expected Outcome: - The user is
   * redirector to the Space 1
   */
  @Test
  public void test08_AcceptASpaceJoinRequestFromViewAll() throws Exception {

    info("Test 8: Accept a Space Join Request from View All");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    String fullName2 = username2 + " " + username2;

    info("Add new user");
    ArrayList<String> arrayUser = new ArrayList<String>();
    arrayUser.add(username2);
    arrayUser.add(username1);
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("Create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("Invite UserB to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    refresh();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User B login");
    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToIntranetNotification();
    homePagePlatform.refreshUntil($(ELEMENT_ACCOUNT_NAME_LINK), visible, 1000);
    intranetNotification.goToAllNotification();

    info("A Space Invitation notifications is displayed in the page");
    String inviteSpaceStatus = "You're invited to join";
    intranetNotification.checkStatusSpace(inviteSpaceStatus, spaceName);
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_CONNECT_ACCEPT_BUTTON.replace("$name", spaceName))), Condition.visible, 1000);

    info("Click [Accept]");
    intranetNotification.acceptRqConnection(spaceName);

    info("The invitation is approved and User B is member of Space 1");
    homePagePlatform.goToHomePage();
    homePagePlatform.goToSpecificSpace(spaceName);
    $(ELEMENT_SPACE_MENU_ACTIVITY_STREAM).waitUntil(Condition.visible, Configuration.timeout);

    info("Check format of the notification after accepted");
    String acceptSpaceStatus = "You joined";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    intranetNotification.checkStatus(acceptSpaceStatus, spaceName);
    intranetNotification.checkAvatarInStatus(arrayUser, false);
    intranetNotification.checkUsers(arrayUser, false);
    intranetNotification.checkStatusSpace(acceptSpaceStatus, spaceName);

    info("Click the notification area");
    intranetNotification.goToDetailAcceptInvitationSpace(spaceName, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125175.</li>
   * <li>Test Case Name: Refuse a Space Join Request from View All.</li>
   * <li>Pre-Condition: - User A requested to join Space 1 - User B is manager of
   * Space 1</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All page Step Description: -
   * Login with User B - Click the notification icon - Click View All Input Data:
   * Expected Outcome: - The View All page is displayed - The Space Join Request
   * notification is displayed in the page
   */
  /*
   * Step number: 2 Step Name: Step 2 : Refuse the Space Join Request Step
   * Description: - Click the button [Refuse] Input Data: Expected Outcome: - User
   * A is not member of the space
   */
  /*
   * Step number: 3 Step Name: Step 3 : Check notification message Step
   * Description: - Check the notifications list Input Data: Expected Outcome: -
   * The notification message is automatically hidden from the list
   */
  @Test
  public void test09_RefuseASpaceJoinRequestFromViewAll() throws Exception {

    info("Test 9: Refuse a Space Join Request from View All");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String fullName2 = username2 + " " + username2;
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("Create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("Invite UserB to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    refresh();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User B login");
    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToIntranetNotification();
    homePagePlatform.refreshUntil($(ELEMENT_ACCOUNT_NAME_LINK), visible, 1000);
    intranetNotification.goToAllNotification();

    info("A Space Invitation notifications is displayed in the page");
    String inviteSpaceStatus = "You're invited to join";
    intranetNotification.checkStatusSpace(inviteSpaceStatus, spaceName);
    homePagePlatform.refreshUntil($(byXpath(ELEMENT_CONNECT_REFUSE_BUTTON.replace("$name", spaceName))), Condition.visible, 1000);

    info("Click [Refuse]");
    intranetNotification.refuseRqConnection(spaceName);

    info("Check format of the notification after accepted");
    String acceptSpaceStatus = "";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    intranetNotification.checkNotStatusSpace(acceptSpaceStatus, spaceName);
    intranetNotification.checkNotStatusSpace(inviteSpaceStatus, spaceName);

    info("User A login");
    manageLogInOut.signIn(username1, password);

    info("User B isnot member of Space 1");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    spaceManagement.verifyMember(username1, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125176.</li>
   * <li>Test Case Name: Click Mention notifications in View All (in
   * comment).</li>
   * <li>Pre-Condition: - An wiki activity is generated (create a new page) - User
   * A has mentioned User B directly in a comment</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test10_ClickMentionNotificationsInViewAllInComment() {
    info("Test 10 Click Mention notifications in View All (in comment)");
    /*
     * Step Number: 1 Step Name: Step 1 : Go to View All Page Step Description: -
     * Login with User B - Click the notification icons in the top navigation -
     * Click View All Input Data: Expected Outcome: - The Mention notification is
     * displayed in the list
     */
    /*
     * Step number: 2 Step Name: Step 2 : Click Mention notification message Step
     * Description: - Click the notification area Input Data: Expected Outcome: -
     * The user is redirected to the activity in the activity viewer with all
     * comments expanded - The comment is highlighted
     */

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String fullName2 = username2 + " " + username2;
    String comment = "comment" + getRandomNumber();
    String password = "123456";

    info("Add new user");
    ArrayList<String> arrayUser = new ArrayList<String>();
    ArrayList<String> comments = new ArrayList<String>();
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.addUserAdmin(username1);
    manageLogInOut.signIn(username1, password);

    info("John add an activity with wiki page");
    String wiki = "wikia" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    $(byXpath(ELEMENT_TREE_WIKI_NAME.replace("${name}", wiki))).waitUntil(Condition.visible, Configuration.timeout);

    info("John mention User A");
    homePagePlatform.goToHomePage();
    String actMention = "actMention" + getRandomNumber();
    activityStream.addCommentWikiWithMentionUser(wiki, username2, actMention);

    info("Add comment to Comment list");
    comments.add(actMention);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    String status = "has commented on you activity";
    intranetNotification.checkStatus(status, fullName2);
    manageLogInOut.signIn(username2, password);

    info("Check detail of Activity Comment");
    intranetNotification.goToDetailMentionNotification(username1, false);
    notificationActivity.checkCommentExpand(actMention, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125177.</li>
   * <li>Test Case Name: Click the Mention notifications from View All (in
   * activity message).</li>
   * <li>Pre-Condition: - User A has mentioned User B directly in the activity
   * message</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All Step Description: - Login
   * with User B - Click the notification icons in the top navigation - Click View
   * All Input Data: Expected Outcome: - The View All page is displayed - The
   * Mention notification is displayed in the page
   */
  /*
   * Step number: 2 Step Name: Step 2 : Click Mention notification Step
   * Description: - Click the notification Input Data: Expected Outcome: - The
   * user is redirected to the activity viewer with all comment expanded.
   */
  @Test
  public void test11_ClickTheMentionNotificationsFromViewAllInActivityMessage() {
    info("Test 11 Click the Mention notifications from View All (in activity message)");

    info("Create 2 users for testing");
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

    info("UserA mention User B");
    homePagePlatform.goToHomePage();
    String actMention = "actMention" + getRandomNumber();
    activityStream.mentionUserActivity(username2, actMention);

    info("User B login");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    String status = "";
    intranetNotification.checkStatus(status, username1);

    info("Check detail of Activity comment");
    intranetNotification.goToDetailMentionNotification(username1, false);
    notificationActivity.checkTitleActivityExpand(actMention);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125178.</li>
   * <li>Test Case Name: Click Comment Notification from View All (1
   * comment).</li>
   * <li>Pre-Condition: - User A and User B are connected - User A has posted an
   * activity - User B has commented on User A activity</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All Step Description: - Login
   * with User A - Click the notifications icon in the top navigation - Go to View
   * All Input Data: Expected Outcome: - The View All Page is displayed - A
   * comment notification is displayed in the page
   */
  /*
   * Step number: 2 Step Name: Step 2 : Click the Comment notification Step
   * Description: - Click the notification area Input Data: Expected Outcome: -
   * The activity is displayed in the activity viewer with all comment expanded. -
   * The comment that this notification is about is highlighted.
   */
  @Test
  public void test12_ClickCommentNotificationFromViewAll1Comment() throws Exception {

    info("Test 12 Click Comment Notification from View All (1 comment)");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    ArrayList<String> arrayUser = new ArrayList<String>();
    ArrayList<String> comments = new ArrayList<String>();
    arrayUser.add(username2);
    arrayUser.add(username1);
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Comment_intranet);

    info("User A sent a connection request to User B");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("User A add an activity");
    String activity = "activitya" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, null);
    activityStream.checkActivity(activity);

    info("User B login");
    manageLogInOut.signIn(username2, password);
    info("User A and User B are connected");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

    info("UserB comments in UserA's activity");
    String comment = "commenta" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment);

    info("Log in with User A");
    manageLogInOut.signIn(username1, password);

    info(" Go to View All Notification");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    String status = "has commented on your activity";
    intranetNotification.checkFormatStatusCommentNotification(arrayUser, status, activity, false);
    homePagePlatform.refreshUntil($(byText(activity)), visible, 1000);

    info("Check detail of activity comment");
    intranetNotification.goToDetailCommentNotification(activity, false);
    notificationActivity.checkCommentExpand(comment, true);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125179.</li>
   * <li>Test Case Name: Click Comment Notification from View All (>= 2
   * comments).</li>
   * <li>Pre-Condition: - User A and User B are connected - User A and User C are
   * connected - User A has posted an activity - User B has commented on User A
   * activity - User C has commented on User A activity</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All Step Description: - Login
   * with User A - Click the notifications icon in the top navigation - Click View
   * All Input Data: Expected Outcome: - The View All page is displayed - A
   * comment notification is displayed in the page
   */
  /*
   * Step number: 2 Step Name: Step 2: click Notification message Step
   * Description: - Click the notification area Input Data: Expected Outcome: -
   * The activity is displayed in the activity viewer with all comment expanded.
   */
  @Test
  public void test13_ClickCommentNotificationFromViewAllMore2Comments() throws Exception {
    info("Test 13 Click Comment Notification from View All (>= 2 comments)");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@gmail.com";

    info("Add new user");
    ArrayList<String> arrayUser = new ArrayList<String>();
    ArrayList<String> comments = new ArrayList<String>();
    arrayUser.add(username3);
    arrayUser.add(username2);
    arrayUser.add(username1);
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Comment_intranet);

    info("User A sent a connection request to UserB");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    info("User A sent a connection request to UserC");
    connectionsManagement.connectToAUser(username3);

    info("User A add an activity");
    String activity = "activitya" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, null);
    activityStream.checkActivity(activity);

    info("UserA and User B are connected");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

    info("UserB comments in UserA's activity");
    String comment = "commenta" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment);

    info("User A and User C are connected");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

    info("User C comment on UserA's activity");
    String comment1 = "commenta" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment1);

    info("Add all comments to the list");
    comments.add(comment);
    comments.add(comment1);

    info("Log in with User A");
    manageLogInOut.signIn(username1, password);

    info("Check format notification in the notification list");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    String status = "has commented on your activity";
    intranetNotification.checkFormatStatusCommentNotification(arrayUser, status, activity, false);
    homePagePlatform.refreshUntil($(byText(activity)), visible, 1000);

    info("Check detail of activity comment");
    intranetNotification.goToDetailCommentNotification(activity, false);
    notificationActivity.checkCommentsExpand(comments, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }

  /**
   * <li>Case ID:125180.</li>
   * <li>Test Case Name: Click Like Notification from View All.</li>
   * <li>Pre-Condition: - User A and User B are connected - User A has posted an
   * activity - User B has liked User A activity</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to All page Step Description: - Login
   * with User A - Click the notifications icon in the top navigation - Click View
   * All Input Data: Expected Outcome: - The View All page is displayed - A Like
   * notification is displayed in the page
   */
  /*
   * Step number: 2 Step Name: Step 3 : Read the notification Step Description: -
   * Click the notification area Input Data: Expected Outcome: - The activity is
   * displayed in the activity viewer with all comment expanded.
   */
  @Test
  public void test14_ClickLikeNotificationFromViewAll() {
    info("Test 14 Click Like Notification from View All");

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
    MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Like_intranet);

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
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

    info("UserB likes UserA's activity");
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);

    info("Log in with User A");
    manageLogInOut.signIn(username1, password);

    info(" Go to View All Notification");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    String status = "";
    intranetNotification.checkStatus(status, username2);

    info("Check detail of activity comment");
    intranetNotification.goToDetailLikeNotification(username2, false);
    notificationActivity.checkTitleActivityExpand(activity);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125181.</li>
   * <li>Test Case Name: Click Post on my Stream notification from View All.</li>
   * <li>Pre-Condition: User A has posted an activity on User B activity
   * stream</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All page Step Description: -
   * Login with User B - Click the notifications icon in the top navigation -
   * Click View All Input Data: Expected Outcome: - View All page is displayed - A
   * Post on my Stream notifications is displayed in the list
   */

  /*
   * Step number: 2 Step Name: Step 2 : Click the notification Step Description: -
   * Click the notification area Input Data: Expected Outcome: The activity is
   * displayedin the activity viewer with all comments expanded
   */
  @Test
  public void test15_ClickPostOnMyStreamNotificationFromViewAll() throws Exception {
    info("Test 15 Click Post on my Stream notification from View All");

    ArrayList<String> arrayUser = new ArrayList<String>();
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
    MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Post_intranet);

    info("User A sent a connection request to User B");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User A and User B are connected");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

    info("User B add an activity on User A's stream");
    String activity = "activitya" + getRandomNumber();
    connectionsManagement.goToUserByFullName(username1 + " " + username1);
    userProfilePage.goToActivity();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("Log in with User A");
    manageLogInOut.signIn(username1, password);

    info(" Go to View All Notification");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    String status = "";
    intranetNotification.checkStatus(status, username2);

    info("Check detail of activity comment");
    intranetNotification.goToDetailPostInMyActivity(username2, false);
    notificationActivity.checkTitleActivityExpand(activity);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125182.</li>
   * <li>Test Case Name: Click Post on my Space notification from View All.</li>
   * <li>Pre-Condition: - User A and User B are members of Space 1 - User B has
   * posted in Space 1</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All Step Description: - Login
   * with User B - Click the notifications icon in the top navigation - Click View
   * All Input Data: Expected Outcome: - View All page is displayed - A Post on my
   * Space notifications is displayed in the page
   */

  /*
   * Step number: 2 Step Name: Step 2 : Click the notification Step Description: -
   * Click the notification area Input Data: Expected Outcome: The activity is
   * displayed in the activity viewer with all comments expanded
   */
  @Test
  public void test16_ClickPostOnMySpaceNotificationFromViewAll() {
    info("Test 16 Click Post on my Space notification from View All");
    ArrayList<String> arrayUser = new ArrayList<String>();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    String fullName2 = username2 + " " + username2;

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.Space_Post_intranet);

    info("Create a space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User A sent a join request to User B from the space");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    refresh();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("UserB accepted the connection");
    homePagePlatform.goToAllSpace();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(spaceName);

    info("User B post an activity on the space's activity");
    String activity = "activity" + getRandomNumber();
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceManagement.goToActivityStreamTab();
    activityStream.addActivity(activity, null);

    info("User A login");
    manageLogInOut.signIn(username1, password);

    info(" Go to View All Notification");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    String status = "";
    intranetNotification.checkStatus(status, username2);

    info("Check detail of activity comment");
    intranetNotification.goToDetailPostInSpace(spaceName, false);
    notificationActivity.checkTitleActivityExpand(activity);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125184.</li>
   * <li>Test Case Name: Check order of the notifications in View All.</li>
   * <li>Pre-Condition: A user has received several notifications (from the oldest
   * to the newest) 1. A like notification 2. A comment notification 3. A
   * connection request</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : check View All page Step Description: -
   * Login - Click the notification icon in the top navigation - Click View All
   * Input Data: Expected Outcome: - The View All page is displayed - The
   * notifications are ordered from the newest at the top to the latest at the
   * bottom (connection request, comment, like)
   */
  @Test
  public void test17_CheckOrderOfTheNotificationsInViewAll() {

    info("Test 17 Check order of the notifications in View All");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    ArrayList<String> arrayUser = new ArrayList<String>();
    ArrayList<String> comments = new ArrayList<String>();
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Comment_intranet);
    MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Like_intranet);

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
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

    info("UserB comments in UserA's activity");
    String comment = "commenta" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    activityStream.commentActivity(activity, comment);

    info("Add comment to Comment list");
    comments.add(comment);

    info("User C login");
    manageLogInOut.signIn(username3, password);

    info("User C sent a connection request to User A");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);
    String statusSendRq = "";
    String statusLikeAc = "";
    String statusCommAc = "";

    info("Log in with User A");
    manageLogInOut.signIn(username1, password);

    info("The notifications are ordered from the newest at the top to the latest at the bottom "
        + "(connection request, comment, like)");
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    intranetNotification.checkOrderNotifications(1, statusSendRq, username3);
    intranetNotification.checkOrderNotifications(2, statusCommAc, username2);
    intranetNotification.checkOrderNotifications(3, statusLikeAc, username2);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125185.</li>
   * <li>Test Case Name: Check number of notifications displayed in the page.</li>
   * <li>Pre-Condition: - The user must has between 20 and 30 notifications
   * existing</li>
   * <li>Post-Condition:</li>
   *
   * @throws AWTException
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to View All Step Description: - Login -
   * Click the notification icon a the top - Click View All Input Data: Expected
   * Outcome: - The View All page is displayed - Max 20 notifications are
   * displayed in the page
   */
  /*
   * Step number: 2 Step Name: Step 2 : Scroll down Step Description: - Scroll
   * down in the page Input Data: Expected Outcome: - We can scroll to see the
   * other notifications. When these notifications are loaded, we display a
   * loading icon.
   */
  @Test
  public void test18_CheckNumberOfNotificationsDisplayedInThePage() throws AWTException {

    info("Test 18 Check number of notifications displayed in the page");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@gmail.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@gmail.com";
    String username5 = "usernamed" + getRandomString();
    String email5 = username5 + "@gmail.com";
    String username6 = "usernamee" + getRandomString();
    String email6 = username6 + "@gmail.com";
    String username7 = "usernamef" + getRandomString();
    String email7 = username7 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
    addUsers.addUser(username6, password, email6, username6, username6);
    addUsers.addUser(username7, password, email7, username7, username7);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    info("Enable like notification");
    MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Like_intranet);

    info("User 1 add an activity");
    String activity1 = "activitya" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, null);
    activityStream.checkActivity(activity1);

    info("User 1 add an activity");
    String activity2 = "activityb" + getRandomNumber();
    homePagePlatform.goToHomePage();

    activityStream.addActivity(activity2, null);
    activityStream.checkActivity(activity2);

    info("User 1 add an activity");
    String activity3 = "activityc" + getRandomNumber();
    homePagePlatform.goToHomePage();

    activityStream.addActivity(activity3, null);
    activityStream.checkActivity(activity3);

    info("User 1 add an activity");
    String activity4 = "activityd" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity4, null);
    activityStream.checkActivity(activity4);

    info("User 1 sent a connection request to User 2");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("User 1 sent a connection request to User 3");
    connectionsManagement.connectToAUser(username3);

    info("User 1 sent a connection request to User 4");
    connectionsManagement.connectToAUser(username4);

    info("User 1 sent a connection request to User 5");
    connectionsManagement.connectToAUser(username5);

    info("User 1 create a space");
    String space = "spacea" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(space, space, 30000);

    info("User 2 login");
    manageLogInOut.signIn(username2, password);

    info("User 1 and User 2 are connected");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

    info("User2 comments in User1's activity");
    homePagePlatform.goToHomePage();
    String comment = "comment" + getRandomNumber();
    ArrayList<String> comments = new ArrayList<String>();
    activityStream.likeActivity(activity1); // notification 1
    activityStream.commentActivity(activity1, comment); // notification 2

    info("Add comment to Comment list");
    comments.add(comment);

    info("User2 mention User1 in activity");
    String actMention1 = "actMentiona" + getRandomNumber();
    activityStream.mentionUserActivity(username1, actMention1); // notification 3

    info("User 2 posts a message in User1's activity");
    String myActivity = "myactivity" + getRandomNumber();
    activityStream.addActivity(myActivity, null); // notification 4

    info("User 2 create a space");
    String space1 = "spacea" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(space1, space1, 30000);

    info("User 2 sent a join request to User 1 from the space");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    refresh();
    spaceSettingManagement.inviteUser(username1, false, ""); // notification 5

    info("User 2 sent a join request to User1's space");
    homePagePlatform.goToAllSpace();
    spaceManagement.requestToJoinSpace(space, true); // notification 6

    info("User 3 login");
    manageLogInOut.signIn(username3, password);

    info("User 3 accepted with User1's connection request");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

    info("User3 comments and likes in User1's activity");
    homePagePlatform.goToHomePage();
    String comment1 = "commenta" + getRandomNumber();
    activityStream.likeActivity(activity2); // notification 7
    activityStream.commentActivity(activity2, comment1); // notification 8

    info("Add comment to Comment list");
    comments.add(comment1);

    info("User3 mention User1 in activity");
    String actMention2 = "actMentionb" + getRandomNumber();
    activityStream.mentionUserActivity(username1, actMention2); // notification 9

    info("User 3 posts a message in User1's activity");
    String myActivity1 = "myactivitya" + getRandomNumber();
    activityStream.addActivity(myActivity1, null); // notification 10

    info("User 3 create a space");
    String space2 = "spaceb" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(space2, space2, 30000);

    info("User 3 sent a join request to User 1 from the space");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    refresh();
    spaceSettingManagement.inviteUser(username1, false, ""); // notification 11

    info("User 3 sent a join request to User1's space");
    homePagePlatform.goToAllSpace();
    spaceManagement.requestToJoinSpace(space, true); // notification 12

    info("User 4 login");
    manageLogInOut.signIn(username4, password);

    info("User 4 accepted with User1's connection request");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

    info("User4 comments and likes in User1's activity");
    homePagePlatform.goToHomePage();
    String comment2 = "commentb" + getRandomNumber();
    activityStream.likeActivity(activity3); // notification 13
    activityStream.commentActivity(activity3, comment2); // notification 14

    info("Add comment to Comment list");
    comments.add(comment2);

    info("User4 mention User1 in activity");
    String actMention3 = "actMentionc" + getRandomNumber();
    activityStream.mentionUserActivity(username1, actMention3); // notification 15

    info("User 4 posts a message in User1's activity");
    String myActivity2 = "myActivityb" + getRandomNumber();
    activityStream.addActivity(myActivity2, null); // notification 16

    info("User 4 create a space");
    String space3 = "space" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(space3, space3, 30000);

    info("User 4 sent a join request to User 1 from the space");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    refresh();
    spaceSettingManagement.inviteUser(username1, false, ""); // notification 17

    info("User 4 sent a join request to User1's space");
    homePagePlatform.goToAllSpace();
    spaceManagement.requestToJoinSpace(space, true); // notification 18

    info("User 5 login");
    manageLogInOut.signIn(username5, password);

    info("User 5 accepted with User1's connection request");

    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

    info("User5 comments and likes in User1's activity");
    homePagePlatform.goToHomePage();

    String comment3 = "commentc" + getRandomNumber();
    activityStream.likeActivity(activity4); // notification 19
    activityStream.commentActivity(activity4, comment3); // notification 20

    info("Add comment to Comment list");
    comments.add(comment3);

    info("User5 mention User1 in activity");
    String actMention4 = "actMentiond" + getRandomNumber();
    activityStream.mentionUserActivity(username1, actMention4); // notification 21

    info("User 5 posts a message in User1's activity");
    String myActivity3 = "myactivityc" + getRandomNumber();
    activityStream.addActivity(myActivity3, null); // notification 22

    info("User 6 login");
    manageLogInOut.signIn(username6, password);

    info("User 6 sent a connection request to User 1");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1); // notification 23

    info("User 7 login");
    manageLogInOut.signIn(username7, password);

    info("User 7 sent a connection request to User 1");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1); // notification 24

    info("User 1 login");
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();

    String statusSendRq = "wants to connect with you";
    String statusMention = "has mentionned you";
    String statusJoinSpace = "has joined";
    String statusInviteSpace = "has requested access to";
    String statusLikeAc = "likes your activity";
    String statusPostFriendAct = "has posted on your activity stream";
    String statusCommAc = "has commented on your activity";

    info("Check status send connection request from User 6 and User 7");
    intranetNotification.checkStatus(statusSendRq, username7); // Notification 24
    intranetNotification.checkStatus(statusSendRq, username6); // Notification 23

    info("Check status from User5");
    intranetNotification.checkStatus(statusPostFriendAct, username5); // Notification 22
    intranetNotification.checkStatus(statusMention, username5); // Notification 21
    intranetNotification.checkStatus(statusCommAc, username5); // Notification 20
    intranetNotification.checkStatus(statusLikeAc, username5); // Notification 19

    info("Check status from User4");
    intranetNotification.checkStatus(statusJoinSpace, username4); // Notification 18
    intranetNotification.checkStatus(statusInviteSpace, username4); // Notification 17
    intranetNotification.checkStatus(statusPostFriendAct, username4); // Notification 16
    intranetNotification.checkStatus(statusMention, username4); // Notification 15
    intranetNotification.checkStatus(statusCommAc, username4); // Notification 14
    intranetNotification.checkStatus(statusLikeAc, username4); // Notification 13

    info("Check status from User3");
    intranetNotification.checkStatus(statusJoinSpace, username3); // Notification 12
    intranetNotification.checkStatus(statusInviteSpace, username3); // Notification 11
    intranetNotification.checkStatus(statusPostFriendAct, username3); // Notification 10
    intranetNotification.checkStatus(statusMention, username3); // Notification 9
    intranetNotification.checkStatus(statusCommAc, username3); // Notification 8
    intranetNotification.checkStatus(statusLikeAc, username3); // Notification 7

    info("Check status from User2");
    intranetNotification.checkNotPresentStatus(statusJoinSpace, username2); // Notification 6
    intranetNotification.checkNotPresentStatus(statusInviteSpace, username2); // Notification 5
    intranetNotification.checkNotPresentStatus(statusPostFriendAct, username2); // Notification 4
    intranetNotification.checkNotPresentStatus(statusMention, username2); // Notification 3
    intranetNotification.checkNotPresentStatus(statusCommAc, username2); // Notification 2
    intranetNotification.checkNotPresentStatus(statusLikeAc, username2); // Notification 1
    intranetNotification.checkStatus(statusPostFriendAct, username2); // Notification 4
    intranetNotification.checkStatus(statusMention, username2); // Notification 3
    intranetNotification.checkStatus(statusCommAc, username2); // Notification 2
    intranetNotification.checkStatus(statusLikeAc, username2);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();// Notification 1
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);
    addUsers.deleteUser(username6);
    addUsers.deleteUser(username7);

  }}
