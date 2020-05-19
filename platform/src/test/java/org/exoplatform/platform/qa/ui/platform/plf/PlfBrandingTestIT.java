package org.exoplatform.platform.qa.ui.platform.plf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.BrandingLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_THEMEDARK;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_THEMELIGHT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("sniff")
@Tag("plf")
public class PlfBrandingTestIT extends Base {
  NavigationToolbar navigationToolbar;

  HomePagePlatform homePagePlatform;

  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);

    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }

  @Test
  public void test01_SelectNavigationBarStyleThenLogoThenCheckDisplayOfBrandingPortlet() {

    String path = "Question_77048.png";

    info("Select navigation bar style");

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToBanding();
    waitForAndGetElement(ELEMENT_PLF_BRANDINGPAGE, 2000, 0);
    waitForAndGetElement(ELEMENT_PLF_BRANDING_THEMELIGHT, 2000, 0);
    waitForAndGetElement(ELEMENT_PLF_BRANDING_THEMEDARK, 2000, 0);

    info("Select logo");
    ELEMENT_UPLOAD_LOGO.uploadFromClasspath("eXo-Platform.png");
    clickByJavascript($(ELEMENT_PLF_BRANDING_THEMELIGHT), 2);

    ELEMENT_BUTTON_APPLY.waitUntil(Condition.visible, Configuration.timeout).click();

    waitForAndGetElement(ELEMENT_PLF_BRANDING_TOPBAR_LOGO);
    waitForAndGetElement(ELEMENT_BANDING_PAGE_SELECT_LOGO);

    waitForAndGetElement(ELEMENT_TOOLBAR_THEMELIGHT, 2000, 0);
    clickByJavascript($(ELEMENT_PLF_BRANDING_THEMEDARK), 2);
    ELEMENT_BUTTON_APPLY.click();
    waitForAndGetElement(ELEMENT_TOOLBAR_THEMEDARK, 2000, 0);

    info("Check display of branding portlet");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToBanding();
    waitForAndGetElement(ELEMENT_PLF_BRANDINGPAGE, 2000, 0);
    waitForAndGetElement(ELEMENT_BANDING_PAGE_SELECT_LOGO);
    waitForAndGetElement(ELEMENT_BANDING_PAGE_SELECT_NAVIGATION_BAR_STYLE);
    ELEMENT_BUTTON_APPLY.waitUntil(Condition.appears, Configuration.timeout);
    waitForAndGetElement(ELEMENT_BUTTON_CANCEL);
  }

}
