package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

@Tag("ecms")
@Tag("sniff")
public class EcmsSESettingTestIT extends Base {
  NavigationToolbar navigationToolbar;

  SiteExplorerHome  siteExplorerHome;

  ManageLogInOut    manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    siteExplorerHome = new SiteExplorerHome(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:116604.</li>
   * <li>Test Case Name: Set up browsing Preferences.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: - Step Description: Step
   * 1: Set up browsing Preferences Input Data: - Go to a driver - Click on [Set
   * up your browsing preference] icon (wheel icon on the top right of action bar)
   * - Perform to set up - Click Save Expected Outcome: New Displaying is shown
   * directly
   */
  @Test
  public void test01_SetUpBrowsingPreferences() {
    info("Test 1: Set up browsing Preferences");

        navigationToolbar.goToSiteExplorer();
        siteExplorerHome.openSettingsDriver(SiteExplorerHome.selectDriverOption.MODIFIEDDATE,
                SiteExplorerHome.selectDriverOrder.DESCENDING);
        $(By.xpath("//a[@class='refresh btn']")).waitUntil(Condition.visible,Configuration.timeout).click();
        siteExplorerHome.openSettingsDriver(SiteExplorerHome.selectDriverOption.ALPHABETICAL,
                SiteExplorerHome.selectDriverOrder.ASCENDING);
    }
}
