package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_TITLE_BOX;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_INTRANET_NOTIFICATION_BELL;
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

  @Test
  public void test01_CheckLikeNotification() {
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
    String username6 = "usernamef" + getRandomString();
    String email6 = username6 + "@gmail.com";
    String username7 = "usernameg" + getRandomString();
    String email7 = username7 + "@gmail.com";
    String username8 = "usernameh" + getRandomString();
    String email8 = username8 + "@gmail.com";
    String username9 = "usernamei" + getRandomString();
    String email9 = username9 + "@gmail.com";
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String name1 = "name1" + getRandomNumber();
    String content1 = "content1" + getRandomNumber();
    String password = "Aa123456";

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
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.addUserAdmin(username1, "manager");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username8);
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePageWithAutoSaveStatus(name, content);
    wikiManagement.saveAddPage();
    $(byXpath(ELEMENT_TREE_WIKI_NAME.replace("${name}", name))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

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
    connectionsManagement.connectToAUser(username6);
    connectionsManagement.connectToAUser(username7);
    connectionsManagement.connectToAUser(username9);

    info("Add a activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(activity, "");
    activityStream.checkActivity(activity);

    homePagePlatform.goToHomePage();
    String textDes = "textDes" + getRandomNumber();
    String link = "https://www.google.fr/";
    activityStream.addActivity(textDes, link);
    $(byXpath(ELEMENT_ACTIVITY_TITLE.replace("${text}", textDes).replace("${file}", link))).waitUntil(Condition.visible,
            Configuration.timeout);
    String title = $(byXpath(ELEMENT_TITLE_BOX.replace("${title}", textDes))).getText();

    info("Check Like Notification on activity with an event");
    homePagePlatform.goToWiki();
    wikiHomePage.goToAddBlankPage();
    wikiManagement.goToSourceEditor();
    sourceTextEditor.addSimplePageWithAutoSaveStatus(name1, content1);
    wikiManagement.saveAddPage();
    $(byXpath(ELEMENT_TREE_WIKI_NAME.replace("${name}", name1))).waitUntil(Condition.visible, Configuration.timeout);

    info("user 1 likes John's activity");
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);

    info("Check Like Notification (1 like)");
    String status0 = username2 + " " + username2 + " " + "likes your activity. ";
    String status01 = "likes your activity.";
    manageLogInOut.signIn(username1, password);
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatus(status0, username2);
    intranetNotification.checkStatus(status01, username2);
    intranetNotification.checkActivityTitleInStatus(activity, true);
    intranetNotification.goToDetailLikeNotification(username2 + " " + username2, true);
    notificationActivity.checkLikeInActivityViewer("1");

    info("user 2 likes John's activity");
    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);

    info("Check Like Notification (2 like)");
    manageLogInOut.signIn(username1, password);
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    String status = username3 + " " + username3 + " " + "and" + " " + username2 + " " + username2 + " " + "like your activity. ";
    intranetNotification.checkAvatarInStatus(username3, true);
    intranetNotification.checkStatus(status, username3);
    intranetNotification.checkActivityTitleInStatus(activity, true);
    intranetNotification.goToDetailLikeNotification(username3 + " " + username3, true);
    notificationActivity.checkLikeInActivityViewer("2");

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
    info("Check Like Notification (4 like)");
    info("Check Like notification in intranet notification");
    manageLogInOut.signIn(username1, password);
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    String status2 = "likes your activity.";
    intranetNotification.checkAvatarInStatus(username5, true);
    intranetNotification.checkStatus(status2, username5);
    intranetNotification.checkActivityTitleInStatus(activity, true);
    info("Check comment notification in activity Viewer");
    intranetNotification.goToDetailLikeNotification(username5 + " " + username5, true);
    notificationActivity.checkLikeInActivityViewer("4");

    info("Check Like Notification on activity with a link");
    manageLogInOut.signIn(username6, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(textDes);

    info("Check Like Notification on activity with a link");
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
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    String status3 = username6 + " " + username6 + " " + "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username6, true);
    intranetNotification.checkStatus(status3, username6);
    intranetNotification.checkActivityTitleInStatus(title, true);
    /*
     * Step number: 3 Step Name: Step 3 : Read the notification Step Description: -
     * Click the notification area Input Data: Expected Outcome: - The activity is
     * displayed in the activity viewer with all comment expanded.
     */
    intranetNotification.goToDetailLikeNotification(username6 + " " + username6, true);
    notificationActivity.checkLikeInActivityViewer("1");

    info("Check Like Notification on activity with a wiki page");
    manageLogInOut.signIn(username7, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(name1);
    info("Check Like Notification on activity with a wiki page");
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
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    String status4 = username7 + " " + username7 + " " + "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username7, true);
    intranetNotification.checkStatus(status4, username7);
    intranetNotification.checkActivityTitleInStatus(name1, true);

    /*
     * Step number: 3 Step Name: Step 3 : Read the notification Step Description: -
     * Click the notification area Input Data: Expected Outcome: - The activity is
     * displayed in the activity viewer with all comment expanded.
     */
    intranetNotification.goToDetailLikeNotification(username7 + " " + username7, true);
    notificationActivity.checkLikeInActivityViewer("1");

    info("Check Like Notification when a new like is pushed");
    manageLogInOut.signIn(username9, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity);

    manageLogInOut.signIn(username1, password);
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    String status6 = "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username9, true);
    intranetNotification.checkStatus(status6, username9);
    intranetNotification.checkActivityTitleInStatus(activity, true);


    manageLogInOut.signIn(username8, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(name);

    info("Check Like Notification on activity with an event");
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
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    String status5 = username8 + " " + username8 + " " + "likes your activity. ";
    intranetNotification.checkAvatarInStatus(username8, true);
    intranetNotification.checkStatus(status5, username8);
    intranetNotification.checkActivityTitleInStatus(name, true);

    /*
     * Step number: 3 Step Name: Step 3 : Read the notification Step Description: -
     * Click the notification area Input Data: Expected Outcome: - The activity is
     * displayed in the activity viewer with all comment expanded.
     */
    intranetNotification.goToDetailLikeNotification(username8 + " " + username8, true);
    notificationActivity.checkLikeInActivityViewer("1");
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

  }

  @BugInPLF("ECMS-7866")
  @Test
  public void test02_CheckLikeNotificationOnActivityWithAContent() {
    info("Test 5: Check Like Notification on activity with a content");
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();

    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";
    String password = "Aa123456";
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
  public void test03_CheckViewAllPageRightAfterANewLikeIsPushed() {
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@gmail.com";

    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@gmail.com";
    String password = "Aa123456";
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
    String status0 = "likes your activity. ";
    info("Test 11 Check View All page after reading a Like notification");
    manageLogInOut.signIn(username1, password);
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    intranetNotification.checkAvatarInStatus(username2, true);
    intranetNotification.checkStatus(status0, username2);
    intranetNotification.checkActivityTitleInStatus(activity1, true);
    homePagePlatform.goToHomePage();
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    intranetNotification.goToAllNotification();
    intranetNotification.checkAvatarInStatus(username2, false);
    intranetNotification.checkStatus(status0, username2);
    intranetNotification.checkActivityTitleInStatus(activity1, false);

    info("Connect with user");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username3);

    manageLogInOut.signIn(username3, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username1);
    homePagePlatform.goToHomePage();
    activityStream.likeActivity(activity1);

    manageLogInOut.signIn(username1, password);
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
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
