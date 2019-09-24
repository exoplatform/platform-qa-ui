package org.exoplatform.platform.qa.ui.selenium.locator.news;

import org.openqa.selenium.By;

public class newsLocator {

  public static final By NEWS_COMPOSER_TAB = By.xpath(".//*[@id='ActivityComposerExt']//*[contains(@class,'UINewsActivityComposer')]");

  public static final By NEWS_COMPOSER_POST_BUTTON = By.xpath(".//*[@id='newsPost']");

  public static final By NEWS_COMPOSER_TITLE_INPUT = By.xpath(".//*[@id='newsTitle']");

  public static final By NEWS_COMPOSER_CONTENT_INPUT = By.xpath(".//*[@id='newsContent']");

}
