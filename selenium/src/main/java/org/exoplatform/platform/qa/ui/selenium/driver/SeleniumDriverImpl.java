/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - Selenium (Legacy Code).
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - Selenium (Legacy Code); if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.selenium.driver;

import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.api.Driver;
import org.exoplatform.platform.qa.ui.selenium.logger.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;

/**
 * This class intend to manage the Selenide and Selenium SeleniumDriverImpl configuration
 * for the whole UI Automation Tests.
 * <p>
 * <i>Refactoring:</i><br/>
 * This class contains the old code from TestBase about <b>SeleniumDriverImpl
 * configuration</b>.
 * </p>
 */
public class SeleniumDriverImpl implements Driver {

  private WebDriver seleniumDriver;

  private String browser;

  private String server;

  private String baseUrl;

  public SeleniumDriverImpl() {
    browser = System.getProperty("browser");
    server = System.getProperty("server");
    baseUrl = System.getProperty("baseUrl");
  }

  public SeleniumDriverImpl(WebDriver seleniumDriver) {
    this.seleniumDriver = seleniumDriver;
    browser = Configuration.browser;
    baseUrl = Configuration.baseUrl;
  }

  public WebDriver createSeleniumWebDriver() {

    return initDriver(browser);
  }

  /**
   * change language of browser
   *
   * @param language English: "en" French: "fr"
   */
  public WebDriver initFFBrowserWithSetLanguageBrowser(String language) {
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("intl.accept_languages", language);
    seleniumDriver = new FirefoxDriver(profile);
    return seleniumDriver;
  }

  /**
   * initNewIEBrowserWithNativeEvent
   */
  public WebDriver initNewIEBrowserWithNativeEvent() {
    Logger.info("initNewIEBrowserWithNativeEvent");
    DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
    capabilitiesIE.setCapability("ignoreProtectedModeSettings", true);
    capabilitiesIE.setCapability("nativeEvents", true);
    capabilitiesIE.setCapability("ignoreZoomSetting", true);
    capabilitiesIE.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
    capabilitiesIE.setCapability("initialBrowserUrl", baseUrl);
    return new InternetExplorerDriver(capabilitiesIE);
  }

  /**
   * init newDriver
   */
  public WebDriver initDriver(final String browser) {
    WebDriver newDriver;
    if ("chrome".equals(browser)) {
      newDriver = initChromeDriver();
    } else if ("iexplorer".equals(browser)) {
      newDriver = initIEDriver();
    } else {
      newDriver = initFFDriver();
    }
    newDriver.manage().window().maximize();
    return newDriver;
  }

  /**
   * init newDriver
   */
  public WebDriver initDriver() {
    return initDriver(browser);
  }

  public boolean isChromeDriver(WebDriver wd) {
    return wd instanceof ChromeDriver;
  }

  public boolean isIEDriver(WebDriver wd) {
    return wd instanceof InternetExplorerDriver;
  }

  public boolean isIEDriver() {
    return seleniumDriver instanceof InternetExplorerDriver;
  }

  public boolean isFFDriver(WebDriver wd) {
    return wd instanceof FirefoxDriver;
  }

  /**
   * Start new seleniumWebDriver without cache
   */
  public WebDriver startNewDriver() {
    final WebDriver newDriver = initDriver();
    newDriver.manage().window().maximize();
    newDriver.navigate().refresh();
    newDriver.navigate().to(baseUrl);
    return newDriver;
  }

  /**
   * Init Chrome seleniumWebDriver
   */
  public ChromeDriver initChromeDriver() {
    Logger.info("Init chrome seleniumWebDriver");
    String pathFile = "";
    String fs = File.separator;
    String temp = System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput";
    ;
    pathFile = temp.replace("/", fs).replace("\\", fs);

    // Add the WebDriver proxy capability.
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    String[] switches = { "start-maximized", "remote-debugging-port=9222" };
    capabilities.setCapability("chrome.switches", switches);
    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
    chromePrefs.put("profile.default_content_settings.popups", 0);
    chromePrefs.put("download.default_directory", pathFile);
    ChromeOptions options = new ChromeOptions();
    HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
    options.setExperimentalOption("prefs", chromePrefs);
    options.addArguments("--test-type");
    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    return new ChromeDriver(capabilities);
  }

  /**
   * Init IE seleniumWebDriver
   */
  public WebDriver initIEDriver() {
    Logger.info("Init IE seleniumWebDriver");
    DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
    capabilitiesIE.setCapability("ignoreProtectedModeSettings", true);
    Logger.info("Set nativeEvent is FALSE");
    capabilitiesIE.setCapability("nativeEvents", false);
    capabilitiesIE.setCapability("javascriptEnabled", true);
    capabilitiesIE.setCapability("requireWindowFocus", true);
    capabilitiesIE.setCapability("enablePersistentHover", false);
    capabilitiesIE.setCapability("ignoreZoomSetting", true);
    capabilitiesIE.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
    capabilitiesIE.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
    capabilitiesIE.setCapability("initialBrowserUrl", baseUrl);
    return new InternetExplorerDriver(capabilitiesIE);
  }

  /**
   * Init FF seleniumWebDriver
   */
  public WebDriver initFFDriver() {
    String pathFile = "";
    Logger.info("Init FF seleniumWebDriver");
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("plugins.hide_infobar_for_missing_plugin", true);
    profile.setPreference("dom.max_script_run_time", 0);
    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
    Logger.info("Save file to " + pathFile);
    profile.setPreference("browser.download.manager.showWhenStarting", false);
    profile.setPreference("browser.download.dir", pathFile);
    profile.setPreference("browser.download.folderList", 2);
    profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                          "application/x-xpinstall;"
                                  + "application/x-zip;application/x-zip-compressed;application/x-winzip;application/zip;"
                                  + "gzip/document;multipart/x-zip;application/x-gunzip;application/x-gzip;application/x-gzip-compressed;"
                                  + "application/x-bzip;application/gzipped;application/gzip-compressed;application/gzip"
                                  + "application/octet-stream" + ";application/pdf;application/msword;text/plain;"
                                  + "application/octet;text/calendar;text/x-vcalendar;text/Calendar;"
                                  + "text/x-vCalendar;image/jpeg;image/jpg;image/jp_;application/jpg;"
                                  + "application/x-jpg;image/pjpeg;image/pipeg;image/vnd.swiftview-jpeg;image/x-xbitmap;image/png;application/xml;text/xml;text/icalendar;");

    profile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf");
    profile.setPreference("pref.downloads.disable_button.edit_actions", true);
    profile.setPreference("pdfjs.disabled", true);
    profile.setPreference("browser.helperApps.alwaysAsk.force", false);
    return new FirefoxDriver(profile);
  }

  /**
   * function set seleniumWebDriver to auto open new window when click link
   */
  public WebDriver getDriverAutoOpenWindow() {
    FirefoxProfile fp = new FirefoxProfile();
    fp.setPreference("browser.link.open_newwindow.restriction", 2);
    return new FirefoxDriver(fp);
  }

  /**
   * set language
   *
   * @param locale
   */
  public WebDriver getDriverSetLanguage(String locale) {
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("intl.accept_languages", locale);
    return new FirefoxDriver(profile);
  }

  /**
   * setPreferenceRunTime
   */
  public void setPreferenceRunTime() {
    FirefoxProfile fp = new FirefoxProfile();
    fp.setPreference("dom.max_script_run_time", 30);
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public String getServer() {
    return server;
  }

  public String getBrowser() {
    return browser;
  }

}
