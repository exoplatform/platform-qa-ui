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
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_password;
import static org.exoplatform.platform.qa.ui.core.PLFData.tribe_username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_ICON_SEARCH;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_SEARCH_INPUT_DW;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_APPLICATION_TAB_ADD_APPLICATION_DW;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("tribeWikiActivities")
public class WikiActivitiesTestIT extends BaseTribe {

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
    manageLogInOut.signInTribe(tribe_username, tribe_password);
  }

  @Test
  public void test01_AddEditRemoveAWikiActivityAfterCreateAWikiPageInSpace() {
    info("Remove wiki's page of space");
    info("Create a space");
    String space = "space" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    String newContent = "newContent" + getRandomNumber();
    String user1 = "Beter Bimel";
    String user2 = "Bret Muller";

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(user1);
    inviteUsers.add(user2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", inviteUsers);
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    wikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePage(title, content);
    tribeWikiManagement.saveAddPage();
    getExoWebDriver().getWebDriver().navigate().refresh();
    wikiValidattions.verifyTitleWikiPage(title);
    info("Check the Activity");
    homePagePlatform.goToStreamPageTribe();
    activityStream.checkActivity(title);
    info("Edit wiki page");
    tribeWikiHomePage.goToAPage(title);
    wikiHomePage.goToEditPage();
    tribeSourceTextEditor.editSimplePage(newTitle, newContent);
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

  @Test
  public void test02_DisplayViewChangesPageFromWikisActivityAfterCreateAWikiPageInSpace() {
    info("Display View Changes page from wiki's activity");
    info("Create a space");
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePage(title, content);
    tribeWikiManagement.saveAddPage();
    getExoWebDriver().getWebDriver().navigate().refresh();
    wikiValidattions.verifyTitleWikiPage(title);
    homePagePlatform.goToStreamPageTribe();
    tribeActivityStream.checkActivity(title);
    info("Edit the page");
    String editTitle = "editTitle" + getRandomNumber();
    String editContent = "editContent" + getRandomNumber();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.searchSpace(space);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAPage(title);
    tribeWikiHomePage.goToEditPage();
    tribeSourceTextEditor.editSimplePage(editTitle, editContent);
    tribeWikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(editTitle);
    info("In the create page's activity a Wiki page's version 'View change' is displayed");
    homePagePlatform.goToStreamPageTribe();
    tribeActivityStream.checkActivity(editTitle);
    activityStream.checkActivityWikiPageDW(editTitle, editContent, "2", true);
    activityStream.clickOnViewChange(editTitle);
    tribeWikiValidattions.verifyCompareVersions("1");
    tribeWikiHomePage.goToHomeWikiPage();
    getExoWebDriver().getWebDriver().navigate().refresh();
    tribeWikiHomePage.deleteWikiDW(editTitle);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space);
  }

  @Test
  public void test03_RemoveWikisPageOfSpace() {
    info("Remove wiki's page of space");
    info("Create a space");
    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);
    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.searchSpace(space);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePage(title, content);
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
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(space);

  }

  @Test
  public void test04_SearchWikiPageFromHomeSearchBar() {
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String title1 = "title" + getRandomNumber();
    String content1 = "content" + getRandomNumber();
    String title2 = "title" + getRandomNumber();
    String content2 = "content" + getRandomNumber();

    String space = "space" + getRandomNumber();
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);
    info("Create a wiki page");
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePage(title, content);
    tribeWikiManagement.saveAddPage();

    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePage(title1, content1);
    tribeWikiManagement.saveAddPage();

    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePage(title2, content2);
    tribeWikiManagement.saveAddPage();
    homePagePlatform.goToSnapshotPageTribe();
    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(title);
    sleep(3000);
    $(By.xpath("//*[@class='v-list-item__title' and @title='${title}']".replace("${title}",title))).isDisplayed();
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.searchSpace(space);
    tribeSpaceManagement.accessToSearchedSpace();
    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.deleteWikiDW(title);
    tribeWikiHomePage.deleteWikiDW(title1);
    tribeWikiHomePage.deleteWikiDW(title2);
    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space);

  }

  @Test
  public void test05_SwitchingToDworToMyNotesFromAWikiSpaceShouldNotDisplaySpaceNavigationAnymore() {

    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    tribeSpaceManagement.goToWikiTabDW(space);
    wikiHomePage.goToSpaceSwitcher();
    wikiHomePage.inputSpaceSwitcherTribe(space);
    wikiValidattions.verifyPresentSpaceSwitcher("Dw");
    wikiValidattions.verifyPresentSpaceSwitcher("Mes Notes");
    wikiValidattions.verifyPresentSpaceSwitcher(space);
    wikiHomePage.closeSpaceWitcherTribe();

    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb("Dw", null);
    wikiValidattions.verifyBreadCrumbePath("Global", "Wiki Home");

    info("Space Navigation is not displayed");
    $(byXpath("//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}']".replace("{space}",space)));

    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb("Mes Notes", null);
    wikiValidattions.verifyBreadCrumbePath("Mes Notes", "Wiki Home");

    info("Space Navigation is not displayed");
    $(byXpath("//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}']".replace("{space}",space)));

    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space);

  }

  @Test
  public void test06_VerifySpacesSwitcherWhenAddingNewWikiPages() {

    String rand = getRandomNumber();
    String title = "title" + rand;
    String content = "content" + rand;
    String content1 = "content1" + rand;
    String title1 = "title1" + rand;

    String space = "space" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space, space, "Open", "No", null);

    tribeSpaceManagement.goToWikiTabDW(space);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePage(title, content);
    tribeWikiManagement.saveAddPage();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(space2, space2, "Open", "No", null);

    tribeSpaceManagement.goToWikiTabDW(space2);
    tribeWikiHomePage.goToAddBlankPageDW();
    tribeWikiManagement.goToSourceEditor();
    tribeSourceTextEditor.addSimplePage(title1, content1);
    tribeWikiManagement.saveAddPage();

    wikiHomePage.goToSpaceSwitcher();
    wikiHomePage.inputSpaceSwitcherTribe(space);
    wikiValidattions.verifyPresentSpaceSwitcher("Dw");
    wikiValidattions.verifyPresentSpaceSwitcher("Mes Notes");
    wikiValidattions.verifyPresentSpaceSwitcher(space);
    wikiHomePage.closeSpaceWitcherTribe();

    wikiHomePage.goToSpaceSwitcher();
    wikiHomePage.inputSpaceSwitcherTribe(space2);
    wikiValidattions.verifyPresentSpaceSwitcher("Dw");
    wikiValidattions.verifyPresentSpaceSwitcher("Mes Notes");
    wikiValidattions.verifyPresentSpaceSwitcher(space2);
    wikiHomePage.closeSpaceWitcherTribe();

    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb(space, null);
    tribeWikiHomePage.goToAPage(title);
    info("Space Navigation is displayed");
    $(byXpath("//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}']".replace("{space}",space)));
    $(byText(space)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);
    $(byText(title)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);

    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb(space2, null);
    tribeWikiHomePage.goToAPage(title1);
    info("Space Navigation is displayed");
    $(byXpath("//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}']".replace("{space}",space2)));
    $(byText(space2)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);
    $(byText(title1)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);

    homePagePlatform.goToMySpacesTribe();
    tribeSpaceManagement.deleteTribeSpace(space);
    tribeSpaceManagement.deleteTribeSpace(space2);

  }

}
