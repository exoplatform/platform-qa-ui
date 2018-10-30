package org.exoplatform.platform.qa.ui.platform.chat;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
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

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_NOTIFICATION_NUMBER;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_MINI_CHAT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("chat")
@Tag("sniff")
public class ChatDesktopNotificationTestIT extends Base {

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
        userAddManagement= new UserAddManagement(this);
        roomManagement=new RoomManagement(this);
        manageLogInOut=new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    }

    @Test
    public void test01_CheckDesktopNotificationWhenSendMessageInRoom(){

        String room = "room" + getRandomNumber();
        String username = "username" + getRandomNumber();
        String password = "123456";
        String email = "emaila" + getRandomNumber() + "@test.com";
        String Firstname= "FirstName"+getRandomString();
        String LastName= "LastName"+getRandomString();
        String message= "message"+getRandomString();
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, Firstname, LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room,username);
        chatManagement.sendMessageInRoomOrSpace(room,message);
        switchToParentWindow();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(username,password);
        ELEMENT_CHAT_NOTIFICATION_NUMBER.waitUntil(Condition.appear, Configuration.timeout).click();
        $(byText(message)).waitUntil(Condition.appear,Configuration.timeout).click();
        ELEMENT_MINI_CHAT.waitUntil(Condition.appear,Configuration.timeout);
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
}
