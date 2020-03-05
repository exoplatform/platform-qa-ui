package org.exoplatform.platform.qa.ui.news.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.news.NewsLocator;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.testbase.ManageFileTestBase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.news.NewsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_INPUT_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class NewsComposerManagement {
  public ManageAlert alert;

  public Button button;

  public ManageFileTestBase mftb;
  NavigationToolbar navigationToolbar;

  /**
   * constructor
   *
   * @param testBase TestBase
   */
  public NewsComposerManagement(TestBase testBase) {
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.mftb = new ManageFileTestBase(testBase);
    navigationToolbar = new NavigationToolbar(testBase);
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
    switchTo().window(1);
  }

  /**
   * Click on the Post button
   */
  public void postNews() {
    // we check if at least a first draft has been saved before posting the News, as a workaround for this
    // bug (we should be able to post a News even if no draft has been saved before)
    verifyDraftSaved();

    NEWS_COMPOSER_POST_BUTTON.waitUntil(enabled, timeout).click();
  }

  /**
   * Click on the Update button
   */
  public void updateNews() {
    NEWS_EDITOR_UPDATE_BUTTON.waitUntil(enabled, timeout).click();
  }

  /**
   * Click on the Update and post button
   */
  public void updateAndPostNews() {
    NEWS_EDITOR_UPDATE_AND_POST_BUTTON.waitUntil(enabled, timeout).click();
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

  public void verifyNewsDetailsIllustrationVIsible() {
    NEWS_DETAILS_ILLUSTRATION.shouldBe(visible);
  }

  public void verifyNewsDetailsPublicationDateVisible() { NEWS_DETAILS_PUBLICATION_DATE.shouldBe(visible); }

  public void verifyNewsComposerLinkVisible(boolean linkVisible) {
    NEWS_COMPOSER_LINK.shouldBe(linkVisible ? visible : not(visible));
  }

  public void verifyDraftSaved() {
    NEWS_DRAFT_STATUS.waitUntil(visible, timeout).shouldNotBe(empty);
  }

  public void verifySpaceNameIsVisible (){ELEMENT_SPACE_NAME.shouldBe(visible);}

  public boolean verifyNewsUpdaterExists() {
    return NEWS_DETAILS_UPDATER.exists();
  }

  public boolean verifyNewsButtonPinExists() {
    return NEWS_DETAILS_BUTTON_PIN_UNPIN.exists();
  }

  public boolean verifyNewsButtonEditExists() {
    return NEWS_DETAILS_BUTTON_EDIT.exists();
  }

  public boolean verifyNewsButtonShareExists() {
    return NEWS_DETAILS_BUTTON_SHARE.exists();
  }


  public void addUserRedactor(String redactor, String spaceName, String username) {
    info("Give the role redactor to user " + username);
    navigationToolbar.goToManageCommunity();
    ELEMENT_GROUPS_BUTTON.click();
    ELEMENT_SPACES_BUTTON.click();
    ELEMENT_GROUPS_PANEL.find(byText(spaceName)).click();
    ELEMENT_USERNAME_INPUT.waitUntil(Condition.visible, Configuration.timeout).setValue(username);
    ELEMENT_MEMBERSHIP_SELECT.selectOptionByValue(redactor);
    NewsLocator.ELEMENT_SAVE_BUTTON.click();
  }

  public void addUserPublisher(String publisher, String username) {
    info("Give the role publisher to user " + username);
    navigationToolbar.goToManageCommunity();
    ELEMENT_GROUPS_BUTTON.click();
    ELEMENT_PLATFORM_BUTTON.click();
    ELEMENT_CONTENT_MANAGEMENT_BUTTON.click();
    ELEMENT_USERNAME_PUBLISHER_INPUT.waitUntil(Condition.visible, Configuration.timeout).setValue(username);
    ELEMENT_MEMBERSHIP_SELECT.selectOptionByValue(publisher);
    NewsLocator.ELEMENT_SAVE_BUTTON.click();
  }

  public void pin_unpin_news_details() {
    NEWS_DETAILS_BUTTON_PIN_UNPIN.click();

  }

  public void ConfirmPinUnPinArticle() {

    CONFIRM_PIN_ARTICLE.click();
  }

  public void pinNewsFromCreationForm() {
    NEWS_CREATION_FORM_PINUNPIN_BUTTON.click();
  }

  public void unpinNewsFromCreationForm() {
    NEWS_CREATION_FORM_PINUNPIN_BUTTON.click();
  }
}
