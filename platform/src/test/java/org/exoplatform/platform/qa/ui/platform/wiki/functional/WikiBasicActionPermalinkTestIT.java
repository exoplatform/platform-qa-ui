package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_SPACE_SETTINGS;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by exo on 6/25/18
 */

@Tag("wiki")
@Tag("functional")

public class WikiBasicActionPermalinkTestIT extends Base {

    HomePagePlatform homePagePlatform;
    WikiHomePage wikiHomePage;
    WikiManagement wikiManagement;
    SourceTextEditor sourceTextEditor;
    ManageLogInOut manageLogInOut;
    WikiValidattions wikiValidattions;
    WikiDraftPage wikiDraftPage;
    RichTextEditor richTextEditor;
    NavigationToolbar navigationToolbar;
    WikiPermission wikiPermission;
    AddUsers addUsers;
    SpaceManagement spaceManagement;
    SpaceHomePage spaceHomePage;
    SpaceSettingManagement spaceSettingManagement;
    UserAddManagement userAddManagement;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        wikiManagement = new WikiManagement(this);
        sourceTextEditor = new SourceTextEditor(this);
        manageLogInOut = new ManageLogInOut(this);
        wikiValidattions = new WikiValidattions(this);
        wikiDraftPage = new WikiDraftPage(this);
        richTextEditor = new RichTextEditor(this);
        navigationToolbar = new NavigationToolbar(this);
        wikiPermission = new WikiPermission(this);
        spaceManagement = new SpaceManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        spaceSettingManagement = new SpaceSettingManagement(this);
        userAddManagement = new UserAddManagement(this);

        addUsers = new AddUsers(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    }


    /**
     * <li> Case ID:139492.</li>
     * <li> Test Case Name: Restrict button for granted user.</li>
     * <li> Pre-Condition: The parent of the page created is with a public status</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1; Add public page
     * Step Description:
     * Go to Intranet/WikiCreate a public page
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is public
     * Step number: 2
     * Step Name: Step 2: Open Page permission
     * Step Description:
     * From the list [More], choose the link [Page permission]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The "Page permission" is displayed
     * Step number: 3
     * Step Name: Step 3: Set permission for page
     * Step Description:
     * Cick on the icon [Select a User] Check to the user to grantClick on the button "Add"
     * Input Data:
     * <p>
     * Expected Outcome:
     * The user is added to the list with "View Page" checked and the "Page edit" box isn't selected
     * Step number: 4
     * Step Name: Step 4: Edit permission
     * Step Description:
     * Select the icon [Edit page]Click on the button [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * permission successfully
     * Step number: 5
     * Step Name: Step 5: Copy "permalink"
     * Step Description:
     * - Select [More]
     * -> Choose [Permalink]
     * - From the page "Permalink", Copy the url of the page to share
     * Input Data:
     * <p>
     * Expected Outcome:
     * Copy link successfully
     * Step number: 6
     * Step Name: Step 6: View permalink
     * Step Description:
     * - Paste the link in a BrowserClick Enter from the Keyboard and connect with the granted user
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is dispalyed
     * Step number: 7
     * Step Name: Step 7: Check "Permalink" link
     * Step Description:
     * From the list [More], choose the link [Permalink]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The pop up "Permalink" is displayed" and the button "Restricted" is played
     */
    @Test
    public void test01_RestrictButtonForGrantedUser() {
        info("Test 1: Restrict button for granted user");
        info("Create 1 new users");
        String password = "123456";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);

        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Verify that the page is published");
        wikiValidattions.verifyPublishedPage();
        info("Go to Page permission");
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiValidattions.verifyEditPermisison(arrayUsers.get(0), false);
        wikiValidattions.verifyViewPermisison(arrayUsers.get(0), true);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison();
        wikiHomePage.goToPermalink();
        String permalink = ELEMENT_WIKI_PERMELINK.getValue();
        ;
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(arrayUsers.get(0), password);
        open(permalink);
        $(byText(title)).click();
        wikiValidattions.verifyPageContent(title, content);
        wikiHomePage.goToPermalink();
        $(ELEMENT_MAKE_RESTRICT_BUTTON).waitUntil(Condition.visible, Configuration.timeout);
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(arrayUsers.get(0));
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139493.</li>
     * <li> Test Case Name: Restrict button for granted user without "Edit page" permission.</li>
     * <li> Pre-Condition: The parent of the page created is with a public status</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Add public page
     * Step Description:
     * Go to Intranet/WikiCreate a public page
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is public
     * <p>
     * Step number: 2
     * Step Name: Step 2: Open Page permission
     * Step Description:
     * From the list [More], choose the link [Page permission]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The "Page permission" is displayed
     * Step number: 3
     * Step Name: Step 3: Set permission for page
     * Step Description:
     * Cick on the icon "Select a User" Check to the user to grantClick on the button "Add"
     * Input Data:
     * <p>
     * Expected Outcome:
     * The user is added to the list with "View Page" and the "Page edit" box isn't selected
     * Step number: 4
     * Step Name: Step 4: Copy "permalink"
     * Step Description:
     * - Select [More]
     * -> Choose [Permalink]
     * - From the page "Permalink", Copy the url of the page to share
     * Input Data:
     * <p>
     * Expected Outcome:
     * Copy link successfully
     * <p>
     * Step number: 5
     * Step Name: Step 5: View permalink
     * Step Description:
     * - Paste the link in a BrowserClick Enter from the Keyboard and connect with the granted user
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is dispalyed*
     * Step number: 6
     * Step Name: Step 6: Check "Permalink" link
     * Step Description:
     * From the list [More], choose the link [Permalink]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The pop up "Permalink" is displayed and the button "Restricted" isn't displayed
     */

