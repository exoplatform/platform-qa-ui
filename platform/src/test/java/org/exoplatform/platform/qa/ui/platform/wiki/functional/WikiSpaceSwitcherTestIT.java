package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
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
  public void test01_ChangingFromOneSpaceWikiToAnotherShouldChangeTheCurrentlyBrowsedSpace() {
    info("Test 1: Changing from one space wiki to another should change the currently browsed space");

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
    info("Create a space");
    String space2 = "space2" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2, 6000);

    info("Create pages");
    String page1 = "page1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space2)).click();
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
   * <li>Case ID:139373.</li>
   * <li>Test Case Name: Changing wiki should display the root page of the
   * targeted wiki.</li>
   * <li>Pre-Condition: User is member of Space 2Wiki of Space 2 has : - Page 1 -
   * Page 2 --- Sub-Page 3</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go
   * into Company wiki Input Data: Expected Outcome: - Company wiki is displayed -
   * Space switcher is displayed in the breadcrumb Step number: 2 Step Name: Step
   * Description: - Open Space switcher component Input Data: Expected Outcome: -
   * Space switcher is displaying its list of options Step number: 3 Step Name:
   * Step Description: - Select "Space 2" Input Data: Expected Outcome: - Wiki of
   * Space 2 is opened on "Wiki Home" page
   */
  @Test
  @Tag("wabis")
  public void test02_ChangingWikiShouldDisplayTheRootPageOfTheTargetedWiki() {
    info("Test 2: Changing wiki should display the root page of the targeted wiki");

    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);

    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);
    info("Create a space");
    String space1 = "space1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1, 6000);
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
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);
  }

  /**
   * <li>Case ID:139374.</li>
   * <li>Test Case Name: Selecting the same wiki should redirect to the wiki's
   * home.</li>
   * <li>Pre-Condition: User is member of Space 2Wiki of Space 2 has : - Page 1 -
   * Page 2 --- Sub-Page 3</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go
   * into "Space 2" wiki Input Data: Expected Outcome: - "Space 2" wiki is
   * displayed - Space switcher is displayed in the breadcrumb Step number: 2 Step
   * Name: Step Description: - Open Space switcher component Input Data: Expected
   * Outcome: - Space switcher is displaying its list of options Step number: 3
   * Step Name: Step Description: - Select "Space 2" Input Data: Expected Outcome:
   * - Wiki of Space 2 is opened on "Wiki Home" page
   */
  @Test
  public void test03_SelectingTheSameWikiShouldRedirectToTheWikisHome() {
    info("Test 3: Selecting the same wiki should redirect to the wiki's home");

    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);

    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);

    info("Create a space");
    String space1 = "space1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1, 6000);

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
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);

  }

  /**
   * <li>Case ID:139375.</li>
   * <li>Test Case Name: Switching to "Intranet" from a space wiki should not
   * display space navigation anymore.</li>
   * <li>Pre-Condition: User is member of "Space 1"</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go
   * into to "Space 1" - Select wiki application Input Data: Expected Outcome: -
   * Space 1 is displayed - Wiki is opened Step number: 2 Step Name: Step
   * Description: - Open the space switcher component Input Data: Expected
   * Outcome: - Space switcher is displaying its list with only:* Intranet* My
   * Wiki* input text* Space 1 Step number: 3 Step Name: Step Description: -
   * Select "Intranet" Input Data: Expected Outcome: - Intranet wiki is displayed
   * - Breadcrumb is displaying "Intranet" - Space navigation from "Space 1" is
   * not displayed anymore
   */
  @Test
  public void test04_SwitchingToIntranetFromASpaceWikiShouldNotDisplaySpaceNavigationAnymore() {
    info("Test 4: Switching to Intranet from a space wiki should not display space navigation anymore");

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
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space1)).click();
    spaceManagement.goToWikiTab();
    wikiHomePage.goToSpaceSwitcher();
    wikiHomePage.inputSpaceSwitcher(space1);
    wikiValidattions.verifyPresentSpaceSwitcher("Intranet");
    wikiValidattions.verifyPresentSpaceSwitcher("My Wiki");
    wikiValidattions.verifyPresentSpaceSwitcher(space1);
    wikiHomePage.closeSpaceWitcher();
    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb("Intranet", null);
    wikiValidattions.verifyBreadCrumbePath("Intranet", "Wiki Home");
    waitForElementNotPresent(ELEMENT_WIKI_TAB);
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
  public void test05_SwitchingToMyWikiFromCompanyWikiShouldDisplayUserNavigation() {
    info("Test 5: Switching to My Wiki from Company Wiki should display User Navigation");

    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);

    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);

    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    wikiHomePage.inputSpaceSwitcher(" ");
    wikiValidattions.verifyPresentSpaceSwitcher("Intranet");
    wikiValidattions.verifyPresentSpaceSwitcher("My Wiki");
    wikiValidattions.verifyNoAnySpaces();
    wikiHomePage.closeSpaceWitcher();
    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb("My Wiki", arrayUsers.get(0));
    wikiValidattions.verifyBreadCrumbePath("My Wiki", "Wiki Home");
    $(ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);

  }

  /**
   * <li>Case ID:139377.</li>
   * <li>Test Case Name: Switching to a space wiki from Company wiki should
   * display space navigation.</li>
   * <li>Pre-Condition: User is member of "Space 1"</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go to
   * "Company" wiki Input Data: Expected Outcome: - Company wiki is displayed -
   * Breadcrumb is displaying the space switcher component Step number: 2 Step
   * Name: Step Description: - Open the space switcher component Input Data:
   * Expected Outcome: - Space switcher is displaying its list with only:*
   * Intranet* My Wiki* input text* Space 1 Step number: 3 Step Name: Step
   * Description: - Select "Space 1" Input Data: Expected Outcome: - Personal wiki
   * is displayed - Breacrumb is displaying "Space 1" - Space navigation is
   * displayed ("Wiki" application selected)
   */
  @Test
  @Tag("wabis")
  public void test06_SwitchingToASpaceWikiFromCompanyWikiShouldDisplaySpaceNavigation() {
    info("Test 6: Switching to a space wiki from Company wiki should display space navigation");
    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);
    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);
    info("Create a space");
    String space1 = "space1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1, 6000);
    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    wikiHomePage.inputSpaceSwitcher(space1);
    wikiValidattions.verifyPresentSpaceSwitcher("Intranet");
    wikiValidattions.verifyPresentSpaceSwitcher("My Wiki");
    wikiValidattions.verifyPresentSpaceSwitcher(space1);
    wikiHomePage.closeSpaceWitcher();
    wikiHomePage.goToWikiHomeOfSpaceFromBreadcrumb(space1, null);
    wikiValidattions.verifyBreadCrumbePath(space1, "Wiki Home");
    $(ELEMENT_WIKI_TAB).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);
  }

  /**
   * <li>Case ID:139379.</li>
   * <li>Test Case Name: Input text should display a placeholder.</li>
   * <li>Pre-Condition: User is member of some spaces</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go to
   * Company wiki Input Data: Expected Outcome: - Company wiki is displayed -
   * Breadcrumb is displaying Space Switcher Step number: 2 Step Name: Step
   * Description: - Open the Space Switcher Input Data: Expected Outcome: - The
   * first item of the list is an input text field - Placeholder is "Filter
   * spaces"
   */
  @Test
  public void test08_InputTextShouldDisplayAPlaceholder() {
    info("Test 8: Input text should display a placeholder");

    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);

    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);

    info("Create a space");
    String space1 = "space1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1, 6000);
    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    $(ELEMENT_SPACE_SWITCHER_SEARCH_FIELD_PLACEHOLDER_TEXT).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);

  }

  /**
   * <li>Case ID:139380.</li>
   * <li>Test Case Name: List of spaces should be displayed when user is member of
   * at least one space.</li>
   * <li>Pre-Condition: User is member of the space "Mobile" Mobile Space avatar
   * is defined</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go to
   * Company wiki Input Data: Expected Outcome: - Company wiki is displayed -
   * Breadcrumb is displaying Space Switcher* Step number: 2 Step Name: Step
   * Description: - Open the Space Switcher Input Data: Expected Outcome: - The
   * list of spaces is displayed after the input textfield - First item is
   * "Mobile" - Avatar of the space is displayed before "Mobile"
   */
  @Test
  public void test09_ListOfSpacesShouldBeDisplayedWhenUserIsMemberOfAtLeastOneSpace() {
    info("Test 9: List of spaces should be displayed when user is member of at least one space");

    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);

    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);
    info("Create a space");
    String space1 = "space1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1, 6000);
    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    $(ELEMENT_SPACE_SWITCHER_SEARCH_FIELD_PLACEHOLDER_TEXT).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_SPACE_SWITCHER_SPACE_AVATAR.replace("$space", space1.toLowerCase()))).waitUntil(Condition.visible,
                                                                                                      Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);
  }

  /**
   * <li>Case ID:139381.</li>
   * <li>Test Case Name: Space switcher component should display a title.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go to
   * Company wiki Input Data: Expected Outcome: - Company wiki is displayed -
   * Breadcrumb is displaying Space Switcher Step number: 2 Step Name: Step
   * Description: - Open the Space Switcher Input Data: Expected Outcome: - Space
   * switcher title is displaying "Select Location"
   */
  @Test
  public void test10_SpaceSwitcherComponentShouldDisplayATitle() {
    info("Test 10 Space switcher component should display a title");

    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    $(ELEMENT_SPACE_SWITCHER_TITLE).waitUntil(Condition.visible, Configuration.timeout);

  }

  /**
   * <li>Case ID:139382.</li>
   * <li>Test Case Name: Space switcher is closed if user is clicking on the close
   * button.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go to
   * Wiki using left sidebar navigation Input Data: Expected Outcome: - Wiki
   * application is displayed - Breadcrumb is displayed with space switcher
   * component Step number: 2 Step Name: Step Description: - Open the space
   * switcher Input Data: Expected Outcome: - space switcher is displaying its
   * list - space switcher is displaying on the top right, a small "x" icon to
   * close Step number: 3 Step Name: Step Description: - Click on the "x" icon
   * Input Data: Expected Outcome: - Space switcher is closed (not showing its
   * list anymore)
   */
  @Test
  public void test11_SpaceSwitcherIsClosedIfUserIsClickingOnTheCloseButton() {
    info("Test 11 Space switcher is closed if user is clicking on the close button");
    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    $(ELEMENT_SPACE_SWITCHER_TITLE).waitUntil(Condition.visible, Configuration.timeout);
    wikiHomePage.closeSpaceWitcher();
    $(ELEMENT_SPACE_SWITCHER_TITLE).waitUntil(Condition.not(Condition.visible), Configuration.timeout);

  }

  /**
   * <li>Case ID:139383.</li>
   * <li>Test Case Name: Space switcher is closed if user is clicking outside the
   * list.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go to
   * Wiki using left sidebar navigation Input Data: Expected Outcome: - Wiki
   * application is displayed - Breadcrumb is displayed with space switcher
   * component Step number: 2 Step Name: Step Description: - Open the space
   * switcher Input Data: Expected Outcome: - space switcher is displaying its
   * list Step number: 3 Step Name: Step Description: - Click outiside the list
   * Input Data: Expected Outcome: - Space switcher is closed (not showing its
   * list anymore)
   */
  @Test
  public void test12_SpaceSwitcherIsClosedIfUserIsClickingOutsideTheList() {
    info("Test 12 Space switcher is closed if user is clicking outside the list");

    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    $(ELEMENT_SPACE_SWITCHER_TITLE).waitUntil(Condition.visible, Configuration.timeout);
    wikiHomePage.closeSpaceSwitcherByClickOutSide();

  }

  /**
   * <li>Case ID:139384.</li>
   * <li>Test Case Name: Space switcher is displaying "Intranet" when user is
   * browsing the company wiki.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: -
   * connect to Intranet Input Data: Expected Outcome: - left sidebar navigation
   * is displayed Step number: 2 Step Name: Step Description: - using the left
   * sidebar navigation, go into the company wiki Input Data: Expected Outcome: -
   * wiki's breadcrumb is showing the space switcher component with the label
   * "Intranet"
   */
  @Test
  public void test13_SpaceSwitcherIsDisplayingIntranetWhenUserIsBrowsingTheCompanyWiki() {
    info("Test 13 Space switcher is displaying Intranet when user is browsing the company wiki");

    homePagePlatform.goToWiki();
    wikiValidattions.verifyBreadCrumbePath("Intranet", "Wiki Home");

  }

  /**
   * <li>Case ID:139385.</li>
   * <li>Test Case Name: Space switcher is displaying "My Wiki" for user personal
   * wiki.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: -
   * connect to Intranet Input Data: Expected Outcome: - Intranet is displaying
   * top navigation bar Step number: 2 Step Name: Step Description: - using the
   * top navigation bar, go into user menu> Wiki Input Data: Expected Outcome: -
   * Personal wiki is displayed Step number: 3 Step Name: Step Description: -
   * check display of "My Wiki" on Space Switcher Input Data: Expected Outcome: -
   * Space Switcher component is displayed in the breadcrumb with value "My Wiki"
   */
  @Test
  public void test14_SpaceSwitcherIsDisplayingMyWikiForUserPersonalWiki() {
    info("Test 14 Space switcher is displaying My Wiki for user personal wiki");

    homePagePlatform.goToWiki();
    wikiValidattions.verifyBreadCrumbePath("Intranet", "Wiki Home");
    navigationToolbar.goToMyWiki();
    wikiValidattions.verifyBreadCrumbePath("My Wiki", "Wiki Home");

  }

  /**
   * <li>Case ID:139386.</li>
   * <li>Test Case Name: Space switcher is displaying a list when opening it.</li>
   * <li>Pre-Condition: User is member of no spaceUser is connect to the
   * intranet</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go to
   * Company Wiki Input Data: Expected Outcome: - Company wiki is displayed -
   * Breadcrumb is displaying the space switcher* Step number: 2 Step Name: Step
   * Description: - Click on the space switcher Input Data: Expected Outcome: - A
   * list is opened
   */
  @Test
  public void test15_SpaceSwitcherIsDisplayingAListWhenOpeningIt() {
    info("Test 15 Space switcher is displaying a list when opening it");

    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);
    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);
    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    wikiValidattions.verifyPresentSpaceSwitcher("Intranet");
    wikiValidattions.verifyPresentSpaceSwitcher("My Wiki");
    wikiHomePage.closeSpaceWitcher();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);
  }

  /**
   * <li>Case ID:139387.</li>
   * <li>Test Case Name: Space switcher is displaying the name of the space if
   * browsing a space's wiki.</li>
   * <li>Pre-Condition: User is member of a space named: Mobile</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: -
   * connect to Intranet Input Data: Expected Outcome: - Intranet is displaying
   * left sidebar navigation Step number: 2 Step Name: Step Description: - using
   * the left sidebar navigation, go into the space "Mobile" Input Data: Expected
   * Outcome: - Intranet is displaying space navigation* Step number: 3 Step Name:
   * Step Description: - using space navigation, go into wiki application Input
   * Data: Expected Outcome: - Space Switcher component is displayed in the
   * breadcrumb with value "Mobile"
   */
  @Test
  public void test16_SpaceSwitcherIsDisplayingTheNameOfTheSpaceIfBrowsingASpacesWiki() {
    info("Test 16 Space switcher is displaying the name of the space if browsing a space's wiki");

    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);
    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);
    info("Create a space");
    String space1 = "space1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1, 6000);
    homePagePlatform.goToSpecificSpace(space1);
    spaceHomePage.goToWikiTab();
    wikiValidattions.verifyBreadCrumbePath(space1, "Wiki Home");
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);
  }

  /**
   * <li>Case ID:139388.</li>
   * <li>Test Case Name: Space switcher is displaying the name of the space if
   * browsing a space's wiki in its list.</li>
   * <li>Pre-Condition: User is member of a space named: Mobile</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: -
   * connect to Intranet Input Data: Expected Outcome: - Intranet is displaying
   * left sidebar navigation Step number: 2 Step Name: Step Description: - using
   * the left sidebar navigation, go into the space "Mobile" Input Data: Expected
   * Outcome: - Intranet is displaying space navigation Step number: 3 Step Name:
   * Step Description: - using space navigation, go into wiki application Input
   * Data: Expected Outcome: - Space Switcher component is displayed in the
   * breadcrumb with value "Mobile" Step number: 4 Step Name: Step Description: -
   * Open the Space switcher Input Data: Expected Outcome: - In the list of
   * spaces, "Mobile" is the first one on the list
   */
  @Test
  public void test17_SpaceSwitcherIsDisplayingTheNameOfTheSpaceIfBrowsingASpacesWikiInItsList() {
    info("Test 17 Space switcher is displaying the name of the space if browsing a space's wiki in its list");

    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);

    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);

    info("Create a space");
    String space1 = "space1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1, 6000);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space1)).click();
    spaceHomePage.goToWikiTab();
    wikiValidattions.verifyBreadCrumbePath(space1, "Wiki Home");
    wikiHomePage.goToSpaceSwitcher();
    wikiValidattions.verifyPresentSpaceSwitcher("Intranet");
    wikiValidattions.verifyPresentSpaceSwitcher("My Wiki");
    wikiValidattions.verifyPositionOfASpaceInList(1, space1);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);
  }

  /**
   * <li>Case ID:139389.</li>
   * <li>Test Case Name: Space switcher list should display "Intranet".</li>
   * <li>Pre-Condition: User is member of 0 spaceUser has not browsed any
   * space</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go to
   * Company wiki Input Data: Expected Outcome: - Company wiki is displayed -
   * Breadcrumb is displaying Space Switcher Step number: 2 Step Name: Step
   * Description: - Open the Space Switcher Input Data: Expected Outcome: - The
   * first item of the list is "Intranet" - Before "Intranet", an icon is
   * displayed (as in the test case description)
   */
  @Test
  public void test18_SpaceSwitcherListShouldDisplayIntranet() {
    info("Test 18 Space switcher list should display Intranet");

    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);

    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);
    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    $(ELEMENT_SPACE_SWITCHER_INTRANET_POSITION).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_SPACE_SWITCHER_INTRANET_ICON).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);
  }

  /**
   * <li>Case ID:139390.</li>
   * <li>Test Case Name: Space switcher list should display "My Wiki".</li>
   * <li>Pre-Condition: User is member of 0 Spaces</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - Go to
   * Company wiki Input Data: Expected Outcome: - Company wiki is displayed -
   * Breadcrumb is displaying Space Switcher
   */

  /*
   * Step number: 2 Step Name: Step Description: - Open the Space Switcher Input
   * Data: Expected Outcome: - The second item of the list is "My Wiki" - An icon
   * is displayed before "My Wiki" (as in the description of the test case)
   */
  @Test
  public void test19_SpaceSwitcherListShouldDisplayMyWiki() {
    info("Test 19 Space switcher list should display My Wiki");

    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);

    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);

    homePagePlatform.goToWiki();
    wikiHomePage.goToSpaceSwitcher();
    $(ELEMENT_SPACE_SWITCHER_MY_WIKI_POSITION).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_SPACE_SWITCHER_MY_WIKI_ICON).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteAllUsers(arrayUsers);
  }

  /**
   * <li>Case ID:139391.</li>
   * <li>Test Case Name: Space Switcher must only display spaces where wiki
   * application exist.</li>
   * <li>Pre-Condition: User is member of some spaces User is administrator of
   * space "Without Wiki", where wiki application has been removed</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step Description: - From
   * left sidebar navigation, go into the space "Without Wiki" Input Data:
   * Expected Outcome: - Space "Without Wiki" is displayed - Wiki application is
   * not available in the space navigation menu Step number: 2 Step Name: Step
   * Description: - From left sidebar navigation, go into the wiki Input Data:
   * Expected Outcome: - Wiki application is displayed - Breadcrumb is displayed
   * with the space switcher component* Step number: 3 Step Name: Step
   * Description: - Open the space switcher component Input Data: Expected
   * Outcome: - In the list of spaces, "Without Wiki" is not displayed Step
   * number: 4 Step Name: Step Description: - in the filter, type "Without" Input
   * Data: Expected Outcome: - In the list of spaces, "Without Wiki" is NOT
   * displayed
   */
  @Test
  public void test20_SpaceSwitcherMustOnlyDisplaySpacesWhereWikiApplicationExist() {
    info("Test 20 Space Switcher must only display spaces where wiki application exist");
    info("Create 2 new users");
    String password = "123456";
    ArrayList<String> arrayUsers;
    navigationToolbar.goToAddUser();
    arrayUsers = userAddManagement.addUsers(1, password);
    info("Login with user 1");
    manageLogInOut.signIn(arrayUsers.get(0), password);
    info("Create a space");
    String space1 = "space1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1, 6000);
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
