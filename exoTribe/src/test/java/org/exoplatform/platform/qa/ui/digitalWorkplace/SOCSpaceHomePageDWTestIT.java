package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseDW;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_SPACE_MEMBERS_SEARCH_TEXT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("dw")
public class SOCSpaceHomePageDWTestIT extends BaseDW {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream activityStream;

  TribeActivityStream tribeActivityStream;

  SpaceHomePage spaceHomePage;

  SpaceManagement spaceManagement;

  TribeSpaceManagement tribeSpaceManagement;

  SpaceSettingManagement spaceSettingManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    homePagePlatform = new HomePagePlatform(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    tribeActivityStream = new TribeActivityStream(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

  }

  @Test
  public void test01_LikeActivity() {

    String activity1 = "activity1" + getRandomNumber();
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
    info("Like Activity");
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);
    tribeActivityStream.likeActivityDW(activityId);
    // click on the activity to appear the delete button
    tribeActivityStream.deleteactivityDW(activity1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test02_AddComment() {

    info("Add comment");

    String activity1 = "activity1" + getRandomNumber();
    String comment = "comment" + getRandomNumber();
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);
    tribeActivityStream.addActivityComment(activityId, comment);

    info("Delete comment");
    activityStream.deletecommentDW(activity1, comment);
    // verify that the comment is deleted
    $(byText(comment)).waitUntil(Condition.not(Condition.exist), Configuration.openBrowserTimeoutMs);

    // click on the activity to appear the delete button
    tribeActivityStream.deleteactivityDW(activity1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test03_UploadPictureAndOpenItInDocument() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String attachedFile = "eXo-Platform.png";
    String activity = "activity" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload the picture --");
    tribeActivityStream.uploadFileActivityStreamDW(attachedFile);
    tribeActivityStream.addMessage(activity);
    tribeActivityStream.postActivity();
    info("-- Verify that an activity has been added --");
    sleep(1000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    $(byText(attachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    info("Preview The Attached File");
    tribeActivityStream.previewAttachedFile(attachedFile);

    info("Open The Attached File In Documents");
    tribeActivityStream.openAttachedFileInDocuments(attachedFile);
    tribeActivityStream.verifyThatPictureAttachedFileIsDisplayedInDocuments(attachedFile);

    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Delete The Attached File");
    tribeActivityStream.deleteAttachedFileDW(attachedFile);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  @BugInPLF("Bug")
  public void test04_UploadDocxOpenItInDocumentAndEditItInOnlyOffice() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String attachedFile = "docx_test.docx";
    String document = "docx_test";
    String extension = ".docx";
    String activity = "activity" + getRandomNumber();


    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload the Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(attachedFile);
    tribeActivityStream.addMessage(activity);
    tribeActivityStream.postActivity();
    info("-- Verify that an activity has been added --");
    sleep(1000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    $(byText(attachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    info("Preview The Attached File");
    tribeActivityStream.previewAttachedFile(attachedFile);

    info("Open The Attached File In Documents");
    tribeActivityStream.openAttachedFileInDocuments(attachedFile);
    tribeActivityStream.verifyThatWordAttachedFileIsDisplayedInDocuments(attachedFile);
    tribeActivityStream.editFileInOnlyOfficeFromDocumentsTab(attachedFile);
    switchTo().window(1);
    info("Check That The Document is opened with Edit Online");
    tribeActivityStream.checkOpeningDocumentWithEditOnlineDW(document, extension, DATA_NAME_USER1);

    switchTo().window(1).close();
    switchTo().window(0);

    homePagePlatform.goToHomeSpaceTribe();

    info("Delete The Attached File");
    tribeActivityStream.deleteAttachedFileDW(attachedFile);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test05_CheckThatSpaceBannerEditButtonIsDisplayed() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    info("Check that Space Banner is displayed");
    ELEMENT_SPACE_BANNER_TRIBE.waitUntil(Condition.visible, openBrowserTimeoutMs);

    info("Check that Space Banner Edit Icon is displayed");
    ELEMENT_SPACE_BANNER_TRIBE.waitUntil(Condition.visible, openBrowserTimeoutMs).hover();
    ELEMENT_EDIT_SPACE_BANNER_TRIBE_BUTTON.waitUntil(Condition.visible, openBrowserTimeoutMs);

    homePagePlatform.goToMySpacesTribeViaUrl();

    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test06_CheckTheSpaceMembersBehavior() {

    String spaceName = "space" + getRandomNumber();
    String spaceDes = "descriptiona" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");
    addUsers.addUserTribe(username2, password, email2, username2, username2, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceName, spaceDes, "Validation", "No", null);

    info("Go to Spaces Members Page");
    spaceSettingManagement.goToSpaceMembersTabDW(spaceName);

    info("Check that Filter is displayed");
    ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.getAttribute("placeholder"), "Filter by name, position or skill");

    ELEMENT_SPACE_MEMBERS_SHOWING_RESULTS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    String resultsNumber = ELEMENT_SPACE_MEMBERS_SHOWING_RESULTS_DW.getText().split("Showing ")[1].split(" results")[0];
    info(resultsNumber + "Results are displayed");

    info("Check that Pulldown Filter is displayed");
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Invite an user to join the space");
    tribeSpaceManagement.inviteUsersToJoinSpaceViaSpaceMembersDW(inviteUsers);

    info("Check Space Guests Pull Down Filter");
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Invited");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", username1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Invited user accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceName);
    homePagePlatform.goToSnapshotPageTribeViaUrl();

    info("Request to join the space");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceName);
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceName))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    sleep(3000);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceName))).getText(), "Cancel request");

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceName);
    tribeSpaceManagement.accessToSearchedSpace();
    spaceSettingManagement.goToSpaceMembersTabDW(spaceName);

