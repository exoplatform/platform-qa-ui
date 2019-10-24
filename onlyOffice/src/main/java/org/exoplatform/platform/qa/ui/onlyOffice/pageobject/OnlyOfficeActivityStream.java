package org.exoplatform.platform.qa.ui.onlyOffice.pageobject;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import static org.exoplatform.platform.qa.ui.selenium.locator.onlyOffice.OnlyOfficeLocator.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;

public class OnlyOfficeActivityStream{

    private final TestBase testBase;

    public ManageAlert alert;

    public HomePagePlatform homePagePlatform;


    private ElementEventTestBase evt;

    public OnlyOfficeActivityStream(TestBase testBase) {
        this.testBase = testBase;
        this.evt = testBase.getElementEventTestBase();
        this.homePagePlatform = new HomePagePlatform(testBase);
        this.alert = new ManageAlert(testBase);
    }

    /**
     * Edit Online button from AS
     */
    public void editOnlineFromAS() {
        ELEMENT_EDIT_ONlINE_BUTTON.waitUntil(visible,openBrowserTimeoutMs).click();
    }

    /**
     * Edit Online button from Preview Document
     */
    public void editOnlineFromPreviw() {
        ELEMENT_EDIT_ONlINE_BUTTON.waitUntil(visible,openBrowserTimeoutMs).click();
    }
}
