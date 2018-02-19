package org.exoplatform.platform.qa.ui.chat.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;

public class ChatManagement {
  private final TestBase testBase;

  public ChatManagement(TestBase testBase) {
    this.testBase = testBase;
  }

  public void sendMessageInRoomOrSpace(String room, String message) {
    $(byText(room)).click();
    ELEMENT_CHAT_MESSAGE_INPUT.setValue(message).pressEnter();
    ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
  }

  public void uploadFile(String file) {
    ELEMENT_CHAT_MEETTING_ACTIONS.click();
    ELEMENT_CHAT_MEETTING_ACTIONS_UPLOAD_FILE.waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_CHAT_POPUP_UPLOAD.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_CHAT_PROGRESS_BAR.click();
    ELEMENT_CHAT_INPUT_UPLOAD.uploadFromClasspath(file);
    ELEMENT_CHAT_MESSAGE_CONTAINER.waitUntil(Condition.have(Condition.text(file)), Configuration.timeout);
  }
}
