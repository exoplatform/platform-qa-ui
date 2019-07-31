package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_ALL_CONNECTIONS_TAB;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONNECTION_USER_NAME;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

@Tag("sniff")
@Tag("social")
public class SOCPeopleProfileViewProfileTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  AddUsers              addUsers;

  ManageLogInOut        manageLogInOut;

  HomePagePlatform      homePagePlatform;

  ConnectionsManagement connectionsManagement;

  UserProfilePage       userProfilePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    connectionsManagement = new ConnectionsManagement(this);
    userProfilePage = new UserProfilePage(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:122923.</li>
   * <li>Test Case Name: Check My Profile page with default value.</li>
   * <li>Pre-Condition: User A has not updated his profile yet</li>
   * <li>Post-Condition:</li>
   * <li>Case ID:122925.</li>
   * <li>Test Case Name: Check the Profile of another user.</li>
   * <li>Pre-Condition: User A and user B are createdUser A has not updated
   * contact information</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Sign in system
   * Step Description: - Login with User A - Click user menu and [My Profile]
   * Input Data: Expected Outcome: - My profile is displayed - The title of the
   * page (displayed in the browser tab) is My Profile Step number: 2 Step Name:
   * Step 2: Check layout Step Description: - Check the mid-column Input Data:
   * Expected Outcome: - The 1st section is named About me and displays to the
   * user :"Help people find out more about you by sharing information on your
   * profile." followed by a button Edit my Profile which takes the user the edit
   * page. - The 2nd sections is name "Recent Activities" display message: You do
   * not have activities yet Step number: 3 Step Name: Step 3: Check layout Step
   * Description: - Check left column Input Data: Expected Outcome: - The section
   * is named "Contact Information" - Only the email field is filled Step number:
   * 4 Step Name: Step 4 : Check layout Step Description: - Check right column
   * Input Data: Expected Outcome: - The section is named Connections - The
   * message "You do not have connection yet" is displayed - The link is updated
   * to "Find connections" and redirect to my connections page on everyone tab.
   */
  @Test
  public void test01_CheckMyProfilePageWithDefaultValue() {
    info("Test 1: Check My Profile page with default value");
    String msgAboutMe = "Help people find you and learn more about you by filling out your profile information.";
    String msgRecent_me = "You do not have activities yet.";
    String msgRecent_other = "This user does not have activities yet.";
    String msg_me = "You do not have connections yet.";
    String msg_other = "This user does not have connections yet.";

    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToMyProfile();
    assertEquals(title(), "Profile");

    info("check mid-column");
    $(byXpath(ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", msgAboutMe))).should(Condition.exist);
    $(byXpath(ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", msgRecent_me))).should(Condition.exist);

    info("check left-column");
    $(ELEMENT_UIBASICPROFILEPORTLET).should(Condition.exist);
    $(byXpath(ELEMENT_EMAIL_INFO.replace("${email}", email1))).should(Condition.exist);

    info("check right-column");
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_me))).should(Condition.exist);
    info("click on Find connections");
    $(ELEMENT_UIMINICONNECTIONS_PORTLET_FIND).click();
    $(ELEMENT_ALL_CONNECTIONS_TAB).click();

    connectionsManagement.searchPeople(username2, null, null, null);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2))).click();

    info("check mid-column");
    $(byXpath(ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", msgRecent_other))).should(Condition.exist);

    info("check left-column");
    $(ELEMENT_UIBASICPROFILEPORTLET).should(Condition.exist);
    $(byXpath(ELEMENT_EMAIL_INFO.replace("${email}", email2))).should(Condition.exist);

    info("check right-column");
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_other))).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  @Test
  public void test03_CheckOtherProfilePageWithDefaultValue() {
    info("Test 1: Check My Profile page with default value");
    String msgAboutMe = "Help people find you and learn more about you by filling out your profile information.";
    String msgRecent_me = "You do not have activities yet.";
    String msgRecent_other = "This user does not have activities yet.";
    String msg_me = "You do not have connections yet.";
    String msg_other = "This user does not have connections yet.";

    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToMyProfile();
    assertEquals(title(), "Profile");

    info("check mid-column");
    $(byXpath(ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", msgAboutMe))).should(Condition.exist);
    $(byXpath(ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", msgRecent_me))).should(Condition.exist);

    info("check left-column");
    $(ELEMENT_UIBASICPROFILEPORTLET).should(Condition.exist);
    $(byXpath(ELEMENT_EMAIL_INFO.replace("${email}", email1))).should(Condition.exist);

    info("check right-column");
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_me))).should(Condition.exist);
    info("click on Find connections");
    $(ELEMENT_UIMINICONNECTIONS_PORTLET_FIND).click();
    $(ELEMENT_ALL_CONNECTIONS_TAB).click();

    connectionsManagement.searchPeople(username2, null, null, null);
    $(byXpath(ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2))).click();

    info("check mid-column");
    $(byXpath(ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", msgRecent_other))).should(Condition.exist);

    info("check left-column");
    $(ELEMENT_UIBASICPROFILEPORTLET).should(Condition.exist);
    $(byXpath(ELEMENT_EMAIL_INFO.replace("${email}", email2))).should(Condition.exist);

    info("check right-column");
    $(byXpath(ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_other))).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }

  /**
   * <li>Case ID:122924.</li>
   * <li>Test Case Name: Check My Profile page after all information is
   * filled.</li>
   * <li>Pre-Condition: User A updates About me, Experience, contact
   * information</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @Tag("sabis")
  public void test02_CheckMyProfilePageAfterAllInformationIsFilled() {
    info("Test 2: Edit Contact Information");
    String jobTitle = "jobTitle" + getRandomNumber();

    String organization = "organization" + getRandomString();
    String jobDetail = "jobDetail" + getRandomString();
    String skill = "skill" + getRandomString();
    String dStart = getDate(-7, "MM/dd/yyyy");
    String dEnd = getDate(-1, "MM/dd/yyyy");
    String im1 = "im1" + getRandomNumber();
    String im2 = "im2" + getRandomNumber();
    String im3 = "im3" + getRandomNumber();
    String im4 = "im4" + getRandomNumber();
    String im5 = "im5" + getRandomNumber();

    String phone1 = getRandomNumber() + getRandomNumber();
    String phone2 = getRandomNumber() + getRandomNumber();
    String phone3 = getRandomNumber() + getRandomNumber();

    String email = "email" + getRandomString() + "@test.com";
    String actContactInfo = "Contact information has been updated.";
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToMyProfile();

    info("edit profile");
    click(ELEMENT_EDIT_MY_PROFILE_LINK);

    info("update information");
    info("edit contact info");
    info("edit info");
    userProfilePage.updateBasicInformation(null, null, email);
    userProfilePage.updateIms("skype", im1, 0);
    userProfilePage.updateIms("gtalk", im2, 1);
    userProfilePage.updateIms("other", im3, 2);
    userProfilePage.updateIms("yahoo", im4, 3);
    userProfilePage.updateIms("msn", im5, 4);

    userProfilePage.updatePhone("work", phone1, 0);
    userProfilePage.updatePhone("home", phone2, 1);
    userProfilePage.updatePhone("other", phone3, 2);
    userProfilePage.saveCancelUpdateInfo(true);

    $(byXpath(ELEMENT_EMAIL_INFO.replace("${email}", email))).should(Condition.exist);

    $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Work").replace("${phone}", phone1))).should(Condition.exist);
    $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Home").replace("${phone}", phone2))).should(Condition.exist);
    $(byXpath(ELEMENT_PHONE_INFO.replace("${type}", "Other").replace("${phone}", phone3))).should(Condition.exist);

    $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Skype").replace("${im}", im1))).should(Condition.exist);
    $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Gtalk").replace("${im}", im2))).should(Condition.exist);
    $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Other").replace("${im}", im3))).should(Condition.exist);
    $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Yahoo").replace("${im}", im4))).should(Condition.exist);
    $(byXpath(ELEMENT_IM_INFO.replace("${type}", "Msn").replace("${im}", im5))).should(Condition.exist);

    $(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}",
                                                                               actContactInfo))).should(Condition.exist);
    $(ELEMENT_EDIT_MY_PROFILE_LINK).click();
    userProfilePage.updateExperience(organization, jobTitle, jobDetail, skill, dStart, dEnd, false);
    userProfilePage.saveCancelUpdateInfo(true);
    $(byXpath(ELEMENT_COMPANY_INFO.replace("${company}", organization))).should(Condition.exist);
    $(byXpath(ELEMENT_POSITION_INFO.replace("${position}", jobTitle))).should(Condition.exist);
    $(byXpath(ELEMENT_JOB_DETAIL_INFO.replace("${description}", jobDetail))).should(Condition.exist);
    $(byText(skill)).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);

  }
}
