package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ChangeLanguages;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_MY_SPACE_LINK_PLF;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Tag("sniff")
@Tag("social")
public class SOCSpaceManagementTestIT extends Base {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  SpaceManagement spaceManagement;

  SpaceHomePage spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  WikiHomePage wikiHomePage;

  WikiManagement wikiManagement;

  SourceTextEditor sourceTextEditor;

  ChangeLanguages changeLanguages;

  ActivityStream activityStream;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    changeLanguages = new ChangeLanguages(this);
    activityStream = new ActivityStream(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:121887.</li>
   * <li>Test Case Name: Access Space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Access space Step
   * Description: - Login by user - Create new space - On Space list, click on
   * space's name or avatar of space Input Data: Expected Outcome: - Display
   * spaces list - Show content of space with: + Focus on home space page + All
   * default portlet display: Home space, Discussion, Members, Wiki, Documents
   * Space settings.
   */
  @Test
  @Tag("sabis")
  public void test01_AccessSpace() {
    info("Test 01: Access Space");
    String space = "space" + getRandomNumber();
    String space3 = "space" + getRandomNumber();
    String space4 = "space" + getRandomNumber();
    String space5 = "space" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String newName = "newName" + getRandomNumber();
    String newDEs = "newDEs" + getRandomNumber();
    String filename = "testavatar.png";
    String space1 = "space1" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();
    String[] arrayRight = {"open"};
    String mess = "You must be a member of the space " + space2 + " to view this page.";
    String[] arrayRight2 = {"hidden"};
    String mess2 = "You must be a member of the space " + space3 + " to view this page.";
    String[] arrayRight3 = {"close"};
    String mess3 = "This page is in a restricted area. Get an invitation by a manager of space '" + space4 + "' to access it.";
    String mess4 = "You must be a member of the space " + space5 + " to view this page.";
    String app = "Bookmarks";
    info("app:" + app);
    String category = "Tools";
    info("cate:" + category);
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);

    manageLogInOut.signIn(username1, password);
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("All default portlet is displayed");
    $(byXpath("//i[@class='uiIconAppSpaceHomePage uiIconDefaultApp']")).waitUntil(Condition.exist, Configuration.timeout);
    $(ELEMENT_SPACE_MENU_AGENDA_PORTLET).should(Condition.exist);
    $(ELEMENT_SPACE_MENU_DOCUMENT_PORTLET).should(Condition.exist);
    $(ELEMENT_SPACE_MENU_FORUM_PORTLET).should(Condition.exist);
    $(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB).should(Condition.exist);
    $(ELEMENT_SPACE_SPACE_SETTINGS).should(Condition.exist);
    $(ELEMENT_SPACE_WIKI_TAB).should(Condition.exist);

    info("Edit the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space, "");
    spaceManagement.editSpaceSimple(space, newName, newDEs, true, filename);
    spaceManagement.saveChangesSpace();
    waitForAndGetElement(ELEMENT_SPACE_MENU_ACTIVITY_PORTLET, 2000, 1);
    info("All changes are saved");
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(newName, "");
    $(byXpath(ELEMENT_SPACE_TITLE.replace("${space}", newName))).should(Condition.visible);
    ELEMENT_SPACES_LIST.find(byText(newName)).parent().parent().find(byText(newDEs)).should(Condition.visible);
    $(ELEMENT_SPACE_AVATAR_DEFAULT).should(Condition.not(Condition.exist));

    info("Check Access Visible Open Space");
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    info("Set permission for the space");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToAccessEditTab();
    spaceSettingManagement.setPermissionForSpace(arrayRight);
    spaceManagement.goToActivityStreamTab();
    String urlSpace = url();

    manageLogInOut.signIn(username2, password);
    homePagePlatform.refreshUntil(ELEMENT_MY_SPACE_LINK_PLF, Condition.visible, 1000);
    open(urlSpace);
    homePagePlatform.refreshUntil($(ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE), Condition.visible, 1000);
    $(ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE).should(Condition.visible);
    $(ELEMENT_SPACE_ACCESS_INFO).shouldHave(Condition.text(mess));
    $(ELEMENT_SPACE_ACCESS_JOIN_BTN).click();
    $(ELEMENT_SPACE_WIKI_TAB).waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToAllSpace();
    spaceManagement.searchSpace(newName, "");
    spaceManagement.sendARequestToASpace(newName);
    info("Check Access Hidden Space");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space3, space3);
    info("Set permission for the space");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToAccessEditTab();
    spaceSettingManagement.setPermissionForSpace(arrayRight2);
    spaceManagement.goToActivityStreamTab();
    String urlSpace2 = url();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.refreshUntil(ELEMENT_MY_SPACE_LINK_PLF, Condition.visible, 1000);
    open(urlSpace2);
    homePagePlatform.refreshUntil($(ELEMENT_SPACE_ACCESS_SPACE_DENIED), Condition.visible, 1000);
    $(ELEMENT_SPACE_ACCESS_SPACE_DENIED).is(Condition.visible);
    $(ELEMENT_SPACE_ACCESS_SPACE_DENIED_INFO).shouldHave(Condition.text(mess2));

    info("Check Access Visible Close Space");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space4, space4);
    info("Set permission for the space");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToAccessEditTab();
    spaceSettingManagement.setPermissionForSpace(arrayRight3);
    spaceManagement.goToActivityStreamTab();
    String urlSpace3 = url();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.refreshUntil(ELEMENT_MY_SPACE_LINK_PLF, Condition.visible, 1000);
    open(urlSpace3);
    homePagePlatform.refreshUntil($(ELEMENT_SPACE_ACCESS_SPACE_DENIED), Condition.visible, 2000);
    $(ELEMENT_SPACE_ACCESS_SPACE_DENIED).is(Condition.visible);
    sleep(2000);
    $(ELEMENT_SPACE_ACCESS_SPACE_DENIED_INFO).shouldHave(Condition.text(mess3));

