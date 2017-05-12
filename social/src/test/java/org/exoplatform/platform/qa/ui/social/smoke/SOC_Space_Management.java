package org.exoplatform.platform.qa.ui.social.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("smoke")
@Tag("social")
public class SOC_Space_Management extends Base {
	NavigationToolbar navigationToolbar;
	AddUsers addUsers;
	ManageLogInOut manageLogInOut;
	HomePagePlatform homePagePlatform;
	SpaceManagement spaceManagement;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		navigationToolbar=new NavigationToolbar(this);
		homePagePlatform =new HomePagePlatform(this);
		addUsers=new AddUsers(this);
		manageLogInOut=new ManageLogInOut(this);
		spaceManagement=new SpaceManagement(this);
	}
	/**
	 *<li> Case ID:121897.</li>
	 *<li> Test Case Name: Create a new Space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CreateNewSpace() {
		info("Test 04: Create a new Space");
		String space = "space"+getRandomNumber();
		String username1 = "usernamea" + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = "usernameb" + getRandomString();
		String email2 = username2+ "@gmail.com";
		String password="123456";
		
		info("Add user");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		manageLogInOut.signIn(username1, password);
		
		/*Step Number: 1
		 *Step Name: Step 1: Create a new Space
		 *Step Description: 
			- Login Intranet
			- Click on My space on Admin bar
			- Click Add new space and input valid value into create space form
			- Click on Create button

		 *Input Data: 

		 *Expected Outcome: 
			- New space is displayed on My space list of user and Publics space list of other user..
		 */ 
		info("Create a space");
		homePagePlatform.goToMySpaces();
		spaceManagement.addNewSpaceSimple(space,space);
		info("Publics space list of other user");
		manageLogInOut.signIn(username2,password);
		homePagePlatform.goToAllSpace();
		spaceManagement.searchSpace(space,"");
		$(byText(space)).waitUntil(Condition.appears, Configuration.timeout);
		manageLogInOut.signIn(username1, password);
		homePagePlatform.goToMySpaces();
		spaceManagement.searchSpace(space,"");
		$(byClassName("tab-content")).find(byText(space)).waitUntil(Condition.appears, Configuration.timeout);
		manageLogInOut.signIn(username1, password);
		homePagePlatform.goToMySpaces();
		spaceManagement.searchSpace(space,"");
		spaceManagement.deleteSpace(space,false);
		manageLogInOut.signIn("root","gtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);
		addUsers.deleteUser(username2);
	}


	/**
	 *<li> Case ID:121912.</li>
	 *<li> Test Case Name: Delete a space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_DeleteASpace() {
		info("Test 06:Delete a space");
		String space = "space"+getRandomNumber();
		String username1 = "usernamea" + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = "usernameb" + getRandomString();
		String email2 = username2+ "@gmail.com";
		String password="123456";
		
		info("Add user");
		navigationToolbar.goToAddUser();
		addUsers.addUser(username1, password, email1, username1, username1);
		addUsers.addUser(username2, password, email2, username2, username2);
		manageLogInOut.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Delete a space
		 *Step Description: 
			- Login Intranet
			- Click on My space on Admin bar
			- Add new space
			- Go to My Space page,  select the space 
			- Click on Delete Space icon
			- Click on OK button to confirm
		 *Input Data: 

		 *Expected Outcome: 
			- Space is removed. It doesn't display on My space list of user and all spaces list of other user.
		 */ 
		info("Create a space");
		homePagePlatform.goToMySpaces();
		spaceManagement.addNewSpaceSimple(space,space);

		info("Delete a Space");
		homePagePlatform.goToMySpaces();
		spaceManagement.searchSpace(space,"");
		spaceManagement.deleteSpace(space,false);

		manageLogInOut.signIn(username2,password);
		homePagePlatform.goToAllSpace();
		spaceManagement.searchSpace(space,"");
		$(byText(space)).shouldNot(Condition.exist);
		manageLogInOut.signIn("root","gtn");
		navigationToolbar.goToManageCommunity();
		addUsers.deleteUser(username1);
		addUsers.deleteUser(username2);

	}


}