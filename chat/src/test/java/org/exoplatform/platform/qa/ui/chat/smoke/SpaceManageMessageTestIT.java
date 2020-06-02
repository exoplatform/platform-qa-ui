package org.exoplatform.platform.qa.ui.chat.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
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
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_USER_PROFILE;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_USER_RESULT_SEARCH;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("smoke")
@Tag("chat")
public class SpaceManageMessageTestIT extends Base {
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
    homePagePlatform = new HomePagePlatform(this);
    navigationToolbar = new NavigationToolbar(this);
    userAddManagement = new UserAddManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    userandgroupmanagement = new UserAndGroupManagement(this);
    chatManagement = new ChatManagement(this);
    spaceManagement = new SpaceManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
  }

  @Test
  public void test01_SendMessageOnSpaceChat() {
    String space = "space" + getRandomNumber();
    String usernamea = "usernamea" + getRandomString();
    String password = "123456";
    String emaila = usernamea + "@test.com";
    String message = "message" + getRandomNumber();
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
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    homePagePlatform.goToSpecificSpace(space);
    spaceHomePage.goToSpaceSettingTab();
    info("accept request by user 1");
    spaceSettingManagement.goToMemberTabInSpaceSettingTab();
    $(byText(usernamea + " " + usernamea)).parent().waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToChat();
    switchTo().window(1);
    info("check that space exist");
    $(byText(space)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).exists();
    info("send message on space");
    chatManagement.sendMessageInRoomOrSpace(space, message);
    switchTo().window(0);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    $(byText(space)).click();
    info("verify message");
    ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
    switchTo().window(0);
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    info("delete data");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    switchTo().window(1);
    refresh();
    info("check that space chat deleted");
    $(byText(space)).shouldNot(Condition.exist);
    switchTo().window(0);
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(usernamea);
  }

  @Test
  public void test02_sendMessageAndItsAnswerFromOtherUser() {
    String usernamea = "usernamea" + getRandomString();
    String password = "123456";
    String emaila = usernamea + getRandomNumber() + "@test.com";
    String message = "message" + getRandomNumber();
    String message2 = "messagee" + getRandomNumber();
    String MiniChatName;
    navigationToolbar.goToAddUser();
    info("Create new user");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    switchTo().window(0);
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    homePagePlatform.goToHomePage();
    homePagePlatform.goToPeople();
    homePagePlatform.searchUsersPeople(usernamea);
    refresh();
    ELEMENT_USER_RESULT_SEARCH.find(byText(usernamea + " " + usernamea)).click();
    ELEMENT_USER_PROFILE.waitUntil(Condition.appear, Configuration.timeout);
    $(byXpath("(//i[@class='uiIconBannerChat'])[2]")).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_MINI_CHAT.waitUntil(Condition.appear, Configuration.timeout);
    MiniChatName = $(byClassName("title-left")).parent().parent().find(byClassName("fullname")).getText();
    assertEquals(usernamea + " " + usernamea, MiniChatName);
    ELEMENT_MINI_CHAT.find(byClassName("uiIconClose")).click();
    switchTo().window(1);
    refresh();
    ELEMENT_CHAT_INPUT_SEARCH_USER.setValue(usernamea);
    $(byText(usernamea + " " + usernamea)).click();
    info("root send message to user");
    chatManagement.sendMessageInRoomOrSpace(usernamea + " " + usernamea, message);
    switchTo().window(0);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    $(byText("Root Root")).click();
    $(byText(message)).should(Condition.exist);
    info("user check message and answer root");
    chatManagement.sendMessageInRoomOrSpace("Root Root", message2);
    switchTo().window(0);
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    homePagePlatform.goToChat();
    switchTo().window(1);
    refresh();
    ELEMENT_CHAT_INPUT_SEARCH_USER.setValue(usernamea);
    $(byText(usernamea + " " + usernamea)).click();
    info("root check the answer");
    $(byText(message2)).should(Condition.exist);
    switchTo().window(0);
    info("delete data");
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(usernamea);

  }
}
