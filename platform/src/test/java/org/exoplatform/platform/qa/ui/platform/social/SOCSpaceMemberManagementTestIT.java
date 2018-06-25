package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("social")
@Tag("sniff")
public class SOCSpaceMemberManagementTestIT extends Base {
    NavigationToolbar navigationToolbar;

    ManageLogInOut manageLogInOut;

    AddUsers addUsers;

    SpaceSettingManagement spaceSettingManagement;

    SpaceHomePage spaceHomePage;

    SpaceManagement spaceManagement;

    HomePagePlatform homePagePlatform;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut = new ManageLogInOut(this);
        addUsers = new AddUsers(this);
        spaceSettingManagement = new SpaceSettingManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        spaceManagement = new SpaceManagement(this);
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    }

    /**
     * <li>Case ID:121890.</li>
     * <li>Test Case Name: Change member's role in Space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Change member's
     * role Step Description: - Invite a user become member of space - User accept
     * invitation - Login by manager of requested space: Access space -> space
     * setting -> member tab - Select user and click on Remove Leader or Make Leader
     * icon Input Data: Expected Outcome: -User become member of Space - After make
     * user is leader, user can access space setting of space.
     */
    @Test
    public void test01_ChangeMemberRoleInSpace() {
        info("Test 01: Change member's role in Space");
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
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.inviteUser(username2, false, "");

        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);

        manageLogInOut.signIn(username1, password);
        spaceHomePage.goToSpace(space);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.changeRole(username2 + " " + username2);
        manageLogInOut.signIn(username2, password);
        spaceHomePage.goToSpace(space);
        spaceHomePage.goToSpaceSettingTab();
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);

    }

    /**
     * <li>Case ID:121895.</li>
     * <li>Test Case Name: Invite/Accept user to Space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Invite/Accept user
     * Step Description: - Access to a space from my space list - Click on Space
     * Settings icon, select Member tab - Click on User icon, select some users from
     * list and click on Invite icon - Login by invited users: + Go to My space ->
     * Invitations Received tab and click on Accept + Another user click on Ignore
     * button Input Data: Expected Outcome: - Space settings is displayed, focus on
     * Member tab - Name of invited user is displayed on invited list. - After
     * invited user accept invitation, invited space will move from invitation space
     * list to my space list of user - Accepted user is displayed on Member List -
     * After user ignored, the invitation is removed from Received Invitations list
     * and this user does not displayed on Member list of space
     */
    @Test
    public void test02_InviteAcceptUserToSpace() {
        info("Test 02: Invite/Accept user to Space");
        String space = "space" + getRandomNumber();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@test.com";
        String username3 = "usernamec" + getRandomString();
        String email3 = username3 + "@test.com";
        String password = "123456";

        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        addUsers.addUser(username3, password, email3, username3, username3);
        manageLogInOut.signIn(username1, password);

        info("Create a space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        info(" After invited user accept invitation, invited  space will move from invitation space list to my space list of user");
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.inviteUser(username2, false, "");
        spaceSettingManagement.inviteUser(username3, false, "");

        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);

        manageLogInOut.signIn(username3, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.ignoreAInvitation(space);

        manageLogInOut.signIn(username1, password);
        spaceHomePage.goToSpace(space);
        spaceHomePage.goToSpaceSettingTab();
        info("Accepted user is displayed on Member List");
        ELEMENT_SPACE_SETTINGS_MEMBERS_TAB_IN_SETTING_TAB.click();
        $(byXpath(ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}", username2 + " " + username2))).waitUntil(Condition.visible,
                Configuration.timeout);
        $(byXpath(ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}",
                username3 + " " + username3))).waitUntil(Condition.not(Condition.visible),
                Configuration.timeout);

        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
        addUsers.deleteUser(username3);
    }

    /**
     * <li>Case ID:121896.</li>
     * <li>Test Case Name: Leave Space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Leave Space Step
     * Description: - Go to My space page - Select a space and Click on Leave button
     * on space Input Data: Expected Outcome: - Display list of space which user Is
     * member - Space is disappeared from list and moved to All spaces list, user is
     * not member of space.
     */
    @Test
    public void test03_LeaveSpace() {
        info("Test 03: Leave Space");
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
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.inviteUser(username2, false, "");

        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);
        homePagePlatform.goToMySpaces();
        spaceManagement.leaveSpace(space);

        manageLogInOut.signIn(username1, password);
        spaceHomePage.goToSpace(space);
        spaceHomePage.goToSpaceSettingTab();
        info(" Space is disappeared from list and moved to All spaces list, user is not member of space");
        ELEMENT_SPACE_SETTINGS_MEMBERS_TAB_IN_SETTING_TAB.click();
        $(byXpath(ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}", username2 + " " + username2))).shouldNot(Condition.exist);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }

    /**
     * <li>Case ID:121902.</li>
     * <li>Test Case Name: Remove user from space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Remove user Step
     * Description: - Invite a user become member of space - User accept invitation
     * - Managers of space go to Space settings -> member - Select user and click on
     * Remove icon Input Data: Expected Outcome: - User become member of Space -
     * User is remove out space. User is not display on Member list.
     */
    @Test
    public void test04_RemoveUSerFromSpace() {
        info("Test 04: Remove user from space");
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
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.inviteUser(username2, false, "");

        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);

        manageLogInOut.signIn(username1, password);
        spaceHomePage.goToSpace(space);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.removeUser(username2 + " " + username2);

        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }

    /**
     * <li>Case ID:121903.</li>
     * <li>Test Case Name: Request/Accept user to Space.</li>
     * <li>Pre-Condition:</li>
     * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Request/Accept
     * user Step Description: - Go to My space page and select All Spaces tab -
     * Click on Request to Join button corresponding of a specific Space - Click on
     * Requests Pending - Login by manager of requested space: Access space -> space
     * setting -> member tab - Accept/deny request by clicking on Validate or
     * Decline icon - Verify requested user is displayed Member List Input Data:
     * Expected Outcome: - Public space list is accessed - After send request, the
     * button Request to Join is changed to Cancel - Display the space user has just
     * requested to join in Requests Pending list - After manager accept the
     * request, sent user is member of space - Accepted user is displayed Member
     * List
     */
    @Test
    public void test05_RequestAcceptUserToSpace() {
        info("Test 05:Request/Accept user to Space");
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
        spaceManagement.sendARequestToASpace(space);
        spaceManagement.goToRequestPendingTab();
        $(byXpath(ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_PENDING.replace("${space}", space))).should(Condition.visible);

        manageLogInOut.signIn(username1, password);
        spaceHomePage.goToSpace(space);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.acceptRequest(username2);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
    }

    @Tag("SOC-5834")
    @Test
    public void test06_checkMembersListIsSortedByAlphabeticalOrder() {
        info("Create a space");
        String space = "space" + getRandomNumber();
        String username1 = "ausername" + getRandomString();
        String email1 = username1 + "@test.com";
        String username2 = "bsername" + getRandomString();
        String email2 = username2 + "@test.com";
        String username3 = "csername" + getRandomString();
        String email3 = username3 + "@test.com";
        String username4 = "dsername" + getRandomString();
        String email4 = username4 + "@test.com";
        String password = "123456";
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        addUsers.addUser(username2, password, email2, username2, username2);
        addUsers.addUser(username3, password, email3, username3, username3);
        addUsers.addUser(username4, password, email4, username4, username4);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        spaceSettingManagement.inviteUser(username1, false, "");
        spaceSettingManagement.inviteUser(username2, false, "");
        spaceSettingManagement.inviteUser(username3, false, "");
        spaceSettingManagement.inviteUser(username4, false, "");
        manageLogInOut.signIn(username1, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);
        manageLogInOut.signIn(username3, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);
        manageLogInOut.signIn(username4, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToMySpaces();
        ELEMENT_SPACES_LIST.find(byText(space)).click();
        spaceSettingManagement.goToMemberTab();
        ELEMENT_LIST_OF_MEMBERS_IN_SPACE.get(0).shouldHave(Condition.text(username1 + " " + username1));
        ELEMENT_LIST_OF_MEMBERS_IN_SPACE.get(1).shouldHave(Condition.text(username2 + " " + username2));
        ELEMENT_LIST_OF_MEMBERS_IN_SPACE.get(2).shouldHave(Condition.text(username3 + " " + username3));
        ELEMENT_LIST_OF_MEMBERS_IN_SPACE.get(3).shouldHave(Condition.text(username4 + " " + username4));
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.goToMemberTabInSpaceSettingTab();
        $$(byId("existingUsersTable")).get(0).shouldHave(Condition.text(username1 + " " + username1));
        $$(byId("existingUsersTable")).get(1).shouldHave(Condition.text(username2 + " " + username2));
        $$(byId("existingUsersTable")).get(2).shouldHave(Condition.text(username3 + " " + username3));
        $$(byId("existingUsersTable")).get(3).shouldHave(Condition.text(username4 + " " + username4));
        $$(byId("existingUsersTable")).get(4).shouldHave(Condition.text(PLFData.DATA_NAME_USER1));
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);
        addUsers.deleteUser(username3);
        addUsers.deleteUser(username4);
    }
}
