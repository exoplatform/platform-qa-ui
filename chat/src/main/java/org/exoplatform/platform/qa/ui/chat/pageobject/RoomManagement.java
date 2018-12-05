package org.exoplatform.platform.qa.ui.chat.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.core.PLFData;
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
    ELEMENT_ROOM_NAME.setValue(name);
    for (int i = 0; i <= users.length - 1; i++) {
      ELEMENT_CHAT_INPUT_ROOM_USERS.setValue(users[i]);
      ELEMENT_CHAT_RESULT_SEARCH_USER.waitUntil(Condition.be(Condition.visible),Configuration.timeout);
      ELEMENT_CHAT_RESULT_SEARCH_USER.waitUntil(Condition.visible,Configuration.timeout);
      ELEMENT_CHAT_INPUT_ROOM_USERS.pressEnter();
    }
    ELEMENT_BUTTON_SAVE_ROOM.click();
    ELEMENT_CONTACT_LIST.find(byText(name)).should(Condition.exist);
  }

    public void deleteRomm(String room) {
      ELEMENT_CHAT_CONTACT.parent().parent().parent().parent().find(byText(room)).click();
      ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
      ELEMENT_DELETE_ROOM.click();
      ELEMENT_CONFIRM_BUTTON_DELETE_ROOM.click();
      ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.not(Condition.appear),Configuration.timeout);
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

public void notificationRoomSettings(String room, String value, String username){

  ELEMENT_CONTACT_LIST.find(byText(room)).click();
  ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
  ELEMENT_CHAT_ROOM_NOTIFICATION.click();
  ELEMENT_CHAT_ROOM_NOTIFICATION_POPUP.waitUntil(Condition.appear, Configuration.timeout);
  switch (value){
    case "Silence":
    ELEMENT_CHAT_SILENCE_ROOM_NOTIFICATION.selectRadio("silence");
      ELEMENT_NOTIFICATION_CONFIRM_BUTTON.click();
      break;
    case "AlertOn":
      ELEMENT_CHAT_ALERT_ON.selectRadio("keywords");
      ELEMENT_CHAT_NOTIFICATION_KEYWORD.setValue(username);
      ELEMENT_NOTIFICATION_CONFIRM_BUTTON.click();
      break;
  }
  }

  public void checkNotificationRoomSettings(String room, String value){
    ELEMENT_CONTACT_LIST.find(byText(room)).click();
    ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
    ELEMENT_CHAT_ROOM_NOTIFICATION.click();
    ELEMENT_CHAT_ROOM_NOTIFICATION_POPUP.waitUntil(Condition.appear, Configuration.timeout);
    $(byValue(value)).shouldBe(Condition.selected);
    ELEMENT_CHAT_ICON_CLOSE_ROOM_NOTIFICATION.click();
  }

  public void checkNotificationRoomSettingsPage(String room){
    ELEMENT_CONTACT_LIST.find(byText(room)).click();
    ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
    ELEMENT_CHAT_ROOM_NOTIFICATION.click();
    ELEMENT_CHAT_ROOM_NOTIFICATION_POPUP.waitUntil(Condition.appear, Configuration.timeout);
    assertEquals(room +" Notifications",$(byXpath("//*[@id=\"room-detail\"]/div[3]/div/div[1]/span")).getText());
    $(byValue("normal")).shouldBe(Condition.selected);
    $(byValue("silence")).shouldNotBe(Condition.selected);
    $(byValue("keywords")).shouldNotBe(Condition.selected);
    assertEquals("",ELEMENT_CHAT_NOTIFICATION_KEYWORD.getText());
    ELEMENT_NOTIFICATION_CONFIRM_BUTTON.shouldBe(Condition.visible);
    ELEMENT_CHAT_CANCEL_BUTTON_ROOM_NOTIFICATION.shouldBe(Condition.visible);
    ELEMENT_CHAT_ICON_CLOSE_ROOM_NOTIFICATION.shouldBe(Condition.visible).click();
  }
}
