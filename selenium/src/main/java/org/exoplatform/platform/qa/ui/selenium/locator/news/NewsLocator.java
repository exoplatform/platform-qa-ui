package org.exoplatform.platform.qa.ui.selenium.locator.news;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class NewsLocator {

  public static final SelenideElement NEWS_COMPOSER_TAB = $(byId("ActivityComposerExt")).find(byClassName("UINewsActivityComposer"));;

  public static final By NEWS_COMPOSER_POST_BUTTON = By.id("newsPost");

  public static final By NEWS_COMPOSER_TITLE_INPUT = By.id("newsTitle");

  public static final SelenideElement NEWS_COMPOSER_CONTENT_INPUT = $(byId("cke_newsContent")).find(byClassName("cke_wysiwyg_frame"));
}
