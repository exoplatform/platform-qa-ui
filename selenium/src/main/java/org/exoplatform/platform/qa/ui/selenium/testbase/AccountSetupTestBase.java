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
package org.exoplatform.platform.qa.ui.selenium.testbase;

import org.exoplatform.platform.qa.ui.selenium.TestBase;

public class AccountSetupTestBase {
  private final TestBase testBase;

  public AccountSetupTestBase(TestBase testBase) {
    this.testBase = testBase;
  }

  /**
   * Create new first account
   */
  public void accountSetupWithoutGreeting() {
    testBase.getElementEventTestBase().click(LocatorTestBase.ELEMENT_INPUT_USERNAME);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_INPUT_USERNAME, "fqa", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_LASTNAME_ACCOUNT, "VN", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_INPUT_PASSWORD, "gtngtn", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_ROOT_PASS_ACCOUNT, "gtngtn", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);
    // click(LocatorTestBase.ELEMENT_SUBMIT_BUTTON);
    testBase.getElementEventTestBase().clickByJavascript(LocatorTestBase.ELEMENT_SUBMIT_BUTTON, 2);
    testBase.getElementEventTestBase().waitForTextNotPresent("Create your account");
  }

  /**
   * Account setup
   */
  public void accountSetup() {
    accountSetupWithoutGreeting();
    testBase.getElementEventTestBase().click(LocatorTestBase.ELEMENT_START_BUTTON, 0, true);
    testBase.getElementEventTestBase().waitForAndGetElement(LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK);
  }
}
