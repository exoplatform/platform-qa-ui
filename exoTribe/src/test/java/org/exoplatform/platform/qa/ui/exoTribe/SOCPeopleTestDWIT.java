package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_CONNECTION_DISONNECT_REFUSE_BTN_DW;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_NOTIFICATION_DROPDOWN_DW;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("social")
@Tag("sniff")
@Tag("dw")
public class SOCPeopleTestDWIT extends BaseTribe {
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
    manageLogInOut.signInTribe(tribe_username1, tribe_password1);

  }

  @Test
  public void test01_AcceptRecievedInvitation() {
    info("Accept Deny Recieved Invitation");

    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribeViaUrl();

    info("Click on Connect button to invite about 2 users");
    connectionsManagement.tribeConnectToAUser(tribe_user2);
    connectionsManagement.tribeConnectToAUser(tribe_user3);

    info("User can accept or deny invitaions");
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username2, tribe_password2);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.acceptAConnectionDW(tribe_user1);

    info("Login by invited users, go to My Connections/Requests Received and ignore invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username3, tribe_password3);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.ignoreConnectionDW(tribe_user1);

    info("Verify after invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username1, tribe_password1);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    info("With user confirmed the invitation, user  becomes friend and user's name is displayed on user's network list ");
    connectionsManagement.verifyConnectionDW(tribe_user2, true, "My Connections");
    info("With user ignored the invitation, User isn't displayed on user's network list");
    connectionsManagement.verifyConnectionDW(tribe_user3, false, "My Connections");
    connectionsManagement.removeConnectionDW(tribe_user2);

  }

  @Test
  public void test02_Network() {

    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribeViaUrl();

    info("Access people list, invite an user");
    connectionsManagement.tribeConnectToAUser(tribe_user2);

    info("Invited user accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username2, tribe_password2);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.acceptAConnectionDW(tribe_user1);
    info("Verify after accept");
    ELEMENT_CONNECTION_DISONNECT_REFUSE_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Go to My Connections and Click Remove Connection button");
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.removeConnectionDW(tribe_user1);
    info("Verify after remove invitation");
    connectionsManagement.verifyConnectionDW(tribe_user1, false, "My Connections");

    info("User can re-add friend");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username1, tribe_password1);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.tribeConnectToAUser(tribe_user2);
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username2, tribe_password2);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.ignoreConnectionDW(tribe_user1);

  }

  @Test
  public void test03_ViewConnectionsListOfOtherUsers() throws Exception {
    info("View Connections List Of Other User");

    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribeViaUrl();

    info("Click on Connect button to invite about 2 users");
    connectionsManagement.tribeConnectToAUser(tribe_user2);
    connectionsManagement.tribeConnectToAUser(tribe_user3);

    info("Login by invited users, go to My Connections from avatar and accept invitation");
    info("Verify before accept User1's invitaion");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username2, tribe_password2);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    homePagePlatform.goToMyConnectionsFromHomePageDW();
    connectionsManagement.verifyConnectionDW(tribe_user1, false, "My Connections");
    info("Accept User1's invitaion");
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.acceptAConnectionDW(tribe_user1);
    info("Verify after accept User1's invitaion");
    homePagePlatform.goToPeoplePageTribeViaUrl();
    homePagePlatform.goToMyConnectionsFromHomePageDW();
    homePagePlatform.refreshUntil(ELEMENT_CONNECTION_DISONNECT_REFUSE_BTN_DW, Condition.exist, 2000);

    info("Login by invited users, go to My Connections/Requests Received and accept invitations");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username3, tribe_password3);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.acceptAConnectionDW(tribe_user1);

    info("Login by User1 and check Connections by click on My Connections in Avartar");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username1, tribe_password1);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    homePagePlatform.goToMyConnectionsFromHomePageDW();
    connectionsManagement.verifyConnectionDW(tribe_user2, true, "My Connections");
    connectionsManagement.verifyConnectionDW(tribe_user3, true, "My Connections");
    connectionsManagement.removeConnectionDW(tribe_user2);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.removeConnectionDW(tribe_user3);

  }

  @Test
  public void test04_CheckPeopleListing() {

    String connect = "Connect";

    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.tribeSearchPeople(tribe_user);
    info("Show all users on Social and user can send friend request to connect with other users");
    ($(By.id("peopleListBody"))).find(byText(tribe_user))
            .parent()
            .parent()
            .parent()
            .find(byText(connect))
            .should(Condition.exist);
    connectionsManagement.tribeSearchPeople(tribe_user3);
    ($(By.id("peopleListBody"))).find(byText(tribe_user3))
            .parent()
            .parent()
            .parent()
            .find(byText(connect))
            .should(Condition.exist);
    connectionsManagement.tribeSearchPeople(tribe_user4);
    ($(By.id("peopleListBody"))).find(byText(tribe_user4))
            .parent()
            .parent()
            .parent()
            .find(byText(connect))
            .should(Condition.exist);

  }

  @Test
  public void test05_CheckNotificationsAfterAcceptInvitation() {

    String comment = "You are connected with " + tribe_user;

    manageLogInOut.signInTribe(tribe_username, tribe_password);
    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribeViaUrl();
    info("Click on Connect button to invite about 2 users");
    connectionsManagement.tribeConnectToAUser(tribe_user3);
    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username3, tribe_password3);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.acceptAConnectionDW(tribe_user);
    navigationToolbar.goToIntranetNotificationDW();
    $(ELEMENT_NOTIFICATION_DROPDOWN_DW).find(byText(tribe_user)).parent().shouldHave(Condition.text(comment));
    $(ELEMENT_NOTIFICATION_DROPDOWN_DW).findAll(byText(tribe_user)).shouldHaveSize(1);
    $(ELEMENT_NOTIFICATION_DROPDOWN_DW).find(byText(tribe_user)).parent().shouldNotHave(text(tribe_user1 + " wants to connect with you"));
    navigationToolbar.removeFirstNotificationDW();
    navigationToolbar.closeNotificationsDW();
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username, tribe_password);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    homePagePlatform.goToMyConnectionsFromHomePageDW();
    connectionsManagement.verifyConnectionDW(tribe_user3, true, "My Connections");
    connectionsManagement.removeConnectionDW(tribe_user3);

  }

}
