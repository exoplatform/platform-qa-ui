package org.exoplatform.platform.qa.ui.platform.social.functional;


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

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
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

}