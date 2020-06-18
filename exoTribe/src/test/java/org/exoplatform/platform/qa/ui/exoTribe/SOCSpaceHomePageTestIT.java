package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_DW_POST_ACTIVITY_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_TRIBE_POST_ACTIVITY_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("tribe")
@Tag("social")
@Tag("sniff")
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

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload the picture --");
    tribeActivityStream.uploadFileActivityStreamDW(attachedFile);

    tribeActivityStream.postActivity();
    info("-- Verify that an activity has been added --");
    sleep(1000);
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
  public void test04_UploadDocxOpenItInDocumentAndEditItInOnlyOffice() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String attachedFile = "docx_test.docx";
    String document = "docx_test";
    String extension = ".docx";
    String userName = tribe_user;

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload the Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(attachedFile);

    tribeActivityStream.postActivity();
    info("-- Verify that an activity has been added --");
    sleep(1000);
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

}
