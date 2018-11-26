package org.exoplatform.platform.qa.ui.platform.social;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.NotificationsAdminSeting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_INPUT_DOCUMENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_EDIT_MY_PROFILE_LINK;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class SOCPeopleProfilePictureTestIT extends Base {

    NavigationToolbar navigationToolbar;
    NotificationsAdminSeting notificationsAdminSeting;
    ManageLogInOut manageLogInOut;
    UserAndGroupManagement userAndGroupManagement;
    AddUsers addUsers;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        addUsers = new AddUsers(this);
        manageLogInOut = new ManageLogInOut(this);
        notificationsAdminSeting = new NotificationsAdminSeting(this);
        userAndGroupManagement = new UserAndGroupManagement(this);
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    }
    @Test
    public void test01_ChangeAvatarFromCameraIcon() {

        String username1 = "a" + getRandomString();
        String email1 = username1 + "@gmail.com";
        String password ="123456";
        navigationToolbar.goToAddUser();
        addUsers.addUser(username1, password, email1, username1, username1);
        manageLogInOut.signIn(username1, password);
        navigationToolbar.goToMyProfile();
        $(byId("BannerAvatarUploader")).find(byClassName("file")).uploadFromClasspath("eXo-Platform.png");
        manageLogInOut.signIn(DATA_USER1,"gtngtn");
        navigationToolbar.goToManageCommunity();
        addUsers.deleteUser(username1);
    }
}
