package org.exoplatform.platform.qa.ui.platform.wiki.functional;

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

import static java.time.zone.ZoneRulesProvider.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("wiki")
@Tag("functional")
public class WikiPermissionPagePermissionAddTestIt extends Base {

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
     * <li> Case ID:139324.</li>
     * <li> Test Case Name: Add permission for group by putting group's name.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * *Step Number: 1
     * *Step Name: Step 1: Create new page
     * *Step Description:
     * - Go to Add Page
     * -> Blank Page (or From Template)
     * - Add values in required fields
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful
     * *Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More
     * -> Page Permissions
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all Users or Groups have permission to view/edit page
     * *Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More
     * -> Page Permissions
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all Users or Groups have permission to view/edit page
     * *Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More
     * -> Page Permissions
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all Users or Groups have permission to view/edit page
     * /*Step number: 3
     * *Step Name: Step 3: Select user to add permission
     * *Step Description:
     * - Put group's name in field
     * - Click on Add icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Selected group is listed in permission's list with View Pages permission selected by default
     * *Step number: 4
     * *Step Name: Step 4: Select permission for group
     * *Step Description:
     * - Select permission for group to view/edit by ticking the checkboxes
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - Mermbers of the selected group has view/edit permission
     */
    @Test
    public void test01_AddPermissionForGroupByPuttingGroupsName() {
        info("Test 1: Add permission for group by putting group's name");
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
        wikiValidattions.verifyTitleWikiPage(page);
        //arrayPage.add(page);
        info("Open form to page permission");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select group to add permission");
        String groupUsers = "*:/" + groupName;
        wikiPermission.addPermisisonByType(groupUsers);
        wikiPermission.selectPermission(groupUsers, WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison();
        wikiHomePage.confirmWaringMessage(true);
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
        wikiHomePage.goToEditPage();
    }

    /**
     * <li> Case ID:139326.</li>
     * <li> Test Case Name: Add permission for group by selecting directly.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * *Step Number: 1
     * *Step Name: Step 1: Create new page
     * *Step Description:
     * - Go to Add Page
     * -> Blank Page (or From Template)
     * - Add values in required fields
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful
     * <p>
     * /*Step number: 2
     * Step Name: Step 2: Open form to page permission
     * Step Description:
     * - Select page above
     * - Select More
     * -> Page Permissions
     * Input Data:
     * <p>
     * Expected Outcome:
     * Page permission form appears. It list all User or Group have permission to view/edit page
     * /*Step number: 3
     * *Step Name: Step 3: Select user to add permission
     * *Step Description:
     * - Click on Add Group icon
     * - Select 1 group
     * - Click on Add icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Selected group is listed in permission's list with View Pages permission selected by default
     * /*Step number: 4
     * *Step Name: Step 4: Select permission for group
     * *Step Description:
     * - Select permission for user to view/edit
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - Members of the group has permission to view/edit this page
     */
    @Test
    public void test02_AddPermissionForGroupBySelectingDirectly() {
        info("Test 2: Add permission for group by selecting directly");
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
        wikiValidattions.verifyTitleWikiPage(page);
        //arrayPage.add(page);
        info("Select group to add permission");
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
        wikiHomePage.goToEditPage();
    }

    /**
     * <li> Case ID:139328.</li>
     * <li> Test Case Name: Add permission for group's membership  by selecting directly.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * *Step Number: 1
     * *Step Name: Step 1: Create new page
     * *Step Description:
     * - Go to Add Page
     * ->Blank Page (or From Template)
     * - Add values in required fields
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful
     * /*Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More
     * -> Page Permission
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all User or Group have permission to view/edit page
     * <p>
     * *Step number: 3
     * *Step Name: Step 3: Select user to add permission
     * *Step Description:
     * - Click on Select Membership icon
     * - Select 1 group
     * - Select membership of this group
     * - Click on Add icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Selected group is listed in permission's list withView Pages permission selected by default
     * *Step number: 4
     * *Step Name: Step 4: Select permission for group
     * *Step Description:
     * - Select permission for user to view/edit
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - Members of the group has permission to view/edit this page
     */
    @Test
    public void test03_AddPermissionForGroupsMembershipBySelectingDirectly() {
        info("Test 3: Add permission for group's membership  by selecting directly");

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
        wikiHomePage.goToEditPage();

    }

    /**
     * <li> Case ID:139331.</li>
     * <li> Test Case Name: Add permission for multi-user at the same time.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Create new page
     * *Step Description:
     * - Go to Add Page
     * ->Blank Page (or From Template)
     * - Add values in required fields
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful
     * /*Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More
     * -> Page Permission
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all User or Group have permission to view/edit page
     * /*Step number: 3
     * *Step Name: Step 3: Select user to add permission
     * *Step Description:
     * - Click on Add User icon
     * - Tick on some check
     * -box corresponding with users want to add
     * - Click on Add button
     * - Click on Add icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Selected users are listed in permission's list with View Pages permission selected by default
     * /*Step number: 4
     * *Step Name: Step 4: Select permission for user
     * *Step Description:
     * - Select permission for users to view/edit
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - The selected users have permission to view/edit this page
     */
    @Test
    public void test04_AddPermissionForMultiuserAtTheSameTime() {
        info("Test 4: Add permission for multi-user at the same time");

        info("Create 3 new users");
        ArrayList<String> arrayUsers;
        String password = "123456";
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(3, password);
        info("Create new page 1");
        String page = "page" + getRandomNumber();
        String pageContent = "pageContent" + getRandomNumber();
        homePagePlatform.goToWiki();
        wikiHomePage.goToAddBlankPage();
        richTextEditor.addSimplePage(page, pageContent);
        wikiManagement.saveAddPage();
        refresh();
        wikiValidattions.verifyTitleWikiPage(page);
        info("Open form to page permission");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select user to add permission");
        wikiPermission.addPermisisonByType(arrayUsers.get(1));
        wikiPermission.selectPermission(arrayUsers.get(1), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(1));
        wikiPermission.selectPermission(arrayUsers.get(1), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(2));
        wikiPermission.selectPermission(arrayUsers.get(2), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(2));
        wikiPermission.selectPermission(arrayUsers.get(2), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(3));
        wikiPermission.selectPermission(arrayUsers.get(3), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(3));
        wikiPermission.selectPermission(arrayUsers.get(3), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        refresh();
        wikiHomePage.goToEditPage();
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermisisonByType(arrayUsers.get(0));
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(1), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        refresh();
        wikiHomePage.goToEditPage();
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(2), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        refresh();
        wikiHomePage.goToEditPage();
    }

    /**
     * <li> Case ID:139333.</li>
     * <li> Test Case Name: Add permission for user by putting user's name.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Create new page
     * *Step Description:
     * - Go to Add Page / Blank Page (or From Template)
     * - Add values in required fields
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful
     * /*Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More/ Page Permission
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all User or Group have permission to view/edit page
     * /*Step number: 3
     * *Step Name: Step 3: Select user to add permission
     * *Step Description:
     * - Put user's name in field
     * - Click on Add icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Selected user is listed in permission's list with View Pages permission selected by default
     * /*Step number: 4
     * *Step Name: Step 4: Select permission for user
     * *Step Description:
     * - Select permission for user to edit
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - User has permission to view/edit this page
     */
    @Test
    public void test05_AddPermissionForUserByPuttingUsersName() {
        info("Test 5: Add permission for user by putting user's name");

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
        refresh();
        wikiValidattions.verifyTitleWikiPage(page);
        info("Open form to page permission");
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
        wikiHomePage.goToEditPage();
    }

    /**
     * <li> Case ID:139335.</li>
     * <li> Test Case Name: Add permission for user by searching user by email.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Create new page
     * *Step Description:
     * - Go to Add Page and Blank Page (or From Template)
     * - Add values in required fields
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful
     * /*Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More and Page Permissions
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all User or Group have permission to view/edit page
     * <p>
     * <p>
     * *Step number: 3
     * *Step Name: Step 3: Searching user
     * *Step Description:
     * - Click on Add User icon
     * - Choose Email
     * - Put email in Search field
     * - Click on Search icon
     * *Input Data:
     * *Expected Outcome:
     * All users corresponding with the emails are listed
     * *Step number: 4
     * *Step Name: Step 4: Select user to add permission
     * *Step Description:
     * - Tick on check
     * -box corresponding with user want to add
     * - Click on Add button
     * - Click on Add icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - Selected user is listed in permission's list with View Pages permission selected by default
     */
    @Test
    public void test06_AddPermissionForUserBySearchingUserByEmail() {
        info("Test 6: Add permission for user by searching user by email");

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
        refresh();
        wikiValidattions.verifyTitleWikiPage(page);
        info("Open form to page permission");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select user to add permission");
        String email = arrayUsers.get(0) + "@gmail.com";
        wikiPermission.addPermissionBySelect(email, "", WikiPermission.userGroupTypes.Users_Email);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison();
        wikiHomePage.confirmWaringMessage(true);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermissionBySelect(email, "", WikiPermission.userGroupTypes.Users_Email);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);

        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        refresh();
        wikiHomePage.goToEditPage();
    }

    /**
     * <li> Case ID:139337.</li>
     * <li> Test Case Name: Add permission for user by searching user by first name.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Create new page
     * *Step Description:
     * - Go to Add Page
     * -
     * -> ’Blank Page (or From Template)
     * - Add values in required fields
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * * 			- New page is created successful
     * /*Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More
     * -
     * -> Page Permission
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all User or Group have permission to view/edit page
     * <p>
     * /*Step number: 3
     * *Step Name: Step 3: Searching user
     * *Step Description:
     * - Click on Add User icon
     * - Choose First Name
     * - Put keyword in Search field
     * - Click on Search icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * All users corresponding with the keyword are listed
     * /*Step number: 4
     * *Step Name: Step 4: Select user to add permission
     * *Step Description:
     * - Tick on check
     * -box corresponding with user want to add
     * - Click on Add button
     * - Click on Add icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - Selected user is listed in permission's list with View Pages permission selected by default
     * /*Step number: 5
     * Step Name: Step 5: Select permission for user
     * Step Description:
     * - Select permission for user to view/edit
     * - Click Save
     * Input Data:
     * <p>
     * Expected Outcome:
     * User has permission to view/edit this page
     */
    @Test
    public void test07_AddPermissionForUserBySearchingUserByFirstName() {
        info("Test 7: Add permission for user by searching user by first name");
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
        refresh();
        wikiValidattions.verifyTitleWikiPage(page);
        info("Open form to page permission");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        wikiPermission.addPermissionBySelect(arrayUsers.get(0), "", WikiPermission.userGroupTypes.Users_FirstName);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermissionBySelect(arrayUsers.get(0), "", WikiPermission.userGroupTypes.Users_FirstName);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        refresh();
        wikiHomePage.goToEditPage();
    }

    /**
     * <li> Case ID:139339.</li>
     * <li> Test Case Name: Add permission for user by searching user by last name.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Create new page
     * *Step Description:
     * - Go to Add Page
     * -
     * -> ’Blank Page (or From Template)
     * - Add values in required fields
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful
     * /*Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More â†’ Page Permission
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all Users or Groups have permission to view/edit page
     * <p>
     * *Step number: 3
     * *Step Name: Step 3: Searching user
     * *Step Description:
     * - Click on Add User icon
     * - Choose Last Name
     * - Put keyword in Search field
     * - Click on Search icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * All users corresponding with the keyword are listed
     * <p>
     * *Step number: 4
     * *Step Name: Step 4: Select user to add permission
     * *Step Description:
     * - Tick on check
     * -box corresponding with user want to add
     * - Click on Add button
     * - Click on Add icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Selected user is listed in permission's list with View Pages permission selected by default
     * /*Step number: 5
     * Step Name: Step 5: Select permission for user
     * Step Description:
     * - Select permission for user to view/edit
     * - Click Save
     * Input Data:
     * <p>
     * Expected Outcome:
     * - Selected user has permission to view/edit the page
     */
    @Test
    public void test08_AddPermissionForUserBySearchingUserByLastName() {
        info("Test 8: Add permission for user by searching user by last name");
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
        refresh();
        wikiValidattions.verifyTitleWikiPage(page);
        info("Open form to page permission");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select user to add permission");
        wikiPermission.addPermissionBySelect(arrayUsers.get(0), "", WikiPermission.userGroupTypes.Users_LastName);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermissionBySelect(arrayUsers.get(0), "", WikiPermission.userGroupTypes.Users_LastName);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        refresh();
        wikiHomePage.goToEditPage();
    }

    /**
     * <li> Case ID:139341.</li>
     * <li> Test Case Name: Add permission for user by searching user by user name.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Create new page
     * *Step Description:
     * - Go to Add Page â†’Blank Page (or From Template)
     * - Add values in required fields
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful
     * /*Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More â†’ Page Permission
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all Users or Groups have permission to view/edit page
     * <p>
     * Step number: 3
     * *Step Name: Step 3: Searching user
     * *Step Description:
     * - Click on Add User icon
     * - Choose User Name
     * - Put keyword in Search field
     * - Click on Search icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * All users corresponding with the keyword are listed
     * <p>
     * /*Step number: 4
     * *Step Name: Step 4: Select user to add permission
     * *Step Description:
     * - Tick on check
     * -box corresponding with user want to add
     * - Click on Add button
     * - Click on Add icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Selected user is listed in permission's list with View Pages permission selected by default
     * /*Step number: 5
     * *Step Name: Step 5: Select permission for user
     * *Step Description:
     * - Select permission for user to view/edit
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * User has permission to view/edit this page
     */
    @Test
    public void test09_AddPermissionForUserBySearchingUserByUserName() {
        info("Test 9: Add permission for user by searching user by user name");
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
        refresh();
        wikiValidattions.verifyTitleWikiPage(page);
        info("Open form to page permission");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select user to add permission");
        wikiPermission.addPermissionBySelect(arrayUsers.get(0), "", WikiPermission.userGroupTypes.Users_UserName);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermissionBySelect(arrayUsers.get(0), "", WikiPermission.userGroupTypes.Users_UserName);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        refresh();
        wikiHomePage.goToEditPage();
    }

    /**
     * <li> Case ID:139343.</li>
     * <li> Test Case Name: Add permission for user by selecting directly.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Create new page
     * *Step Description:
     * - Go to Add Page
     * -
     * -> ’Blank Page (or From Template)
     * - Add values in required fields
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful
     * /*Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More
     * -
     * -> Page Permissions
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all Users or Groups have permission to view/edit page
     * <p>
     * /*Step number: 3
     * *Step Name: Step 3: Select user to add permission
     * *Step Description:
     * - Click on Add User icon
     * - Tick on check
     * -box corresponding with user want to add
     * - Click on Add button
     * - Click on Add icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Selected user is listed in permission's list with View Pages permission selected by default
     * *Step number: 4
     * *Step Name: Step 4: Select permission for user
     * *Step Description:
     * - Select permission for user to view/edit
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * User has permission to view/edit this page
     */
    @Test
    public void test10_AddPermissionForUserBySelectingDirectly() {
        info("Test 10 Add permission for user by selecting directly");
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
        refresh();
        wikiValidattions.verifyTitleWikiPage(page);
        info("Open form to page permission");
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select user to add permission");
        wikiPermission.addPermissionBySelect(arrayUsers.get(0), "", WikiPermission.userGroupTypes.Users_FirstName);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Select permission page");
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToPermissions();
        wikiPermission.addPermissionBySelect(arrayUsers.get(0), "", WikiPermission.userGroupTypes.Users_FirstName);
        wikiPermission.selectPermission(arrayUsers.get(0), WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        refresh();
        wikiHomePage.goToEditPage();
    }

    /**
     * <li> Case ID:139345.</li>
     * <li> Test Case Name: Add permission for users by putting group's membership.</li>
     * <li> Pre-Condition: </li>
     * <li> Post-Condition: </li>
     * /*Step Number: 1
     * *Step Name: Step 1: Create new page
     * *Step Description:
     * - Go to Add Page
     * -
     * -> Blank Page (or From Template)
     * - Add values in required fields
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * - By default, the [Create Wiki page] is displayed in the [Rich Text] mode
     * - New page is created successful
     * /*Step number: 2
     * *Step Name: Step 2: Open form to page permission
     * *Step Description:
     * - Select page above
     * - Select More
     * -
     * -> Page Permissions
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Page permission form appears. It list all Users or Groups have permission to view/edit page
     * <p>
     * /*Step number: 3
     * *Step Name: Step 3: Select user to add permission
     * *Step Description:
     * - Put group's membership in field
     * - Click on Add icon
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Selected groups is listed in permission's list with View Pages permission selected by default
     * /*Step number: 4
     * *Step Name: Step 4: Select permission for group's membership
     * *Step Description:
     * - Select permission for group to view/edit
     * - Click Save
     * *Input Data:
     * <p>
     * *Expected Outcome:
     * Group has permission to view/edit this page
     */
    @Test
    public void test11_AddPermissionForUsersByPuttingGroupsMembership() {
        info("Test 11 Add permission for users by putting group's membership");
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
        info("Open form to page permission");
        String groupUsers = "manager:/developers";
        homePagePlatform.goToWiki();
        wikiHomePage.goToWikiSettingPage();
        wikiSettingPage.goToPermissionTab();
        info("Select user to add permission");
        wikiPermission.addPermisisonByType(groupUsers);
        wikiPermission.selectPermission(groupUsers, WikiPermission.permissionType.Edit_Pages);
        wikiPermission.savePermisison(true);
        wikiHomePage.confirmWaringMessage(true);
        info("Mermbers of the selected group has view/edit permission");
        manageLogInOut.signIn("john","gtngtn",true);
        homePagePlatform.goToWiki();
        wikiHomePage.goToAPage(page);
        wikiHomePage.goToEditPage();
    }
}