    @Test
    public void test02_RestrictButtonForGrantedUserWithoutEditPagePermission() {
        info("Test 2: Restrict button for granted user without Edit page permission");
        info("Create 1 new users");
        String password = "123456";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);

        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Verify that the page is published");
        wikiValidattions.verifyPublishedPage();
        info("Go to Page permission");
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiValidattions.verifyEditPermisison(arrayUsers.get(0), false);
        wikiValidattions.verifyViewPermisison(arrayUsers.get(0), true);
        wikiPermission.savePermisison();
        wikiHomePage.goToPermalink();
        String permalink = ELEMENT_WIKI_PERMELINK.getValue();
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(arrayUsers.get(0), password);
        refresh();
        open(permalink);
        wikiValidattions.verifyPageContent(title, content);
        wikiHomePage.goToPermalinkForSimpleUserNotAdmin();
        $(ELEMENT_MAKE_RESTRICT_BUTTON).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(arrayUsers.get(0));
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139494.</li>
     * <li> Test Case Name: Restrict button for no granted user.</li>
     * <li> Pre-Condition: The parent of the page created is with a public status
     * - The user no granted isn't also the creator the page and the Space manager</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Add public page
     * Step Description:
     * Go to Intranet/WikiCreate a public page
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is public
     * <p>
     * Step number: 2
     * Step Name: Step 2: Copy "permalink"
     * Step Description:
     * - Select More
     * -> Choose Permalink
     * - From the page "Permalink", Copy the url of the page to share
     * Input Data:
     * <p>
     * Expected Outcome:
     * Copy link successfully
     * <p>
     * Step number: 3
     * Step Name: Step 3: View permalink
     * Step Description:
     * - Login with other user
     * - Paste the link in a BrowserClick Enter from the Keyboard and connect with a user
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is dispalyed
     * Step number: 4
     * Step Name: Step 4: Check "Permalink" link
     * Step Description:
     * From the list "More", choose the link "Permalink"
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The pop up " Permalink" is displayed
     * - the button "Restrict" isn't displayed
     */
    @Test
    public void test03_RestrictButtonForNoGrantedUser() {
        info("Test 3: Restrict button for no granted user");
        info("Create 1 new users");
        String password = "123456";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);

        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Verify that the page is published");
        wikiValidattions.verifyPublishedPage();
        wikiHomePage.goToPermalink();
        String permalink = ELEMENT_WIKI_PERMELINK.getValue();
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(arrayUsers.get(0), password);
        open(permalink);
        wikiValidattions.verifyPageContent(title, content);
        wikiHomePage.goToPermalinkForSimpleUserNotAdmin();
        $(ELEMENT_MAKE_RESTRICT_BUTTON).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(arrayUsers.get(0));
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139513.</li>
     * <li> Test Case Name: Access to page by an user who is not member of the space.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Add new page for space when set permission for page
     * Step Description:
     * - Login by admin
     * - Add new space
     * - Go to space/wiki page
     * - Add new page
     * - Set permission for page: remove any permission
     * - Select [More]
     * -> choose [Permalink]
     * - Copy the link
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Add new page have permission successfully
     * - Copy link successfully
     * <p>
     * Step number: 2
     * Step Name: Step 2: Check permalink when user is not member of space
     * Step Description:
     * - From the list [More], choose the link [Permalink]
     * - Copy the link
     * - Connect with User B, not a member in the space
     * - Paste the permalink
     * - Click [Enter] from the keyboard
     * Input Data:
     * <p>
     * Expected Outcome
     * The "Page Not found" is displayed, the user B cannot view the page
     */
    @Test
    public void test04_AccessToPageByAnUserWhoIsNotMemberOfTheSpace() {
        info("Test 4: Access to page by an user who is not member of the space");
        info("Create 1 new users");
        String password = "123456";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);

