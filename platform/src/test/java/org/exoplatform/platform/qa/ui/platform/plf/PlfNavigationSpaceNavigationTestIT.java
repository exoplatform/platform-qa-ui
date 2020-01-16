package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;

/**
 * @author eXo
 */
@Tag("sniff")
@Tag("plf")
public class PlfNavigationSpaceNavigationTestIT extends Base {

  HomePagePlatform       homePagePlatform;

  SpaceHomePage          spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  SpaceManagement        spaceManagement;

  ManageLogInOut         manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceManagement = new SpaceManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }

  @Test
  public void test03_RemoveCollaborationApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Tasks";
    info("app:" + app);
    String category = "Collaboration";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);

    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//*[@id='tasks']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byText(app)).should(Condition.exist);
    }
    spaceSettingManagement.removeApplication("Tasks");
    ELEMENT_SPACE_MENU_TAB.find(byText(app)).shouldNot(Condition.exist);

    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test05_RemoveToolsApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Bookmarks";
    info("app:" + app);
    String category = "Tools";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='Bookmark']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("Bookmark")).should(Condition.exist);
    }
    spaceSettingManagement.removeApplication(app);
    ELEMENT_SPACE_MENU_TAB.find(byText(app)).shouldNot(Condition.exist);

    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test07_RemoveSocialPeopleDirectoryApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "People Directory";
    info("app:" + app);
    String category = "social";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='PeoplePortlet']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("PeoplePortlet")).should(Condition.exist);
    }
    spaceSettingManagement.removeApplication(app);
    ELEMENT_SPACE_MENU_TAB.find(byText(app)).shouldNot(Condition.exist);

    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test08_AddCollaborationApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Wiki";
    info("app:" + app);
    String category = "Collaboration";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);

    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='wiki']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("wiki")).should(Condition.exist);
    }
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test10_AddToolsApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Bookmarks";
    info("app:" + app);
    String category = "Tools";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='Bookmark']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("Bookmark")).should(Condition.exist);
    }
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test12_AddSocialApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "People Directory";
    info("app:" + app);
    String category = "social";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='PeoplePortlet']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("PeoplePortlet")).should(Condition.exist);
    }
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test13_AddManyApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "People Directory";
    String category = "social";
    String app2 = "Bookmarks";
    String category2 = "Tools";
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    spaceSettingManagement.addApplication(category2, app2);
    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='PeoplePortlet']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("PeoplePortlet")).should(Condition.exist);
    }
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='Bookmark']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("Bookmark")).should(Condition.exist);
    }
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test14_RemoveManyApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "People Directory";
    String category = "social";
    String app2 = "Bookmarks";
    String category2 = "Tools";
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    spaceSettingManagement.addApplication(category2, app2);
    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='PeoplePortlet']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("PeoplePortlet")).should(Condition.exist);
    }
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='Bookmark']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("Bookmark")).should(Condition.exist);
    }
    spaceSettingManagement.removeApplication(app);
    executeJavaScript("window.scrollBy(0,-200)");
    spaceSettingManagement.removeApplication(app2);
    ELEMENT_SPACE_MENU_TAB.find(byText(app)).shouldNot(Condition.exist);
    ELEMENT_SPACE_MENU_TAB.find(byText(app2)).shouldNot(Condition.exist);
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }
  @Test
  public void test15_CheckSpaceApplicationIconsDisplayed() {
    //8166
    String space1 = "space1" + getRandomNumber();
    info("Add new space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    info("Check that tabs on Space are displayed");
    $(ELEMENT_SPACE_WIKI_TAB).waitUntil(Condition.visible, Configuration.timeout).isDisplayed();
    $(ELEMENT_SPACE_FORUMS_TAB).waitUntil(Condition.visible, Configuration.timeout).isDisplayed();
    $(ELEMENT_DOCUMENT_TAB).waitUntil(Condition.visible, Configuration.timeout).isDisplayed();
    $(ELEMENT_TASK_TAB).waitUntil(Condition.visible, Configuration.timeout).isDisplayed();
    $(ELEMENT_AGENDA_TAB).waitUntil(Condition.visible, Configuration.timeout).isDisplayed();
    $(ELEMENT_MEMBER_TAB).waitUntil(Condition.visible, Configuration.timeout).isDisplayed();
    $(ELEMENT_SPACE_SPACE_SETTINGS).waitUntil(Condition.visible, Configuration.timeout).isDisplayed();
    $(ELEMENT_HOME_SPACE_TAB).waitUntil(Condition.visible, Configuration.timeout).isDisplayed();
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }
}
