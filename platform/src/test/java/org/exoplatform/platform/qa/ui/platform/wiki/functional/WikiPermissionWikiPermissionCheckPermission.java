package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("wiki")
@Tag("functional")
public class WikiPermissionWikiPermissionCheckPermission extends Base {

    HomePagePlatform homePagePlatform;

    WikiHomePage wikiHomePage;

    ManageLogInOut manageLogInOut;

    WikiManagement wikiManagement;

    RichTextEditor richTextEditor;

    NavigationToolbar navigationToolbar;

    WikiValidattions wikiValidattions;

    WikiSettingPage wikiSettingPage;

    SpaceManagement spaceManagement;

    SpaceHomePage spaceHomePage;

    WikiPermission wikiPermission;

    UserAndGroupManagement userAndGroupManagement;

    UserAddManagement userAddManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        wikiHomePage = new WikiHomePage(this);
        manageLogInOut = new ManageLogInOut(this);
        wikiManagement = new WikiManagement(this);
        navigationToolbar = new NavigationToolbar(this);
        spaceManagement = new SpaceManagement(this);
        wikiSettingPage = new WikiSettingPage(this);
        wikiValidattions = new WikiValidattions(this);
        spaceHomePage = new SpaceHomePage(this);
        wikiPermission = new WikiPermission(this);
        richTextEditor = new RichTextEditor(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        userAddManagement = new UserAddManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        manageLogInOut.signInCas(username, password);
    }

    /**
     * <li> Case ID:139595.</li>
     * <li> Test Case Name: Verify View permission of an user.</li>
     * <li> Pre-Condition: - The User A has already the permission : View Pages in the Wiki Settings
     * - Some wiki pages are already created in this space</li>
     * <li> Post-Condition: </li> 	Step Number: 1
     * *Step Name: Step 1: Open form to add permission for space
     * *Step Description:
     * - Go to Browse
     * -
     * -> Wiki Settings
     * - Choose Permission tab
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Space Wiki Permissions form appears
     * *Step number: 2
     * *Step Name: Step 2: Check permission for a User
     * *Step Description:
     * - Check the User A permission
     * - In the permission table,
     * check the View Pages checkbox of the User A, uncheck all other checkbox
     * - Click Save
     * -
     * -> OK
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - The permission View Pages is checked
     * - Other checkbox are unchecked
     * *Step number: 3
     * *Step Name: Step 3: Check if theuser can view the page
     * *Step Description:
     * - Log in as User A
     * - Go to Wiki of the space.
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - The user can view the wiki page but cannot see Edit Page, Add Page; Move Page
     * and Delete Page and Page Permissions from More menu
     * - The user can not see Wiki Settings from Browse menu
     */

