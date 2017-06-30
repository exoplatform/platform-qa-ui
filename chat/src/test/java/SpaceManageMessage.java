import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_INPUT_SEARCH_USER;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_LIST_MSG;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_ICON_ACCEPT_SPACE_REQUEST_IN_MEMBERS_TAB;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
@Tag("smoke")
public class SpaceManageMessage extends Base{
    HomePagePlatform homePagePlatform;
    NavigationToolbar navigationToolbar;
    UserAddManagement userAddManagement;
    ManageLogInOut manageLogInOut;
    UserAndGroupManagement userandgroupmanagement;
    ChatManagement chatManagement;
    SpaceManagement spaceManagement;
    SpaceHomePage spaceHomePage;
    SpaceSettingManagement spaceSettingManagement;
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform=new HomePagePlatform(this);
        navigationToolbar=new NavigationToolbar(this);
        userAddManagement=new UserAddManagement(this);
        manageLogInOut=new ManageLogInOut(this);
        userandgroupmanagement=new UserAndGroupManagement(this);
        chatManagement=new ChatManagement(this);
        spaceManagement=new SpaceManagement(this);
        spaceHomePage=new SpaceHomePage(this);
        spaceSettingManagement=new SpaceSettingManagement(this);
    }
    @Test
    public void test01_SendMessageOnSpaceChat(){
        String space="space"+getRandomNumber();
        String usernamea = "usernamea" + getRandomString();
        String password = "123456" ;
        String emaila = usernamea + "@gmail.com";
        String message="message"+getRandomNumber();
        info("Create new user");
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
        info("Create a space");
        homePagePlatform.goToHomePage();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        info("connect with user ");
        manageLogInOut.signIn(usernamea, password);
        homePagePlatform.goToAllSpace();
        spaceManagement.goToAllSpacesTab();
        info("send request");
        spaceManagement.sendARequestToASpace(space);
        homePagePlatform.goToHomePage();
        manageLogInOut.signIn("root", "gtn");
        homePagePlatform.goToSpecificSpace(space);
        spaceHomePage.goToSpaceSettingTab();
        info("accept request by user 1");
        spaceSettingManagement.goToMemberTabInSpaceSettingTab();
        $(byText(usernamea + " " + usernamea)).parent().find(ELEMENT_ICON_ACCEPT_SPACE_REQUEST_IN_MEMBERS_TAB).click();
        homePagePlatform.goToHomePage();
        homePagePlatform.goToChat();
        switchTo().window("Chat");
        chatManagement.sendMessageInRoomOrSpace(space,message);
        switchTo().window("Home Page");
        manageLogInOut.signIn(usernamea,password);
        homePagePlatform.goToChat();
        switchTo().window("Chat");
        $(byText(space)).click();
        ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
        switchTo().window("Home Page");
        manageLogInOut.signIn("root","gtn");
        homePagePlatform.goToAllSpace();
        spaceManagement.deleteSpace(space,false);
        navigationToolbar.goToManageCommunity();
        userandgroupmanagement.deleteUser(usernamea);
    }
@Test
    public void test02_checkSpaceChatDeletedAfterDelete(){
        String space="space"+getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        homePagePlatform.goToHomePage();
        homePagePlatform.goToChat();
        switchTo().window("Chat");
        $(byText(space)).should(Condition.exist);
        switchTo().window("Home Page");
        homePagePlatform.goToAllSpace();
        spaceManagement.deleteSpace(space,false);
        switchTo().window("Chat");
        refresh();
        $(byText(space)).shouldNot(Condition.exist);
    }
@Test
    public void test03_sendMessageAndItsAnswerFromOtherUser(){
    String usernamea = "usernamea" + getRandomString();
    String password = "123456" ;
    String emaila = usernamea + getRandomNumber() + "@gmail.com";
    String message="message"+getRandomNumber();
    String message2="messagee"+getRandomNumber();
    navigationToolbar.goToAddUser();
    info("Create new user");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    manageLogInOut.signIn(usernamea,password);
    homePagePlatform.goToChat();
    switchTo().window("Home Page");
    manageLogInOut.signIn("root","gtn");
    homePagePlatform.goToHomePage();
    switchTo().window("Chat");
    refresh();
    ELEMENT_CHAT_INPUT_SEARCH_USER.setValue("@"+usernamea);
    $(byText(usernamea+" "+usernamea)).click();
    chatManagement.sendMessageInRoomOrSpace(usernamea+" "+usernamea,message);
    switchTo().window("Home Page");
    manageLogInOut.signIn(usernamea,password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    $(byText("Root Root")).click();
    $(byText(message)).should(Condition.exist);
    chatManagement.sendMessageInRoomOrSpace("Root Root",message2);
    switchTo().window("Home Page");
    manageLogInOut.signIn("root","gtn");
    homePagePlatform.goToChat();
    switchTo().window(1);
    refresh();
    ELEMENT_CHAT_INPUT_SEARCH_USER.setValue("@"+usernamea);
    $(byText(usernamea+" "+usernamea)).click();
    $(byText(message2)).should(Condition.exist);
    switchTo().window("Home Page");
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(usernamea);

    }
}
