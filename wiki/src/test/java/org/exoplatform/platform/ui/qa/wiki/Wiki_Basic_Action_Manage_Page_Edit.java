package org.exoplatform.platform.ui.qa.wiki;


import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import org.openqa.selenium.By;

import java.util.ArrayList;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TREE_WIKI_NAME;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_WIKI_PAGE_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class  Wiki_Basic_Action_Manage_Page_Edit extends Base {

		HomePagePlatform homePagePlatform;

		WikiHomePage wikiHomePage;

		RichTextEditor richTextEditor;

		WikiValidattions wikiValidattions;

		WikiManagement wikiManagement;

		WikiDraftPage wikiDraftPage;

		SourceTextEditor sourceTextEditor;

		ActivityStream activityStream;

		ArrayList<String> arrayPage;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");

		homePagePlatform = new HomePagePlatform(this);
		wikiValidattions = new WikiValidattions(this);
		wikiManagement = new WikiManagement(this);
		sourceTextEditor= new SourceTextEditor(this);
		activityStream= new ActivityStream(this);

		try {
		richTextEditor = new RichTextEditor(this);
		} catch (Exception e) {
		e.printStackTrace();
		}

		arrayPage = new ArrayList<String>();
		wikiDraftPage = new WikiDraftPage (this);
		}

	/**
	 *<li> Case ID:122819.</li>
	 *<li> Test Case Name: Edit page with Source Editor.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */

	@Test
	@Smoke
	@Tag("smoke")
	public  void test02_EditPageUsingSourceEditor() {
		info("Test 02: Edit page with Source Editor");
		String wiki = "WIKI1"+getRandomNumber();
		String wiki2 = "WIKI2"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Step 1: Edit page with Source Editor
		 *Step Description:
			- Open an existing page by clicking on page name in navigation tree
			- Click on Edit icon in toolbar
			- Change content in full page and click on Save icon in toolbar

		 *Input Data:

		 *Expected Outcome:
			Edit page successfully*/
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(wiki, wiki);
		wikiManagement.saveAddPage();
		waitForAndGetElement(By.xpath(ELEMENT_TREE_WIKI_NAME.replace("{name}", wiki))).isDisplayed();
		info("Verify that the page is editor successfully");
		wikiHomePage.goToEditPage();
		richTextEditor.editSimplePage(wiki2,wiki2);
		wikiManagement.saveAddPage();
		waitForAndGetElement(By.xpath(ELEMENT_TREE_WIKI_NAME.replace("{name}", wiki2))).isDisplayed();

		info("Delete the page");
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(wiki2);
	}

	/**
	 *<li> Case ID:122817.</li>
	 *<li> Test Case Name: Edit Page with Rich Text Editor</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */

	@Test
	@Smoke
	@Tag("smoke")
	public  void test01_EditPageUsingRichTextEditor() {
		info("Test 1: Edit Page with Rich Text Editor");
		/*Step Number: 1
		 *Step Name: Step 1: Edit Page with Rich Text Editor
		 *Step Description:
			- Open an existing page by clicking on page name in navigation tree
			- Click on Edit icon in toolbar
			- Change content in full page and click on Save icon in toolbar

		 *Input Data:

		 *Expected Outcome:
			Edit page successfully*/
		info("Create a wiki page 1");
		String wiki = "WIKI1"+getRandomNumber();
		String wiki2 = "WIKI2"+getRandomNumber();
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(wiki, wiki);
		wikiManagement.saveAddPage();
		waitForAndGetElement(By.xpath(ELEMENT_TREE_WIKI_NAME.replace("{name}", wiki))).isDisplayed();

		info("Verify that the page is editor successfully");
		wikiHomePage.goToEditPage();
		richTextEditor.editSimplePage(wiki2, wiki2);
		wikiManagement.saveAddPage();
		waitForAndGetElement(By.xpath(ELEMENT_TREE_WIKI_NAME.replace("{name}", wiki2))).isDisplayed();

		info("Delete the page");
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(wiki2);
	}

	/**
	 *<li> Case ID:122824.</li>
	 *<li> Test Case Name: Auto save when editing page</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	@Smoke
	@Tag("smoke")
	public  void test03_AutoSaveWhenEditingPage() {
		info("Test 3: Auto save when editing page");

		String wiki = "WIKI1"+getRandomNumber();
		String wiki2 = "WIKI2"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Step 1: Check auto save when editing page
		 *Step Description:
			- Go to Browser -> My draft
			- Select a page and click open it
			- Edit title or content for it

		 *Input Data:

		 *Expected Outcome:
			While editing the draft will be saved automatically after 30 seconds (default value) only
			if there's any modifications in the content or page title. */
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePageHasAutoSaveWithoutSave(wiki,wiki);

		wikiHomePage.goToMyDraft();
		info("The draft is displayed in the list");
		wikiDraftPage.resumeADraft(wiki);
		info("The page in edit mode is displayed");
		richTextEditor.editSimplePageWithAutoSave(wiki2, wiki2);

		info("Delete draf");
		wikiHomePage.goToMyDraft();
		wikiDraftPage.deleteDraft(wiki2);
	}


	/**
	 *<li> Case ID:122836.</li>
	 *<li> Test Case Name: Edit  Page with template layout.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	@Smoke
	@Tag("smoke")
	public  void test04_EditPageFromTemplate() {
		info("Test 04:  Edit  Page with template layout");
		String template = "HOW-TO_Guide";
		String title = "HOW-TO_Guide";
		String title2 = "HOW-TO_Guide" + getRandomNumber();
		/*Step Number: 1
		 *Step Name: Step 1: Edit Page with template layout
		 *Step Description:
			- Open an existing page by clicking on page name in navigation tree
			- Click on Edit icon in toolbar
			- Change content in full page and click on Save icon in toolbar


		 *Input Data:

		 *Expected Outcome:
			Edit page successfully*/
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddTemplateWikiPage();
		wikiManagement.addSimpleWikiPageByTemplate(template,title);
		//waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		waitForAndGetElement(By.xpath(ELEMENT_TREE_WIKI_NAME.replace("{name}", title))).isDisplayed();


		wikiHomePage.goToEditPage();
		richTextEditor.editSimplePage(title2, title2);
		wikiManagement.saveAddPage();
		//waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title2),2000,0);
		waitForAndGetElement(By.xpath(ELEMENT_TREE_WIKI_NAME.replace("{name}", title2))).isDisplayed();


		info("Delete the page");
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(title2);
	}

	/**
	 *<li> Case ID:122808.</li>
	 *<li> Test Case Name:Edit Paragraph in Page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	@Smoke
	@Tag("smoke")
	public  void test05_EditParagraphInPage() {
		info("Test 05: Edit Paragraph in Page");
		String template = "HOW-TO_Guide";
		String title = "HOW-TO_Guide";
		String content = "== "+title+" ==";
		//String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		//String content = "== "+title+" ==";
		//String title2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		//String content2 = "=== "+title2+" ===";

		/*Step Number: 1
		 *Step Name: Step 1: Edit Paragraph
		 *Step Description:
			- Open an exiting page that contains some paragraphs inside
			- Click on Edit icon corresponding of paragraph that you want to edit
			- Change content and click on Save icon in tool bar

		 *Input Data:

		 *Expected Outcome:
			- Selected paragraph in page is edited successful and shown new content
			- Other paragraph's content  in page is remain */

		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		wikiHomePage.goToAddTemplateWikiPage();
		wikiManagement.addSimpleWikiPageByTemplate(template,title);
		//waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		waitForAndGetElement(By.xpath(ELEMENT_TREE_WIKI_NAME.replace("{name}", title))).isDisplayed();

		wikiManagement.editParagraph(title, content);
		wikiManagement.saveAddPage();
		wikiHomePage.deleteWiki(title);
	}

	/**
	 *<li> Case ID:122847.</li>
	 *<li> Test Case Name:Edit page when publish activity is checked.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	@Smoke
	@Tag("smoke")
	public  void test06_EditPageWhenPublishActivityIsChecked() {
		info("Test 06: Edit page when publish activity is checked");
		String title = "test"+getRandomNumber();
		String newTitle = "updated"+getRandomNumber();
		String comment = "Page's title has been updated to:"+ " "+newTitle;
		String comment1 = "Page's title has been updated to:";

		/*Step Number: 1
		 *Step Name: Step 1: Add new page
		 *Step Description:
			- Go to wiki page
			- Add new page


		 *Input Data:

		 *Expected Outcome:
			- Add new page successfully */
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		wikiManagement.goToRichTextEditor();
		richTextEditor.addSimplePage(title, title);
		wikiManagement.saveAddPage();
		waitForAndGetElement(By.xpath(ELEMENT_TREE_WIKI_NAME.replace("{name}", title))).isDisplayed();


		/*Step Number: 2
		 *Step Name: Step 2: Edit page ( title of page)
		 *Step Description:
			- Select page above
			- Click Edit page
			- Change title of page or content of page
			- Check Publish Activity
			- Click Save


		 *Input Data:

		 *Expected Outcome:
			- Edit page successfully */
		wikiHomePage.goToEditPage();
		richTextEditor.editSimplePage(newTitle,"");
		wikiManagement.publishPageWhenEditPage();
		wikiManagement.saveAddPage();
		waitForAndGetElement(By.xpath(ELEMENT_TREE_WIKI_NAME.replace("{name}", newTitle))).isDisplayed();

		/*Step Number: 3
		 *Step Name: Step 3: Check Publish activity
		 *Step Description:
			- Go to Homepage

		 *Input Data:

		 *Expected Outcome:
			- Show activity of page after editing
			- Page's title has been updated to: $value.
         */
		homePagePlatform.goToHomePage();
		activityStream.checkActivity(newTitle);
		activityStream.checkCommentOfActivity(newTitle,comment);

		/*Step Number: 4
		 *Step Name: Step 4: Edit page ( content of page)
		 *Step Description:
			- Select page above
			- Click Edit page
			- Change content of page
			- Check Publish Activity
			- Click Save

		 *Input Data:

		 *Expected Outcome:
			- Edit page successfully.
         */

		homePagePlatform.goToWiki();
		wikiHomePage.goToAPage(newTitle);
		wikiHomePage.goToEditPage();
		richTextEditor.editSimplePage("",newTitle);
		wikiManagement.publishPageWhenEditPage();
		wikiManagement.saveAddPage();
		//waitForAndGetElement(wikiMg.ELEMENT_WIKI_PAGE_CONTENT.replace("${text}",newTitle),2000,0);
		waitForAndGetElement(By.xpath(ELEMENT_WIKI_PAGE_CONTENT.replace("{name}", newTitle))).isDisplayed();

		/*Step Number: 5
		 *Step Name: Step 5: Check Publish activity
		 *Step Description:
			- Go to Homepage

		 *Input Data:

		 *Expected Outcome:
			- Show activity of page after editing
			-Show "Page's content has been edited." in comment list.
         */
		homePagePlatform.goToHomePage();
		activityStream.checkActivity(newTitle);
		activityStream.checkCommentOfActivity(newTitle,comment1);

		info("Delete the page");
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(newTitle);
	}

	/**
	 *<li> Case ID:122848.</li>
	 *<li> Test Case Name:Edit page when publish activity is not checked.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	@Smoke
	@Tag("smoke")
	public  void test07_EditAPageWhenPublishActivityIsNotChecked() {
		info("Test 07: Edit page when publish activity is not checked");
		String title = "test"+getRandomNumber();
		String newTitle = "updated"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Step 1: Add new page
		 *Step Description:
			- Go to wiki page
			- Add new page


		 *Input Data:

		 *Expected Outcome:
			- Add new page successfully */
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.editSimplePage(newTitle,"");
		wikiManagement.saveAddPage();
		waitForAndGetElement(By.xpath(ELEMENT_TREE_WIKI_NAME.replace("{name}", title))).isDisplayed();

		/*Step Number: 2
		 *Step Name: Step 2: Edit page ( title of page)
		 *Step Description:
			- Select page above
			- Click Edit page
			- Change something in title
			- UnCheck Publish Activity
			- Click Save


		 *Input Data:

		 *Expected Outcome:
			- Edit page successfully */
		wikiHomePage.goToEditPage();
		richTextEditor.editSimplePage(newTitle,"");
		wikiManagement.saveAddPage();
		waitForAndGetElement(By.xpath(ELEMENT_TREE_WIKI_NAME.replace("{name}", newTitle))).isDisplayed();

		/*Step Number: 3
		 *Step Name: Step 3: Check Publish activity
		 *Step Description:
			- Go to Homepage

		 *Input Data:

		 *Expected Outcome:
			Don't show activity of page after editing
         */
		homePagePlatform.goToHomePage();
		//waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",newTitle),3000,0);
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{name}", newTitle))).isDisplayed();

		/*Step Number: 4
		 *Step Name: Step 4: Edit page ( content of page)
		 *Step Description:
			- Select page above
			- Click Edit page
			- Change something in content
			- UnCheck Publish Activity
			- Click Save

		 *Input Data:

		 *Expected Outcome:
			- Edit page successfully.
         */

		homePagePlatform.goToWiki();
		wikiHomePage.goToAPage(newTitle);
		wikiHomePage.goToEditPage();
		richTextEditor.editSimplePage("",newTitle);
		wikiManagement.saveAddPage();
		//waitForAndGetElement(wikiMg.ELEMENT_WIKI_PAGE_CONTENT.replace("${text}",newTitle),2000,0);
		waitForAndGetElement(By.xpath(ELEMENT_WIKI_PAGE_CONTENT.replace("{name}", newTitle))).isDisplayed();

		/*Step Number: 5
		 *Step Name: Step 5: Check Publish activity
		 *Step Description:
			- Go to Homepage

		 *Input Data:

		 *Expected Outcome:
			Don't show activity of page after editing
         */
		homePagePlatform.goToHomePage();
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{name}", newTitle))).isDisplayed();

		info("Delete the page");
		wikiHomePage.deleteWiki(newTitle);
		homePagePlatform.goToWiki();
	}
}