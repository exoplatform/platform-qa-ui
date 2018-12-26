package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_LINK_VIEW_ALL_COMMENTS;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_ACTIVITY_STREAM_CONTAINER;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_BOX_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACES_LIST;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream.activitiesFormat.*;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManageSites;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;

public class SOCCommentEditTestIT extends Base {

  ActivityStream          activityStream;

  HomePagePlatform        homePagePlatform;

  ManageLogInOut          manageLogInOut;

  NavigationToolbar       navigationToolbar;

  AddUsers                addUsers;

  ForumForumManagement    forumForumManagement;

  ForumTopicManagement    forumTopicManagement;

  ForumCategoryManagement forumCategoryManagement;

  ForumHomePage           forumHomePage;

  WikiHomePage            wikiHomePage;

  WikiManagement          wikiManagement;

  SourceTextEditor        sourceTextEditor;

  WikiValidattions        wikiValidattions;

  CalendarManagement      calendarManagement;

  ConnectionsManagement   connectionsManagement;

  SpaceManagement         spaceManagement;

  SpaceSettingManagement  spaceSettingManagement;

  SpaceHomePage           spaceHomePage;

  PortalManageSites       portalManageSites;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    activityStream = new ActivityStream(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    forumForumManagement = new ForumForumManagement(this);
    forumTopicManagement = new ForumTopicManagement(this);
    forumHomePage = new ForumHomePage(this);
    wikiHomePage = new WikiHomePage(this);
    sourceTextEditor = new SourceTextEditor(this);
    wikiValidattions = new WikiValidattions(this);
    wikiManagement = new WikiManagement(this);
    calendarManagement = new CalendarManagement(this);
    connectionsManagement = new ConnectionsManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    portalManageSites = new PortalManageSites(this);

    spaceManagement = new SpaceManagement(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, DATA_PASS2);
  }

  @Test
  public void test01_EditCommentbyadmin() {
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String newComment = "newComment" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.editComment(comment1, newComment);
    $(byText(activity1)).parent()
                        .parent()
                        .find(byText(comment1 + newComment))
                        .waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test02_CheckEditCommentButton() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.CheckEditComment(comment1, true);

  }

