package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.digitalWorkplace.pageobject.*;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_password;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_STREAM_CONTAINER;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_WIKI_PAGE_CONTAINER;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("wiki")
@Tag("sniff")
public class WikiPublishActivityTestIT extends BaseTribe {
  HomePagePlatform homePagePlatform;

  WikiHomePage wikiHomePage;

  WikiManagement wikiManagement;

  SourceTextEditor sourceTextEditor;

  WikiValidattions wikiValidattions;

  ActivityStream activityStream;

  RichTextEditor richTextEditor;

  ManageLogInOut manageLogInOut;

  SpaceManagement spaceManagement;

  SpaceHomePage spaceHomePage;

  NavigationToolbar navigationToolbar;

  SpaceSettingManagement spaceSettingManagement;

  TribeWikiHomePage tribeWikiHomePage;

  TribeWikiValidattions tribeWikiValidattions;

  TribeWikiManagement tribeWikiManagement;

  WikiPageInformation wikiPageInformation;

  TribeWikiPageInformation tribeWikiPageInformation;

  TribeSourceTextEditor tribeSourceTextEditor;

  TribeActivityStream tribeActivityStream;

  TribeSpaceManagement tribeSpaceManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    wikiValidattions = new WikiValidattions(this);
    activityStream = new ActivityStream(this);
    richTextEditor = new RichTextEditor(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    navigationToolbar = new NavigationToolbar(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    tribeWikiHomePage = new TribeWikiHomePage(this);
    tribeWikiValidattions = new TribeWikiValidattions(this);
    tribeWikiManagement = new TribeWikiManagement(this);
    wikiPageInformation = new WikiPageInformation(this);
    tribeWikiPageInformation = new TribeWikiPageInformation(this);
    tribeSourceTextEditor = new TribeSourceTextEditor(this);
    tribeActivityStream = new TribeActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInTribe(tribe_username, tribe_password);
  }

  /**
   * <li>Case ID:122865.</li>
   * <li>Test Case Name: Update activity - edit wiki page title</li>
   * <li>Pre-Condition: the wiki activity is already shared in the activity
   * stream</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @Tag("wabis")
  public void test01_OpenWikiPageFromWikiActivity() {
    info("Test 04: Update activity - edit wiki page title");

    String space = "space" + getRandomNumber();
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String text = "text" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Add new wiki page Step Description: - Login and
     * goto intranet - Click Wiki on left Navigation to go to Wiki Application -
     * Click add page-> Blank page - Fill the title and content and click save Input
     * Data: Expected Outcome: - Wiki page is created - An activity is added into
     * activity stream
     */
    info("Create a new wiki page");
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpace(space, space, "Open", "No", null);
    info("Add new wiki page for space");
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePageDW(title, content);
    tribeWikiManagement.saveAddPage();

    info("Verify that an activity is added to the activity stream");
    getExoWebDriver().getWebDriver().navigate().refresh();
    homePagePlatform.goToSpaceHomeDW();
    activityStream.checkActivity(title);
    activityStream.commentWikiActivity(title, text);
    info("check that the comment is added successfully");
    activityStream.checkCommentOfActivity(title, text);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(title)).click();
    info("Verify that The wiki application is opened in the correspond page ");
    ELEMENT_WIKI_PAGE_CONTAINER.find(byText(title)).should(Condition.exist);
    /*
     * Step Number: 2 Step Name: Edit Wiki Page Step Description: - Goto Wiki page
     * and click Edit - Edit a title of a wiki page - Click save and check [Publish
     * Activity] - Go to the Homepage Input Data: Expected Outcome: - The wiki
     * activity is updated - A comment is added in the activity: Page's title has
     * been updated to: $value.
     */
    String comment = "Page's title has been updated to: " + newTitle;
    info("Go to Wiki porlet and select the wiki page created");
    info("Open wiki page 1");
    getExoWebDriver().getWebDriver().navigate().refresh();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.searchSpace(space);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeSpaceManagement.goToWikiTabDW(space);
    $(byText(title)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    info("Edit the title of the wiki page and check on published checkbox");
    tribeWikiHomePage.goToEditPage();
    tribeSourceTextEditor.editSimplePageDW(newTitle, "");
    tribeWikiManagement.publishPageWhenEditPage();
    tribeWikiManagement.saveAddPage();
    info("The title of wiki page's activity is updated");
    homePagePlatform.goToSpaceHomeTribe();
    activityStream.checkActivity(newTitle);

    info("Delete the page");
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.deleteWikiDW(newTitle);
    homePagePlatform.goToSpaceHomeTribe();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space);
  }

}
