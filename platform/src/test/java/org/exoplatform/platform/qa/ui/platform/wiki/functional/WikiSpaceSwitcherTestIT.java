package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;

@Tag("wiki")
@Tag("functional")
public class WikiSpaceSwitcherTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  ManageLogInOut         manageLogInOut;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

  WikiHomePage           wikiHomePage;

  WikiManagement         wikiManagement;

  RichTextEditor         richTextEditor;

  HomePagePlatform       homePagePlatform;

  WikiValidattions       wikiValidattions;

  UserAddManagement      userAddManagement;

  UserAndGroupManagement userAndGroupManagement;

  SpaceSettingManagement spaceSettingManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    richTextEditor = new RichTextEditor(this);
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    wikiValidattions = new WikiValidattions(this);
    wikiManagement = new WikiManagement(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    userAddManagement = new UserAddManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:139372.</li>
   * <li>Test Case Name: Changing from one space wiki to another should change the
   * currently browsed space.</li>
   * <li>Pre-Condition: User is member of Space 1 and Space 2 Wiki of Space 2 has
   * : - Page 1 - Page 2 --- Sub-Page 3</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Using
   * the left sidebar navigation, select "Space 2" Input Data: Expected Outcome: -
   * Space 2 is displayed with space navigation, on its activity stream Step
   * number: 2 Step Name: Step Description: - Go to wiki application Input Data:
   * Expected Outcome: - Space 2 is displaying wiki application. - The space
   * navigation is still displayed Step number: 3 Step Name: Step Description: -
   * Open the space switcher - Select "Space 1" Input Data: Expected Outcome: -
   * The wiki of "Space 1" is displayed - The Space navigation of "Space 2" is not
   * displayed anymore - Wiki is selected on the left sidebar navigation
   */
  @Test
  public void test01_ChangingWikiShouldDisplayTheRootPageOfTheTargetedWikiThenChangingFromOneSpaceWikiToAnotherShouldChangeTheCurrentlyBrowsedSpace() {
    info("Create 2 new users");
    ArrayList<String> arrayUsers;
    String password = "123456";
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);
    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);
    info("Create a space");
    String space1 = "space1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1, 6000);
    info("Changing wiki should display the root page of the targeted wiki");
    info("Create pages");
    String page1 = "page1" + getRandomNumber();
    homePagePlatform.goToSpecificSpace(space1);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(page1, page1);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(page1);
    info("Create pages");
    String page2 = "page2" + getRandomNumber();
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(page2, page2);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(page2);
    info("Create subpage");
    String subpage = "subpage" + getRandomNumber();
    wikiHomePage.goToAPage(page2);
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(subpage, subpage);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(subpage);
    homePagePlatform.goToWiki();
    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb(space1, null);
    wikiValidattions.verifyBreadCrumbePath(space1, "Wiki Home");
    $(ELEMENT_WIKI_TAB).waitUntil(Condition.visible, Configuration.timeout);
    info("Changing from one space wiki to another should change the currently browsed space");
    info("Create a space");
    String space2 = "space2" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2, 6000);
    info("Create pages");
    String page3 = "page3" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space2)).click();
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(page3, page3);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(page3);
    info("Create pages");
    String page4 = "page4" + getRandomNumber();
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(page4, page4);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(page4);
    info("Create subpage");
    String subpage1 = "subpage" + getRandomNumber();
    wikiHomePage.goToAPage(page4);
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(subpage1, subpage1);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(subpage1);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space2)).click();
    spaceManagement.goToWikiTab();
    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb(space1, null);
    wikiValidattions.verifyBreadCrumbePath(space1, "Wiki Home");
    $(ELEMENT_WIKI_TAB).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);
  }

  /**
   * <li>Case ID:139376.</li>
   * <li>Test Case Name: Switching to "My Wiki" from Company Wiki should display
   * User Navigation.</li>
   * <li>Pre-Condition: User is member of 0 spaces</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go to
   * "Company" wiki Input Data: Expected Outcome: - Company wiki is displayed -
   * Breadcrumb is displaying the space switcher component Step number: 2 Step
   * Name: Step Description: - Open the space switcher component Input Data:
   * Expected Outcome: - Space switcher is displaying its list with only:*
   * Intranet* My Wiki Step number: 3 Step Name: Step Description: - Select "My
   * Wiki" Input Data: Expected Outcome: - Personal wiki is displayed - Breacrumb
   * is displaying "My Wiki" - User navigation is displayed "My wiki" selected
   */

  @Test
  public void test_SwitchingToMyWikiFromCompanyWikiShouldDisplayUserNavigationThenSwitchingToASpaceWikiFromCompanyWikiShouldDisplaySpaceNavigationThenSelectingTheSameWikiShouldRedirectToTheWikisHome() {
    info("Switching to My Wiki from Company Wiki should display User Navigation");
    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);
    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);
    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    getExoWebDriver().getWebDriver().navigate().refresh();
    wikiHomePage.goToSpaceSwitcher();
    info("Space switcher list should display Intranet");
    $(ELEMENT_SPACE_SWITCHER_INTRANET_POSITION).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_SPACE_SWITCHER_INTRANET_ICON).waitUntil(Condition.visible, Configuration.timeout);
    info("Space switcher list should display My Wiki");
    $(ELEMENT_SPACE_SWITCHER_MY_WIKI_POSITION).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_SPACE_SWITCHER_MY_WIKI_ICON).waitUntil(Condition.visible, Configuration.timeout);
    wikiHomePage.inputSpaceSwitcher(" ");
    wikiValidattions.verifyPresentSpaceSwitcher("Intranet");
    wikiValidattions.verifyPresentSpaceSwitcher("My Wiki");
    wikiValidattions.verifyNoAnySpaces();
    wikiHomePage.closeSpaceWitcher();
    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb("My Wiki", arrayUsers.get(0));
    wikiValidattions.verifyBreadCrumbePath("My Wiki", "Wiki Home");
    $(ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI).waitUntil(Condition.visible, Configuration.timeout);
    info("Switching to a space wiki from Company wiki should display space navigation");
    info("Create a space");
    String space1 = "space1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1, 6000);
    spaceManagement.goToWikiTab();
    wikiHomePage.goToSpaceSwitcher();
    info("Switching to Intranet from a space wiki should not display space navigation anymore");
    wikiHomePage.inputSpaceSwitcher(space1);
    wikiValidattions.verifyPresentSpaceSwitcher("Intranet");
    wikiValidattions.verifyPresentSpaceSwitcher("My Wiki");
    wikiValidattions.verifyPresentSpaceSwitcher(space1);
    wikiHomePage.closeSpaceWitcher();
    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb("Intranet", null);
    wikiValidattions.verifyBreadCrumbePath("Intranet", "Wiki Home");
    waitForElementNotPresent(ELEMENT_WIKI_TAB);
    info("Space switcher is displaying the name of the space if browsing a space's wiki");
    homePagePlatform.goToSpecificSpace(space1);
    wikiValidattions.verifyPresentSpaceSwitcher(space1);
    spaceHomePage.goToWikiTab();
    wikiValidattions.verifyBreadCrumbePath(space1, "Wiki Home");
    info("Space switcher is displaying My Wiki for user personal wiki");
    navigationToolbar.goToMyWiki();
    wikiValidattions.verifyBreadCrumbePath("My Wiki", "Wiki Home");
    homePagePlatform.goToWiki();
    info("Space switcher is displaying Intranet when user is browsing the company wiki");
    wikiValidattions.verifyBreadCrumbePath("Intranet", "Wiki Home");
    wikiHomePage.goToSpaceSwitcher();
    info("Space switcher is displaying a list when opening it");
    wikiValidattions.verifyPresentSpaceSwitcher("Intranet");
    wikiValidattions.verifyPresentSpaceSwitcher("My Wiki");
    wikiValidattions.verifyPositionOfASpaceInList(1, space1);
    info("Space switcher is closed if user is clicking outside the list");
    $(ELEMENT_SPACE_SWITCHER_TITLE).waitUntil(Condition.visible, Configuration.timeout);
    wikiHomePage.closeSpaceSwitcherByClickOutSide();
    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    info("Input text should display a placeholder");
    $(ELEMENT_SPACE_SWITCHER_SEARCH_FIELD_PLACEHOLDER_TEXT).waitUntil(Condition.visible, Configuration.timeout);
    info("Space switcher component should display a title");
    $(ELEMENT_SPACE_SWITCHER_TITLE).waitUntil(Condition.visible, Configuration.timeout);
    info("List of spaces should be displayed when user is member of at least one space");
    $(byXpath(ELEMENT_SPACE_SWITCHER_SPACE_AVATAR.replace("$space", space1.toLowerCase()))).waitUntil(Condition.visible,
            Configuration.timeout);
    info("Space switcher is closed if user is clicking on the close button");
    $(ELEMENT_SPACE_SWITCHER_TITLE).waitUntil(Condition.visible, Configuration.timeout);
    wikiHomePage.inputSpaceSwitcher(space1);
    wikiValidattions.verifyPresentSpaceSwitcher("Intranet");
    wikiValidattions.verifyPresentSpaceSwitcher("My Wiki");
    wikiValidattions.verifyPresentSpaceSwitcher(space1);
    wikiHomePage.closeSpaceWitcher();
    $(ELEMENT_SPACE_SWITCHER_TITLE).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb(space1, null);
    wikiValidattions.verifyBreadCrumbePath(space1, "Wiki Home");
    $(ELEMENT_WIKI_TAB).waitUntil(Condition.visible, Configuration.timeout);
    info("Selecting the same wiki should redirect to the wiki's home");
    info("Create pages");
    String page1 = "page1" + getRandomNumber();
    homePagePlatform.goToSpecificSpace(space1);
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(page1, page1);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(page1);
    info("Create pages");
    String page2 = "page2" + getRandomNumber();
    wikiHomePage.goToHomeWikiPage();
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(page2, page2);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(page2);
    info("Create subpage");
    String subpage = "subpage" + getRandomNumber();
    wikiHomePage.goToAPage(page2);
    wikiHomePage.goToAddBlankPage();
    richTextEditor.addSimplePage(subpage, subpage);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(subpage);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space1)).click();
    spaceManagement.goToWikiTab();
    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb(space1, null);
    wikiValidattions.verifyBreadCrumbePath(space1, "Wiki Home");
    $(ELEMENT_WIKI_TAB).waitUntil(Condition.visible, Configuration.timeout);
    info("Space Switcher must only display spaces where wiki application exist");
    info("Remove wiki application");
    homePagePlatform.goToSpecificSpace(space1);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.removeApplication("Wiki");
    info("Verify that Wiki application on the menu is removed");
    refresh();
    $(ELEMENT_SPACE_WIKI_TAB).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    info("Go to the wiki of the intranet");
    homePagePlatform.goToWiki();
    wikiValidattions.verifyBreadCrumbePath("Intranet", "Wiki Home");
    wikiHomePage.goToSpaceSwitcher();
    wikiValidattions.verifyNotPresentSpaceSwitcher(space1);
    wikiHomePage.inputSpaceSwitcher(space1);
    wikiValidattions.verifyNotPresentSpaceSwitcher(space1);
    wikiHomePage.closeSpaceWitcher();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);

  }

}
