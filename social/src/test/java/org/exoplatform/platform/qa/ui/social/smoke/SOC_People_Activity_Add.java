package org.exoplatform.platform.qa.ui.social.smoke;


import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.UserPageBase;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_AUTHOR_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class SOC_People_Activity_Add extends Base{
	NavigationToolbar navigationToolbar;
	AddUsers addUsers;
	ManageLogInOut manageLogInOut;
	HomePagePlatform homePagePlatform;
	ConnectionsManagement connectionsManagement;
	ActivityStream activityStream;
	UserPageBase userPageBase;


	/**
	 *<li> Case ID:122785.</li>
	 *<li> Test Case Name: Add new your activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	@Smoke
	@Tag("smoke")
	public  void test03_AddNewYourActivity() {
		/*Create data test*/
		String username1 = "usernamea" + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = "usernameb" + getRandomString();
		String email2 = username2+"@gmail.com";
		String password="123456";
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		manageLogInOut.signIn(username1,password);
		Utils.pause(3000);
		
		
		info("Click on Connections on the left panel");
		homePagePlatform.goToConnections();
		
		info("Access people list, invite an user");
		connectionsManagement.connectToAUser(username2);
		
		info("Invited user accept invitation");
		manageLogInOut.signIn(username2, password);
		homePagePlatform.goToConnections();
		connectionsManagement.acceptAConnection(username1);
		info("Verify after accept");
		connectionsManagement.verifyConnection(username1, true);
		
		info("Test 3: Add new your activity");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Go to my profile page
		 *Input Data: 
			- Sign in system
			- Select Activities page on User Toolbar portlet in the upper right corner of the screen
		 *Expected Outcome: 
			- Show content of People page. It focus on activity list*/
		manageLogInOut.signIn(username1,password);
		navigationToolbar.goToMyActivities();
		
		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Add new activities
		 *Input Data: 
			- Select activity
			- Enter some text into text box
			- Click on Add button
		 *Expected Outcome: 
			Add an activity successfully:
			- This activity is added into users activities list.User who is in your contact, can view your active on his/her activity list*/ 
		String activity1 = "activity1"+getRandomNumber();
		activityStream.addActivity(activity1, "");

		manageLogInOut.signIn(username2, password);
		homePagePlatform.goToConnections();
		click(ELEMENT_ALL_CONNECTIONS_TAB);
		connectionsManagement.searchPeople(username1,null,null,null);
		click(ELEMENT_CONNECTION_USER_NAME.replace("${user}",username1));
		userPageBase.goToActivityTab();
		waitForAndGetElement(ELEMENT_ACTIVITY_AUTHOR_ACTIVITY.replace("${activityText}", activity1));
	}


}