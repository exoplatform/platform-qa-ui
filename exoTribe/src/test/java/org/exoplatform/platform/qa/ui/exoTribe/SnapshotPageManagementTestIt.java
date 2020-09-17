package org.exoplatform.platform.qa.ui.exoTribe;

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

import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TITLE_OF_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("tribe")
@Tag("social")
@Tag("sniff")
public class SnapshotPageManagementTestIt extends BaseTribe {
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
    manageLogInOut.signInTribe(tribe_username, tribe_password);

  }

  @Test
  public void test01_CheckTheDisplayOfProfileStatistics() {

    info("Check That Profile Statistics are displayed");
    info("Check That Profile Spaces Number are displayed");
    ELEMENT_TRIBE_SNAPSHOT_SPACES_NUMBER.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_SPACES_NUMBER_TITLE.getText(), "Espaces");

    info("Check That Profile Weekly Points are displayed");
    ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_POINTS_TITLE.getText(), "Points hebdomadaires");

    info("Check That Profile Contacts Number are displayed");
    ELEMENT_TRIBE_SNAPSHOT_CONTACTS_NUMBER.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_CONTACTS_NUMBER_TITLE.getText(), "Contacts");

    info("Check That Profile Weekly Rank are displayed");
    ELEMENT_TRIBE_SNAPSHOT_WEEKLY_RANK.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals(ELEMENT_TRIBE_SNAPSHOT_WEEKLY_RANK_TITLE.getText(), "Rang hebdomadaire");

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
    tribeSpaceManagement.addNewSpaceTribe(spaceNamea, spaceDesa, "Open", "No", null);

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
    String newTaskName = "newTaskName" + getRandomNumber();

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

    manageLogInOut.signInTribe(tribe_username1, tribe_password1);

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

  }

  @Test
  public void test05_CheckThatANewTaskAddedInAProjectIsNotDisplayedInSnapshotPageByAnOtherUser() {

    String title = "title" + getRandomNumber();
    String task = "task" + getRandomNumber();
    manageLogInOut.signInTribe(tribe_username1, tribe_password1);

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
    Assert.assertNotEquals(ELEMENT_TRIBE_SNAPSHOT_TASK_TITLE.getAttribute("title"), task);

    info("Delete the task");
    homePagePlatform.goToTasksPageDW();
    tribeProjectsManagement.deleteProject(title);

  }

}
