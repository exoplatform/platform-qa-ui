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
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS;
import static org.exoplatform.platform.qa.ui.core.PLFData.USER_ROOT;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_BADGE_NUMBER_DISPLAY;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_INTRANET_NOTIFICATION_NEAR_USER_AVATAR;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("social")
@Tag("sniff")
public class SOCNotificationIntranetNotificationIconTestIT extends Base {
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
     * <li> Case ID:125112.</li>
     * <li> Test Case Name: Check Notifications icon in the top navigation.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     */
    /*Step Number: 1
		*Step Name:
		*Step Description:
			- Login to platform
		*Input Data:

		*Expected Outcome:
			- The homepage is displayed*/

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Check the top navigation
		*Input Data:

		*Expected Outcome:
			- The Notifications icon is displayed in the top navigation, next to the profile menu*/
    @Test
    public void test01_CheckNotificationsIconInTheTopNavigation() {
        info("Test 1: Check Notifications icon in the top navigation");
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String password = "123456";

        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username1, "123456", email1, username1, username1);
        manageLogInOut.signIn(username1, "123456");

        info("Check The Notifications icon is displayed in the top navigation");
        $(ELEMENT_INTRANET_NOTIFICATION_NEAR_USER_AVATAR).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * <li> Case ID:125113.</li>
     * <li> Test Case Name: New notifications number is displayed next to the icon.</li>
     * <li> Pre-Condition: The user has unread notifications (no matters the notification type), for instance 3 notifications</li>
     * <li> Post-Condition: </li>
     */
    /*Step Number: 1
		*Step Name:
		*Step Description:
			- Login to platform
			- Check the top navigation
		*Input Data:

		*Expected Outcome:
			- The number of new notifications (3 notifications) is displayed above the icon with a blue badge.*/
    @Test
    public void test02_NewNotificationsNumberIsDisplayedNextToTheIcon() {
        info("Test 2: New notifications number is displayed next to the icon");

        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String username3 = "usernamec" + getRandomString();
        String email3 = username3 + "@gmail.com";
        String username4 = "usernamed" + getRandomString();
        String email4 = username4 + "@gmail.com";
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
        UserAddManagement.addUser(username3, "123456", email3, username3, username3);
        UserAddManagement.addUser(username4, "123456", email4, username4, username4);

        info("Verify that The number of new notifications (3 notifications) is displayed above the icon");
        $(byXpath(ELEMENT_BADGE_NUMBER_DISPLAY.replace("${number}", "3"))).waitUntil(Condition.visible, Configuration.timeout);

    }

    /**
     * <li> Case ID:125114.</li>
     * <li> Test Case Name: Open the notifications list to reset the number of notifications.</li>
     * <li> Pre-Condition: The user has unread notifications (no matters the notification type), for instance 3 notifications</li>
     * <li> Post-Condition: </li>
     */
    /*Step Number: 1
		*Step Name:
		*Step Description:
			- Login to platform
			- Check the top navigation
		*Input Data:

		*Expected Outcome:
			- The number of new notifications (3 notifications) is displayed above the icon with a blue badge.*/
    /*Step number: 2
		*Step Name:
		*Step Description:
			- Click the notification icon
		*Input Data:

		*Expected Outcome:
			- The list opens
			- When opening the notification list, the number of new notifications displayed in the badge is reset.*/
    @Test
    public void test03_OpenTheNotificationsListToResetTheNumberOfNotifications() {
        info("Test 3: Open the notifications list to reset the number of notifications");

        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String username3 = "usernamec" + getRandomString();
        String email3 = username3 + "@gmail.com";
        String username4 = "usernamed" + getRandomString();
        String email4 = username4 + "@gmail.com";
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
        UserAddManagement.addUser(username3, "123456", email3, username3, username3);
        UserAddManagement.addUser(username4, "123456", email4, username4, username4);

        info("Verify that The number of new notifications (3 notifications) is displayed above the icon");
        $(byXpath(ELEMENT_BADGE_NUMBER_DISPLAY.replace("${number}", "3"))).waitUntil(Condition.visible, Configuration.timeout);
        navigationToolbar.goToIntranetNotification();

        info("Verify that The number of new notifications (3 notifications) isnot displayed above the icon");
        $(byXpath(ELEMENT_BADGE_NUMBER_DISPLAY.replace("${number}", "3"))).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    }
}
