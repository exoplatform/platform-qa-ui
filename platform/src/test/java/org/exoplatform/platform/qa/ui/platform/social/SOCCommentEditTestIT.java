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
import org.junit.jupiter.api.Tag;
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

@Tag("sniff")
@Tag("social")
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
    String activity2 = "activity1" + getRandomNumber();
    String activity3 = "activity1" + getRandomNumber();
    String activity4 = "activity1" + getRandomNumber();
    String activity5 = "activity1" + getRandomNumber();
    String activity6 = "activity1" + getRandomNumber();
    String activity7 = "activity1" + getRandomNumber();
    String activity8 = "activity1" + getRandomNumber();
    String activity9 = "activity1" + getRandomNumber();
    String activity10 = "activity1" + getRandomNumber();
    String activity11 = "activity1" + getRandomNumber();
    String activity12 = "activity1" + getRandomNumber();
    String activity13 = "activity1" + getRandomNumber();
    String activity14 = "activity1" + getRandomNumber();
    String activity15 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String comment2 = "comment11@@@@$$$" + getRandomNumber();
    String comment3 = "comment1" + getRandomNumber();
    String comment4 = "comment1" + getRandomNumber();
    String newComment = "newComment" + getRandomNumber();
    String newComment1 = "a" + getRandomNumber();
    String newComment2 = "newComment" + getRandomNumber();
    String newComment3 = "newComment" + getRandomNumber();
    String newComment4 = "newcomment" + getRandomNumber();
    String comment5 = "comment1" + getRandomNumber();
    String comment6 = "comment2" + getRandomNumber();
    String comment7 = "comment3" + getRandomNumber();
    String comment8 = "comment" + getRandomNumber();

    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    info("Ckeck Comment Viewer");
    $(byText(comment1)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.gotoCommentViewer(comment1);
    info("Cancel Edit Comment");
    activityStream.cancelComment(comment1, newComment);
    $(byText(activity1)).parent()
            .parent()
            .parent()
            .parent()
            .find(byText(comment1))
            .waitUntil(Condition.visible, Configuration.timeout);
    info("Check Edit Comment Button");
    activityStream.CheckEditComment(comment1, true);
    activityStream.gotoCommentViewer(comment1);
    activityStream.editComment(comment1, newComment);
    $(byText(activity1)).parent()
                        .parent()
                        .find(byText(comment1 + newComment))
                        .waitUntil(Condition.visible, Configuration.timeout);
    info("Add character Edit Comment");
    activityStream.gotoCommentViewer(comment1 + newComment);
    activityStream.editComment(comment1 + newComment, newComment1);
    $(byText(activity1)).parent()
            .parent()
            .parent()
            .parent()
            .find(byText(comment1 + newComment + newComment1))
            .waitUntil(Condition.visible, Configuration.timeout);
    info("Add Special Character Edit Comment");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity2, "");
    activityStream.commentActivity(activity2, comment2);
    activityStream.editComment(comment2, newComment2);
    $(byText(activity2)).parent()
            .parent()
            .find(byText(comment2 + newComment2))
            .waitUntil(Condition.visible, Configuration.timeout);
    info("Check Comment Edited When Remove Characters");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity3, "");
    activityStream.commentActivity(activity3, comment3);
    activityStream.removeCharactersComment(comment3, 3, false);
    info("Check Order Of Activity When Edit Comment");
    refresh();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(0).find(byText(activity3)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(1).find(byText(activity2)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(2).find(byText(activity1)).should(Condition.exist);
    info("Check Comment Edited When Remove All Comments");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity4, "");
    activityStream.commentActivity(activity4, comment4);
    activityStream.removeCharactersComment(comment4, comment4.length(), true);
    info("Check Order Of Comment When Edit Comment");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity5, "");
    activityStream.commentActivity(activity5, comment5);
    activityStream.commentActivity(activity5, comment6);
    activityStream.commentActivity(activity5, comment7);
    activityStream.editComment(comment5, newComment3);
    activityStream.editComment(comment6, newComment3);
    activityStream.editComment(comment7, newComment3);
    refresh();
    String idViewComments = $(byText(activity5)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byXpath(ELEMENT_LINK_VIEW_ALL_COMMENTS.replace("{id}", idViewComments))).waitUntil(Condition.visible, Configuration.timeout)
            .click();
    $(byText(activity5)).parent()
            .parent()
            .findAll(byClassName("CommentBlock"))
            .get(2)
            .find(byText(comment7 + newComment3))
            .shouldBe(Condition.visible);
    $(byText(activity5)).parent()
            .parent()
            .findAll(byClassName("CommentBlock"))
            .get(1)
            .find(byText(comment6 + newComment3))
            .shouldBe(Condition.visible);
    $(byText(activity5)).parent()
            .parent()
            .findAll(byClassName("CommentBlock"))
            .get(0)
            .find(byText(comment5 + newComment3))
            .shouldBe(Condition.visible);
    info("Check Edit Comment With Formatting Text Bold");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity6, "");
    activityStream.addFormattedTextInComment(activity6, comment8, Add_FormattingBOLD);
    activityStream.editFormattingComment(comment8, newComment4);
    assertEquals(Integer.parseInt($(byText(newComment4 + comment8)).getCssValue("font-weight")), 700);
    info("Check Edit Comment With Formatting Text Italic");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity7, "");
    activityStream.addFormattedTextInComment(activity7, comment8, Add_FormattingITALIC);
    activityStream.editFormattingComment(comment8, newComment4);
    assertEquals(($(byText(newComment4 + comment8)).getCssValue("font-style")), "italic");
    info("Check Edit Comment With Formatting Numbred List");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity8, "");
    activityStream.addFormattedTextInComment(activity8, comment8, Add_Formtting_numbred_List);
    activityStream.editFormattingComment(comment8, newComment4);
    assertEquals($(byText(newComment4 + comment8)).closest("ol").toString(), "<ol>" + newComment4 + comment8 + "</ol>");
    info("Check Edit Comment With Formatting Bulled List");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity9, "");
    activityStream.addFormattedTextInComment(activity9, comment8, Add_Formtting_Bulled_List);
    activityStream.editFormattingComment(comment8, newComment4);
    assertEquals($(byText(newComment4 + comment8)).closest("ul").toString(), "<ul>" + newComment4 + comment8 + "</ul>");
    info("Check Edit Comment With Formatting Block Quote");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity10, "");
    activityStream.addFormattedTextInComment(activity10, comment8, Add_FormattingQuote);
    activityStream.editFormattingComment(comment8, newComment4);
    assertEquals($(byText(newComment4 + comment8)).closest("blockquote").toString(),
            "<blockquote>" + newComment4 + comment8 + "</blockquote>");
    info("Check Edit Comment With Formatting Remove Format");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity11, "");
    activityStream.addFormattedTextInComment(activity11, comment8, Add_FormttingRemoveFormat);
    activityStream.editComment(comment8, newComment4);
    assertEquals(($(byText(comment8 + newComment4)).getCssValue("font-style")), "normal");
    info("Check Comment Enabled By Default");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity12, "");
    activityStream.commentActivity(activity12, comment8);
    activityStream.CheckEditCommentbyDefault(comment8, true);
    info("Check Edit Comment With link Form Toolbar");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity13, "");
    activityStream.addFormattedTextInComment(activity13, comment8, Add_Formtting_Link);
    activityStream.editComment(comment8, newComment4);
    $(byText(comment8)).parent().shouldHave(Condition.text(comment8 + comment8 + newComment4));
    info("Check Edit Comment With Image Form Toolbar");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity14, "");
    activityStream.addFormattedTextInComment(activity14, comment8, Add_Formtting_Image);
    activityStream.editCommentImage(comment8, newComment4);
    homePagePlatform.goToHomePage();
    activityStream.deleteactivity(activity14);
    activityStream.deleteactivity(activity13);
    activityStream.deleteactivity(activity12);
    activityStream.deleteactivity(activity11);
    activityStream.deleteactivity(activity10);
    activityStream.deleteactivity(activity9);
    activityStream.deleteactivity(activity8);
    activityStream.deleteactivity(activity7);
    activityStream.deleteactivity(activity6);
    activityStream.deleteactivity(activity5);
    activityStream.deleteactivity(activity4);
    activityStream.deleteactivity(activity3);
    activityStream.deleteactivity(activity1);
    activityStream.deleteactivity(activity2);
    info("Check Edit Comment In Default Skin");
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToDefaultSkin();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity15, "");
    activityStream.commentActivity(activity15, comment8);
    activityStream.CheckEditComment(comment8, true);
    activityStream.deleteactivity(activity15);
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToEntrepriseSkin();

  }

  @Test
  public void test02_EditCommentAnOtherUser() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment11@@@@$$$" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String usertest = "usernamea" + getRandomString();
    String email2 = usertest + "@test.com";
    String activity2 = "activity1" + getRandomNumber();
    String comment2 = "comment" + getRandomNumber();
    String newcomment = "newacomment" + getRandomNumber();
    String password = "Aa123456";
    String comment3 = "comment1" + getRandomNumber();
    String newComment3 = "newComment" + getRandomNumber();
    String activity3 = "activity1" + getRandomNumber();

    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    addUsers.addUser(usertest, password, email2, usertest, usertest);
    homePagePlatform.goToHomePage();
    info("Check Mention User When Edit Comment");
    activityStream.addActivity(activity2, "");
    homePagePlatform.refreshUntil($(byText(activity2)), Condition.visible, 2000);
    activityStream.addCommentWithMentionUser(activity2, usertest, comment2);
    activityStream.editComment(comment2, newcomment);
    $(byText(comment2 + newcomment)).parent().shouldHave(Condition.text(usertest + " " + usertest + " " + comment2 + newcomment));
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
    info("Edit Comment By Owner");
    manageLogInOut.signIn(usernametest, password);
    activityStream.addActivity(activity3, "");
    activityStream.commentActivity(activity3, comment3);
    activityStream.editComment(comment3, newComment3);
    $(byText(activity3)).parent()
            .parent()
            .parent()
            .parent()
            .find(byText(comment3 + newComment3))
            .waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity3);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usertest);
    addUsers.deleteUser(usernametest);

  }

  @Test
  public void test03_ckeckEditCommentInSpaceByUser() {

    String comment1 = "comment1" + getRandomNumber();
    String space = "space" + getRandomNumber();
    info("Create a space");
    String activity1 = "activity1" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "Aa123456";
    String user = "user" + getRandomString();
    String email1 = user + "@gmail.com";
    String password1 = "Aa123456";
    String comment2 = "comment1" + getRandomNumber();
    String newComment2 = "newComment" + getRandomNumber();
    String activity2 = "activity1" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    addUsers.addUser(user, password1, email1, user, user);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    info("Edit Comment From Space");
    activityStream.addActivity(activity2, "");
    activityStream.commentActivity(activity2, comment2);
    activityStream.editComment(comment2, newComment2);
    $(byText(activity2)).parent()
            .parent()
            .parent()
            .parent()
            .find(byText(comment2 + newComment2))
            .waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
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
    ELEMENT_SPACES_LIST.find(byText(space)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    activityStream.CheckEditComment(comment1, false);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    info("Ckeck Edit Comment In Space By Manager");
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    activityStream.CheckEditCommentInSpaceByManager(comment1, false);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

}
