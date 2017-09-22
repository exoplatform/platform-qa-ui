package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_HELP_TOOLBAR;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TITLE_WIKI_INPUT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;

/**
 * @author eXo
 */
@Tag("smoke")
@Tag("plf")
public class PlfNavigationTopNavigationTestIT extends Base {

  NavigationToolbar       navigationToolbar;

  HomePagePlatform        homePagePlatform;

  ForumCategoryManagement forumCategoryManagement;

  ForumForumManagement    forumForumManagement;

  CalendarHomePage        calendarHomePage;

  ForumHomePage           forumHomePage;

  TasksManagement         tasksManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    navigationToolbar = new NavigationToolbar(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    forumForumManagement = new ForumForumManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    forumHomePage = new ForumHomePage(this);
    tasksManagement = new TasksManagement(this);

  }

  /**
   * <li>Case ID:120889.</li>
   * <li>Test Case Name: Create a new Wiki page via the top navigation.</li>
   */
  @Test
  public void test01_CreateANewWikiPageViaTheTopNavigation() {
    info("Test 1: Create a new Wiki page via the top navigation");
    /*
     * Step Number: 1 Step Name: Connect to intranet Step Description: - Login as
     * normal user - Connect to Intranet Input Data: Expected Outcome: - The Top
     * Navigation bar is displayed
     */

    /*
     * Step number: 2 Step Name: Open wiki application Step Description: - Click on
     * the button "Create" (+) - Select "Wiki Page" - Choose a location to add a new
     * wiki page from the space switcher - Click on the button "Next" Input Data:
     * Expected Outcome: - The menu is updated to "Create a new Wiki Page:" - The
     * wiki application is opened with the New Page editor opened
     */
    info("Go to Create Wiki page from Navigation toolbar");
    navigationToolbar.goToCreateWikiPage("");
    info("Verify that wiki home page is shown");
    $(ELEMENT_TITLE_WIKI_INPUT).waitUntil(Condition.appears, Configuration.timeout);

  }

  /**
   * <li>Case ID:120890.</li>
   * <li>Test Case Name: Create a new Poll via the top navigation.</li>
   * <li>Pre-Condition: A forum exists in Intranet</li>
   */
  @Test
  public void test02_CreateANewPollViaTheTopNavigation() {
    info("Test 2: Create a new Poll via the top navigation");

    String category = "category" + getRandomNumber();
    ;
    String forum = "forum" + getRandomNumber();
    String forum1 = "forumA" + getRandomNumber();

    homePagePlatform.goToForum();
    info("Add a category");
    forumCategoryManagement.addCategorySimple(category, "", category);

    info("Add a forum in the category1");
    forumForumManagement.addForumSimple(forum, "", forum);
    forumForumManagement.addForumSimple(forum1, "", forum1);

    /*
     * Step Number: 1 Step Name: Connect to intranet Step Description: - Login as
     * normal user - Connect to Intranet Input Data: - The Top Navigation bar is
     * displayed
     */
    navigationToolbar.goToAddPoll("", forum);
    info("Verify that the poll popup is shown");
    $(ELEMENT_POLL_SUBMIT).waitUntil(Condition.appears, Configuration.timeout);
    info("Delete data");
    ELEMENT_POLL_CANCEL.click();
    homePagePlatform.goToForum();
    forumHomePage.goToHomeCategory();
    forumCategoryManagement.deleteCategory(category);
    /*
     * Step number: 2 Step Name: Open poll application Step Description: - Click on
     * the button "Create" (+) - Select "Poll" - Click on the button "Next" Input
     * Data: Expected Outcome: - Form to create new Poll is displayed - A new Post
     * editor is opened ready to create a new poll
     */

  }

