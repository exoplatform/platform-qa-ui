package org.exoplatform.platform.qa.ui.platform.social.functionnal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_MENTION_USER;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("sniff")
@Tag("social")

public class SOCNotificationIntranetMentionTestIT extends Base {
    NavigationToolbar navigationToolbar;
    AddUsers addUsers;
    IntranetNotification intranetNotification;
    ManageLogInOut manageLogInOut;
    MyNotificationsSetting myNotificationsSetting;
    HomePagePlatform homePagePlatform;
    ConnectionsManagement connectionsManagement;
    ActivityStream activityStream;
    UserAddManagement userAddManagement;
    ArrayList<String> arrayUser;
    NotificationActivity notificationActivity;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        addUsers = new AddUsers(this);
        intranetNotification = new IntranetNotification(this);
        manageLogInOut = new ManageLogInOut(this);
        myNotificationsSetting = new MyNotificationsSetting(this);
        homePagePlatform = new HomePagePlatform(this);
        connectionsManagement = new ConnectionsManagement(this);
        userAddManagement = new UserAddManagement(this);
        activityStream = new ActivityStream(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    }


    @Test
    public void test01_CheckMentionNotificationInActivityMessage() {
        info("Test 1: Check Mention notifications (in activity message)");

        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";
        arrayUser.add(username1 + " " + username1);

        info("Add new user");
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username1, "123456", email1, username1, username1);
        userAddManagement.addUser(username2, "123456", email2, username2, username2);
        manageLogInOut.signIn(username1, "123456");
        homePagePlatform.goToHomePage();
        String activity1 = "activitya" + getRandomNumber();
        activityStream.mentionUserActivity(username2, activity1);

		/*Step Number: 1
		 *Step Name:
		 *Step Description:
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data:

		 *Expected Outcome:
			- The Mention notification is displayed in the list*/
        manageLogInOut.signIn(username2, password);
        navigationToolbar.goToIntranetNotification();

		/*Step number: 2
		 *Step Name:
		 *Step Description:
			- Check the notification message
		 *Input Data:

		 *Expected Outcome:
			The notification message is : $AVATAR$USER has mentioned you $ACTIVITY$DATEWhere :
			- $AVATAR is the thumbnail of User A
			- $USER is User A
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the notification*/
        arrayUser.add(username1);
        String status = "UserA has mentionned you";
        intranetNotification.checkAvatarInStatus(username1 + " " + username1, true);
        intranetNotification.checkStatus(status, username1);
        intranetNotification.checkActivityTitleInStatus(activity1, true);
        intranetNotification.checkUsers(arrayUser, true);

        info("Test 4: Click the Mention notifications (in activity message)");
		/*Step Number: 1
		 *Step Name:
		 *Step Description:
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data:

		 *Expected Outcome:
			- The Mention notification is displayed in the list*/

		/*Step number: 2
		 *Step Name:
		 *Step Description:
			- Click the notification
		 *Input Data:

		 *Expected Outcome:
			- The user is redirected to the activity viewer with all comment expanded.*/
        intranetNotification.checkAvatarInStatus(arrayUser, true);
        intranetNotification.checkStatus(status, username1);
        intranetNotification.checkActivityTitleInStatus(activity1, true);
        refresh();
        $(byXpath(ELEMENT_ACTIVITY_MENTION_USER.replace("${content}", activity1).replace("${user}", username2))).waitUntil(Condition.visible, Configuration.timeout);
    }


    @Test
    public void test02_CheckMentionNotificationsInComment() {
        info("Test 1: Check Mention notifications (in activity message)");
        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";
        arrayUser.add(username1 + " " + username1);

        info("Add new user");
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username1, "123456", email1, username1, username1);
        userAddManagement.addUser(username2, "123456", email2, username2, username2);
        manageLogInOut.signIn(username1, "123456");
        homePagePlatform.goToHomePage();
        homePagePlatform.goToHomePage();
        String activity1 = "activitya" + getRandomNumber();
        activityStream.addActivity(activity1, "");
        String comment = "comment" + getRandomNumber();
        activityStream.addCommentWithMentionUser(activity1, username2, comment);


        info("Test 2: Check Mention notifications (in comment)");
		/*Step Number: 1
		 *Step Name:
		 *Step Description:
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data:

		 *Expected Outcome:
			- The Mention notification is displayed in the list*/
        manageLogInOut.signIn(username2, password);
        navigationToolbar.goToIntranetNotification();

		/*Step number: 2
		 *Step Name:
		 *Step Description:
			- Check the notification message
		 *Input Data:

		 *Expected Outcome:
			The notification message is : $AVATAR$USER has mentioned you $ACTIVITY$DATEWhere :
			- $AVATAR is the thumbnail of User A
			- $USER is User A
			- $ACTIVITY is the name of the wiki page
			- $DATE is the date of the notification*/
        arrayUser.add(username2);
        String status = "userA has mentioned you ";
        intranetNotification.checkAvatarInStatus(username1, true);
        intranetNotification.checkStatus(status, username1);
        intranetNotification.checkActivityTitleInStatus(activity1, true);


        info("Test 5: Click the Mention notifications (in comment)");
		/*Step Number: 1
		 *Step Name:
		 *Step Description:
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data:

		 *Expected Outcome:
			- The Mention notification is displayed in the list*/

		/*Step number: 2
		 *Step Name:
		 *Step Description:
			- Click the notification
		 *Input Data:

		 *Expected Outcome:
			- The user is redirected to the activity viewer with all comment expanded.
			- The comment where the mention has been done is highlighted*/
        intranetNotification.goToDetailMentionNotification(username1, true);
    }

    /*Step Number: 1
		 *Step Name:
		 *Step Description:
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data:

		 *Expected Outcome:
			- The Mention notification is displayed in the list*/
    /*Step number: 2
		 *Step Name:
		 *Step Description:
			- Go to View All page
		 *Input Data:

		 *Expected Outcome:
			- The Mention notification is displayed / available in the page*/

    @Test
    public void test03_CheckViewAllAfterReceivingAMentionNotification() {
        info("Test 3: Check View All after receiving a Mention notification");
        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";
        arrayUser.add(username1 + " " + username1);

        info("Add new user");
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username1, "123456", email1, username1, username1);
        userAddManagement.addUser(username2, "123456", email2, username2, username2);
        manageLogInOut.signIn(username1, "123456");
        homePagePlatform.goToHomePage();
        homePagePlatform.goToHomePage();
        String activity1 = "activitya" + getRandomNumber();
        activityStream.mentionUserActivity(username2, activity1);
        manageLogInOut.signIn(username2, password);
        navigationToolbar.goToIntranetNotification();
        String status = "";
        intranetNotification.checkStatus(status, username1 + " " + username1);
        intranetNotification.goToAllNotification();
        intranetNotification.checkStatus(status, username1 + " " + username1);
    }
}








