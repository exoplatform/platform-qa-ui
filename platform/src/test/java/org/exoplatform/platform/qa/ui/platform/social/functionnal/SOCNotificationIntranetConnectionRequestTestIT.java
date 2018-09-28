package org.exoplatform.platform.qa.ui.platform.social.functionnal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS;
import static org.exoplatform.platform.qa.ui.core.PLFData.USER_ROOT;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_NOTIFICATION_DROPDOWN;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_PROFILE_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("sniff")
@Tag("social")

/**
 * Created by exo on 21/09/18.
 */
public class SOCNotificationIntranetConnectionRequestTestIT extends Base {

    NavigationToolbar navigationToolbar;
    ManageLogInOut manageLogInOut;
    AddUsers addUsers;
    ConnectionsManagement connectionsManagement;
    HomePagePlatform homePagePlatform;
    IntranetNotification intranetNotification;
    MyNotificationsSetting MyNotificationsSetting;
    UserAddManagement UserAddManagement;
    ArrayList<String> arrayUser;
    UserProfilePage userProfilePage;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut = new ManageLogInOut(this);
        addUsers = new AddUsers(this);
        connectionsManagement = new ConnectionsManagement(this);
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut.signInCas(USER_ROOT, DATA_PASS);
        MyNotificationsSetting = new MyNotificationsSetting(this);
        UserAddManagement = new UserAddManagement(this);
        userProfilePage = new UserProfilePage(this);
        intranetNotification = new IntranetNotification(this);

