package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
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
  public void test01_AddLinkWithoutTextLikeActivityLikeUnlikeComment() {
    info("Like Activity");
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String activity2 = "activity1" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameab" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "Aa123456";
    String comment2 = "comment" + getRandomNumber();
    String link = "http://www.google.fr";
    info("Add link without text");
    ELEMENT_ACTIVITY_COMPOSER_FILE_TAB.click();
    ELEMENT_TAB_ADD_LINK.click();
    ELEMENT_INPUT_LINK.setValue(link);
    ELEMENT_BUTTON_ATTACH_LINK.click();
    $(ELEMENT_COMPOSER_SHARE_BUTTON).waitUntil(Condition.be(Condition.enabled), Configuration.timeout);
    $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
    $(byText(link)).should(Condition.exist);
    $(byText(link)).waitUntil(Condition.visible,Configuration.timeout).click();
    switchTo().window(1);
    sleep(2000);
    String title = "Google";
    assertEquals(title, Selenide.title());
    switchTo().window(0);
    sleep(2000);
    String idL = $(byXpath("(//div[@id='boxContainer']/div)[1]")).waitUntil(Condition.visible,Configuration.timeout).getAttribute("id").split("ContextBox")[1];
    $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}",idL))).waitUntil(Condition.visible,Configuration.timeout).click();
    $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}",idL))).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, Configuration.timeout).click();
    $(byXpath("//div[@id='ContextBox{id}']".replace("{id}",idL))).waitUntil(Condition.disappear, Configuration.timeout);
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    // navigationToolbar.goToMyActivities();
    activityStream.addActivity(activity2, "");
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Access people list, invite an user");
    connectionsManagement.connectToAUser(username2);
    info("Invited user accept invitation");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity2, comment2);
    String idBlocComment = $(byText(activity2)).parent()
            .parent()
            .parent()
            .find(byText(comment2))
            .parent()
            .parent()
            .parent()
            .parent()
            .getAttribute("id")
            .split("commentContainercomment")[1];
    manageLogInOut.signIn(username2, password);
    info("Like comment");
    activityStream.likeUnlikeComment(activity2, comment2);
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(1)"));
    // rgba(47, 94, 146, 1) is the css value of blue
    Assert.assertEquals("rgba(87, 141, 201, 1)",
            $(byId("LikeCommentLinkcomment" + idBlocComment)).find(byClassName("uiIconThumbUp"))
                    .getCssValue("color"));
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).hover();
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Unlike"));
    info("Unike comment");
    activityStream.likeUnlikeComment(activity2, comment2);
    refresh();
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text(""));
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).hover();
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Like"));
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    activityStream.addActivity(activity1, "");
    /*
     * Step Number: 1 Step Name: Step 1: Like/Unlike Activity Step Description: - Go
     * to Intranet home - Click on Like activity in action bar part of an activity
     * Input Data: Expected Outcome: - Like button is highlighted and the number of
     * likers is updated
     */
    // get the id of the activity created
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // click on the like button of the activity
    $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).click();
    $(byXpath(ELEMENT_UNLIKE_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout);
    /*
     * Step number: 2 Step Name: Check Likes part Step Description: - Check avatar -
     * Mouse over the avatar Input Data: Expected Outcome: - Avatar of liker is
     * added into likes part, the oldest liker is displayed at the right and the
     * newest at the left. - Profile pictures of users popup
     */
    ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.timeout);
    info("Add comment");
    // click on comment link
    $(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).click();
    // insert comment
    $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout).click();
    executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
    // click on the button comment
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).pressEnter().waitUntil(Condition.disappears, Configuration.timeout);
    $(byText(comment)).should(Condition.exist);
    info("Delete comment");
    activityStream.deletecomment(activity1,comment);
    // verify that the comment is deleted
    $(byText(comment)).shouldNot(Condition.exist);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    info("Delete your activity");
    // click on the activity to appear the delete button
    homePagePlatform.goToHomePage();
    activityStream.deleteactivity(activity1);
    $(byText(activity1)).shouldNot(Condition.exist);
    info("the activity is removed successfully");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }
  @Test
  public void test02_LikeYourActivityOnSpaceAndSearchDeletedSpace() {
    info("Test 04: Create a new Space");
    String space = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();
    String space3 = "space" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "Aa123456";
    String comment = "comment" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space3, space3, 60000);
    activityStream.addActivity(activity1, "");
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // click on the like button of the activity
    $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).click();
    $(byXpath(ELEMENT_UNLIKE_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.timeout);
    info("Add Comment On Your Activity On Space");
    activityStream.commentActivity(activity1,comment);
    info("Delete comment");
    // scroll up
    executeJavaScript("window.scrollBy(0,-550)");
    // the id of the comment is id of the activity+1
    activityStream.deletecomment(activity1,comment);
    homePagePlatform.goToHomePage();
    activityStream.deleteactivity(activity1);
    $(byText(activity1)).shouldNotBe(Condition.visible);
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    info("Delete a Space");
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space2, "");
    spaceManagement.deleteSpace(space2, false);
    info("Publics space list of other user");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.searchSpace(space2, "");
    $(byClassName("boxSpaceList")).find(byText(space2)).shouldNot(Condition.exist);
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

}
