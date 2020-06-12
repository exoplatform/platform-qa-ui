package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.pageobject.*;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_APPLICATION_TAB_ADD_APPLICATION_DW;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("wiki")
@Tag("functional")

public class WikiActivitiesTestDWIT extends BaseDW {

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
    tribeSourceTextEditor = new TribeSourceTextEditor(this);
    tribeActivityStream = new TribeActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }

  /**
   * <li>Case ID:139189.</li>
   * <li>Test Case Name: Display "View Changes" page from wiki's activity.</li>
   * <li>Pre-Condition: the wiki page is not "version 1"</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Add page for wiki
   * Step Description: - Connect to [Intranet] - Go to the [Homepage] - Click
   * [Wiki] to go to the Wiki app - Click [Add Page] - -> [Blank Page]/[From
   * Template...] - Enter title and content for the Wiki page - Click [Save] Input
   * Data: Expected Outcome: - A wiki page is created successfully - A wiki
   * activity is displayed Step number: 2 Step Name: Step 2: Edit wiki's page Step
   * Description: - Click [Edit] to edit the wiki page - Change content for page -
   * Click [Save] Input Data: Expected Outcome: - Page is edited successfully
   * /*Step number: 3 Step Name: Step Description: - Back to the Homepage Input
   * Data: Expected Outcome: - In the create page's activity a Wiki page's version
   * "View change" is displayed Step number: 4 Step Name: Step 3: Check compare
   * revision Step Description: - Click on the link "View changes" Input Data:
   * Expected Outcome: - The wiki application is opened in the view to compare
   * Version N -1 and Version N
   */

  @Test
  @Tag("wabis")
  public void test01_DisplayViewChangesPageFromWikisActivityAfterCreateAWikiPageInSpace() {
    info("Test 2: Display View Changes page from wiki's activity");
    info("Create a space");
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpace(space, space, "Open", "No", null);
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePageDW(title, content);
    tribeWikiManagement.saveAddPage();
    getExoWebDriver().getWebDriver().navigate().refresh();
    wikiValidattions.verifyTitleWikiPage(title);
    homePagePlatform.goToSpaceHomeDW();
    homePagePlatform.goToStreamPageTribe();
    tribeActivityStream.checkActivity(title);
    info("Edit the page");
    String editTitle = "editTitle" + getRandomNumber();
    String editContent = "editContent" + getRandomNumber();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.searchSpace(space);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeSpaceManagement.goToWikiTabDW(space);
    getExoWebDriver().getWebDriver().navigate().refresh();
    tribeWikiHomePage.goToAPage(title);
    tribeWikiHomePage.goToEditPage();
    tribeSourceTextEditor.editSimplePageDW(editTitle, editContent);
    tribeWikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(editTitle);
    info("In the create page's activity a Wiki page's version 'View change' is displayed");
    homePagePlatform.goToStreamPageTribe();
    tribeActivityStream.checkActivity(editTitle);
    activityStream.checkActivityWikiPage(editTitle, editContent, "2", true);
    activityStream.clickOnViewChange(editTitle);
    tribeWikiValidattions.verifyCompareVersions("1");
    tribeWikiHomePage.goToHomeWikiPage();
    getExoWebDriver().getWebDriver().navigate().refresh();
    tribeWikiHomePage.deleteWikiDW(editTitle);
    homePagePlatform.goToSpaceHomeDW();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space);
  }

  @Test
  @Tag("WIKI-1440")
  public void test02_RemoveWikisPageOfSpace() {
    info("Test 13 Remove wiki's page of space");
    info("Create a space");
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpace(space, space, "Open", "No", null);
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.searchSpace(space);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePageDW(title, content);
    tribeWikiManagement.saveAddPage();
    getExoWebDriver().getWebDriver().navigate().refresh();
    wikiValidattions.verifyTitleWikiPage(title);
    info("Remove Wiki application in the space");
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.searchSpace(space);
    tribeSpaceManagement.accessToSearchedSpace();
    spaceHomePage.goToSpaceSettingTabDW(space);
    spaceSettingManagement.goToApplicationTabDW();
    spaceSettingManagement.removeApplicationDW("Wiki");
    $(ELEMENT_APPLICATION_TAB_ADD_APPLICATION_DW).waitUntil(Condition.appears, Configuration.timeout);
    spaceSettingManagement.addApplicationDW("Wiki");
    info("Check on AS");
    homePagePlatform.goToSpaceHomeDW();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space);

  }

}
