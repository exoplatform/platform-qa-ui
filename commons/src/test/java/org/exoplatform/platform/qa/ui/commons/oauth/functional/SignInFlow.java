/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - Core.
 *
 * eXo PLF:: QA - UI - Core is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - Core software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - Core; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.commons.oauth.functional;

import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.gatein.UserAndGroupManagement;
import org.junit.jupiter.api.*;

import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_PLF_HOMEPAGE_DISPLAY;
import static org.exoplatform.platform.qa.ui.selenium.locator.ManageLogInOutLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class SignInFlow extends Base {

  ManageLogInOut         manageLoginOut;

  NavigationToolbar      navigationToolbar;

  UserAndGroupManagement userAndGroup;

  Button button;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    initSeleniumTest();

    manageLoginOut = new ManageLogInOut(this);
    navigationToolbar = new NavigationToolbar(this);
    userAndGroup = new UserAndGroupManagement(this);
    button = new Button(this);
  }

  /**
   * <li>Case ID:128293.</li>
   * <li>Test Case Name: Check the OAuth flow when eXo has not access to the
   * Social Network.</li>
   * <li>Pre-Condition: User A has not authorized eXo to access his user data in
   * the social network</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @DisplayName("Case ID:128293")
  public void test01_CheckTheOAuthFlowWhenEXoHasNotAccessToTheSocialNetwork() {
    info("Test 1: Check the OAuth flow when eXo has not access to the Social Network");

    /*
     * Step Number: 1 Step Name: Step 1: Sign in with a social network Step
     * Description: - Open Login page (accessible from /portal/login) - Sign in
     * with a social network with User A Input Data: Expected Outcome: - User A
     * is redirected to the chosen social network
     */
    info("Check facebook");
    manageLoginOut.checkFacebookLoginForm();
    waitForAndGetElement(ELEMENT_FACEBOOK_LOGIN_FORM_LOGIN_BUTTON);

    info("Back to the platform login page");
    getExoWebDriver().getWebDriver().navigate().back();

    info("Check twitter");
    manageLoginOut.checkTwitterLoginForm();
    waitForAndGetElement(ELEMENT_TWITTER_LOGIN_FORM_LOGIN_BUTTON);

    info("Back to the platform login page");
    getExoWebDriver().getWebDriver().navigate().back();

    info("Check linkedin");
    manageLoginOut.checkLinkedinLoginForm();
    waitForAndGetElement(ELEMENT_LINKEDIN_LOGIN_FORM_LOGIN_BUTTON);

    info("Back to the platform login page");
    getExoWebDriver().getWebDriver().navigate().back();

    info("Check G+");
    manageLoginOut.checkGoogleLoginForm();
    Assertions.assertNotNull(waitForAndGetElement(ELEMENT_GOOGLE_LOGIN_FORM_NEXT_BUTTON));
  }

  /**
   * <li>Case ID:128300.</li>
   * <li>Test Case Name: Check the registration NOT on the fly after user has
   * granted authorization.</li>
   * <li>Pre-Condition: - - By default, sign up on fly mode is not activated for
   * Twitter provider (If want to change the setting for sign up on fly mode, we
   * can setting in exo.properties file) - - Twitter account has not authorized
   * eXo to access his user data in the social network - - Twitter account is
   * not currently logged in the social network</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @DisplayName("Case ID:128300")
  @Disabled
  public void test05_CheckTheRegistrationNOTOnTheFlyAfterUserHasGrantedAuthorization() {
    info("Test 5: Check the registration NOT on the fly after user has granted authorization");

    String name = "exofqa-twitt";
    /*
     * Step Number: 1 Step Name: Step 1: Open Login page Step Description: -
     * Login page (accessible from /portal/login) - Choose Sign in with a
     * provider that is not actived sign up on fly mode (For example: Twitter)
     * Input Data: Expected Outcome: - OAuth authorization page is displayed
     */
    manageLoginOut.checkTwitterLoginForm();

    /*
     * Step number: 2 Step Name: Step 2: Sign in on OAuth authorization page
     * Step Description: - On OAuth authorization page: click Sign in button -
     * Enter valid value for Username and Password fields - Click Sign in button
     * Input Data: Expected Outcome: - Registration New Account form is
     * displayed - Registration New Account form is pre-filled with information
     * extracted from the social network (Username,First Name, Last Name, Email)
     */

    manageLoginOut.loginWithTwitterAccount(SOCIAL_NETWORKS_ACCOUNT_TWITTER, SOCIAL_NETWORKS_PD);
    manageLoginOut.loginWithTwitterAccount(SOCIAL_NETWORKS_ACCOUNT_TWITTER, SOCIAL_NETWORKS_PD);
    Utils.pause(3000);
    waitForAndGetElement(ELEMENT_NEW_ACCOUNT_POPUP);
    waitForAndGetElement(ELEMENT_NEW_ACCOUNT_VALUE_USERNAME.replace("${value}", "exofqa"));
    waitForAndGetElement(ELEMENT_NEW_ACCOUNT_VALUE_FIRSTNAME.replace("${value}", "exofqa_twitt"));
    waitForAndGetElement(ELEMENT_NEW_ACCOUNT_VALUE_DISPLAYNAME.replace("${value}", "exofqa_twitt"));

    /*
     * Step number: 3 Step Name: Step 3: Register a new account Step
     * Description: - Enter valid value for other fields (Password, Confirm
     * Password,Display Name) - Click "Subscribe and Login" button Input Data:
     * Expected Outcome: - A new account is created automatically using user
     * attributes from the linked social network - The user is authenticated and
     * redirected to the eXo page he initially requested.
     */

    type(ELEMENT_NEW_ACCOUNT_PASSWORD, SOCIAL_NETWORKS_PD, true);
    type(ELEMENT_NEW_ACCOUNT_PASSWORD_CONFIRM, SOCIAL_NETWORKS_PD, true);
    type(ELEMENT_NEW_ACCOUNT_FIRST_NAME, name, true);
    type(ELEMENT_NEW_ACCOUNT_LAST_NAME, name, true);
    type(ELEMENT_NEW_ACCOUNT_DISPLAY_NAME, name, true);
    type(ELEMENT_NEW_ACCOUNT_EMAIL, SOCIAL_NETWORKS_ACCOUNT_TWITTER, true);

    click(ELEMENT_NEW_ACCOUNT_SUBSCRIBE_AND_LOGIN_BUTTON);
    waitForAndGetElement(ELEMENT_PLF_HOMEPAGE_DISPLAY);

    /*
     * Step number: 4 Step Name: Step 4: Check information in My profile page
     * Step Description: - Choose User menu -> My profile Input Data: Expected
     * Outcome: - Information of registered account (First Name, Last Name,
     * Email, Avatar) is displayed in My profile page
     */
    info("Go to My Profile");
    navigationToolbar.goToMyProfile();

    // Check email
    waitForAndGetElement(ELEMENT_EMAIL_INFO.replace("${email}", SOCIAL_NETWORKS_ACCOUNT_TWITTER));
    // Check name
    waitForAndGetElement(ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", name));
    // Check avatar
    waitForAndGetElement(ELEMENT_USER_AVATAR);

    /*
     * Step number: 5 Step Name: Step 5: Check information in Account profile
     * page Step Description: - Choose User menu -> Settings-> Open Account
     * profile tab Input Data: Expected Outcome: - Information of registered
     * account (User Name, First Name, Last Name, Display Name, Email) is
     * displayed in Account profile page
     */
    navigationToolbar.goToMySettings();
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_FIRST_NAME.replace("${value}", name));
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_LAST_NAME.replace("${value}", name));
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_DISPLAY_NAME.replace("${value}", name));
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_EMAIL.replace("${value}", SOCIAL_NETWORKS_ACCOUNT_TWITTER));

    // Unlink
    navigationToolbar.goToMySettings();
    userAndGroup.unLinkedSocialAccount(ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_TWITTER);
    button.ok();
    button.close();
  }

  /**
   * <li>Case ID:128346.</li>
   * <li>Test Case Name: Check the registration on the fly in case the social
   * network account is not linked to any eXo user account.</li>
   * <li>Pre-Condition: - - By default, sign up on fly mode is activated for
   * FACEBOOK, GOOGLE, LINKEDIN providers (If want to change the setting for
   * sign up on fly mode, we can setting in exo.properties file) - - Social
   * network account has not authorized eXo to access his user data in the
   * social network - - Social network account is not currently logged in the
   * social network - - Social network account is not linked to any eXo user
   * account - - Delete cookies before test</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @DisplayName("Case ID:128346")
  @Disabled
  @Smoke
  public void test07_CheckTheRegistrationOnTheFlyInCaseTheSocialNetworkAccountIsNotLinkedToAnyEXoUserAccount() {
    info("Test 7: Check the registration on the fly in case the social network account is not linked to any eXo user account");

    /*
     * Step Number: 1 Step Name: Step 1: Open Login page Step Description: -
     * Login page (accessible from /portal/login) - Choose Sign in with a
     * provider that is actived sign up on fly mode (For example: Facebook)
     * Input Data: Expected Outcome: - Login page of chosen social network is
     * displayed
     */

    manageLoginOut.checkGoogleLoginForm();

    /*
     * Step number: 2 Step Name: Step 2: Sign in to social network Step
     * Description: - Enter valid value for social network account to sign in
     * Input Data: Expected Outcome: - OAuth authorization page is displayed
     */

    info("check login with Google account");
    manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_GOOGLE, SOCIAL_NETWORKS_PD);

    info("Check OAuth authorization screen");
    waitForAndGetElement(ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
    waitForAndGetElement(ELEMENT_GOOGLE_PERMISSION_FORM_DENY);

    /*
     * Step number: 3 Step Name: Step 3: Sign in on OAuth authorization page
     * Step Description: - On OAuth authorization page: click Accept button
     * Input Data: Expected Outcome: - The user is authenticated and redirected
     * to eXo.
     */
    Utils.pause(3000);
    click(ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
    waitForAndGetElement(ELEMENT_PLF_HOMEPAGE_DISPLAY);

    /*
     * Step number: 4 Step Name: Step 4: Check information in My profile page
     * Step Description: - Go to My profile of User Input Data: Expected
     * Outcome: - The providers should extract the following user profile
     * attributes from the social network and display them in "My profile" of
     * user+ First Name+ Last Name+ Email
     */
    info("Go to My Profile");
    navigationToolbar.goToMyProfile();

    // Check email
    waitForAndGetElement(ELEMENT_EMAIL_INFO.replace("${email}", SOCIAL_NETWORKS_ACCOUNT_GOOGLE));
    // Check name
    waitForAndGetElement(ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", "exofqagplus fqaplus"));
    // Check avatar
    waitForAndGetElement(ELEMENT_USER_AVATAR);
    /*
     * Step number: 5 Step Name: Step 5: Check information in "Account profile"
     * page Step Description: - Choose User menu -> Settings -> Open Account
     * profile tab Input Data: Expected Outcome: - The providers should extract
     * the following user profile attributes from the social network and display
     * them in "My profile" of user+ User Name+ First Name+ Last Name+ Display
     * Name+ Email
     */
    navigationToolbar.goToMySettings();
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_FIRST_NAME.replace("${value}", "exofqagplus"));
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_LAST_NAME.replace("${value}", "fqaplus"));
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_DISPLAY_NAME.replace("${value}", "exofqagplus fqaplus"));
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_EMAIL.replace("${value}", SOCIAL_NETWORKS_ACCOUNT_GOOGLE));

    // Unlink
    userAndGroup.unLinkedSocialAccount(ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_GOOGLE);
    button.ok();
    button.close();
  }

  /**
   * <li>Case ID:128348.</li>
   * <li>Test Case Name: Check successfully authentication on Existing Account
   * Detected form via using eXo password.</li>
   * <li>Pre-Condition: - - By default, sign up on fly mode is activated for
   * FACEBOOK, GOOGLE, LINKEDIN providers (If want to change the setting for
   * sign up on fly mode, we can setting in exo.properties file) - - Social
   * network account has not authorized eXo to access his user data in the
   * social network - - Social network account is not currently logged in the
   * social network - - Open "Existing Account Detected" form in following
   * cases: + The username extracted from the social network matches an existing
   * eXo user name + The email address extracted from the social network matches
   * an existing user account email + Current browser has recently logged on
   * this eXo server(user name detected via a cookie)</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @DisplayName("Case ID:128348")
  @Disabled
  public void test08_CheckSuccessfullyAuthenticationOnExistingAccountDetectedFormViaUsingEXoPassword() {
    info("Test 8: Check successfully authentication on Existing Account Detected form via using eXo password");

    String firstName = "Mary";
    String lastName = "Williams";
    String name = "Mary Williams";

    // Change user's email
    manageLoginOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS);
    navigationToolbar.goToCommunityManagement();
    userAndGroup.goToEditUserInfo("mary");
    userAndGroup.editUserInfo_AccountTab(null, null, null, SOCIAL_NETWORKS_ACCOUNT_LINKEDIN);

    manageLoginOut.signOut();

    /*
     * Step Number: 1 Step Name: Step 1: Open Login page Step Description: -
     * Login page (accessible from /portal/login) - Choose Sign in with a
     * provider that is actived sign up on fly mode (For example: FACEBOOK)
     * Input Data: Expected Outcome: - Login page of chosen social network is
     * displayed
     */

    manageLoginOut.checkGoogleLoginForm();

    /*
     * Step number: 2 Step Name: Step 2: Sign in to social network Step
     * Description: - Enter valid value of social network account to sign in
     * Input Data: Expected Outcome: - OAuth authorization screen is displayed
     */

    manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_LINKEDIN, SOCIAL_NETWORKS_PD);
    Utils.pause(3000);
    waitForAndGetElement(ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW, 2000, 1);
    waitForAndGetElement(ELEMENT_GOOGLE_PERMISSION_FORM_DENY, 2000, 1);

    /*
     * Step number: 3 Step Name: Step 3: Open "Existing Account Detected" form
     * Step Description: - On OAuth authorization page: click Accept button
     * Input Data: Expected Outcome: - Existing Account Detected form is
     * displayed
     */

    click(ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);

    info("Verify title");
    waitForAndGetElement(ELEMENT_DETECT_ACCOUNT_POPUP);

    info("Verify message");
    waitForAndGetElement(ELEMENT_DETECT_ACCOUNT_MESSAGE.replace("${detectedName}", SOCIAL_NETWORKS_ACCOUNT_LINKEDIN));

    info("Verify password");
    waitForAndGetElement(ELEMENT_NEW_ACCOUNT_PASSWORD);

    info("Verify actions");
    waitForAndGetElement(ELEMENT_DETECT_ACCOUNT_CONFIRM);
    waitForAndGetElement(ELEMENT_DETECT_ACCOUNT_REGISTER);

    /*
     * Step number: 4 Step Name: Step 4: Enter valid password Step Description:
     * - Enter valid password of eXo account - Click "Confirm" button Input
     * Data: Expected Outcome: - The user is authenticated to eXo platform
     * successfully - A link is established between the eXo Platform user
     * account and the social network account.
     */

    type(ELEMENT_NEW_ACCOUNT_PASSWORD, PLFData.DATA_PASS, true);
    waitForAndGetElement(ELEMENT_DETECT_ACCOUNT_CONFIRM);
    click(ELEMENT_DETECT_ACCOUNT_CONFIRM);
    waitForAndGetElement(ELEMENT_PLF_HOMEPAGE_DISPLAY);

    info("Check link established");
    navigationToolbar.goToMySettings();
    userAndGroup.checkLinkedSocialAccount(GateinLocator.ELEMENT_USER_SOCIAL_NETWORKS_TAB_GOOGLE_ACCOUNT,
                                          "${account}",
                                          SOCIAL_NETWORKS_ACCOUNT_LINKEDIN.substring(0,
                                                                                     SOCIAL_NETWORKS_ACCOUNT_LINKEDIN.indexOf('@')));
    button.close();

    /*
     * Step number: 5 Step Name: Step 5: Check information in My profile page
     * Step Description: - Choose User menu - -> My profile Input Data: Expected
     * Outcome: - The information of eXo account (First Name, Last Name, Email,
     * Avatar) are kept
     */

    info("Go to My Profile");
    navigationToolbar.goToMyProfile();

    // Check email
    waitForAndGetElement(ELEMENT_EMAIL_INFO.replace("${email}", SOCIAL_NETWORKS_ACCOUNT_LINKEDIN));
    // Check name
    waitForAndGetElement(ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", "Mary Williams"));
    // Check avatar
    waitForAndGetElement(ELEMENT_USER_AVATAR);

    /*
     * Step number: 6 Step Name: Step 6: Check information in Account profile
     * page Step Description: - Choose User menu - -> Settings - -> Open Account
     * Profile tab Input Data: Expected Outcome: - The information of eXo
     * account (Username, First Name, Last Name, Display Name, Email) are kept
     */
    navigationToolbar.goToMySettings();
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_FIRST_NAME.replace("${value}", firstName));
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_LAST_NAME.replace("${value}", lastName));
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_DISPLAY_NAME.replace("${value}", name));
    waitForAndGetElement(ELEMENT_ACCOUNT_PROFILE_EMAIL.replace("${value}", SOCIAL_NETWORKS_ACCOUNT_LINKEDIN));

    // Unlink
    userAndGroup.unLinkedSocialAccount(ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_GOOGLE);
    button.ok();
    button.close();
  }
}
