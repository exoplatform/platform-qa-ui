package org.exoplatform.platform.qa.ui.platform.wiki.functional;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.DownloadFileControl.driver;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.ELEMENT_TITLE_WIKI_HOME_LINK;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ManageLayout;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;

/**
 * Created by exo on 5/10/18.
 */
public class WikiBasicActionPublicPageTestIT extends Base {

  ManageLogInOut    manageLogInOut;

  NavigationToolbar navigationToolbar;

  HomePagePlatform  homePagePlatform;

  WikiHomePage      wikiHomePage;

  ManageLayout      manageLayout;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    manageLogInOut = new ManageLogInOut(this);
    navigationToolbar = new NavigationToolbar(this);
    homePagePlatform = new HomePagePlatform(this);
    wikiHomePage = new WikiHomePage(this);
    manageLayout = new ManageLayout(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  @AfterEach
  public void signout() {
    manageLogInOut.signOut();
  }

  /**
   * <li>Case ID:139611.</li>
   * <li>Test Case Name: Make a Wiki page in public mode.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Make public for
   * Intranet site Step Description: - Connect to intranet as administrator - On
   * homepage: From top navigation, click Edit ->Site ->Layout - Click Sites
   * config ->Permission tab ->Access permission - Tick [Make it public] - Save
   * Input Data: Expected Outcome: - Site is public Step number: 2 Step Name: Step
   * 2: Make public for portlets in Intranet site Step Description: - Edit all
   * portlets in this site with permission [Make it public] is ticked - Finish
   * Input Data: Expected Outcome: - All portlets are public Step number: 3 Step
   * Name: Step 3: Make public for a Wiki page Step Description: - Open Wiki
   * application - Click Edit ->Page ->Layout - Click Page Properties ->Permission
   * tab ->Access permission - Tick [Make it public] - Save Input Data: Expected
   * Outcome: Step number: 4 Step Name: Step 4: Make public for all portlets in
   * Wiki page Step Description: - Make public for both wiki portlet and container
   * by setting Access permission is [Make it public] - Finish Input Data:
   * Expected Outcome: - All portlets are public Step number: 5 Step Name: Step 5:
   * Logout Step Description: - Log out Input Data: Expected Outcome: - Log out
   * normally and no exception throws for this logout. - Wiki home is displayed
   */
  @Test
  public void test01_MakeAWikiPageInPublicMode() {
    info("Test 1: Make a Wiki page in public mode");
    navigationToolbar.goToEditSiteLayout();
    manageLayout.goToSiteConfigPopup();
    manageLayout.goToSitePermissionTab();
    manageLayout.publicMode();
    manageLayout.saveChangesSiteConfig();
    // manageLayout.publicModePortlet(ManageLayout.homePortletName.Breadcrumbs);
    manageLayout.publicModePortlet(ManageLayout.homePortletName.Company);
    manageLayout.publicModePortlet(ManageLayout.homePortletName.Groups);
    manageLayout.publicModePortlet(ManageLayout.homePortletName.Space);
    manageLayout.saveChangesSiteLayout();
    homePagePlatform.goToWiki();
    String wiki_url = driver.getCurrentUrl();
    navigationToolbar.goToEditLayout();
    manageLayout.goToPagePropertiesPopup();
    manageLayout.goToPagePermissionTab();
    manageLayout.publicMode();
    manageLayout.saveChangesPropertiesPopup();
    manageLayout.publicModeWikiPortletContainer();
    manageLayout.saveChangesContainerPopup();
    manageLayout.saveChangesPageLayout();
    manageLogInOut.signOut();
    driver.get(wiki_url);
    info("Verify that wiki home page is shown");
    waitForAndGetElement(ELEMENT_TITLE_WIKI_HOME_LINK);

  }
}
