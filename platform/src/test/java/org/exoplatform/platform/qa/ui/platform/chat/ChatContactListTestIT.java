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

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CONTACT_LIST;
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

    @Test void test02_CheckFavoriteIconForSpace(){
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
}
