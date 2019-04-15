package org.exoplatform.platform.qa.ui.platform.social.functional;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import java.util.ArrayList;

import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;


@Tag("functional")
@Tag("social")
public class PortletinSpaceHomepageTestIT extends Base {
    NavigationToolbar      navigationToolbar;
    ManageLogInOut         manageLogInOut;
    HomePagePlatform       homePagePlatform;
    SpaceManagement        spaceManagement;
    UserAndGroupManagement userAndGroupManagement;
    AddUsers               addUsers;
    UserAddManagement userAddManagement;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut = new ManageLogInOut(this);
        homePagePlatform = new HomePagePlatform(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        addUsers = new AddUsers(this);
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
        spaceManagement = new SpaceManagement(this);
        userAddManagement = new UserAddManagement(this);

    }
    @Test
    public void test02_CheckElementsInTheSpaceportlet() {

        String username1 = "usernamea" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String username2 = "usernameb" + getRandomString();
        String email2 = username2 + "@gmail.com";
        String password = "123456";

        info("Add new user");
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        userAddManagement.addUser(username2, password, email2, username2, username2);
        manageLogInOut.signIn(username1, password);

        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);

        info("Test 1: CheckElementsInTheSpaceportlet");
        navigationToolbar.goToSpaceAdminstration();
        ELEMENT_SPACE_GRID.find(byText(spaceNamea))
                .parent()
                .parent()
                .find(ELEMENT_DELETESPACE_MANAGE_ICON)
                .waitUntil(Condition.visible, Configuration.timeout);
        ELEMENT_SPACE_GRID.find(byText(spaceDesa))
                .parent()
                .parent()
                .find(ELEMENT_EDITSPACE_MANAGE_ICON)
                .waitUntil(Condition.visible, Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
    }}