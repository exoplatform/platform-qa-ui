package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_ALL_CONNECTIONS_TAB;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONNECTION_USER_NAME;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
@Tag("social")
@Tag("sniff")
public class SOCPeopleProfileExperienceTestIT extends Base {

  NavigationToolbar     navigationToolbar;

  AddUsers              addUsers;

  ManageLogInOut        manageLogInOut;

  UserProfilePage       userProfilePage;

  HomePagePlatform      homePagePlatform;

  ConnectionsManagement connectionsManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    userProfilePage = new UserProfilePage(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  @Test
  @Tag("sabis")
  public void test02_CheckOtherExperienceSection() {
    info("Test 1: Check my Experience section");
    String organization = "organization" + getRandomString();
    String jobTitle = "jobTitle" + getRandomString();
    String jobDetail = "jobDetail" + getRandomString();
    String skill = "skill" + getRandomString();
    String dStart = getDate(-7, "MM/dd/yyyy");
    String dEnd = getDate(-1, "MM/dd/yyyy");
    String actdStart = getDate(-7, "MMMM dd, yyyy");
    String actdEnd = getDate(-1, "MMMM dd, yyyy");

    /* Create data test */
    String username1 = "" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToMyProfile();
    $(ELEMENT_EDIT_MY_PROFILE_LINK).click();
    userProfilePage.updateExperience(organization, jobTitle, jobDetail, skill, dStart, dEnd, false);
    userProfilePage.saveCancelUpdateInfo(true);
    $(byXpath(ELEMENT_COMPANY_INFO.replace("${company}", organization))).should(Condition.visible);
    $(byXpath(ELEMENT_POSITION_INFO.replace("${position}", jobTitle))).should(Condition.visible);
    $(byXpath(ELEMENT_JOB_DETAIL_INFO.replace("${description}", jobDetail))).should(Condition.visible);
    $(byXpath(ELEMENT_SKILL_INFO.replace("${skill}", skill))).should(Condition.visible);
    $(byXpath(ELEMENT_STARTDATE_INFO.replace("${date}", actdStart))).should(Condition.visible);
    $(byXpath(ELEMENT_ENDDATE_INFO.replace("${date}", actdEnd))).should(Condition.visible);

    info("Test 2: Check Experience section of other");
    info("login as user 2");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");

    info("goto profile of user 1");
    homePagePlatform.goToConnections();
    $(ELEMENT_ALL_CONNECTIONS_TAB).click();
    connectionsManagement.searchPeople(username1, null, null, null);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1))).click();
    $(byXpath(ELEMENT_COMPANY_INFO.replace("${company}", organization))).should(Condition.visible);
    $(byXpath(ELEMENT_POSITION_INFO.replace("${position}", jobTitle))).should(Condition.visible);
    $(byXpath(ELEMENT_JOB_DETAIL_INFO.replace("${description}", jobDetail))).should(Condition.visible);
    $(byXpath(ELEMENT_SKILL_INFO.replace("${skill}", skill))).should(Condition.visible);
    $(byXpath(ELEMENT_STARTDATE_INFO.replace("${date}", actdStart))).should(Condition.visible);
    $(byXpath(ELEMENT_ENDDATE_INFO.replace("${date}", actdEnd))).should(Condition.visible);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);

  }

}
