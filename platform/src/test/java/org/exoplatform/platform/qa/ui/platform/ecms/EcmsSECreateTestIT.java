package org.exoplatform.platform.qa.ui.platform.ecms;


import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("sniff")
@Tag("ecms")
public class EcmsSECreateTestIT extends Base{
NavigationToolbar navigationToolbar;
SiteExplorerHome siteExplorerHome;
ManageLogInOut manageLogInOut;
CreateNewDocument createNewDocument;
	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		navigationToolbar=new NavigationToolbar(this);
		siteExplorerHome=new SiteExplorerHome(this);
		manageLogInOut=new ManageLogInOut(this);
		createNewDocument=new CreateNewDocument(this);
		manageLogInOut.signInCas(PLFData.DATA_USER1,"gtngtn");
	}
	/**
	 *<li> Case ID:116569.</li>
	 *<li> Test Case Name: Create Content folder.</li>

	 *<li> Case ID:116645.</li>
	 *<li> Test Case Name: Delete Content folder.</li>
	 *Step Number: 1
	 *Step Name: -
	 *Step Description:
	 Step 1: Delete Content folder
	 *Input Data:
	 - Create a content folder
	 - Right click on a folder and choose Delete
	 - Click Delete at confirmation
	 *Expected Outcome:
	 - Folder is deleted
	 - A modal message appears with Undo option. You can click undo to restore*/

	@Test
	public  void test01_Create_ContentFolder() {
		info("Test 1: Create Content folder");
        info("Create data test");
		String title = "title"+getRandomNumber();
		String folderType = "Content Folder";
		info("Finished creating data test");


		info("Create a content folder");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewFolder();
		info("Create new file document");
		siteExplorerHome.createFolder(title, folderType);

		info("Test 10: Delete file document");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(title);
	}
	@Test
	public  void test10_DeleteContentFolder() {
		info("Test 1: Create Content folder");
		info("Create data test");
		String title = "title"+getRandomNumber();
		String folderType = "Content Folder";
		info("Finished creating data test");


		info("Create a content folder");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewFolder();
		info("Create new file document");
		siteExplorerHome.createFolder(title, folderType);

		info("Test 10: Delete file document");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(title);
	}
	/**
	 *<li> Case ID:116572.</li>
	 *<li> Test Case Name: Delete File document.</li>

	 *<li> Case ID:116641.</li>
	 *<li> Test Case Name: Create File document.</li>

	 *<li> Case ID:116642.</li>
	 *<li> Test Case Name: Edit File document.</li>
	 */
	/*Step Number: 1
		 *Step Name: -
		 *Step Description:
			Step 1: Edit File document
		 *Input Data:
			- Create a File
			- Click Edit on action bar, perform to edit it
			- Click Save & Close
		 *Expected Outcome:
			File is Edited successfully*/
	@Test
	public  void test02_Create_FileDocument() {
		info("Test 2: Delete File document");
		info("Create data test");
		String name = "name"+getRandomNumber();
		String content = "content"+getRandomNumber();
		info("Finished creating data test");
		info("Create a content");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewContent();
		info("Create new file document");
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(name, content);
		createNewDocument.saveAndClose();
		$(byText(name)).should(Condition.visible);
		switchTo().frame($(byClassName("ECMIframe")));
		$(byText(content)).should(Condition.visible);
		switchTo().defaultContent();
		info("Delete file document");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(name);
	}
	@Test
	public  void test06_EditFileDocument() {
		info("Test 2: Delete File document");
		info("Create data test");
		String name = "name"+getRandomNumber();
		String content = "content"+getRandomNumber();
		String content2 = "content2"+getRandomNumber();
		info("Finished creating data test");
		info("Create a content");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewContent();
		info("Create new file document");
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(name, content);
		createNewDocument.saveAndClose();
		$(byText(name)).should(Condition.visible);
		switchTo().frame($(byClassName("ECMIframe")));
		$(byText(content)).should(Condition.visible);
		switchTo().defaultContent();
		info("Edit a content");
		siteExplorerHome.selectNode(name);
		siteExplorerHome.editDocument("",content2);
		createNewDocument.saveAndClose();
		switchTo().frame($(byClassName("ECMIframe")));
		$(byText(content+content2)).should(Condition.visible);
		switchTo().defaultContent();
		info("Delete file document");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(name);
	}
	@Test
	public  void test07_DeleteFileDocument() {
		info("Test 2: Delete File document");
		info("Create data test");
		String name = "name"+getRandomNumber();
		String content = "content"+getRandomNumber();
		info("Finished creating data test");
		info("Create a content");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewContent();
		info("Create new file document");
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(name, content);
		createNewDocument.saveAndClose();
		$(byText(name)).should(Condition.visible);
		switchTo().frame($(byClassName("ECMIframe")));
		$(byText(content)).should(Condition.visible);
		switchTo().defaultContent();
		info("Delete file document");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(name);
	}
	/**
	 *<li> Case ID:116573.</li>
	 *<li> Test Case Name: Create Web Content document.</li>

	 *<li> Case ID:116643.</li>
	 *<li> Test Case Name: Edit Web Content document.</li>
	 *<li> Pre-Condition: A Web Content document is already created.</li>

	 *<li> Case ID:116644.</li>
	 *<li> Test Case Name: Delete Web Content document.</li>
	 *<li> Pre-Condition: A Web Content document is already created.</li>
	Step Number: 1
		 *Step Name: Step 1: Edit Web Content document
		 *Step Description:
			- Click Edit on action bar, perform to edit it
			- Click Save & Close
		 *Input Data:

		 *Expected Outcome:
			The Web Content document is edited successfully*/
	@Test
	public  void test03_CreateWebContentDocument() {
		info("Test 3: Create Web Content document");
		info("Create data test");
		String name = "name"+getRandomNumber();
		String content = "content"+getRandomNumber();
		info("Finished creating data test");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewContent();
		info("Create new file document");
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(name, content);
		createNewDocument.saveAndClose();
		$(byText(name)).should(Condition.visible);
		$(byText(content)).should(Condition.visible);
		info("Delete file document");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(name);
	}
	@Test
	public  void test08_Edit_WebContentDocument() {
		info("Test 3: Create Web Content document");
		info("Create data test");
		String name = "name"+getRandomNumber();
		String content = "content"+getRandomNumber();
		String content2 ="content2"+getRandomNumber();
		info("Finished creating data test");

		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewContent();
		info("Create new file document");
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(name, content);
		createNewDocument.saveAndClose();
		$(byText(name)).should(Condition.visible);
		$(byText(content)).should(Condition.visible);
		info("Edit the content");
		siteExplorerHome.selectNode(name);
		siteExplorerHome.editDocument("",content2);
		createNewDocument.saveAndClose();
		$(byText(name)).should(Condition.visible);
		$(byText(content+content2)).should(Condition.visible);
		info("Delete file document");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(name);
	}
	@Test
	public  void test09_Delete_WebContentDocument() {
		info("Test 3: Create Web Content document");
		info("Create data test");
		String name = "name"+getRandomNumber();
		String content = "content"+getRandomNumber();
		info("Finished creating data test");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewContent();
		info("Create new file document");
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
		createNewDocument.addNewWebContent(name, content);
		createNewDocument.saveAndClose();
		$(byText(name)).should(Condition.visible);
		$(byText(content)).should(Condition.visible);
		info("Delete file document");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(name);
	}
}