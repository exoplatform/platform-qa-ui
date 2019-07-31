package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_TITLE_BOX;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TREE_WIKI_NAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.social.pageobject.MyNotificationsSetting.myNotiType.AS_Like_intranet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.*;
import org.exoplatform.platform.qa.ui.wiki.pageobject.SourceTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;

@Tag("functional")
@Tag("social")
public class SOCNotificationLikeActivitiesTestIT extends Base {
  NavigationToolbar        navigationToolbar;

  NotificationsAdminSeting notificationsAdminSeting;

  ManageLogInOut           manageLogInOut;

  UserAndGroupManagement   userAndGroupManagement;

  AddUsers                 addUsers;

  MyNotificationsSetting   myNotificationsSetting;

  HomePagePlatform         homePagePlatform;

  ConnectionsManagement    connectionsManagement;

  ActivityStream           activityStream;

  IntranetNotification     intranetNotification;

  NotificationActivity     notificationActivity;

  SiteExplorerHome         siteExplorerHome;

  CreateNewDocument        createNewDoc;

  WikiHomePage             wikiHomePage;

  WikiManagement           wikiManagement;

  SourceTextEditor         sourceTextEditor;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    notificationsAdminSeting = new NotificationsAdminSeting(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    connectionsManagement = new ConnectionsManagement(this);
    notificationActivity = new NotificationActivity(this);
    activityStream = new ActivityStream(this);
    intranetNotification = new IntranetNotification(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDoc = new CreateNewDocument(this);
    notificationActivity = new NotificationActivity(this);
    wikiHomePage = new WikiHomePage(this);
    wikiManagement = new WikiManagement(this);
    sourceTextEditor = new SourceTextEditor(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:125087.</li>
   * <li>Test Case Name: Check Like Notification (1 like).</li>
   * <li>Case ID:125091.</li>
   * <li>Test Case Name: Check Like Notification after reading the
   * notification.</li>
   * <li>Pre-Condition: - User A and User B are connected - User A has posted an
   * activity - User B has liked User A activity - The notification "Someone likes
   * one of my activities" is activated in the user settings</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : Check
   * notifications list Step Description: - Login with User A - Click the
   * notifications icon in the top navigation - Check the notification list Input
   * Data:
   * <p>
   * Expected Outcome: - A Like notification is displayed in the list Step number:
   * 2 Step Name: Step 2 : Check notification message Step Description: - Check
   * the notification message Input Data:
   * <p>
   * Expected Outcome: The activity message is : $AVATAR$USER likes your
   * activity.$ACTIVITY$DATEWhere : - $AVATAR is the thumbnail of User B - $USER
   * is User B - $ACTIVITY is the activity title/message - $DATE is the date of
   * the activity*
   */
  @Test
  public void test01_CheckLikeNotification1Like() {
    info("Test 1: Check Like Notification (1 like)");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(AS_Like_intranet);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    homePagePlatform.goToHomePage();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    String status = username2 + " " + username2 + " " + "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatus(status, username2);
    intranetNotification.checkActivityTitleInStatus(activity1, true);
    intranetNotification.goToDetailLikeNotification(username2 + " " + username2, true);
    notificationActivity.checkLikeInActivityViewer("1");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /*
   * <li> Case ID:125091.</li> <li> Test Case Name: Check Like Notification after
   * reading the notification.</li>
   * info("Test 4: Check Like Notification after reading the notification");
   * /*Step number: 3 Step Name: Step 3 : Read the notification Step Description:
   * - Click the notification area Input Data: Expected Outcome: - The activity is
   * displayed in the activity viewer with all comment expanded. Step Number: 1
   * Step Name: Step 1 : Check notifications list Step Description: - Login with
   * User A - Click the notifications icon in the top navigation - Check the
   * notification list Input Data: Expected Outcome: - A Like notification is
   * displayed in the list Step number: 2 Step Name: Step 2 : Check notification
   * message Step Description: - Check the notification message Input Data:
   * Expected Outcome: The activity message is : $AVATAR$USER likes your
   * activity.$ACTIVITY$DATEWhere : - $AVATAR is the thumbnail of User B - $USER
   * is User B - $ACTIVITY is the activity title/message - $DATE is the date of
   * the activity
   */
  @Test
  public void test04_CheckLikeNotificationAfterReadingTheNotification() {
    info("Test 1: Check Like Notification (1 like)");
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(AS_Like_intranet);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    homePagePlatform.goToHomePage();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    String status = "likes your activity.";
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatus(status, username2);
    intranetNotification.checkActivityTitleInStatus(activity1, true);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125088.</li>
   * <li>Test Case Name: Check Like Notification (2 like).</li>
   * <li>Pre-Condition: - User A and User B are connected - User A and User C are
   * connected - User A has posted an activity - User B and User C like the posted
   * activity of User A - User B has liked User A activity - User C has liked User
   * A activity - The notification "Someone likes one of my activities" is
   * activated in the user settings</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : Check
   * notifications list Step Description: - Login with User A - Click the
   * notifications icon in the top navigation - Check the notification list Input
   * Data:
   * <p>
   * Expected Outcome: - A Like notification is displayed in the list Step number:
   * 2 Step Name: Step 2 : Check notification message Step Description: - Check
   * the notification message Input Data:
   * <p>
   * Expected Outcome: The activity message is : $LAST_AVATAR$USER_LIST like your
   * activity.$ACTIVITY$DATEWhere : - $LAST_AVATAR is the thumbnail of User C -
   * $USER_LISTis User B, User C - $ACTIVITY is the activity title/message - $DATE
   * is the date of the activity* Step number: 3 Step Name: Step 3 : Read the
   * notification Step Description: - Click the notification area Input Data:
   * <p>
   * Expected Outcome: - The activity is displayed in the activity viewer with all
   * comment expanded.
   */
  @Test
  public void test02_CheckLikeNotification2Like() {
    info("Test 2: Check Like Notification (2 like)");
    String activity = "activity" + getRandomString();
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@gmail.com";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);
    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(AS_Like_intranet);
    info("Connect with 2 users");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);
    info("Add a activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    info("user1 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    String status = username3 + " " + username3 + " " + "and" + " " + username2 + " " + username2 + " " + "like your activity. ";
    intranetNotification.checkAvatarInStatus(username3, true);
    intranetNotification.checkStatus(status, username3);
    intranetNotification.checkActivityTitleInStatus(activity, true);
    intranetNotification.goToDetailLikeNotification(username3 + " " + username3, true);
    notificationActivity.checkLikeInActivityViewer("2");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125089.</li>
   * <li>Test Case Name: Check Like Notification (4 like).</li>
   * <li>Pre-Condition: - User A is connected with User B, User C, User D and User
   * E - User A has posted an activity - User B has liked User A activity - User C
   * has liked User A activity - User D has liked User A activity - User E has
   * liked User A activity - The notification "Someone likes one of my activities"
   * is activated in the user settings</li>
   * <li>Post-Condition:</li> Precondition: - User A is connected with User B,
   * User C, User D and User E - User A has posted an activity - User B has liked
   * User A activity - User C has liked User A activity - User D has liked User A
   * activity - User E has liked User A activity - The notification "Someone likes
   * one of my activities" is activated in the user settings Step Number: 1 Step
   * Name: Step 1 : Check notifications list Step Description: - Login with User A
   * - Click the notifications icon in the top navigation - Check the notification
   * list Input Data:
   * <p>
   * Expected Outcome: - A Like notification is displayed in the list Step number:
   * 2 Step Name: Step 2 : Check notification message Step Description: - Check
   * the notification message Input Data:
   * <p>
   * Expected Outcome: The activity message is : $LAST_AVATAR$LAST2_USERS and
   * $COUNT more like your activity. $ACTIVITY$DATEWhere : - $LAST_AVATAR is the
   * thumbnail of User E - $LAST2_USERSis User E, User D - $COUNT is 2 - $ACTIVITY
   * is the activity title/message - $DATE is the date of the activity Step
   * number: 3 Step Name: Step 3 : Read the notification Step Description: - Click
   * the notification area Input Data:
   * <p>
   * Expected Outcome: - The activity is displayed in the activity viewer with all
   * comment expanded.
   */
  @Test
  public void test03_CheckLikeNotification4Like() {
    String activity = "activity" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@gmail.com";
    String username4 = "usernamed" + getRandomString();
    String email4 = username4 + "@gmail.com";
    String username5 = "usernamee" + getRandomString();
    String email5 = username5 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    addUsers.addUser(username4, password, email4, username4, username4);
    addUsers.addUser(username5, password, email5, username5, username5);
    manageLogInOut.signIn(username1, password);
    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with 4 users");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);
    connectionsManagement.connectToAUser(username4);
    connectionsManagement.connectToAUser(username5);
    info("Add a activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    info("user 1 likes John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);

    info("user 2 likes John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);

    info("user 3 likes John's activity");
    manageLogInOut.signIn(username4, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);

    info("user 4 likes John's activity");
    manageLogInOut.signIn(username5, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);
    info("Test 3: Check Like Notification (4 like)");
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    String status = "likes your activity.";
    intranetNotification.checkAvatarInStatus(username5, true);
    intranetNotification.checkStatus(status, username5);
    intranetNotification.checkActivityTitleInStatus(activity, true);
    info("Check comment notification in activity Viewer");
    intranetNotification.goToDetailLikeNotification(username5 + " " + username5, true);
    notificationActivity.checkLikeInActivityViewer("4");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
    addUsers.deleteUser(username4);
    addUsers.deleteUser(username5);

  }

  /**
   * <li>Case ID:125099.</li>
   * <li>Test Case Name: Check Like Notification on activity with a content.</li>
   * <li>Pre-Condition: - User A and User B are connected - User A has posted an
   * activity with a content attached - User B has liked User A activity - The
   * notification "Someone likes one of my activities" is activated in the user
   * settings</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : Check
   * notifications list Step Description: - Login with User A - Click the
   * notifications icon in the top navigation - Check the notification list Input
   * Data:
   * <p>
   * Expected Outcome: - A Like notification is displayed in the list Step number:
   * 2 Step Name: Step 2 : Check notification message Step Description: - Check
   * the notification message Input Data:
   * <p>
   * Expected Outcome: The activity message is : $AVATAR$USER likes your
   * activity.$ACTIVITY$DATEWhere : - $AVATAR is the thumbnail of User B - $USER
   * is User B - $ACTIVITY is the activity title/message : the name of the content
   * is displayed - $DATE is the date of the activity Step number: 3 Step Name:
   * Step 3 : Read the notification Step Description: - Click the notification
   * area Input Data:
   * <p>
   * Expected Outcome: - The activity is displayed in the activity viewer with all
   * comment expanded.
   */
  @BugInPLF("ECMS-7866")
  @Test
  public void test05_CheckLikeNotificationOnActivityWithAContent() {
    info("Test 5: Check Like Notification on activity with a content");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();

    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.addUserAdmin(username1, "manager");
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    homePagePlatform.goToHomePage();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToPath("intranet/documents", "Site Management");
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDoc.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDoc.addNewWebContent(name, content);
    createNewDoc.saveAndClose();

    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(name);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    String status = username2 + " " + username2 + " " + "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatus(status, username2);
    intranetNotification.checkActivityTitleInStatus(name, true);
    intranetNotification.goToDetailLikeNotification(username2 + " " + username2, true);
    notificationActivity.checkLikeInActivityViewer("1");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125098.</li>
   * <li>Test Case Name: Check Like Notification on activity with a link.</li>
   * <li>Pre-Condition: - User A and User B are connected - User A has posted an
   * activity with a link - User B has liked User A activity - The notification
   * "Someone likes one of my activities" is activated in the user settings</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test06_CheckLikeNotificationOnActivityWithALink() {

    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    homePagePlatform.goToHomePage();
    String textDes = "textDes" + getRandomNumber();
    String link = "https://www.google.fr/";
    activityStream.addActivity(textDes, link);
    $(byXpath(ELEMENT_ACTIVITY_TITLE.replace("${text}", textDes).replace("${file}", link))).waitUntil(Condition.visible,
                                                                                                      Configuration.timeout);
    String title = $(byXpath(ELEMENT_TITLE_BOX.replace("${title}", textDes))).getText();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(textDes);

    info("Test 6: Check Like Notification on activity with a link");
    /*
     * Step Number: 1 Step Name: Step 1 : Check notifications list Step Description:
     * - Login with User A - Click the notifications icon in the top navigation -
     * Check the notification list Input Data: Expected Outcome: - A Like
     * notification is displayed in the list
     */

    /*
     * Step number: 2 Step Name: Step 2 : Check notification message Step
     * Description: - Check the notification message Input Data: Expected Outcome:
     * The activity message is : $AVATAR$USER likes your
     * activity.$ACTIVITY$DATEWhere : - $AVATAR is the thumbnail of User B - $USER
     * is User B - $ACTIVITY is the activity title/message : the link is displayed -
     * $DATE is the date of the activity
     */
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    String status = username2 + " " + username2 + " " + "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatus(status, username2);
    intranetNotification.checkActivityTitleInStatus(title, true);
    /*
     * Step number: 3 Step Name: Step 3 : Read the notification Step Description: -
     * Click the notification area Input Data: Expected Outcome: - The activity is
     * displayed in the activity viewer with all comment expanded.
     */
    intranetNotification.goToDetailLikeNotification(username2 + " " + username2, true);
    notificationActivity.checkLikeInActivityViewer("1");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  /**
   * <li>Case ID:125101.</li>
   * <li>Test Case Name: Check Like Notification on activity with a wiki
   * page.</li>
   * <li>Pre-Condition: - User A and User B are connected - User A has posted an
   * activity with a wiki page - User B has liked User A activity - The
   * notification "Someone likes one of my activities" is activated in the user
   * settings</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @Tag("sabis")
  public void test07_CheckLikeNotificationOnActivityWithAWikiPage() {
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.addUserAdmin(username1, "manager");
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePageWithAutoSaveStatus(name, content);
    wikiManagement.saveAddPage();
    $(byXpath(ELEMENT_TREE_WIKI_NAME.replace("${name}", name))).waitUntil(Condition.visible, Configuration.timeout);

    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(name);
    info("Test 7: Check Like Notification on activity with a wiki page");
    /*
     * Step Number: 1 Step Name: Step 1 : Check notifications list Step Description:
     * - Login with User A - Click the notifications icon in the top navigation -
     * Check the notification list Input Data: Expected Outcome: - A Like
     * notification is displayed in the list
     */

    /*
     * Step number: 2 Step Name: Step 2 : Check notification message Step
     * Description: - Check the notification message Input Data: Expected Outcome:
     * The activity message is : $AVATAR$USER likes your
     * activity.$ACTIVITY$DATEWhere : - $AVATAR is the thumbnail of User B - $USER
     * is User B - $ACTIVITY is the activity title/message : the name of the wiki
     * page - $DATE is the date of the activity
     */
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    String status = username2 + " " + username2 + " " + "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatus(status, username2);
    intranetNotification.checkActivityTitleInStatus(name, true);

    /*
     * Step number: 3 Step Name: Step 3 : Read the notification Step Description: -
     * Click the notification area Input Data: Expected Outcome: - The activity is
     * displayed in the activity viewer with all comment expanded.
     */
    intranetNotification.goToDetailLikeNotification(username2 + " " + username2, true);
    notificationActivity.checkLikeInActivityViewer("1");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125100.</li>
   * <li>Test Case Name: Check Like Notification on activity with an event.</li>
   * <li>Pre-Condition: - User A and User B are connected - User A has posted an
   * activity with an event - User B has liked User A activity - The notification
   * "Someone likes one of my activities" is activated in the user settings</li>
   * <li>Post-Condition:</li> PENDING: Do not have activity for my event/my task
   */
  @Test
  public void test08_CheckLikeNotificationOnActivityWithAnEvent() {
    String username1 = "usernamea" + getRandomString();
    String password1 = "123456";
    String email1 = username1 + "@gmail.com";
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    /* Create data test */
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password1, email1, username1, username1);
    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username1);
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePageWithAutoSaveStatus(name, content);
    wikiManagement.saveAddPage();
    $(byXpath(ELEMENT_TREE_WIKI_NAME.replace("${name}", name))).waitUntil(Condition.visible, Configuration.timeout);

    manageLogInOut.signIn(username1, password1);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(name);

    info("Test 8: Check Like Notification on activity with an event");
    /*
     * Step Number: 1 Step Name: Step 1 : Check notifications list Step Description:
     * - Login with User A - Click the notifications icon in the top navigation -
     * Check the notification list Input Data: Expected Outcome: - A Like
     * notification is displayed in the list
     */

    /*
     * Step number: 2 Step Name: Step 2 : Check notification message Step
     * Description: - Check the notification message Input Data: Expected Outcome:
     * The activity message is : $AVATAR$USER likes your
     * activity.$ACTIVITY$DATEWhere : - $AVATAR is the thumbnail of User B - $USER
     * is User B - $ACTIVITY is the activity title/message : the name of the event -
     * $DATE is the date of the activity
     */
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToIntranetNotification();
    String status = username1 + " " + username1 + " " + "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username1, true);
    intranetNotification.checkStatus(status, username1);
    intranetNotification.checkActivityTitleInStatus(name, true);

    /*
     * Step number: 3 Step Name: Step 3 : Read the notification Step Description: -
     * Click the notification area Input Data: Expected Outcome: - The activity is
     * displayed in the activity viewer with all comment expanded.
     */
    intranetNotification.goToDetailLikeNotification(username1 + " " + username1, true);
    notificationActivity.checkLikeInActivityViewer("1");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:125090.</li>
   * <li>Test Case Name: Check Like Notification when a new like is pushed.</li>
   * <li>Pre-Condition: - User A and User B are connected - User A has posted an
   * activity - User B has liked User A activity - The notification "Someone likes
   * one of my activities" is activated in the user settings</li> Step Number: 1
   * Step Name: Step 1 : Check notifications list Step Description: - Login with
   * User A - Click the notifications icon in the top navigation - Check the
   * notification list (keep the notification unread) Input Data:
   * <p>
   * Expected Outcome: - A Like notification is displayed in the list*
   * <li>Post-Condition:</li> Step number: 2 Step Name: Step 2 : push a new
   * notification Step Description: - Login with User C in another browser session
   * - Make a connection request to User A Input Data:
   * <p>
   * Expected Outcome: - The Connect Request is displayed in the notification list
   * of User A* Step number: 3 Step Name: Step 3 : Accept connection request Step
   * Description: - With User A, [Accept] the Connection Request Input Data:
   * <p>
   * Expected Outcome: - User A and User C are connected
   * <p>
   * Step number: 4 Step Name: Step 4 : Push a new like notification Step
   * Description: - With User C, like the activity of User A - Check the
   * notifications list Input Data:
   * <p>
   * Expected Outcome: - The Like notification is listed/merged in the same
   * previous notification (step 1) - The notification is displayed at the top of
   * the list Step number: 5 Step Name: Step 5 : check the message of the Like
   * notification Step Description: - Check notification message Input Data:
   * <p>
   * Expected Outcome: - The notification message is : LAST_AVATAR$USER_LIST like
   * your activity$ACTIVITY$DATEWhere : - $LAST_AVATAR is the thumbnail of User C
   * - $USER_LIST is User B, User C - $ACTIVITY is the activity message/title -
   * $DATE is the date of the last notification of User C*
   */
  @Test
  public void test09_CheckLikeNotificationWhenANewLikeIsPushed() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";

    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    homePagePlatform.goToHomePage();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");

    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity1);

    info("Test 9: Check Like Notification when a new like is pushed");

    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    String status = username2 + " " + username2 + " " + "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatus(status, username2);
    intranetNotification.checkActivityTitleInStatus(activity1, true);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username3);
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    String status1 = "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username3, true);
    intranetNotification.checkStatus(status1, username3);
    intranetNotification.checkActivityTitleInStatus(activity1, true);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  /**
   * <li>Case ID:125092.</li>
   * <li>Test Case Name: Check View All after receiving a Like notification (1
   * like).</li>
   * <li>Case ID:125096.</li>
   * <li>Test Case Name: Check View All page after reading a Like
   * notification.</li>
   * <li>Pre-Condition: - User A and User B are connected - User A has posted an
   * activity - User B has liked User A activity</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : Check
   * notifications list Step Description: - Login with User A - Click the
   * notifications icon in the top navigation - Check the notification list Input
   * Data:
   * <p>
   * Expected Outcome: - A Like notification is displayed in the list* Step
   * number: 2 Step Name: Step 2 : Check View All page Step Description: - Go to
   * View All page Input Data:
   * <p>
   * Expected Outcome: The notification is available/displayed in the View All
   * page :$AVATAR$USER likes your activity.$ACTIVITY$DATEWhere : - $AVATAR is the
   * thumbnail of User B - $USER is User B - $ACTIVITY is the activity
   * title/message - $DATE is the date of the activity*
   */
  @Test
  public void test10_CheckViewAllAfterReceivingALikeNotification1Like() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);
    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    homePagePlatform.goToHomePage();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity1);
    info("Test 10 Check View All after receiving a Like notification (1 like)");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    String status = "likes your activity.";
    intranetNotification.checkAvatarInStatus(username2, false);
    intranetNotification.checkStatus(status, username2);
    intranetNotification.checkActivityTitleInStatus(activity1, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }

  /**
   * <li>Case ID:125092.</li>
   * <li>Test Case Name: Check View All after receiving a Like notification (1
   * like).</li>
   * <li>Case ID:125096.</li>
   * <li>Test Case Name: Check View All page after reading a Like
   * notification.</li>
   * <li>Pre-Condition: - User A and User B are connected - User A has posted an
   * activity - User B has liked User A activity</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test11_CheckViewAllAfterReceivingALikeNotification1Like() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    String status = username2 + " " + username2 + " " + "likes your activity. ";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    homePagePlatform.goToHomePage();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity1);
    info("Test 11 Check View All page after reading a Like notification");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatus(status, username2);
    intranetNotification.checkActivityTitleInStatus(activity1, true);
    homePagePlatform.goToHomePage();
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    intranetNotification.checkAvatarInStatus(username2, false);
    intranetNotification.checkStatus(status, username2);
    intranetNotification.checkActivityTitleInStatus(activity1, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:125093.</li>
   * <li>Test Case Name: Check View All page after receiving a Like notification
   * (2 like).</li>
   * <li>Pre-Condition: - User A and User B are connected - User A and User C are
   * connected - User A has posted an activity - User B has liked User A activity
   * - User C has liked User A activity - The notification "Someone likes one of
   * my activities" is activated in the user settings</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test13_CheckViewAllPageAfterReceivingALikeNotification2Like() {
    info("Test 13 Check View All page after receiving a Like notification (2 like)");
    String activity = "activity" + getRandomString();
    // Setup data test
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";

    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);
    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with 2 users");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    connectionsManagement.connectToAUser(username3);

    info("Add a activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);
    info("user1 comments in John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);

    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);

    /*
     * Step Number: 1 Step Name: Step 1 : Check notifications list Step Description:
     * - Login with User A - Click the notifications icon in the top navigation -
     * Check the notification list Input Data: Expected Outcome: - A Like
     * notification is displayed in the list
     */
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();

    /*
     * Step number: 2 Step Name: Step 2 : Check View All page Step Description: - Go
     * to View All page Input Data: Expected Outcome: The Comment notification is
     * displayed / available in the page : $LAST_AVATAR$USER_LIST like your
     * activity$ACTIVITY$DATEWhere : - $LAST_AVATAR is the thumbnail of User C -
     * $USER_LISTis User B, User C - $ACTIVITY is the activity title/message - $DATE
     * is the date of the activity
     */
    intranetNotification.goToAllNotification();
    String status = "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username3, false);
    intranetNotification.checkStatus(status, username3);
    intranetNotification.checkActivityTitleInStatus(activity, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);
  }

  /**
   * <li>Case ID:125095.</li>
   * <li>Test Case Name: Check View All page right after a new Like is
   * pushed.</li>
   * <li>Pre-Condition: - User A and User B are connected - User A has posted an
   * activity - User B has liked on User A activity - The notification "Someone
   * likes one of my activities" is activated in the user settings</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : Check
   * notifications list Step Description: - Login with User A - Click the
   * notifications icon in the top navigation - Check the notification list (keep
   * the notification unread) Input Data:
   * <p>
   * Expected Outcome: - A Like notification is displayed in the list* Step
   * number: 2 Step Name: Step 2 : Push a new notification Step Description: -
   * Login with User C in another browser session - Make a connection request to
   * User A Input Data:
   * <p>
   * Expected Outcome: - The Connection Request is displayed in the notiication
   * list of User A* Step number: 3 Step Name: Step 3 : Accept the notification
   * request Step Description: - With User A, [Accept] the Connection Request
   * Input Data:
   * <p>
   * Expected Outcome: - User A and User C are connected Step number: 4 Step Name:
   * Step 4 : Add a new like to the activity Step Description: - With User C, like
   * the activity of User A - Check the notification list Input Data:
   * <p>
   * Expected Outcome: - The Like notification is listed/merged in the same
   * previous notification (step 1) - The notification is displayed at the top of
   * the list Step number: 5 Step Name: Step 5 : Check on View All page Step
   * Description: - Go to View All page Input Data: Expected Outcome: - The Like
   * notification is available/displayed in the View All page, at the top of the
   * page. LAST_AVATAR$USER_LIST like your activity$ACTIVITY$DATEWhere : -
   * $LAST_AVATAR is the thumbnail of User C - $USER_LIST is User B, User C -
   * $ACTIVITY is the activity message/title - $DATE is the date of the last
   * notification of User C
   */
  @Test
  public void test14_CheckViewAllPageRightAfterANewLikeIsPushed() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";

    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);
    info("goto My notification");
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.enableNotification(MyNotificationsSetting.myNotiType.AS_Like_intranet);
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    homePagePlatform.goToHomePage();
    String activity1 = "activity1" + getRandomNumber();
    activityStream.addActivity(activity1, "");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity1);
    info("Test 14 Check View All page right after a new Like is pushed");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username3);
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotification();
    intranetNotification.goToAllNotification();
    String status = "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username3, false);
    intranetNotification.checkStatus(status, username3);
    intranetNotification.checkActivityTitleInStatus(activity1, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);

  }
}
