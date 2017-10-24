package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.BrandingLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_THEMELIGHT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

@Tag("sniff")
@Tag("plf")
public class PlfBrandingTestIT extends Base {
  NavigationToolbar navigationToolbar;

  HomePagePlatform  homePagePlatform;

  ManageLogInOut    manageLogInOut;

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


  /**
   * <li>Case ID:120887.</li>
   * <li>Test Case Name: Select navigation bar style.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Open branding page Step
   * Description: - Login as admin - Connect to intranet home page - Go to
   * Administration button -> Portal -> Branding Input Data: Expected Outcome: -
   * Branding portlet is displayed Step number: 2 Step Name: Show a theme select
   * box Step Description: - Go to the part to select the theme - Click on the
   * select box Input Data: Expected Outcome: - The select box is opened with two
   * choices:* Dark* Light Step number: 3 Step Name: Select one theme Step
   * Description: - Select one from the list Input Data: Expected Outcome: - The
   * full navigation bar preview is displaying the selected style navigation
   * barover the navigation bar Step number: 4 Step Name: Save Step Description: -
   * Click on save button Input Data: Expected Outcome: - The navigation bar of
   * the intranet is displayed as preview
   */

  @Test
  public void test01_SelectNavigationBarStyle() {
    info("Test 1: Select navigation bar style");

    navigationToolbar.goToBanding();
    waitForAndGetElement(ELEMENT_PLF_BRANDINGPAGE);

    click(ELEMENT_PLF_BRANDING_SELECTTHEME);
    waitForAndGetElement(ELEMENT_PLF_BRANDING_THEMELIGHT, 2000, 0);
    waitForAndGetElement(ELEMENT_PLF_BRANDING_THEMEDARK, 2000, 0);

    click(ELEMENT_PLF_BRANDING_THEMELIGHT);
    waitForAndGetElement(ELEMENT_PLF_BRANDING_TOPBAR_THEMELIGHT, 2000, 0);

    ELEMENT_BUTTON_APPLY.click();
    waitForAndGetElement(ELEMENT_TOOLBAR_THEMELIGHT, 2000, 0);
    $(ELEMENT_PLF_BRANDING_SELECTTHEME).click();
    $(ELEMENT_PLF_BRANDING_THEMEDARK).click();
    ELEMENT_BUTTON_APPLY.click();
  }

  /**
   * <li>Case ID:120888.</li>
   * <li>Test Case Name: Select logo.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Open branding page Step
   * Description: - Login as admin - Connect to intranet home page - Go to
   * Administration button -> Portal -> Branding Input Data: Expected Outcome: -
   * Branding portlet is displayed Step number: 2 Step Name: Upload a logo Step
   * Description: - Upload a new logo image Input Data: Expected Outcome: - The
   * new logo image is uploaded Step number: 3 Step Name: Preview Step
   * Description: - Check preview boxes Input Data: Expected Outcome: - The logo
   * must be displayed in :* preview boxe for the logo* preview of the full
   * navigation bar done in the upload function
   * Step number: 4 Step Name: Save Step Description: -Click on Save Input Data:
   * Expected Outcome: New Logo is shown as preview
   */
  @Test
  public void test02_SelectLogo() {
    info("Test 2: Select logo");

    String path = "Question_77048.png";

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToBanding();
    waitForAndGetElement(ELEMENT_PLF_BRANDINGPAGE, 2000, 0);

    ELEMENT_UPLOAD_LOGO.uploadFromClasspath("TestBrandingLogo.png");

    ELEMENT_BUTTON_APPLY.click();
    waitForAndGetElement(ELEMENT_PLF_BRANDING_TOPBAR_LOGO);
    waitForAndGetElement(ELEMENT_BANDING_PAGE_SELECT_LOGO);

  }

  /**
   * <li>Case ID:120906.</li>
   * <li>Test Case Name: Check display of branding portlet.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Open Branding page Step
   * Description: - Login as an admin - Go to intranet home page - Click on
   * Administration -> Portal -> Branding Input Data: Expected Outcome: - Intranet
   * home page is opened - The Branding page is shown. There is 4 parts on the
   * layout : + Select Logo + Select Navigation Bar Style + Preview + Validation
   * buttons - See attachment.
   */
  @Test
  public void test03_CheckDisplayOfBrandingPortlet() {
    info("Test 3: Check display of branding portlet");

    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToBanding();
    waitForAndGetElement(ELEMENT_PLF_BRANDINGPAGE, 2000, 0);
    waitForAndGetElement(ELEMENT_BANDING_PAGE_SELECT_LOGO);
    waitForAndGetElement(ELEMENT_BANDING_PAGE_SELECT_NAVIGATION_BAR_STYLE);
    ELEMENT_BUTTON_APPLY.waitUntil(Condition.appears, Configuration.timeout);
    waitForAndGetElement(ELEMENT_BUTTON_CANCEL);
  }
}
