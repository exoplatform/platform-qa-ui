package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

/**
 * Path: Navigation Management popup
 */
public class NavigationManagement {
  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public NavigationManagement(TestBase testBase) {
    this.testBase = testBase;
    this.alert = new ManageAlert(testBase);
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Open Navigation Management popup
   *
   * @param site as acme or intranet or a group
   */
  public void goToEditNavigation(String site) {
    evt.waitForAndGetElement(ELEMENT_MANAGESITES_EDIT_NAVIGATION_ICON.replace("${site}", site), 3000, 0);
    evt.click(ELEMENT_MANAGESITES_EDIT_NAVIGATION_ICON.replace("${site}", site));
    evt.waitForAndGetElement(ELEMENT_NAVIGATION_MANAGEMENT_POPUP_TITLE, 3000, 0);
  }

  /**
   * Edit layout of a portal
   *
   * @param site
   */
  public void goToEditLayout(String site) {
    info("Click on Edit layout button");
    evt.click(ELEMENT_MANAGESITES_EDIT_LAYOUT_ICON.replace("${site}", site));

  }

  /**
   * change config of a portal
   *
   * @param site
   */
  public void changeConfig(String site) {
    goToEditLayout(site);
    info("Click on site's config button");
    evt.click(ELEMENT_MANAGESITES_EDIT_LAYOUT_SITE_CONFIG_BTN);

  }

  /**
   * Go to Edit site configuration
   *
   * @param site
   */
  public void goToEditSiteConfig(String site) {
    info("Click on Edit Site Configuration button");
    evt.click(ELEMENT_MANAGESITES_EDIT_CONFIG_ICON.replace("${site}", site));

  }

  /**
   * Select a item in ContextMenu
   *
   * @param link
   */
  public void selectItem(specifiContextMenu link) {
    switch (link) {
    case ADD_NEW_NODE:
      break;
    case EDIT_NODE_PAGE:
      info("Click on Edit node page");
      $(byText("Edit Node's Page")).click();

      break;
    case EDIT_THIS_NODE:
      info("Click on Edit icon");
      $(ELEMENT_MANAGESITES_CONTEXTMENU_EDIT_ICON).click();

      break;
    case COPY_NODE:
      info("Click on Copy node");
      evt.click(ELEMENT_MANAGESITES_CONTEXTMENU_COPY_ICON);

      break;
    case CLONE_NODE:
      info("Click on Clone node");
      evt.click(ELEMENT_MANAGESITES_CONTEXTMENU_CLONE_ICON);

      break;
    case CUT_NODE:
      info("Click on Cut node");
      evt.click(ELEMENT_MANAGESITES_CONTEXTMENU_CUT_ICON);

      break;
    case PASTE_NODE:
      info("Click on Paste node");
      evt.clickByJavascript(ELEMENT_MANAGESITES_CONTEXTMENU_PASTE_ICON);

      break;
    case DELETE_NODE:
      info("Click on Delete node");
      $(ELEMENT_MANAGESITES_CONTEXTMENU_DELETE_ICON).click();
      alert.acceptAlert();
      break;
    case MOVE_UP:
      info("Click on Moveup node");
      evt.clickByJavascript(ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_UP_ICON);

      break;
    case MOVE_DOWN:
      info("Click on Move down node");
      evt.clickByJavascript(ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_DOWN_ICON);

      break;
    }
  }

  /**
   * Add a node
   *
   * @param title
   * @param subTitle
   */
  public void addNode(String title, String subTitle) {
    if (subTitle.isEmpty()) {
      info("Add parent node");
      evt.click(ELEMENT_UP_LEVEL_PATH_NODE);
      evt.click(ELEMENT_ADD_NODE);
      evt.type(ELEMENT_NODE_NAME, title, true);
    } else {
      info("Add sub node");
      evt.waitForAndGetElement(ELEMENT_NAVIGATION_SPECIFIC_NODE.replace("${name}", title));
      evt.click(ELEMENT_NAVIGATION_SPECIFIC_NODE.replace("${name}", title));
      evt.click(ELEMENT_ADD_NODE);
      evt.type(ELEMENT_NODE_NAME, subTitle, true);
    }
  }

  /**
   * Delete a node
   *
   * @param title
   */
  public void deleteNode(String title) {
    info("Delete a node");
    info("Right click on the node");

    $(byClassName("uiNavigationManagement")).find(byText(title)).scrollTo().contextClick();
    info("Select Delete link");
    selectItem(specifiContextMenu.DELETE_NODE);

    info("Click on Save button");
    $(ELEMENT_NAVIGATION_MANAGEMENT_SAVE).click();

  }

  /**
   * Edit Node page
   *
   * @param name
   */

  public void editNodePage(String name) {
    info("Edit a node page");
    info("Right click on the node");
    $(byClassName("uiNavigationManagement")).find(byText(name)).scrollTo().contextClick();
    info("Select Edit link");
    selectItem(specifiContextMenu.EDIT_NODE_PAGE);
  }

  /**
   * Edit a Node
   *
   * @param oldName
   * @param newName
   */
  public void editThisNode(String name) {
    info("Edit a node");
    info("Right click on the node");
    $(byClassName("uiNavigationManagement")).find(byLinkText(name)).scrollTo().contextClick();
    info("Select Edit link");
    selectItem(specifiContextMenu.EDIT_THIS_NODE);
  }

  /**
   * Copy a node
   *
   * @param name
   */
  public void copyNode(String name) {
    info("Copy a node");
    info("Right click on the node");
    evt.rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name));
    info("Select Edit link");
    selectItem(specifiContextMenu.COPY_NODE);
  }

  /**
   * Clone a node
   *
   * @param name
   */
  public void cloneNode(String name) {
    info("Clone a node");
    info("Right click on the node");
    evt.rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name));
    info("Select Edit link");
    selectItem(specifiContextMenu.CLONE_NODE);
  }

  /**
   * Cut a node
   *
   * @param name
   */
  public void cutNode(String name) {
    info("Cut a node");
    info("Right click on the node");
    evt.rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name));
    info("Select Edit link");
    selectItem(specifiContextMenu.CUT_NODE);
  }

  /**
   * Paste a node
   *
   * @param name
   */
  public void pasteNode(String name) {
    info("Paste a node");
    info("Right click on the node");
    evt.rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name));
    info("Select Edit link");
    selectItem(specifiContextMenu.PASTE_NODE);
  }

  /**
   * Move up a node
   *
   * @param name
   */
  public void moveUpNode(String name) {
    info("Move up a node");
    info("Right click on the node");
    evt.rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name));
    info("Select Edit link");
    selectItem(specifiContextMenu.MOVE_UP);
  }

  /**
   * Move down a node
   *
   * @param name
   */
  public void moveDownNode(String name) {
    info("Move down a node");
    info("Right click on the node");
    evt.rightClickOnElement(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name));
    info("Select Edit link");
    selectItem(specifiContextMenu.MOVE_DOWN);
  }

  /**
   * Save all changes of a node
   */
  public void saveNode() {
    info("Save all changes of a node");
    $(ELEMENT_NAVIGATION_MANAGEMENT_SAVE).waitUntil(Condition.appears, Configuration.timeout);
    info("Click on Save button");
    $(ELEMENT_NAVIGATION_MANAGEMENT_SAVE).click();
    closeNavigationManagementPopup();
  }

  /**
   * Save and close navigation mangement popup
   */
  public void closeNavigationManagementPopup() {
    $(ELEMENT_SAVE_NODE).click();

  }

  /**
   * Input information for Page Node Setting tab
   *
   * @param language
   * @param isLabelMode
   * @param label
   * @param isVisibale
   * @param isPublishDateTime
   */
  public void inputInfoNodeSetting(boolean isNotLabelMode,
                                   String language,
                                   String label,
                                   boolean isVisibale,
                                   boolean isPublishDateTime) {
    if (isNotLabelMode == true) {
      info("Uncheck label mode");
      $(byText("Extended label mode:")).click();
      if (!label.isEmpty()) {
        info("Input a label");

        $(ELEMENT_NODE_SETTING_LABEL_FIELD_2).setValue(label);
      }
    } else {
      if (!language.isEmpty()) {
        info("Click on Language box");
        $(ELEMENT_NODE_SETTING_LANGUAGE_BOX).selectOption(language);
      }
      if (!label.isEmpty()) {
        info("Input a label");
        $(ELEMENT_NODE_SETTING_LABEL_FIELD_1).setValue(label);
      }
    }

    if (isVisibale == true) {
      info("Uncheck visible box");
      $(byText("Visible:")).click();
    } else {
      if (isPublishDateTime == true) {
        info("Check publish date and time box");

        $(byText("Publication date & time:")).click();
      }
    }
  }

  /**
   * Input information for Page Selector tab
   *
   * @param namePage
   * @param titlePage
   * @param isCreatePage
   * @param isSelectPage
   * @param isCleanPage
   */
  public void inputInfoPageSelector(String namePage,
                                    String titlePage,
                                    boolean isCreatePage,
                                    boolean isSelectPage,
                                    boolean isCleanPage) {
    info("Click on Page Selector tab");
    evt.click(ELEMENT_NODE_PAGE_SELECTOR_TAB);
    if (!namePage.isEmpty()) {
      info("Input a new Page's name");
      evt.type(ELEMENT_NODE_PAGE_SELECTOR_PAGE_NAME, namePage, true);
    }
    if (!titlePage.isEmpty()) {
      info("Input a new Page's title");
      evt.type(ELEMENT_NODE_PAGE_SELECTOR_PAGE_TITLE, titlePage, true);

    }
    if (isCreatePage == true) {
      info("click on Create new page button");
      evt.click(ELEMENT_NODE_PAGE_SELECTOR_CREATE_PAGE_BTN);
    }

    if (isSelectPage == true) {
      info("click on Select a page button");
      evt.click(ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGE_BTN);
    }
    if (isCleanPage == true) {
      info("click on Clear button");
      evt.click(ELEMENT_NODE_PAGE_SELECTOR_CLEAR_PAGE_BTN);
    }

  }

  /**
   * Input information for Icon tab
   *
   * @param isSizeIcon
   * @param typeIcon
   * @param nameIcon
   * @param isGetDefault
   */
  public void inputInfoIcon(boolean isSizeIcon, String typeIcon, String nameIcon, boolean isGetDefault) {

  }

  /**
   * Search a page
   *
   * @param titlePage
   * @param siteName
   * @param type
   */
  public void searchPage(String title, String siteName, String type) {
    info("waiting the page is loaded full");
    if (!title.isEmpty()) {
      info("Input a new title");
      evt.type(ELEMENT_NODE_PAGE_SELECTOR_TITLE_FIELD, title, true);
    }
    if (!siteName.isEmpty()) {
      info("Input a new site Name");
      evt.type(ELEMENT_NODE_PAGE_SELECTOR_SITES_NAME_FIELD, siteName, true);
    }
    if (!type.isEmpty()) {
      info("Select a type");
      evt.select(ELEMENT_NODE_PAGE_SELECTOR_TYPE_DROPBOX, type, 2);
    }
    info("Click on Search button");
    evt.click(ELEMENT_NODE_PAGE_SELECTOR_SEARCH_BUTTON);
  }

  /**
   * Select a page in pages list after searching the page
   *
   * @param title
   */
  public void selectPage(String title) {
    info("Searching the page");
    searchPage(title, "", "");
    info("Select a page");
    evt.click(ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGES_BTN);

  }

  /**
   * verify Add Navigation permission
   *
   * @param isEnable
   */
  public void verifyAddNavigationPerm(String title, boolean isEnable) {
    info("verify Add Navigation permission");

    if (isEnable) {

    }
  }

  /**
   * list all sublinks in Contextmenu
   */
  public enum specifiContextMenu {
    ADD_NEW_NODE, EDIT_NODE_PAGE, EDIT_THIS_NODE, COPY_NODE, CLONE_NODE, CUT_NODE, PASTE_NODE, DELETE_NODE, MOVE_UP, MOVE_DOWN;
  }
}
