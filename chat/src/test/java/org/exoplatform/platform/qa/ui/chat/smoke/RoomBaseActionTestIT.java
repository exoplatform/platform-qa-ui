package org.exoplatform.platform.qa.ui.chat.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("chat")
@Tag("smoke")
public class RoomBaseActionTestIT extends Base {

    HomePagePlatform homePagePlatform;
    RoomManagement roomManagement;
    NavigationToolbar navigationToolbar;
    UserAddManagement userAddManagement;
    ManageLogInOut manageLogInOut;
    UserAndGroupManagement userandgroupmanagement;
    ChatManagement chatManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        roomManagement = new RoomManagement(this);
        navigationToolbar = new NavigationToolbar(this);
        userAddManagement = new UserAddManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        userandgroupmanagement = new UserAndGroupManagement(this);
        chatManagement = new ChatManagement(this);
        manageLogInOut.signIn(PLFData.DATA_USER1,PLFData.DATA_PASS2);
    }

    @Test
    public void Test01_AddRoomWithSeveralUsers() {
        String usernamea = "usernamea" + getRandomString();
        String usernameb = "usernameb" + getRandomString();
        String usernamec = "usernamec" + getRandomString();
        String password = "123456";
        String room = "room" + getRandomNumber();
        String emaila = usernamea + getRandomNumber() + "@test.com";
        String emailb = usernameb + getRandomNumber() + "@test.com";
        String emailc = usernamec + getRandomNumber() + "@test.com";
        navigationToolbar.goToAddUser();
        info("Create 3 users");
        userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
        userAddManagement.addUser(usernameb, password, emailb, usernameb, usernamec);
        userAddManagement.addUser(usernamec, password, emailc, usernamec, usernamec);
        manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        info("Add room with users");
        switchTo().window(1);
        roomManagement.addRoom(room, usernamea, usernameb, usernamec);
        switchTo().window(0);
        manageLogInOut.signIn(usernamea, password);
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
        switchTo().window(0);
        manageLogInOut.signIn(usernameb, password);
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
        switchTo().window(0);
        manageLogInOut.signIn(usernamec, password);
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
        info("delete data");
        switchTo().window(0);
        manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.deleteRomm(room);
        switchTo().window(0);
        navigationToolbar.goToManageCommunity();
        userandgroupmanagement.deleteUser(usernamea);
        userandgroupmanagement.deleteUser(usernameb);
        userandgroupmanagement.deleteUser(usernamec);
    }

    @Test
    public void test02_deleteRoom() {
        String usernamea = "usernamea" + getRandomString();

        String password = "123456";
        String room = "room" + getRandomNumber();
        String emaila = usernamea + "@test.com";
        navigationToolbar.goToAddUser();
        info("Create new user");
        userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
        manageLogInOut.signIn(usernamea, password);
        homePagePlatform.goToChat();
        switchToParentWindow();
        manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        info("add room");
        roomManagement.addRoom(room, usernamea);
        info("delete room");
        roomManagement.deleteRomm(room);
        info("delete data");
        switchToParentWindow();
        navigationToolbar.goToManageCommunity();
        userandgroupmanagement.deleteUser(usernamea);
    }

    @Test
    public void test03_SendMessageInAROOM() {
        String usernamea = "usernamea" + getRandomString();
        String password = "123456";
        String emaila = usernamea + "@test.com";
        String room = "room" + getRandomNumber();
        String message = "message" + getRandomNumber();
        navigationToolbar.goToAddUser();
        info("Create new user");
        userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
        switchToParentWindow();
        manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        info("add room");
        roomManagement.addRoom(room, usernamea);
        info("send message");
        chatManagement.sendMessageInRoomOrSpace(room, message);
        switchTo().window(0);
        manageLogInOut.signIn(usernamea, password);
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
        info("verify message");
        ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
        switchToParentWindow();
        manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.deleteRomm(room);
        switchTo().window(0);
        navigationToolbar.goToManageCommunity();
        userandgroupmanagement.deleteUser(usernamea);
    }

    @Test
    public void test04_modifyTheTittleOfAroom() {
        String usernamea = "usernamea" + getRandomString();
        String password = "123456";
        String emaila = usernamea + "@test.com";
        String room = "room" + getRandomNumber();
        String newroom = "newroom" + getRandomNumber();
        navigationToolbar.goToAddUser();
        info("Create new user");
        userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
        manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        info("add room");
        roomManagement.addRoom(room, usernamea);
        info("edit title");
        roomManagement.editTitleofAroom(room, newroom);
        ELEMENT_CONTACT_LIST.$(byText(newroom)).should(Condition.exist);
        roomManagement.deleteRomm(newroom);
        switchTo().window(0);
        navigationToolbar.goToManageCommunity();
        userandgroupmanagement.deleteUser(usernamea);
    }

    @Test
    public void test05_CheckRoomNotificationSettingsPage(){
        String room= "room"+getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        roomManagement.checkNotificationRoomSettingsPage(room);
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test06_CheckAlertOnNotificationInRoomDiscussion(){
        String room= "room"+getRandomNumber();

        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        roomManagement.notificationRoomSettings(room,"AlertOn",PLFData.DATA_USER1);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1,PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.checkNotificationRoomSettings(room,"keywords");
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test07_CheckSilenceNotificationInRoomDiscussion(){
        String room= "room"+getRandomNumber();

        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        roomManagement.notificationRoomSettings(room,"Silence",PLFData.DATA_USER1);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1,PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.checkNotificationRoomSettings(room,"silence");
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test08_CheckRoomNotificationCancel(){
        String room= "room"+getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        ELEMENT_CONTACT_LIST.find(byText(room)).click();
        ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
        ELEMENT_CHAT_ROOM_NOTIFICATION.click();
        ELEMENT_CHAT_ROOM_NOTIFICATION_POPUP.waitUntil(Condition.appear, Configuration.timeout);
        ELEMENT_CHAT_CANCEL_BUTTON_ROOM_NOTIFICATION.click();
        ELEMENT_CHAT_ROOM_NOTIFICATION_POPUP.waitUntil(Condition.not(Condition.appear),Configuration.timeout);
        ELEMENT_CONTAINER_LIST_MESSAGES.waitUntil(Condition.appear,Configuration.timeout);
    }

    @Test
    public void test09_CheckRoomNotificationIconCLose(){
        String room= "room"+getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        ELEMENT_CONTACT_LIST.find(byText(room)).click();
        ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
        ELEMENT_CHAT_ROOM_NOTIFICATION.click();
        ELEMENT_CHAT_ROOM_NOTIFICATION_POPUP.waitUntil(Condition.appear, Configuration.timeout);
        ELEMENT_CHAT_ICON_CLOSE_ROOM_NOTIFICATION.click();
        ELEMENT_CHAT_ROOM_NOTIFICATION_POPUP.waitUntil(Condition.not(Condition.appear),Configuration.timeout);
        ELEMENT_CONTAINER_LIST_MESSAGES.waitUntil(Condition.appear,Configuration.timeout);
    }

    @Test
    public void test10_CheckRoomNoficationConfirmButton(){
        String room= "room"+getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        ELEMENT_CONTACT_LIST.find(byText(room)).click();
        ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
        ELEMENT_CHAT_ROOM_NOTIFICATION.click();
        ELEMENT_CHAT_ROOM_NOTIFICATION_POPUP.waitUntil(Condition.appear, Configuration.timeout);
        ELEMENT_NOTIFICATION_CONFIRM_BUTTON.click();
        ELEMENT_CHAT_ROOM_NOTIFICATION_POPUP.waitUntil(Condition.not(Condition.appear),Configuration.timeout);
        ELEMENT_CONTAINER_LIST_MESSAGES.waitUntil(Condition.appear,Configuration.timeout);
    }

    @Test
    public void test09_ShowParticipant(){
        String room="room"+getRandomNumber();
        String username = "usernamea" + getRandomString();
        String password = "123456";
        String email = "email" + getRandomNumber() + "@test.com";
        String FirstName = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, FirstName, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room,username);
        ELEMENT_CONTACT_LIST.find(byText(room)).click();
        ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
        ELEMENT_CHAT_SHOW_PARTICIPANT.click();
        ELEMENT_CHAT_SHOW_PARTICIPANT_POPUP.waitUntil(Condition.appear,Configuration.timeout);
        roomManagement.deleteRomm(room);
    }
}
