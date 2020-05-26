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
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_STREAM_HEADING;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_USER_PROFILE;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_USER_RESULT_SEARCH;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_MENU;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_PORTLET;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("chat")
@Tag("sniff")
public class MiniChatTestIT extends Base {
  HomePagePlatform homePagePlatform;

  ManageLogInOut manageLogInOut;

  NavigationToolbar navigationToolbar;

  UserAddManagement userAddManagement;

  ConnectionsManagement connectionsManagement;

  UserAndGroupManagement userAndGroupManagement;

  ActivityStream activityStream;

  SpaceManagement spaceManagement;

  SpaceHomePage spaceHomePage;

  RoomManagement roomManagement;

  ChatManagement chatManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    userAddManagement = new UserAddManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    connectionsManagement = new ConnectionsManagement(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    activityStream = new ActivityStream(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    roomManagement = new RoomManagement(this);
    chatManagement = new ChatManagement(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }

  @Test
  @Tag("cab")
  public void test01_OpenMiniChatFromUserPopUpThenFromUserProfileInConnectionThenFromHomeActivityStreamThenChatTabUpdateThenFromSpace() {
    String username = "username" + getRandomNumber();
    String password = "123456";
    String email = "emaila" + getRandomNumber() + "@test.com";
    String Firstname = "FirstName" + getRandomString();
    String LastName = "LastName" + getRandomString();
    String username2 = "username" + getRandomNumber();
    String email2 = "emailb" + getRandomNumber() + "@test.com";
    String Firstname2 = "FirstName" + getRandomString();
    String LastName2 = "LastName" + getRandomString();
    String usernamea = "username" + getRandomNumber();
    String emaila = "emaila" + getRandomNumber() + "@test.com";
    String FirstnameA = "FirstName" + getRandomString();
    String LastNameA = "LastName" + getRandomString();
    String message = "message" + getRandomNumber();
    String room = "room" + getRandomNumber();
    String usernameb = "usernamea" + getRandomNumber();
    String emailb = "emaila" + getRandomNumber() + "@test.com";
    String FirstnameB = "FirstName" + getRandomString();
    String LastNameB = "LastName" + getRandomString();
    String MiniChatName;

    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username, password, email, Firstname, LastName);
    userAddManagement.addUser(username2, password, email2, Firstname2, LastName2);
    userAddManagement.addUser(usernamea, password, emaila, FirstnameA, LastNameA);
    userAddManagement.addUser(usernameb, password, emailb, FirstnameB, LastNameB);

    homePagePlatform.goToConnections();
    info("Open Mini Chat From User PopUp In Connection");
    connectionsManagement.searchPeople(Firstname + " " + LastName, null, null, null);
    refresh();
    ELEMENT_USER_RESULT_SEARCH.find(byText(Firstname + " " + LastName)).hover();
    ELEMENT_CHAT_TIP_CONTENT.waitUntil(Condition.appear, Configuration.timeout);
    $(byXpath("//*[@id=\"tiptip_content\"]/div/a/i")).click();
    ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
    MiniChatName = $(byClassName("title-left")).parent().parent().find(byClassName("fullname")).getText();
    assertEquals(Firstname + " " + LastName, MiniChatName);
    ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();
    info("Open Mini Chat From User Profile");
    homePagePlatform.goToConnections();
    connectionsManagement.searchPeople(Firstname + " " + LastName, null, null, null);
    refresh();
    ELEMENT_USER_RESULT_SEARCH.find(byText(Firstname + " " + LastName)).click();
    ELEMENT_USER_PROFILE.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHAT_BUTTON_USER_PROFILE.waitUntil(Condition.appear, Configuration.collectionsTimeout).click();
    ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
    MiniChatName = $(byClassName("title-left")).parent().parent().find(byClassName("fullname")).getText();
    assertEquals(Firstname + " " + LastName, MiniChatName);
    ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();

    info("Check Minimize In Mini Chat PopUp");
    homePagePlatform.goToConnections();
    connectionsManagement.searchPeople(Firstname + " " + LastName, null, null, null);
    refresh();
    ELEMENT_USER_RESULT_SEARCH.find(byText(Firstname + " " + LastName)).hover();
    ELEMENT_CHAT_TIP_CONTENT.waitUntil(Condition.appear, Configuration.timeout);
    $(byXpath("//*[@id=\"tiptip_content\"]/div/a/i")).click();
    ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_MINI_CHAT_MINIMIZE_ICON.click();
    $(byClassName("chat-message-list")).waitUntil(Condition.not(Condition.appear), Configuration.timeout);
    ELEMENT_MINI_CHAT_MAXIMIZE_ICON.click();
    ELEMENT_MINI_CHAT_MESSAGE_LIST.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();

    info("Open Mini Chat From Home Activity Stream");
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(username2);
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(PLFData.DATA_USER1);
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    ELEMENT_ACTIVITY_STREAM_HEADING.find(byText(Firstname2 + " " + LastName2)).hover().hover();
    ELEMENT_CHAT_TIP_CONTENT.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHAT_ICON_TIP_CONTENT.click();
    ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
    MiniChatName = $(byClassName("title-left")).parent().parent().find(byClassName("fullname")).getText();
    assertEquals(Firstname2 + " " + LastName2, MiniChatName);
    ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();

    info("Check Mini Chat When A New Conversation Is Opened");
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToConnections();
    connectionsManagement.searchPeople(FirstnameB + " " + LastNameB, null, null, null);
    refresh();
    ELEMENT_USER_RESULT_SEARCH.find(byText(FirstnameB + " " + LastNameB)).hover();
    ELEMENT_CHAT_TIP_CONTENT.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHAT_ICON_TIP_CONTENT.click();
    ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHAT_MESSAGE_INPUT.setValue(message).pressEnter();
    ELEMENT_MINI_CHAT_MINIMIZE_ICON.click();
    manageLogInOut.signIn(usernameb, password);
    chatManagement.checkMessageNotification(message);
    ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_MINI_CHAT_MESSAGE_LIST.find(byText(message));
    ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();
    info("Open Chat Tab Update");
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room, username);
    chatManagement.sendMessageInRoomOrSpace(room, message);
    switchToParentWindow();
    manageLogInOut.signIn(username, password);
    ELEMENT_CHAT_NOTIFICATION_NUMBER.waitUntil(Condition.appear, Configuration.timeout).click();
    $(byText(message)).waitUntil(Condition.appear, Configuration.timeout).click();
    ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
    $(byText(message)).should(Condition.exist);
    ELEMENT_MINI_CHAT_POPOUT_ICON.waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
    switchTo().window(1);
    $(byId("room-detail")).waitUntil(Condition.visible, Configuration.collectionsTimeout).find(byText(room)).waitUntil(Condition.appear, Configuration.openBrowserTimeoutMs);
    switchToParentWindow();
    ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchToParentWindow();
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(usernamea);
    userAndGroupManagement.deleteUser(usernameb);
    userAndGroupManagement.deleteUser(username);
    userAndGroupManagement.deleteUser(username2);
    info("Open Mini Chat From Space");
    String space = "space" + getRandomNumber();
    info("create space and invite user");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    ELEMENT_SPACE_PORTLET.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_SPACE_MENU.click();
    ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
    MiniChatName = $(byClassName("title-left")).parent().parent().find(byClassName("fullname")).getText();
    assertEquals(space, MiniChatName);
    ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);

  }

  @BugInPLF("CHAT-1004")
  public void test02_ChatWithMemberOfRoomUsingUserPopOver() {
    String room = "room" + getRandomNumber();
    String usernamea = "username" + getRandomNumber();
    String password = "123456";
    String emaila = "emaila" + getRandomNumber() + "@test.com";
    String FirstnameA = "FirstName" + getRandomString();
    String LastNameA = "LastName" + getRandomString();

    navigationToolbar.goToAddUser();
    userAddManagement.addUser(usernamea, password, emaila, FirstnameA, LastNameA);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room, usernamea);
    switchToParentWindow();
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).click();
    ELEMENT_CHAT_ROOM_PARTICIPANTS.find(byClassName("contact-list-item")).hover();
    ELEMENT_CHAT_TIP_CONTENT.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHAT_ICON_TIP_CONTENT.click();
    ELEMENT_CHAT_CONTACT.parent()
            .parent()
            .find(byText(PLFData.DATA_NAME_USER1))
            .waitUntil(Condition.appear, Configuration.timeout);
    switchToParentWindow();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchToParentWindow();
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(usernamea);
  }
}
