package org.exoplatform.platform.qa.ui.platform.ecms;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.sun.jna.platform.win32.Kernel32Util.deleteFile;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.ELEMENT_ECM_EXPLORER_VIEW_EDIT_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class EcmsSEAdminTestIT extends Base{
	NavigationToolbar navigationToolbar;
	SiteExplorerHome siteExplorerHome;
	CreateNewDocument createNewDocument;
	ManageLogInOut manageLogInOut;
	ContentAdministration contentAdministration;
	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
	navigationToolbar=new NavigationToolbar(this);
	siteExplorerHome=new SiteExplorerHome(this);
	createNewDocument=new CreateNewDocument(this);
	contentAdministration=new ContentAdministration(this);
	manageLogInOut=new ManageLogInOut(this);
	manageLogInOut.signInCas(PLFData.DATA_USER1,"gtngtn");
	}

	/**
	 * By QuynhPT
	 *<li> Case ID:116656</li>
	 *<li> Test Case Name: Add Relation</li>
		 * Precondition: If Relations is not available on action bar,
		 * go to Content Administration/ Manage View and edit your current view in use with Relation option ticked
		 * Step Number: 1
		 *Step Description:
			Step 1: Add Relation
		 *Input Data:
			- Add Relation
            - Select a node
            - Click on Relations icon in the action bar
            - Go to Select Relation tab
            - Select document to add relation for document/uploaded file
		 *Expected Outcome:
			- A relation is added for a node.
			*/
	@Test
	public  void test01_AddRelation() {
		info("Test 1: Add Relation");
		info("Create data test");
		String node1 = "node1"+getRandomNumber();
		String node2 = "node2"+getRandomNumber();
		info("Finished data test");
		//Declare a string array
		String[] nameContent={node1};
		
		info("Create content 1");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
		$(byClassName("uiIconEcmsViewWeb")).click();
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(node1, node1);
		createNewDocument.saveAndClose();

		siteExplorerHome.selectNode("documents");
		
		info("Create content 2");
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(node2, node2);
		createNewDocument.saveAndClose();
		siteExplorerHome.selectNode(node2);
		info("Click on More link ");
		executeJavaScript("window.scrollBy(0,-5500)", "");
		$(byClassName("uiIconEcmsViewAdmin")).click();
		$(ELEMENT_ACTIONBAR_MORE).click();
		info("Add relation");
		siteExplorerHome.goToManageRelation();
		siteExplorerHome.addRelation(nameContent,"sites/intranet/documents");
		siteExplorerHome.closeAddRelationPopup();

		info("Select relation tab of SE");
		siteExplorerHome.goToRelationSideBar();
		$(byXpath(ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}",node1))).waitUntil(Condition.visible, Configuration.timeout);
		$(byXpath(ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}",node1))).click();

		info("Verify the file in reference section");
		$(byXpath(ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}",node2))).waitUntil(Condition.visible,Configuration.timeout);
		
		info("Delete all data test");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(node1);
		siteExplorerHome.deleteData(node2);
	}
	/**
	 * By QuynhPT
	 * <li>Case ID:116657.</li>
	 * <li>Test Case Name: Delete Relation</li>
	 */

	@Test
	public  void test02_DeleteRelation() {
		info("Test 02: Delete Relation");

		info("Create data test");
		String node1 = "node1"+getRandomNumber();
		String node2 = "node2"+getRandomNumber();
		info("Finished data test");
		//Declare a string array
		String[] nameContent={node1};

		info("Create content 1");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
		$(byClassName("uiIconEcmsViewWeb")).click();
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(node1, node1);
		createNewDocument.saveAndClose();

		siteExplorerHome.selectNode("documents");

		info("Create content 2");
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(node2, node2);
		createNewDocument.saveAndClose();
		siteExplorerHome.selectNode(node2);
		info("Click on More link ");
		executeJavaScript("window.scrollBy(0,-5500)", "");
		$(byClassName("uiIconEcmsViewAdmin")).click();
		$(ELEMENT_ACTIONBAR_MORE).click();
		info("Add relation");
		siteExplorerHome.goToManageRelation();
		siteExplorerHome.addRelation(nameContent,"sites/intranet/documents");
		siteExplorerHome.deleteRelation(node1.toLowerCase());
		siteExplorerHome.closeAddRelationPopup();
		//Check Relation on left of sideBar
		siteExplorerHome.goToRelationSideBar();
		$(byXpath(ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}",node1))).shouldNot(Condition.exist);
		info("Delete all data test");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(node1);
		siteExplorerHome.deleteData(node2);

	}
	/**
	 *<li> Case ID:116596.</li>
	 *<li> Test Case Name: Show/ Hide Relation</li>
	 */
	@Test
	public  void test03_ShowHideRelation() {
		info("Test 3: Show/Hide Relation");
		info("Create data test");
		String node1 = "node1"+getRandomNumber();
		String node2 = "node2"+getRandomNumber();
		info("Finished data test");
		//Declare a string array
		String[] nameContent={node1};
		info("Create content 1");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
		$(byClassName("uiIconEcmsViewWeb")).click();
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(node1, node1);
		createNewDocument.saveAndClose();
		siteExplorerHome.selectNode("documents");
		info("Create content 2");
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(node2, node2);
		createNewDocument.saveAndClose();
		siteExplorerHome.selectNode(node2);
		info("Click on More link ");
		executeJavaScript("window.scrollBy(0,-5500)", "");
		$(byClassName("uiIconEcmsViewAdmin")).click();
		$(ELEMENT_ACTIONBAR_MORE).click();
		info("Add relation");
		siteExplorerHome.goToManageRelation();
		siteExplorerHome.addRelation(nameContent,"sites/intranet/documents");
		siteExplorerHome.closeAddRelationPopup();
		//Show relation
		siteExplorerHome.goToRelationSideBar();
		$(byXpath(ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}",node1))).waitUntil(Condition.visible,Configuration.timeout);
		//Hide relation
		$(byClassName("uiIconEcmsViewWeb")).click();
		$(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();
		info("Delete all data test");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(node1);
		siteExplorerHome.deleteData(node2);
	}
	/**
	 *<li> Case ID:116578.</li>
	 *<li> Test Case Name: Export a node.</li>
	 *<li> Case ID:116655.</li>
	 *<li> Test Case Name: Import a node</li>
	 Step Number: 1
		 *Step Name: Export a node
		 *Step description:
			- Select one node
			- On action bar, click Export button
			- Choose System View in Format
			- Click on Export button
			- Click Save in the bottom message to export.
		 *Expected Outcome:
			- Node is exported successfully.
		*/
	@Test
	public  void test04_ExportANode() {
		info("Test 4: Export a Node");
		info("Create data test");
		String node1 = "node1"+ getRandomNumber();
		info("Finished data test");
		navigationToolbar.goToContentAdministration();
		contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
		contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
		$(byXpath(ELEMENT_ECM_EXPLORER_VIEW_EDIT_LIST.replace("{$name}","Admin"))).click();
		$(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM).click();
		$(ELEMENT_ECM_EXPLORER_EDIT_ACTION_VIEW_FORM).click();
		$(byId("exportNode")).parent().click();
		if($(byId("exportNode")).is(Condition.not(Condition.selected))){
		$(byId("exportNode")).parent().click();}
		$(ELEMENT_ECM_EXPORER_ACTIONS_POPUP_SAVE_BUTTON).click();
		$(ELEMENT_ECM_EXPLORER_EDIT_VIEWS_SAVE_BUTTON).click();
		info("Add New folder");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
		$(byClassName("uiIconEcmsViewWeb")).click();
		siteExplorerHome.goToAddNewFolder();
		info("Create Folder node");
		createNewDocument.createNewFolder(node1, CreateNewDocument.folderType.Content);
		info("Select folder");
		siteExplorerHome.selectNode(node1);
		executeJavaScript("window.scrollBy(0,-5500)", "");
		$(byClassName("uiIconEcmsViewAdmin")).click();
		info("Export a node");
		siteExplorerHome.goToExportNode();
		siteExplorerHome.exportNode(true, false);
		info("Delete all data test");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
		$(byClassName("uiIconEcmsViewWeb")).click();
		$(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();
		$(ELEMENT_TOOLBAR_ADMINISTRATION).click();
		ELEMENT_CONTENT_LIST.find(byLinkText(node1)).contextClick();
		info("Click on Delete link");
		$(ELEMENT_SITEEXPLORER_ACTION_DELETE).click();
		info("Click on Delete button on Confirm popup");
		$(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE).click();

	}
	@Test
	public void test05_ImportNode(){
		info("Test 5: Import a Node");
		info("Create data test");
		String node2 = "node2"+ getRandomNumber();
		String filePath = "sysview.xml";
		info("Finished data test");
		navigationToolbar.goToContentAdministration();
		contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
		contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
		$(byXpath(ELEMENT_ECM_EXPLORER_VIEW_EDIT_LIST.replace("{$name}","Admin"))).click();
		$(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM).click();
		$(ELEMENT_ECM_EXPLORER_EDIT_ACTION_VIEW_FORM).click();
		$(byId("importNode")).parent().click();
		if($(byId("importNode")).is(Condition.not(Condition.selected))){
			$(byId("importNode")).parent().click();}
		$(ELEMENT_ECM_EXPORER_ACTIONS_POPUP_SAVE_BUTTON).click();
		$(ELEMENT_ECM_EXPLORER_EDIT_VIEWS_SAVE_BUTTON).click();
		info("Add New folder");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
		$(byClassName("uiIconEcmsViewWeb")).click();
		siteExplorerHome.goToAddNewFolder();

		info("Create Folder node");
		createNewDocument.createNewFolder(node2, CreateNewDocument.folderType.Content);
		info("Select folder");
		siteExplorerHome.selectNode(node2);
		executeJavaScript("window.scrollBy(0,-5500)", "");
		$(byClassName("uiIconEcmsViewAdmin")).click();
		info("Import a node");
		siteExplorerHome.goToImportNode();
		$(byId("upload")).find(byClassName("file")).uploadFromClasspath("data/ecms/importNode.xml");
		$(byId("upload")).find(byClassName("progressBarFrame")).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
		$(ELEMENT_IMPORT_MODE_POPUP_IMPORT_BUTTON).click();
		$(Button.ELEMENT_OK_BUTTON).click();
		info("Delete all data test");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("sites/intranet/documents", "Collaboration");
		$(byClassName("uiIconEcmsViewWeb")).click();
		$(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON).click();
		$(ELEMENT_TOOLBAR_ADMINISTRATION).click();
		ELEMENT_CONTENT_LIST.find(byLinkText(node2)).contextClick();
		info("Click on Delete link");
		$(ELEMENT_SITEEXPLORER_ACTION_DELETE).click();
		info("Click on Delete button on Confirm popup");
		$(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE).click();
	}
}