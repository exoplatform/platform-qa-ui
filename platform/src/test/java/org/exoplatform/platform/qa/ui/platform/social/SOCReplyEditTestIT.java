package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_VIEW_ALL_REPLIES_LINK;
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
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
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
public class SOCReplyEditTestIT extends Base {
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
  public void test01_EditReplybyadmin() {
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "Aa123456";
    String activity2 = "activity2" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();
    String reply2 = "reply2" + getRandomNumber();
    String reply3 = "reply3" + getRandomNumber();

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(usernametest);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    sleep(2000);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    activityStream.CheckEditComment(reply1, true);
    activityStream.CheckEditCommentbyDefault(reply1, true);

    activityStream.gotoCommentViewer(comment1);
    activityStream.cancelComment(reply1, newreply);
    $(byText(reply1)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.editComment(reply1, newreply);
    $(byText(reply1 + newreply)).waitUntil(Condition.visible, Configuration.timeout);

    manageLogInOut.signIn(usernametest, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.CheckEditComment(reply1 + newreply, false);
    activityStream.addActivity(activity2, "");
    activityStream.commentActivity(activity2, comment2);
    activityStream.replyToComment(comment2, reply2, usernametest + " " + usernametest);
    activityStream.editComment(reply2, newreply);
    $(byText(reply2 + newreply)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.removeCharactersComment(reply2 + newreply, 3, false);
    activityStream.replyToComment(comment2, reply3, usernametest + " " + usernametest);
    activityStream.removeCharactersComment(reply3, reply3.length(), true);

    executeJavaScript("window.scrollBy(0,-150)");
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usernametest);
    info("Check Edit Reply In Default Skin");
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToDefaultSkin();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    activityStream.CheckEditComment(reply1, true);
    homePagePlatform.goToHomePage();
    activityStream.deleteactivity(activity1);
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToEntrepriseSkin();

  }

  @Test
  public void test08_ckeckEditReplyInSpaceByManager() {

    String space = "space" + getRandomNumber();
    String space0 = "space0" + getRandomNumber();
    info("Create a space");
    String activity1 = "activity1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String usernametest1 = "usernametesta" + getRandomString();
    String email1 = usernametest1 + "@gmail.com";
    String password = "Aa123456";
    String comment1 = "comment1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    String activity0 = "activity0" + getRandomNumber();
    String reply0 = "reply0" + getRandomNumber();
    String comment0 = "comment0" + getRandomNumber();
    String user = "user" + getRandomString();
    String email0 = user + "@gmail.com";

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space0, space0);
    activityStream.addActivity(activity0, "");
    activityStream.commentActivity(activity0, comment0);
    activityStream.replyToComment(comment0, reply0, DATA_NAME_USER1);
    activityStream.editComment(reply0, newreply);
    $(byText(activity0)).parent()
            .parent()
            .parent()
            .parent()
            .find(byText(reply0 + newreply))
            .waitUntil(Condition.visible, Configuration.timeout);
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    addUsers.addUser(usernametest1, password, email1, usernametest1, usernametest1);
    addUsers.addUser(user, password, email0, user, user);
    manageLogInOut.signIn(usernametest1, password);
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
    activityStream.replyToComment(comment1, reply1, usernametest + " " + usernametest);
    manageLogInOut.signIn(user, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(space);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    activityStream.CheckEditActivity(activity1, false);
    activityStream.CheckEditComment(reply1, false);
    manageLogInOut.signIn(usernametest1, password);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    activityStream.CheckEditCommentbySpaceManager(reply1, false);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usernametest);
    addUsers.deleteUser(usernametest1);
    addUsers.deleteUser(user);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space0, false);
  }

