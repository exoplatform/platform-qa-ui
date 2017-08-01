package org.exoplatform.platform.qa.ui.chat.smoke;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_LIST_MSG;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

@Tag("chat")
@Tag("smoke")
public class RoomBaseActionTestIT extends Base {
  HomePagePlatform       homePagePlatform;

  RoomManagement roomManagement;

  NavigationToolbar      navigationToolbar;

  UserAddManagement      userAddManagement;

  ManageLogInOut         manageLogInOut;

  UserAndGroupManagement userandgroupmanagement;

  ChatManagement chatManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    roomManagement = new RoomManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    userAddManagement = new UserAddManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    userandgroupmanagement = new UserAndGroupManagement(this);
    chatManagement = new ChatManagement(this);
  }

  @Test
  public void Test01_AddRoomWithSeveralUsers() {
    String usernamea = "usernamea" + getRandomString();
    String usernameb = "usernameb" + getRandomString();
    String usernamec = "usernamec" + getRandomString();
    String password = "123456";
    String room = "room" + getRandomNumber();

    String emaila = usernamea + getRandomNumber() + "@test.com";
    String emailb = usernameb + getRandomNumber() + "@test.com";
    String emailc = usernamec + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    info("Create 3 users");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    userAddManagement.addUser(usernameb, password, emailb, usernameb, usernamec);
    userAddManagement.addUser(usernamec, password, emailc, usernamec, usernamec);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    manageLogInOut.signIn(usernameb, password);
    homePagePlatform.goToChat();
    manageLogInOut.signIn(usernamec, password);
    homePagePlatform.goToChat();
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    homePagePlatform.goToChat();
    info("Add room with users");
    switchTo().window("Chat");
    roomManagement.addRoom(room, usernamea, usernameb, usernamec);

    switchTo().window("Home Page");
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    switchTo().window("Chat");
    $(byText(room)).should(Condition.exist);

    switchTo().window("Home Page");
    manageLogInOut.signIn(usernameb, password);
    homePagePlatform.goToChat();
    switchTo().window("Chat");
    $(byText(room)).should(Condition.exist);

    switchTo().window("Home Page");
    manageLogInOut.signIn(usernamec, password);
    homePagePlatform.goToChat();
    switchTo().window("Chat");
    $(byText(room)).should(Condition.exist);
    info("delete data");
    switchTo().window("Home Page");
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    homePagePlatform.goToChat();
    switchTo().window("Chat");
    roomManagement.deleteRomm(room);

    switchTo().window("Home Page");
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(usernamea);
    userandgroupmanagement.deleteUser(usernameb);
    userandgroupmanagement.deleteUser(usernamec);

  }

  @Test
  public void test02_deleteRoom() {
    String usernamea = "usernamea" + getRandomString();

    String password = "123456";
    String room = "room" + getRandomNumber();
    String emaila = usernamea + "@test.com";
    navigationToolbar.goToAddUser();
    info("Create new user");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    homePagePlatform.goToChat();
    switchTo().window("Chat");
    info("add room");
    roomManagement.addRoom(room, usernamea);
    info("delete room");
    roomManagement.deleteRomm(room);
    info("delete data");
    switchTo().window("Home Page");
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(usernamea);
  }

  @Test
  public void test03_SendMessageInAROOM() {
    String usernamea = "usernamea" + getRandomString();
    String password = "123456";
    String emaila = usernamea + "@test.com";
    String room = "room" + getRandomNumber();
    String message = "message" + getRandomNumber();
    navigationToolbar.goToAddUser();
    info("Create new user");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    homePagePlatform.goToChat();
    switchTo().window("Chat");
    info("add room");
    roomManagement.addRoom(room, usernamea);
    info("send message");
    chatManagement.sendMessageInRoomOrSpace(room, message);
    switchTo().window("Home Page");
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    $(byText(room)).click();
    info("verify message");
    ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
    switchTo().window("Home Page");
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    homePagePlatform.goToChat();
    switchTo().window("Chat");
    roomManagement.deleteRomm(room);
    switchTo().window("Home Page");
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(usernamea);
  }

  @Test
  public void test04_modifyTheTittleOfAroom() {
    String usernamea = "usernamea" + getRandomString();
    String password = "123456";
    String emaila = usernamea + "@test.com";
    String room = "room" + getRandomNumber();
    String newroom = "newroom" + getRandomNumber();
    navigationToolbar.goToAddUser();
    info("Create new user");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    homePagePlatform.goToChat();
    switchTo().window("Chat");
    info("add room");
    roomManagement.addRoom(room, usernamea);
    info("edit title");
    roomManagement.editTitleofAroom(room, newroom);
    $(byText(newroom)).should(Condition.exist);
    roomManagement.deleteRomm(newroom);
  }
}
