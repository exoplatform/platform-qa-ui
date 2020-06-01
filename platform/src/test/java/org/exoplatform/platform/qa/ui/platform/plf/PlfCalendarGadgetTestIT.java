package org.exoplatform.platform.qa.ui.platform.plf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("sniff")
@Tag("plf")
public class PlfCalendarGadgetTestIT extends Base {
  ManageLogInOut manageLogInOut;
  HomePagePlatform homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }

    manageLogInOut.signInCas(PLFData.username, PLFData.password);
  }


  @Test
  public void test01_SwitchBetweenDaysThenConfigureCalendarGadgetThenAddACalendarToTheListOfFollowedCalendarsThenFilterCalendarsInSettings() {

    info("Switch between days");
    info("Click on next arrow of Calendar gadget");
    click(ELEMENT_HP_CALENDARGADGET_RIGHTARROW);
    info("Verify that 'Tomorrow' text is shown");
    ELEMENT_CALENDAR_CONTAINER.shouldHave(Condition.text("Tomorrow"));

    info("Click on previous arrow of Calendar gadget");
    click(ELEMENT_HP_CALENDARGADGET_LEFTARROW);
    click(ELEMENT_HP_CALENDARGADGET_LEFTARROW);
    info("Verify that 'Yesterday' text is shown");
    ELEMENT_CALENDAR_CONTAINER.shouldHave(Condition.text("Yesterday"));


    info("Add a calendar to the list of followed calendars");
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

    info("Configure calendar gadget");
    homePagePlatform.goToHomePage();
    info("Hover over on the calendar box");
    mouseOver(ELEMENT_HP_CALENDARGADGET_BOX, true);
    info("Click on Settings icon");
    click(ELEMENT_HP_CALENDARGADGET_SETTINGS);
    info("Verify the results");
    waitForAndGetElement(ELEMENT_HP_CALENDARGADGET_SETTINGS_DISPLAYEDCAL);
    waitForAndGetElement(ELEMENT_HP_CALENDARGADGET_SETTINGS_SETTINGSCAL);
    info("Filter calendars in settings");
    info("remove a calendar ");
    ELEMENT_ICON_DELETE_CALENDAR_GADGET_CONTENT_MANAGEMENT.waitUntil(Condition.visible, Configuration.timeout).click();
    info("input a calendar");
    $(ELEMENT_HP_CALENDARGADGET_SETTINGS_SEARCHCAL).setValue("Content");
    executeJavaScript("window.scrollBy(0,150)");
    info("Hover over on add button");
    $(byXpath("(//*[@class='calName'])[2]")).hover();
    info("Click on add button");
    $(byXpath("(//i[@class='uiIconSimplePlusMini uiIconLightGray'])[2]")).waitUntil(Condition.visible, Configuration.timeout).click();
    info("Verify that the calendar is added");
    ELEMENT_ICON_DELETE_CALENDAR_GADGET_CONTENT_MANAGEMENT.parent().shouldHave(Condition.text("Content Management"));

  }

}
