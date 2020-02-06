package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONNECTION_USER_NAME;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("sniff")
@Tag("social")
public class SOCPeopleProfileUserStatusTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  AddUsers              addUsers;

  ManageLogInOut        manageLogInOut;

  HomePagePlatform      homePagePlatform;

  ConnectionsManagement connectionsManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:122959.</li>
   * <li>Test Case Name: Check my Status when Chat is installed.</li>
   * <li>Pre-Condition: - The Chat add-on is installed in the platform</li>
   * <li>Post-Condition:</li>
   * <li>Case ID:122960.</li>
   * <li>Test Case Name: Check the Status of another when Chat is installed.</li>
   * <li>Pre-Condition: - User A and User B are created - The Chat add-on is
   * installed in the platform</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : Go to user
   * profile Step Description: - Login as user A - Go to User Menu > [My Profile]
   * Input Data: Expected Outcome: - The profile of User A is displayed - A user
   * status, symbolized by a round shape, is displayed next to the user name. The
   * status isn't clickable but a simple visual indication. Step number: 2 Step
   * Name: Step 2 : Check Away status Step Description: - Update chat status of
   * User A to Away - Refresh the page - Mouse over the status Input Data:
   * Expected Outcome: - A yellow dot is displayed next to the user full name -The
   * tooltip is "Away" Step number: 3 Step Name: Step 3 : Check Do not disturb
   * status Step Description: - Update chat status of User A to Do not disturb -
   * Refresh the page - Mouse over the status Input Data: Expected Outcome: - A
   * red dot is displayed next to the user full name -The tooltip is " Do not
   * disturb" Step number: 4 Step Name: Step 4 : Check Invisible status Step
   * Description: - Update chat status of User A to Invisible - Refresh the page -
   * Mouse over the status Input Data: Expected Outcome: - A grey dot is displayed
   * next to the user full name -The tooltip is "Offline"
   */
  @Test
  public void test03_04_CheckMyStatusWhenChatIsInstalled() {
    info("Test 3: Check my Status when Chat is installed");
    String iconAway = "away";
    String statusAway = "Away";
    String iconDisturb = "donotdisturb";
    String statusDisturb = "Do not disturb";
    String iconInvi = "invisible";
    String statusInvi = "Invisible";
    String iconOnline = "available";
    String statusOnline = "Available";
    String orangeColor = "rgba(253, 140, 64, 1)";
    String redColor = "rgba(199, 34, 34, 1)";
    String whiteColor = "rgba(153, 153, 153, 1)";
    String greenColor = "rgba(70, 165, 70, 1)";

    // Create data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToMyProfile();

    info("change status to Away");
    mouseOverAndClick(ELEMENT_CHAT_ICON);
    sleep(Configuration.timeout);
    $(byClassName(ELEMENT_CHAT_STATUS.replace("${status}", iconAway))).waitUntil(visible, Configuration.openBrowserTimeoutMs).click();
    info(ELEMENT_CHAT_UISTATUSPROFILEPORTLET.replace("${icon}", statusAway).replace("${status}", statusAway));
    homePagePlatform.refreshUntil($(ELEMENT_CHAT_ICON),visible,Configuration.timeout);
    $(ELEMENT_CHAT_ICON).find(byClassName(ELEMENT_CHAT_STATUS.replace("${status}", iconAway))).should(Condition.exist);
    assertEquals($(ELEMENT_CHAT_ICON).find(byClassName("uiIconStatus")).getCssValue("color"),
                 orangeColor);
    $(byClassName(ELEMENT_STATUS_CHAT_IN_PROFILE_PAGE.replace("{status}", statusAway))).should(visible);

    info("change status to Do not disturb");
    mouseOverAndClick(ELEMENT_CHAT_ICON);
    $(byClassName(ELEMENT_CHAT_STATUS.replace("${status}", iconDisturb))).click();
    homePagePlatform.refreshUntil($(ELEMENT_CHAT_ICON),visible,1000);
    $(ELEMENT_CHAT_ICON).find(byClassName(ELEMENT_CHAT_STATUS.replace("${status}", iconDisturb))).should(Condition.exist);
    assertEquals($(ELEMENT_CHAT_ICON).find(byClassName("uiIconStatus"))
                                     .getCssValue("color"),
                 redColor);
    $(byClassName(ELEMENT_STATUS_CHAT_IN_PROFILE_PAGE.replace("{status}", statusDisturb.replaceAll(" ", "")))).should(visible);

    info("change status to Offline");
    mouseOverAndClick(ELEMENT_CHAT_ICON);
    $(byClassName(ELEMENT_CHAT_STATUS.replace("${status}", iconInvi))).click();
    homePagePlatform.refreshUntil($(ELEMENT_CHAT_ICON),visible,1000);
    $(ELEMENT_CHAT_ICON).find(byClassName(ELEMENT_CHAT_STATUS.replace("${status}", iconInvi))).should(Condition.exist);
    assertEquals($(ELEMENT_CHAT_ICON).find(byClassName("uiIconStatus")).getCssValue("color"),
                 whiteColor);
    $(byClassName(ELEMENT_STATUS_CHAT_IN_PROFILE_PAGE.replace("{status}", statusInvi))).should(visible);
    info("restore data");
    info("change status to Online");
    mouseOverAndClick(ELEMENT_CHAT_ICON);
    $(byClassName(ELEMENT_CHAT_STATUS.replace("${status}", iconOnline))).click();
    homePagePlatform.refreshUntil($(ELEMENT_CHAT_ICON),visible,1000);
    assertEquals($(ELEMENT_CHAT_ICON).find(byClassName("uiIconStatus"))
                                     .getCssValue("color"),
                 greenColor);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test04_CheckOtherStatusWhenChatIsInstalled() throws Exception {
    info("Test 3: Check my Status when Chat is installed");

    // Create data test
    String username1 = "usernameb" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";
    String statusOnline = "Offline";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.searchPeople(username1, null, null, null);
    homePagePlatform.refreshUntil($(byText(username1 + " " + username1)), visible, 2000);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).click();
    $(byClassName(ELEMENT_STATUS_CHAT_IN_PROFILE_PAGE.replace("{status}", statusOnline))).should(visible);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }
}
