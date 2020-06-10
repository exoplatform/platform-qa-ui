package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.core.PLFData;
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
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_BUTTON_SELECT_RELATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("wiki")
@Tag("sniff")
public class WikiInformationTestIT extends BaseDW {

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
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }


  @Test
  @Tag("wabis")
  public void test01_AddRelationWithIntranetPortal() {
    info("Test 02: Add relations with Intranet portal");
    String space1 = "space1" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();
    String title1 = "title1" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();

    info("Create space 1 and wiki page 1");
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpace(space1, space1, "Open", "No", null);
    info("Add new wiki page for space 1");
    tribeSpaceManagement.goToWikiTabDW(space1);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePageDW(title1, title1);
    tribeWikiManagement.saveAddPage();
    getExoWebDriver().getWebDriver().navigate().refresh();
    $(byText(title1)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);

    info("Create space 2 and wiki page 2");
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.addNewSpace(space2, space2, "Open", "No", null);
    info("Add new wiki page for space 1");
    tribeSpaceManagement.goToWikiTabDW(space2);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePageDW(title2, title2);
    tribeWikiManagement.saveAddPage();
    $(byText(title2)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);

    info("Open wiki page 1");
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.searchSpace(space1);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeSpaceManagement.goToWikiTabDW(space1);
    info("Open page 1 and Go to Page Info");
    tribeWikiHomePage.goToAPage(title1);
    tribeWikiHomePage.goToPageInformation(title1);

    executeJavaScript("window.scrollBy(0,500)");
    info("Go to Related page form");
    info("Check when add relations from 2 different spaces");
    tribeWikiPageInformation.addRelations("Dw", "Wiki Home");
    info("intranet's portal is added as a related pages on page info layout");
    ELEMENT_BUTTON_SELECT_RELATION.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    homePagePlatform.goToSpaceHomeDW();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space1);
    tribeSpaceManagement.deleteTribeSpace(space2);

  }

}
