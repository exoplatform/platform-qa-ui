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
  @Tag("PLF-8135")
  @Tag("SOC-5646")
  public void test01_CheckAllActivitiesFilter() {

    String name = "" + getRandomNumber();
    String text = "text" + getRandomNumber();
    String textDes = "textDes" + getRandomNumber();
    String link = "http://www.google.fr";
    String text1 = "text1" + getRandomNumber();
    String text2 = "text" + getRandomNumber();
    String text3 = "text1" + getRandomNumber();
    String text4 = "text" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String comment1 = "comment" + getRandomNumber();

    homePagePlatform.goToHomePage();
    info("Mention a user in activity composer");
    activityStream.mentionUserActivity(DATA_USER2, text4);
    $(byText(text4)).parent().shouldHave(Condition.text(PLFData.DATA_NAME_USER2+" "+text4));
    info("Check Padding Of Spaces At Left And Right Of Activities");
    assertEquals("40px", ELEMENT_ACTIVITY_CONTAINER.getCssValue("padding-top"));
    assertEquals("20px", ELEMENT_ACTIVITY_CONTAINER.getCssValue("padding-right"));
    assertEquals("40px", ELEMENT_ACTIVITY_CONTAINER.getCssValue("padding-bottom"));
    assertEquals("20px", ELEMENT_ACTIVITY_CONTAINER.getCssValue("padding-left"));
    homePagePlatform.goToHomePage();
    activityStream.addActivity(text, "");
    info("Check All Activities Filter");
    assertEquals("ALL ACTIVITIES", $(byId("DisplayModesDropDown")).getText());
    executeJavaScript("window.scrollBy(0,-1500)", "");
    refresh();
    activityStream.addActivity(text1, "");
    $(byText(text1)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    info("Check Activities Order");
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(0).find(byText(text1)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(1).find(byText(text)).should(Condition.exist);
    activityStream.commentActivity(text, comment);
    refresh();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(0).find(byText(text)).should(Condition.exist);
    executeJavaScript("window.scrollBy(0,-1500)", "");
    homePagePlatform.goToHomePage();
    info("Add an activity with $ and check that is added succesfully");
    activityStream.addActivity("$ {val}", "");
    activityStream.checkActivity("$ {val}");
    homePagePlatform.goToHomePage();
    info("Check Layout of Activities");
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
    info("View Comments");
    for (int i = 0; i <= 9; i++) {
      activityStream.commentActivity(name, comment1);
      refresh();
      executeJavaScript("window.scrollBy(0,-1500)", "");
    }
    activityStream.commentActivity(name, text2);
    refresh();
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.commentActivity(name, text3);
    refresh();
    executeJavaScript("window.scrollBy(0,-1500)", "");
    info("Verify that only second last and last comment are shown");
    $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(byText(text2)).should(Condition.exist);
    $(byText(name)).parent().parent().parent().parent().parent().parent().parent().find(byText(text3)).should(Condition.exist);
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
    info("Mention a user in comment");
    activityStream.gotoActivityViewer(name);
    activityStream.addCommentWithMentionUser(name, DATA_USER2, text);
    $(byText(text)).parent().shouldHave(Condition.text(PLFData.DATA_NAME_USER2+" "+text));
    executeJavaScript("window.scrollBy(0,-1500)", "");
    homePagePlatform.goToHomePage();
    info("Add a link");
    activityStream.addActivity(textDes, link);
    $(byText(link)).should(Condition.exist);
    $(byText(textDes)).should(Condition.exist);
    executeJavaScript("window.scrollBy(0,-1500)", "");

    info("Delete activities");
    homePagePlatform.goToHomePage();
    activityStream.deleteactivity(name);
    activityStream.deleteactivity("$ {val}");
    activityStream.deleteactivity(text);
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.deleteactivity(text1);
    // verify that the activity doesn't exist
    $(byText(text1)).waitUntil(Condition.not(Condition.exist),Configuration.openBrowserTimeoutMs);
    activityStream.deleteactivity(text4);
    // verify that the activity doesn't exist
    $(byText(text4)).shouldNot(Condition.exist);
    activityStream.deleteactivity(textDes);
    $(byText(link)).parent().parent().parent().parent().parent().waitUntil(Condition.disappear, Configuration.timeout);
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
  public void test02_AddADocumentThenUpdateProfileThenCheckConnectionsFilterThenLoadPreviousActivityPageAutomatically() {

    String text = "text" + getRandomNumber();
    String comment = "Avatar has been updated.";
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String comment2 = "I'm now connected with 1 user(s)";
    String comment1 = "Contact information has been updated.";
    String textDesfirst = "textDesfirst" + getRandomNumber();
    String username = "username" + getRandomString();
    String password = "123456";
    String email = username + "@test.com";
    String email1 = getRandomString() + "@test.com";

    activityStream.addActivity(text, "");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(name, content);
    createNewDocument.saveAndClose();
    homePagePlatform.goToHomePage();
    info("share file from activity stream");
    ELEMENT_ACTIVITY_COMPOSER_FILE_TAB.click();
    refresh();
    ELEMENT_LINK_TEXT_SELECT_FROM_EXISTING_UPLOAD.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TEXT_DOCUMENT_IN_DOC_ACTIVITY_POPUP.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SITE_MANAGEMENT_IN_DOC_ACTIVITY_POPUP.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_POPUP_DOC_ACTIVITY.find(byText(name)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_BUTTON_ATTASH_FILE_IN_DOC_ACTIVITY_POPUP.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byAttribute("data-original-title", name)).should(Condition.exist);
    $(ELEMENT_COMPOSER_SHARE_BUTTON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email, username, username);
    manageLogInOut.signIn(username,password);
    info("Update Profile - Update Basic information");
    navigationToolbar.goToMyProfile();
    userProfilePage.goToEditProfile();
    userProfilePage.updateBasicInformation("", "", email1);
    userProfilePage.saveCancelUpdateInfo(true);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(DATA_USER2);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(username);
    connectionsManagement.acceptAConnection(DATA_USER1);
    info("Update Profile - change of avatar");
    navigationToolbar.goToMyProfile();
    userProfilePage.goToEditProfile();
    userProfilePage.changeAvatar();
    homePagePlatform.goToHomePage();
    $(byText(comment)).should(Condition.exist);
    info("Relation Activity");
    homePagePlatform.goToHomePage();
    $(byText(username+" "+username)).parent().parent().parent().parent().find(byText(comment2)).should(Condition.exist);
    info("Check Connections Filter");
    $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_PUBLICATION_DISPLAYMODE_CONNECTION_OPTION).waitUntil(Condition.visible,Configuration.timeout).click();
    executeJavaScript("window.scrollBy(0,-1500)", "");
    $(byText(text)).should(Condition.exist);
    manageLogInOut.signIn(username,password);
    homePagePlatform.goToHomePage();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(comment1)).should(Condition.exist);
    info("Load previous activity page automatically");
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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    executeJavaScript("window.scrollBy(0,-1500)", "");
    activityStream.deleteactivity(text);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username);
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(name);

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
  public void test03_CheckMyActivitiesFilter() {
    info("Check My Activities Filter");
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

  @Test
  public void test04_UserJoinASpaceAndCheckOrderOfActivitiesAndPromoteAMemberAsManager() {

    String space = "space" + getRandomNumber();
    String contentSpace = "contentSpace" + getRandomNumber();
    String comment = "Has joined the space.";
    String space2 = "space" + getRandomNumber();
    String contentSpace2 = "contentSpace" + getRandomNumber();
    String newSpace2 = "newSpace" + getRandomNumber();
    String comment2 = "Name has been updated to: {spaceName}.";
    String comment3 = "1 Member";
    String comment4 = "has been promoted as the space's manager.";
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String name2 = "name2" + getRandomNumber();
    String content2 = "content2" + getRandomNumber();
    String name3 = "name3" + getRandomNumber();
    String content3 = "content3" + getRandomNumber();
    String space3 = "space" + getRandomNumber();
    String contentSpace3 = "contentSpace" + getRandomNumber();
    String username = "username" + getRandomString();
    String email = username + "@gmail.com";
    String username1 = "usernameb" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String fullName1 = username1 + " " + username1;
    String password = "123456";

    info("Promote a member as manager");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email, username, username);
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username, password);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space3, contentSpace3);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(username1, false, "");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space3);
    manageLogInOut.signIn(username, password);
    homePagePlatform.goToMySpaces();
    ELEMENT_SPACES_LIST.find(byText(space3)).click();
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.changeRole(fullName1);
    homePagePlatform.goToHomePage();
    $(byText(comment4)).should(Condition.exist);
    info("Rename a Space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, contentSpace2);
    homePagePlatform.goToHomePage();
    info("Create a new space");
    $(byText(space2)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(contentSpace2)).should(Condition.exist);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.find(byText(comment3)).should(Condition.exist);
    info("Check My Spaces Filter");
    homePagePlatform.refreshUntil($(ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES),Condition.visible,1000);
    $(ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES).click();
    $(ELEMENT_PUBLICATION_DISPLAYMODE_MYSPACE_OPTION).click();
    $(byText(space2)).should(Condition.exist);
    homePagePlatform.goToSpecificSpace(space2);
    spaceHomePage.goToSpaceSettingTab();
    spaceManagement.editSpaceSimple(space2, newSpace2, "", false, "");
    spaceManagement.saveChangesSpace();
    homePagePlatform.goToHomePage();
    $(byText(comment2.replace("{spaceName}", newSpace2))).should(Condition.exist);
    info("User join a space");
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    info("Check order of the activities");
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
    createNewDocument.addNewWebContent(name, content);
    createNewDocument.saveAndClose();
    $(ELEMENT_SITEEXPLORER_LEFTBOX_ROOTNODE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    createNewDocument.createNewDoc(CreateNewDocument.selectDocumentType.FILE);
    createNewDocument.addNewFile(name2, content2);
    createNewDocument.saveAndClose();
    $(ELEMENT_SITEEXPLORER_LEFTBOX_ROOTNODE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.goToAddNewContent();
    info("Create new file document");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(name3, content3);
    homePagePlatform.goToHomePage();
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(0).find(byText(content3)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    ELEMENT_ACTIVITY_STREAM_CONTAINER.findAll(ELEMENT_BOX_ACTIVITY).get(1).find(byText(name2)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    $(byXpath("//*[@class='fileNameParentLink']//*[contains(text(),'${name}')]".replace("${name}",name))).isDisplayed();
    navigationToolbar.goToSiteExplorer();
    siteExplorerHome.deleteData(name);
    siteExplorerHome.deleteData(name2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(name3, false);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, contentSpace);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.inviteUser(DATA_USER1, false, "");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    homePagePlatform.goToHomePage();
    info("Check Home Page");
    $(byText(comment)).parent().parent().parent().parent().find(byText(DATA_NAME_USER1)).should(Condition.exist);
    $(byText(comment)).parent().parent().parent().parent().find(byText(DATA_NAME_USER2)).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(newSpace2, false);
    spaceManagement.deleteSpace(space3, false);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username);
    addUsers.deleteUser(username1);

  }

}
