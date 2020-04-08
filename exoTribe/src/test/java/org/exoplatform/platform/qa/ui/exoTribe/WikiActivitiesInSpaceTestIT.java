package org.exoplatform.platform.qa.ui.exoTribe;

import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.*;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeWikiHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("sniff")
@Tag("wiki")
public class WikiActivitiesInSpaceTestIT extends BaseTribe {

  HomePagePlatform homePagePlatform;

  SpaceManagement  spaceManagement;

  TribeWikiHomePage tribeWikiHomePage;

  WikiManagement   wikiManagement;

  TribeWikiManagement tribeWikiManagement;

  TribeSourceTextEditor tribeSourceTextEditor;

  WikiValidattions wikiValidattions;

  WikiHomePage     wikiHomePage;

  SpaceHomePage    spaceHomePage;

  ActivityStream   activityStream;

  TribeActivityStream tribeActivityStream;

  TribeSpaceManagement tribeSpaceManagement;

  ManageLogInOut   manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);

    spaceHomePage = new SpaceHomePage(this);
    tribeWikiHomePage = new TribeWikiHomePage(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    tribeWikiManagement = new TribeWikiManagement(this);
    tribeSourceTextEditor = new TribeSourceTextEditor(this);
    wikiValidattions = new WikiValidattions(this);
    spaceManagement = new SpaceManagement(this);
    activityStream = new ActivityStream(this);
    tribeActivityStream = new TribeActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInTribeWithGoogle(tribe_mail, atlassian_username, atlassian_password);

  }

  @Test
  public void test11_AddEditRemoveAWikiActivityAfterCreateAWikiPageInSpace() {
    info("Test 13 Remove wiki's page of space");
    info("Create a space");
    String space = "space" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpaceSimple(space, space, 6000);
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    spaceHomePage.goToWikiTab();
    tribeWikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    getExoWebDriver().getWebDriver().navigate().refresh();
    wikiValidattions.verifyTitleWikiPage(title);
    info("Check the Activity");
    homePagePlatform.goToStreamPageTribe();
    activityStream.checkActivity(title);
    info("Edit wiki page");
    tribeWikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    tribeSourceTextEditor.editSimplePage(newTitle, newContent);
    wikiManagement.saveAddPage();
    getExoWebDriver().getWebDriver().navigate().refresh();
    wikiValidattions.verifyTitleWikiPage(newTitle);
    homePagePlatform.goToMySpacesTribe();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceHomePage.goToWikiTab();
    tribeWikiHomePage.deleteWiki(newTitle);
    wikiValidattions.verifyWikiPageNotDisplayedInWikiHome(newTitle);
    homePagePlatform.goToStreamPageTribe();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteSpace(space, false);

  }

}
