package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

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

    if (!siteName.isEmpty()) {
      info("Input a new site Name");
      evt.type(ELEMENT_MANAGEPAGES_SITES_NAME_FIELD, siteName, true);
    }
    if (!type.isEmpty()) {
      info("Select a type");
      $(ELEMENT_MANAGEPAGES_TYPE_DROPBOX).selectOption(type);
    }
   $(ELEMENT_MANAGEPAGES_TITLE_FIELD).waitUntil(Condition.appears,Configuration.timeout);
    info("Input a new title");
    $(ELEMENT_MANAGEPAGES_TITLE_FIELD).scrollTo().setValue(title);
    info("Select a type");
    $(ELEMENT_MANAGEPAGES_TYPE_DROPBOX).selectOption("group");
    info("Click on Search button");
    $(ELEMENT_MANAGEPAGES_SEARCH_BUTTON).click();
    info("Verify that the search page is shown with correct results");
    $(byText(title)).should(Condition.exist);
  }

  /**
   * Delete a page
   *
   * @param titlePage
   */
  public void deletePage(String titlePage, String type) {
    info("Delete a page");
    searchPage(titlePage, "", type);
    $(ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_DELETE).click();
    alert.acceptAlert();
    if ($(byText("No result found.")).waitUntil(Condition.appears,Configuration.timeout).is(Condition.exist)){
      $(byText("OK")).click();
    }

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
    $(ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_EDIT).click();
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

    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN).scrollTo().click();
    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_PAGE_NAME).setValue(pageName);
    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TITLE).setValue(title);
    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TYPE_DROPBOX).selectOption("group");
    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGES_POPUP_SAVE_BTN).click();
  }

  /**
   * Open page
   *
   * @param url
   */
  public void openPage(String url) {
    info("open page:" + url);
    testBase.getExoWebDriver().getWebDriver().get(url);
    Utils.pause(1000);
  }

}
