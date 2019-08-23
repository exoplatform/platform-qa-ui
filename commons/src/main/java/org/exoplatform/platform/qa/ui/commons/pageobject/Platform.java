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
package org.exoplatform.platform.qa.ui.commons.pageobject;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.error;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_PASSWORD;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOP_TOOLBAR_PORTLET;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOP_TOOLBAR_MENU_USER;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOP_TOOLBAR_MENU_USER_LOGOUT;

import org.openqa.selenium.By;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

/**
 * Created by mgreau on 23/01/2017.
 */
public class Platform {

  public final String          URI                 = "/portal";

  public final SelenideElement licenseAgreement    = $("#agreement");

  public final SelenideElement registration        = $(".plf-registration");

  public final SelenideElement accountSetup        = $("#AccountSetup");

  public final SelenideElement signinContainer     = $("div.loginContainer");

  public final SelenideElement signinFailContainer = $("div.signinFail");

  public Platform open() {
    return Selenide.open(URI, Platform.class);
  }

  /**
   * It ensures that, if the license screen appears, the license agreement will be
   * accepted. If this screen doesn't appear, there is no exception.
   */
  public Platform ensureLicenseIsAccepted() {
    boolean alreadySkipped = false;
    try {
      alreadySkipped = !licenseAgreement.exists();
    } catch (Exception ex) {
      error("License skip exception " + ex.getStackTrace());
    }
    if (alreadySkipped == false) {
      info("Skip the License ");
      acceptLicense();
    }
    return this;
  }

  /**
   * Accept the eXo Platform license
   * 
   * @return Platform page object
   */
  public Platform acceptLicense() {
    licenseAgreement.parent().click();
    $("#continueButton").click();
    return this;

  }

  /**
   * Ensure that the Register Software UI is skipped.
   * 
   * @return Platform page object
   */
  public Platform ensureRegisterSoftwareIsSkipped() {

    if (isNeededSoftwareRegistration()) {
      info("Skip Registration screen");
      return skipSoftwareRegistration();
    } else {
      return this;
    }
  }

  /**
   * Skip the Software Registration screen.
   *
   * @return Platform page object
   */
  private Platform skipSoftwareRegistration() {

    // Click on the label because the checkbox is not visible
    // '<input class="checkbox" id="agreement" name="checktc"
    // onclick="toggleState();" type="checkbox" value="false"
    // displayed:false></input>'

    $(By.name("btnSkip")).click();
    $(By.name("setupbutton")).click();

    return this;
  }

  /**
   * Check if Software Registration process is needed
   * 
   * @return
   */
  public boolean isNeededSoftwareRegistration() {
    return registration.exists();
  }

  /**
   * Ensure that the Account Setup UI is skipped.
   * 
   * @return Platform page object
   */
  public Platform ensureAccountSetupIsSkipped() {
    if (isNeededAccountSetup()) {
      info("Skip Account setup screen");
      return skipAccountSetup();
    } else {
      return this;
    }
  }

  /**
   * Check if Account Setup process is needed
   * 
   * @return
   */
  public boolean isNeededAccountSetup() {
    return accountSetup.exists();
  }

  /**
   * Skip the Account Setup UI.
   *
   * @return Platform page object
   */
  private Platform skipAccountSetup() {
    $(By.name("setupbutton")).click();
    return this;
  }

  /**
   * Ensure a user is currently logged-in
   * 
   * @return Platform page object
   */
  public Platform ensureUserIsLoggedIn() {
    if (isUserLogged() == false) {
      return signIn();
    }
    return this;
  }

  /**
   * Check if a user is logged-in
   * 
   * @return
   */
  public boolean isUserLogged() {
    return $("#UIUserPlatformToolBarPortlet").exists();
  }

  /**
   * SignIn with default eXo Root Credentials
   * 
   * @return Platform page object
   */
  public Platform signIn() {
    return this.signIn(PLFData.username, PLFData.password);
  }

  /**
   * SignIn with a specific User and Password
   *
   * @param user the username to use to sign-in PLF
   * @param password the password associated with the user
   * @return Platform page object
   */
  public Platform signIn(final String user, final String password) {
    Temporal start = LocalDateTime.now();
    info("Sign in with '" + user + "' username");
    $(ELEMENT_INPUT_USERNAME).setValue(user);
    $(ELEMENT_INPUT_PASSWORD).setValue(password);
    $(".button").click();
    info("Sign in in " + Duration.between(start, LocalDateTime.now()).toString());
    return this;
  }

  /**
   * Sign-out from eXo Platform
   * 
   * @return Platform page object
   */
  public Platform signOut() {
    Temporal start = LocalDateTime.now();
    info("Sign out");
    ELEMENT_TOP_TOOLBAR_PORTLET.exists();
    ELEMENT_TOP_TOOLBAR_MENU_USER.click();
    ELEMENT_TOP_TOOLBAR_MENU_USER_LOGOUT.click();
    info("Sign out in " + Duration.between(start, LocalDateTime.now()).toString());
    return this;
  }

}
