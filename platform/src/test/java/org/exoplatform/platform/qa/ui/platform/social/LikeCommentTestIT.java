package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

/**
 * Created by exo on 11/09/17.
 */
@Tag("social")
@Tag("sniff")
public class LikeCommentTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  ConnectionsManagement  connectionsManagement;

  ActivityStream         activityStream;

  SpaceHomePage          spaceHomePage;

  SpaceManagement        spaceManagement;

  SpaceSettingManagement spaceSettingManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    homePagePlatform = new HomePagePlatform(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
     manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  @Test
  public void test01_checkNumberWhenUserLikeThenUnlikeComment() {
    String activity1 = "activity1" + getRandomNumber();
    String activity2 = "activity2" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String comment1 = " likes your comment.";
    String comment2 = "comment2" + getRandomNumber();
    String comment4 = " like your comment.";

    ELEMENT_ACTIVITY_COMPOSER_FILE_TAB.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.openBrowserTimeoutMs);
    activityStream.addActivity(activity2, "");
    info("Like Comment After Preview Document");
    String id = $(byText(activity2)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment2 + "\")", "");
    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.openBrowserTimeoutMs).click();
    $(byText(comment2)).should(Condition.exist);
    $(byText(comment2)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byText(comment2)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).parent().parent().shouldHave(Condition.text("(1)"));
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    // get the id of activity created
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

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment2)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    info("Check Notification When One User Likes Comment");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    ELEMENT_ICON_NOTIFICATION.click();
    Assert.assertEquals(ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs)
            .find(byText(comment))
            .parent()
            .parent()
            .getText(),DATA_NAME_USER2 +" likes your comment.\n" + comment + "\nJust Now");
    info("Check Notification When One User Likes Comment In Document Previw");
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
            .find(byText(comment2))
            .parent()
            .parent()
            .shouldHave(Condition.text(DATA_NAME_USER2 + comment1));
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
            .find(byText(DATA_NAME_USER2))
            .parent()
            .parent()
            .find(byText(comment2))
            .should(Condition.exist);
    info("Check Full Name Of User Is Displayed");
    homePagePlatform.refreshUntil($(ELEMENT_ACCOUNT_NAME_LINK),Condition.visible,1000);
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().find(byText("(1)")).hover();
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText("Mary Williams")).should(Condition.exist);

    manageLogInOut.signIn(PLFData.DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment2)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    info("Check Notification When Two Users Like Comment");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_ICON_NOTIFICATION.click();
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
            .find(byText(comment))
            .parent()
            .parent()
            .shouldHave(Condition.text(DATA_NAME_USER3 + " and " + DATA_NAME_USER2 + comment4));
    info("Check Notification When Two Users Like Comment In Document Previw");
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
            .find(byText(comment2))
            .parent()
            .parent()
            .shouldHave(Condition.text(DATA_NAME_USER3 + " and " + DATA_NAME_USER2 + comment4));
    manageLogInOut.signIn(PLFData.DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment2)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    info("Check Notification When Many Users Like Comment");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    ELEMENT_ICON_NOTIFICATION.click();
    Assert.assertEquals(ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs)
            .find(byText(comment))
            .parent()
            .parent()
            .getText(),DATA_NAME_USER4 + ", " + DATA_NAME_USER3 + " and 1 others like your comment.\n" + comment + "\nJust Now");
    info("Check Notification When Many Users Like Comment In Document Previw");
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
            .find(byText(comment2))
            .parent()
            .parent()
            .shouldHave(Condition.text(DATA_NAME_USER4 + ", " + DATA_NAME_USER3 + " and 1 others like your comment.\n" + comment2 + "\nJust Now"));
    Assert.assertEquals(ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs)
            .find(byText(comment2))
            .parent()
            .parent()
            .getText(),DATA_NAME_USER4 + ", " + DATA_NAME_USER3 + " and 1 others like your comment.\n" + comment2 + "\nJust Now");
    info("Check List Of Users Who Like Comment Ordered By The Most Recent Like");
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(3)"));
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().find(byText("(3)")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Jack Miller")).should(Condition.exist);
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("James David")).should(Condition.exist);
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Mary Williams")).should(Condition.exist);
    info("Check Connexion Status Button Of Users Like Comment");
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Jack Miller"))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_IN_POPUP_WHO_LIKE_COMMENT)
            .shouldHave(Condition.text("Remove Connection"));
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("James David"))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_IN_POPUP_WHO_LIKE_COMMENT)
            .shouldHave(Condition.text("Remove Connection"));
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Mary Williams"))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_IN_POPUP_WHO_LIKE_COMMENT)
            .shouldHave(Condition.text("Remove Connection"));
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    // connect with user 4 and unlike comment
    manageLogInOut.signIn(PLFData.DATA_USER4, PLFData.DATA_PASS);
    activityStream.likeUnlikeComment(activity1, comment);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    info("Check Number Of Users Comment Likes After Preview Document");
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(2)"));
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Display List Of Users Ordered By The Most Recent Like After Preview Document");
    $(byId(ELEMENT_INCON_LIKE_COMMENT_PREVIEW.replace("{id}", String.valueOf(Integer.parseInt(id) +1) ))).shouldHave(Condition.text("(4)"));
    $(byId(ELEMENT_INCON_LIKE_COMMENT_PREVIEW.replace("{id}", String.valueOf(Integer.parseInt(id) +1) ))).shouldHave(Condition.text("(4)")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_FIRST_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Jack Miller"));
    ELEMENT_SECOND_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("James David"));
    ELEMENT_THIRD_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Mary Williams"));
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment2)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Check Number Of Users Comment Unlikes After Preview Document");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(2)"));
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Check Connection Status After Preview Document");
    $(byId(ELEMENT_INCON_LIKE_COMMENT_PREVIEW.replace("{id}", String.valueOf(Integer.parseInt(id) +1) ))).shouldHave(Condition.text("(3)"));
    $(byId(ELEMENT_INCON_LIKE_COMMENT_PREVIEW.replace("{id}", String.valueOf(Integer.parseInt(id) +1) ))).shouldHave(Condition.text("(3)")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("James David"))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_IN_POPUP_WHO_LIKE_COMMENT)
            .shouldHave(Condition.text("Remove Connection"));
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Mary Williams"))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_IN_POPUP_WHO_LIKE_COMMENT)
            .shouldHave(Condition.text("Remove Connection"));
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    homePagePlatform.goToHomePage();
    activityStream.deleteactivity(activity1);
    activityStream.deleteactivity(activity2);

  }

  @Test
  @Tag("sabis")
  public void test02_checkConnectionStatusAfterPreviewDocumentAndStatusButtonOfUsersCommentLikesInSpace() {
    String activity1 = "activity1" + getRandomNumber();
    String activity2 = "activity2" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();
    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    $(byXpath("//*[@class='uidocactivitycomposer']")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.openBrowserTimeoutMs);
    activityStream.addActivity(activity2, "");
    String id = $(byText(activity2)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment2 + "\")", "");
    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.openBrowserTimeoutMs).click();
    $(byText(comment2)).should(Condition.exist);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    spaceSettingManagement.goToMemberTab();
    ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER2);
    $(ELEMENT_SPACE_BTN_INVITE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    refresh();
    ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER3);
    $(ELEMENT_SPACE_BTN_INVITE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    refresh();
    ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER4);
    $(ELEMENT_SPACE_BTN_INVITE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    homePagePlatform.goToSpecificSpace(space);
    spaceManagement.goToActivityStreamTab();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment);
    executeJavaScript("window.scrollBy(0,-550)", "");
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

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeUnlikeComment(activity1, comment);
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment2)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    executeJavaScript("window.scrollBy(0,300)", "");
    activityStream.likeUnlikeComment(activity1, comment);
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment2)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeUnlikeComment(activity1, comment);
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment2)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    info("Check Connection Status After Preview Document");
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment2))
            .parent()
            .parent()
            .find(ELEMENT_ICON_LIKE_COMMENT)
            .parent()
            .parent()
            .find(byText("(3)"))
            .click();
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Jack Miller"))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_IN_POPUP_WHO_LIKE_COMMENT)
            .shouldHave(Condition.text("Connect"));
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("James David"))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_IN_POPUP_WHO_LIKE_COMMENT)
            .shouldHave(Condition.text("Connect"));
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Mary Williams"))
            .parent()
            .parent()
            .find(ELEMENT_BUTTON_IN_POPUP_WHO_LIKE_COMMENT)
            .shouldHave(Condition.text("Connect"));
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    info("Check Connexion Status Button Of Users Comment Likes In Space");
    homePagePlatform.goToSpecificSpace(space);
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(3)"));
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().find(byText("(3)")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Jack Miller"))
            .parent()
            .parent()
            .find(byClassName("uiActionLike"))
            .shouldHave(Condition.text("Connect"));
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("James David"))
            .parent()
            .parent()
            .find(byClassName("uiActionLike"))
            .shouldHave(Condition.text("Connect"));
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Mary Williams"))
            .parent()
            .parent()
            .find(byClassName("uiActionLike"))
            .shouldHave(Condition.text("Connect"));
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  @Tag("sabis")
  public void test03_checkListOfUsersInToolTipWhen11UsersLikeComment() {
    String space = "space" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String username1 = "a" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "b" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String username3 = "c" + getRandomString();
    String email3 = username3 + "@gmail.com";
    String username4 = "d" + getRandomString();
    String email4 = username4 + "@gmail.com";
    String username5 = "e" + getRandomString();
    String email5 = username5 + "@gmail.com";
    String username6 = "f" + getRandomString();
    String email6 = username6 + "@gmail.com";
    String username7 = "g" + getRandomString();
    String email7 = username7 + "@gmail.com";
    String username8 = "h" + getRandomString();
    String email8 = username8 + "@gmail.com";
    String username9 = "i" + getRandomString();
    String email9 = username9 + "@gmail.com";
    String username10 = "k" + getRandomString();
    String email10 = username10 + "@gmail.com";
    String username11 = "l" + getRandomString();
    String email11 = username11 + "@gmail.com";
    String password = "Aa123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
    addUsers.addUser(username6, password, email6, username6, username6);
    addUsers.addUser(username7, password, email7, username7, username7);
    addUsers.addUser(username8, password, email8, username8, username8);
    addUsers.addUser(username9, password, email9, username9, username9);
    addUsers.addUser(username10, password, email10, username10, username10);
    addUsers.addUser(username11, password, email11, username11, username11);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    ELEMENT_TAB_ACCESS_AND_EDIT.click();
    $(byId("UIRegistration")).selectRadio("open");
    ELEMENT_BUTTON_SAVE_IN_ACCESS_AND_EDIT_TAB.click();
    ELEMENT_OK_BUTTON.click();
    ELEMENT_OK_BUTTON.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    spaceManagement.goToActivityStreamTab();
    activityStream.addActivity(activity1, "");
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

    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space,"");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
    $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(space)).click();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space,"");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
    $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(space)).click();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space,"");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
    $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(space)).click();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space,"");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
    $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(space)).click();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(username5, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space,"");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
    $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(space)).click();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(username6, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space,"");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
    $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(space)).click();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(username7, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space,"");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
    $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(space)).click();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(username8, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space,"");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
    $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(space)).click();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(username9, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space,"");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
    $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(space)).click();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(username10, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space,"");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
    $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    $(byText(space)).click();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(username11, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToAllSpacesTab();
    spaceManagement.searchSpace(space,"");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_JOIN_SPACE).click();
    $(byText(space)).parent().parent().parent().find(byText("Leave")).waitUntil(Condition.appears, Configuration.timeout);
    $(byText(space)).click();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space,"");
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    homePagePlatform.refreshUntil($(ELEMENT_WIKI_TAB),Condition.visible,1000);
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().find(byText("(11)")).scrollTo().hover();
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username11 + " " + username11)).should(Condition.exist);
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username10 + " " + username10)).should(Condition.exist);
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username9 + " " + username9)).should(Condition.exist);
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username8 + " " + username8)).should(Condition.exist);
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username7 + " " + username7)).should(Condition.exist);
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username6 + " " + username6)).should(Condition.exist);
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username5 + " " + username5)).should(Condition.exist);
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username4 + " " + username4)).should(Condition.exist);
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText(username3 + " " + username3)).should(Condition.exist);
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText("2 more...")).should(Condition.exist);
    executeJavaScript("window.scrollBy(0,-2000)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);
    addUsers.deleteUser(username6);
    addUsers.deleteUser(username7);
    addUsers.deleteUser(username8);
    addUsers.deleteUser(username9);
    addUsers.deleteUser(username10);
    addUsers.deleteUser(username11);
  }

}
