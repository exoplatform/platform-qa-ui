package org.exoplatform.platform.qa.ui.digitalWorkplace.pageobject;

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
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.ELEMENT_TRIBE_POST_ACTIVITY_BUTTON;
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
     * Check if there is an activity in the stream
     *
     * @param name String
     */
    public void checkActivity(String name) {
        info("Verify that the activity of the name:" + name + " is shown");
        $(byText(name)).waitUntil(Condition.visible, openBrowserTimeoutMs);
        info("The activity of the name:" + name + " is shown successfully");
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

    public void addMessage(String text) {
        info("----Add text into activity text box-----");
        SelenideElement frame = $(byXpath("//*[@class='cke_wysiwyg_frame cke_reset']")).waitUntil(Condition.visible, openBrowserTimeoutMs);
        switchTo().frame(frame);
        ELEMENT_INPUT_ACTIVITY.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        ELEMENT_INPUT_ACTIVITY.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).sendKeys(text);
        switchTo().defaultContent();
    }

    public void addTribeActivity(String text, String link) {
        info("-- Adding an activity--");

        if (text != "" && text != null) {
            info("----Add text into activity text box-----");
            addMessage(text);
        }
        if (link != "" && link != null) {
            info("----Add link into activity text box-----");
            addLink(link);
        }
        postActivity();
        info("-- Verify that an activity has been added --");
        $(byText(text)).waitUntil(Condition.exist, openBrowserTimeoutMs);
        $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).shouldBe(Condition.disabled);
        info("The activity is shared success");
    }


    public void postActivity() {
        info("----Click share button----");
        if (testBase.getBrowser().contains("iexplorer")) {
            info("mouse over and click");

            WebElement el = evt.waitForAndGetElement(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON, 2000, 1, 2);
            el.sendKeys("\n");
        } else
            $(ELEMENT_TRIBE_POST_ACTIVITY_BUTTON).click();
    }

    public void likeActivity(String activity) {

       String id = $(byXpath("//*[@class='description' and text()='${activity}']/preceding::*[@class='heading']//*[@class='btn-group uiDropdownWithIcon actLink ']".replace("${activity}",activity))).waitUntil(Condition.visible, openBrowserTimeoutMs).getAttribute("id").split("dropDownEditActivity")[1];
        $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).click();
        $(byXpath(ELEMENT_UNLIKE_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout);
        /*
         * Step number: 2 Step Name: Check Likes part Step Description: - Check avatar -
         * Mouse over the avatar Input Data: Expected Outcome: - Avatar of liker is
         * added into likes part, the oldest liker is displayed at the right and the
         * newest at the left. - Profile pictures of users popup
         */

        ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    }

    public void likeActivityDW(String activity) {

        String id = $(byXpath("//*[@class='description']//*[text()='${activity}']/preceding::*[@class='heading']//*[@class='btn-group uiDropdownWithIcon actLink ']".replace("${activity}",activity))).waitUntil(Condition.visible, openBrowserTimeoutMs).getAttribute("id").split("dropDownEditActivity")[1];
        $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).click();
        $(byXpath(ELEMENT_UNLIKE_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout);
        /*
         * Step number: 2 Step Name: Check Likes part Step Description: - Check avatar -
         * Mouse over the avatar Input Data: Expected Outcome: - Avatar of liker is
         * added into likes part, the oldest liker is displayed at the right and the
         * newest at the left. - Profile pictures of users popup
         */

        ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    }

    public void addActivityComment(String activity, String comment) {

        String id = $(byXpath("//*[@class='description']//*[text()='${activity}']/preceding::*[@class='heading']//*[@class='btn-group uiDropdownWithIcon actLink ']".replace("${activity}",activity))).waitUntil(Condition.visible, openBrowserTimeoutMs).getAttribute("id").split("dropDownEditActivity")[1];


        $(byXpath(ELEMENT_COMMENT_LINK.replace("{id}", id))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
        // insert comment
        $(byId(ELEMENT_COMMENT_INPUT.replace("{id}", id))).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
        sleep(2000);
        executeJavaScript("CKEDITOR.instances.CommentTextarea" + id + ".insertText(\"" + comment + "\")", "");
        // click on the button comment
        $(byXpath(ELEMENT_COMMENT_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).pressEnter().waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs);

        $(byText(comment)).waitUntil(Condition.exist, Configuration.openBrowserTimeoutMs);
        sleep(1000);
        executeJavaScript("window.scrollBy(0,-2000)");

    }

    public void unlikeActivity(String activity) {

        String id = $(byXpath("//*[@class='description' and text()='${activity}']/preceding::*[@class='heading']//*[@class='btn-group uiDropdownWithIcon actLink ']".replace("${activity}",activity))).waitUntil(Condition.visible, openBrowserTimeoutMs).getAttribute("id").split("dropDownEditActivity")[1];
        $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).click();
        $(byXpath(ELEMENT_LIKE_BUTTON.replace("{id}", id))).waitUntil(Condition.appears, Configuration.timeout);
        /*
         * Step number: 2 Step Name: Check Likes part Step Description: - Check avatar -
         * Mouse over the avatar Input Data: Expected Outcome: - Avatar of liker is
         * added into likes part, the oldest liker is displayed at the right and the
         * newest at the left. - Profile pictures of users popup
         */

        ELEMENT_WHO_LIKED_POPUP.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    }

    public void deleteactivity(String text) {
        // get the id of activity created
        info("-- Editing an activity--");
        homePagePlatform.refreshUntil($(byText(text)), Condition.visible, 500);
        String idActivity = $(byXpath("//*[@class='description' and text()='${activity}']/preceding::*[@class='heading']//*[@class='btn-group uiDropdownWithIcon actLink ']".replace("${activity}",text))).waitUntil(Condition.visible, openBrowserTimeoutMs).getAttribute("id").split("dropDownEditActivity")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
        $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
        $(byText(text)).waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
    }

    public void deleteactivityDW(String text) {
        // get the id of activity created
        info("-- Editing an activity--");
        homePagePlatform.refreshUntil($(byText(text)), Condition.visible, 500);
        String idActivity = $(byXpath("//*[@class='description']//*[text()='${activity}']/preceding::*[@class='heading']//*[@class='btn-group uiDropdownWithIcon actLink ']".replace("${activity}",text))).waitUntil(Condition.visible, openBrowserTimeoutMs).getAttribute("id").split("dropDownEditActivity")[1];
        $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
        $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}", idActivity))).waitUntil(Condition.visible, openBrowserTimeoutMs).click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, openBrowserTimeoutMs).click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
        $(byText(text)).waitUntil(Condition.not(Condition.visible), openBrowserTimeoutMs);
    }

    public void commentActivity(String text) {
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
