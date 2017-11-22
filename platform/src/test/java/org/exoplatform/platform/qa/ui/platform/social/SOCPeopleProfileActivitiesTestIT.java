package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
@Tag("sniff")
@Tag("social")
public class SOCPeopleProfileActivitiesTestIT extends Base {

  NavigationToolbar navigationToolbar;

  AddUsers          addUsers;

  ManageLogInOut    manageLogInOut;

  UserProfilePage   userProfilePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    userProfilePage = new UserProfilePage(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:122972.</li>
   * <li>Test Case Name: Update user profile activity.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Go to my profile
   * page Step Description: - Login - Go to User Menu > [My Profile] Input Data:
   * Expected Outcome: Show content of my profile page* Step number: 2 Step Name:
   * Step 2: Edit Profile Step Description: - Click on [Edit my Profile] in the
   * top left corner, next to the user avatar Input Data: Expected Outcome: Edit
   * Profile page is displayed* Step number: 3 Step Name: Step 3: Edit Contact
   * Information Step Description: - Edit contact information (First Name/Last
   * Name/Email....), Experience, About Me and Avatar - Click on [Save] button
   * Input Data: Expected Outcome: - A comment is added in activity stream and
   * Recent Activities: "Contact information has been updated." - Activity content
   * is updated with new value* Step number: 4 Step Name: Step 4: Edit Experiences
   * Step Description: - Edit Experiences - Click on [Save] button Input Data:
   * Expected Outcome: - A comment is added in activity stream and Recent
   * Activities: "Experiences haves been updated." - Activity content is updated
   * with new value* Step number: 5 Step Name: Step 5: Edit Avatar Step
   * Description: - Edit Avatar - Click on [Save] button Input Data: Expected
   * Outcome: - A comment is added in activity stream and Recent Activities:
   * "Avatar has been updated." - Activity content is updated with new value* Step
   * number: 6 Step Name: Step 6: Edit About me Step Description: - Edit About me
   * - Click on [Save] button Input Data: Expected Outcome: - A comment is added
   * in activity stream and Recent Activities: "About me has been updated." -
   * Activity content is updated with new value
   */
  @Test
  public void test01_UpdateUserProfileActivity() {
    info("Test 1: Update user profile activity");
    String firstName = "firstname" + getRandomString() + "first";
    String lastName = "lastname" + getRandomString() + "last";
    String email = "email" + getRandomString() + "@test.com";
    String aboutMe = "" + getRandomNumber();
    String organization = "organization" + getRandomString();
    String jobTitle = "jobTitle" + getRandomString();
    String password = "123456";
    String actContactInfo = "Contact information has been updated.";

    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToMyProfile();

    click(ELEMENT_EDIT_MY_PROFILE_LINK);

    info("edit info");
    userProfilePage.updateBasicInformation(firstName, lastName, email);
    userProfilePage.saveCancelUpdateInfo(true);
    ELEMENT_NAME_PROFILE_OF_USERS.find(byText(firstName + " " + lastName)).should(Condition.visible);
    $(byClassName("uiEmail")).shouldHave(Condition.text(email));
    $(byText(actContactInfo)).should(Condition.visible);

    info("edit experience");
    click(ELEMENT_EDIT_MY_PROFILE_LINK);
    userProfilePage.updateExperience(organization, jobTitle, null, null, null, null, null);
    userProfilePage.saveCancelUpdateInfo(true);
    $(byXpath(ELEMENT_COMPANY_INFO.replace("${company}", organization))).should(Condition.visible);
    $(byXpath(ELEMENT_POSITION_INFO.replace("${position}", jobTitle))).should(Condition.visible);
    $(byText(actContactInfo)).should(Condition.visible);

    info("edit avatar");
    click(ELEMENT_EDIT_MY_PROFILE_LINK);
    ELEMENT_BUTTON_CHANGE_AVATAR.click();
    ELEMENT_INPUT_UPLOAD_AVATAR.uploadFromClasspath("testavatar.png");
    ELEMENT_BUTTON_CONFIRM_UPLOAD.click();
    ELEMENT_BUTTON_SAVE_UPLOAD_AVATAR.click();
    userProfilePage.saveCancelUpdateInfo(false);
    $(byText(actContactInfo)).should(Condition.visible);

    info("edit about me");
    click(ELEMENT_EDIT_MY_PROFILE_LINK);
    userProfilePage.updateAboutMe(aboutMe);
    userProfilePage.saveCancelUpdateInfo(true);
    $(byXpath(ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe))).should(Condition.visible);
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }
}
