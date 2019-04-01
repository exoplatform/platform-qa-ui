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

  /**
   * <li>Case ID:122756.</li>
   * <li>Test Case Name: Add Relation Activity for both connected user.</li>
   * <li>Case ID:122754.</li>
   * <li>Test Case Name: Update Add Relation Activity after connecting to another
   * user.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Send request Step
   * Description: - Connect to Intranet with the user A - Send an invitation to
   * connect to the user B Input Data:
   * <p>
   * Expected Outcome: Invitation is sent Step Number: 1 Step Name: Step
   * Description: - Connect to Intranet as user A - Accept invitation from user B
   * Input Data:
   * <p>
   * Expected Outcome: - A Relation activity is updated in the activity stream: it
   * displays thenumber of connections of the user + 1 - A comment is added with
   * the message "I'm now connected with user B"
   * <p>
   * Step number: 2 Step Name: Accept invitation Step Description: - Connect to
   * Intranet with the user B - Accept invitation Input Data:
   * <p>
   * Expected Outcome: - A Relation activity is added to the activity stream with
   * comment"I'm now connected with User A" Step number: 3 Step Name: Check
   * activity Step Description: - Come back to Intranet with the user A Input
   * Data:
   * <p>
   * Expected Outcome: - A Relation activity is added to the activity stream with
   * comment"I'm now connected with User B"
   */
  @Test
  public void test01_AddRelationActivityForBothConnectedUser() {
    info("Test 1: Add Relation Activity for both connected user");
    String activity1 = "I'm now connected with 1 user(s)";

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
    String comment1 = "I'm now connected with " + username1 + " " + username1;
    String comment2 = "I'm now connected with " + username2 + " " + username2;
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Access people list, invite an user");
    connectionsManagement.connectToAUser(username2);

    info("Test 4: Update Add Relation Activity after connecting to another user");
    info("Invited user accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    info("Verify after accept");
    connectionsManagement.verifyConnection(username1, true);
    navigationToolbar.goToMyActivities();
    $(byText(activity1)).parent().parent().find(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyActivities();
    $(byText(activity1)).parent().parent().find(byText(comment2)).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:122758.</li>
   * <li>Test Case Name: Delete a Relation activity from activity stream by its
   * user.</li>
   * <li>Pre-Condition: - A relation activity of user A is already shared</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Connect to
   * Intranet Step Description: - Connect to Intranet as user A Input Data:
   * <p>
   * Expected Outcome: The Relation activity is displayed in the activity stream
   * Step number: 2 Step Name: Step 2: Show [remove] icon Step Description: - Move
   * the mouse over the Relation activity Input Data:
   * <p>
   * Expected Outcome: A (X) icon is displayed in the top right panel of the
   * activity
   * <p>
   * Step number: 3 Step Name: Step 3: Click [remove] icon Step Description: -
   * Click on the (X) icon - Click on OK button of confirmation message Input
   * Data:
   * <p>
   * Expected Outcome: The Relation activity is removed from the activity stream
   */
  @Test
  public void test02_DeleteARelationActivityFromActivityStreamByItsUser() {

    info("Add new user");
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
    navigationToolbar.goToMyActivities();

    info("Test 2: Delete a Relation activity from activity stream by its user");
    $(byText(activity1)).parent().parent().find(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteGeneratedActivity(activity1);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:122759.</li>
   * <li>Test Case Name: Dislike a Relation activity from like icon.</li>
   * <li>Pre-Condition: - A Relation activity of user A is already shared - And
   * it's liked by the user A</li>
   * <li>Post-Condition:</li> *Step Number: 1 Step Name: Display Relation activity
   * Step Description: - Connect to Intranet with User A Input Data:
   * <p>
   * Expected Outcome: - The Relation activity is displayed in the activity
   * stream*like icon + number of likes to 1 Step number: 2 Step Name: Like
   * activity Step Description: - Click on like icon Input Data:
   * <p>
   * Expected Outcome: - The Relation activity is disliked by the user, the number
   * of like is updated to -1
   */
  @Test
  public void test03_DislikeARelationActivityFromLikeIcon() {

    info("Add new user");
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
    navigationToolbar.goToMyActivities();
    info("Test 3: Dislike a Relation activity from like icon");
    activityStream.likeActivity(activity1);

    activityStream.unlikeActivity(activity1);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:122756.</li>
   * <li>Test Case Name: Add Relation Activity for both connected user.</li>
   * <li>Case ID:122754.</li>
   * <li>Test Case Name: Update Add Relation Activity after connecting to another
   * user.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Send request Step
   * Description: - Connect to Intranet with the user A - Send an invitation to
   * connect to the user B Input Data:
   * <p>
   * Expected Outcome: Invitation is sent Step Number: 1 Step Name: Step
   * Description: - Connect to Intranet as user A - Accept invitation from user B
   * Input Data:
   * <p>
   * Expected Outcome: - A Relation activity is updated in the activity stream: it
   * displays thenumber of connections of the user + 1 - A comment is added with
   * the message "I'm now connected with user B"
   * <p>
   * Step number: 2 Step Name: Accept invitation Step Description: - Connect to
   * Intranet with the user B - Accept invitation Input Data:
   * <p>
   * Expected Outcome: - A Relation activity is added to the activity stream with
   * comment"I'm now connected with User A"
   */
  @Test
  public void test04_AddRelationActivityForBothConnectedUser() {
    info("Test 1: Add Relation Activity for both connected user");
    String activity1 = "I'm now connected with 1 user(s)";

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
    String comment2 = "I'm now connected with " + username2 + " " + username2;

    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Access people list, invite an user");
    connectionsManagement.connectToAUser(username2);

    info("Test 4: Update Add Relation Activity after connecting to another user");
    info("Invited user accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    info("Verify after accept");
    connectionsManagement.verifyConnection(username1, true);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyActivities();
    $(byText(activity1)).parent().parent().find(byText(comment2)).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyActivities();
    $(byText(activity1)).parent().parent().find(byText(comment2)).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

}
