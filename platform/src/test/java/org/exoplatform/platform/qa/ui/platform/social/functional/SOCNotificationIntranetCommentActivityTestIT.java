package org.exoplatform.platform.qa.ui.platform.social.functional;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
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
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("social")
@Tag("functional")
public class SOCNotificationIntranetCommentActivityTestIT extends Base {
NavigationToolbar navigationToolbar;
AddUsers addUsers;
ManageLogInOut manageLogInOut;
HomePagePlatform homePagePlatform;
ConnectionsManagement connectionsManagement;
ActivityStream activityStream;
IntranetNotification intranetNotification;
NotificationActivity notificationActivity;
MyNotificationsSetting myNotificationsSetting;
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
    navigationToolbar=new NavigationToolbar(this);
    addUsers=new AddUsers(this);
    manageLogInOut=new ManageLogInOut(this);
    homePagePlatform=new HomePagePlatform(this);
    connectionsManagement=new ConnectionsManagement(this);
    activityStream=new ActivityStream(this);
    intranetNotification=new IntranetNotification(this);
    notificationActivity=new NotificationActivity(this);
    myNotificationsSetting=new MyNotificationsSetting(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1,"gtngtn");
    }
/**
*<li> Case ID:125064.</li>
*<li> Test Case Name: Check Comment Notification (1 comment).</li>
*<li> Pre-Condition: - User A and User B are connected
- User A has posted an activity
- User B has commented on User A activity
- The notification "Someone comments on one of my activities" is activated in User A settings</li>
*<li> Post-Condition: </li>
*/
@Test
public  void test01_CheckCommentNotificationOneComment() {
    info("Test 1: Check Comment Notification (1 comment)");
    /*Step Number: 1
    *Step Name:
    *Step Description:
        - Login with User A
        - Click the notifications icon in the top navigation
        - Check the notification list
    *Input Data:

    *Expected Outcome:
        - A comment notification is displayed in the list*/
    String username1 ="username" + getRandomString();
    String email1 = username1+"@test.com";
    String username2 = "username" + getRandomString();
    String email2 = username2+"@test.com";
    String password="123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1,password);

    info("User A sent a connection request to User B");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);

    info("User A add an activity");
    String activity = "activity" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity,null);
    activityStream.checkActivity(activity);

    info("User B login");
    manageLogInOut.signIn(username2, password);
    info("User A and User B are connected");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    info("UserB comments in UserA's activity");
    String comment = "comment" + getRandomNumber();

    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity, comment);


    /*Step number: 2
    *Step Name:
    *Step Description:
        - Check the notification message
    *Input Data:

    *Expected Outcome:
        The activity message is : $AVATAR$USER has commented on your activity$ACTIVITY$DATEWhere :
        - $AVATAR is the thumbnail of User B
        - $USER is User B
        - $ACTIVITY is the activity title/message
        - $DATE is the date of the activity*/
    String status=" has commented on your activity.";
    ArrayList<String> arrayUser = new ArrayList<String>();
    info("Log in with User A");
    manageLogInOut.signIn(username1, password);
    info("Check format notification in the notification list");
    navigationToolbar.goToIntranetNotification();
    arrayUser.add(username1);
    arrayUser.add(username2);
    intranetNotification.checkFormatStatusCommentNotification(arrayUser,status,activity,true);

    /*Step number: 3
    *Step Name:
    *Step Description:
        - Click the notification area
    *Input Data:

    *Expected Outcome:
        - The activity is displayed in the activity viewer with all comment expanded.
        - The comment that this notification is about is highlighted.*/
    info("Check detail of Activity Comment");
    intranetNotification.goToDetailCommentNotification(activity,true);
    notificationActivity.checkCommentExpand(comment, true);
    manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
 }
    /**
     *<li> Case ID:125065.</li>
     *<li> Test Case Name: Check Comment Notification (2 comments).</li>
     *<li> Pre-Condition: - User A and User B are connected
     - User A and User C are connected
     - User A has posted an activity
     - User B has commented on User A activity
     - User C has commented on User A activity
     - The notification "Someone comments on one of my activities" is activated in User A settings</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test02_CheckCommentNotificationTwoComments(){
        info("Test 2: Check Comment Notification (2 comments)");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data:

		*Expected Outcome:
			- A comment notification is displayed in the list
			- The badge includes only 1 notification in the total number*/
        String username1 ="username" + getRandomString();
        String email1 = username1+"@gmail.com";
        String username2 = "username" + getRandomString();
        String email2 = username2+"@gmail.com";
        String username3 = "username" + getRandomString();
        String email3= username3+"@gmail.com";
        String password="123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        addUsers.addUser(username3, password, email3, username3, username3);
        manageLogInOut.signIn(username1,password);

        info("User A sent a connection request to UserB");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);
        info("User A sent a connection request to UserC");
        connectionsManagement.connectToAUser(username3);

        info("User A add an activity");
        String activity = "activity" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity,null);
        activityStream.checkActivity(activity);

        info("UserA and User B are connected");
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);

        info("UserB comments in UserA's activity");
        String comment = "comment" + getRandomNumber();

        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment);

        info("User A and User C are connected");
        manageLogInOut.signIn(username3, password);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);

        info("User C comment on UserA's activity");
        String comment1 = "comment1" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment1);

        info("Add all comments to the list");
        ArrayList<String> comments = new ArrayList<String>();
        comments.add(comment);
        comments.add(comment1);
		/*Step number: 2
		*Step Name:
		*Step Description:
			- Check the notification message
		*Input Data:

		*Expected Outcome:
			The activity message is : $LAST_AVATAR$USER_LIST have commented on your activity$ACTIVITY$DATEWhere :
			- $LAST_AVATAR is the thumbnail of User C
			- $USER_LISTis User B, User C
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/
        String status=" have commented on your activity.";
        info("Log in with User A");
        manageLogInOut.signIn(username1, password);
        info("Check format notification in the notification list");
        navigationToolbar.goToIntranetNotification();
        ArrayList<String> arrayUser = new ArrayList<String>();
        arrayUser.add(username1);
        arrayUser.add(username2);
        arrayUser.add(username3);
        intranetNotification.checkFormatStatusCommentNotification(arrayUser,status,activity,true);
		/*Step number: 3
		*Step Name:
		*Step Description:
			- Click the notification area
		*Input Data:

		*Expected Outcome:
			- The activity is displayed in the activity viewer with all comment expanded.*/
        info("Check detail of Activity comment");
        intranetNotification.goToDetailCommentNotification(activity,true);
        notificationActivity.checkCommentsExpand(comments, true);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
        addUsers.deleteUser(username3);
    }
    /**
     *<li> Case ID:125066.</li>
     *<li> Test Case Name: Check Comment Notification (4 comments).</li>
     *<li> Pre-Condition: - User A is connected with User B, User C and User D
     - User A has posted an activity
     - User B has commented on User A activity
     - User C has commented on User A activity
     - User D has commented on User A activity
     - User B has commented again on User A activity
     - The notification "Someone comments on one of my activities" is activated in User A settings</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test03_CheckCommentNotificationFourComments() {
        info("Test 3: Check Comment Notification (4 comments)");
		/*Step Number: 1
		*Step Name:
		*Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data:

		*Expected Outcome:
			- A comment notification is displayed in the list*/
        String username1 = "username" + getRandomString();
        String email1 = username1+"@test.com";
        String username2 = "username" + getRandomString();
        String email2 = username2+"@test.com";
        String username3 = "username" + getRandomString();
        String email3= username3+"@test.com";
        String username4 = "username" + getRandomString();
        String email4= username4+"@test.com";
        String password="123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        addUsers.addUser(username3, password, email3, username3, username3);
        addUsers.addUser(username4, password, email4, username4, username4);
        manageLogInOut.signIn(username1,password);

        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
        info("User A sent a connection request to UserB");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);
        info("User A sent a connection request to UserC");
        connectionsManagement.connectToAUser(username3);
        info("User A sent a connection request to UserD");
        connectionsManagement.connectToAUser(username4);


        info("User A add an activity");
        String activity = "activity" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity,null);
        activityStream.checkActivity(activity);

        info("UserA and User B are connected");
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);

        info("UserB comments in UserA's activity");
        String comment = "comment" + getRandomNumber();

        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment);

        info("User A and User C are connected");
        manageLogInOut.signIn(username3, password);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);

        info("User C comment on UserA's activity");
        String comment1 = "comment1" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment1);

        info("User A and User D are connected");
        manageLogInOut.signIn(username4, password);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);

        info("User D comment on UserA's activity");
        String comment2 = "comment2" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment2);
        ArrayList<String> comments = new ArrayList<String>();
        info("Add all comments to the list");
        comments.add(comment);
        comments.add(comment1);
        comments.add(comment2);

		/*Step number: 2
		*Step Name:
		*Step Description:
			- Check the notification message
		*Input Data:

		*Expected Outcome:
			The activity message is : $LAST_AVATAR$LAST2_USERS and $COUNT more have commented on your activity $ACTIVITY$DATEWhere :
			- $LAST_AVATAR is the thumbnail of User B
			- $LAST2_USERSis User D, User B
			- $COUNT is 1
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/
        String status="more have commented on your activity.";
        info("Log in with User A");
        manageLogInOut.signIn(username1, password);
        info("Check format notification in the notification list");
        ArrayList<String> arrayUser = new ArrayList<String>();
        homePagePlatform.goToConnections();
        navigationToolbar.goToIntranetNotification();
        arrayUser.add(username1);
        arrayUser.add(username2);
        arrayUser.add(username3);
        arrayUser.add(username4);
        intranetNotification.checkFormatStatusCommentNotification(arrayUser,status,activity,true);
		/*Step number: 3
		*Step Name:
		*Step Description:
			- Click the notification area
		*Input Data:

		*Expected Outcome:
			- The activity is displayed in the activity viewer with all comment expanded.*/
        info("Check detail of activity comment");
        intranetNotification.goToDetailCommentNotification(activity,true);
        notificationActivity.checkCommentsExpand(comments, true);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
        addUsers.deleteUser(username3);
        addUsers.deleteUser(username4);
    }
    /**
     *<li> Case ID:125067.</li>
     *<li> Test Case Name: Check Comment Notification when a new comment is pushed.</li>
     *<li> Pre-Condition: - User A and User B are connected
     - User A has posted an activity
     - User B has commented on User A activity
     - The notification "Someone comments on one of my activities" is activated in User A settings</li>
     *<li> Post-Condition: </li>
     Step Number: 1
		*Step Name:
		*Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		*Input Data:

		*Expected Outcome:
			- A comment notification is displayed in the list
    Step number: 2
		*Step Name:
		*Step Description:
			- Login with User C in another browser session
			- Make a connection request to User A
		*Input Data:

		*Expected Outcome:
			- The Connect Request notification is displayed in the list of User A
    Step number: 3
		*Step Name:
		*Step Description:
			- With User A, [Accept] the Connection Request
		*Input Data:

		*Expected Outcome:
			- User A and User C are connected
    Step number: 4
		*Step Name:
		*Step Description:
			- With User C, comment the activity of User A
			- Check the notifications list
		*Input Data:

		*Expected Outcome:
			- The Comment notification is listed/merged in the same previous notification (step 1)
			- The notification is displayed at the top of the list
    Step number: 5
		*Step Name:
		*Step Description:
			- Check notification message
		*Input Data:

		*Expected Outcome:
			- The notification message is : LAST_AVATAR$USER_LIST have commented on your activity$ACTIVITY$DATEWhere :
			- $LAST_AVATAR is the thumbnail of User C
			- $USER_LIST is User B, User C
			- $ACTIVITY is the activity message/title
			- $DATE is the date of the last notification of User C*/
    @Test
    public  void test04_CheckCommentNotificationWhenANewCommentIsPushed() {
        info("Test 4: Check Comment Notification when a new comment is pushed");

        String username1 =getRandomString();
        String email1 = username1+"@test.com";
        String username2 = getRandomString();
        String email2 = username2+"@test.com";
        String username3 =  getRandomString();
        String email3= username3+"@test.com";
        String password="123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        addUsers.addUser(username3, password, email3, username3, username3);
        manageLogInOut.signIn(username1,password);
        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);
        info("Add a activity");
        String activity = "activity" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity,null);
        activityStream.checkActivity(activity);
        info("User A and User B are connected");
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);
        info("UserB comments in UserA's activity");
        String comment = "" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment);
        info("User C login");
        manageLogInOut.signIn(username3, password);
        info("User C sent a connection request to User A");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username1);
        info("User A login");
        manageLogInOut.signIn(username1, password);
        info("User A and User C are connected");
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username3);
        info("Log in with User C");
        manageLogInOut.signIn(username3, password);
        info("UserC comments in UserA's activity");
        String comment1 = "comment1" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment1);
        info("Add all comments to the list");
        String status=" have commented on your activity.";
        info("Log in with user A");
        ArrayList<String> arrayUser = new ArrayList<String>();
        manageLogInOut.signIn(username1, password);
        info("Check format notification in the notification list");
        navigationToolbar.goToIntranetNotification();
        arrayUser.add(username1);
        arrayUser.add(username2);
        arrayUser.add(username3);
        intranetNotification.checkFormatStatusCommentNotification(arrayUser,status,activity,true);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
        addUsers.deleteUser(username3);
    }
    /**
     *<li> Case ID:125068.</li>
     *<li> Test Case Name: Check Comment Notification after reading the notification.</li>
     *<li> Pre-Condition: - User A and User B are connected
     - User A has posted an activity
     - User B has commented on User A activity
     - The notification "Someone comments on one of my activities" is activated in User A settings</li>
     *<li> Post-Condition: </li>
    Step Number: 1
		*Step Name:
		*Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		*Input Data:

		*Expected Outcome:
			- A comment notification is displayed in the list
    	Step number: 2
		*Step Name:
		*Step Description:
			- Click the notification message
		*Input Data:

		*Expected Outcome:
			- The activity in the activity viewer with all comments expanded.
			- The comment that this notification is about is highlighted.
			- The notification is read
    	*Step number: 3
		*Step Name:
		*Step Description:
			- Login with User C in another browser session
			- Make a connection request to User A
		*Input Data:

		*Expected Outcome:
			- The Connection Request is displayed in the notiication list of User A

		Step number: 4
		*Step Name:
		*Step Description:
			- With User A, [Accept] the Connection Request
		*Input Data:

		*Expected Outcome:
			- User A and User C are connected
		Step number: 5
		*Step Name:
		*Step Description:
			- With User C, comment the activity of User A
			- Check the notifications list
		*Input Data:

		*Expected Outcome:
			- A new notification entry is created in the list
		*Step number: 6
		*Step Name:
		*Step Description:
			- Check notification message
		*Input Data:

		*Expected Outcome:
			- The notification message is : $AVATAR$USER has commented on your activity$ACTIVITY$DATEWhere :
			- $LAST_AVATAR is the thumbnail of User C
			- $USER is User C
			- $ACTIVITY is the activity message/title
			- $DATE is the date of the notification of User C*/

    @Test
    public  void test05_CheckCommentNotificationAfterReadingTheNotification() {
        info("Test 5: Check Comment Notification after reading the notification");
        String username1 =  getRandomString();
        String email1 = username1+"@gmail.com";
        String username2 = getRandomString();
        String email2 = username2+"@gmail.com";
        String username3 = getRandomString();
        String email3= username3+"@gmail.com";
        String password="123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        addUsers.addUser(username3, password, email3, username3, username3);
        manageLogInOut.signIn(username1,password);
        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);

        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);

        info("User A add an activity");
        String activity = "activity" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity,null);
        activityStream.checkActivity(activity);

        info("User B login");
        manageLogInOut.signIn(username2, password);
        info("User A and User B are connected");
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);
        info("UserB comments in UserA's activity");
        String comment = "comment" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment);
        info("User A login");
        manageLogInOut.signIn(username1, password);
        info("Verify that badge notification is shown. The notification still is not read");
        $(byXpath(ELEMENT_INTRANET_NOTIFICATION_BADGE_NUMBER.replace("$num","1"))).waitUntil(Condition.visible,Configuration.timeout);
        homePagePlatform.goToWiki();
        info("Open Notification list");
        navigationToolbar.goToIntranetNotification();
        info("Read detail notification");
        intranetNotification.goToDetailCommentNotification(activity,true);
        notificationActivity.checkCommentExpand(comment, true);
        info("Verify that badge notification is not shown. The notification is read.");
        $(byXpath(ELEMENT_INTRANET_NOTIFICATION_BADGE_NUMBER.replace("$num","1"))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
        info("Login with User C");
        manageLogInOut.signIn(username3, password);
        info("User sent a connnection request to User A");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username1);
        info("Login with User A");
        manageLogInOut.signIn(username1, password);
        info("User A and User C are connected");
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username3);
        info("Login with User C");
        manageLogInOut.signIn(username3, password);
        info("UserC comments in UserA's activity");
        String comment1 = "comment1" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment1);

        info("Add all comments to the list");
        info("Login with User A");
        manageLogInOut.signIn(username1, password);
        info("Check format notification in the notification list");
        String status=" has commented on your activity.";
        navigationToolbar.goToIntranetNotification();
        ArrayList<String> arrayUser = new ArrayList<String>();
        arrayUser.add(username1);
        arrayUser.add(username2);
        arrayUser.add(username3);
        intranetNotification.checkFormatStatusCommentNotification(arrayUser,status,activity,true);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
        addUsers.deleteUser(username3);
    }

    /**
     *<li> Case ID:125069.</li>
     *<li> Test Case Name: Check View All after receiving a Comment notification (1 comment).</li>
     *<li> Pre-Condition: - User A and User B are connected
     - User A has posted an activity
     - User B has commented on User A activity
     - The notification "Someone comments on one of my activities" is activated in User A settings</li>
     *<li> Post-Condition: </li>

    		Step Number: 1
		*Step Name:
		*Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data:

		*Expected Outcome:
			- A comment notification is displayed in the list
    		Step number: 2
		*Step Name:
		*Step Description:
			- Go to View All page
		*Input Data:

		*Expected Outcome:
			The notification is available/displayed in the View All page :$AVATAR$USER has commented on your activity$ACTIVITY$DATEWhere :
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/

    @Test
    public  void test06_CheckViewAllAfterReceivingACommentNotificationOneComment() {
        info("Test 6: Check View All after receiving a Comment notification (1 comment)");
        String username1 = getRandomString();
        String email1 = username1+"@test.com";
        String username2 = getRandomString();
        String email2 = username2+"@test.com";
        String password="123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        manageLogInOut.signIn(username1,password);
        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);

        info("User A sent a connection request to User B");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);

        info("User A add an activity");
        String activity = "activity" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity,null);
        activityStream.checkActivity(activity);

        info("Login with User B");
        manageLogInOut.signIn(username2, password);
        info("User A and User B are connected");
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);
        info("UserB comments in UserA's activity");
        String comment = "comment" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment);
        info("Log in with user A");
        manageLogInOut.signIn(username1, password);
        info("Check format notification in the notification list page");
        String status=" has commented on your activity.";
        navigationToolbar.goToIntranetNotification();
        intranetNotification.goToAllNotification();
        ArrayList<String> arrayUser = new ArrayList<String>();
        arrayUser.add(username2);
        intranetNotification.checkFormatStatusCommentNotification(arrayUser,status,activity,false);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }
    /**
     *<li> Case ID:125070.</li>
     *<li> Test Case Name: Check View All page after receiving a Comment notification (2 comments).</li>
     *<li> Pre-Condition: - User A and User B are connected
     - User A and User C are connected
     - User A has posted an activity
     - User B has commented on User A activity
     - User C has commented on User A activity
     - The notification "Someone comments on one of my activities" is activated in User A settings</li>
     *<li> Post-Condition: </li>
    Step Number: 1
		*Step Name:
		*Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data:

		*Expected Outcome:
			- A comment notification is displayed in the list

		Step number: 2
		*Step Name:
		*Step Description:
			- Go to View All page
		*Input Data:

		*Expected Outcome:
			The Comment notification is displayed / available in the page : $LAST_AVATAR$USER_LIST have commented on your activity$ACTIVITY$DATEWhere :
			- $LAST_AVATAR is the thumbnail of User C
			- $USER_LISTis User B, User C
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/
    @Test
    public  void test07_CheckViewAllPageAfterReceivingACommentNotificationTwoComments() {
        info("Test 7: Check View All page after receiving a Comment notification (2 comments)");

        String username1 =getRandomString();
        String email1 = username1+"@test.com";
        String username2 = getRandomString();
        String email2 = username2+"@test.com";
        String username3 = getRandomString();
        String email3= username3+"@test.com";
        String password="123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        addUsers.addUser(username3, password, email3, username3, username3);
        manageLogInOut.signIn(username1,password);
        info("goto My notification");
        navigationToolbar.goToMyNotifications();
        myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);
        homePagePlatform.goToConnections();
        info("User A sent a connection request to User B");
        connectionsManagement.connectToAUser(username2);
        info("User A sent a connection request to User C");
        connectionsManagement.connectToAUser(username3);
        info("User A add an activity");
        String activity = "" +getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity,null);
        activityStream.checkActivity(activity);
        info("Log in with User B");
        manageLogInOut.signIn(username2, password);
        info("User A and User B are connected");
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);
        info("UserB comments in UserA's activity");
        String comment = "" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment);
        info("Log in with User C");
        manageLogInOut.signIn(username3, password);
        info("User A and User C are connected");
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(username1);
        info("UserC comments in UserA's activity");
        String comment1 = "comment1" + getRandomNumber();
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity, comment1);
        manageLogInOut.signIn(username1, password);
        info("Check format notification in the notification list page");
        String status=" have commented on your activity.";
        navigationToolbar.goToIntranetNotification();
        intranetNotification.goToAllNotification();
        ArrayList<String> arrayUser = new ArrayList<String>();
        arrayUser.add(username1);
        arrayUser.add(username2);
        arrayUser.add(username3);
        intranetNotification.checkFormatStatusCommentNotification(arrayUser,status,activity,false);
        manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
        addUsers.deleteUser(username3);
    }
}