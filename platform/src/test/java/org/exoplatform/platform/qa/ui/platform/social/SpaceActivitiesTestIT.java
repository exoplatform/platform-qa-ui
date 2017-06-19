package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;

/**
 * Created by exo on 07/06/17.
 */
@Tag("social")
@Tag("smoke")
public class SpaceActivitiesTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  SpaceManagement        spaceManagement;

  SpaceSettingManagement spaceSettingManagement;

  SpaceHomePage          spaceHomePage;

  ActivityStream         activityStream;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    activityStream = new ActivityStream(this);

  }

  @Test
  public void test01_AddNewYourActivityOnSpace() {
    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);
    homePagePlatform.goToSpecificSpace(space);
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test02_likeYourActivityOnSpace() {
    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);
    homePagePlatform.goToSpecificSpace(space);
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
  public void test03_DeleteYourActivityOnSpace() {
    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);
    homePagePlatform.goToSpecificSpace(space);
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    // hover on the activity to appear the delete button
    $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).hover();
    // click on delete button
    $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
    $(ELEMENT_DELETE_POPUP_OK).click();
    // verify that the activity doesn't exist
    $(byText(activity1)).shouldNot(Condition.exist);
    info("the activity is removed successfully");
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test04_AddCommentOnYourActivityOnSpace() {
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
    executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");

    // click on the button comment
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitWhile(Condition.disabled, Configuration.timeout);
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).doubleClick();
    if ($(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).isDisplayed()) {
      $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).click();
    }
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.disappears, Configuration.timeout);

    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test05_DeleteCommentOnYourActivityOnSpace() {
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
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitWhile(Condition.disabled, Configuration.timeout);
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).doubleClick();
    if ($(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).isDisplayed()) {
      $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).click();
    }
    $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.disappears, Configuration.timeout);
    info("Test 15: Delete comment");
    // scroll up
    executeJavaScript("window.scrollBy(0,-550)");
    // hover on the comment to appear the delete button
    ELEMENT_COMMENT_DESCRIPTION.find(byClassName(ELEMENT_DATE_COMMENT))
                               .waitUntil(Condition.appears, Configuration.timeout)
                               .hover();
    // the id of the comment is id of the activity+1
    Integer idComment = Integer.parseInt(id) + 1;
    $(byId(ELEMENT_COMMENT_DELETE.replace("{id}", idComment.toString()))).click();
    // Confirm
    $(ELEMENT_DELETE_POPUP_OK).click();
    // verify that the comment is deleted
    $(byText(comment)).shouldNot(Condition.exist);
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);
  }
}
