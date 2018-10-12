package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
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
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
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
    public void test01_AccessSpace() {
        info("Test 01: Access Space");
        String space = "space" + getRandomNumber();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String password = "123456";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        manageLogInOut.signIn(username1, password);

        info("Create a space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        info("All default portlet is displayed");
        $(ELEMENT_SPACE_MENU_ACTIVITY_PORTLET).should(Condition.exist);
        $(ELEMENT_SPACE_MENU_AGENDA_PORTLET).should(Condition.exist);
        $(ELEMENT_SPACE_MENU_DOCUMENT_PORTLET).should(Condition.exist);
        $(ELEMENT_SPACE_MENU_FORUM_PORTLET).should(Condition.exist);
        $(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB).should(Condition.exist);
        $(ELEMENT_SPACE_SPACE_SETTINGS).should(Condition.exist);
        $(ELEMENT_SPACE_WIKI_TAB).should(Condition.exist);

        info("Delete a Space");
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }

    /**
     * <li>Case ID:121889.</li>
     * <li>Test Case Name: Add application on Space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li>
     */
    @Test
    public void test07_AddApplicationOnSpace() {
        info("Test 07: Add application on Space");
        String space1 = "space1" + getRandomNumber();
        String app = "Bookmarks";
        info("app:" + app);
        String category = "Tools";
        info("cate:" + category);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info(" Click on Add Application, select application and click add button");
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.goToApplicationTab();
        spaceSettingManagement.addApplication(category, app);
        info("Verify that Application is added to space");
        if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
            $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
            ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("Bookmark")).should(Condition.exist);
        } else {
            ELEMENT_SPACE_MENU_TAB.find(byId("Bookmark")).should(Condition.exist);
        }
        info("Delete the space");
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
    }

    /**
     * <li>Case ID:121891.</li>
     * <li>Test Case Name: Spaces list.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Check All Spaces
     * list Step Description: - Create new space - Login by other user - Access
     * Space/All spaces Input Data: Expected Outcome: - Show created space. User can
     * send request to join space.
     */
    @Test
    public void test03_SpaceList() {
        info("Test 03: Spaces list");
        String space = "space" + getRandomNumber();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@test.com";
        String password = "123456";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        manageLogInOut.signIn(username1, password);
        info("Create a space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToAllSpace();
        spaceManagement.searchSpace(space, "");
        spaceManagement.sendARequestToASpace(space);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);

    }

    /**
     * <li>Case ID:121911.</li>
     * <li>Test Case Name: Edit a space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Edit a space Step
     * Description: - Login Intranet - Click on My space on Admin bar - Click Add
     * new space - On My spaces list, click Edit: Edit information, visibility, edit
     * avatar and click save Input Data: Expected Outcome: - Add new space
     * successfully - All changed of space is saved. User see it when access space
     */
    @Test
    public void test05_EditASpace() {
        info("Test 05:Edit a space");
        String space = "space" + getRandomNumber();
        String newName = "newName" + getRandomNumber();
        String newDEs = "newDEs" + getRandomNumber();
        String filename = "testavatar.png";
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String password = "123456";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        manageLogInOut.signIn(username1, password);

        info("Create a space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
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
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }

    /**
     * <li>Case ID:121916.</li>
     * <li>Test Case Name: Remove application on space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li>
     */
    @Test
    public void test02_RemoveApplicationOnSpace() {
        info("Test 02: Remove application of space's toolbar");
        String space1 = "space1" + getRandomNumber();
        String app = "Forum Statistics";
        info("app:" + app);
        String category = "Adoption";
        info("cate:" + category);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);

        info(" Click on Add Application, select application and click add button");
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.goToApplicationTab();
        spaceSettingManagement.addApplication(category, app);

        info("Verify that Application is added to space");
        $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
        ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byText("ForumsStatistic")).should(Condition.exist);
        spaceSettingManagement.removeApplication(app);
        $(ELEMENT_SPACE_MENU_MORE).shouldNot(Condition.exist);
        ELEMENT_SPACE_MENU_TAB.find(byText(app)).shouldNot(Condition.exist);

        info("Delete the space");
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
    }

    /**
     * <li>Case ID:121917.</li>
     * <li>Test Case Name: Check access visible/open space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Access with
     * visible/open space Step Description: - Login by john - Add new space with
     * access is: visible/open - Logout - Login by mary - Access the url of an open
     * space that she is not member of via url : /portal/g/:spaces:open/open/forum/
     * Click on Join link Input Data: Expected Outcome: - New space is added
     * successfully - A page with Restricted Area title is displayed Message is :
     * You must be a member of the space Open to access this page. [Join - Mary
     * joins the space and is redirected to the initially requested page.
     */
    @Test
    public void test08_CheckAccessVisibleOpenSpace() {
        info("Test 08:Check access visible/open space");
        String space = "space" + getRandomNumber();
        String[] arrayRight = {"open"};
        String mess = "You must be a member of the space " + space + " to view this page.";
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@test.com";
        String password = "123456";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        manageLogInOut.signIn(username1, password);
        info("Create a space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        info("Set permission for the space");
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.goToAccessEditTab();
        spaceSettingManagement.setPermissionForSpace(arrayRight);
        spaceManagement.goToActivityStreamTab();
        String urlSpace = url();
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToWiki();
        open(urlSpace);
        if ($(ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE).is(Condition.not(Condition.visible)))
            refresh();
        $(ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE).should(Condition.visible);
        $(ELEMENT_SPACE_ACCESS_INFO).shouldHave(Condition.text(mess));
        $(ELEMENT_SPACE_ACCESS_JOIN_BTN).click();
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }

    /**
     * <li>Case ID:121918.</li>
     * <li>Test Case Name: Check access hidden space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Check hidden space
     * Step Description: - Login as user1 - Add new hidden space - Logout - Login as
     * user2 - access the url of an hidden space that she is not member of via url :
     * /portal/g/:spaces:hidden/hidden/forum/ - Click on [Find Spaces] link Input
     * Data: Expected Outcome: - Add new space successfully - A page with 'Space not
     * Found' title is displayed Message is : No space is available at this URL.
     * [Find Spaces] - User2 is redirected to Spaces directory page
     */
    @Test
    public void test09_CheckAccessHiddenSpace() {
        info("Test 09:Check access hidden space");
        String space = "space" + getRandomNumber();
        String[] arrayRight = {"hidden"};
        String mess = "You must be a member of the space " + space + " to view this page.";
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@test.com";
        String password = "123456";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        manageLogInOut.signIn(username1, password);

        info("Create a space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);

        info("Set permission for the space");
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.goToAccessEditTab();
        spaceSettingManagement.setPermissionForSpace(arrayRight);
        spaceManagement.goToActivityStreamTab();
        String urlSpace = url();
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToWiki();
        open(urlSpace);
        if ($(ELEMENT_SPACE_ACCESS_SPACE_DENIED).is(Condition.not(Condition.visible)))
            refresh();
        $(ELEMENT_SPACE_ACCESS_SPACE_DENIED).is(Condition.visible);
        $(ELEMENT_SPACE_ACCESS_SPACE_DENIED_INFO).shouldHave(Condition.text(mess));
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }

    /**
     * <li>Case ID:121919.</li>
     * <li>Test Case Name: Check access visible/close space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Check
     * visible/close space Step Description: - Login by John - Add new space with
     * visible/closed - Logout - Login by mary - Access the url of an closed space
     * that she is not member of via url : /portal/g/:spaces:closed/closed/forum/
     * Input Data: Expected Outcome: - Add new space successfully - A page with
     * 'Access Denied' title is displayed Message is : You must be invited by an
     * administrator to the Closed space to access this page.
     */
    @Test
    public void test10_CheckAccessVisibleCloseSpace() {
        info("Test 10:Check access visible/close space");
        String space = "space" + getRandomNumber();
        String[] arrayRight = {"close"};
        String mess = " You must be invited by an administrator to the space " + space + " to access this page.";
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@test.com";
        String password = "123456";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        manageLogInOut.signIn(username1, password);

        info("Create a space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        info("Set permission for the space");
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.goToAccessEditTab();
        spaceSettingManagement.setPermissionForSpace(arrayRight);
        spaceManagement.goToActivityStreamTab();
        refresh();
        String urlSpace = url();
        manageLogInOut.signIn(username2, password);
        open(urlSpace);
        $(ELEMENT_SPACE_ACCESS_SPACE_DENIED).is(Condition.visible);
        $(ELEMENT_SPACE_ACCESS_SPACE_DENIED_INFO).shouldHave(Condition.text(mess));
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }

    /**
     * <li>Case ID:121920.</li>
     * <li>Test Case Name: Check access visible/validation space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Access
     * visible/validation space Step Description: - Login by john - Add new space
     * with visible/validation - Logout - Login by mary - access the url of a space
     * requiring validation that she is not member of via url :
     * /portal/g/:spaces:validation/validation/forum/ - Click on [Request to Join]
     * link Input Data: Expected Outcome: - Add new space successfully - A page with
     * Restricted Area title is displayed Message is : You must be a member of the
     * space Validation to access this page. [Request to Join] - Restricted Area
     * page remains
     */
    @Test
    public void test11_CheckAccessVisibleValidationSpace() {
        info("Test 11:Check access visible/validation space");
        String space = "space" + getRandomNumber();
        String mess = "You must be a member of the space " + space + " to view this page.";
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@test.com";
        String password = "123456";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        manageLogInOut.signIn(username1, password);

        info("Create a space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);

        spaceManagement.goToActivityStreamTab();
        refresh();
        String urlSpace = url();
        manageLogInOut.signIn(username2, password);
        open(urlSpace);
        $(ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE).should(Condition.visible);
        $(ELEMENT_SPACE_ACCESS_INFO).shouldHave(Condition.text(mess));
        $(ELEMENT_SPACE_ACCESS_REQUEST_JOIN_BTN).click();
        refresh();
        $(ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE).shouldNot(Condition.visible);
        $(ELEMENT_SPACE_ACCESS_INFO).shouldNot(Condition.visible);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }

    /**
     * <li>Case ID:121921.</li>
     * <li>Test Case Name: Check when user is member of space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Check when user is
     * member of space Step Description: - Login by user A - Add new space - Add new
     * page for space/wiki - From the list "More", choose the link "Permalink" and
     * copy this link - Login by user B is member of space - Paste the permalink
     * Input Data: Expected Outcome: - Add space and wiki of space successfully -
     * The member of space can view the page created by the manager
     */
    @Test
    public void test12_CheckWhenUserIsMemberOfSpace() {
        info("Test 12:Check when user is member of space");
        String space = "space" + getRandomNumber();
        String wiki = "wiki" + getRandomNumber();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@test.com";
        String password = "123456";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        manageLogInOut.signIn(username1, password);

        info("Create space 1 and wiki page 1");
        homePagePlatform.goToMySpaces();
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
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space, "");
        spaceManagement.acceptAInvitation(space);
        open(perLink);
        refresh();
        $(byXpath(ELEMENT_WIKI_PAGE_LEFTBOX.replace("${title}", wiki))).should(Condition.visible);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }

    /**
     * <li>Case ID:121922.</li>
     * <li>Test Case Name: Check when user is not member of space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Check when user is
     * not member of space Step Description: - Login by user A - Add new space - Add
     * new page for space/wiki - From the list "More", choose the link "Permalink"
     * and copy this link - Login by user B is not member of space - Paste the
     * permalink Input Data: Expected Outcome: - Add space and wiki of space
     * successfully - The "Page Not found" is displayed, the user B cannot view the
     * page
     */
    @Test
    public void test13_CheckWhenUserIsNOTMemberOfSpace() {
        info("Test 13:Check when user is not member of space");
        String space = "space" + getRandomNumber();
        String wiki = "wiki" + getRandomNumber();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@test.com";
        String password = "123456";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        manageLogInOut.signIn(username1, password);

        info("Create space 1 and wiki page 1");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        info("Add new wiki page 1 for space 1");
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        wikiManagement.goToSourceEditor();
        sourceTextEditor.addSimplePage(wiki, wiki);
        wikiManagement.saveAddPage();
        $(byXpath(ELEMENT_TREE_WIKI_NAME.replace("${name}", wiki))).should(Condition.visible);

        wikiHomePage.goToPermalink();
        String perLink = ELEMENT_WIKI_PERMELINK.getValue();
        ;
        manageLogInOut.signIn(username2, password);
        open(perLink);
        refresh();
        $(ELEMENT_WIKI_HOME_PAGENOTFOUND).should(Condition.visible);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }

    @Test
    public void test14_Translatepagetitles() {

        String space = "space" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        navigationToolbar.goToChangeLanguage();
        changeLanguages.changeLanguage("French", "Apply");
        spaceManagement.goToTaskTab();
        assertEquals(title(), space + " - Tâches");
        spaceManagement.goToForumTab();
        assertEquals(title(), space + " - Forum");
        spaceManagement.goToAgendaTab();
        assertEquals(title(), space + " - Calendrier");
        spaceManagement.goToMemberTab();
        assertEquals(title(), space + " - Membres");
        navigationToolbar.goToChangeLanguage();
        changeLanguages.changeLanguage("Anglais", "Appliquer");
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false
        );

    }

    @Tag("SOC-6067")
    @Test
    public void test15_checkNOtPermissionToEditSpaceApp() {
        String space = "space" + getRandomNumber();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String password = "123456";
        String activity1 = "activity1" + getRandomNumber();
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.inviteUser(username1, false, "");
        manageLogInOut.signIn(username1, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space, "");
        spaceManagement.acceptAInvitation(space);
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space, "");
        ELEMENT_SPACES_LIST.find(byText(space)).click();
        activityStream.addActivity(activity1, "");
        $(ELEMENT_ACTIVITY_STREAM_TAB).parent().find(byClassName("tabName")).doubleClick();
        $(byValue(" Activity Stream")).shouldNotBe(Condition.visible);
        spaceManagement.goToTaskTab();
        ELEMENT_SPACE_MENU_TAB.find(ELEMENT_TASK_TAB).parent().find(byClassName("tabName")).doubleClick();
        $(byValue("Tasks")).shouldNotBe(Condition.visible);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);
    }
}
