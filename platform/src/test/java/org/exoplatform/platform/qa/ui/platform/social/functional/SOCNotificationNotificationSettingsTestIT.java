package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.IntranetNotification;
import org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationActivity;

@Tag("functional")
@Tag("social")

public class SOCNotificationNotificationSettingsTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  UserAddManagement      userAddManagement;

  ActivityStream         activityStream;

  IntranetNotification   intranetNotification;

  MyNotificationsSetting myNotificationsSetting;

  NotificationActivity   notificationActivity;

  ArrayList<String>      arrayUser;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    userAddManagement = new UserAddManagement(this);
    activityStream = new ActivityStream(this);
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    intranetNotification = new IntranetNotification(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    notificationActivity = new NotificationActivity(this);
    myNotificationsSetting = new MyNotificationsSetting(this);

  }

  /**
   * <li>Case ID:117388.</li>
   * <li>Test Case Name: Access notification settings.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1: Access notification settings Step
   * Description: - Log in as a user. - Move mouse over the full name of user and
   * select [My Notifications] on the menu. Input Data: Expected Outcome: -
   * Notification Settings page is appeared. - The entry is placed in the menu
   * after My Dashboard. -It opens the Notifications Settings page in the user
   * navigation.
   */
  @Test
  public void test01_AccessNotificationSettings() {

    info("Test 1: Access notification settings");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, "123456", email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    $(ELEMENT_MY_NOTIFICATION_SETTING_FORM).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:117511.</li>
   * <li>Test Case Name: Check notification types are grouped in categories.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   *//*
      * Step Number: 1 Step Name: Step 1: Access notification settings Step
      * Description: - Login - Move mouse over the full name of user and select [My
      * Notifications] on the menu Input Data: Expected Outcome: - Notification
      * Settings page is appeared
      */
  /*
   * Step number: 2 Step Name: Step 2: Check notification types are grouped in
   * categories Step Description: - Check notification types are grouped in
   * categories Input Data: Expected Outcome: - Category "General" includes:
   * "Someone joins the social intranet" - Category "Connections" includes:
   * Someone sends me a connection request - Category "Spaces" includes:Someone
   * requests to join one of my spaces, I receive a Space invitation, An activity
   * is posted on one of my spaces - Category "Activity Stream" includes: Someone
   * likes one of my activities, Someone @mentions me, Someone posts a message on
   * my activity stream, Someone comments on one of my activities
   */
  @Test
  public void test02_CheckNotificationTypesAreGroupedInCategories() {
    info("Test 2: Check notification types are grouped in categories");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, "123456", email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    $(ELEMENT_MY_NOTIFICATION_SETTING_FORM).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_GROUP.replace("$number", "1")
                                                  .replace("$groupName", "General"))).waitUntil(Condition.visible,
                                                                                                Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "2")
                                                 .replace("$id", "NewUserPlugin"))).waitUntil(Condition.visible,
                                                                                              Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_GROUP.replace("$number", "3")
                                                  .replace("$groupName", "Connections"))).waitUntil(Condition.visible,
                                                                                                    Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "4")
                                                 .replace("$id", "RelationshipReceivedRequestPlugin"))).waitUntil(
                                                                                                                  Condition.visible,
                                                                                                                  Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_GROUP.replace("$number", "5")
                                                  .replace("$groupName", "Spaces"))).waitUntil(Condition.visible,
                                                                                               Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "6")
                                                 .replace("$id", "SpaceInvitationPlugin"))).waitUntil(Condition.visible,
                                                                                                      Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "7")
                                                 .replace("$id", "RequestJoinSpacePlugin"))).waitUntil(Condition.visible,
                                                                                                       Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "8")
                                                 .replace("$id", "PostActivitySpaceStreamPlugin"))).waitUntil(Condition.visible,
                                                                                                              Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_GROUP.replace("$number", "9")
                                                  .replace("$groupName", "Activity Stream"))).waitUntil(Condition.visible,
                                                                                                        Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "10")
                                                 .replace("$id", "ActivityMentionPlugin"))).waitUntil(Condition.visible,
                                                                                                      Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "11")
                                                 .replace("$id", "PostActivityPlugin"))).waitUntil(Condition.visible,
                                                                                                   Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "12")
                                                 .replace("$id", "EditActivityPlugin"))).waitUntil(Condition.visible,
                                                                                                   Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "13")
                                                 .replace("$id", "ActivityCommentPlugin"))).waitUntil(Condition.visible,
                                                                                                      Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "14")
                                                 .replace("$id", "EditCommentPlugin"))).waitUntil(Condition.visible,
                                                                                                  Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "15")
                                                 .replace("$id", "ActivityReplyToCommentPlugin"))).waitUntil(Condition.visible,
                                                                                                             Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "16")
                                                 .replace("$id", "LikePlugin"))).waitUntil(Condition.visible,
                                                                                           Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "17")
                                                 .replace("$id", "LikeCommentPlugin"))).waitUntil(Condition.visible,
                                                                                                  Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_GROUP.replace("$number", "18")
                                                  .replace("$groupName", "Document Sharing"))).waitUntil(Condition.visible,
                                                                                                         Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "19")
                                                 .replace("$id", "ShareFileToUserPlugin"))).waitUntil(Condition.visible,
                                                                                                      Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "20")
                                                 .replace("$id", "ShareFileToSpacePlugin"))).waitUntil(Condition.visible,
                                                                                                       Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_GROUP.replace("$number", "21")
                                                  .replace("$groupName", "My Tasks"))).waitUntil(Condition.visible,
                                                                                                 Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "22")
                                                 .replace("$id", "TaskAssignPlugin"))).waitUntil(Condition.visible,
                                                                                                 Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "23")
                                                 .replace("$id", "TaskCoworkerPlugin"))).waitUntil(Condition.visible,
                                                                                                   Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "24")
                                                 .replace("$id", "TaskDueDatePlugin"))).waitUntil(Condition.visible,
                                                                                                  Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "25")
                                                 .replace("$id", "TaskCompletedPlugin"))).waitUntil(Condition.visible,
                                                                                                    Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "26")
                                                 .replace("$id", "TaskCommentedPlugin"))).waitUntil(Condition.visible,
                                                                                                    Configuration.timeout);
    $(byXpath(ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number", "27")
                                                 .replace("$id", "TaskMentionedPlugin"))).waitUntil(Condition.visible,
                                                                                                    Configuration.timeout);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }}

 