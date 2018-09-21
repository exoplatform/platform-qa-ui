package org.exoplatform.platform.qa.ui.platform.gatein;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_HOME_LINK_PLF_IN_FRENCH;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ChangeLanguages;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;

/**
 * @date 8 April 2015
 * @author tult
 */
@Tag("sniff")
@Tag("gatein")

public class GateinGlobalSettingsTestIT extends Base {

  ManageLogInOut         manageLogInOut;

  NavigationToolbar      navigationToolbar;

  UserAddManagement      userAddManagement;

  UserAndGroupManagement userAndGroupManagement;

  UserProfilePage        userProfilePage;

  ChangeLanguages        changeLanguage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    userAddManagement = new UserAddManagement(this);
    userProfilePage = new UserProfilePage(this);
    changeLanguage = new ChangeLanguages(this);
    manageLogInOut = new ManageLogInOut(this);
    userAndGroupManagement = new UserAndGroupManagement(this);

    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }

  /**
   * <li>Case ID:123030.</li>
   * <li>Test Case Name: Change Password.</li>
   */
  @Test
  public void test01_ChangePassword() {
    String username = "username" + getRandomNumber();
    String password = "123456";
    String email = "email" + getRandomNumber() + "@gmail.com";
    String newpass = "12345678";
    String firstname = "firstname";
    String lastname = "lastname";

    info("Add new user");
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username, password, email, firstname, lastname);

    /*
     * Step Number: 1 Step Name: Step 1: Change Password Step Description: - Click
     * on the name of user - Click on Settings - Select Change Password tab - Change
     * new password and click Save Input Data: Expected Outcome: - Priority is
     * changed successfully
     */
    info("Change password of user");
    manageLogInOut.signIn(username, password);
    navigationToolbar.goToMySettings();
    userAddManagement.changePassWord(password, newpass, newpass);
    manageLogInOut.signOut();
    info("Login with new pass");
    manageLogInOut.signInCas(username, newpass);

    info("Clear Data");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username);
  }

  /**
   * <li>Case ID:123029.</li>
   * <li>Test Case Name: Change User Profile.</li>
   */
  @Test
  public void test02_ChangeUserProfile() {
    String username = "username" + getRandomNumber();
    String password = "123456" + getRandomNumber();
    String email = "email" + getRandomNumber() + "@gmail.com";

    String FirstName = "FirstName";
    String LastName = "LastName";
    String newFirstName = "newFirstName";
    String newLastName = "newLastName";
    String newEmail = "newEmail" + getRandomNumber() + "@gmail.com";
    info("Add new User");
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username, password, email, FirstName, LastName);
    manageLogInOut.signOut();

    /*
     * Step Number: 1 Step Name: Step 1: Change User Profile Step Description: -
     * Click on the name of user - Select My Profile tab - Select [Edit My Profile]
     * Button - Change user profile for user ( First Name, Last Name, Email) and
     * click Save Input Data: Expected Outcome: - The User profile is updated with
     * the change value
     */
    info("Change User Profile");
    info("Verify before change User Profile");
    manageLogInOut.signInCas(username, password);
    // waitForAndGetElement(ELEMENT_NAME_OF_USER_TOP_RIGHT.replace("${firstName}",
    // username).replace("${lastName}", username));
    info("Change User Profile");
    navigationToolbar.goToMyProfile();
    userProfilePage.goToEditProfile();
    userProfilePage.updateBasicInformation(newFirstName, newLastName, newEmail);
    userProfilePage.saveCancelUpdateInfo(true);
    info("Verify after change User Profile");
    manageLogInOut.signIn(username, password);
    // waitForAndGetElement(ELEMENT_NAME_OF_USER_TOP_RIGHT.replace("${firstName}",
    // newFirstName).replace("${lastName}", newLastName));

    info("Clear Data");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username);
  }

  /**
   * <li>Case ID:123145.</li>
   * <li>Test Case Name: Change language for another user.</li>
   */
  @Test
  public void test03_ChangeLanguageForAnotherUser() {
    String username = "username" + getRandomNumber();
    String password = "123456" + getRandomNumber();
    String email = "email" + getRandomNumber() + "@gmail.com";
    String FirstName = "FirstName";
    String LastName = "LastName";

    String language = "French";

    info("Add new user");
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username, password, email, FirstName, LastName);

    /*
     * Step Number: 1 Step Name: Step 1: Change language for another user Step
     * Description: - Go to Administration/users/Group and Roles - In User
     * management form: select a user in list and click Edit User infor - Choose
     * User Profile sub-tab, then change the display language for this user from the
     * Language field. ( ex: change to French language) - Click Save Input Data:
     * Expected Outcome: - Show message alert: "The user profile has been updated."
     */
    info("Change language of user mary to French");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.goToEditUserInfo(username);
    userAndGroupManagement.editUserInfo_UserProfileTab("", "", "", "", "Female", "", "", "", language);
    manageLogInOut.signOut();

    /*
     * Step Number: 2 Step Name: Step 2: Check after change language for user Step
     * Description: - Go to Administration/users/Group and Roles - In User
     * management form: select a user in list and click Edit User infor - Choose
     * User Profile sub-tab, then change the display language for this user from the
     * Language field. ( ex: change to French language) - Click Save Input Data:
     * Expected Outcome: - Show message alert: "The user profile has been updated."
     */
    info("Check language display for user");
    manageLogInOut.signInCas(username, password);
    $(ELEMENT_HOME_LINK_PLF_IN_FRENCH).waitUntil(Condition.visible, Configuration.timeout);

    info("Clear Data");
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.deleteUser(username);
  }

  /**
   * <li>Case ID:123146.</li>
   * <li>Test Case Name: Change language with demo account.</li>
   */
  @Test
  public void test04_ChangeLanguageWithDemoAccount() {
    String language1 = "French";
    String language2 = "English";

    /*
     * Step Number: 1 Step Name: Step 1: Change language with demo account
     * (john/demo/mary/james) Step Description: - Sign In portal by
     * Admin/Manager/User/Demo account Input Data: Expected Outcome: - Portal in
     * private mode is displayed - It is displayed in language of the language of
     * Cookie on website that was supported by portal or by current using portal (
     * admin/manager/User/Demo don't set language )
     */
    info("Check language default when do not set language for user is English");
    // $(ELEMENT_HOME_LINK_PLF).waitUntil(Condition.visible, Configuration.timeout);
    // manageLogInOut.signOut();

    /*
     * Step Number: 2 Step Name: Step 2: Check displaying language when language of
     * browser don't support by portal with user account demo Step Description: -
     * Sign In portal by Admin/Manager/User/Demo account Input Data: Expected
     * Outcome: - Portal in private mode is displayed - It is displayed in language
     * of current Cookie on your site ( admin/manager/User/Demo don't set language )
     */

    /*
     * Step Number: 3 Step Name: Step 3: Check when change language Step
     * Description: - Change language to French Input Data: Expected Outcome: -
     * Portal is displayed in new selected language - At next time when edited user
     * sign in, the displaying language will be in the new selected
     */
    info("Check when change language to French");
    navigationToolbar.goToChangeLanguage();
    changeLanguage.changeLanguage(language1, "Apply");
    $(ELEMENT_HOME_LINK_PLF_IN_FRENCH).waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signOut();
    info("At next time when edited user sign in, the displaying language will be in the new selected");
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);

    info("Change language of john account to French");
    navigationToolbar.goToChangeLanguage();
    changeLanguage.changeLanguage(language2, "Apply");
    $(ELEMENT_HOME_LINK_PLF_IN_FRENCH).waitUntil(Condition.visible, Configuration.timeout);
  }
}
