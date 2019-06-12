package org.exoplatform.platform.qa.ui.selenium.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class SpaceHomePage {
    private final TestBase testBase;

    public SpaceSettingManagement setSpaceMg;

    public ManageLogInOut magAc;

    public HomePagePlatform homePagePlatform;

    private ElementEventTestBase evt;

    /**
     * constructor
     *
     * @param testBase
     */
    public SpaceHomePage(TestBase testBase) {
        this.testBase = testBase;
        this.evt = testBase.getElementEventTestBase();
        this.magAc = new ManageLogInOut(testBase);
        this.homePagePlatform = new HomePagePlatform(testBase);
    }

    /**
     * Open Space setting page
     */
    public void goToSpaceSettingTab() {
        info("--Open Setting tab of the space");
        info("Click on the tab");
        homePagePlatform.refreshUntil($(ELEMENT_SPACE_WIKI_TAB), Condition.visible, 1000);
        if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.visible)) {
            $(ELEMENT_SPACE_MENU_MORE).click();
            $(ELEMENT_SPACE_SPACE_SETTINGS).click();
        } else {
            $(ELEMENT_SPACE_SPACE_SETTINGS).click();
            $(ELEMENT_SPACE_SPACE_SETTINGS_TITLE).waitUntil(Condition.appears, Configuration.timeout);
            info("Space setting page is shown");
        }
    }

    /**
     * Open Wiki portlet of space
     */
    public void goToWikiTab() {
        info("--Open Wiki tab of the space");
        info("Click on the tab");
        homePagePlatform.refreshUntil($(ELEMENT_SPACE_WIKI_TAB), Condition.visible, 1000);
        $(ELEMENT_SPACE_WIKI_TAB).waitUntil(Condition.appears, Configuration.timeout).click();
        info("wiki page is shown");
    }

    public void goToForumsTab() {
        info("--Open Wiki tab of the space");
        info("Click on the tab");
        homePagePlatform.refreshUntil($(ELEMENT_SPACE_FORUMS_TAB), Condition.visible, 1000);
        $(ELEMENT_SPACE_FORUMS_TAB).waitUntil(Condition.appears, Configuration.timeout).click();
        info("wiki page is shown");
    }

    /**
     * Open a space from left menu
     *
     * @param name
     */
    public void goToSpace(String name) {
        info("Go to the Space:" + name);
        $(byXpath(ELEMENT_SPACE_LEFT_MENU_SPACE_NAME.replace("${name}", name))).waitUntil(Condition.appears, Configuration.timeout);
        $(byXpath(ELEMENT_SPACE_LEFT_MENU_SPACE_NAME.replace("${name}", name))).click();
        ELEMENT_SPACE_NAME_CONTAINER.find(byText(name)).waitUntil(Condition.visible, Configuration.timeout);
        info("The space is shown");
    }

    /**
     * Verify that the space is shown
     *
     * @param name is the name of space
     */
    public void verifyTitleSpace(String name) {
        info("Verify that the space is shown");
        evt.waitForAndGetElement(ELEMENT_SPACE_NAME.replace("${name}", name));

    }

    /**
     * Open space in All spaces
     *
     * @param name
     */
    public void openSpace(String name, boolean hasPerm) {
        info("open space in All spaces");
        evt.waitForAndGetElement(ELEMENT_SPACE_TITLE.replace("${space}", name)).click();
        if (hasPerm)
            evt.waitForAndGetElement(ELEMENT_SPACE_NAME.replace("${name}", name));
        else
            evt.waitForElementNotPresent(ELEMENT_SPACE_NAME.replace("${name}", name));
    }

    /**
     * Open more mentu
     */
    public void goToMore() {
        info("Click more link on the navigation");
        if (evt.waitForAndGetElement(ELEMENT_SPACE_MENU_MORE, 2000, 0) != null) {
            evt.click(ELEMENT_SPACE_MENU_MORE);
            evt.waitForAndGetElement(ELEMENT_MEMBER_TAB, 3000, 1);
            info("List menu is shown");
        }
    }

    /**
     * Open Dashboard portlet
     */
    public void goToDashBoard() {
        if (evt.waitForAndGetElement(ELEMENT_SPACE_MENU_DASHBOARD, 2000, 0) == null) {
            goToMore();
        }
        info("Click on Dash board link");
        evt.click(ELEMENT_SPACE_MENU_DASHBOARD);
        evt.waitForAndGetElement(ELEMENT_MYDASH_BTN_ADDGADGET, 3000, 1);
        info("Dashboard is shown");
    }

    public void checkNewsTab() {
        ELEMENT_News.isDisplayed();
        ELEMENT_News.waitUntil(Condition.visible, Configuration.timeout).click();
    }

    public void checkDetailsNewsTab (){
        info("Verify the details of News tab");
        ELEMENT_News .click();
        $(ELEMENT_Title).should(Condition.exist);
        $(ELEMENT_Content).should(Condition.exist);
        $(ELEMENT_field_Title).should(Condition.exist);
        switchTo().frame($(byClassName("cke_wysiwyg_frame")).should(Condition.exist)) ;
        $(ELEMENT_Button_Post).isDisplayed();
        $(ELEMENT_Icon_NewsPlus).isDisplayed();
        $(ELEMENT_Checkbox_pinArticle).isDisplayed();
    }
}