package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
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
      $(byXpath("(//span[@class='PopupTitle popupTitle'])[1]")).dragAndDropTo($(byXpath("//div[@class='UITableColumnContainer']")));
      $(ELEMENT_MANAGESITES_CONTEXTMENU_DELETE_ICON).waitUntil(Condition.visible,Configuration.timeout).click();
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

  /*
  *Go To Search And Select Page
   */
  public void checkSearchAndSelectPage() {
    info("CLick on Search And Select Page");
    $(ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGES_BTN).waitUntil(Condition.visible,Configuration.timeout).click();
    $(byXpath("(//td[@headers='pageId'])[1]")).dragAndDropTo($(byXpath("(//td[@headers='actions'])[1]")));
    executeJavaScript("arguments[0].scrollBy(0,550);", $(byXpath("(//div[@class=\"PopupContent popupContent\"])[2]")).waitUntil(Condition.visible,Configuration.timeout));
    $(byXpath("//td[@class='center actionContainer']/a")).isDisplayed();
    $(byXpath(ELEMENT_CLOSE_MESSAGE)).click();
  }
  /**
   * list all sublinks in Contextmenu
   */
  public enum specifiContextMenu {
    ADD_NEW_NODE, EDIT_NODE_PAGE, EDIT_THIS_NODE, COPY_NODE, CLONE_NODE, CUT_NODE, PASTE_NODE, DELETE_NODE, MOVE_UP, MOVE_DOWN;
  }
}
