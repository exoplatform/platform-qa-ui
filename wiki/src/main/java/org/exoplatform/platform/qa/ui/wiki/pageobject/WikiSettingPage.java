package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiSettingPage {

  /**
   * constructor
   *
   * @param dr
   */
  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public WikiSettingPage(TestBase testBase) {
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
    $(ELEMENT_TEMPLATE_SEARCH_TEXTBOX).setValue(template);

    info("Press Enter key");
    testBase.getExoWebDriver().getWebDriver().findElement(ELEMENT_TEMPLATE_SEARCH_TEXTBOX).sendKeys(Keys.ENTER);

    info("Verify that the search results is shown that matchs with keyword");

    $(byClassName("TemplateGrid")).find(byText(template)).should(Condition.exist);

  }

  /**
   * Edit a wiki template
   *
   * @param template
   * @param text
   */
  public void editTemplate(String template, String newTitle, String newDes, String newContent) {
    evt.click(By.xpath(ELEMENT_EDIT_TEMPLATE.replace("{$template}", template)));
    if (!newTitle.isEmpty()) {
      info("Input the title for the template");
      evt.type(ELEMENT_TITLE_TEMPLATE, newTitle, true);
    }

    if (!newDes.isEmpty()) {
      info("Input the description for the template");
      evt.type(ELEMENT_DESCRIPTION_TEMPLATE, newDes, true);
    }

    if (!newContent.isEmpty()) {
      info("Input the content for the template");
      evt.type(ELEMENT_CONTENT_TEMPLATE, newContent, true);
    }
    saveTemplate();
  }

  /**
   * Delete a template
   *
   * @param template
   */
  public void deleteTemplate(String template) {
    if (evt.waitForAndGetElement(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template), 2000, 0) != null) {
      info("Delete template " + template);
      evt.click(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template));
      alert.acceptAlert();
      evt.waitForElementNotPresent(By.xpath(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template)));
    }
  }

  /**
   * Cancel deleting a template
   *
   * @param template
   */
  public void deleteTemplateWithCanceling(String template) {
    info("Delete template " + template);
    evt.click(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template));
    alert.cancelAlert();
    evt.waitForAndGetElement(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template));
  }

  /**
   * Open template tab
   */
  public void goToTemplateTab() {
    info("click on the template tab");
    evt.click(ELEMENT_WIKI_SETTING_TEMPLATE_TAB);

  }

  /**
   * Open Permission tab
   */
  public void goToPermissionTab() {
    info("Click on Permission tab");
    evt.click(ELEMENT_WIKI_SETTING_PERMISSION_TAB);

  }

  /**
   * Save all changes for the template
   */
  public void saveTemplate() {
    info("Click on Save template");
    evt.click(ELEMENT_SAVE_TEMPLATE);
    evt.waitForElementNotPresent(ELEMENT_SAVE_TEMPLATE);
  }

  /**
   * Cancel all changes for the template
   */
  public void cancelTemplate() {
    info("Click on Cancel template");
    evt.click(ELEMENT_CANCEL_TEMPLATE);

  }

  /**
   * Add new a template
   *
   * @param title
   * @param des
   * @param content
   */
  public void addTemplate(String title, String des, String content) {
    info("Click on Add more Template button");
    evt.click(ELEMENT_WIKI_SETTING_ADD_MORE_TEMPALTE);

    if (!title.isEmpty()) {
      info("Input the title for the template");
      evt.type(ELEMENT_TITLE_TEMPLATE, title, true);
    }

    if (!des.isEmpty()) {
      info("Input the description for the template");
      evt.type(ELEMENT_DESCRIPTION_TEMPLATE, des, true);
    }

    if (!content.isEmpty()) {
      info("Input the content for the template");
      evt.type(ELEMENT_CONTENT_TEMPLATE, content, true);
    }
  }

}
