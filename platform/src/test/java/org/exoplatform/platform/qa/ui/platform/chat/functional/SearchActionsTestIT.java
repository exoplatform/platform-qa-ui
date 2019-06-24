package org.exoplatform.platform.qa.ui.platform.chat.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.apache.bcel.generic.LADD;
import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
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
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_USER_PROFILE;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_USER_RESULT_SEARCH;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CONTACT_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_BUTTON_ACCEPT_SPACE_INVITATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchActionsTestIT extends Base {

    HomePagePlatform homePagePlatform;

    ChatManagement chatManagement;

    ManageLogInOut manageLogInOut;

    ConnectionsManagement connectionsManagement;

    SpaceManagement spaceManagement;

    SpaceHomePage spaceHomePage;

    SpaceSettingManagement spaceSettingManagement;

    RoomManagement roomManagement;

    WikiHomePage wikiHomePage;
    UserAndGroupManagement userAndGroupManagement;

    NavigationToolbar navigationToolbar;
    UserAddManagement userAddManagement;
    AddUsers addUsers;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        connectionsManagement = new ConnectionsManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        spaceManagement = new SpaceManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        chatManagement = new ChatManagement(this);
        spaceSettingManagement = new SpaceSettingManagement(this);
        roomManagement = new RoomManagement(this);
        wikiHomePage = new WikiHomePage(this);
        navigationToolbar = new NavigationToolbar(this);
        userAddManagement = new UserAddManagement(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        addUsers = new AddUsers(this);
        manageLogInOut.signInCas(PLFData.username, PLFData.password);

    }

    @Test
    public void test1_searchUserWithSpecialCharacter() {
        String username1 = "usera" + getRandomString();
        String searchedValue = "userb";
        String username2 = searchedValue + getRandomString();
        String user2_first_name = "usérb";
        String password = "123456";
        String email1 = getRandomString() + "@t.t";
        String email2 = getRandomString() + "@t.t";

        // 1-add 2 users
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        userAddManagement.addUser(username2, password, email2, user2_first_name, username2);
        info("users added");
        //2-connect created users
        manageLogInOut.signIn(username1, password);
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(username2);
        info("invitation sent");
        manageLogInOut.signIn(username2, password);
        homePagePlatform.goToConnections();
        connectionsManagement.goToConnectionTab(ConnectionsManagement.selectTabOption.RECEIVE);
        connectionsManagement.acceptAConnection(username1);
        //3-open chat form user profile
        manageLogInOut.signIn(username1, password);
        homePagePlatform.goToConnections();
        connectionsManagement.goToUserByUserName(username2);
        chatManagement.openChatFromMiniChat();
        chatManagement.searchUserInChatDiscussion(searchedValue,user2_first_name);
        //delete added users
        manageLogInOut.signIn("root", "gtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
        addUsers.deleteUser(username2);

    }
}
