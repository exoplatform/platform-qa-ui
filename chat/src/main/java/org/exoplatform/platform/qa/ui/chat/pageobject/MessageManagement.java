package org.exoplatform.platform.qa.ui.chat.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
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
}
