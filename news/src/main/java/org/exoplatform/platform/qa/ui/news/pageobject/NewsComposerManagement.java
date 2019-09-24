package org.exoplatform.platform.qa.ui.news.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ManageFileTestBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_INPUT_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_CATEGORY_BREADCUMB_HOME;
import static org.exoplatform.platform.qa.ui.selenium.locator.news.newsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENET_SPACE_CREATE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

public class NewsComposerManagement {
  private final TestBase testBase;

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
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.mftb = new ManageFileTestBase(testBase);
  }

  /**
   * go to news composer
   */
  public void goToNews() {
         $(NEWS_COMPOSER_TAB).waitUntil(Condition.visible, Configuration.timeout).click();
  }

  /**
   * Create a simple news
   *
   * @param title : News title
   * @param content : News Content
   */
  public void addNewSimpleNews(String title, String content) {
    $(NEWS_COMPOSER_TITLE_INPUT).waitUntil(Condition.appears, Configuration.timeout);
    $(NEWS_COMPOSER_TITLE_INPUT).setValue(title);
    if (content != "" && content != null) {
      info("----Add text into news content text box-----");
      addText(content);
    }
    info("Save all changes");
    $(NEWS_COMPOSER_POST_BUTTON).click();
    $(NEWS_COMPOSER_POST_BUTTON).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
  }

  /**
   * Add text into news content text box
   *
   * @param text String
   */
  public void addText(String text) {
    info("----Add text into into news content text box-----");
    SelenideElement frame = $(byId("cke_newsContent")).find(byTitle("Rich Text Editor, newsContent")).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    switchTo().frame(frame);
    ELEMENT_INPUT_ACTIVITY.click();
    ELEMENT_INPUT_ACTIVITY.sendKeys(text);
    switchTo().defaultContent();
  }

}
