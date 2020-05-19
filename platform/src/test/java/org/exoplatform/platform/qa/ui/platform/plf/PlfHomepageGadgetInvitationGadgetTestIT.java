package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.QuickSearchResultLocator.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import com.codeborne.selenide.Configuration;
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
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
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
  public void test01_CheckDisplayInvitationGadgetThenAcceptRefuseARequest() {
    info("prepare data");

    info("Create datatest");
    String usernamea = "usernamea" + getRandomString();
    String password = "123456";
    String email1 = usernamea + "@test.com";
    String usernameb = "usernameb" + getRandomString();
    String email2 = usernameb + "@test.com";
    String usernamec = "usernamec" + getRandomString();
    String email3 = usernamec + "@test.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@test.com";
    String usernamed = "usernamed" + getRandomString();
    String email5 = usernamed + "@test.com";
    String usernamee = "usernamee" + getRandomString();
    String email6 = usernamee + "@test.com";

    info("Go to user profile");
    String title = "title" + getRandomNumber();
    homePagePlatform.goToUserProfile();
    $(ELEMENT_EDIT_PROFILE).waitUntil(Condition.appears, Configuration.timeout).click();
    $(ELEMENT_TITLE_INPUT).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_TITLE_INPUT).waitUntil(Condition.appears, Configuration.timeout).setValue(title);
    $(ELEMENT_BUTTON_SAVE).waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
    $(ELEMENT_EDIT_PROFILE).waitUntil(Condition.visible, Configuration.timeout + Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_TITLE_INPUT).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue("");
    $(ELEMENT_BUTTON_SAVE).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).doubleClick();
    executeJavaScript("window.scrollBy(0,150)");
    $(ELEMENT_BUTTON_SAVE).waitUntil(Condition.not(Condition.visible),Configuration.collectionsTimeout);
    $(ELEMENT_ABOUT_ME).shouldNot(Condition.visible);

    /* Create data test */
    info("Add new user");
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(usernamea, password, email1, usernamea, usernamea);
    userAddManagement.addUser(usernameb, password, email2, usernameb, usernameb);
    userAddManagement.addUser(usernamec, password, email3, usernamec, usernamec);
    userAddManagement.addUser(usernamed, password, email5, usernamed, usernamed);
    userAddManagement.addUser(usernamee, password, email6, usernamee, usernamee);
    userAddManagement.addUser(username4, password, email4, username4, username4);
    info("--Send request 2 to John");
    info("Sign in with username1 account");
    manageLogInOut.signIn(usernamea, password);
    info("Not show Invitation gadget");
    waitForElementNotPresent(ELEMENT_INVITATIONS_GADGET);

    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username4);

    info("Sign in with username2 account");
    manageLogInOut.signIn(usernameb, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username4);

    info("Sign in with username3 account");
    manageLogInOut.signIn(usernamed, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username4);

    manageLogInOut.signIn(username4, password);
    info("Verify that the maximum number of displayed requests is 4");
    info("Verify that for user request, the portlet will displayes the profile picture of the user, his name");

    ELEMENT_GADGET_INVITATION.find(byText(usernamea + " " + usernamea)).should(Condition.exist);
    ELEMENT_GADGET_INVITATION.find(byText(usernameb + " " + usernameb)).should(Condition.exist);
    ELEMENT_GADGET_INVITATION.find(byText(usernamed + " " + usernamed)).should(Condition.exist);

    info("Accept a Request");
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_GADGET_INVITATION.find(byText(usernameb + " " + usernameb)).parent().hover();
    ELEMENT_GADGET_INVITATION.find(byText(usernameb + " " + usernameb)).parent().parent().find(ELEMENT_BUTTON_CONNECT_USER_FROM_GADGET).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_GADGET_INVITATION.find(byText(usernameb + " " + usernameb)).shouldNot(Condition.exist);
    ELEMENT_GADGET_INVITATION.find(byText(usernamea + " " + usernamea)).should(Condition.exist);

    info("Sign in with username4 account");
    manageLogInOut.signIn(usernamec, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username4);

    info("Sign in with username5 account");
    manageLogInOut.signIn(usernamee, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username4);

    manageLogInOut.signIn(username4, password);
    navigationToolbar.goToQuickSearch();
    $(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_GADGET_INVITATION.find(byText(usernamee + " " + usernamee)).parent().hover();
    ELEMENT_GADGET_INVITATION.find(byText(usernamee + " " + usernamee)).parent().parent().find(byClassName("uiIconClose")).click();
    ELEMENT_GADGET_INVITATION.find(byText(usernamee + " " + usernamee)).shouldNot(Condition.exist);
    ELEMENT_GADGET_INVITATION.find(byText(usernamec + " " + usernamec)).should(Condition.exist);

    info("Delete DATA for the last test");
    manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(usernamea);
    userAndGroupManagement.deleteUser(usernameb);
    userAndGroupManagement.deleteUser(usernamec);
    userAndGroupManagement.deleteUser(usernamed);
    userAndGroupManagement.deleteUser(usernamee);
    userAndGroupManagement.deleteUser(username4);

  }

}
