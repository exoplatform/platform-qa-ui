package org.exoplatform.platform.qa.ui.chat.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_LIST_MSG;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_MESSAGE_INPUT;

import com.codeborne.selenide.Condition;

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
}