  @Test
  @Tag("SOC-6147")
  public void test15_CheckEditReplyWithFormattingText() {
    // Check when this feature will be done
    String activity1 = "activity" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String reply2 = "reply2" + getRandomNumber();
    String reply3 = "reply3" + getRandomNumber();
    String reply4 = "reply4" + getRandomNumber();
    String reply5 = "reply5" + getRandomNumber();
    String reply6 = "reply6" + getRandomNumber();
    String reply7 = "reply7" + getRandomNumber();
    String reply8 = "reply8" + getRandomNumber();
    String activity2 = "activity2" + getRandomNumber();
    String activity3 = "activity3" + getRandomNumber();
    String comment0 = "comment0" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.addFormattedTextInReply(comment1, reply1, DATA_NAME_USER1, Add_FormattingBOLD);
    activityStream.editFormattingComment(reply1, newreply);
    assertEquals(Integer.parseInt($(byText(newreply + reply1)).getCssValue("font-weight")), 700);
    activityStream.addFormattedTextInReply(comment1, reply2, DATA_NAME_USER1, Add_FormattingITALIC);
    activityStream.editFormattingComment(reply2, newreply);
    assertEquals(($(byText(newreply + reply2)).getCssValue("font-style")), "italic");
    activityStream.addFormattedTextInReply(comment1, reply3, DATA_NAME_USER1, Add_Formtting_numbred_List);
    activityStream.editFormattingComment(reply3, newreply);
    assertEquals($(byText(newreply + reply3)).closest("ol").toString(), "<ol>" + newreply + reply3 + "</ol>");
    activityStream.addFormattedTextInReply(comment1, reply4, DATA_NAME_USER1, Add_Formtting_Bulled_List);
    activityStream.editFormattingComment(reply4, newreply);
    assertEquals($(byText(newreply + reply4)).closest("ul").toString(), "<ul>" + newreply + reply4 + "</ul>");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity3, "");
    activityStream.commentActivity(activity3, comment1);
    activityStream.addFormattedTextInReply(comment1, reply5, DATA_NAME_USER1, Add_FormattingQuote);
    activityStream.editFormattingComment(reply5, newreply);
    assertEquals($(byText(newreply + reply5)).closest("blockquote").toString(),
            "<blockquote>" + newreply + reply5 + "</blockquote>");
    activityStream.addFormattedTextInReply(comment1, reply6, DATA_NAME_USER1, Add_FormttingRemoveFormat);
    activityStream.editComment(reply6, newreply);
    assertEquals(($(byText(reply6 + newreply)).getCssValue("font-style")), "normal");
    activityStream.addFormattedTextInReply(comment1, reply7, DATA_NAME_USER1, Add_Formtting_Link);
    activityStream.editComment(reply7, newreply);
    $(byText(reply7)).parent().shouldHave(Condition.text(reply7 + reply7 + newreply));
    activityStream.addFormattedTextInReply(comment1, reply8, DATA_NAME_USER1, Add_Formtting_Image);
    activityStream.editCommentImage(reply8, newreply);
    homePagePlatform.goToHomePage();
    info("Check Order Of Reply When Edit Reply");
    activityStream.addActivity(activity2, "");
    activityStream.commentActivity(activity2, comment0);
    activityStream.replyToComment(comment0, reply1, DATA_NAME_USER1);
    activityStream.replyToComment(comment0, reply2, DATA_NAME_USER1);
    activityStream.replyToComment(comment0, reply3, DATA_NAME_USER1);
    activityStream.editComment(reply1, newreply);
    activityStream.editComment(reply2, newreply);
    activityStream.editComment(reply3, newreply);
    String idBlocComment = $(byText(activity2)).parent()
            .parent()
            .parent()
            .find(byText(comment0))
            .parent()
            .parent()
            .parent()
            .parent()
            .getAttribute("id")
            .split("commentContainercomment")[1];

    $(byText(comment0)).parent().parent().findAll(byClassName("CommentBlock")).get(2).find(byText(reply3 + newreply));
    $(byText(comment0)).parent().parent().findAll(byClassName("CommentBlock")).get(1).find(byText(reply2 + newreply));
    $(byText(comment0)).parent().parent().findAll(byClassName("CommentBlock")).get(0).find(byText(reply1 + newreply));

    homePagePlatform.goToHomePage();
    activityStream.deleteactivity(activity2);
    activityStream.deleteactivity(activity3);
    activityStream.deleteactivity(activity1);

  }

}
