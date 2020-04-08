package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_INPUT_TEXT;
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
public class SOCActivityEditTestIT extends Base {
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
  public void test01_EditActivitybyadmin() {

    String activity1 = "activity1" + getRandomNumber();
    String activity2 = "activity1@@@@$$$" + getRandomNumber();
    String activity3 = "activity1" + getRandomNumber();
    String activity4 = "activity1" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    String newActivity2 = "a" + getRandomNumber();
    String newactivity3 = "newactivity" + getRandomNumber();
    String newActivity4 = "newActivity" + getRandomNumber();
    String activity5 = "activity1" + getRandomNumber();
    String activity6 = "activity1" + getRandomNumber();
    String activity7 = "activity1" + getRandomNumber();
    String activity8 = "activity1" + getRandomNumber();
    String activity9 = "activity1" + getRandomNumber();
    String activity10 = "activity1" + getRandomNumber();

    activityStream.addActivity(activity1, "");
    info("Cancel Edit Activity");
    activityStream.cancelActivity(activity1, newActivity);
    $(byText(activity1)).waitUntil(Condition.visible, Configuration.timeout);
    info("Check Edit Activity Button");
    activityStream.CheckEditActivity(activity1, true);
    info("Check Activity Viewer");
    activityStream.gotoActivityViewer(activity1);
    activityStream.editActivity(activity1, newActivity);
    $(byText(activity1 + newActivity)).waitUntil(Condition.visible, Configuration.timeout);
    info("Add Character Edit Activity");
    activityStream.editActivity(activity1 + newActivity, newActivity2);
    $(byText(activity1 + newActivity + newActivity2)).waitUntil(Condition.visible, Configuration.timeout);
    info("Add Special Character Edit Activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity2, "");
    info("Check Activities Enabled By Default");
    activityStream.CheckEditActivityBydefault(activity2, true);
    activityStream.gotoActivityViewer(activity2);
    activityStream.editActivity(activity2, newActivity);
    $(byText(activity2 + newActivity)).waitUntil(Condition.visible, Configuration.timeout);
    info("Check Activity Edited When Remove Characters");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity3, "");
    activityStream.removeCharactersActivity(activity3, 3, false);
    info("Check Activity Edited When Remove All Characters");
    activityStream.addActivity(activity4, "");
    activityStream.removeCharactersActivity(activity4, activity4.length(), true);
    info("Check Order Of The Activities When Edit Activity");
    activityStream.addActivity(activity5, "");
    activityStream.addActivity(activity6, "");
    activityStream.addActivity(activity7, "");
    activityStream.editActivity(activity5, newactivity3);
    refresh();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY)
            .get(0)
            .find(byText(activity5 + newactivity3))
            .should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(1).find(byText(activity7)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(2).find(byText(activity6)).should(Condition.exist);
    info("Edit Activity With Link");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity8, "https://www.google.com/");
    activityStream.editActivity(activity8, newActivity4);
    $(byText(activity8 + newActivity4)).waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToHomePage();
    info("Check Edit Activity With File");
    activityStream.Upload_File_With_Text(activity9);
    activityStream.CheckEditActivityBydefault(activity9, true);
    info("Check Edit Activity In Default Skin");
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToDefaultSkin();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity10, "");
    activityStream.CheckEditActivity(activity10, true);
    homePagePlatform.goToHomePage();
    activityStream.deleteactivity(activity10);
    activityStream.deleteactivity(activity9);
    activityStream.deleteactivity(activity8 + newActivity4);
    activityStream.deleteactivity(activity5 + newactivity3);
    activityStream.deleteactivity(activity6);
    activityStream.deleteactivity(activity7);
    activityStream.deleteactivity(activity4);
    activityStream.deleteactivity(activity3.substring(0, activity3.length() - 3));
    activityStream.deleteactivity(activity1 + newActivity + newActivity2);
    activityStream.deleteactivity(activity2 + newActivity);
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToEntrepriseSkin();

  }

  @Test
  @Tag("sab")
  public void test02_CheckEditActivity() {

    String activity = "activity" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    String activity2 = "activity" + getRandomNumber();
    String newActivity2 = "newActivity" + getRandomNumber();
    String activity3 = "activity" + getRandomNumber();
    String newActivity3 = "newActivity" + getRandomNumber();
    String activity4 = "activity" + getRandomNumber();
    String newActivity4 = "newActivity" + getRandomNumber();
    String activity5 = "activity" + getRandomNumber();
    String newActivity5 = "newActivity" + getRandomNumber();
    String activity6 = "activity" + getRandomNumber();
    String newActivity6 = "newActivity" + getRandomNumber();
    String activity7 = "activity" + getRandomNumber();
    String newActivity7 = "newActivity" + getRandomNumber();
    String activity8 = "activity1" + getRandomNumber();
    String newactivity8 = "activity1" + getRandomNumber();

    info("Check Edit Activity With Formatting Text Bold");
    activityStream.addFormattingText(activity, Add_FormattingBOLD);
    activityStream.editFormatedActivity(activity, newActivity);
    assertEquals(Integer.parseInt($(byText(activity + newActivity)).getCssValue("font-weight")), 700);
    info("Check Edit Activity With Formatting Text Italic");
    activityStream.addFormattingText(activity2, Add_FormattingITALIC);
    activityStream.editFormatedActivity(activity2, newActivity2);
    assertEquals(($(byText(activity2 + newActivity2)).getCssValue("font-style")), "italic");
    info("Check Edit Activity With Formatting Text Numbred List");
    activityStream.addFormattingText(activity3, Add_Formtting_numbred_List);
    activityStream.editFormatedActivity(activity3, newActivity3);
    assertEquals($(byText(activity3 + newActivity3)).getCssValue("list-style-position"), "inside");
    info("Check Edit Activity With Formatting Text Bulled List");
    activityStream.addFormattingText(activity4, Add_Formtting_Bulled_List);
    activityStream.editFormatedActivity(activity4, newActivity4);
    assertEquals($(byText(activity4 + newActivity4)).getCssValue("display"), "list-item");
    info("Check Edit Activity With Formatting Text Block quote");
    activityStream.addFormattingText(activity5, Add_FormattingQuote);
    activityStream.editFormatedActivity(activity5, newActivity5);
    assertEquals($(byText(activity5 + newActivity5)).getCssValue("display"), "block");
    info("Check Edit Activity With Formatting Text Remove Format");
    activityStream.addFormattingText(activity6, Add_FormttingRemoveFormat);
    activityStream.editActivity(activity6, newActivity6);
    assertEquals(($(byText(activity6 + newActivity6)).getCssValue("font-style")), "normal");
    info("Check Edit Activity With Link Formmicroblog");
    activityStream.addFormattingText(activity7, Add_Formtting_Link);
    activityStream.editActivity(activity7, newActivity7);
    $(byText(activity7 + newActivity7)).waitUntil(Condition.visible, Configuration.timeout);
    info("Check Edit Activity With Image Formmicroblog");
    activityStream.addFormattingText(activity8, Add_Formtting_Image);
    activityStream.editActivityImage(activity8, newactivity8);

    activityStream.deleteactivity(newactivity8 + activity8);
    activityStream.deleteactivity(activity7 + newActivity7);
    activityStream.deleteactivity(activity6 + newActivity6);
    getExoWebDriver().getWebDriver().navigate().refresh();
    activityStream.deleteFormatedActivity(activity5 + newActivity5);
    getExoWebDriver().getWebDriver().navigate().refresh();
    activityStream.deleteFormatedActivity(activity4 + newActivity4);
    getExoWebDriver().getWebDriver().navigate().refresh();
    activityStream.deleteFormatedActivity(activity3 + newActivity3);
    getExoWebDriver().getWebDriver().navigate().refresh();
    activityStream.deleteFormatedActivity(activity2 + newActivity2);
    getExoWebDriver().getWebDriver().navigate().refresh();
    activityStream.deleteFormatedActivity(activity + newActivity);

  }

  @Test
  public void test03_checkEditForumThenCheckEditWikiDisable() throws Exception {

    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();

    homePagePlatform.goToForum();
    info("Check Edit Forum");
    info("Add a topic");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    forumForumManagement.addForumSimple(name2, "", desc);
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "eXo-Platform.png");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(topic)), Condition.visible, 1000);
    activityStream.checkNotEditInTopicActivity(topic);
    info("Check Edit Wiki Disable");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title);
    homePagePlatform.goToHomePage();
    activityStream.checkNotEditInWikiActivity(title);
    homePagePlatform.goToForum();
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(name);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);

  }

  @Test
  public void test04_EditActivityanotheruser() {

    String activity1 = "activity1" + getRandomNumber();
    String activity2 = "activity1" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    String usertest = "usernamea" + getRandomString();
    String email1 = usertest + "@test.com";
    String password = "123456";
    String activity3 = "activity1" + getRandomNumber();
    String newactivity3 = "newactivity" + getRandomNumber();
    String newActivity4 = "newActivity" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";

    info("Add new user");
    activityStream.addActivity(activity1, "");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usertest, password, email1, usertest, usertest);
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(usernametest);
    info("CheckMention User When Edit Activity");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil(ELEMENT_ACTIVITY_INPUT_TEXT, Condition.visible, 2000);
    activityStream.mentionUserActivity(usertest, activity3);
    activityStream.editActivity(activity3, newactivity3);
    $(byText(activity3 + newactivity3)).parent()
            .shouldHave(Condition.text(usertest + " " + usertest + " " + activity3 + newactivity3));
    manageLogInOut.signIn(usernametest, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(PLFData.DATA_USER1);
    homePagePlatform.goToHomePage();
    info("Edit Activity By Owner");
    activityStream.addActivity(activity2, "");
    activityStream.editActivity(activity2, newActivity);
    $(byText(activity2 + newActivity)).waitUntil(Condition.visible, Configuration.timeout);
    info("Edit Activity By Admin To Normal User");
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.gotoActivityViewer(activity2 + newActivity);
    activityStream.editActivity(activity2 + newActivity, newActivity4);
    manageLogInOut.signIn(usernametest, password);
    activityStream.deleteactivity(activity2 + newActivity + newActivity4);
    homePagePlatform.goToHomePage();
    activityStream.CheckEditActivity(activity1, false);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usertest);
    addUsers.deleteUser(usernametest);

  }

  @Test
  public void test05_CkeckEditAcivityInSpaceByUser() {

    String space = "space" + getRandomNumber();
    info("Create a space");
    String activity1 = "activity1" + getRandomNumber();
    String activity2 = "activity1" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
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
    homePagePlatform.goToSpecificSpace(space);
    activityStream.addActivity(activity2, "");
    activityStream.editActivity(activity2, newActivity);
    $(byText(activity2 + newActivity)).waitUntil(Condition.visible, Configuration.timeout);
    info("Edit Activity From Space");
    manageLogInOut.signIn(usernametest, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToInvitationsReceivedTab();
    spaceManagement.acceptAInvitation(space);
    activityStream.addActivity(activity1, "");
    info("Ckeck Edit Acivity In Space By Manager");
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    activityStream.CheckEditActivityInSpaceByManager(activity1, true);
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

  }

}
