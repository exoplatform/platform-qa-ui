package org.exoplatform.platform.qa.ui.chat.smoke;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_INPUT_SEARCH_USER;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_LIST_MSG;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_ICON_ACCEPT_SPACE_REQUEST_IN_MEMBERS_TAB;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

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

@Tag("smoke")
@Tag("chat")
public class SpaceManageMessageTestIT extends Base {
  HomePagePlatform       homePagePlatform;

  NavigationToolbar      navigationToolbar;

  UserAddManagement      userAddManagement;

  ManageLogInOut         manageLogInOut;

  UserAndGroupManagement userandgroupmanagement;

  ChatManagement         chatManagement;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

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
    $(byText(usernamea + " " + usernamea)).parent().find(ELEMENT_ICON_ACCEPT_SPACE_REQUEST_IN_MEMBERS_TAB).click();
    homePagePlatform.goToHomePage();
    homePagePlatform.goToChat();
    switchTo().window(1);
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
    homePagePlatform.goToAllSpace();
    spaceManagement.deleteSpace(space, false);
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(usernamea);
  }


  @Test
  public void test03_sendMessageAndItsAnswerFromOtherUser() {
    String usernamea = "usernamea" + getRandomString();
    String password = "123456";
    String emaila = usernamea + getRandomNumber() + "@test.com";
    String message = "message" + getRandomNumber();
    String message2 = "messagee" + getRandomNumber();
    navigationToolbar.goToAddUser();
    info("Create new user");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToChat();
    switchTo().window(0);
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    homePagePlatform.goToHomePage();
    switchTo().window(1);
    refresh();
    ELEMENT_CHAT_INPUT_SEARCH_USER.setValue("@" + usernamea);
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
    ELEMENT_CHAT_INPUT_SEARCH_USER.setValue("@" + usernamea);
    $(byText(usernamea + " " + usernamea)).click();
    info("root check the answer");
    $(byText(message2)).should(Condition.exist);
    switchTo().window(0);
    info("delete data");
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(usernamea);

  }
}
