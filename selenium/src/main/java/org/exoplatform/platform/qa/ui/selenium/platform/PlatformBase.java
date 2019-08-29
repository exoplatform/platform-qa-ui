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

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

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
   * User pageinator
   *
   * @param locator
   * @param exceptionMessage
   */
  public void usePaginator(Object locator, String exceptionMessage) {
    String page1 = ELEMENT_PAGINATOR_PAGE_LINK.replace("${number}", "1");

    if (evt.waitForAndGetElement(byXpath(page1), 5000, 0) != null)
      $(byXpath("$(byXpath(\"//*[@id=\\\"UIGridUser\\\"]/div/ul/li[2]/a\"))")).click();
    int totalPages = 0;
    if (evt.waitForAndGetElement(ELEMENT_TOTAL_PAGE, 3000, 0) != null) {
      totalPages = evt.isElementPresent(ELEMENT_TOTAL_PAGE) ? Integer.valueOf(evt.getText(ELEMENT_TOTAL_PAGE)) : 1;
    }
    info("-- The total pages is: " + totalPages);
    int i = 1;
    while (evt.isElementNotPresent(locator)) {
      if (i == totalPages) {
        info(exceptionMessage);
        break;
      }
      if (evt.waitForAndGetElement(ELEMENT_NEXT_PAGE, 3000, 0) != null) {
        $(ELEMENT_NEXT_PAGE).click();
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
      evt.type(ELEMENT_SEARCH_USER_INPUT, user, true);
      switch (op) {
      case userName:
        selectOption(ELEMENT_SELECT_SEARCH, filterOption.userName.name());
        break;
      case firstName:
        selectOption(ELEMENT_SELECT_SEARCH, filterOption.firstName.name());
        break;
      case lastName:
        selectOption(ELEMENT_SELECT_SEARCH, filterOption.lastName.name());
        break;
      case email:
        selectOption(ELEMENT_SELECT_SEARCH, filterOption.email.name());
        break;
      }
      evt.click(ELEMENT_QUICK_SEARCH_BUTTON);

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
    evt.check(ELEMENT_USER_CHECKBOX.replace("$user", user), 2);
    info("Click on Add button");
    evt.click(ELEMENT_ADD_USERS_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_ADD_USERS_BUTTON);
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
      evt.click(ELEMENT_GROUP_MEMBERSHIP_NAME_SELECT.replace("$groupName", groupName));
    }
    if (!membership.isEmpty()) {
      info("Select the membership:" + membership);
      evt.click(ELEMENT_GROUP_MEMBERSHIP_NAME_SELECT.replace("$groupName", membership));
    }
    evt.waitForElementNotPresent(ELEMENT_MEMBERSHIP_POPUP);
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
      evt.click(ELEMENT_GROUP_NAME.replace("$group", groupName));
    }
    info("Select the group");
    evt.click(ELEMENT_SELECT_THIS_GROUP);
    evt.waitForElementNotPresent(ELEMENT_SELECT_GROUP_POPUP);
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
    userName, firstName, lastName, email
  }

}
