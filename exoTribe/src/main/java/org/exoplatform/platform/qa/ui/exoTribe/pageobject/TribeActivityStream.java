package org.exoplatform.platform.qa.ui.exoTribe.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static com.codeborne.selenide.Configuration.openBrowserTimeoutMs;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class TribeActivityStream {
    private final TestBase testBase;

    public Button button;

    public static HomePagePlatform homePagePlatform;

    private ElementEventTestBase evt;

    /**
     * constructor
     *
     * @param testBase testbase
     */

    public TribeActivityStream(TestBase testBase) {
        this.testBase = testBase;
        this.evt = testBase.getElementEventTestBase();
        this.button = new Button(testBase);
        this.homePagePlatform = new HomePagePlatform(testBase);

    }

    /**
     * Add text into activity text box
     *
     * @param text String
     */
    public void addText(String text) {
        info("----Add text into activity text box-----");
        SelenideElement frame = $(byXpath("(//*[@class='cke_wysiwyg_frame cke_reset'])[2]")).waitUntil(Condition.visible, openBrowserTimeoutMs);
        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        ELEMENT_INPUT_ACTIVITY.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).sendKeys(text);
        switchTo().defaultContent();
    }

    /**
     * Add link activity
     *
     * @param link String
     */
    public void addLink(String link) {
        info("----Click on Link----");
        ELEMENT_TAB_ADD_LINK.click();

        info("----Input link into link box-----");
        ELEMENT_INPUT_LINK.setValue(link);

        info("----Click attach button-----");
        $(ELEMENT_COMPOSER_ATTACH_LINK_BUTTON).click();
        evt.waitForAndGetElement(By.id("LinkTitle"));
    }

    /**
     * Share activity
     */
    public void shareActivity() {
        info("----Click share button----");
        if (testBase.getBrowser().contains("iexplorer")) {
            info("mouse over and click");

            WebElement el = evt.waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON, 2000, 1, 2);
            el.sendKeys("\n");
        } else
            $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
    }

    /**
     * Add new activity for space
     *
     * @param text String
     * @param link String
     */
    public void addActivity(String text, String link) {
        info("-- Adding an activity--");

        if (text != "" && text != null) {
            info("----Add text into activity text box-----");
            addText(text);
        }
        if (link != "" && link != null) {
            info("----Add link into activity text box-----");
            addLink(link);
        }
        shareActivity();
        info("-- Verify that an activity has been added --");
        $(byText(text)).waitUntil(Condition.exist, openBrowserTimeoutMs);
        $(ELEMENT_COMPOSER_SHARE_BUTTON).shouldBe(Condition.disabled);
        info("The activity is shared success");
    }

    public void deleteactivity(String text) {
        // get the id of activity created
        info("-- Editing an activity--");
        homePagePlatform.refreshUntil($(byText(text)), Condition.visible, 500);
        String idActivity = $(byText(text)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
        $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
        $(byText(text)).waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
    }

}
