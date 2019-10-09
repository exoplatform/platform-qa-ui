package org.exoplatform.platform.qa.ui.news.pageobject;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ManageFileTestBase;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_INPUT_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.locator.news.NewsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

public class NewsComposerManagement {
  public ManageAlert alert;

  public Button button;

  public ManageFileTestBase mftb;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase TestBase
   */
  public NewsComposerManagement(TestBase testBase) {
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.mftb = new ManageFileTestBase(testBase);
  }

  /**
   * go to news composer
   */
  public void selectNewsComposer() {
    NEWS_COMPOSER_TAB.shouldBe(visible).click();
  }

  /**
   * Post a simple news in the current space
   *
   * @param title : News title
   * @param content : News Content
   */
  public void postSimpleNews(String title, String content) {
    selectNewsComposer();
    NEWS_COMPOSER_TITLE_INPUT.should(appears);
    NEWS_COMPOSER_TITLE_INPUT.setValue(title);
    if (content != null && !"".equals(content)) {
      info("----Add text into news content text box-----");
      fillContentField(content);
    }
    info("Save all changes");
    NEWS_COMPOSER_POST_BUTTON.waitUntil(enabled, timeout).click();

    info("-- Verify that news has been added --");
    $(byText(title)).should(exist);
    NEWS_COMPOSER_POST_BUTTON.shouldBe(disabled);
  }

  /**
   * Add text into news content text box
   *
   * @param text String
   */
  public void fillContentField(String text) {
    info("----Add text into into news content text box-----");
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    switchTo().frame(NEWS_COMPOSER_CONTENT_INPUT);
    ELEMENT_INPUT_ACTIVITY.click();
    ELEMENT_INPUT_ACTIVITY.sendKeys(text);
    switchTo().defaultContent();
  }

}
