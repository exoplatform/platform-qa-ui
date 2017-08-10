package org.exoplatform.platform.qa.ui.social.smoke;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_ALL_CONNECTIONS_TAB;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.UserPageBase;

@Tag("smoke")
@Tag("social")
public class SOCPeopleActivityAddTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  AddUsers              addUsers;

  ManageLogInOut        manageLogInOut;

  HomePagePlatform      homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream        activityStream;

  UserPageBase          userPageBase;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    homePagePlatform = new HomePagePlatform(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    userPageBase = new UserPageBase(this);
  }

  /**
   * <li>Case ID:122785.</li>
   * <li>Test Case Name: Add new your activity.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @BugInPLF("SOC-5738")
  // this test case is disabled until resolving this bug:
  // https://jira.exoplatform.org/browse/SOC-5738
  public void test03_AddNewYourActivity() {
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
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
    refresh();
    connectionsManagement.verifyConnection(username1, true);

    info("Test 3: Add new your activity");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Go to my profile page
     * Input Data: - Sign in system - Select Activities page on User Toolbar portlet
     * in the upper right corner of the screen Expected Outcome: - Show content of
     * People page. It focus on activity list
     */
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyActivities();

    /*
     * Step number: 2 Step Name: - Step Description: Step 2: Add new activities
     * Input Data: - Select activity - Enter some text into text box - Click on Add
     * button Expected Outcome: Add an activity successfully: - This activity is
     * added into users activities list.User who is in your contact, can view your
     * active on his/her activity list
     */
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");

    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    $(ELEMENT_ALL_CONNECTIONS_TAB).click();
    connectionsManagement.searchPeople(username1, null, null, null);
    // click on the username to join his profile
    $(byClassName("uiTabNormal")).find(byClassName("limitText")).click();
    userPageBase.goToActivityTab();
    $(byText(activity1)).should(Condition.exist);
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

}
