package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("plf")
@Tag("sniff")
public class PlfHomepageGadgetSuggestionGadgetTestIT extends Base {

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  SpaceManagement        spaceManagement;

  ConnectionsManagement  connectionsManagement;

  SpaceSettingManagement spaceSettingManagement;

  SpaceHomePage          spaceHomePage;

  AddUsers               addUsers;

  UserAndGroupManagement userAndGroupManagement;

  NavigationToolbar      navigationToolbar;

  String                 space1;

  String                 space2;

  String                 space3;

  String                 space4;

  String                 space5;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    spaceManagement = new SpaceManagement(this);
    connectionsManagement = new ConnectionsManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    addUsers = new AddUsers(this);
    navigationToolbar = new NavigationToolbar(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }


  /**
   * Create 3 spaces
   */
  public void createSpaces() {
    info("--Login with mary account--");
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    info("Create 1 space");
    space1 = "space1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info("--Log in with james account--");
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    info("Create 1 space");
    space2 = "space2" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);

    info("-- Log in with demo account--");
    manageLogInOut.signIn(DATA_USER4, DATA_PASS);
    info("Create 1 space");
    space3 = "space3" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space3, space3);

    info("--Login back to Root");

    manageLogInOut.signIn(username, password);
  }

  /**
   * Create 2 spaces and invite to John account
   */
  public void createTwoSpaces() {
    info("--Login with mary account--");
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    info("Create 1 space");
    space4 = "space4" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space4, space4);

    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    spaceSettingManagement.inviteUser(DATA_USER4, false, "");

    info("--Log in with james account--");
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    info("Create 1 space");
    space5 = "space5" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space5, space5);

    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    spaceSettingManagement.inviteUser(DATA_USER4, false, "");

    info("--Login back to John");
    info("Sign in with John account");
    manageLogInOut.signIn(username, password);
  }

  /**
   * Delete all spaces are created before
   */
  public void deleteDataTest() {
    info("Delete space 1");
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
    info("Delete space 2");
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space2, false);
    info("Delete space 3");
    manageLogInOut.signIn(DATA_USER4, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space3, false);
  }

  /**
   * <li>Case ID:120858.</li>
   * <li>Test Case Name: Not show suggestions gadget.</li> Step number: 4 Step
   * Name: Invite user A Step Description: - Login with user B and C - Go to
   * [Space setting] - Choose [member] tab - invite user A is member of space
   * Input Data: Expected Outcome: Invite user successfully Step number: 5 Step
   * Name: Check show the space suggestion Step Description: - Login by user A
   * Input Data: Expected Outcome: Don't show the space suggestion Step number: 6
   * Step Name: Invite user Step Description: - Login by user A - Go to [My
   * connetions] - Connect with user B and C Input Data: Expected Outcome: Connect
   * user successfully Step number: 7 Step Name: Check when this gadget disappears
   * Step Description: Back to homepage Input Data: Expected Outcome: The
   * suggestions gadget is not hided
   */
  @Test
  public void test02_NotShowSuggestionsGadget() throws Exception {
    info("Test 02: Not show suggestions gadget");

    info("Invite John to 2 spaces");
    createTwoSpaces();
    manageLogInOut.signIn(DATA_USER4, DATA_PASS);

    info("Verify that Don't show the space suggestion");
    ELEMENT_SUGGETION_SPACE.find(byText(space4)).shouldNot(Condition.exist);
    ELEMENT_SUGGETION_SPACE.find(byText(space5)).shouldNot(Condition.exist);

    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER1);
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);

    homePagePlatform.goToHomePage();
    $(ELEMENT_SUGGESTION_BOX).find(byText(DATA_USER1)).shouldNot(Condition.exist);
    $(ELEMENT_SUGGESTION_BOX).find(byText(DATA_USER2)).shouldNot(Condition.exist);
    $(ELEMENT_SUGGESTION_BOX).find(byText(DATA_USER3)).shouldNot(Condition.exist);
    info("delete data");
    homePagePlatform.goToConnections();
    connectionsManagement.cancelConnection(DATA_USER1);
    connectionsManagement.cancelConnection(DATA_USER2);
    connectionsManagement.cancelConnection(DATA_USER3);

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space4, false);
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space5, false);
  }

  /**
   * <li>Case ID:120857.</li>
   * <li>Test Case Name: Check display of Suggestions Gadget.</li>
   * <li>Pre-Condition: There are more than 4 users on system. There are more than
   * 3 spaces on system which userA is not member</li>
   * <li>Post-Condition:</li>
   * <li>Done with OSs and browsers :</li>
   * <li>Generated by rosso at 2015/02/09 14:31:58</li> Step Number: 1 Step Name:
   * - Check display of Suggestions gadget Step Description: - Login as userA -
   * Open intranet home Input Data: Expected Outcome: - This gadget is displayed
   * at the right, as attachment SuggestionsGadget.png - The suggestion gadget
   * always displays 2 people suggestions and 2 space suggestions. - The 2 people
   * suggestions are people with the most common connections with the users and
   * ordered by alphabet - The 2 space suggestions are spaces that have the most
   * members who are user's connections and ordered by creation date
   */
  @Test

  public void test01_CheckDisplayOfSuggestionsGadgetIfCommunConnectionIs0() {
    String username1 = "usernamea" + getRandomString();
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String email = username1 + "@test.com";
    String password = "123456";
    info("Test 01: Check display of Suggestions Gadget");

    info("--Create 3 spaces--");
    createSpaces();
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(DATA_USER2, PLFData.password);
    info("Verify that The suggestion gadget always displays 2 people suggestions");
    $(ELEMENT_SUGGESTION_BOX).find(byText(username1 + " " + username1)).should(Condition.exist);
    $(ELEMENT_SUGGESTION_BOX).find(byText(username2 + " " + username2)).should(Condition.exist);
    info("Verify that The suggestion gadget always displays 2 last spaces suggestions");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    ELEMENT_SUGGETION_SPACE.find(byText(space2)).should(Condition.exist);
    ELEMENT_SUGGETION_SPACE.find(byText(space3)).should(Condition.exist);
    deleteDataTest();
    homePagePlatform.goToAllSpace();
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username1);
    userAndGroupManagement.deleteUser(username2);
  }

  /*
   * Step Number: 1 Step Name: - Check display of Suggestions gadget Step
   * Description: - Login as userA - Open intranet home Input Data: Expected
   * Outcome: - This gadget is displayed at the right, as attachment
   * SuggestionsGadget.png - The suggestion gadget always displays 2 people
   * suggestions and 2 space suggestions. - The 2 people suggestions are people
   * with the most common connections with the users and ordered by alphabet - The
   * 2 space suggestions are spaces that have the most members who are user's
   * connections and ordered by creation date
   */
  @Test
  public void test03_CheckDisplayOfSuggestionsGadgethigherNumberOfCommonConnections() {
    String username1 = "usernamea" + getRandomString();
    String username2 = "usernameb" + getRandomString();
    String username3 = "usernamec" + getRandomString();
    String username4 = "usernamed" + getRandomString();
    String email2 = username2 + "@test.com";
    String email = username1 + "@test.com";
    String email3 = username3 + "@test.com";
    String email4 = username4 + "@test.com";
    String password = "123456";
    info("Test 01: Check display of Suggestions Gadget");

    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);
    manageLogInOut.signIn(username1, password);
    ELEMENT_GADGET_INVITATION.find(byText(DATA_NAME_ROOT)).waitUntil(Condition.visible, Configuration.timeout).hover();
    ELEMENT_GADGET_INVITATION.find(ELEMENT_BUTTON_CONNECT_USER_FROM_GADGET)
            .waitUntil(Condition.visible, Configuration.timeout)
            .click();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username3);
    connectionsManagement.connectToAUser(username4);
    manageLogInOut.signIn(username3, password);
    ELEMENT_GADGET_INVITATION.find(byText(username1 + " " + username1))
            .waitUntil(Condition.visible, Configuration.timeout)
            .hover();
    ELEMENT_GADGET_INVITATION.find(ELEMENT_BUTTON_CONNECT_USER_FROM_GADGET)
            .waitUntil(Condition.visible, Configuration.timeout)
            .click();
    manageLogInOut.signIn(username4, password);
    ELEMENT_GADGET_INVITATION.find(byText(username1 + " " + username1))
            .waitUntil(Condition.visible, Configuration.timeout)
            .hover();
    ELEMENT_GADGET_INVITATION.find(ELEMENT_BUTTON_CONNECT_USER_FROM_GADGET)
            .waitUntil(Condition.visible, Configuration.timeout)
            .click();
    manageLogInOut.signIn(username, PLFData.password);
    info("Verify that The suggestion gadget always displays 2 people suggestions");
    $(ELEMENT_SUGGESTION_BOX).find(byText(username3 + " " + username3)).should(Condition.exist);
    $(ELEMENT_SUGGESTION_BOX).find(byText(username4 + " " + username4)).should(Condition.exist);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username1);
    userAndGroupManagement.deleteUser(username2);
    userAndGroupManagement.deleteUser(username3);
    userAndGroupManagement.deleteUser(username4);
  }

  /*
   * Step Number: 1 Step Name: - Check display of Suggestions gadget Step
   * Description: - Login as userA - Open intranet home Input Data: Expected
   * Outcome: - This gadget is displayed at the right, as attachment
   * SuggestionsGadget.png - The suggestion gadget always displays 2 people
   * suggestions and 2 space suggestions. - The 2 people suggestions are people
   * with the most common connections with the users and ordered by alphabet - The
   * 2 space suggestions are spaces that have the most members who are user's
   * connections and ordered by creation date
   */
  @Test
  public void test04_CheckDisplayOfSuggestionsGadgethigherNumberOfMembersInSpace() {
    String username1 = "usernamea" + getRandomString();
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String email = username1 + "@test.com";
    String password = "123456";
    space1 = "spaceA" + getRandomNumber();
    space2 = "spaceB" + getRandomNumber();
    space3 = "spaceC" + getRandomNumber();
    info("Test 01: Check display of Suggestions Gadget");

    info("--Create 3 spaces--");

    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    spaceManagement.goToMemberTab();
    spaceSettingManagement.inviteUser(username1, false, "");
    spaceSettingManagement.inviteUser(username2, false, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    spaceSettingManagement.inviteUser(username1, false, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space3, space3);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToAllSpace();
    ELEMENT_MY_SPACE_SEARCH_TEXT.setValue(space1).pressEnter();
    $(ELEMENT_SPACES_LIST).find(byText(space1)).parent().parent().parent().find(ELEMENT_BUTTON_ACCEPT_SPACE_INVITATION).click();
    homePagePlatform.goToAllSpace();
    ELEMENT_MY_SPACE_SEARCH_TEXT.setValue(space2).pressEnter();
    $(ELEMENT_SPACES_LIST).find(byText(space2)).parent().parent().parent().find(ELEMENT_BUTTON_ACCEPT_SPACE_INVITATION).click();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    ELEMENT_MY_SPACE_SEARCH_TEXT.setValue(space1).pressEnter();
    $(ELEMENT_SPACES_LIST).find(byText(space1)).parent().parent().parent().find(ELEMENT_BUTTON_ACCEPT_SPACE_INVITATION).click();
    manageLogInOut.signIn(DATA_USER2, PLFData.password);
    $(ELEMENT_SUGGESTION_BOX).find(byText(space1)).should(Condition.exist);
    $(ELEMENT_SUGGESTION_BOX).find(byText(space2)).should(Condition.exist);
    manageLogInOut.signIn(username, PLFData.password);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space2, false);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space3, false);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username1);
    userAndGroupManagement.deleteUser(username2);

  }

  @Test
  public void test05_checkDispalayOfSuggestionButtonWhenUserMovesTheMouseOnPeopleSuggestion() {
    ELEMENT_GADGET_USER_SUGGESTION.find(byClassName("peopleName")).waitUntil(Condition.visible, Configuration.timeout).hover();
    ELEMENT_GADGET_USER_SUGGESTION.find(ELEMENT_BUTTON_CONNECT_USER_FROM_GADGET).should(Condition.exist);
    ELEMENT_GADGET_USER_SUGGESTION.find(ELEMENT_BUTTON_CANCEL_SUGGESTION_USER_FROM_GADGET).should(Condition.exist);

  }

  @Test
  public void test06_checkDispalayOfButtonSuggestionWhenUserMovesTheMouseOnSpaceSuggestion() {
    space1 = "spaceA" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1)).hover();
    String id = ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1)).parent().parent().getAttribute("id");
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byXpath(ELEMNT_BUTTON_REQUEST_SPACE_FROM_GADGET.replace("{id}", id)))
            .should(Condition.exist);
    ELEMENT_GAGET_SUGGESTION_SPACE.find(ELEMENT_BUTTON_CANCEL_SUGGESTION_USER_FROM_GADGET).should(Condition.exist);
    manageLogInOut.signIn(username, PLFData.password);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test07_checkHiddenOfSuggestionGadgetWhenUserAcceptSuggestionOfUser() {
    String username1 = "usernamea" + getRandomString();
    String email = username1 + "@test.com";
    String password = "123456";
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email, username1, username1);
    manageLogInOut.signIn(username1, password);
    ELEMENT_GADGET_USER_SUGGESTION.find(byText(DATA_NAME_USER1)).hover();
    ELEMENT_GADGET_USER_SUGGESTION.find(byText(DATA_NAME_USER1)).parent().parent().find(ELEMENT_BUTTON_CONNECT_USER_FROM_GADGET)
            .waitUntil(Condition.appears, Configuration.timeout)
            .click();
    ELEMENT_GADGET_USER_SUGGESTION.find(byText(DATA_NAME_USER1)).shouldNot(Condition.visible);
    manageLogInOut.signIn(username, PLFData.password);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test08_checkHiddenOfSuggestionGadgetWhenUserRefuseSuggestionOfUser() {
    String username1 = "usernamea" + getRandomString();
    String email = username1 + "@test.com";
    String password = "123456";
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email, username1, username1);
    manageLogInOut.signIn(username1, password);
    ELEMENT_GADGET_USER_SUGGESTION.find(byText(DATA_NAME_USER1)).waitUntil(Condition.visible, Configuration.timeout).hover();
    ELEMENT_GADGET_USER_SUGGESTION.find(byText(DATA_NAME_USER1)).parent().parent().find(ELEMENT_BUTTON_CANCEL_SUGGESTION_USER_FROM_GADGET)
            .waitUntil(Condition.visible, Configuration.timeout)
            .click();
    ELEMENT_GADGET_USER_SUGGESTION.find(byText(DATA_NAME_USER1)).shouldNot(Condition.visible);
    manageLogInOut.signIn(username, PLFData.password);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test09_checkHiddenOfSuggestionGadgetWhenUserAcceptSuggestionOfSpace() {
    space1 = "spaceA" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    executeJavaScript("window.scrollBy(0,300)");
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1)).hover();
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1)).parent().find(ELEMENT_BUTTON_CONNECT_USER_FROM_GADGET).click();
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1)).shouldNot(Condition.visible);
    manageLogInOut.signIn(username, PLFData.password);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test10_checkHiddenOfSuggestionGadgetWhenUserCloseSuggestionOfSpace() {
    space1 = "spaceA" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    executeJavaScript("window.scrollBy(0,300)");
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1)).hover();
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1))
            .parent()
            .find(ELEMENT_BUTTON_CANCEL_SUGGESTION_USER_FROM_GADGET).waitUntil(Condition.visible,Configuration.timeout)
            .click();
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1)).shouldNot(Condition.visible);
    manageLogInOut.signIn(username, PLFData.password);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test11_checkDisplayOfOtherSuggestionGadgetWhenUserCloseSuggestionOfSpace() {
    space1 = "spaceA" + getRandomNumber();
    space2 = "spaceB" + getRandomNumber();
    space3 = "spaceC" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space3, space3);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    executeJavaScript("window.scrollBy(0,300)");
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1)).shouldNot(Condition.visible);
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space2)).hover();
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space2))
                                  .parent()
                                  .parent()
                                  .find(ELEMENT_BUTTON_CANCEL_SUGGESTION_USER_FROM_GADGET)
                                  .click();
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1)).shouldNot(Condition.visible);
    manageLogInOut.signIn(username, PLFData.password);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
    spaceManagement.deleteSpace(space2, false);
    spaceManagement.deleteSpace(space3, false);
  }

  @Test
  public void test12_checkDisplayOfOtherSuggestionGadgetWhenUserAcceptSuggestionOfSpace() {
    space1 = "spaceA" + getRandomNumber();
    space2 = "spaceB" + getRandomNumber();
    space3 = "spaceC" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space3, space3);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    executeJavaScript("window.scrollBy(0,300)");
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1)).shouldNot(Condition.visible);
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space2)).hover();
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space2)).parent().find(ELEMENT_BUTTON_CONNECT_USER_FROM_GADGET).click();
    ELEMENT_GAGET_SUGGESTION_SPACE.find(byText(space1)).should(Condition.visible);
    manageLogInOut.signIn(username, PLFData.password);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
    spaceManagement.deleteSpace(space2, false);
    spaceManagement.deleteSpace(space3, false);
  }

}