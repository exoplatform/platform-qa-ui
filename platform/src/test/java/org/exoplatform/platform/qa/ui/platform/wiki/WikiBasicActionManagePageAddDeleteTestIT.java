package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiDraftPage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;

@Tag("wiki")
@Tag("sniff")
public class WikiBasicActionManagePageAddDeleteTestIT extends Base {
  HomePagePlatform  homePagePlatform;

  WikiHomePage      wikiHomePage;

  WikiManagement    wikiManagement;

  NavigationToolbar navigationToolbar;

  RichTextEditor    richTextEditor;

  WikiDraftPage     wikidraftpage;

  SpaceManagement   spaceManagement;

  SpaceHomePage     spaceHomePage;

  ManageLogInOut    manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    manageLogInOut = new ManageLogInOut(this);
    wikiManagement = new WikiManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    wikidraftpage = new WikiDraftPage(this);
    richTextEditor = new RichTextEditor(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }

  /*
   * Step Number: 1 Step Name: Step 1: Preview template Step Description: - Go to
   * wiki porlet - Click on Add Page -> From Template icon in toolbar action -
   * Choose a template in list and click Preview Input Data: Expected Outcome:
   * Show layout which you choose
   */

  @Test
  public void test06_AutoSaveWhenAddingPageFromHowToGuideThreeColumnLayoutStatusMeetingLeavePlanningTwoColumnLayoutTemplates() {
    info("Test 06: Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();
    String title3 = "title3" + getRandomNumber();
    String title4 = "title4" + getRandomNumber();
    String title5 = "title5" + getRandomNumber();
    String template1 = "HOW-TO Guide";
    String template2 = "Three-Column Layout";
    String template3 = "Status Meeting";
    String template4 = "Leave Planning";
    String template5 = "Two-Column Layout";

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.previewATemplate(template1);
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_HowToGuide, title);
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.previewATemplate(template2);
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_ThreeColumnLayout, title2);
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.previewATemplate(template3);
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_StatusMeeting, title3);
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.previewATemplate(template4);
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_LeavePlanning, title4);
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.previewATemplate(template5);
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_TwoColumnLayout, title5);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
    wikiHomePage.deleteWiki(title2);
    wikiHomePage.deleteWiki(title3);
    wikiHomePage.deleteWiki(title4);
    wikiHomePage.deleteWiki(title5);

  }

  /**
   * <li>Case ID:122852.</li>
   * <li>Test Case Name:Resume a draft with save as normal.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1: Add new page Step Description: - Go to
   * Intranet/Wiki - Add a page Input Data: Expected Outcome: A draft version is
   * saved after 30s. Step Number: 2 Step Name: Step 2: Resume a draft with save
   * as normal Step Description: - Close a window of the browser without saving of
   * the page - Go to "My drafts" - Click on the link "title" of the draft -
   * Change title or content of page and click Save Input Data: Expected Outcome:
   * - The draft is displayed in the list - The page in edit mode is displayed -
   * New page is added and don't show in "My draft" list. Step Number: 1 Step
   * Name: Step 1: Delete a draft Step Description: - Go to Browser -> My drafts -
   * Select a page and click delete draft - A pop up is displayed
   * "Are you sure to delete this draft?" - Click OK button Input Data: Expected
   * Outcome: Delete draft successfully and don't show in draft list
   */
  @Test
  public void test07_11_Resume_Delete_ADraftWithSaveAsNormal() {
    info("Test 07: Resume a draft with save as normal");
    String title = "title" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();
    String title3 = "title3" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePageHasAutoSaveWithoutSave(title2, title2);
    wikiHomePage.goToMyDraft();
    ELEMENT_DRAFT_NEW_PAGE.find(byText(title2 + "(New Page)")).waitUntil(Condition.exist, Configuration.timeout);
    info("The draft is displayed in the list");
    wikidraftpage.resumeADraft(title2);
    info("The page in edit mode is displayed");
    richTextEditor.editSimplePageWithAutoSave(title3, title3);
    executeJavaScript("window.scrollBy(0,-5500)", "");
    wikiHomePage.goToMyDraft();
    ELEMENT_DRAFT_NEW_PAGE.find(byText(title2 + "(New Page)")).waitUntil(Condition.not(Condition.exist), Configuration.timeout);
    ELEMENT_DRAFT_NEW_PAGE.find(byText(title3 + "(New Page)")).waitUntil(Condition.exist, Configuration.timeout);
    wikidraftpage.deleteDraft();
    info("Delete the page ");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePageHasAutoSaveWithoutSave(title, title);
    wikiHomePage.goToMyDraft();
    info("The draft is displayed in the list");
    ELEMENT_DRAFT_NEW_PAGE.find(byText(title + "(New Page)")).waitUntil(Condition.exist, Configuration.timeout);
    wikidraftpage.resumeADraft(title);
    info("The page in edit mode is displayed");
    richTextEditor.editSimplePage(newTitle, newTitle);
    wikiManagement.saveAddPage();
    info("Verify that the new page is added");
    ELEMENT_TREE_NAME_WIKI.find(byText(newTitle)).waitUntil(Condition.exist, Configuration.timeout);
    info("Verify that the new page is not shown in my draft list");
    wikiHomePage.goToMyDraft();
    ELEMENT_DRAFT_NEW_PAGE.find(byText(title + "(New Page)")).waitUntil(Condition.not(Condition.exist), Configuration.timeout);
    info("Test 11: Delete a draft");
    wikidraftpage.deleteDraft();
    info("Delete the page ");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle);
  }

  @Test
  public void test06_1_AutoSaveWhenAddingPageFromHowToGuideThreeColumnLayoutStatusMeetingLeavePlanningTwoColumnLayoutTemplatesOnSpace() {
    info("Test 06: Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();
    String title3 = "title3" + getRandomNumber();
    String title4 = "title4" + getRandomNumber();
    String title5 = "title4" + getRandomNumber();
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_HowToGuide, title);
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_ThreeColumnLayout, title2);
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_StatusMeeting, title3);
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_LeavePlanning, title4);
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_TwoColumnLayout, title5);
    info("Delete the page");
    homePagePlatform.goToWiki();
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
   * navigation tree Step Number: 1 Step Name: Step 1: Delete page with template
   * layout Step Description: - Open an existing page by clicking on page name in
   * navigation tree. - Click on More icon on toolbar and select Delete page
   * action in menu - Click on OK button on Confirm message form Input Data:
   * Expected Outcome: Delete page successfully
   */

  @Test
  public void test04CreateDeletePageFromStatusMeetingTemplateOnSpace() {
    info("Test 04: Create page from template");
    SelenideElement template = ELEMENT_SELECT_TEMPLATE_StatusMeeting;
    String title = "title1" + getRandomNumber();
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
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
