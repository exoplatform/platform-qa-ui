package org.exoplatform.platform.qa.ui.selenium.platform;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformPermissionLocator.*;

public class PlatformPermission  {
	private final TestBase testBase;
	private ElementEventTestBase evt;

	public PlatformPermission(TestBase testBase){
		this.testBase = testBase;
		this.evt = testBase.getElementEventTestBase();
	}

	/**
	 * Search user by key search
	 * @param keySearch
	 * @param type
	 * 				type of search
	 * 				1: search by user name
	 * 				2: search by first name
	 * 				3: search by last name
	 * 				4: search by email
	 * 				default: search by user name
	 * 
	 */
	public void searchUser(String keySearch, int type){
		evt.type(ELEMENT_SEARCH_USER_INPUT, keySearch, true);
		switch(type){
		case 1: //search by user name
			evt.select(ELEMENT_SELECT_SEARCH, "User Name");
			break;
		case 2: //search by first name
			evt.select(ELEMENT_SELECT_SEARCH, "First Name");
			break;
		case 3: //search by last name
			evt.select(ELEMENT_SELECT_SEARCH, "Last Name");
			break;
		case 4: //search by email
			evt.select(ELEMENT_SELECT_SEARCH, "Email");
			break;
		default: //search by user name
			evt.select(ELEMENT_SELECT_SEARCH, "User Name");
			break;

		}
		Utils.pause(500);
		evt.click(ELEMENT_QUICK_SEARCH_BUTTON);
		//clickByJavascript(ELEMENT_QUICK_SEARCH_BUTTON);
		evt.waitForAndGetElement((ELEMENT_USER_CHECKBOX.replace("${user}", keySearch)),5000,1,2);
	}

	/**
	 * Select user permission
	 * @param user
	 * 				list of user: john/mary
	 * @param type
	 * 				type of search
	 * 				1: search by user name
	 * 				2: search by first name
	 * 				3: search by last name
	 * 				4: search by email
	 * 				default: search by user name
	 */
	public void selectUserPermission(String user, int type){	
		String[] temp = user.split("/");
		if (temp.length > 0){
			for (int i = 0; i < temp.length; i ++){
				if(evt.waitForAndGetElement(ELEMENT_SELECT_USER_ICON, 5000,0)!=null)
					evt.click(ELEMENT_SELECT_USER_ICON);
				searchUser(temp[i], type);
				Utils.pause(1000);
				//check((ELEMENT_USER_CHECKBOX.replace("${user}", temp[i])), 2);
				evt.clickByJavascript(ELEMENT_USER_CHECKBOX.replace("${user}", temp[i]), 2);
				evt.click(ELEMENT_ADD_USERS_BUTTON);
			}
		}
	}
	/**
	 * Check display of user selector
	 * @param user
	 * @param isPresent
	 */
	public void checkUserSelector(String user,boolean isPresent){
		if(isPresent)
			evt.waitForAndGetElement(ELEMENT_USER_LIST.replace("${user}", user));
		else
			evt.waitForElementNotPresent(ELEMENT_USER_LIST.replace("${user}", user));
	}
	/**
	 * Select group permission
	 * @param grouppath
	 * 					path group: (Ex: Organization/Employees)
	 */
	public void selectGroupPermission(String grouppath){
		String[] temp;
		evt.click(ELEMENT_SELECT_GROUP_ICON);
		evt.waitForAndGetElement(ELEMENT_SELECT_GROUP_POPUP);
		temp = grouppath.split("/");
		for (int i = 0; i < temp.length; i ++){
			evt.click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
		}
		if(evt.waitForAndGetElement(ELEMENT_SELECT_THIS_GROUP, testBase.getDefaultTimeout(),0) != null)
			evt.click(ELEMENT_SELECT_THIS_GROUP);
		evt.waitForElementNotPresent(ELEMENT_SELECT_GROUP_POPUP);
	}

	/**
	 * Select group membership
	 * @param groupPath
	 * 					path group: (Ex: Organization/Employees)
	 * @param membership
	 * 					membership: (Ex:  author)
	 */
	public void selectGroupMembership(String groupPath, String membership){
		String[] temp;
		evt.click(ELEMENT_SELECT_MEMBERSHIP_ICON);
		evt.waitForAndGetElement(ELEMENT_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			evt.click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
		}
		evt.click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership));
		evt.waitForElementNotPresent(ELEMENT_SELECT_MEMBERSHIP_POPUP);
	}
	/**
	 * Check search result
	 * @param keySearch
	 * @param isPresent
	 * 					true if it has result
	 * 					false if it doesn't have result
	 */
	public void searchUser(String keySearch, boolean isPresent){
		evt.type(ELEMENT_SEARCH_USER_INPUT, keySearch, true);
		Utils.pause(500);
		evt.click(ELEMENT_QUICK_SEARCH_BUTTON);
		if(isPresent)
			evt.waitForAndGetElement((ELEMENT_USER_CHECKBOX.replace("${user}", keySearch)),5000,1,2);
		else
			evt.waitForElementNotPresent((ELEMENT_USER_CHECKBOX.replace("${user}", keySearch)),5000,1,2);
	}
}