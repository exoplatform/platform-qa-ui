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
package org.exoplatform.platform.qa.ui.selenium;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class ManageAlert {
  // ECMS > Symlink
  public final By ELEMENT_ALERT = By.xpath("//*[contains(@class, 'popupTitle') and contains(text(), 'Warning')]");

  public final By ELEMENT_MESSAGE = By.xpath("//*[contains(@class, 'warningIcon')]");

  public final By ELEMENT_INFO = By.xpath("//*[contains(@class, 'infoIcon')]");

  public final By ELEMENT_CONFIRM = By.xpath("//*[contains(@class, 'confirmationIcon')]");

  private final TestBase testBase;

  private Button button;

  private ElementEventTestBase evt;

  private WebDriver seleniumWebDriver;

  public ManageAlert(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.seleniumWebDriver = testBase.getExoWebDriver().getWebDriver();
    this.button = new Button(testBase);
  }

  /**
   * accept alert
   */
  public void acceptAlert() {
    try {
      Alert alert = seleniumWebDriver.switchTo().alert();
      alert.accept();
      evt.switchToParentWindow();
    } catch (NoAlertPresentException e) {
    }
    Utils.pause(1000);
  }

  /**
   * Cancel alert
   */
  public void cancelAlert() {
    try {
      Alert alert = seleniumWebDriver.switchTo().alert();
      alert.dismiss();
      evt.switchToParentWindow();
    } catch (NoAlertPresentException e) {
    }
    Utils.pause(1000);
  }

  /**
   * Get text from alert
   *
   * @return text from alert
   */
  public String getTextFromAlert() {
    Utils.pause(1000);
    try {
      Alert alert = seleniumWebDriver.switchTo().alert();
      return alert.getText();
    } catch (NoAlertPresentException e) {
      return "";
    }
  }

  /**
   * wait for confirmation
   *
   * @param confirmationText
   * @param wait             wait[0]: timeout
   */
  public void waitForConfirmation(String confirmationText, int... wait) {
    String message = getTextFromAlert();
    System.out.println(message);
    System.out.println(confirmationText);
    int timeOut = wait.length > 0 ? wait[0] : testBase.getDefaultTimeout();
    if (message.isEmpty()) {
      if (testBase.loopCount > timeOut / 500) {
        Assert.fail("Message is empty");
      }
      Utils.pause(500);
      testBase.loopCount++;
      waitForConfirmation(confirmationText);
      return;
    }

    for (int second = 0; ; second++) {
      if (second >= timeOut) {
        Assert.fail("Timeout at waitForConfirmation: " + confirmationText);
      }
      if (message.contains(confirmationText)) {
        break;
      }

      Utils.pause(100);
    }
    Alert alert = seleniumWebDriver.switchTo().alert();
    alert.accept();
    Utils.pause(3000);
  }

  /**
   * Verify Alert Message
   *
   * @param message
   */
  public void verifyAlertMessage(String message) {
    Utils.pause(1000);
    if (evt.isElementPresent(ELEMENT_MESSAGE)) {
      assert evt.getText(ELEMENT_MESSAGE).contains(message) : "Message is wrong. Actual msg is " + evt.getText(ELEMENT_MESSAGE);
    } else if (evt.isElementPresent(ELEMENT_INFO)) {
      assert evt.getText(ELEMENT_INFO).contains(message) : "Message is wrong. Actual msg is " + evt.getText(ELEMENT_INFO);
    } else if (evt.isElementPresent(ELEMENT_CONFIRM)) {
      assert evt.getText(ELEMENT_CONFIRM).contains(message) : "Message is wrong. Actual msg is " + evt.getText(ELEMENT_CONFIRM);
    }
    if ($(button.ELEMENT_OK_BUTTON).is(Condition.exist)) {
      $(button.ELEMENT_OK_BUTTON).click();
    }
    if ($(button.ELEMENT_YES_BUTTON).is(Condition.exist)) {
      $(button.ELEMENT_YES_BUTTON).click();
    }
    Utils.pause(1000);
  }

  /**
   * Input Alert Text
   *
   * @param text
   */
  public void inputAlertText(String text) {
    try {
      Alert alert = seleniumWebDriver.switchTo().alert();
      alert.sendKeys(text);
      alert.accept();
      evt.switchToParentWindow();
    } catch (NoAlertPresentException e) {
    }
    Utils.pause(1000);
  }
}
