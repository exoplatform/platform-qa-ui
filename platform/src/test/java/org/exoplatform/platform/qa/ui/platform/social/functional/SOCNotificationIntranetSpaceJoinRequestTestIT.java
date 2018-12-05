package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
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
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationsAdminSeting;

@Tag("functional")
@Tag("social")

public class SOCNotificationIntranetSpaceJoinRequestTestIT extends Base {
  NavigationToolbar        navigationToolbar;

  NotificationsAdminSeting notificationsAdminSeting;

  ManageLogInOut           manageLogInOut;

  AddUsers                 addUsers;

  HomePagePlatform         homePagePlatform;

  SpaceManagement          spaceManagement;

  IntranetNotification     intranetNotification;

  MyNotificationsSetting   myNotificationsSetting;

  UserAddManagement        userAddManagement;

  ActivityStream           activityStream;

  ArrayList<String>        arrayUser;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    notificationsAdminSeting = new NotificationsAdminSeting(this);
    userAddManagement = new UserAddManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    activityStream = new ActivityStream(this);
    intranetNotification = new IntranetNotification(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");

  }

  /**
   * <li>Case ID:125150.</li>
   * <li>Test Case Name: Check Space Join Request notification.</li>
   * <li>Pre-Condition: - User A requested to join Space 1 - User B is manager of
   * Space 1 - Space Join Request notification is activated in User's B
   * settings</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : check
   * notification list Step Description: - Login with User B - Click the
   * notification icon - Check the notification list Input Data: Expected Outcome:
   * - The Space Join Request notification is displayed in the list Step number: 2
   * Step Name: Step : Check content of notification Step Description: - Check the
   * notification message Input Data: Expected Outcome: The notification message
   * is : - $AVATAR - $USER has requested access to $SPACE space. - [Accept] |
   * [Refuse] - $DATEWhere : - $AVATAR is the thumbnail of User A - $USER is User
   * A - $DATE is the date of the notification Step number: 3 Step Name: Step 3 :
   * click notification area Step Description: - Click the notification area Input
   * Data: Expected Outcome: - The user is redirect to the Space 1
   */
  @Test
  public void test01_CheckSpaceJoinRequestNotification() {
    info("Test 1: Check Space Join Request notification");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    userAddManagement.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Join_Req_intranet);

    info("User A create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "spaceDes" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User B login");
    manageLogInOut.signIn(username2, password);
    info("User B send a join request to UserA's space");
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(spaceName, "");
    spaceManagement.sendARequestToASpace(spaceName, true);
    info("User A login");
    manageLogInOut.signIn(username1, password);
    info("Check intranet notification format");
    String status = "has requested access to ";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatusSpace(status, spaceName);
    intranetNotification.checkBtnConnectJoinRequest(spaceName);
    info("The user is redirected to the Space");
    intranetNotification.goToDetailRequestJoinSpace(username2, true);
    homePagePlatform.refreshUntil($(ELEMENT_SPACE_MENU_ACTIVITY_PORTLET),Condition.visible,1000);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:125151.</li>
   * <li>Test Case Name: Accept a Space Join Request from notification.</li>
   * <li>Pre-Condition: - User A requested to join Space 1 - User B is manager of
   * Space 1 - Space Join Request notification is activated in User's B
   * settings</li>
   * <li>Post-Condition: </li Step Number: 1 Step Name: Step 1 : Check
   * notification list Step Description: - Login with User B - Click the
   * notification icon - Check the notification list Input Data: Expected Outcome:
   * - The Space Join Request notification is displayed in the list Step number: 2
   * Step Name: Step 2 : Accept space request Step Description: - Click the button
   * [Accept] Input Data: Expected Outcome: - User A is accepted and member of the
   * space Step number: 3 Step Name: Step 3 : Check notification message Step
   * Description: - Check the notification message Input Data: Expected Outcome:
   * The notification message is updated to :$AVATAR$USER joined $SPACE
   * space$DATEWhere: - $AVATAR is the thumbnail of User A - $USER is User A -
   * $SPACE is Space 1 - $DATE is the date of the notification Step number: 4 Step
   * Name: Step 4 : click notification area Step Description: - Click the
   * notification area Input Data: Expected Outcome: - The user is redirector to
   * the Space 1
   */
  @Test
  public void test02_AcceptASpaceJoinRequestFromNotification() throws Exception {
    info("Test 2: Accept a Space Join Request from notification");
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
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Join_Req_intranet);

    info("User A create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "spaceDes" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User B send a join request to UserA's space");
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(spaceName, "");
    spaceManagement.sendARequestToASpace(spaceName, true);

