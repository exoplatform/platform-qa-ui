package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
@Tag("sniff")
@Tag("social")
public class SOCPeopleProfileConnectionTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  ManageLogInOut        manageLogInOut;

  AddUsers              addUsers;

  ConnectionsManagement connectionsManagement;

  HomePagePlatform      homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    addUsers = new AddUsers(this);
    connectionsManagement = new ConnectionsManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signInCas(USER_ROOT, DATA_PASS);
  }

  /**
   * <li>Case ID:122970.</li>
   * <li>Test Case Name: Check my Connections section when no connection.</li>
   * <li>Pre-Condition: User A doesn't have any connection</li>
   * <li>Post-Condition:</li>
   * <li>Case ID:122971.</li>
   * <li>Test Case Name: Check the Connections of another user when no
   * connection.</li>
   * <li>Pre-Condition: User A doesn't have any connection</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : check Connections
   * in profile page Step Description: - Login - Go to profile page of User A
   * Input Data: Expected Outcome: - A new section Connections is added in the
   * right column of the page. - a message is displayed in the section : "You do
   * not have connections yet." - a link"Find connections" is displayed and
   * redirect to My Connections page, on everyone tab Step Number: 1 Step Name:
   * Step 1 : check Connections in profile page Step Description: - Login - Go to
   * profile page of User A Input Data: Expected Outcome: - A new section
   * Connections is added in the right column of the page. - a message is
   * displayed in the section : "This user does not have connections yet."
   */
  @Test
  public void test01_CheckMyConnectionsSectionWhenNoConnection() {
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    info("Test 1: Check my Connections section when no connection");
    String msg_me = "You do not have connections yet.";
    String msg_other = "This user does not have connections yet.";

    info("goto my profile's page");
    navigationToolbar.goToMyProfile();
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_me))).should(Condition.visible);
    $(ELEMENT_UIMINICONNECTIONS_PORTLET_FIND).should(Condition.visible);

    info("click on Find connections");
    click(ELEMENT_UIMINICONNECTIONS_PORTLET_FIND);
    $(ELEMENT_ALL_CONNECTIONS_TAB).waitUntil(Condition.visible, Configuration.timeout);

    info("Test 2: Check the Connections of another user when no connection");

    manageLogInOut.signIn(USER_ROOT, PASS_ROOT);
    homePagePlatform.goToConnections();
    click(ELEMENT_ALL_CONNECTIONS_TAB);
    connectionsManagement.searchPeople(username1, null, null, null);
    click(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1)));
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_other))).should(Condition.visible);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test02_CheckOthersSectionWhenNoConnection() {
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    info("Test 1: Check my Connections section when no connection");
    String msg_me = "You do not have connections yet.";
    String msg_other = "This user does not have connections yet.";
    info("goto my profile's page");
    navigationToolbar.goToMyProfile();
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_me))).should(Condition.visible);
    $(ELEMENT_UIMINICONNECTIONS_PORTLET_FIND).should(Condition.visible);

    info("click on Find connections");
    click(ELEMENT_UIMINICONNECTIONS_PORTLET_FIND);
    $(ELEMENT_ALL_CONNECTIONS_TAB).waitUntil(Condition.visible, Configuration.timeout);

    info("Test 2: Check the Connections of another user when no connection");
    manageLogInOut.signIn(USER_ROOT, PASS_ROOT);
    homePagePlatform.goToConnections();
    click(ELEMENT_ALL_CONNECTIONS_TAB);
    connectionsManagement.searchPeople(username1, null, null, null);
    click(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1)));
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_other))).should(Condition.visible);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:122955.</li>
   * <li>Test Case Name: Check my Connections section.</li>
   * <li>Pre-Condition: User A has 13 connections</li>
   * <li>Post-Condition:</li>
   * <li>Case ID:122956.</li>
   * <li>Test Case Name: Check the Connections section of another user.</li>
   * <li>Pre-Condition: User A has 3 connections</li>
   * <li>Post-Condition:</li> Issue: https://jira.exoplatform.org/browse/SOC-4789
   * Step Number: 1 Step Name: Step 1 : check Connections in profile page Step
   * Description: - Login - Go to profile page of User A Input Data: Expected
   * Outcome: - A new section Connections is added in the right column of the
   * page. - The section displays the last 12 connections of a user, with a
   * maximum of 4 users' avatar per row - A link View all (13) displayed at the
   * bottom of the section Step number: 3 Step Name: Step 3 : check hover effect
   * Step Description: - Mouseover one user's avatar Input Data: Expected Outcome:
   * - On hover of a user's avatar, the Generic User Popup is displayed. Step
   * number: 2 Step Name: Step 2 : CheckView All link Step Description: - Click
   * [View All (13)] Input Data: Expected Outcome: - The user is redirected to
   * User A's connections page
   */
  @Test
  public void test03_CheckMyConnectionsSection() {
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";

    String numberAllConnection = "13";

    ArrayList<String> userList = new ArrayList<String>();

    userList.add(getRandomString() + "a");
    userList.add(getRandomString() + "b");
    userList.add(getRandomString() + "c");
    userList.add(getRandomString() + "d");
    userList.add(getRandomString() + "e");
    userList.add(getRandomString() + "f");
    userList.add(getRandomString() + "g");
    userList.add(getRandomString() + "h");
    userList.add(getRandomString() + "i");
    userList.add(getRandomString() + "j");
    userList.add(getRandomString() + "k");
    userList.add(getRandomString() + "l");
    userList.add(getRandomString() + "m");

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    for (int i = 0; i < 13; i++) {
      String userName = userList.get(i);
      String email = userName + "@test.com";
      info("Add user " + userName);
      addUsers.addUser(userName, userName, email, userName, userName);
    }
    manageLogInOut.signIn(username1, password);

    info("Create pre-condition");
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    info("Click on Connect button to invite an user");
    for (int i = 0; i < 13; i++) {
      String userName = userList.get(i);
      info(userName + " connect request");
      connectionsManagement.connectToAUser(userName);
    }

    info("Click on Accept button");
    for (int i = 0; i < 13; i++) {
      String userName = userList.get(i);
      info(userName + " accept request");
      manageLogInOut.signIn(userName, userName);
      homePagePlatform.goToConnections();
      connectionsManagement.acceptAConnection(username1);
    }

    info("Test 3: Check my Connections section");

    info("login as user");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyProfile();
    info("Number of last 12 connections: "
        + String.valueOf(getElements(ELEMENT_UIMINICONNECTIONS_PORLET_NUMBER_CONNECTION).size()));
    for (int i = 12; i > 0; i--) {
      String userName = userList.get(i);
      ELEMENT_MINI_CONNECTION_PORTLET.find(byAttribute("alt", userName + " " + userName)).should(Condition.visible);
    }
    $(byAttribute("alt", userList.get(0))).shouldNot(Condition.visible);

    ELEMENT_MINI_CONNECTION_PORTLET.findAll(byClassName("avatarXSmall")).shouldHaveSize(12);
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}", numberAllConnection))).waitUntil(Condition.visible,
                                                                                                           Configuration.timeout);

    ELEMENT_MINI_CONNECTION_PORTLET.find(byAttribute("alt", userList.get(11) + " " + userList.get(11))).hover();
    $(byId("tiptip_content")).find(byText(userList.get(11) + " " + userList.get(11))).should(Condition.visible);

    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}", numberAllConnection))).click();
    waitForAndGetElement(ELEMENT_MY_CONNECTIONS_TAB);

    info("Test 4: Check the Connections section of another user.");
    manageLogInOut.signIn(USER_ROOT, PASS_ROOT);
    info("goto profile of user 1");
    homePagePlatform.goToConnections();
    click(ELEMENT_ALL_CONNECTIONS_TAB);
    connectionsManagement.searchPeople(username1, null, null, null);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).click();
    info("Number of last 12 connections: "
        + String.valueOf(getElements(ELEMENT_UIMINICONNECTIONS_PORLET_NUMBER_CONNECTION).size()));
    for (int i = 12; i > 0; i--) {
      String userName = userList.get(i);
      ELEMENT_MINI_CONNECTION_PORTLET.find(byAttribute("alt", userName + " " + userName)).should(Condition.visible);
    }
    ELEMENT_MINI_CONNECTION_PORTLET.find(byAttribute("alt", userList.get(0))).shouldNot(Condition.exist);

    ELEMENT_MINI_CONNECTION_PORTLET.findAll(byClassName("avatarXSmall")).shouldHaveSize(12);
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}", numberAllConnection))).waitUntil(Condition.visible,
                                                                                                           Configuration.timeout);

    ELEMENT_MINI_CONNECTION_PORTLET.find(byAttribute("alt", userList.get(11) + " " + userList.get(11))).hover();
    $(byId("tiptip_content")).find(byText(userList.get(11) + " " + userList.get(11))).should(Condition.visible);
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}", numberAllConnection))).click();
    $(ELEMENT_MY_CONNECTIONS_TAB).waitUntil(Condition.visible, Configuration.timeout);
    navigationToolbar.goToManageCommunity();
    for (int i = 12; i > 0; i--) {
      String userName = userList.get(i);
      addUsers.deleteUser(userName);
    }
  }

  @Test
  public void test04_CheckConnectionsSectionOfOtherUser() {
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";

    String numberAllConnection = "13";

    ArrayList<String> userList = new ArrayList<String>();

    userList.add(getRandomString() + "a");
    userList.add(getRandomString() + "b");
    userList.add(getRandomString() + "c");
    userList.add(getRandomString() + "d");
    userList.add(getRandomString() + "e");
    userList.add(getRandomString() + "f");
    userList.add(getRandomString() + "g");
    userList.add(getRandomString() + "h");
    userList.add(getRandomString() + "i");
    userList.add(getRandomString() + "j");
    userList.add(getRandomString() + "k");
    userList.add(getRandomString() + "l");
    userList.add(getRandomString() + "m");

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);

    for (int i = 0; i < 13; i++) {
      String userName = userList.get(i);
      String email = userName + "@test.com";
      info("Add user " + userName);
      addUsers.addUser(userName, userName, email, userName, userName);
    }
    manageLogInOut.signIn(username1, password);

    info("Create pre-condition");
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    info("Click on Connect button to invite an user");
    for (int i = 0; i < 13; i++) {
      String userName = userList.get(i);
      info(userName + " connect request");
      connectionsManagement.connectToAUser(userName);
    }

    info("Click on Accept button");
    for (int i = 0; i < 13; i++) {
      String userName = userList.get(i);
      info(userName + " accept request");
      manageLogInOut.signIn(userName, userName);
      homePagePlatform.goToConnections();
      connectionsManagement.acceptAConnection(username1);
    }

    info("Test 3: Check my Connections section");

    info("login as user");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyProfile();
    info("Number of last 12 connections: "
        + String.valueOf(getElements(ELEMENT_UIMINICONNECTIONS_PORLET_NUMBER_CONNECTION).size()));
    for (int i = 12; i > 0; i--) {
      String userName = userList.get(i);
      ELEMENT_MINI_CONNECTION_PORTLET.find(byAttribute("alt", userName + " " + userName)).should(Condition.visible);
    }
    $(byAttribute("alt", userList.get(0))).shouldNot(Condition.visible);

    ELEMENT_MINI_CONNECTION_PORTLET.findAll(byClassName("avatarXSmall")).shouldHaveSize(12);
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}", numberAllConnection))).waitUntil(Condition.visible,
                                                                                                           Configuration.timeout);

    ELEMENT_MINI_CONNECTION_PORTLET.find(byAttribute("alt", userList.get(11) + " " + userList.get(11))).hover();
    $(byId("tiptip_content")).find(byText(userList.get(11) + " " + userList.get(11))).should(Condition.visible);

    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}", numberAllConnection))).click();
    waitForAndGetElement(ELEMENT_MY_CONNECTIONS_TAB);

    info("Test 4: Check the Connections section of another user.");
    manageLogInOut.signIn(USER_ROOT, PASS_ROOT);
    info("goto profile of user 1");
    homePagePlatform.goToConnections();
    click(ELEMENT_ALL_CONNECTIONS_TAB);
    connectionsManagement.searchPeople(username1, null, null, null);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).click();
    info("Number of last 12 connections: "
        + String.valueOf(getElements(ELEMENT_UIMINICONNECTIONS_PORLET_NUMBER_CONNECTION).size()));
    for (int i = 12; i > 0; i--) {
      String userName = userList.get(i);
      ELEMENT_MINI_CONNECTION_PORTLET.find(byAttribute("alt", userName + " " + userName)).should(Condition.visible);
    }
    ELEMENT_MINI_CONNECTION_PORTLET.find(byAttribute("alt", userList.get(0))).shouldNot(Condition.exist);

    ELEMENT_MINI_CONNECTION_PORTLET.findAll(byClassName("avatarXSmall")).shouldHaveSize(12);
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}", numberAllConnection))).waitUntil(Condition.visible,
                                                                                                           Configuration.timeout);

    ELEMENT_MINI_CONNECTION_PORTLET.find(byAttribute("alt", userList.get(11) + " " + userList.get(11))).hover();
    $(byId("tiptip_content")).find(byText(userList.get(11) + " " + userList.get(11))).should(Condition.visible);
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}", numberAllConnection))).click();
    $(ELEMENT_MY_CONNECTIONS_TAB).waitUntil(Condition.visible, Configuration.timeout);
    navigationToolbar.goToManageCommunity();
    for (int i = 12; i > 0; i--) {
      String userName = userList.get(i);
      addUsers.deleteUser(userName);
    }
  }
}
