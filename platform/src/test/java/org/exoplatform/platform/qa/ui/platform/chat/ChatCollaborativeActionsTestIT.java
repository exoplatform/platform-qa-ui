package org.exoplatform.platform.qa.ui.platform.chat;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("chat")
@Tag("sniff")

public class ChatCollaborativeActionsTestIT extends Base {

    HomePagePlatform homePagePlatform;
    ManageLogInOut manageLogInOut;
    NavigationToolbar navigationToolbar;
    RoomManagement roomManagement;
    CalendarHomePage calendarHomePage;
    EventManagement eventManagement;
    UserAddManagement userAddManagement;
    UserAndGroupManagement userAndGroupManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut = new ManageLogInOut(this);
        navigationToolbar = new NavigationToolbar(this);
        roomManagement = new RoomManagement(this);
        calendarHomePage = new CalendarHomePage(this);
        eventManagement = new EventManagement(this);
        userAddManagement = new UserAddManagement(this);
        userAndGroupManagement=new UserAndGroupManagement(this);
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    }

    @Test
    public void test01_AddEventInChatRoom() {

        String room = "room" + getRandomNumber();
        String username = "username" + getRandomNumber();
        String event = "event" + getRandomNumber();
        String location = "location" + getRandomNumber();
        String password = "123456";
        String FirstName = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        String email = username + "@test.com";

        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, FirstName, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room, username);
        ELEMENT_CONTACT_LIST.find(byText(room)).click();
        ELEMENT_COLLABORATION_ACTIONS.click();
        ELEMENT_CHAT_ADD_EVENT.click();
        ELEMENT_ADD_EVENT_WINDOW.waitUntil(Condition.appear, Configuration.timeout);
        ELEMENT_ADD_EVENT.setValue(event);
        ELEMENT_CHAT_EVENT_FROM_DATE_.click();
        $(byClassName("today")).click();
        ELEMENT_CHAT_EVENT_TO_DATE_.click();
        $(byClassName("today")).click();
        ELEMENT_CHAT_EVENT_LOCATION.setValue(location);
        ELEMENT_CHAT_POST_EVENT.click();
        ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(event)).waitUntil(Condition.appear, Configuration.timeout);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username, password);
        ELEMENT_CHAT_ICON_STATUS.click();
        ELEMENT_CHAT_NOTIFICATION.find(byText(event)).should(Condition.appears);
        homePagePlatform.goToCalendarPage();
        $(byText(event)).waitUntil(Condition.appear,Configuration.timeout);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1,PLFData.DATA_PASS2);
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(username);
    }
}
