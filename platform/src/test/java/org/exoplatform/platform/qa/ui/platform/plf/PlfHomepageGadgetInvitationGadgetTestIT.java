package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;

/**
 * @author eXo
 */
@Tag("sniff")
@Tag("plf")
public class PlfHomepageGadgetInvitationGadgetTestIT extends Base {
  ManageLogInOut         manageLogInOut;

  NavigationToolbar      navigationToolbar;

  UserAddManagement      userAddManagement;

  ConnectionsManagement  connectionsManagement;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  HomePagePlatform       homePagePlatform;

  UserAndGroupManagement userAndGroupManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    userAddManagement = new UserAddManagement(this);
    connectionsManagement = new ConnectionsManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(username, password);
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
  }

  /**
   * <li>Case ID:120861.</li>
   * <li>Test Case Name: Not show Invitation gadget.</li>
   * <li>Pre-Condition: - UserA does not receive any invitation from user or
   * space</li> Step Number: 1 Step Name: - Check if no invitation Step
   * Description: - Login as UserA - Go to intranet home page Input Data: Expected
   * Outcome: This gadget is not shown
   */
  @Test
  public void test01_NotShowInvitationGadget() {
    info("Test 01: Not show Invitation gadget");

    waitForElementNotPresent(ELEMENT_INVITATIONS_GADGET);
  }

  /**
   * <li>Case ID:120854.</li>
   * <li>Test Case Name: Check display of Invitation Gadget.</li>
   * <li>Pre-Condition: - There are 3 connection request are sent to userA - There
   * are 2 invitation form space are sent to userA</li> Step Number: 1 Step Name:
   * Check display gadget Step Description: - Login as userA - Open intranet home
   * - Check Invitation gadget Input Data: Expected Outcome: - A maximum number of
   * displayed requests is 4 -The oldest request will appear at the bottom while
   * the newest will appear on the top - For user request, the gadget displays the
   * profile picture of the user, his name and if available, his title. - For
   * Spaces request, the gadget displays the space icon, the name, privacy status
   * which is private or public, and the number of members in the space. - The
   * title of the gadget will show the total number of requests received which is
   * 4
   */
  @Test
  public void test02_CheckDisplayInvitationGadget() {
    info("prepare data");

    info("Create datatest");
    String usernamea = "usernamea" + getRandomString();
    String password = "123456";
    String email1 = usernamea + "@test.com";
    String usernameb = "usernameb" + getRandomString();
    String email2 = usernameb + "@test.com";
    String usernamec = "usernamec" + getRandomString();
    String email3 = usernamec + "@test.com";

    /* Create data test */
    info("Add new user");
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(usernamea, password, email1, usernamea, usernamea);
    userAddManagement.addUser(usernameb, password, email2, usernameb, usernameb);
    userAddManagement.addUser(usernamec, password, email3, usernamec, usernamec);

    info("--Send request 2 to John");
    info("Sign in with username1 account");
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username);

    info("Sign in with username2 account");
    manageLogInOut.signIn(usernameb, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username);

    info("Sign in with username3 account");
    manageLogInOut.signIn(usernamec, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username);

    manageLogInOut.signIn(username, PLFData.password);
    info("Verify that the maximum number of displayed requests is 4");
    info("Verify that for user request, the portlet will displayes the profile picture of the user, his name");

    ELEMENT_GADGET_INVITATION.find(byText(usernamea + " " + usernamea)).should(Condition.exist);
    ELEMENT_GADGET_INVITATION.find(byText(usernameb + " " + usernameb)).should(Condition.exist);
    ELEMENT_GADGET_INVITATION.find(byText(usernamec + " " + usernamec)).should(Condition.exist);
    info("Delete DATA for the last test");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(usernamea);
    userAndGroupManagement.deleteUser(usernameb);
    userAndGroupManagement.deleteUser(usernamec);
  }

  /**
   * <li>Case ID:120855.</li>
   * <li>Test Case Name: Accept a request.</li>
   * <li>Pre-Condition: - Add users (root, mary) - Root sends request to connect
   * to John - Mary sends connection request to John</li> Step Number: 1 Step
   * Name: Accept a request Step Description: - Login as John - Open intranet
   * homepage - On this gadget, mouse over an invitation of mary - Click on Accept
   * button Input Data: /*Expected Outcome: - The invitation of root, mary is
   * shown on the invitation gadget - The Accept and Refuse button are displayed.
   * - John is connected to mary and the invitation fades out and is permanently
   * removed from the list - Request of root are moving to the top of the gadget
   * if needed
   */
  @Test
  public void test03_AcceptARequest() {
    info("Test03: Accept a Request");
    info("prepare data");
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String password = "123456";
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    info("Add new user");
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username1, password, email1, username1, username1);
    userAddManagement.addUser(username2, password, email2, username2, username2);

    info("Sign in with mary account");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username);

    info("--Send request 2 to John");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username);

    manageLogInOut.signIn(username, PLFData.password);
    ELEMENT_GADGET_INVITATION.find(byText(username2 + " " + username2)).hover();
    ELEMENT_GADGET_INVITATION.find(ELEMENT_BUTTON_CONNECT_USER_FROM_GADGET).click();
    ELEMENT_GADGET_INVITATION.find(byText(username2 + " " + username2)).shouldNot(Condition.exist);
    ELEMENT_GADGET_INVITATION.find(byText(username1 + " " + username1)).should(Condition.exist);

    info("Clear Data");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username1);
    userAndGroupManagement.deleteUser(username2);
  }

  /**
   * <li>Case ID:120856.</li>
   * <li>Test Case Name: Refuse a request.</li>
   * <li>Pre-Condition: - Root sends connection request to mary - John sends
   * connection request to mary</li> Step Number: 1 Step Name: Refuse a request
   * Step Description: - Login as mary - Open intranet homepage - On this gadget,
   * mouse over an invitation of Jack(demo) - Click on Refuse icon Input Data:
   * Expected Outcome: - Invitations of root, james are displayed on Invitation
   * gadget - The Accept and Refuse button are displayed. - The invitation of jack
   * fades out and is permanently removed from the list - Requests of James is
   * moved to the top of the gadget
   */
  @Test
  public void test04_RefuseARequest() {
    info("Test 04: Refuse a request");
    info("prepare data");
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String password = "123456";
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    info("Add new user");
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username1, password, email1, username1, username1);
    userAddManagement.addUser(username2, password, email2, username2, username2);

    info("Sign in with mary account");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username);

    info("--Send request 2 to John");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username);

    info("Sign in with ROOT account");
    manageLogInOut.signIn(username, PLFData.password);
    ELEMENT_GADGET_INVITATION.find(byText(username2 + " " + username2)).hover();
    ELEMENT_GADGET_INVITATION.find(byClassName("uiIconClose")).click();
    ELEMENT_GADGET_INVITATION.find(byText(username2 + " " + username2)).shouldNot(Condition.exist);
    ELEMENT_GADGET_INVITATION.find(byText(username1 + " " + username1)).should(Condition.exist);

    info("Clear Data");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username1);
    userAndGroupManagement.deleteUser(username2);
  }
}
