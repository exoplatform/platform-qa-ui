/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - Selenium (Legacy Code).
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - Selenium (Legacy Code); if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.selenium.platform;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.locator.ManageLogInOutLocator.ELEMENT_SIGN_OUT_LINK;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.locator.ManageLogInOutLocator;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ManageLogInOut {

  private final TestBase       testBase;

  public ManageAlert           alt;

  public HomePagePlatform homePagePlatform;

  private ElementEventTestBase evt;

  public ManageLogInOut(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alt = new ManageAlert(testBase);
    this.homePagePlatform=new HomePagePlatform(testBase);
  }

  /**
   * Log in to intranet
   *
   * @param username
   * @param password
   * @param opParams
   */
  public void signIn(String username, String password, Boolean... opParams) {
    sleep(2000);
    Boolean verify = (Boolean) (opParams.length > 0 ? opParams[0] : false);
    if ($(ELEMENT_ACCOUNT_NAME_LINK).exists()){
    //if (evt.waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK, 5000, 0) != null) {
      signOut();
    }
    if (testBase.getSsoType() != "" && testBase.getSsoType() != null) {
      SSO sso = SSO.valueOf(testBase.getSsoType().toUpperCase());
      switch (sso) {
      case OPENAM:
        info("login by openam with user " + username + " and pass " + password);
        signInOpenam(username, password);
        break;
      case CAS:
        info("Login by cas with user " + username + " and pass " + password);
        signInCas(username, password);
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_REGISTER_SKIP_BUTTON, 3000, 0, 2) != null) {
        info("-- Skipping register account--");
        evt.click(ELEMENT_REGISTER_SKIP_BUTTON);
        evt.waitForElementNotPresent(ELEMENT_REGISTER_SKIP_BUTTON);
      }
    } else {
      info("login normally if not use SSO with user " + username + " and pass " + password);
      $(ELEMENT_INPUT_USERNAME).waitUntil(Condition.visible,Configuration.collectionsTimeout).setValue(username);
      $(ELEMENT_INPUT_PASSWORD).waitUntil(Condition.visible,Configuration.timeout).setValue(password);
      sleep(2000);
      evt.clickByJavascript(ManageLogInOutLocator.ELEMENT_SIGN_IN_BUTTON, 2);
      if (verify)
        evt.waitForElementNotPresent(ManageLogInOutLocator.ELEMENT_SIGN_IN_BUTTON);
    }
  }

  /**
   * Log in via OpenAM
   *
   * @param username
   * @param password
   */
  public void signInOpenam(String username, String password) {
    sleep(2000);
    testBase.getExoWebDriver().getWebDriver();
    $(ELEMENT_INPUT_USERNAME_OPENAM).setValue(username);
    $(ELEMENT_INPUT_PASSWORD_OPENAM).setValue(password);
    sleep(2000);
    $(ELEMENT_SIGN_IN_BUTTON_OPENAM).click();
    sleep(2000);

  }

  /**
   * Log in via CAS
   *
   * @param username
   * @param password
   */
  public void signInCas(String username, String password) {
    testBase.getExoWebDriver().getWebDriver();
    $(ELEMENT_INPUT_USERNAME).setValue(username);
    $(ELEMENT_INPUT_PASSWORD).setValue(password);
    sleep(2000);
    ELEMENT_SIGN_IN_BUTTON_CAS.click();
    sleep(2000);

  }

  /**
   * Sign out from intranet
   */
  public void signOut() {
    homePagePlatform.refreshUntil($(ELEMENT_ACCOUNT_NAME_LINK),Condition.visible,Configuration.timeout);
    info("Sign out");
    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        evt.mouseOverAndClick(ELEMENT_ACCOUNT_NAME_LINK);
        break;
      }
      sleep(2000);
      $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.appears, Configuration.timeout).click();

      if (evt.waitForAndGetElement(ELEMENT_SIGN_OUT_LINK, 5000, 0) != null) {
        sleep(2000);
        info("Element " + ELEMENT_SIGN_OUT_LINK + "... is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
      testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    }
    sleep(Configuration.timeout);
    $(ELEMENT_SIGN_OUT_LINK).waitUntil(Condition.visible,Configuration.timeout).click();

    if (evt.waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK, 2000, 0) != null) {
      info("Clear cache and reconnect to the package");
      testBase.getExoWebDriver().getWebDriver().manage().deleteAllCookies();
      testBase.getExoWebDriver().getWebDriver();
    }

    if (ExpectedConditions.alertIsPresent() != null) {
      alt = new ManageAlert(testBase);
      alt.acceptAlert();
    }
    WebElement logOutSucess = evt.waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK, 3000, 0);
    if (logOutSucess != null) {
      info("Because issue: in jboss, logout then come back homepage, we have to close IE and init the new one");
      testBase.getExoWebDriver().getWebDriver().manage().deleteAllCookies();
      testBase.getExoWebDriver().getWebDriver();
    } else {
      info("Logout sucessfully");
    }
  }

  /**
   * Sign in as disable user
   *
   * @param user
   * @param pass
   */
  public void signInAsDisableUser(String user, String pass) {
    evt.type(ELEMENT_INPUT_USERNAME, user, true);
    evt.type(ELEMENT_INPUT_PASSWORD, pass, true);
    evt.click(ManageLogInOutLocator.ELEMENT_SIGN_IN_BUTTON, 0, true);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_DISABLE_USER_ERROR_MES, 2000, 1);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_SIGN_IN_BUTTON);
  }

  /**
   * Log in to intranet
   *
   * @param username
   * @param password
   * @param opParams
   */
  public void signInWithoutRefresh(String username, String password, Boolean... opParams) {
    Boolean verify = (Boolean) (opParams.length > 0 ? opParams[0] : false);
    if (evt.waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK, 5000, 0) != null) {
      signOut();
    }
    if (testBase.getSsoType() != "" && testBase.getSsoType() != null) {
      SSO sso = SSO.valueOf(testBase.getSsoType().toUpperCase());
      switch (sso) {
      case OPENAM:
        info("login by openam with user " + username + " and pass " + password);
        signInOpenam(username, password);
        break;
      case CAS:
        info("Login by cas with user " + username + " and pass " + password);
        signInCas(username, password);
        break;
      }
    } else {
      info("login normally if not use SSO with user " + username + " and pass " + password);
      evt.type(ELEMENT_INPUT_USERNAME, username, true);
      evt.type(ELEMENT_INPUT_PASSWORD, password, true);
      evt.click(ManageLogInOutLocator.ELEMENT_SIGN_IN_BUTTON);
      if (verify)
        evt.waitForElementNotPresent(ManageLogInOutLocator.ELEMENT_SIGN_IN_BUTTON);
    }

  }

  /**
   * Check Login screen with social login buttons By: QuyenNT Date: Nov 24, 2015
   */
  public void checkSocialLoginButtons() {
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_SIGN_IN_FACEBOOK_BUTTON);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_SIGN_IN_TWITTER_BUTTON);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_SIGN_IN_LINKEDIN_BUTTON);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_SIGN_IN_GOOGLE_BUTTON);
  }

  /**
   * Go to facebook login form and check elements By: QuyenNT Date: Nov 25, 2015
   */
  public void checkFacebookLoginForm() {
    evt.click(ManageLogInOutLocator.ELEMENT_SIGN_IN_FACEBOOK_BUTTON);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_FACEBOOK_LOGIN_FORM_LOGO);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_FACEBOOK_LOGIN_FORM_USERNAME);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_FACEBOOK_LOGIN_FORM_PASSWORD);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_FACEBOOK_LOGIN_FORM_LOGIN_BUTTON);
  }

  /**
   * Go to TWITTER login form and check elements By: QuyenNT Date: Nov 25, 2015
   */
  public void checkTwitterLoginForm() {
    evt.click(ManageLogInOutLocator.ELEMENT_SIGN_IN_TWITTER_BUTTON);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_TWITTER_LOGIN_FORM_LOGO);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_TWITTER_LOGIN_FORM_USERNAME);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_TWITTER_LOGIN_FORM_PASSWORD);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_TWITTER_LOGIN_FORM_LOGIN_BUTTON);
  }

  /**
   * Go to LINKEDIN login form and check elements By: QuyenNT Date: Nov 25, 2015
   */
  public void checkLinkedinLoginForm() {
    evt.click(ManageLogInOutLocator.ELEMENT_SIGN_IN_LINKEDIN_BUTTON);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_LINKEDIN_LOGIN_FORM_LOGO);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_LINKEDIN_LOGIN_FORM_USERNAME);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_LINKEDIN_LOGIN_FORM_PASSWORD);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_LINKEDIN_LOGIN_FORM_LOGIN_BUTTON);
  }

  /**
   * Go to GOOGLE login form and check elements By: QuyenNT Date: Nov 25, 2015
   */
  public void checkGoogleLoginForm() {
    evt.click(ManageLogInOutLocator.ELEMENT_SIGN_IN_GOOGLE_BUTTON);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_GOOGLE_LOGIN_FORM_LOGO);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_GOOGLE_LOGIN_FORM_USERNAME);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_GOOGLE_LOGIN_FORM_NEXT_BUTTON);
  }

  /**
   * Login platform with facebook account By: QuyenNT Date: Nov 25, 2015
   */
  public void loginWithFacebookAccount(String userName, String password) {
    info("login with facebook account:" + userName + " and pass " + password);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_FACEBOOK_LOGIN_FORM_USERNAME);

    evt.type(ManageLogInOutLocator.ELEMENT_FACEBOOK_LOGIN_FORM_USERNAME, userName, true);
    evt.type(ManageLogInOutLocator.ELEMENT_FACEBOOK_LOGIN_FORM_PASSWORD, password, true);

    evt.click(ManageLogInOutLocator.ELEMENT_FACEBOOK_LOGIN_FORM_LOGIN_BUTTON);
  }

  /**
   * Login platform with twitter account By: QuyenNT Date: Nov 25, 2015
   */
  public void loginWithTwitterAccount(String userName, String password) {
    info("login with twitter account:" + userName + " and pass " + password);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_TWITTER_LOGIN_FORM_USERNAME);
    evt.click(ManageLogInOutLocator.ELEMENT_TWITTER_LOGIN_FORM_USERNAME);

    evt.type(ManageLogInOutLocator.ELEMENT_TWITTER_LOGIN_FORM_USERNAME, userName, false);
    evt.type(ManageLogInOutLocator.ELEMENT_TWITTER_LOGIN_FORM_PASSWORD, password, false);

    evt.click(ManageLogInOutLocator.ELEMENT_TWITTER_LOGIN_FORM_LOGIN_BUTTON);
  }

  /**
   * Login platform with Linkedin account By: QuyenNT Date: Nov 25, 2015
   */
  public void loginWithLinkedinAccount(String userName, String password) {
    info("login with Linkedin account:" + userName + " and pass " + password);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_LINKEDIN_LOGIN_FORM_USERNAME);

    evt.type(ManageLogInOutLocator.ELEMENT_LINKEDIN_LOGIN_FORM_USERNAME, userName, true);
    evt.type(ManageLogInOutLocator.ELEMENT_LINKEDIN_LOGIN_FORM_PASSWORD, password, true);
    evt.click(ManageLogInOutLocator.ELEMENT_LINKEDIN_LOGIN_FORM_LOGIN_BUTTON);
  }

  /**
   * Login platform with Google account By: QuyenNT Date: Nov 25, 2015
   */
  public void loginWithGoogleAccount(String userName, String password) {
    info("login with Google account:" + userName + " and pass " + password);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_GOOGLE_LOGIN_FORM_USERNAME);

    evt.type(ManageLogInOutLocator.ELEMENT_GOOGLE_LOGIN_FORM_USERNAME, userName, true);
    evt.waitForAndGetElement(ManageLogInOutLocator.ELEMENT_GOOGLE_LOGIN_FORM_NEXT_BUTTON);
    evt.click(ManageLogInOutLocator.ELEMENT_GOOGLE_LOGIN_FORM_NEXT_BUTTON);

    evt.type(ManageLogInOutLocator.ELEMENT_GOOGLE_LOGIN_FORM_PASSWORD, password, true);
    evt.click(ManageLogInOutLocator.ELEMENT_GOOGLE_LOGIN_FORM_LOGIN_BUTTON);
  }

  public enum SSO {
    OPENAM, CAS;
  }

}
