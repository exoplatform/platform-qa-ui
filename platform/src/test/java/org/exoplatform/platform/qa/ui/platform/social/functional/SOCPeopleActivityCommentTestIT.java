package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
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
public class SOCPeopleActivityCommentTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  ManageLogInOut        manageLogInOut;

  ActivityStream        activityStream;

  HomePagePlatform      homePagePlatform;

  ConnectionsManagement connectionsManagement;

  AddUsers              addUsers;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    activityStream = new ActivityStream(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:122799.</li>
   * <li>Test Case Name: Comment on your activity.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Add new activities Input Data: - Sign in system - Select Activities page
   * on User Toolbar portlet in the upper right corner of the screen - Select
   * activity in the left pane - Enter some text into text box - Click on [Share]
   * button Expected Outcome: Add an activity successfully - This activity is
   * added into users activities list.User who is in your contact, can view your
   * active on his/her activity list Step number: 2 Step Name: - Step Description:
   * Step 2: Show comment form Input Data: - Click on Comment link under the
   * activity Expected Outcome: Show a text box allow user add comment for
   * activity Step number: 3 Step Name: - Step Description: Step 3: Add a comment
   * Input Data: - Enter some text into text box comment - Click on Comment button
   * Expected Outcome: Add a comment successfully. Other user can view activity
   * and comment of one. With each comment, some information is display: - Avatar
   * of user comment - Name of user comment - Content of comment - Time comment is
   * posted
   */
  @Test
  public void test01_CommentOnYourActivity() {
    info("Test 1: Comment on your activity");
    info("Add new user");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    String comment = "comment" + getRandomNumber();
    activityStream.commentActivity(activity1, comment);
    $(byText(activity1)).parent().parent().find(byText(comment)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:122792.</li>
   * <li>Test Case Name: Comment on your friends activity.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Create your comment ( browser 1) Input Data: - Login by user AAA - Go to
   * My profile - Create new activities Expected Outcome: Activity is added
   * successfully Step number: 2 Step Name: - Step Description: Step 2: Go to
   * people page Input Data: - Select people page Expected Outcome: - Show content
   * of People page Step number: 3 Step Name: - Step Description: Step 3: Invite
   * user Input Data: - Select user who has no relation with user to add relation
   * - Click on [Invite] (BBB) Expected Outcome: Invited user successfully Step
   * number: 4 Step Name: - Step Description: Step 4: Accept the friend ( browser
   * 2) Input Data: - Sign in with user which invited at step 2 (BBB) - Select
   * people page - Click on button [accept] Expected Outcome: - After the user
   * clicks on [accept], the relation between two users has set. The user will be
   * added into users relations user list. - By side each the user, has a [remove]
   * button to user can remove from this relation.=> Two user become friend Step
   * number: 5 Step Name: - Step Description: Step 5: Add a comment Input Data: -
   * Go to my profile and click comment button in AAA's activities Expected
   * Outcome: Show comment successfully
   */
  @Test
  public void test02_CommentOnYourFriendsActivity() {
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

    info("Test 2: Comment on your friends activity");
    navigationToolbar.goToMyActivities();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
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
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.ALL);
    connectionsManagement.goToUserByFullName(username1 + " " + username1);
    homePagePlatform.goToHomePage();
    String comment = "comment" + getRandomNumber();
    activityStream.commentActivity(activity1, comment);
    $(byText(activity1)).parent().parent().find(byText(comment)).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(username1, password);
    activityStream.deleteactivity(activity1);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:122787.</li>
   * <li>Test Case Name: Delete comment.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Add new activities Input Data: - Sign in system - Select Activities page
   * on User Toolbar portlet in the upper right corner of the screen - Select
   * activity in the left pane - Enter some text into text box - Click on [Share]
   * button Expected Outcome: Add an activity successfully: - This activity is
   * added into users activities list.User who is in your contact, can view your
   * active on his/her activity list Step number: 2 Step Name: - Step Description:
   * Step 2: Add a comment Input Data: - Click on comment link under the activity
   * - Enter some text into text box comment - Click on Comment button - Add more
   * comments Expected Outcome: Add an activity successfully: - This activity is
   * added into users activities list.User who is in your contact, can view your
   * active on his/her activity list Step number: 3 Step Name: - Step Description:
   * Step 3: Delete comment Input Data: - Select an comment - Click on Delete -
   * Click OK to confirm deleting Expected Outcome: This comment is deleted.
   */
  @Test
  public void test03_DeleteComment() {
    info("Test 3: Delete comment");
    info("Add new user");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    String comment = "comment" + getRandomNumber();
    activityStream.commentActivity(activity1, comment);
    $(byText(activity1)).parent().parent().find(byText(comment)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1);
    $(byText(activity1)).parent()
                        .parent()
                        .find(byText(comment))
                        .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:122794.</li>
   * <li>Test Case Name: Show/hide all comment.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Add new activities Input Data: - Sign in system - Select Activities page
   * on User Toolbar portlet in the upper right corner of the screen - Select
   * activity in the left pane - Enter some text into text box - Click on [Share]
   * button Expected Outcome: Add an activity successfully: - This activity is
   * added into users activities list.User who is in your contact, can view your
   * active on his/her activity list Step number: 2 Step Name: - Step Description:
   * Step 2: Add a comment Input Data: - Click on comment link under the activity
   * - Enter some text into text box comment - Click on Comment button - Add more
   * comments Expected Outcome: Add an activity successfully: - This activity is
   * added into users activities list.User who is in your contact, can view your
   * active on his/her activity list Step number: 3 Step Name: - Step Description:
   * Step 3: Move other page Input Data: - Select any other page to access -
   * Return to activity page Expected Outcome: -Show content of activity page.
   * There are 2 lastest comments are showed. A link â€œshow all 'number'
   * commentsâ€ is displayed Step number: 4 Step Name: - Step Description: Step
   * 4: Show all comment Input Data: - Click on â€œshow all 'number' commentsâ€
   * link Expected Outcome: Display all comments of activity. A link â€œhide all
   * commentsâ€ is show Step number: 5 Step Name: - Step Description: Step 5:
   * Hide all comments Input Data: - Click on â€œhide all commentsâ€ link
   * Expected Outcome: Hide all comment. A link â€œshow all 'number' commentsâ€
   * is displayed
   */
  @Test
  public void test04_ShowhideAllComment() {
    info("Test 4: Show/hide all comment");
    info("Add new user");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    String comment1 = "comment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();
    String comment3 = "comment3" + getRandomNumber();
    String comment4 = "comment4" + getRandomNumber();
    activityStream.commentActivity(activity1, comment1);
    $(byText(activity1)).parent().parent().find(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);

    activityStream.commentActivity(activity1, comment2);
    $(byText(activity1)).parent().parent().find(byText(comment2)).waitUntil(Condition.visible, Configuration.timeout);

    activityStream.commentActivity(activity1, comment3);
    $(byText(activity1)).parent().parent().find(byText(comment3)).waitUntil(Condition.visible, Configuration.timeout);

    activityStream.commentActivity(activity1, comment4);
    $(byText(activity1)).parent().parent().find(byText(comment4)).waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToConnections();
    homePagePlatform.goToHomePage();
    $(byText(activity1)).parent()
                        .parent()
                        .find(byText(comment1))
                        .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(byText(activity1)).parent()
                        .parent()
                        .find(byText(comment2))
                        .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(byText(activity1)).parent().parent().find(byText(comment3)).waitUntil(Condition.visible, Configuration.timeout);
    $(byText(activity1)).parent().parent().find(byText(comment4)).waitUntil(Condition.visible, Configuration.timeout);
    info("Verify that view all comment links is shown and clickable on it");
    activityStream.showComment(activity1);
    $(byText(activity1)).parent().parent().find(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);
    $(byText(activity1)).parent().parent().find(byText(comment2)).waitUntil(Condition.visible, Configuration.timeout);
    $(byText(activity1)).parent().parent().find(byText(comment3)).waitUntil(Condition.visible, Configuration.timeout);
    $(byText(activity1)).parent().parent().find(byText(comment4)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.hideComment(activity1);
    $(byText(activity1)).parent()
                        .parent()
                        .find(byText(comment1))
                        .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(byText(activity1)).parent()
                        .parent()
                        .find(byText(comment2))
                        .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(byText(activity1)).parent()
                        .parent()
                        .find(byText(comment3))
                        .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(byText(activity1)).parent()
                        .parent()
                        .find(byText(comment4))
                        .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }
}
