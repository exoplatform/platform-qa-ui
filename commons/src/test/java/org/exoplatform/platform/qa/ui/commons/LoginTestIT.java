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
package org.exoplatform.platform.qa.ui.commons;

import org.exoplatform.platform.qa.ui.commons.pageobject.Login;
import org.exoplatform.platform.qa.ui.commons.pageobject.Platform;
import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Tag("login")
public final class LoginTestIT extends Base {

  public LoginTestIT() {
    super();
  }

  @Test
  @Smoke
  public void signIn() {
    // Init instance for signInTest
    Platform plf = new Platform();
    plf.open();
    plf.ensureLicenseIsAccepted().ensureRegisterSoftwareIsSkipped().ensureAccountSetupIsSkipped();

    assertTrue("User should be logged", plf.signIn().isUserLogged());
  }

  /**
   * Signin with a user that do not exist in the system.
   *
   * <p>
   * This test should display an error message in the Login Container.
   * </p>
   */
  @Test
  public void signInWithUnknownUser() {
    // Init instance for signInTest
    Platform plf = new Platform();
    plf.open();
    plf.ensureLicenseIsAccepted().ensureRegisterSoftwareIsSkipped().ensureAccountSetupIsSkipped();

    if (plf.isUserLogged()){
      plf.signOut();
    }

    plf.signIn("failTo", "logIn");

    assertFalse("User should not be logged", plf.isUserLogged());
    assertTrue("SignIn with unknown user should display a message.", plf.signinFailContainer.exists());
  }

}
