package org.exoplatform.platform.qa.ui.platform.plf;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PageEditor;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.QuickSearchResult;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.locator.QuickSearchResultLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("plf")
@Tag("sniff")
public class PlfUnifiedSearchTestIT extends Base{

NavigationToolbar navigationToolbar;
QuickSearchResult quickSearchResult;
ManageLogInOut manageLogInOut;
HomePagePlatform homePagePlatform;
ForumCategoryManagement forumCategoryManagement;
ForumForumManagement forumForumManagement;
ForumHomePage forumHomePage;
WikiHomePage wikiHomePage;
WikiManagement wikiManagement;
RichTextEditor richTextEditor;
	PageEditor pageEditor;
	ManageAlert manageAlert;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		navigationToolbar=new NavigationToolbar(this);
		quickSearchResult=new QuickSearchResult(this);
		homePagePlatform=new HomePagePlatform(this);
		forumCategoryManagement=new ForumCategoryManagement(this);
		forumForumManagement=new ForumForumManagement(this);
		forumHomePage=new ForumHomePage(this);
		wikiHomePage=new WikiHomePage(this);
		wikiManagement=new WikiManagement(this);
		richTextEditor=new RichTextEditor(this);
		manageLogInOut = new ManageLogInOut(this);
		manageAlert=new ManageAlert(this);
		pageEditor=new PageEditor(this);
		if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
			$(ELEMENT_SKIP_BUTTON).click();
		}
		if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
			manageLogInOut.signOut();
		}

		manageLogInOut.signInCas(username, password);
	}
	/**
	 *<li> Case ID:120868.</li>
	 *<li> Test Case Name: Quick Search.</li>
	 *<li> Pre-Condition: Some contents such as wiki, events, tasks, people...are existed, and contain text "cloud"</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_QuickSearch() {
		String name="cloud";
		info("Test 1: Quick Search");
		/*Step Number: 1
		 *Step Name: Quick search
		 *Step Description: 
			- Log in Intranet
			- Type the text "cloud" at Quick search box
		 *Input Data: 

		 *Expected Outcome: 
			- By default, quick search returns results for items 
			with All types located in the current site only, as attachment SearchResult.png*/
		homePagePlatform.goToForum();
		info("Add a category");
		forumCategoryManagement.addCategorySimple(name, "", name);
		info("Add a forum in the category");
		forumForumManagement.addForumSimple(name, "", name);

		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(name, name);
		wikiManagement.saveAddPage();
		homePagePlatform.goToHomePage();
		navigationToolbar.goToQuickSearch();
		quickSearchResult.search("cloud");
		waitForAndGetElement(ELEMENT_SEARCHRESULT_ALLTYPECHECK,5000,0);
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(name);
		homePagePlatform.goToForum();
		forumHomePage.goToHomeCategory();
		forumCategoryManagement.deleteCategory(name);

	}

	/**
	 *<li> Case ID:120869.</li>
	 *<li> Test Case Name: Configure quick search.</li>
	 *<li> Pre-Condition: There is a page containing a Quick search portlet</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_ConfigureQuickSearch() {
		info("Test 2: Configure quick search");
		/*Step Number: 1
		 *Step Name: Configure Quick search
		 *Step Description:
			- Login as admin, go to intranet home page
			- Open Quick search page
			- Click Edit this page
			- Click Edit portlet "Quick search"
			- Set value to fields
			- Click Save settings,
		 *Input Data:

		 *Expected Outcome:
			- Quick search settings screen is shown.
			- value is save*/
		navigationToolbar.goToQuickSearch();
		quickSearchResult.search("log");
		navigationToolbar.goToEditLayout();
		pageEditor.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		$(ELEMENT_EDIT_PORTLET_FORM_RESULTPERPAGE).selectOption("5");
		click(ELEMENT_EDIT_PORTLET_FORM_SAVESETTINGS_BUTTON);
		manageAlert.acceptAlert();
		click(ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON);
		click(ELEMENT_EDIT_PORTLET_FINISH);
		waitForAndGetElement(ELEMENT_QUICKSEARCHRESULT_NUMBEROFRESULT,5000,0);
	}
	
}