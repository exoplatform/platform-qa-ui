package org.exoplatform.platform.qa.ui.social.smoke;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_COMMENT_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.UserPageBase;

@Tag("smoke")
@Tag("social")
public class SOCPeopleActivityCommentTestIT extends Base {
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
   * <li>Case ID:122799.</li>
   * <li>Test Case Name: Comment on your activity.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test01_CommentOnYourActivity() {
    info("Test 1: Comment on your activity");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Add new activities
     * Input Data: - Sign in system - Select Activities page on User Toolbar
     * portlet in the upper right corner of the screen - Select activity in the
     * left pane - Enter some text into text box - Click on [Share] button
     * Expected Outcome: Add an activity successfully: - This activity is added
     * into users activities list.User who is in your contact, can view your
     * active on his/her activity list
     */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");

    /*
     * Step number: 2 Step Name: - Step Description: Step 2: Show comment form
     * Input Data: - Click on Comment link under the activity Expected Outcome:
     * Show a text box allow user add comment for activity
     */

    /*
     * Step number: 3 Step Name: - Step Description: Step 3: Add a comment Input
     * Data: - Enter some text into text box comment - Click on Comment button
     * Expected Outcome: Add a comment successfully. Other user can view
     * activity and comment of one. With each comment, some information is
     * display: - Avatar of user comment - Name of user comment - Content of
     * comment - Time comment is posted
     */
    String comment = "comment" + getRandomNumber();
    // get the id of webContent created
    String id = $(byClassName("heading")).parent().getAttribute("id").split("ActivityContextBox")[1];
    // click on comment icon
    $(byXpath("//*[@id=\"CommentLink" + id + "\"]")).click();
    // insert comment
    $(byId("cke_CommentTextarea" + id)).waitUntil(Condition.appears, Configuration.timeout).click();
    executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
    // click on the button comment
    $(byText("Comment")).waitUntil(Condition.not(Condition.disabled), Configuration.timeout).click();
    $(byText("Comment")).waitUntil(Condition.disappears, Configuration.timeout);
    $(byText(comment)).should(Condition.exist);
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:122792.</li>
   * <li>Test Case Name: Comment on your friends activity.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @BugInPLF("SOC-5738")
  // this test case is disabled until resolving this bug:
  // https://jira.exoplatform.org/browse/SOC-5738
  public void test02_CommentOnYourFriendsActivity() {
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameab" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("Test 2: Comment on your friends activity");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Create your comment
     * ( browser 1) Input Data: - Login by user AAA - Go to My profile - Create
     * new activities Expected Outcome: Activity is added successfully
     */
    navigationToolbar.goToMyActivities();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");

    /*
     * Step number: 2 Step Name: - Step Description: Step 2: Go to people page
     * Input Data: - Select people page Expected Outcome: - Show content of
     * People page
     */

    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    /*
     * Step number: 3 Step Name: - Step Description: Step 3: Invite user Input
     * Data: - Select user who has no relation with user to add relation - Click
     * on [Invite] (BBB) Expected Outcome: Invited user successfully
     */
    info("Access people list, invite an user");
    connectionsManagement.connectToAUser(username2);

    /*
     * Step number: 4 Step Name: - Step Description: Step 4: Accept the friend (
     * browser 2) Input Data: - Sign in with user which invited at step 2 (BBB)
     * - Select people page - Click on button [accept] Expected Outcome: - After
     * the user clicks on [accept], the relation between two users has set. The
     * user will be added into users relations user list. - By side each the
     * user, has a [remove] button to user can remove from this relation.=> Two
     * user become friend
     */
    info("Invited user accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    info("Verify after accept");
    connectionsManagement.verifyConnection(username1, true);

    /*
     * Step number: 5 Step Name: - Step Description: Step 5: Add a comment Input
     * Data: - Go to my profile and click comment button in AAA's activities
     * Expected Outcome: Show comment successfully
     */
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.ALL);
    connectionsManagement.searchPeople(username1, null, null, null);
    // click on the username to join his profile
    $(byClassName("uiTabNormal")).find(byClassName("limitText")).click();
    userPageBase.goToActivityTab();
    String comment = "comment" + getRandomNumber();
    // get the id of webContent created
    String id = $(byClassName("heading")).parent().getAttribute("id").split("ActivityContextBox")[1];
    // click on comment icon
    $(byXpath("//*[@id=\"CommentLink" + id + "\"]")).click();
    // insert comment
    $(byId("cke_CommentTextarea" + id)).waitUntil(Condition.appears, Configuration.timeout).click();
    executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
    // click on the button comment

    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.enabled, Configuration.timeout).click();
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.disappears, Configuration.timeout);
    $(byText(comment)).should(Condition.exist);
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  /**
   * <li>Case ID:122787.</li>
   * <li>Test Case Name: Delete comment.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test03_DeleteComment() {
    info("Test 3: Delete comment");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Add new activities
     * Input Data: - Sign in system - Select Activities page on User Toolbar
     * portlet in the upper right corner of the screen - Select activity in the
     * left pane - Enter some text into text box - Click on [Share] button
     * Expected Outcome: Add an activity successfully: - This activity is added
     * into users activities list.User who is in your contact, can view your
     * active on his/her activity list
     */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");

    /*
     * Step number: 2 Step Name: - Step Description: Step 2: Add a comment Input
     * Data: - Click on comment link under the activity - Enter some text into
     * text box comment - Click on Comment button - Add more comments Expected
     * Outcome: Add an activity successfully: - This activity is added into
     * users activities list.User who is in your contact, can view your active
     * on his/her activity list
     */
    String comment = "comment" + getRandomNumber();
    // get the id of webContent created
    String id = $(byClassName("heading")).parent().getAttribute("id").split("ActivityContextBox")[1];
    // click on comment icon
    $(byXpath("//*[@id=\"CommentLink" + id + "\"]")).click();
    // insert comment
    $(byId("cke_CommentTextarea" + id)).waitUntil(Condition.appears, Configuration.timeout).click();
    executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
    // click on the button comment

    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.not(Condition.disabled), Configuration.timeout)
                                                          .click();
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.disappears, Configuration.timeout);
    $(byText(comment)).should(Condition.exist);

    /*
     * Step number: 3 Step Name: - Step Description: Step 3: Delete comment
     * Input Data: - Select an comment - Click on Delete - Click OK to confirm
     * deleting Expected Outcome: This comment is deleted.
     */
    // scroll up
    executeJavaScript("window.scrollBy(0,-250);", "");
    // hover on the comment to appear the delete button
    $(byClassName("commentRight")).waitUntil(Condition.appears, Configuration.timeout).hover();
    // the id of the comment is id of the activity+1
    Integer idComment = Integer.parseInt(id) + 1;
    $(byId("DeleteCommentButtoncomment" + idComment.toString())).click();
    // Confirm
    $(byText("OK")).click();
    // verify that the comment is deleted
    $(byText(comment)).shouldNot(Condition.exist);
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

}
