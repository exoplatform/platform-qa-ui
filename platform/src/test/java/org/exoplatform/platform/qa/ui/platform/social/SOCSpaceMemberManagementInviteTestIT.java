package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("social")
@Tag("sniff")
public class SOCSpaceMemberManagementInviteTestIT extends Base {

  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  SpaceManagement        spaceManagement;

  SpaceSettingManagement spaceSettingManagement;

  SpaceHomePage          spaceHomePage;

  UserAndGroupManagement userAndGroupManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    userAndGroupManagement = new UserAndGroupManagement(this);

  }

  @Test
  public void test01_InvitedUserAcceptInvitation() {
    info("Test 2: Invited user accept invitation");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "Aa123456";
    info("Add new user");
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);

    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();

    ELEMENT_INPUT_INVITE_USER.sendKeys(username2);
    $(ELEMENT_SPACE_BTN_INVITE).click();

    manageLogInOut.signIn(username2, password);
    ELEMENT_ALERT_NOTIFICATION_EXIST.waitUntil(Condition.appears, Configuration.timeout).click();
    $(byText("Accept")).click();
    homePagePlatform.goToAllSpace();
    $(byText(space)).should(Condition.exist);
    spaceManagement.sendARequestToASpace(space);
    spaceManagement.goToRequestPendingTab();
    $(byText(space)).waitUntil(Condition.appears, Configuration.timeout);

    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceSettingManagement.goToMemberTab();
    $(byId("spaceMemberListBox")).scrollTo().find(byText(username2 + " " + username2)).should(Condition.exist);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTabInSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    $(byText(username2 + " " + username2)).scrollTo().should(Condition.exist);
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username1);
    userAndGroupManagement.deleteUser(username2);

  }

}
