package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


public class Plf_Integration extends Plf_TestConfig  {

	/**
	 *<li> Case ID:120872.</li>
	 *<li> Test Case Name: Check Home page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CheckHomePage() {
		info("Test 1: Check Home page");
		/*Step Number: 1
		 *Step Name: Step 1: Show Intranet Home page
		 *Step Description: 
			- Login intranet site by root
		 *Input Data: 

		 *Expected Outcome: 
			Home page is show properly, inlcuding activity stream at the center, gadgets that are well displayed at the right*/ 
		info("Verify that Home page is shown");
		waitForAndGetElement(hp.ELEMENT_PLF_HOMEPAGE_DISPLAY,3000,0);
		info("Verify that Activity stream is shown on the home page");
		waitForAndGetElement(hp.ELEMENT_PLF_HOMEPAGE_ACTIVITY_PORTLET,3000,0);
		info("Verify that Gadgets is shown on right of the page");
		waitForAndGetElement(hp.ELEMENT_PLF_HOMEPAGE_GADGET_PORTLET,3000,0);
	}

	/**
	 *<li> Case ID:120873.</li>
	 *<li> Test Case Name: Check IDE.</li>
	 *<li> Pre-Condition: The package tested is the Express/Enterprise one (where IDE is packaged)</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CheckIDE() {
		info("Test 2: Check IDE");
		/*Step Number: 1
		 *Step Name: Step 1: Check IDE page
		 *Step Description: 
			- Login and go to intranet home page
			- Move mouse over the Setup Menu icon on the top
			-right, click on IDE
		 *Input Data: 

		 *Expected Outcome: 
			There's IDE page*/
		info("Go to IDE page");
		navToolBar.goToIDE();
		info("Verify that IDE page is shown");
		waitForAndGetElement(ide.ELEMENT_PLF_IDE_DISPLAY);
		waitForAndGetElement(ide.ELEMENT_PLF_IDE_WORKSPACE,3000,0);
		/*Step number: 2
		 *Step Name: Step 2: Check showing IDE page
		 *Step Description: 
			Check look of IDE page
		 *Input Data: 

		 *Expected Outcome: 
			IDE page is shown correctly, dev-monit workspace is selected as default*/ 
		info("Verify that dev-monit workspace is shown as default");
		waitForAndGetElement(ide.ELEMENT_PLF_IDE_FOLDER,3000,0);
		
	}

	/**
	 *<li> Case ID:120874.</li>
	 *<li> Test Case Name: Install CMIS Expert gadget.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_InstallCMISExpertGadget() {
		info("Test 3: Install CMIS Expert gadget");
		
		int index = remoteGadData.getRandomIndexByType(1);
		String title = remoteGadData.newTitle.get(index);
		String link = remoteGadData.newLinks.get(index);
		String page = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		int index1 = appGateData.getRandomIndexByType(3);
		String titleApp = appGateData.newTitle.get(index1);
		String nameGatein = appGateData.newName.get(index1);
		
		/*Step Number: 1
		 *Step Name: Step 1: Open applications page
		 *Step Description: 
			- Login and go to intranet home page
			- Move mouse over the Setup Menu icon on the top
			-right and select Applications
		 *Input Data: 

		 *Expected Outcome: 
			Application registry form is shown*/
		info("Go to Application registry page");
		navToolBar.goToApplication();

		/*Step number: 2
		 *Step Name: Step 2: Add gadget
		 *Step Description: 
			- Click Gadgets and Add a remote gadget. Enter URL given bellow and click Add.
		 *Input Data: 

		 *Expected Outcome: 
			New Gadget is added*/

		info("Add a remote gadget");
		appHP.goToGadgetPage();
		gadMag.addRemoteGadget(link);
		info("Verify that new gadget is added");
		waitForAndGetElement(gadMag.ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}",title));
		
		/*Step number: 3
		 *Step Name: Step 3: Add the gadget to Gadgets category
		 *Step Description: 
			- Click on "Click here to add into categories"
		 *Input Data: 

		 *Expected Outcome: 
			This gadget is added to the Gadget category.*/
        info("Add the gadget into admin category");
        gadMag.addIntoCategory("Administration");
        info("Verify that the gadget is added to Administration category");
        waitForAndGetElement(gadMag.ELEMENT_REMOTE_GADGET_INFO_CATEGORY.replace("${category}","Administration"));
		
		/*Step number: 4
		 *Step Name: Step 4: Import application
		 *Step Description: 
			- Go to Categories and do Import Applications
		 *Input Data: 

		 *Expected Outcome: 
			Applications are imported & listed in left pane*/
         info("Go to Application management");
         appHP.goToManageApplication();
         info("Add an application to a category");
         appHP.addApplicationToCategory("Administration",titleApp,titleApp);
         info("Verify that application is added and listed in left panel");
         waitForAndGetElement(appHP.ELEMENT_LEFT_PANEL_APPLICATION_NAME.replace("${category}","Administration").replace("${application}",titleApp));
         
		/*Step number: 5
		 *Step Name: Step 5: Add page with CMIS
		 *Step Description: 
			- Go to Edit Page orAdd page
			- Choose the path for new page
			- Input CMISexpert for page name
			- At step 3 of Add page wizard, drag & drop CMIS Expert gadget portlet into page
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			New page with CMIS is added & well displayed*/ 
         hp.goToHomePage();
         navToolBar.goToAddPage();
 		 pagCW.inputPageInfoStep1(page, true, "English",page, true,false);
 		 click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
 		 click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
 		 pagCW.addApplication(pagCW.ELEMENT_APPLICATION_ADMINISTRATION_TAB,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",nameGatein));
 		 pagCW.addApplication(pagCW.ELEMENT_APPLICATION_ADMINISTRATION_TAB,pagCW.ELEMENT_APPLICATION_REMOTE_GADGET.replace("${name}",title));
 		 click(pagCW.ELEMENT_PAGE_FINISH_BTN);
 		 Utils.pause(2000);
 		 info("Verify that CMIS is shown on new page");
 		 navToolBar.goToEditLayout();
 		 waitForAndGetElement(pagCW.ELEMENT_PAGEEDITOR_CONTENT.replace("${name}", title));
 		 waitForAndGetElement(pagCW.ELEMENT_PAGEEDITOR_CONTENT.replace("${name}", titleApp));
 		 click(pagCW.ELEMENT_PAGE_ABORT_BTN);
 		 
 		 info("Delete node on navigation");
 		 navToolBar.goToPotalSites();
		 magSite.goToEditNavigation("intranet");
		 navMag.deleteNode(page);
		 
		 info("Delete created page");
		 navToolBar.goToPotalPages();
		 pagMang.deletePage(page,"");
			
 		 info("Delete Gadget");
		 navToolBar.goToApplication();
		 appHP.goToGadgetPage();
		 gadMag.deleteGadget(title);
		 
		 info("Delete application");
		 appHP.goToManageApplication();
		 appHP.deleteApplication(titleApp);
	  }
	}