package org.exoplatform.platform.qa.ui.platform.chat;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Tag("chat")
@Tag("smoke")
public class ChatContactListTestIT extends Base {

    HomePagePlatform homePagePlatform;
    ManageLogInOut manageLogInOut;
    NavigationToolbar navigationToolbar;
    RoomManagement roomManagement;
    UserAddManagement userAddManagement;
    UserAndGroupManagement userAndGroupManagement;
    ChatManagement chatManagement;
    SpaceManagement spaceManagement;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut = new ManageLogInOut(this);
        navigationToolbar = new NavigationToolbar(this);
        roomManagement = new RoomManagement(this);
        userAddManagement = new UserAddManagement(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        chatManagement = new ChatManagement(this);
        spaceManagement= new SpaceManagement(this);
        manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    }

    @Test
    public void test01_CheckFavoriteIconForRoom(){

        String room= "room" + getRandomNumber();
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room);
        info("add room to favorites");
        ELEMENT_CONTACT_LIST.find(byText(room)).hover();
        assertEquals("Add to Favorites", $(byClassName("favorite")).waitUntil(Condition.appear, Configuration.timeout).getAttribute("data-original-title"));
        $(byClassName("favorite")).waitUntil(Condition.appear, Configuration.timeout).click();
        ELEMENT_CONTACT_LIST.find(byText(room)).parent().parent().parent().parent().find(byClassName("is-fav")).should(Condition.exist);
        info("remove room from favorites");
        assertEquals("Remove from Favorites",ELEMENT_CONTACT_LIST.find(byText(room)).parent().parent().parent().parent().find(byClassName("is-fav")).getAttribute("data-original-title"));
        ELEMENT_CONTACT_LIST.find(byText(room)).parent().parent().parent().parent().find(byClassName("is-fav")).click();
        ELEMENT_CONTACT_LIST.find(byText(room)).parent().parent().parent().parent().find(byClassName("is-fav")).shouldNot(Condition.appear);
        roomManagement.deleteRomm(room);
    }

    @Test
    public void test02_CheckFavoriteIconForSpace(){

        String space= "space"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(space);
        info("add room to favorites");
        ELEMENT_CONTACT_LIST.find(byText(space)).hover();
        assertEquals("Add to Favorites", $(byClassName("favorite")).waitUntil(Condition.appear, Configuration.timeout).getAttribute("data-original-title"));
        $(byClassName("favorite")).waitUntil(Condition.appear, Configuration.timeout).click();
        ELEMENT_CONTACT_LIST.find(byText(space)).parent().parent().parent().parent().find(byClassName("is-fav")).should(Condition.exist);
        info("remove room from favorites");
        assertEquals("Remove from Favorites",ELEMENT_CONTACT_LIST.find(byText(space)).parent().parent().parent().parent().find(byClassName("is-fav")).getAttribute("data-original-title"));
        ELEMENT_CONTACT_LIST.find(byText(space)).parent().parent().parent().parent().find(byClassName("is-fav")).click();
        ELEMENT_CONTACT_LIST.find(byText(space)).parent().parent().parent().parent().find(byClassName("is-fav")).shouldNot(Condition.appear);
        switchToParentWindow();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);
    }

    @Test
    public void test03_SearchFieldAction(){
        String room1= "room1"+getRandomNumber();
        String room2="room2"+getRandomNumber();
        String room3="room3"+getRandomNumber();
        String newroom="newroom"+getRandomNumber();

        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room1);
        roomManagement.addRoom(room2);
        roomManagement.addRoom(room3);
        ELEMENT_CHAT_SEARCH_FIELD.setValue(room1);
        ELEMENT_CONTACT_LIST.find(byText(room1)).waitUntil(Condition.visible,Configuration.timeout);
        ELEMENT_CONTACT_LIST.find(byText(room2)).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
        ELEMENT_CONTACT_LIST.find(byText(room3)).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
        roomManagement.editTitleofAroom(room1,newroom);
        assertEquals(room1,ELEMENT_CHAT_SEARCH_FIELD.getValue());
        ELEMENT_CHAT_SEARCH_FIELD.click();
        for(int i=0;i<room1.length();i++){
            ELEMENT_CHAT_SEARCH_FIELD.sendKeys(Keys.BACK_SPACE);
        }
        ELEMENT_CONTACT_LIST.find(byText(newroom)).waitUntil(Condition.appear,Configuration.timeout);
        ELEMENT_CONTACT_LIST.find(byText(room2)).waitUntil(Condition.appear,Configuration.timeout);
        ELEMENT_CONTACT_LIST.find(byText(room3)).waitUntil(Condition.appear,Configuration.timeout);
        roomManagement.deleteRomm(newroom);
        roomManagement.deleteRomm(room2);
        roomManagement.deleteRomm(room3);
    }

    @Test
    public void test04_CheckNotificationSettingPopUp(){
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CHAT_SETTING_NOTIFICATION.click();
        ELEMENT_CHAT_PREFERRENCE_POPUP.waitUntil(Condition.appear,Configuration.timeout);
        assertEquals("Do Not Disturb Notifications",ELEMENT_CHAT_PREFERRENCE_POPUP.find(byXpath("//*[@id=\"chatPreferences\"]/div/div[2]/section[1]/div/div[2]/b")).getText());
        assertEquals("Desktop notifications",ELEMENT_CHAT_PREFERRENCE_POPUP.find(byXpath("//*[@id=\"chatPreferences\"]/div/div[2]/section[2]/div[1]/div[2]/b")).getText());
        assertEquals("On-site notifications",ELEMENT_CHAT_PREFERRENCE_POPUP.find(byXpath("//*[@id=\"chatPreferences\"]/div/div[2]/section[2]/div[2]/div[2]/b")).getText());
        assertEquals("Bips",ELEMENT_CHAT_PREFERRENCE_POPUP.find(byXpath("//*[@id=\"chatPreferences\"]/div/div[2]/section[2]/div[3]/div[2]/b")).getText());
    }

    @Test
    public void test05

}
