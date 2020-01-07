package org.exoplatform.platform.qa.ui.platform.plf;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("sniff")
@Tag("plf")
public class Plf_GettintStartedGadgetTestIT extends Base {

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  ActivityStream         activityStream;

  SpaceManagement        spaceManagement;

  ConnectionsManagement  connectionsManagement;

  SiteExplorerHome       siteExplorerHome;

  NavigationToolbar      navigationToolbar;

  UserAndGroupManagement userAndGroupManagement;

  AddUsers               addUsers;

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


  /**
   * <li>Case ID:120844.</li>
   * <li>Test Case Name: Check display of Getting started Gadget.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Check display of Getting
   * Started Gadget Step Description: - Login and Open intranet homepage - Check
   * getting started gadget Input Data: Expected Outcome: This gadget is displayed
   * at the first gadget level (top right), from the first user's connection - The
   * gadget should have the following tasks:+ Add a profile picture+ Connect to
   * coworkers+Join a space+ Post an activity+ Upload a document
   */
  @Test
  public void test01_CheckDisplayOfGettingStartedGadget() {
    info("Test 1: Check display of Getting started Gadget");

    info("Verify that getting started is shown with full tasks");
    waitForAndGetElement(ELEMENT_HP_GETTINGSTARTED_BOX);
    ELEMENT_HP_GETTING_STARTED_SET_YOUR_PROFILE_PICTURE.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_HP_GETTING_STARTED_CONNECT_WITH_OTHERS.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_HP_GETTING_STARTED_JOIN_A_SPACE.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_HP_GETTING_STARTED_POST_A_MESSAGE.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_HP_GETTING_STARTED_UPLOAD_A_DOCUMENT.waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * <li>Case ID:120845.</li>
   * <li>Test Case Name: Perform an action on Getting started Gadget.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Connect to intranet home
   * page Step Description: - Login and connect to intranet home page Input Data:
   * Expected Outcome: - Intranet home page is shown with Getting started gadget
   * Step number: 2 Step Name: Perform an action Step Description: - Click on an
   * action, for example, Upload a profile picture - Complete to upload a profile
   * picture Input Data: Expected Outcome: - The arrow is transformed into a check
   * icon - The action is strikedthrough - The completion percentage is updated
   */
  @Test
  public void test02_PerformAnActionOnGettingStartedGadget() {
    info("Test 2: Perform an action on Getting started Gadget");

    info("Verify that getting started is shown");
    waitForAndGetElement(ELEMENT_HP_GETTINGSTARTED_BOX);

    info("click on connect to coworker link");
    ELEMENT_HP_GETTING_STARTED_CONNECT_WITH_OTHERS.click();
    info("Connect to a coworker");
    String idBtnConnect = $(byText(DATA_NAME_USER3)).parent().parent().parent().getAttribute("id");
    $(byXpath(ELEMENT_BUTTON_CONNECT_TO_USER.replace("{idbtn}", idBtnConnect))).click();
    manageLogInOut.signIn(DATA_USER3, "gtngtngtn");
    info("Accept an requested connection");
    homePagePlatform.goToConnections();
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.RECEIVE);
    $(ELEMENT_HP_GETTINGSTARTED_ACCEPTTOCOWORKERSBTN).click();
    manageLogInOut.signIn(username, password);
    info("Verify that connect to coworker action is done");
    waitForAndGetElement(ELEMENT_HP_GETTINGSTARTED_CONNETTOCOWORKERS_DONE);
    homePagePlatform.goToConnections();
    $(byXpath(ELEMENT_BUTTON_CONNECT_TO_USER.replace("{idbtn}", idBtnConnect))).click();
  }

  /**
   * <li>Case ID:120846.</li>
   * <li>Test Case Name: Check direction when performing an action.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Connect to intranet home page Step Description: -
   * Login and connect to intranet home page Input Data: Expected Outcome: -
   * Intranet home page is shown Step number: 2 Step Name: Perform a task: Add a
   * picture Step Description: - Click on "Add a picture" Input Data: Expected
   * Outcome: Open Profile page Step number: 3 Step Name: Perform a task: Connect
   * to coworkers Step Description: - Click on "Connect to coworkers" Input Data:
   * Expected Outcome: Open Employee Directory Step number: 4 Step Name: Perform a
   * task: Join a space Step Description: - Click on "Join a space" Input Data:
   * Expected Outcome: Open Space directory Step number: 5 Step Name: Perform a
   * task: Post an activity Step Description: - Click on "Post an activity" Input
   * Data: Expected Outcome: Open Homepage (use #) Step number: 6 Step Name:
   * Perform a task: Upload a document Step Description: Click on
   * "Upload a document" Input Data: Expected Outcome: Open Document Explorer (in
   * Personal Documents Folders)
   */
  @Test
  public void test03_CheckDirectionWhenPerformingAnAction() {
    info("Test 3: Check direction when performing an action");

    info("Click on add a picture link");
    ELEMENT_HP_GETTING_STARTED_SET_YOUR_PROFILE_PICTURE.click();
    info("Verify that the profile page is shown");
    waitForAndGetElement(ELEMENT_HP_GETTINGSTARTED_PROFILEPAGE);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    info("click o connect to coworker link");
    homePagePlatform.goToHomePage();
    ELEMENT_HP_GETTING_STARTED_CONNECT_WITH_OTHERS.click();
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
  }

  /**
   * <li>Case ID:120848.</li>
   * <li>Test Case Name: Complete this Getting Started gadget.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Connect to intranet home
   * page Step Description: - Login and connect to intranet home page Input Data:
   * Expected Outcome: - Intranet home page is shown Step number: 2 Step Name:
   * Complete the Getting Started Gadget Step Description: - Perform all tasks of
   * this gadget Input Data: Expected Outcome: - The progress is 100% - A link
   * labelled "Close" is displayed to invite the user to remove the gadget
   */
  @Test
  public void test04_CompleteThisGettingStartedGadget() {
    info("Test 04: Complete this Getting Started gadget");

    String text = "text" + getRandomNumber();
    String spacename = "spacename" + getRandomNumber();

    info("add an avatar");
    ELEMENT_HP_GETTING_STARTED_SET_YOUR_PROFILE_PICTURE.click();
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(ELEMENT_EDIT_MY_PROFILE_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_BUTTON_CHANGE_AVATAR.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_INPUT_UPLOAD_AVATAR.uploadFromClasspath("testavatar.png");
    ELEMENT_BUTTON_CONFIRM_UPLOAD.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_BUTTON_SAVE_UPLOAD_AVATAR.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
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
    spaceManagement.deleteSpace(spacename,false);
  }

  /**
   * <li>Case ID:120847.</li>
   * <li>Test Case Name: Remove Getting Started gadget.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Connect to intranet home
   * page Step Description: - Login and connect to intranet home page Input Data:
   * Expected Outcome: - Intranet home page is shown Step number: 2 Step Name:
   * Remove Getting Started gadget Step Description: - Click the close icon in
   * upper right corner of Getting Started gadget Input Data: Expected Outcome: -
   * Getting Started gadget is removed permanently - Once the gadget is removed,
   * there is no way to bring it back.
   */
  @Test
  public void test05_RemoveGettingStartedGadget() {
    info("Test 05: Remove Getting Started gadget");

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
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
  }
}
