package org.exoplatform.platform.qa.ui.platform.social.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("functional")
@Tag("social")
public class SpaceAdministrationTestIT extends Base {
    NavigationToolbar navigationToolbar;
    ManageLogInOut manageLogInOut;
    HomePagePlatform homePagePlatform;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut = new ManageLogInOut(this);
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    }

    @Test
    public void test01_CheckTheNewSpacesSubmenuInTheAdministrationMenu() {
        info("Test 1: CheckTheNewSpacesSubmenuInTheAdministrationMenu");
        $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, Configuration.timeout);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        $(byAttribute("href", "/portal/g/:platform:administrators/spacesAdministration")).waitUntil(Condition.visible,Configuration.timeout);

    }

    @Test
    public void test02_CheckThatOnlyAdministratorsAreRedirectedToSpaceManagementPage() {
        info("Test 2: CheckThatOnlyAdministratorsAreRedirectedToSpaceManagementPage");
        $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, 10000);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/a")).click();
    }

    @Test
    public void test03_Checktabsinthespacemanagementpage() {
        info("Test 3: CheckThatOnlyAdministratorsAreRedirectedToSpaceManagementPage");
        $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, 10000);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/a")).click();
        $(byXpath("//*[@id=\"1b6db64e-29cf-410f-908e-e3a43400401d\"]/div/div/div/ul/li[1]/a")).click();
        $(byXpath("//*[@id=\"1b6db64e-29cf-410f-908e-e3a43400401d\"]/div/div/div/ul/li[2]/a")).click();
    }
}