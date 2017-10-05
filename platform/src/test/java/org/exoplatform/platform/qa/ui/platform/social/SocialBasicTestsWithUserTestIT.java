package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

/**
 * Created by ilyes on 05/10/17.
 */

@Tag("sniff")
@Tag("social")
public class SocialBasicTestsWithUserTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  HomePagePlatform      homePagePlatform;

  ActivityStream        activityStream;

  ManageLogInOut        manageLogInOut;

  AddUsers              addUsers;

  ConnectionsManagement connectionsManagement;

  SpaceManagement       spaceManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    homePagePlatform = new HomePagePlatform(this);
    activityStream = new ActivityStream(this);
    connectionsManagement = new ConnectionsManagement(this);
    spaceManagement = new SpaceManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
  }

  @Test
  public void test01_LikeActivity() {
    info("Test 1: Like Activity");
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    // get the id of the activity created
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // click on the like button of the activity
    $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).click();
    $(byXpath(ELEMENT_UNLIKE_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout);

    ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.timeout);
    // click on the activity to appear the delete button
    $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
    // click on delete button
    $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
    ELEMENT_DELETE_POPUP_OK.click();
    // verify that the activity doesn't exist
    $(byText(activity1)).shouldNot(Condition.exist);
    info("the activity is removed successfully");
  }

  @Test
  public void test02AddComment() {
    info("Test 2: Add comment");

    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    // get the id of activity created
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // click on comment link
    $(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
    // insert comment
    $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
    executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
    // click on the button comment
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
    $(byText(comment)).should(Condition.exist);
    // click on the activity to appear the delete button
    $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
    // click on delete button
    $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
    ELEMENT_DELETE_POPUP_OK.click();
    // verify that the activity doesn't exist
    $(byText(activity1)).shouldNot(Condition.exist);
    info("the activity is removed successfully");
  }

  @Test
  public void test_03DeleteComment() {
    info("Test 2: Add comment");

    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    // get the id of activity created
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // click on comment link
    $(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
    // insert comment
    $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
    executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
    // click on the button comment
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
    info("Test 15: Delete comment");
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    // scroll up
    executeJavaScript("window.scrollBy(0,-550)");
    // the id of the comment is id of the activity+1
    Integer idComment = Integer.parseInt(id) + 1;
    // hover on the comment to appear the delete button
    $(byId(ELEMENT_COMMENT_BLOC.replace("{id}", id))).hover().click();
    $(byId(ELEMENT_COMMENT_DELETE.replace("{id}", idComment.toString()))).click();
    // Confirm
    $(ELEMENT_DELETE_POPUP_OK).click();
    // verify that the comment is deleted
    $(byText(comment)).shouldNot(Condition.exist);
    // click on the activity to appear the delete button
    $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
    // click on delete button
    $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
    ELEMENT_DELETE_POPUP_OK.click();
    // verify that the activity doesn't exist
    $(byText(activity1)).shouldNot(Condition.exist);
    info("the activity is removed successfully");
  }

  @Test
  public void test04_DeleteYourActivity() {
    info("Test 3: Delete your activity");
    // get the id of the webContent created
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // click on the activity to appear the delete button
    $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
    // click on delete button
    $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
    $(ELEMENT_DELETE_POPUP_OK).click();
    // verify that the activity doesn't exist
    $(byText(activity1)).shouldNot(Condition.exist);
    info("the activity is removed successfully");
  }

  @Test
  public void test05_Upload_File_Without_Text() {
    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    $(ELEMENT_COMPOSER_SHARE_BUTTON).should(Condition.be(Condition.enabled));
    $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
    $(byAttribute("data-original-title", "eXo-Platform.png")).parent()
                                                             .parent()
                                                             .parent()
                                                             .parent()
                                                             .find(byClassName(ELEMENT_DATE_ACTIVITY))
                                                             .hover();
    $(byAttribute("data-original-title", "eXo-Platform.png")).parent()
                                                             .parent()
                                                             .parent()
                                                             .parent()
                                                             .find(ELEMENT_ICON_DELETE_ACTIVITY)
                                                             .click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, Configuration.timeout).click();
    $(byAttribute("data-original-title", "eXo-Platform.png")).parent()
                                                             .parent()
                                                             .parent()
                                                             .parent()
                                                             .waitUntil(Condition.disappears, Configuration.timeout);
  }

  @Test
  public void test06_add_link_without_text() {
    String link = "http://www.google.fr";
    ELEMENT_TAB_LINK.click();
    ELEMENT_TAB_ADD_LINK.click();
    ELEMENT_INPUT_LINK.setValue(link);
    ELEMENT_BUTTON_ATTACH_LINK.click();
    $(ELEMENT_COMPOSER_SHARE_BUTTON).waitUntil(Condition.be(Condition.enabled), Configuration.timeout);
    $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
    $(byText(link)).should(Condition.exist);
    $(byText(link)).click();
    switchTo().window(1);
    String title = "Google";
    assertEquals(title, Selenide.title());
    switchTo().window(0);
    $(byText(link)).parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .find(byClassName(ELEMENT_DATE_ACTIVITY))
                   .waitUntil(Condition.visible, Configuration.timeout)
                   .hover();
    $(byText(link)).parent().parent().parent().parent().parent().find(ELEMENT_ICON_DELETE_ACTIVITY).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, Configuration.timeout).click();
    $(byText(link)).parent().parent().parent().parent().parent().waitUntil(Condition.disappear, Configuration.timeout);

  }

  @Test
  public void test07_LikeComment() {
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

    // navigationToolbar.goToMyActivities();
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
    String idBlocComment = $(byText(activity1)).parent()
                                               .parent()
                                               .parent()
                                               .find(byText(comment))
                                               .parent()
                                               .parent()
                                               .parent()
                                               .parent()
                                               .getAttribute("id")
                                               .split("commentContainercomment")[1];
    manageLogInOut.signIn(username2, password);
    activityStream.likeUnlikeComment(activity1, comment);
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(1)"));
    // rgba(47, 94, 146, 1) is the css value of blue
    Assert.assertEquals("rgba(47, 94, 146, 1)",
                        $(byId("LikeCommentLinkcomment" + idBlocComment)).find(byClassName("uiIconThumbUp"))
                                                                         .getCssValue("color"));
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).hover();
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Unlike"));
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  @Test
  public void test08_UnlikeComment() {
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

    // navigationToolbar.goToMyActivities();
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
    String idBlocComment = $(byText(activity1)).parent()
                                               .parent()
                                               .parent()
                                               .find(byText(comment))
                                               .parent()
                                               .parent()
                                               .parent()
                                               .parent()
                                               .getAttribute("id")
                                               .split("commentContainercomment")[1];
    activityStream.likeUnlikeComment(activity1, comment);
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(1)"));
    Assert.assertEquals("rgba(47, 94, 146, 1)",
                        $(byId("LikeCommentLinkcomment" + idBlocComment)).find(byClassName("uiIconThumbUp"))
                                                                         .getCssValue("color"));
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).hover();
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Unlike"));
    activityStream.likeUnlikeComment(activity1, comment);
    refresh();
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text(""));
    Assert.assertEquals("rgba(153, 153, 153, 1)",
                        $(byId("LikeCommentLinkcomment" + idBlocComment)).find(byClassName("uiIconThumbUp"))
                                                                         .getCssValue("color"));
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).hover();
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Like"));
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  @Test
  public void test09_CreateNewSpace() {
    info("Test 04: Create a new Space");
    String space = "space" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Publics space list of other user");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.searchSpace(space, "");
    $(byText(space)).waitUntil(Condition.appears, Configuration.timeout);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space, "");
    $(byClassName("tab-content")).find(byText(space)).waitUntil(Condition.appears, Configuration.timeout);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space, "");
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:121912.</li>
   * <li>Test Case Name: Delete a space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test10_DeleteASpace() {
    info("Test 06:Delete a space");
    String space = "space" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);

    info("Delete a Space");
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space, "");
    spaceManagement.deleteSpace(space, false);

    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.searchSpace(space, "");
    $(byClassName("boxSpaceList")).find(byText(space)).shouldNot(Condition.exist);
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  @Test
  public void test11_AddNewYourActivityOnSpace() {
    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test12_likeYourActivityOnSpace() {
    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // click on the like button of the activity
    $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).click();
    $(byXpath(ELEMENT_UNLIKE_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.timeout);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test13_DeleteYourActivityOnSpace() {
    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // click on the activity to appear the delete button
    $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
    // click on delete button
    $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
    ELEMENT_DELETE_POPUP_OK.click();
    // verify that the activity doesn't exist
    $(byText(activity1)).shouldNot(Condition.exist);
    info("the activity is removed successfully");
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test14_AddCommentOnYourActivityOnSpace() {
    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    String comment = "comment" + getRandomNumber();
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1]; // click
    // on
    // comment
    // icon
    $(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
    // insert comment
    executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
    // click on the button comment
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter();
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.disappears, Configuration.timeout);

    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test15_DeleteCommentOnYourActivityOnSpace() {
    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);
    homePagePlatform.goToSpecificSpace(space);
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    String comment = "comment" + getRandomNumber();
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1]; // click
    // on
    // comment
    // icon
    $(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
    // insert comment
    $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
    executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
    // click on the button comment
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter();
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.disappears, Configuration.timeout);
    info("Test 15: Delete comment");
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    // scroll up
    executeJavaScript("window.scrollBy(0,-550)");
    // the id of the comment is id of the activity+1
    Integer idComment = Integer.parseInt(id) + 1;
    // hover on the comment to appear the delete button
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    $(byId(ELEMENT_COMMENT_BLOC.replace("{id}", id))).hover().click();
    $(byId(ELEMENT_COMMENT_DELETE.replace("{id}", idComment.toString()))).click();
    // Confirm
    ELEMENT_DELETE_POPUP_OK.click();
    // verify that the comment is deleted
    $(byText(comment)).shouldNot(Condition.exist);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);
  }
}
