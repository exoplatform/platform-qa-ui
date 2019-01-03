package org.exoplatform.platform.qa.ui.platform.chat.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.apache.bcel.generic.LADD;
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

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CONTACT_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_BUTTON_ACCEPT_SPACE_INVITATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by exo on 7/3/18.
 */

@Tag("chat")
@Tag("functional")
public class LeaveChatRoomTestIt extends Base {
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

    @Test
    public void test05_CheckLeaveRoomBuutonOnlyForMembers(){
        String room = "room" + getRandomNumber();
        String usernamea = "username" + getRandomNumber();
        String password = "123456";
        String emaila = "emaila" + getRandomNumber() + "@test.com";
        String FirstnameA = "FirstName" + getRandomString();
        String LastNameA = "LastName" + getRandomString();
        String usernameb = "username" + getRandomNumber();
        String emailb = "emaila" + getRandomNumber() + "@test.com";
        String FirstnameB = "FirstName" + getRandomString();
        String LastNameB = "LastName" + getRandomString();

        navigationToolbar.goToAddUser();
        userAddManagement.addUser(usernamea,password,emaila,FirstnameA,LastNameA);
        userAddManagement.addUser(usernameb,password,emailb,FirstnameB,LastNameB);
        homePagePlatform.goToChat();
        switchTo().window(1);
        info("add usernamea to room");
        roomManagement.addRoom(room,usernamea,usernameb);
        info("check leave room button doesn't exist for manager for manager");
        ELEMENT_MORE_ACTION.click();
        ELEMENT_LEAVEROOM_ACTION.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        switchToParentWindow();
        info("check leave room exist for members");
        manageLogInOut.signIn(usernameb,password);
        homePagePlatform.goToChat();
        switchTo().window(1);
        $(byText(room)).click();
        ELEMENT_MORE_ACTION.click();
        ELEMENT_LEAVEROOM_ACTION.waitUntil(Condition.visible, Configuration.timeout);
        switchToParentWindow();
        manageLogInOut.signIn(usernameb,password);
        homePagePlatform.goToChat();
        switchTo().window(1);
        $(byText(room)).click();
        ELEMENT_MORE_ACTION.click();
        ELEMENT_LEAVEROOM_ACTION.waitUntil(Condition.visible, Configuration.timeout);
        switchToParentWindow();
        manageLogInOut.signIn(PLFData.username, PLFData.password);
        homePagePlatform.goToChat();
        switchTo().window(1);
        roomManagement.deleteRomm(room);
        switchToParentWindow();
        navigationToolbar.goToManageCommunity();
        userAndGroupManagement.deleteUser(usernamea);
        userAndGroupManagement.deleteUser(usernameb);
    }

