package org.exoplatform.platform.qa.ui.platform.social.functional;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("functional")
@Tag("social")

public class CheckThatOnlyAdministratorsAreRedirectedToSpaceManagementPageTestIT extends Base {

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
    public void test01_CheckThatOnlyAdministratorsAreRedirectedToSpaceManagementPage() {
        info("Test 1: CheckThatOnlyAdministratorsAreRedirectedToSpaceManagementPage");
        $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, 10000);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/a")).click();
    }
}