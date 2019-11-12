package org.exoplatform.platform.qa.ui.chat.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static junit.framework.TestCase.assertEquals;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_COLLABORATION_ACTIONS;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.core.PLFData;
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
    ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_CHAT_UPLOAD_FILE.waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(2000);
    ELEMENT_CHAT_INPUT_UPLOAD.uploadFromClasspath(file);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(file)).waitUntil(Condition.appear,Configuration.timeout);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byClassName("uiIconChatUpload")).waitUntil(Condition.appear, Configuration.timeout);
  }

  public void changeStatus(String status) {
    ELEMENT_CHAT_ICON_STATUS.click();
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

  public void assignTaskInChat(String taskName, String... user) {

    ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible,Configuration.timeout).click();
    $(byXpath("//div[@class='apps-item-icon']/i[@class='uiIconChatCreateTask']")).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_CHAT_TASK_NAME).setValue(taskName);
    for (int i = 0; i <= user.length - 1; i++) {
      ELEMENT_CHAT_ASSIGNEE_TASK.setValue(user[i]);
      sleep(Configuration.collectionsTimeout);
      ELEMENT_CHAT_RESULT_SEARCH_ASSIGNEE.waitUntil(Condition.visible, Configuration.timeout);
      ELEMENT_CHAT_ASSIGNEE_TASK.waitUntil(Condition.visible,Configuration.timeout).pressEnter();
    }
    ELEMENT_CHAT_DUE_DATE_TASK.waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_CHAT_CURRENT_DATE_TASK.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CHAT_POST_TASK_BUTTON.waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_CONTAINER_LIST_MESSAGES.waitUntil(Condition.visible,Configuration.timeout).find(byLinkText(taskName)).waitUntil(Condition.visible,Configuration.timeout).shouldBe(Condition.visible);
  }

  public void addEventInChat(String event, String location){

    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_ADD_EVENT.click();
    ELEMENT_ADD_EVENT_WINDOW.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_ADD_EVENT.setValue(event);
    ELEMENT_CHAT_EVENT_FROM_DATE_.click();
    $(byClassName("today")).click();
    ELEMENT_CHAT_EVENT_TO_DATE_.click();
    $(byClassName("today")).click();
    ELEMENT_CHAT_EVENT_LOCATION.setValue(location);
    ELEMENT_CHAT_POST_EVENT.click();
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(event)).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byClassName("uiIconChatCreateEvent")).waitUntil(Condition.appear, Configuration.timeout);
  }

  public void checkMenuCollaborativeAction(){

    ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CHAT_COLLABORATION_MENU.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHAT_ADD_EVENT.should(Condition.visible);
    ELEMENT_CHAT_SHARE_LINK.should(Condition.visible);
    ELEMENT_CHAT_UPLOAD_FILE.should(Condition.visible);
    ELEMENT_CHAT_ASK_QUESTION.should(Condition.visible);
    ELEMENT_CHAT_RAISE_HAND.should(Condition.visible);
    ELEMENT_CHAT_CREATE_TASK.should(Condition.visible);
  }

  public void checkAskQuestionPopUp(){
    ELEMENT_POPUP_CONTAINER.find(byText("Ask a Question")).waitUntil(Condition.appear,Configuration.timeout);
    ELEMENT_POPUP_CONTAINER.find(byXpath("//input[@placeholder='What is your question?']")).waitUntil(Condition.appear, Configuration.timeout);
    assertEquals("Ask",ELEMENT_CHAT_ASK_BUTTON.getText());
    assertEquals("Cancel", ELEMENT_CHAT_CANCEL_BUTTON.getText());
    ELEMENT_CHAT_CANCEL_BUTTON.click();
    ELEMENT_POPUP_CONTAINER.find(byText("Ask a Question")).shouldNot(Condition.appear);
  }

  public void uploadFileChatUser(String file) {
    ELEMENT_COLLABORATION_ACTIONS.waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_CHAT_UPLOAD_FILE.waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(2000);
    ELEMENT_CHAT_INPUT_UPLOAD.uploadFromClasspath(file);
    $(byXpath("//div[@class='message-content']/b/a[text()='${file}']".replace("${file}",file))).exists();
    $(byXpath("//div[@class='message-content']/b/a[text()='${file}']/following::i[@class='uiIconChatUpload']".replace("${file}",file))).exists();
  }

  public void openMiniChat(String userChat, String userChatName) {
    if (!$(byText(userChatName)).exists())
      ELEMENT_CHAT_BUTTON_HIDE_OFF_LINE.click();
    sleep(Configuration.timeout);
    if ($(byXpath("(//div[@class='chat-contact']/div[contains(@style,'${userChat}')])[2]".replace("${userChat}",userChat))).exists())
    {
      $(byXpath("(//div[@class='chat-contact']/div[contains(@style,'${userChat}')])[2]".replace("${userChat}", userChat))).click();
    }
    else {
      $(byXpath("//div[@class='chat-contact']/div[contains(@style,'${userChat}')]".replace("${userChat}", userChat))).click();
    }
    $(byXpath("//td[@id='profileName']/a[text()='${userChatName}']".replace("${userChatName}",userChatName))).click();
    $(byXpath("(//span[@class='chat-label-status'])[2]")).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(2000);
    ELEMENT_MINI_CHAT_POPOUT_ICON.click();
    refresh();  }

  public void addQuestionInChat(String question){
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_ASK_QUESTION.click();
    ELEMENT_POPUP_CONTAINER.find(byXpath("//input[@placeholder='What is your question?']")).sendKeys(question+"?");
    ELEMENT_CHAT_ASK_BUTTON.click();
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(question+"?")).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byClassName("uiIconChatQuestion")).waitUntil(Condition.appear, Configuration.timeout);
  }


  public void checkAddEventPopUp(){
    ELEMENT_POPUP_CONTAINER.find(byText("Add Event")).waitUntil(Condition.appear,Configuration.timeout);
    ELEMENT_POPUP_CONTAINER.find(byXpath("//input[@placeholder='Event Title']")).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(0)
            .shouldHave(Condition.text("From"))
            .find(byXpath("//input[@placeholder='mm/dd/yyyy']")).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(0)
            .find(byXpath("//input[@placeholder='mm/dd/yyyy']")).click();
    ELEMENT_CHAT_EVENT_CALENDAR.should(Condition.appear);
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(0)
            .find(byXpath("//input[@placeholder='mm/dd/yyyy']")).click();
    $(byClassName("chat-app-event")).click();
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(1)
            .shouldHave(Condition.text("To"))
            .find(byXpath("//input[@placeholder='mm/dd/yyyy']")).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(1)
            .find(byXpath("//input[@placeholder='mm/dd/yyyy']")).click();
    ELEMENT_CHAT_EVENT_CALENDAR.should(Condition.appear);
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(1)
            .find(byXpath("//input[@placeholder='mm/dd/yyyy']")).click();
    ELEMENT_POPUP_CONTAINER.find(byXpath("//input[@placeholder='Location']")).waitUntil(Condition.appear, Configuration.timeout);
    assertEquals("All Day",$("select[name=startTime]").getText());
    assertEquals("All Day",$("select[name=endTime]").getText());
    assertEquals("Post",ELEMENT_CHAT_POST_EVENT.getText());
    assertEquals("Cancel", ELEMENT_CHAT_CANCEL_BUTTON.getText());
    ELEMENT_CHAT_CANCEL_BUTTON.click();
    ELEMENT_POPUP_CONTAINER.find(byText("Add Event")).shouldNot(Condition.appear);
  }




  public void checkRaiseHandPopUp(){
    ELEMENT_POPUP_CONTAINER.find(byText("Raise Hand")).waitUntil(Condition.appear,Configuration.timeout);
    ELEMENT_POPUP_CONTAINER.find(byXpath("//input[@placeholder='Optional comment']")).waitUntil(Condition.appear, Configuration.timeout);
    assertEquals("Raise your hand",ELEMENT_RAISE_HAND_BUTTON.getText());
    assertEquals("Cancel", ELEMENT_CHAT_CANCEL_BUTTON.getText());
    ELEMENT_CHAT_CANCEL_BUTTON.click();
    ELEMENT_POPUP_CONTAINER.find(byText("Raise Hand")).shouldNot(Condition.appear);
  }

  public void raiseHandInChat(String comment){
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_RAISE_HAND.click();
    ELEMENT_POPUP_CONTAINER.find(byXpath("//input[@placeholder='Optional comment']")).sendKeys(comment);
    ELEMENT_RAISE_HAND_BUTTON.click();
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(comment)).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byClassName("uiIconChatRaiseHand")).waitUntil(Condition.appear, Configuration.timeout);
  }

  public void checkUploadFilePopUp(){
    ELEMENT_POPUP_CONTAINER.find(byText("Upload File")).waitUntil(Condition.appear,Configuration.timeout);
    assertEquals("DROP YOUR FILE HERE",$(byClassName(" label-inner")).getText());
    assertEquals("Select Manually",ELEMENT_CHAT_SELECT_FILE.getText());
    ELEMENT_CHAT_CANCEL_UPLOAD_FILE_BUTTON.click();
    ELEMENT_POPUP_CONTAINER.find(byText("Upload File")).shouldNot(Condition.appear);
  }

  public void goToChatUser(String userChat){
    if (!$(byText(PLFData.DATA_NAME_USER2)).exists())
      ELEMENT_CHAT_BUTTON_HIDE_OFF_LINE.click();
    sleep(Configuration.timeout);
    $(byXpath("//div[@class='chat-contact']/div[contains(@style,'${userChat}')]".replace("${userChat}",userChat))).click();
    if ($(byXpath("//i[@class='uiIconBannerChat uiIconLightGray']")).exists())
      $(byXpath("//i[@class='uiIconBannerChat uiIconLightGray']")).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(Configuration.timeout);
    refresh();
  }

  public void checkShareLinPopUp(){
    ELEMENT_POPUP_CONTAINER.find(byText("Share Link")).waitUntil(Condition.appear,Configuration.timeout);
    ELEMENT_POPUP_CONTAINER.find(byXpath("//input[@placeholder='E.g: http://www.exoplatform.com']")).waitUntil(Condition.appear, Configuration.timeout);
    assertEquals("Share",ELEMENT_CHAT_SHARE_LINK_BUTTON.getText());
    assertEquals("Cancel", ELEMENT_CHAT_CANCEL_BUTTON.getText());
    ELEMENT_CHAT_CANCEL_BUTTON.click();
    ELEMENT_POPUP_CONTAINER.find(byText("Share Link")).shouldNot(Condition.appear);
  }

  public void shareLinkInChat(String link){
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_SHARE_LINK.click();
    ELEMENT_POPUP_CONTAINER.find(byXpath("//input[@placeholder='E.g: http://www.exoplatform.com']")).sendKeys(link);
    ELEMENT_CHAT_SHARE_LINK_BUTTON.click();
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(link)).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byClassName("uiIconChatLink")).waitUntil(Condition.appear, Configuration.timeout);
  }

  public void checkPopUpAssignTask() {
    ELEMENT_ASSIGN_TASK_WINDOW.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_ASSIGN_TASK_CONTAINER.find(byXpath("//input[@placeholder='Task Title']")).waitUntil(Condition.appear, Configuration.timeout);
    assertEquals(ELEMENT_CHAT_ASSIGNEE_TASK.getAttribute("placeholder"),"Assignee");
    ELEMENT_ASSIGN_TASK_CONTAINER.find(byXpath("//input[@placeholder='Due date']")).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHAT_POST_TASK_BUTTON.should(Condition.appears);
    info("check that cancel button works");
    ELEMENT_CHAT_CANCEL_TASK_BUTTON.should(Condition.appears).click();
    ELEMENT_ASSIGN_TASK_WINDOW.shouldNot(Condition.appears);
    info("check the close button");
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_CREATE_TASK.click();
    ELEMENT_CHAT_CLOSE_ICON.should(Condition.appears).click();
    ELEMENT_ASSIGN_TASK_WINDOW.shouldNot(Condition.appears);
  }
  public void CreateTask(String task, String... users) {
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_CREATE_TASK.click();
    $(ELEMENT_CHAT_TASK_NAME).setValue(task);
    for (int i = 0; i <= users.length - 1; i++) {
      ELEMENT_CHAT_ASSIGNEE_TASK.setValue(users[i]);
      ELEMENT_CHAT_RESULT_SEARCH_ASSIGNEE.waitUntil(Condition.visible, Configuration.collectionsTimeout);
      ELEMENT_CHAT_ASSIGNEE_TASK.waitUntil(Condition.visible, Configuration.timeout).pressEnter();
    }
      ELEMENT_CHAT_DUE_DATE_TASK.waitUntil(Condition.visible,Configuration.timeout).click();
      ELEMENT_CHAT_CURRENT_DATE_TASK.waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
      ELEMENT_CHAT_POST_TASK_BUTTON.waitUntil(Condition.visible,Configuration.timeout).click();
      ELEMENT_CONTAINER_LIST_MESSAGES.find(byLinkText(task)).shouldBe(Condition.visible);
    }
    public void sendSmile(String Emoticon){
    ELEMENT_CHAT_EMOTICON.click();
    switch (Emoticon) {
      case "smile":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-smile")).click();
        break;
      case "wink":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-wink")).click();
        break;
      case "speechless":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-speechless")).click();
        break;
      case "surprise":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-surprise")).click();
        break;
      case "smile-tongue":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-smile-tongue")).click();
        break;
      case "emoticon-flaugh":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-flaugh")).click();
        break;
      case "cool":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-cool")).click();
        break;
      case "crying":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-crying")).click();
        break;
      case "beer":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-beer")).click();
        break;
      case "bug":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName(" emoticon-bug")).click();
        break;
      case "cake":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-cake")).click();
        break;
      case "coffee":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-coffee")).click();
        break;
      case "star":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName(" emoticon-star")).click();
        break;
      case "heart":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-heart")).click();
        break;
      case "raise-up":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName("emoticon-raise-up")).click();
        break;
      case "raise-down":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName(" emoticon-raise-down")).click();
        break;
      case "devil":
        ELEMENT_CHAT_COMPOSER_EMOTICON.find(byClassName(" emoticon-devil")).click();
        break;
    }
    ELEMENT_CHAT_MESSAGE_INPUT.pressEnter();
    ELEMENT_CHAT_LIST_MSG.find(byClassName("emoticon-"+Emoticon)).waitUntil(Condition.appear,Configuration.timeout);
  }

  public void checkMessageNotification(String message){
    ELEMENT_CHAT_NOTIFICATION_NUMBER.waitUntil(Condition.appear, Configuration.timeout).click();
    $(byText(message)).waitUntil(Condition.appear, Configuration.timeout).click();
  }

  public void changeChatSettings(String Notification){
    ELEMENT_CHAT_SETTING_NOTIFICATION.click();
    switch (Notification) {
      case "DoNotDisturbNotication":
        ELEMENT_CHAT_DO_NOT_DISTURB_BUTTON_NOTIFICATION.parent().click();
        break;
      case "DesktopNotification":
        sleep(Configuration.timeout);
        ELEMENT_CHAT_DESKTOP_NOTIFICATION_BUTTON_.parent().waitUntil(Condition.visible,Configuration.timeout).click();
        break;
      case "BipNotification":
        ELEMENT_CHAT_BIP_NOTIFICATION_BUTTON.parent().click();
        break;
      case "OnSiteNotification":
        ELEMENT_CHAT_ON_SITE_NOTIFICATION_BUTTON.parent().click();
        break;
    }
    ELEMENT_CHAT_CONFIRM_BUTTON_NOTIFICATION.click();

  }

}
