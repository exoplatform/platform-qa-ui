package org.exoplatform.platform.qa.ui.platform.chat;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.MessageManagement;
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

import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_NAME_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("chat")
@Tag("smoke")
public class ChatMessageManagementTestIT extends Base {

    HomePagePlatform homePagePlatform;
    ManageLogInOut manageLogInOut;
    NavigationToolbar navigationToolbar;
    RoomManagement roomManagement;
    CalendarHomePage calendarHomePage;
    EventManagement eventManagement;
    UserAddManagement userAddManagement;
    UserAndGroupManagement userAndGroupManagement;
    ChatManagement chatManagement;
    MessageManagement messageManagement;

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
        messageManagement=new MessageManagement(this);
        manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    }

    @Test
    public void test01_CheckMessageActions(){

        String room = "room"+getRandomNumber();
        String message= "message"+getRandomNumber();

        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        chatManagement.sendMessageInRoomOrSpace(room,message);
        messageManagement.checkMessageMenu(message);

    }

    @Test
    public void test02_EditMessage(){
        String room = "room"+getRandomNumber();
        String message= "message"+getRandomNumber();
        String newmessage="newmessage"+getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        chatManagement.sendMessageInRoomOrSpace(room,message);
        messageManagement.editMessage(message,newmessage);
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test03_DeleteMessage(){

        String room = "room"+getRandomNumber();
        String message= "message"+getRandomNumber();
        String newmessage="newmessage"+getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        chatManagement.sendMessageInRoomOrSpace(room,message);
        messageManagement.deleteMessage(message);
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test04_QuoteMessage(){
        String room="room"+getRandomNumber();
        String message="message"+getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        chatManagement.sendMessageInRoomOrSpace(room,message);
        messageManagement.quoteMessage(message,DATA_NAME_USER1);
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test05_SaveNotes(){
        String room="room"+getRandomNumber();
        String message="message"+getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        chatManagement.sendMessageInRoomOrSpace(room,message);
        messageManagement.saveNotes(message,DATA_NAME_USER1);
        switchTo().window(1);
        roomManagement.deleteRomm(room);
    }
}
