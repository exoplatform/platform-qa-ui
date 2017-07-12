package org.exoplatform.platform.qa.ui.platform.wiki;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("wiki")
@Tag("smoke")
public class WikiInformation extends Base {

	HomePagePlatform homePagePlatform;
	WikiHomePage wikiHomePage;
	ManageLogInOut manageLogInOut;
	WikiManagement wikiManagement;
	RichTextEditor richTextEditor;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		homePagePlatform = new HomePagePlatform(this);
		wikiHomePage = new WikiHomePage(this);
		manageLogInOut = new ManageLogInOut(this);
		wikiManagement = new WikiManagement(this);

		try {
			richTextEditor = new RichTextEditor(this);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public  void test01_ViewPageInfo() {
		info("Test 01: View Page info");
		String title = "title" + getRandomNumber();
		String child1 = "child1" + getRandomNumber();
		String child2 = "child2" + getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Check page history
		 *Step Description: 
			- Create a new page under wiki home, eg WikiPage1
			- Edit this page
			- Create 2 child page for it, child 1 and child 2
			- Open this page
			- Click [More][Page Info]

		 *Input Data: 

		 *Expected Outcome: 
			Page information is shown
			- Summary: Title, author, Last changed by
			- Related page: list all related page. A button [Add More Relation] 
			- Hierarchy: list parent page and child
			- Recent changes: list all revisions and a button [View Page History] */ 
		
		info("Create a new wiki page");
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title,title);
		wikiManagement.saveAddPage();
		$(byText(title)).should(Condition.exist);
	
		
		info("Create child 1 for wiki page");
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(child1,child1);
		wikiManagement.saveAddPage();
		$(byText(child1)).should(Condition.exist);

		info("Create child 2 for wiki page");
		wikiHomePage.selectAPage(title);
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(child2,child2);
		wikiManagement.saveAddPage();
		$(byText(child2)).should(Condition.exist);

		info("Open Page info");
		String date = getDateByTextFormat("MM dd, yyyy");
		wikiHomePage.goToPageInformation(title);
		
		info("Summary: Title, author, Last changed by");
		$(byText("Title")).parent().parent().find(byText(title)).should(Condition.exist);
		$(byText("Author")).parent().parent().find(byText("Root Root")).should(Condition.exist);
		$(byText("Last changed by")).parent().parent().find(byText("Root Root")).should(Condition.exist);
		
		info("Related page: list all related page. A button [Add More Relation]");
		$(byClassName("empty center")).find(byText("Empty data"));
		$(ELEMENT_PAGE_INFO_ADD_MORE_RELATIONS).waitUntil(Condition.appears,Configuration.timeout);

		info("Hierarchy: list parent page and child");
		$(byClassName("uiPageInfoItem uiPageInfoHierarchy")).find(byClassName("nodeLabel")).find(byText("Wiki Home"));
		$(byClassName("uiPageInfoItem uiPageInfoHierarchy")).find(byClassName("nodeGroup")).find(byText("child1"));
		$(byClassName("uiPageInfoItem uiPageInfoHierarchy")).find(byClassName("nodeGroup")).find(byText("child2"));

		info("Recent changes: list all revisions and a button [View Page History]");
		$(byClassName("")).find(byText(""));
		$(ELEMENT_VIEW_PAGE_HISTORY).should(Condition.exist);

		info("Delete the page");
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(title);
	}

	}