    info("Check access visible/validation space");
    info("Create a space");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpace(space5, space5, "validation", "No", "");
    spaceManagement.goToActivityStreamTab();
    String urlSpace4 = url();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.refreshUntil(ELEMENT_MY_SPACE_LINK_PLF, Condition.visible, 1000);
    open(urlSpace4);
    homePagePlatform.refreshUntil($(ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE), Condition.visible, 1000);
    $(ELEMENT_SPACE_ACCESS_INFO).shouldHave(Condition.text(mess4));
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(ELEMENT_SPACE_ACCESS_REQUEST_JOIN_BTN).waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE).shouldNot(Condition.visible);
    $(ELEMENT_SPACE_ACCESS_INFO).shouldNot(Condition.visible);

    info("Delete a Space");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//div[@class=\"communityContainer\"]/span[text()='${app}']".replace("${app}", app))).exists();
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("Bookmark")).should(Condition.exist);
    }
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space1);
    spaceManagement.accessToSearchedSpace();
    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    info("Verify that Application is added to space");
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);
    executeJavaScript("window.scrollBy(0,200)", "");
    sleep(2000);
    $(byXpath("//div[@class=\"communityContainer\"]/span[text()='${app}']".replace("${app}", app))).exists();
    sleep(1000);
    spaceSettingManagement.removeApplication(app);
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.visible))
      $(ELEMENT_SPACE_MENU_MORE).click();
    ELEMENT_SPACE_MENU_TAB.find(byId("ForumsStatistic")).shouldNot(Condition.exist);

    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);

  }

  @Test
  @Tag("SOC-6067")
  public void test02_CheckWhenUserIsMemberThenNotMemberOfSpace() {
    String space = "space" + getRandomNumber();
    String space0 = "space" + getRandomNumber();
    String wiki = "wiki" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String email2 = username2 + "@test.com";
    String password = "123456";
    String activity1 = "activity1" + getRandomNumber();
    String space1 = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();


    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space0, space0);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username3, false, "");

    manageLogInOut.signIn(username3, password);
    info("Translate page titles");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage("French", "Apply");
    spaceManagement.goToTaskTab();
    assertEquals(title(), "(1) " + space1 + " - Tâches");
    spaceManagement.goToForumTab();
    assertEquals(title(), "(1) " + space1 + " - Forum");
    spaceManagement.goToAgendaTab();
    assertEquals(title(), "(1) " + space1 + " - Calendrier");
    spaceManagement.goToMemberTab();
    assertEquals(title(), "(1) " + space1 + " - Membres");
    navigationToolbar.goToChangeLanguage();
    changeLanguages.changeLanguage("Anglais", "Appliquer");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);

    info("Check Not Permission To Edit Space App");
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space0, "");
    spaceManagement.acceptAInvitation(space0);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space0, "");
    ELEMENT_SPACES_LIST.find(byText(space0)).click();
    activityStream.addActivity(activity1, "");
    $(ELEMENT_ACTIVITY_STREAM_TAB).parent().find(byClassName("tabName")).doubleClick();
    $(byValue(" Activity Stream")).shouldNotBe(Condition.visible);
    spaceManagement.goToTaskTab();
    ELEMENT_SPACE_MENU_TAB.find(ELEMENT_TASK_TAB).parent().find(byClassName("tabName")).doubleClick();
    $(byValue("Tasks")).shouldNotBe(Condition.visible);

    info("Check when user is member of space");
    manageLogInOut.signIn(username1, password);
    info("Create space 1 and wiki page 1");
    homePagePlatform.goToMySpaces();
    sleep(2000);
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username2, false, "");

    info("Add new wiki page 1 for space 1");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    $(byXpath(ELEMENT_TREE_WIKI_NAME.replace("${name}", wiki))).should(Condition.visible);

    wikiHomePage.goToPermalink();
    String perLink = ELEMENT_WIKI_PERMELINK.getValue();
    ELEMENT_BUTTON_CLOSE_PERMALINK.click();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space, "");
    spaceManagement.acceptAInvitation(space);
    open(perLink);
    refresh();
    $(byXpath(ELEMENT_WIKI_PAGE_LEFTBOX.replace("${title}", wiki))).should(Condition.visible);

    info("Check when user is not member of space");
    manageLogInOut.signIn(username1, password);
    info("Create space 1 and wiki page 1");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    info("Add new wiki page 1 for space 1");
    spaceHomePage.goToWikiTab();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(wiki, wiki);
    wikiManagement.saveAddPage();
    $(byXpath(ELEMENT_TREE_WIKI_NAME.replace("${name}", wiki))).should(Condition.visible);

    wikiHomePage.goToPermalink();
    String perLink2 = ELEMENT_WIKI_PERMELINK.getValue();
    ELEMENT_BUTTON_CLOSE_PERMALINK.click();
    manageLogInOut.signIn(username2, password);
    open(perLink2);
    homePagePlatform.refreshUntil($(ELEMENT_WIKI_HOME_PAGENOTFOUND), Condition.visible, 1000);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space0, false);

  }

}
