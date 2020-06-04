package org.exoplatform.platform.qa.ui.social.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.UserPageBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_COMMENT_BLOC;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_DELETE_POPUP_OK;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_ADD_TOOTLBAR;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.Assert.assertEquals;

@Tag("smoke")
@Tag("social")
public class SOCPeopleActivityCommentTestIT extends Base {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream activityStream;

  UserPageBase userPageBase;


  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    homePagePlatform = new HomePagePlatform(this);
    addUsers = new AddUsers(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    userPageBase = new UserPageBase(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }

  /**
   * <li>Case ID:122799.</li>
   * <li>Test Case Name: Comment on your activity.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test01_CommentOnYourActivity() {
    info("Comment on your activity");
    /*
     * Step Number: 1 Step Name: - Step Description: Step 1: Add new activities
     * Input Data: - Sign in system - Select Activities page on User Toolbar portlet
     * in the upper right corner of the screen - Select activity in the left pane -
     * Enter some text into text box - Click on [Share] button Expected Outcome: Add
     * an activity successfully: - This activity is added into users activities
     * list.User who is in your contact, can view your active on his/her activity
     * list
     */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    String activity1 = "activity1" + getRandomNumber();
    String activity2 = "activity2" + getRandomNumber();

    activityStream.addActivity(activity1, "");

    /*
     * Step number: 2 Step Name: - Step Description: Step 2: Show comment form Input
     * Data: - Click on Comment link under the activity Expected Outcome: Show a
     * text box allow user add comment for activity
     */

    /*
     * Step number: 3 Step Name: - Step Description: Step 3: Add a comment Input
     * Data: - Enter some text into text box comment - Click on Comment button
     * Expected Outcome: Add a comment successfully. Other user can view activity
     * and comment of one. With each comment, some information is display: - Avatar
     * of user comment - Name of user comment - Content of comment - Time comment is
     * posted
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

    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter();
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.disappears, Configuration.timeout);
    $(byText(comment)).should(Condition.exist);

    info("Delete Comment");
    $(ELEMENT_ADD_TOOTLBAR).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    /*
     * Step number: 3 Step Name: - Step Description: Step 3: Delete comment Input
     * Data: - Select an comment - Click on Delete - Click OK to confirm deleting
     * Expected Outcome: This comment is deleted.
     */
    // scroll up
    executeJavaScript("window.scrollBy(0,-250);", "");
    // the id of the comment is id of the activity+1
    Integer idComment = Integer.parseInt(id) + 1;
    // hover on the comment to appear the delete button
    $(byId(ELEMENT_COMMENT_BLOC.replace("{id}", id))).hover().click();
    $(byId("dropDownEditCommentcomment" + idComment.toString())).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byId("DeleteCommentButtoncomment" + idComment.toString())).waitUntil(Condition.visible, Configuration.timeout).click();
    // Confirm
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    // verify that the comment is deleted
    $(byText(comment)).shouldNot(Condition.exist);

    info("Delete activity by the user");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyActivities();
    activityStream.addActivity(activity2, "");

    /*
     * Step number: 2 Step Name: - Step Description: Step 2: Delete activity Input
     * Data: - Select an activity - Click on Delete - Click OK to confirm deleting
     * Expected Outcome: The activity is deleted. All comments of activity are
     * deleted too.
     */
    activityStream.deleteactivity(activity2);
    info("the activity is removed successfully");

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
     * Step Number: 1 Step Name: - Step Description: Step 1: Create your comment (
     * browser 1) Input Data: - Login by user AAA - Go to My profile - Create new
     * activities Expected Outcome: Activity is added successfully
     */
    navigationToolbar.goToMyActivities();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");

    /*
     * Step number: 2 Step Name: - Step Description: Step 2: Go to people page Input
     * Data: - Select people page Expected Outcome: - Show content of People page
     */

    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    /*
     * Step number: 3 Step Name: - Step Description: Step 3: Invite user Input Data:
     * - Select user who has no relation with user to add relation - Click on
     * [Invite] (BBB) Expected Outcome: Invited user successfully
     */
    info("Access people list, invite an user");
    connectionsManagement.connectToAUser(username2);

    /*
     * Step number: 4 Step Name: - Step Description: Step 4: Accept the friend (
     * browser 2) Input Data: - Sign in with user which invited at step 2 (BBB) -
     * Select people page - Click on button [accept] Expected Outcome: - After the
     * user clicks on [accept], the relation between two users has set. The user
     * will be added into users relations user list. - By side each the user, has a
     * [remove] button to user can remove from this relation.=> Two user become
     * friend
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

    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
    $(byText(comment)).should(Condition.exist);

    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  @Test
  public void test03_LikeComment() {
    info("Test 4: Like comment");

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameab" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    String comment = "comment" + getRandomNumber();
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    //navigationToolbar.goToMyActivities();
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
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity1, comment);
    String idBlocComment = $(byText(activity1)).parent().parent().parent().find(byText(comment)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
    manageLogInOut.signIn(username2, password);
    activityStream.likeUnlikeComment(activity1, comment);
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(1)"));
    //rgba(47, 94, 146, 1) is the css value of blue
    assertEquals("rgba(87, 141, 201, 1)", $(byId("LikeCommentLinkcomment" + idBlocComment)).find(byClassName("uiIconThumbUp")).getCssValue("color"));
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).hover();
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Unlike"));
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);


  }

  @Test
  public void test04_UnlikeComment() {
    info("Test 5: Like comment");

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

    //navigationToolbar.goToMyActivities();
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
    homePagePlatform.goToHomePage();
    String comment = "comment" + getRandomNumber();
    activityStream.commentActivity(activity1, comment);
    manageLogInOut.signIn(username2, password);
    String idBlocComment = $(byText(activity1)).parent().parent().parent().find(byText(comment)).parent().parent().parent().parent().getAttribute("id").split("commentContainercomment")[1];
    activityStream.likeUnlikeComment(activity1, comment);
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(1)"));
    assertEquals("rgba(87, 141, 201, 1)", $(byId("LikeCommentLinkcomment" + idBlocComment)).find(byClassName("uiIconThumbUp")).getCssValue("color"));
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).hover();
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Unlike"));
    activityStream.likeUnlikeComment(activity1, comment);
    refresh();
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text(""));
    assertEquals("rgba(87, 141, 201, 1)", $(byId("LikeCommentLinkcomment" + idBlocComment)).find(byClassName("uiIconThumbUp")).getCssValue("color"));
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).hover();
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Like"));
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

}