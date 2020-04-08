package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_SOURCE_EDITOR_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
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

@Tag("sniff")
@Tag("wiki")
public class WikiActivitiesInSpaceTestIT extends Base {

  HomePagePlatform homePagePlatform;

  SpaceManagement  spaceManagement;

  WikiHomePage     wikiHomePage;

  WikiManagement   wikiManagement;

  SourceTextEditor sourceTextEditor;

  WikiValidattions wikiValidattions;

  SpaceHomePage    spaceHomePage;

  ActivityStream   activityStream;

  ManageLogInOut   manageLogInOut;

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
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(username, password);

  }

  /**
   * <li>Case ID:139201.</li>
   * <li>Test Case Name: Add/edit/remove a wiki's activity after create a wiki page in
   * space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1: Add wiki for space Step Description: -
   * Connect to [Intranet] - Add a Wiki activity for space Input Data: Expected
   * Outcome: - The Wiki activity for spaceis displayed in the activity stream
   * Step number: 2 Step Name: Step 2: Check show delete icon Step Description: -
   * Back to activity stream - Move the mouse over the Wiki activity for space
   * Input Data: Expected Outcome: A (X) icon is displayed in the top right panel
   * of the activity
   */
  @Test
  public void test11_AddEditRemoveAWikisActivityAfterCreateAWikiPageInSpace() {
    info("Test 13 Remove wiki's page of space");
    info("Create a space");
    String space = "space" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);

    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title);
    info("Check the Activity");
    homePagePlatform.goToHomePage();
    activityStream.checkActivity(title);
    info("Edit wiki page");
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).isDisplayed()) {
      wikiManagement.goToSourceEditor();
    }
    sourceTextEditor.editSimplePage(newTitle, newContent);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(newTitle);
    homePagePlatform.goToSpecificSpace(space);
    spaceHomePage.goToWikiTab();
    wikiHomePage.deleteWiki(newTitle);
    wikiValidattions.verifyWikiPageNotDisplayedInWikiHome(newTitle);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }

}
