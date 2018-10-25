package org.exoplatform.platform.qa.ui.platform.social.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("social")
@Tag("sniff")

public class SOCNotificationIntranetNotificationListTestIT extends Base {
    NavigationToolbar navigationToolbar;
    ManageLogInOut manageLogInOut;
    AddUsers addUsers;
    ConnectionsManagement connectionsManagement;
    HomePagePlatform homePagePlatform;
    IntranetNotification intranetNotification;
    MyNotificationsSetting MyNotificationsSetting;
    UserAddManagement UserAddManagement;
    ArrayList<String> arrayUser;
    ArrayList<String> comments;
    UserProfilePage userProfilePage;
    UserAndGroupManagement UserAndGroupManagement;
    ActivityStream activityStream;
    UserAndGroupManagement userAndGroupManagement;
    WikiHomePage wikiHomePage;
    WikiManagement wikiManagement;
    SourceTextEditor sourceTextEditor;
    WikiValidattions wikiValidattions;


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
        activityStream = new ActivityStream(this);
        UserAndGroupManagement = new UserAndGroupManagement(this);
        wikiHomePage = new WikiHomePage(this);
        wikiManagement = new WikiManagement(this);
        sourceTextEditor = new SourceTextEditor(this);
        wikiValidattions = new WikiValidattions(this);
    }

    /*Step Number: 1
      *Step Name:
      *Step Description:
          - Login
          - Click the notifications icon
      *Input Data:

      *Expected Outcome:
          - Unread notifications are displayed in the list*/
      /*Step number: 2
		*Step Name:
		*Step Description:
			- Click 1 notification area of a like or comment notifications
		*Input Data:

		*Expected Outcome:
			- The user is redirected accordingly to the associated activity*/
    /*Step number: 3
		*Step Name:
		*Step Description:
			- Click the notifications icon again
		*Input Data:

		*Expected Outcome:
			- The list of notifications is displayed
			- The previously clicked notifications is displayed as read (different layout)*/
    /*Step number: 4
		*Step Name:
		*Step Description:
			- Click [Accept] action of a connection request notiication
		*Input Data:

		*Expected Outcome:
			- The notification message is updated accordingly
			- The notification become unread (different layout)*/
    @Test
    public void test02_ReadANotification() {
        info("Test 2: Read a notification");

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
        UserAddManagement.addUser(username1, "123456", email1, username1, username1);
        UserAddManagement.addUser(username2, "123456", email2, username2, username2);
        UserAddManagement.addUser(username3, "123456", email3, username3, username3);
        manageLogInOut.signIn(username1, "123456");

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
        ArrayList<String> comments = new ArrayList<String>();
        String comment = "comment" + getRandomNumber();
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
        String statusSendRq = "has send you a connection request";
        String statusLikeAc = "likes your activity";
        String statusCommAc = "has commented on you activity";

        info("Log in with User A");
        manageLogInOut.signIn(username1, password);
        navigationToolbar.goToIntranetNotification();
        intranetNotification.checkStatus(statusSendRq, username3);
        intranetNotification.checkStatus(statusCommAc, username2);
        intranetNotification.checkStatus(statusLikeAc, username2);

        info("Click 1 notification area of a like notification");
        intranetNotification.goToDetailLikeNotification(username2 + " " + username2, true);
        $(byText(activity)).waitUntil(Condition.visible, Configuration.timeout);
        navigationToolbar.goToIntranetNotification();
        intranetNotification.checkReadNotification(statusLikeAc, username2);
        intranetNotification.acceptRqConnection(username3);
        intranetNotification.checkReadNotification(statusSendRq, username3);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
        addUsers.deleteUser(username3);
    }

    /**
     * <li> Case ID:125121.</li>
     * <li> Test Case Name: Check order of the notifications.</li>
     * <li> Pre-Condition: A user has received several notifications (from the oldest to the newest)
     * 1. A like notification2. A comment notification3. A connection request</li>
     * <li> Post-Condition: </li>
     */
    @Test
    public void test05_CheckOrderOfTheNotifications() {
        info("Test 5: Check order of the notifications");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Login
			- Click the notification icon
			- Check the order of the notifications
		*Input Data:

		*Expected Outcome:
			- The list is revealed
			- The notifications are ordered from the newest at the top to the latest at the bottom (connection request, comment, like)*/
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String username3 = "usernamec" + getRandomString();
        String email3 = username3 + "@gmail.com";
        String password = "123456";

        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username1, password, email1, username1, username1);
        UserAddManagement.addUser(username2, password, email2, username2, username2);
        UserAddManagement.addUser(username3, password, email3, username3, username3);
        manageLogInOut.signIn(username1, password);

        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Comment_intranet);
        MyNotificationsSetting.enableNotification(org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Like_intranet);

        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);

        info("User A add an activity");
        String activity = "activity1" + getRandomNumber();
        homePagePlatform.goToHomePage();

        activityStream.addActivity(activity, null);
        activityStream.checkActivity(activity);

        info("User B login");
        manageLogInOut.signIn(username2, password);

        info("User A and User B are connected");

        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);

        info("UserB comments in UserA's activity");
        ArrayList<String> comments = new ArrayList<String>();
        String comment = "comment1" + getRandomNumber();

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

        String statusSendRq = "has send you a connection request ";
        String statusLikeAc = "likes your activity ";
        String statusCommAc = "has commented on you activity";

        info("Log in with User A");
        manageLogInOut.signIn(username1, password);


        info("The notifications are ordered from the newest at the top to the latest at the bottom (connection request, comment, like)");
        navigationToolbar.goToIntranetNotification();
        intranetNotification.checkOrderNotifications(1, statusSendRq, username3);
        intranetNotification.checkOrderNotifications(2, statusCommAc, username2);
        intranetNotification.checkOrderNotifications(3, statusLikeAc, username2);
    }

    /**
     * <li> Case ID:125122.</li>
     * <li> Test Case Name: Notifications are pushed instantaneously.</li>
     * <li> Pre-Condition: - The user A doesn't have any new notifications while starting the test
     * - The test is performed on latest version of FF, Chrome or IE 10/11</li>
     * <li> Post-Condition: </li>
     *
     * @throws AWTException
     */
    /*Step Number: 1
		*Step Name:
		*Step Description:
			- Login with user A
			- Go to wiki application
		*Input Data:

		*Expected Outcome:
			- User A is doing some actions on wiki*/
    /*Step number: 2
		*Step Name:
		*Step Description:
			- Login with User B on a different browser
			- Go to Connections or Who's Online and request a connection with User A
		*Input Data:

		*Expected Outcome:
			- The number of notifications of User A is updated as soon
			as the connection request notification is received*/
    /*Step number: 3
		*Step Name:
		*Step Description:
			- With User A, click on the notification icon
			- Browse the notifications list
		*Input Data:

		*Expected Outcome:
			- The connection request notifications is displayed*/
    /*Step number: 4
		*Step Name:
		*Step Description:
			- Come back to User B while User A is browsing the list
			- Go to the actiivty stream and mention User A
		*Input Data:

		*Expected Outcome:
			- While User A is browsing the notification list,
			the number of notification and the content of the list is updated
			as soon as a new notifications is received. The new notification is
			displayed at the top of the list.*/
    @Test
    public void test06_NotificationsArePushedInstantaneously() throws AWTException {
        info("Test 6: Notifications are pushed instantaneously");
        ArrayList<String> arrayUser = new ArrayList<String>();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";

        info("Add new user");
        navigationToolbar.goToAddUser();
        UserAddManagement.addUser(username1, "123456", email1, username1, username1);
        UserAddManagement.addUser(username2, "123456", email2, username2, username2);
        navigationToolbar.goToUsersAndGroupsManagement();
        UserAndGroupManagement.addUserAdmin(username1);
        manageLogInOut.signIn(username1, "123456");

        info("UserA created a new wiki page");
        String wiki = "wiki" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(wiki, wiki);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(wiki);

        info("Log in with userB and connect to userA");
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username1);

        info("User B login on drifferent browser");
        manageLogInOut.signIn(username2, password);

        info("User B sent a connection request to userA");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username1);

        info("Switch to user1 and check intranet notification");
        manageLogInOut.signIn(username1, password);

        info("The number of notifications of User A is updated as soon as the connection request notification is received");
        intranetNotification.checkBadgeNoti(1);

        info("The connection request notifications is displayed");
        String statusSendRq = " ";
        navigationToolbar.goToIntranetNotification();
        intranetNotification.checkStatus(statusSendRq, username2);
        String text = "text" + getRandomNumber();
        String statusMention = " ";

        ("Go to the actiivty stream and mention User A");
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToHomePage();
        activityStream.mentionUserActivity(username1, text);
        manageLogInOut.signIn(username1, password);

        info("Check badge and content notification of User A");
        intranetNotification.checkBadgeNoti(1);
        navigationToolbar.goToIntranetNotification();
        intranetNotification.checkStatus(statusMention, username2);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }
}