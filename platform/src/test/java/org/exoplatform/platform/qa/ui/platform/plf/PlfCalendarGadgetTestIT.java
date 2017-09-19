package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

@Tag("sniff")
@Tag("plf")
public class PlfCalendarGadgetTestIT extends Base {
  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(PLFData.username, PLFData.password);
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
  }

  /**
   * <li>Case ID:120849.</li>
   * <li>Test Case Name: Switch between days.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   * Step Number: 1 Step Name: Connect to intranet home page Step Description: -
   * Login and connect to intranet home page Input Data: Expected Outcome: -
   * Intranet home page is shown Step number: 2 Step Name: Switch to next day Step
   * Description: - Click on arrow on the right of date on calendar gadget Input
   * Data: Expected Outcome: "TOMORROW" is displayed in title* Step number: 3 Step
   * Name: Switch to previous day Step Description: - Click on arrow on the left
   * of date on calendar gadget Input Data: Expected Outcome: "YESTERDAY" is
   * displayed in title
   */
  @Test
  public void test01_SwitchBetweenDays() {
    info("Test 1: Switch between days");

    info("Click on next arrow of Calendar gadget");
    click(ELEMENT_HP_CALENDARGADGET_RIGHTARROW);
    info("Verify that 'Tomorrow' text is shown");
    ELEMENT_CALENDAR_CONTAINER.shouldHave(Condition.text("Tomorrow"));

    info("Click on previous arrow of Calendar gadget");
    click(ELEMENT_HP_CALENDARGADGET_LEFTARROW);
    click(ELEMENT_HP_CALENDARGADGET_LEFTARROW);
    info("Verify that 'Yesterday' text is shown");
    ELEMENT_CALENDAR_CONTAINER.shouldHave(Condition.text("Yesterday"));

  }

  /**
   * <li>Case ID:120850.</li>
   * <li>Test Case Name: Configure calendar gadget.</li>
   * <li>Pre-Condition: - Create some Calendars - Create some events, tasks</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: -Go into gadget's settings
   * Step Description: - Login and go to intranet - Hovering this gadget - Clicks
   * on Setting button Input Data: Expected Outcome: - A small configuration
   * (Setting) button will appear - Settings are displayed. - User can see the
   * list of already selected calendars. - A small sentence "Displayed calendars"
   * is displayed before the list of events' calendar.
   */
  @Test
  public void test02_ConfigureCalendarGadget() {
    info("Test 2: Configure calendar gadget");

    info("Hover over on the calendar box");
    mouseOver(ELEMENT_HP_CALENDARGADGET_BOX, true);
    info("Click on Settings icon");
    click(ELEMENT_HP_CALENDARGADGET_SETTINGS);
    info("Verify the results");
    waitForAndGetElement(ELEMENT_HP_CALENDARGADGET_SETTINGS_DISPLAYEDCAL);
    waitForAndGetElement(ELEMENT_HP_CALENDARGADGET_SETTINGS_SETTINGSCAL);
  }

  /**
   * <li>Case ID:120851.</li>
   * <li>Test Case Name: Add a calendar to the list of followed calendars.</li>
   * <li>Pre-Condition: Create some Calendars: "calendar new", "old cal"</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: -Go into gadget's settings
   * Step Description: - Login and Open intranet home - Mouse over Calendar gadget
   * - Clicks on Setting button Input Data: Expected Outcome: - "Calendar new",
   * "old cal" are displayed - A small configuration (Setting) button will appear
   * - Settings are displayed. Step number: 2 Step Name: - Remove a calendar Step
   * Description: - Mouse over a calendar - "old cal" calendar - Click on Remove
   * icon at "old cal" calendar - Click on OK at the bottom Input Data: Expected
   * Outcome: - The calendar is removed from the list of followed calendars Step
   * number: 3 Step Name: - Add a calendar so that it is displayed on this gadget
   * Step Description: - Mouse over a calendar - "old cal" - Click on Add button -
   * Click on OK at the bottom Input Data: Expected Outcome: - An "ADD" button is
   * displayed. - The "old cal" calendar is added is the list of followed
   * calendars and remove from the of calendars that can be added. - The setting
   * is saved
   */

  @Test
  public void test03_AddACalendarToTheListOfFollowedCalendars() {
    info("Test 3: Add a calendar to the list of followed calendars");

    info("Hover over on the calendar box");
    mouseOver(ELEMENT_HP_CALENDARGADGET_BOX, true);
    info("Click on Settings icon");
    click(ELEMENT_HP_CALENDARGADGET_SETTINGS);

    info("Click on Remove icon of the old calendar");
    ELEMENT_ICON_DELETE_CALENDAR_GADGET_CONTENT_MANAGEMENT.click();
    info("Verify that the old calendar is removed");
    ELEMENT_CALENDAR_CONTAINER.find(byText("Content Management")).shouldNot(Condition.exist);
    info("Hover over the calendar box");
    ELEMENT_CONTAINER_NO_DISPALYED_CALENDAR.find(byText("Content Management")).hover();
    info("Click on add button");
    click(ELEMENT_HP_CALENDARGADGET_SETTINGS_ADDCAL);
    info("Verifyt that the calendar is added");
    ELEMENT_ICON_DELETE_CALENDAR_GADGET_CONTENT_MANAGEMENT.parent().shouldHave(Condition.text("Content Management"));
  }

  /**
   * <li>Case ID:120852.</li>
   * <li>Test Case Name: Remove a calendar from the list of followed
   * calendars.</li>
   * <li>Pre-Condition: - Create some Calendars: "calendar new", "old cal"</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: -Go into gadget's settings
   * Step Description: - Login and Open intranet home - Mouse over Calendar gadget
   * - Clicks on Setting button Input Data: Expected Outcome: - "Calendar new",
   * "old cal" are displayed - A small configuration (Setting) button will appear
   * - Settings are displayed.
   * Step number: 2 Step Name: - Remove a calendar Step Description: - Mouse over
   * a calendar - "old cal" calendar - Click on Remove icon at "old cal" calendar
   * - Click on OK at the bottom Input Data: Expected Outcome: - The calendar is
   * removed from the list of followed calendars Step number: 3 Step Name: - Add a
   * calendar so that it is displayed on this gadget Step Description: - Mouse
   * over a calendar - "old cal" - Click on Add button - Click on OK at the bottom
   * Input Data: Expected Outcome: - An "ADD" button is displayed. - The "old cal"
   * calendar is added is the list of followed calendars and remove from the of
   * calendars that can be added. - The setting is saved
   */
  @Test
  public void test04_DeleteACalendarToTheListOfFollowedCalendars() {
    info("Test 3: Add a calendar to the list of followed calendars");

    info("Hover over on the calendar box");
    mouseOver(ELEMENT_HP_CALENDARGADGET_BOX, true);
    info("Click on Settings icon");
    click(ELEMENT_HP_CALENDARGADGET_SETTINGS);

    info("Click on Remove icon of the old calendar");
    ELEMENT_ICON_DELETE_CALENDAR_GADGET_CONTENT_MANAGEMENT.click();
    info("Verify that the old calendar is removed");
    ELEMENT_CALENDAR_CONTAINER.find(byText("Content Management")).shouldNot(Condition.exist);

    info("Hover over the calendar box");
    ELEMENT_CONTAINER_NO_DISPALYED_CALENDAR.find(byText("Content Management")).hover();
    info("Click on add button");
    $(ELEMENT_HP_CALENDARGADGET_SETTINGS_ADDCAL).click();
    info("Verifyt that the calendar is added");
    ELEMENT_ICON_DELETE_CALENDAR_GADGET_CONTENT_MANAGEMENT.parent().shouldHave(Condition.text("Content Management"));
  }

  /**
   * <li>Case ID:120853.</li>
   * <li>Test Case Name: Filter calendars in settings.</li>
   * <li>Pre-Condition: - Create some Calendars: "calendar new", "old cal"</li>
   * <li>Post-Condition:</li>
   * Step Number: 1 Step Name: -Go into gadget's settings Step Description: -
   * Login and Open intranet home - Move mouse over Calendar gadget - Clicks on
   * Setting button Input Data: Expected Outcome: - Settings are displayed. Step
   * number: 2 Step Name: - Filter calendars Step Description: -Enter text "cal"
   * into the Search field in settings form: Input Data: Expected Outcome: - The
   * search is starting with the first letter entered in the text field, then show
   * "calendar new"
   */

  @Test
  public void test05_FilterCalendarsInSettings() {
    info("Test 5: Filter calendars in settings");

    info("Hover over on the calendar box");
    mouseOver(ELEMENT_HP_CALENDARGADGET_BOX, true);
    info("Click on settings icon");
    click(ELEMENT_HP_CALENDARGADGET_SETTINGS);
    info("remove a calendar ");
    ELEMENT_ICON_DELETE_CALENDAR_GADGET_CONTENT_MANAGEMENT.click();
    info("input a calendar");
    $(ELEMENT_HP_CALENDARGADGET_SETTINGS_SEARCHCAL).setValue("Content");
    info("Hover over on add button");
    ELEMENT_CONTAINER_NO_DISPALYED_CALENDAR.find(byText("Content Management")).hover();
    info("Click on add button");
    click(ELEMENT_HP_CALENDARGADGET_SETTINGS_ADDCAL);
    info("Verify that the calendar is added");
    ELEMENT_ICON_DELETE_CALENDAR_GADGET_CONTENT_MANAGEMENT.parent().shouldHave(Condition.text("Content Management"));
  }
}
