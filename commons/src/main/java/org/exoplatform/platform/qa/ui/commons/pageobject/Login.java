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

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_PASSWORD;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;


public class Login {

  public final SelenideElement container = $(By.cssSelector("loginContainer"));

  /** HTML container to display information when the signin action has failed **/
  public final SelenideElement signinFailContainer = $(By.cssSelector("signinFail"));

  public Login() {
  }


  public boolean isUserLogged(){
    return $(byId("UIUserPlatformToolBarPortlet")).exists();

  }

  /**
   * SignIn with default eXo Root Credentials
   */
  public Login signIn() {
    return this.signIn(PLFData.username, PLFData.password);
  }

  /**
   * SignIn with a specific User and Password
   *
   * @param user the username to use to sign-in PLF
   * @param password the password associated with the user
   */
  public Login signIn(final String user, final String password) {
    $(ELEMENT_INPUT_USERNAME).setValue(user);
    $(ELEMENT_INPUT_PASSWORD).setValue(password);
    $(byClassName("button")).click();

    return this;
  }


  public Login signOut() {
    sleep(Configuration.timeout);
    $(byId("UIUserPlatformToolBarPortlet")).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(Configuration.collectionsTimeout);
    $(byClassName("uiIconPLFLogout")).waitUntil(Condition.visible,Configuration.timeout).click();

    return this;
  }

}
