package org.exoplatform.platform.qa.ui.platform.wiki;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_SOURCE_EDITOR_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
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
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas("john", "gtngtn");
  }

  @Test
  public void test01_AddAPageWithLinkWikiPageExisted() {
    info("Test 1: Add a page with link wiki page existed");

    info("Create a wiki page 1");
    String title1 = "title AddAPageWithLinkWikiPageExisted" ;
    String content1 = "content AddAPageWithLinkWikiPageExisted" ;

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title1, content1);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title1);
    arrayPage.add(title1);

    info("Create a wiki page 2");
    String title2 = "title2 AddAPageWithLinkWikiPageExisted" ;
    String content2 = "content2 AddAPageWithLinkWikiPageExisted" ;
    String label = "label AddAPageWithLinkWikiPageExisted" ;
    String tooltip = "tooltip AddAPageWithLinkWikiPageExisted" ;

    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title2, content2);
    richTextEditor.goToWikiPageLink();
    richTextEditor.insertExistWikiPageLink(title1, label, tooltip, RichTextEditor.wikiPageLinkTab.All_pages);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title2);
    arrayPage.add(title2);
    info("Page is shown successfully");
    wikiHomePage.goToAPage(title2);
    wikiValidattions.verifyInsertedExistLink(label, title1);


  }

  @Test
  public void test02_AddWebPage() {
    info("Test 2: Add web page");

    info("Create a wiki page 2");
    String title1 = "title1 AddWebPage" ;
    String content1 = "content1 AddWebPage" ;
    String label = "label AddWebPage";
    String tooltip = "tooltip AddWebPage";
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

  }

  @Test
  public void test03_EditPage() {
    info("Test 3: Edit page");

    info("Create a wiki page");
    String title = "title EditPage" ;
    String content = "content EditPage" ;
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();

    wikiValidattions.verifyTitleWikiPage(title);
    arrayPage.add(title);

    info("Edit a wiki page");
    String newTitle = "newTitle EditPage";
    String newContent = "newContent EditPage" ;
    wikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    if ($(ELEMENT_SOURCE_EDITOR_BUTTON).isDisplayed()) {
      wikiManagement.goToSourceEditor();
    }
    sourceTextEditor.editSimplePage(newTitle, newContent);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(newTitle);
    arrayPage.add(newTitle);


  }

  @Test
  public void test04_05_CreateDeletePageUsingSourceEditor() {
    info("Test 4: Create page using Source Editor");
    String wiki = "wiki CreateDeletePageUsingSourceEditor";
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    $(byText(wiki)).should(Condition.exist);

  }

  @Test
  public void test06_CreateNewWikiPage() {
    info("Test 6: Create new wiki page");
    String title = "title CreateNewWikiPage" ;
    String line1 = "line1 CreateNewWikiPage" ;
    String line2 = "line2 CreateNewWikiPage" ;
    String line3 = "line3 CreateNewWikiPage" ;
    String line4 = "line4 CreateNewWikiPage" ;
    String line5 = "line5 CreateNewWikiPage" ;
    String content = line1 + "</br>" + line2 + "</br>" + line3 + "</br>" + line4 + "</br>" + line5;

    info("Create a new wiki page");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    info("Verify that the new wiki page is created successfully");
    $(byText(title)).should(Condition.exist);

    homePagePlatform.goToHomePage();
    activityStream.checkActivityAddWikiPage(title, content, null);


  }
}

