package org.exoplatform.platform.qa.ui.platform.social;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
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

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by nbenslima on 20/05/19.
 */
@Tag("social")
@Tag("functional")

public class SpacesNewsTestIT extends Base {

    NavigationToolbar navigationToolbar;

    ManageLogInOut manageLogInOut;

    HomePagePlatform homePagePlatform;

    SpaceManagement spaceManagement;

    SpaceSettingManagement spaceSettingManagement;

    SpaceHomePage spaceHomePage;

    ActivityStream activityStream;
    AddUsers addUsers;
    UserAndGroupManagement userAndGroupManage;

    
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut = new ManageLogInOut(this);
        homePagePlatform = new HomePagePlatform(this);
        spaceManagement = new SpaceManagement(this);
        spaceSettingManagement = new SpaceSettingManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        activityStream = new ActivityStream(this);
        addUsers = new AddUsers(this);
        userAndGroupManage = new UserAndGroupManagement(this);
        manageLogInOut.signInCas(username, password);
    }
    @Test
    public void test01_ChecktheTabNews_UserRedactor() {

        String space = "space" + getRandomNumber();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String password = "123456";
        String redactor = "redactor";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        info("Create a space and invite a user");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space, 60000);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.inviteUser(username1, false, "");
        navigationToolbar.goToManageCommunity();
        userAndGroupManage.addUsertoSpaceGroup(space, username1, redactor);
        info("user accept invitation to the space ");
        manageLogInOut.signIn(username1, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);
        spaceHomePage.checkNewsTab();
        manageLogInOut.signIn(username, "gtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }

    @Test
    public void test01_ChecktheTabNews_SpaceManager() {

        String space = "space" + getRandomNumber();
        info("Create a space ");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space, 60000);
        spaceHomePage.checkNewsTab();
    }

    @Test
    public void test02_Check_Details_TabNews_SpaceManager() {

        String space = "space" + getRandomNumber();
        info("Create a space ");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space, 60000);
        spaceHomePage.checkDetailsNewsTab();
    }

    @Test
    public void test02_Post_button_enabled() {

        String space = "space" + getRandomNumber();
        String title = "title" + getRandomNumber();
        String content = "content" + getRandomNumber();
        info("Create a space ");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space, 60000);
        spaceHomePage.checkNewsTab();
        info(" input Title and content and Verify Post button enabled");
        $(ELEMENT_field_Title).setValue(title);
        switchTo().frame($(ELEMENT_frame));
        $(byXpath("/html/body")).waitUntil(Condition.visible, Configuration.timeout).sendKeys(content);
        switchTo().defaultContent();
        $(ELEMENT_Button_Post_enabled).waitUntil(Condition.enabled, Configuration.timeout);
        homePagePlatform.goToHomePage();
        switchTo().alert().accept();
    }

    @Test
    public void test02_Post_button_disabled() {

        String space = "space" + getRandomNumber();
        info("Create a space ");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space, 60000);
        spaceHomePage.checkNewsTab();
        info(" Verify Post button is disabled");
        $(ELEMENT_Button_Post).isDisplayed();
    }

    @Test
    public void test02_Check_Details_TabNews_UserRedactor() {

        String space = "space" + getRandomNumber();
        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String password = "123456";
        String redactor = "redactor";
        info("Add user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        info("Create a space and invite a user");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space, 60000);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.inviteUser(username1, false, "");
        navigationToolbar.goToManageCommunity();
        userAndGroupManage.addUsertoSpaceGroup(space, username1, redactor);
        info("user accept invitation to the space ");
        manageLogInOut.signIn(username1, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);
        spaceHomePage.checkDetailsNewsTab();
        manageLogInOut.signIn(username, "gtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }

}
