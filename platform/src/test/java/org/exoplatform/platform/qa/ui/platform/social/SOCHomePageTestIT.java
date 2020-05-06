package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_ACTIVITY_STREAM_CONTAINER;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.ELEMENT_SITEEXPLORER_LEFTBOX_ROOTNODE;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACES_LIST;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("sniff")
@Tag("social")
public class SOCHomePageTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  HomePagePlatform       homePagePlatform;

  ActivityStream         activityStream;

  ManageLogInOut         manageLogInOut;

  SiteExplorerHome       siteExplorerHome;

  CreateNewDocument      createNewDocument;

  SpaceManagement        spaceManagement;

  ConnectionsManagement  connectionsManagement;

  UserProfilePage        userProfilePage;

  AddUsers               addUsers;

  SpaceHomePage          spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    homePagePlatform = new HomePagePlatform(this);
    activityStream = new ActivityStream(this);
    manageLogInOut = new ManageLogInOut(this);
    siteExplorerHome = new SiteExplorerHome(this);
    createNewDocument = new CreateNewDocument(this);
    connectionsManagement = new ConnectionsManagement(this);
    userProfilePage = new UserProfilePage(this);
    addUsers = new AddUsers(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
  }

  /**
   * <li>Case ID:121915.</li>
   * <li>Test Case Name: Mention a user in activity composer.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Mentions on User
   * Activity Stream Step Description: - Login Intranet home - Hover the mouse
   * over the name of user and select My Activity streams - Write something
   * -Entering @ followed by the user name - Select name is wrapped in the input -
   * Click Share button Input Data: Expected Outcome: - The suggestion list is
   * hidden - In the activity stream, mentions are displayed as a link on
   * "Firstname Lastname" to the user's activities page
   */
  @Test
  public void test04_MentionAUserInActivityComposer() {
    info("Test 4: Mention a user in activity composer");
    String text = "text" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.mentionUserActivity(DATA_USER2, text);
    $(byText(text)).parent().shouldHave(Condition.text(PLFData.DATA_NAME_USER2+" "+text));
    activityStream.deleteactivity(text);
    // verify that the activity doesn't exist
    $(byText(text)).shouldNot(Condition.exist);
  }

  /**
   * <li>Case ID:121923.</li>
   * <li>Test Case Name: Share your status.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Share your status Step
   * Description: - Log in - Go to Home page - Input into activity input field and
   * click share Input Data: Expected Outcome: - The message will show in Activity
   * Stream
   */
  @Test
  public void test05_ShareYourStatus() {
    info("Test 5: Share your status");

    String name = "name" + getRandomNumber();

    homePagePlatform.goToHomePage();
    activityStream.addActivity(name, "");
    $(byText(name)).should(Condition.exist);
    activityStream.deleteactivity(name);
    // verify that the activity doesn't exist
    $(byText(name)).shouldNot(Condition.exist);
  }

  /**
   * <li>Case ID:121924.</li>
   * <li>Test Case Name: Add a document.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Go to Select File Dialog
   * Step Description: - Log in - Goto Homepage - In Activity Composer click File
   * (icon) Input Data: Expected Outcome: - Select File Dialog shows up Step
   * number: 2 Step Name: - Select document Step Description: - Browse the Select
   * File Dialog to choose a document - Click Select Input Data: Expected Outcome:
   * - A breadcrumb is displaying the current position of the user in the browsed
   * drive. - the name of the file should be displayed under the activity input
   * field. Step number: 3 Step Name: - Share the document Step Description: -
   * Click Share button Input Data: Expected Outcome: - Activity is added into
   * activity stream
   */
  @Test
  public void test06_AddADocument() {
    info("Test 6: Add a document");

    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    info("add file");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(name, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    info("share file from activity stream");
    ELEMENT_TAB_LINK.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    refresh();
    ELEMENT_LINK_TEXT_SELECT_FROM_EXISTING_UPLOAD.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TEXT_DOCUMENT_IN_DOC_ACTIVITY_POPUP.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SITE_MANAGEMENT_IN_DOC_ACTIVITY_POPUP.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_POPUP_DOC_ACTIVITY.find(byText(name)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_BUTTON_ATTASH_FILE_IN_DOC_ACTIVITY_POPUP.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byAttribute("data-original-title", name)).should(Condition.exist);
    $(ELEMENT_COMPOSER_SHARE_BUTTON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("delete data");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(name);

  }

  /**
   * <li>Case ID:121926.</li>
   * <li>Test Case Name: Add a link.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Add a link Step
   * Description: - Log in - Goto Homepage - In Activity Composer click Add Link -
   * Input URL and click Attach Input Data: Expected Outcome: - A input text field
   * shows up to users to input the URL - The infomation of URL is shown in
   * featured content parts click(hp.ELEMENT_PUBLICATION_ADDLINK);
   * type(hp.ELEMENT_PUBLICATION_ADDLINK_INPUT, "http://www.ted.com", false);
   * click(hp.ELEMENT_PUBLICATION_ADDLINK_ATTCHBTN); Step number: 2 Step Name: -
   * Share the document Step Description: - Click Share button Input Data:
   * Expected Outcome: - Activity is added into activity stream
   */
  @Test
  public void test08_AddALink() {
    info("Test 8: Add a link");
    String textDes = "textDes" + getRandomNumber();
    String link = "http://www.google.fr";

    homePagePlatform.goToHomePage();
    activityStream.addActivity(textDes, link);
    $(byText(link)).should(Condition.exist);
    $(byText(textDes)).should(Condition.exist);
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.deleteactivity(textDes);
    $(byText(link)).parent().parent().parent().parent().parent().waitUntil(Condition.disappear, Configuration.timeout);

  }

  /**
   * <li>Case ID:121927.</li>
   * <li>Test Case Name: Load previous activity page automatically.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Create more than 1 page
   * of activities (Default 20) Step Description: - Log in - Go to homepage and
   * create more than 20 activities Input Data: Expected Outcome: - more than 20
   * activities are created successfully Step number: 2 Step Name: - check the
   * loading automaticallyprevious activity page Step Description: - log out
   * andlog in again - go to home page - scroll to the bottom of the activity
   * stream Input Data: Expected Outcome: -the first page of last activities is
   * displayed - previous activities' pages are load automatically
   */
  @Test
  public void test09_LoadPreviousActivityPageAutomatically() {
    info("Test 9: Load previous activity page automatically");
    String textDesfirst = "textDesfirst" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password="123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1,password);
    activityStream.addActivity(textDesfirst, "");
    refresh();
    for (int i = 0; i <= 15; i++) {
      String act = "act" + getRandomNumber();
      activityStream.addActivity(act, "");
    }
    refresh();
    executeJavaScript("window.scrollBy(0,5500)", "");
    homePagePlatform.loadMoreActivities();
    $(byText(textDesfirst)).should(Condition.exist);
    manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:121928.</li>
   * <li>Test Case Name: Check [All activities] filter.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Goto social homepage
   * Step Description: - Log in - Goto homepage Input Data: Expected Outcome: -
   * Home page is displayed Step number: 2 Step Name: - Check [All activity]
   * filter Step Description: - In the drop -down select box, select [All
   * Activities] Input Data: Expected Outcome: - All activities are displayed in
   * activity stream
   */
  @Test
  public void test10_CheckAllActivitiesFilter() {
    info("Test 10 Check [All activities] filter");

    String text = "text" + getRandomNumber();
    homePagePlatform.goToHomePage();
    activityStream.addActivity(text, "");
    assertEquals("ALL ACTIVITIES", $(byId("DisplayModesDropDown")).getText());
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.deleteactivity(text);

  }

  /**
   * <li>Case ID:121929.</li>
   * <li>Test Case Name: Check [My Spaces] filter.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Goto social homepage
   * Step Description: - Log in - Goto homepage Input Data: Expected Outcome: -
   * Home page is displayed Step number: 2 Step Name: - Check [My Spaces] filter
   * Step Description: - In the drop -down select box, select [My Space] Input
   * Data: Expected Outcome: - It shows only activities created in space where the
   * user is a member
   */
  @Test
  public void test11_CheckMySpacesFilter() {
    info("Test 11 Check [My Spaces] filter");

    String space = "space" + getRandomNumber();

    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES),Condition.visible,1000);
    $(ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES).click();
    $(ELEMENT_PUBLICATION_DISPLAYMODE_MYSPACE_OPTION).click();
    $(byText(space)).should(Condition.exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);

  }

  /**
   * <li>Case ID:121930.</li>
   * <li>Test Case Name: Check [Connections] filter.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Goto intranet homepage
   * Step Description: - Log in - Goto intranet homepage Input Data: Expected
   * Outcome: - Home page is displayed Step number: 2 Step Name: - Check
   * [Connections] filter Step Description: - In the drop -down select box, select
   * [Connnection] Input Data: Expected Outcome: shows only activities created by
   * the user's connections and by the user himself, outside a space
   */
  @Test
  public void test12_CheckConnectionsFilter() {
    info("Test 12 Check [Connections] filter");
    String text = "text" + getRandomNumber();
    activityStream.addActivity(text, "");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_PUBLICATION_DISPLAYMODE_CONNECTION_OPTION).waitUntil(Condition.visible,Configuration.timeout).click();
    executeJavaScript("window.scrollBy(0,-1500)", "");
    $(byText(text)).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.deleteactivity(text);

  }

  /**
   * <li>Case ID:121931.</li>
   * <li>Test Case Name: Check [My Activities] filter.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Go to intranethomepage
   * Step Description: - Log in - Go to intranet homepage Input Data: Expected
   * Outcome: - Home page is displayed* Step number: 2 Step Name: - check [My
   * Activities] filter Step Description: - In the drop -down select box, select
   * [My Activities] Input Data: Expected Outcome: shows only activities where the
   * user has been @mentionned, the user has commented or liked, and the user's
   * activities (inside and outside a space)
   */

  @Test
  public void test13_CheckMyActivitiesFilter() {
    info("Test 13 Check [My Activities] filter");
    String space = "space" + getRandomNumber();
    String text = "text" + getRandomNumber();
    String text1 = "text" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space, 60000);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(DATA_USER1);
    homePagePlatform.goToHomePage();
    activityStream.addActivity(text, "");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    activityStream.addActivity(text1, "");
    homePagePlatform.goToMySpaces();
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES),Condition.visible,1000);
    $(ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES).click();
    $(ELEMENT_PUBLICATION_DISPLAYMODE_MYACTIVITIES_OPTION).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(space)).should(Condition.exist);
    $(byText(text)).shouldNot(Condition.exist);
    $(byText(text1)).should(Condition.exist);
    activityStream.deleteactivity(text1);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    activityStream.deleteactivity(text);

  }

  /**
   * <li>Case ID:121932.</li>
   * <li>Test Case Name: Check activities order.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Check order of
   * activities Step Description: - Log in and go to homepage - Check order of
   * activity Input Data: Expected Outcome: - Homepage is displayed - the order of
   * activities is based on activity's last action date. The last action date is
   * the latest of: 1. The publication date 2. The date of the last comment posted
   */

  @Test
  public void test14_CheckActivitiesOrder() {
    info("Test 14 Check activities order");
    String text = "text" + getRandomNumber();
    String text1 = "text1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    activityStream.addActivity(text, "");
    refresh();
    activityStream.addActivity(text1, "");
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(0).find(byText(text1)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(1).find(byText(text)).should(Condition.exist);
    activityStream.commentActivity(text, comment);
    refresh();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(0).find(byText(text)).should(Condition.exist);
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.deleteactivity(text);
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.deleteactivity(text1);
  }

  /**
   * <li>Case ID:121934.</li>
   * <li>Test Case Name: Check Layout of Activities.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Check layout of activity
   * Step Description: - log in and goto intranet hompage - Select an activity to
   * check its layout Input Data: Expected Outcome: An activity is made out of
   * different parts:(see attached) 1.the author 2.the author's avatar 3.the space
   * (optional) 4.the type (optional) 5.the activity message 6.the featured
   * content (optional) 7.the Action bar (Comment and Like links + custom actions)
   * 8.the like section (optional) 9.the comment section (optional)
   */
  @Test
  public void test16_CheckLayoutOfActivities() {
    info("Test 16 Check Layout of Activities");

    String name = "" + getRandomNumber();

    homePagePlatform.goToHomePage();
    activityStream.addActivity(name, "");
    $(byText(name)).parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .find(byText(DATA_NAME_USER1))
                   .should(Condition.exist);
    $(byText(name)).parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .find(ELEMENT_PUBLICATION_FIRSTPOST_AUTHORAVATAR)
                   .should(Condition.exist);
    $(byText(name)).parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .find(ELEMENT_PUBLICATION_FIRSTPOST_ACTIVITYTEXT)
                   .should(Condition.exist);
    $(byText(name)).parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .parent()
                   .find(ELEMENT_ICONCOMMENT)
                   .should(Condition.exist);
    $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(ELEMENT_ICONLIKE).should(Condition.exist);
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.deleteactivity(name);
  }

  /**
   * <li>Case ID:121935.</li>
   * <li>Test Case Name: View Comments.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Check view of comment
   * Step Description: - goto home page - select an activity with more than 2
   * comments on it (fx: 10 comments) - click on the message "View all 10
   * comments" Input Data: Expected Outcome: - onlythe latest (most recently
   * posted) two comments are displayed below the activity. - "View all 10
   * comments" message is shown - all comments is displayed, in the time order
   * (oldest at the top)
   */
  @Test
  public void test17_ViewComments() {
    info("Test 17 View Comments");

    String name = "name" + getRandomNumber();

    activityStream.addActivity(name, "");

    for (int i = 0; i <= 9; i++) {
      String comment = "comment" + getRandomNumber();
      activityStream.commentActivity(name, comment);
      refresh();
      executeJavaScript("window.scrollBy(0,-1500)", "");
    }
    String text = "text" + getRandomNumber();
    String text1 = "text1" + getRandomNumber();
    activityStream.commentActivity(name, text);
    refresh();
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.commentActivity(name, text1);
    refresh();
    executeJavaScript("window.scrollBy(0,-1500)", "");
    info("Verify that only second last and last comment are shown");
    $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(byText(text)).should(Condition.exist);
    $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(byText(text1)).should(Condition.exist);
    info("Verify that view all comment links is shown and clickable on it");
    String id = $(byText(name)).parent()
                               .parent()
                               .parent()
                               .parent()
                               .parent()
                               .parent()
                               .parent()
                               .getAttribute("id")
                               .split("UIActivityLoader")[1];
    $(byXpath(ELEMENT_LINK_TEXT_VIEW_ALL_COMMENTS.replace("{id}", id))).click();
    $(byXpath(ELEMENT_LINK_TEXT_VIEW_ALL_COMMENTS.replace("{id}", id))).waitUntil(Condition.hasText("Hide all 12 comments."),
                                                                                  Configuration.timeout);
    info("Verify that all comments is shown");
    assertEquals(13, $(byId(ELEMENT_LIST_ALL_COMMENNTS.replace("{id}", id))).findAll(byClassName("commentItem")).size());
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.deleteactivity(name);
    refresh();

  }

  /**
   * <li>Case ID:121936.</li>
   * <li>Test Case Name: Mention a user in comment.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  public void test18_MentionAUserInComment() {
    info("Test 18 Mention a user in comment");
    String name = "name" + getRandomNumber();
    String text = "text" + getRandomNumber();

    homePagePlatform.goToHomePage();
    activityStream.addActivity(name, null);
    activityStream.addCommentWithMentionUser(name, DATA_USER2, text);
    $(byText(text)).parent().shouldHave(Condition.text(PLFData.DATA_NAME_USER2+" "+text));
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.deleteactivity(name);
  }

  /**
   * <li>Case ID:121937.</li>
   * <li>Test Case Name: Relation Activity.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Invite another user Step
   * Description: - Login with User A and go to Intranet - Go to Connections -
   * Find user B and send a request Input Data: Expected Outcome: - Request is
   * sent to the user B Step number: 2 Step Name: - Accept request Step
   * Description: - Login as user B and goto my Connection - Accept the request
   * from user A Input Data: Expected Outcome: - A Relation activity is displayed
   * to the activity stream
   */

  @Test
  public void test19_RelationActivity() {
    info("Test 19 Relation Activity");
    String username = "username" + getRandomString();
    String email = username + "@test.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email, username, username);
    String comment = "I'm now connected with 1 user(s)";
    manageLogInOut.signIn(username,password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username);
    homePagePlatform.goToHomePage();
    $(byText(username+" "+username)).parent().parent().parent().parent().find(byText(comment)).should(Condition.exist);
    manageLogInOut.signIn(PLFData.DATA_USER1,"gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username);
  }

  /**
   * <li>Case ID:121938.</li>
   * <li>Test Case Name: Create a new space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Create a new Space Step
   * Description: - Goto homepage - Click [Join a space] on left Navigation -
   * Click [Add new space] button - Fill the information and click create - Check
   * homepage Input Data: Expected Outcome: - a new space is created - an activity
   * is added into activity stream - Informations displayed in the featured
   * content are : 1. Space's avatar 2. Space's description 3. Number of members.
   */
  @Test
  public void test20_CreateASpace() {
    info("Test 20 Create a new space");

    String space = "space" + getRandomNumber();
    String contentSpace = "contentSpace" + getRandomNumber();
    String comment = "1 Member";

    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, contentSpace);

    homePagePlatform.goToHomePage();
    $(byText(space)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(contentSpace)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(comment)).should(Condition.exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  /**
   * <li>Case ID:121942.</li>
   * <li>Test Case Name: Update Profile - change of avatar.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Change Avatar Step
   * Description: - Connect to Intranet - Click username on Top Navitgation -> My
   * Profile - Click [Change the avatar] and upload new avatar - Check homepage
   * Input Data: Expected Outcome: - A user profile activity is updated in the
   * activity stream - A comment is added: Avatar has been updated.
   */
  @Test
  @Tag("sabis")
  public void test24_UpdateProfileChangeOfAvatar() {
    info("Test 24: Update Profile - change of avatar");
    String comment = "Avatar has been updated.";
    manageLogInOut.signIn(DATA_USER2, password);

    navigationToolbar.goToMyProfile();
    userProfilePage.goToEditProfile();
    userProfilePage.changeAvatar();

    homePagePlatform.goToHomePage();
    $(byText(comment)).should(Condition.exist);
  }

  /**
   * <li>Case ID:121943.</li>
   * <li>Test Case Name: Update Profile - Update Basic information.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Change Avatar Step
   * Description: - Connect to Intranet - Click username on Top Navitgation -> My
   * Profile - Click Edit to edit basic information - Check homepage Input Data:
   * Expected Outcome: - A user profile activity is updated in the activity stream
   * - A comment is added: Basic informations has been updated.
   */
  @Test
  public void test25_UpdateProfileUpdateBasicInformation() {
    info("Test 25 Update Profile - Update Basic information");
    String username = "username" + getRandomString();
    String email = username + "@test.com";
    String email1 = getRandomString() + "@test.com";
    String password = "123456";
    String comment = "Contact information has been updated.";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email, username, username);

    manageLogInOut.signIn(username, password);
    navigationToolbar.goToMyProfile();
    userProfilePage.goToEditProfile();
    userProfilePage.updateBasicInformation("", "", email1);
    userProfilePage.saveCancelUpdateInfo(true);
    homePagePlatform.goToHomePage();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(comment)).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username);
  }

  /**
   * <li>Case ID:121944.</li>
   * <li>Test Case Name: Check order of the activities.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Check the order of
   * activities Step Description: - Login to the platform - Create activities of
   * different kinds (ECMS webcontents, ECMS symlinks, share files to Personal
   * Documents/Public, create space ...etc), at least 10 activities Input Data:
   * Expected Outcome: - The activities should be displayed in the good order
   * (newest at the top) - We have only 1 activity per kind (no duplication
   */
  @Test
  @Tag("sabis")
  public void test26_CheckOrderOfTheActivities() {
    info("Test 26 Check order of the activities");

    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String name2 = "name2" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    String name3 = "name3" + getRandomNumber();
    String content3 = "content3" + getRandomNumber();
    manageLogInOut.signIn(DATA_USER2,DATA_PASS);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(name, content);
    createNewDocument.saveAndClose();
    sleep(Configuration.timeout);
    click(ELEMENT_SITEEXPLORER_LEFTBOX_ROOTNODE);
    sleep(2000);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(name2, content2);
    createNewDocument.saveAndClose();
    click(ELEMENT_SITEEXPLORER_LEFTBOX_ROOTNODE);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(name3, content3);
    homePagePlatform.goToHomePage();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(0).find(byText(content3)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(1).find(byText(name2)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(2).find(byText(name)).should(Condition.exist);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(name);
    siteExplorerHome.deleteData(name2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(name3, false);

  }

  /**
   * <li>Case ID:121941.</li>
   * <li>Test Case Name: Promote a member as manager.</li>
   * <li>Pre-Condition: a space activity is shared in the activity stream</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Promote a member as
   * manager Step Description: - Connect to Intranet - Open a space - Click
   * [Settings] -> Members - Click Grant Manager to promote the user we want -
   * Back to the Homepage Input Data: Expected Outcome: - The Space activity
   * content isn't updated in the activity stream - A comment is added: $user has
   * been promoted as space's manager.
   */
  @Test
  public void test23_PromoteAMemberAsManager() {
    info("Test 23: Promote a member as manager");

    String space = "space" + getRandomNumber();
    String contentSpace = "contentSpace" + getRandomNumber();

    String username = "username" + getRandomString();
    String email = username + "@gmail.com";
    String username1 = "usernameb" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String fullName1 = username1 + " " + username1;
    String password = "123456";
    String comment = "has been promoted as the space's manager.";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email, username, username);
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username, password);

    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, contentSpace);

    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username1, false, "");

    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);

    manageLogInOut.signIn(username, password);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space)).click();
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.changeRole(fullName1);

    homePagePlatform.goToHomePage();
    $(byText(comment)).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username);
    addUsers.deleteUser(username1);

  }

  /**
   * <li>Case ID:121939.</li>
   * <li>Test Case Name: User join a space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Create a new Space Step
   * Description: - Goto homepage - Click [Join a space] on left Navigation -
   * Click [Add new space] button - Fill the information and click create - Check
   * homepage Input Data: Expected Outcome: - a new space is created - an activity
   * is added into activity stream - Informations displayed in the featured
   * content are : 1. Space's avatar 2. Space's description 3. Number of members.
   * Step Number: 2 Step Name: - Invite other user Step Description: - goto Space
   * setting -> member - Click [select user] icon and select user B to invite -
   * Click [Invite] Icon Input Data: Expected Outcome: - User is added into the
   * table below and status in [Actions] column is [Cancel request] Step Number: 3
   * Step Name: - User B join space Step Description: - Log in as user B - Click
   * [Join a space] - Click [accept] to join the space - Check homepage Input
   * Data: Expected Outcome: - A comment is added into activity - Message: "Has
   * joined the space." is shown
   */

  @Test
  public void test21_UserJoinASpace() {
    info("Test 21: User join a space");

    String space = "space" + getRandomNumber();
    String contentSpace = "contentSpace" + getRandomNumber();
    String comment = "Has joined the space.";
    manageLogInOut.signIn(DATA_USER2, password);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, contentSpace);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(DATA_USER1, false, "");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    homePagePlatform.goToHomePage();
    info("check home page");
    $(byText(comment)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(comment)).parent().parent().parent().parent().find(byText(DATA_NAME_USER2)).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  /**
   * <li>Case ID:121940.</li>
   * <li>Test Case Name: Rename a space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Rename space Step
   * Description: - Connect to Intranet and goto space - Click [setting] to edit
   * space - In the setting form, rename space - Back to the Homepage Input Data:
   * Expected Outcome: - The Space activity is updated in the activity stream
   * withy the new title - A comment is added: Name has been updated to: $value.
   */
  @Test
  public void test22_RenameASpace() {
    info("Test 22: Rename a Space");
    String space = "space" + getRandomNumber();
    String contentSpace = "contentSpace" + getRandomNumber();
    String newSpace = "newSpace" + getRandomNumber();
    String comment = "Name has been updated to: {spaceName}.";

    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, contentSpace);
    spaceHomePage.goToSpaceSettingTab();
    spaceManagement.editSpaceSimple(space, newSpace, "", false, "");
    spaceManagement.saveChangesSpace();
    homePagePlatform.goToHomePage();
    $(byText(comment.replace("{spaceName}", newSpace))).should(Condition.exist);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(newSpace, false);

  }

  @Tag("SOC-5646")
  @Test
  public void test23_SpecialisationOfDollarInActivityMessage(){

    homePagePlatform.goToHomePage();
    info("add an activity with $ and check that is added succesfully");
    activityStream.addActivity("$ {val}", "");
    activityStream.checkActivity("$ {val}");
    info("delete activity");
    activityStream.deleteactivity("$ {val}");
  }
  @Test
  @Tag("PLF-8135")
  public void test23_CheckPaddingOfSpacesAtLeftAndRightOfActivities(){
    homePagePlatform.goToHomePage();
    assertEquals("40px", ELEMENT_ACTIVITY_CONTAINER.getCssValue("padding-top"));
    assertEquals("20px", ELEMENT_ACTIVITY_CONTAINER.getCssValue("padding-right"));
    assertEquals("40px", ELEMENT_ACTIVITY_CONTAINER.getCssValue("padding-bottom"));
    assertEquals("20px", ELEMENT_ACTIVITY_CONTAINER.getCssValue("padding-left"));
  }
}
