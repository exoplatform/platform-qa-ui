package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_ICON_ACCEPT_SPACE_REQUEST_IN_MEMBERS_TAB;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_ICON_DECLINE_SPACE_REQUEST_IN_MEMBERS_TAB;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

/**
 * Created by exo on 07/06/17.
 */
@Tag("smoke")
@Tag("social")
public class SOCSpaceMemberManagementRequestTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  SpaceManagement        spaceManagement;

  SpaceSettingManagement spaceSettingManagement;

  SpaceHomePage          spaceHomePage;

  UserAndGroupManagement userAndGroupManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    spaceHomePage = new SpaceHomePage(this);
    userAndGroupManagement = new UserAndGroupManagement(this);

  }

   @Test
  public void test02_AcceptUserRequestToJoinASpace() {
    String username1 = "usernamea AcceptUserRequestToJoinASpace" ;
    String email1 = username1 + "@gmail.com";
    String username2 = "usernameb AcceptUserRequestToJoinASpace" ;
    String email2 = username2 + "@gmail.com";
    String password = "123456";
    info("Add new user");
    manageLogInOut.signIn("root", "gtn");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    /*
     * Step Number: 1 Step Name: Step 1: Create a new Space Step Description: - Sign
     * in system - Select space page to view - Click on [Add new Space] - Enter all
     * valid data - Click [Create] Input Data: Expected Outcome: - Create new Space
     * successfully.
     */
    String space = "space AcceptUserRequestToJoinASpace" ;

    info("Create a space by user 1");
    homePagePlatform.goToAllSpace();
    spaceManagement.addNewSpaceSimple(space, space);
    manageLogInOut.signIn(username2, password);
    homePagePlatform.goToAllSpace();
    spaceManagement.goToAllSpacesTab();
    info("send request by user 2");
    spaceManagement.sendARequestToASpace(space);
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToSpecificSpace(space);
    spaceHomePage.goToSpaceSettingTab();
    info("accept request by user 1");
    spaceSettingManagement.goToMemberTabInSpaceSettingTab();
    $(byText(username2 + " " + username2)).parent().find(ELEMENT_ICON_ACCEPT_SPACE_REQUEST_IN_MEMBERS_TAB).click();
    spaceSettingManagement.goToMemberTab();
    $(byText(username2 + " " + username2)).scrollTo().should(Condition.exist);
    manageLogInOut.signOut();


  }

}
