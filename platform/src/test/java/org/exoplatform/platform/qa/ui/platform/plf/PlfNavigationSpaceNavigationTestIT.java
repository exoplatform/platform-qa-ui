package org.exoplatform.platform.qa.ui.platform.plf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * @author eXo
 */
@Tag("sniff")
@Tag("plf")
public class PlfNavigationSpaceNavigationTestIT extends Base {

  HomePagePlatform homePagePlatform;

  SpaceHomePage spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  SpaceManagement spaceManagement;

  ManageLogInOut manageLogInOut;

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
  public void test01_AddThenRemoveCollaborationThenBookmarksThenPeopleDirectoryThenWikiApplicationToolbar() {
    String space1 = "space1" + getRandomNumber();
    String app2 = "Bookmarks";
    info("app2:" + app2);
    String category2 = "Tools";
    info("cate2:" + category2);
    String app3 = "People Directory";
    info("app3:" + app3);
    String category3 = "social";
    info("cate3:" + category3);
    String app4 = "Wiki";
    info("app4:" + app4);
    String category4 = "Collaboration";
    info("cate4:" + category4);

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

    info("Remove application of space's toolbar");
    info(" Click on Add Application, select application and click add button");
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToApplicationTab();
    info("Remove application of space's toolbar");
    info(" Click on Add Application, select application and click add button");
    spaceSettingManagement.addApplication(category2, app2);
    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      executeJavaScript("window.scrollBy(0,-150)");
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='Bookmark']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("Bookmark")).should(Condition.exist);
    }
    spaceSettingManagement.removeApplication(app2);
    ELEMENT_SPACE_MENU_TAB.find(byText(app2)).shouldNot(Condition.exist);

    info("Remove application of space's toolbar");
    spaceSettingManagement.addApplication(category3, app3);
    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='PeoplePortlet']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("PeoplePortlet")).should(Condition.exist);
    }
    spaceSettingManagement.removeApplication(app3);
    ELEMENT_SPACE_MENU_TAB.find(byText(app3)).shouldNot(Condition.exist);

    info(" Click on Add Application, select application and click add button");
    spaceSettingManagement.addApplication(category4, app4);
    info("Verify that Application is added to space");
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.exist)) {
      $(ELEMENT_SPACE_MENU_MORE).waitUntil(Condition.appears, Configuration.timeout).click();
      $(byXpath("//span[@id='wiki']")).should(Condition.exist);
    } else {
      ELEMENT_SPACE_MENU_TAB.find(byId("wiki")).should(Condition.exist);
    }

    info(" Click on Add Application, select application and click add button");
    spaceSettingManagement.addApplication(category2, app2);
    spaceSettingManagement.addApplication(category3, app3);
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
    spaceSettingManagement.removeApplication(app2);
    executeJavaScript("window.scrollBy(0,-200)");
    spaceSettingManagement.removeApplication(app3);
    ELEMENT_SPACE_MENU_TAB.find(byText(app2)).shouldNot(Condition.exist);
    ELEMENT_SPACE_MENU_TAB.find(byText(app3)).shouldNot(Condition.exist);

    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);

  }

}
