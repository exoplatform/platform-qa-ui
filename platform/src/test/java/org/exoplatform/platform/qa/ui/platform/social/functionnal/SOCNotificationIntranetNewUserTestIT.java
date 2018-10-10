package org.exoplatform.platform.qa.ui.platform.social.functionnal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
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

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS;
import static org.exoplatform.platform.qa.ui.core.PLFData.USER_ROOT;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_NOTIFICATION_DROPDOWN;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_USER_NAME_PAGE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("social")
@Tag("sniff")

public class SOCNotificationIntranetNewUserTestIT extends Base {
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
    UserAndGroupManagement UserAndGroupManagement;

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
        UserAndGroupManagement = new UserAndGroupManagement(this);
    }

    /**
     * <li> Case ID:125108.</li>
     * <li> Test Case Name: Check New User notification.</li>
     * <li> Pre-Condition: - The settings "Someone joins the social intranet" is activated in User Settings
     * - A new user has joined the Intranet</li>
     * <li> Post-Condition: </li>
     */
    /*Step Number: 1
		*Step Name: Step1 ; Check notification list
		*Step Description:
			- Login to the platform (not with the new user)
			- Click the notifications icon
			- Check the list
		*Input Data:

		*Expected Outcome:
			A New User notification is displayed in the list*/
    /*Step number: 2
		*Step Name: Step 2 : Check notification message
		*Step Description:
			- Check the notification message
		*Input Data:

		*Expected Outcome:
			The notification message is :$AVATAR$USER has joined $PORTAL_NAME.$DATEWhere :
			- $USER is the new user
			- $AVATAR is the thumbnail of the new user
			- $DATE is the date of the notification*/
    @Test
    public void test01_CheckNewUserNotification() {

        info("Test 1: Check New User notification");
        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";

        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username1, "123456", email1, username1, username1);
        navigationToolbar.goToUsersAndGroupsManagement();
        UserAndGroupManagement.addUserAdmin(username1);
        manageLogInOut.signIn(username1, "123456");

        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.NewUser_intranet);

        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username2, "123456", email2, username2, username2);

        info("Check intranet notification");
        String status = "user2 has joined exo";
        navigationToolbar.goToIntranetNotification();
        intranetNotification.checkAvatarInStatus(username2, true);
        intranetNotification.checkStatus(status, username2);
    }

    /**
     * <li> Case ID:125109.</li>
     * <li> Test Case Name: Click the New User notification.</li>
     * <li> Pre-Condition: - The settings "Someone joins the social intranet" is activated in User Settings
     * - A New User notification has been received</li>
     * <li> Post-Condition: </li>
     */
    @Test
    public void test02_ClickTheNewUserNotification() {
        info("Test 2: Click the New User notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description:
			- Login
			- Click the notifications icon
		*Input Data:

		*Expected Outcome:
			- A New User notification is displayed in the list*/
        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";

        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username1, "123456", email1, username1, username1);
        navigationToolbar.goToUsersAndGroupsManagement();
        UserAndGroupManagement.addUserAdmin(username1);
        manageLogInOut.signIn(username1, "123456");

        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.NewUser_intranet);


        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username2, "123456", email2, username2, username2);
        homePagePlatform.goToHomePage();
        info("Check intranet notification");
        String status = "userA has joined exo";
        navigationToolbar.goToIntranetNotification();
        $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2 + " " + username2))
                .parent()
                .shouldHave(text(username2 + " " + username2 + " has joined eXo."));
		/*Step number: 2
		*Step Name: Step 2 : Click the notification area
		*Step Description:
			- Click the notification area
		*Input Data:

		*Expected Outcome:
			- The user is redirected to the profile of the new user*/
        info("The user is redirected to the profile of the new user");
        intranetNotification.goToDetailNewUserJoinIntranet(username2 + " " + username2, true);
        $(byXpath(ELEMENT_USER_NAME_PAGE.replace("$fullName", username2 + " " + username2))).waitUntil(Condition.visible, Configuration.timeout);

    }

    /**
     * <li> Case ID:125110.</li>
     * <li> Test Case Name: Check View All after receiving a New User notification.</li>
     * <li> Pre-Condition: - The settings "Someone joins the social intranet" is activated in User Settings
     * - A New User notification has been received</li>
     * <li> Post-Condition: </li>
     */
    @Test
    public void test03_CheckViewAllAfterReceivingANewUserNotification() {
        info("Test 3: Check View All after receiving a New User notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description:
			- Login
			- Click the notifications icon
		*Input Data:

		*Expected Outcome:
			- A New User notification is displayed in the list*/
        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username1, "123456", email1, username1, username1);
        navigationToolbar.goToUsersAndGroupsManagement();
        UserAndGroupManagement.addUserAdmin(username1);
        manageLogInOut.signIn(username1, "123456");

        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.NewUser_intranet);

        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username2, "123456", email2, username2, username2);
        homePagePlatform.goToHomePage();
		/*Step number: 2
		*Step Name: Step 2 : Check View All page
		*Step Description:
			- Go to View All
		*Input Data:

		*Expected Outcome:
			- The New User Notification is displayed*/
        info("Check intranet notification");
        String status = "";
        navigationToolbar.goToIntranetNotification();
        intranetNotification.goToAllNotification();
        intranetNotification.checkStatus(status, username2);

    }
}