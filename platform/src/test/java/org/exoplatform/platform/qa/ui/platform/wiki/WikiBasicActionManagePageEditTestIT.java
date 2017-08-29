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
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;

@Tag("wiki")
@Tag("sniff")

public class WikiBasicActionManagePageEditTestIT extends Base {

  HomePagePlatform  homePagePlatform;

  WikiHomePage      wikiHomePage;

  ManageLogInOut    manageLogInOut;

  WikiManagement    wikiManagement;

  NavigationToolbar navigationToolbar;

  RichTextEditor    richTextEditor;

  WikiDraftPage     wikidraftpage;

  SourceTextEditor  sourceTextEditor;

  ActivityStream    activityStream;

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
    sourceTextEditor = new SourceTextEditor(this);
    activityStream = new ActivityStream(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(username, password);
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
  }

  /**
   * <li>Case ID:122819.</li>
   * <li>Test Case Name: Edit page with Source Editor.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1: Edit page with Source Editor Step
   * Description: - Open an existing page by clicking on page name in navigation
   * tree - Click on Edit icon in toolbar - Change content in full page and click
   * on Save icon in toolbar Input Data: Expected Outcome: Edit page successfully
   */
  @Test
  public void test02_EditPageUsingSourceEditor() {
    info("Test 02: Edit page with Source Editor");
    String title = "Wiki1" + getRandomNumber();
    String title2 = "Wiki2" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, title);
    wikiManagement.saveAddPage();
    ELEMENT_TREE_NAME_WIKI.find(byText(title)).should(Condition.exist);
    info("Verify that the page is editor successfully");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title2, title2);
    wikiManagement.saveAddPage();
    ELEMENT_DRAFT_NEW_PAGE.find(byText(title + "(New Page)")).shouldNot(Condition.exist);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title2);
  }

  /*
   * Step Number: 1 Step Name: Step 1: Check auto save when editing page Step
   * Description: - Go to Browser -> My draft - Select a page and click open it -
   * Edit title or content for it Input Data: Expected Outcome: While editing the
   * draft will be saved automatically after 30 seconds (default value) only if
   * there's any modifications in the content or page title.
   */
  @Test
  public void test03_AutoSaveWhenEditingPage() {
    info("Test 3: Auto save when editing page");

    String title = "Wiki1" + getRandomNumber();
    String title2 = "Wiki2" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePageHasAutoSaveWithoutSave(title, title2);

    wikiHomePage.goToMyDraft();
    info("The draft is displayed in the list");
    wikidraftpage.resumeADraft(title);
    info("The page in edit mode is displayed");
    richTextEditor.editSimplePageWithAutoSave(title, title2);

    info("Delete draf");
    wikiHomePage.goToMyDraft();
    wikidraftpage.deleteDraft(title2);
  }

  /**
   * <li>Case ID:122817.</li>
   * <li>Test Case Name: Edit Page with Rich Text Editor</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */

  @Test
  public void test01_EditPageUsingRichTextEditor() {
    info("Test 1: Edit Page with Rich Text Editor");
    String wiki = "wiki" + getRandomNumber();
    String wiki2 = "wiki2" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Step 1: Edit Page with Rich Text Editor Step
     * Description: - Open an existing page by clicking on page name in navigation
     * tree - Click on Edit icon in toolbar - Change content in full page and click
     * on Save icon in toolbar Input Data: Expected Outcome: Edit page successfully
     */

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    $(byText(wiki)).should(Condition.exist);
    info("Verify that the page is editor successfully");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(wiki2, wiki2);
    wikiManagement.saveAddPage();
    $(byText(wiki2)).should(Condition.exist);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(wiki2);
    $(byText(wiki2)).shouldNot(Condition.exist);
  }

  /**
   * <li>Case ID:122836.</li>
   * <li>Test Case Name: Edit Page with template layout.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test04_EditPageFromTemplateHow_To_Guide() {
    info("Test 04:  Edit  Page with template layout");
    String title = "title" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();
    /*
     * Step Number: 1 Step Name: Step 1: Edit Page with template layout Step
     * Description: - Open an existing page by clicking on page name in navigation
     * tree - Click on Edit icon in toolbar - Change content in full page and click
     * on Save icon in toolbar Input Data: Expected Outcome: Edit page successfully
     */
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(ELEMENT_SELECT_TEMPLATE_HowToGuide, title);

    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title2, title2);
    wikiManagement.saveAddPage();

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title2);
  }

  /**
   * <li>Case ID:122836.</li>
   * <li>Test Case Name: Edit Page with template layout.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test05_EditPageFromTemplateThree_Column_Layout() {
    info("Test 04:  Edit  Page with template layout");
    String title = "title" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();
    /*
     * Step Number: 1 Step Name: Step 1: Edit Page with template layout Step
     * Description: - Open an existing page by clicking on page name in navigation
     * tree - Click on Edit icon in toolbar - Change content in full page and click
     * on Save icon in toolbar Input Data: Expected Outcome: Edit page successfully
     */
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(ELEMENT_SELECT_TEMPLATE_ThreeColumnLayout, title);

    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title2, title2);
    wikiManagement.saveAddPage();

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title2);
  }

  /**
   * <li>Case ID:122836.</li>
   * <li>Test Case Name: Edit Page with template layout.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test06_EditPageFromTemplateStatus_Meeting() {
    info("Test 04:  Edit  Page with template layout");
    String title = "title" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();
    /*
     * Step Number: 1 Step Name: Step 1: Edit Page with template layout Step
     * Description: - Open an existing page by clicking on page name in navigation
     * tree - Click on Edit icon in toolbar - Change content in full page and click
     * on Save icon in toolbar Input Data: Expected Outcome: Edit page successfully
     */
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(ELEMENT_SELECT_TEMPLATE_StatusMeeting, title);

    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title2, title2);
    wikiManagement.saveAddPage();

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title2);
  }

  /**
   * <li>Case ID:122836.</li>
   * <li>Test Case Name: Edit Page with template layout.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test07_EditPageFromTemplateLeave_Planning() {
    info("Test 04:  Edit  Page with template layout");
    String title = "title" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();
    /*
     * Step Number: 1 Step Name: Step 1: Edit Page with template layout Step
     * Description: - Open an existing page by clicking on page name in navigation
     * tree - Click on Edit icon in toolbar - Change content in full page and click
     * on Save icon in toolbar Input Data: Expected Outcome: Edit page successfully
     */
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(ELEMENT_SELECT_TEMPLATE_LeavePlanning, title);

    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title2, title2);
    wikiManagement.saveAddPage();

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title2);
  }

  /**
   * <li>Case ID:122836.</li>
   * <li>Test Case Name: Edit Page with template layout.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test08_EditPageFromTemplateTwo_Column_Layout() {
    info("Test 04:  Edit  Page with template layout");
    String title = "title" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();
    /*
     * Step Number: 1 Step Name: Step 1: Edit Page with template layout Step
     * Description: - Open an existing page by clicking on page name in navigation
     * tree - Click on Edit icon in toolbar - Change content in full page and click
     * on Save icon in toolbar Input Data: Expected Outcome: Edit page successfully
     */
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(ELEMENT_SELECT_TEMPLATE_TwoColumnLayout, title);

    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title2, title2);
    wikiManagement.saveAddPage();

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title2);
  }

  /**
   * <li>Case ID:122808.</li>
   * <li>Test Case Name:Edit Paragraph in Page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test05_EditParagraphInPage() {
    info("Test 05: Edit Paragraph in Page");

    String title = "title" + getRandomNumber();
    String content = "== " + title + " ==";
    String title2 = "title2" + getRandomNumber();
    String content2 = "=== " + title2 + " ===";

    /*
     * Step Number: 1 Step Name: Step 1: Edit Paragraph Step Description: - Open an
     * exiting page that contains some paragraphs inside - Click on Edit icon
     * corresponding of paragraph that you want to edit - Change content and click
     * on Save icon in tool bar Input Data: Expected Outcome: - Selected paragraph
     * in page is edited successful and shown new content - Other paragraph's
     * content in page is remain
     */
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    // rtMode.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    $(byText(title)).should(Condition.exist);
    wikiManagement.editParagraph(title, content2);
    wikiManagement.saveAddPage();
    $(byText(title2)).should(Condition.exist);
    wikiHomePage.deleteWiki(title);
  }

  /**
   * <li>Case ID:122847.</li>
   * <li>Test Case Name:Edit page when publish activity is checked.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test06_EditPageWhenPublishActivityIsChecked() {
    info("Test 06: Edit page when publish activity is checked");
    String title = "title" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String comment = "Page's title has been updated to: " + newTitle;
    String comment1 = "Page's content has been edited.";

    /*
     * Step Number: 1 Step Name: Step 1: Add new page Step Description: - Go to wiki
     * page - Add new page Input Data: Expected Outcome: - Add new page successfully
     */
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, title);
    wikiManagement.saveAddPage();
    $(byText(title)).should(Condition.exist);

    /*
     * Step Number: 2 Step Name: Step 2: Edit page ( title of page) Step
     * Description: - Select page above - Click Edit page - Change title of page or
     * content of page - Check Publish Activity - Click Save Input Data: Expected
     * Outcome: - Edit page successfully
     */
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newTitle, "");
    wikiManagement.publishPageWhenEditPage();
    wikiManagement.saveAddPage();
    $(byText(newTitle)).should(Condition.exist);

    /*
     * Step Number: 3 Step Name: Step 3: Check Publish activity Step Description: -
     * Go to Homepage Input Data: Expected Outcome: - Show activity of page after
     * editing - Page's title has been updated to: $value.
     */
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(newTitle);
    activityStream.checkCommentOfActivity(newTitle, comment);

    /*
     * Step Number: 4 Step Name: Step 4: Edit page ( content of page) Step
     * Description: - Select page above - Click Edit page - Change content of page -
     * Check Publish Activity - Click Save Input Data: Expected Outcome: - Edit page
     * successfully.
     */

    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(newTitle);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage("", newTitle);
    wikiManagement.publishPageWhenEditPage();
    wikiManagement.saveAddPage();

    /*
     * Step Number: 5 Step Name: Step 5: Check Publish activity Step Description: -
     * Go to Homepage Input Data: Expected Outcome: - Show activity of page after
     * editing -Show "Page's content has been edited." in comment list.
     */
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(newTitle);
    activityStream.checkCommentOfActivity(newTitle, comment1);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle);
  }

  /**
   * <li>Case ID:122848.</li>
   * <li>Test Case Name:Edit page when publish activity is not checked.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test07_EditAPageWhenPublishActivityIsNotChecked() {
    info("Test 07: Edit page when publish activity is not checked");
    String title = "title" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String comment = "Page's title has been updated to: " + newTitle;
    String comment1 = "Page's content has been edited.";

    /*
     * Step Number: 1 Step Name: Step 1: Add new page Step Description: - Go to wiki
     * page - Add new page Input Data: Expected Outcome: - Add new page successfully
     */
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.editSimplePage(title, "");
    wikiManagement.saveAddPage();
    $(byText(title)).should(Condition.exist);
    /*
     * Step Number: 2 Step Name: Step 2: Edit page ( title of page) Step
     * Description: - Select page above - Click Edit page - Change something in
     * title - UnCheck Publish Activity - Click Save Input Data: Expected Outcome: -
     * Edit page successfully
     */
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newTitle, "");
    wikiManagement.saveAddPage();
    $(byText(newTitle)).should(Condition.exist);
    /*
     * Step Number: 3 Step Name: Step 3: Check Publish activity Step Description: -
     * Go to Homepage Input Data: Expected Outcome: Don't show activity of page
     * after editing
     */
    homePagePlatform.goToHomePage();
    $(byText(title)).parent().parent().parent().parent().find(byText(comment)).shouldNot(Condition.exist);
    /*
     * Step Number: 4 Step Name: Step 4: Edit page ( content of page) Step
     * Description: - Select page above - Click Edit page - Change something in
     * content - UnCheck Publish Activity - Click Save Input Data: Expected Outcome:
     * - Edit page successfully.
     */

    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(newTitle);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage("", newTitle);
    wikiManagement.saveAddPage();
    $(byText(newTitle)).should(Condition.exist);
    /*
     * Step Number: 5 Step Name: Step 5: Check Publish activity Step Description: -
     * Go to Homepage Input Data: Expected Outcome: Don't show activity of page
     * after editing
     */
    homePagePlatform.goToHomePage();
    $(byText(title)).parent().parent().parent().parent().find(byText(comment)).shouldNot(Condition.exist);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle);
  }
}
