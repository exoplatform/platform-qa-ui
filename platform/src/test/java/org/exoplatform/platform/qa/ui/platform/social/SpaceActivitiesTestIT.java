package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.core.PLFData;
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
@Tag("sniff")
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
    manageLogInOut.signIn(PLFData.username, PLFData.DATA_PASS);

  }

  @Test
  public void test02_LikeCommentYourActivityOnSpace() {
    String space = "space" + getRandomNumber();
    String space1 = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();
    String space3 = "space" + getRandomNumber();
    String space4 = "space" + getRandomNumber();
    String space5 = "space" + getRandomNumber();
    String space6 = "space" + getRandomNumber();
    String space7 = "space" + getRandomNumber();
    String space8 = "space" + getRandomNumber();
    String space9 = "space" + getRandomNumber();
    String space10 = "space" + getRandomNumber();
    String space11 = "space" + getRandomNumber();
    String space12 = "space" + getRandomNumber();
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, "", 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, "", 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space3, "", 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space4, "", 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space5, "", 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space6, "", 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space7, "", 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space8, "", 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space9, "", 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space10, "", 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space11, "", 60000);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space12, "", 60000);
    homePagePlatform.goToHomePage();
    // scroll only the left navigation
    executeJavaScript("arguments[0].scrollBy(0,550);", $(byId("LeftNavigation")));
    ELEMENT_BUTTON_SHOW_MORE_SPACES_IN_LEFT_NAVIGATION.click();
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space1)).shouldHaveSize(1);
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space2)).shouldHaveSize(1);
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space3)).shouldHaveSize(1);
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space4)).shouldHaveSize(1);
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space5)).shouldHaveSize(1);
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space6)).shouldHaveSize(1);
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space7)).shouldHaveSize(1);
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space8)).shouldHaveSize(1);
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space9)).shouldHaveSize(1);
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space10)).shouldHaveSize(1);
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space11)).shouldHaveSize(1);
    ELEMENT_SPECIFIC_PANEL.findAll(byText(space12)).shouldHaveSize(1);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    String id = $(byClassName("activityStream")).parent().getAttribute("id").split("UIActivityLoader")[1];
    activityStream.checkActivity(activity1);
    // click on the like button of the activity
    $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).click();
    $(byXpath(ELEMENT_UNLIKE_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.timeout);
    String comment = "comment" + getRandomNumber();
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
    Integer idComment = Integer.parseInt(id);
    Integer idDelete = Integer.parseInt(id) + 1 ;
    // hover on the comment to appear the delete button
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    $(byXpath("//div[@id='CommentBlock{id}1']//i[@class='uiIconActivityAction uiIconLightGray']".replace("{id}", idComment.toString()))).click();
    $(byId(ELEMENT_COMMENT_DELETE.replace("{id}", idDelete.toString()))).waitUntil(Condition.visible,Configuration.timeout).click();
    // Confirm
    ELEMENT_DELETE_POPUP_OK.click();
    // verify that the comment is deleted
    $(byText(comment)).shouldNot(Condition.exist);
    // click on the activity to appear the delete button
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    $(byId(ELEMENT_CONTAINER_ACTIVITY.replace("{id}", id))).find(byClassName(ELEMENT_DATE_ACTIVITY)).click();
    // click on delete button
    $(byXpath("(//i[@class='uiIconActivityAction uiIconLightGray'])[1]")).waitUntil(Condition.visible,Configuration.timeout).click();
    $(byId(ELEMENT_DELETE_ACTIVITY.replace("{id}", id))).click();
    ELEMENT_DELETE_POPUP_OK.click();
    // verify that the activity doesn't exist
    $(byText(activity1)).shouldNot(Condition.exist);

    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);
    spaceManagement.deleteSpace(space1, false);
    spaceManagement.deleteSpace(space2, false);
    spaceManagement.deleteSpace(space3, false);
    spaceManagement.deleteSpace(space4, false);
    spaceManagement.deleteSpace(space5, false);
    spaceManagement.deleteSpace(space6, false);
    spaceManagement.deleteSpace(space7, false);
    spaceManagement.deleteSpace(space8, false);
    spaceManagement.deleteSpace(space9, false);
    spaceManagement.deleteSpace(space10, false);
    spaceManagement.deleteSpace(space11, false);
    spaceManagement.deleteSpace(space12, false);

  }

  }
