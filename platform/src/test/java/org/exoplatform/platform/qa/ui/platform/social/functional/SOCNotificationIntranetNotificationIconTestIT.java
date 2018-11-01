package org.exoplatform.platform.qa.ui.platform.social.functional;

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
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_BADGE_NUMBER_DISPLAY;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_INTRANET_NOTIFICATION_NEAR_USER_AVATAR;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("social")
@Tag(" functional")
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
        MyNotificationsSetting = new MyNotificationsSetting(this);
        UserAddManagement = new UserAddManagement(this);
        userProfilePage = new UserProfilePage(this);
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
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
        manageLogInOut.signIn(username1, password);

        info("Check The Notifications icon is displayed in the top navigation");
        $(ELEMENT_INTRANET_NOTIFICATION_NEAR_USER_AVATAR).waitUntil(Condition.visible, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }
}