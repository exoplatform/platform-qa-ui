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
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
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
  public void test02_CheckNumberWhenUserLikeComment() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);
    homePagePlatform.goToHomePage();
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

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(PLFData.DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(PLFData.DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(3)"));
    activityStream.deleteactivity(activity1);


  }

  @Test
  public void test03_CheckNumberWhenUserUnLikeComment() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, ""); // get the id of activity created
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

    manageLogInOut.signIn(PLFData.DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(PLFData.DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(3)"));
    // connect with user 4 and unlike comment
    manageLogInOut.signIn(PLFData.DATA_USER4, PLFData.DATA_PASS);
    activityStream.likeUnlikeComment(activity1, comment);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().shouldHave(Condition.text("(2)"));
    activityStream.deleteactivity(activity1);


  }

  @Test
  public void test04_CheckListOfUsersWhoLikeComment() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, ""); // get the id of activity created
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

    manageLogInOut.signIn(PLFData.DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(PLFData.DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().find(byText("(3)")).click();
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Jack Miller")).should(Condition.exist);
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("James David")).should(Condition.exist);
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Mary Williams")).should(Condition.exist);
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
    executeJavaScript("window.scrollBy(0,-950)", "");
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test05_DisplayListOfUsersOrderedByTheMostRecentLike() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, ""); // get the id of activity created
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

    manageLogInOut.signIn(PLFData.DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(PLFData.DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().find(byText("(3)")).click();
    ELEMENT_FIRST_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Jack Miller"));
    ELEMENT_SECOND_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("James David"));
    ELEMENT_THIRD_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Mary Williams"));
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
    executeJavaScript("window.scrollBy(0,-950)", "");
    activityStream.deleteactivity(activity1);


  }

  @Test
  public void test06_CheckConnexionStatusButtonOfUsersLikeComment() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, ""); // get the id of activity created
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

    manageLogInOut.signIn(PLFData.DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(PLFData.DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().find(byText("(3)")).click();
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
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
    executeJavaScript("window.scrollBy(0,-950)", "");
    activityStream.deleteactivity(activity1);


  }

  @Test
  @Tag("sabis")
  public void test07_CheckConnexionStatusButtonOfUsersLikeCommentInSpace() {

    String space = "space" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    info("Create a space and invite users");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceSettingManagement.goToMemberTab();
    ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER2);
    $(ELEMENT_SPACE_BTN_INVITE).click();
    refresh();
    ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER3);
    $(ELEMENT_SPACE_BTN_INVITE).click();
    refresh();
    ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER4);
    $(ELEMENT_SPACE_BTN_INVITE).click();
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

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeUnlikeComment(activity1, comment);
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeUnlikeComment(activity1, comment);
    manageLogInOut.signIn(DATA_USER4, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(space);
    activityStream.likeUnlikeComment(activity1, comment);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().find(byText("(3)")).click();
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
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  @Tag("sabis")
  public void test08_checkListOfUsersInToolTipWhen11UsersLikeComment() {
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
    String password = "123456";
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

  @Test
  public void test09_checkFullNameOfUserIsDisplayed() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, ""); // get the id of activity created
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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.refreshUntil($(ELEMENT_ACCOUNT_NAME_LINK),Condition.visible,1000);
    $(byId(ELEMENT_INCON_LIKE_COMMENT.replace("{id}", idBlocComment))).parent().find(byText("(1)")).hover();
    ELEMENT_TOLLTIP_WHO_LIKE_COMMENT.find(byText("Mary Williams")).should(Condition.exist);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test10_likeComentAfterPreviewDocument() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
    $(byText(comment)).should(Condition.exist);
    $(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    $(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).parent().parent().shouldHave(Condition.text("(1)"));
    //assertEquals("rgba(47, 94, 146, 1)",
      //           $(byText(comment)).parent().parent().find(byClassName("uiIconThumbUp")).getCssValue("color"));
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test11_CheckNumberOfUserslikeComentAfterPreviewDocument() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
    $(byText(comment)).should(Condition.exist);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment))
                                                     .parent()
                                                     .parent()
                                                     .find(ELEMENT_ICON_LIKE_COMMENT)
                                                     .click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment))
                                                .parent()
                                                .parent()
                                                .find(ELEMENT_ICON_LIKE_COMMENT)
                                                .parent()
                                                .parent()
                                                .shouldHave(Condition.text("(3)"));
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test12_CheckNumberOfUsersUnlikeComentAfterPreviewDocument() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
    $(byText(comment)).should(Condition.exist);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment))
                                                .parent()
                                                .parent()
                                                .find(ELEMENT_ICON_LIKE_COMMENT)
                                                .parent()
                                                .parent()
                                                .shouldHave(Condition.text("(3)"));
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment))
                                                .parent()
                                                .parent()
                                                .find(ELEMENT_ICON_LIKE_COMMENT)
                                                .parent()
                                                .parent()
                                                .shouldHave(Condition.text("(2)"));
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    activityStream.deleteactivity(activity1);
    refresh();
  }

  @Test
  public void test13_CheckListOfUserslikeComentAfterPreviewDocument() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
    $(byText(comment)).should(Condition.exist);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment))
                                                .parent()
                                                .parent()
                                                .find(ELEMENT_ICON_LIKE_COMMENT)
                                                .parent()
                                                .parent()
                                                .find(byText("(3)"))
                                                .click();
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Jack Miller")).should(Condition.exist);
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("James David")).should(Condition.exist);
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Mary Williams")).should(Condition.exist);
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test14_DisplayListOfUsersOrderedByTheMostRecentLikeAfterPreviewDocument() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
    $(byText(comment)).should(Condition.exist);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment))
                                                .parent()
                                                .parent()
                                                .find(ELEMENT_ICON_LIKE_COMMENT)
                                                .parent()
                                                .parent()
                                                .find(byText("(3)"))
                                                .click();
    ELEMENT_FIRST_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Jack Miller"));
    ELEMENT_SECOND_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("James David"));
    ELEMENT_THIRD_USER_IN_POPUP_WHO_LIKE_COMMENT.shouldHave(Condition.text("Mary Williams"));
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test15_CheckConnectionStatusAfterPreviewDocument() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();

    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
    $(byText(comment)).should(Condition.exist);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment))
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
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    activityStream.deleteactivity(activity1);
  }

  @Test
  @Tag("sabis")
  public void test16_CheckConnectionStatusAfterPreviewDocumentInspace() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String space = "space" + getRandomNumber();

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    ELEMENT_SPACE_FILE_TAB.waitUntil(Condition.visible,Configuration.timeout).click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
    $(byText(comment)).should(Condition.exist);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    spaceSettingManagement.goToMemberTab();
    ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER2);
    $(ELEMENT_SPACE_BTN_INVITE).click();
    refresh();
    ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER3);
    $(ELEMENT_SPACE_BTN_INVITE).click();
    refresh();
    ELEMENT_INPUT_INVITE_USER.sendKeys(DATA_USER4);
    $(ELEMENT_SPACE_BTN_INVITE).click();

    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToAllSpace();
    spaceManagement.acceptAInvitation(space);
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment))
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
                                   .shouldHave(Condition.text("Remove Connection"));
    ELEMENT_POPUP_WHO_LIKED_COMMENT.find(byText("Mary Williams"))
                                   .parent()
                                   .parent()
                                   .find(ELEMENT_BUTTON_IN_POPUP_WHO_LIKE_COMMENT)
                                   .shouldHave(Condition.text("Remove Connection"));
    ELEMENT_ICON_CLONE_POPUP_WHO_LIKED_COMMENT.click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    executeJavaScript("window.scrollBy(0,-2000)", "");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test17_checkNotificationWhenOneUserLikeComment() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String comment1 = " likes your comment.";
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    homePagePlatform.goToHomePage();
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
    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_ICON_NOTIFICATION.click();
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                              .find(byText(comment))
                              .parent()
                              .parent()
                              .shouldHave(Condition.text(DATA_NAME_USER2 + comment1));
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                              .find(byText(DATA_NAME_USER2))
                              .parent()
                              .parent()
                              .find(byText(comment))
                              .should(Condition.exist);
    refresh();
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test18_checkNotificationWhenTWOUserLikeComment() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String comment1 = " like your comment.";
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment);
    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);
    manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_ICON_NOTIFICATION.click();
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                              .find(byText(comment))
                              .parent()
                              .parent()
                              .shouldHave(Condition.text("James David" + " and " + DATA_NAME_USER2 + comment1));
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                              .find(byText(DATA_NAME_USER2))
                              .parent()
                              .parent()
                              .find(byText(comment))
                              .should(Condition.exist);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test19_checkNotificationWhenManyUserLikeComment() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String comment1 = " and 1 others like your comment.";
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment);
    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);
    manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);
    manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeUnlikeComment(activity1, comment);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_ICON_NOTIFICATION.click();
    ELEMENT_NOTIFICATION_POPUP.find(byText(comment))
                              .parent()
                              .parent()
                              .shouldHave(Condition.text(DATA_NAME_USER4 + ", James David" + comment1));
    ELEMENT_ICON_NOTIFICATION.click();
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test20_checkNotificationWhenOneUserLikeCommentInDocumentPreviw() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String comment1 = " likes your comment.";
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    homePagePlatform.goToHomePage();
    ELEMENT_TAB_LINK.click();
    ELEMENT_TAB_LINK.click();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");

    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
    $(byText(comment)).should(Condition.exist);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_ICON_NOTIFICATION.click();
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                              .find(byText(comment))
                              .parent()
                              .parent()
                              .shouldHave(Condition.text(DATA_NAME_USER2 + comment1));
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                              .find(byText(DATA_NAME_USER2))
                              .parent()
                              .parent()
                              .find(byText(comment))
                              .should(Condition.exist);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test21_checkNotificationWhenTWOUserLikeCommentInDocumentPreviw() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String comment1 = " like your comment.";
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    homePagePlatform.goToHomePage();
    ELEMENT_TAB_LINK.click();
    ELEMENT_TAB_LINK.click();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
    $(byText(comment)).should(Condition.exist);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible, Configuration.timeout).click();

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible, Configuration.timeout).click();

    manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible, Configuration.timeout).click();

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_ICON_NOTIFICATION.click();
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                              .find(byText(comment))
                              .parent()
                              .parent()
                              .shouldHave(Condition.text("James David" + " and " + DATA_NAME_USER2 + comment1));
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                              .find(byText(DATA_NAME_USER2))
                              .parent()
                              .parent()
                              .find(byText(comment))
                              .should(Condition.exist);
    refresh();
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test22_checkNotificationWhenManyUserLikeCommentInDocumentPreviw() {
    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String comment1 = " and 1 others like your comment.";
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    connectionsManagement.connectToAUser(DATA_USER3);
    connectionsManagement.connectToAUser(DATA_USER4);
    homePagePlatform.goToHomePage();
    ELEMENT_TAB_LINK.click();
    ELEMENT_TAB_LINK.click();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
    executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
    ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
    $(byText(comment)).should(Condition.exist);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER3, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(DATA_USER4, PLFData.DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile")).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_RIGHT_NAVIGATION_IN_DOCUMENT_PREVIEW.find(byText(comment)).parent().parent().find(ELEMENT_ICON_LIKE_COMMENT).click();
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    ELEMENT_ALERT_NOTIFICATION.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_ICON_NOTIFICATION.click();
    ELEMENT_NOTIFICATION_POPUP.waitUntil(Condition.appears, Configuration.timeout)
                              .find(byText(comment))
                              .parent()
                              .parent()
                              .shouldHave(Condition.text(DATA_NAME_USER4 + ", James David" + comment1));
    ELEMENT_ICON_NOTIFICATION.click();
    activityStream.deleteactivity(activity1);

  }
}
