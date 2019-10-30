package org.exoplatform.platform.qa.ui.onlyOffice.pageobject;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;

public class OnlyOfficeEditingPage {

    private final TestBase testBase;

    public ManageAlert alert;

    public HomePagePlatform homePagePlatform;

    private ElementEventTestBase evt;

    public OnlyOfficeEditingPage(TestBase testBase) {
        this.testBase = testBase;
        this.evt = testBase.getElementEventTestBase();
        this.homePagePlatform = new HomePagePlatform(testBase);
        this.alert = new ManageAlert(testBase);
    }

    /**
     * Check that document is opened on OnlyOffice Editor
     * @param documentName
     * @param extension
     */
    public void checkOpeningDocumentWithEditOnline(String documentName,String extension) {
        $(byText(documentName + extension)).waitUntil(visible, openBrowserTimeoutMs);
        $(".editor").waitUntil(visible, 60000).exists();
        switchTo().frame("frameEditor");
        $("#view-left-menu").parent().find(".tool-menu.left").waitUntil(visible, 120000);
    }
}
