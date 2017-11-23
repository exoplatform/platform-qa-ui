package org.exoplatform.platform.qa.ui.platform.ecms;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;


/**
 * @author eXo
 *
 */
public class EcmsSEBasicActionTestIT extends Base{
	NavigationToolbar navigationToolbar;
	SiteExplorerHome siteExplorerHome;
	CreateNewDocument createNewDocument;
	ManageLogInOut manageLogInOut;
	ManageAlert manageAlert;
	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
	navigationToolbar=new NavigationToolbar(this);
	siteExplorerHome=new SiteExplorerHome(this);
	createNewDocument=new CreateNewDocument(this);
	manageLogInOut=new ManageLogInOut(this);
	manageAlert=new ManageAlert(this);
	manageLogInOut.signInCas(PLFData.DATA_USER1,"gtngtn");
	}
	/**
	 *<li> Case ID:116565.</li>
	 *<li> Test Case Name: Add symlink for a node.</li>
	Step Number: 1
		 *Step Name: -
		 *Step Description:
			Step 1: Add symlink for a node
		 *Input Data:
			- Select a node (folder)
			- Right click and select Add Symlink icon
			- Select a node to add symlink
			- Click Save
		 *Expected Outcome:
			Symlink for a node is added successfully. Show symlink node as a child node

		Step number: 2
		 *Step Name:
		 *Step Description:
			Step 2 : Move Symlink
		 *Input Data:
			- Go back to destination symlink node
		 *Expected Outcome:
			- Destination symlink node is opened

		Step number: 3
		 *Step Name:
		 *Step Description:
			Step 3 : Add file in the node
		 *Input Data:
			- Add a web content file
		 *Expected Outcome:
			The webcontent file created must appears in the Symlink*/
	@Test
	public  void test01_AddSymlinkForANode() {
		info("Test 1: Add symlink for a node");

		
		 getRandomNumber();
		String folderTitle = "folderTitle"+getRandomNumber();
		String node =folderTitle .toLowerCase();
		
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToAddNewFolder();
		siteExplorerHome.createFolder(folderTitle, "Content Folder");
		siteExplorerHome.addSymlink(folderTitle);
		$(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node+".lnk"))).waitUntil(Condition.visible, Configuration.timeout);
		
		$(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", folderTitle))).click();
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(node,node);
		createNewDocument.saveAndClose();
		
		$(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
		$(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", node+".lnk"))).click();
		$(ELEMENT_ACCOUNT_NAME_LINK).click();
		$(byXpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node))).click();
		siteExplorerHome.deleteData(node+".lnk");
		siteExplorerHome.deleteData(folderTitle);
	}

	/**
	 *<li> Case ID:116567.</li>
	 *<li> Test Case Name: Copy/paste a node.</li>
	 *<li> Case ID:116574.</li>
	 *<li> Test Case Name: Cut/paste a node.</li>
	 *<li> Case ID:116576.</li>
	 *<li> Test Case Name: Drag and drop a node.</li>
	Step Number: 1
		 *Step Name: -
		 *Step Description:
			Step 1: Copy/paste a node
		 *Input Data:
			- Right click on a node and select Copy
			- Right click on destination node and select Paste
		 *Expected Outcome:
			Node is pasted into destination node*/
	@Test
	public  void test02_CopypasteANode() {

		String destination="intranet";
		String secondDestination="shared";

		String titleCommonNode= "titleCommonNode"+getRandomNumber();

		navigationToolbar.goToSiteExplorer();
		$(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(titleCommonNode, titleCommonNode);
		createNewDocument.saveAndClose();
		info("Test 2: Copy/paste a node");
		siteExplorerHome.copyPasteNode(titleCommonNode, destination);
		info("delete data");
		$(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination))).click();
		siteExplorerHome.deleteData(titleCommonNode);
		$(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", secondDestination))).click();
		siteExplorerHome.deleteData(titleCommonNode);
	}
	@Test
	public  void test03_CutpasteANodeANode() {

		String destination="intranet";
		String secondDestination="shared";

		String titleCommonNode= "titleCommonNode"+getRandomNumber();

		navigationToolbar.goToSiteExplorer();
		$(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(titleCommonNode, titleCommonNode);
		createNewDocument.saveAndClose();
		info("Test 3: Cut/paste a node");
		siteExplorerHome.cutPasteNode(titleCommonNode, secondDestination);
		$(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", secondDestination))).click();
		siteExplorerHome.deleteData(titleCommonNode);
	}
	@Test
	public  void test_04_DragAndDropANode() {

		String destination="intranet";
		String secondDestination="shared";

		String titleCommonNode= "titleCommonNode"+getRandomNumber();

		navigationToolbar.goToSiteExplorer();
		$(ELEMENT_SIDEBAR_SITES_MANAGEMENT).click();
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(titleCommonNode, titleCommonNode);
		createNewDocument.saveAndClose();
		info("Test 4: Drag and drop a node");
		$(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode))).dragAndDropTo($(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination))));
		manageAlert.acceptAlert();
		$(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination))).click();
		siteExplorerHome.deleteData(titleCommonNode);

	}

}