package org.exoplatform.platform.qa.ui.api.config.driver;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
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

  private final boolean useRemoteWebDriver = Boolean.getBoolean("remote");

  private final boolean proxyEnabled       = Boolean.getBoolean("proxyEnabled");

  private final String browser = System.getProperty("browser");

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

    if (useRemoteWebDriver) {
      configureSeleniumHub();
    }

    // No default browser defined, we use chrome
    if (browser == null || browser.trim().equals("")) {
      Configuration.browser = WebDriverRunner.CHROME;
      Configuration.holdBrowserOpen = true;
    }

    if (Configuration.browser.equals(WebDriverRunner.CHROME)) {
      return configureChromeDriver(capabilities);

    } else {
      FirefoxProfile profile = new FirefoxProfile();
      profile.setPreference("intl.accept_languages", "en");
      capabilities.setCapability(FirefoxDriver.PROFILE, profile);
      return new FirefoxDriver(capabilities);
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
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    return new ChromeDriver(capabilities);

  }

  public void configureSeleniumHub() {

    final String ipAddress;
    try {
      ipAddress = getIpAddress();

      Configuration.baseUrl = "http://" + ipAddress + ":" + getPLFHTTPPort();

    } catch (SocketException e) {
      e.printStackTrace();
    }

  }

  private String getPLFHTTPPort() {
    String defaultPort = "8080";

    return defaultPort;
  }

  private String getHubHTTPPort() {
    String defaultPort = "4444";

    return defaultPort;
  }

  private String getIpAddress() throws SocketException {
    String ipAddress = null;

    Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
    for (; n.hasMoreElements();) {
      NetworkInterface e = n.nextElement();

      Enumeration<InetAddress> a = e.getInetAddresses();
      for (; a.hasMoreElements();) {
        InetAddress addr = a.nextElement();
        // Find local address
        if (addr.getHostAddress().contains("192")) {
          ipAddress = addr.getHostAddress();
        }
      }
    }
    return ipAddress;
  }
}
