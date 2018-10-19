package org.exoplatform.platform.qa.ui.chat.pageobject;

import static com.codeborne.selenide.Selectors.*;
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

  public void sendMessageAssignTaskInRoom(String room, String task, String... user){
    ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.visible,Configuration.timeout).click();
    if(user.length>0){
      ELEMENT_CHAT_MESSAGE_INPUT.setValue("++"+task+" @"+user[0]);
      $(byClassName("atwho-view")).waitUntil(Condition.visible,Configuration.timeout);
    }
    else{
      ELEMENT_CHAT_MESSAGE_INPUT.setValue("++"+task);
    }
    ELEMENT_CHAT_MESSAGE_INPUT.pressEnter();
    ELEMENT_CHAT_LIST_MSG.find(byText(task)).should(Condition.exist);
  }


  public void sendMessageTaskPriority(String room, String task, String priority){
    ELEMENT_CONTACT_LIST.find(byText(room)).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_CHAT_MESSAGE_INPUT.setValue("++"+task+" !"+priority).pressEnter();
    ELEMENT_CHAT_LIST_MSG.find(byText(task)).should(Condition.exist);

  }

  public void uploadFile(String file) {
    ELEMENT_CHAT_MEETTING_ACTIONS.click();
    ELEMENT_CHAT_MEETTING_ACTIONS_UPLOAD_FILE.waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_CHAT_POPUP_UPLOAD.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_CHAT_PROGRESS_BAR.click();
    ELEMENT_CHAT_INPUT_UPLOAD.uploadFromClasspath(file);
    ELEMENT_CHAT_MESSAGE_CONTAINER.waitUntil(Condition.have(Condition.text(file)), Configuration.timeout);
  }

  public void changeStatus(String status) {
    ELEMENT_ICON_CHAT.click();
    switch (status) {
    case "Available":
      ELEMENT_CHAT_STATUS_AVAILABLE.click();
      break;
    case "Do not disturb":
      ELEMENT_CHAT_STATUS_DONOTDISTURB.click();
      break;
    case "Away":
      ELEMENT_CHAT_STATUS_AWAY.click();
      break;
    case "Invisible":
      ELEMENT_CHAT_STATUS_INVISIBLE.click();
      break;
    }
  }

  public void assignTaskInChat(String taskName, String DueDate, String... user) {
    ELEMENT_CHAT_MEETTING_ACTIONS.click();
    $(byClassName("meeting-action-task")).waitUntil(Condition.visible, Configuration.timeout).click();
    if (taskName != null || taskName != "")
      ELEMENT_CHAT_INPUT_TASKNAME.setValue(taskName);
    if (user.length > 0) {
      for (int i = 0; i < user.length; i++) {
        ELEMENT_CHAT_INPUT_USERNAME_IN_ASSIGN_TASK.setValue(user[i]).pressEnter();
      }
    }
    if (DueDate != null || DueDate != "")
      ELEMENT_CHAT_INPUT_DUE_TASK_DATE.setValue(DueDate);
    ELEMENT_BUTTON_ADD_TASK.click();
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byLinkText(taskName)).shouldBe(Condition.visible);
  }
}
