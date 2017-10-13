package org.exoplatform.platform.qa.ui.platform.social;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.refresh;
import static org.bouncycastle.cms.CMSSignedGenerator.DATA;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ICON_LIKE_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_ALERT_NOTIFICATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_BUTTON_ACCEPT_INVITATION_IN_NOTIFICATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_BUTTON_CANCEL_INVITATION_IN_NOTIFICATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_NOTIFICATION_DROPDOWN;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_CONTENT_NAME_PROFILE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;


public class SOCNotificationsIntranetNotificationTypesTestIT extends Base{
NavigationToolbar navigationToolbar;
AddUsers addUsers;
ManageLogInOut manageLogInOut;
ConnectionsManagement connectionsManagement;
MyNotificationsSetting myNotificationsSetting;
HomePagePlatform homePagePlatform;
ActivityStream activityStream;
IntranetNotification intranetNotification;
NotificationActivity notificationActivity;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
	navigationToolbar=new NavigationToolbar(this);
	addUsers=new AddUsers(this);
	manageLogInOut=new ManageLogInOut(this);
	connectionsManagement=new ConnectionsManagement(this);
	myNotificationsSetting=new MyNotificationsSetting(this);
	homePagePlatform=new HomePagePlatform(this);
	activityStream=new ActivityStream(this);
	intranetNotification=new IntranetNotification(this);
	notificationActivity=new NotificationActivity(this);
		if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
			$(ELEMENT_SKIP_BUTTON).click();
		}
		if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
			manageLogInOut.signOut();
		}
		manageLogInOut.signInCas(DATA_USER1, "gtngtn");
	}



	/**
	 *<li> Case ID:122997.</li>
	 *<li> Test Case Name: Comment Intranet Notification.</li>
	 Precondition:
		 	- User A and User B are connected
			- User A has posted an activity
			- User B has commented on User A activity
			- The notification "Someone comments on one of my activities" is activated in User A settings
	Step Number: 1
		 *Step Name: Step 1: Check comment notification in the notification list
		 *Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data:
		 *Expected Outcome:
			- A comment notification is displayed in the list
			- The activity message is :
				$AVATAR
				$USER has commented on your activity
				$ACTIVITY
				$DATE

			Where :
				- $AVATAR is the thumbnail of User B
				- $USER is User B
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity
		*Step Number: 2
		 *Step Name: Step 2: Check comment notification in activity Viewer
		 *Step Description:
			- Click the notification area
		 *Input Data:
		 *Expected Outcome:
			- The activity is displayed in the activity viewer with all comment expanded.
			- The comment that this notification is about is highlighted.

		Step Number: 3
		 *Step Name: Step 3: Check comment notification in the View All
		 *Step Description:
			- Click [View All]
		 *Input Data:
		 *Expected Outcome:
			- The comment notification is displayed in the View All page
			- The activity message is :
				$AVATAR
				$USER has commented on your activity
				$ACTIVITY
				$DATE

			Where :
				- $AVATAR is the thumbnail of User B
				- $USER is User B
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
	@Test
	public  void test01_CommentIntranetNotification() {
		//Setup data test
		String username1 = "usernamea" + getRandomString();
		String email1 = username1+ "@test.com";
	
		String username2 = "usernameb" + getRandomString();
		String email2 = username2+ "@test.com";
		
		String activity = "activity" + getRandomNumber();
		String comment1 = "comment1" + getRandomNumber();
		String password="123456";


		info("Add new user");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		manageLogInOut.signIn(username1, password);
		info ("Connect with user");
		homePagePlatform.goToConnections();
		connectionsManagement.connectToAUser(username2);
		
		info("Enable like and new user notifications");
		navigationToolbar.goToMyNotifications();
		myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);

		info("Add a activity");
		executeJavaScript("window.scrollBy(0,-5500)","");
		homePagePlatform.goToHomePage();
		activityStream.addActivity(activity, "");
		activityStream.checkActivity(activity);

		info("user comments in John's activity");
		manageLogInOut.signIn(username2, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.commentActivity(activity,comment1);


		info("Check comment notification in the notification list");
		manageLogInOut.signIn(username1, password);
		ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
		ELEMENT_ALERT_NOTIFICATION.click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).should(Condition.exist);

		info("Check comment notification in activity Viewer");
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).click();
		$(byText(activity)).parent().parent().parent().parent().find(byText(comment1)).should(Condition.exist);

		info("Check comment notification in the View All");
		navigationToolbar.goToIntranetNotification();
		intranetNotification.goToAllNotification();
		$(byId("UIIntranetNotificationsPortlet")).find(byText(comment1)).should(Condition.exist);
		manageLogInOut.signIn(DATA_USER1,"gtngtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);
		addUsers.deleteUser(username2);
	}
	/**
	 *<li> Case ID:122999.</li>
	 *<li> Test Case Name: Comment notification when a new comment is pushed.</li>
	 */
	@Test
	@BugInPLF("5852")
	public  void test02_CommentNotificationWhenANewNotificationIsPushed() {
		//Setup data test
		String username1 ="usernamea" + getRandomString();
		String email1 = username1+ "@test.com";

		String username2 = "usernameb" + getRandomString();
		String email2 = username2+ "@test.com";

		String username3 = "usernamec" + getRandomString();
		String email3 = username3+ "@test.com";
String password="123456";
		String activity = "activity" + getRandomNumber();
		String comment1 = "comment1" + getRandomNumber();
		String comment2 = "comment2" + getRandomNumber();


		/*Precondition:
		 	- User A, User B and User B are connected
			- User A has posted an activity
			- User B has commented on User A activity
			- The notification "Someone comments on one of my activities" is activated in User A settings*/
		info("Add 2 users");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		addUsers.addUser(username3, password, email3, username3, username3);
		manageLogInOut.signIn(username1, password);

		info ("Connect with 2 users");
		homePagePlatform.goToConnections();
		connectionsManagement.connectToAUser(username2);
		connectionsManagement.connectToAUser(username3);

		info("Enable like and new user notifications");
		navigationToolbar.goToMyNotifications();
		myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);

		info("Add a activity");
		homePagePlatform.goToHomePage();
		activityStream.addActivity(activity, "");
		activityStream.checkActivity(activity);

		info("user1 comments in John's activity");
		manageLogInOut.signIn(username2, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.commentActivity(activity,comment1);

		/*Step Number: 1
		 *Step Name: Step 1: Check comment notification of user 1
		 *Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		 *Input Data:
		 *Expected Outcome:
			- A comment notification is displayed in the list (from User B)*/
		info("Check comment notification in the notification list");
		manageLogInOut.signIn(username1, password);
		ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
		ELEMENT_ALERT_NOTIFICATION.click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment1)).should(Condition.exist);

		/*Step Number: 2
		 *Step Name: Step 2: Check comment notification of user 2
		 *Step Description:
			- With User C, comment the activity of User A
			- Check the notifications list
		 *Input Data:
		 *Expected Outcome:
			- The Comment notification is listed/merged in the same previous notification (step 1)
			- The notification is displayed at the top of the list*/
		info("user2 comments in John's activity");
		manageLogInOut.signIn(username3, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.commentActivity(activity,comment2);

		info("Check comment notification in the notification list");
		manageLogInOut.signIn(username1, password);
		ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
		ELEMENT_ALERT_NOTIFICATION.click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(comment2)).should(Condition.exist);

		/*Step Number: 3
		 *Step Name: Step 3: Check comment notification in View All
		 *Step Description:
			- Check notification message in the list and in View All
		 *Input Data:
		 *Expected Outcome:
			- The notification message is :
				LAST_AVATAR
				$USER_LIST have commented on your activity
				$ACTIVITY
				$DATE
			Where :
				- $LAST_AVATAR is the thumbnail of User C
				- $USER_LIST is User B, User C
				- $ACTIVITY is the activity message/title
				- $DATE is the date of the last notification of User C*/
		info("Check comment notification in the View All");
		intranetNotification.goToAllNotification();
		$(byId("UIIntranetNotificationsPortlet")).find(byText(comment1)).parent().find(byText(username2+" "+username2+" has commented on your activity.")).should(Condition.exist);
		$(byId("UIIntranetNotificationsPortlet")).find(byText(comment2)).should(Condition.exist);
	}
	/**
	 *<li> Case ID:122998.</li>
	 *<li> Test Case Name: Comments Intranet Notification merged.</li>
	Precondition:
		 	- User A is connected with User B, User C and User D
			- User A has posted an activity
			- User B has commented on User A activity
			- User C has commented on User A activity
			- User D has commented on User A activity
			- User B has commented again on User A activity
			- The notification "Someone comments on one of my activities" is activated in User A settings
			Step Number: 1
		 *Step Name: Step 1: Check comment notification in notification list
		 *Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data:
		 *Expected Outcome:
			- A comment notification is displayed in the list
			- The activity message is :
				$LAST_AVATAR
				$LAST2_USERS and $COUNT more have commented on your activity $ACTIVITY
				$DATE
			Where :
				- $LAST_AVATAR is the thumbnail of User B
				- $LAST2_USERS  is User D, User B
				- $COUNT is 2
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*

		*Step Number: 2
		 *Step Name: Step 2: Check comment notification of in activity viewer
		 *Step Description:
			- Click the notification area
		 *Input Data:
		 *Expected Outcome:
			- The activity is displayed in the activity viewer with all comment expanded.
			Step Number: 3
		 *Step Name: Step 3: Check comment notification of in View All
		 *Step Description:
			- Click [View All] from the notification list
		 *Input Data:
		 *Expected Outcome:
			- The comment notification is displayed in the View All page
			- The activity message is :
				$LAST_AVATAR
				$LAST2_USERS and $COUNT more have commented on your activity $ACTIVITY
				$DATE
			Where :
				- $LAST_AVATAR is the thumbnail of User B
				- $LAST2_USERS  is User D, User B
				- $COUNT is 2
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
	@Test
	public  void test03_CommentsIntranetNotificationMerged() {
		//Setup data test
		String username1 = "usernamea" + getRandomString();
		String email1 = username1+ "@test.com";

		String username2 ="usernameb" + getRandomString();
		String email2 = username2+ "@test.com";

		String username3 = "usernamec" + getRandomString();
		String email3 = username3+ "@test.com";

		String username4 = "usernamed" + getRandomString();
		String email4 = username4+ "@test.com";

		String activity = "activity" + getRandomNumber();
		String comment1 = "comment1" + getRandomNumber();
		String comment2 = "comment2" + getRandomNumber();
		String comment3 = "comment3" + getRandomNumber();
		String comment4 = "comment4" + getRandomNumber();
String password="123456";


		info("Add 4 users");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		addUsers.addUser(username3, password, email3, username3, username3);
		addUsers.addUser(username4, password, email4, username4, username4);
		manageLogInOut.signIn(username1, password);

		info ("Connect with 3 users");
		homePagePlatform.goToConnections();
		connectionsManagement.connectToAUser(username2);
		connectionsManagement.connectToAUser(username3);
		connectionsManagement.connectToAUser(username4);

		info("Enable like and new user notifications");
		navigationToolbar.goToMyNotifications();
		myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Comment_intranet);

		info("Add a activity");
		executeJavaScript("window.scrollBy(0,-5500)","");
		homePagePlatform.goToHomePage();
		activityStream.addActivity(activity, "");
		activityStream.checkActivity(activity);

		info("user1 comments in John's activity");
		manageLogInOut.signIn(username2, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.commentActivity(activity, comment1);

		info("user2 comments in John's activity");
		manageLogInOut.signIn(username3, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.commentActivity(activity, comment2);

		info("user3 comments in John's activity");
		manageLogInOut.signIn(username4, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.commentActivity(activity, comment3);

		info("user2 comments in John's activity");
		manageLogInOut.signIn(username2, password);
		activityStream.commentActivity(activity, comment4);


		info("Check comment notification in the notification list");
		manageLogInOut.signIn(username1, password);
		ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
		ELEMENT_ALERT_NOTIFICATION.click();


		info("Check comment notification in activity Viewer");
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).click();
		$(byText(activity)).parent().parent().parent().parent().find(byText(comment1)).should(Condition.exist);
		$(byText(activity)).parent().parent().parent().parent().find(byText(comment2)).should(Condition.exist);
		$(byText(activity)).parent().parent().parent().parent().find(byText(comment3)).should(Condition.exist);
		$(byText(activity)).parent().parent().parent().parent().find(byText(comment4)).should(Condition.exist);


		info("Check comment notification in the View All");
		navigationToolbar.goToIntranetNotification();
		intranetNotification.goToAllNotification();
		$(byText(activity)).parent().parent().shouldHave(text(username2+" "+username2+", "+username4+" "+username4+" and 1 more have commented on your activity."));
		$(byText(comment4)).should(Condition.exist);
		manageLogInOut.signIn(DATA_USER1,"gtngtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);
		addUsers.deleteUser(username2);
		addUsers.deleteUser(username3);
		addUsers.deleteUser(username4);

	}
	/**
	 *<li> Case ID:122982.</li>
	 *<li> Test Case Name: Connection Request Intranet notification (Accept).</li>
	 */
	@Test
	public  void test04_ConnectionRequestIntranetNotification_Accept() {
		//Setup data test
		String username1 = "usernamea" + getRandomString();
		String email1 = username1+ "@test.com";
		String username2 ="usernameb" + getRandomString();
		String email2 = username2+ "@test.com";
		String password="123456";
		/*Precondition:
		 	- The notification "Someone sends me a connection request" is activated in User Settings
			- User A sent a connection request to User B*/
		info("Add a user");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		manageLogInOut.signIn(username1, password);
		info ("Connect with user");
		homePagePlatform.goToConnections();
		connectionsManagement.connectToAUser(username2);


		/*Step Number: 1
		 *Step Name: Step 1: Check notification list
		 *Step Description:
			- Login with User B
			- Click the notifications icon
			- Check the notification list
		 *Input Data:
		 *Expected Outcome:
			- The list includes a Connection Request notifications.*/
		/*Step Number: 2
		 *Step Name: Step 2: Check notification message
		 *Step Description:
			- Check the notification message
		 *Input Data:
		 *Expected Outcome:
			- The notification message is :
				- $AVATAR
				- $USER wants to connect with you
				- $DATE
				- 2 actions : [Accept] | [Refuse]
			Where :
				- $AVATAR is thumbnail of User A
				- $USER is User A
				- $DATE is the date of the notification*/
		info("Check connection request notification in notification list");
		manageLogInOut.signIn(username2, password);
		ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
		ELEMENT_ALERT_NOTIFICATION.click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().shouldHave(text(username1+" "+username1+" wants to connect with you"));
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().parent().find(ELEMENT_BUTTON_ACCEPT_INVITATION_IN_NOTIFICATION).should(Condition.exist);
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().parent().find(ELEMENT_BUTTON_CANCEL_INVITATION_IN_NOTIFICATION).should(Condition.exist);
		/*Step Number: 3
		 *Step Name: Step 3: Check notification area
		 *Step Description:
			- Click the notification area
		 *Input Data:
		 *Expected Outcome:
			- User B is redirected to the profile of User A*/
		/*Step Number: 4
		 *Step Name: Step 4: Accept
		 *Step Description:
			- Click [Accept]
		 *Input Data:
		 *Expected Outcome:
			- The connection request is accepted (User A and User B are connected)
			- The notification message is updated to :
				$AVATAR
				You are connected with $USER
				$DATE*/
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().parent().find(ELEMENT_BUTTON_ACCEPT_INVITATION_IN_NOTIFICATION).click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().shouldHave(text("You are connected with "+username1+" "+username1)).click();
		ELEMENT_CONTENT_NAME_PROFILE.find(byText(username1 + " " + username1)).should(Condition.exist);
		manageLogInOut.signIn(DATA_USER1,"gtngtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);
		addUsers.deleteUser(username2);

	}


	/**
	 *<li> Case ID:122983.</li>
	 *<li> Test Case Name: Connection Request Intranet notification (Accept).</li>
	 */
	@Test
	public  void test05_ConnectionRequestIntranetNotification_Refuse() {
		//Setup data test
		String username1 = "usernamea" + getRandomString();
		String email1 = username1+ "@test.com";
		String username2 ="usernameb" + getRandomString();
		String email2 = username2+ "@test.com";
		String password="123456";
		/*Precondition:
		 	- The notification "Someone sends me a connection request" is activated in User Settings
			- User A sent a connection request to User B*/
		info("Add a user");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		manageLogInOut.signIn(username1, password);
		info ("Connect with user");
		homePagePlatform.goToConnections();
		connectionsManagement.connectToAUser(username2);


		/*Step Number: 1
		 *Step Name: Step 1: Check notification list
		 *Step Description:
			- Login with User B
			- Click the notifications icon
			- Check the notification list
		 *Input Data:
		 *Expected Outcome:
			- The list includes a Connection Request notifications.*/
		/*Step Number: 2
		 *Step Name: Step 2: Check notification message
		 *Step Description:
			- Check the notification message
		 *Input Data:
		 *Expected Outcome:
			- The notification message is :
				- $AVATAR
				- $USER wants to connect with you
				- $DATE
				- 2 actions : [Accept] | [Refuse]
			Where :
				- $AVATAR is thumbnail of User A
				- $USER is User A
				- $DATE is the date of the notification*/
		info("Check connection request notification in notification list");
		manageLogInOut.signIn(username2, password);
		ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
		ELEMENT_ALERT_NOTIFICATION.click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().shouldHave(text(username1+" "+username1+" wants to connect with you"));
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().parent().find(ELEMENT_BUTTON_ACCEPT_INVITATION_IN_NOTIFICATION).should(Condition.exist);
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().parent().find(ELEMENT_BUTTON_CANCEL_INVITATION_IN_NOTIFICATION).should(Condition.exist);
		/*Step Number: 3
		 *Step Name: Step 3: Check notification area
		 *Step Description:
			- Click the notification area
		 *Input Data:
		 *Expected Outcome:
			- User B is redirected to the profile of User A*/
		/*Step Number: 4
		 *Step Name: Step 4: Accept
		 *Step Description:
			- Click [Accept]
		 *Input Data:
		 *Expected Outcome:
			- The connection request is accepted (User A and User B are connected)
			- The notification message is updated to :
				$AVATAR
				You are connected with $USER
				$DATE*/
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().parent().find(ELEMENT_BUTTON_CANCEL_INVITATION_IN_NOTIFICATION).click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().shouldNot(Condition.exist);
		manageLogInOut.signIn(DATA_USER1,"gtngtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);
		addUsers.deleteUser(username2);

	}

	/**
	 *<li> Case ID:123000.</li>
	 *<li> Test Case Name: Like Intranet Notification.</li>
	 */
	@Test
	public  void test06_LikeIntranetNotification() {
		//Setup data test
		String username1 = "usernamea" + getRandomString();
		String email1 = username1+ "@test.com";
		String username2 = "usernameb" + getRandomString();
		String email2 = username2+"@test.com";
		String password="123456";
		String activity ="activity" + getRandomNumber();

		/*Precondition:
		 	- User A and User B are connected
			- User A has posted an activity
			- User B has liked User A activity
			- The notification "Someone likes one of my activities" is activated in the user settings*/
		info("Add User");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		manageLogInOut.signIn(username1, password);
		info ("Connect with user");
		homePagePlatform.goToConnections();
		connectionsManagement.connectToAUser(username2);
		info("Enable like and new user notifications");
		navigationToolbar.goToMyNotifications();
		myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);

		info("Add a activity");
		executeJavaScript("window.scrollBy(0,-5500)","");
		homePagePlatform.goToHomePage();
		activityStream.addActivity(activity, "");
		activityStream.checkActivity(activity);

		info("user likes John's activity");
		manageLogInOut.signIn(username2, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.likeActivity(activity);

		/*Step Number: 1
		 *Step Name: Step 1: Check notifications list
		 *Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data:
		 *Expected Outcome:
			- A Like notification is displayed in the list
			- The activity message is :
				$AVATAR
				$USER likes your activity.
				$ACTIVITY
					$DATE
			Where :
				- $AVATAR is the thumbnail of User B
				- $USER is User B
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
		info("Check Like notification in intranet notification");
		manageLogInOut.signIn(username1, password);
		ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
		ELEMENT_ALERT_NOTIFICATION.click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).parent().parent().shouldHave(text(username2+" "+username2+" likes your activity."));

		/*Step Number: 2
		 *Step Name: Step 2: Read the notification
		 *Step Description:
			- Click the notification area
		 *Input Data:
		 *Expected Outcome:
			- The activity is displayed in the activity viewer with all comment expanded.*/
		info("Check comment notification in activity Viewer");
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).click();
		$(byText(activity)).parent().parent().parent().find(ELEMENT_ICON_LIKE_ACTIVITY).parent().shouldHave(text("1"));
		/*Step Number: 3
		 *Step Name: Step 3: Check notification message
		 *Step Description:
			- Click [View All] from the notification list
		 *Input Data:
		 *Expected Outcome:
			- A Like notification is displayed in the list
			- The activity message is :
				$AVATAR
				$USER likes your activity.
				$ACTIVITY
				$DATE
			Where :
				- $AVATAR is the thumbnail of User B
				- $USER is User B
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
		navigationToolbar.goToIntranetNotification();
		intranetNotification.goToAllNotification();
		$(byText(activity)).parent().parent().parent().find(byText(username2+" "+username2)).should(Condition.exist);
		manageLogInOut.signIn(DATA_USER1,"gtngtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);
		addUsers.deleteUser(username2);

	}

	/**
	 *<li> Case ID:123001.</li>
	 *<li> Test Case Name: Like Intranet Notification merged.</li>
	 */
	@Test
	@BugInPLF("PLF-7656")
	public  void test07_LikeIntranetNotificationMerged(){
		String username1 = "usernamea" + getRandomString();
		String email1 = username1+ "@test.com";
		String username2 = "usernameb" + getRandomString();
		String email2 = username2+ "@test.com";
		String username3 = "usernamec" + getRandomString();
		String email3 = username3+ "@test.com";
		String username4 = "usernamed" + getRandomString();
		String email4 = username4+"@test.com";
		String username5 = "usernamee" + getRandomString();
		String email5 = username5+ "@test.com";
		String password="123456";

		String activity = "activity" + getRandomNumber();

		/*Precondition:
		 	- User A is connected with User B, User C, User D and User E
			- User A has posted an activity
			- User B has liked User A activity
			- User C has liked User A activity
			- User D has liked User A activity
			- User E has liked User A activity
			- The notification "Someone likes one of my activities" is activated in the user settings*/
		info("Add 4 users");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		addUsers.addUser(username3, password, email3, username3, username3);
		addUsers.addUser(username4, password, email4, username4, username4);
		addUsers.addUser(username5, password, email5, username5, username5);
		manageLogInOut.signIn(username1, password);
		info ("Connect with 4 users");
		homePagePlatform.goToConnections();
		connectionsManagement.connectToAUser(username2);
		connectionsManagement.connectToAUser(username3);
		connectionsManagement.connectToAUser(username4);
		connectionsManagement.connectToAUser(username5);

		info("Enable like and new user notifications");
		navigationToolbar.goToMyNotifications();
		myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);

		info("Add a activity");
		executeJavaScript("window.scrollBy(0,-5500)","");
		homePagePlatform.goToHomePage();
		activityStream.addActivity(activity, "");
		activityStream.checkActivity(activity);

		info("user 1 likes John's activity");
		manageLogInOut.signIn(username2, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.likeActivity(activity);

		info("user 2 likes John's activity");
		manageLogInOut.signIn(username3, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.likeActivity(activity);

		info("user 3 likes John's activity");
		manageLogInOut.signIn(username4, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.likeActivity(activity);

		info("user 4 likes John's activity");
		manageLogInOut.signIn(username5, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.likeActivity(activity);

		/*Step Number: 1
		 *Step Name: Step 1: Check notifications list
		 *Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data:
		 *Expected Outcome:
			- A Like notification is displayed in the list
			- The activity message is :
				$LAST_AVATAR
				$LAST2_USERS and $COUNT more like your activity. $ACTIVITY
				$DATE
			Where :
				- $LAST_AVATAR is the thumbnail of User E
				- $LAST2_USERS  is User E and User D
				- $COUNT is 2
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
		info("Check Like notification in intranet notification");
		manageLogInOut.signIn(username1, password);
		ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
		ELEMENT_ALERT_NOTIFICATION.click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).parent().parent().shouldHave(text(username5+" "+username5+", "+username4+" "+username4+" and 2 more like your activity."));

		/*Step Number: 2
		 *Step Name: Step 2: Read the notification
		 *Step Description:
			- Click the notification area
		 *Input Data:
		 *Expected Outcome:
			- The activity is displayed in the activity viewer with all comment expanded.*/
		info("Check comment notification in activity Viewer");
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).click();
		$(byText(activity)).parent().parent().parent().find(ELEMENT_ICON_LIKE_ACTIVITY).parent().shouldHave(text("4"));

		/*Step Number: 3
		 *Step Name: Step 3: Check notification message
		 *Step Description:
			- Click [View All] from the notifications list
		 *Input Data:
		 *Expected Outcome:
			- A Like notification is displayed in View All page
			- The activity message is :
				$LAST_AVATAR
				$LAST2_USERS and $COUNT more like your activity. $ACTIVITY
				$DATE
			Where :
				- $LAST_AVATAR is the thumbnail of User E
				- $LAST2_USERS  is User E and User D
				- $COUNT is 2
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity.*/
		navigationToolbar.goToIntranetNotification();
		intranetNotification.goToAllNotification();
		$(byText(activity)).parent().parent().parent().find(byText(username2+" "+username2)).should(Condition.exist);
		manageLogInOut.signIn(DATA_USER1,"gtngtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);
		addUsers.deleteUser(username2);
		addUsers.deleteUser(username3);
		addUsers.deleteUser(username4);
		addUsers.deleteUser(username5);
	}

	/**
	 *<li> Case ID:123002.</li>
	 *<li> Test Case Name: Like Notification when a new like is pushed.</li>
	 */
	@Test
	@BugInPLF("PLF-7656")
	public  void test08_NotificationWhenANewLikeIsPushed(){
		//Setup data test
		String username1 = "usernamea" + getRandomString();
		String email1 = username1+ "@test.com";
		String username2 = "usernameb" + getRandomString();
		String email2 = username2+"@test.com";
		String username3 ="usernamec" + getRandomString();
		String email3 = username3+ "@test.com";
		String password="123456";
		String activity = "activity" + getRandomNumber();

		/*Precondition:
		 	- User A, User B, User C are connected
			- User A has posted an activity
			- User B has liked User A activity
			- The notification "Someone likes one of my activities" is activated in the user settings*/
		info("Add 2 users");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		addUsers.addUser(username3, password, email3, username3, username3);
		manageLogInOut.signIn(username1, password);
		info ("Connect with 2 users");
		homePagePlatform.goToConnections();
		connectionsManagement.connectToAUser(username2);
		connectionsManagement.connectToAUser(username3);
		info("Enable like and new user notifications");
		navigationToolbar.goToMyNotifications();
		myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);

		info("Add a activity");
		homePagePlatform.goToHomePage();
		activityStream.addActivity(activity, "");
		activityStream.checkActivity(activity);

		info("user 1 likes John's activity");
		manageLogInOut.signIn(username2, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.likeActivity(activity);

		/*Step Number: 1
		 *Step Name: Step 1: Check notifications list
		 *Step Description:
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		 *Input Data:
		 *Expected Outcome:
			- A Like notification is displayed in the list.*/
		info("Check Like notification in intranet notification");
		manageLogInOut.signIn(username1, password);
		ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
		ELEMENT_ALERT_NOTIFICATION.click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).parent().parent().shouldHave(text(username2+" "+username2+" likes your activity."));

		/*Step Number: 2
		 *Step Name: Step 2: Push a new like notification
		 *Step Description:
			- With User C, like the activity of User A
			- Check the notifications list
		 *Input Data:
		 *Expected Outcome:
			- The Like notification is listed/merged in the same previous notification (step 1)
			- The notification is displayed at the top of the list.*/
		info("user 2 likes John's activity");
		manageLogInOut.signIn(username3, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.likeActivity(activity);

		info("Check Like notification in intranet notification");
		manageLogInOut.signIn(username1, password);
		ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
		ELEMENT_ALERT_NOTIFICATION.click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(activity)).parent().parent().shouldHave(text(username2+" "+username2+", "+username3+" "+username3+" likes your activity."));


		/*Step Number: 3
		 *Step Name: Step 3: check the message of the Like notification
		 *Step Description:
			- Check notification message in the notifications list and View All page
		 *Input Data:
		 *Expected Outcome:
			- The notification message is :
				LAST_AVATAR
				$USER_LIST like your activity
				$ACTIVITY
				$DATE
			Where :
				- $LAST_AVATAR is the thumbnail of User C
				- $USER_LIST is User B, User C
				- $ACTIVITY is the activity message/title
				- $DATE is the date of the last notification of User C*/
		intranetNotification.goToAllNotification();
		$(byText(activity)).parent().parent().parent().find(byText(username3+" "+username3)).should(Condition.exist);
		manageLogInOut.signIn(DATA_USER1,"gtngtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);
		addUsers.deleteUser(username2);
		addUsers.deleteUser(username3);
	}

	/**
	 *<li> Case ID:122995.</li>
	 *<li> Test Case Name: Mention Intranet notifications (in activity message).</li>
	 * @throws AWTException
	 */
	@Test
	public  void test09_MentionIntranetNotification_InactivityMessage() throws AWTException {
		String activity = "activity" + getRandomNumber();

		String username1 = "usernamea" + getRandomString();
		String email1 = username1+ "@test.com";
		String username2 = "usernameb" + getRandomString();
		String email2 = username2+ "@test.com";
		String password="123456";

		info("Add user");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		manageLogInOut.signIn(username1, password);

		/*Precondition:
		 	- User A has mentioned User B directly in the activity message*/

		info ("Connect with user");
		homePagePlatform.goToConnections();
		connectionsManagement.connectToAUser(username2);

		info("Enable the notifications");
		navigationToolbar.goToMyNotifications();
		myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Mention_intranet);

		info("User accepts Request notification and mention John in activity");
		manageLogInOut.signIn(username2, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		homePagePlatform.goToHomePage();
		activityStream.mentionUserActivity(username1, activity);
		activityStream.checkActivity(activity);

		/*Step Number: 1
		 *Step Name: Step 1:
		 *Step Description:
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data:
		 *Expected Outcome:
			- The Mention notification is displayed in the list
			- The notification message is : $AVATAR
				$USER has mentioned you $ACTIVITY
				$DATE
			Where :
				- $AVATAR is the thumbnail of User A
				- $USER is User A
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the notification*/
		info ("Check Mention Intranet Notification in notification list");
		manageLogInOut.signIn(username1,password);
		ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.visible, Configuration.timeout);
		ELEMENT_ALERT_NOTIFICATION.click();
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2+" "+username2)).parent().shouldHave(text(username2+" "+username2+" has mentioned you."));

		/*Step Number: 2
		 *Step Name: Step 2:
		 *Step Description:
			- Click the notification message
		 *Input Data:
		 *Expected Outcome:
			- The activity is displayed in the activity viewer with all comments expanded.*/
		info("check notification in activity viewer");
		$(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username2+" "+username2)).parent().click();
		$(byClassName("uiActivityLoader")).find(byText(username1+" "+username1)).parent().shouldHave(Condition.text(username1+" "+username1+" "+activity));
		$(byClassName("uiActivityLoader")).find(byText(username1+" "+username1)).parent().parent().parent().find(byText(username2+" "+username2)).should(exist);
		/*Step Number: 3
		 *Step Name: Step 3:
		 *Step Description:
			- Click [View All]
		 *Input Data:
		 *Expected Outcome:
			- The activity is displayed in View All page
			- The notification message is : $AVATAR
				$USER has mentioned you $ACTIVITY
				$DATE
			Where :
				- $AVATAR is the thumbnail of User A
				- $USER is User A
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the notification.*/
		info("Check notification in view all");
		refresh();
		navigationToolbar.goToIntranetNotification();
		intranetNotification.goToAllNotification();
		$(byId("UIIntranetNotificationsPortlet")).find(byText(username1+" "+username1)).parent().shouldHave(Condition.text(username1+" "+username1+" "+activity));
		$(byId("UIIntranetNotificationsPortlet")).find(byText(username1+" "+username1)).parent().parent().parent().find(byText(username2+" "+username2)).should(exist);
		manageLogInOut.signIn(DATA_USER1,"gtngtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);
		addUsers.deleteUser(username2);

	}
}
