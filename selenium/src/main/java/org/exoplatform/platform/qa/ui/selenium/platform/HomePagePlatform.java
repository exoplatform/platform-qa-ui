package org.exoplatform.platform.qa.ui.selenium.platform;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONNECTION_EVERYONE_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.ELEMENT_ANSWER_PORTLET;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.ELEMENT_CALENDAR_WORKING_PANEL;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_ADDNEWSPACE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

public class HomePagePlatform {

  private final TestBase testBase;

  private ElementEventTestBase evt;

  public HomePagePlatform(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Go to Wiki portlet
   */
  public void goToWiki() {
    info("--Go to Wiki--");
    refreshUntil($(ELEMENT_WIKI_LINK_PLF), Condition.visible, 500);
    $(ELEMENT_WIKI_LINK_PLF).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    $(ELEMENT_WIKI_LINK_PLF).click();
    refreshUntil($(ELEMENT_WIKI_LINK_PLF), Condition.visible, 500);
  }

  public void goToChat() {
    info("--Go to chat--");
    refreshUntil($(byClassName("status-dropdown")), Condition.visible, 1000);
    sleep(Configuration.timeout);
    $(byClassName("status-dropdown")).waitUntil(Condition.visible, Configuration.timeout).click();
    sleep(Configuration.timeout);
    $(byClassName("notif-chat-open-link")).waitUntil(Condition.appears, Configuration.timeout).click();
  }

  /**
   * Go to Documents
   */
  public void goToDocuments() {
    info("--Go to Documents--");
    $(ELEMENT_DOCUMENTS_LINK_PLF).click();

  }

  /**
   * Go to People
   */
  public void goToPeople() {
    info("--Go to People--");
    $(ELEMENT_PEOPLE_LINK_PLF).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

  /**
   * Go to Home page
   */
  public void goToHomePage() {
    info("Click on Home link of intranet page");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    $(ELEMENT_HOME_LINK_PLF).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs + Configuration.collectionsTimeout).click();
  }

  /**
   * Go to Home page
   */
  public void goToSpaceHomeTribe() {
    info("Click on Home link of intranet page");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    $(ELEMENT_HOME_SPACE_TRIBE).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs + Configuration.collectionsTimeout).click();
  }

