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

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.locator.ManageLogInOutLocator;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

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
    Boolean verify = (Boolean) (opParams.length > 0 ? opParams[0] : false);
    if ($(ELEMENT_ACCOUNT_NAME_LINK).exists()){
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
      $(ELEMENT_INPUT_USERNAME).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(username);
      $(ELEMENT_INPUT_PASSWORD).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(password);
      evt.clickByJavascript(ManageLogInOutLocator.ELEMENT_SIGN_IN_BUTTON, 2);
      if (verify)
        evt.waitForElementNotPresent(ManageLogInOutLocator.ELEMENT_SIGN_IN_BUTTON);
    }
  }

  public void signInDigitalWorkplace(String username, String password, Boolean... opParams) {
    Boolean verify = (Boolean) (opParams.length > 0 ? opParams[0] : false);
      signOutTribe();
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
      $(ELEMENT_INPUT_USERNAME).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(username);
      $(ELEMENT_INPUT_PASSWORD).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(password);
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
    testBase.getExoWebDriver().getWebDriver();
    $(ELEMENT_INPUT_USERNAME_OPENAM).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(username);
    $(ELEMENT_INPUT_PASSWORD_OPENAM).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(password);
    $(ELEMENT_SIGN_IN_BUTTON_OPENAM).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
  }


  /**
   * Log in to Tribe
   *
   * @param username
   * @param password
   */

  public void signInTribe(String username, String password) {
    info("login normally with user " + username + " and pass " + password);

    if (ELEMENT_TRIBE_TOOLBAR.exists()){
      signOutTribe();
    }
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    open(baseUrl);
    ELEMENT_TRIBE_COMMUNITY_NAVIGATION_SIGN_IN.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    USERNAME_TRIBE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(username);
    PASSWORD_TRIBE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(password);
    ELEMENT_TRIBE_SIGN_IN.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

  }

  /**
   * Log in to Tribe with Google
   *
   * @param mail
   */

  public void signInTribeWithGoogle(String mail, String username, String password) {
    info("login with Google with mail " + mail);
    if (ELEMENT_TRIBE_TOOLBAR.exists()){
      signOutTribe();
    }
    ELEMENT_TRIBE_COMMUNITY_NAVIGATION_SIGN_IN.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TRIBE_SIGN_IN_WITH_GOOGLE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    MAIL_TRIBE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(mail);
    NEXT_MAIL_TRIBE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    USERNAME_ATLASSIAN.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(username);
    PASSWORD_ATLASSIAN.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(password);
    ELEMENT_ATLASSIAN_SIGN_IN.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_ATLASSIAN_CONTINUE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_TRIBE_TOOLBAR).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

  }

  /**
   * Log in via CAS
   *
   * @param username
   * @param password
   */
  public void signInCas(String username, String password) {
    testBase.getExoWebDriver().getWebDriver();
    $(ELEMENT_INPUT_USERNAME).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(username);
    $(ELEMENT_INPUT_PASSWORD).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(password);
    ELEMENT_SIGN_IN_BUTTON_CAS.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

  }

  /**
   * Sign out from intranet
   */
  public void signOutTribe() {
    Temporal start = LocalDateTime.now();
    info("Sign out");
    ELEMENT_TRIBE_VERTICAL_SIDEBAR_MENU.waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TRIBE_SIGN_OUT.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Sign out in " + Duration.between(start, LocalDateTime.now()).toString());

  }

  /**
   * Sign out from intranet
   */
  public void signOut() {
    Temporal start = LocalDateTime.now();
    info("Sign out");
    ELEMENT_TOP_TOOLBAR_PORTLET.waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    ELEMENT_TOP_TOOLBAR_MENU_USER.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_TOP_TOOLBAR_MENU_USER_LOGOUT.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

    info("Sign out in " + Duration.between(start, LocalDateTime.now()).toString());

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

  public enum SSO {
    OPENAM, CAS;
  }

}
