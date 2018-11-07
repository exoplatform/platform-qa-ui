package org.exoplatform.platform.qa.ui.platform.social.functional;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("functional")
@Tag("social")

public class SOCNotificationIntranetPostInStreamTestIT extends Base {
    NavigationToolbar navigationToolbar;
    AddUsers addUsers;
    ManageLogInOut manageLogInOut;
    HomePagePlatform homePagePlatform;
    UserAddManagement userAddManagement;
    ActivityStream activityStream;
    IntranetNotification intranetNotification;
    MyNotificationsSetting myNotificationsSetting;
    NotificationActivity notificationActivity;
    ConnectionsManagement connectionsManagement;
    UserProfilePage userProfilePage;

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
        notificationActivity = new NotificationActivity(this);

    }

    /**
     * <li> Case ID:125138.</li>
     * <li> Test Case Name: Check Post on my Stream notification.</li>
     * <li> Pre-Condition: - User A has posted an activity on User B activity stream
     * - The notification "Someone posts a message on my activity stream" is activated in the user settings</li>
     * <li> Post-Condition: </li>
     */
    /*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description:
			- Login with User B
			- Click the notifications icon in the top navigation
			- Check the notifications list
		*Input Data:

		*Expected Outcome:
			- A Post on my Stream notifications is displayed in the list*/

            /*Step number: 2
		*Step Name: Step 2 : Check notification message
		*Step Description:
			- Check the notification message
		*Input Data:

		*Expected Outcome:
			The notification message is :$AVATAR$USER has posted on your activity stream$ACTIVITY$DATEWhere :
			- $AVATAR is the thumbnail of User A
			- $USER is User A
			- $ACTIVITY is the activity title / message
			- $DATE is the date of the notification*/
    @Test
    public void test01_CheckPostOnMyStreamNotification() {
        info("Test 1: Check Post on my Stream notification");

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
        myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Post_intranet);


        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);

        info("User B login");
        manageLogInOut.signIn(username2, password);


        info("User A and User B are connected");
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);
        connectionsManagement.goToUserByFullName(username1 + " " + username1);

        info("User B add an activity on User A's stream");
        String activity = "activity" + getRandomNumber();
        userProfilePage.goToActivity();
        activityStream.addActivity(activity, "");
        activityStream.checkActivity(activity);

        info("Log in with User A");
        manageLogInOut.signIn(username1, password);


        info(" Go to Intranet Notification");
        navigationToolbar.goToIntranetNotification();
        String status = "has posted on your activity stream";
        intranetNotification.checkStatus(status, username2);
        intranetNotification.checkAvatarInStatus(username2, true);
        intranetNotification.checkActivityTitleInStatus(activity, true);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }

    /**
     * <li> Case ID:125139.</li>
     * <li> Test Case Name: Click Post on my Stream notification.</li>
     * <li> Pre-Condition: - User A has posted an activity on User B activity stream
     * - The notification "Someone posts a message on my activity stream" is activated in the user settings</li>
     * <li> Post-Condition: </li>
     */
      /*Step Number: 1
      *Step Name: Step 1 : Check notification list
      *Step Description:
         - Login with User B
         - Click the notifications icon in the top navigation
         - Check the notifications list
      *Input Data:
      *Expected Outcome:
         - A Post on my Stream notifications is displayed in the list*/
        /*Step number: 2
      *Step Name: Step 2 : View the notification
      *Step Description:
         - Click the notification area
      *Input Data:
      *Expected Outcome:
         The activity is displayedin the activity viewer with all comments expanded*/
    @Test
    public void test02_ClickPostOnMyStreamNotification() {

        info("Test 2: Click Post on my Stream notification");
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
        myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Post_intranet);

        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);

        info("User B login");
        manageLogInOut.signIn(username2, password);

        info("User A and User B are connected");
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);
        connectionsManagement.goToUserByFullName(username1 + " " + username1);

        info("User B add an activity on User A's stream");
        String activity = "activity" + getRandomNumber();
        userProfilePage.goToActivity();
        activityStream.addActivity(activity, null);
        activityStream.checkActivity(activity);

        info("Log in with User A");
        manageLogInOut.signIn(username1, password);

        info(" Go to Intranet Notification");
        navigationToolbar.goToIntranetNotification();
        String status = "has posted on your activity stream";
        intranetNotification.checkStatus(status, username2);
        intranetNotification.goToDetailPostInMyActivity(username2, true);
        notificationActivity.checkTitleActivityExpand(activity);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }

    /**
     * <li> Case ID:125140.</li>
     * <li> Test Case Name: Check View All after receiving a Post on my Stream notification.</li>
     * <li> Pre-Condition: - User A has posted an activity on User B activity stream
     * - The notification "Someone posts a message on my activity stream" is activated in the user settings</li>
     * <li> Post-Condition: </li>
     */
    /*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description:
			- Login with User B
			- Click the notifications icon in the top navigation
			- Check the notifications list
		*Input Data:

		*Expected Outcome:
			- A Post on my Stream notifications is displayed in the list*/
		/*Step number: 2
		*Step Name: Step 2 : Check in View all page
		*Step Description:
			- Go to View All
		*Input Data:

		*Expected Outcome:
			- Post of my Stream notification is displayed / available in the page*/
    @Test
    public void test03_CheckViewAllAfterReceivingAPostOnMyStreamNotification() {
        info("Test 3: Check View All after receiving a Post on my Stream notification");

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
        myNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Post_intranet);


        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);

        info("User B login");
        manageLogInOut.signIn(username2, password);

        info("User A and User B are connected");
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);
        connectionsManagement.goToUserByFullName(username1 + " " + username1);

        info("User B add an activity on User A's stream");
        String activity = "activity" + getRandomNumber();
        userProfilePage.goToActivity();
        activityStream.addActivity(activity, "");
        activityStream.checkActivity(activity);

        info("Log in with User A");
        manageLogInOut.signIn(username1, password);

        info(" Go to Intranet Notification");
        navigationToolbar.goToIntranetNotification();
        String status = "has posted on your activity";
        intranetNotification.goToAllNotification();
        intranetNotification.checkStatus(status, username2);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);

    }}