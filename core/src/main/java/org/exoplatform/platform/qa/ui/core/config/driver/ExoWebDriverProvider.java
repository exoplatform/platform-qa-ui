package org.exoplatform.platform.qa.ui.core.config.driver;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import com.codeborne.selenide.WebDriverRunner;

/**
 * eXo Web Driver Provider to create the WebDriver and configured it for eXo
 * Platform requirements.
 */
public class ExoWebDriverProvider implements WebDriverProvider {

  private final String  chromeDriver          = System.getProperty("webdriver.chrome.driver");

  private final String  recommendedResolution = System.getProperty("window.size", "1366,768");

  /**
   * Create the WebDriver regarding to the System Properties configured.
   *
   * @param capabilities
   * @return
   */
  @Override
  public WebDriver createDriver(DesiredCapabilities capabilities) {

    // Override output configuration for Maven
    Configuration.reportsFolder = "target";

    // ChromeDriver
    if (chromeDriver != null && !chromeDriver.trim().equals("")) {
      return configureChromeDriver(capabilities);
    }

    if (Configuration.browser.equals(WebDriverRunner.CHROME)) {
      return configureChromeDriver(capabilities);

    } else {
      return configureFirefoxDriver(capabilities);
    }

  }

  private ChromeDriver configureChromeDriver(DesiredCapabilities capabilities) {
    ChromeOptions options = new ChromeOptions();
    // options.addArguments("--lang=en");
    Map<String, Object> prefs = new HashMap<>();
    // Language en by default because of XPath elements use text() with English
    // names
    prefs.put("intl.accept_languages", "en");
    options.setExperimentalOption("prefs", prefs);
    options.addArguments("--window-size=" + recommendedResolution);
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);

    return new ChromeDriver(capabilities);

  }

  private FirefoxDriver configureFirefoxDriver(DesiredCapabilities capabilities) {
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("intl.accept_languages", "en");

    capabilities.setCapability(FirefoxDriver.MARIONETTE, true);
    // To fix problem with Firefox/Marionette
    // https://github.com/mozilla/geckodriver/issues/517
    // profile.setPreference("browser.tabs.remote.autostart", false);
    // profile.setPreference("browser.tabs.remote.autostart.1", false);
    // profile.setPreference("browser.tabs.remote.autostart.2", false);
    profile.setPreference("browser.tabs.remote.force-enable", "false");

    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
    return new FirefoxDriver(capabilities);

  }

}
