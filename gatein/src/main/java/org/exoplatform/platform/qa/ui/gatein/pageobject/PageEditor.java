package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class PageEditor {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public PageEditor(TestBase testBase) {
    this.evt = testBase.getElementEventTestBase();
    this.testBase = testBase;
  }

  /**
   * Edit a Portlet with locator of the portlet
   *
   * @param locatorPortlet
   */
  public void goToEditPortlet(Object locatorPortlet) {
    info("Go to edit portlet");
    Utils.pause(3000);
    evt.mouseOver(locatorPortlet, true);
    evt.click(ELEMENT_EDIT_PORTLET_ICON);
    evt.waitForAndGetElement(ELEMENT_EDIT_PORTLET_FORM);
  }

  /**
   * Edit a Portlet with the name of portlet
   *
   * @param namePortlet
   */
  public void goToEditPortlet(String namePortlet) {
    info("Go to edit portlet");
    evt.mouseOver(ELEMENT_EDITOR_PAGE_APPLICATION_PORTLET.replace("${name}", namePortlet), true);
    evt.click(ELEMENT_EDIT_PORTLET_ICON);
    evt.waitForAndGetElement(ELEMENT_EDIT_PORTLET_FORM);
  }

  /**
   * Finish Editing PageLayout
   */
  public void finishEditLayout() {
    info("Finish Editing PageLayout");
    evt.click(ELEMENT_EDIT_PORTLET_FINISH);
    evt.waitForElementNotPresent(ELEMENT_EDIT_PORTLET_FINISH, 60000);
  }

  /**
   * Select an application and move to tagerget container
   *
   * @param portletPath (Format: catgory-porlets). Ex: answer-AnswersPortlet)
   * @param targetLocator
   */
  public void selectApplication(String portletPath, Object targetLocator) {
    info("Select an application and move to tagerget container");
    String[] portlets = portletPath.split("-");
    if (portlets.length > 1) {
      for (int i = 0; i < portlets.length - 1; i++) {
        if (evt.waitForAndGetElement(ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_RIGHT.replace("$category", portlets[i]),
                                     5000,
                                     0) != null)
          evt.click(ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_RIGHT.replace("$category", portlets[i]));
        evt.waitForAndGetElement(ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_DOWN.replace("$category", portlets[i]));
      }
    }
    evt.dragAndDropToObject(ELEMENT_EDIT_PORTLET_APPLICATION_ID.replace("$portlet", portlets[portlets.length - 1]),
                            targetLocator);
  }

  /**
   * Verify application permission
   *
   * @param cat
   * @param app
   * @param isEnable
   */
  public void verifyAppPermission(String cat, String app, boolean isEnable) {
    info("verify application permission");
    evt.scrollBarToGetElement(By.xpath(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", cat)));
    evt.click(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", cat), 0, true);
    if (isEnable) {
      evt.waitForAndGetElement(ELEMENT_APPLICATION_NAME.replace("$app", app));
    } else {
      evt.waitForElementNotPresent(ELEMENT_APPLICATION_NAME.replace("$app", app));
    }

  }

  /**
   * Verify category permission
   *
   * @param cat
   * @param isEnable
   */
  public void verifyCatPermission(String cat, boolean isEnable) {
    info("verify category permission");
    if (isEnable) {
      evt.scrollBarToGetElement(By.xpath(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", cat)));
    } else {
      evt.waitForElementNotPresent(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", cat));
    }
  }

  /**
   * Verify permission edit delete application
   *
   * @param app
   * @param isEnable
   */
  public void verifyEditDeleteAppPerm(String app, boolean isEnable) {
    evt.mouseOver(ELEMENT_EDITOR_PAGE_APPLICATION_PORTLET.replace("${name}", app), true);
    if (isEnable) {
      evt.waitForAndGetElement(ELEMENT_EDIT_PORTLET.replace("${portlet}", app));
      evt.waitForAndGetElement(ELEMENT_DELETE_PORTLET.replace("${portlet}", app));
    } else {
      evt.waitForElementNotPresent(ELEMENT_EDIT_PORTLET.replace("${portlet}", app));
      evt.waitForElementNotPresent(ELEMENT_DELETE_PORTLET.replace("${portlet}", app));
    }
  }
}
