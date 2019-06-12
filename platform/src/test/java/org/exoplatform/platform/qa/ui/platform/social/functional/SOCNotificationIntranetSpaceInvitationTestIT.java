package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
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
public class SOCNotificationIntranetSpaceInvitationTestIT extends Base {
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
    connectionsManagement = new ConnectionsManagement(this);
    userProfilePage = new UserProfilePage(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
  }

  /**
   * <li>Case ID:125143.</li>
   * <li>Test Case Name: Check Space Invitation notification.</li>
   * <li>Pre-Condition: - User A is manager of the space 1 - User A invite User B
   * to join the space 1 - Space Invitation notification is activated in the
   * settings of User B</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Check notification list Step Description:
   * - Login with User B - Click Notifications icon - Check the notifications list
   * Input Data: Expected Outcome: - A Space Invitation notifications is displayed
   * in the list
   */
  /*
   * Step number: 2 Step Name: Step 2 : Check notification message Step
   * Description: - Check the notification message Input Data: Expected Outcome:
   * The notification message is : - $AVATAR - You're invited to join $SPACE space
   * - [Accept] | [Refuse] - $DATEWhere : - $AVATAR is the thumbnail of the space
   * - $SPACE is space 1 - $DATE is the date of the notification
   */

  /*
   * Step number: 3 Step Name: Step 3 : Click Notification Step Description: -
   * Click the notification area Input Data: Expected Outcome: - The user isnot
   * redirected to the home of Space 1
   */
  @Test
  public void test01_CheckSpaceInvitationNotification() {

    info("Test 1: Check Space Invitation notification");
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
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.Space_Invitation_Intranet);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User B create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User B invites UserA to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    refresh();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("User A login");
    manageLogInOut.signIn(username1, "123456");
    String status = "You're invited to join";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.checkAvatarInStatus(spaceName, true);
    intranetNotification.checkStatusSpace(status, spaceName);
    intranetNotification.checkBtnConnectJoinRequest(spaceName);
    intranetNotification.goToDetailInvitationSpace(spaceName, true);
    $(ELEMENT_SPACE_MENU_ACTIVITY_PORTLET).waitUntil(not(Condition.visible), Configuration.timeout);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  /**
   * <li>Case ID:125144.</li>
   * <li>Test Case Name: Accept a Space Invitation from notification.</li>
   * <li>Pre-Condition: - User A is manager of the space 1 - User A invite User B
   * to join the space 1 - Space Invitation notification is activated in the
   * settings of User B</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step number: 2 Step Name: Step 2 : Accept Space invitation Step Description:
   * - Click [Accept] Input Data: Expected Outcome: - The invitation is approved
   * and User B is member of Space 1
   */
  /*
   * Step number: 3 Step Name: Step 3 : Check Notification message Step
   * Description: - Click the notification message Input Data: Expected Outcome:
   * The notification message is updated to : $AVATARYou joined $SPACE
   * space$DATEWhere : - $AVATAR is the thumbnail of the space - $SPACE is space 1
   * - $DATE is the date of the notification
   */
  @Test
  public void test02_AcceptASpaceInvitationFromNotification() {

    info("Test 2: AcceptASpaceInvitationFromNotification");
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
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.Space_Invitation_Intranet);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User B create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User B invites UserA to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    refresh();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("User A login");
    manageLogInOut.signIn(username1, password);
    String status = "You're invited to join ";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.checkStatusSpace(status, spaceName);

    info("User A accepted invitation");
    intranetNotification.acceptRqConnection(spaceName);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToSpecificSpace(spaceName);
    $(ELEMENT_SPACE_MENU_ACTIVITY_PORTLET).waitUntil(Condition.visible, Configuration.timeout);
    navigationToolbar.goToIntranetNotification();
    String statusJoin = "You joined";
    intranetNotification.checkStatusSpace(statusJoin, spaceName);
    intranetNotification.checkAvatarInStatus(spaceName, true);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125145.</li>
   * <li>Test Case Name: Refuse a Space Invitation from notification.</li>
   * <li>Pre-Condition: - User A is manager of the space 1 - User A invite User B
   * to join the space 1 - Space Invitation notification is activated in the
   * settings of User B</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Check notification list Step Description:
   * - Login with User B - Click Notifications icon - Check the notifications list
   * Input Data: Expected Outcome: - A Space Invitation notifications is displayed
   * in the list
   */
  /*
   * Step number: 2 Step Name: Step 2 : Refuse space invitation Step Description:
   * - Click [Refuse] Input Data: Expected Outcome: - User B is not member of
   * Space 1
   */
  /*
   * Step number: 3 Step Name: Step 3 : Check notification message Step
   * Description: - Click the notification message Input Data: Expected Outcome: -
   * The notifications message is hidden from the list
   */
  @Test
  public void test03_RefuseASpaceInvitationFromNotification() {
    info("Test 3: Refuse a Space Invitation from notification");
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
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.Space_Invitation_Intranet);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User B create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User B invites UserA to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    refresh();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("User A login");
    manageLogInOut.signIn(username1, password);
    String status = "You're invited to join";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.checkStatusSpace(status, spaceName);

