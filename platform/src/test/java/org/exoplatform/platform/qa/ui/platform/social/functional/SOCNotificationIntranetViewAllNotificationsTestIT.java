package org.exoplatform.platform.qa.ui.platform.social.functional;

import org.exoplatform.platform.qa.ui.commons.Base;

public class SOCNotificationIntranetViewAllNotificationsTestIT extends Base {


    /**
     *<li> Case ID:125167.</li>
     *<li> Test Case Name: Check View All page.</li>
     *<li> Pre-Condition: The user has several notifications
     - Like
     - Comment
     - Connection request
     - Post in My stream</li>
     *<li> Post-Condition: </li>
     */
    @Test
    public  void test01_CheckViewAllPage() {
        info("Test 1: Check View All page");
        String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
        String email1 = username1+"@gmail.com";
        String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
        String email2 = username2+"@gmail.com";
        String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
        String email3 = username3+"@gmail.com";

        info("Add new user");
        navTool.goToAddUser();
        addUserPage.addUser(username1, password, email1, username1, username1);
        addUserPage.addUser(username2, password, email2, username2, username2);
        addUserPage.addUser(username3, password, email3, username3, username3);
        magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All page
		*Step Description:
			- Login
			- Click the notifications icon in the top navigation
			- Click View All button
		*Input Data:

		*Expected Outcome:
			- The user is redirected to the View All page*/
        info("goto My notification");
        navTool.goToMyNotifications();
        myNoti.enableNotification(myNotiType.AS_Comment_intranet);
        myNoti.enableNotification(myNotiType.AS_Like_intranet);
        myNoti.enableNotification(myNotiType.AS_Post_intranet);

        info("User A sent a connection request to User B");
        Utils.pause(3000);
        hp.goToConnections();
        connMag.connectToAUser(username2);

        info("User A add an activity");
        String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
        hp.goToHomePage();
        Utils.pause(3000);
        hpAct.addActivity(activity,null);
        hpAct.checkActivity(activity);

        info("User B login");
        magAc.signIn(username2, password);

        info("User A and User B are connected");
        Utils.pause(3000);
        navTool.goToIntranetNotification();
        intraNot.acceptRqConnection(username1);

        String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
        hp.goToHomePage();
        Utils.pause(3000);
        info("Like UserA's activity");
        hpAct.likeActivity(activity);
        info("Add a comment to UserA's activity");
        hpAct.addCommentUsingJavascript(activity, comment);

        info("Add comment to Comment list");
        comments.add(comment);

        info("Go to UserA's profile page");
        hp.goToFriendProfilePage(username1);
        userProPage.goToActivity();

        info("User B add an activity in UserA's activity stream");
        String myActivity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
        hpAct.addActivity(myActivity,null);
        hpAct.checkActivity(myActivity);

        info("User C login");
        magAc.signIn(username3, password);
        Utils.pause(3000);

        info("User C sent a connection request to User A");
        Utils.pause(3000);
        hp.goToConnections();
        connMag.connectToAUser(username1);

        info("User A login");
        magAc.signIn(username1, password);
        Utils.pause(3000);

        info("The user is redirected to the View All page");
        navTool.goToIntranetNotification();
        intraNot.goToAllNotification();


		/*Step number: 2
		*Step Name: Step 2 : Check title of the page
		*Step Description:
			- Check title of the View All page
		*Input Data:

		*Expected Outcome:
			- The title of the page isAll Notifications*/

        String titlePage=driver.getTitle();
        if(titlePage.equals("All Notifications")==true) assert true;
        else assert false:"the page's title is not correct";

		/*Step number: 3
		*Step Name: Step 3 : Check content of the View All page
		*Step Description:
			- Check the content of the list
		*Input Data:

		*Expected Outcome:
			The page displays the following notifications
			- Like
			- Comment
			- Connection request
			- Post in My stream*/
        String likeStatus=notiIntranetData.getContentByArrayTypeRandom(6);
        String commentStatus=notiIntranetData.getNotiContent(0);
        String postStatus=notiIntranetData.getContentByArrayTypeRandom(8);
        String connectStatus=notiIntranetData.getContentByArrayTypeRandom(2);
        intraNot.checkStatus(likeStatus,username2);
        intraNot.checkStatus(commentStatus,username2);
        intraNot.checkStatus(postStatus,username2);
        intraNot.checkStatus(connectStatus,username2);


    }