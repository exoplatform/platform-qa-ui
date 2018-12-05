package org.exoplatform.platform.qa.ui.platform.chat.functional;

import com.codeborne.selenide.Condition;
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
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by exo on 7/3/18.
 */

@Tag("chat")
@Tag("functional")
public class LeaveChatRoomTestIT extends Base {
    HomePagePlatform homePagePlatform;

    ChatManagement chatManagement;

    ManageLogInOut manageLogInOut;

    ConnectionsManagement connectionsManagement;

    SpaceManagement spaceManagement;

    SpaceHomePage spaceHomePage;

    SpaceSettingManagement spaceSettingManagement;

    RoomManagement roomManagement;

    WikiHomePage wikiHomePage;
    UserAndGroupManagement userAndGroupManagement;

    NavigationToolbar navigationToolbar;
    UserAddManagement userAddManagement;
    AddUsers addUsers;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        connectionsManagement = new ConnectionsManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        spaceManagement = new SpaceManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        chatManagement = new ChatManagement(this);
        spaceSettingManagement = new SpaceSettingManagement(this);
        roomManagement = new RoomManagement(this);
        wikiHomePage = new WikiHomePage(this);
        navigationToolbar = new NavigationToolbar(this);
        userAddManagement = new UserAddManagement(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        addUsers = new AddUsers(this);
        manageLogInOut.signInCas(PLFData.username, PLFData.password);
    }

    @Test
    public void test01_checktheleaveroombutton() {

        info("Create 4 new users");
        String password = "123456";
        String room1 = "room1" + getRandomNumber();
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(4, password);
        manageLogInOut.signIn(arrayUsers.get(1), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(2), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(3), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room1, arrayUsers.get(1), arrayUsers.get(2), arrayUsers.get(3));
        switchTo().window(0);
        manageLogInOut.signIn(arrayUsers.get(1), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.leaveRoom(room1);
        switchTo().window(0);
        manageLogInOut.signIn(PLFData.username, PLFData.password);
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(arrayUsers.get(0));
        userAndGroupManagement.deleteUser(arrayUsers.get(1));
        userAndGroupManagement.deleteUser(arrayUsers.get(2));
        userAndGroupManagement.deleteUser(arrayUsers.get(3));
    }

    @Test
    public void test02_CheckthedisplayofthepopupFromNoButton() {

        info("Create 4 new users");
        String password = "123456";
        String room1 = "room1" + getRandomNumber();
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(4, password);
        manageLogInOut.signIn(arrayUsers.get(1), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(2), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(3), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room1, arrayUsers.get(1), arrayUsers.get(2), arrayUsers.get(3));
        switchTo().window(0);
        manageLogInOut.signIn(arrayUsers.get(1), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.CancelleaveRoomFromNo(room1);
        switchTo().window(0);
        manageLogInOut.signIn(PLFData.username, PLFData.password);
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(arrayUsers.get(0));
        userAndGroupManagement.deleteUser(arrayUsers.get(1));
        userAndGroupManagement.deleteUser(arrayUsers.get(2));
        userAndGroupManagement.deleteUser(arrayUsers.get(3));
    }

    @Test
    public void test03_CheckthedisplayofthepopupFromxButton() {


        info("Create 4 new users");
        String password = "123456";
        String room1 = "room1" + getRandomNumber();
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(4, password);
        manageLogInOut.signIn(arrayUsers.get(1), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(2), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(3), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room1, arrayUsers.get(1), arrayUsers.get(2), arrayUsers.get(3));
        switchTo().window(0);
        manageLogInOut.signIn(arrayUsers.get(1), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.CancelleaveRoom(room1);
        switchTo().window(0);
        manageLogInOut.signIn(PLFData.username, PLFData.password);
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(arrayUsers.get(0));
        userAndGroupManagement.deleteUser(arrayUsers.get(1));
        userAndGroupManagement.deleteUser(arrayUsers.get(2));
        userAndGroupManagement.deleteUser(arrayUsers.get(3));

    }

    @Test
    public void test04_Checkthedisplayofthepopup() {


        info("Create 4 new users");
        String password = "123456";
        String room1 = "room1" + getRandomNumber();
        ArrayList<String> arrayUsers = new ArrayList<String>();
        navigationToolbar.goToAddUser();
        arrayUsers = userAddManagement.addUsers(4, password);
        manageLogInOut.signIn(arrayUsers.get(1), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(2), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(3), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        manageLogInOut.signIn(arrayUsers.get(0), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.addRoom(room1, arrayUsers.get(1), arrayUsers.get(2), arrayUsers.get(3));
        switchTo().window(0);
        manageLogInOut.signIn(arrayUsers.get(1), password);
        homePagePlatform.refreshUntil($(byClassName("status-dropdown")), Condition.visible,1000);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.ViewLeavePopup(room1);
        switchTo().window(0);
        manageLogInOut.signIn(PLFData.username, PLFData.password);
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(arrayUsers.get(0));
        userAndGroupManagement.deleteUser(arrayUsers.get(1));
        userAndGroupManagement.deleteUser(arrayUsers.get(2));
        userAndGroupManagement.deleteUser(arrayUsers.get(3));
    }
}