        info("Create a space");
        String space = "space" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);

        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);

        info("Copy permalink");
        wikiHomePage.goToPermalink();
        String permalink = ELEMENT_WIKI_PERMELINK.getValue();
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(arrayUsers.get(0), password);
        refresh();
        open(permalink);
        ;
        wikiValidattions.verifyPageNotFound();
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(arrayUsers.get(0));
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);


    }

    /**
     * <li> Case ID:139514.</li>
     * <li> Test Case Name: Access to Page permission by the page creator.</li>
     * <li> Pre-Condition: the user is the space manager and the creator of the page</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Add wiki page for space
     * Step Description:
     * - Login with user A is space manager
     * - Go to Space/wiki
     * - Create a page
     * Input Data:
     * <p>
     * Expected Outcome:
     * a page is created by the manager of the space*
     * Step number: 2
     * Step Name: Step 2: Check show Page Permission
     * Step Description:
     * From the list [More], choose the link [Permalink]
     * Input Data:
     * <p>
     * Expected Outcome:
     * The pop up "Permalink" is displayedThe button "Manage permission" is displayed
     */
    @Test
    public void test05_AccessToPagePermissionByThePageCreator() {
        info("Test 5: Access to Page permission by the page creator");
        info("Create a space");
        String space = "space" + getRandomNumber();
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space, space);


        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Verify that The button 'Manage permission' is displayed");
        wikiHomePage.goToPermalink();
        $(ELEMENT_MANAGER_PERMISSION_BTN).waitUntil(Condition.visible, Configuration.timeout);
        wikiHomePage.closePermalinkPopup();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);


    }

    /**
     * <li> Case ID:139516.</li>
     * <li> Test Case Name: Access to space setting by a page creator.</li>
     * <li> Pre-Condition: User B is member in the spaceUser B is creator of the pageUser B isn't an admin in the space</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Add new space
     * Step Description:
     * - Login with user A ( ex: john)
     * - Create new space with name is "test" ( user A is space manager)
     * - Go to Space/wiki
     * - Create a page
     * Input Data:
     * <p>
     * Expected Outcome:
     * A wiki's page is created by the manager of the space
     * Step number: 2
     * Step Name: Step 2: Add member into space
     * Step Description:
     * - Go to Settings
     * - Choose member tab
     * - Add user B ( ex: mary) is member of space
     * Input Data:
     * <p>
     * Expected Outcome:
     * Add user successfully
     * Step number: 3
     * Step Name: Step 3: Login by user B to accept is member
     * Step Description:
     * - Login by mary
     * - Click join a space
     * - Select space "test" and click accept
     * Input Data:
     * <p>
     * Expected Outcome:
     * Mary is member of space
     * Step number: 4
     * Step Name: Step 4: Access to space setting by member of space
     * Step Description:
     * - Go to Space/Wiki
     * - User B, member of the space, create a page in the space
     * Input Data:
     * <p>
     * Expected Outcome:
     * - A page wiki is created in the space
     * - User B has not access to the space setting for the wiki application
     */
    @Test
    @Tag("wabis")
    public void test06_AccessToSpaceSettingByAPageCreator() {
        info("Test 6: Access to space setting by a page creator");
        info("Create 1 new users");
        String password = "123456";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Create a space");
        String space = "space" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);


        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();

        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("John invites User to the space");
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.goToMemberTab();
        spaceSettingManagement.inviteUser(arrayUsers.get(0), true, "");
        info("User accepted to become a member of the space");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);
        info("Create a wiki page");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        homePagePlatform.goToSpecificSpace(space);
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title1, content1);
        wikiManagement.saveAddPage();
        ;
        wikiValidattions.verifyTitleWikiPage(title1);

        info("User cannot see Space setting tab");
        $(ELEMENT_SPACE_SPACE_SETTINGS).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(arrayUsers.get(0));
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space, "");
        spaceManagement.deleteSpace(space, false);

    }

    /**
     * <li> Case ID:139517.</li>
     * <li> Test Case Name: Display ancestor Restricted page in left tree panel.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Create a page by user A
     * Step Description:
     * - Connect to Intranet/Wiki with UserA
     * - Create a page: Page 1
     * Input Data:
     * <p>
     * Expected Outcome:
     * The Page 1 is created
     * Step number: 2
     * Step Name: Step 2: Set permission for Page 1
     * Step Description:
     * - From the list [More], choose [Page Permission]
     * - Remove 'any' permission
     * - Click [Save]
     * Input Data:
     * <p>
     * Expected Outcome:
     * Set permission for page successful
     * Step number: 3
     * Step Name: Step 2: Add sub
     * -page
     * Step Description:
     * - Add a sub page: Page 1.1
     * - Add View permission to User B
     * Input Data:
     * <p>
     * Expected Outcome:
     * The Page 1.1 is created
     * <p>
     * Step number: 4
     * Step Name: step 3: Copy permalink
     * Step Description:
     * - Open Page 1.1
     * - From the list [More], choose the link [Permalink] copy the link to share
     * Input Data:
     * <p>
     * Expected Outcome:
     * Copy permalink successfully
     * Step number: 5
     * Step Name: Step 3: Check permalink for user B for sub
     * -page
     * Step Description:
     * - Connect with User B
     * -Paste the link in a Browser
     * - Click Enter from the Keyboard
     * Input Data:
     * <p>
     * Expected Outcome:
     * - In the left tree panel the page 1 is labelled Restricted in italic.
     * - It has a tooltip : this page is restricted, you don't have permissions to view it.
     * - The restricted ancestors are not clickable.
     */
    @Test
    @Tag("wabis")
    public void test07_DisplayAncestorRestrictedPageInLeftTreePanel() {
        info("Test 7: Display ancestor Restricted page in left tree panel");
        info("Create 1 new users");
        String password = "123456";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);

        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Remove any permission");
        wikiHomePage.goToPermissions();
        wikiPermission.deletePermission("any");
        wikiPermission.savePermisison();
        info("Add sub page");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title1, content1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title1);

        info("Add View permission to User B");
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiValidattions.verifyEditPermisison(arrayUsers.get(0), false);
        wikiValidattions.verifyViewPermisison(arrayUsers.get(0), true);
        wikiPermission.savePermisison();

        info("Copy permalink");
        wikiHomePage.goToPermalink();
        String permalink = ELEMENT_WIKI_PERMELINK.getValue();
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(arrayUsers.get(0), password);
        open(permalink);
        wikiValidattions.verifyRestrictedPageHasChildPage();
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(arrayUsers.get(0));
        homePagePlatform.goToWiki();
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }


    /**
     * <li> Case ID:139518.</li>
     * <li> Test Case Name: Display descendant Restricted page in left tree panel.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step 1: Add new page is public
     * Step Description:
     * - Connect to Intranet/Wiki with User A
     * - Create a page is public: Page 1
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page 1 is public*
     * Step number: 2
     * Step Name: Step 2: Add sub
     * -page is restricted
     * Step Description:
     * - Add a sub page: Page 1.1
     * - From the list [More], choose [Permalink]
     * - Click on the button "Restricted"
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page 1.1 is restricted
     * Step number: 3
     * Step Name: Step 3: Copy permalink
     * Step Description:
     * - Copy the link to share
     * Input Data:
     * <p>
     * Expected Outcome:
     * Copy link successfully
     * Step number: 4
     * Step Name: Step 4: View permalink with user does not have permission
     * Step Description:
     * - Connect with the User B
     * - Open the page 1.1
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The Page not found message is displayed
     * Step number: 5
     * Step Name: Step 5: Check on wiki home
     * Step Description:
     * Back to Intranet/Wiki
     * Input Data:
     * <p>
     * Expected Outcome:
     * - In the left tree panel, the page 1.1 isn't displayed
     */
    @Test
    @Tag("wikio")
    public void test08_DisplayDescendantRestrictedPageInLeftTreePanel() {
        info("Test 8: Display descendant Restricted page in left tree panel");
        info("Create 1 new users");
        String password = "123456";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);

        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Verify that the page is published");
        wikiValidattions.verifyPublishedPage();
        info("Add sub page");
        String title1 = "title1" + getRandomNumber();
        String content1 = "content1" + getRandomNumber();
        wikiHomePage.goToAPage(title);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title1, content1);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title1);
        wikiManagement.unCheckViewAUserOfPage(ELEMENT_PERMISSION_VIEW_ANY);
        info("check Restricted the sub page");
        wikiValidattions.verifyRestrictedPage();
        info("Copy permalink");
        wikiHomePage.goToPermalink();
        String permalink = ELEMENT_WIKI_PERMELINK.getValue();
        ;
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(arrayUsers.get(0), password);
        open(permalink);
        wikiValidattions.verifyPageNotFound();
        homePagePlatform.goToWiki();
        wikiValidattions.verifyNotTitleWikiPage(title1);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(arrayUsers.get(0));
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }

    /**
     * <li> Case ID:139554.</li>
     * <li> Test Case Name: Create a wiki page in a Space.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: Step1: Add new page for wiki of space
     * Step Description:
     * - Select a space
     * - Go to Space/wiki
     * - Create a page
     * Input Data:
     * <p>
     * Expected Outcome:
     * The page is addedIn the buttom of the page is displayed to "Restricted"
     */
    @Test
    public void test09_CreateAWikiPageInASpace() {
        info("Test 9: Create a wiki page in a Space");
        info("Create a space");
        String space = "space" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);


        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        spaceHomePage.goToWikiTab();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);

        info("Verify that the page is restricted");
        wikiValidattions.verifyRestrictedPage();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);


    }

    /**
     * <li> Case ID:139556.</li>
     * <li> Test Case Name: Permission Indicator is refreshed when changing Public/Restrict.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * Step Name: - Open [Permalink] Dialog
     * Step Description:
     * - Goto Wiki
     * - Create a new wiki page
     * - Click [More]
     * ->[Permalink]
     * Input Data:
     * <p>
     * Expected Outcome:
     * [Permalink] dialog is displayed
     * <p>
     * Step number: 2
     * Step Name: - Make the page public/restrict
     * Step Description:
     * - Click on button [Make Public] or [Retstrict] on [Permalink] dialog
     * Input Data:
     * <p>
     * Expected Outcome:
     * the Permission Indicator is refreshed accordingly.
     */
    @Test
    public void test10_PermissionIndicatorIsRefreshedWhenChangingPublicRestrict() {
        info("Test 10 Permission Indicator is refreshed when changing Public/Restrict");
        info("Create a wiki page");
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title, content);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title);
        info("Verify that the page is published");
        wikiValidattions.verifyPublishedPage();
        info("Make restricted the page");
        wikiHomePage.restrictedPage();
        info("Verify that the page is restricted");
        wikiValidattions.verifyRestrictedPage();
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);

    }
}
