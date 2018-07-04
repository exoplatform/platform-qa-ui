package org.exoplatform.platform.qa.ui.platform.chat.Functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.NavigationManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("chat")
@Tag("sniff")
public class ChatAssignTaskPopoverTestIT extends Base {

    HomePagePlatform homePagePlatform;
    ManageLogInOut manageLogInOut;
    UserAndGroupManagement userandgroupmanagement;
    UserAddManagement      userAddManagement;
    NavigationToolbar      navigationToolbar;
    RoomManagement         roomManagement;




    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        userAddManagement = new UserAddManagement(this);
        userandgroupmanagement = new UserAndGroupManagement(this);
        navigationToolbar=new NavigationToolbar(this);
        roomManagement= new RoomManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2 );
    }

     @Test
     public void test_AssignTaskPopover(){
         homePagePlatform.goToChat();
         switchTo().window(1);
         $(byClassName("uiIconSimplePlus")).waitUntil(Condition.appears, Configuration.timeout);
         $(byClassName("uiIconSimplePlus")).click();
         $(byClassName("PopupContent")).waitUntil(Condition.appear, Configuration.timeout);
         $(byXpath("//*[@id=\"chat-application\"]/div[1]/div[2]/div[4]/div/div[2]/div[1]/input[1]")).setValue("room1");
         $(byClassName("selectize-input")).find(by("type", "text")).sendKeys("root");
         $(byClassName("btn-primary")).click();
         $(byId("chat-users")).find(byText("room1")).click();
         $(byClassName("uiIconPlusCircled")).click();
         $(byClassName("uiIconChatCreateTask")).click();
     }

}
