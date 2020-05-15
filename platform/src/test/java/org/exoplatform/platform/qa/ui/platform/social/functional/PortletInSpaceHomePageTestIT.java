package org.exoplatform.platform.qa.ui.platform.social.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_TOOLTIP_LEFT;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_TOOLTIP_RIGHT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("functional")
@Tag("social")
public class PortletInSpaceHomePageTestIT extends Base {

  NavigationToolbar navigationToolbar;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  SpaceManagement spaceManagement;

  UserAndGroupManagement userAndGroupManagement;

  AddUsers addUsers;

  EventManagement eventManagement;

  CalendarHomePage calendarHomePage;

  SpaceSettingManagement spaceSettingManagement;

  SpaceHomePage spaceHomePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    addUsers = new AddUsers(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    spaceManagement = new SpaceManagement(this);
    eventManagement = new EventManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceHomePage = new SpaceHomePage(this);
  }

  @Test
    public void test01_CheckTheSpaceTodayEventInThePortlet() throws ParseException {
    //TC21640 //TC21647 //TC21648 //TC21649

    info("Create new space");
    String titleEvent = "titleEvent" + getRandomNumber();
    String space = "space" + getRandomNumber();
    DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String dateEventAdded = format.format(date);
    String pattern = "MM/dd/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String addDateEvent = simpleDateFormat.format(new Date());
    String toolTips = "arrows";
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    spaceSettingManagement.inviteUser(DATA_USER3, false, "");
    spaceManagement.goToAgendaTab();
    info("Add an event in personal calendar");
    ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.contextClick();
    homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT), Condition.visible, 1000);
    executeJavaScript("window.scrollBy(0,-2000)", "");
    eventManagement.goToAddEventFromActionBar();
    eventManagement.checkEventPopUp(addDateEvent, DATA_NAME_USER1, DATA_USER1);
    info("Add event");
    ELEMENT_EVENT_TITLE_DRAWER.setValue(titleEvent);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
            CalendarHomePage.selectViewOption.DAY,
            CalendarHomePage.selectDayOption.DETAILTIME);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(null, DATA_NAME_USER1, null, space, titleEvent, dateEventAdded, toolTips);
    manageLogInOut.signIn(DATA_USER3, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(null, DATA_NAME_USER1, null, space, titleEvent, dateEventAdded, toolTips);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test02_CheckTheManagerPortletInTheSpaceSettings() throws ParseException {
    //TC21680 //TC21638 //TC21643 //TC21649
    info("Create a space");
    String space = "space" + getRandomNumber();
    ArrayList<String> managersNames = new ArrayList<>();
    managersNames.add(0, DATA_NAME_ROOT);
    managersNames.add(1, DATA_NAME_USER3);
    managersNames.add(2, null);
    manageLogInOut.signIn(PLFData.username, PLFData.PASS_ROOT);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    spaceSettingManagement.inviteUser(DATA_USER3, false, "");
    manageLogInOut.signIn(DATA_USER3, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    manageLogInOut.signIn(PLFData.username, PLFData.PASS_ROOT);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceHomePage.goToSpaceSettingTab();
    spaceManagement.assignUnassignUserAsManager(DATA_NAME_USER3, null);
    spaceManagement.goToHomeSpace();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(null, null, managersNames, null, null, null, null);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test03_CheckTheSpaceWhoIsOnlinePortlet() {
    //TC21652 //TC21649
    info("create new space");
    String space = "space" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    spaceManagement.changeStatus("Available");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, spaceDesa);
    spaceSettingManagement.inviteUser(DATA_USER2, false, "");
    spaceSettingManagement.inviteUser(DATA_USER3, false, "");
    manageLogInOut.signIn(DATA_USER2, password);
    spaceManagement.changeStatus("Available");
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    spaceManagement.checkOnlineUsers(DATA_USER1);
    manageLogInOut.signIn(DATA_USER3, password);
    spaceManagement.changeStatus("Available");
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    spaceManagement.checkOnlineUsers(DATA_USER1);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test04_CheckSpaceCreatorNotDisplayedInSpaceManagersListAfterDelete() throws ParseException {
    //TC21679
    info("Create a new space");
    String space = "space" + getRandomNumber();
    ArrayList<String> managersNames = new ArrayList<>();
    managersNames.add(0, DATA_NAME_USER3);
    managersNames.add(1, DATA_NAME_USER1);
    managersNames.add(2, DATA_NAME_USER2);
    ArrayList<String> newManagersNames = new ArrayList<>();
    newManagersNames.add(0, DATA_NAME_USER3);
    newManagersNames.add(1, DATA_NAME_USER2);
    newManagersNames.add(2, null);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    spaceSettingManagement.inviteUser(DATA_USER3, false, "");
    spaceSettingManagement.inviteUser(DATA_USER2, false, "");
    manageLogInOut.signIn(DATA_USER3, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    manageLogInOut.signIn(DATA_USER2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceHomePage.goToSpaceSettingTab();
    spaceManagement.assignUnassignUserAsManager(null, managersNames);
    spaceManagement.goToHomeSpace();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(null, null, managersNames, null, null, null, null);
    manageLogInOut.signIn(DATA_USER3, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceHomePage.goToSpaceSettingTab();
    spaceManagement.assignUnassignUserAsManager(DATA_NAME_USER1, null);
    spaceManagement.goToHomeSpace();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(null, null, newManagersNames, null, null, null, null);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test05_CheckTheUpdatedDescription() throws ParseException {
    //TC21642
    info("create new space");
    String space = "space" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceDesaUpdated = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, spaceDesa);
    spaceSettingManagement.inviteUser(DATA_USER3, false, "");
    spaceSettingManagement.inviteUser(DATA_USER2, false, "");
    manageLogInOut.signIn(DATA_USER3, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(spaceDesa, DATA_NAME_USER1, null, null, null, null, null);
    manageLogInOut.signIn(DATA_USER2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(spaceDesa, DATA_NAME_USER1, null, null, null, null, null);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceHomePage.goToSpaceSettingTab();
    spaceManagement.updateSpaceDescription(spaceDesaUpdated);
    spaceManagement.goToHomeSpace();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(spaceDesaUpdated, DATA_NAME_USER1, null, null, null, null, null);
    manageLogInOut.signIn(DATA_USER3, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(spaceDesaUpdated, DATA_NAME_USER1, null, null, null, null, null);
    manageLogInOut.signIn(DATA_USER2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(spaceDesaUpdated, DATA_NAME_USER1, null, null, null, null, null);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test06_CheckTheSpaceWhoIsOnlinePortletAccordingToUserStatus() {
    //TC21639 //TC21644
    info("create new space");
    String space = "space" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    spaceManagement.changeStatus("Available");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, spaceDesa);
    spaceSettingManagement.inviteUser(DATA_USER2, false, "");
    manageLogInOut.signIn(DATA_USER2, password);
    spaceManagement.changeStatus("Available");
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceManagement.checkOnlineUsers(DATA_USER2);
    manageLogInOut.signIn(DATA_USER2, password);
    spaceManagement.changeStatus("Away");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceManagement.checkOnlineUsers(DATA_USER2);
    manageLogInOut.signIn(DATA_USER2, password);
    spaceManagement.changeStatus("Do not disturb");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceManagement.checkOnlineUsers(DATA_USER2);
    manageLogInOut.signIn(DATA_USER2, password);
    spaceManagement.changeStatus("Invisible");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceManagement.checkNotOnlineUsers(DATA_USER2);
    manageLogInOut.signIn(DATA_USER2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.changeStatus("Available");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test07_CheckTheAvatarImageDimensionsThenThePaddingOfDescriptionPortletThenTheManagerTitleDimensionsThenThatOnlySpaceManagerCanEditSpaceHomePageThenTheContentOfTheSpaceDescriptionPortlet() throws ParseException {
    //TC21650 //TC21654 //TC21646 //TC21656 //TC21657 //TC21651 //TC21641 //TC21637 //TC21649
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(spaceDesa, DATA_NAME_USER1, null, null, null, null, null);
    info("Check Avatar Image Dimensions");
    spaceManagement.checkAvatarUserDimensions();
    info("Check the padding of Description portlet");
    spaceManagement.checkDescriptionPortletPadding();
    info("Check Manager Title Dimensions");
    spaceManagement.checkManagerTitleDimensions();
    info("Check Only Space Manager Can Edit Space Home Page");
    spaceSettingManagement.inviteUser(DATA_USER3, false, "");
    spaceManagement.editLayout("Add");
    spaceManagement.editLayout("Delete");
    manageLogInOut.signOut();
    manageLogInOut.signIn(DATA_USER3, password);
    homePagePlatform.goToMySpaces();
    info("Check Only Platform Administrator Can Add Or Delete The Portlet Applications In The Space Home Page");
    spaceManagement.acceptAInvitation(spaceNamea);
    info("Check Elements of the space Description portlet");
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(spaceDesa, DATA_NAME_USER1, null, null, null, null, null);
    spaceManagement.editLayout("Add");
    spaceManagement.editLayout("Delete");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(spaceNamea);
    spaceManagement.accessToSearchedSpace();
    info("Check The Content Of The Space Description Portlet");
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(spaceDesa, DATA_NAME_USER1, null, null, null, null, null);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
  }

  @Test
  public void test08_CheckTheSpaceCalendarPortlet() throws ParseException {
    //TC21653
    info("Create new space");
    String titleEvent = "titleEvent" + getRandomNumber();
    String space = "space" + getRandomNumber();
    DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String dateEventAdded = format.format(date);
    String pattern = "MM/dd/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String addDateEvent = simpleDateFormat.format(new Date());
    Calendar today = Calendar.getInstance();
    today.setTime(date);
    today.add(Calendar.DAY_OF_YEAR, 1);
    String nextDate = format.format(today.getTime());
    today.add(Calendar.DAY_OF_YEAR, -2);
    String previousDate = format.format(today.getTime());
    String toolTips = "arrows";
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    spaceHomePage.goToSpaceSettingTab();
    spaceSettingManagement.goToMemberTab();
    spaceSettingManagement.inviteUser(DATA_USER3, false, "");
    spaceSettingManagement.inviteUser(DATA_USER2, false, "");
    spaceManagement.goToAgendaTab();
    info("Add an event in personal calendar");
    ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.contextClick();
    homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT), Condition.visible, 1000);
    executeJavaScript("window.scrollBy(0,-2000)", "");
    eventManagement.goToAddEventFromActionBar();
    eventManagement.checkEventPopUp(addDateEvent, DATA_NAME_USER1, DATA_USER1);
    info("Add event");
    ELEMENT_EVENT_TITLE_DRAWER.setValue(titleEvent);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
            CalendarHomePage.selectViewOption.DAY,
            CalendarHomePage.selectDayOption.DETAILTIME);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(null, DATA_NAME_USER1, null, space, titleEvent, dateEventAdded, toolTips);
    manageLogInOut.signIn(DATA_USER3, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    spaceManagement.goToAgendaTab();
    info("Add an event in personal calendar");
    ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.contextClick();
    homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT), Condition.visible, 1000);
    executeJavaScript("window.scrollBy(0,-2000)", "");
    eventManagement.goToAddEventFromActionBar();
    eventManagement.checkEventPopUp(addDateEvent, DATA_NAME_USER3, DATA_USER3);
    info("Add event");
    ELEMENT_EVENT_TITLE_DRAWER.setValue(titleEvent);
    eventManagement.selectPreviousAndNextDayInTheEventToAdd(null, "N");
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
            CalendarHomePage.selectViewOption.DAY,
            CalendarHomePage.selectDayOption.DETAILTIME);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(null, DATA_NAME_USER1, null, space, titleEvent, dateEventAdded, toolTips);
    ELEMENT_SPACE_TOOLTIP_RIGHT.waitUntil(Condition.visible, Configuration.timeout).click();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(null, DATA_NAME_USER1, null, space, titleEvent, nextDate, toolTips);
    manageLogInOut.signIn(DATA_USER2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(space);
    spaceManagement.goToAgendaTab();
    info("Add an event in personal calendar");
    ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.contextClick();
    homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT), Condition.visible, 1000);
    executeJavaScript("window.scrollBy(0,-2000)", "");
    eventManagement.goToAddEventFromActionBar();
    eventManagement.checkEventPopUp(addDateEvent, DATA_NAME_USER2, DATA_USER2);
    info("Add event");
    ELEMENT_EVENT_TITLE_DRAWER.setValue(titleEvent);
    eventManagement.selectPreviousAndNextDayInTheEventToAdd("P", null);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
            CalendarHomePage.selectViewOption.DAY,
            CalendarHomePage.selectDayOption.DETAILTIME);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space);
    spaceManagement.accessToSearchedSpace();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(null, DATA_NAME_USER1, null, space, titleEvent, dateEventAdded, toolTips);
    ELEMENT_SPACE_TOOLTIP_LEFT.waitUntil(Condition.visible, Configuration.timeout).click();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(null, DATA_NAME_USER1, null, space, titleEvent, previousDate, toolTips);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
  }

  @Test
  public void test09_CheckPopupDisplayWhenMouseOverTheAvatarWhoIsOnline() {
    //TC21655 //TC21658
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String jobTitleFirstUser = "Business Analyst";
    spaceManagement.editUserProfile(jobTitleFirstUser);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    spaceSettingManagement.inviteUser(DATA_USER2, false, "");
    spaceSettingManagement.inviteUser(DATA_USER3, false, "");
    manageLogInOut.signIn(DATA_USER2, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(spaceNamea);
    spaceManagement.checkOnlineUserJobTitle(DATA_USER1, jobTitleFirstUser);
    info("Check Who Is Online Portlet Dimensions");
    spaceManagement.checkOnlineUsers(DATA_USER1);
    spaceManagement.checkWhoIsOnlinePortletDimensions(DATA_USER1);
    manageLogInOut.signIn(DATA_USER3, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.acceptAInvitation(spaceNamea);
    spaceManagement.checkOnlineUserJobTitle(DATA_USER1, jobTitleFirstUser);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
  }

  @Test
  public void test10_CheckHeaderDimensionAndPaddingThenNoSettingsButtonIsDisplayedForCalendarPortlet() throws ParseException {
    //TC21659 //TC21660
    info("create some spaces");
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String titleEvent = "titleEvent" + getRandomNumber();
    String pattern = "MM/dd/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String dateEvent = simpleDateFormat.format(new Date());
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    spaceManagement.goToAgendaTab();
    info("Add an event in personal calendar");
    ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.contextClick();
    homePagePlatform.refreshUntil($(ELEMENT_BUTTON_EVENT), Condition.visible, 1000);
    executeJavaScript("window.scrollBy(0,-2000)", "");
    eventManagement.goToAddEventFromActionBar();
    eventManagement.checkEventPopUp(dateEvent, DATA_NAME_USER1, DATA_USER1);
    info("Add event");
    ELEMENT_EVENT_TITLE_DRAWER.setValue(titleEvent);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
            CalendarHomePage.selectViewOption.DAY,
            CalendarHomePage.selectDayOption.DETAILTIME);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(spaceNamea);
    spaceManagement.accessToSearchedSpace();
    spaceManagement.checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(spaceDesa, DATA_NAME_USER1, null, null, null, null, null);
    info("Check Calendar Portlet");
    spaceManagement.checkCalendarPortletInSpaceHomePage();
    info("Check No Settings Button Display For Calendar");
    spaceManagement.checkNoSettingsButtonDisplayForCalendar();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
  }

}
