package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import org.openqa.selenium.WebElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

/**
 * Path: Edit-Page-Add Pages
 */
public class PageCreationWizard {

  private final TestBase       testBase;

  public ContentList           contList;

  public ManageAlert           magAlert;

  public Button                but;

  public PortalManagePages     portMgPg;

  ContentDetail                contDetail;

  private ElementEventTestBase evt;

  public PageCreationWizard(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.contList = new ContentList(testBase);
    this.contDetail = new ContentDetail(testBase);
    this.magAlert = new ManageAlert(testBase);
    this.but = new Button(testBase);
    this.portMgPg = new PortalManagePages(testBase);
  }

  /**
   * Input data in page info page at step 1
   *
   * @param name String
   * @param isMode String
   * @param lang String
   * @param disName String
   * @param isVis Boolean
   * @param isPub Boolean
   */
  public void inputPageInfoStep1(String name, Boolean isMode, String lang, String disName, Boolean isVis, Boolean isPub) {
    info("Input data in page info page at step 1");
    if (name != null && name != "") {
      info("Input name");
      $(ELEMENT_PAGE_NAME_INPUT).setValue(name);

    }
    if (isMode != null) {
      info("Input mode");
      if (isMode)
        $(ELEMENT_PAGE_MODE_CHECKBOX).setSelected(true);
      else
        evt.uncheck(ELEMENT_PAGE_MODE_CHECKBOX, 2);
    }
    if (lang != null && lang != "") {
      info("Input language");
      $(ELEMENT_PAGE_LANGUAGE_SELECT_BOX).selectOption(lang);
    }
    if (disName != null && disName != "") {
      info("Input display name");
      $(ELEMENT_PAGE_DISPLAY_NAME_INPUT).setValue(name);
    }
    if (isVis != null) {
      info("Input Visible");
      if (isVis)
        $(ELEMENT_PAGE_VISIBLE_CHECKBOX).setSelected(true);
      else
        $(ELEMENT_PAGE_VISIBLE_CHECKBOX).setSelected(false);
    }
    if (isPub != null) {
      info("Input publication date");
      if (isPub)
        $(ELEMENT_PAGE_PUBLICATION_DATE_CHECKBOX).setSelected(true);
      else
        $(ELEMENT_PAGE_PUBLICATION_DATE_CHECKBOX).setSelected(false);
    }
  }

