package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_SOURCE_EDITOR_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import java.util.ArrayList;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;

@Tag("wiki")
@Tag("sniff")
public class WikiBasicTestWithUserTestIT extends Base {

  HomePagePlatform       homePagePlatform;

  WikiHomePage           wikiHomePage;

  RichTextEditor         richTextEditor;

  SourceTextEditor       sourceTextEditor;

  WikiValidattions       wikiValidattions;

  WikiManagement         wikiManagement;

  UserAddManagement      userAddManagement;

  ArrayList<String>      arrayPage;

  NavigationToolbar      navigationToolbar;

  ManageLogInOut         manageLogInOut;

  UserAndGroupManagement userAndGroupManagement;

  ActivityStream         activityStream;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    wikiValidattions = new WikiValidattions(this);
    wikiManagement = new WikiManagement(this);
    wikiHomePage = new WikiHomePage(this);
    activityStream = new ActivityStream(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    sourceTextEditor = new SourceTextEditor(this);
    richTextEditor = new RichTextEditor(this);
    arrayPage = new ArrayList<String>();
    userAddManagement = new UserAddManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }

    manageLogInOut.signInCas("john", "gtngtn");
  }


  @Test
  @Tag("wabis")
  public void test01_AddAPageWithLinkWikiPageExisted() {
    info("Test 1: Add a page with link wiki page existed");

    info("Create a wiki page 1");
    String title1 = "title" + getRandomNumber();
    String content1 = "content" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, content1);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title1);
    arrayPage.add(title1);

    info("Create a wiki page 2");
    String title2 = "title2" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    String label = "label" + getRandomNumber();
    String tooltip = "tooltip" + getRandomNumber();

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title2, content2);
    richTextEditor.goToWikiPageLink();
    sleep(1000);
    richTextEditor.insertExistWikiPageLink(title1, label, tooltip, RichTextEditor.wikiPageLinkTab.All_pages);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title2);
    arrayPage.add(title2);
    info("Page is shown successfully");
    wikiHomePage.goToAPage(title2);
    wikiValidattions.verifyInsertedExistLink(label, title1);
    wikiHomePage.deleteWiki(title1);
    wikiHomePage.deleteWiki(title2);

  }

  @Test
  public void test02_AddWebPage() {
    info("Test 2: Add web page");

    info("Create a wiki page 2");
    String title1 = "title1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String label = "label" + getRandomNumber();
    String tooltip = "tooltip" + getRandomNumber();
    String address = "www.google.com";
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, content1);
    richTextEditor.goToWebPageLink();
    richTextEditor.insertWebLink(address, label, tooltip, true);
    wikiValidattions.verifyInsertedLinkIntoFrame(label, tooltip);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title1);
    arrayPage.add(title1);

    info("Page is shown successfully");
    wikiHomePage.goToAPage(title1);
    wikiManagement.viewInsertLink(label);
    String titlePage = this.getExoWebDriver().getWebDriver().getTitle();
    if (titlePage.contains("Google"))
      assert true;
    else
      assert false;
    back();
    wikiHomePage.deleteWiki(title1);
  }

  @Test
  @Tag("wabis")
  public void test03_EditPage() {
    info("Test 3: Edit page");

    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String line1 = "line1" + getRandomNumber();
    String line2 = "line2" + getRandomNumber();
    String line3 = "line3" + getRandomNumber();
    String line4 = "line4" + getRandomNumber();
    String line5 = "line5" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newContent = line1 + "</br>" + line2 + "</br>" + line3 + "</br>" + line4 + "</br>" + line5;
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title);
    arrayPage.add(title);
    info("Edit a wiki page");
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).isDisplayed()) {
      wikiManagement.goToSourceEditor();
    }
    sourceTextEditor.editSimplePage(newTitle, newContent);
    wikiManagement.saveAddPage();
    $(byText(title)).waitUntil(Condition.not(Condition.exist), Configuration.openBrowserTimeoutMs);
    homePagePlatform.goToHomePage();
    activityStream.checkActivityAddWikiPage(newTitle, newContent, null);
    homePagePlatform.goToWiki();
    arrayPage.add(newTitle);
    wikiHomePage.deleteWiki(newTitle);

  }

  @Test
  @Tag("wabis")
  public void test10_editPageWikionSpace() {
    info("Edit page wiki on space");
    info("Create a space");
    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 6000);

    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    sleep(1000);
    wikiValidattions.verifyTitleWikiPage(title);
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

  @Test
  @Tag("wabis")
  public void test11_SearchWikiPageFromHomeSearchBar() {
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String title1 = "title" + getRandomNumber();
    String content1 = "content" + getRandomNumber();
    String title2 = "title" + getRandomNumber();
    String content2 = "content" + getRandomNumber();

    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, content1);
    wikiManagement.saveAddPage();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title2, content2);
    wikiManagement.saveAddPage();

    homePagePlatform.goToHomePage();
    ELEMENT_ICON_SEARCH.click();
    ELEMENT_SEARCH_INPUT.setValue(title);
    ELEMENT_SEARCH_RESULT.shouldHave(Condition.exactText(title));
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);
    wikiHomePage.deleteWiki(title1);
    wikiHomePage.deleteWiki(title2);
  }
}
