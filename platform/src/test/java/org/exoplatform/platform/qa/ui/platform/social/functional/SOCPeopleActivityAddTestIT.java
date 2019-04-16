package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_AUTHOR_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_ALL_CONNECTIONS_TAB;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONNECTION_USER_NAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.UserPageBase;

@Tag("functional")
@Tag("social")

public class SOCPeopleActivityAddTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  ManageLogInOut        manageLogInOut;

  AddUsers              addUsers;

  HomePagePlatform      homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream        activityStream;

  IntranetNotification  intranetNotification;

  UserPageBase          userPageBase;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    intranetNotification = new IntranetNotification(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    userPageBase = new UserPageBase(this);
  }

  /**
   * <li>Case ID:122798.</li>
   * <li>Test Case Name: Add a share link.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /* Create data test */
  /*
   * Step Number: 1 Step Name: - Step Description: Step 1: Go to my profile page
   * Input Data: - Sign in system - Select Activities page on User Toolbar portlet
   * in the upper right corner of the screen Expected Outcome: - User activities
   * page is displayed. It focus on activity list
   */
  /*
   * Step number: 2 Step Name: - Step Description: Step 2: Share a link Input
   * Data: - Select activity in the left pane - Click on Attach icon - Enter a
   * valid URL - Hit Enter from keyboard or click on [Attach] button Expected
   * Outcome: Get information from link successfully. Some information are: -
   * Title of site - Description of site - URL user has entered - If it gets image
   * from link, there is a check box to allows display or not display image -
   * Close link: allow user closes the link shareOther user has not seen the link
   * share
   */

  /*
   * Step number: 3 Step Name: - Step Description: Step 3: Add share links on
   * activity list Input Data: - Enter chars into text box - Click on[Share]
   * button Expected Outcome: A link is shared with some text on activity. Other
   * user can view and click on shared link
   */
  @Test
  public void test01_AddAShareLink() {
    info("Test 1: Add a share link");

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
    connectionsManagement.verifyConnection(username1, true);
    String textDes = "textDesca" + getRandomNumber();
    String link = "www.google.com/";
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyActivities();
    activityStream.addActivity(textDes, link);
    $(byXpath(ELEMENT_ACTIVITY_TITLE.replace("${text}", textDes).replace("${file}", link))).waitUntil(Condition.visible,
                                                                                                      Configuration.timeout);
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    $(ELEMENT_ALL_CONNECTIONS_TAB).click();
    connectionsManagement.searchPeople(username1, null, null, null);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).click();
    userPageBase.goToActivityTab();
    $(byXpath(ELEMENT_ACTIVITY_AUTHOR_ACTIVITY.replace("${activityText}", link))).waitUntil(Condition.visible,
                                                                                            Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }
}
