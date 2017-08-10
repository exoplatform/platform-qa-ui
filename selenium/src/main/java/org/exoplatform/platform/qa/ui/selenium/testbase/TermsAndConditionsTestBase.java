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

import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.*;

import org.exoplatform.platform.qa.ui.selenium.TestBase;

public class TermsAndConditionsTestBase {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  private AccountSetupTestBase acc;

  public TermsAndConditionsTestBase(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.acc = new AccountSetupTestBase(testBase);
  }

  /**
   * Check term and conditions
   *
   * @param opParams
   */
  public void termsAndConditions(Object... opParams) {
    info("Term and conditions");
    Boolean isCreateAccount = (Boolean) (opParams.length > 0 ? opParams[0] : true);
    testBase.getExoWebDriver().getWebDriver();
    info("Agreement page");
    if (evt.waitForAndGetElement(ELEMENT_REGISTER_SKIP_BTN, testBase.getDefaultTimeout(), 2) != null) {
      info("Skipp register");
      evt.clickByJavascript(ELEMENT_REGISTER_SKIP_BTN, 2);

    }
    if (evt.waitForAndGetElement(ELEMENT_AGREEMENT_CHECKBOX, 3000, 0, 2) != null) {
      info("-- Checking the terms and conditions agreement... --");
      evt.click(ELEMENT_AGREEMENT_CHECKBOX, 2);
      evt.click(ELEMENT_CONTINUE_BUTTON);
      evt.waitForTextNotPresent("terms and conditions agreement");

      if (evt.waitForAndGetElement(ELEMENT_REGISTER_SKIP_BUTTON, 3000, 0, 2) != null) {
        info("-- Skipping register account--");
        evt.click(ELEMENT_REGISTER_SKIP_BUTTON);
        evt.waitForElementNotPresent(ELEMENT_REGISTER_SKIP_BUTTON);
      }

    } else if (evt.waitForAndGetElement(ELEMENT_REGISTER_SKIP_BUTTON, 3000, 0, 2) != null) {
      info("-- Skipping register account--");
      info("Click on Continue button");
      if (evt.waitForAndGetElement(ELEMENT_CONTINUE_BTN, 3000, 0, 2) != null) {
        evt.click(ELEMENT_CONTINUE_BTN);
        evt.waitForElementNotPresent(ELEMENT_CONTINUE_BTN);
      } else {
        evt.click(ELEMENT_REGISTER_SKIP_BUTTON);
        evt.waitForElementNotPresent(ELEMENT_REGISTER_SKIP_BUTTON);
      }

    }
    if (evt.waitForAndGetElement(ELEMENT_ROOT_PASS_ACCOUNT, 3000, 0, 2) != null) {
      info("-- Creating an Admin account: FQA... --");
      if (isCreateAccount == true) {
        acc.accountSetup();
        info("-- Administrator account (FQA) has been created successfully... --");
        testBase.getExoWebDriver().getWebDriver().navigate().refresh();
      }
    }

    info("End of term and conditions");
  }
}
