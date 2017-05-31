package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.WebElement;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class GadgetManagement {

  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public GadgetManagement(TestBase testBase) {
    this.testBase = testBase;
    this.alert = new ManageAlert(testBase);
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Add a Remote gadget
   *
   * @param url
   */
  public void addRemoteGadget(String url) {
    info("Click on Gadgets button");

    evt.click(ELEMENT_REMOTE_GADGETBTN);
    evt.waitForAndGetElement(ELEMENT_REMOTE_GADGET_URL);
    info("input a url");
    evt.type(ELEMENT_REMOTE_GADGET_URL, url, true);
    info("Click on Add button");
    evt.click(ELEMENT_REMOTE_GADGET_ADDBTN);

  }

  /**
   * Add a new gadget into a category
   *
   * @param category
   */
  public void addIntoCategory(String category) {
    info("Click on the link: Click here to add into category");
    evt.click(ELEMENT_REMOTE_GADGET_ADD_INTO_CATEGORY_LINK);
    info("Verify that the links is hided after clicked");
    evt.waitForElementNotPresent(ELEMENT_REMOTE_GADGET_ADD_INTO_CATEGORY_LINK);
    info("Waiting categories table is shown");
    evt.waitForAndGetElement(ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES, 2000, 0);
    evt.check(ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_CHECKBOX.replace("${category}", category), 2);
    info("Save all changes");
    evt.click(ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_SAVEBTN);

  }

  /**
   * Delete a gadget
   *
   * @param name
   */
  public void deleteGadget(String name) {
    info("Click on delete button");
    evt.click(ELEMENT_REMOTE_GADGET_LEFT_DELETE_BTN.replace("${name}", name));
    alert.acceptAlert();
    evt.waitForElementNotPresent(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}", name));
  }

  /**
   * Create a new gadget
   *
   * @param name
   * @param xmlCode
   */
  public void createNewGadget(String name, String xmlCode) {
    info("Click on Create new gadget button");
    evt.click(ELEMENT_CREATE_NEW_GADGET_BTN);
    info("Input a new name");
    evt.type(ELEMENT_CREATE_NEW_GADGET_INPUT_NAME_FIELD, name, true);
    if (!xmlCode.isEmpty()) {
      info("Input a new source code");
      WebElement element = testBase.getExoWebDriver().getWebDriver().findElement(ELEMENT_CREATE_NEW_GADGET_SOURCE_FIELD);
      element.clear();
      element.click();
      System.out.println(xmlCode);
      element.sendKeys(xmlCode);
      // type(ELEMENT_CREATE_NEW_GADGET_SOURCE_FIELD,xmlCode,true);
    }
    info("Click on save button");
    evt.click(ELEMENT_CREATE_NEW_GADGET_SAVE_BTN);
    info("Verify that the gadget is created successfully");
    evt.waitForAndGetElement(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}", name), 2000, 0);
  }

  /**
   * Edit a Gadget with newXmlCode that include newName
   *
   * @param oldName
   * @param newXmlCode
   * @param newName
   */
  public void editAGadget(String oldName, String newXmlCode, String newName) {
    info("Select a Gadget");
    evt.click(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}", oldName));
    info("Click on Edit button");
    evt.click(ELEMENT_GADGET_EDIT_BTN);
    info("Change xml code");
    evt.type(ELEMENT_CREATE_NEW_GADGET_SOURCE_FIELD, newXmlCode, true);
    info("Click on Save button");
    evt.click(ELEMENT_CREATE_NEW_GADGET_SAVE_BTN);
    info("Verify that the gadget is edit successfully");
    evt.waitForAndGetElement(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}", newName), 2000, 0);
  }
}
