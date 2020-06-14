package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.pageobject.*;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_password;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("wiki")
@Tag("sniff")
public class WikiBasicActionOtherActionsTestIT extends BaseTribe {

  HomePagePlatform homePagePlatform;

  SpaceManagement spaceManagement;

  TribeWikiHomePage tribeWikiHomePage;

  WikiManagement wikiManagement;

  TribeWikiManagement tribeWikiManagement;

  TribeSourceTextEditor tribeSourceTextEditor;

  TribeRichTextEditor tribeRichTextEditor;

  WikiValidattions wikiValidattions;

  NavigationToolbar navigationToolbar;

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
    tribeRichTextEditor = new TribeRichTextEditor(this);
    navigationToolbar = new NavigationToolbar(this);
    wikiValidattions = new WikiValidattions(this);
    spaceManagement = new SpaceManagement(this);
    activityStream = new ActivityStream(this);
    tribeActivityStream = new TribeActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInTribe(tribe_username, tribe_password);

  }

  @Test
  public void test01_MovePage_Intranet_Space_Then_Space_Intranet() {

    info("Create a space");
    String space = "space" + getRandomNumber();
    String space1 = "space1" + getRandomNumber();
    String title1 = "title1" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();


    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space, space, "Open", "No", null);

    info("Create a wiki page ");
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    sleep(2000);
    tribeSourceTextEditor.addSimplePage(title1, title1);
    tribeWikiManagement.saveAddPage();
    $(byText(title1)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);

    info("Move page to Space");
    tribeWikiManagement.selectSpaceDestination(space);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.searchSpace(space);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeSpaceManagement.goToWikiTabDW(space);
    $(byClassName("uiTreeExplorer")).find(byText(title1)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);
    homePagePlatform.goToStreamPageTribe();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space);
    tribeSpaceManagement.addNewSpace(space1, space1, "Open", "No", null);

    info("Create a wiki page in space");
    tribeSpaceManagement.goToWikiTabDW(space1);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeRichTextEditor.addSimplePage(title2, title2);
    tribeWikiManagement.saveAddPage();
    $(byText(title2)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);

    info("Move page to Intranet");
    tribeWikiManagement.selectSpaceDestination("Mes Notes");
    $(byClassName("uiTreeExplorer")).find(byText(title2)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);
    tribeWikiHomePage.deleteWikiDW(title2);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space1);

  }

  @Test
  public void test02_MovePage_Space2_Space1() {

    info("Create a space");
    String space1 = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(space1, space1, "Open", "No", null);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpace(space2, space2, "Open", "No", null);

    info("Create a wiki page in space2");
    String title1 = "title1" + getRandomNumber();
    tribeSpaceManagement.goToWikiTabDW(space2);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeRichTextEditor.addSimplePage(title1, title1);
    tribeWikiManagement.saveAddPage();
    $(byText(title1)).should(Condition.exist);

    info("Move page to Space1");

    tribeWikiManagement.selectSpaceDestination(space1);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.searchSpace(space1);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeSpaceManagement.goToWikiTabDW(space1);
    $(byClassName("uiTreeExplorer")).find(byText(title1)).should(Condition.exist);
    homePagePlatform.goToStreamPageTribe();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space1);
    tribeSpaceManagement.deleteTribeSpace(space2);

  }

}
