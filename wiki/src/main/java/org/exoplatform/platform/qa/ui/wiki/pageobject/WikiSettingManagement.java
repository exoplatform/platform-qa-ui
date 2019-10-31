package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiSettingManagement {

  private final TestBase       testBase;

  private HomePagePlatform homePagePlatform;

  public ManageAlert           alert;

  private ElementEventTestBase evt;


  public WikiSettingManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    alert = new ManageAlert(testBase);
    this.homePagePlatform=new HomePagePlatform(testBase);

  }

  /**
   * Search a template
   *
   * @param template String
   */
  public void searchTemplate(String template) {
    info("Input a template's name");
    evt.type(ELEMENT_TEMPLATE_SEARCH_TEXTBOX, template, true);
    info("Press Enter key");
    testBase.getExoWebDriver().getWebDriver().findElement(ELEMENT_TEMPLATE_SEARCH_TEXTBOX).sendKeys(Keys.ENTER);
    info("Verify that the search results is shown that matchs with keyword");
    evt.waitForAndGetElement(ELEMENT_WIKI_SETTINGS_RESULTS.replace("${tempalte}", template), 3000, 0);
  }
public void addTemplate(String title,String description,String content){

    $(ELEMENT_TITLE_TEMPLATE).waitUntil(Condition.appears, Configuration.timeout).setValue(title);
    $(ELEMENT_DESCRIPTION_TEMPLATE).waitUntil(Condition.visible, Configuration.timeout).setValue(description);
    $(ELEMENT_CONTENT_TEMPLATE).waitUntil(Condition.visible, Configuration.timeout).setValue(content);
    $(ELEMENT_SAVE_TEMPLATE).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_WIKI_OK_SAVE_TEMPLATE.waitUntil(Condition.appears,Configuration.timeout).click();
}
  /**
   * Edit a wiki template
   *
   * @param template String
   * @param text String
   * @param title String
   * @param subTitle String
   */
  public void editTemplate(String template, String title, String subTitle, String text) {
    if (title != "") {
      $(ELEMENT_TITLE_TEMPLATE).setValue(title);
    }

    $(ELEMENT_SAVE_TEMPLATE).click();
  }

  public void deleteTemplate(String template) {
    info("Delete template " + template);
    $(byText(template)).parent().parent().find(ELEMENT_WIKI_ICON_DELETE_TEMPLATE).click();
    alert.acceptAlert();
    ELEMENT_WIKI_LISTE_TEMPLATE.find(byText(template)).shouldNot(Condition.exist);  }

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
   * @param searchOption (Can be: User name, Last name, First name or Email option
   *          corresponding with information you input in "Search")
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

  }
}
