package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_SOURCE_EDITOR_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;

@Tag("smoke")
@Tag("wiki")
public class WikiActivitiesTestIT extends Base {

  HomePagePlatform homePagePlatform;

  SpaceManagement  spaceManagement;

  WikiHomePage     wikiHomePage;

  WikiManagement   wikiManagement;

  SourceTextEditor sourceTextEditor;

  WikiValidattions wikiValidattions;

  SpaceHomePage    spaceHomePage;

  ActivityStream   activityStream;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceHomePage = new SpaceHomePage(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    wikiValidattions = new WikiValidattions(this);
    spaceManagement = new SpaceManagement(this);
    activityStream = new ActivityStream(this);
  }

  /**
   * <li>Case ID:139201.</li>
   * <li>Test Case Name: Add a wiki's activity after create a wiki page in
   * space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test11_AddAWikisActivityAfterCreateAWikiPageInSpace() {
    info("Test 11 Add a wiki's activity after create a wiki page in space");
    /*
     * Step Number: 1 Step Name: Step 1: Add new space Step Description: -
     * Connect to Intranet - Click [Join a space] - Click [Add New Space] Input
     * Data: Expected Outcome: The new space is created successfully
     */

    info("Create a space");
    String space = "space" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(space, space, 6000);

    /*
     * Step number: 2 Step Name: Step 2: Add a wiki page for space Step
     * Description: - Click the created space in the [My Spaces] panel - Click
     * [Wiki] link on the space's top navigation bar - Click [Add Page] - ->
     * [Blank Page]/[From Template...] - Enter title and content - Click [Save]
     * Input Data: Expected Outcome: - By default, the [Create Wiki page] is
     * displayed in the [Rich Text] mode - Wiki page is created for space
     */
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToSpecificSpace(space);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title);

    /*
     * Step number: 3 Step Name: Step 3: Check wiki's activity after created
     * page Step Description: - Back to the Homepage Input Data: Expected
     * Outcome: - An activity of wikiis added to the activity stream - The
     * content of the activity is displayed
     */

    info("Check the Activity");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(title);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }

  /**
   * <li>Case ID:139203.</li>
   * <li>Test Case Name: Remove wiki's page of space.</li>
   * <li>Pre-Condition: Have a space</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test13_RemoveWikisPageOfSpace() {
    info("Test 13 Remove wiki's page of space");
    /*
     * Step Number: 1 Step Name: Step 1: Add wiki for space Step Description: -
     * Connect to [Intranet] - Add a Wiki activity for space Input Data:
     * Expected Outcome: - The Wiki activity for spaceis displayed in the
     * activity stream
     */
    info("Create a space");
    String space = "space" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(space, space, 6000);

    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToSpecificSpace(space);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title);

    /*
     * Step number: 2 Step Name: Step 2: Check show delete icon Step
     * Description: - Back to activity stream - Move the mouse over the Wiki
     * activity for space Input Data: Expected Outcome: A (X) icon is displayed
     * in the top right panel of the activity
     */
    homePagePlatform.goToSpecificSpace(space);
    spaceHomePage.goToWikiTab();
    wikiHomePage.deleteWiki(title);
    wikiValidattions.verifyWikiPageNotDisplayedInWikiHome(title);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test01_editPageWikionSpace() {
    info("Edit page wiki on space");
    info("Create a space");
    String space = "space" + getRandomNumber();
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(space, space, 6000);

    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToSpecificSpace(space);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title);
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(title);
    info("Edit wiki page");
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).isDisplayed()) {
      wikiManagement.goToSourceEditor();
    }
    String newTitle = "newTitle" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    sourceTextEditor.editSimplePage(newTitle, newContent);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(newTitle);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }
}
