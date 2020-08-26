package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONNECTION_USER_NAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("social")
@Tag("sniff")
public class SOCPeopleSearchTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  AddUsers              addUsers;

  HomePagePlatform      homePagePlatform;

  ConnectionsManagement connectionsManagement;

  UserProfilePage       userProfilePage;

  ManageLogInOut        manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    userProfilePage = new UserProfilePage(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  @Test
  public void test02_SearchPeopleByPosition() {
    String organization1 = "organization1" + getRandomString();
    String jobTitle1 = "jobTitle1" + getRandomString();
    String jobDetail1 = "jobDetail1" + getRandomString();
    String skill1 = "skill1" + getRandomString();
    String dStart = getDate(-7, "MM/dd/yyyy");

    String organization2 = "organization2" + getRandomString();
    String jobTitle2 = "jobTitle2" + getRandomString();
    String jobDetail2 = "jobDetail2" + getRandomString();
    String skill2 = "skill2" + getRandomString();

    /* Create data test */
    String username1 = "ausernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "busernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "Aa123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    manageLogInOut.signIn(username1, password);

    info("Edit user profile of user 1");
    info("Click on the name of user, click on My profile");
    navigationToolbar.goToMyProfile();
    info("Click on Edit button to change user's information");
    userProfilePage.goToEditProfile();
    userProfilePage.updateGenderJob("", jobTitle1);
    userProfilePage.updateExperience(organization1, jobTitle1, jobDetail1, skill1, dStart, null, true);
    userProfilePage.saveCancelUpdateInfo(true);

    info("Edit user profile of user 1");
    info("Click on the name of user, click on My profile");
    refresh();
    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToMyProfile();
    info("Click on Edit button to change user's information");
    userProfilePage.goToEditProfile();
    userProfilePage.updateGenderJob("", jobTitle2);
    userProfilePage.updateExperience(organization2, jobTitle2, jobDetail2, skill2, dStart, null, true);
    userProfilePage.saveCancelUpdateInfo(true);

    info("Login as John");
    manageLogInOut.signIn(username3, password);
    refresh();
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Test case 02: Search people by Position");
    connectionsManagement.searchPeople("", jobTitle2, "", "");
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).shouldNot(Condition.exist);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2))).should(Condition.exist);

    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();
    info("Search people by directory");
    connectionsManagement.searchPeople("", "", "", "B");
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).shouldNot(Condition.exist);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2))).should(Condition.exist);

    connectionsManagement.searchPeople("", "", "", "A");
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).should(Condition.exist);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2))).shouldNot(Condition.exist);

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);

  }

}
