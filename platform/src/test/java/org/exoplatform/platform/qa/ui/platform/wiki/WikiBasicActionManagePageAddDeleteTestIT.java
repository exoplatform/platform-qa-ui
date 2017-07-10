package org.exoplatform.platform.qa.ui.platform.wiki;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
@Tag("wiki")
@Tag("smoke")
@Tag("test")
public class WikiBasicActionManagePageAddDeleteTestIT extends Base {
	HomePagePlatform homePagePlatform;
	WikiHomePage wikiHomePage;
	ManageLogInOut manageLogInOut;
	WikiManagement wikiManagement;
	NavigationToolbar navigationToolbar;


	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		homePagePlatform = new HomePagePlatform(this);
		wikiHomePage = new WikiHomePage(this);
		manageLogInOut = new ManageLogInOut(this);
		wikiManagement = new WikiManagement(this);
		navigationToolbar = new NavigationToolbar(this);
	}
	/**
	 *<li> Case ID:122841.</li>
	 *<li> Test Case Name:Auto Save when adding page from template.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_AutoSaveWhenAddingPageFromHowToGuideTemplate() {
		info("Test 06: Auto Save when adding page from template");
		String title = "title1" + getRandomNumber();
		
			homePagePlatform.goToWiki();
			wikiHomePage.goToAddTemplateWikiPage();
			wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_HowToGuide,title);
			info("Delete the page");
			homePagePlatform.goToWiki();
			wikiHomePage.deleteWiki(title);
	}
	@Test
	public  void test07_AutoSaveWhenAddingPageFromThreeColumnLayoutTemplate() {
		info("Test 06: Auto Save when adding page from template");
		String title = "title1" + getRandomNumber();

		homePagePlatform.goToWiki();
		wikiHomePage.goToAddTemplateWikiPage();
		wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_ThreeColumnLayout,title);
		info("Delete the page");
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(title);
	}
	@Test
	public  void test08_AutoSaveWhenAddingPageFromStatusMeetingTemplate() {
		info("Test 06: Auto Save when adding page from template");
		String title = "title1" + getRandomNumber();

		homePagePlatform.goToWiki();
		wikiHomePage.goToAddTemplateWikiPage();
		wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_StatusMeeting,title);
		info("Delete the page");
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(title);
	}
	@Test
	public  void test09_AutoSaveWhenAddingPageFromLeavePlanningTemplate() {
		info("Test 06: Auto Save when adding page from template");
		String title = "title1" + getRandomNumber();

		homePagePlatform.goToWiki();
		wikiHomePage.goToAddTemplateWikiPage();
		wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_LeavePlanning,title);
		info("Delete the page");
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(title);
	}
	@Test
	public  void test10_AutoSaveWhenAddingPageFromTwoColumnLayoutTemplate() {
		info("Test 06: Auto Save when adding page from template");
		String title = "title1" + getRandomNumber();

		homePagePlatform.goToWiki();
		wikiHomePage.goToAddTemplateWikiPage();
		wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_TwoColumnLayout,title);
		info("Delete the page");
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(title);
	}
}