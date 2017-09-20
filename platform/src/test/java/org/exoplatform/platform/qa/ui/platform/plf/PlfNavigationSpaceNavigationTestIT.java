package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
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
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(username, password);
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
  }

  @Test
  public void test02_RemoveAdoptionApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Forum Statistics";
    info("app:" + app);
    String category = "Adoption";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);

    info("Verify that Application is added to space");
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byText("ForumsStatistic")).should(Condition.exist);
    spaceSettingManagement.removeApplication(app);
    $(ELEMENT_SPACE_MENU_MORE).shouldNot(Condition.exist);
    ELEMENT_SPACE_MENU_TAB.find(byText(app)).shouldNot(Condition.exist);

    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test03_RemoveCollaborationApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Answers";
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
    ELEMENT_SPACE_MENU_TAB.find(byText("Answer")).should(Condition.exist);
    spaceSettingManagement.removeApplication("Answer");
    $(ELEMENT_SPACE_MENU_MORE).shouldNot(Condition.exist);
    ELEMENT_SPACE_MENU_TAB.find(byText(app)).shouldNot(Condition.exist);

    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test04_RemoveComponentsApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Blog Articles";
    info("app:" + app);
    String category = "Components";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    info("Verify that Application is added to space");
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("BlogArticlesPortlet")).should(Condition.exist);
    spaceSettingManagement.removeApplication(app);
    $(ELEMENT_SPACE_MENU_MORE).shouldNot(Condition.exist);
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
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("Bookmark")).should(Condition.exist);
    spaceSettingManagement.removeApplication(app);
    $(ELEMENT_SPACE_MENU_MORE).shouldNot(Condition.exist);
    ELEMENT_SPACE_MENU_TAB.find(byText(app)).shouldNot(Condition.exist);

    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test06_RemoveToolsApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Google Map";
    info("app:" + app);
    String category = "Web";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    info("Verify that Application is added to space");
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("GoogleMapPortlet")).should(Condition.exist);
    spaceSettingManagement.removeApplication(app);
    $(ELEMENT_SPACE_MENU_MORE).shouldNot(Condition.exist);
    ELEMENT_SPACE_MENU_TAB.find(byText(app)).shouldNot(Condition.exist);

    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test07_RemoveSocialApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Friend Suggestions";
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
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("FriendSuggestion")).should(Condition.exist);
    spaceSettingManagement.removeApplication(app);
    $(ELEMENT_SPACE_MENU_MORE).shouldNot(Condition.exist);
    ELEMENT_SPACE_MENU_TAB.find(byText(app)).shouldNot(Condition.exist);

    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test07_AddAdoptionApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Forum Statistics";
    info("app:" + app);
    String category = "Adoption";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);

    info("Verify that Application is added to space");
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byText("ForumsStatistic")).should(Condition.exist);
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test08_AddCollaborationApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Answers";
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
    ELEMENT_SPACE_MENU_TAB.find(byText("Answer")).should(Condition.exist);
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test09_AddComponentsApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Blog Articles";
    info("app:" + app);
    String category = "Components";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    info("Verify that Application is added to space");
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("BlogArticlesPortlet")).should(Condition.exist);
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
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("Bookmark")).should(Condition.exist);
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test11_AddToolsApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Google Map";
    String category = "Web";
    info("cate:" + category);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    info("Verify that Application is added to space");
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("GoogleMapPortlet")).should(Condition.exist);
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test12_AddocialApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Friend Suggestions";
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
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("FriendSuggestion")).should(Condition.exist);
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test13_AddManyApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Friend Suggestions";
    String category = "social";
    String app1 = "Google Map";
    String category1 = "Web";
    String app2 = "Bookmarks";
    String category2 = "Tools";
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    spaceSettingManagement.addApplication(category1, app1);
    spaceSettingManagement.addApplication(category2, app2);
    info("Verify that Application is added to space");
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("FriendSuggestion")).should(Condition.exist);
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("GoogleMapPortlet")).should(Condition.exist);
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("Bookmark")).should(Condition.exist);
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }

  @Test
  public void test14_RemoveManyApplicationToolbar() {
    info("Test 02: Remove application of space's toolbar");
    String space1 = "space1" + getRandomNumber();
    String app = "Friend Suggestions";
    String category = "social";
    String app1 = "Google Map";
    String category1 = "Web";
    String app2 = "Bookmarks";
    String category2 = "Tools";
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    spaceSettingManagement.addApplication(category, app);
    spaceSettingManagement.addApplication(category1, app1);
    spaceSettingManagement.addApplication(category2, app2);
    info("Verify that Application is added to space");
    $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("FriendSuggestion")).should(Condition.exist);
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("GoogleMapPortlet")).should(Condition.exist);
    ELEMENT_LIST_OF_MORE_APPLICATION_IN_SPACE.find(byId("Bookmark")).should(Condition.exist);
    spaceSettingManagement.removeApplication(app);
    spaceSettingManagement.removeApplication(app1);
    spaceSettingManagement.removeApplication(app2);
    $(ELEMENT_SPACE_MENU_MORE).shouldNot(Condition.exist);
    ELEMENT_SPACE_MENU_TAB.find(byText(app)).shouldNot(Condition.exist);
    ELEMENT_SPACE_MENU_TAB.find(byText(app1)).shouldNot(Condition.exist);
    ELEMENT_SPACE_MENU_TAB.find(byText(app2)).shouldNot(Condition.exist);
    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
  }
}
