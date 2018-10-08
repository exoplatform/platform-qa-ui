package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.QuickSearchResultLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PageEditor;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;

@Tag("plf")
@Tag("sniff")
public class PlfUnifiedSearchTestIT extends Base {

  NavigationToolbar       navigationToolbar;

  QuickSearchResult       quickSearchResult;

  ManageLogInOut          manageLogInOut;

  HomePagePlatform        homePagePlatform;

  ForumCategoryManagement forumCategoryManagement;

  ForumForumManagement    forumForumManagement;

  ForumHomePage           forumHomePage;

  WikiHomePage            wikiHomePage;

  WikiManagement          wikiManagement;

  RichTextEditor          richTextEditor;

  PageEditor              pageEditor;

  ManageAlert             manageAlert;

  SpaceManagement         spaceManagement;

  ActivityStream          activityStream;

  ForumTopicManagement    forumTopicManagement;

  SiteExplorerHome        siteExplorerHome;

  CreateNewDocument       createNewDocument;

  SpaceHomePage           spaceHomePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    quickSearchResult = new QuickSearchResult(this);
    homePagePlatform = new HomePagePlatform(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    forumForumManagement = new ForumForumManagement(this);
    forumHomePage = new ForumHomePage(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    richTextEditor = new RichTextEditor(this);
    manageLogInOut = new ManageLogInOut(this);
    manageAlert = new ManageAlert(this);
    pageEditor = new PageEditor(this);
    spaceManagement = new SpaceManagement(this);
    activityStream = new ActivityStream(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDocument = new CreateNewDocument(this);
    forumTopicManagement = new ForumTopicManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }

  /**
   * <li>Case ID:120868.</li>
   * <li>Test Case Name: Quick Search.</li>
   * <li>Pre-Condition: Some contents such as wiki, events, tasks, people...are
   * existed, and contain text "cloud"</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Quick search Step
   * Description: - Log in Intranet - Type the text "cloud" at Quick search box
   * Input Data: Expected Outcome: - By default, quick search returns results for
   * items with All types located in the current site only, as attachment
   * SearchResult.png
   */
  @Test
  public void test01_QuickSearch() {
    String name = "cloud";
    info("Test 1: Quick Search");

    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(name, "", name);
    info("Add a forum in the category");
    forumForumManagement.addForumSimple(name, "", name);

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(name, name);
    wikiManagement.saveAddPage();
    homePagePlatform.goToHomePage();
    navigationToolbar.goToQuickSearch();
    quickSearchResult.search("cloud");
    $(ELEMENT_SEARCHRESULT_ALLTYPECHECK).waitUntil(Condition.visible,Configuration.timeout);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(name);
    homePagePlatform.goToForum();
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(name);

  }

  /**
   * <li>Case ID:120869.</li>
   * <li>Test Case Name: Configure quick search.</li>
   * <li>Pre-Condition: There is a page containing a Quick search portlet</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Configure Quick search
   * Step Description: - Login as admin, go to intranet home page - Open Quick
   * search page - Click Edit this page - Click Edit portlet "Quick search" - Set
   * value to fields - Click Save settings, Input Data: Expected Outcome: - Quick
   * search settings screen is shown. - value is save
   */
  @Test
  public void test02_ConfigureQuickSearch() {
    info("Test 2: Configure quick search");

    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).setValue("log");
    ELEMENT_DROP_DOWN_LIST_RESULT_IN_QUICK_SEARCH.waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).pressEnter();
    navigationToolbar.goToEditLayout();
    pageEditor.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
    $(ELEMENT_EDIT_PORTLET_FORM_RESULTPERPAGE).selectOption("5");
    click(ELEMENT_EDIT_PORTLET_FORM_SAVESETTINGS_BUTTON);
    switchTo().alert();
    confirm();
    switchToParentWindow();
    $(ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON).click();
    ELEMENT_CLOSE_PORTLET.click();
  }

