package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_SEARCH_NORESULT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;

@Tag("wiki")
@Tag("sniff")
public class WikiSearchTestIT extends Base {
  /**
   * <li>Case ID:122814.</li>
   * <li>Test Case Name: Quick search.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition: Some wiki pages already exists and contain the word
   * "cluster" in the title and content</li>
   */

  HomePagePlatform  homePagePlatform;

  WikiHomePage      wikiHomePage;

  RichTextEditor    richTextEditor;

  WikiManagement    wikiManagement;

  NavigationToolbar navigationToolbar;

  WikiSearch        wikiSearch;

  WikiSettingPage   wikiSettingPage;

  ManageLogInOut    manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    richTextEditor = new RichTextEditor(this);
    wikiManagement = new WikiManagement(this);
    wikiSearch = new WikiSearch(this);
    navigationToolbar = new NavigationToolbar(this);
    wikiSettingPage = new WikiSettingPage(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }


  /*
   * Step Number: 1 Step Name: Step 1: Quick Search Step Description: - Log in and
   * access Wiki app - Input the text "cluster"into Search box - Hit Enter key
   * Input Data: Expected Outcome: The Search Screen is displayed and the results
   * matching the keyword into search box are displayed.
   */
  @Test
  public void test01_QuickSearch() {
    info("Test 1: Quick search");
    String wiki = "wiki" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    wikiHomePage.goTosearchPage(wiki);

    info("Verify that search page is shown with the text");
    $(byClassName("resultInfo")).find(byText(wiki)).should(Condition.exist);

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(wiki);
  }

  /*
   * Step Number: 1 Step Name: Step 1: Do quick search Step Description: - Log in
   * and access Wiki app - Input the word "cluster" into the Search box - Press
   * Enter key Input Data: Expected Outcome: The Search Screen is displayed and
   * the results matching the keyword into search box are displayed. Step number:
   * 2 Step Name: Step 2: Advanced Search Step Description: - In Search screen
   * form: input keyword, select space in drop down list and click on Search
   * button Input Data: Expected Outcome: Search results will list pages that
   * matches with keyword and selected space
   */
  @Test
  public void test02_AdvancedSearch() {
    info("Test 2: Advanced search");

    String wiki = "wiki" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    wikiHomePage.goTosearchPage(wiki);
    info("Verify that search page is shown with the text");
    $(byClassName("resultInfo")).find(byText(wiki)).should(Condition.exist);
    wikiSearch.advancedSearch("", "My Wiki");
    info("Verify that the searched results is listed that matches with keyword and selected location");
    $(ELEMENT_SEARCH_NORESULT).waitUntil(Condition.appears, Configuration.timeout);

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(wiki);
  }

  @Test
  public void test03_AdvancedSearchWithExistPage() {
    info("Test 3: Advanced search With Exist Page");

    String title1 = "title1" + getRandomNumber();
    navigationToolbar.goToMyWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, title1);
    wikiManagement.saveAddPage();
    $(byText(title1)).should(Condition.exist);

    String wiki = "wiki" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    wikiHomePage.goTosearchPage(wiki);
    info("Verify that search page is shown with the text");
    $(byClassName("resultInfo")).find(byText(wiki)).should(Condition.exist);

    wikiSearch.advancedSearch("", "My Wiki");

    info("Verify that the searched results is listed that matches with keyword and selected location");
    $(ELEMENT_SEARCH_NORESULT).waitUntil(Condition.appears, Configuration.timeout);

    info("Delete the page");
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(wiki);
  }

  /*
   * Step Number: 1 Step Name: Step 1: Search Template Step Description: - Log in
   * and access Wiki app - Click on Browse at top right and select Wiki Settings
   * action - Input keyword "test" for ex into Search box - Hit Enter key Input
   * Data: Expected Outcome: The Search results matching keyword into search box
   * are displayed.
   */

  @Test
  public void test04_SearchTemplate() {
    info("Test 4: Search template");

    String template = "HOW-TO Guide";
    homePagePlatform.goToWiki();
    wikiHomePage.goToWikiSettingPage();
    wikiSettingPage.searchTemplate(template);

  }
}
