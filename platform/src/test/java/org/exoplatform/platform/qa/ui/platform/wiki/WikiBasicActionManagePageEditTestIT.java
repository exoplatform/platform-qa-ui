package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_DRAFT_NEW_PAGE;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TREE_NAME_WIKI;
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
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiDraftPage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;

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

}
