package org.exoplatform.platform.qa.ui.platform.chat;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import javax.security.auth.Refreshable;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_LIST_MSG;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("chat")
@Tag("sniff")
public class ChatOnSiteNotificationTestIT extends Base {

    HomePagePlatform homePagePlatform;
    ChatManagement chatManagement;
    ManageLogInOut manageLogInOut;
    RoomManagement roomManagement;
    UserAndGroupManagement userAndGroupManagement;
    NavigationToolbar navigationToolbar;
    UserAddManagement userAddManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        navigationToolbar = new NavigationToolbar(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        chatManagement = new ChatManagement(this);
        userAddManagement = new UserAddManagement(this);
        roomManagement = new RoomManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    }

    @Test
    public void test01_CheckOnSiteNotificationWhenSendMessageInRoom() {

        String room = "room" + getRandomNumber();
        String username = "username" + getRandomNumber();
        String password = "123456";
        String email = "emaila" + getRandomNumber() + "@test.com";
        String Firstname = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        String message = "message" + getRandomString();
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, Firstname, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room, username);
        chatManagement.sendMessageInRoomOrSpace(room, message);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username, password);
        ELEMENT_CHAT_NOTIFICATION_NUMBER.waitUntil(Condition.appear, Configuration.timeout).click();
        $(byText(message)).waitUntil(Condition.appear, Configuration.timeout).click();
        ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
        $(byText(message)).should(Condition.exist);
        ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();
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
    public void test02_CheckOnSiteNotificationWhenSendEmoticons() {

        String room = "room" + getRandomNumber();
        String username = "username" + getRandomNumber();
        String password = "123456";
        String email = "emaila" + getRandomNumber() + "@test.com";
        String Firstname = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, Firstname, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room, username);
        ELEMENT_CHAT_EMOTICON.click();
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-smile")).click();
        ELEMENT_CHAT_MESSAGE_INPUT.pressEnter();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("emoticon-smile")).waitUntil(Condition.appear,Configuration.timeout);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username, password);
        ELEMENT_CHAT_NOTIFICATION_NUMBER.waitUntil(Condition.appear, Configuration.timeout).click();
        $(byText(":)")).waitUntil(Condition.appear, Configuration.timeout).click();
        ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
        $(byClassName("emoticon-smile")).should(Condition.exist);
        ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();
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
    public void test03_CheckOnSiteNotificationWhenStartMeeting(){
        String room = "room" + getRandomNumber();
        String username = "username" + getRandomNumber();
        String password = "123456";
        String email = "emaila" + getRandomNumber() + "@test.com";
        String Firstname = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, Firstname, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room, username);
        roomManagement.startStopmeeting(room);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username,password);
        ELEMENT_CHAT_NOTIFICATION_NUMBER.waitUntil(Condition.appear, Configuration.timeout).click();
        $(byClassName("uiIconChatMeeting")).should(Condition.visible);
        assertEquals("Meeting started",$(byXpath("//*[@id=\"chat-notifications-details\"]/a/div/div[1]/div/a")).getText());
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
    public void test04_CheckOnSiteNotificationWhenStopMeeting(){

        String room = "room" + getRandomNumber();
        String username = "username" + getRandomNumber();
        String password = "123456";
        String email = "emaila" + getRandomNumber() + "@test.com";
        String Firstname = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, Firstname, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room, username);
        roomManagement.startStopmeeting(room);
        roomManagement.startStopmeeting(room);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username,password);
        ELEMENT_CHAT_NOTIFICATION_NUMBER.waitUntil(Condition.appear, Configuration.timeout).click();
        $(byClassName("uiIconChatMeeting")).should(Condition.visible);
        assertEquals("Meeting finished",$(byXpath("//*[@id=\"chat-notifications-details\"]/a/div/div[1]/div/a")).getText());
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.deleteRomm(room);
        switchToParentWindow();
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(username);
    }

    @BugInPLF("CHAT-990")
    public void test05_CheckOnSiteNotificationWhenToggleIsOFF(){
        String message= "message"+getRandomNumber();
        String username = "username" + getRandomNumber();
        String password = "123456";
        String email = "emaila" + getRandomNumber() + "@test.com";
        String Firstname = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, Firstname, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CHAT_SETTING_NOTIFICATION.click();
        ELEMENT_CHAT_ON_SITE_BUTTON_NOTIFICATION.parent().click();
        ELEMENT_CHAT_CONFIRM_BUTTON_NOTIFICATION.click();
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username,password);
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CHAT_SEARCH_FIELD.setValue("John Smith");
        ELEMENT_CONTACT_LIST.find(byText("John Smith")).waitUntil(Condition.visible,Configuration.timeout).click();
        ELEMENT_CHAT_MESSAGE_INPUT.setValue(message).pressEnter();
        ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1,PLFData.DATA_PASS2);
        ELEMENT_CHAT_BADGE_NOTIFICATION.shouldNotBe(Condition.visible);
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(username);
    }

    @Test
    public void test06_CheckOnSiteNotificationWhenStatusIsDONotDisturb(){
        String room ="room"+getRandomNumber();
        String message= "message"+getRandomNumber();
        String username = "username" + getRandomNumber();
        String password = "123456";
        String email = "emaila" + getRandomNumber() + "@test.com";
        String Firstname = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        chatManagement.changeStatus("Do not disturb");
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, Firstname, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room,username);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username,password);
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.visible,Configuration.timeout).click();
        chatManagement.sendMessageInRoomOrSpace(room, message);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1,PLFData.DATA_PASS2);
        $(byXpath("//*[@id=\"chatApplicationNotification\"]/div[1]/a/div/span")).shouldBe(Condition.visible);
        switchTo().window(1);
        refresh();
        roomManagement.deleteRomm(room);
        switchToParentWindow();
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(username);
    }

    @Test
    public void test07_CheckDONotDisturbNotificationButton(){
      homePagePlatform.goToChat();
      switchTo().window(1);
        ELEMENT_CHAT_SETTING_NOTIFICATION.click();
        ELEMENT_CHAT_DO_NOT_DISTURB_BUTTON_NOTIFICATION.parent().click();
        ELEMENT_CHAT_CONFIRM_BUTTON_NOTIFICATION.click();
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1,PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        refresh();
        ELEMENT_CHAT_SETTING_NOTIFICATION.click();
        assertEquals("ON",$(byId("notifyDonotdisturb")).parent().getText());
    }

    @Test
    public void test08_CheckDesktopNotificationButton(){
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CHAT_SETTING_NOTIFICATION.click();
        ELEMENT_CHAT_DESKTOP_NOTIFICATION_BUTTON_.parent().click();
        ELEMENT_CHAT_CONFIRM_BUTTON_NOTIFICATION.click();
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1,PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        refresh();
        ELEMENT_CHAT_SETTING_NOTIFICATION.click();
        assertEquals("OFF",$(byId("notifyDesktop")).parent().getText());
    }

    @Test
    public void test09_CheckBipsNotificationButton(){
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CHAT_SETTING_NOTIFICATION.click();
        ELEMENT_CHAT_BIP_NOTIFICATION_BUTTON.parent().click();
        ELEMENT_CHAT_CONFIRM_BUTTON_NOTIFICATION.click();
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1,PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        refresh();
        ELEMENT_CHAT_SETTING_NOTIFICATION.click();
        assertEquals("OFF",ELEMENT_CHAT_BIP_NOTIFICATION_BUTTON.parent().getText());
    }

    @Test
    public void test10_CheckOnSiteNotificationButton(){
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CHAT_SETTING_NOTIFICATION.click();
        ELEMENT_CHAT_ON_SITE_NOTIFICATION_BUTTON.parent().click();
        ELEMENT_CHAT_CONFIRM_BUTTON_NOTIFICATION.click();
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1,PLFData.DATA_PASS2);
        homePagePlatform.goToChat();
        switchTo().window(1);
        refresh();
        ELEMENT_CHAT_SETTING_NOTIFICATION.click();
        assertEquals("OFF",ELEMENT_CHAT_ON_SITE_NOTIFICATION_BUTTON.parent().getText());
    }
}
