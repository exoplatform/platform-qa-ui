package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
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
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("tribeSpaceHome")
public class SOCSpaceHomePageTestIT extends BaseTribe {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream activityStream;

  TribeActivityStream tribeActivityStream;

  SpaceHomePage spaceHomePage;

  SpaceManagement spaceManagement;

  SpaceSettingManagement spaceSettingManagement;

  TribeSpaceManagement tribeSpaceManagement;

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
    manageLogInOut.signInTribe(tribe_username, tribe_password);

  }

  @Test
  public void test01_LikeActivity() {

    String activity1 = "activity1" + getRandomNumber();
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);
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
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);
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

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);
    info("-- Upload the picture --");
    tribeActivityStream.uploadFileActivityStreamDW(attachedFile);
    tribeActivityStream.postActivity();
    sleep(3000);
    info("-- Verify that an activity has been added --");
    $(byText(attachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    info("Preview The Attached File");
    tribeActivityStream.previewAttachedFile(attachedFile);

    info("Open The Attached File In Documents");
    tribeActivityStream.openAttachedFileInDocuments(attachedFile);
    tribeActivityStream.verifyThatPictureAttachedFileIsDisplayedInDocuments(attachedFile);

    homePagePlatform.goToHomeSpaceTribe();

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
    String userName = tribe_user;

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload the Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(attachedFile);

    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    info("-- Verify that an activity has been added --");
    $(byText(attachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    info("Preview The Attached File");
    tribeActivityStream.previewAttachedFile(attachedFile);

    info("Open The Attached File In Documents");
    tribeActivityStream.openAttachedFileInDocuments(attachedFile);
    tribeActivityStream.verifyThatWordAttachedFileIsDisplayedInDocuments(attachedFile);
    tribeActivityStream.editFileInOnlyOfficeFromDocumentsTab( attachedFile);
    switchTo().window(1);
    info("Check That The Document is opened with Edit Online");
    tribeActivityStream.checkOpeningDocumentWithEditOnlineDW(document,extension,userName);

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
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);

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

    String user1 = tribe_user3;

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(user1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceName, spaceDes, "Validation", "No", null);

    info("Go to Spaces Members Page");
    spaceSettingManagement.goToSpaceMembersTabDW(spaceName);

    info("Check that Filter is displayed");
    ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.getAttribute("placeholder"), "Filtrer par nom, position ou compétence");

    ELEMENT_SPACE_MEMBERS_SHOWING_RESULTS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    String resultsNumber = ELEMENT_SPACE_MEMBERS_SHOWING_RESULTS_DW.getText().split("de ")[1].split(" résultats")[0];
    info(resultsNumber + "Results are displayed");

    info("Check that Pulldown Filter is displayed");
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Invite an user to join the space");
    tribeSpaceManagement.inviteUsersToJoinSpaceViaSpaceMembersTribe(inviteUsers);

    info("Check Space Guests Pull Down Filter");
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Invité");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", tribe_user3))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Invited user accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username3, tribe_password3);
    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceName);
    homePagePlatform.goToSnapshotPageTribeViaUrl();

    info("Request to join the space");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username4, tribe_password4);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceName);
    $(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceName))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
    sleep(3000);
    Assert.assertEquals($(By.xpath(ELEMENT_SPACE_STATUS_DW.replace("${space}", spaceName))).getText(), "Cancel request");

    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username, tribe_password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceName);
    tribeSpaceManagement.accessToSearchedSpace();
    spaceSettingManagement.goToSpaceMembersTabDW(spaceName);

    info("Check Space Members Pull Down Filter");
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Membres");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", tribe_user3))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", tribe_user))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check Space Administrators Pull Down Filter");
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Gestionnaires");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", tribe_user))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check Space Requests Pull Down Filter");
    sleep(2000);
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Demandes");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", tribe_user4))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check that " + tribe_user3 + " is not displayed in Space Guests Pull Down Filter");
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys("Invité");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", tribe_user3))).waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceName);

  }

  @Test
  public void test07_CheckMembersCardBehavior() {

    String spaceName = "space" + getRandomNumber();
    String spaceDes = "descriptiona" + getRandomNumber();

    String user1 = tribe_user3;
    String user2 = tribe_user4;

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(user1);
    inviteUsers.add(user2);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpaceTribe(spaceName, spaceDes, "Open", "No", inviteUsers);

    info("Invited user " + tribe_user3 + " accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username3, tribe_password3);
    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceName);
    homePagePlatform.goToSnapshotPageTribeViaUrl();

    info("Invited user " + tribe_user4 + " accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username4, tribe_password4);
    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceName);
    homePagePlatform.goToSnapshotPageTribeViaUrl();

    manageLogInOut.signOutTribe();
    manageLogInOut.signInTribe(tribe_username, tribe_password);
    homePagePlatform.goToMySpacesTribeViaUrl();
    connectionsManagement.tribeSearchSpace(spaceName);
    tribeSpaceManagement.accessToSearchedSpace();

    info("Go to Spaces Members Page");
    spaceSettingManagement.goToSpaceMembersTabDW(spaceName);

    info("Check that Filter is displayed");
    ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.getAttribute("placeholder"), "Filtrer par nom, position ou compétence");

    ELEMENT_SPACE_MEMBERS_SHOWING_RESULTS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    String resultsNumber = ELEMENT_SPACE_MEMBERS_SHOWING_RESULTS_DW.getText().split("de ")[1].split(" résultats")[0];
    Assert.assertEquals(resultsNumber, "3");
    info(resultsNumber + "Results are displayed");

    info("Check that Pulldown Filter is displayed");
    ELEMENT_SPACE_MEMBERS_PULLDOWN_FILTER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that " + tribe_user3 + "," + tribe_user4 + "," + tribe_user + " are displayed in Space Members Pull Down Filter");
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", tribe_user3))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", tribe_user4))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    $(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}", tribe_user))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);


    info("Check member " + tribe_user + " card");
    ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys(tribe_user);
    ELEMENT_CONTACT_AVATAR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_ADD_CONTACT_COVER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_ADD_CONTACT_JOB_DW.getText(),"IT Engineer");
    Assert.assertEquals($(byXpath(ELEMENT_ADD_CONTACT_FULLNAM_WITH_PARAM_DW.replace("${user}",tribe_user))).getText(),tribe_user);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    Assert.assertEquals(ELEMENT_FILTER_USER_ABOUT_ME_TEXT_DW.getText(),"A propos de moi");
    Assert.assertEquals(ELEMENT_FILTER_USER_CONTACTS_TEXT_DW.getText(),"Contacts");
    Assert.assertEquals(ELEMENT_FILTER_USER_SPACES_TEXT_DW.getText(),"Espaces");

    info(tribe_user + " Contacts Number is " + ELEMENT_FILTER_USER_CONTACTS_NUMBER_DW.getText());
    info(tribe_user + " Spaces Number is " + ELEMENT_FILTER_USER_SPACES_NUMBER_DW.getText());

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
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Promouvoir comme rédacteur");
    info("Open Chat Button is not displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();


    info("Check member " + tribe_user3 + " card");
    spaceSettingManagement.goToSpaceMembersTabDW(spaceName);
    ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys(tribe_user3);
    ELEMENT_CONTACT_AVATAR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_ADD_CONTACT_COVER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_ADD_CONTACT_FULLNAME_DW.getText(),tribe_user3);
    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    Assert.assertEquals(ELEMENT_FILTER_USER_ABOUT_ME_TEXT_DW.getText(),"A propos de moi");
    Assert.assertEquals(ELEMENT_FILTER_USER_CONTACTS_TEXT_DW.getText(),"Contacts");
    Assert.assertEquals(ELEMENT_FILTER_USER_SPACES_TEXT_DW.getText(),"Espaces");

    info(tribe_user3 + " Contacts Number is " + ELEMENT_FILTER_USER_CONTACTS_NUMBER_DW.getText());
    info(tribe_user3 + " Spaces Number is " + ELEMENT_FILTER_USER_SPACES_NUMBER_DW.getText());

    sleep(2000);
    ELEMENT_FILTER_CLOSE_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check " + tribe_user3 + " Menu Icon");
    sleep(2000);
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Envoyer un kudos");
    info("Send Cauris Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_TEXT_DW.getText(), "Envoyer Cauri");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Supprimer le membre");
    info("Set As Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Promouvoir gestionnaire");
    info("Set As Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Promouvoir comme rédacteur");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Ouvrir le Chat");

    info("Set " + tribe_user3 + " As Manager");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(2000);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Space Manager Icon is displayed");
    ELEMENT_FILTER_SPACE_MANAGER_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Envoyer un kudos");
    info("Send Cauris Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_TEXT_DW.getText(), "Envoyer Cauri");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Supprimer le membre");
    info("Remove the role Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Retirer le rôle gestionnaire");
    info("Set As Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Promouvoir comme rédacteur");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Ouvrir le Chat");

    info("Set " + tribe_user3 + " As Redactor");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(2000);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Space Manager Icon is displayed");
    ELEMENT_FILTER_SPACE_MANAGER_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Envoyer un kudos");
    info("Send Cauris Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_TEXT_DW.getText(), "Envoyer Cauri");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Supprimer le membre");
    info("Remove the role Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Retirer le rôle gestionnaire");
    info("Remove the Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Retirer le rôle de rédacteur");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Ouvrir le Chat");

    info("Remove the role manager from " + tribe_user3);
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(2000);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Space Manager Icon is not displayed");
    ELEMENT_FILTER_SPACE_MANAGER_ICON_DW.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Envoyer un kudos");
    info("Send Cauris Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_TEXT_DW.getText(), "Envoyer Cauri");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Supprimer le membre");
    info("Set As Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Promouvoir gestionnaire");
    info("Remove the role Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Retirer le rôle de rédacteur");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Ouvrir le Chat");

    info("Remove the role redactor from " + tribe_user3);
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(2000);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Check that Space Manager Icon is not displayed");
    ELEMENT_FILTER_SPACE_MANAGER_ICON_DW.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Envoyer un kudos");
    info("Send Cauris Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_TEXT_DW.getText(), "Envoyer Cauri");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Supprimer le membre");
    info("Set As Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Promouvoir gestionnaire");
    info("Set As Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Promouvoir comme rédacteur");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Ouvrir le Chat");

    info("Check member " + tribe_user4 + " card");
    spaceSettingManagement.goToSpaceMembersTabDW(spaceName);
    ELEMENT_SPACE_MEMBERS_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).sendKeys(tribe_user4);
    ELEMENT_CONTACT_AVATAR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_ADD_CONTACT_COVER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_ADD_CONTACT_FULLNAME_DW.getText(),tribe_user4);

    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_FILTER_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    Assert.assertEquals(ELEMENT_FILTER_USER_ABOUT_ME_TEXT_DW.getText(),"A propos de moi");
    Assert.assertEquals(ELEMENT_FILTER_USER_CONTACTS_TEXT_DW.getText(),"Contacts");
    Assert.assertEquals(ELEMENT_FILTER_USER_SPACES_TEXT_DW.getText(),"Espaces");

    info(tribe_user4 + " Contacts Number is " + ELEMENT_FILTER_USER_CONTACTS_NUMBER_DW.getText());
    info(tribe_user4 + " Spaces Number is " + ELEMENT_FILTER_USER_SPACES_NUMBER_DW.getText());

    sleep(2000);
    ELEMENT_FILTER_CLOSE_USER_INFORMATIONS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check " + tribe_user4 + " Menu Icon");
    sleep(2000);
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Send Kudos Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_KUDOS_TEXT_DW.getText(), "Envoyer un kudos");
    info("Send Cauris Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_SEND_CAURIS_TEXT_DW.getText(), "Envoyer Cauri");
    info("Remove Member Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_MEMBER_TEXT_DW.getText(), "Supprimer le membre");
    info("Set As Manager Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_MANAGER_TEXT_DW.getText(), "Promouvoir gestionnaire");
    info("Set As Redactor Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_REMOVE_SET_AS_REDACTOR_TEXT_DW.getText(), "Promouvoir comme rédacteur");
    info("Open Chat Button is displayed");
    ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_FILTER_USER_MENU_ICON_OPEN_CHAT_TEXT_DW.getText(), "Ouvrir le Chat");
    ELEMENT_FILTER_USER_MENU_ICON_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceName);

  }

}
