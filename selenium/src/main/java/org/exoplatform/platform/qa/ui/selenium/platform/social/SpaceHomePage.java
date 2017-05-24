package org.exoplatform.platform.qa.ui.selenium.platform.social;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class SpaceHomePage {
  private final TestBase        testBase;

  public SpaceSettingManagement setSpaceMg;

  public ManageLogInOut         magAc;

  private ElementEventTestBase  evt;

  /**
   * constructor
   * 
   * @param dr
   */
  public SpaceHomePage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.magAc = new ManageLogInOut(testBase);
  }

  /**
   * Open Space setting page
   */
  public void goToSpaceSettingTab() {
    info("--Open Setting tab of the space");
    info("Click on the tab");
    Utils.pause(2000);
    if (evt.waitForAndGetElement(ELEMENT_SPACE_SPACE_SETTINGS, 5000, 0) == null) {
      evt.click(ELEMENT_SPACE_MENU_MORE, 2);
      evt.clickByJavascript(ELEMENT_SPACE_SPACE_SETTINGS);
    } else
      evt.clickByJavascript(ELEMENT_SPACE_SPACE_SETTINGS);
    Utils.pause(3000);
    evt.waitForAndGetElement(ELEMENT_SPACE_SPACE_SETTINGS_TITLE, 3000, 1);
    info("Space setting page is shown");
  }

  /**
   * Open Wiki portlet of space
   */
  public void goToWikiTab() {
    info("--Open Wiki tab of the space");
    info("Click on the tab");
    evt.waitForAndGetElement(ELEMENT_SPACE_WIKI_TAB, 3000, 0).click();
    info("wiki page is shown");
  }

  /**
   * Open a space from left menu
   * 
   * @param name
   */
  public void goToSpace(String name) {
    info("Go to the Space:" + name);
    evt.waitForAndGetElement(ELEMENT_SPACE_LEFT_MENU_SPACE_NAME.replace("${name}", name), 2000, 0).click();
    evt.waitForAndGetElement(ELEMENT_SPACE_NAME.replace("${name}", name), 2000, 0);
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
