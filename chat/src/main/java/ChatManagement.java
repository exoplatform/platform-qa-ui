import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.selenium.TestBase;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;

public class ChatManagement {
    private final TestBase testBase;
    public ChatManagement(TestBase testBase) {
        this.testBase = testBase;}

        public void sendMessageInRoomOrSpace(String room,String message){
            $(byText(room)).click();
            ELEMENT_CHAT_MESSAGE_INPUT.setValue(message).pressEnter();
            ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
        }
}