  /**
   * Add content in page editor
   *
   * @param tab SelenideElement
   * @param element SelenideElement
   */
  public void addApplication(SelenideElement tab, SelenideElement element) {
    $(ELEMENT_APPLICATION_TAB_ACTIVE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(tab).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    sleep(3000);
    element.dragAndDropTo($(byClassName("VIEW-PAGE")));
  }

  /**
   * Add an application to a layout
   *
   * @param nameApp String
   * @param appLocator String
   * @param layoutLocator SelenideElement
   * @param tabName String
   */
  public void addApp(String tabName, String nameApp, SelenideElement appLocator, SelenideElement layoutLocator) {
    info("Add an application to the layout");
    $(ELEMENT_APPLICATION_TAB).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    if (!tabName.isEmpty())
      $(byText(tabName)).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    $(appLocator).dragAndDropTo(layoutLocator);

    info("Verify that the application is shown in the layout");
    $(byText(nameApp)).should(Condition.exist);
    info("The application is shown in the layout page");
    saveChangesPageEditor();
  }

  /**
   * Add a Content list to a Page by folder
   *
   * @param path String
   * @param folder String
   */
  public void addContentlistByFolder(String path, String folder) {

    $(ELEMENT_PAGEEDITOR_VIEWPAGE).hover();
    $(ELEMENT_CONTENT_LIST_EDIT_BTN).click();
    contList.selectFolderContent(path, folder);
    $(ELEMENT_CONTENT_LIST_SAVE_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_CONTENT_LIST_CLOSE_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_PAGE_FINISH_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

  }

  /**
   * Add a Content list to a page by content
   *
   * @param path String
   * @param content String
   */
  public void addContentListByContent(String path, String content) {

    $(ELEMENT_PAGEEDITOR_VIEWPAGE).hover();
    $(ELEMENT_CONTENT_LIST_EDIT_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    sleep(2000);
    evt.check(ELEMENT_CONTENT_LIST_BY_CONTENT_MODE, 2);
    contList.selectFolderContent(path, content);
    $(ELEMENT_MULTIPLE_CONTENT_POPUP_SAVE_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_CONTENT_LIST_SAVE_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_CONTENT_LIST_CLOSE_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_PAGE_FINISH_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
  }

  /**
   * Add a Cotent Detail to a page
   *
   * @param path String
   * @param content String
   */
  public void addContentDetail(String path, String content) {
    addApplication($(byTitle("Content")), $(byId("Content/portlet_SingleContentViewer")));
    sleep(3000);
    $(ELEMENT_PAGEEDITOR_VIEWPAGE).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_CONTENT_DETAIL_EDIT_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    contDetail.selectFolderContent(path, content);
    $(ELEMENT_CONTENT_DETAIL_SAVE_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_CONTENT_DETAIL_CLOSE_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_PAGE_FINISH_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
  }

  /**
   * Save all changes of page editor
   */
  public void saveChangesPageEditor() {
    info("Save change Page Editor");
    $(ELEMENT_PAGEEDITOR_FINISHBTN).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_PAGEEDITOR_FINISHBTN).click();
  }

  /**
   * View properties
   *
   * @param verify boolean
   */
  public void viewProperties(boolean... verify) {
    info("Click on Switch view mode button");
    $(ELEMENT_VIEW_PROPERTIES).click();

    $(ELEMENT_VIEW_PROPERTIES_POPUP).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Get old title of a page
   *
   * @return title
   */
  public String getOldTitle() {
    WebElement el = testBase.getExoWebDriver().getWebDriver().findElement(ELEMENT_VIEW_PROPERTIES_TITLE);
    String tilte = el.getAttribute("value");
    info("tilte:" + tilte);
    return tilte;
  }

  /**
   * Change Properties of a page
   *
   * @param title boolean
   * @param groupsPath boolean
   * @param memberShips boolean
   * @param isShowMaxWindow boolean
   * @param  isAccessPermision boolean
   * @param isEditPermission boolean
   */
  public void changeProperties(String title,
                               String groupsPath,
                               String memberShips,
                               boolean isAccessPermision,
                               boolean isEditPermission,
                               boolean... isShowMaxWindow) {
    if (!title.isEmpty()) {
      info("Input new title");
      $(ELEMENT_VIEW_PROPERTIES_TITLE).waitUntil(Condition.visible,Configuration.collectionsTimeout).setValue(title);

    }
    if (!groupsPath.isEmpty()) {
      info("Select a group");

      $(ELEMENT_VIEW_PROPERTIES_PERMISSION_TAB).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
      if (isAccessPermision == true) {
        $(ELEMENT_VIEW_PROPERTIES_ADD_PERMISSION_BTN).click();
        info("Select a group");
        selectGroup(groupsPath);
        info("Select a meberships");
        selectMemberShip(memberShips);
      }

      if (isEditPermission == true) {
        info("Select Edit permission settings tab");
        $(ELEMENT_VIEW_PROPERTIES_EDIT_PERMISSITION_SETTINGS).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
        info("Click on Select permission button");
        $(ELEMENT_VIEW_PROPERTIES_SELECT_PERMISSION_BTN).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
        selectGroupEditTab(groupsPath);
        info("Select a meberships");
        selectMemberShipEditTab(memberShips);
      }
    }
    if (isShowMaxWindow.length > 0) {
      info("Check on show Max window checkbox");
      evt.check(ELEMENT_VIEW_PROPERTIES_SHOW_MAX_WINDOW, 2);
    }
    saveChangeProperties();

  }

  /**
   * Save changes all when View Properties
   */
  public void saveChangeProperties() {
    info("Save all changes");
    $(ELEMENT_VIEW_PROPERTIES_SAVE_BTN).click();
  }

  /**
   * Select a group in permission selector popup
   *
   * @param groupsPath is path of groups as:Platform/Content Manangement
   */
  public void selectGroup(String groupsPath) {
    info("Select a group with the path:" + groupsPath);
    String[] groups = groupsPath.split("/");
    for (String groupSelect : groups) {
      info("Select group:" + groupSelect);
      $(byXpath("(//*[@title='${groupSelect}'])[1]".replace("${groupSelect}",groupSelect))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    }

  }

  /**
   * Select a group in permission selector popup
   *
   * @param groupsPath is path of groups as:Platform/Content Manangement
   */
  public void selectGroupEditTab(String groupsPath) {
    info("Select a group with the path:" + groupsPath);
    String[] groups = groupsPath.split("/");
    for (String groupSelect : groups) {
      info("Select group:" + groupSelect);
      evt.click(ELEMENT_EDIT_PERMISSION_SELECTOR_POPUP_GROUP.replace("${group}", groupSelect), 0, true);
    }

  }

  /**
   * Select a membership of a group
   *
   * @param memberShip String
   */
  public void selectMemberShip(String memberShip) {
    info("Select a membership:" + memberShip);
    $(byText(memberShip)).click();

  }

  /**
   * Select a membership of a group
   *
   * @param memberShip String
   */
  public void selectMemberShipEditTab(String memberShip) {
    info("Select a membership:" + memberShip);
    evt.click(ELEMENT_EDIT_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP.replace("${member}", memberShip), 0, true);

  }

}
