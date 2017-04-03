package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiSettingManagement {

  private final TestBase       testBase;

  /**
   * constructor
   *
   * @param dr
   */
  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public WikiSettingManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    alert = new ManageAlert(testBase);
  }

  /**
   * Search a template
   *
   * @param template
   */
  public void searchTemplate(String template) {
    info("Input a template's name");
    evt.type(ELEMENT_TEMPLATE_SEARCH_TEXTBOX, template, true);
    info("Press Enter key");
    testBase.getExoWebDriver().getWebDriver().findElement(ELEMENT_TEMPLATE_SEARCH_TEXTBOX).sendKeys(Keys.ENTER);
    info("Verify that the search results is shown that matchs with keyword");
    evt.waitForAndGetElement(ELEMENT_WIKI_SETTINGS_RESULTS.replace("${tempalte}", template), 3000, 0);
  }

  /**
   * Edit a wiki template
   *
   * @param template
   * @param text
   */
  public void editTemplate(String template, String title, String subTitle, String text) {
    evt.click(By.xpath(ELEMENT_EDIT_TEMPLATE.replace("{$template}", template)));
    if (title != "") {
      evt.type(ELEMENT_TITLE_TEMPLATE, title, true);
    }

    evt.click(ELEMENT_SAVE_TEMPLATE);
  }

  public void deleteTemplate(String template) {
    info("Delete template " + template);
    evt.click(By.xpath(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template)));
    alert.acceptAlert();
    evt.waitForElementNotPresent(By.xpath(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template)));
  }

  /**
   * Go to Wiki Setting Permission page
   */
  public void goToWikiSettingPermission() {
    info("-- Go to wiki home page --");
    evt.click(ELEMENT_WIKI_SETTINGS_PERMISSION);
  }

  /**
   * Go to User Selector page
   */
  public void gotoUserSelector() {
    info("-- Go to wiki home page --");
    evt.click(ELEMENT_WIKI_SETTINGS_PERMISSION_SELECT_USER_ICON);
  }

  /**
   * function: Search user in Wiki Settings Permission Page
   *
   * @param user (Can be: User name, Last name, First name or Email of the user
   *          you want to search)
   * @param searchOption (Can be: User name, Last name, First name or Email
   *          option corresponding with information you input in "Search")
   */
  public void searchUser(String user, String searchOption) {
    info("--Search user " + user + "--");
    evt.type(ELEMENT_WIKI_SETTINGS_PERMISSION_INPUT_SEARCH_USER_NAME, user, true);
    evt.select(ELEMENT_WIKI_SETTINGS_PERMISSION_SELECT_SEARCH_OPTION, searchOption);
    evt.click(ELEMENT_WIKI_SETTINGS_PERMISSION_SEARCH_ICON);
    evt.waitForTextPresent(user);
  }

  public void searchUserNotPresent(String user, String searchOption) {
    info("--Search user " + user + "--");
    evt.type(ELEMENT_WIKI_SETTINGS_PERMISSION_INPUT_SEARCH_USER_NAME, user, true);
    evt.select(ELEMENT_WIKI_SETTINGS_PERMISSION_SELECT_SEARCH_OPTION, searchOption);
    evt.click(ELEMENT_WIKI_SETTINGS_PERMISSION_SEARCH_ICON);
    evt.waitForTextNotPresent(user);
  }

  /**
   * Verify that the user is shown in the user list
   *
   * @param userName is user-name of the user
   */
  public void verifyUserPresent(String userName) {
    info("---Verify that the user is shown in the table");
    evt.waitForAndGetElement(ELEMENT_WIKI_SETTINGS_PERMISSION_USER_NAME_IN_USER_LIST.replace("$userName", userName));
    info("The user is shown in the table");
  }

  /**
   * Close User Selector page
   */
  public void closeUserSelector() {
    info("-- Go to User Selector page --");
    evt.click(ELEMENT_WIKI_SETTINGS_CLOSE_USER_SELETOR);
    Utils.pause(2000);
  }
}
