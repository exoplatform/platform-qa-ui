package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import org.exoplatform.platform.qa.ui.selenium.Dialog;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiHomePage {
  private final TestBase       testBase;

  public Dialog                dialog;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase
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
    $(ELEMENT_ADD_PAGE_LINK).click();
    $(ELEMENT_BLANK_PAGE_LINK).click();
    info("Blank wiki page is shown");
  }

  /**
   * Go to "Add template wiki page"
   */
  public void goToAddTemplateWikiPage() {
    info("--Go to add template wiki page--");
    $(ELEMENT_ADD_PAGE_LINK).click();
    $(ELEMENT_FROM_TEMPLATE_LINK).click();
  }

  /**
   * Go to "Add blank wiki page"
   */
  public void goToEditPage() {
    info("--Go to edit page--");
    $(ELEMENT_EDIT_PAGE_LINK).click();
  }

  /**
   * Go to Home Wiki Page
   */
  public void goToHomeWikiPage() {
    info("-- Go to wiki home page --");
    $(ELEMENT_WIKI_HOME_PAGE_LINK).click();
  }

  /**
   * Select any page
   *
   * @param title
   */
  public void goToAPage(String title) {
    info("-- Go to wiki page --");
    $(byText(title)).click();
    $(ELEMENT_WIKI_HOME_PAGE_TEXT).shouldNot(Condition.exist);
  }

  /**
   * Select any page
   *
   * @param title
   */
  public void deleteWiki(String title) {

    info("Select the wiki page to delete");
    selectAPage(title);
    info("Click on More link");

    $(ELEMENT_MORE_LINK).click();
    $(ELEMENT_DELETE_LINK).click();
    $(ELEMENT_CONFIRM_WIKI_DELETE).click();
    $(byText(title)).shouldNot(Condition.exist);

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
      $(ELEMENT_MORE_LINK).click();
      if (evt.waitForAndGetElement(ELEMENT_DELETE_LINK, 5000, 0) == null) {
        evt.mouseOverAndClick(ELEMENT_DELETE_LINK);
      } else {
        $(ELEMENT_DELETE_LINK).click();
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
    $(byText(page)).click();
    info("The page is shown");
  }

  /**
   * Go to "Go to My Draft"
   */
  public void goToMyDraft() {
    info("Click on Browser drop down");
    $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).click();
    info("Select wiki settings label");
    $(ELEMENT_SEARCH_BROWSERS_MY_DRAFT).click();
  }

  /**
   * Open search page with a text
   *
   * @param text
   */
  public void goTosearchPage(String text) {
    info("Input a text to search field");
    $(ELEMENT_SEARCH_TEXTBOX_POPUP).val(text);
    $(ELEMENT_SEARCH_BTN).click();
  }

  /**
   * Open Wiki Settings page
   */
  public void goToWikiSettingPage() {
    info("Click on Browser drop down");
    $(ELEMENT_SEARCH_BROWSERS_DROPDOWN).click();
    info("Select wiki settings label");
    $(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS).click();
  }

  /**
   * Open permission tab
   */
  public void openPermTab() {
    info("Open Permission tab");
    $(ELEMENT_WIKI_SETTING_PERM_TAB).click();
  }

  /**
   * Open permissions for the wiki
   */
  public void goToPermissions() {
    info("Permissions page");
    evt.click(ELEMENT_MORE_LINK);
    evt.click(ELEMENT_PERMISSION_LINK);
    info("The permission popup is shown");
  }

  /**
   * Confirm messages
   *
   * @param isConfirm = true if want to click on Confirm button = false if want to
   *          click on Cancel button
   */
  public void confirmWaringMessage(Boolean isConfirm) {
    if (isConfirm) {
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_OK_BTN, 2000, 0) != null) {
        info("Click on OK button");
        $(ELEMENT_CONFIRM_POPUP_OK_BTN).click();
      }
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_CONFIRM_BTN, 2000, 0) != null) {
        info("Click on Confirm button");
        $(ELEMENT_CONFIRM_POPUP_CONFIRM_BTN).click();
      }
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_YES_BTN, 2000, 0) != null) {
        info("Click on Yes button");
        $(ELEMENT_CONFIRM_POPUP_YES_BTN).click();
      }
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_YES_BTN, 2000, 0) != null) {
        info("Click on Yes button");
        $(ELEMENT_CONFIRM_POPUP_YES_BTN).click();
      }
      if (evt.waitForAndGetElement(ELEMENT_WARNING_OK_BTN, 2000, 0) != null) {
        info("Click OK button");
        $(ELEMENT_WARNING_OK_BTN).click();
      }
    } else {
      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_CANCEL_BTN, 2000, 0) != null) {
        info("Click on Cancel button");
        $(ELEMENT_CONFIRM_POPUP_CANCEL_BTN).click();
      }

      if (evt.waitForAndGetElement(ELEMENT_CONFIRM_POPUP_NO_BTN, 2000, 0) != null) {
        info("Click on No button");
        $(ELEMENT_CONFIRM_POPUP_NO_BTN).click();
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
  }

  /**
   * Restricted a page from infor bar or More menu
   *
   * @param opParams
   */
  public void restrictedPage(Boolean... opParams) {
    info("Make Restricted page");
    Boolean useRestrictLink = (opParams.length > 0 ? opParams[0] : false);
    if (useRestrictLink) {
      evt.waitForAndGetElement(ELEMENT_PUBLIC_WIKI_ICON);
      $(ELEMENT_PUBLIC_WIKI_ICON).click();
    } else {
      goToPermalink();
    }
    $(ELEMENT_MAKE_RESTRICT_BUTTON).click();
    dialog.closeMessageDialog();
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
      $(ELEMENT_RESTICT_WIKI_ICON).click();
    } else {
      goToPermalink();
    }
    $(ELEMENT_MAKE_PUBLIC_BUTTON).click();
    dialog.closeMessageDialog();
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
    $(ELEMENT_PERMALINK_CLOSE).click();
    info("Permalink popup is closed");
  }

  /**
   * Go to attach files in Wiki Home page
   *
   * @param number
   */
  public void goToAttachFiles(String number) {
    info("Click attach file link");
    $(ELEMENT_PAGE_ATTACHFILE_NUMBER.replace("${number}", number)).click();
  }

  /**
   * Delete attach file in View mode in Wiki Homepage or in edit mode when editing
   * a wiki page
   *
   * @param fileName
   */
  public void DeleteAttachFiles(String fileName) {
    info("Delete attach files");

    if (evt.waitForAndGetElement(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE.replace("${fileName}", fileName), 5000, 0) != null) {
      $(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE.replace("${fileName}", fileName)).click();
    } else {
      $(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE_2.replace("${fileName}", fileName)).click();
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
      $(ELEMENT_SPACE_SWITCHER_SELECTED_SPACE.replace("$space", space)).click();
    }
  }

  /**
   * Open Space switcher
   */
  public void goToSpaceSwitcher() {
    info("Click on drop down");
    $(ELEMENT_SPACE_DROP_DOWN).click();
  }

  /**
   * Input and search a space in space switcher
   *
   * @param text
   */
  public void inputSpaceSwitcher(String text) {
    SelenideElement spaceSwitcherInput = $(ELEMENT_SPACE_SWITCHER_INPUT);
    spaceSwitcherInput.clear();
    spaceSwitcherInput.click();
    spaceSwitcherInput.sendKeys(text);
  }

  /**
   * Close space switcher
   */
  public void closeSpaceWitcher() {
    info("Click on Close button");
    $(ELEMENT_SPACE_SWITCHER_CLOSE_BTN).click();
    evt.waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_INPUT);
  }

  /**
   * Close space switcher of the breadcrumb on wiki home page by clicking on
   * outside
   */
  public void closeSpaceSwitcherByClickOutSide() {
    info("Click on outside to close space switcher");
    $(ELEMENT_SPACE_SWITCHER_OUTSIDE).click();
    evt.waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_INPUT);
  }

  /**
   * Close space switcher of move page popup by clicking on outside
   */
  public void closeSpaceSwitcherMovePopupByClickOutside() {
    info("Click on outside to close space switcher");
    $(ELEMENT_SPACE_SWITCHER_OUTSIDE).click();
    evt.waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_INPUT_MOVE_PAGE_POPUP);
  }

  /**
   * Open Page information
   */
  public void goToPageInformation() {
    info("Go to Page Information");
    evt.mouseOverAndClick(ELEMENT_MORE_LINK);
    evt.mouseOverAndClick(ELEMENT_PAGE_INFO);
  }

  /**
   * Open information version table
   *
   * @param version
   */
  public void goToRevisions(String version) {
    info("Click on Version");
    $(ELEMENT_WIKI_PAGE_INFOMATION_VERSION.replace("${version}", version)).click();
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
    $(ELEMENT_INFOR_BAR_VIEW_CHANGE_LINK).click();
    info("Verify that compare version page is shown");
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_COMPARE_VERSION_TITLE);
  }

  /**
   * Export a Wiki Page
   */
  public void exportWikiPage() {
    info("Export a Wiki Page");
    $(ELEMENT_MORE_LINK).click();
    $(ELEMENT_PDF_LINK).click();
  }

  /**
   * Go to Move page form
   */
  public void goToMovePageForm() {
    info("Go to Move page form");
    info("Click on More link");
    $(ELEMENT_MORE_LINK).click();
    info("Click on Move page link");
    if (evt.waitForAndGetElement(ELEMENT_MOVE_PAGE, 5000, 0) == null) {
      evt.mouseOverAndClick(ELEMENT_MOVE_PAGE);
    } else {
      $(ELEMENT_MOVE_PAGE).click();
    }
  }

  public void cancelPermissions() {
    info("Permissions page");
    $(ELEMENT_CANCEL_PERMISSION).click();
  }

  /**
   * Go to Export a page
   */
  public void goToExportPage() {
    info("Go to Export a page");
    info("Click on More link");
    $(ELEMENT_MORE_LINK).click();
    info("Click on Move page link");
    if (evt.waitForAndGetElement(ELEMENT_PDF_LINK, 5000, 0) == null) {
      evt.mouseOverAndClick(ELEMENT_PDF_LINK);
    } else {
      $(ELEMENT_PDF_LINK).click();
    }
  }
}
