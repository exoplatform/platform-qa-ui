package org.exoplatform.platform.qa.ui.selenium.platform.gatein;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.*;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

/**
 * Path: Administration-->Portal-->Pages
 */
public class PortalManagePages {

  private final TestBase       testBase;

  public Dialog                dialog;

  public ManageAlert           alert;

  public Button                button;

  private ElementEventTestBase evt;

  public PortalManagePages(TestBase testBase) {
    this.testBase = testBase;
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.dialog = new Dialog(testBase);
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Search a page
   *
   * @param siteName
   * @param type
   */
  public void searchPage(String title, String siteName, String type, boolean... verify) {
    info("waiting the page is loaded full");
    evt.waitForAndGetElement(ELEMENT_MANAGEPAGES_TITLE_FIELD);
    if (!title.isEmpty()) {
      info("Input a new title");
      evt.type(ELEMENT_MANAGEPAGES_TITLE_FIELD, title, true);
    }
    if (!siteName.isEmpty()) {
      info("Input a new site Name");
      evt.type(ELEMENT_MANAGEPAGES_SITES_NAME_FIELD, siteName, true);
    }
    if (!type.isEmpty()) {
      info("Select a type");
      evt.select(ELEMENT_MANAGEPAGES_TYPE_DROPBOX, type, 2);
    }
    info("Click on Search button");
    evt.click(ELEMENT_MANAGEPAGES_SEARCH_BUTTON);
    if (verify.length > 0) {
      info("Verify that the search page is shown with correct results");
      if (!title.isEmpty())
        evt.waitForAndGetElement(ELEMENT_MANAGEPAGES_CONTENT_TITLE_COLUMN.replace("${title}", title), 3000, 0);
      if (!siteName.isEmpty() & !type.isEmpty())
        evt.waitForAndGetElement(ELEMENT_MANAGEPAGES_CONTENT_SEARCH_TABLE.replace("${type}", type)
                                                                         .replace("${siteName}", siteName)
                                                                         .replace("${title}", title),
                                 2000,
                                 0);
    }
  }

  /**
   * Delete a page
   * 
   * @param titlePage
   */
  public void deletePage(String titlePage, String type) {
    info("Delete a page");
    searchPage(titlePage, "", type);
    evt.click(ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_DELETE);
    alert.acceptAlert();
    /*
     * waitForAndGetElement(ELEMENT_MANGEPAGES_INFORM_POPUP_OK, 4000, 0);
     * click(ELEMENT_MANGEPAGES_INFORM_POPUP_OK, 2);
     */
    evt.waitForAndGetElement(ELEMENT_MANGEPAGES_INFORM_POPUP_CLOSE, 4000, 0);
    evt.click(ELEMENT_MANGEPAGES_INFORM_POPUP_CLOSE, 2);
    evt.waitForElementNotPresent(ELEMENT_MANAGEPAGES_CONTENT_TITLE_COLUMN.replace("${tilte}", titlePage));
    Utils.pause(2000);
  }

  /**
   * Go to edit a page
   * 
   * @param titlePage
   */
  public void editPage(String titlePage, String type) {
    info("Go to edit a page");
    searchPage(titlePage, "", type);
    info("Click on Edit button");
    evt.click(ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_EDIT);
    Utils.pause(2000);
  }

  /**
   * Add a new simple page
   * 
   * @param pageName
   * @param title
   * @param type
   * @param isMaxWindow
   */
  public void addPage(String pageName, String title, String type, boolean... isMaxWindow) {
    info("Click on Add new Page button");
    Utils.pause(3000);
    evt.waitForAndGetElement(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN, testBase.getDefaultTimeout(), 1);
    evt.click(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN);
    testBase.getSeleniumDriver().navigate().refresh();
    if (!pageName.isEmpty()) {
      info("Input page name");
      evt.type(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_PAGE_NAME, pageName, true);
    }
    if (!title.isEmpty()) {
      info("Input title");
      evt.type(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TITLE, title, true);
    }
    if (!type.isEmpty()) {
      info("Select a type");
      evt.select(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TYPE_DROPBOX, type);
    }
    if (isMaxWindow.length > 0) {
      info("Tick on show Max Window checkbox");
      evt.check(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_CHECKBOX, 2);
    }
    info("Save all changes");
    evt.click(ELEMENT_MANAGEPAGES_ADD_NEW_PAGES_POPUP_SAVE_BTN);
    Utils.pause(2000);
  }

  /**
   * Open page
   * 
   * @param url
   */
  public void openPage(String url) {
    info("open page:" + url);
    testBase.getSeleniumDriver().get(url);
    Utils.pause(1000);
  }

}
