package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.pageobject.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_CONNECTION_DISONNECT_REFUSE_BTN_DW;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_NOTIFICATION_DROPDOWN_DW;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("social")
@Tag("sniff")
public class SOCPeopleTestDWIT extends BaseDW {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  ConnectionsManagement connectionsManagement;

  HomePagePlatform homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
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
    navigationToolbar.goToAddUsersPageDW();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribe();

    info("Click on Connect button to invite about 2 users");
    connectionsManagement.tribeConnectToAUser(username2);
    connectionsManagement.tribeConnectToAUser(username3);

    info("Test Case 01 + Test Case 06: User can accept or deny invitaions");
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToPeoplePageTribe();
    connectionsManagement.acceptAConnectionDW(username1);

    info("Login by invited users, go to My Connections/Requests Received and ignore invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToPeoplePageTribe();
    connectionsManagement.ignoreConnectionDW(username1);

    info("Verify after invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToPeoplePageTribe();
    info("With user confirmed the invitation, user  becomes friend and user's name is displayed on user's network list ");
    connectionsManagement.verifyConnectionDW(username2, true, "My Connections");
    info("With user ignored the invitation, User isn't displayed on user's network list");
    connectionsManagement.verifyConnectionDW(username3, false, "My Connections");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageUsersPageDW();
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
    navigationToolbar.goToAddUsersPageDW();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribe();

    info("Access people list, invite an user");
    connectionsManagement.tribeConnectToAUser(username2);

    info("Invited user accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToPeoplePageTribe();
    connectionsManagement.acceptAConnectionDW(username1);
    info("Verify after accept");
    ELEMENT_CONNECTION_DISONNECT_REFUSE_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Go to My Connections and Click Remove Connection button");
    homePagePlatform.goToPeoplePageTribe();
    connectionsManagement.removeConnectionDW(username1);
    info("Verify after remove invitation");
    connectionsManagement.verifyConnectionDW(username1, false, "My Connections");

    info("User can re-add friend");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToPeoplePageTribe();
    connectionsManagement.tribeConnectToAUser(username2);
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageUsersPageDW();
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
    navigationToolbar.goToAddUsersPageDW();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribe();

    info("Click on Connect button to invite about 2 users");
    connectionsManagement.tribeConnectToAUser(username2);
    connectionsManagement.tribeConnectToAUser(username3);

    info("Login by invited users, go to My Connections from avatar and accept invitation");
    info("Verify before accept John's invitaion");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMyConnectionsFromHomePageDW();
    connectionsManagement.verifyConnectionDW(username1, false, "My Connections");
    info("Accept John'sinvitaion");
    homePagePlatform.goToPeoplePageTribe();
    connectionsManagement.acceptAConnectionDW(username1);
    info("Verify after accept John's invitaion");
    homePagePlatform.goToMyConnectionsFromHomePageDW();
    homePagePlatform.refreshUntil(ELEMENT_CONNECTION_DISONNECT_REFUSE_BTN_DW, Condition.exist, 2000);

    info("Login by invited users, go to My Connections/Requests Received and accept invitations");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToPeoplePageTribe();
    connectionsManagement.acceptAConnectionDW(username1);

    info("Login by John and check Connections by click on My Connections in Avartar");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMyConnectionsFromHomePageDW();
    connectionsManagement.verifyConnectionDW(username2, true, "My Connections");
    connectionsManagement.verifyConnectionDW(username3, true, "My Connections");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageUsersPageDW();
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
  @Tag("sabis")
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
    navigationToolbar.goToAddUsersPageDW();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribe();
    connectionsManagement.tribeSearchPeople(username2);
    info("Show all users on Social and user can send friend request to connect with other users");
    ($(By.id("peopleListBody"))).find(byText(username2 + " " + username2))
            .parent()
            .parent()
            .parent()
            .find(byText(connect))
            .should(Condition.exist);
    connectionsManagement.tribeSearchPeople(username3);
    ($(By.id("peopleListBody"))).find(byText(username3 + " " + username3))
            .parent()
            .parent()
            .parent()
            .find(byText(connect))
            .should(Condition.exist);
    connectionsManagement.tribeSearchPeople(username4);
    ($(By.id("peopleListBody"))).find(byText(username4 + " " + username4))
            .parent()
            .parent()
            .parent()
            .find(byText(connect))
            .should(Condition.exist);
    connectionsManagement.tribeSearchPeople(username5);
    ($(By.id("peopleListBody"))).find(byText(username5 + " " + username5))
            .parent()
            .parent()
            .parent()
            .find(byText(connect))
            .should(Condition.exist);
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageUsersPageDW();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);
  }

  @Tag("SOC-5917")
  @Test
  public void test05_CheckNotificationsAfterAcceptInvitation() {
    info("test01_06_AcceptDenyRecievedInvitation");
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String comment = "You are connected with " + username1 + " " + username1;
    info("Add user");
    navigationToolbar.goToAddUsersPageDW();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribe();
    info("Click on Connect button to invite about 2 users");
    connectionsManagement.tribeConnectToAUser(username2);
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToPeoplePageTribe();
    connectionsManagement.acceptAConnectionDW(username1);
    navigationToolbar.goToIntranetNotificationDW();
    $(ELEMENT_NOTIFICATION_DROPDOWN_DW).find(byText(username1 + " " + username1)).parent().shouldHave(Condition.text(comment));
    $(ELEMENT_NOTIFICATION_DROPDOWN_DW).findAll(byText(username1 + " " + username1)).shouldHaveSize(1);
    $(ELEMENT_NOTIFICATION_DROPDOWN_DW).find(byText(username1 + " " + username1)).parent().shouldNotHave(text(username1 + " " + username1 + " wants to connect with you"));
    navigationToolbar.closeNotificationsDW();
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageUsersPageDW();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Tag("SOC-5917")
  @Test
  public void test06_CheckNotificationsAfterRefuseInvitation() {
    info("test01_06_AcceptDenyRecievedInvitation");
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUsersPageDW();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribe();
    info("Click on Connect button to invite about 2 users");
    connectionsManagement.tribeConnectToAUser(username2);
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToPeoplePageTribe();
    connectionsManagement.ignoreConnectionDW(username1);
    navigationToolbar.goToIntranetNotificationDW();
    $(ELEMENT_NOTIFICATION_DROPDOWN_DW).find(byText(username1 + " " + username1)).shouldNotBe(visible);
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageUsersPageDW();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }
}
