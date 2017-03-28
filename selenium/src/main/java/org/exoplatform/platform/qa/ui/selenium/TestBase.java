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

import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.debug;

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

  private final TermsAndConditionsTestBase termsAndConditionsTestBase = new TermsAndConditionsTestBase(this);

  public int                               loopCount                  = 0;

  public Actions                           action;

  public String                            nativeEvent;
  // =
  // 30
  // seconds

  protected int                            DEFAULT_TIMEOUT            = 30000;                               // milliseconds

  protected boolean                        ieFlag;

  protected boolean                        chromeFlag;

  protected String                         plfVersion                 = "";

  /* ========System Property==================== */
  protected Boolean                        isRandom;

  protected Boolean                        isUseFile;

  protected String                         jdbcDriver;

  protected String                         dbUrl;

  protected String                         user;

  protected String                         pass;

  protected String                         sqlWiki;

  protected String                         sqlAttach;

  protected String                         sqlUser;

  protected String                         sqlContent;

  protected String                         defaultSheet;

  /**
   * exoWebDriver that uses Selenide Driver, a wrapper of Selenium Web Driver
   */
  private Driver                           exoWebDriver               = new ExoWebDriver();

  private String                           ssoType;

  public String getSsoType() {
    return ssoType;
  }

  /**
   * Get System Property
   */
  public void getSystemProperty() {
    nativeEvent = System.getProperty("nativeEvent");

    ssoType = System.getProperty("ssoType");

    jdbcDriver = System.getProperty("jdbcDriver");
    dbUrl = System.getProperty("dbUrl");
    user = System.getProperty("user");
    pass = System.getProperty("pass");
    sqlWiki = System.getProperty("sqlWiki");
    sqlAttach = System.getProperty("sqlAttach");
    sqlUser = System.getProperty("sqlUser");

    defaultSheet = System.getProperty("defaultSheet");

    if (ssoType == null)
      ssoType = DefaultDataTestBase.DEFAULT_SSOTYPE;

    if (nativeEvent == null)
      nativeEvent = DefaultDataTestBase.DEFAULT_NATIVE_EVENT;

    if (isRandom == null)
      isRandom = DefaultDataTestBase.DEFAULT_ISRANDOM;
    if (isUseFile == null)
      isUseFile = DefaultDataTestBase.DEFAULT_ISUSEFILE;

    if (jdbcDriver == null)
      jdbcDriver = DefaultDataTestBase.DEFAULT_JDBCDRIVER;
    if (dbUrl == null)
      dbUrl = DefaultDataTestBase.DEFAULT_DBURL;
    if (user == null)
      user = DefaultDataTestBase.DEFAULT_USERMYSQL;
    if (pass == null)
      pass = DefaultDataTestBase.DEFAULT_USERPASS;

    if (sqlWiki == null)
      sqlWiki = DefaultDataTestBase.DEFAULT_SQLWIKI;
    if (sqlAttach == null)
      sqlAttach = DefaultDataTestBase.DEFAULT_SQLATTACHMENT;
    if (sqlUser == null)
      sqlUser = DefaultDataTestBase.DEFAULT_SQLUSER;
    if (sqlContent == null)
      sqlContent = DefaultDataTestBase.DEFAULT_SQLCONTENT;

    if (defaultSheet == null)
      defaultSheet = DefaultDataTestBase.DEFAULT_SHEET;

  }

  /**
   * init browser
   *
   * @param opParams
   */
  public void initSeleniumTest(Object... opParams) {
    // Now the Selenium Driver and the eXo Platform screens are initialize in the core project.
  }

  public void initSeleniumTestWithOutTermAndCondition(Object... opParams) {
    // final WebDriver wd = exoWebDriver.initDriver();
    chromeFlag = exoWebDriver.isChromeDriver();
    ieFlag = exoWebDriver.isIEDriver();
    action = new Actions(exoWebDriver.getWebDriver());
  }

  /**
   * verify element exists or not
   *
   * @param locator
   * @return true if element exists false if element doesn't exist
   */
  public boolean isElementPresent(Object locator) {
    return elementEventTestBase.isElementPresent(locator);
  }

  /**
   * verify element exists or not
   *
   * @param locator
   * @return true if element doesn't exists false if element exist
   */
  public boolean isElementNotPresent(Object locator) {
    return elementEventTestBase.isElementNotPresent(locator);
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
   * @param text
   * @param opts
   * @return true if text exist false if test is not exist
   */
  public boolean isTextPresent(String text, int... opts) {
    return elementEventTestBase.isTextPresent(text, opts);
  }

  /**
   * get text of element
   *
   * @param locator
   * @param opts
   * @return text of element
   */
  public String getText(Object locator, int... opts) {
    return elementEventTestBase.getText(locator, opts);
  }

  /**
   * get list of element
   *
   * @param xpath
   * @return list of elements
   */
  public List<WebElement> getElements(String xpath) {
    return elementEventTestBase.getElements(xpath);
  }

  /**
   * verify text exists or noet
   *
   * @param text
   * @return true if text exists false if text doesn't exits
   */
  public boolean isTextNotPresent(String text) {
    return elementEventTestBase.isTextNotPresent(text);
  }

  /**
   * drag and drop element
   *
   * @param sourceLocator
   * @param targetLocator
   */
  public void dragAndDropToObject(Object sourceLocator, Object targetLocator) {
    elementEventTestBase.dragAndDropToObject(sourceLocator, targetLocator);
  }

  /**
   * Drag an object
   *
   * @param sourceLocator
   * @param targetLocator
   */
  public void dragObject(String sourceLocator, String targetLocator) {
    elementEventTestBase.dragObject(sourceLocator, targetLocator);
  }

  /**
   * Click by using javascript
   *
   * @param locator
   * @param opParams
   */
  public void clickByJavascript(Object locator, Object... opParams) {
    elementEventTestBase.clickByJavascript(locator, opParams);
  }

  /**
   * Type by java script
   *
   * @param locatorById
   * @param value
   * @param opParams
   */
  public void typeByJavascript(Object locatorById, String value, Object... opParams) {
    elementEventTestBase.typeByJavascript(locatorById, value, opParams);
  }

  /**
   * click action
   *
   * @param locator
   * @param opParams
   */
  public void click(Object locator, Object... opParams) {
    elementEventTestBase.click(locator, opParams);
  }

  /**
   * clear cache
   */
  public void clearCache() {
    Actions actionObject = new Actions(exoWebDriver.getWebDriver());
    try {
      actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
    } catch (WebDriverException e) {
      debug("Retrying clear cache...");
      actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
    }
  }

  /**
   * Use this function to verify if a check-box is checked (using when creating
   * a portal/publicMode)
   *
   * @param locator
   * @param opParams
   */
  public void check(Object locator, int... opParams) {
    elementEventTestBase.check(locator, opParams);
  }

  /**
   * get value attribute
   *
   * @param locator
   * @return value of element
   */
  public String getValue(Object locator) {
    return elementEventTestBase.getValue(locator);
  }

  /**
   * Mouse hover by Javascript
   *
   * @param locator
   * @param opParams
   */
  public void mouseHoverByJavaScript(Object locator, Object... opParams) {
    elementEventTestBase.mouseHoverByJavaScript(locator, opParams);
  }

  /**
   * mouse over action
   *
   * @param locator
   * @param safeToSERE
   * @param opParams
   */
  public void mouseOver(Object locator, boolean safeToSERE, Object... opParams) {
    elementEventTestBase.mouseOver(locator, safeToSERE, opParams);
  }

  /**
   * mouse over and clic
   *
   * @param locator
   */
  public void mouseOverAndClick(Object locator) {
    elementEventTestBase.mouseOverAndClick(locator);
  }

  /**
   * wait for text present
   *
   * @param text
   * @param opts
   */
  public void waitForTextPresent(String text, int... opts) {
    elementEventTestBase.waitForTextPresent(text, opts);
  }

  /**
   * wait for text not present
   *
   * @param text
   * @param wait
   */
  public void waitForTextNotPresent(String text, int... wait) {
    elementEventTestBase.waitForTextNotPresent(text, wait);
  }

  /**
   * wait for msg
   *
   * @param message
   * @param wait
   */
  public void waitForMessage(String message, int... wait) {
    elementEventTestBase.waitForMessage(message, wait);
  }

  /**
   * type to textbox
   *
   * @param locator
   * @param value
   * @param validate
   * @param opParams
   * @deprecated
   */
  public void type(Object locator, String value, boolean validate, Object... opParams) {
    elementEventTestBase.type(locator, value);
  }

  /**
   * type to textbox
   *
   * @param locator
   * @param value
   */
  public void type(Object locator, String value) {
    elementEventTestBase.type(locator, value);
  }

  /**
   * Select option from combo box
   *
   * @param locator
   * @param option
   * @param display
   */
  public void select(Object locator, String option, int... display) {
    elementEventTestBase.select(locator, option, display);
  }

  /**
   * un-check a checked-box
   *
   * @param locator
   * @param opParams
   */
  public void uncheck(Object locator, int... opParams) {
    elementEventTestBase.uncheck(locator, opParams);
  }

  /**
   * rightClickOnElement
   *
   * @param locator
   * @param opParams
   */
  public void rightClickOnElement(Object locator, int... opParams) {
    elementEventTestBase.rightClickOnElement(locator, opParams);
  }

  /**
   * doubleClickOnElement
   *
   * @param locator
   */
  public void doubleClickOnElement(Object locator) {
    elementEventTestBase.doubleClickOnElement(locator);
  }

  /**
   * checkCycling
   *
   * @param e
   * @param loopCountAllowed
   */
  public void checkCycling(Exception e, int loopCountAllowed) {
    elementEventTestBase.checkCycling(e, loopCountAllowed);
  }

  /**
   * function to switch to parent windows
   */
  public void switchToParentWindow() {
    elementEventTestBase.switchToParentWindow();
  }

  /**
   * check element displays or net
   *
   * @param locator
   * @return true if element displays false if element doesn't display
   */
  public boolean isDisplay(Object locator) {
    return elementEventTestBase.isDisplay(locator);
  }

  /**
   * function set seleniumDriver to auto open new window when click link
   */
  public void getDriverAutoOpenWindow() {
    WebDriver seleniumWebDriver = exoWebDriver.getDriverAutoOpenWindow();
    action = new Actions(seleniumWebDriver);
    termsAndConditionsTestBase.termsAndConditions();

  }

  /**
   * set language
   *
   * @param language
   */
  public void getDriverSetLanguage(Language language) {
    WebDriver seleniumWebDriver = exoWebDriver.getDriverSetLanguage(language.toString());
    action = new Actions(seleniumWebDriver);
    termsAndConditionsTestBase.termsAndConditions();
  }

  /**
   * Change attribute "display" of HTML tag from "none" to "block"
   *
   * @param locator
   */
  public void changeDisplayAttributeHTML(Object locator) {
    elementEventTestBase.changeDisplayAttributeHTML(locator);
  }

  /**
   * setPreferenceRunTime
   */
  public void setPreferenceRunTime() {
    exoWebDriver.setPreferenceRunTime();
  }

  /**
   * Copy and paste a string from one locator to other
   *
   * @param origin
   * @param target
   * @param value
   */
  public void copyPasteString(By origin, By target, String value) {
    elementEventTestBase.copyPasteString(origin, target, value);
  }

  /**
   * @param object
   * @return = true: if there is not scroll bar on element = false: if there is
   *         scroll bar on element
   */
  public boolean checkExitScrollBar(By object) {
    return elementEventTestBase.checkExitScrollBar(object);
  }

  /**
   * function get an element from link text when cannot get by text in xpath
   *
   * @param text
   * @return an element from link text
   */
  public WebElement getElementFromTextByJquery(String text) {
    return elementEventTestBase.getElementFromTextByJquery(text);
  }

  /**
   * scrollBarToGetElement
   *
   * @param object
   * @param opParams
   */
  public void scrollBarToGetElement(By object, int... opParams) {
    elementEventTestBase.scrollBarToGetElement(object, opParams);
  }

  /**
   * inputDataToCKEditor
   *
   * @param framelocator
   * @param data
   */
  public void inputDataToCKEditor(By framelocator, String data) {
    elementEventTestBase.inputDataToCKEditor(framelocator, data);
  }

  /**
   * Press Enter key
   */
  public void pressEnterKey() {
    elementEventTestBase.pressEnterKey();
  }

  /**
   * Press End Key
   *
   * @param driver
   */
  public void pressEndKey(WebDriver driver) {
    elementEventTestBase.pressEndKey(driver);
  }

  public void pressHomeKey(WebDriver driver) {
    elementEventTestBase.pressHomeKey(driver);
  }

  /**
   * This function returns a absolute path from a relative path
   *
   * @param relativeFilePath
   * @return - FQA-2092: Run and check calendar smoke on IE and FF
   */
  public String getAbsoluteFilePath(String relativeFilePath) {
    return manageFileTestBase.getAbsoluteFilePath(relativeFilePath);
  }

  /**
   * Get a File Content
   *
   * @param filePath
   * @return fileContent
   */
  public String getFileContent(String filePath) {
    return manageFileTestBase.getFileContent(filePath);
  }

  /**
   * Get a file name from current Url
   *
   * @param driver
   * @param params
   * @return fileName
   */
  public String getFileNameFromCurrentUrl(WebDriver driver, Object... params) {
    return manageFileTestBase.getFileNameFromCurrentUrl(driver, params);
  }

  /**
   * Attach file in attach popup
   *
   * @param pathFile
   * @param fileName
   */
  public void attachFile(String pathFile, String fileName) {
    manageFileTestBase.attachFile(pathFile, fileName, DEFAULT_TIMEOUT, exoWebDriver.getWebDriver());
  }

  /**
   * Upload file using AutoIt
   *
   * @param file
   */
  public void uploadFileUsingAutoIt(String file) {
    manageFileTestBase.uploadFileUsingAutoIt(file);
  }

  /**
   * Download file using autoit
   *
   * @param file
   */
  public void downloadFileUsingAutoIt(String file) {
    manageFileTestBase.downloadFileUsingAutoIt(file);
  }

  /**
   * Download file using Robot class
   *
   * @param element
   * @throws Exception
   */
  public void downloadFileUsingRobot(WebElement element) throws Exception {

    // Get the focus on the element..don't use click since it stalls the
    // seleniumDriver

    // simulate pressing enter

    // Wait for the download manager to open
    // Switch to download manager tray via Alt+N

    // Press S key to save

    // Switch back to download manager tray via Alt+N

    // Tab to X exit key

    // Press Enter to close the Download Manager
    manageFileTestBase.downloadFileUsingRobot(element);
  }

  /**
   * Download file using Robot class via URL download link
   *
   * @throws Exception
   */
  public void downloadFileUsingRobotViaURL() throws Exception {

    // simulate pressing enter

    // Wait for the download manager to open
    // Switch to download manager tray via Alt+N

    // Press S key to save

    // Switch back to download manager tray via Alt+N

    // Tab to X exit key

    // Press Enter to close the Download Manager
    manageFileTestBase.downloadFileUsingRobotViaURL();
  }

  /**
   * uploadFileUsingRobot
   *
   * @param fileLocation
   */
  public void uploadFileUsingRobot(String fileLocation) {
    manageFileTestBase.uploadFileUsingRobot(fileLocation);
  }

  /**
   * uploadFileUsingRobot using for Document preview
   *
   * @param fileLocation
   */
  public void uploadFileUsingRobotDocumentPreview(String fileLocation) {
    // String path=getAbsoluteFilePath(fileLocation.replace("/", fs));
    manageFileTestBase.uploadFileUsingRobotDocumentPreview(fileLocation);
  }

  /**
   * This function will try to get an element. if after timeout, the element is
   * not found. The function will refresh the page and find the element again.
   *
   * @param element
   */
  public void waitElementAndTryGetElement(Object element, Boolean... isClicked) {
    elementEventTestBase.waitElementAndTryGetElement(element, isClicked);
  }

  /**
   * Check if a checkbox is checked or not
   *
   * @Author: QuyenNT Date: Oct 30, 2015
   */
  public boolean checkCheckBoxAttribute(String checkedElement) {
    return elementEventTestBase.checkCheckBoxAttribute(checkedElement);
  }

  /**
   * Get minute in format "HH" from current date
   *
   * @return hours
   */
  public int getHours() {
    return dateTestBase.getHours();
  }

  /**
   * Get date by text format ex. Saturday, Febuary 16, 2015
   *
   * @param format
   */
  public String getDateByTextFormat(String format) {
    return dateTestBase.getDateByTextFormat(format);
  }

  /**
   * Get first day of week
   *
   * @param format
   * @return firstDayOfWeek
   */
  public String getFirstDayOfWeek(String format) {
    return dateTestBase.getFirstDayOfWeek(format);
  }

  /**
   * Get last day of week
   *
   * @param format
   * @return firstDayOfWeek
   */
  public String getLastDayOfWeek(String format) {
    return dateTestBase.getLastDayOfWeek(format);
  }

  /**
   * function get current Date of system follow a format
   *
   * @param format
   * @return current Date of system
   */
  public String getCurrentDate(String format) {
    return dateTestBase.getCurrentDate(format);
  }

  /**
   * Get current date with time zone
   *
   * @param format
   * @param local
   * @return current Date with correct time zone
   */
  public String getCurrentDate(String format, String local) {
    return dateTestBase.getCurrentDate(format, local);
  }

  /**
   * Add 1 minute to current date time
   *
   * @param min
   * @param format
   * @return string minute
   */
  public String addMinuteToCurrentDateTime(int min, String... format) {
    return dateTestBase.addMinuteToCurrentDateTime(min, format);
  }

  /**
   * Get date in format "dd"
   *
   * @param gap distance from current date
   * @return date in format "dd"
   */
  public String getDate(int gap, String format) {
    return dateTestBase.getDate(gap, format);
  }

  /**
   * Get date from firstDayOf Week
   *
   * @param gap
   * @param format
   * @return date in format
   */
  public String getDateFromFirstDayOfWeek(int gap, String format) {
    return dateTestBase.getDateFromFirstDayOfWeek(gap, format);
  }

  /**
   * Get day of week
   *
   * @param gap distance from current date
   * @return day of week (monday, tuesday,..., sunday)
   */
  public int getDayOfWeek(int gap) {
    return dateTestBase.getDayOfWeek(gap);
  }

  /**
   * Get day of the next month
   *
   * @param format
   * @return date
   */
  public String getDayOfNextMonth(String format, int dayNum, int weekNum) {
    return dateTestBase.getDayOfNextMonth(format, dayNum, weekNum);
  }

  /**
   * Get the day of next year
   *
   * @param format
   * @param year
   * @return dayOfYear
   */
  public String getDayOfNextYear(String format, int year) {
    return dateTestBase.getDayOfNextYear(format, year);
  }

  /**
   * Get the day of next week
   *
   * @param format
   * @return
   */
  public String getDayOfNextWeek(String format) {
    return dateTestBase.getDayOfNextWeek(format);
  }

  /**
   * Get the number of current week
   *
   * @return weekNum
   */
  public int getWeekNumber() {
    return dateTestBase.getWeekNumber();
  }

  public int getDayNumber() {
    return dateTestBase.getDayNumber();
  }

  /**
   * Get current month/day/year
   *
   * @param format as MMM for month, dd for day, or yyyy for year
   * @return dateFormat.format(now.getTime())
   */
  public String getCurrentMonthDayYear(String format) {
    return dateTestBase.getCurrentMonthDayYear(format);
  }

  /**
   * Get minute in format "mm" from current date
   *
   * @return minute
   */
  public int getMinute() {
    return dateTestBase.getMinute();
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

  /**
   * define language
   */
  public enum Language {
    en, fr, vi, lo;
  }

}
