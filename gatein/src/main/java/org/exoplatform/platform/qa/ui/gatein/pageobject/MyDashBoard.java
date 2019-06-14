package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

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
   */
  public void addGadget() {
    info("Click on GadGet button");
    evt.click(ELEMENT_MYDASH_BTN_ADDGADGET);
    evt.waitForAndGetElement(ELEMENT_DASHBOARD_WORKSPACE_POPUP_TITLE, 2000, 0);
    info("The popup is shown");
    $(byXpath("//*[@class='GadgetTitle' and @title='Services Management']")).dragAndDropTo($(byXpath("//div[@id=\"GadgetContainer\"]"))).waitUntil(Condition.visible,Configuration.timeout);
    evt.click(ELEMENT_DASHBOARD_WORKSPACE_POPUP_CLOSE);
    info("The gadget is added to dashboard");
  }

  /**
   * Delete a Gadget in Dashboard page
   *
   * @param name String
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
   * @param url String
   * @param name String
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
   * @param name String
   */
  public void editLastTab(String name) {
    info("Click on add button");
    testBase.getExoWebDriver().getWebDriver().findElement(By.className("last")).click();
    $(byXpath("//li[@class='active last']")).doubleClick();
    $(byXpath(ELEMENT_MYDASH_LAST_TAB_EDIT)).clear();
    $(byXpath(ELEMENT_MYDASH_LAST_TAB_EDIT)).sendKeys(name);
    $(byXpath(ELEMENT_MYDASH_LAST_TAB_EDIT)).sendKeys(Keys.ENTER);
      info("Verify that the new tab is added");
      Assert.assertEquals($(byXpath("//li[@class=\"active last\"]/a/span")).getText(),name);
  }

  /**
   * Rename a tab
   *
   * @param oldName String
   * @param newName String
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
   * @param name String
   */
  public void deleteTab(String name) {
    info("Click on delete button");
    evt.click(ELEMENT_MYDASH_BTN_DELETETAB.replace("${name}", name));
    magAlert.acceptAlert();
    info("Verify that the tab is deleted");
    evt.waitForElementNotPresent(ELEMENT_MYDASH_BTN_DELETETAB.replace("${name}", name), 2000, 0);
  }
}
