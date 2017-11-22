package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
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

  /**
   * <li>Case ID:121904.</li>
   * <li>Test Case Name: Search people by name.</li>
   * <li>Case ID:121964.</li>
   * <li>Test Case Name: Search people by position.</li>
   * <li>Case ID:121965.</li>
   * <li>Test Case Name: Search people by skill.</li>
   * <li>Case ID:121966.</li>
   * <li>Test Case Name: Search people by directory.</li> Step Number: 1 Step
   * Name: Step 1: Search people by name PreConditions: - There's are some people
   * with name has character "t" for example Step Description: - Log in and click
   * Connections on the left panel - Enter keyword "n" into the [Search by name]
   * box and press Enter Input Data: Expected Outcome: - Display all results match
   * with keyword Step Number: 2 Step Name: Step 2: Search people by position
   * PreConditions: - There's are some people who have the same position Step
   * Description: - Log in and click Connections on the left panel - Enter keyword
   * position into the [Search by position] box and press Enter Input Data:
   * Expected Outcome: - Display all results match with keyword Step Number: 3
   * Step Name: Step 3: Search people by skill PreConditions: - There's are some
   * people who have the same skill Step Description: - Log in and click
   * Connections on the left panel - Enter keyword skill into the [Search by
   * skill] box and press Enter Input Data: Expected Outcome: - Display all
   * results match with keyword Step Name: Step 4: Search people by directory Step
   * Description: - Log in and click Connections on the left panel - Click on
   * character from people directory characters list Input Data: Expected Outcome:
   * - Display all user which has the last name starting by selected char
   */
  @Test
  public void test01SearchPeopleByName() {

    /* Create data test */
    String username1 = "ausernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "busernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);
    info("Login as John");
    manageLogInOut.signIn(username3, password);
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    info("Test case 01: Search people by name");
    connectionsManagement.searchPeople(username1, "", "", "");
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).should(Condition.exist);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2))).shouldNot(Condition.exist);
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);

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
    String password = "123456";

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
    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToMyProfile();
    info("Click on Edit button to change user's information");
    userProfilePage.goToEditProfile();
    userProfilePage.updateGenderJob("", jobTitle2);
    userProfilePage.updateExperience(organization2, jobTitle2, jobDetail2, skill2, dStart, null, true);
    userProfilePage.saveCancelUpdateInfo(true);

    info("Login as John");
    manageLogInOut.signIn(username3, password);
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    info("Test case 02: Search people by Position");
    connectionsManagement.searchPeople("", jobTitle2, "", "");
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).shouldNot(Condition.exist);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2))).should(Condition.exist);

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);

  }

  @Test
  public void test03_SearchPeopleBySkills() {
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
    String password = "123456";

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
    userProfilePage.updateExperience(organization1, jobTitle1, jobDetail1, skill1, dStart, null, true);
    userProfilePage.saveCancelUpdateInfo(true);

    info("Edit user profile of user 1");
    info("Click on the name of user, click on My profile");
    manageLogInOut.signIn(username2, password);
    navigationToolbar.goToMyProfile();
    info("Click on Edit button to change user's information");
    userProfilePage.goToEditProfile();
    userProfilePage.updateExperience(organization2, jobTitle2, jobDetail2, skill2, dStart, null, true);
    userProfilePage.saveCancelUpdateInfo(true);

    info("Login as John");
    manageLogInOut.signIn(username3, password);
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    info("Test case 03: Search people by skill");
    connectionsManagement.searchPeople("", "", skill1, "");
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).should(Condition.exist);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2))).shouldNot(Condition.exist);

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
    addUsers.deleteUser(username3);

  }

  @Test
  public void test04_SearchPeopleByDirectory() {

    /* Create data test */
    String username1 = "ausernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "busernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String username3 = "usernamec" + getRandomString();
    String email3 = username3 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    addUsers.addUser(username3, password, email3, username3, username3);

    manageLogInOut.signIn(username3, password);
    info("Click on Connections on the left panel");
    homePagePlatform.goToConnections();

    info("Test case 04: Search people by directory");
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
