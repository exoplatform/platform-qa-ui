package org.exoplatform.platform.qa.ui.platform.chat;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
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

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("chat")
@Tag("sniff")

public class ChatCollaborativeActionsTestIT extends Base {

  HomePagePlatform homePagePlatform;
  ManageLogInOut manageLogInOut;
  NavigationToolbar navigationToolbar;
  RoomManagement roomManagement;
  CalendarHomePage calendarHomePage;
  EventManagement eventManagement;
  UserAddManagement userAddManagement;
  UserAndGroupManagement userAndGroupManagement;
  ChatManagement chatManagement;

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
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }


  @Test
  public void test01_addEvent_Question_ShareLink_InvalidLink_UploadFileInChatRoom() {

    String room = "room" + getRandomNumber();
    String room2 = "room" + getRandomNumber();
    String username = "username" + getRandomNumber();
    String username2 = "username" + getRandomNumber();
    String event = "event" + getRandomNumber();
    String location = "location" + getRandomNumber();
    String password = "Aa123456";
    String FirstName = "FirstName" + getRandomString();
    String LastName = "LastName" + getRandomString();
    String email = username + "@test.com";
    String Question = "Question" + getRandomString();
    String Comment = "comment" + getRandomString();
    String FirstName2 = "FirstName" + getRandomString();
    String LastName2 = "LastName" + getRandomString();
    String email2 = username2 + "@test.com";

    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room);
    info("Check Ask Question PopUp");
    chatManagement.checkMenuCollaborativeAction();
    ELEMENT_CHAT_ASK_QUESTION.waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
    chatManagement.checkAskQuestionPopUp();
    info("Check Raise Hand PopUp");
    ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CHAT_RAISE_HAND.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    chatManagement.checkRaiseHandPopUp();
    info("Check Upload File PopUp");
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_UPLOAD_FILE.click();
    chatManagement.checkUploadFilePopUp();
    info("Add Invalid Link In Chat");
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_SHARE_LINK.click();
    ELEMENT_POPUP_CONTAINER.find(byXpath("//input[@placeholder='E.g: http://www.exoplatform.com']")).sendKeys("link");
    ELEMENT_CHAT_SHARE_LINK_BUTTON.click();
    $(byText("The link has an incorrect format. Please enter a valid URL.")).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHAT_CANCEL_BUTTON.click();
    info("Check Share Link PopUp");
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_SHARE_LINK.click();
    chatManagement.checkShareLinPopUp();
    info("Check Add Event PopUp");
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_ADD_EVENT.click();
    chatManagement.checkAddEventPopUp();
    roomManagement.deleteRomm(room);
    switchTo().window(0);
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username, password, email, FirstName, LastName);
    userAddManagement.addUser(username2, password, email2, FirstName2, LastName2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    sleep(1000);
    roomManagement.addRoom(room, username);
    chatManagement.shareLinkInChat("https://www.google.fr/");
    switchToParentWindow();
    manageLogInOut.signOut();
    manageLogInOut.signInCas(username, password);
    sleep(2000);
    ELEMENT_CHAT_ICON_STATUS.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(3000);
    ELEMENT_CHAT_NOTIFICATION.find(byClassName("uiIconChatLink")).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    ELEMENT_CHAT_NOTIFICATION.find(byLinkText("https://www.google.fr/")).should(Condition.appears);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    info("Add Share Link In Chat");
    ELEMENT_CONTACT_LIST.find(byText(room)).click();
    chatManagement.addEventInChat(event, location);
    switchToParentWindow();
    manageLogInOut.signOut();
    manageLogInOut.signInCas(username, password);
    sleep(2000);
    ELEMENT_CHAT_ICON_STATUS.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CHAT_NOTIFICATION.find(byText(event)).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    ELEMENT_CHAT_NOTIFICATION.find(byClassName("uiIconChatCreateEvent")).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    homePagePlatform.goToCalendarPage();
    $(byText(event)).waitUntil(Condition.appear, Configuration.openBrowserTimeoutMs);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    info("Raise Hand In Chat");
    chatManagement.raiseHandInChat(Comment);
    switchToParentWindow();
    manageLogInOut.signOut();
    manageLogInOut.signInCas(username, password);
    sleep(2000);
    ELEMENT_CHAT_ICON_STATUS.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CHAT_NOTIFICATION.find(byClassName("uiIconChatRaiseHand")).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    ELEMENT_CHAT_NOTIFICATION.find(byText(Comment)).should(Condition.appears);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    info("Upload File In Chat");
    chatManagement.uploadFile("eXo-Platform.png");
    sleep(2000);
    switchToParentWindow();
    manageLogInOut.signOut();
    manageLogInOut.signInCas(username, password);
    sleep(2000);
    ELEMENT_CHAT_ICON_STATUS.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    sleep(3000);
    ELEMENT_CHAT_NOTIFICATION.find(byClassName("uiIconChatUpload")).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room2, username2);
    info("Add Question In Chat");
    chatManagement.addQuestionInChat(Question);
    switchToParentWindow();
    manageLogInOut.signOut();
    manageLogInOut.signInCas(username2, password);
    sleep(2000);
    ELEMENT_CHAT_ICON_STATUS.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    sleep(3000);
    ELEMENT_CHAT_NOTIFICATION.find(byClassName("uiIconChatQuestion")).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    ELEMENT_CHAT_NOTIFICATION.find(byText(Question + "?")).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    roomManagement.deleteRomm(room2);
    switchToParentWindow();
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(username);
    userAndGroupManagement.deleteUser(username2);

  }

}
