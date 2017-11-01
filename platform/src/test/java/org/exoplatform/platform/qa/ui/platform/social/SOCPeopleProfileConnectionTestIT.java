package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_ALL_CONNECTIONS_TAB;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONNECTION_USER_NAME;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_UIMINICONNECTIONS_PORTLET_FIND;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

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
   */
  /**
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
  public void test01_02_CheckMyConnectionsSectionWhenNoConnection() {
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

}
