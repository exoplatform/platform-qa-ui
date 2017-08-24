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


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiDraftPage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;

@Tag("wiki")
@Tag("smoke")
public class WikiBasicActionManagePageAddDeleteTestIT extends Base {
  HomePagePlatform  homePagePlatform;

  WikiHomePage      wikiHomePage;

  WikiManagement    wikiManagement;

  NavigationToolbar navigationToolbar;

  RichTextEditor    richTextEditor;

  WikiDraftPage     wikidraftpage;

  SpaceManagement   spaceManagement;

  SpaceHomePage     spaceHomePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    navigationToolbar = new NavigationToolbar(this);

    wikidraftpage = new WikiDraftPage(this);

    richTextEditor = new RichTextEditor(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);

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

  /**
   * <li>Case ID:122834.</li>
   * <li>Test Case Name: Create page from template.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1: Create Page from template Step Description:
   * - Go to wiki porlet - Click on Add Page -> From Template icon in toolbar
   * action - Choose a template in list and click Select - Click on Save icon in
   * toolbar Input Data: Expected Outcome: Page is added successful and listed in
   * navigation tree
   * Step Number: 1 Step Name: Step 1: Delete page with template
   * layout Step Description: - Open an existing page by clicking on page name in
   * navigation tree. - Click on More icon on toolbar and select Delete page
   * action in menu - Click on OK button on Confirm message form Input Data:
   * Expected Outcome: Delete page successfully
   */

  @Test
  public void test04CreatePageFromTemplateOnSpace() {
    info("Test 04: Create page from template");
    SelenideElement template = ELEMENT_SELECT_TEMPLATE_StatusMeeting;
    String title = "title1" + getRandomNumber();
    String space = "space" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    homePagePlatform.goToSpecificSpace(space);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(template, title);
    $(byText(title)).should(Condition.exist);

    info("Test 10: Delete data");
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);
  }

  /**
   * <li>Case ID:122834.</li>
   * <li>Test Case Name: Create page from template.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1: Create Page from template Step Description:
   * - Go to wiki porlet - Click on Add Page -> From Template icon in toolbar
   * action - Choose a template in list and click Select - Click on Save icon in
   * toolbar Input Data: Expected Outcome: Page is added successful and listed in
   * navigation tree
   * Step Number: 1 Step Name: Step 1: Delete page with template
   * layout Step Description: - Open an existing page by clicking on page name in
   * navigation tree. - Click on More icon on toolbar and select Delete page
   * action in menu - Click on OK button on Confirm message form Input Data:
   * Expected Outcome: Delete page successfully
   */

  @Test
  public void test10DeteyePageFromTemplateOnSpace() {
    info("Test 04: Create page from template");
    SelenideElement template = ELEMENT_SELECT_TEMPLATE_StatusMeeting;
    String title = "title1" + getRandomNumber();
    String space = "space" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    homePagePlatform.goToSpecificSpace(space);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(template, title);
    $(byText(title)).should(Condition.exist);
    wikiHomePage.deleteWiki(title);

    info("Test 10: Delete data");
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);
  }
}
