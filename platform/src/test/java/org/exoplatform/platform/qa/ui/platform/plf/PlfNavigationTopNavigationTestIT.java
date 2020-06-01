package org.exoplatform.platform.qa.ui.platform.plf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_HELP_TOOLBAR;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TITLE_WIKI_INPUT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author eXo
 */
@Tag("sniff")
@Tag("plf")
public class PlfNavigationTopNavigationTestIT extends Base {

  NavigationToolbar navigationToolbar;

  HomePagePlatform homePagePlatform;

  ForumCategoryManagement forumCategoryManagement;

  ForumForumManagement forumForumManagement;

  CalendarHomePage calendarHomePage;

  ForumHomePage forumHomePage;

  TasksManagement tasksManagement;

  ForumTopicManagement forumTopicManagement;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    navigationToolbar = new NavigationToolbar(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    forumForumManagement = new ForumForumManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    forumHomePage = new ForumHomePage(this);
    forumTopicManagement = new ForumTopicManagement(this);
    tasksManagement = new TasksManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }

  @Test
  public void test01_CreateANewPollViaTheTopNavigation() {

    String category = "category" + getRandomNumber();
    ;
    String forum = "forum" + getRandomNumber();
    String forum1 = "forumA" + getRandomNumber();
    String question = "Question" + getRandomNumber();
    String option1 = "Option1" + getRandomNumber();
    String option2 = "Option2" + getRandomNumber();

    homePagePlatform.goToForum();
    info("Verify that the forum home page is shown full");
    $(ELEMENT_FORUM_WHAT_GOING_ON).waitUntil(Condition.appears, Configuration.timeout);


    info("Create a new Poll via the top navigation");
    info("Add a category");
    forumCategoryManagement.addCategorySimple(category, "", category);

    info("Add a forum in the category");
    forumForumManagement.addForumSimple(forum, "", forum);
    forumForumManagement.addForumSimple(forum1, "", forum1);
    homePagePlatform.goToHomePage();
    sleep(2000);
    navigationToolbar.goToAddPoll("", forum);
    info("Add a new poll to the topic");
    info("Input a question to poll");
    $(ELEMENT_POLL_QUESTION).val(question);
    info("Input an option 1 to poll");
    $(ELEMENT_POLL_OPTIONS0).val(option1);
    info("Input an option 2 to poll");
    $(ELEMENT_POLL_OPTIONS1).val(option2);
    info("Click on Submit button");
    $(ELEMENT_POLL_SUBMIT).click();
    info("Verify Poll is added");
    $(byText(option1)).should(Condition.exist);
    info("Finished adding poll");
    info("Delete data");
    homePagePlatform.goToForum();
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(category);

  }

  @Test
  public void test02_CreateANewWikiPageViaTheTopNavigation() {

    info("Create a new Wiki page via the top navigation");
    info("Go to Create Wiki page from Navigation toolbar");
    homePagePlatform.goToHomePage();
    navigationToolbar.goToCreateWikiPage("");
    info("Verify that wiki home page is shown");
    getExoWebDriver().getWebDriver().navigate().refresh();
    $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);

    info("Open user guide");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(ELEMENT_HELP_TOOLBAR), Condition.visible, Configuration.timeout);
    $(ELEMENT_HELP_TOOLBAR).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    switchTo().window(1);
    sleep(2000);
    String url = WebDriverRunner.url();
    assertEquals(url,
            "https://docs.exoplatform.org/en/5.3/");
    switchTo().window(0);

  }

  @Test
  @Tag("PLF-8521")
  public void test03_CreateANewTaskThenANewTopicViaTheTopNavigation() {

    info("Create a new Task via the top navigation");
    String name = "name" + getRandomNumber();
    navigationToolbar.gotoAddTask(name);

    homePagePlatform.goToTaskPage();
    $(byText(name)).waitUntil(Condition.visible, Configuration.collectionsTimeout).should(Condition.exist);
    tasksManagement.deleteTask(name);
    $(byText(name)).waitUntil(Condition.visible, Configuration.collectionsTimeout).shouldNot(Condition.exist);

    info("Create a new Topic via the top navigation");
    String category = "category" + getRandomNumber();
    String forum = "forum" + getRandomNumber();
    homePagePlatform.goToForum();
    info("Verify that the forum home page is shown full");
    $(ELEMENT_FORUM_WHAT_GOING_ON).waitUntil(Condition.appears, Configuration.timeout);

    info("Add a category");
    forumCategoryManagement.addCategorySimple(category, "", category);

    info("Add a forum in the category1");
    forumForumManagement.addForumSimple(forum, "", forum);

    navigationToolbar.goToAddTopic("", forum);
    info("Verify that the topic is shown");
    $(byXpath("//*[@id='UIForumDescription']//strong[text()='${forum}']".replace("${forum}", forum))).waitUntil(Condition.appears, Configuration.timeout);
    info("Delete data");
    homePagePlatform.goToForum();
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(category);

  }

  /**
   * <li>Case ID:120892.</li>
   * <li>Test Case Name: Create a new Event via the top navigation.</li>
   */
  @Test
  @BugInPLF("CAL-1280")
  public void test04_CreateANewEventViaTheTopNavigation() {
    info("Create a new Event via the top navigation");
    String name = "name" + getRandomNumber();
    /*
     * Step Number: 1 Step Name: - Connect to intranet Step Description: - Login as
     * a normal user - Connect to Intranet Input Data: Expected Outcome: - The Top
     * Navigation bar is displayed
     */
    navigationToolbar.goToAddEvent(name, "", "", "");

    /*
     * Step number: 2 Step Name: - Open form "Add new" to input data Step
     * Description: - Click on the button "Create" (+) - Select the item
     * "Event/Task" Input Data: Expected Outcome: - Form to create Event/Task is
     * displayed
     */

    /*
     * Step number: 3 Step Name: - Create an event Step Description: - Select
     * "event" from "Add new" - Input available data of date and times - Select the
     * calendar - Click on the button "Next" - Wait 2 second Input Data: Expected
     * Outcome: - A message is displayed: "The event was added to $calendar_name" -
     * The message disappears with a fadeout effect
     */
    homePagePlatform.goToCalendarPage();
    calendarHomePage.verifyIsPresentEventTask(name,
            CalendarHomePage.selectViewOption.WEEK,
            CalendarHomePage.selectDayOption.DETAILTIME);
    calendarHomePage.deleteEventTask(name,
            CalendarHomePage.selectViewOption.WEEK,
            CalendarHomePage.selectDayOption.DETAILTIME,
            getDate(0, "MM/dd/yyyy"));

  }

}
