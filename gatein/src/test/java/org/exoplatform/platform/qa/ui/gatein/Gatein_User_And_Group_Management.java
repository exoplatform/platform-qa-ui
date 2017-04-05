package org.exoplatform.platform.qa.ui.gatein;


import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.gatein.pageobject.MyDashBoard;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PageCreationWizard;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManagePages;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * @date March 10 2015
 * @author tult
 */

public class Gatein_User_And_Group_Management extends Base{
	
	NavigationToolbar navigationToolbar;
	UserAndGroupManagement userandgroupmanagement;
	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");

		navigationToolbar = new NavigationToolbar(this);
		userandgroupmanagement=new UserAndGroupManagement(this);}
	
	/**
	*<li> Case ID:123072.</li>
	*<li> Test Case Name: Add new group.</li>
	*<li> Case ID:123094.</li>
	*<li> Test Case Name: Edit Group.</li>
	*<li> Case ID:123073.</li>
	*<li> Test Case Name: Add user into group.</li>
	*<li> Case ID:123095.</li>
	*<li> Test Case Name: Delete group.</li>
	*/
	@Test
	@Smoke
	@Tag("smoke")
	public void test01_02_03_04_AddEditDeleteGroupAddUsersToGroup(){
		String groupName = "groupName"+getRandomNumber();
		String groupLabel = "groupLabel"+getRandomNumber();
		String groupDesc = "groupDesc"+getRandomNumber();
		
		String newLabel = "newLabel"+getRandomNumber();
		String newDesc = "newDesc"+getRandomNumber();
		
		String membership1 = "author";
		String membership2 = "editor";
		/*Step Number: 1
		*Step Name: Step 1: Add new Group
		*Step Description: 
			- Go to Group/Organization/ User and group management
			- Click Add new group icon
			- Input some filed required and click Save
		*Input Data: 
		*Expected Outcome: 
			- Add group successfully*/
		info("Test Case 01: Add new group");
			navigationToolbar.goToUsersAndGroupsManagement();
			userandgroupmanagement.goToGroupTab();
			userandgroupmanagement.addGroup(groupName, groupLabel, groupDesc, true);
		
		/*Step Number: 2
		*Step Name: Step 2: Edit Group
		*Step Description: 
			- Go to Administration/Users/Groups and Roles
			- Select a group and click [Edit selected group]
			- Change some fields and click Save
		*Input Data: 
		*Expected Outcome: 
			- The group is updated with the change value*/
		info("Test Case 02: Edit group");
			userandgroupmanagement.editGroup(groupLabel, null, newLabel, newDesc, true);
		
		/*Step Number: 3
		*Step Name: Step 3: Add user to Group
		*Step Description: 
			- Choose Group management form
			- Select a group from list
			- Input or search user from list
			- Click Add button
			- Choose membership type and Save
		*Input Data: 
		*Expected Outcome: 
			- Add user into group successfully*/
		info("Test Case 03: Add some users into group");
			userandgroupmanagement.addUsersToGroup("mary", membership1, true, true);
			userandgroupmanagement.addUsersToGroup("demo", membership2, false, true);
		
		/*Step Number: 4
		*Step Name: Step 4: Delete Group
		*Step Description: 
			- Go to Group/Organization/ User and group management
			- Select a group and click [Delete selected group] icon
			- Click Save
		*Input Data: 
		*Expected Outcome: 
			- The group is removed from the list*/
		info("Test Case 04: Delete group");
			userandgroupmanagement.deleteGroup(groupLabel, true);
	}
	
	

	
	/**
	*<li> Case ID:123098.</li>
	*<li> Test Case Name: Edit User information.</li>
	*<li> Case ID:123071.</li>
	*<li> Test Case Name: Search user.</li>
	*<li> Case ID:123070.</li>
	*<li> Test Case Name: Delete user.</li>
	*/
	/*@Test
	public void test08_09_10_EditSearchDeleteUser(){
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		
		String newFirstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String newLastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String newDisplayName = userInfoData.getDisplayNameByIndex(5)+getRandomString();
		String newEmail = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String searchLastName = userSearchOptionData.getUserSearchOptionByIndex(1);
		String searchFirstName = userSearchOptionData.getUserSearchOptionByIndex(2);
		String searchEmail = userSearchOptionData.getUserSearchOptionByIndex(3);
		
		String searchUser1 = userInfoData.getLastNameByIndex(2);
		String searchUser2 = userInfoData.getFirstNameByIndex(4);
		
		navToolBar.goToAddUser();
		info("Create new user");
		addUserPage.addUser(username, password, email, firstName, lastName);
		
		/*Step Number: 1
		*Step Name: Step 1: Edit User information
		*Step Description: 
			- Go to Group/Organization/ User and group management
			- Select a user and click [Edit user info] icon
			- Change some fields and click Save
		*Input Data: 
		*Expected Outcome: 
			- Users list is shown properly
			- User is updated with the change value*/
		/*info("Test Case 08: Edit user information");
		navToolBar.goToUsersAndGroupsManagement();
		if(waitForAndGetElement(userAndGroup.ELEMENT_USER_MANAGEMENT_ACTIVE_TAB, 3000, 0) == null)
			click(userAndGroup.ELEMENT_USER_MANAGEMENT_TAB);
		userAndGroup.goToEditUserInfo(username);
		userAndGroup.editUserInfo_AccountTab(newFirstName, newLastName, newDisplayName, newEmail);
		waitForTextPresent(newFirstName);
		
		
		/*Step Number: 2
		*Step Name: Step 2: Search User
		*Step Description: 
			- Search user by:
  				+ User name
  				+ Last Name
  				+ First Name
  				+ Email
		*Input Data: 
		*Expected Outcome: 
			- The results are displayed matching with the search keyword*/
		/*info("Test Case 09: Search user follow username, last name, first name, email");
		userAndGroup.searchUser(DATA_USER1, searchUserName);
		userAndGroup.searchUser(searchUser1, searchLastName);
		userAndGroup.searchUser(searchUser2, searchFirstName);
		userAndGroup.searchUser(newEmail, searchEmail);
		
		
		/*Step Number: 3
		*Step Name: Step 3: Delete User
		*Step Description: 
			- Go to Group/Organization/ User and group management
			- Select a user in list and click [Delete user] icon
		*Input Data: 
		*Expected Outcome: 
			- - The user will be removed from the list*/
		/*info("Test Case 10: Delete user");
		userAndGroup.deleteUser(username);
	}*/
	
}
