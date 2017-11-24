package org.exoplatform.platform.qa.ui.platform.ecms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * @date 15-January-2015
 * @author exo
 */

	public class EcmsSECollaborationTestIT extends Base {
		
		NavigationToolbar navigationToolbar;
		SiteExplorerHome siteExplorerHome;
		CreateNewDocument createNewDocument;
		ManageLogInOut manageLogInOut;
	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
	navigationToolbar=new NavigationToolbar(this);
	siteExplorerHome=new SiteExplorerHome(this);
	createNewDocument=new CreateNewDocument(this);
	manageLogInOut=new ManageLogInOut(this);
	manageLogInOut.signInCas(PLFData.DATA_USER1,"gtngtn");

	}
	/**
	*<li> Case ID:116564.</li>
	*<li> Test Case Name: Add translation for document/uploaded file.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	 * Step Number: 1
	 *Step Name: -
	 *Step Description:
	 Step 1: Add translation for document/uploaded file
	 *Input Data:
	 - Add some documents in different languages, eg English, French...
	 - Open document in English
	 - Click on Add Translation icon on the action bar
	 - Navigate to French document
	 - Save
	 - Click Relation icon on side bar
	 *Expected Outcome:
	 - Documents are created
	 - French document is list in Languages list of English document.When you add this content in a CLV or SCV, change language, you will see effect*/

	@Test
	public  void test01_AddTranslationForDocumentuploadedFile() {
		info("Test 1: Add translation for document/uploaded file");
		String title = "title"+getRandomNumber()+"en";
		String content = "content"+getRandomNumber()+"en";
		String title2 = "title2"+getRandomNumber()+"fr";
		String content2 = "content2"+getRandomNumber()+"fr";
		

		info("Create content 1");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(title, content);
		createNewDocument.saveAndClose();
		
		siteExplorerHome.selectNode("documents");
		info("Create content 2");
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(title2, content2);
		$(ELEMENT_FILEFORM_LANGUAGE).selectOption("fr");
		createNewDocument.saveAndClose();
		
		$(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", title))).click();
		siteExplorerHome.addDocumentTranslation("General Drives/Sites Management/intranet/documents", title2);
		$(ELEMENT_SITEEXPLORER_LEFTBOX_RELATION).waitUntil(Condition.visible, Configuration.timeout).click();
		$(byXpath(ELEMENT_SITEEXPLORER_LEFTBOX_TITLE_TRANSLATION.replace("${title}", title2))).waitUntil(Condition.visible,Configuration.timeout);
		
		info("Delete all data test");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.deleteData(title);
		siteExplorerHome.deleteData(title2);
 	}
	/**
	 *<li> Case ID:116586.</li>
	 *<li> Test Case Name: Add Comment.</li>
	 *<li> Pre-Condition: If Comment is not available on action bar, go to Content Administration/ Explorer/ Views, Edit view being in use with Comment option is ticked</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_AddComment() {
		info("Test 2: Add Comment");
		info("Get data test");
		String title = "title"+getRandomNumber();
		String content = "content"+getRandomNumber();
		String content2 = "content2"+getRandomNumber();
		info("Fnishing Getting data test");

		/*Step Number: 1
		*Step Name: -
		*Step Description:
			Step 1: Add Comment
		*Input Data:
			Add a comment
			- Select a document or uploaded file
			- Click on Comment icon on the action bar
			- Fill comment's content
			- Save
		*Expected Outcome:
			You can see the comment at the bottom of document/uploaded file.*/
		info("Create content 1");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(title, content);
		createNewDocument.saveAndClose();
		info("Add a comment");
		siteExplorerHome.addEditComment(content2,true);
		$(byClassName("showComments")).click();
		$(byText(content2)).should(Condition.visible);
		siteExplorerHome.selectNode("documents");
		siteExplorerHome.deleteData(title);
	}
	/**
	 *<li> Case ID:116592.</li>
	 *<li> Test Case Name: Add a tag.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 **<li> Case ID:116652.</li>
	 *<li> Test Case Name: Edit a tag.</li>
	 *<li> Pre-Condition: A tag is created successfully</li>
	 *<li> Post-Condition: </li>
	 *
	 **<li> Case ID:116653.</li>
	 *<li> Test Case Name: Detele a tag.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: A tag is already created.</li>
	Step Number: 1
		*Step Name: Step 1: Add a tag
		*Step Description:
			- Select a document or uploaded file
			- Click on Tag button in the action bar (if not, select Tag from More drop
			-down menu)
			- Put name for a tag
			- Click Add button, the Close button
		*Input Data:

		*Expected Outcome:
			Node is added tag. You can find document using tag in Tag cloud of FE*/
	@Test
	@BugInPLF("ECMS-7676")
	public  void test05_AddATag() {
		info("Test 05: Add a tag");
		String title = "title"+getRandomNumber();
		String content = "content"+getRandomNumber();
		String content2 = "content2"+getRandomNumber();
		String content3 = "content3"+getRandomNumber();


		info("Create content 1");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(title, content);
		createNewDocument.saveAndClose();

		info("Add a tag to the Content");
		siteExplorerHome.selectNode(title);
		siteExplorerHome.addTag(content2);
		info("Verify that the tag is shown in Tag tab of SE");
		$(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB).click();
		$(byId("UITagExplorer")).find(byText(content2)).should(Condition.visible);
		info("Test 06: Edit a tag");
		siteExplorerHome.editTag(content2,content3);
		info("Test 07: Delete a tag");
		siteExplorerHome.deleteTag(content3);
		siteExplorerHome.deleteData(title);
	}
	@Test
	@BugInPLF("ECMS-7676")
	public  void test06_EditATag() {
		info("Test 05: Add a tag");
		String title = "title"+getRandomNumber();
		String content = "content"+getRandomNumber();
		String content2 = "content2"+getRandomNumber();
		String content3 = "content3"+getRandomNumber();


		info("Create content 1");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(title, content);
		createNewDocument.saveAndClose();

		info("Add a tag to the Content");
		siteExplorerHome.selectNode(title);
		siteExplorerHome.addTag(content2);
		info("Verify that the tag is shown in Tag tab of SE");
		$(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB).click();
		$(byId("UITagExplorer")).find(byText(content2)).should(Condition.visible);
		info("Test 06: Edit a tag");
		siteExplorerHome.editTag(content2,content3);
		info("Test 07: Delete a tag");
		siteExplorerHome.deleteTag(content3);
		siteExplorerHome.deleteData(title);
	}
	@Test
	@BugInPLF("ECMS-7676")
	public  void test07_DeleteATag() {
		info("Test 05: Add a tag");
		String title = "title"+getRandomNumber();
		String content = "content"+getRandomNumber();
		String content2 = "content2"+getRandomNumber();
		String content3 = "content3"+getRandomNumber();


		info("Create content 1");
		navigationToolbar.goToSiteExplorer();
		siteExplorerHome.goToPath("intranet/documents", "Site Management");
		siteExplorerHome.goToAddNewContent();
		createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
		createNewDocument.addNewFile(title, content);
		createNewDocument.saveAndClose();

		info("Add a tag to the Content");
		siteExplorerHome.selectNode(title);
		siteExplorerHome.addTag(content2);
		info("Verify that the tag is shown in Tag tab of SE");
		$(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB).click();
		$(byId("UITagExplorer")).find(byText(content2)).should(Condition.visible);
		info("Test 06: Edit a tag");
		siteExplorerHome.editTag(content2,content3);
		info("Test 07: Delete a tag");
		siteExplorerHome.deleteTag(content3);
		siteExplorerHome.deleteData(title);
	}
}