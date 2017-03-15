package org.exoplatform.platform.qa.ui.api.config.driver;

import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.Configuration;

/**
 *
 */
public class ExoWebDriver implements Driver {

  public WebDriver getWebDriver() {
    /** Selenide Driver, a wrapper of Selenium Web Driver */
    return com.codeborne.selenide.WebDriverRunner.getWebDriver();
  }

  public String getBrowser() {
    return Configuration.browser;
  }

  public String getBaseUrl() {
    return Configuration.baseUrl;
  }

  /**
   * TODO: Implements it
   * 
   * @param locale
   * @return
   */
  @Override
  public WebDriver getDriverSetLanguage(String locale) {
    // @TODO the goal is to set profile.setPreference("intl.accept_languages",
    // locale);
    return getWebDriver();
  }

  /**
   * TODO
   */
  @Override
  public void setPreferenceRunTime() {
    // @TODO the goal is to set fp.setPreference("dom.max_script_run_time", 30);
  }

  /**
   * function set seleniumWebDriver to auto open new window when click link
   */
  public WebDriver getDriverAutoOpenWindow() {
    // @TODO the goal is to set
    // fp.setPreference("browser.link.open_newwindow.restriction", 2);
    return getWebDriver();
  }

}
