package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_NOTIFICATION_DROPDOWN;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_VIEW_ALL_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.exoplatform.platform.qa.ui.social.pageobject.NotificationsAdminSeting.notificationType.Edit_Activity;
import static org.exoplatform.platform.qa.ui.social.pageobject.NotificationsAdminSeting.notificationType.Edit_Comment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
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
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiValidattions;
@Tag("social")
@Tag("sniff")
public class SOCNotificationsEditActivityTestIT extends Base {

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

  MyNotificationsSetting  myNotificationsSetting;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    activityStream = new ActivityStream(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    navigationToolbar = new NavigationToolbar(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
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
    manageLogInOut.signInCas(username, password);
  }

  @Test
  public void test01_DisplayEditActivityOnsiteNotification() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.verifyLabelNotificationType("Someone edits an activity");
    myNotificationsSetting.verifyNotificationDefault(Edit_Activity);
    manageLogInOut.signIn(username, PASS_ROOT);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test02_DisplayEditCommentOnsiteNotification() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.verifyLabelNotificationType("Someone edits a comment");
    myNotificationsSetting.verifyNotificationDefault(Edit_Comment);
    manageLogInOut.signIn(username, PASS_ROOT);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test03_CHeckOnSiteNotifWhenEditActivity() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    String activity1 = "activity1" + getRandomNumber();
    String newactivity = "newactivity" + getRandomString();
    String comment1 = "comment1" + getRandomString();
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username, username + " " + username);
    refresh();
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity1, comment1);
    manageLogInOut.signIn(username, PASS_ROOT);
    activityStream.editActivity(activity1, newactivity);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(DATA_NAME_ROOT))
                                    .parent()
                                    .shouldHave(Condition.text(DATA_NAME_ROOT + " has edited his activity."));
    manageLogInOut.signIn(username, PASS_ROOT);
    activityStream.deleteactivity(activity1 + newactivity);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);

  }

  @Test
  public void test04_CHeckOnSiteNotifWhenEditComment() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String newcomment1 = "newcomment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomString();
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username, username + " " + username);
    refresh();
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity1, comment2);
    manageLogInOut.signIn(username, PASS_ROOT);
    activityStream.editComment(comment1, newcomment1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(DATA_NAME_ROOT))
                                    .parent()
                                    .shouldHave(Condition.text(DATA_NAME_ROOT + " has edited his comment on your activity."));
    manageLogInOut.signIn(username, PASS_ROOT);
    activityStream.deleteactivity(activity1);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);

  }

  @Test
  public void test05_DisplayOnsiteNotificationForAllActivitvitisCommentsEdited() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    String activity1 = "activity1" + getRandomNumber();
    String comment1 = "comment1" + getRandomNumber();
    String newcomment1 = "newcomment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomString();
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    activityStream.commentActivity(activity1, comment1);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username, username + " " + username);
    refresh();
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity1, comment2);
    manageLogInOut.signIn(username, PASS_ROOT);
    activityStream.editComment(comment1, newcomment1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_VIEW_ALL_BUTTON).click();
    $(byText(DATA_NAME_ROOT)).parent().shouldHave(Condition.text(DATA_NAME_ROOT + " has edited his comment on your activity."));
    manageLogInOut.signIn(username, PASS_ROOT);
    activityStream.deleteactivity(activity1);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);

  }

  @Test
  public void test06_DisplayOnsiteNotificationForEveryActivityEdited() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    String activity1 = "activity1" + getRandomNumber();
    String activity2 = "activity2" + getRandomNumber();
    String newactivity = "newactivity" + getRandomString();
    String newactivity2 = "newactivity2" + getRandomString();
    String comment1 = "comment1" + getRandomNumber();
    String comment2 = "comment2" + getRandomNumber();
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity1, "");
    activityStream.addActivity(activity2, "");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username, username + " " + username);
    refresh();
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity1, comment1);
    homePagePlatform.goToHomePage();
    activityStream.commentActivity(activity2, comment2);
    manageLogInOut.signIn(username, PASS_ROOT);
    activityStream.editActivity(activity1, newactivity);
    activityStream.editActivity(activity2, newactivity2);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    $(ELEMENT_NOTIFICATION_DROPDOWN).find(byText(DATA_NAME_ROOT))
                                    .parent()
                                    .shouldHave(Condition.text(DATA_NAME_ROOT + " has edited his activity."));
    manageLogInOut.signIn(username, PASS_ROOT);
    activityStream.deleteactivity(activity1);
    activityStream.deleteactivity(activity2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test07_CheckOnsiteNotificationEditActivityValueByDefault() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.verifyNotificationDefault(Edit_Activity);
    manageLogInOut.signIn(username, PASS_ROOT);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test08_CheckOnsiteNotificationEditCommentValueByDefault() {

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.verifyNotificationDefault(Edit_Comment);
    manageLogInOut.signIn(username, PASS_ROOT);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }
}
