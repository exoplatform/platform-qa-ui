package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
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
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    activityStream.editComment(reply1, newreply);
    $(byText(reply1 + newreply)).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test02_CheckEditReplyButton() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    activityStream.CheckEditComment(reply1, true);

  }

  @Test
  public void test03_addcharacter_EditReply() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    activityStream.editComment(reply1, newreply);
    $(byText(reply1 + newreply)).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test04_CancelEdiReply() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    activityStream.cancelComment(reply1, newreply);
    $(byText(reply1)).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test05_EditReplyanotheruser() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment11@@@@$$$" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String reply1 = "reply1" + getRandomNumber();
    String email = usernametest + "@gmail.com";
    String password = "123456";
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(usernametest);
    manageLogInOut.signIn(usernametest, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.CheckEditComment(reply1, false);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usernametest);

  }

  @Test
  public void test06_EditReplyfromspace() {

    String space = "space" + getRandomNumber();
    info("Create a space");
    String comment1 = "comment1" + getRandomNumber();
    String activity1 = "activity1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    activityStream.editComment(reply1, newreply);
    $(byText(activity1)).parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byText(reply1 + newreply))
                        .waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test07_EditReplybyowner() {

    String comment1 = "comment1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
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
    activityStream.replyToComment(comment1, reply1, usernametest + " " + usernametest);
    activityStream.editComment(reply1, newreply);
    manageLogInOut.signIn(usernametest, password);
    $(byText(reply1 + newreply)).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usernametest);
  }

  @Test
  public void test08_ckeckEditReplyInSpaceByManager() {

    String space = "space" + getRandomNumber();
    info("Create a space");
    String activity1 = "activity1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String usernametest1 = "usernametesta" + getRandomString();
    String email1 = usernametest1 + "@gmail.com";
    String password = "123456";
    String comment1 = "comment1" + getRandomNumber();
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    addUsers.addUser(usernametest1, password, email1, usernametest1, usernametest1);
    manageLogInOut.signIn(usernametest1, password);
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
    activityStream.replyToComment(comment1, reply1, usernametest + " " + usernametest);
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
  }

  @Test
  public void test09_ckeckEditReplyInSpaceByUser() {

    String space = "space" + getRandomNumber();
    info("Create a space");
    String activity1 = "activity1" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "123456";
    String user = "user" + getRandomString();
    String email1 = user + "@gmail.com";
    String password1 = "123456";
    String comment1 = "comment1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
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
    activityStream.replyToComment(comment1, reply1, usernametest + " " + usernametest);
    manageLogInOut.signIn(user, password1);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(space);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    activityStream.CheckEditActivity(activity1, false);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usernametest);
    addUsers.deleteUser(user);

  }

  @Test
  public void test10_ckeckEditReplyInSpaceByUser() {

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
    String reply1 = "reply1" + getRandomNumber();
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
    activityStream.replyToComment(comment1, reply1, usernametest + " " + usernametest);
    manageLogInOut.signIn(user, password1);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(space);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    activityStream.CheckEditComment(reply1, false);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usernametest);
    addUsers.deleteUser(user);

  }

  @Test
  public void test11_ckeckReplyViewer() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    $(byText(reply1)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.gotoCommentViewer(reply1);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test12_checkEditReplyInDefaultSkin() {

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToDefaultSkin();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    activityStream.CheckEditComment(reply1, true);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToEntrepriseSkin();

  }

  @Test
  public void test13_CheckReplyEditedWhenRemoveCharacters() {
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    manageLogInOut.signIn(usernametest, password);
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, usernametest + " " + usernametest);
    activityStream.removeCharactersComment(reply1, 3, false);
    executeJavaScript("window.scrollBy(0,-150)");
    activityStream.deleteactivity(activity1);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usernametest);
  }

  @Test
  public void test14_CheckReplyEditedWhenRemoveAllreply() {
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    manageLogInOut.signIn(usernametest, password);
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, usernametest + " " + usernametest);
    activityStream.removeCharactersComment(reply1, reply1.length(), true);
    manageLogInOut.signIn(usernametest, password);
    activityStream.deleteactivity(activity1);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usernametest);
  }

  @Test

  public void test15_CheckEditReplyWithFormattingTextBold() {
    // Check when this feature will be done
    String activity1 = "activity" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.addFormattedTextInReply(comment1, reply1, DATA_NAME_USER1, Add_FormattingBOLD);
    activityStream.editFormattingComment(reply1, newreply);
    assertEquals(Integer.parseInt($(byText(newreply + reply1)).getCssValue("font-weight")), 700);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test16_CheckReply_EnabledBydefault() {
    // Check when this feature will be done
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    activityStream.CheckEditCommentbyDefault(reply1, true);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);

  }

  @Test
  @Tag("sabis")
  public void test17_CheckEditReplyWithFormattingTextItalic() {
    // Check when this feature will be done
    String activity1 = "activity" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.addFormattedTextInReply(comment1, reply1, DATA_NAME_USER1, Add_FormattingITALIC);
    activityStream.editFormattingComment(reply1, newreply);
    assertEquals(($(byText(newreply + reply1)).getCssValue("font-style")), "italic");
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test

  public void test18_CheckEditReplyWithFormattingTextNumbredList() {
    // Check when this feature will be done
    String activity1 = "activity" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.addFormattedTextInReply(comment1, reply1, DATA_NAME_USER1, Add_Formtting_numbred_List);
    activityStream.editFormattingComment(reply1, newreply);
    assertEquals($(byText(newreply + reply1)).closest("ol").toString(), "<ol>" + newreply + reply1 + "</ol>");

    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test

  public void test19_CheckEditReplyWithFormattingTextBulledList() {
    // Check when this feature will be done
    String activity1 = "activity" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.addFormattedTextInReply(comment1, reply1, DATA_NAME_USER1, Add_Formtting_Bulled_List);
    activityStream.editFormattingComment(reply1, newreply);
    assertEquals($(byText(newreply + reply1)).closest("ul").toString(), "<ul>" + newreply + reply1 + "</ul>");
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test

  public void test20_CheckEditReplyWithFormattingTextBlockquote() {
    // Check when this feature will be done
    String activity1 = "activity" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.addFormattedTextInReply(comment1, reply1, DATA_NAME_USER1, Add_FormattingQuote);
    activityStream.editFormattingComment(reply1, newreply);
    assertEquals($(byText(newreply + reply1)).closest("blockquote").toString(),
                 "<blockquote>" + newreply + reply1 + "</blockquote>");
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);

  }

  @Test

  public void test21_CheckEditReplyWithFormattingTextRemoveFormat() {
    // Check when this feature will be done
    String activity1 = "activity" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.addFormattedTextInReply(comment1, reply1, DATA_NAME_USER1, Add_FormttingRemoveFormat);
    activityStream.editComment(reply1, newreply);
    assertEquals(($(byText(reply1 + newreply)).getCssValue("font-style")), "normal");
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test22_CheckEditReplyWithlinkFormtoolbar() {
    // Check when this feature will be done
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.addFormattedTextInReply(comment1, reply1, DATA_NAME_USER1, Add_Formtting_Link);
    activityStream.editComment(reply1, newreply);
    $(byText(reply1)).parent().shouldHave(Condition.text(reply1 + reply1 + newreply));
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test23_CheckEditReplyWithImageFormtoolbar() {
    // Check when this feature will be done

    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String newreply = "reply1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.addFormattedTextInReply(comment1, reply1, DATA_NAME_USER1, Add_Formtting_Image);
    activityStream.editCommentImage(reply1, newreply);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test
  @BugInPLF("SOC-6147")
  public void test24_CheckOrderOfREplyWhenEditReply() {

    String activity1 = "activity1" + getRandomNumber();
    String reply1 = "reply1" + getRandomNumber();
    String reply2 = "reply2" + getRandomNumber();
    String reply3 = "reply3" + getRandomNumber();
    String newreply = "newreply" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    activityStream.replyToComment(comment1, reply1, DATA_NAME_USER1);
    activityStream.replyToComment(comment1, reply2, DATA_NAME_USER1);
    activityStream.replyToComment(comment1, reply3, DATA_NAME_USER1);
    activityStream.editComment(reply1, newreply);
    activityStream.editComment(reply2, newreply);
    activityStream.editComment(reply3, newreply);
    String idBlocComment = $(byText(activity1)).parent()
                                               .parent()
                                               .parent()
                                               .find(byText(comment1))
                                               .parent()
                                               .parent()
                                               .parent()
                                               .parent()
                                               .getAttribute("id")
                                               .split("commentContainercomment")[1];
    $(byId(ELEMENT_VIEW_ALL_REPLIES_LINK.replace("{id}", idBlocComment))).waitUntil(Condition.appear, Configuration.timeout)
                                                                         .click();
    $(byText(comment1)).parent().parent().findAll(byClassName("CommentBlock")).get(2).find(byText(reply3 + newreply));
    $(byText(comment1)).parent().parent().findAll(byClassName("CommentBlock")).get(1).find(byText(reply2 + newreply));
    $(byText(comment1)).parent().parent().findAll(byClassName("CommentBlock")).get(0).find(byText(reply1 + newreply));
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);

  }

}
