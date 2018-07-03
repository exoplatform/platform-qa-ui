package org.exoplatform.platform.qa.ui.platform.chat.functional;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONTENT_PEOPLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_POPOVER_USER;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_SLIDER_WEBCONFERENCING;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

@Tag("chat")
@Tag("functional")
public class ChatGenericUserPopupTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  UserAddManagement      userAddManagement;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  ConnectionsManagement  connectionsManagement;

  UserAndGroupManagement userAndGroupManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    userAddManagement = new UserAddManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.username, PLFData.password);
  }

  @Test
  public void test01_CheckChatButtonInUserPopUpWhenUsersAreAlreadyConnected() {
    String usernamea = "usernamea" + getRandomString();
    String usernameb = "usernameb" + getRandomString();
    String usernamec = "usernamec" + getRandomString();
    String password = "123456";

    String emaila = usernamea + getRandomNumber() + "@test.com";
    String emailb = usernameb + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    info("Create 3 users");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    userAddManagement.addUser(usernameb, password, emailb, usernameb, usernamec);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(usernameb);
    manageLogInOut.signIn(usernameb, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(usernamea);
    connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.ALL);
    ELEMENT_CONTENT_PEOPLE.find(byText(usernamea + " " + usernamea)).hover();
    ELEMENT_POPOVER_USER.find(byText(usernamea + " " + usernamea))
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byClassName("uiIconBannerChat"))
                        .waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(usernamea);
    userAndGroupManagement.deleteUser(usernameb);

  }

  @Test
  public void test02_CheckChatButtonInUserPopUpWhenUsersAreNotConnected() {
    String usernamea = "usernamea" + getRandomString();
    String usernameb = "usernameb" + getRandomString();
    String password = "123456";

    String emaila = usernamea + getRandomNumber() + "@test.com";
    String emailb = usernameb + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    info("Create 3 users");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    userAddManagement.addUser(usernameb, password, emailb, usernameb, usernameb);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToConnections();
    connectionsManagement.searchPeople(usernameb, null, null, null);
    ELEMENT_CONTENT_PEOPLE.find(byText(usernameb + " " + usernameb)).hover();
    ELEMENT_POPOVER_USER.find(byText(usernameb + " " + usernameb))
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byClassName("uiIconBannerChat"))
                        .waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(usernamea);
    userAndGroupManagement.deleteUser(usernameb);
  }

  @Test
  public void test03_CheckChatButtonInUserPopUpWhenUserReceiveAConnectionRequest() {
    String usernamea = "usernamea" + getRandomString();
    String usernameb = "usernameb" + getRandomString();
    String password = "123456";

    String emaila = usernamea + getRandomNumber() + "@test.com";
    String emailb = usernameb + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    info("Create 3 users");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    userAddManagement.addUser(usernameb, password, emailb, usernameb, usernameb);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(usernameb);
    manageLogInOut.signIn(usernameb, password);
    homePagePlatform.goToConnections();
    connectionsManagement.searchPeople(usernamea, null, null, null);
    ELEMENT_CONTENT_PEOPLE.find(byText(usernamea + " " + usernamea)).hover();
    ELEMENT_POPOVER_USER.find(byText(usernamea + " " + usernamea))
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byClassName("uiIconBannerChat"))
                        .waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(usernamea);
    userAndGroupManagement.deleteUser(usernameb);
  }

  @Test
  public void test04_CheckThePositionOfCallAndChatButtonsWhenChatWithVideoCalls() {
    String usernamea = "usernamea" + getRandomString();
    String usernameb = "usernameb" + getRandomString();
    String password = "123456";

    String emaila = usernamea + getRandomNumber() + "@test.com";
    String emailb = usernameb + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    info("Create 3 users");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    userAddManagement.addUser(usernameb, password, emailb, usernameb, usernameb);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(usernameb);
    manageLogInOut.signIn(usernameb, password);
    homePagePlatform.goToConnections();
    connectionsManagement.searchPeople(usernamea, null, null, null);
    ELEMENT_CONTENT_PEOPLE.find(byText(usernamea + " " + usernamea)).hover();
    ELEMENT_POPOVER_USER.find(byText(usernamea + " " + usernamea))
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byClassName("uiIconBannerChat"))
                        .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_POPOVER_USER.find(byText(usernamea + " " + usernamea))
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byClassName("callButton"))
                        .waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(usernamea);
    userAndGroupManagement.deleteUser(usernameb);
  }

  @Test
  public void test05_CheckChatButtonInUserPopupWhenUsersAreAlreadyCnnectedAndVideoCallDeactivated() {
    String usernamea = "usernamea" + getRandomString();
    String usernameb = "usernameb" + getRandomString();
    String usernamec = "usernamec" + getRandomString();
    String password = "123456";

    String emaila = usernamea + getRandomNumber() + "@test.com";
    String emailb = usernameb + getRandomNumber() + "@test.com";
    navigationToolbar.goToWebConferencing();
    ELEMENT_SLIDER_WEBCONFERENCING.click();
    navigationToolbar.goToAddUser();
    info("Create 3 users");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    userAddManagement.addUser(usernameb, password, emailb, usernameb, usernamec);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(usernameb);
    manageLogInOut.signIn(usernameb, password);
    homePagePlatform.goToConnections();
    connectionsManagement.acceptAConnection(usernamea);
    ELEMENT_CONTENT_PEOPLE.find(byText(usernamea + " " + usernamea)).hover();
    ELEMENT_POPOVER_USER.find(byText(usernamea + " " + usernamea))
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byClassName("uiIconBannerChat"))
                        .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_POPOVER_USER.find(byText(usernamea + " " + usernamea))
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byClassName("callButton"))
                        .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    navigationToolbar.goToWebConferencing();
    ELEMENT_SLIDER_WEBCONFERENCING.click();
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(usernamea);
    userAndGroupManagement.deleteUser(usernameb);
  }

  @Test
  public void test06_CheckChatButtonInUserPopupWhenUserReceiveAconnectionRequestAndVideoCallDeactivated() {
    String usernamea = "usernamea" + getRandomString();
    String usernameb = "usernameb" + getRandomString();
    String password = "123456";

    String emaila = usernamea + getRandomNumber() + "@test.com";
    String emailb = usernameb + getRandomNumber() + "@test.com";
    navigationToolbar.goToWebConferencing();
    ELEMENT_SLIDER_WEBCONFERENCING.click();
    navigationToolbar.goToAddUser();
    info("Create 3 users");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    userAddManagement.addUser(usernameb, password, emailb, usernameb, usernameb);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToConnections();
    connectionsManagement.connectToAUser(usernameb);
    manageLogInOut.signIn(usernameb, password);
    homePagePlatform.goToConnections();
    connectionsManagement.searchPeople(usernamea, null, null, null);
    ELEMENT_CONTENT_PEOPLE.find(byText(usernamea + " " + usernamea)).hover();
    ELEMENT_POPOVER_USER.find(byText(usernamea + " " + usernamea))
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byClassName("uiIconBannerChat"))
                        .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_POPOVER_USER.find(byText(usernamea + " " + usernamea))
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byClassName("callButton"))
                        .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    navigationToolbar.goToWebConferencing();
    ELEMENT_SLIDER_WEBCONFERENCING.click();
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(usernamea);
    userAndGroupManagement.deleteUser(usernameb);
  }

  @Test
  public void test07_CheckChatButtonInUserPopUpWhenUsersAreNotConnectedAndVideoCallDeactivated() {
    String usernamea = "usernamea" + getRandomString();
    String usernameb = "usernameb" + getRandomString();
    String password = "123456";

    String emaila = usernamea + getRandomNumber() + "@test.com";
    String emailb = usernameb + getRandomNumber() + "@test.com";
    navigationToolbar.goToWebConferencing();
    ELEMENT_SLIDER_WEBCONFERENCING.click();
    navigationToolbar.goToAddUser();
    info("Create 3 users");
    userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
    userAddManagement.addUser(usernameb, password, emailb, usernameb, usernameb);
    manageLogInOut.signIn(usernamea, password);
    homePagePlatform.goToConnections();
    connectionsManagement.searchPeople(usernameb, null, null, null);
    ELEMENT_CONTENT_PEOPLE.find(byText(usernameb + " " + usernameb)).hover();
    ELEMENT_POPOVER_USER.find(byText(usernameb + " " + usernameb))
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byClassName("uiIconBannerChat"))
                        .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_POPOVER_USER.find(byText(usernameb + " " + usernameb))
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .parent()
                        .find(byClassName("callButton"))
                        .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    manageLogInOut.signIn(PLFData.username, PLFData.password);
    navigationToolbar.goToWebConferencing();
    ELEMENT_SLIDER_WEBCONFERENCING.click();
    navigationToolbar.goToManageCommunity();
    userAndGroupManagement.deleteUser(usernamea);
    userAndGroupManagement.deleteUser(usernameb);
  }
}
