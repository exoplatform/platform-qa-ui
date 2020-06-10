package org.exoplatform.platform.qa.ui.digitalWorkplace;

import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.digitalWorkplace.pageobject.*;
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

import java.util.ArrayList;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("wiki")
@Tag("sniff")
public class WikiActivitiesInSpaceTestIT extends BaseDW {

  HomePagePlatform homePagePlatform;

  SpaceManagement spaceManagement;

  TribeWikiHomePage tribeWikiHomePage;

  WikiManagement wikiManagement;

  TribeWikiManagement tribeWikiManagement;

  TribeSourceTextEditor tribeSourceTextEditor;

  WikiValidattions wikiValidattions;

  WikiHomePage wikiHomePage;

  SpaceHomePage spaceHomePage;

  ActivityStream activityStream;

  TribeActivityStream tribeActivityStream;

  TribeSpaceManagement tribeSpaceManagement;

  ManageLogInOut manageLogInOut;

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
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);

  }

  @Test
  public void test01_AddEditRemoveAWikiActivityAfterCreateAWikiPageInSpace() {
    info("Test 13 Remove wiki's page of space");
    info("Create a space");
    String space = "space" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    String user1 = "Adam Larsen";
    String user2 = "Adem Reim";

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(user1);
    inviteUsers.add(user2);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpace(space, space, "Open", "No", inviteUsers);
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    wikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePageDW(title, content);
    tribeWikiManagement.saveAddPage();
    getExoWebDriver().getWebDriver().navigate().refresh();
    wikiValidattions.verifyTitleWikiPage(title);
    info("Check the Activity");
    homePagePlatform.goToStreamPageTribe();
    activityStream.checkActivity(title);
    info("Edit wiki page");
    tribeWikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    tribeSourceTextEditor.editSimplePageDW(newTitle, newContent);
    tribeWikiManagement.saveAddPage();
    getExoWebDriver().getWebDriver().navigate().refresh();
    wikiValidattions.verifyTitleWikiPage(newTitle);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.searchSpace(space);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.deleteWikiDW(newTitle);
    wikiValidattions.verifyWikiPageNotDisplayedInWikiHome(newTitle);
    homePagePlatform.goToStreamPageTribe();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space);

  }

}
