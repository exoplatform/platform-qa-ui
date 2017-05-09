package org.exoplatform.platform.qa.ui.social.smoke;


import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.UserPageBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class SOC_People_MyConnections extends Base{
	NavigationToolbar navigationToolbar;
	AddUsers addUsers;
	ManageLogInOut manageLogInOut;
	HomePagePlatform homePagePlatform;
	ConnectionsManagement connectionsManagement;


	/**
	 *<li> Case ID:122731.</li>
	 *<li> Test Case Name: Check request pending after inviting an user.</li>
	 *<li> Case ID:122713.</li>
	 *<li> Test Case Name: Accept the invitation.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_06_AcceptTheInvitation() {
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

		info("Test 6: Check request pending after inviting an user");
		/*Step Number: 1
		 *Step Name: Step 1: Go to My Connection page
		 *Step Description: 
			- Login as User1
			- Go to My Connections page, Everyone tab.
		 *Input Data: 

		 *Expected Outcome: 
			- Show content of Connections page*/

		/*Step number: 2
		 *Step Name: Step 2: Invite user
		 *Step Description: 
			- Select user User2 who has no connection with User1.
			- Click on [Connect] button
		 *Input Data: 

		 *Expected Outcome: 
			- Invitation is sent to User2 successfully. 
			- User2 is listed in Request Pending list*/
		info("Click on Connections on the left panel");
		homePagePlatform.goToConnections();

		info("Click on Connect button to invite about 2 users");
		connectionsManagement.connectToAUser(username2);
		connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.PENDING);
		waitForAndGetElement(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username2),2000,1);

		info("Test 1: Accept the invitation");
		/*Step number: 3
		 *Step Name: Step 3: Ignore a request
		 *Step Description: 
			- Login as User2
			- Go to My Connections page, Select Requests Received tab
			- Click on button [Confirm]
		 *Input Data: 

		 *Expected Outcome: 
			- User1 is listed in Requests Received list of User2. 
			- There are [Confirm] and [Ignore]
			- After clicking on [Confirm] button, User1 will be removed from Requests Received tab of User2.*/
		manageLogInOut.signIn(username2, password);
		homePagePlatform.goToConnections();
		connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.RECEIVE);
		connectionsManagement.acceptAConnection(username1);
	}

	/**
	 *<li> Case ID:122735.</li>
	 *<li> Test Case Name: Cancel a invitation request.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CancelAInvitationRequest() {
		info("Test 2: Cancel a invitation request");
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
		/*Step Number: 1
		 *Step Name: Step 1: Go to Connections page
		 *Step Description: 
			- Login as User1
			- Go to Connections page
		 *Input Data: 

		 *Expected Outcome: 
			Connections page is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Invite User2
		 *Step Description: 
			- In Everyone tab
			- Click on [Connect] button to connect with User2
		 *Input Data: 

		 *Expected Outcome: 
			- Invitation is sent to User2*/
		info("Click on Connections on the left panel");
		homePagePlatform.goToConnections();

		info("Click on Connect button to invite about 2 users");
		connectionsManagement.connectToAUser(username2);
		connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.PENDING);
		waitForAndGetElement(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username2),2000,1);

		/*Step number: 3
		 *Step Name: Step 3: Cancel Request
		 *Step Description: 
			- In Pending Request tab, click [Cancel Request] button
		 *Input Data: 

		 *Expected Outcome: 
			- The request is cancelled, [Cancel Request] button is changed to [Connect] button*/
		connectionsManagement.cancelConnection(username2);
	}


}