    info("User A login");
    manageLogInOut.signIn(username1, password);
    info("Check intranet notification format");
    String status = "has requested access to ";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.checkStatusSpace(status, spaceName);
    info("User A is accepted and member of the space");
    intranetNotification.acceptRqConnection(username2 + " " + username2);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToSpecificSpace(spaceName);
    homePagePlatform.refreshUntil($(ELEMENT_SPACE_MENU_ACTIVITY_PORTLET), Condition.visible, 1000);
    info("Check intranet notification format");
    String statusJoin = "joined ";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatusSpace(statusJoin, spaceName);
    info("The user is redirected to the Space");
    intranetNotification.goToDetailJoinSpace(username2, true);
    $(ELEMENT_SPACE_MENU_ACTIVITY_PORTLET).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username1);

  }

  /**
   * <li>Case ID:125152.</li>
   * <li>Test Case Name: Refuse a Space Join Request from notification.</li>
   * <li>Pre-Condition: - User A requested to join Space 1 - User B is manager of
   * Space 1 - Space Join Request notification is activated in User's B
   * settings</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : Check
   * notification list Step Description: - Login with User B - Click the
   * notification icon - Check the notification list Input Data: Expected Outcome:
   * - The Space Join Request notification is displayed in the list Step number: 2
   * Step Name: Step 2 : Refuse space request Step Description: - Click the button
   * [Refuse] Input Data: Expected Outcome: - User A is not member of the space
   * Step number: 3 Step Name: Step 3 : check notification list Step Description:
   * - Check the notifications list Input Data: Expected Outcome: - The
   * notification message is automatically hidden from the list
   */
  @Test
  public void test03_RefuseASpaceJoinRequestFromNotification() {
    info("Test 3: Refuse a Space Join Request from notification");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username1, password, email1, username1, username1);
    userAddManagement.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Join_Req_intranet);

    info("User A create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "spaceDes" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User B send a join request to UserA's space");
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(spaceName, "");
    spaceManagement.sendARequestToASpace(spaceName, true);

    info("User A login");
    manageLogInOut.signIn(username1, password);

    info("Check intranet notification format");
    String status = "has requested access to ";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.checkStatusSpace(status, spaceName);
    info("User A is accepted and member of the space");
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(spaceName, "");
    intranetNotification.refuseRqConnection(username2);
    intranetNotification.checkNotStatusSpace(status, spaceName);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username1);

  }

  /**
   * <li>Case ID:125153.</li>
   * <li>Test Case Name: Check View All after refusing a Space Join Request.</li>
   * <li>Pre-Condition: - User A requested to join Space 1 - User B is manager of
   * Space 1 - Space Join Request notification is activated in User's B
   * settings</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : Check
   * notification list Step Description: - Login with User B - Click the
   * notification icon - Check the notification list Input Data: Expected Outcome:
   * - The Space Join Request notification is displayed in the list Step number: 2
   * Step Name: Step 2 : Refuse space request and check view all Step Description:
   * - Click the button [Refuse] - Go to View All page Input Data: Expected
   * Outcome: - The notification is not displayed / available in the page
   */
  @Test
  public void test04_CheckViewAllAfterRefusingASpaceJoinRequest() throws Exception {
    info("Test 4: Check View All after refusing a Space Join Request");
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
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Join_Req_intranet);

    info("User A create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "spaceDes" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User B send a join request to UserA's space");
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(spaceName, "");
    spaceManagement.sendARequestToASpace(spaceName, true);

    info("User A login");
    manageLogInOut.signIn(username1, password);

    info("Check intranet notification format");
    String status = "has requested access to ";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.checkStatusSpace(status, spaceName);
    info("User A is accepted and member of the space");
    intranetNotification.refuseRqConnection(username2);
    intranetNotification.goToAllNotification();
    intranetNotification.checkNotStatusSpace(status, spaceName);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username1);

  }

  /**
   * <li>Case ID:125154.</li>
   * <li>Test Case Name: Check View All after accepting a Space Join Request.</li>
   * <li>Pre-Condition: - User A requested to join Space 1 - User B is manager of
   * Space 1 - Space Join Request notification is activated in User's B
   * settings</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : Check
   * notification list Step Description: - Login with User B - Click the
   * notification icon - Check the notification list Input Data: Expected Outcome:
   * - The Space Join Request notification is displayed in the list Step number: 2
   * Step Name: Step 2: Accept Space Request Step Description: - Click the button
   * [Accept] Input Data: Expected Outcome: - User A is accepted and member of the
   * space Step number: 3 Step Name: Step 3 : Check View All page Step
   * Description: - Go to View All page Input Data: Expected Outcome: - The
   * notification is displayed / available in the View All page - The message
   * displayed is the last one updated
   */
  @Test
  public void test05_CheckViewAllAfterAcceptingASpaceJoinRequest() throws Exception {
    info("Test 5: Check View All after accepting a Space Join Request");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username1, password, email1, username1, username1);
    userAddManagement.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.Space_Join_Req_intranet);

    info("User A create a new space");
    String spaceName = "spaceName" + getRandomNumber();
    String spaceDes = "spaceDes" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName, spaceDes);

    info("User B login");
    manageLogInOut.signIn(username2, password);

    info("User B send a join request to UserA's space");
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(spaceName, "");
    spaceManagement.sendARequestToASpace(spaceName, true);

    info("User A login");
    manageLogInOut.signIn(username1, password);

    info("Check intranet notification format");
    String status = "has requested access to ";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.checkStatusSpace(status, spaceName);
    info("User A is accepted and member of the space");
    intranetNotification.acceptRqConnection(username2 + " " + username2);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToSpecificSpace(spaceName);
    homePagePlatform.refreshUntil($(ELEMENT_SPACE_MENU_ACTIVITY_PORTLET), Condition.visible, 1000);
    info("Check intranet notification format");
    String statusJoin = "joined ";
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    intranetNotification.checkStatusSpace(statusJoin, spaceName);
    intranetNotification.checkNotStatusSpace(status, spaceName);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username1);
  }

}
