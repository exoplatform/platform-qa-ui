package org.exoplatform.platform.qa.ui.onlyOffice.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public static void editOnlineFromAS() {
        ELEMENT_EDIT_ONlINE_BUTTON.waitUntil(visible,openBrowserTimeoutMs).click();
    }

    /**
     * Edit Online button from Preview Document
     */
    public static void editOnlineFromPreview() {
        ELEMENT_EDIT_ONLINE_BUTTON_FROM_PREVIEW.waitUntil(visible, openBrowserTimeoutMs).click();
    }

    /**
     * Check alignment of Element
     * @param element1
     * @param element2
     * @param element3
     */
    public void checkAlignmentElement(SelenideElement element1, SelenideElement element2, SelenideElement element3) {
        int locElement1Button = element1.getLocation().y;
        int locElement2Button = element2.getLocation().y;
        int locElement3Button = element3.getLocation().y;
        assertEquals(locElement1Button,locElement2Button,locElement3Button);
    }
}
