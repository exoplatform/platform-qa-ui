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
package org.exoplatform.platform.qa.ui.core.oauth.functional;

import org.exoplatform.platform.qa.ui.core.Base;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.gatein.UserAndGroupManagement;
import org.junit.jupiter.api.*;

import static org.exoplatform.platform.qa.ui.core.PLFData.PASS_ROOT;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_PLF_HOMEPAGE_DISPLAY;
import static org.exoplatform.platform.qa.ui.selenium.locator.ManageLogInOutLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_USER_SOCIAL_NETWORKS_TAB;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class LoginScreens extends Base {

  ManageLogInOut manageLoginOut;

  NavigationToolbar navigationToolbar;

  UserAndGroupManagement userAndGroup;

  Button button;

  @BeforeEach
  public void beforeEach() throws Exception {
    info("Start setUpBeforeMethod");

    initSeleniumTest();

    manageLoginOut = new ManageLogInOut(this);

    navigationToolbar = new NavigationToolbar(this);
    userAndGroup = new UserAndGroupManagement(this);
    button = new Button(this);
  }

  @AfterEach
  public void afterMethod() {
    getSeleniumDriver().manage().deleteAllCookies();
    getSeleniumDriver().quit();
  }

  /**
   * <li> Case ID:128289.</li>
   * <li> Test Case Name: Check sign-in buttons in portal login screen.</li>
   * <li> Pre-Condition: </li>
   * <li> Post-Condition: </li>
   */
  @Test
  @DisplayName("Case ID:128289")
  public void test02_CheckSigninButtonsInPortalLoginScreen() {
    info("Test 2: Check sign-in buttons in portal login screen");
  /*Step Number: 1
  *Step Name: Step 1: Open Login page
  *Step Description:
    - Open Login page (/portal/login)
  *Input Data:

  *Expected Outcome:
    - Login screen is displayed
    - 4 sign up buttons are displayed : Facebook, Google, LinkedIn, Twitter*/
    waitForAndGetElement(ELEMENT_SIGN_IN_BUTTON);
    manageLoginOut.checkSocialLoginButtons();


  /*Step number: 2
  *Step Name: Step 2: Mouse over on buttons
  *Step Description:
    - Move the mouse over each button (Facebook, Google, LinkedIn, Twitter)
  *Input Data:

  *Expected Outcome:
    - The button is displayed with a highlighting effect*/
    info("Check highlight effect");
    mouseOver(ELEMENT_SIGN_IN_FACEBOOK_BUTTON, true);

    String css = waitForAndGetElement(ELEMENT_SIGN_IN_FACEBOOK_BUTTON, 2000, 0).getCssValue("background-image");
    if (css.contains("oauthIconHover.png")) {
      assert true;
    } else {
      assert false : "facebook is not highlighted";
    }

    //Check twitter
    mouseOver(ELEMENT_SIGN_IN_TWITTER_BUTTON, true);

    css = waitForAndGetElement(ELEMENT_SIGN_IN_TWITTER_BUTTON, 2000, 0).getCssValue("background-image");
    if (css.contains("oauthIconHover.png")) {
      assert true;
    } else {
      assert false : "twitter is not highlighted";
    }

    //Check linkedin
    mouseOver(ELEMENT_SIGN_IN_LINKEDIN_BUTTON, true);

    css = waitForAndGetElement(ELEMENT_SIGN_IN_LINKEDIN_BUTTON, 2000, 0).getCssValue("background-image");
    if (css.contains("oauthIconHover.png")) {
      assert true;
    } else {
      assert false : "Linkedin is not highlighted";
    }
    //Check Google
    mouseOver(ELEMENT_SIGN_IN_GOOGLE_BUTTON, true);

    css = waitForAndGetElement(ELEMENT_SIGN_IN_GOOGLE_BUTTON, 2000, 0).getCssValue("background-image");
    if (css.contains("oauthIconHover.png")) {
      assert true;
    } else {
      assert false : "Goolge is not highlighted";
    }

  }

  /**
   * <li> Case ID:128290.</li>
   * <li> Test Case Name: Check buttons of social network after add/delete of provider.</li>
   * <li> Pre-Condition: </li>
   * <li> Post-Condition: </li>
   */
  @Test
  @DisplayName("Case ID:128290")
  @Disabled
  public void test03_CheckButtonsOfSocialNetworkAfterAdddeleteOfProvider() {
    info("Test 3: Check buttons of social network after add/delete of provider");
  /*Step Number: 1
  *Step Name: Step 1: Open Login screen
  *Step Description:
    - Connect to portal
  *Input Data:

  *Expected Outcome:
    - Login screen is displayed
    - 4 sign up buttons are displayed: Facebook, Google, LinkedIn, Twitter*/


  /*Step number: 2
  *Step Name: Step 2: Deactivate providers
  *Step Description:
    - Deactivate providers from the file exo.properties
    - Open Login screen
  *Input Data:

  *Expected Outcome:
    - The button related to provider is not displayed*/

    waitForElementNotPresent(ELEMENT_SIGN_IN_FACEBOOK_BUTTON);
    waitForElementNotPresent(ELEMENT_SIGN_IN_TWITTER_BUTTON);
    waitForElementNotPresent(ELEMENT_SIGN_IN_LINKEDIN_BUTTON);
    waitForElementNotPresent(ELEMENT_SIGN_IN_GOOGLE_BUTTON);

  /*Step number: 3
  *Step Name: Step 3: Activate providers
  *Step Description:
    - Activate providers from the file configuration.properties
    - Open Login screen
  *Input Data:

  *Expected Outcome:
    - The button related to provider is displayed*/

  }

  /**
   * <li> Case ID:128291.</li>
   * <li> Test Case Name: Check sign-in to exo platform with a social network login form.</li>
   * <li> Pre-Condition: -
   * - User has an established link to a social network AND an eXo account
   * -
   * - Make an user has an established link to a social network as following:
   * - Login with an user with an eXo account
   * - Click on the Display Name of user on administration bar
   * -
   * -> Choose Settings
   * -
   * -> Open Social Network tab: Click on "Link to social account" of coresponding provider (Facebook, Google, LinkedIn, Twitter)</li>
   * <li> Post-Condition: </li>
   */
  @Test
  @DisplayName("Case ID:128291")
  public void test04_CheckSigninToExoPlatformWithASocialNetworkLoginForm() {
    info("Test 4: Check sign-in to exo platform with a social network login form");

    //Pre-condition
    manageLoginOut.signIn("fqa", PASS_ROOT);
    navigationToolbar.goToMySettings();
    waitForAndGetElement(ELEMENT_USER_SOCIAL_NETWORKS_TAB);
    click(ELEMENT_USER_SOCIAL_NETWORKS_TAB);

    waitForAndGetElement(ELEMENT_ACCOUNT_LINK_TO_SOCIAL_GOOGLE);
    click(ELEMENT_ACCOUNT_LINK_TO_SOCIAL_GOOGLE);

    manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_GOOGLE, SOCIAL_NETWORKS_PD);
    Utils.pause(3000);

    click(ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);

    waitForAndGetElement(ELEMENT_ACCOUNT_LINK_TO_SOCIAL_INFO.replace("$network}", "Google+")
                                                            .replace("$account}", "fqa"));
    button.ok();
    button.close();

    manageLoginOut.signOut();

    /** FIXME
     afterMethod();
     try {
     setupBeforeMethod();
     } catch (Exception e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     }
     **/

  /*Step Number: 1
  *Step Name: Step 1: Open Login page
  *Step Description:
    - Open Login page (accessible from /portal/login)
  *Input Data:

  *Expected Outcome:
    - The Login screen is displayed
    - 4 sign up buttons are displayed : Facebook, Google, LinkedIn, Twitter*/
    info("Check social buttons");
    waitForAndGetElement(ELEMENT_SIGN_IN_BUTTON);
    manageLoginOut.checkSocialLoginButtons();

  /*Step number: 2
  *Step Name: Step 2: Choose a social network
  *Step Description:
    - Click on social network button
  *Input Data:

  *Expected Outcome:
    - Login page of the chosen social network is displayed*/

    info("Check twitter login form");
    manageLoginOut.checkTwitterLoginForm();

    info("Back to the platform login page");
    getSeleniumDriver().navigate().back();
    Utils.pause(2000);

    info("Check linkedin login form");
    manageLoginOut.checkLinkedinLoginForm();

    info("Back to the platform login page");
    getSeleniumDriver().navigate().back();
    Utils.pause(2000);

    info("Check facebook login form");
    manageLoginOut.checkFacebookLoginForm();

    info("Back to the platform login page");
    getSeleniumDriver().navigate().back();

    info("Check google login form");
    manageLoginOut.checkGoogleLoginForm();

  /*Step number: 3
  *Step Name: Step 3: Input user credentials
  *Step Description:
    - Input user credentials (social account)
  *Input Data:

  *Expected Outcome:
    - The user is signed in exoplatform with social account*/
    info("check login with Facebook account");
    manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_GOOGLE, SOCIAL_NETWORKS_PD);
    Utils.pause(3000);
    click(ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
    Utils.pause(3000);
    waitForAndGetElement(ELEMENT_PLF_HOMEPAGE_DISPLAY);
  }

  /**
   * <li> Case ID:128292.</li>
   * <li> Test Case Name: Check sign in to exo with exo signin form.</li>
   * <li> Pre-Condition: -
   * - User has an established link to a social network AND an eXo account
   * -
   * - Make an user has an established link to a social network as following:
   * - Login with an user with an eXo account
   * - Click on the Display Name of user on administration bar
   * -
   * -> Choose Settings
   * -
   * -> Open Social Network tab: Click on "Link to social account" of coresponding provider (Facebook, Google, LinkedIn, Twitter)</li>
   * <li> Post-Condition: </li>
   */
  @Test
  @DisplayName("Case ID:128292")
  public void test05_CheckSignInToExoWithExoSigninForm() {
    info("Test 5: Check sign in to exo with exo signin form");
    //Pre-condition
    manageLoginOut.signIn("fqa", PASS_ROOT);
    navigationToolbar.goToMySettings();
    waitForAndGetElement(ELEMENT_USER_SOCIAL_NETWORKS_TAB);
    click(ELEMENT_USER_SOCIAL_NETWORKS_TAB);

    waitForAndGetElement(ELEMENT_ACCOUNT_LINK_TO_SOCIAL_LINKEDIN);
    click(ELEMENT_ACCOUNT_LINK_TO_SOCIAL_LINKEDIN);

    manageLoginOut.loginWithLinkedinAccount(SOCIAL_NETWORKS_ACCOUNT_LINKEDIN, SOCIAL_NETWORKS_PD);
    Utils.pause(3000);
    waitForAndGetElement(ELEMENT_ACCOUNT_LINK_TO_SOCIAL_INFO.replace("$network}", "LinkedIn")
                                                            .replace("$account}", "fqa"));
    button.ok();
    button.close();

    manageLoginOut.signOut();

  /*Step Number: 1
  *Step Name: Step 1: Open Login screen
  *Step Description:
    - Open Login page (accessible from /portal/login)
  *Input Data:

  *Expected Outcome:
    - The Login screen is displayed
    - 4 sign up buttons are displayed : Facebook, Google, LinkedIn, Twitter*/
    waitForAndGetElement(ELEMENT_SIGN_IN_BUTTON);
    manageLoginOut.checkSocialLoginButtons();

  /*Step number: 2
  *Step Name: Step 2: Sign in with eXo account
  *Step Description:
    - Input user credentials (eXo account) in exoplatform sign in form
  *Input Data:

  *Expected Outcome:
    - The user is signed in eXo platform*/

    manageLoginOut.signIn("fqa", PASS_ROOT);
    Utils.pause(3000);
    waitForAndGetElement(ELEMENT_PLF_HOMEPAGE_DISPLAY);
  }

}