  /**
   * <li>Case ID:120891.</li>
   * <li>Test Case Name: Create a new Topic via the top navigation.</li>
   * <li>Pre-Condition: 1 forum already exist on Intranet</li>
   */
  @Test
  public void test03_CreateANewTopicViaTheTopNavigation() {
    info("Test 3: Create a new Topic via the top navigation");
    String category = "category" + getRandomNumber();
    String forum = "forum" + getRandomNumber();
    String forum1 = "forumA" + getRandomNumber();

    homePagePlatform.goToForum();
    info("Verify that the forum home page is shown full");
    $(ELEMENT_FORUM_WHAT_GOING_ON).waitUntil(Condition.appears, Configuration.timeout);

    info("Add a category");
    forumCategoryManagement.addCategorySimple(category, "", category);

    info("Add a forum in the category1");
    forumForumManagement.addForumSimple(forum, "", forum);
    /*
     * Step Number: 1 Step Name: - Connect to intranet Step Description: - Login as
     * a normal user - Connect to Intranet Input Data: Expected Outcome: - The Top
     * Navigation bar is displayed
     */

    /*
     * Step number: 2 Step Name: - Open Topic application Step Description: - Click
     * on the button "Create" (+) - Select "Topic" - Click on the button "Next"
     * Input Data: Expected Outcome: - Form to create new Topic is displayed - A new
     * Post editor is opened
     */
    navigationToolbar.goToAddTopic("", forum);
    info("Verify that the topic is shown");
    $(ELEMENT_START_TOPIC_POPUP_TITLE).waitUntil(Condition.appears, Configuration.timeout);
    info("Delete data");
    ELEMENT_TOPIC_CANCEL.scrollTo().click();
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
    info("Test 4: Create a new Event via the top navigation");
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

  /**
   * <li>Case ID:120893.</li>
   * <li>Test Case Name: Create a new Task via the top navigation.</li>
   */
  @Test
  public void test05_CreateANewTaskViaTheTopNavigation() {
    info("Test 5: Create a new Task via the top navigation");
    String name = "name" + getRandomNumber();
    /*
     * Step Number: 1 Step Name: - Connect to intranet Step Description: - Login as
     * a normal user - Connect to Intranet Input Data: Expected Outcome: - The Top
     * Navigation bar is displayed
     */
    navigationToolbar.gotoAddTask(name);

    /*
     * Step number: 2 Step Name: Open form "Add new Task" Step Description: - Click
     * on the button "Create" (+) - Select the item "Event/Task" Input Data:
     * Expected Outcome: - Form to create new Event/Task is displayed
     */

    /*
     * Step number: 3 Step Name: Create a task Step Description: - Select "Task"
     * from "Add new" - Input available data of date and times - Select the calendar
     * - Click on the button "Next" Input Data: Expected Outcome: - A message is
     * displayed: "The task was added to $calendar_name" - The message disappears
     * with a fadeout effect
     */
    homePagePlatform.goToTaskPage();
    $(byText(name)).should(Condition.exist);
    tasksManagement.deleteTask(name);
    $(byText(name)).shouldNot(Condition.exist);
  }

  /**
   * <li>Case ID:120895.</li>
   * <li>Test Case Name: Open user guide.</li> /*Step Number: 1 Step Name: Connect
   * to intranet Step Description: - Login as normal user - Connect to Intranet
   * Input Data: Expected Outcome: - The Homepage is displayed - The Help button
   * is displayed in the right of the bar Step number: 2 Step Name: Open user
   * guide Step Description: - Click on the button "?" Input Data: Expected
   * Outcome: - A new tab in the internet browser is opened - The user guide is
   * opened and the chapter displayed matched with the current navigation of the
   * user.
   */
  @Test
  @Tag("sniff")
  public void test07_OpenUserGuide() {
    info("Test 7: Open user guide");
    homePagePlatform.goToHomePage();
    click(ELEMENT_HELP_TOOLBAR);
    switchTo().window(1);
    String url = WebDriverRunner.url();
    assertEquals(url,
                 "https://docs.exoplatform.org/public/index.jsp?topic=/PLF44/PLFUserGuide.GettingStarted.SocialIntranetHomepage.html");
    close();
  }
}
