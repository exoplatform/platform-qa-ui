package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
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
     manageLogInOut.signInCas(username, password);
  }


  /**
   * <li>Case ID:122819.</li>
   * <li>Test Case Name: Edit page with Source Editor.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /**
   * <li>Case ID:122808.</li>
   * <li>Test Case Name:Edit Paragraph in Page.</li>
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
  public void test02_EditPageUsingSourceEditorAndEditParagraphInPage() {
    info("Test 02: Edit page with Source Editor");
    String title = "title1" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();
    String content = "== " + title + " ==";
    String content2 = "=== " + title2 + " ===";

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, title);
    wikiManagement.saveAddPage();
    ELEMENT_TREE_NAME_WIKI.find(byText(title)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    info("Verify that the page is editor successfully");
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title2, title2);
    wikiManagement.saveAddPage();
    ELEMENT_DRAFT_NEW_PAGE.find(byText(title + "(New Page)")).waitUntil(Condition.not(Condition.exist), Configuration.openBrowserTimeoutMs);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title2);
    $(byText(title2)).waitUntil(Condition.not(Condition.exist),Configuration.openBrowserTimeoutMs);
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    $(byText(title)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    wikiManagement.editParagraph(title, content2);
    wikiManagement.saveAddPage();
    $(byText(title2)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    wikiHomePage.deleteWiki(title);

  }

  /**
   * <li>Case ID:122836.</li>
   * <li>Test Case Name: Edit Page with template layout.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test05_EditPageFromTemplateHowToGuideThreeColumnLayoutStatusMeetingLeavePlanningTwoColumnLayoutTemplates() {
    info("Test 04:  Edit  Page with template layout");
    String title = "title" + getRandomNumber();
    String title1 = "title1" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();
    String title3 = "title3" + getRandomNumber();
    String title4 = "title4" + getRandomNumber();
    String title5 = "title5" + getRandomNumber();

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
    richTextEditor.editSimplePage(title1, title1);
    wikiManagement.saveAddPage();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(ELEMENT_SELECT_TEMPLATE_ThreeColumnLayout, title);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title2, title2);
    wikiManagement.saveAddPage();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(ELEMENT_SELECT_TEMPLATE_StatusMeeting, title);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title3, title3);
    wikiManagement.saveAddPage();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(ELEMENT_SELECT_TEMPLATE_LeavePlanning, title);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title4, title4);
    wikiManagement.saveAddPage();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddTemplateWikiPage();
    wikiManagement.addSimpleWikiPageByTemplate(ELEMENT_SELECT_TEMPLATE_TwoColumnLayout, title);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(title5, title5);
    wikiManagement.saveAddPage();

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title2);
    wikiHomePage.deleteWiki(title3);
    wikiHomePage.deleteWiki(title4);
    wikiHomePage.deleteWiki(title5);

  }

  /**
   * <li>Case ID:122847.</li>
   * <li>Test Case Name:Edit page when publish activity is checked.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @Tag("wabis")
  public void test06_EditPageWhenPublishActivityIsCheckedAndWhenPublishActivityIsNotChecked() {
    info("Test 06: Edit page when publish activity is checked");
    String title = "title" + getRandomNumber();
    String title1 = "title" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String newTitle1 = "newTitle" + getRandomNumber();
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
    $(byText(title)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);

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
    $(byText(newTitle)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);

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
    richTextEditor.editSimplePage(newTitle, newTitle);
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

    /*
     * Step Number: 1 Step Name: Step 1: Add new page Step Description: - Go to wiki
     * page - Add new page Input Data: Expected Outcome: - Add new page successfully
     */
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.editSimplePage(title1, "");
    wikiManagement.saveAddPage();
    $(byText(title1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    /*
     * Step Number: 2 Step Name: Step 2: Edit page ( title of page) Step
     * Description: - Select page above - Click Edit page - Change something in
     * title - UnCheck Publish Activity - Click Save Input Data: Expected Outcome: -
     * Edit page successfully
     */
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage(newTitle1, "");
    wikiManagement.saveAddPage();
    $(byText(newTitle1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    /*
     * Step Number: 3 Step Name: Step 3: Check Publish activity Step Description: -
     * Go to Homepage Input Data: Expected Outcome: Don't show activity of page
     * after editing
     */
    homePagePlatform.goToHomePage();
    $(byText(title1)).parent().parent().parent().parent().find(byText(comment)).waitUntil(Condition.not(Condition.exist),Configuration.openBrowserTimeoutMs);

    /*
     * Step Number: 4 Step Name: Step 4: Edit page ( content of page) Step
     * Description: - Select page above - Click Edit page - Change something in
     * content - UnCheck Publish Activity - Click Save Input Data: Expected Outcome:
     * - Edit page successfully.
     */

    homePagePlatform.goToWiki();
    wikiHomePage.goToAPage(newTitle1);
    wikiHomePage.goToEditPage();
    richTextEditor.editSimplePage("", newTitle1);
    wikiManagement.saveAddPage();
    $(byText(newTitle1)).should(Condition.exist);
    /*
     * Step Number: 5 Step Name: Step 5: Check Publish activity Step Description: -
     * Go to Homepage Input Data: Expected Outcome: Don't show activity of page
     * after editing
     */
    homePagePlatform.goToHomePage();
    $(byText(title1)).parent().parent().parent().parent().find(byText(comment)).waitUntil(Condition.not(Condition.exist),Configuration.openBrowserTimeoutMs);
    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(newTitle);
    wikiHomePage.deleteWiki(newTitle1);
  }

}
