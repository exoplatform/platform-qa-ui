package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.pageobject.TribeSpaceManagement;
import org.exoplatform.platform.qa.ui.pageobject.TribeUserProfilePage;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("tribeAppCenterManagement")
public class AppCenterManagementTestIT extends BaseTribe {
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
    manageLogInOut.signInTribe(tribe_username, tribe_password);

  }

  @Test
  public void test01_CheckAppCenterApplicationsBehavior() {

    info("Check that Agenda application is opened");
    homePagePlatform.goToAgendaAppCenterApplication();

    info("Check that Tasks application is opened");
    homePagePlatform.goToTasksAppCenterApplication();

    info("Check that News application is opened");
    homePagePlatform.goToNewsAppCenterApplication();

    info("Check that Perk Store application is opened");
    homePagePlatform.goToPerkStoreAppCenterApplication();

    info("Check that Wallet application is opened");
    homePagePlatform.goToWalletAppCenterApplication();

    info("Check that Send Feedback application is opened");
    homePagePlatform.goToSendFeedbackAppCenterApplication();

    homePagePlatform.goToSnapshotPageTribeViaUrl();

  }

  @Test
  public void test02_CheckAddAppCenterApplicationToFavoritesBehavior() {


    String application = "Forums";

    info("Check that All Applications are displayed");
    homePagePlatform.seeAllApplications();

    info("Add application " + application + " to favorites");
    homePagePlatform.addRemoveApplicationToFavorites(application);

    info("Check that application " + application + " is added to favorites");
    homePagePlatform.checkThatAppcenterApplicationIsDisplayed(application);

    info("Go to the AppCenter Application " + application);
    homePagePlatform.goToTheAppcenterApplicationPage(application);

    info("Check that application " + application + " is opened");
    ELEMENT_TRIBE_FORUMS_APPLICATION_PAGE.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Check that All Applications are displayed");
    homePagePlatform.seeAllApplications();

    info("Remove application " + application + " from favorites");
    homePagePlatform.addRemoveApplicationToFavorites(application);

    info("Check that application " + application + " is removed from favorites");
    homePagePlatform.checkThatAppcenterApplicationIsNotDisplayed(application);

    homePagePlatform.goToSnapshotPageTribeViaUrl();

  }

  @Test
  public void test03_CheckThatApplicationCardButtonsAreDisplayed() {

    String application = "Forums";

    info("Check that All Applications are displayed");
    homePagePlatform.seeAllApplications();

    info("Check that add application " + application + " to favorites Button is displayed");
    $(byXpath(ELEMENT_TRIBE_APPCENTER_ADD_APP_TO_FAVORITES_BTN.replace("${app}", application))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Check that open application " + application + " Button is displayed");
    $(byXpath(ELEMENT_TRIBE_APPCENTER_ALL_APPLICATIONS_OPEN_APP_BTN.replace("${app}", application))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    homePagePlatform.goToSnapshotPageTribeViaUrl();

  }

  @Test
  public void test04_CheckAddAppCenterApplicationToFavoritesBehaviorByAnOtherUser() {

    manageLogInOut.signInTribe(tribe_username3, tribe_password3);

    String application = "Forums";

    info("Check that All Applications are displayed");
    homePagePlatform.seeAllApplications();

    info("Add application " + application + " to favorites");
    homePagePlatform.addRemoveApplicationToFavorites(application);

    info("Check that application " + application + " is added to favorites");
    homePagePlatform.checkThatAppcenterApplicationIsDisplayed(application);

    info("Go to the AppCenter Application " + application);
    homePagePlatform.goToTheAppcenterApplicationPage(application);

    info("Check that application " + application + " is opened");
    ELEMENT_TRIBE_FORUMS_APPLICATION_PAGE.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    info("Check that All Applications are displayed");
    homePagePlatform.seeAllApplications();

    info("Remove application " + application + " from favorites");
    homePagePlatform.addRemoveApplicationToFavorites(application);

    info("Check that application " + application + " is removed from favorites");
    homePagePlatform.checkThatAppcenterApplicationIsNotDisplayed(application);

    homePagePlatform.goToSnapshotPageTribeViaUrl();

  }

  @Test
  public void test05_CheckOpenAppCenterApplicationBehavior() {

    String application = "Forums";

    info("Check that All Applications are displayed");
    homePagePlatform.seeAllApplications();

    info("Check that open application " + application + " Button is displayed");
    $(byXpath(ELEMENT_TRIBE_APPCENTER_ALL_APPLICATIONS_OPEN_APP_BTN.replace("${app}", application))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Click on open application " + application);
    $(byXpath(ELEMENT_TRIBE_APPCENTER_ALL_APPLICATIONS_OPEN_APP_BTN.replace("${app}", application))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check that application " + application + " is opened");
    ELEMENT_TRIBE_FORUMS_APPLICATION_PAGE.waitUntil(Condition.visible, openBrowserTimeoutMs).isDisplayed();

    homePagePlatform.goToSnapshotPageTribeViaUrl();

  }

}