    @Test
    public void test01_VerifyViewPermissionOfAnUser() {
        info("Test 1: Verify View permission of an user");
        info("Create 1 new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Create new page 1");
        String page = "page" + getRandomNumber();
        String pageContent = "pageContent" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page, pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);
        //arrayPage.add(page);
        info(" Open form to add permission for space");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select user to add permission");
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);

        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        $(ELEMENT_EDIT_PAGE_LINK).waitUntil(Condition.not(Condition.exist), Configuration.timeout);
        $(ELEMENT_ADD_PAGE_LINK).waitUntil(Condition.not(Condition.exist), Configuration.timeout);
        ELEMENT_MORE_LINK_SIMPLE.click();
        ELEMENT_MOVE_PAGE.waitUntil(Condition.not(Condition.exist), Configuration.timeout);
        $(ELEMENT_DELETE_LINK).waitUntil(Condition.not(Condition.exist), Configuration.timeout);
        $(ELEMENT_PERMISSION_LINK).waitUntil(Condition.not(Condition.exist), Configuration.timeout);
        $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).click();
        $(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS).waitUntil(Condition.not(Condition.exist), Configuration.timeout);
    }

    /**
     * <li> Case ID:139596.</li>
     * <li> Test Case Name: Verify Edit permission for a user.</li>
     * <li> Pre-Condition: - The User A has already the permission: Edit Pages in the Wiki Settings
     * - Some wiki pages are already created in this space</li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Open form to add permission for space
     * *Step Description:
     * - Go to Browse
     * -
     * -> Wiki Settings
     * - Choose Permission tab
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Space Wiki Permissions form appears
     * *Step number: 2
     * *Step Name: Step 2: Check Edit Pages permission for User A
     * *Step Description:
     * - Check the User A permission
     * - In the permission table, check the Edit Pages checkbox of the User A
     * - Uncheck permission "Admin Pages" and "Admin Wiki"
     * - Click Save
     * -
     * -> OK
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - Edit permission and View permission are selected
     * - Other permission are unchecked
     * *Step number: 3
     * *Step Name: Step 3: Check if the User A has Edit Pages permission
     * *Step Description:
     * - Login as User A
     * - Go to space
     * -
     * -> Wiki
     * - Select a wiki page
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - The user A can view the wiki page and can see Edit Page, Add Page;
     * Move Page and Delete Page from More menu
     * - The user can not see Wiki Settings from Browse menu
     * - The user A can not see Page Permissions from More menu
     */
    @Test
    public void test02_VerifyEditPermissionForAUser() {
        info("Test 2: Verify Edit permission for a user");

        info("Create 1 new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Create new page 1");
        String page = "page" + getRandomNumber();
        String pageContent = "pagecontent" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page, pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);
        //arrayPage.add(page);
        info(" Open form to add permission for space");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select user to add permission");
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        refresh();
        $(ELEMENT_EDIT_PAGE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_ADD_PAGE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        ELEMENT_MORE_LINK.click();
        ELEMENT_MOVE_PAGE.waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_DELETE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_PERMISSION_LINK).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).click();
        $(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    }

    /**
     * <li> Case ID:139597.</li>
     * <li> Test Case Name: Verify Admin Pages permission of a user.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: - The User A has already the permission: Admin Pages in the Wiki Settings
     * - Some wiki pages are already created in this space</li>
     * Step Number: 1
     * Step Name: Step 1: Open form to add permission for space
     * Step Description:
     * - Go to Browse
     * -
     * -> Wiki Settings
     * - Choose Permission tab
     * Input Data:
     * <p>
     * Expected Outcome:
     * Space Wiki Permissions form appears
     * <p>
     * Step number: 2
     * Step Name: Step 2: Check Admin Pages permission
     * Step Description:
     * - Check the User A permission
     * - In the permission table, check the Admin Pages checkbox of the User A
     * - Uncheck permission "Admin Wiki"
     * - Click Save
     * -
     * -> OK
     * Input Data:
     * <p>
     * Expected Outcome:
     * - All permissions are selected except permission "Admin Wiki"
     * /*Step number: 3
     * Step Name: Step 3: Check if theuser A has Admin Pages permission
     * Step Description:
     * - Login as user A
     * - Go to the space
     * -
     * -> Wiki
     * - Select a wiki page
     * Input Data:
     * <p>
     * Expected Outcome:
     * - The user can not see Wiki Settings from Browse menu
     * - The user A can see Page Permissions from More menu
     */
    @Test
    public void test03_VerifyAdminPagesPermissionOfAUser() {
        info("Test 3: Verify Admin Pages permission of a user");
        info("Create 1 new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Create new page 1");
        String page = "page" + getRandomNumber();
        String pageContent = "pageContent" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page, pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);
        //arrayPage.add(page);
        info(" Open form to add permission for space");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select user to add permission");
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Admin_Pages);
        wikiPermission.savePermisison();
        wikiHomePage.confirmWaringMessage(true);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        $(ELEMENT_EDIT_PAGE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_ADD_PAGE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        ELEMENT_MORE_LINK.click();
        ELEMENT_MOVE_PAGE.waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_DELETE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_PERMISSION_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).click();
        $(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    }

    /**
     * <li> Case ID:139598.</li>
     * <li> Test Case Name: Verify Admin Wiki permission for a user.</li>
     * <li> Pre-Condition: - The User A has already the permission: Admin Wiki in the Wiki Settings
     * - Some wiki pages are already created in this space</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * *Step Name: Step 1: Open form to add permission for space
     * *Step Description:
     * - Go to Browse
     * -
     * -> Wiki Settings
     * - Choose Permission tab
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Space Wiki Permissions form appears
     * <p>
     * Step number: 2
     * *Step Name: Step 2: Check the Admin Wiki permission for User A
     * *Step Description:
     * - Check the User A permission
     * - In the permission table, check the Admin Wiki checkbox of the User A
     * - Click Save
     * -
     * -> OK
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - All permission are selected
     * <p>
     * *Step number: 3
     * *Step Name: Step 3: Check if theuser A has Admin Wiki permission
     * *Step Description:
     * - Login as user A
     * - Go to the space
     * -
     * -> Wiki
     * - Select a wiki page
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - The user can see Wiki Settings from Browse menu
     * - The user A can see Page Permissions from More menu
     */
    @Test
    public void test04_VerifyAdminWikiPermissionForAUser() {
        info("Test 4: Verify Admin Wiki permission for a user");
        info("Create 1 new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Create new page 1");
        String page = "page" + getRandomNumber();
        String pageContent = "pageContent" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page, pageContent);
        wikiManagement.saveAddPage();
        wikiValidattions.verifyTitleWikiPage(page);
        // arrayPage.add(page);
        info(" Open form to add permission for space");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select user to add permission");
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Admin_Pages);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Admin_Wiki);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        //arrayGroupsPath.add(arrayUsers.get(0));
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        refresh();
        $(ELEMENT_EDIT_PAGE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_ADD_PAGE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        ELEMENT_MORE_LINK.click();
        ELEMENT_MOVE_PAGE.waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_DELETE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_PERMISSION_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).click();
        $(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * <li> Case ID:139599.</li>
     * <li> Test Case Name: Verify View permission for a group.</li>
     * <li> Pre-Condition: - The Group A has already the permission : View Pages in the Wiki Settings
     * - Some wiki pages are already created in this space</li>
     * <li> Post-Condition: </li>
     * Step Number: 1
     * *Step Name: Step 1: Open form to add permission for space
     * *Step Description:
     * - Go to Browse
     * -
     * -> Wiki Settings
     * - Choose Permission tab
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Space Wiki Permissions form appears
     * <p>
     * *        Step number: 2
     * *Step Name: Step 2: Check permission for a Group
     * *Step Description:
     * - Check the Group A permission
     * - In the permission table, check the View Pages checkbox of the Group A and uncheck other checkbox
     * - Click Save
     * -
     * -> OK
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - The permission View Pages is selected
     * - Other permissions are unchecked
     * *Step number: 3
     * *Step Name: Step 3: Check if the group can view the page
     * *Step Description:
     * - Log in with a user member of the Group A
     * - Go to Wiki of the space.
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - The user can view the wiki page but cannot see Edit Page, Add Page; Move Page and Delete Page and Page Permissions from More menu
     * - The user can not see Wiki Settings from Browse menu
     */
    @Test
    public void test05_VerifyViewPermissionForAGroup() {
        info("Test 5: Verify View permission for a group");
        info("Create 1 new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Create a new group");
        String groupName = "groupName" + getRandomNumber();
        String groupLabel = "groupLabel" + getRandomNumber();
        String groupDesc = "groupDesc " + getRandomNumber();
        navigationToolbar.goToUsersAndGroupsManagement();
        userAndGroupManagement.goToGroupTab();
        userAndGroupManagement.addGroup(groupName, groupLabel, groupDesc, true);
        arrayUsers.add(groupLabel);
        info("Add users to the group");
        String membership = "manager";
        navigationToolbar.goToUsersAndGroupsManagement();
        userAndGroupManagement.goToGroupTab();
        userAndGroupManagement.selectGroup(groupLabel);
        userAndGroupManagement.addUsersToGroup(arrayUsers.get(0), membership, false, true);
        info("Create new page 1");
        String page = "page" + getRandomNumber();
        String pageContent = "pageContent" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page, pageContent);
        wikiManagement.saveAddPage();
        refresh();
        wikiValidattions.verifyTitleWikiPage(page);
        //arrayPage.add(page);
        info(" Open form to add permission for space");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select group to add permission");
        String groupUsers = "*:/" + groupName;
        wikiPermission.addPermisisonByType(groupUsers);
        wikiPermission.savePermisison();
        wikiHomePage.confirmWaringMessage(true);
        // arrayUsers.add(groupUsers);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(groupUsers);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        refresh();
        $(ELEMENT_EDIT_PAGE_LINK).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(ELEMENT_ADD_PAGE_LINK).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        refresh();
        ELEMENT_MORE_LINK_SIMPLE.click();
        ELEMENT_MOVE_PAGE.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(ELEMENT_DELETE_LINK).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(ELEMENT_PERMISSION_LINK).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).click();
        $(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    }
    /**
     * <li> Case ID:139600.</li>
     * <li> Test Case Name: Verify Admin Pages permission of a group.</li>
     * <li> Pre-Condition: - The Group A has already the permission : Admin Pages in the Wiki Settings
     * - Some wiki pages are already created in this space</li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Open form to add permission for space
     * *Step Description:
     * - Go to Browse
     * -
     * -> Wiki Settings
     * - Choose Permission tab
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Space Wiki Permissions form appears
     * <p>
     * *Step number: 2
     * *Step Name: Step 2: Check the Admin Pages permission for Group A
     * *Step Description:
     * - Check the Group A permission
     * - In the permission table, check the Admin Pages checkbox of the Group A
     * - Uncheck "Admin Wiki" permission
     * - Click Save
     * -
     * -> OK
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - All permission are selected except permission "Admin Wiki"
     * *Step number: 3
     * *Step Name: Step 3: Check ifGroup A has Admin Pages permission
     * *Step Description:
     * - Log in with a user member of the Group A
     * - Go to Wiki of the space.
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - The user can not see Wiki Settings from Browse menu
     * - The user A can see Page Permissions from More menu
     */
    @Test
    public void test06_VerifyAdminPagesPermissionOfAGroup() {
        info("Test 6: Verify Admin Pages permission of a group");
        info("Create 1 new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Create a new group");
        String groupName = "groupName" + getRandomNumber();
        String groupLabel = "groupLabel" + getRandomNumber();
        String groupDesc = "groupDesc " + getRandomNumber();
        navigationToolbar.goToUsersAndGroupsManagement();
        userAndGroupManagement.goToGroupTab();
        userAndGroupManagement.addGroup(groupName, groupLabel, groupDesc, true);
        arrayUsers.add(groupLabel);
        info("Add users to the group");
        String membership = "manager";
        navigationToolbar.goToUsersAndGroupsManagement();
        userAndGroupManagement.goToGroupTab();
        userAndGroupManagement.selectGroup(groupLabel);
        userAndGroupManagement.addUsersToGroup(arrayUsers.get(0), membership, false, true);
        info("Create new page 1");
        String page = "page" + getRandomNumber();
        String pageContent = "pageContent" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page, pageContent);
        wikiManagement.saveAddPage();
        refresh();
        wikiValidattions.verifyTitleWikiPage(page);
        info(" Open form to add permission for space");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select group to add permission");
        String groupUsers = "*:/" + groupName;
        wikiPermission.addPermisisonByType(groupUsers);
        wikiPermission.selectPermission(groupUsers, WikiPermission.permissionType.Admin_Pages);
        wikiPermission.savePermisison();
        wikiHomePage.confirmWaringMessage(true);
        arrayUsers.add(groupUsers);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(groupUsers);
        wikiPermission.selectPermission(groupUsers, WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        ELEMENT_MORE_LINK.click();
        $(ELEMENT_PERMISSION_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).click();
        $(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    }
    /**
     * <li> Case ID:139601.</li>
     * <li> Test Case Name: Verify Admin Wiki permission for a group.</li>
     * <li> Pre-Condition: - The Group A has already the permission: Admin Wiki in the Wiki Settings
     * - Some wiki pages are already created in this space</li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Open form to add permission for space
     * *Step Description:
     * - Go to Browse
     * -
     * -> Wiki Settings
     * - Choose Permission tab
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Space Wiki Permissions form appears
     * <p>
     * *Step number: 2
     * *Step Name: Step 2: Select the Admin Wiki permission
     * *Step Description:
     * - Check the Group A permission
     * - In the permission table, check the Admin Wiki checkbox of the Group A
     * - Click Save
     * -
     * -> OK
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - All permission are checked
     * *Step number: 3
     * *Step Name: Step 3: Check ifGroup A has Admin Wiki permission
     * *Step Description:
     * - Log in with a user member of the Group A
     * - Go to Wiki of the space.
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - The user can see Wiki Settings from Browse menu
     * - The user A can see Page Permissions from More menu
     */
    @Test
    public void test07_VerifyAdminWikiPermissionForAGroup() {
        info("Test 7: Verify Admin Wiki permission for a group");
        info("Create 1 new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Create a new group");
        String groupName = "groupName" + getRandomNumber();
        String groupLabel = "groupLabel" + getRandomNumber();
        String groupDesc = "groupDesc " + getRandomNumber();
        navigationToolbar.goToUsersAndGroupsManagement();
        userAndGroupManagement.goToGroupTab();
        userAndGroupManagement.addGroup(groupName, groupLabel, groupDesc, true);
        arrayUsers.add(groupLabel);
        info("Add users to the group");
        String membership = "manager";
        navigationToolbar.goToUsersAndGroupsManagement();
        userAndGroupManagement.goToGroupTab();
        userAndGroupManagement.selectGroup(groupLabel);
        userAndGroupManagement.addUsersToGroup(arrayUsers.get(0), membership, false, true);
        info("Create new page 1");
        String page = "page" + getRandomNumber();
        String pageContent = "pageContent" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page, pageContent);
        wikiManagement.saveAddPage();
        refresh();
        wikiValidattions.verifyTitleWikiPage(page);
        info(" Open form to add permission for space");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select group to add permission");
        String groupUsers = "*:/" + groupName;
        wikiPermission.addPermisisonByType(groupUsers);
        wikiPermission.selectPermission(groupUsers, WikiPermission.permissionType.Admin_Wiki);
        wikiPermission.savePermisison();
        wikiHomePage.confirmWaringMessage(true);
        arrayUsers.add(groupUsers);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        ELEMENT_MORE_LINK_SIMPLE.click();
        $(ELEMENT_PERMISSION_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).click();
        $(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS).waitUntil(Condition.visible, Configuration.timeout);
    }
    /**
     * <li> Case ID:139602.</li>
     * <li> Test Case Name: Verify Edit permission for a group.</li>
     * <li> Pre-Condition: - The Group A has already the permission: Edit Pages in the Wiki Settings
     * - Some wiki pages are already created in this space</li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Open form to add permission for space
     * *Step Description:
     * - Go to Browse
     * -
     * -> Wiki Settings
     * - Choose Permission tab
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Space Wiki Permissions form appears
     * <p>
     * *Step number: 2
     * *Step Name: Step 2: Delete Edit Pages permission for Group A
     * *Step Description:
     * - Check the Group A permission
     * - In the permission table, check the Edit Pages checkbox of the Group A
     * - Uncheck permission "Admin Pages" and "Admin Wiki"
     * - Click Save
     * -
     * -> OK
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - Edit Pages and View Page permission are selected
     * - Other permission are not selected
     * *Step number: 3
     * *Step Name: Step 3: Check if the Group A has Edit Pages permission
     * *Step Description:
     * - Log in with a user member of the Group A
     * - Go to Wiki of the space.
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - The user can view the wiki page and can see Edit Page, Add Page;
     * Move Page and Delete Page from More menu
     * - The user can not see Wiki Settings from Browse menu
     * - The user A can not see Page Permissions from More menu
     */
    @Test
    public void test08_VerifyEditPermissionForAGroup() {
        info("Test 8: Verify Edit permission for a group");
        info("Create 1 new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(1, password);
        info("Create a new group");
        String groupName = "groupName" + getRandomNumber();
        String groupLabel = "groupLabel" + getRandomNumber();
        String groupDesc = "groupDesc " + getRandomNumber();
        navigationToolbar.goToUsersAndGroupsManagement();
        userAndGroupManagement.goToGroupTab();
        userAndGroupManagement.addGroup(groupName, groupLabel, groupDesc, true);
        arrayUsers.add(groupLabel);
        info("Add users to the group");
        String membership = "manager";
        navigationToolbar.goToUsersAndGroupsManagement();
        userAndGroupManagement.goToGroupTab();
        userAndGroupManagement.selectGroup(groupLabel);
        userAndGroupManagement.addUsersToGroup(arrayUsers.get(0), membership, false, true);
        info("Create new page 1");
        String page = "page" + getRandomNumber();
        String pageContent = "pageContent" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page, pageContent);
        wikiManagement.saveAddPage();
        refresh();
        wikiValidattions.verifyTitleWikiPage(page);
        info(" Open form to add permission for space");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select group to add permission");
        String groupUsers = "*:/" + groupName;
        wikiPermission.addPermisisonByType(groupUsers);
        wikiPermission.selectPermission(groupUsers, WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison();
        wikiHomePage.confirmWaringMessage(true);
        arrayUsers.add(groupUsers);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        $(ELEMENT_EDIT_PAGE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_ADD_PAGE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        refresh();
        ELEMENT_MORE_LINK.click();
        $(ELEMENT_MOVE_PAGE).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_DELETE_LINK).waitUntil(Condition.visible, Configuration.timeout);
        $(ELEMENT_PERMISSION_LINK).waitUntil(Condition.not(Condition.visible), Configuration.timeout);

        $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).click();
        $(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    }
}


