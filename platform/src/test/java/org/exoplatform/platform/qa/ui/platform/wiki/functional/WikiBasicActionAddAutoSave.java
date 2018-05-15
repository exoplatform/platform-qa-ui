package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;

/**
 * Created by exo on 5/11/18.
 */

@Tag("wiki")
@Tag("functional")

public class WikiBasicActionAddAutoSave extends Base {

  HomePagePlatform homePagePlatform;

  WikiHomePage     wikiHomePage;

  WikiManagement   wikiManagement;

  SourceTextEditor sourceTextEditor;

  ManageLogInOut   manageLogInOut;

  WikiValidattions wikiValidattions;

  SpaceManagement  spaceManagement;

  WikiDraftPage    wikiDraftPage;

  @BeforeEach
  public void setupBeforeMethod() {

    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    manageLogInOut = new ManageLogInOut(this);
    spaceManagement = new SpaceManagement(this);
    wikiDraftPage = new WikiDraftPage(this);
    wikiValidattions = new WikiValidattions(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:139436.</li>
   * <li>Test Case Name: Add a Draft to Wiki Intranet.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add new page Step
   * Description: - Login portal - Connect to [Intranet] - -> [Wiki] - Click [Add
   * page] - -> [Blank Page] - Enter title and content for the Wiki page Input
   * Data: Expected Outcome: - An empty template is displayed - The draft is
   * created after 30sA message is displayed "Draft saved at HH:MM"
   */
  @Test
  public void test01_AddADraftToWikiIntranet() {
    info("Test 1: Add a Draft to Wiki Intranet");

    info("Create a draf wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePageHasAutoSaveWithoutSave(title, content);
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyTitleDrafPage(title);
    wikiDraftPage.deleteDraft(title);
  }

  /**
   * <li>Case ID:139438.</li>
   * <li>Test Case Name: Autosave a page without Title.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Create a wiki page
   * Step Description: - Login portal - Connect to [Intranet] - -> [Wiki] - Click
   * [Add page] - -> [Blank Page]/[From Template...] - Add a page wiki and keep
   * the field "Title" empty - Keep the page without saving for 30s Input Data:
   * Expected Outcome: - A draft is saved - A page is saved with a generic title
   * within the format: "Untitled_YearMonthDayTime."
   */
  @BugInPLF("WIKI-1417")
  @Test
  public void test02_AutosaveAPageWithoutTitle() {
    info("Test 2: Autosave a page without Title");

    info("Create a draf wiki page");
    String content = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePageHasAutoSaveWithoutSave("", content);

    info("A page is saved with a generic title within the format: 'Untitled_YearMonthDayTime.''");
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyTitleDrafPage("");
    wikiHomePage.goToMyDraft();
    wikiDraftPage.deleteDraft("");

  }

  /**
   * <li>Case ID:139549.</li>
   * <li>Test Case Name: Add a Draft to Wiki space.</li>
   * <li>Pre-Condition: create a space or join a space with an application wiki
   * available</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Select wiki of
   * space Step Description: - Connect to a space and choose the [Wiki] tab on the
   * space navigation bar Input Data: Expected Outcome: The wiki application is
   * displayed Step number: 2 Step Name: Step 2: Add wiki's page for space Step
   * Description: - Choose [Add page] -> [Blank Page] - Add Title - Add a content
   * Input Data: Expected Outcome: - An empty template is displayed - The draft is
   * created after 30sA message is displayed "Draft saved at HH:MM"
   */
  @Test
  public void test03_AddADraftToWikiSpace() {
    info("Test 3: Add a Draft to Wiki space");
    info("Create a space");
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);
    info("Create a wiki page 1");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToSpecificSpace(space);
    spaceManagement.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePageHasAutoSaveWithoutSave(title, content);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  /**
   * <li>Case ID:139550.</li>
   * <li>Test Case Name: Resume a draft after loosing a session.</li>
   * <li>Pre-Condition: edit a page</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Create wiki page
   * Step Description: - Go to [Intranet] - -> [Wiki] - Create/Edit a wiki page
   * without saving for 30s Input Data: Expected Outcome: - A draft is created
   * after 30s Step number: 2 Step Name: Step 2: Session work expired Step
   * Description: - Keep the session opened until time expiration Input Data:
   * Expected Outcome: - The session is expired Step number: 3 Step Name: Step 3:
   * Autosave a page as draft Step Description: - Re-connect to the session - Go
   * to "My draft" Input Data: Expected Outcome: - The draft is displayed in the
   * list Step number: 4 Step Name: Step 4: Check show draft page Step
   * Description: - Click on the link "Title" of the draft Input Data: Expected
   * Outcome: The page is displayed in edit mode and the last saved content is
   * displayed
   */
  @BugInPLF("WIKI-1418")
  @Test
  public void test04_ResumeADraftAfterLoosingASession() {
    info("Test 4: Resume a draft after loosing a session");
    info("Create a draf wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePageHasAutoSaveWithoutSave(title, content);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToWiki();
    wikiHomePage.goToMyDraft();
    wikiValidattions.verifyTitleDrafPage(title);
    wikiDraftPage.resumeADraft(title);
    wikiManagement.goToSourceEditor();
    wikiValidattions.verifyResumADraf(title, content);
    wikiHomePage.goToMyDraft();
    wikiDraftPage.deleteDraft(title);

  }

}
