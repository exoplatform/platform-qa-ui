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

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.exoplatform.platform.qa.ui.selenium.user.UserDatabase;

public class PlatformBase {

  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public PlatformBase(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
  }

  /**
   * get default user pass from data driven
   *
   * @param userDataFile
   * @param userSheet
   * @param opParams
   * @throws Exception
   */
  public static void getDefaultUserPass(String userDataFile, String userSheet, Object... opParams) throws Exception {
    info("Get deault user pass from data driven");
    UserDatabase userData = new UserDatabase();
    userData.setUserData(userDataFile, userSheet, opParams);

  }

  /**
   * Type a text to a Frame using for CKEDITOR By QuynhPT
   *
   * @param frameLocator
   * @param content
   */
  public void inputFrame(By frameLocator, String content) {
    info("Finding the frameLocator:" + frameLocator);
    WebElement e = $(frameLocator);
    info("Switch to the frame:" + frameLocator);
    WebDriver webDriver = testBase.getExoWebDriver().getWebDriver();
    webDriver.switchTo().frame(e);
    WebElement inputsummary = webDriver.switchTo().activeElement();
    info("focus on the text area");
    inputsummary.click();
    info("Input the content:" + content);
    inputsummary.clear();
    inputsummary.sendKeys(content);
    info("Back to parent window");
    evt.switchToParentWindow();
  }

  /**************************** Method *************************************/

  /**
   * Switch into the frame
   *
   * @param frameLocator
   */
  public void switchFrame(By frameLocator, Object... param) {
    info("Finding the frameLocator:" + frameLocator);
    WebElement e = evt.waitForAndGetElement(frameLocator, testBase.getDefaultTimeout(), 1, 2);
    info("Switch to the frame:" + frameLocator);
    testBase.getExoWebDriver().getWebDriver().switchTo().frame(e);
    WebElement inputsummary = testBase.getExoWebDriver().getWebDriver().switchTo().activeElement();
    info("focus on the text area");
    inputsummary.click();
    if (param.length > 0)
      inputsummary.sendKeys("\n");
  }

  /**
   * Select option from combo box
   *
   * @param locator
   * @param option
   */
  public void selectOption(Object locator, String option) {
    try {
      for (int second = 0;; second++) {
        if (second >= testBase.getDefaultTimeout() / evt.getWaitInterval()) {
          Assertions.fail("Timeout at select: " + option + " into " + locator);
        }

        Select select = new Select(evt.waitForAndGetElement(locator));
        select.selectByValue(option);
        if (option.equals(select.getFirstSelectedOption().getAttribute("value"))) {
          break;
        }

      }
    } catch (StaleElementReferenceException e) {
      evt.checkCycling(e, testBase.getDefaultTimeout() / evt.getWaitInterval());

      evt.select(locator, option);
    } finally {
      testBase.loopCount = 0;
    }
  }

  /**
   * Add by @author vuna2
   * Switch to a new browser/ Popup window
   */
  public void switchToNewWindow() {
    Set<String> windowids = testBase.getExoWebDriver().getWebDriver().getWindowHandles();
    Iterator<String> iter = windowids.iterator();
    while (iter.hasNext()) {
      String windowHandle = iter.next();
      testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
      info("Switch to new windown successfully");
    }
  }

  /**
   * Switch to new browser window
   *
   * @param user
   * @param pass
   */
  public void switchToNewBrowserWindow(String user, String pass) {
    ManageLogInOut magAcc = new ManageLogInOut(testBase);

    this.openNewBrowser();
    if (user != null) {
      if (evt.isElementNotPresent(ELEMENT_INPUT_USERNAME)) {
        magAcc.signOut();
      } else {
        info("-- User.logIn: " + user);
      }
      magAcc.signIn(user, pass);

    }
  }

  /**
   * Add by @author vuna2 Open a new browser by Javascript
   */
  public void openNewBrowser() {
    // Open new browser by Javascript
    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("window.open()");
    for (String winHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(winHandle);
    }
    testBase.getExoWebDriver().getWebDriver().manage().window().maximize();
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    testBase.getExoWebDriver().getWebDriver().navigate().to(testBase.getExoWebDriver().getBaseUrl());
  }