    info("Check Space Members Pull Down Filter");
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Members");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", username1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", DATA_NAME_USER1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check Space Administrators Pull Down Filter");
      ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Managers");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", DATA_NAME_USER1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check Space Requests Pull Down Filter");
    sleep(2000);
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Requested");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", username2))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check that " + username1 + " is not displayed in Space Guests Pull Down Filter");
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Invited");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", username1))).waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceName);

  }

  @Test
  public void test07_CheckMembersCardBehavior() {

    String spaceName = "space" + getRandomNumber();
    String spaceDes = "descriptiona" + getRandomNumber();

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");
    addUsers.addUserTribe(username2, password, email2, username2, username2, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceName, spaceDes, "Open", "No", inviteUsers);

    info("Invited user " + username1 + " accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceName);
    homePagePlatform.goToSnapshotPageTribeViaUrl();

    info("Invited user " + username2 + " accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(spaceName);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceName))).getText(), "Join");
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceName))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceName);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Go to Spaces Members Page");
    spaceSettingManagement.goToSpaceMembersTabDW(spaceName);

    info("Check that Filter is displayed");
    ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.getAttribute("placeholder"), "Filter by name, position or skill");

    ELEMENT_SPACE_MEMBERS_SHOWING_RESULTS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    String resultsNumber = ELEMENT_SPACE_MEMBERS_SHOWING_RESULTS_DW.getText().split("Showing ")[1].split(" results")[0];
    Assert.assertEquals(resultsNumber, "3");
    info(resultsNumber + "Results are displayed");

    info("Check that Pulldown Filter is displayed");
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that " + username1 + "," + username2 + "," + DATA_NAME_USER1 + " are displayed in Space Members Pull Down Filter");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", username1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", username2))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", DATA_NAME_USER1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);


    info("Check member " + DATA_NAME_USER1 + " card");
    ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys(DATA_NAME_USER1);
    ELEMENT_CONTACT_AVATAR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_ADD_CONTACT_COVER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals($(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}",DATA_NAME_USER1))).getText(),DATA_NAME_USER1);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    Assert.assertEquals(ELEMENT_FILTER_USER_ABOUT_ME_TEXT_DW.getText(),"About me");
    Assert.assertEquals(ELEMENT_FILTER_USER_CONTACTS_TEXT_DW.getText(),"Connections");
    Assert.assertEquals(ELEMENT_FILTER_USER_SPACES_TEXT_DW.getText(),"Spaces");

    info(DATA_NAME_USER1 + " Contacts Number is " + ELEMENT_FILTER_USER_CONTACTS_NUMBER_DW.getText());
    info(DATA_NAME_USER1 + " Spaces Number is " + ELEMENT_FILTER_USER_SPACES_NUMBER_DW.getText());

    sleep(2000);
    ELEMENT_FILTER_CLOSE_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Space Manager Icon is displayed");
    ELEMENT_FILTER_SPACE_MANAGER_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    sleep(2000);
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is not displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    info("Send Cauris Button is not displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_DW.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    info("Remove Member Button is not displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    info("Remove the role Manager Button is not displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    info("Set As Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Set as redactor");
    info("Open Chat Button is not displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();


    info("Check member " + username1 + " card");
    spaceSettingManagement.goToSpaceMembersTabDW(spaceName);
    ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys(username1);
    ELEMENT_CONTACT_AVATAR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_ADD_CONTACT_COVER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_ADD_CONTACT_FULLNAME_DW.getText(),username1 + " " + username1);
    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    Assert.assertEquals(ELEMENT_FILTER_USER_ABOUT_ME_TEXT_DW.getText(),"About me");
    Assert.assertEquals(ELEMENT_FILTER_USER_CONTACTS_TEXT_DW.getText(),"Connections");
    Assert.assertEquals(ELEMENT_FILTER_USER_SPACES_TEXT_DW.getText(),"Spaces");

    info(username1 + " Contacts Number is " + ELEMENT_FILTER_USER_CONTACTS_NUMBER_DW.getText());
    info(username1 + " Spaces Number is " + ELEMENT_FILTER_USER_SPACES_NUMBER_DW.getText());

    sleep(2000);
    ELEMENT_FILTER_CLOSE_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check " + username1 + " Menu Icon");
    sleep(2000);
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Send a Kudos");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Remove member");
    info("Set As Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Promote as manager");
    info("Set As Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Set as redactor");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Open Chat");

    info("Set " + username1 + " As Manager");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(2000);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Space Manager Icon is displayed");
    ELEMENT_FILTER_SPACE_MANAGER_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Send a Kudos");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Remove member");
    info("Remove the role Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Remove manager");
    info("Set As Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Set as redactor");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Open Chat");

    info("Set " + username1 + " As Redactor");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(2000);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Space Manager Icon is displayed");
    ELEMENT_FILTER_SPACE_MANAGER_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Send a Kudos");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Remove member");
    info("Remove the role Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Remove manager");
    info("Remove the Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Remove redactor");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Open Chat");

    info("Remove the role manager from " + username1);
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(2000);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Space Manager Icon is not displayed");
    ELEMENT_FILTER_SPACE_MANAGER_ICON_DW.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Send a Kudos");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Remove member");
    info("Set As Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Promote as manager");
    info("Remove the role Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Remove redactor");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Open Chat");

    info("Remove the role redactor from " + username1);
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(2000);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Space Manager Icon is not displayed");
    ELEMENT_FILTER_SPACE_MANAGER_ICON_DW.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Send a Kudos");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Remove member");
    info("Set As Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Promote as manager");
    info("Set As Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Set as redactor");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Open Chat");

    info("Check member " + username2 + " card");
    spaceSettingManagement.goToSpaceMembersTabDW(spaceName);
    ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys(username2);
    ELEMENT_CONTACT_AVATAR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_ADD_CONTACT_COVER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_ADD_CONTACT_FULLNAME_DW.getText(),username2 + " " + username2);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    Assert.assertEquals(ELEMENT_FILTER_USER_ABOUT_ME_TEXT_DW.getText(),"About me");
    Assert.assertEquals(ELEMENT_FILTER_USER_CONTACTS_TEXT_DW.getText(),"Connections");
    Assert.assertEquals(ELEMENT_FILTER_USER_SPACES_TEXT_DW.getText(),"Spaces");

    info(username2 + " Contacts Number is " + ELEMENT_FILTER_USER_CONTACTS_NUMBER_DW.getText());
    info(username2 + " Spaces Number is " + ELEMENT_FILTER_USER_SPACES_NUMBER_DW.getText());

    sleep(2000);
    ELEMENT_FILTER_CLOSE_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check " + username2 + " Menu Icon");
    sleep(2000);
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Send a Kudos");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Remove member");
    info("Set As Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Promote as manager");
    info("Set As Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Set as redactor");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Open Chat");
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceName);

  }

}
