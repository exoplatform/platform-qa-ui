package org.exoplatform.platform.qa.ui.selenium.platform.wiki;

import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.Dialog;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiHomePage {
  private final TestBase       testBase;

  public Dialog                dialog;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param dr
   */
  public WikiHomePage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.dialog = new Dialog(testBase);
    this.alert = new ManageAlert(testBase);
  }

  /**
   * Go to "Add blank wiki page"
   */
  public void goToAddBlankPage() {
    info("--Go to add blank wiki page--");
    evt.waitForAndGetElement(ELEMENT_ADD_PAGE_LINK, testBase.getDefaultTimeout(), 1);
    evt.click(ELEMENT_ADD_PAGE_LINK);
    evt.waitForAndGetElement(ELEMENT_BLANK_PAGE_LINK, testBase.getDefaultTimeout(), 1);
    evt.click(ELEMENT_BLANK_PAGE_LINK);
    /*
     * mouseOverAndClick(ELEMENT_ADD_PAGE_LINK);
     * mouseOverAndClick(ELEMENT_BLANK_PAGE_LINK);
     */
    info("Blank wiki page is shown");
  }

  /**
   * Go to "Add template wiki page"
   */
  public void goToAddTemplateWikiPage() {
    info("--Go to add template wiki page--");
    Utils.pause(2000);
    evt.waitForAndGetElement(ELEMENT_ADD_PAGE_LINK, testBase.getDefaultTimeout(), 1);
    evt.click(ELEMENT_ADD_PAGE_LINK);
    evt.waitForAndGetElement(ELEMENT_FROM_TEMPLATE_LINK, testBase.getDefaultTimeout(), 1);
    evt.click(ELEMENT_FROM_TEMPLATE_LINK);
    /*
     * mouseOverAndClick(ELEMENT_ADD_PAGE_LINK);
     * mouseOverAndClick(ELEMENT_FROM_TEMPLATE_LINK);
     */
  }

  /**
   * Go to "Add blank wiki page"
   */
  public void goToEditPage() {
    info("--Go to edit page--");
    evt.waitForAndGetElement(ELEMENT_EDIT_PAGE_LINK, testBase.getDefaultTimeout(), 1);
    evt.click(ELEMENT_EDIT_PAGE_LINK);
    evt.waitForElementNotPresent(ELEMENT_WIKI_HOME_PAGE_TEXT);
  }

  /**
   * Go to Home Wiki Page
   */
  public void goToHomeWikiPage() {
    info("-- Go to wiki home page --");
    evt.click(ELEMENT_WIKI_HOME_PAGE_LINK);
    evt.waitForAndGetElement(ELEMENT_WIKI_HOME_PAGE_TEXT);
  }

  /**
   * Select any page
   * 
   * @param title
   */
  public void goToAPage(String title) {
    info("-- Go to wiki page --");
    evt.click(ELEMENT_WIKI_PAGE_LINK.replace("${pageTitle}", title.replace(" ", "_")));
    evt.waitForElementNotPresent(ELEMENT_WIKI_HOME_PAGE_TEXT);
  }

  /**
   * Select any page
   * 
   * @param title
   */
  public void deleteWiki(String title) {
    if (evt.waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}", title), 3000, 0) != null) {
      info("Go to delete wiki page...");
      info("Select the wiki page to delete");
      selectAPage(title);
      info("Click on More link");
      evt.click(ELEMENT_MORE_LINK);
      if (evt.waitForAndGetElement(ELEMENT_DELETE_LINK, 5000, 0) == null) {
        evt.mouseOverAndClick(ELEMENT_DELETE_LINK);
      } else {
        evt.click(ELEMENT_DELETE_LINK);
      }
      evt.waitForAndGetElement(ELEMENT_CONFIRM_WIKI_DELETE, 2000, 0).click();
      evt.waitForElementNotPresent(ELEMENT_TREE_WIKI_NAME.replace("${name}", title));
    }
  }

  /**
   * Select any page
   * 
   * @param title
   */
  public void cancelDeleteWiki(String title) {
    if (evt.waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}", title), 3000, 0) != null) {
      info("Go to delete wiki page...");
      info("Select the wiki page to delete");
      selectAPage(title);
      info("Click on More link");
      evt.click(ELEMENT_MORE_LINK);
      if (evt.waitForAndGetElement(ELEMENT_DELETE_LINK, 5000, 0) == null) {
        evt.mouseOverAndClick(ELEMENT_DELETE_LINK);
      } else {
        evt.click(ELEMENT_DELETE_LINK);
      }
      evt.waitForAndGetElement(ELEMENT_CANCEL_WIKI_DELETE, 2000, 0).click();
      evt.waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}", title));
    }
  }

  /**
   * Select a page
   * 
   * @param page
   */
  public void selectAPage(String page) {
    info("Go to a wiki page...");
    info("Select the wiki page");
    evt.waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}", page), 5000, 0).click();
    info("The page is shown");
    evt.waitForAndGetElement(ELEMENT_PAGE_TITLE.replace("${title}", page), 3000, 0);
  }

  /**
   * Go to "Go to My Draft"
   */
  public void goToMyDraft() {
    info("Click on Browser drop down");
    evt.click(ELEMENT_SEARCH_BROWSERS_DROPDOWN);
    info("Select wiki settings label");
    evt.click(ELEMENT_SEARCH_BROWSERS_MY_DRAFT);
    evt.waitForAndGetElement(ELEMENT_DRAFT_PAGE_TITLE);
    Utils.pause(2000);
  }

  /**
   * Open search page with a text
   * 
   * @param text
   */
  public void goTosearchPage(String text) {
    info("Input a text to search field");
    evt.type(ELEMENT_SEARCH_TEXTBOX_POPUP, text, true);
    Utils.pause(1000);
    evt.click(ELEMENT_SEARCH_BTN);
    Utils.pause(2000);
  }

  /**
   * Open Wiki Settings page
   */
  public void goToWikiSettingPage() {
    info("Click on Browser drop down");
    evt.click(ELEMENT_SEARCH_BROWSERS_DROPDOWN);
    info("Select wiki settings label");
    evt.click(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);
    Utils.pause(2000);
  }

  /**
   * Open permission tab
   */
  public void openPermTab() {
    info("Open Permission tab");
    evt.click(ELEMENT_WIKI_SETTING_PERM_TAB, 0, true);
  }

  /**
   * Open permissions for the wiki
   */
  public void goToPermissions() {
    info("Permissions page");
    evt.click(ELEMENT_MORE_LINK);
    evt.click(ELEMENT_PERMISSION_LINK);
    evt.waitForAndGetElement(ELEMENT_PAGE_PERMISSION_POPUP);
    info("The permission popup is shown");
  }

  /**
   * Confirm messages
   * 
   * @param isConfirm = true if want to click on Confirm button = false if want
   *          to click on Cancel button
   */
  public void confirmWaringMessage(Boolean isConfirm) {
    if (isConfirm) {
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_OK_BTN, 2000, 0) != null) {
        info("Click on OK button");
        evt.click(ELEMENT_CONFIRM_POPUP_OK_BTN);
      }
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_CONFIRM_BTN, 2000, 0) != null) {
        info("Click on Confirm button");
        evt.click(ELEMENT_CONFIRM_POPUP_CONFIRM_BTN);
      }
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_YES_BTN, 2000, 0) != null) {
        info("Click on Yes button");
        evt.click(ELEMENT_CONFIRM_POPUP_YES_BTN);
      }
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_YES_BTN, 2000, 0) != null) {
        info("Click on Yes button");
        evt.click(ELEMENT_CONFIRM_POPUP_YES_BTN);
      }
      if (evt.waitForAndGetElement(ELEMENT_WARNING_OK_BTN, 2000, 0) != null) {
        info("Click OK button");
        evt.click(ELEMENT_WARNING_OK_BTN);
      }
    } else {
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_CANCEL_BTN, 2000, 0) != null) {
        info("Click on Cancel button");
        evt.click(ELEMENT_CONFIRM_POPUP_CANCEL_BTN);
      }

      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_NO_BTN, 2000, 0) != null) {
        info("Click on No button");
        evt.click(ELEMENT_CONFIRM_POPUP_NO_BTN);
      }

    }
  }

  /**
   * Get a permalink of the page
   * 
   * @return perLink
   */
  public void goToPermalink() {
    info("Go to permalink");
    evt.mouseOverAndClick(ELEMENT_MORE_LINK);
    evt.mouseOverAndClick(ELEMENT_PERMALINK_LINK);
    evt.waitForAndGetElement(ELEMENT_PERMALINK_POPUP);
    Utils.pause(2000);
  }

  /**
   * Restricted a page from infor bar or More menu
   * 
   * @param opParams
   */
  public void restrictedPage(Boolean... opParams) {
    info("Make Restricted page");
    Boolean useRestrictLink = (Boolean) (opParams.length > 0 ? opParams[0] : false);
    if (useRestrictLink) {
      evt.waitForAndGetElement(ELEMENT_PUBLIC_WIKI_ICON);
      evt.click(ELEMENT_PUBLIC_WIKI_ICON);
    } else {
      goToPermalink();
    }
    evt.click(ELEMENT_MAKE_RESTRICT_BUTTON);
    evt.waitForAndGetElement(ELEMENT_MAKE_PUBLIC_BUTTON);
    dialog.closeMessageDialog();
    Utils.pause(2000);
  }

  /**
   * Public a page from infor bar or More menu
   * 
   * @param opParams
   */
  public void publicPage(Boolean... opParams) {
    info("Make Public page");
    Boolean useRestrictLink = (Boolean) (opParams.length > 0 ? opParams[0] : false);
    if (useRestrictLink) {
      evt.waitForAndGetElement(ELEMENT_RESTICT_WIKI_ICON);
      evt.click(ELEMENT_RESTICT_WIKI_ICON);
    } else {
      goToPermalink();
    }
    evt.click(ELEMENT_MAKE_PUBLIC_BUTTON);
    evt.waitForAndGetElement(ELEMENT_MAKE_RESTRICT_BUTTON);
    dialog.closeMessageDialog();
    Utils.pause(2000);
  }

  /**
   * Gets a permanent link by a given value.
   * 
   * @return The value.
   */
  public String getPermalink() {
    return evt.getValue(ELEMENT_PERMALINK_TEXT);
  }

  /**
   * Close permalink popup
   */
  public void closePermalinkPopup() {
    info("Click on Close button");
    evt.click(ELEMENT_PERMALINK_CLOSE);
    evt.waitForElementNotPresent(ELEMENT_PERMALINK_POPUP);
    info("Permalink popup is closed");
  }

  /**
   * Go to attach files in Wiki Home page
   * 
   * @param number
   */
  public void goToAttachFiles(String number) {
    info("Click attach file link");
    Utils.pause(2000);
    evt.waitForAndGetElement(ELEMENT_PAGE_ATTACHFILE_NUMBER.replace("${number}", number), testBase.getDefaultTimeout(), 1);
    evt.click(ELEMENT_PAGE_ATTACHFILE_NUMBER.replace("${number}", number));
  }

  /**
   * Delete attach file in View mode in Wiki Homepage or in edit mode when
   * editing a wiki page
   * 
   * @param fileName
   */
  public void DeleteAttachFiles(String fileName) {
    info("Delete attach files");
    Utils.pause(2000);
    if (evt.waitForAndGetElement(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE.replace("${fileName}", fileName), 5000, 0) != null) {
      evt.click(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE.replace("${fileName}", fileName));
      evt.waitForElementNotPresent(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE.replace("${fileName}", fileName));
    } else {
      evt.waitForAndGetElement(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE_2.replace("${fileName}", fileName),
                               testBase.getDefaultTimeout(),
                               0);
      evt.click(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE_2.replace("${fileName}", fileName));
      evt.waitForElementNotPresent(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE_2.replace("${fileName}", fileName));
    }
  }

  /**
   * Go to Wiki Home of the space
   * 
   * @param space
   */
  public void goToWikiHomeOfSpaceFromBreadcrumb(String space, String userWiki) {
    goToSpaceSwitcher();
    if (!space.isEmpty()) {
      info("Select the space");
      evt.click(ELEMENT_SPACE_SWITCHER_SELECTED_SPACE.replace("$space", space));
      Utils.pause(3000);
      if (userWiki == null || userWiki == "")
        evt.waitForAndGetElement(ELEMENT_WIKI_HOME_BREADCRUMB_PATH_HOME.replace("$locator1", space).replace("$locator2",
                                                                                                            "Wiki Home"));
      else
        evt.waitForAndGetElement(ELEMENT_WIKI_HOME_BREADCRUMB_PATH_HOME.replace("$locator1", userWiki).replace("$locator2",
                                                                                                               "Wiki Home"));
    }
  }

  /**
   * Open Space switcher
   */
  public void goToSpaceSwitcher() {
    info("Click on drop down");
    evt.click(ELEMENT_SPACE_DROP_DOWN);
    Utils.pause(2000);
  }

  /**
   * Input and search a space in space switcher
   * 
   * @param text
   */
  public void inputSpaceSwitcher(String text) {
    evt.waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).clear();
    evt.waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).click();
    evt.waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).sendKeys(text);
    Utils.pause(1000);

  }

  /**
   * Close space switcher
   */
  public void closeSpaceWitcher() {
    info("Click on Close button");
    evt.click(ELEMENT_SPACE_SWITCHER_CLOSE_BTN);
    evt.waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_INPUT);
  }

  /**
   * Close space switcher of the breadcrumb on wiki home page by clicking on
   * outside
   */
  public void closeSpaceSwitcherByClickOutSide() {
    info("Click on outside to close space switcher");
    evt.click(ELEMENT_SPACE_SWITCHER_OUTSIDE);
    evt.waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_INPUT);
  }

  /**
   * Close space switcher of move page popup by clicking on outside
   */
  public void closeSpaceSwitcherMovePopupByClickOutside() {
    info("Click on outside to close space switcher");
    evt.click(ELEMENT_SPACE_SWITCHER_OUTSIDE);
    evt.waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_INPUT_MOVE_PAGE_POPUP);
  }

  /**
   * Open Page information
   */
  public void goToPageInformation() {
    info("Go to Page Information");
    evt.mouseOverAndClick(ELEMENT_MORE_LINK);
    evt.mouseOverAndClick(ELEMENT_PAGE_INFO);
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_PAGE_INFO_TITLE);
    Utils.pause(2000);
  }

  /**
   * Open information version table
   * 
   * @param version
   */
  public void goToRevisions(String version) {
    info("Click on Version");
    evt.click(ELEMENT_WIKI_PAGE_INFOMATION_VERSION.replace("${version}", version));
    info("Verify that the table is shown");
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_INFORMATION_TABLE_TITLE, 2000, 1);
  }

  /**
   * Open information table
   * 
   * @param page
   * @param version
   */
  public void viewInformationTable(String page, String version) {
    info("Open a wiki page 1");
    evt.waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}", page), 2000, 0).click();
    info("Open information table");
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_INFOMATION_VERSION.replace("${version}", version), 2000, 0).click();
    info("Verify that the table is shown");
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_INFORMATION_TABLE_TITLE, 2000, 1);
  }

  /**
   * Open compare version page by clicking on View Changes link
   */
  public void goToViewChange() {
    info("Click on View change link on the information bar");
    evt.click(ELEMENT_INFOR_BAR_VIEW_CHANGE_LINK);
    info("Verify that compare version page is shown");
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_COMPARE_VERSION_TITLE);
  }

  /**
   * Export a Wiki Page
   */
  public void exportWikiPage() {
    info("Export a Wiki Page");
    evt.click(ELEMENT_MORE_LINK);
    evt.click(ELEMENT_PDF_LINK);
    Utils.pause(2000);
  }

  /**
   * Go to Move page form
   */
  public void goToMovePageForm() {
    info("Go to Move page form");
    info("Click on More link");
    evt.click(ELEMENT_MORE_LINK);
    info("Click on Move page link");
    if (evt.waitForAndGetElement(ELEMENT_MOVE_PAGE, 5000, 0) == null) {
      evt.mouseOverAndClick(ELEMENT_MOVE_PAGE);
    } else {
      evt.click(ELEMENT_MOVE_PAGE);
    }
    evt.waitForAndGetElement(ELEMENT_MOVE_PAGE_POPUP, 3000, 1);
    Utils.pause(2000);
  }

  public void cancelPermissions() {
    info("Permissions page");
    evt.click(ELEMENT_CANCEL_PERMISSION);
  }

  /**
   * Go to Export a page
   */
  public void goToExportPage() {
    info("Go to Export a page");
    info("Click on More link");
    evt.click(ELEMENT_MORE_LINK);
    info("Click on Move page link");
    if (evt.waitForAndGetElement(ELEMENT_PDF_LINK, 5000, 0) == null) {
      evt.mouseOverAndClick(ELEMENT_PDF_LINK);
    } else {
      evt.click(ELEMENT_PDF_LINK);
    }
    evt.waitForElementNotPresent(ELEMENT_PDF_LINK, 3000, 1);
    Utils.pause(2000);
  }
}
