package org.exoplatform.platform.qa.ui.platform.ecms;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.gatein.pageobject.NavigationManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PageCreationWizard;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManagePages;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManageSites;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


/**
 * @date 15-January-2015
 * @author exo
 * update 26/01/2015
 */


public class EcmsWCMTestIT extends Base {
	NavigationToolbar navigationToolbar;
	SiteExplorerHome siteExplorerHome;
	CreateNewDocument createNewDocument;
	PageCreationWizard pageCreationWizard;
	PortalManageSites portalManageSites;
	PortalManagePages portalManagePages;
	NavigationManagement navigationManagement;
	ManageLogInOut manageLogInOut;
	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
	navigationToolbar=new NavigationToolbar(this);
	siteExplorerHome=new SiteExplorerHome(this);
	createNewDocument=new CreateNewDocument(this);
	pageCreationWizard=new PageCreationWizard(this);
	portalManageSites=new PortalManageSites(this);
	portalManagePages=new PortalManagePages(this);
	navigationManagement=new NavigationManagement(this);
	manageLogInOut=new ManageLogInOut(this);
	manageLogInOut.signInCas(PLFData.DATA_USER1,"gtngtn");

	}
	/**
	 *<li> Case ID:116568.</li>
	 *<li> Test Case Name: Create new Content List viewer page with mode "By Folder".</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	Step Number: 1
		 *Step Name: Step 1: Create new Content List viewer page with mode "By Folder"
		 *Step Description:
			- Login acme by admin/web contributor
			- Go to Administration
			-
			-> Content
			-
			-> Sites Explorer.
			- Add some web contents in Sites Management/acme/web contents (or documents folder)

		- Go to acme homepage, select Edit/ Page/Add Page to create new page.+
		 * Fill node name+ Click Next -> Next.
		 * + Drag and drop Content/Content list portlet from page editor+
			Click Edit portlet icon of this Content list portlet+ Choose By Folder option+
			Click [Select folder path] icon to some folder that you want to view+
			Click [Save] button in Multiple content selector panel+
			Click [Save] button in Content list viewer configuration form+
			Click [Close] button inContent list viewer configuration form
			-Click Finish icon in Page editor
			- Make created web content public.
		 *Input Data:

		 *Expected Outcome:
			- All web contents/documents in selected folder are listed in Content List Viewer portlet in this page*/
	@Test
	public  void test01_CreateNewContentListViewerPageWithModeByFolder() {
		info("Test 1: Create new Content List viewer page with mode By Folder");
		String content = "content"+getRandomNumber();

		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet", "Site Management");
        //Create node
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(content, content);
		createNewDocument.saveAndClose();
		siteExplorerHome.verifyContentCreatedSuccessfully(content);
		navigationToolbar.goToAddPage();
		pageCreationWizard.inputPageInfoStep1(content, true, "English", content, true,false);
		click(ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(ELEMENT_ADDNEWPAGE_BTNNEXT);
		pageCreationWizard.addApplication($(ELEMENT_APPLICATION_CONTENT_TAB),$(byId("Content/portlet_ContentListViewerPortlet")));
		pageCreationWizard.addContentlistByFolder("General Drives/Sites Management","intranet");
		navigationToolbar.goToEditContent();
		ELEMENT_LIST_CONTENT.find(byText(content)).waitUntil(Condition.visible,Configuration.timeout);
		info("Delete Data test");
		info("Delete created file");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.deleteData(content, true);
		info("Delete created page");
		navigationToolbar.goToPotalPages();
		portalManagePages.deletePage(content,"");
	}

	/**
	 *<li> Case ID:116570.</li>
	 *<li> Test Case Name: Create new Content List Viewer page with mode "By Contents".</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 Step Number: 1
		 *Step Name: Step 1: Create new Content List Viewer page with mode "By Contents"
		 *Step Description:
			- Login acme by admin/web contributor
			- Go to content explorer, add some web contents Sites Management/acme/web contents (or documents folder)
		- Go to acme home page, select Edit/ Page/Add Page to create new page. +
		 * Fill node name+ Next+ Next +
		 * Drag and drop Content/Content list portlet from page editor+
		 * Click edit portlet icon of this Content list portlet+
		 * Choose By Contents option+ Click [Select folder path] icon to some web contents/documents that you want to view+
		 *  Click [Save] button in Multiple content selector panel+
		 *  Click [Save] button in Content list viewer configuration form+
		 *  Click [Close] button inContent list viewer configuration form
			-Click Finish icon in Page editor
		 *Input Data:

		 *Expected Outcome:
			All selected web content/documents are displayed as list in List Content Viewer page*/
	@Test
	public  void test02_CreateNewContentListViewerPageWithModeByContents() {
		info("Test 2: Create new Content List Viewer page with mode By Contents");
		String content = "content"+getRandomNumber();


		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet", "Site Management");
		//Create node
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(content, content);
		createNewDocument.saveAndClose();
		siteExplorerHome.verifyContentCreatedSuccessfully(content);
		navigationToolbar.goToAddPage();
		pageCreationWizard.inputPageInfoStep1(content, true, "English", content, true,false);
		$(ELEMENT_ADDNEWPAGE_BTNNEXT).click();
		$(ELEMENT_ADDNEWPAGE_BTNNEXT).click();
		pageCreationWizard.addContentListByContent("General Drives/Sites Management/intranet",content);
		navigationToolbar.goToEditContent();
		ELEMENT_LIST_CONTENT.find(byText(content)).waitUntil(Condition.visible,Configuration.timeout);
		info("Delete Data test");
		info("Delete created file");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.deleteData(content, true);
		info("Delete created page");
		navigationToolbar.goToPotalPages();
		portalManagePages.deletePage(content,"");
	}
	/**
	 *<li> Case ID:116571.</li>
	 *<li> Test Case Name: Create Single Content Viewer page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 Step Number: 1
		 *Step Name: Step 1: Create Single Content Viewer page
		 *Step Description:
			- Login acme by Admin/Web contributor
			- Choose Edit/Page/Add Page+ Fill name+ Next+ Next+
			Drag and drop Content/ Content Detail portlet to this Page+
			Click Edit icon to edit this porlet+ Select [Content Path] where stores these web contents/documents+
		 	Click icon in Action column in the right to select one of them+
		 	Click Save+
		 	Click Close+
		 	Click Finish icon in page editor
		 *Input Data:

		 *Expected Outcome:
			- The selected web content/document is displayed*/
	@Test
	public  void test03_CreateSingleContentViewerPage() {
		info("Test 3: Create Single Content Viewer page");
		String content ="content" +getRandomNumber();
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet", "Site Management");
		//Create node
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(content, content);
		createNewDocument.saveAndClose();
		siteExplorerHome.verifyContentCreatedSuccessfully(content);
		navigationToolbar.goToAddPage();
		pageCreationWizard.inputPageInfoStep1(content, true, "English", content, true,false);
		click(ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(ELEMENT_ADDNEWPAGE_BTNNEXT);
		pageCreationWizard.addContentDetail("General Drives/Sites Management/intranet",content);
		navigationToolbar.goToEditContent();
		ELEMENT_LIST_CONTENT.find(byText(content)).waitUntil(Condition.visible,Configuration.timeout);
		info("Delete Data test");
		info("Delete created file");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.deleteData(content, true);
		info("Delete created page");
		navigationToolbar.goToPotalPages();
		portalManagePages.deletePage(content,"");
	}

	/**
	 *<li> Case ID:116606.</li>
	 *<li> Test Case Name: Show draft/public content from page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 Step Number: 1
	 *Step Name: Step 1: Show draft /public content from page
	 *Step Description:
	 - Login acme by Admin/web contributor
	 - Go to Content Explorer/Sites Management/acme
	 - Add new document/web content
	 - Go to acme home page, add a SCV or CLV page with these contents
	 - Open page contains document/web content above
	 - Change to Edit mode [1]
	 - Return to Content Explorer/Sites Management/acme
	 - Select the document above
	 - Click Publications in Action bar
	 - Choose Publish status.
	 - Go to page contains document/web content above
	 - Change to Public mode [2]
	 *Input Data:

	 *Expected Outcome:
	 - [1] Selected document/web content is displayed into this page with draft
	 - [2] Selected document/web content is published into this page with published status*/
	@Test
	public  void test07_ShowDraftpublicContentFromPage() {
		info("Test 7: Show draft/public content from page");
		String content = "content"+getRandomNumber();
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet", "Site Management");
		//Create node
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(content , content);
		createNewDocument.saveAndClose();
		siteExplorerHome.verifyContentCreatedSuccessfully(content);
		navigationToolbar.goToAddPage();
		pageCreationWizard.inputPageInfoStep1(content , true, "English", content, true,false);
		click(ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(ELEMENT_ADDNEWPAGE_BTNNEXT);
		pageCreationWizard.addContentListByContent("General Drives/Sites Management/intranet",content);
		navigationToolbar.goToEditContent();
		//Verify that Selected document/web content is displayed into this page with draft
		ELEMENT_LIST_CONTENT.find(byText(content)).waitUntil(Condition.visible,Configuration.timeout);
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.selectNode(content);
		siteExplorerHome.goToPublication();
		siteExplorerHome.changeStatusPulication("Published");
		navigationToolbar.goToUnEditContent();
		info("Delete Data test");
		info("Delete created file");
		refresh();
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.deleteData(content, true);
		info("Delete created page");
		navigationToolbar.goToPotalPages();
		portalManagePages.deletePage(content,"");

	}
}