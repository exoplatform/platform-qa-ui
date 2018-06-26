package org.exoplatform.platform.qa.ui.platform.plf;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("gatein")
@Tag("smoke")
public class PlfPeopleSearchTestIT extends Base {

    private NavigationToolbar navigationToolbar;

    private UserAndGroupManagement userandgroupmanagement;

    private UserAddManagement useraddmanagement;

    private HomePagePlatform homePagePlatform;

    private ConnectionsManagement connectionsManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        setNavigationToolbar(new NavigationToolbar(this));
        setUserandgroupmanagement(new UserAndGroupManagement(this));
        setUseraddmanagement(new UserAddManagement(this));
        setHomePagePlatform(new HomePagePlatform(this));

        setConnectionsManagement(new ConnectionsManagement(this));
    }

    @Test
    public void test09_SearchUserOnPeopleWithSpecialCaractor() throws Exception {
        info("JIRA_SOC-5775");
        String username = "username" + getRandomNumber();
        String password = "password" + getRandomNumber();
        String firstName = "user-name" + getRandomString();
        String lastName = getRandomString();
        String email = firstName + getRandomNumber() + "@test.com";
        getNavigationToolbar().goToAddUser();
        info("Create new user");
        getUseraddmanagement().addUser(username, password, email, firstName, lastName);
        info("Search user on People");
        getHomePagePlatform().goToConnections();
        getConnectionsManagement().searchPeople(firstName, "","", "");
        getHomePagePlatform().refreshUntil($(byText(firstName+" "+lastName)),Condition.visible,1000);

        info("Test Case 10: Delete user");
        getNavigationToolbar().goToManageCommunity();
        getUserandgroupmanagement().deleteUser(username);
    }

    @Test
    public void test10_SearchUserOnPeapleWithaccent() throws Exception {
        String username = "username" + getRandomNumber();
        String password = "password" + getRandomNumber();
        String firstName = "éric" + getRandomString();
        String lastName = getRandomString();
        String email = firstName + getRandomNumber() + "@test.com";
        getNavigationToolbar().goToAddUser();
        info("Create new user");
        getUseraddmanagement().addUser(username, password, email, firstName, lastName);
        info("Search user on People");
        getHomePagePlatform().goToConnections();
        getConnectionsManagement().searchPeople(firstName, "", "", "");
        getHomePagePlatform().refreshUntil($(byText(firstName+" "+lastName)),Condition.visible,1000);
        info("Test Case 10: Delete user");
        getNavigationToolbar().goToManageCommunity();
        getUserandgroupmanagement().deleteUser(username);
    }

    public NavigationToolbar getNavigationToolbar() {
        return navigationToolbar;
    }

    public void setNavigationToolbar(NavigationToolbar navigationToolbar) {
        this.navigationToolbar = navigationToolbar;
    }

    public UserAndGroupManagement getUserandgroupmanagement() {
        return userandgroupmanagement;
    }

    public void setUserandgroupmanagement(UserAndGroupManagement userandgroupmanagement) {
        this.userandgroupmanagement = userandgroupmanagement;
    }

    public UserAddManagement getUseraddmanagement() {
        return useraddmanagement;
    }

    public void setUseraddmanagement(UserAddManagement useraddmanagement) {
        this.useraddmanagement = useraddmanagement;
    }

    public HomePagePlatform getHomePagePlatform() {
        return homePagePlatform;
    }

    public void setHomePagePlatform(HomePagePlatform homePagePlatform) {
        this.homePagePlatform = homePagePlatform;
    }

    public ConnectionsManagement getConnectionsManagement() {
        return connectionsManagement;
    }

    public void setConnectionsManagement(ConnectionsManagement connectionsManagement) {
        this.connectionsManagement = connectionsManagement;
    }
}



