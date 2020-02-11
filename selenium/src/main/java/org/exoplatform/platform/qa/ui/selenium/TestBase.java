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
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import org.exoplatform.platform.qa.ui.core.config.driver.Driver;
import org.exoplatform.platform.qa.ui.core.config.driver.ExoWebDriver;
import org.exoplatform.platform.qa.ui.selenium.testbase.*;

public class TestBase {

  public final int                         ACTION_REPEAT              = 5;

  /**
   * REFACTORING: Use delegate to new classes
   */
  private final ManageFileTestBase         manageFileTestBase         = new ManageFileTestBase(this);

  private final DateTestBase               dateTestBase               = new DateTestBase(this);

  private final ElementEventTestBase       elementEventTestBase       = new ElementEventTestBase(this);

  public int                               loopCount                  = 0;

  public Actions                           action;

  protected int                            DEFAULT_TIMEOUT            = 30000;                               // milliseconds

  protected boolean                        ieFlag;

  /* ========System Property==================== */
  protected String                         user;

  /**
   * exoWebDriver that uses Selenide Driver, a wrapper of Selenium Web Driver
   */
  private Driver                           exoWebDriver               = new ExoWebDriver();

  private String                           ssoType;

  public String getSsoType() {
    return ssoType;
  }

  /**
   * verify element exists or not
   *
   * @param locator element html
   * @return true if element exists false if element doesn't exist
   */
  public boolean isElementPresent(Object locator) {
    return elementEventTestBase.isElementPresent(locator);
  }

  /**
   * Get element
   *
   * @param locator locator of element
   * @param opParams opPram[0]: timeout opPram[1]: 0,1 0: No Assert 1: Assert
   * @return an element
   * @deprecated
   */
  public WebElement waitForAndGetElement(Object locator, Object... opParams) {
    return elementEventTestBase.waitForAndGetElement(locator, opParams);
  }

  /**
   * Get element
   *
   * @param locator locator of element
   * @return an element
   */
  public WebElement waitForAndGetElement(Object locator) {
    return elementEventTestBase.waitForAndGetElement(locator, null);
  }

  /**
   * Get element
   *
   * @param locator locator of element
   * @param opParams opPram[0]: timeout opPram[1]: 0,1 0: No Assert 1: Assert
   * @return an element
   */
  public WebElement waitForElementNotPresent(Object locator, int... opParams) {
    return elementEventTestBase.waitForElementNotPresent(locator, opParams);
  }

  /**
   * @param text text
   * @param opts opts
   * @return true if text exist false if test is not exist
   */
  public boolean isTextPresent(String text, int... opts) {
    return elementEventTestBase.isTextPresent(text, opts);
  }

  /**
   * get text of element
   *
   * @param locator element html
   * @param opts opts
   * @return text of element
   */
  public String getText(Object locator, int... opts) {
    return elementEventTestBase.getText(locator, opts);
  }

  /**
   * get list of element
   *
   * @param xpath xpath
   * @return list of elements
   */
  public List<WebElement> getElements(String xpath) {
    return elementEventTestBase.getElements(xpath);
  }

  /**
   * Click by using javascript
   *
   * @param locator element html
   * @param opParams object
   */
  public void clickByJavascript(Object locator, Object... opParams) {
    elementEventTestBase.clickByJavascript(locator, opParams);
  }

  /**
   * click action
   *
   * @param locator element html
   * @param opParams object
   */
  public void click(Object locator, Object... opParams) {
    elementEventTestBase.click(locator, opParams);
  }

  /**
   * Use this function to verify if a check-box is checked (using when creating a
   * portal/publicMode)
   *
   * @param locator element html
   * @param opParams object
   */
  public void check(Object locator, int... opParams) {
    elementEventTestBase.check(locator, opParams);
  }

  /**
   * get value attribute
   *
   * @param locator element html
   * @return value of element
   */
  public String getValue(Object locator) {
    return elementEventTestBase.getValue(locator);
  }

  /**
   * mouse over action
   *
   * @param locator element html
   * @param safeToSERE true or false
   * @param opParams object
   */
  public void mouseOver(Object locator, boolean safeToSERE, Object... opParams) {
    elementEventTestBase.mouseOver(locator, safeToSERE, opParams);
  }

  /**
   * mouse over and clic
   *
   * @param locator element html
   */
  public void mouseOverAndClick(Object locator) {
    elementEventTestBase.mouseOverAndClick(locator);
  }

  /**
   * type to textbox
   *
   * @param locator object
   * @param value string
   * @param validate boolean
   * @param opParams object
   * @deprecated
   */
  public void type(Object locator, String value, boolean validate, Object... opParams) {
    elementEventTestBase.type(locator, value);
  }

  /**
   * type to textbox
   *
   * @param locator object
   * @param value string
   */
  public void type(Object locator, String value) {
    elementEventTestBase.type(locator, value);
  }

  /**
   * Select option from combo box
   *
   * @param locator object
   * @param option string
   * @param display int
   */
  public void select(Object locator, String option, int... display) {
    elementEventTestBase.select(locator, option, display);
  }

  /**
   * un-check a checked-box
   *
   * @param locator object
   * @param opParams int
   */
  public void uncheck(Object locator, int... opParams) {
    elementEventTestBase.uncheck(locator, opParams);
  }

  /**
   * function to switch to parent windows
   */
  public void switchToParentWindow() {
    elementEventTestBase.switchToParentWindow();
  }

  /**
   * This function returns a absolute path from a relative path
   *
   * @param relativeFilePath String
   * @return - FQA-2092: Run and check calendar smoke on IE and FF
   */
  public String getAbsoluteFilePath(String relativeFilePath) {
    return manageFileTestBase.getAbsoluteFilePath(relativeFilePath);
  }

  /**
   * Attach file in attach popup
   *
   * @param pathFile string
   * @param fileName string
   */
  public void attachFile(String pathFile, String fileName) {
    manageFileTestBase.attachFile(pathFile, fileName, DEFAULT_TIMEOUT, exoWebDriver.getWebDriver());
  }

  /**
   * Get date by text format ex. Saturday, Febuary 16, 2015
   *
   * @param format string
   * @return DateByTextFormat
   */
  public String getDateByTextFormat(String format) {
    return dateTestBase.getDateByTextFormat(format);
  }

  /**
   * function get current Date of system follow a format
   *
   * @param format string
   * @return current Date of system
   */
  public String getCurrentDate(String format) {
    return dateTestBase.getCurrentDate(format);
  }

  /**
   * Get date in format "dd"
   *
   * @param gap distance from current date
   * @param format string
   * @return date in format "dd"
   */
  public String getDate(int gap, String format) {
    return dateTestBase.getDate(gap, format);

  }

  public int getDefaultTimeout() {
    return DEFAULT_TIMEOUT;
  }

  public ElementEventTestBase getElementEventTestBase() {
    return elementEventTestBase;
  }

  public int getLoopCount() {
    return loopCount;
  }

  public void setLoopCount(int loopCount) {
    this.loopCount = loopCount;
  }

  public String getBrowser() {
    return exoWebDriver.getBrowser();
  }

  public Actions getAction() {
    return action;
  }

  public void setAction(Actions action) {
    this.action = action;
  }

  public Driver getExoWebDriver() {
    return exoWebDriver;
  }

  public String getUser() {
    return user;
  }

}
