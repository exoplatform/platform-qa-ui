package org.exoplatform.platform.qa.ui.platform.chat;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
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

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_AVATAR_IN_SEARCH_USER;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_MINI_CHAT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("chat")
@Tag("sniff")
public class MiniChatTestIt extends Base {
    HomePagePlatform homePagePlatform;
    ManageLogInOut   manageLogInOut;
    NavigationToolbar navigationToolbar;
    UserAddManagement userAddManagement;
    ConnectionsManagement connectionsManagement;
    UserAndGroupManagement userAndGroupManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut = new ManageLogInOut(this);
        userAddManagement=new UserAddManagement(this);
        navigationToolbar=new NavigationToolbar(this);
        connectionsManagement=new ConnectionsManagement(this);
        userAndGroupManagement=new UserAndGroupManagement(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    }

    @Test
    public void test01_OpenMiniChatFromUserPopUpInConnection(){
        String username = "username" + getRandomNumber();
        String password = "123456";
        String email = "emaila" + getRandomNumber() + "@test.com";
        String Firstname = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        String MiniChatName;
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, Firstname, LastName);
        homePagePlatform.goToConnections();
        connectionsManagement.searchPeople(Firstname+" "+LastName, null, null, null);
        refresh();
        $(byClassName(" spaceBox")).find(byText(Firstname+" "+LastName)).hover();
        $(byId("tiptip_content")).waitUntil(Condition.appear, Configuration.timeout);
        $(byXpath("//*[@id=\"tiptip_content\"]/div/a/i")).click();
        ELEMENT_MINI_CHAT.waitUntil(Condition.appear,Configuration.timeout);
        MiniChatName=$(byClassName("title-left")).parent().parent().find(byClassName("fullname")).getText();
        assertEquals(Firstname+" "+LastName,MiniChatName);
        ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(username);
    }

    @Test
    public void test02_OpenMiniChatFromUserProfile(){
        String username = "username" + getRandomNumber();
        String password = "123456";
        String email = "emaila" + getRandomNumber() + "@test.com";
        String Firstname = "FirstName" + getRandomString();
        String LastName = "LastName" + getRandomString();
        String MiniChatName;
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username, password, email, Firstname, LastName);
        homePagePlatform.goToConnections();
        connectionsManagement.searchPeople(Firstname+" "+LastName, null, null, null);
        refresh();
        $(byClassName(" spaceBox")).find(byText(Firstname+" "+LastName)).click();
        $(byClassName("uiIconAppprofile")).waitUntil(Condition.appear,Configuration.timeout);
        $(byXpath("//*[@id=\"UIUserNavigationPortlet\"]/div[1]/div/div[1]/ul/li[2]/a")).click();
        ELEMENT_MINI_CHAT.waitUntil(Condition.appear,Configuration.timeout);
        MiniChatName=$(byClassName("title-left")).parent().parent().find(byClassName("fullname")).getText();
        assertEquals(Firstname+" "+LastName,MiniChatName);
        ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(username);
    }

}
