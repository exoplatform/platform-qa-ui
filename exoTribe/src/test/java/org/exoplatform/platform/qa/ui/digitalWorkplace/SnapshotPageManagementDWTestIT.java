package org.exoplatform.platform.qa.ui.digitalWorkplace;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.pageobject.TribeProjectsManagement;
import org.exoplatform.platform.qa.ui.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
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
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TITLE_OF_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("dw")
public class SnapshotPageManagementDWTestIT extends BaseTribe {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream activityStream;

  TribeActivityStream tribeActivityStream;

  SpaceHomePage spaceHomePage;

  SpaceManagement spaceManagement;

  TasksManagement tasksManagement;

  TribeProjectsManagement tribeProjectsManagement;

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
    tasksManagement = new TasksManagement(this);
    tribeActivityStream = new TribeActivityStream(this);
    tribeSpaceManagement = new TribeSpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceManagement = new SpaceManagement(this);
    tribeProjectsManagement = new TribeProjectsManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);

  }

  @Test
  public void test01_CheckTheDisplayOfProfileStatistics() {

    info("Check That Profile Statistics is displayed");
    info("Check That Profile Spaces Number are displayed");
    ELEMENT_TRIBE_SNAPSHOT_SPACES_NUMBER.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_SPACES_NUMBER_TITLE.getText(), "Spaces");

    info("Check That Profile Weekly Points is displayed");
    ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS_TITLE.getText(), "Weekly points");

    info("Check That Profile Contacts Number is displayed");
    ELEMENT_TRIBE_SNAPSHOT_CONTACTS_NUMBER.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_CONTACTS_NUMBER_TITLE.getText(), "Connections");

    info("Check That Profile Weekly Rank is displayed");
    ELEMENT_TRIBE_SNAPSHOT_WEEKLY_RANK.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_RANK_TITLE.getText(), "Weekly rank");

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test02_CheckSpacesNumberAndWeeklyPointsAfterCreatingNewSpace() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();

    info("Get Profile Spaces Number");
    int intialSnapshotSpacesNumber = Integer.parseInt(ELEMENT_TRIBE_SNAPSHOT_SPACES_NUMBER.getText());

    info("Get Profile Weekly Points");
    int intialSnapshotWeeklyPoints = Integer.parseInt(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).getText());

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    homePagePlatform.goToSnapshotPageTribe();

    info("Get Profile Spaces Number after creating a new space");
    int lastSnapshotSpacesNumber = Integer.parseInt(ELEMENT_TRIBE_SNAPSHOT_SPACES_NUMBER.getText());

    info("Check Spaces Number after creating a new space");

    Assert.assertEquals(intialSnapshotSpacesNumber + 1, lastSnapshotSpacesNumber);

    info("Get Profile Weekly Points after creating a new space");
    int lastSnapshotWeeklyPoints = Integer.parseInt(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).getText());

    info("Check Weekly Points after creating a new space");

    Assert.assertEquals(intialSnapshotWeeklyPoints + 5, lastSnapshotWeeklyPoints);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

  @Test
  public void test03_CheckWeeklyPointsAfterCreatingATask() {

    String taskName = "task" + getRandomNumber();

    info("Get Profile Weekly Points");
    int intialSnapshotWeeklyPoints = Integer.parseInt(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).getText());

    info("Add Task");
    homePagePlatform.goToTasksPageDW();
    tasksManagement.addTask(taskName);
    info("verify task added");
    $(byText(taskName)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);

    homePagePlatform.goToSnapshotPageTribe();
    info("Get Profile Weekly Points after creating a new task");
    int lastSnapshotWeeklyPoints = Integer.parseInt(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).getText());

    info("Check Weekly Points after creating a new task");
    Assert.assertEquals(intialSnapshotWeeklyPoints + 3, lastSnapshotWeeklyPoints);

    info("Delete Task");
    homePagePlatform.goToTasksPageDW();
    tasksManagement.deleteTask(taskName);
    info("verify task deleted");
    $(byText(taskName)).should(Condition.not(Condition.visible));

  }

  @Test
  public void test04_CheckThatTheCreatedTaskIsDisplayedInSnapshotPageAfterAddingANewTaskByAnOtherUser() {

    String taskName = "task" + getRandomNumber();
    String spaceNamea = "spacenamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", inviteUsers);


    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    info("Get Profile Weekly Points");
    int intialSnapshotWeeklyPoints = Integer.parseInt(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).getText());

    info("Add Task");
    homePagePlatform.goToTasksPageDW();
    tasksManagement.addTask(taskName);
    info("verify task added");
    $(byText(taskName)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);

    homePagePlatform.goToSnapshotPageTribe();
    info("Get Profile Weekly Points after creating a new task");
    int lastSnapshotWeeklyPoints = Integer.parseInt(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).getText());

    info("Check Weekly Points after creating a new task");
    Assert.assertEquals(intialSnapshotWeeklyPoints + 3, lastSnapshotWeeklyPoints);

    info("Check That The Created Task Is Displayed In Snapshot Page");
    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_TASK_TITLE.getAttribute("title"), taskName);

    info("Delete Task");
    homePagePlatform.goToTasksPageDW();
    tasksManagement.deleteTask(taskName);
    info("verify task deleted");
    $(byText(taskName)).should(Condition.not(Condition.visible));

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.deleteUserDW(username1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

  @Test
  public void test05_CheckThatANewTaskAddedInAProjectIsNotDisplayedInSnapshotPageByAnOtherUser() {

    String title = "title" + getRandomNumber();
    String task = "task" + getRandomNumber();

    String spaceNamea = "spacenamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", inviteUsers);


    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    info("Get Profile Weekly Points");
    int intialSnapshotWeeklyPoints = Integer.parseInt(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).getText());

    homePagePlatform.goToTasksPageDW();

    tribeProjectsManagement.addProject(title, "", false);
    executeJavaScript("window.scrollBy(0,-20000)");
    $(byText(title)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TITLE_OF_PROJECT.waitUntil(Condition.hasText(title), Configuration.timeout);

    executeJavaScript("window.scrollBy(0,-20000)");
    tasksManagement.addTask(task);
    $(byText(task)).should(Condition.exist);

    homePagePlatform.goToSnapshotPageTribe();
    info("Get Profile Weekly Points after creating a new task");
    int lastSnapshotWeeklyPoints = Integer.parseInt(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).getText());

    info("Check Weekly Points after creating a new task");
    Assert.assertEquals(intialSnapshotWeeklyPoints + 3, lastSnapshotWeeklyPoints);

    info("Check that the new task added in a project is not displayed in Snapshot Page");
    $(byXpath("//*[@id='tasks']//*[@class='taskTitle' and @title='${taskName}']".replace("${taskName}",task))).waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);

    info("Delete the task");
    homePagePlatform.goToTasksPageDW();
    tribeProjectsManagement.deleteProject(title);
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.deleteUserDW(username1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

  @Test
  public void test06_CheckThatSectionsAreDisplayesInSnapshotPage() {

    info("Check That six sections are displayed in Snapshot Page");
    info("Check That Last News Section is displayed");
    ELEMENT_TRIBE_SNAPSHOT_LAST_NEWS.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Check That User Statistics Section is displayed");
    ELEMENT_TRIBE_SNAPSHOT_USER_STATS.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Check That Popular Spaces Section is displayed");
    ELEMENT_TRIBE_SNAPSHOT_SPACES.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Check That Tasks Section is displayed");
    ELEMENT_TRIBE_SNAPSHOT_TASKS.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Check That Documents Section is displayed");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Check That Slider Section is displayed");
    ELEMENT_TRIBE_SNAPSHOT_SLIDER.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test07_CheckTheScrollBehaviorForDocumentsTabsInSnapShotPage() {

    info("Scroll from right to left in Documents Widget");
    info("Check That Documents Explore Tab is displayed");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_EXPLORE.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_EXPLORE.click();

    info("Check That Documents Shared with me Tab is displayed");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_SHARED_WITH_ME.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_SHARED_WITH_ME.click();

    info("Check That Documents Favorites Tab is displayed");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_FAVORITE.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_FAVORITE.click();

    info("Scroll to left");
    ELEMENT_TRIBE_SNAPSHOT_SCROLL_TO_LEFT_IN_DOCUMENTS_WIDGET.waitUntil(Condition.visible, openBrowserTimeoutMs).click();

    info("Check That Documents My Work Tab is displayed");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_MYWORK.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_MYWORK.click();

    info("Scroll from left to right in Documents Widget");
    info("Check That Documents My Work Tab is displayed");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_MYWORK.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_MYWORK.click();

    info("Check That Documents Favorites Tab is displayed");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_FAVORITE.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_FAVORITE.click();

    info("Check That Documents Shared with me Tab is displayed");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_SHARED_WITH_ME.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_SHARED_WITH_ME.click();

    info("Scroll to right");
    ELEMENT_TRIBE_SNAPSHOT_SCROLL_TO_RIGHT_IN_DOCUMENTS_WIDGET.waitUntil(Condition.visible, openBrowserTimeoutMs).click();

    info("Check That Documents Explore Tab is displayed");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_EXPLORE.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_EXPLORE.click();

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test08_CheckThatTheChartUserPointsIsDisplayedInSnapShotPage() {

    info("Check That Profile Weekly Points is displayed");
    ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS_TITLE.getText(), "Weekly points");

    info("Check That Profile Weekly Points is displayed");
    ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.click();

    info("Check That Chart User Points is displayed");
    ELEMENT_TRIBE_SNAPSHOT_CHART_USER_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test09_CheckThatMyOrdersAndWalletBalanceAreDisplayedInSnapShotPage() {

    info("Check That Wallet Section is displayed");

    ELEMENT_TRIBE_SNAPSHOT_WALLET_SECTION.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Check That PerkStore Section is displayed");

    ELEMENT_TRIBE_SNAPSHOT_PERKSTORE_SECTION.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_WALLET_TITLE.getText(), "Wallet balance");
    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_MY_ORDERS_TITLE.getText(), "My orders");

    String productsNumber = ELEMENT_TRIBE_SNAPSHOT_MY_ORDERS_VALUE.getText().split("Orders")[0];
    String caurisNumber = ELEMENT_TRIBE_SNAPSHOT_WALLET_VALUE.getText().split(" Ȼ")[0];

    info("The total number of products on the Perk store is" + productsNumber);

    info("The number of cauris in Wallet is" + caurisNumber);

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test10_DisplayUserRankInTheUserWidget() {

      info("Go to Weekly Rank");

    ELEMENT_TRIBE_SNAPSHOT_WEEKLY_RANK.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_WEEKLY_RANK.click();

    info("Check That Weekly Rank Section Title is displayed");

    ELEMENT_TRIBE_SNAPSHOT_RANK_SECTION_TITLE.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Check That Podium Section Title is displayed");

    ELEMENT_TRIBE_SNAPSHOT_PODIUM_SECTION_TITLE.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Display the 3 first places on Podium");
    String firstUserPodium= ELEMENT_TRIBE_SNAPSHOT_FIRST_PODIUM_NAME.getAttribute("title");
    String secondUserPodium= ELEMENT_TRIBE_SNAPSHOT_SECOND_PODIUM_NAME.getAttribute("title");
    String thirdUserPodium= ELEMENT_TRIBE_SNAPSHOT_THIRD_PODIUM_NAME.getAttribute("title");

    info("First User On Podium is" + firstUserPodium);
    info("Second User On Podium is" + secondUserPodium);
    info("Third User On Podium is" + thirdUserPodium);

    String firstUserPodiumPoints= ELEMENT_TRIBE_SNAPSHOT_FIRST_PODIUM_POINTS.getText();
    String secondUserPodiumPoints= ELEMENT_TRIBE_SNAPSHOT_SECOND_PODIUM_POINTS.getText();
    String thirdUserPodiumPoints= ELEMENT_TRIBE_SNAPSHOT_THIRD_PODIUM_POINTS.getText();

    info("First User On Podium Points is" + firstUserPodiumPoints);
    info("Second User On Podium Points is" + secondUserPodiumPoints);
    info("Third User On Podium Points is" + thirdUserPodiumPoints);

    info("Check that 3 more users are displayed just below the Podium");

    String fourthUserPodium= ELEMENT_TRIBE_SNAPSHOT_FOURTH_PODIUM_NAME.getText();
    String fifthUserPodium= ELEMENT_TRIBE_SNAPSHOT_FIFTH_PODIUM_NAME.getText();
    String sixthUserPodium= ELEMENT_TRIBE_SNAPSHOT_SIXTH_PODIUM_NAME.getText();

    info("Fourth User On Podium is" + fourthUserPodium);
    info("Fifth User On Podium is" + fifthUserPodium);
    info("Sixth User On Podium is" + sixthUserPodium);

    String fourthUserPodiumPoints= ELEMENT_TRIBE_SNAPSHOT_FOURTH_PODIUM_POINTS.getText();
    String fifthUserPodiumPoints= ELEMENT_TRIBE_SNAPSHOT_FIFTH_PODIUM_POINTS.getText();
    String sixthUserPodiumPoints= ELEMENT_TRIBE_SNAPSHOT_SIXTH_PODIUM_POINTS.getText();

    info("Fourth User On Podium Points is" + fourthUserPodiumPoints);
    info("Fifth User On Podium Points is" + fifthUserPodiumPoints);
    info("Sixth User On Podium Points is" + sixthUserPodiumPoints);

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    homePagePlatform.goToStreamPageTribeViaUrl();

  }

  @Test
  public void test11_CheckThatUploadedDocumentsAreDisplayedInOrderInSnapshotDocumentsWidget() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();

    String attachedFile = "docx_test.docx";
    String secondAttachedFile = "OO_test.docx";
    String thirdAttachedFile = "dependencies.docx";

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameb, spaceDesb, "Open", "No", null);


    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload first Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(attachedFile);

    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    info("-- Verify that an activity has been added --");
    $(byText(attachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    getExoWebDriver().getWebDriver().navigate().refresh();
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload second Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(secondAttachedFile);
    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(Configuration.timeout);
    info("-- Verify that an activity has been added --");
    $(byText(secondAttachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload third Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(thirdAttachedFile);
    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    info("-- Verify that an activity has been added --");
    $(byText(thirdAttachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_MYWORK.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Check that uploaded documents are displayed in order");
    info("Check that " + thirdAttachedFile + " is displayed in first rank");
    Assert.assertEquals(ELEMENT_TRIBE_FIRST_MYWORK_DOCUMENT_NAME.getText(), thirdAttachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_FIRST_MYWORK_DOCUMENT_SPACE_NAME.getText(), spaceNamea);

    info("Check that " + secondAttachedFile + " is displayed in second rank");
    Assert.assertEquals(ELEMENT_TRIBE_SECOND_MYWORK_DOCUMENT_NAME.getText(), secondAttachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_SECOND_MYWORK_DOCUMENT_SPACE_NAME.getText(), spaceNameb);

    info("Check that " + attachedFile + " is displayed in third rank");
    Assert.assertEquals(ELEMENT_TRIBE_THIRD_MYWORK_DOCUMENT_NAME.getText(), attachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_THIRD_MYWORK_DOCUMENT_SPACE_NAME.getText(), spaceNameb);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    tribeSpaceManagement.deleteTribeSpace(spaceNameb);

  }

  @Test
  public void test12_CheckTheUploadedDocumentsBehaviorInSpacesDocumentsWidgetByAnOtherUser() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();

    String attachedFile = "docx_test.docx";
    String secondAttachedFile = "OO_test.docx";
    String thirdAttachedFile = "dependencies.docx";

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNameb, spaceDesb, "Open", "No", inviteUsers);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload first Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(attachedFile);

    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    info("-- Verify that an activity has been added --");
    $(byText(attachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    getExoWebDriver().getWebDriver().navigate().refresh();
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload second Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(secondAttachedFile);
    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    info("-- Verify that an activity has been added --");
    $(byText(secondAttachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.searchSpace(spaceNamea);
    tribeSpaceManagement.accessToSearchedSpace();

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload third Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(thirdAttachedFile);
    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    info("-- Verify that an activity has been added --");
    $(byText(thirdAttachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_MYWORK.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Check that uploaded documents are displayed in order");
    info("Check that " + thirdAttachedFile + " is displayed in first rank");
    Assert.assertEquals(ELEMENT_TRIBE_FIRST_MYWORK_DOCUMENT_NAME.getText(), thirdAttachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_FIRST_MYWORK_DOCUMENT_SPACE_NAME.getText(), spaceNamea);

    info("Check that " + secondAttachedFile + " is displayed in second rank");
    Assert.assertEquals(ELEMENT_TRIBE_SECOND_MYWORK_DOCUMENT_NAME.getText(), secondAttachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_SECOND_MYWORK_DOCUMENT_SPACE_NAME.getText(), spaceNameb);

    info("Check that " + attachedFile + " is displayed in third rank");
    Assert.assertEquals(ELEMENT_TRIBE_THIRD_MYWORK_DOCUMENT_NAME.getText(), attachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_THIRD_MYWORK_DOCUMENT_SPACE_NAME.getText(), spaceNameb);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToIntranetNotificationDW();
    navigationToolbar.acceptJoinSpaceViaNotificationnDW(spaceNameb);

    homePagePlatform.goToSnapshotPageTribeViaUrl();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_MYWORK.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Go to Documents Explore Tab");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_EXPLORE.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_EXPLORE.click();

    info("Check that uploaded documents in " + spaceNameb + " are displayed");
    info("Check that " + secondAttachedFile + " is displayed in first rank");
    Assert.assertEquals(ELEMENT_TRIBE_FIRST_EXPLORE_DOCUMENT_NAME.getText(), secondAttachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_FIRST_EXPLORE_DOCUMENT_SPACE_NAME.getText(), spaceNameb);

    info("Check that " + attachedFile + " is displayed in second rank");
    Assert.assertEquals(ELEMENT_TRIBE_SECOND_EXPLORE_DOCUMENT_NAME.getText(), attachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_SECOND_EXPLORE_DOCUMENT_SPACE_NAME.getText(), spaceNameb);

    info("Check that uploaded documents in " + spaceNamea + " are not displayed");
    info("Check that " + thirdAttachedFile + " is not displayed");
    Assert.assertNotEquals(ELEMENT_TRIBE_FIRST_EXPLORE_DOCUMENT_NAME.getText(), thirdAttachedFile);
    Assert.assertNotEquals(ELEMENT_TRIBE_SECOND_EXPLORE_DOCUMENT_NAME.getText(), thirdAttachedFile);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);
    tribeSpaceManagement.deleteTribeSpace(spaceNameb);

  }

  @Test
  public void test13_CheckTheUploadedDocumentsBehaviorInSharedWithMeDocumentsWidgetByAnOtherUser() {

    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();

    String attachedFile = "docx_test.docx";
    String secondAttachedFile = "OO_test.docx";
    String thirdAttachedFile = "dependencies.docx";

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "12345678";

    info("Add user");
    navigationToolbar.goToAddUsersPageViaUrlDW();
    addUsers.addUserTribe(username1, password, email1, username1, username1, "");

    ArrayList<String> inviteUsers = new ArrayList<>();
    inviteUsers.add(username1);

    info("Click on Connections on the left panel");
    homePagePlatform.goToPeoplePageTribeViaUrl();

    info("Click on Connect button to invite " + username1);
    connectionsManagement.tribeConnectToAUser(username1);

    info("Login by invited users, go to My Connections/Requests Received and accept invitation");
    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);

    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.acceptAConnectionDW(DATA_NAME_USER1);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload first Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(attachedFile);

    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    info("-- Verify that an activity has been added --");
    $(byText(attachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");
    tribeActivityStream.previewAttachedFile(attachedFile);

    info("Open The Attached File In Documents");
    tribeActivityStream.openAttachedFileInDocuments(attachedFile);
    info("Share the document with " + username1);
    tribeActivityStream.shareDocumentWithUserDW(inviteUsers);

    homePagePlatform.goToHomeSpaceTribe();
    getExoWebDriver().getWebDriver().navigate().refresh();
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload second Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(secondAttachedFile);
    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    info("-- Verify that an activity has been added --");
    $(byText(secondAttachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    tribeActivityStream.previewAttachedFile(secondAttachedFile);

    info("Open The Attached File In Documents");
    tribeActivityStream.openAttachedFileInDocuments(secondAttachedFile);
    info("Share the document with " + username1);
    tribeActivityStream.shareDocumentWithUserDW(inviteUsers);

    sleep(3000);
    homePagePlatform.goToHomeSpaceTribe();
    getExoWebDriver().getWebDriver().navigate().refresh();
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload third Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(thirdAttachedFile);
    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    info("-- Verify that an activity has been added --");
    $(byText(thirdAttachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(username1, password);
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_MYWORK.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Go to Documents Shared with me Tab");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_SHARED_WITH_ME.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_SHARED_WITH_ME.click();

    info("Check that uploaded documents in " + spaceNameb + " are displayed");
    sleep(2000);
    info("Check that " + secondAttachedFile + " is displayed in first rank");
    Assert.assertEquals(ELEMENT_TRIBE_FIRST_SHARED_WITH_ME_DOCUMENT_NAME.getText(), secondAttachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_FIRST_SHARED_WITH_ME_DOCUMENT_SPACE_NAME.getText(), "Shared");

    info("Check that " + attachedFile + " is displayed in second rank");
    Assert.assertEquals(ELEMENT_TRIBE_SECOND_SHARED_WITH_ME_DOCUMENT_NAME.getText(), attachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_SECOND_SHARED_WITH_ME_DOCUMENT_SPACE_NAME.getText(), "Shared");

    info("Check that Uploaded document not shared is not displayed");
    info("Check that " + thirdAttachedFile + " is not displayed");
    ELEMENT_TRIBE_THIRD_SHARED_WITH_ME_DOCUMENT_NAME.waitUntil(Condition.not(Condition.exist), openBrowserTimeoutMs);

    manageLogInOut.signOutTribe();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToPeoplePageTribeViaUrl();
    connectionsManagement.removeConnectionDW(username1);
    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

  @Test
  public void test14_CheckTheUploadedDocumentsBehaviorInFavoritesDocumentsWidget() {

    String spaceNamea = "spacenamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();

    String attachedFile = "docx_test.docx";
    String secondAttachedFile = "OO_test.docx";
    String thirdAttachedFile = "dependencies.docx";

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.addNewSpace(spaceNamea, spaceDesa, "Open", "No", null);

    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload first Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(attachedFile);

    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    info("-- Verify that an activity has been added --");
    $(byText(attachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    getExoWebDriver().getWebDriver().navigate().refresh();
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload second Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(secondAttachedFile);
    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(Configuration.timeout);
    info("-- Verify that an activity has been added --");
    $(byText(secondAttachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    getExoWebDriver().getWebDriver().navigate().refresh();
    ELEMENT_DW_POST_ACTIVITY_BUTTON.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);

    info("-- Upload third Word Document --");
    tribeActivityStream.uploadFileActivityStreamDW(thirdAttachedFile);
    tribeActivityStream.postActivity();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    info("-- Verify that an activity has been added --");
    $(byText(thirdAttachedFile)).waitUntil(Condition.exist, openBrowserTimeoutMs);
    $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).waitUntil(Condition.disabled, openBrowserTimeoutMs);
    info("The activity is shared success");

    tribeSpaceManagement.goToDocumentsSpaceTribe(spaceNamea);
    info("Add Documents to Favorites");
    tribeActivityStream.goToDocumentsActivityStream();
    tribeActivityStream.addDocumentsToFavorites(attachedFile);
    getExoWebDriver().getWebDriver().navigate().refresh();
    tribeActivityStream.addDocumentsToFavorites(secondAttachedFile);

    homePagePlatform.goToSnapshotPageTribeViaUrl();

    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_MYWORK.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Go to Favorite Documents Tab");
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_FAVORITE.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    ELEMENT_TRIBE_SNAPSHOT_DOCUMENTS_FAVORITE.click();

    info("Check that uploaded documents in " + spaceNameb + " are displayed");
    info("Check that " + secondAttachedFile + " is displayed in first rank");
    Assert.assertEquals(ELEMENT_TRIBE_FIRST_FAVORITES_DOCUMENT_NAME.getText(), secondAttachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_FIRST_FAVORITES__DOCUMENT_SPACE_NAME.getText(), "Favorites");

    info("Check that " + attachedFile + " is displayed in second rank");
    Assert.assertEquals(ELEMENT_TRIBE_SECOND_FAVORITES_DOCUMENT_NAME.getText(), attachedFile);
    Assert.assertEquals(ELEMENT_TRIBE_SECOND_FAVORITES__DOCUMENT_SPACE_NAME.getText(), "Favorites");

    info("Check that Uploaded document is not displayed in Favorites Documents Widget");
    info("Check that " + thirdAttachedFile + " is not displayed");
    ELEMENT_TRIBE_THIRD_FAVORITES__DOCUMENT_SPACE_NAME.waitUntil(Condition.not(Condition.exist), openBrowserTimeoutMs);

    homePagePlatform.goToMySpacesTribeViaUrl();
    tribeSpaceManagement.deleteTribeSpace(spaceNamea);

  }

}