  @Test
  public void test03_addcharacter_EditComment() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String newComment = "a" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.editComment(comment1, newComment);
    $(byText(activity1)).parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byText(comment1 + newComment))
                        .waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test04_CancelEdiComment() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String newComment = "newComment" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.cancelComment(comment1, newComment);
    $(byText(activity1)).parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byText(comment1))
                        .waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test05_addspecialcharacter_EditComment() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment11@@@@$$$" + getRandomNumber();
    String newComment = "newComment" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.editComment(comment1, newComment);
    $(byText(activity1)).parent()
                        .parent()
                        .find(byText(comment1 + newComment))
                        .waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test06_EditCommentanotheruser() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment11@@@@$$$" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "123456";
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(usernametest);
    manageLogInOut.signIn(usernametest, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(PLFData.DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.CheckEditComment(comment1, false);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
    homePagePlatform.goToHomePage();
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usernametest);

  }

  @Test
  public void test07_EditCommentfromspace() {

    String space = "space" + getRandomNumber();
    info("Create a space");
    String comment1 = "comment1" + getRandomNumber();
    String newComment = "newComment" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.editComment(comment1, newComment);
    $(byText(activity1)).parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byText(comment1 + newComment))
                        .waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test08_EditCommentbyowner() {

    String comment1 = "comment1" + getRandomNumber();
    String newComment = "newComment" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "123456";
    String activity1 = "activity1" + getRandomNumber();
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    manageLogInOut.signIn(usernametest, password);
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.editComment(comment1, newComment);
    $(byText(activity1)).parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byText(comment1 + newComment))
                        .waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test09_ckeckEditCommentInSpaceByManager() {

    String comment1 = "comment1" + getRandomNumber();
    String space = "space" + getRandomNumber();
    info("Create a space");
    String activity1 = "activity1" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceSettingManagement.goToMemberTab();
    spaceSettingManagement.inviteUser(usernametest, true, "");
    manageLogInOut.signIn(usernametest, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(space);
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    activityStream.CheckEditCommentInSpaceByManager(comment1, false);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test10_ckeckEditCommentInSpaceByUser() {

    String comment1 = "comment1" + getRandomNumber();
    String space = "space" + getRandomNumber();
    info("Create a space");
    String activity1 = "activity1" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "123456";
    String user = "user" + getRandomString();
    String email1 = user + "@gmail.com";
    String password1 = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    addUsers.addUser(user, password1, email1, user, user);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceSettingManagement.goToMemberTab();
    spaceSettingManagement.inviteUser(usernametest, true, "");
    spaceSettingManagement.inviteUser(user, true, "");
    manageLogInOut.signIn(usernametest, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(space);
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    manageLogInOut.signIn(user, password1);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(space);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    activityStream.CheckEditComment(comment1, false);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test11_ckeckCommentViewer() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    $(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.gotoCommentViewer(comment1);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test12_checkEditCommentInDefaultSkin() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToDefaultSkin();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.CheckEditComment(comment1, true);
    activityStream.deleteactivity(activity1);
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToEntrepriseSkin();

  }

  @Test
  public void test13_CheckCommentEditedWhenRemoveCharacters() {
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.removeCharactersComment(comment1, 3, false);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test14_CheckCommentEditedWhenRemoveAllcomment() {
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.removeCharactersComment(comment1, comment1.length(), true);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test15_CheckOrderOfActivityWhenEditComment() {

    String activity1 = "activity1" + getRandomNumber();
    String activity2 = "activity1" + getRandomNumber();
    String activity3 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String newComment = "newComment" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.addActivity(activity2, "");
    activityStream.addActivity(activity3, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.editComment(comment1, newComment);
    refresh();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(0).find(byText(activity1)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(1).find(byText(activity3)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(2).find(byText(activity2)).should(Condition.exist);

  }

  @Test
  public void test16_CheckMentionUserWhenEditComment() throws Exception {

    String usertest = "usernamea" + getRandomString();
    String email1 = usertest + "@test.com";
    String password = "123456";
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    String newcomment = "newacomment" + getRandomNumber();
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usertest, password, email1, usertest, usertest);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    homePagePlatform.refreshUntil($(byText(activity1)), Condition.visible, 2000);
    activityStream.addCommentWithMentionUser(activity1, usertest, comment1);
    activityStream.editComment(comment1, newcomment);
    $(byText(comment1 + newcomment)).parent().shouldHave(Condition.text(usertest + " " + usertest + " " + comment1 + newcomment));
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usertest);
  }

  @Test
  public void test17_CheckOrderOfCommentWhenEditComment() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();
    String comment3 = "comment3" + getRandomNumber();
    String newComment = "newComment" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.commentActivity(activity1, comment2);
    activityStream.commentActivity(activity1, comment3);
    activityStream.editComment(comment1, newComment);
    activityStream.editComment(comment2, newComment);
    activityStream.editComment(comment3, newComment);
    refresh();
    String idViewComments = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byXpath(ELEMENT_LINK_VIEW_ALL_COMMENTS.replace("{id}", idViewComments))).waitUntil(Condition.visible, Configuration.timeout)
                                                                              .click();
    $(byText(activity1)).parent()
                        .parent()
                        .findAll(byClassName("CommentBlock"))
                        .get(2)
                        .find(byText(comment3 + newComment))
                        .shouldBe(Condition.visible);
    $(byText(activity1)).parent()
                        .parent()
                        .findAll(byClassName("CommentBlock"))
                        .get(1)
                        .find(byText(comment2 + newComment))
                        .shouldBe(Condition.visible);
    $(byText(activity1)).parent()
                        .parent()
                        .findAll(byClassName("CommentBlock"))
                        .get(0)
                        .find(byText(comment1 + newComment))
                        .shouldBe(Condition.visible);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test18_CheckEditCommentWithFormattingTextBold() {
    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String newcomment = "newcomment" + getRandomNumber();
    activityStream.addActivity(activity, "");
    activityStream.addFormattedTextInComment(activity, comment, Add_FormattingBOLD);
    activityStream.editFormattingComment(comment, newcomment);
    assertEquals(Integer.parseInt($(byText(newcomment + comment)).getCssValue("font-weight")), 700);
    activityStream.deleteactivity(activity);
  }

  @Test
  public void test19_CheckEditCommentWithFormattingTextItalic() {
    // Check when this feature will be done
    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String newcomment = "newcomment" + getRandomNumber();
    activityStream.addActivity(activity, "");
    activityStream.addFormattedTextInComment(activity, comment, Add_FormattingITALIC);
    activityStream.editFormattingComment(comment, newcomment);
    assertEquals(($(byText(newcomment + comment)).getCssValue("font-style")), "italic");
    activityStream.deleteactivity(activity);
  }

  @Test
  public void test20_CheckEditCommentWithFormattingNumbredList() {

    // Check when this feature will be done
    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String newcomment = "newcomment" + getRandomNumber();
    activityStream.addActivity(activity, "");
    activityStream.addFormattedTextInComment(activity, comment, Add_Formtting_numbred_List);
    activityStream.editFormattingComment(comment, newcomment);
    assertEquals($(byText(newcomment + comment)).closest("ol").toString(), "<ol>" + newcomment + comment + "</ol>");
    activityStream.deleteactivity(activity);
  }

  @Test
  public void test21_CheckEditCommentWithFormattingBulledList() {

    // Check when this feature will be done
    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String newcomment = "newcomment" + getRandomNumber();
    activityStream.addActivity(activity, "");
    activityStream.addFormattedTextInComment(activity, comment, Add_Formtting_Bulled_List);
    activityStream.editFormattingComment(comment, newcomment);
    assertEquals($(byText(newcomment + comment)).closest("ul").toString(), "<ul>" + newcomment + comment + "</ul>");
    activityStream.deleteactivity(activity);
  }

  @Test
  public void test22_CheckEditCommentWithFormattingBlockquote() {

    // Check when this feature will be done
    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String newcomment = "newcomment" + getRandomNumber();
    activityStream.addActivity(activity, "");
    activityStream.addFormattedTextInComment(activity, comment, Add_FormattingQuote);
    activityStream.editFormattingComment(comment, newcomment);
    assertEquals($(byText(newcomment + comment)).closest("blockquote").toString(),
                 "<blockquote>" + newcomment + comment + "</blockquote>");
    activityStream.deleteactivity(activity);
  }

  @Test
  public void test23_CheckEditCommentWithFormattingRemoveFormat() {

    // Check when this feature will be done
    String activity = "activity" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String newcomment = "newcomment" + getRandomNumber();
    activityStream.addActivity(activity, "");
    activityStream.addFormattedTextInComment(activity, comment, Add_FormttingRemoveFormat);
    activityStream.editComment(comment, newcomment);
    assertEquals(($(byText(comment + newcomment)).getCssValue("font-style")), "normal");
    activityStream.deleteactivity(activity);
  }

  @Test
  public void test24_CheckComment_EnabledBydefault() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.CheckEditCommentbyDefault(comment1, true);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test25_CheckEditCommentWithlinkFormtoolbar() {
    // Check when this feature will be done
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    String newcomment = "comment" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.addFormattedTextInComment(activity1, comment1, Add_Formtting_Link);
    activityStream.editComment(comment1, newcomment);
    $(byText(comment1)).parent().shouldHave(Condition.text(comment1 + comment1 + newcomment));
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test26_CheckEditCommentWithImageFormtoolbar() {
    // Check when this feature will be done
    String activity1 = "activity1" + getRandomNumber();
    String newcomment = "newcomment" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.addFormattedTextInComment(activity1, comment1, Add_Formtting_Image);
    activityStream.editCommentImage(comment1, newcomment);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }
}
