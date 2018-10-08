package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_USER_PROFILE_TAB;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase.scrollToBottomPage;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.openqa.selenium.By;

@Tag("sniff")
@Tag("social")
public class SOCPeopleProfileEditProfileTestIT extends Base {
  NavigationToolbar navigationToolbar;

  ManageLogInOut    manageLogInOut;

  AddUsers          addUsers;

  UserProfilePage   userProfilePage;
  UserAndGroupManagement userAndGroupManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    userAndGroupManagement= new UserAndGroupManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    addUsers = new AddUsers(this);
    userProfilePage = new UserProfilePage(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:122964.</li>
   * <li>Test Case Name: Edit About Me.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Go to my profile
   * page Step Description: - Login - Go to User Menu > [My Profile] Input Data:
   * Expected Outcome: Show content of my profile page* Step number: 2 Step Name:
   * Step 2: Edit Profile Step Description: - Click on [Edit my Profile] in the
   * top left corner, next to the user avatar Input Data: Expected Outcome: Edit
   * Profile page is displayed* Step number: 3 Step Name: Step 3: Edit About me
   * Step Description: - Edit About me field with some values - Click on [Save]
   * button Input Data: Expected Outcome: - [Save] button is enable. - About me
   * content is displayed in the middle column - A comment is added in Recent
   * Activities: About me has been updated. Step number: 4 Step Name: Step 4 :
   * Check Cancel button Step Description: - Click on [Edit my Profile] in the top
   * left corner, next to the user avatar - Do some changes in the page<br />
   * - Click [Cancel] button Input Data: Expected Outcome: - Change aren't saved
   */
  @Test
  public void test01_EditAboutMe() {
    info("Test 1: Edit About Me");
    String aboutMe = "aboutMe" + getRandomNumber();
    String aboutMe1 = "aboutMe1" + getRandomNumber();
    String actAboutMe = "\"About me\" has been updated.";
    String password = "123456";
    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToMyProfile();

    info("edit profile");
    $(ELEMENT_EDIT_MY_PROFILE_LINK).click();

    info("update about me");
    userProfilePage.updateAboutMe(aboutMe);
    userProfilePage.saveCancelUpdateInfo(true);
    $(byXpath(ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe))).should(Condition.exist);
    $(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}",
                                                                               actAboutMe))).should(Condition.exist);

    info("edit profile");
    click(ELEMENT_EDIT_MY_PROFILE_LINK);
    info("update about me");
    userProfilePage.updateAboutMe(aboutMe1);
    userProfilePage.saveCancelUpdateInfo(false);
    $(byXpath(ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe1))).shouldNot(Condition.exist);
    refresh();
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:122965.</li>
   * <li>Test Case Name: Edit Contact Information.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Go to my profile
   * page Step Description: - Login - Go to User Menu > [My Profile] Input Data:
   * Expected Outcome: Show content of my profile page Step number: 2 Step Name:
   * Step 2: Edit profile Step Description: - Click on [Edit my Profile] in the
   * top left corner, next to the user avatar Input Data: Expected Outcome: Show
   * edit page Step number: 3 Step Name: Step 3: Edit information of the Contact
   * Step Description: - Select gender, Enter the valid value for phone, instant
   * message, URLs field. - Click on [save] button Input Data: Expected Outcome: -
   * [Save] button is enable - New value of contact information is on the left
   * column - A comment is added in Recent Activities: Contact information has
   * been updated.
   */
  @Test
  public void test02_EditContactInformation() {
    info("Test 2: Edit Contact Information");
    String jobTitle = "jobTitle" + getRandomNumber();

    String gender = "Male";

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
    $(byXpath(ELEMENT_GENDER_INFO.replace("${gender}", gender))).should(Condition.exist);

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
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);

  }

  /**
   * <li>Case ID:122966.</li>
   * <li>Test Case Name: Edit Experience.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Go to my profile
   * page Step Description: - Login - Go to User Menu > [My Profile] Input Data:
   * Expected Outcome: Show content of my profile page Step number: 2 Step Name:
   * Step 2: Edit information of the experiences Step Description: - Click on
   * [Edit my Profile] in the top left corner, next to the user avatar - Select
   * experiences and Click on [+] button - Enter valid data into all field:
   * Organization, Job Title, Job Details, Skills - Start date: select month and
   * enter valid year - End date: Tick or Untick on check box "Still in this
   * position" , if [Untick], select valid month and enter valid year - Click on
   * [save] button Input Data: Expected Outcome: - [Save] button is enable -
   * Experience is displayed above Recent activities - A comment is added in
   * Recent Activities:Experiences have been updated.
   */
  @Test
  public void test03_EditExperience() {
    info("Test 3: Edit Experience");
    String organization = "organization" + getRandomString();
    String jobTitle = "jobTitle" + getRandomString();
    String jobDetail = "jobDetail" + getRandomString();
    String skill = "skill" + getRandomString();
    String dStart = getDate(-7, "MM/dd/yyyy");
    String dEnd = getDate(-1, "MM/dd/yyyy");
    String actdStart = getDate(-7, "MMMM dd, yyyy");
    String actdEnd = getDate(-1, "MMMM dd, yyyy");
    String actExperience = "Experience has been updated.";

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
    userProfilePage.updateExperience(organization, jobTitle, jobDetail, skill, dStart, dEnd, false);
    userProfilePage.saveCancelUpdateInfo(true);
    $(byXpath(ELEMENT_COMPANY_INFO.replace("${company}", organization))).should(Condition.exist);
    $(byXpath(ELEMENT_POSITION_INFO.replace("${position}", jobTitle))).should(Condition.exist);
    $(byXpath(ELEMENT_JOB_DETAIL_INFO.replace("${description}", jobDetail))).should(Condition.exist);
    $(byText(skill)).should(Condition.exist);
    $(byXpath(ELEMENT_STARTDATE_INFO.replace("${date}", actdStart))).should(Condition.exist);
    $(byXpath(ELEMENT_ENDDATE_INFO.replace("${date}", actdEnd))).should(Condition.exist);
    $(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}",
                                                                               actExperience))).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:122967.</li>
   * <li>Test Case Name: Change Avatar.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Go to my profile
   * page Step Description: - Login - Go to User Menu > [My Profile] Input Data:
   * Expected Outcome: Show content of my profile page Step number: 2 Step Name:
   * Step 2: Show form upload avatar Step Description: - Click on [Edit my
   * Profile] in the top left corner, next to the user avatar - Click on Change
   * avatar in the middle column Input Data: Expected Outcome: Avatar upload form
   * is displayed with: - Select File: browser to file on local and upload image -
   * Confirm: confirm the uploading image - Cancel: cancel the uploading Step
   * number: 3 Step Name: Step 3: upload image Step Description: - Browser to
   * image file ( .png, .gif, .jpg, .jpeg) on local - Click on Confirm button
   * Input Data: Expected Outcome: Show avatar preview form with image and all
   * information about the image Step number: 4 Step Name: Step 4: Finish change
   * avatar Step Description: - Click on Save button on avatar preview form Input
   * Data: Expected Outcome: - New avatar is displayed* Step number: 5 Step Name:
   * Step 5: Save edit page Step Description: Click on [Save] button of edit page
   * Input Data: Expected Outcome: - A comment is added in Recent Activities:
   * Avatar has been updated.
   */
  @Test
  public void test04_ChangeAvatar() {
    info("Test 4: Change Avatar");
    String actAvatar = "Avatar has been updated.";

    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToMyProfile();

    info("edit avatar");
    click(ELEMENT_EDIT_MY_PROFILE_LINK);
    ELEMENT_BUTTON_CHANGE_AVATAR.click();
    ELEMENT_INPUT_UPLOAD_AVATAR.uploadFromClasspath("testavatar.png");
    ELEMENT_BUTTON_CONFIRM_UPLOAD.click();
    ELEMENT_BUTTON_SAVE_UPLOAD_AVATAR.click();
    userProfilePage.saveCancelUpdateInfo(false);
    $(byXpath(ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", actAvatar))).should(Condition.exist);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test05_CheckDefaultEmptyGenderInManageUsers(){
    navigationToolbar.goToAddUser();
    $(ELEMENT_USER_PROFILE_TAB).click();
    $(ELEMENT_GENDER).shouldHave(Condition.exactValue(""));

  }

  @Test
  public void test06_CheckEmptyGenderIsSynchronizedInEditProfile(){
    String username = "username" + getRandomString();
    String email = username + "@test.com";
    String password = "123456";
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email, username, username);
    manageLogInOut.signIn(username,password);
    navigationToolbar.goToMyProfile();
    userProfilePage.goToEditProfile();
    scrollToBottomPage(this.getExoWebDriver().getWebDriver());
    $(ELEMENT_GENDER_EDIT_PROFILE).shouldHave(Condition.exactValue(""));
  }


  @BugInPLF("SOC-6086")
  @Test
  public void test07_CheckEmptyGenderIsSynchronizedInMangeUsers(){
    String username = "username" + getRandomString();
    String email = username + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username, password, email, username, username);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.goToEditUserInfo(username);
    userAndGroupManagement.editUserInfo_UserProfileTab("", "", "", "", "female", "", "", "","");
    manageLogInOut.signIn(username,password);
    navigationToolbar.goToMyProfile();
    userProfilePage.goToEditProfile();
    scrollToBottomPage(this.getExoWebDriver().getWebDriver());
    $(ELEMENT_GENDER_EDIT_PROFILE).shouldHave(Condition.exactValue("female"));
    $(ELEMENT_GENDER_EDIT_PROFILE).selectOptionByValue("");
    userProfilePage.saveCancelUpdateInfo(true);
    userProfilePage.goToEditProfile();
    scrollToBottomPage(this.getExoWebDriver().getWebDriver());
    $(ELEMENT_GENDER_EDIT_PROFILE).shouldHave(Condition.exactValue(""));
    info("verify that empty gender is synchronized in manage users");
    manageLogInOut.signIn(DATA_USER1,DATA_PASS2);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.goToEditUserInfo(username);
    $(ELEMENT_USER_PROFILE_TAB).click();
    $(ELEMENT_GENDER).shouldHave(Condition.exactValue(""));
    info("delete user");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username);
  }
}
