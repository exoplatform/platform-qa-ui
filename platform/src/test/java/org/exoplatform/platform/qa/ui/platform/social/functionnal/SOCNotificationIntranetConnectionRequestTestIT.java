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

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS;
import static org.exoplatform.platform.qa.ui.core.PLFData.USER_ROOT;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
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
}