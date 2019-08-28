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


  /**
   * <li>Case ID:122841.</li>
   * <li>Test Case Name:Auto Save when adding page from template.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /**
   * Step Number: 1 Step Name: Step 1: Check auto save when add page Step
   * Description: - Go to wiki - Click [Add Page] -> [Blank Page] in toolbar
   * action - Input valid data for Title page and Page's content (Ex: Title -Auto
   * Save, Content - Auto Save) Input Data: Expected Outcome: - The draft will be
   * saved automatically after 30 seconds (default value) only if there's any
   * modifications in the content or page title. After the draft was saved, a
   * notification message will be showed near the title.
   */

  @Test
  @Tag("wabis")
  public void test03_AutoSaveWhenAddingPage() {
    info("Test 3: Auto Save when adding page");

    String title = "Wiki" + getRandomNumber();
    String content = "Content" + getRandomNumber();

    info("Go to Wiki app");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePageWithAutoSaveStatus(title, content);
    wikiManagement.cancelAddPage();
    $(ELEMENT_CONFIRMATION_POPUP_YES_BTN).click();
    info("Go to my draft");
    $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).click();
    $(ELEMENT_SEARCH_BROWSERS_MY_DRAFT).click();
    wikidraftpage.deleteDraft();
    info("Delete the page");

  }

  /*
   * Step Number: 1 Step Name: Step 1: Preview template Step Description: - Go to
   * wiki porlet - Click on Add Page -> From Template icon in toolbar action -
   * Choose a template in list and click Preview Input Data: Expected Outcome:
   * Show layout which you choose
   */
  @Test
  public void test05_PreviewTemplateWhenAddingNewPageFromTemplate() {
    info("Test 05: Preview template when adding new page from template");
    String title = "title1" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.previewATemplate(title);
  }

  @Test
  public void test06_AutoSaveWhenAddingPageFromHowToGuideTemplate() {
    info("Test 06: Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_HowToGuide, title);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }

  @Test
  public void test07_AutoSaveWhenAddingPageFromThreeColumnLayoutTemplate() {
    info("Test 06: Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_ThreeColumnLayout, title);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }

  @Test
  public void test08_AutoSaveWhenAddingPageFromStatusMeetingTemplate() {
    info("Test 06: Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_StatusMeeting, title);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }

  @Test
  public void test09_AutoSaveWhenAddingPageFromLeavePlanningTemplate() {
    info("Test 06: Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_LeavePlanning, title);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
  }

  @Test
  @Tag("wikis")
  public void test10_AutoSaveWhenAddingPageFromTwoColumnLayoutTemplate() {
    info("Test 06: Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_TwoColumnLayout, title);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
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
  @Tag("wikis")
  public void test07_11_Resume_Delete_ADraftWithSaveAsNormal() {
    info("Test 07: Resume a draft with save as normal");
    String title = "title" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePageHasAutoSaveWithoutSave(title, title);

    wikiHomePage.goToMyDraft();
    info("The draft is displayed in the list");
    wikidraftpage.resumeADraft(title);
    info("The page in edit mode is displayed");
    richTextEditor.editSimplePage(newTitle, newTitle);
    wikiManagement.saveAddPage();
    info("Verify that the new page is added");
    ELEMENT_TREE_NAME_WIKI.find(byText(newTitle)).should(Condition.exist);
    info("Verify that the new page is not shown in my draft list");
    wikiHomePage.goToMyDraft();
    ELEMENT_DRAFT_NEW_PAGE.find(byText(title + "(New Page)")).shouldNot(Condition.exist);

    info("Test 11: Delete a draft");
    wikidraftpage.deleteDraft();
    info("Delete the page ");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle);
  }

  @Test
  @Tag("wabis")
  public void Auto_save_message() {
    info("Test: Check auto save message");
    String title = "title" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.checkAutoSaveMessage(title, title);
    wikiHomePage.goToMyDraft();
    info("The draft is displayed in the list");
    info("Test 11: Delete a draft");
    wikidraftpage.deleteDraft();
    info("Delete the page ");
    homePagePlatform.goToWiki();
  }

  @Test
  public void test06_1_AutoSaveWhenAddingPageFromHowToGuideTemplateOnSpace() {
    info("Test 06: Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_HowToGuide, title);
    info("Delete the page");
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  @Tag("wabis")
  public void test06_2_AutoSaveWhenAddingPageFromThreeColumnLayoutTemplateOnSpace() {
    info("Test 06: Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
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
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
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
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimplePageByTemplateWithAutoSave(ELEMENT_SELECT_TEMPLATE_LeavePlanning, title);
    info("Delete the page");
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  @Tag("wikis")
  public void test06_5_AutoSaveWhenAddingPageFromTwoColumnLayoutOnSpace() {
    info("Test 06: Auto Save when adding page from template");
    String title = "title1" + getRandomNumber();
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
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
   * navigation tree Step Number: 1 Step Name: Step 1: Delete page with template
   * layout Step Description: - Open an existing page by clicking on page name in
   * navigation tree. - Click on More icon on toolbar and select Delete page
   * action in menu - Click on OK button on Confirm message form Input Data:
   * Expected Outcome: Delete page successfully
   */

  @Test
  @Tag("wikis")
  public void test04CreatePageFromTemplateOnSpace() {
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
   * navigation tree Step Number: 1 Step Name: Step 1: Delete page with template
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