  /**
   * <li>Case ID:120870.</li>
   * <li>Test Case Name: Sort search result.</li>
   * <li>Pre-Condition: Some contents (such as wiki pages, events, tasks...)
   * having the word "dinner" are existed.</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Quick search Step
   * Description: - Login to Intranet - Type the word "dinner" into search box on
   * top navigation bar - Click on Search icon or press Enter key Input Data:
   * Expected Outcome: - By default, quick search returns results for items
   * located in the current site only, as attachment SearchResult.png*
   * action.sendKeys(Keys.ENTER); action.perform(); action.release(); Step number:
   * 2 Step Name: Sort search results Step Description: - Click on Sort By combo,
   * select one of sets "Relevance", "Date", "Title" Input Data: Expected Outcome:
   * Search result will sorted by Relevance, or Date, or Title respectively
   */
  @Test
  public void test03_SortSearchResult() {
    String name = "space";
    String wiki = "wiki" + getRandomNumber();
    info("Test 3: Sort search result");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(name, "");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, "");
    wikiManagement.saveAddPage();
    homePagePlatform.goToHomePage();
    refresh();
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).click();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).setValue(wiki);
    ELEMENT_QUICKSEARCHRESULT_SEE_ALL_SEARCH.waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_QUICKSEARCHRESULT_SORTBY).click();
    $(ELEMENT_QUICKSEARCHRESULT_SORTBY_DATE).click();
    ELEMENT_RESULT_SEARCH.findAll(byText(wiki)).get(0).shouldHave(Condition.text(wiki));
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(wiki);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(name, false);
  }

  /**
   * <li>Case ID:120871.</li>
   * <li>Test Case Name: Filter search.</li>
   * <li>Pre-Condition: Some contents such as wiki, events, tasks, people,
   * pages...are existed, and contain text "cloud"</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Quick search Step
   * Description: - Login and Open intranet home or ACME homepage - Type the text
   * "cloud" in search box in top navigation - Press Enter key, or click Search
   * icon Input Data: Expected Outcome: - By default, quick search returns results
   * for items located in the current site only, as attachment SearchResult.png
   * Step number: 2 Step Name: Filter search Step Description: On filter area,
   * click on fields that you want to search Input Data: Expected Outcome: The
   * page will search only selected fields for results
   */

  @Test
  public void test04_FilterSearch() {
    String wiki = "wiki" + getRandomNumber();
    info("Test 4: Filter search");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, "");
    wikiManagement.saveAddPage();
    homePagePlatform.goToHomePage();
    refresh();
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).click();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).setValue(wiki);
    ELEMENT_QUICKSEARCHRESULT_SEE_ALL_SEARCH.waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_SEARCHRESULT_ALLTYPECHECK).click();
    $(ELEMENT_SEARCHRESULT_WIKITYPECHECK).click();
    ELEMENT_RESULT_SEARCH.find(byText(wiki)).should(Condition.exist);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(wiki);

  }

  /**
   * <li>Case ID:120875.</li>
   * <li>Test Case Name: Configure Search page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Go to search page Step
   * Description: - Log in to intranet - Enter a keyword into the Search box on
   * the administration bar, then press Enter Input Data: Expected Outcome: Seach
   * page is shown Step number: 2 Step Name: Configure search page Step
   * Description: - Click Edit > Page > Edit Layout on the administration bar -
   * Click Edit portlet icon - Set value to fields - Click Save Settings - Click
   * Close - Click Finish icon on the Page Editor Input Data: Expected Outcome: -
   * Edit Mode settings screen is shown. - value is save - Quit the Page editor
   */
  @Test
  public void test05_ConfigureSearchPage() {
    info("Test 5: Configure Search page");

    homePagePlatform.goToHomePage();
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).setValue("wiki");
    ELEMENT_DROP_DOWN_LIST_RESULT_IN_QUICK_SEARCH.waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).pressEnter();
    navigationToolbar.goToEditLayout();
    pageEditor.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
    $(ELEMENT_EDIT_PORTLET_FORM_RESULTPERPAGE).selectOption("5");
    click(ELEMENT_EDIT_PORTLET_FORM_SAVESETTINGS_BUTTON);
    switchTo().alert();
    confirm();
    switchToParentWindow();
    $(ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON).click();
    ELEMENT_CLOSE_PORTLET.click();

  }

  /**
   * <li>Case ID:120876.</li>
   * <li>Test Case Name: Administrate the unified search engine.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Open Search Administration
   * Step Description: - Login and go to intranet home page - Click Administration
   * > Content > Search menu of the navigation bar Input Data: Expected Outcome:
   * The search admin has a table with 3 columns : Content Type, Description, and
   * Actions, as attachment searchAdmin.png* Step number: 2 Step Name: Enable a
   * content type Step Description: Click on Enable button matching a content type
   * Input Data: Expected Outcome: - Disable button label is changed into Enable -
   * This content type will no longer appear in the search results, nor in the
   * search apps settings Step number: 3 Step Name: Disable a content type Step
   * Description: Click on Disable button matching a content type Input Data:
   * Expected Outcome: - The Enable button label is changed into Disable - The
   * content type will reappear in the search results, and in the search apps
   * settings.
   */
  @Test
  public void test06_AdministrateTheUnifiedSearchEngine() {
    info("Test 6: Administrate the unified search engine");
    String wiki = "wiki" + getRandomNumber();
    info("Test 4: Filter search");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, "");
    wikiManagement.saveAddPage();
    homePagePlatform.goToHomePage();
    refresh();
    navigationToolbar.goToAdminSearch();
    info("Content type is shown");
    $(ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_TITLE).waitUntil(Condition.appears, Configuration.timeout).click();
    info("Description is shown");
    $(ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_DESCRIPTION).waitUntil(Condition.appears, Configuration.timeout);
    info("Action is shown");
    $(ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_ACTION).waitUntil(Condition.appears, Configuration.timeout);

    info("Click on Disable button");
    ELEMENT_BUTTON_DISABLE_ENABLE_WIKI_SEARCH.click();
    info("Open Search page");
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).setValue(wiki);
    ELEMENT_DROP_DOWN_LIST_RESULT_IN_QUICK_SEARCH.waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).pressEnter();
    info("Verify that File checkbox is not shown");
    $(ELEMENT_SEARCHRESULT_WIKITYPECHECK).shouldNot(Condition.exist);
    navigationToolbar.goToAdminSearch();
    $(ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_TITLE).waitUntil(Condition.appears, Configuration.timeout).click();
    info("Click on Enable button");
    $(byId("wiki")).click();
    info("Open Search page");
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).setValue(wiki);
    ELEMENT_DROP_DOWN_LIST_RESULT_IN_QUICK_SEARCH.waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).pressEnter();
    $(ELEMENT_SEARCHRESULT_WIKITYPECHECK).should(Condition.exist);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(wiki);
  }

  /**
   * <li>Case ID:120877.</li>
   * <li>Test Case Name: Search files.</li>
   * <li>Pre-Condition: Some files containing the word "cloud" are existed</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Search files Step
   * Description: - Login Intranet - Type the text "cloud" into search box on the
   * top navigation bar - Click on Search icon, or press Enter key Input Data:
   * Expected Outcome: - Search results of files should display: file title, the
   * file location and the mimetype icon used in content explorer - Item in search
   * result is clickable and open it when user click
   */

  @Test
  public void test07_SearchFiles() {
    info("Test 7: Search files");
    String activity1 = "activity1" + getRandomNumber();
    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");

    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).setValue("eXo");
    ELEMENT_DROP_DOWN_LIST_RESULT_IN_QUICK_SEARCH.waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).pressEnter();
    $(ELEMENT_SEARCHRESULT_ALLTYPECHECK).click();
    $(ELEMENT_SEARCHRESULT_FILESTYPECHECK).click();
    ELEMENT_RESULT_SEARCH.find(byText("eXo")).parent().parent().shouldHave(Condition.text("eXo-Platform.png"));
    homePagePlatform.goToHomePage();
    activityStream.deleteactivity(activity1);

  }

  /**
   * <li>Case ID:120879.</li>
   * <li>Test Case Name: Search Discussions.</li>
   * <li>Pre-Condition: Forums, topics, posts containing the text "cloud" are
   * already existed on Forum application.</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test09_SearchDiscussions() {
    info("Test 9: Search Discussions");
    info("Create data test");
    String nameCat = "Category" + getRandomNumber();
    String nameForum = "Forum" + getRandomNumber();
    String nameTopic = "Topic" + getRandomNumber();
    String description = "Description" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String title = "title" + getRandomNumber();
    info("Finished creating data test");
    info("Open forum portlet");
    homePagePlatform.goToForum();
    info("Add a new category");
    forumCategoryManagement.addCategorySimple(nameCat, "", description);
    info("Add a new forum");
    forumForumManagement.addForumSimple(nameForum, "", description);
    info("Add a new topic");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(nameTopic, description, "", "");
    forumHomePage.goToTopic(nameTopic);
    forumTopicManagement.postReply(title, content);
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).setValue(content);
    ELEMENT_DROP_DOWN_LIST_RESULT_IN_QUICK_SEARCH.waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).pressEnter();
    $(ELEMENT_SEARCHRESULT_ALLTYPECHECK).click();
    $(ELEMENT_SEARCHRESULT_DISCTYPECHECK).click();
    ELEMENT_RESULT_SEARCH.find(byText(content)).should(Condition.exist);
    ELEMENT_RESULT_SEARCH.find(byText(title)).should(Condition.exist);
    homePagePlatform.goToForum();
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(nameCat);

  }

  @Test
  @Tag("ECMS-7784")
  public void test10_searchWebContentByContent() {
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(name, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).setValue(content);
    ELEMENT_DROP_DOWN_LIST_RESULT_IN_QUICK_SEARCH.waitUntil(Condition.visible, Configuration.timeout)
                                                 .find(byText(name))
                                                 .shouldBe(Condition.visible);
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).pressEnter();
    $(ELEMENT_SEARCHRESULT_ALLTYPECHECK).click();
    $(ELEMENT_SEARCHRESULT_DOCTYPECHECK).parent().click();
    ELEMENT_RESULT_SEARCH.find(byText(name)).should(Condition.visible);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.deleteData(name);
  }

  @Test
  @Tag("FORUM-1375")
  public void test11_SearchDiscussionInPrivateSpace() {
    String space1 = "space" + getRandomNumber();
    String topic = "topic" + getRandomNumber();
    String topic1 = "topic" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpace(space1, space1, "hidden/close", "");
    spaceHomePage.goToForumsTab();
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    manageLogInOut.signIn(PLFData.DATA_USER2, password);
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).setValue(topic);
    ELEMENT_DROP_DOWN_LIST_RESULT_IN_QUICK_SEARCH.waitUntil(Condition.visible, Configuration.timeout)
                                                 .find(byText(topic))
                                                 .parent()
                                                 .shouldHave(Condition.text("No result for " + topic));
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).setValue(topic1);
    ELEMENT_DROP_DOWN_LIST_RESULT_IN_QUICK_SEARCH.waitUntil(Condition.visible, Configuration.timeout)
                                                 .find(byText(topic1))
                                                 .parent()
                                                 .shouldHave(Condition.text("No result for " + topic1));
    manageLogInOut.signIn(username, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Tag("PLF-8027")
  @Test
  public void test12_checkSearchBoxActivatedAfterDeleteSpace() {
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    homePagePlatform.goToMySpaces();
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).waitUntil(Condition.visible, Configuration.timeout);
    spaceManagement.deleteSpace(space, false);
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).waitUntil(Condition.visible, Configuration.timeout);
  }
}
