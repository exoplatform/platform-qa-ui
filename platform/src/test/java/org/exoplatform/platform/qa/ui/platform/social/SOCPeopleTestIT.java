package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_INTRANET_NOTIFICATION_BELL;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_NOTIFICATION_DROPDOWN;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.core.PLFData;
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
@Tag("social")
@Tag("sniff")
public class SOCPeopleTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  AddUsers              addUsers;

  ManageLogInOut        manageLogInOut;

  ConnectionsManagement connectionsManagement;

  HomePagePlatform      homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:121886.</li>
   * <li>Test Case Name: Accept/Deny Invitation.</li>
   * <li>Case ID:121901.</li>
   * <li>Test Case Name: Received Invitations.</li> Step Number: 1 Step Name: Step
   * 1: Send invitation Step Description: - Login intranet home - Click on
   * Connections on the left panel - Click on Connect button to invite about 2
   * users - Login by invited users, go to My Connections/Requests Received - An
   * user click on Confirm button and another user Ignore the invitation Input
   * Data: Expected Outcome: - Display list of people - Invitation is sent to
   * user, Connect button is changed to Cancel Request - With user confirmed the
   * invitation, user becomes friend and user's name is displayed on user's
   * network list - With user ignored the invitation, User isn't displayed on
   * user's network list
   */

  @Test
  @Tag("SOC-5917")
  public void test06_AcceptDenyRecievedInvitation() throws Exception {
    info("AcceptDenyRecievedInvitation");
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";
    String comment="You are connected with "+username1+" "+username1;
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";
    String username5 = "usernamee" + getRandomString();
    String email5 = username5 + "@test.com";
    String connect = "Connect";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);

    manageLogInOut.signIn(username3, password);
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Click on Connect button to invite about 2 users");
    connectionsManagement.connectToAUser(username2);
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.ignoreConnection(username3);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username3 + " " + username3)).shouldNotBe(visible);

    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    info("Show all users on Social and user can send friend request to connect with other users");
    ELEMENT_CONTENT_PEOPLE.find(byText(username2 + " " + username2))
            .parent()
            .parent()
            .parent()
            .find(byText(connect))
            .should(Condition.exist);
    ELEMENT_CONTENT_PEOPLE.find(byText(username3 + " " + username3))
            .parent()
            .parent()
            .parent()
            .find(byText(connect))
            .should(Condition.exist);
    ELEMENT_CONTENT_PEOPLE.find(byText(username4 + " " + username4))
            .parent()
            .parent()
            .parent()
            .find(byText(connect))
            .should(Condition.exist);
    homePagePlatform.searchUsersPeople(username5);
    ELEMENT_CONTENT_PEOPLE.find(byText(username5 + " " + username5))
            .parent()
            .parent()
            .parent()
            .find(byText(connect))
            .should(Condition.exist);
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Click on Connect button to invite an user");
    connectionsManagement.connectToAUser(username2);
    $(ELEMENT_CONNECTION_CANCEL_BTN).waitUntil(Condition.appears, Configuration.timeout);
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.PENDING);
    $(ELEMENT_CONNECTION_CANCEL_BTN).waitUntil(Condition.appears, Configuration.timeout);

    info("Cancel request");
    connectionsManagement.cancelConnection(username2);

    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    info("Click on Connect button to invite about 2 users");
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);

    info("User can accept or deny invitaions");
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.RECEIVE);
    $(byXpath(ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username1))).waitUntil(Condition.visible,Configuration.timeout);
    homePagePlatform.goToConnections();
    connectionsManagement.verifyConnection(username1, false);
    info("Accept John'sinvitaion");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    info("Verify after accept");
    ELEMENT_CONNECTION_REVOVE_BTN.waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    sleep(2000);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().shouldHave(Condition.text(comment));
    $(ELEMENT_NOTIFICATION_DROPDOWN).findAll(byText(username1+" "+username1)).shouldHaveSize(1);
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).parent().shouldNotHave(text(username1 + " " + username1 + " wants to connect with you"));

    info("Login by invited users, go to My Connections/Requests Received and ignore invitation");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.ignoreConnection(username1);

    info("Verify after invitation");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    info("With user confirmed the invitation, user  becomes friend and user's name is displayed on user's network list ");
    connectionsManagement.verifyConnection(username2, true);
    info("With user ignored the invitation, User isn't displayed on user's network list");
    connectionsManagement.verifyConnection(username3, false);

    manageLogInOut.signIn(username2, password);
    info("Go to My Connections and Click Remove Connection button");
    homePagePlatform.goToConnections();
    connectionsManagement.removeConnection(username1);
    info("Verify after remove invitation");
    connectionsManagement.verifyConnection(username1, false);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);

  }

}
