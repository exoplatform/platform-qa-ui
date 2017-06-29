package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.Keys;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class MyDashBoard {

  private final TestBase       testBase;

  public ManageAlert           magAlert;

  private ElementEventTestBase evt;

  public MyDashBoard(TestBase testBase) {
    this.testBase = testBase;
    this.magAlert = new ManageAlert(testBase);
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Add a gadget to dashboard
   *
   * @param name the name of gadget that will be added to dashboard
   * @param numberCol the number of column that will put the gadget. We have 3
   *          columns as: + numberCol= 1: this is for left column + numberCol=
   *          2: this is for middle column + numberCol =3: this is for right
   *          column
   */
  public void addGadget(String name, String numberCol) {
    info("Click on GadGet button");
    evt.click(ELEMENT_MYDASH_BTN_ADDGADGET);
    evt.waitForAndGetElement(ELEMENT_DASHBOARD_WORKSPACE_POPUP_TITLE, 2000, 0);
    info("The popup is shown");
    evt.dragAndDropToObject(ELEMENT_MYDASH_GADGET_NAME.replace("${name}", name),
                            ELEMENT_MYDASH_GADGET_COLUMN.replace("${number}", numberCol));

    evt.click(ELEMENT_DASHBOARD_WORKSPACE_POPUP_CLOSE);
    evt.waitForAndGetElement(ELEMENT_MYDASH_ADDED_GADGET_IN_DASHBOARD.replace("${name}", name), 2000, 0);
    info("The gadget is added to dashboard");
  }

  /**
   * Delete a Gadget in Dashboard page
   *
   * @param name
   */
  public void deleteGadget(String name) {
    info("Click on Delete button");
    evt.clickByJavascript(ELEMENT_MYDASH_DELETE_GADGET.replace("${name}", name));
    magAlert.acceptAlert();
    evt.waitForElementNotPresent(ELEMENT_MYDASH_DELETE_GADGET.replace("${name}", name));
    info("The gadget is deleted successfully");

  }

  /**
   * Add a remote gadget to the page
   *
   * @param url
   * @param name
   */
  public void addRemoteGadget(String url, String name) {
    info("Open add gadget popup");
    evt.clickByJavascript(ELEMENT_MYDASH_BTN_ADDGADGET);
    info("Input a url link");
    if (testBase.getBrowser().contains("iexplorer"))
      evt.typeByJavascript("url", url);
    else
      evt.type(ELEMENT_MYDASH_GADGET_BYURL, url, true);
    info("Click on add button");
    evt.click(ELEMENT_MYDASH_GADGET_ADDBTN);
    info("Close the popup");
    evt.click(ELEMENT_DASHBOARD_WORKSPACE_POPUP_CLOSE);
    info("Verify that the remote gadget is shown on the page");
    evt.waitForAndGetElement(ELEMENT_MYDASH_ADDED_GADGET_IN_DASHBOARD.replace("${name}", name), 2000, 1);

  }

  /**
   * Add new tab
   *
   * @param name
   */
  public void addTab(String name) {
    info("Click on add button");
    evt.click(ELEMENT_MYDASH_BTN_ADDTAB);
    evt.waitForAndGetElement(ELEMENT_MYDASH_BTN_NAMETAB.replace("${name}", "Tab_2")).clear();
    evt.type(ELEMENT_MYDASH_BTN_NAMETAB.replace("${name}", "Tab_2"), name, false);
    evt.type(ELEMENT_MYDASH_BTN_NAMETAB.replace("${name}", "Tab_2"), "\n", false);
    info("Verify that the new tab is added");
    evt.waitForAndGetElement(ELEMENT_MYDASH_TAB_NAME.replace("${name}", name), 2000, 0);

  }

  /**
   * Rename a tab
   *
   * @param oldName
   * @param newName
   */
  public void renameTab(String oldName, String newName) {
    info("Click on the tab");
    evt.click(ELEMENT_MYDASH_TAB_NAME.replace("${name}", oldName));
    info("Input new name");
    evt.waitForAndGetElement(ELEMENT_MYDASH_TAB_NAME.replace("${name}", oldName), 2000, 0).clear();
    evt.waitForAndGetElement(ELEMENT_MYDASH_TAB_NAME.replace("${name}", oldName), 2000, 0).sendKeys(newName);
    (evt.waitForAndGetElement(ELEMENT_MYDASH_TAB_NAME.replace("${name}", oldName), 2000, 0)).sendKeys(Keys.ENTER);
    info("Verify that the new name is added");
    evt.waitForAndGetElement(ELEMENT_MYDASH_TAB_NAME.replace("${name}", newName), 2000, 0);
  }

  /**
   * Delete a tab
   *
   * @param name
   */
  public void deleteTab(String name) {
    info("Click on delete button");
    evt.click(ELEMENT_MYDASH_BTN_DELETETAB.replace("${name}", name));
    magAlert.acceptAlert();
    info("Verify that the tab is deleted");
    evt.waitForElementNotPresent(ELEMENT_MYDASH_BTN_DELETETAB.replace("${name}", name), 2000, 0);
  }
}
