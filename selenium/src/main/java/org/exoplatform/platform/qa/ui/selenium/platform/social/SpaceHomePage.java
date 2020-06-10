package org.exoplatform.platform.qa.ui.selenium.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
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

  public void goToSpaceSettingTabDW(String space) {
    info("--Open Setting tab of the space");
    info("Click on the tab");
    $(byXpath("//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/wiki']".replace("{space}",space)))
            .waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    $(byXpath("//*[@class='v-slide-group__next']//i")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byXpath("//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/settings']".replace("{space}", space)))
            .waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs)
            .click();
    info("Space setting page is shown");

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
}