  /**
   * Add by @author vuna2 Open a new browser by Javascript
   */
  public void openNewBrowser(String url) {
    // Open new browser by Javascript
    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("window.open()");
    for (String winHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(winHandle);
    }
    testBase.getExoWebDriver().getWebDriver().manage().window().maximize();
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    testBase.getExoWebDriver().getWebDriver().navigate().to(url);
  }

  /**
   * function: switch between windows using title windows
   *
   * @param windowTitle
   */
  public void switchBetweenWindowsUsingTitle(String windowTitle) {
    Set<String> windows = testBase.getExoWebDriver().getWebDriver().getWindowHandles();
    for (String window : windows) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(window);
      if (testBase.getExoWebDriver().getWebDriver().getTitle().contains(windowTitle)) {
        return;
      }
    }
  }

  /**
   * switch between browsers using window handle
   *
   * @param windowHandle
   */
  public void switchBetweenBrowsers(String windowHandle) {
    Set<String> windows = testBase.getExoWebDriver().getWebDriver().getWindowHandles();
    for (String window : windows) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(window);
      if (testBase.getExoWebDriver().getWebDriver().getWindowHandle().contains(windowHandle)) {
        return;
      }
    }
  }

  /**
   * Go to gmail and login by new browser
   *
   * @param email
   * @param pass
   */
  public void goToMail(String email, String pass) {
    // ((JavascriptExecutor)
    // testBase.getExoWebDriver().getWebDriver()).executeScript("window.open()");
    testBase.getExoWebDriver().getWebDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "n");
    for (String winHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(winHandle);
    }
    info("Go to gmail");
    testBase.getExoWebDriver().getWebDriver().navigate().to(GMAIL_URL);
    testBase.getExoWebDriver().getWebDriver().manage().window().maximize();

    // login to mail
    if (evt.waitForAndGetElement(ELEMENT_GMAIL_USERNAME, 5000, 0) == null) {
      if (evt.waitForAndGetElement(ELEMENT_GMAIL_SIGN_IN_LINK, 3000, 0) != null)
        evt.click(ELEMENT_GMAIL_SIGN_IN_LINK);
      else {
        evt.click(ELEMENT_GMAIL_SIGNIN_DIFFERENT_ACC);
        evt.click(ELEMENT_GMAIL_ADD_ACCOUNT);
      }
    }
    evt.type(ELEMENT_GMAIL_USERNAME, email, true);
    evt.click(ELEMENT_GMAIL_NEXT_BTN);

    evt.type(ELEMENT_GMAIL_PASS, pass, true);
    evt.click(ELEMENT_GMAIL_SIGN_IN);
    // clearCache();

    evt.click(ELEMENT_GMAIL_INBOX);

  }

  /**
   * Open gmail when user is logging
   */
  public void openMail() {
    testBase.getExoWebDriver().getWebDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "n");
    for (String winHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(winHandle);
    }
    info("Go to gmail");
    testBase.getExoWebDriver().getWebDriver().navigate().to(GMAIL_URL);
    testBase.getExoWebDriver().getWebDriver().manage().window().maximize();

    evt.click(ELEMENT_GMAIL_INBOX);

  }

  /**
   * Open mail by opening new tab
   *
   * @param email
   * @param pass
   */
  public void goToMailByTab(String email, String pass) {
    testBase.getExoWebDriver().getWebDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
    ArrayList<String> tabs = new ArrayList<String>(testBase.getExoWebDriver().getWebDriver().getWindowHandles());
    testBase.getExoWebDriver().getWebDriver().switchTo().window(tabs.get(0));
    info("Go to gmail");
    testBase.getExoWebDriver().getWebDriver().navigate().to(GMAIL_URL);

    // login to mail
    if (evt.waitForAndGetElement(ELEMENT_GMAIL_USERNAME, 5000, 0) == null) {
      if (evt.waitForAndGetElement(ELEMENT_GMAIL_SIGN_IN_LINK, 3000, 0) != null)
        evt.click(ELEMENT_GMAIL_SIGN_IN_LINK);
      else {
        evt.click(ELEMENT_GMAIL_SIGNIN_DIFFERENT_ACC);
        evt.click(ELEMENT_GMAIL_ADD_ACCOUNT);
      }
    }
    evt.type(ELEMENT_GMAIL_USERNAME, email, true);
    evt.click(ELEMENT_GMAIL_NEXT_BTN);

    evt.type(ELEMENT_GMAIL_PASS, pass, true);
    evt.click(ELEMENT_GMAIL_SIGN_IN);
    testBase.clearCache();

    evt.click(ELEMENT_GMAIL_INBOX);

  }

  /**
   * function: check content of mail then delete mail
   *
   * @param mail element title of mail
   * @param content mail content
   */
  public void checkAndDeleteMail(By mail, String content) {
    info("Check and delete mail");
    evt.waitForAndGetElement(mail, 300000);
    evt.click(mail);
    if (evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT.replace("${content}", content), 20000, 0) == null)
      evt.click(ELEMENT_FIRST_MAIL);
    assert evt.waitForAndGetElement(ELEMENT_MAIL_CONTENT).getText().contains(content);
    info("Found notify mail");

    info("delete mail");
    if (evt.waitForAndGetElement(ELEMENT_DELETE_MAIL_2, 5000, 0) == null) {
      evt.click(ELEMENT_DELETE_MAIL);
      info("Delete 1");
    } else {
      evt.click(ELEMENT_DELETE_MAIL_2);
      info("Delete 2");
    }
    evt.waitForElementNotPresent(mail);

  }

  /**
   * Get list all Browsers
   */
  public void getAllChildWindows() {
    for (String windowHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
      info("testBase.getExoWebDriver().getWebDriver().title:" + testBase.getExoWebDriver().getWebDriver().getTitle());
      testBase.getExoWebDriver().getWebDriver().manage().window().maximize();
    }
  }

  /**
   * Close all child testBase.getExoWebDriver().getWebDriver()s
   *
   * @param parentWindow is the tilte of parent browsers
   */
  public void closeChildBrowsers(String parentWindow) {
    info("parentWindow:" + parentWindow);
    Set<String> handlers = testBase.getExoWebDriver().getWebDriver().getWindowHandles();
    // Handler will have all the three window handles
    for (String windowHandle : handlers) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
      info("windowHandle" + windowHandle);
      // If it is not the parent window it will close the child window
      if (!windowHandle.contains(parentWindow)) {
        info("close testBase.getExoWebDriver().getWebDriver().title:" + testBase.getExoWebDriver().getWebDriver().getTitle());

        testBase.getExoWebDriver().getWebDriver().close();
      }

    }
    evt.switchToParentWindow();
  }

  /**
   * function: check content of mail then delete mail
   *
   * @param title title of the page
   * @param opParams if true check it's present, false check if it's not present
   */
  public void checkEmailNotification(String title, Object... opParams) {
    info("Check and delete mail");
    Boolean checkOrNo = (Boolean) (opParams.length > 0 ? opParams[0] : true);

    String parentWindow = testBase.getExoWebDriver().getWebDriver().getWindowHandle();
    info("parentWindow:" + parentWindow);
    for (String windowHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
      info("testBase.getExoWebDriver().getWebDriver().title:" + testBase.getExoWebDriver().getWebDriver().getTitle());
    }
    if (opParams.length > 0) {
      if (checkOrNo == true)
        evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT.replace("${title}", title), 30000, 1);
      else
        evt.waitForElementNotPresent(ELEMENT_GMAIL_CONTENT.replace("${title}", title), 30000, 1);
    }

    // close windows mail
    if (opParams.length > 1)
      testBase.getExoWebDriver().getWebDriver().close();
  }

  /**
   * User pageinator
   *
   * @param locator
   * @param exceptionMessage
   */
  public void usePaginator(Object locator, String exceptionMessage) {
    String page1 = ELEMENT_PAGINATOR_PAGE_LINK.replace("${number}", "1");

    if (evt.waitForAndGetElement(page1, 5000, 0) != null)
      evt.click(page1);

    int totalPages = 0;
    if (evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE, 3000, 0) != null) {
      totalPages = evt.isElementPresent(ELEMENT_TOTAL_PAGE) ? Integer.valueOf(testBase.getText(ELEMENT_TOTAL_PAGE)) : 1;
    }
    info("-- The total pages is: " + totalPages);
    int i = 1;
    while (evt.isElementNotPresent(locator)) {
      if (i == totalPages) {
        info(exceptionMessage);
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_NEXT_PAGE, 3000, 0) != null) {
        evt.click(ELEMENT_NEXT_PAGE);
      }

    }
  }

  /**
   * Search users in user list popup
   *
   * @param user
   * @param op
   */
  public void searchUser(String user, filterOption op) {
    if (!user.isEmpty()) {
      info("Type user into the search field");
      $(ELEMENT_SEARCH_USER_INPUT).setValue(user);
      switch (op) {
      case userName:
        if($(ELEMENT_SELECT_SEARCH).is(Condition.visible))
        selectOption(ELEMENT_SELECT_SEARCH, filterOption.userName.name());
        break;
      case firstName:
        if($(ELEMENT_SELECT_SEARCH).is(Condition.visible))
        selectOption(ELEMENT_SELECT_SEARCH, filterOption.firstName.name());
        break;
      case lastName:
        if($(ELEMENT_SELECT_SEARCH).is(Condition.visible))
        selectOption(ELEMENT_SELECT_SEARCH, filterOption.lastName.name());
        break;
      case email:
        if($(ELEMENT_SELECT_SEARCH).is(Condition.visible))
        selectOption(ELEMENT_SELECT_SEARCH, filterOption.email.name());
        break;
      }
     $(ELEMENT_QUICK_SEARCH_BUTTON).click();

      info("the user is shown in searched result list");
    }

  }

  /**
   * Select a user in User list
   *
   * @param user
   * @param op
   */
  public void selectUser(String user, filterOption op) {
    searchUser(user, op);
    info("Select the user");
    if($(byXpath( ELEMENT_USER_CHECKBOX.replace("$user", user))).is(Condition.not(Condition.checked)))
    $(byXpath( ELEMENT_USER_CHECKBOX.replace("$user", user))).parent().click();
    info("Click on Add button");
    $(ELEMENT_ADD_USERS_BUTTON).click();
    $(ELEMENT_ADD_USERS_BUTTON).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    info("the user is added");
  }

  /**
   * Select a membership in the list
   *
   * @param group
   * @param membership
   */
  public void selectMembership(String group, String membership) {
    String[] groups = group.split("/");
    for (String groupName : groups) {
      info("Select the group:" + groupName);
     $(byXpath(ELEMENT_GROUP_MEMBERSHIP_NAME_SELECT.replace("$groupName", groupName))).click();
    }
    if (!membership.isEmpty()) {
      info("Select the membership:" + membership);
     $(byXpath(ELEMENT_GROUP_MEMBERSHIP_NAME_SELECT.replace("$groupName", membership))).click();
    }
    $(ELEMENT_MEMBERSHIP_POPUP).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Select a group
   *
   * @param group
   */
  public void selectGroup(String group) {
    String[] groups = group.split("/");
    for (String groupName : groups) {
      info("Select the group:" + groupName);
      $(byXpath(ELEMENT_GROUP_NAME.replace("$group", groupName))).click();
    }
    info("Select the group");
   $(ELEMENT_SELECT_THIS_GROUP).click();
  $(ELEMENT_SELECT_GROUP_POPUP).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Available option
   */
  public enum selectInvitationOption {
    ALWAYS, NEVER, ASK
  }

  /**
   * Arrow option
   */
  public enum selectArrowOption {
    NEXT, PREVIOUS, NOW
  }

  /**
   * Define filter user option
   */
  public enum filterOption {
    userName, firstName, lastName, email;
  }

}
