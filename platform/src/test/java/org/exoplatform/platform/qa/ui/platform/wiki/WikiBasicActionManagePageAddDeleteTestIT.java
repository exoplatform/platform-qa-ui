package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiDraftPage;
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
public class WikiBasicActionManagePageAddDeleteTestIT extends Base {
	HomePagePlatform homePagePlatform;

	WikiHomePage wikiHomePage;


	WikiManagement wikiManagement;

	NavigationToolbar navigationToolbar;

	RichTextEditor richTextEditor;

	WikiDraftPage wikidraftpage;
	SpaceManagement spaceManagement;
	SpaceHomePage spaceHomePage;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		homePagePlatform = new HomePagePlatform(this);
		wikiHomePage = new WikiHomePage(this);
		wikiManagement = new WikiManagement(this);
		navigationToolbar = new NavigationToolbar(this);

		wikidraftpage = new WikiDraftPage(this);

		richTextEditor = new RichTextEditor(this);
		spaceManagement=new SpaceManagement(this);
		spaceHomePage=new SpaceHomePage(this);

		}

	@Test
	public void test06_1_AutoSaveWhenAddingPageFromHowToGuideTemplateOnSpace() {
		info("Test 06: Auto Save when adding page from template");
		String title = "title1" + getRandomNumber();
		String space = "space" + getRandomNumber();
		homePagePlatform.goToAllSpace();
		spaceManagement.addNewSpaceSimple(space, space, 6000);
		homePagePlatform.goToSpecificSpace(space);
		spaceHomePage.goToWikiTab();
		wikiHomePage.goToAddTemplateWikiPage();
		wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_HowToGuide, title);
		info("Delete the page");
		homePagePlatform.goToAllSpace();
		spaceManagement.deleteSpace(space, false);
	}
	@Test
	public void test06_2_AutoSaveWhenAddingPageFromThreeColumnLayoutTemplateOnSpace() {
		info("Test 06: Auto Save when adding page from template");
		String title = "title1" + getRandomNumber();
		String space = "space" + getRandomNumber();
		homePagePlatform.goToAllSpace();
		spaceManagement.addNewSpaceSimple(space, space, 6000);
		homePagePlatform.goToSpecificSpace(space);
		spaceHomePage.goToWikiTab();
		wikiHomePage.goToAddTemplateWikiPage();
		wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_ThreeColumnLayout, title);
		info("Delete the page");
		homePagePlatform.goToAllSpace();
		spaceManagement.deleteSpace(space, false);
	}
	@Test
	public void test06_3_AutoSaveWhenAddingPageFromStatusMeetingTemplateOnSpace() {
		info("Test 06: Auto Save when adding page from template");
		String title = "title1" + getRandomNumber();
		String space = "space" + getRandomNumber();
		homePagePlatform.goToAllSpace();
		spaceManagement.addNewSpaceSimple(space, space, 6000);
		homePagePlatform.goToSpecificSpace(space);
		spaceHomePage.goToWikiTab();
		wikiHomePage.goToAddTemplateWikiPage();
		wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_StatusMeeting, title);
		info("Delete the page");
		homePagePlatform.goToAllSpace();
		spaceManagement.deleteSpace(space, false);
	}
	@Test
	public void test06_4_AutoSaveWhenAddingPageFromLeavePlanningTemplateOnSpace() {
		info("Test 06: Auto Save when adding page from template");
		String title = "title1" + getRandomNumber();
		String space = "space" + getRandomNumber();
		homePagePlatform.goToAllSpace();
		spaceManagement.addNewSpaceSimple(space, space, 6000);
		homePagePlatform.goToSpecificSpace(space);
		spaceHomePage.goToWikiTab();
		wikiHomePage.goToAddTemplateWikiPage();
		wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_LeavePlanning, title);
		info("Delete the page");
		homePagePlatform.goToAllSpace();
		spaceManagement.deleteSpace(space, false);
	}
	@Test
	public void test06_5_AutoSaveWhenAddingPageFromTwoColumnLayoutOnSpace() {
		info("Test 06: Auto Save when adding page from template");
		String title = "title1" + getRandomNumber();
		String space = "space" + getRandomNumber();
		homePagePlatform.goToAllSpace();
		spaceManagement.addNewSpaceSimple(space, space, 6000);
		homePagePlatform.goToSpecificSpace(space);
		spaceHomePage.goToWikiTab();
		wikiHomePage.goToAddTemplateWikiPage();
		wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_TwoColumnLayout, title);
		info("Delete the page");
		homePagePlatform.goToAllSpace();
		spaceManagement.deleteSpace(space, false);
	}
	}

