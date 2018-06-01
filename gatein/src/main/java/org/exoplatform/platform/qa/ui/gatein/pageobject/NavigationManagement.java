package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

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
    $(byXpath(ELEMENT_MANAGESITES_EDIT_NAVIGATION_ICON.replace("${site}", site))).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_NAVIGATION_MANAGEMENT_POPUP_TITLE).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Edit layout of a portal
   *
   * @param site String
   */
  public void goToEditLayout(String site) {
    info("Click on Edit layout button");
    $(byXpath(ELEMENT_MANAGESITES_EDIT_LAYOUT_ICON.replace("${site}", site))).click();

  }

  /**
   * change config of a portal
   *
   * @param site String
   */
  public void changeConfig(String site) {
    goToEditLayout(site);
    info("Click on site's config button");
  $(ELEMENT_MANAGESITES_EDIT_LAYOUT_SITE_CONFIG_BTN).click();

  }

  /**
   * Go to Edit site configuration
   *
   * @param site String
   */
  public void goToEditSiteConfig(String site) {
    info("Click on Edit Site Configuration button");
   $(byXpath(ELEMENT_MANAGESITES_EDIT_CONFIG_ICON.replace("${site}", site))).click();

  }

  /**
   * Select a item in ContextMenu
   *
   * @param link specifiContextMenu
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
      $(ELEMENT_MANAGESITES_CONTEXTMENU_COPY_ICON).click();

      break;
    case CLONE_NODE:
      info("Click on Clone node");
     $(ELEMENT_MANAGESITES_CONTEXTMENU_CLONE_ICON).click();

      break;
    case CUT_NODE:
      info("Click on Cut node");
      $(ELEMENT_MANAGESITES_CONTEXTMENU_CUT_ICON).click();

      break;
    case PASTE_NODE:
      info("Click on Paste node");
      $(ELEMENT_MANAGESITES_CONTEXTMENU_PASTE_ICON).click();

      break;
    case DELETE_NODE:
      info("Click on Delete node");
      $(ELEMENT_MANAGESITES_CONTEXTMENU_DELETE_ICON).click();
      alert.acceptAlert();
      break;
    case MOVE_UP:
      info("Click on Moveup node");
      $(ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_UP_ICON).click();

      break;
    case MOVE_DOWN:
      info("Click on Move down node");
      $(ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_DOWN_ICON).click();

      break;
    }
  }

  /**
   * Add a node
   *
   * @param title String
   * @param subTitle String
   */
  public void addNode(String title, String subTitle) {
    if (subTitle.isEmpty()) {
      info("Add parent node");
      $(ELEMENT_UP_LEVEL_PATH_NODE).click();
      $(ELEMENT_ADD_NODE).click();
      $(ELEMENT_NODE_NAME).setValue(title);
    } else {
      info("Add sub node");
      $(byText(title)).waitUntil(Condition.appears,Configuration.timeout);
      $(byClassName("uiNavigationManagement")).find(byText(title)).scrollTo().click();
      $(ELEMENT_ADD_NODE).click();
      $(ELEMENT_NODE_NAME).setValue(subTitle);
    }
  }

  /**
   * Delete a node
   *
   * @param title String
   */
  public void deleteNode(String title) {
    info("Delete a node");
    info("Right click on the node");
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byClassName("uiNavigationManagement")).find(byText(title)).scrollTo().contextClick();
    info("Select Delete link");
    selectItem(specifiContextMenu.DELETE_NODE);

    info("Click on Save button");
    $(ELEMENT_NAVIGATION_MANAGEMENT_SAVE).click();

  }

  /**
   * Edit Node page
   *
   * @param name String
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
   * @param name String
   *
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
   * @param name String
   */
  public void copyNode(String name) {
    info("Copy a node");
    info("Right click on the node");
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byXpath(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name))).contextClick();
    info("Select Edit link");
    selectItem(specifiContextMenu.COPY_NODE);
  }

  /**
   * Clone a node
   *
   * @param name String
   */
  public void cloneNode(String name) {
    info("Clone a node");
    info("Right click on the node");
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byXpath(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name))).contextClick();
    info("Select Edit link");
    selectItem(specifiContextMenu.CLONE_NODE);
  }

  /**
   * Cut a node
   *
   * @param name String
   */
  public void cutNode(String name) {
    info("Cut a node");
    info("Right click on the node");
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
   $(byXpath(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name))).contextClick();
    info("Select Edit link");
    selectItem(specifiContextMenu.CUT_NODE);
  }

  /**
   * Paste a node
   *
   * @param name String
   */
  public void pasteNode(String name) {
    info("Paste a node");
    info("Right click on the node");
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byXpath(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name))).contextClick();
    info("Select Edit link");
    selectItem(specifiContextMenu.PASTE_NODE);
  }

  /**
   * Move up a node
   *
   * @param name String
   */
  public void moveUpNode(String name) {
    info("Move up a node");
    info("Right click on the node");
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
   $(byXpath(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name))).contextClick();
    info("Select Edit link");
    selectItem(specifiContextMenu.MOVE_UP);
  }

  /**
   * Move down a node
   *
   * @param name String
   */
  public void moveDownNode(String name) {
    info("Move down a node");
    info("Right click on the node");
    $(ELEMENT_ACCOUNT_NAME_LINK).click();
    $(byXpath(ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace("${name}", name))).contextClick();
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
   * @param language String
   * @param isNotLabelMode  boolean
   * @param label String
   * @param isVisibale boolean
   * @param isPublishDateTime boolean
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
   * @param namePage String
   * @param titlePage String
   * @param isCreatePage boolean
   * @param isSelectPage boolean
   * @param isCleanPage boolean
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
   * @param isSizeIcon boolean
   * @param typeIcon boolean
   * @param nameIcon boolean
   * @param isGetDefault boolean
   */
  public void inputInfoIcon(boolean isSizeIcon, String typeIcon, String nameIcon, boolean isGetDefault) {

  }

  /**
   * Search a page
   *
   * @param title  String
   * @param siteName String
   * @param type String
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
   * @param title String
   */
  public void selectPage(String title) {
    info("Searching the page");
    searchPage(title, "", "");
    info("Select a page");
    evt.click(ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGES_BTN);

  }

  /**
   * verify Add Navigation permission
   *@param title String
   * @param isEnable boolean
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