   @Test
   public void test06_CheckDisplayMessageWhenUserLeftRoom(){
       String room = "room" + getRandomNumber();
       String usernamea = "username" + getRandomNumber();
       String password = "123456";
       String emaila = "emaila" + getRandomNumber() + "@test.com";
       String FirstnameA = "FirstNameA" + getRandomString();
       String LastNameA = "LastNameA" + getRandomString();
       String usernameb = "username" + getRandomNumber();
       String emailb = "emaila" + getRandomNumber() + "@test.com";
       String FirstnameB = "FirstNameB" + getRandomString();
       String LastNameB = "LastNameB" + getRandomString();

       navigationToolbar.goToAddUser();
       userAddManagement.addUser(usernamea,password,emaila,FirstnameA,LastNameA);
       userAddManagement.addUser(usernameb,password,emailb,FirstnameB,LastNameB);
       homePagePlatform.goToChat();
       switchTo().window(1);
       info("add usernamea to room");
       roomManagement.addRoom(room,usernamea,usernameb);
       switchToParentWindow();
       manageLogInOut.signIn(usernamea,password);
       homePagePlatform.goToChat();
       switchTo().window(1);
       roomManagement.leaveRoom(room);
       switchToParentWindow();
       manageLogInOut.signIn(usernameb,password);
       homePagePlatform.goToChat();
       switchTo().window(1);
       $(byText(room));
       ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(FirstnameA+" "+LastNameA)).parent().shouldHave(Condition.text(" has left this room."))
               .should(Condition.exist);
   }

   @Test
    public void test07_CheckUserLeaveSpaceRoomWhenLeaveSpace(){

       String space = "space" + getRandomNumber();
       info("create space and invite user");

       homePagePlatform.goToMySpaces();
       spaceManagement.addNewSpaceSimple(space, space);
       spaceHomePage.goToSpaceSettingTab();
       spaceSettingManagement.goToMemberTab();
       ELEMENT_INPUT_INVITE_USER.sendKeys(PLFData.DATA_USER2);
       $(ELEMENT_SPACE_BTN_INVITE).click();
       homePagePlatform.goToConnections();
       connectionsManagement.connectToAUser(PLFData.DATA_USER2);
       manageLogInOut.signIn(PLFData.DATA_USER2, "gtn");
       homePagePlatform.goToConnections();
       connectionsManagement.acceptAConnection(PLFData.username);
       homePagePlatform.goToAllSpace();
       ELEMENT_SPACES_LIST.find(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_ACCEPT_SPACE_INVITATION).click();
       homePagePlatform.goToChat();
       switchTo().window(1);
       ELEMENT_CONTACT_LIST.find(byText(space)).should(Condition.exist);
       switchToParentWindow();
       homePagePlatform.goToAllSpace();
       ELEMENT_SPACES_LIST.find(byText(space)).parent().parent().parent().find(byAttribute("class", "btn pull-right")).click();
       homePagePlatform.goToChat();
       switchTo().window(1);
       ELEMENT_CONTACT_LIST.find(byText(space)).shouldNotBe(Condition.exist);
       switchToParentWindow();
       manageLogInOut.signIn(PLFData.username,PLFData.password);
       homePagePlatform.goToMySpaces();
       spaceManagement.deleteSpace(space, false);
   }
    @Test
   public void test08_CheckUserSeeOldMessageWhenReinvitedToRoom(){

       String room = "room" + getRandomNumber();
       String message="message"+getRandomNumber();
       String usernamea = "username" + getRandomNumber();
       String password = "123456";
       String emaila = "emaila" + getRandomNumber() + "@test.com";
       String FirstnameA = "FirstName" + getRandomString();
       String LastNameA = "LastName" + getRandomString();

       navigationToolbar.goToAddUser();
       userAddManagement.addUser(usernamea,password,emaila,FirstnameA,LastNameA);
       homePagePlatform.goToChat();
       switchTo().window(1);
       roomManagement.addRoom(room,usernamea);
       chatManagement.sendMessageInRoomOrSpace(room,message);
       switchToParentWindow();
       manageLogInOut.signIn(usernamea,password);
       homePagePlatform.goToChat();
       switchTo().window(1);
       $(byText(room));
       ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
       roomManagement.leaveRoom(room);
       switchToParentWindow();
       manageLogInOut.signIn(PLFData.username,PLFData.password);
       homePagePlatform.goToChat();
       switchTo().window(1);
       $(byText(room)).click();
       roomManagement.editRoom(room,usernamea);
       switchToParentWindow();
       manageLogInOut.signIn(usernamea,password);
       homePagePlatform.goToChat();
       switchTo().window(1);
       $(byText(room));
       ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
       switchToParentWindow();
       manageLogInOut.signIn(PLFData.username,PLFData.password);
       homePagePlatform.goToChat();
       switchTo().window(1);
       roomManagement.deleteRomm(room);
       switchToParentWindow();
       navigationToolbar.goToManageCommunity();
       userAndGroupManagement.deleteUser(usernamea);
    }

    @BugInPLF("CHAT-990")
    public void test09_CheckDiscussionListWhenLeaveRoom(){
        String room = "room" + getRandomNumber();
        String message="message"+getRandomNumber();
        String usernamea = "username" + getRandomNumber();
        String password = "123456";
        String emaila = "emaila" + getRandomNumber() + "@test.com";
        String FirstnameA = "FirstNameA" + getRandomString();
        String LastNameA = "LastNameA" + getRandomString();
        String usernameb = "username" + getRandomNumber();
        String emailb = "emaila" + getRandomNumber() + "@test.com";
        String FirstnameB = "FirstNameB" + getRandomString();
        String LastNameB = "LastNameB" + getRandomString();

        navigationToolbar.goToAddUser();
        userAddManagement.addUser(usernamea,password,emaila,FirstnameA,LastNameA);
        userAddManagement.addUser(usernameb,password,emailb,FirstnameB,LastNameB);
        homePagePlatform.goToChat();
        switchTo().window(1);
        info("add usernamea to room");
        roomManagement.addRoom(room,usernamea);
        switchToParentWindow();
        manageLogInOut.signIn(usernamea,password);
        homePagePlatform.goToChat();
        switchTo().window(1);
        ELEMENT_CHAT_SEARCH_FIELD.setValue(FirstnameB+" "+LastNameB);
        ELEMENT_CONTACT_LIST.find(byText(FirstnameB+" "+LastNameB)).waitUntil(Condition.visible,Configuration.timeout).click();
        ELEMENT_CHAT_MESSAGE_INPUT.setValue(message).pressEnter();
        ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
        $(byText(room));
        roomManagement.leaveRoom(room);
    }
}