        info("Test 01: Check Connection Request notification");
    }

    @Test

    public void Test01_CheckConnectionRequestNotification() {

		/*  Check Connection Request notification
        - The notification "Someone sends me a connection request" is activated in User Settings
		- User A sent a connection request to User B
		Step 1
		- Login with User B
		- Click the notifications icon
		- Check the notification list
		*/
        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";
        arrayUser.add(username1 + " " + username1);
        info("Add new user");

        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username1, "123456", email1, username1, username1);
        UserAddManagement.addUser(username2, "123456", email2, username2, username2);
        manageLogInOut.signIn(username1, "123456");


        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.ConnectionRequest_intranet);

        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);
        /** Step 2
         - Check the notification message
         --> Expected result:
         The notification message is :
         - $AVATAR
         - $USER wants to connect with you
         - $DATE
         - 2 actions : [Accept] | [Refuse]

         Where :
         - $AVATAR is thumbnail of User A
         - $USER is User A
         - $DATE is the date of the notification
         */
        info("Log in with User B");
        manageLogInOut.signIn(username2, password);
        String status = "User A wants to connect with you ";
        navigationToolbar.goToIntranetNotification();

        intranetNotification.checkStatus(status, username1);
        intranetNotification.checkBtnConnectJoinRequest(username1 + " " + username1);
        intranetNotification.checkAvatarInStatus(username1 + " " + username1, true);
        intranetNotification.checkUsers(arrayUser, true);


        /**    Step 3:
         - Click the notification area
         --> Expected: User B is redirected to the profile of User A
         */
        intranetNotification.goToDetailRequestConnectionUser(username1 + " " + username1, true);
        info("Verify that User B is redirected to the profile of User A");
        $(byXpath(ELEMENT_PROFILE_TITLE.replace("${fullName}", username1 + " " + username1))).waitUntil(Condition.visible, Configuration.timeout);

    }

    @Test
    public void Test02_AcceptAConnectionRequestFromTheNotification() {
        info("Test 02: Accept a Connection Request from the notification");
        /** Accept a Connection Request from the notification
         - The notification "Someone sends me a connection request" is activated in User Settings
         - User A sent a connection request to User B
         - Login with User B
         - Click notifications icon
         - Check the list
         Step 2:
         Click on Accept
         --> Expected: - The connection is approved, the 2 users are connected
         */
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username1, "123456", email1, username1, username1);
        UserAddManagement.addUser(username2, "123456", email2, username2, username2);
        manageLogInOut.signIn(username1, "123456");
        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.ConnectionRequest_intranet);
        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);
        navigationToolbar.goToMyNotifications();
        MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.ConnectionRequest_intranet);

        /** Step 3:
         - Check the notification message
         --> Expected: After clicking Accept, the notification message is updated to :
         $AVATAR
         You are connected with $USER
         $DATE
         Where :
         - $AVATAR is thumbnail of User A
         - $USER is User A
         - $DATE is the date of the notification
         */
        info("Log in with User B");
        manageLogInOut.signIn(username2, password);
        String status = " ";
        navigationToolbar.goToIntranetNotification();
        intranetNotification.acceptRqConnection(username1);
        intranetNotification.checkStatus(status, username1);
        info("Verify that User A and User B are friend");
        homePagePlatform.goToConnections();
        connectionsManagement.searchPeople(username1, null, null, null);
        /** Step 4:
         - Click the notification area
         --> Expected:- User B is redirected to the profile of User A
         */
        navigationToolbar.goToIntranetNotification();
        intranetNotification.goToDetailAcceptRequestConnectionUser(username1, true);
        info("Verify that User B is redirected to the profile of User A");
        $(byXpath(ELEMENT_PROFILE_TITLE.replace("${fullName}", username1 + " " + username1))).waitUntil(Condition.visible, Configuration.timeout);
    }

    @Test
    public void Test03_RefuseAConnectionRequestFromTheNotification() {
        info("Test 03: Refuse a Connection Request from the notification");
        /** Refuse a Connection Request from the notification
         - The notification "Someone sends me a connection request" is activated in User Settings
         - User A sent a connection request to User B
         - Login with User B
         - Click notifications icon
         - Check the list
         - Click [Refuse]
         --> Expected: - The connection is not approved, the 2 users are not connected
         - The notification message is automatically hidden from the list
         - Go to View All page
         --> Expected: - The notification is not available / displayed in the View All page*/
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username1, "123456", email1, username1, username1);
        UserAddManagement.addUser(username2, "123456", email2, username2, username2);
        manageLogInOut.signIn(username1, "123456");
        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.ConnectionRequest_intranet);

        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);

        info("Log in with User B");
        manageLogInOut.signIn(username2, password);
        String status = "";
        navigationToolbar.goToIntranetNotification();
        intranetNotification.refuseRqConnection(username1);
        intranetNotification.checkStatus(status, username1);

        info("Verify that User A and User B are not friend");

        homePagePlatform.goToConnections();
        connectionsManagement.searchPeople(username1, null, null, null);
        connectionsManagement.verifyConnection(username1, false);
        homePagePlatform.goToHomePage();
        info("The notification is not available / displayed in the View All page");
        navigationToolbar.goToIntranetNotification();
        $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).shouldNot(Condition.visible);


    }

    @Test
    public void Test04_CheckViewAllAfterAcceptingAConnectionRequest() {
        info("Test 04: Check View All after accepting a Connection Request");
      /*Check View All after accepting a Connection Request
      Precondition:
      - The notification "Someone sends me a connection request" is activated in User Settings
      - User A sent a connection request to User B
      Step 1:
      - Login with User B
      - Click notifications icon
      - Check the list
      - Click [Accept]
      --> Expected: - The connection is approved, the 2 users are connected
                  - The notification message is updated accordingly
      */
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username1, "123456", email1, username1, username1);
        UserAddManagement.addUser(username2, "123456", email2, username2, username2);
        manageLogInOut.signIn(username1, "123456");

        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.ConnectionRequest_intranet);

        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);

        info("Log in with User B");
        manageLogInOut.signIn(username2, password);
        String status = " ";
        navigationToolbar.goToIntranetNotification();
        intranetNotification.acceptRqConnection(username1);
        intranetNotification.checkStatus(status, username1);
        /** Step 2:
         - Go to View All
         --> Expected: - The notifications is displayed in the message
         - The message is displayed at the same position as the connection request
         */
        info("The notification is not available / displayed in the View All page");
        navigationToolbar.goToIntranetNotification();
        $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).shouldNot(Condition.visible);
    }

    @Test
    public void Test05_CheckViewAllAfterrefusingAConnectionRequest(){
        info("Test 05: Check View All after refusing a Connection Request");
        /** Check View All after refusing a Connection Request
         *
         * - The notification "Someone sends me a connection request" is activated in User Settings
         - User A sent a connection request to User B
         * - Login with User B
         - Click notifications icon
         - Check the list
         - Click [Refuse]
         - Go to View All
         --> Expected: - The notifications message is not displayed in the page
         */
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username1, "123456", email1, username1, username1);
        UserAddManagement.addUser(username2, "123456", email2, username2, username2);
        manageLogInOut.signIn(username1, "123456");
        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.ConnectionRequest_intranet);
        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);
        info("Log in with User B");
        manageLogInOut.signIn(username2, password);
        String status="";
        navigationToolbar.goToIntranetNotification();
        intranetNotification.refuseRqConnection(username1);
        info("The notification is not available / displayed in the View All page");
        intranetNotification.checkStatus(status,username1);
    }}

