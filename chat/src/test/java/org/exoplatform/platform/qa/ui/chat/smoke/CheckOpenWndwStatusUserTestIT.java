package org.exoplatform.platform.qa.ui.chat.smoke;

import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("chat")
@Tag("smoke")

  public class CheckOpenWndwStatusUserTestIT extends Base{

    HomePagePlatform homePagePlatform;
    RoomManagement roomManagement;
    NavigationToolbar navigationToolbar;
    UserAddManagement userAddManagement;
    ManageLogInOut manageLogInOut;
    UserAndGroupManagement userandgroupmanagement;
    ChatManagement chatManagement;
    SpaceManagement        spaceManagement;
    AddUsers addUsers;


    @BeforeEach
  public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        roomManagement = new RoomManagement(this);
        navigationToolbar = new NavigationToolbar(this);
        userAddManagement = new UserAddManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        userandgroupmanagement = new UserAndGroupManagement(this);
        chatManagement = new ChatManagement(this);
        spaceManagement = new SpaceManagement(this);
        addUsers = new AddUsers(this);
    }
    @Test
  public void test001_AddRoomWithSeveralUsers()  {
        String usernamea = "usernamea" + getRandomString();
        String password = "123456";
        String room = "room" + getRandomNumber();
        String emaila = usernamea + getRandomNumber() + "@test.com";
        navigationToolbar.goToAddUser();
        info("Create 1 users");
        userAddManagement.addUser(usernamea, password, emaila, usernamea, usernamea);
        manageLogInOut.signIn(usernamea, password);
        String space1 = "space1" + getRandomNumber();
        String app = "Google Map";
        info("app:" + app);
        String category = "Web";
        info("cate:" + category);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space1, space1);
            $(byClassName("uiIconBannerChat")).click();
            $(byClassName("uiNotifChatIcon.toggle-status-available")).shouldNotBe();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space1, false);
  }
}