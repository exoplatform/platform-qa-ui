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
    String newActivity = "newActivity" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.editActivity(activity1, newActivity);
    $(byText(activity1 + newActivity)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1 + newActivity);

  }

  @Test
  public void test02_CheckEditActivityButton() {

    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.CheckEditActivity(activity1, true);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test03_addcharacter_EditActivity() {

    String activity1 = "activity1" + getRandomNumber();
    String newActivity = "a" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.editActivity(activity1, newActivity);
    $(byText(activity1 + newActivity)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1 + newActivity);

  }

  @Test
  public void test04_CancelEditActivity() {

    String activity1 = "activity1" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.cancelActivity(activity1, newActivity);
    $(byText(activity1)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test05_addspecialcharacter_EditActivity() {

    String activity1 = "activity1@@@@$$$" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.editActivity(activity1, newActivity);
    $(byText(activity1 + newActivity)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1 + newActivity);

  }

  @Test
  public void test06_checkEditForum() throws Exception {

    String name = "Category" + getRandomNumber();
    String name2 = "Forum" + getRandomNumber();
    String desc = "Description" + getRandomNumber();
    String topic = "Topic" + getRandomNumber();

    homePagePlatform.goToForum();
    info("Add a topic");
    forumCategoryManagement.addCategorySimple(name, "", desc);
    forumForumManagement.addForumSimple(name2, "", desc);
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "eXo-Platform.png");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(topic)), Condition.visible, 1000);
    activityStream.checkNotEditInTopicActivity(topic);
    homePagePlatform.goToForum();
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(name);
  }

  @Test
  public void test07_checkEditWikiDisable() {

    info("Create a wiki page");
    String title = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePage(title, content);
    wikiManagement.saveAddPage();
    wikiValidattions.verifyTitleWikiPage(title);
    homePagePlatform.goToHomePage();
    activityStream.checkNotEditInWikiActivity(title);
    homePagePlatform.goToWiki();
    wikiHomePage.deleteWiki(title);

  }

  @Test
  public void test08_EditActivityanotheruser() {

    String activity1 = "activity1" + getRandomNumber();
    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "123456";
    info("Add new user");
    activityStream.addActivity(activity1, "");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(usernametest);
    manageLogInOut.signIn(usernametest, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(PLFData.DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.CheckEditActivity(activity1, false);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usernametest);

  }

  @Test
  public void test09_EditActivityfromspace() {

    String space = "space" + getRandomNumber();
    info("Create a space");
    String activity1 = "activity1" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    activityStream.addActivity(activity1, "");
    activityStream.editActivity(activity1, newActivity);
    $(byText(activity1 + newActivity)).waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test10_EditActivitybyowner() {

    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "123456";
    String activity1 = "activity1" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    manageLogInOut.signIn(usernametest, password);
    activityStream.addActivity(activity1, "");
    activityStream.editActivity(activity1, newActivity);
    $(byText(activity1 + newActivity)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1 + newActivity);
  }

  @Test
  public void test11_EditActivitybyadmintonormaluser() {

    String usernametest = "usernametest" + getRandomString();
    String email = usernametest + "@gmail.com";
    String password = "123456";
    String activity1 = "activity1" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usernametest, password, email, usernametest, usernametest);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(usernametest);
    manageLogInOut.signIn(usernametest, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(PLFData.DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.editActivity(activity1, newActivity);
    manageLogInOut.signIn(usernametest, password);
    activityStream.deleteactivity(activity1 + newActivity);
  }

  @Test
  public void test12_ckeckEditAcivityInSpaceByManager() {

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
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    activityStream.CheckEditActivityInSpaceByManager(activity1, true);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  @Test
  public void test13_ckeckEditAcivityInSpaceByUser() {

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

  @Test
  public void test14_ckeckActivityViewer() {

    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    $(byText(activity1)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.gotoActivityViewer(activity1);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);

  }

  @Test
  public void test15_checkEditActivityInDefaultSkin() {

    String activity1 = "activity1" + getRandomNumber();
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToDefaultSkin();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    activityStream.CheckEditActivity(activity1, true);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditSiteConfig("intranet");
    portalManageSites.goToEntrepriseSkin();
  }

  @Test
  public void test16_CheckActivityEditedWhenRemoveCharacters() {
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.removeCharactersActivity(activity1, 3, false);
    activityStream.deleteactivity(activity1.substring(0, activity1.length() - 3));
  }

  @Test
  public void test17_CheckActivityEditedWhenRemoveAllCharacters() {
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.removeCharactersActivity(activity1, activity1.length(), true);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test18_CheckOrderOfTheActivitiesWhenEditActivity() {

    String activity1 = "activity1" + getRandomNumber();
    String activity2 = "activity1" + getRandomNumber();
    String activity3 = "activity1" + getRandomNumber();
    String newactivity = "newactivity" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.addActivity(activity2, "");
    activityStream.addActivity(activity3, "");
    activityStream.editActivity(activity1, newactivity);
    refresh();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY)
                                     .get(0)
                                     .find(byText(activity1 + newactivity))
                                     .should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(1).find(byText(activity3)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(2).find(byText(activity2)).should(Condition.exist);

  }

  @Test
  public void test19_CheckMentionUserWhenEditActivity() throws Exception {

    String usertest = "usernamea" + getRandomString();
    String email1 = usertest + "@test.com";
    String password = "123456";
    String activity1 = "activity1" + getRandomNumber();
    String newactivity = "newactivity" + getRandomNumber();
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(usertest, password, email1, usertest, usertest);
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil(ELEMENT_ACTIVITY_INPUT_TEXT, Condition.visible, 2000);
    activityStream.mentionUserActivity(usertest, activity1);
    activityStream.editActivity(activity1, newactivity);
    $(byText(activity1 + newactivity)).parent()
                                      .shouldHave(Condition.text(usertest + " " + usertest + " " + activity1 + newactivity));
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(usertest);
  }

  @Test
  public void test20_CheckEditActivityWithFormattingTextBold() {

    String activity = "activity" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    info("Add user");
    activityStream.addFormattingText(activity, Add_FormattingBOLD);
    activityStream.editFormatedActivity(activity, newActivity);
    assertEquals(Integer.parseInt($(byText(activity + newActivity)).getCssValue("font-weight")), 700);
    activityStream.deleteFormatedActivity(activity + newActivity);

  }

  @Test
  public void test21_EditActivityWithLink() {

    // Check when this feature will be done

    String activity1 = "activity1" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    activityStream.addActivity(activity1, "https://www.google.com/");
    activityStream.editActivity(activity1, newActivity);
    $(byText(activity1 + newActivity)).waitUntil(Condition.visible, Configuration.timeout);
    activityStream.deleteactivity(activity1 + newActivity);

  }

  @Test
  public void test22_CheckEditActivityWithFormattingTextItalic() {

    String activity = "activity" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    info("Add user");
    activityStream.addFormattingText(activity, Add_FormattingITALIC);
    activityStream.editFormatedActivity(activity, newActivity);
    assertEquals(($(byText(activity + newActivity)).getCssValue("font-style")), "italic");
    activityStream.deleteFormatedActivity(activity + newActivity);

  }

  @Test
  public void test23_CheckEditActivityWithFormattingTextNumbredList() {

    // Check when this feature will be done

    String activity = "activity" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    info("Add user");
    activityStream.addFormattingText(activity, Add_Formtting_numbred_List);
    activityStream.editFormatedActivity(activity, newActivity);
    assertEquals($(byText(activity + newActivity)).getCssValue("list-style-position"), "inside");
    activityStream.deleteFormatedActivity(activity + newActivity);

  }

  @Test
  public void test24_CheckEditActivityWithFormattingTextBulledList() {
    String activity = "activity" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    activityStream.addFormattingText(activity, Add_Formtting_Bulled_List);
    activityStream.editFormatedActivity(activity, newActivity);
    assertEquals($(byText(activity + newActivity)).getCssValue("display"), "list-item");
    activityStream.deleteFormatedActivity(activity + newActivity);

  }

  @Test
  public void test25_CheckEditActivityWithFormattingTextBlockquote() {
    // Check when this feature will be done
    String activity = "activity" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    info("Add user");
    activityStream.addFormattingText(activity, Add_FormattingQuote);
    activityStream.editFormatedActivity(activity, newActivity);
    assertEquals($(byText(activity + newActivity)).getCssValue("display"), "block");
    activityStream.deleteFormatedActivity(activity + newActivity);

  }

  @Test
  public void test26_CheckEditActivityWithFormattingTextRemoveFormat() {
    // Check when this feature will be done

    String activity = "activity" + getRandomNumber();
    String newActivity = "newActivity" + getRandomNumber();
    activityStream.addFormattingText(activity, Add_FormttingRemoveFormat);
    activityStream.editActivity(activity, newActivity);
    assertEquals(($(byText(activity + newActivity)).getCssValue("font-style")), "normal");
    activityStream.deleteactivity(activity + newActivity);

  }

  @Test
  public void test27_CheckActivities_EnabledBydefault() {

    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    activityStream.CheckEditActivityBydefault(activity1, true);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test28_CheckEditActivityWithFile() {

    String activity1 = "activity1" + getRandomNumber();
    activityStream.Upload_File_With_Text(activity1);
    activityStream.CheckEditActivityBydefault(activity1, true);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1);
  }

  @Test
  public void test29_CheckEditActivityWithlinkFormmicroblog() {
    // Check when this feature will be done
    String activity1 = "activity1" + getRandomNumber();
    String newactivity = "newactivity" + getRandomNumber();
    activityStream.addFormattingText(activity1, Add_Formtting_Link);
    activityStream.editActivity(activity1, newactivity);
    $(byText(activity1 + newactivity)).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(activity1 + newactivity);
  }

  @Test
  public void test30_CheckEditActivityWithImageFormmicroblog() {

    String activity1 = "activity1" + getRandomNumber();
    String newactivity = "activity1" + getRandomNumber();
    activityStream.addFormattingText(activity1, Add_Formtting_Image);
    activityStream.editActivityImage(activity1, newactivity);
    manageLogInOut.signIn(PLFData.DATA_USER1, DATA_PASS2);
    activityStream.deleteactivity(newactivity + activity1);
  }

}
