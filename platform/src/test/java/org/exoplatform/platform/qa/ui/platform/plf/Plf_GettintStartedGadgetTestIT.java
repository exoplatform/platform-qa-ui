package org.exoplatform.platform.qa.ui.platform.plf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_EDIT_MY_PROFILE_LINK;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("sniff")
@Tag("plf")
public class Plf_GettintStartedGadgetTestIT extends Base {

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ActivityStream activityStream;

  SpaceManagement spaceManagement;

  ConnectionsManagement connectionsManagement;

  SiteExplorerHome siteExplorerHome;

  NavigationToolbar navigationToolbar;

  UserAndGroupManagement userAndGroupManagement;

  AddUsers addUsers;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    activityStream = new ActivityStream(this);
    spaceManagement = new SpaceManagement(this);
    connectionsManagement = new ConnectionsManagement(this);
    siteExplorerHome = new SiteExplorerHome(this);
    navigationToolbar = new NavigationToolbar(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    addUsers = new AddUsers(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }


  @Test
  public void test01_PerformAnActionThenCompleteThisGettingStartedGadget() {

    String text = "text" + getRandomNumber();
    String spacename = "spacename" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";

    info("Perform an action on Getting started Gadget");

    info("Verify that getting started is shown");
    waitForAndGetElement(ELEMENT_HP_GETTINGSTARTED_BOX);
    info("Check display of Getting started Gadget");


    info("click on connect to coworker link");
    ELEMENT_HP_GETTING_STARTED_CONNECT_WITH_OTHERS.click();
    info("Connect to a coworker");
    String idBtnConnect = $(byText(DATA_NAME_USER3)).parent().parent().parent().getAttribute("id");
    $(byXpath(ELEMENT_BUTTON_CONNECT_TO_USER.replace("{idbtn}", idBtnConnect))).click();
    manageLogInOut.signIn(DATA_USER3, DATA_PASS);
    info("Accept an requested connection");
    homePagePlatform.goToConnections();
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.RECEIVE);
    sleep(3000);
    $(ELEMENT_HP_GETTINGSTARTED_ACCEPTTOCOWORKERSBTN).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    manageLogInOut.signIn(username, PLFData.password);
    info("Verify that connect to coworker action is done");
    getExoWebDriver().getWebDriver().navigate().refresh();
    $(ELEMENT_HP_GETTINGSTARTED_CONNETTOCOWORKERS_DONE).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    homePagePlatform.goToConnections();
    $(byXpath(ELEMENT_BUTTON_CONNECT_TO_USER.replace("{idbtn}", idBtnConnect))).click();

    info("Complete this Getting Started gadget");
    info("add an avatar");
    homePagePlatform.goToHomePage();
    ELEMENT_HP_GETTING_STARTED_SET_YOUR_PROFILE_PICTURE.click();
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(ELEMENT_EDIT_MY_PROFILE_LINK).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_BUTTON_CHANGE_AVATAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_INPUT_UPLOAD_AVATAR.uploadFromClasspath("testavatar.png");
    ELEMENT_BUTTON_CONFIRM_UPLOAD.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_BUTTON_SAVE_UPLOAD_AVATAR.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Check direction when performing an action");
    info("Click on add a picture link");
    homePagePlatform.goToHomePage();
    ELEMENT_HP_GETTING_STARTED_SET_YOUR_PROFILE_PICTURE.click();
    info("Verify that the profile page is shown");
    waitForAndGetElement(ELEMENT_HP_GETTINGSTARTED_PROFILEPAGE);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    info("click o connect to coworker link");
    homePagePlatform.goToHomePage();
    sleep(2000);
    getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    ELEMENT_HP_GETTING_STARTED_CONNECT_WITH_OTHERS.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Verify that connection page is shown");
    waitForAndGetElement(ELEMENT_HP_GETTINGSTARTED_CONNECTIONPAGE);

    info("Click on join to space link");
    homePagePlatform.goToHomePage();
    ELEMENT_HP_GETTING_STARTED_JOIN_A_SPACE.click();
    info("Verify that check join space page is shown");
    waitForAndGetElement(ELEMENT_HP_GETTINGSTARTED_CHECKJOINSPACE);

    info("Click on post an activity link");
    homePagePlatform.goToHomePage();
    ELEMENT_HP_GETTING_STARTED_POST_A_MESSAGE.click();
    info("Verify that the activity page is shown");
    waitForAndGetElement(ELEMENT_HOMPAGE_MIDDLE_PANEL);

    info("Click on upload a document link");
    ELEMENT_HP_GETTING_STARTED_UPLOAD_A_DOCUMENT.click();
    info("Verify that upload document page is shown");
    waitForAndGetElement(ELEMENT_HP_GETTINGSTARTED_CHECKUPLOADDOC);

    info("Remove Getting Started gadget");
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    info("Hover over on the title of getting started gadget");
    mouseOver(ELEMENT_HP_GETTINGSTARTED_TITLE, true);
    info("click on close icon");
    click(ELEMENT_HP_GETTINGSTARTED_CLOSEBOX);
    info("Verify that getting started box is hidden");
    waitForElementNotPresent(ELEMENT_HP_GETTINGSTARTED_BOX);
    manageLogInOut.signIn(username, PLFData.password);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username1);
    info("Post an activity");
    homePagePlatform.goToHomePage();
    activityStream.addActivity(text, "");
    refresh();
    info("Joint a space");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(spacename, spacename);
    info("Verify that the progress is completed 100%");
    homePagePlatform.goToHomePage();
    waitForAndGetElement(ELEMENT_HP_GETTINGSTARTED_PROGRESSRATE);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spacename, false);

  }

}
