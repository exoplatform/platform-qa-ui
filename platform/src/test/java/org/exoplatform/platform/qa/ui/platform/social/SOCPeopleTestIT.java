package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_NOTIFICATION_DROPDOWN;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.core.PLFData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
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
  public void test01_AcceptRecievedInvitation() {
    info("test01_06_AcceptDenyRecievedInvitation");
    /* Create data test */
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

    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    info("Click on Connect button to invite about 2 users");
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);

    info("Test Case 01 + Test Case 06: User can accept or deny invitaions");
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);

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
  public void test06_AcceptDenyRecievedInvitation() {
    info("test01_06_AcceptDenyRecievedInvitation");
    /* Create data test */
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

    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    info("Click on Connect button to invite about 2 users");
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);

    info("Test Case 01 + Test Case 06: User can accept or deny invitaions");
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);

  }

  /**
   * <li>Case ID:121899.</li>
   * <li>Test Case Name: Network.</li> Step Number: 1 Step Name: Step 1: Open
   * Network list Step Description: - Login intranet home - Access people list,
   * invite an user - Invited user accept invitation - Go to My Connections -
   * Click Remove Connection button Input Data: Expected Outcome: - Display list
   * of people - Invitation is sent to user, Connect button is changed to Cancel
   * Request - With user confirmed the invitation, user becomes friend and user's
   * name is displayed on user's network list - With user ignored the invitation,
   * User isn't displayed on user's network list
   */
  @Test
  public void test02_Network() {
    info("test02_Network");
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

    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    info("Access people list, invite an user");
    connectionsManagement.connectToAUser(username2);

    info("Invited user accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    info("Verify after accept");
    ELEMENT_CONNECTION_REVOVE_BTN.should(Condition.exist);

    info("Go to My Connections and Click Remove Connection button");
    homePagePlatform.goToConnections();
    connectionsManagement.removeConnection(username1);
    info("Verify after remove invitation");
    connectionsManagement.verifyConnection(username1, false);

    info("User can re-add friend");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  /**
   * <li>Case ID:121907.</li>
   * <li>Test Case Name: View Connections list of other user.</li> Step Number: 1
   * Step Name: Step 1: View Connections list Step Description: - Click on name or
   * avatar - Click on My network tab Input Data: Expected Outcome: - Display
   * user's relation
   */

  @Test
  public void test03_ViewConnectionsListOfOtherUser() throws Exception {
    info("test03_ViewConnectionsListOfOtherUser");
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernameb" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);

    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    info("Click on Connect button to invite about 2 users");
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);

    info("Login by invited users, go to My Connections from avatar and accept invitation");
    info("Verify before accept John's invitaion");
    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToMyConnection();
    connectionsManagement.verifyConnection(username1, false);
    info("Accept John'sinvitaion");
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    info("Verify after accept John's invitaion");
    navigationToolbar.goToMyConnection();
    homePagePlatform.refreshUntil($(byText("Remove Connection")), Condition.exist, 2000);

    info("Login by invited users, go to My Connections/Requests Received and accept invitations");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);

    info("Login by John and check Connections by click on My Connections in Avartar");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyConnection();
    connectionsManagement.verifyConnection(username2, true);
    connectionsManagement.verifyConnection(username3, true);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }

  /**
   * <li>Case ID:121894.</li>
   * <li>Test Case Name: Check People listing.</li> Step Number: 1 Step Name: Step
   * 1: People listing Step Description: - Login and go to intranet home page -
   * Click on Connections on the left panel Input Data: Expected Outcome: - Show
   * all users on Social and user can send friend request to connect with other
   * users
   */
  @Test
  public void test04_CheckPeopleListing() {
    info("test04_CheckPeopleListing");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";
    String username5 = "usernamee" + getRandomString();
    String email5 = username5 + "@test.com";
    String password = "123456";
    String connect = "Connect";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
    manageLogInOut.signIn(username1, password);

    info("Click on Connections on the left panel");
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
    ELEMENT_CONTENT_PEOPLE.find(byText(username5 + " " + username5))
                          .parent()
                          .parent()
                          .parent()
                          .find(byText(connect))
                          .should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);
  }

  /**
   * <li>Case ID:121900.</li>
   * <li>Test Case Name: Pending Requests list.</li>
   * <li>Case ID:121901.</li>
   * <li>Test Case Name: Received Invitations</li> Step Number: 1 Step Name: Step
   * 1: Open Pending Requests list Step Description: - Login intranet home -
   * Access people list, invite an user - Go to My Connections/Requests Sent -
   * Click Cancel Request Input Data: Expected Outcome: - Display all user's
   * requests - Requested user is removed from list
   */
  @Test
  public void test05_PendingRequests() throws Exception {
    info("test05_PendingRequestsList");
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

    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Click on Connect button to invite an user");
    connectionsManagement.connectToAUser(username2);
    $(ELEMENT_CONNECTION_CANCEL_BTN).waitUntil(Condition.appears, Configuration.timeout);
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.PENDING);
    $(ELEMENT_CONNECTION_CANCEL_BTN).waitUntil(Condition.appears, Configuration.timeout);

    info("Cancel request");
    connectionsManagement.cancelConnection(username2);

    info("test06_ReceivedInvitations");
    homePagePlatform.goToConnections();
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.ALL);
    connectionsManagement.connectToAUser(username2);
    $(ELEMENT_CONNECTION_CANCEL_BTN).waitUntil(Condition.appears, Configuration.timeout);

    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.RECEIVE);
    $(byXpath(ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username1))).waitUntil(Condition.visible,Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }



  /*
   * Step Number: 1 Step Name: Step 1: Open Pending Requests list Step
   * Description: - Login intranet home - Access people list, invite an user - Go
   * to My Connections/Requests Sent - Click Cancel Request Input Data: Expected
   * Outcome: - Display all user's requests - Requested user is removed from list
   */
  @Test
  public void test06_RequestsList() throws Exception {
    info("test05_PendingRequestsList");
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

    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Click on Connect button to invite an user");
    connectionsManagement.connectToAUser(username2);
    $(ELEMENT_CONNECTION_CANCEL_BTN).waitUntil(Condition.appears, Configuration.timeout);
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.PENDING);
    $(ELEMENT_CONNECTION_CANCEL_BTN).waitUntil(Condition.appears, Configuration.timeout);

    info("Cancel request");
    connectionsManagement.cancelConnection(username2);

    info("test06_ReceivedInvitations");
    homePagePlatform.goToConnections();
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.ALL);
    connectionsManagement.connectToAUser(username2);
    $(ELEMENT_CONNECTION_CANCEL_BTN).waitUntil(Condition.appears, Configuration.timeout);

    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.RECEIVE);
    $(byXpath(ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username1))).waitUntil(Condition.visible,Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }
  @Tag("SOC-5917")
  @Test
  public void test07_checkNotificationsAfterAcceptInvitation(){
    info("test01_06_AcceptDenyRecievedInvitation");
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String comment="You are connected with "+username1+" "+username1;
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Click on Connect button to invite about 2 users");
    connectionsManagement.connectToAUser(username2);
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1+" "+username1)).parent().shouldHave(Condition.text(comment));
    $(ELEMENT_NOTIFICATION_DROPDOWN).findAll(byText(username1+" "+username1)).shouldHaveSize(1);
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).parent().shouldNotHave(text(username1 + " " + username1 + " wants to connect with you"));
    manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }
  @Tag("SOC-5917")
  @Test
  public void test08_checkNotificationsAfterRefuseInvitation(){
    info("test01_06_AcceptDenyRecievedInvitation");
    /* Create data test */
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
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Click on Connect button to invite about 2 users");
    connectionsManagement.connectToAUser(username2);
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.ignoreConnection(username1);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(username1 + " " + username1)).shouldNotBe(visible);
    manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }
}