  public void goToSpaceHomeDW() {
    $(byXpath("//*[@class='v-slide-group__prev']//i")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_SPACE_HOME_TAB_DW).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    info("Space setting page is shown");
    $(ELEMENT_HOME_SPACE_DW).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs + Configuration.collectionsTimeout).click();
  }

  public void goToHomeSpaceTribe() {
    $(ELEMENT_SPACE_HOME_TAB_DW).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    info("Space setting page is shown");
    $(ELEMENT_HOME_SPACE_DW).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs + Configuration.collectionsTimeout).click();
  }

  /**
   * Go to Stream page Tribe
   */
  public void goToStreamPageTribe() {

    info("Click on Home page");
    sleep(2000);
    ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(1000);
    ELEMENT_TRIBE_STREAM_PAGE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byXpath("//*[@id='StreamPage']")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

  public void goToStreamPageTribeViaUrl() {

    info("Open Stream page by link");
    open(Configuration.baseUrl + "portal/dw/stream");
    sleep(2000);
    $(byXpath("//*[@id='StreamPage']")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

  public void goToPeoplePageTribeViaUrl() {

    info("Open People page by link");
    open(Configuration.baseUrl + "portal/dw/people");
    sleep(2000);
    $(byXpath("//*[@id='peopleListApplication']")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

  /**
   * Go to People page Tribe
   */
  public void goToPeoplePageTribe() {

    info("Click on People page");
    sleep(2000);
    ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(2000);
    ELEMENT_TRIBE_PEOPLE_PAGE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byXpath("//*[@id='peopleListApplication']")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

  public void goToMyConnectionsFromHomePageDW() {
    ELEMENT_MY_CONNECTIONS_BTN_DW.sendKeys("My Connections");
  }

  public void goToSettingsPageTribe() {
    info("Click on Settings page");
    sleep(2000);
    ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(2000);
    ELEMENT_TRIBE_SETTINGS_PAGE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byXpath("//*[@id='UserSettingLanguage']")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
  }

  public void goToSettingsPageTribeViaUrl() {

    open(Configuration.baseUrl + "portal/dw/settings");
    sleep(2000);
    $(byXpath("//*[@id='UserSettingLanguage']")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

  public void goToSnapshotPageTribe() {

    info("Click on Home page");
    ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TRIBE_SNAPSHOT_PAGE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byXpath("//*[@id='digitalWorkplaceHomePage']")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

  public void goToSnapshotPageTribeViaUrl() {

    open(Configuration.baseUrl + "portal/dw/home");
    sleep(2000);
    $(byXpath("//*[@id='digitalWorkplaceHomePage']")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

  public void goToTasksPageTribe() {
    info("Click on Task page");
    ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TRIBE_SNAPSHOT_PAGE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TRIBE_TASKS_PAGE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

  public void goToTasksPageDW() {
    info("Open My Tasks page by link");
    open(Configuration.baseUrl + "portal/dw/tasks/my-task");
  }

  public void goToTasksIncomingPageDW() {
    info("Open Tasks page by link");
    open(Configuration.baseUrl + "portal/dw/tasks/");
  }

  public void goToTheLabel(String label) {
    $(byXpath("//*[@class='list-labels']//*[@class='label-name' and contains(text(),'${label}')]".replace("${label}", label))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
  }

  /**
   * Go to Home Calendar Page
   */
  public void goToCalendarPage() {
    info("-- Go to calendar home page --");
    ELEMENT_CALENDAR_LINK_PLF.waitUntil(Condition.appears, Configuration.collectionsTimeout).click();
    info("Verify that Calendar page is shown");
    refreshUntil($(ELEMENT_CALENDAR_WORKING_PANEL), Condition.visible, 1000);
    $(ELEMENT_CALENDAR_WORKING_PANEL).waitUntil(Condition.appears, Configuration.timeout);
    info("The calendar page is shown successfully");
  }

  public void goToTaskPage() {
    ELEMENT_TASKS_LINK_PLF.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
  }

  /**
   * Go to my spaces
   */
  public void goToMySpaces() {
    info("-- Go to my spaces --");
    ELEMENT_MY_SPACE_LINK_PLF.waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
    refreshUntil(ELEMENT_ADDNEWSPACE_BUTTON, Condition.visible, Configuration.timeout);
  }

  /**
   * Go to my spaces Tribe
   */
  public void goToMySpacesTribe() {

    info("-- Go to my spaces --");
    ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    GO_TO_SPACES_TRIBE_BTN.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byXpath("//*[@id='spacesListApplication']")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

  public void goToMySpacesTribeViaUrl() {

    sleep(1000);
    open(Configuration.baseUrl + "portal/dw/spaces");
    sleep(1000);
    $(byXpath("//*[@id='spacesListApplication']")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

  }

  /**
   * Go to All space list
   */
  public void goToAllSpace() {
    info("Click on Join a space link");
    executeJavaScript("arguments[0].scrollBy(0,5000);", $(byId("LeftNavigation")));
    $(ELEMENT_ALL_SPACE_JOIN_LINK).waitUntil(Condition.appears, Configuration.timeout).click();

  }

  /**
   * Go to answer page
   */
  public void goToAnswer() {
    info("-- Go to answer page --");

    $(ELEMENT_ANSWER_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_ANSWER_LINK_PLF).click();
    if (evt.waitForAndGetElement(ELEMENT_ANSWER_PORTLET, 5000, 0) == null)
      testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    evt.waitForAndGetElement(ELEMENT_ANSWER_PORTLET);

  }

  /**
   * Go to forum page
   */
  public void goToForum() {
    info("-- Go to forum page --");
    sleep(Configuration.timeout);
    $(ELEMENT_FORUM_LINK_PLF).waitUntil(Condition.visible, Configuration.timeout).click();
    refresh();
  }

  /**
   * Open Connections page
   */
  public void goToConnections() {
    info("--Go to Connections page---");
    info("Click on Connection link");
    $(ELEMENT_CONNECTIONS_LINK_PLF).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).parent().click();
    info("Verify that the connections portlet is shown");
    refreshUntil($(ELEMENT_CONNECTION_EVERYONE_TITLE), Condition.visible, 500);
    info("The connections portlet is shown successfully");
  }

  /**
   * Go to a space
   *
   * @param space
   */
  public void goToSpecificSpace(String space) {
    info("Go to space " + space);
    $(byXpath("//*[@class='ps__scrollbar-y']")).dragAndDropTo(ELEMENT_SPECIFIC_PANEL);
    ELEMENT_SPECIFIC_PANEL.find(byText(space)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

  /**
   * Search User in People Field
   */
  public void searchUsersPeople(String user) {
    info("Enter User Name");
    sleep(2000);
    $(byXpath("//div[@class='selectize-input items not-full']/input[@placeholder='Name']")).setValue(user);
    sleep(2000);
    $(byXpath("//button[@id='SearchButton']")).waitUntil(Condition.visible, Configuration.timeout).click();
    sleep(2000);
  }

  /**
   * Load More Activities
   */
  public void loadMoreActivities() {

    info("Load more activities");
    $(ELEMENT_PLF_HOMEPAGE_LOAD_MORE_BUTTON).scrollTo().click();
  }

  public void refreshUntil(SelenideElement selenideElement, Condition condition, long time) {
    for (int i = 0; i <= 15; i++) {
      try {
        Thread.sleep(time);
      } catch (Exception e) {
        info("Element doesn't " + condition);
      }
      if (selenideElement.is(condition)) {
        break;
      }
      refresh();
    }
  }

  /**
   * Go to user profile
   */

  public void goToUserProfile() {

    info("-- Go to user profile --");

    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(ELEMENT_USER_PROFILE).click();

  }

  public void goToSideBarMenuTribe() {

    ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

}
