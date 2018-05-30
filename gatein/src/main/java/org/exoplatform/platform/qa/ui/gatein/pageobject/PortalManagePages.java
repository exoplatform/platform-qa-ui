package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.Dialog;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

/**
 * Path: Administration-Portal-Pages
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
   *@param title String
   * @param siteName String
   * @param type String
   * @param verify  boolean
   */

  public void searchPage(String title, String siteName, String type, boolean... verify) {

    info("waiting the page is loaded full");
    $(ELEMENT_MANAGEPAGES_TITLE_FIELD).waitUntil(Condition.visible, Configuration.timeout);
    if (!title.isEmpty()) {
      info("Input a new title");
      $(ELEMENT_MANAGEPAGES_TITLE_FIELD).scrollTo().setValue(title).waitUntil(Condition.visible,Configuration.timeout);
    }

    if (!siteName.isEmpty()) {
      info("Input a new site Name");
      $(ELEMENT_MANAGEPAGES_SITES_NAME_FIELD).setValue(siteName).waitUntil(Condition.visible,Configuration.timeout);
    }

    if (!type.isEmpty()) {
      info("Select a type");
      $(ELEMENT_MANAGEPAGES_TYPE_DROPBOX).selectOption(type);
    }
    try {

    info("Click on Search button");
    executeJavaScript("window.scrollBy(0,-350);", "");
      $(byId("pageId")).click();
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    $(ELEMENT_MANAGEPAGES_SEARCH_BUTTON).click();

      info("Verify that the search page is shown with correct results");
      $(byText(title)).should(Condition.exist);


      $(byText(title)).should(Condition.exist);
      if (!title.isEmpty())

               $(byXpath(ELEMENT_MANAGEPAGES_CONTENT_TITLE_COLUMN.replace("${title}", title))).waitUntil(Condition.visible,Configuration.timeout);
      if (!siteName.isEmpty() & !type.isEmpty())

                $(byXpath(ELEMENT_MANAGEPAGES_CONTENT_SEARCH_TABLE.replace("${type}", type).replace("${siteName}", siteName).replace("${title}", title))).waitUntil(Condition.visible,Configuration.timeout);


    }








  /*

   * Delete a page
   *
   * @param titlePage String
   * @param type String
   */


  public void deletePage(String titlePage, String type) {
    info("Delete a page");
    searchPage(titlePage, "", type);

    $(ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_DELETE).click();
    alert.acceptAlert();
    if ($(byText("No result found.")).waitUntil(Condition.appears, Configuration.timeout).is(Condition.exist)) {
      $(byText("OK")).click();
    }

  }

  /**
   * Go to edit a page
   *
   * @param titlePage String
   * @param type String
   */
  public void editPage(String titlePage, String type) {

    info("Go to edit a page");
    searchPage(titlePage,"",type);
    info("Click on Edit button");
    $(ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_EDIT).click();
  }

  /**
   * Add a new simple page
   *
   * @param pageName String
   * @param title String
   * @param type String
   * @param isMaxWindow boolean
   */
  public void addPage(String pageName, String title, String type, boolean... isMaxWindow) {
    info("Click on Add new Page button");

    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN).scrollTo(). waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN).scrollTo().click();
    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_PAGE_NAME).setValue(pageName);
    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TITLE).setValue(title);
    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TYPE_DROPBOX).selectOption("group");
    $(ELEMENT_MANAGEPAGES_ADD_NEW_PAGES_POPUP_SAVE_BTN).click();
  }

  /**
   * Open page
   *
   * @param url boolean
   */
  public void openPage(String url) {
    info("open page:" + url);
    testBase.getExoWebDriver().getWebDriver().get(url);

  }

}
