package org.exoplatform.platform.qa.ui.chat.pageobject.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("Functional")
@Tag("Chat")
public class ChatAccentCaractersSpaceFunctionAddEventTestIt extends Base {
    ManageLogInOut manageLogInOut;

    NavigationToolbar navigationToolbar;

    UserAddManagement userAddManagement;

    ConnectionsManagement connectionsManagement;

    SpaceManagement spaceManagement;

    SpaceHomePage spaceHomePage;

    SpaceSettingManagement spaceSettingManagement;

    HomePagePlatform homePagePlatform;

    AddUsers addUsers;

    UserAndGroupManagement userAndGroupManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut = new ManageLogInOut(this);
        userAddManagement = new UserAddManagement(this);
        connectionsManagement = new ConnectionsManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        spaceManagement = new SpaceManagement(this);
        spaceSettingManagement = new SpaceSettingManagement(this);
        addUsers = new AddUsers(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        manageLogInOut.signInCas("root", "gtn");
    }

    @Test
    public void test01_CheckTheAddEventOnTheChatareaOfSpaceConversation() {
        info("add user  1");
        String username = "username" + getRandomString();
        String email = username + "@gmail.com";
        String password = "123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username, password, email, username, username);
        info("Connect with user 2");
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String password1 = "123456";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password1, email1, username1, username1);
        info("add space 1");
        String space = "space" + getRandomNumber();
        String app = "Forum Statistics";
        info("Create a space");
        manageLogInOut.signIn(username, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.inviteUser(username1, false, "");
        manageLogInOut.signIn(username1, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.goToInvitationsReceivedTab();
        $(byXpath(ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN.replace("${space}", space))).click();
        refresh();
        info("Add Event");
        String titleEvent = "event" + "é" + "à" + "è" + getRandomNumber();
        String startDate = "20/08/2020";
        String endDate = "25/08/2020";
        String locationEvent = "Location" + "é" + "à" + getRandomNumber();
        info("Accept Request to space");
        manageLogInOut.signIn(username, password);
        info("open chat window");
        navigationToolbar.gotochatwindow();
        switchTo().window(1);
        info("Select space ");
        $(byText(space)).click();
        ELEMENT_ICON_PLUS_CHAT.click();
        ELEMENT_ADD_EVENT_CHAT.waitUntil(Condition.visible, Configuration.timeout).click();
        ELEMENT_SUMMARY_EVENT.setValue(titleEvent);
        ELEMENT_START_DATE_EVENT_CHAT.setValue(startDate);
        ELEMENT_END_DATE_EVENT_CHAT.setValue(endDate);
        ELEMENT_LOCATION_EVENT_CHAT.setValue(locationEvent);
        ELEMENT_LOCATION_EVENT_CHAT.click();
        ELEMENT_POST_ADD_EVENT_CHAT.click();
        switchTo().window(0);
        refresh();
        manageLogInOut.signIn(username1, password1);
        refresh();
        navigationToolbar.gotochatwindow();
        switchTo().window(1);
        refresh();
        $(byText(space)).click();
        ELEMENT_VERIFY_ICON_EVENT_CHAT.shouldBe(Condition.visible);
    }

    @Test
    public void test02_CheckDisplayOfEventInSpaceCalendarAppAfterItIsCreatedInChatAreaOfSpaceConversation() {
        info("add user  1");
        String username = "username" + getRandomString();
        String email = username + "@gmail.com";
        String password = "123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username, password, email, username, username);
        info("Connect with user 2");
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@test.com";
        String password1 = "123456";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password1, email1, username1, username1);
        info("add space 1");
        String space = "space" + getRandomNumber();
        String app = "Forum Statistics";
        info("Create a space");
        manageLogInOut.signIn(username, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.inviteUser(username1, false, "");
        manageLogInOut.signIn(username1, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.goToInvitationsReceivedTab();
        $(byXpath(ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN.replace("${space}", space))).click();
        refresh();
        info("Add Event");
        String titleEvent = "event" + "é" + "à" + "è" + getRandomNumber();
        String startDate = "05/07/2018";
        String endDate = "05/07/2018";
        String locationEvent = "Location" + "é" + "à" + getRandomNumber();
        info("Accept Request to space");
        manageLogInOut.signIn(username, password);
        refresh();
        refresh();
        info("open chat window");
        navigationToolbar.gotochatwindow();
        switchTo().window(1);
        refresh();
        info("Select space ");
        $(byText(space)).click();
        ELEMENT_ICON_PLUS_CHAT.click();
        ELEMENT_ADD_EVENT_CHAT.waitUntil(Condition.visible, Configuration.timeout).click();
        ELEMENT_SUMMARY_EVENT.setValue(titleEvent);
        ELEMENT_START_DATE_EVENT_CHAT.setValue(startDate);
        ELEMENT_END_DATE_EVENT_CHAT.setValue(endDate);
        ELEMENT_LOCATION_EVENT_CHAT.setValue(locationEvent);
        ELEMENT_LOCATION_EVENT_CHAT.click();
        ELEMENT_POST_ADD_EVENT_CHAT.click();
        switchTo().window(0);
        refresh();
        manageLogInOut.signIn(username, password);
        refresh();
        homePagePlatform.goToMySpaces();
        homePagePlatform.goToSpecificSpace(space);
        ELEMENT_CALENDAR_SPACE_CHAT.click();
        ELEMENT_SEARCH_CALENDAR_EVENT_CHAT.setValue(titleEvent);
        ELEMENT_CLICK_SEARCH_BUTON_CHAT.click();
        ELEMENT_SEARCH_CALENDAR_RESULT_CHAT.find(byText(titleEvent)).waitUntil(Condition.visible, Configuration.timeout);
    }
}