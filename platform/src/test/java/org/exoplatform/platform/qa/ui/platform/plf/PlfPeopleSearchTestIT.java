package org.exoplatform.platform.qa.ui.platform.plf;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_LINE_IN_TABLE_LIST_USERS;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_TABLE_LIST_USERS;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONTENT_PEOPLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_NAME_OF_PEOPLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_INPUT_SEARCH_USER_NAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;


@Tag("gatein")
@Tag("smoke")

public class PlfPeopleSearchTestIT extends Base {

    NavigationToolbar      navigationToolbar;

    UserAndGroupManagement userandgroupmanagement;

    UserAddManagement      useraddmanagement;

    HomePagePlatform       homePagePlatform;

    ConnectionsManagement connectionsManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        navigationToolbar = new NavigationToolbar(this);
        userandgroupmanagement = new UserAndGroupManagement(this);
        useraddmanagement = new UserAddManagement(this);
        homePagePlatform = new HomePagePlatform(this);

        connectionsManagement = new ConnectionsManagement(this);
    }

    @Test
    public void test09_SearchUserOnPeopleWithSpecialCaractor() throws Exception {
        String username = "username" + getRandomNumber();
        String password = "password" + getRandomNumber();
        String firstName = "user-name"+getRandomString();
        String lastName = getRandomString();
        String email = firstName + getRandomNumber() + "@test.com";
        navigationToolbar.goToAddUser();
        info("Create new user");
        useraddmanagement.addUser(username, password, email, firstName, lastName);
        info("Search user on People");
        homePagePlatform.goToConnections();
        connectionsManagement.searchPeople(firstName, "","", "");
        homePagePlatform.refreshUntil(firstName+" "+lastName,Condition.visible,1000);
        info("Test Case 10: Delete user");
        navigationToolbar.goToManageCommunity();
        userandgroupmanagement.deleteUser(username);
    }

    @Test
public void test10_SearchUserOnPeapleWithaccent () throws Exception  {
    String username = "username" + getRandomNumber();
    String password = "password" + getRandomNumber();
    String firstName = "éric" + getRandomString();
    String lastName = getRandomString();
    String email = firstName + getRandomNumber() + "@test.com";
    navigationToolbar.goToAddUser();
    info("Create new user");
    useraddmanagement.addUser(username, password, email, firstName, lastName);
    info("Search user on People");
    homePagePlatform.goToConnections();
    connectionsManagement.searchPeople(firstName, "", "", "");
        homePagePlatform.refreshUntil(firstName+" "+lastName,Condition.visible,1000);
    info("Test Case 10: Delete user");
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(username);
    }

}



