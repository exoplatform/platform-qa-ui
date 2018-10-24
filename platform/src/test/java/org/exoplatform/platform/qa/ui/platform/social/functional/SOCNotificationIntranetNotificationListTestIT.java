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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
}