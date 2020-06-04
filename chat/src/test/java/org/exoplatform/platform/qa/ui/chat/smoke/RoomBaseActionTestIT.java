package org.exoplatform.platform.qa.ui.chat.smoke;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
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

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_LIST_MSG;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CONTACT_LIST;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("chat")
@Tag("smoke")
public class RoomBaseActionTestIT extends Base {

  HomePagePlatform homePagePlatform;
  RoomManagement roomManagement;
  NavigationToolbar navigationToolbar;
  UserAddManagement userAddManagement;
  ManageLogInOut manageLogInOut;
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
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
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
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    info("Add room with users");
    switchTo().window(1);
    roomManagement.addRoom(room, usernamea, usernameb, usernamec);
    switchTo().window(0);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
    switchTo().window(0);
    manageLogInOut.signIn(usernameb, password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
    switchTo().window(0);
    manageLogInOut.signIn(usernamec, password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(room)).should(Condition.exist);
    info("delete data");
    switchTo().window(0);
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchTo().window(0);
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(usernamea);
    userandgroupmanagement.deleteUser(usernameb);
    userandgroupmanagement.deleteUser(usernamec);
  }

  @Test
  public void test02_SendMessageInAROOM() {

    String newroom = "newroom" + getRandomNumber();
    String usernamea = "usernamea" + getRandomString();
    String password = "123456";
    String emaila = usernamea + "@test.com";
    String room = "room" + getRandomNumber();
    String message = "message" + getRandomNumber();
    navigationToolbar.goToAddUser();
    info("Create new user");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    switchToParentWindow();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    info("add room");
    roomManagement.addRoom(room, usernamea);
    info("edit title");
    roomManagement.editTitleofAroom(room, newroom);
    ELEMENT_CONTACT_LIST.$(byText(newroom)).should(Condition.exist);
    info("send message");
    chatManagement.sendMessageInRoomOrSpace(newroom, message);
    switchTo().window(0);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    ELEMENT_CONTACT_LIST.find(byText(newroom)).should(Condition.exist);
    info("verify message");
    ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);

    switchToParentWindow();
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.deleteRomm(newroom);
    switchTo().window(0);
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(usernamea);

  }

}
