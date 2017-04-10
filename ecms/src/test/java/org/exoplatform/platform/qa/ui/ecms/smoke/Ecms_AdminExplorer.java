package org.exoplatform.platform.qa.ui.ecms.smoke;


import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


/**
 * @author rosso
 *
 */
public class Ecms_AdminExplorer extends Base{
	ContentAdministration contentAdministration;
	NavigationToolbar navigationToolbar;
	TestBase testBase;
	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");

		contentAdministration=new ContentAdministration(this);
		navigationToolbar=new NavigationToolbar(this);

	}
	/**
	 *<li> Case ID:116587.</li>
	 *<li> Test Case Name: Add Drive.</li>
	 *<li> Case ID:116623.</li>
	 *<li> Test Case Name: Edit Drive.</li>
	 *<li> Case ID:116624.</li>
	 *<li> Test Case Name: Delete Drive.</li>
	 */
	@Test
	@Smoke
	@Tag("smoke")
	public  void test01_02_03_Add_Edit_Delete_Drive() {
		info("Test 01: Add Drive");
		info("Get data test");
		String title = "title"+getRandomNumber();
		String permission = "any";
		ContentAdministration.specificView[] view ={ContentAdministration.specificView.ADMIN};
		ContentAdministration.specificView[] newView = {ContentAdministration.specificView.WEB};
		String [] newV={"Web"};
		info("Finished getting data test");
		navigationToolbar.goToContentAdministration();
		contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
		contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DRIVES);
		contentAdministration.addDrives(title, permission, view);
		contentAdministration.editDrives(title, newView);
		waitForAndGetElement(By.xpath(ELEMENT_ECM_EXPLORER_DRIVES_VIEW_OF_VIEWS_LIST.replace("{$name}",title).replace("{$view}",newV[0])));
		// delete drive
		contentAdministration.deleteDrives(title);
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1:Add Drive
		 *Input Data: 
			- Go to Content Administration/Explorer/ Drives
			- Click on Add Drive button 
			- Put value in required fields
			- Click Save button
		 *Expected Outcome: 
			- A drive is created successfully, when go to Site Explorer , you can see new drive*/ 

	}

	/**
	 *<li> Case ID:116627.</li>
	 *<li> Test Case Name: Add a View.</li>
	 *<li> Case ID:116625.</li>
	 *<li> Test Case Name: Edit a View.</li>
	 *<li> Case ID:116600.</li>
	 *<li> Test Case Name: View a View.</li>
	 *<li> Case ID:116626.</li>
	 *<li> Test Case Name: Delete a View.</li>
	 */
	@Test
	public  void test04_05_06_07_Add_Edit_Delete_View_AView() {
		info("Test 02 : Add, edit, show and delete a View");
		String title = "title"+getRandomNumber();
		String tabName = "tabName"+getRandomNumber();
		String oldPermission = "fqa";
		String[] tab={"Add Category"};
		String permission = "john";

		navigationToolbar.goToContentAdministration();
		contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
		testBase.getExoWebDriver().getWebDriver().navigate().refresh();
		contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
		info("add a view");
		contentAdministration.addView(title, tabName, tab, oldPermission);
		info("show a view");
		click(By.xpath(ELEMENT_ECM_EXPLORER_VIEW_SHOW_A_VIEW_LIST.replace("{$name}",title)));
		waitForAndGetElement(By.xpath(ELEMENT_ECM_EXPLORER_NAME_VIEW_SHOW_VIEW.replace("{$name}",title)));
		click(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM);
		waitForAndGetElement(By.xpath(ELEMENT_ECM_EXPLORER_TAB_ICONS_LIST_SHOW_VIEW.replace("{$tab}",tab[0])));
		click(ELEMENT_ECM_EXPLORER_CLOSE_VIEW_MODE);
		info("edit a view");
		contentAdministration.editViewPermissionUser(title, "Fqa", permission);
		waitForAndGetElement(By.xpath(ELEMENT_ECM_EXPLORER_VIEW_PERMISSIONS_LIST.replace("{$name}",title).replace("{$permission}","John")));
		info("delete a view");
		contentAdministration.deleteView(title);
		
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1:Add a View
		 *Input Data: 
			- Go to Content Administration/ Explorer/ Views 
			- Click Add View button
			- Put value in required field
			- Click Add Tab, fill name and tick on action for tabs
			- Click Save button
		 *Expected Outcome: 
			- A new view is created successfully*/ 

	}
	


}