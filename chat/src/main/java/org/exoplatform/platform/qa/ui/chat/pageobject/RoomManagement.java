package org.exoplatform.platform.qa.ui.chat.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Button.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_CHAT_ADD_ROOM_SAVE_DW;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;

public class RoomManagement {
  private final TestBase testBase;

  public RoomManagement(TestBase testBase) {
    this.testBase = testBase;
  }

  public void addRoom(String name, String... users) {
    ELEMENT_CREATE_ROOM.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_CREATE_ROOM.click();
    ELEMENT_POPUP_ROOM.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_ROOM_NAME.waitUntil(Condition.visible, Configuration.timeout).setValue(name);
    for (int i = 0; i <= users.length - 1; i++) {
        ELEMENT_CHAT_INPUT_ROOMUSERSS.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(users[i]);
        ELEMENT_CHAT_RESULT_SEARCH_USER.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
        ELEMENT_CHAT_INPUT_ROOMUSERSS.pressEnter();
        sleep(Configuration.timeout);
    }
    ELEMENT_BUTTON_SAVE_ROOM.waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_CONTACT_LIST.find(byText(name)).should(Condition.exist);
  }

  public void addRoomTribe(String name, String... users) {
    ELEMENT_CREATE_ROOM.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_CREATE_ROOM.click();
    ELEMENT_POPUP_ROOM.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_ROOM_NAME.waitUntil(Condition.visible, Configuration.timeout).setValue(name);
    for (int i = 0; i <= users.length - 1; i++) {
      ELEMENT_CHAT_INPUT_ROOMUSERSS.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(users[i]);
      ELEMENT_CHAT_RESULT_SEARCH_USER.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
      ELEMENT_CHAT_INPUT_ROOMUSERSS.pressEnter();
      sleep(Configuration.timeout);
    }
    ELEMENT_CHAT_ADD_ROOM_SAVE_DW.waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_CONTACT_LIST.find(byText(name)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
  }

    public void deleteRomm(String room) {
      ELEMENT_CHAT_CONTACT.parent().parent().parent().parent().find(byText(room)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
      ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
      ELEMENT_DELETE_ROOM.click();
      ELEMENT_CONFIRM_BUTTON_DELETE_ROOM.click();
      ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.not(Condition.appear),Configuration.openBrowserTimeoutMs);
  }

  public void editTitleofAroom(String room, String newroom) {
    $(byText(room)).click();
    ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
    ELEMENT_CHAT_ROOM_EDIT.click();
    ELEMENT_CHAT_INPUT_ROOM_NAME.setValue(newroom);
    ELEMENT_CHAT_BUTTON_SAVE_ADD_ROOM.click();

  }
public void startStopmeeting(String room){
  $(byText(room)).click();
  ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
  ELEMENT_CHAT_ROOM_STARTSTOPMEETING.click();


  }
  public void leaveRoom(String room) {
    $(byText(room)).click();
    ELEMENT_MORE_ACTION.click();
    ELEMENT_LEAVEROOM_ACTION.waitUntil(Condition.visible,Configuration.timeout).click();
    $(byId("team-delete-window-chat-name")).has(Condition.text("Are you sure you want to leave the room '"+room+"' ?"));
    $(ELEMENT_YES_BUTTON_AUX).click();
    $(ELEMENT_YES_BUTTON_AUX).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    refresh();
    $(byText(room)).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
}
public void CancelleaveRoomFromNo(String room){

  $(byText(room)).click();
  ELEMENT_MORE_ACTION.click();
  ELEMENT_LEAVEROOM_ACTION.waitUntil(Condition.visible,Configuration.timeout).click();
  $(byId("team-delete-window-chat-name")).has(Condition.text("Are you sure you want to leave the room '"+room+"' ?"));
  $(ELEMENT_NO_BUTTON_LEAVEROOM).click();
  $(ELEMENT_NO_BUTTON_LEAVEROOM).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  $(byText(room)).waitUntil((Condition.visible),Configuration.timeout);
}
  public void CancelleaveRoom(String room){

    $(byText(room)).click();
    ELEMENT_MORE_ACTION.click();
    ELEMENT_LEAVEROOM_ACTION.waitUntil(Condition.visible,Configuration.timeout).click();
      $(byId("team-delete-window-chat-name")).shouldHave(Condition.text("Are you sure you want to leave the room \""+room+"\" ?")).waitUntil(Condition.visible,Configuration.timeout);
      $(ELEMENT_CLOSE_WINDOW_ROOM).click();
      $(ELEMENT_CLOSE_WINDOW_ROOM).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
      $(byText(room)).waitUntil((Condition.visible),Configuration.timeout);
  }
  public void ViewLeavePopup(String room){
    $(byText(room)).click();
    ELEMENT_MORE_ACTION.click();
    ELEMENT_LEAVEROOM_ACTION.waitUntil(Condition.visible,Configuration.timeout).click();
    $(byId("team-delete-window-chat-name")).has(Condition.text("Are you sure you want to leave the room '"+room+"' ?"));

  }

  public void editRoom(String room,  String... users){
    $(byText(room)).click();
    ELEMENT_MORE_ACTION.click();
    $(byXpath("//*[@id=\"room-detail\"]/div[2]/div[2]/div[3]/ul/li[5]/a")).click();
    for (int i = 0; i <= users.length - 1; i++) {
      ELEMENT_CHAT_INPUT_ROOMUSERSS.setValue(users[i]);
      ELEMENT_CHAT_RESULT_SEARCH_USER.waitUntil(Condition.be(Condition.visible),Configuration.timeout);
      ELEMENT_CHAT_RESULT_SEARCH_USER.waitUntil(Condition.visible,Configuration.timeout);
      ELEMENT_CHAT_INPUT_ROOMUSERSS.pressEnter();
    }
    ELEMENT_BUTTON_SAVE_ROOM.click();
  }

}
