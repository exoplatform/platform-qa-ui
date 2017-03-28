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

import org.exoplatform.platform.qa.ui.api.context.Smoke;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.exoplatform.platform.qa.ui.commons.pageobject.Platform;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.junit.jupiter.api.TestInfo;

public class Base extends TestBase {

  public Base() {

  }

  @BeforeAll
  public static void setup() {


  }

  @BeforeEach
  public void openPlatform(TestInfo testInfo) {
    Platform plf = new Platform();
    plf.open();

    // Smoke tests use default data
    if (testInfo.getTags().contains(Smoke.class.getSimpleName().toLowerCase())){
      plf.ensureLicenseIsAccepted().ensureRegisterSoftwareIsSkipped().ensureAccountSetupIsSkipped().ensureUserIsLoggedIn();
    }
  }
}
