import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;


public class RoomManagement {
    private final TestBase testBase;
    public RoomManagement(TestBase testBase) {
        this.testBase = testBase;}

    public void addRoom(String name,int nbUsers,String... users){
        ELEMENT_CHAT_ICON_ADD_ROOM.click();
        ELEMENT_CHAT_INPUT_ROOM_NAME.setValue(name);
    for(int i=0;i<=nbUsers-1;i++){
        ELEMENT_CHAT_INPUT_ROOM_USERS.setValue(users[i]);
        ELEMENT_CHAT_RESULT_SEARCH_USER.waitUntil(Condition.appears,Configuration.timeout);
        ELEMENT_CHAT_INPUT_ROOM_USERS.pressEnter();
        }
        ELEMENT_CHAT_BUTTON_SAVE_ADD_ROOM.click();}

public void deleteRomm(String room){
    $(byText(room)).click();
    ELEMENT_CHAT_ROOM_BUTTON_DROP_DOWN.click();
    ELEMENT_CHAT_ROOM_DELETE.click();
    ELEMENT_CHAT_CONFIRM_DELETE_ROOM.click();
    $(byText(room)).shouldNot(Condition.exist);
}

}