    info("User A refuse invitation");
    intranetNotification.refuseRqConnection(spaceName);
    intranetNotification.checkNotStatusSpace(status, spaceName);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125146.</li>
   * <li>Test Case Name: Check View All after accepting a Space Invitation.</li>
   * <li>Pre-Condition: - User A is manager of the space 1 - User A invite User B
   * to join the space 1 - Space Invitation notification is activated in the
   * settings of User B</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Check notification list Step Description:
   * - Login with User B - Click Notifications icon - Check the notifications list
   * Input Data: Expected Outcome: - A Space Invitation notifications is displayed
   * in the list
   */
  /*
   * Step number: 2 Step Name: Step 2 : Check View All page Step Description: -
   * Click [Accept] - Go to View All Input Data: Expected Outcome: - The
   * notifications is displayed / available in the View All page - The message
   * displayed in the last one updated after accepting the request
   */
  @Test
  public void test04_CheckViewAllAfterAcceptingASpaceInvitation() {

    info("Test 4: Check View All after accepting a Space Invitation");
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
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.Space_Invitation_Intranet);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User B create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User B invites UserA to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    spaceHomePage.goToSpaceSettingTab();
    refresh();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("User A login");
    manageLogInOut.signIn(username1, password);
    String status = "You're invited to join ";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    intranetNotification.checkStatusSpace(status, spaceName);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125147.</li>
   * <li>Test Case Name: Check View All after refusing a Space Invitation.</li>
   * <li>Pre-Condition: - User A is manager of the space 1 - User A invite User B
   * to join the space 1 - Space Invitation notification is activated in the
   * settings of User B</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Check notification list Step Description:
   * - Login with User B - Click Notifications icon - Check the notifications list
   * Input Data: Expected Outcome: - A Space Invitation notifications is displayed
   * in the list
   */
  /*
   * Step number: 2 Step Name: Step 2 : Check View all page Step Description: -
   * Click [Refuse] - Go to View All Input Data: Expected Outcome: - The
   * notifications is not displayed / available in the View All page
   */
  @Test
  public void test05_CheckViewAllAfterRefusingASpaceInvitation() throws Exception {
    info("Test 5: Check View All after refusing a Space Invitation");
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
    myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.Space_Invitation_Intranet);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User B create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "description" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User B invites UserA to the space");
    homePagePlatform.goToSpecificSpace(spaceName);
    refresh();
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username1, false, "");

    info("User A login");
    manageLogInOut.signIn(username1, password);
    String status = "You're invited to join";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    intranetNotification.checkStatusSpace(status, spaceName);
    homePagePlatform.refreshUntil($(byText(spaceName)), Condition.visible, 2000);

    info("User A refuse invitation");
    intranetNotification.refuseRqConnection(spaceName);
    intranetNotification.checkNotStatusSpace(status, spaceName);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }
  @Tag("SOC-6212")
  @Test
  public void test06_CheckRefuseInvitation() throws Exception {
    homePagePlatform.goToPeople();
    spaceSettingManagement.searchUsersPeople(DATA_USER3);
    spaceSettingManagement.connectSearchedUser();
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    spaceSettingManagement.declineNotificationConnectRequest(DATA_NAME_USER1);
    homePagePlatform.goToPeople();
    spaceSettingManagement.searchUsersPeople(DATA_USER1);
    spaceSettingManagement.checkUserNotConnected();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToPeople();
    spaceSettingManagement.searchUsersPeople(DATA_USER3);
    spaceSettingManagement.checkUserNotConnected();
  }
  @Tag("PLF-8210")
  @Test
  public void test07_Check() throws Exception {
    info("Test 5: Check View All after refusing a Space Invitation");
    homePagePlatform.goToDocuments();

  }
}
