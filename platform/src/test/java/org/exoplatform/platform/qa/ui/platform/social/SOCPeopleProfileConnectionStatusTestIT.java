package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Condition.hasText;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
@Tag("sniff")
@Tag("social")
public class SOCPeopleProfileConnectionStatusTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  AddUsers              addUsers;

  ManageLogInOut        manageLogInOut;

  HomePagePlatform      homePagePlatform;

  ConnectionsManagement connectionsManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }
  /**
   * <li>Case ID:122961.</li>
   * <li>Test Case Name: Check Connection Status between two users.</li>
   * <li>Pre-Condition: test1 and test2 have not connected yet</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : Go to profile
   * page and check connection buttons Step Description: - Login with test1 - Go
   * to test2 profile - Check the connection button displayed in the page Input
   * Data: Expected Outcome: - The portlet shows a button "Connect" Step number: 2
   * Step Name: Step 2: Send request to connect Step Description: - click on
   * button Connect Input Data: Expected Outcome: - The portlet shows a button
   * Cancel Request Step number: 3 Step Name: Step 3 : Check connection buttons
   * Step Description: - Login with test2 - Go to test1 profile - Check the
   * connection buttons displayed in the page Input Data: Expected Outcome: - The
   * portlet shows two buttons : * Accept Request * Deny Step number: 4 Step Name:
   * Step 4: Accept request Step Description: - click on Accept Request Input
   * Data: Expected Outcome: - The portlet shows a button Connected. - The button
   * is labeled Connected with a tick icon. Step number: 5 Step Name: Step 5 :
   * Mouseover on Connected Step Description: - Mouseover the button Connected
   * Input Data: Expected Outcome: - On mouse over, the button becomes Disconnect
   * : * The icon to a disconnect icon* The label to Disconnect
   */
  @Test
  @Tag("PLF-7947")
  public void test01_CheckAccept_Deny_ConnectionStatusBetweenTwoUsers() throws Exception {
    info("Test 1: Check Connection Status between two users");
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamea" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernameb" + getRandomString();
    String email4 = username4 + "@test.com";
    String password = "Aa123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);

    manageLogInOut.signIn(username1, password);
    info("goto user2's profile");
    homePagePlatform.goToConnections();
    $(ELEMENT_ALL_CONNECTIONS_TAB).click();
    connectionsManagement.searchPeople(username2, null, null, null);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2))).click();
    $(ELEMENT_ADD_CONNECT_PROFIL_STATUS).click();
    $(ELEMENT_CANCEL_CONNECT_PROFILE_STATUS).waitUntil(visible,Configuration.timeout);
    info("login as user 2");
    manageLogInOut.signIn(username2, password);
    info("goto user1's profile");
    homePagePlatform.goToConnections();
    $(ELEMENT_ALL_CONNECTIONS_TAB).click();
    connectionsManagement.searchPeople(username1, null, null, null);
    homePagePlatform.refreshUntil($(byText(username1 + " " + username1)), visible, 2000);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).click();
    $(ELEMENT_ACCEPT_CONNECT_PROFIL_STATUS).waitUntil(visible, Configuration.timeout);
    ELEMENT_ICON_CHAT_PROFIL_STATUS.waitUntil(visible,Configuration.timeout);
    $(ELEMENT_ACCEPT_CONNECT_PROFIL_STATUS).waitUntil(visible, Configuration.timeout);
    $(ELEMENT_DROPDOWN_DENY_PROFIL_STATUS).click();
    $(ELEMENT_DENY_PROFIL_STATUS).waitUntil(visible, Configuration.timeout);
    info("connect to user 1");
    $(ELEMENT_DROPDOWN_DENY_PROFIL_STATUS).click();
    $(ELEMENT_ACCEPT_ADD_USER_PROFIL_STATUS).click();
    $(ELEMENT_ACCEPT_ADD_USER_PROFIL_STATUS).waitUntil(not(visible), Configuration.openBrowserTimeoutMs);
    info("Verify not appears Connect button on user 2");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    $(ELEMENT_ALL_CONNECTIONS_TAB).click();
    connectionsManagement.searchPeople(username2, null, null, null);
    homePagePlatform.refreshUntil($(byText(username1 + " " + username1)), visible, 2000);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2))).click();
    $(ELEMENT_ACCEPT_ADD_USER_PROFIL_STATUS).waitUntil(not(visible), Configuration.timeout);
    info("Check Deny Connection Status Between Two Users");
    manageLogInOut.signIn(username3, password);
    info("goto user2's profile");
    homePagePlatform.goToConnections();
    $(ELEMENT_ALL_CONNECTIONS_TAB).click();
    connectionsManagement.searchPeople(username4, null, null, null);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username4))).click();
    $(ELEMENT_ADD_CONNECT_PROFIL_STATUS).waitUntil(visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_CANCEL_CONNECT_PROFILE_STATUS).waitUntil(visible,Configuration.timeout).waitUntil(hasText("Cancel Request"),Configuration.timeout);
    $(ELEMENT_CANCEL_REQUEST_PROFIL_STATUS).waitUntil(visible,Configuration.timeout);
    info("login as user 2");
    manageLogInOut.signIn(username4, password);
    info("goto user1's profile");
    homePagePlatform.goToConnections();
    $(ELEMENT_ALL_CONNECTIONS_TAB).click();
    connectionsManagement.searchPeople(username3, null, null, null);
    homePagePlatform.refreshUntil($(byText(username3 + " " + username3)), visible, 2000);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username3))).click();
    $(ELEMENT_ACCEPT_CONNECT_PROFIL_STATUS).waitUntil(visible, Configuration.timeout);
    info("Deny to connect user1");
    $(ELEMENT_DROPDOWN_DENY_PROFIL_STATUS).click();
    $(ELEMENT_DENY_PROFIL_STATUS).waitUntil(visible, Configuration.timeout);
    $(ELEMENT_CLICK_DENY_USER_PROFIL_STATUS).click();
    $(ELEMENT_ADD_CONNECT_PROFIL_STATUS).waitUntil(visible,Configuration.timeout).waitUntil(hasText("Connect"),Configuration.timeout);

    info("Delete users");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);

  }

}

