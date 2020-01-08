package org.exoplatform.platform.qa.ui.platform.chat;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
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
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    ChatManagement chatManagement;

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
        userAndGroupManagement = new UserAndGroupManagement(this);
        chatManagement = new ChatManagement(this);
        manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    }


    @Test
    public void test01_checkMenuCollaborativeActions() {
        String room = "room" + getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        chatManagement.checkMenuCollaborativeAction();
        ELEMENT_COLLABORATION_ACTIONS.click();
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test02_checkaddEventPopUp() {
        String room = "room" + getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        ELEMENT_COLLABORATION_ACTIONS.click();
        ELEMENT_CHAT_ADD_EVENT.click();
        chatManagement.checkAddEventPopUp();
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test03_addEventInChatRoom() {

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
        chatManagement.addEventInChat(event, location);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username, password);
        ELEMENT_CHAT_ICON_STATUS.click();
        ELEMENT_CHAT_NOTIFICATION.find(byText(event)).should(Condition.appears);
        ELEMENT_CHAT_NOTIFICATION.find(byClassName("uiIconChatCreateEvent")).should(Condition.appears);
        homePagePlatform.goToCalendarPage();
        $(byText(event)).waitUntil(Condition.appear, Configuration.timeout);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.deleteRomm(room);
        switchToParentWindow();
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(username);
    }

    @Test
    public void test04_checkAskQuestionPopUp() {
        String room = "room" + getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
        ELEMENT_CHAT_ASK_QUESTION.waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
        chatManagement.checkAskQuestionPopUp();
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test05_addQuestionInChat() {
        String room = "room" + getRandomNumber();
        String Question = "Question" + getRandomString();
        String username = "username" + getRandomNumber();
        String password = "123456";
        String FirstName = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        String email = username + "@test.com";
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, FirstName, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room, username);
        chatManagement.addQuestionInChat(Question);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username, password);
        ELEMENT_CHAT_ICON_STATUS.click();
        ELEMENT_CHAT_NOTIFICATION.find(byClassName("uiIconChatQuestion")).should(Condition.appears);
        ELEMENT_CHAT_NOTIFICATION.find(byText(Question + "?")).should(Condition.appears);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.deleteRomm(room);
        switchToParentWindow();
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(username);
    }

    @Test
    public void test06_checkRaiseHandPopUp() {
        String room = "room" + getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        ELEMENT_CHAT_RAISE_HAND.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        chatManagement.checkRaiseHandPopUp();
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test07_raiseHandInChat() {
        String room = "room" + getRandomNumber();
        String Comment = "comment" + getRandomString();
        String username = "username" + getRandomNumber();
        String password = "123456";
        String FirstName = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        String email = username + "@test.com";
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, FirstName, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room, username);
        chatManagement.raiseHandInChat(Comment);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username, password);
        ELEMENT_CHAT_ICON_STATUS.click();
        ELEMENT_CHAT_NOTIFICATION.find(byClassName("uiIconChatRaiseHand")).should(Condition.appears);
        ELEMENT_CHAT_NOTIFICATION.find(byText(Comment)).should(Condition.appears);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.deleteRomm(room);
        switchToParentWindow();
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(username);
    }

    @Test
    public void test08_checkUploadFilePopUp() {
        String room = "room" + getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        ELEMENT_COLLABORATION_ACTIONS.click();
        ELEMENT_CHAT_UPLOAD_FILE.click();
        chatManagement.checkUploadFilePopUp();
    }


    @Test
    public void test09_uploadFileInChat() {
        String room = "room" + getRandomNumber();
        String username = "username" + getRandomNumber();
        String password = "123456";
        String FirstName = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        String email = username + "@test.com";
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, FirstName, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room, username);
        chatManagement.uploadFile("eXo-Platform.png");
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username, password);
        ELEMENT_CHAT_ICON_STATUS.click();
        ELEMENT_CHAT_NOTIFICATION.find(byClassName("uiIconChatUpload")).should(Condition.appears);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.deleteRomm(room);
        switchToParentWindow();
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(username);
    }

    @Test
    public void test10_checkShareLinkPopUp() {
        String room = "room" + getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        ELEMENT_COLLABORATION_ACTIONS.click();
        ELEMENT_CHAT_SHARE_LINK.click();
        chatManagement.checkShareLinPopUp();
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test11_addShareLinkInChat() {
        String room = "room" + getRandomNumber();
        String username = "username" + getRandomNumber();
        String password = "123456";
        String FirstName = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        String email = username + "@test.com";
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, FirstName, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room, username);
        chatManagement.shareLinkInChat("https://www.google.fr/");
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username, password);
        ELEMENT_CHAT_ICON_STATUS.click();
        ELEMENT_CHAT_NOTIFICATION.find(byClassName("uiIconChatLink")).should(Condition.appears);
        ELEMENT_CHAT_NOTIFICATION.find(byLinkText("https://www.google.fr/")).should(Condition.appears);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.deleteRomm(room);
        switchToParentWindow();
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(username);
    }

    @Test
    public void test12_addInvalidLinkInChat() {
        String room = "room" + getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        ELEMENT_COLLABORATION_ACTIONS.click();
        ELEMENT_CHAT_SHARE_LINK.click();
        ELEMENT_POPUP_CONTAINER.find(byXpath("//input[@placeholder='E.g: http://www.exoplatform.com']")).sendKeys("link");
        ELEMENT_CHAT_SHARE_LINK_BUTTON.click();
        $(byText("The link has an incorrect format. Please enter a valid URL.")).waitUntil(Condition.appear, Configuration.timeout);
        ELEMENT_CHAT_CANCEL_BUTTON.click();
        roomManagement.deleteRomm(room);
    }
}
