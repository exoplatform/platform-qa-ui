package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.pageobject.TribeUserProfilePage;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("dw")
public class MeedsSearchManagementTestDWIT extends BaseTribe {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  ConnectionsManagement connectionsManagement;

  TribeActivityStream tribeActivityStream;

  TribeSpaceManagement tribeSpaceManagement;

  TasksManagement tasksManagement;

  HomePagePlatform homePagePlatform;

  ActivityStream activityStream;

  TribeUserProfilePage tribeUserProfilePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    tribeUserProfilePage = new TribeUserProfilePage(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    tasksManagement = new TasksManagement(this);
    tribeActivityStream = new TribeActivityStream(this);
    activityStream = new ActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

  }

  @Test
  public void test01_CheckThatPeopleCardsAreDisplayed() {
    String username1 = getRandomString() + DATA_NAME_USER1.split(" ")[1].toLowerCase();
    String user1 = "test " + DATA_NAME_USER1.split(" ")[1];
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, "test", DATA_NAME_USER1.split(" ")[1], "");


    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(DATA_NAME_USER1.split(" ")[1] + " ");
    ELEMENT_CANCEL_SPACE_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The Searched user " + DATA_NAME_USER1 + " is displayed");
    sleep(2000);
    $(byXpath(ELEMENT_USER_SEARCH_TITLE.replace("${user}", DATA_NAME_USER1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("The Searched user " + user1 + " is displayed");
    $(byXpath(ELEMENT_USER_SEARCH_TITLE.replace("${user}", user1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals($(byXpath(ELEMENT_USER_SEARCH_TITLE.replace("${user}", user1))).getAttribute("href"), getExoWebDriver().getBaseUrl() + "portal/dw/profile/" + username1);

    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.deleteUserDW(username1);
  }

  @Test
  public void test02_CheckThatActivityCardsAreDisplayed() {

    String comment = "testcomment" + getRandomNumber();
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String activity1 = "testactivity32";
    String activity2 = "testactivity37";

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity1, "");

    String activityId = tribeActivityStream.getActivityIdDW(activity1);
    tribeActivityStream.addActivityComment(activityId, comment);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    tribeActivityStream.addTribeActivity(activity2, "");

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(activity1);
    ELEMENT_CANCEL_SPACE_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The Searched activity " + activity1 + " is displayed");
    $(byXpath(ELEMENT_ACTIVITY_SEARCH_TITLE.replace("${activity}", activity1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_ACCESS_TO_SEARCHED_ACTIVITY.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    Assert.assertEquals(ELEMENT_ACTIVITY_TITLE.getText(), activity1);

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(activity2);
    ELEMENT_CANCEL_SPACE_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The Searched activity " + activity2 + " is displayed");
    $(byXpath(ELEMENT_ACTIVITY_SEARCH_TITLE.replace("${activity}", activity2))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_ACCESS_TO_SEARCHED_ACTIVITY.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    Assert.assertEquals(ELEMENT_ACTIVITY_TITLE.getText(), activity2);

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(comment);
    ELEMENT_CANCEL_SPACE_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The comment " + comment + " is displayed");
    $(byXpath(ELEMENT_ACTIVITY_SEARCH_TITLE.replace("${activity}", comment))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_ACCESS_TO_SEARCHED_ACTIVITY.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    Assert.assertEquals(ELEMENT_COMMENT_TITLE.getText(), comment);

    info("Delete comment");
    activityStream.deletecommentDW(activity1, comment);
    // verify that the comment is deleted
    $(byText(comment)).waitUntil(Condition.not(Condition.exist), Configuration.openBrowserTimeoutMs);

    homePagePlatform.goToStreamPageTribeViaUrl();
    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue("testactivity3");
    ELEMENT_CANCEL_SPACE_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byXpath(ELEMENT_ACTIVITY_SEARCH_TITLE.replace("${activity}", activity1))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    $(byXpath(ELEMENT_ACTIVITY_SEARCH_TITLE.replace("${activity}", activity2))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    ELEMENT_CLOSE_SEARCH_INPUT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    // click on the activity to appear the delete button

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    homePagePlatform.goToSnapshotPageTribeViaUrl();

  }

  @Test
  public void test03_CheckThatSpacesCardsAreDisplayed() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(spaceNamea);
    ELEMENT_CANCEL_SPACE_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The Searched space " + spaceNamea + " is displayed");
    $(byXpath(ELEMENT_SPACE_SEARCH_TITLE.replace("${space}", spaceNamea))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    info("Go to the searched space");
    $(byXpath(ELEMENT_SPACE_SEARCH_TITLE.replace("${space}", spaceNamea))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    info(spaceNamea + " is opened");
    $(byXpath("//*[@class='pl-2 align-self-center brandingContainer space']//*[contains(text(),'${spaceName}')]"
            .replace("${spaceName}", spaceNamea))).waitUntil(Condition.visible, 60000);
    homePagePlatform.goToSnapshotPageTribeViaUrl();

  }

  @Test
  public void test04_CheckThatFilesCardsAreDisplayed() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String attachedFile = "testTribe.png";

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);
    info("-- Upload the picture --");
    tribeActivityStream.uploadFileActivityStreamDW(attachedFile);
    tribeActivityStream.postActivity();
    info("-- Verify that an activity has been added --");
    sleep(3000);
    $(byText(attachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(attachedFile.split(".p")[0]);
    ELEMENT_CANCEL_SPACE_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The Searched file " + attachedFile + " is displayed");
    $(byXpath(ELEMENT_FILE_SEARCH_TITLE.replace("${file}", attachedFile.split(".p")[0]))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    info("Preview The Attached File");
    $(byXpath(ELEMENT_FILE_SEARCH_TITLE.replace("${file}", attachedFile.split(".p")[0]))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byText(attachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);

    homePagePlatform.goToStreamPageTribeViaUrl();

    info("Delete The Attached File");
    tribeActivityStream.deleteAttachedFileDW(attachedFile);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    homePagePlatform.goToSnapshotPageTribeViaUrl();

  }

  @Test
  public void test05_CheckThatTasksCardsAreDisplayed() {

    String taskName = "testtask" + getRandomNumber();

    info("Add Task");
    homePagePlatform.goToTasksPageDW();
    tasksManagement.addTask(taskName);
    info("verify task added");
    $(byText(taskName)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);

    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(taskName);
    ELEMENT_CANCEL_SPACE_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The Searched task " + taskName + " is displayed");
    $(byXpath(ELEMENT_TASK_SEARCH_TITLE.replace("${task}", taskName))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    info("Go to the searched task");
    $(byXpath(ELEMENT_TASK_SEARCH_TITLE.replace("${task}", taskName))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check that the searched task is displayed");
    Assert.assertEquals(ELEMENT_DRAWER_TASK_NAME.waitUntil(Condition.visible, openBrowserTimeoutMs).getValue(), taskName);

    info("Delete Task");
    homePagePlatform.goToTasksPageDW();
    tasksManagement.deleteTask(taskName);
    info("verify task deleted");
    $(byText(taskName)).should(Condition.not(Condition.visible));

  }

  @Test
  public void test06_CheckThatApplicationsCardsAreDisplayed() {

    String appName = "Wallet";
    String appDesc = "Wallet application";

    info("Search the application " + appName);
    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(appName);
    ELEMENT_CANCEL_SPACE_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The Searched application " + appName + " is displayed");
    $(byXpath(ELEMENT_APPLICATION_SEARCH_TITLE.replace("${application}", appName))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("The Searched application description" + appName + " is displayed");
    $(byXpath(ELEMENT_APPLICATION_SEARCH_DESCRIPTION.replace("${applicationDescription}", appDesc))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("The Searched application picture is displayed");
    ELEMENT_APPLICATION_SEARCH_PICTURE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("Click on the searched application");
    $(byXpath(ELEMENT_APPLICATION_SEARCH_TITLE.replace("${application}", appName))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("The "+ appName + " application is displayed");
    ELEMENT_WALLET_APPLICATION_PAGE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

}