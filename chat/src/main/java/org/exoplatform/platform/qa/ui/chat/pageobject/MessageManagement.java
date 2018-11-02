package org.exoplatform.platform.qa.ui.chat.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static junit.framework.TestCase.assertEquals;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;

public class MessageManagement {
    private final TestBase testBase;

    public MessageManagement(TestBase testBase) {
        this.testBase = testBase;
    }

    public void checkMessageMenu(String message){

        ELEMENT_CHAT_LIST_MSG.find(byText(message)).hover();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("uiIconDots")).click();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("dropdown-menu ")).waitUntil(Condition.appear,Configuration.timeout);
        ELEMENT_CHAT_LIST_MSG.find(byClassName("dropdown-menu ")).find(byText("Edit message")).should(Condition.exist);
        ELEMENT_CHAT_LIST_MSG.find(byClassName("dropdown-menu ")).find(byText("Delete")).should(Condition.exist);
        ELEMENT_CHAT_LIST_MSG.find(byClassName("dropdown-menu ")).find(byText("Quote")).should(Condition.exist);
        ELEMENT_CHAT_LIST_MSG.find(byClassName("dropdown-menu ")).find(byText("Save notes")).should(Condition.exist);
    }

    public void editMessage(String message,String newmessage){
        ELEMENT_CHAT_LIST_MSG.find(byText(message)).hover();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("uiIconDots")).click();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("dropdown-menu ")).find(byText("Edit message")).click();
        ELEMENT_CHAT_EDIT_MESSAGE .waitUntil(Condition.appear,Configuration.timeout);
        assertEquals(message, ELEMENT_CHAT_MESSAGE_VALUE.getValue());
        ELEMENT_CHAT_MESSAGE_VALUE.setValue(newmessage);
        ELEMENT_CHAT_SAVE_EDIT_MESSAGE.click();
        ELEMENT_CHAT_LIST_MSG.find(byText(newmessage)).waitUntil(Condition.appear,Configuration.timeout);
        ELEMENT_CHAT_LIST_MSG.find(byClassName("uiIconChatEdit")).should(Condition.appear);
    }

    public void deleteMessage(String message){
        ELEMENT_CHAT_LIST_MSG.find(byText(message)).hover();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("uiIconDots")).click();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("dropdown-menu ")).find(byText("Delete")).click();
        $$(byId("team-delete-button-ok")).get(2).waitUntil(Condition.appear,Configuration.timeout).click();
        $(byText(message)).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    }

    public void quoteMessage(String message, String username){
        ELEMENT_CHAT_LIST_MSG.find(byText(message)).hover();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("uiIconDots")).click();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("dropdown-menu ")).find(byText("Quote")).click();
        ELEMENT_CHAT_MESSAGE_INPUT.shouldHave(Condition.value("[quote="+username+"] "+message+" [/quote]")).pressEnter();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("quote-user-name")).shouldHave(Condition.text(username+":"));
        ELEMENT_CHAT_LIST_MSG.find(byClassName("message-content")).parent().parent().parent().parent().find(byText(message)).should(Condition.exist);
    }

    public void saveNotes(String message, String username){

        ELEMENT_CHAT_LIST_MSG.find(byText(message)).hover();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("uiIconDots")).click();
        ELEMENT_CHAT_LIST_MSG.find(byClassName("dropdown-menu ")).find(byText("Save notes")).click();
        ELEMENT_CHAT_LIST_MSG.find(byText("The notes have been saved")).waitUntil(Condition.appear,Configuration.timeout);
        ELEMENT_CHAT_LIST_MSG.find(byLinkText("Save notes")).waitUntil(Condition.appear,Configuration.timeout);
        ELEMENT_CHAT_LIST_MSG.find(byLinkText("Save as wiki")).waitUntil(Condition.appear,Configuration.timeout);
        $$(byClassName("custom-message-item")).get(0).find(byClassName("uiIconChatSendEmail")).should(Condition.exist);
        $$(byClassName("custom-message-item")).get(1).find(byClassName("uiIconChatWiki")).should(Condition.exist);
        ELEMENT_CHAT_LIST_MSG.find(byLinkText("Save notes")).click();
        ELEMENT_CHAT_MEETING_NOTE_SENT.waitUntil(Condition.text("Sent!"),Configuration.timeout);
        ELEMENT_CHAT_MEETING_NOTE_SENT.parent().parent().parent().find(byText("Check your mailbox.")).should(Condition.exist);
        ELEMENT_CHAT_LIST_MSG.find(byLinkText("Save as wiki")).click();
        ELEMENT_CHAT_MEETING_NOTE_SAVED.waitUntil(Condition.text("Saved!"),Configuration.timeout);
        ELEMENT_CHAT_MEETING_NOTE_SAVED.parent().parent().parent().find(byLinkText("Open Wiki application")).should(Condition.exist);
        ELEMENT_CHAT_MEETING_NOTE_SAVED.parent().parent().parent().find(byLinkText("Open Wiki application")).click();
        switchTo().window(2);
        ELEMENT_WIKI_CONTAINER.waitUntil(Condition.appear,Configuration.timeout);
        ELEMENT_WIKI_DISCUSSION_CONTAINER.should(Condition.exist);
        assertEquals(username,ELEMENT_WIKI_ATTENDEE_NAME.getText());
        assertEquals(message,ELEMENT_WIKI_ATTENDEE_MESSAGE.getText());
    }
}
