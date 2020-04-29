package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("functional")
@Tag("social")
public class SOCPeopleActivityRelationActivityTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  ManageLogInOut        manageLogInOut;

  ConnectionsManagement connectionsManagement;

  AddUsers              addUsers;

  HomePagePlatform      homePagePlatform;

  ActivityStream        activityStream;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
  }

  @Test
  public void test01_AddRelationActivityForBothConnectedUser() {

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
    String activity1 = "I'm now connected with 1 user(s)";

    String comment1 = "I'm now connected with " + username1 + " " + username1;
    String comment2 = "I'm now connected with " + username2 + " " + username2;

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
    homePagePlatform.goToHomePage();
    $(byText(activity1)).parent().parent().find(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);

    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyActivities();
    $(byText(activity1)).parent().parent().find(byText(comment2)).waitUntil(Condition.visible, Configuration.timeout);

    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToMyActivities();
    info("Dislike a Relation activity from like icon");
    activityStream.likeActivity(activity1);
    activityStream.unlikeActivity(activity1);

    info("Delete a Relation activity from activity stream by its user");
    $(byText(activity1)).parent().parent().find(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteGeneratedActivity(activity1);

    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyActivities();
    $(byText(activity1)).parent().parent().find(byText(comment2)).waitUntil(Condition.visible, Configuration.timeout);

    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

}
