package org.exoplatform.platform.qa.ui.news.pageobject;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ManageFileTestBase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.news.NewsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_INPUT_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

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
   * Open news composer
   */
  public void openNewsComposer() {
    NEWS_COMPOSER_LINK.shouldBe(visible).click();
    switchTo().window(1);
  }

  /**
   * Open news composer
   */
  public void openNewsEditor() {
    NEWS_EDITOR_LINK.shouldBe(visible).click();
    switchTo().window(2);
  }

  /**
   * Click on the Post button
   */
  public void postNews() {
    NEWS_COMPOSER_POST_BUTTON.waitUntil(enabled, timeout).click();
  }

  /**
   * Click on the Update button
   */
  public void updateNews() {
    NEWS_EDITOR_UPDATE_BUTTON.waitUntil(enabled, timeout).click();
  }

  /**
   * Add text into news title text box
   *
   * @param text String
   */
  public void fillTitleField(String text) {
    NEWS_COMPOSER_TITLE_INPUT.waitUntil(visible, timeout).setValue(text);
  }

  /**
   * Add text into news summary text box
   *
   * @param text String
   */
  public void fillSummaryField(String text) {
    NEWS_COMPOSER_SUMMARY_INPUT.waitUntil(visible, timeout).setValue(text);
  }

  /**
   * Add text into news content text box
   *
   * @param text String
   */
  public void fillBodyField(String text) {
    info("----Add text into into news content text box-----");
    switchTo().frame(NEWS_COMPOSER_CONTENT_INPUT);
    ELEMENT_INPUT_ACTIVITY.click();
    ELEMENT_INPUT_ACTIVITY.sendKeys(text);
    switchTo().defaultContent();
  }

  /**
   * Add text into news title text box
   */
  public void uploadIllustration() {
    NEWS_COMPOSER_ILLUSTRATION_INPUT.uploadFromClasspath("illustration.jpg");
  }

  /**
   * Create a News
   *
   * @param title
   * @param body
   */
  public void createNews(String title, String body) {
    openNewsComposer();

    fillTitleField(title);
    fillBodyField(body);

    postNews();
  }

  public void verifyPostButton(boolean buttonEnabled) {
    NEWS_COMPOSER_POST_BUTTON.shouldBe(visible).shouldBe(buttonEnabled ? enabled : disabled);
  }

  public void verifyUpdateButton(boolean buttonEnabled) {
    NEWS_EDITOR_UPDATE_BUTTON.shouldBe(visible).shouldBe(buttonEnabled ? enabled : disabled);
  }

  public void verifyNewsDetailsTitle(String text) {
    NEWS_DETAILS_TITLE.shouldBe(visible);
    NEWS_DETAILS_TITLE.shouldHave(text(text));
  }

  public void verifyNewsDetailsBody(String text) {
    NEWS_DETAILS_BODY.shouldBe(visible);
    NEWS_DETAILS_BODY.shouldHave(text(text));
  }

  public void verifyNewsEditorTitle(String text) {
    NEWS_COMPOSER_TITLE_INPUT.shouldBe(visible);
    NEWS_COMPOSER_TITLE_INPUT.shouldHave(value(text));
  }

  public void verifyNewsAuthor(String author) {
    NEWS_DETAILS_AUTHOR.shouldBe(visible);
    NEWS_DETAILS_AUTHOR.shouldHave(text(author));
  }

  public void verifyNewsUpdater(String updater) {
    NEWS_DETAILS_UPDATER.shouldBe(visible);
    NEWS_DETAILS_UPDATER.shouldHave(text(updater));
  }

  public boolean verifyNewsUpdaterExists() {
    return NEWS_DETAILS_UPDATER.exists();
  }

  public boolean verifyNewsButtonPinExists() {
    return NEWS_DETAILS_BUTTON_PIN.exists();
  }

  public boolean verifyNewsButtonEditExists() {
    return NEWS_DETAILS_BUTTON_EDIT.exists();
  }

  public boolean verifyNewsButtonShareExists() {
    return NEWS_DETAILS_BUTTON_SHARE.exists();
  }
}
