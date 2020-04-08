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
    public void test03_RestrictButtonForGrantedUserThenForGrantedUserWithoutEditPagePermissionThenForNoGrantedUser() {

        info("Create new users");
        String password0 = "345678";
        String password = "123456";
        String password2 = "456789";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        ArrayList<String> arrayUsers2 = new ArrayList<String>();
        ArrayList<String> arrayUsers0 = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers0 = userAddManagement.addUsers(1, password0);
        arrayUsers = userAddManagement.addUsers(2, password);
        arrayUsers2 = userAddManagement.addUsers(3, password2);

        info("Restrict button for granted user");
        info("Create a wiki page");
        String title0 = "title0" + getRandomNumber();
        String content0 = "content0" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title0, content0);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title0);
        info("Verify that the page is published");
        wikiValidattions.verifyPublishedPage();
        info("Go to Page permission");
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers0.get(0));
        wikiValidattions.verifyEditPermisison(arrayUsers0.get(0), false);
        wikiValidattions.verifyViewPermisison(arrayUsers0.get(0), true);
        wikiPermission.selectPermission(arrayUsers0.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison();
        wikiHomePage.goToPermalink();
        String permalink0 = ELEMENT_WIKI_PERMELINK.getValue();
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(arrayUsers0.get(0), password0);
        open(permalink0);
        $(byText(title0)).click();
        wikiValidattions.verifyPageContent(title0, content0);
        wikiHomePage.goToPermalink();
        $(ELEMENT_MAKE_RESTRICT_BUTTON).waitUntil(Condition.visible, Configuration.timeout);
        wikiHomePage.closePermalinkPopup();
        info("Restrict button for granted user without Edit page permission");
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
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
        info("Restrict button for no granted user");
        info("Create a wiki page");
        String title2 = "title2" + getRandomNumber();
        String content2 = "content2" + getRandomNumber();
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title2, content2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title2);
        info("Verify that the page is published");
        wikiValidattions.verifyPublishedPage();
        wikiHomePage.goToPermalink();
        String permalink2 = ELEMENT_WIKI_PERMELINK.getValue();
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(arrayUsers2.get(0), password2);
        open(permalink2);
        wikiValidattions.verifyPageContent(title2, content2);
        wikiHomePage.goToPermalinkForSimpleUserNotAdmin();
        $(ELEMENT_MAKE_RESTRICT_BUTTON).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(arrayUsers.get(0));
        addUsers.deleteUser(arrayUsers2.get(0));
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title0);
        wikiHomePage.deleteWiki(title);
        wikiHomePage.deleteWiki(title2);

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
    public void test06_AccessByAnUserWhoIsNotMemberOfTheSpaceThenToPagePermissionThenToSpaceSettingByAPageCreator() {
        info("Access to page by an user who is not member of the space");
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
        info("Verify that The button 'Manage permission' is displayed");
        wikiHomePage.goToPermalink();
        $(ELEMENT_MANAGER_PERMISSION_BTN).waitUntil(Condition.visible, Configuration.timeout);
        String permalink = ELEMENT_WIKI_PERMELINK.getValue();
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(arrayUsers.get(0), password);
        refresh();
        open(permalink);
        wikiValidattions.verifyPageNotFound();
        info("Access to space setting by a page creator");
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        info("John invites User to the space");
        homePagePlatform.goToMySpaces();
        homePagePlatform.goToSpecificSpace(space);
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
    public void test07_DisplayAncestorRestrictedPageInLeftTreePanelThenDescendantRestrictedPageInLeftTreePanel() {
        info("Display ancestor Restricted page in left tree panel");
        info("Create 1 new users");
        String password = "123456";
        String password2 = "456789";
        ArrayList<String> arrayUsers = new ArrayList<String>();
        ArrayList<String> arrayUsers1 = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        arrayUsers1 = userAddManagement.addUsers(2, password2);
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
        info("Display descendant Restricted page in left tree panel");
        info("Create 1 new users");
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        info("Create a wiki page");
        String title0 = "title0" + getRandomNumber();
        String content0 = "content0" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title0, content0);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title0);
        info("Verify that the page is published");
        wikiValidattions.verifyPublishedPage();
        info("Add sub page");
        String title2 = "title2" + getRandomNumber();
        String content2 = "content2" + getRandomNumber();
        wikiHomePage.goToAPage(title0);
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(title2, content2);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(title2);
        wikiManagement.unCheckViewAUserOfPage(ELEMENT_PERMISSION_VIEW_ANY);
        info("check Restricted the sub page");
        wikiValidattions.verifyRestrictedPage();
        info("Copy permalink");
        wikiHomePage.goToPermalink();
        String permalink2 = ELEMENT_WIKI_PERMELINK.getValue();
        wikiHomePage.closePermalinkPopup();
        manageLogInOut.signIn(arrayUsers1.get(1), password2);
        open(permalink2);
        wikiValidattions.verifyPageNotFound();
        homePagePlatform.goToWiki();
        wikiValidattions.verifyNotTitleWikiPage(title2);
        manageLogInOut.signIn(DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(arrayUsers.get(0));
        addUsers.deleteUser(arrayUsers1.get(1));
        homePagePlatform.goToWiki();
        wikiHomePage.deleteWiki(title);
        wikiHomePage.deleteWiki(title0);

    }

}
