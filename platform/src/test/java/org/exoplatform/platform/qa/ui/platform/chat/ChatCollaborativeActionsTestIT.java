package org.exoplatform.platform.qa.ui.platform.chat;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
@Tag("chat")
@Tag("sniff")
public class ChatCollaborativeActionsTestIT extends Base {

        HomePagePlatform     homePagePlatform;
        ManageLogInOut       manageLogInOut;
        NavigationToolbar    navigationToolbar;
        RoomManagement       roomManagement;
        CalendarHomePage     calendarHomePage;
        EventManagement      eventManagement;
        UserAddManagement    userAddManagement;



    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut= new ManageLogInOut(this);
        navigationToolbar =new NavigationToolbar(this);
        roomManagement = new RoomManagement(this);
        calendarHomePage= new CalendarHomePage(this);
        eventManagement= new EventManagement(this);
        userAddManagement= new UserAddManagement(this);
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);

    }
    @Test
    public void test01_AddEventInChatRoom(){

        String room = "room" +    getRandomNumber();
        String username= "username" + getRandomNumber();
        String event= "event" + getRandomNumber();
        String password= "123456";
        String FirstName= "FirstName"+getRandomString();
        String LastName= "LastName" + getRandomString();
        String email= username + "@test.com";


        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username,password,email,FirstName,LastName);
        homePagePlatform.goToChat();
        switchTo().window(1);
        
    }

}
