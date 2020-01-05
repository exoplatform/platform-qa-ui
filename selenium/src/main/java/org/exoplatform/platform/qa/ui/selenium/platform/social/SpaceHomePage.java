package org.exoplatform.platform.qa.ui.selenium.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;
import static org.exoplatform.platform.qa.ui.selenium.locator.news.NewsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_INPUT_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.By;

public class SpaceHomePage {
    private final TestBase        testBase;

    public SpaceSettingManagement setSpaceMg;

    public ManageLogInOut         magAc;

    public HomePagePlatform homePagePlatform;

    private ElementEventTestBase  evt;

    /**
     * constructor
     *
     * @param testBase
     */
    public SpaceHomePage(TestBase testBase) {
        this.testBase = testBase;
        this.evt = testBase.getElementEventTestBase();
        this.magAc = new ManageLogInOut(testBase);
        this.homePagePlatform=new HomePagePlatform(testBase);
    }

    /**
     * Open Space setting page
     */
    public void goToSpaceSettingTab() {
        info("--Open Setting tab of the space");
        info("Click on the tab");
        homePagePlatform.refreshUntil($(ELEMENT_SPACE_WIKI_TAB),Condition.visible,1000);
        if  ($(ELEMENT_SPACE_MENU_MORE).is(Condition.visible)) {
            $(ELEMENT_SPACE_MENU_MORE).click();
            $(ELEMENT_SPACE_SPACE_SETTINGS).click();
        } else {
            $(ELEMENT_SPACE_SPACE_SETTINGS).click();
            $(ELEMENT_SPACE_SPACE_SETTINGS_TITLE).waitUntil(Condition.appears, Configuration.timeout);
            info("Space setting page is shown");
        }
    }

  public void goToNewsTab() {
    info("--Open News tab of the space");
    $(ELEMENT_SPACE_NEWS_TAB).shouldBe(Condition.visible).click();
    $(ELEMENT_SPACE_NEWS_CREATION_FORM).waitUntil(Condition.appears, Configuration.timeout);
    info("News creation form is shown");
  }
  
  public void addNews(String title, String description) {
    info("--Set news title");
    $(ELEMENT_SPACE_NEWS_CREATION_FORM_INPUT_TITLE).setValue(title);
    info("--Set news description");
    fillContentField(description);
    info("--Wait until post button is enabled");
    $(ELEMENT_SPACE_NEWS_CREATION_FORM_BUTTON_POST).waitUntil(Condition.enabled, Configuration.timeout);
    info("--Post news");
    sleep(2000); // It will be deleted when the draft problem is fixed
    $(ELEMENT_SPACE_NEWS_CREATION_FORM_BUTTON_POST).shouldBe(Condition.enabled).click();
      info("--Post news save");
  }

    public void fillContentField(String description) {
        info("----Add text into into news content text box-----");
        $(ELEMENT_ACCOUNT_NAME_LINK).click();
        switchTo().frame(ELEMENT_SPACE_NEWS_ACTIVITY_INPUT_CONTENT);
        ELEMENT_INPUT_ACTIVITY.click();
        ELEMENT_INPUT_ACTIVITY.sendKeys(description);
        switchTo().defaultContent();
    }

  public void goToNewsDetails(String newsTitle) {
    info("--Go to news details page");
    $(By.xpath(ELEMENT_SPACE_NEWS_ACTIVITY_INPUT_TITLE.replace("${newsName}", newsTitle))).shouldBe(Condition.exist).click();
    ELEMENT_EDIT_BUTTON_EDIT.waitUntil(Condition.appear, Configuration.timeout);
    info("--News details page is shown");
  }

  public void goToNewsEditForm(String newsTitle) {
    info("--Go to news edit form");
    ELEMENT_EDIT_BUTTON_EDIT.shouldBe(Condition.exist).click();
    info("--News edit form is shown");
  }

    /**
     * Open Wiki portlet of space
     */
    public void goToWikiTab() {
        info("--Open Wiki tab of the space");
        info("Click on the tab");
        homePagePlatform.refreshUntil($(ELEMENT_SPACE_WIKI_TAB),Condition.visible,1000);
        $(ELEMENT_SPACE_WIKI_TAB).waitUntil(Condition.appears, Configuration.timeout).click();
        info("wiki page is shown");
    }

    public void goToForumsTab() {
        info("--Open Wiki tab of the space");
        info("Click on the tab");
        homePagePlatform.refreshUntil($(ELEMENT_SPACE_FORUMS_TAB),Condition.visible,1000);
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
        ELEMENT_SPACE_NAME_CONTAINER.find(byText(name)).waitUntil(Condition.visible,Configuration.timeout);
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
}