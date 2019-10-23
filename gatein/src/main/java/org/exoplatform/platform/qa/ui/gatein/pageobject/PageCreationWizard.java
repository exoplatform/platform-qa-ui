package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

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
    $(ELEMENT_APPLICATION_TAB_ACTIVE).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    $(tab).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
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
    $(ELEMENT_CONTENT_LIST_SAVE_BTN).click();
    $(ELEMENT_CONTENT_LIST_CLOSE_BTN).click();
    $(ELEMENT_PAGE_FINISH_BTN).click();

  }

  /**
   * Add a Content list to a page by content
   *
   * @param path String
   * @param content String
   */
  public void addContentListByContent(String path, String content) {

    $(ELEMENT_PAGEEDITOR_VIEWPAGE).hover();
    $(ELEMENT_CONTENT_LIST_EDIT_BTN).click();
    evt.check(ELEMENT_CONTENT_LIST_BY_CONTENT_MODE, 2);
    contList.selectFolderContent(path, content);
    $(ELEMENT_MULTIPLE_CONTENT_POPUP_SAVE_BTN).click();
    $(ELEMENT_CONTENT_LIST_SAVE_BTN).click();
    $(ELEMENT_CONTENT_LIST_CLOSE_BTN).click();
    $(ELEMENT_PAGE_FINISH_BTN).click();
  }

  /**
   * Add a Cotent Detail to a page
   *
   * @param path String
   * @param content String
   */
  public void addContentDetail(String path, String content) {
    addApplication($(byTitle("Content")), $(byId("Content/SingleContentViewer")));

    $(ELEMENT_PAGEEDITOR_VIEWPAGE).waitUntil(Condition.appears, Configuration.timeout).click();
    $(ELEMENT_CONTENT_DETAIL_EDIT_BTN).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(2000);
    contDetail.selectFolderContent(path, content);
    sleep(2000);
    $(ELEMENT_CONTENT_DETAIL_SAVE_BTN).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(2000);
    $(ELEMENT_CONTENT_DETAIL_CLOSE_BTN).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(2000);
    $(ELEMENT_PAGE_FINISH_BTN).waitUntil(Condition.visible,Configuration.timeout).click();
  }

  /**
   * Create a simple page
   *
   * @param title String
   * @param description String
   */
  public void addAPageSimple(String title, String description) {
    info("Input the title and descript");
    inputPageInfoStep1(title, true, "English", description, true, false);
    info("click on Next button of step 1");
    evt.click(ELEMENT_ADDNEWPAGE_BTNNEXT);
    info("click on Next button of step 2");
    evt.click(ELEMENT_ADDNEWPAGE_BTNNEXT);
    saveChangesPageEditor();
  }

  /**
   * Add a Container
   *
   * @param numRow this name of containers as: oneRow,twoRow...
   * @param verify boolean
   */
  public void addContainer(String numRow, boolean... verify) {
    evt.click(ELEMENT_CONTAINER_TAB);
    boolean isVerify = (verify.length > 0 ? verify[0] : true);
    info("Add container");
    info("Add new container: " + numRow);
    try {
      evt.click(ELEMENT_CONTAINER_TAB);
    } catch (org.openqa.selenium.UnhandledAlertException e) {
      magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock", 40000);
      testBase.clearCache();
    }
    evt.click(By.linkText("Rows Layout"));
    evt.dragAndDropToObject(By.id(numRow), By.className("UIRowContainer"));

    if (isVerify) {
      evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);
      evt.waitForAndGetElement(ELEMENT_CONTAINER_TITLE.replace("${title}", "Container"));
    }
    saveChangesPageEditor();
    info("the container is added");
  }

  /**
   * Edit a container
   *
   * @param newTitle String
   * @param width String
   * @param height String
   * @param oldTitle String
   */
  public void editContainer(String oldTitle, String newTitle, String width, String height) {
    info("Edit container");
    evt.click(ELEMENT_SWITCH_VIEW_MODE);
    evt.click(ELEMENT_CONTAINER_TAB);
    if (!oldTitle.isEmpty())
      evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}", oldTitle), true);
    else
      evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);

    if (!oldTitle.isEmpty())
      evt.click(ELEMENT_EDIT_CONTAINER_ICON_BY_NAME.replace("${name}", oldTitle));
    else
      evt.click(ELEMENT_EDIT_CONTAINER_ICON);
    if (!newTitle.isEmpty())
      evt.type(ELEMENT_CONTAINER_POPUP_TITLE, newTitle, true);
    if (!width.isEmpty())
      evt.type(ELEMENT_CONTAINER_POPUP_WIDTH, width, true);
    if (!height.isEmpty())
      evt.type(ELEMENT_CONTAINER_POPUP_HEIGHT, height, true);
    // but.save();
    evt.waitForAndGetElement(ELEMENT_SAVE_BTN_2);
    evt.click(ELEMENT_SAVE_BTN_2);
    evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);
    evt.waitForAndGetElement(ELEMENT_CONTAINER_TITLE.replace("${title}", newTitle));
    saveChangesPageEditor();
    info("the container is edited");
  }

  /**
   * Move a container to new place in layout
   *
   * @param title is the name of container that will be dragged and dropped
   * @param sourceLocator is the locator of the container that will be moved up or
   *          down targetLocator
   * @param targetLocator is the locator of the portlet or the container that will
   *          be replaced position by sourceLocator
   * @param heightTarget is height size of the portlet or the container that will
   *          be replaced position by sourceLocator
   */
  public void moveContainer(String title, Object sourceLocator, Object targetLocator, int heightTarget) {
    info("Move container to new place");
    try {
      evt.click(ELEMENT_CONTAINER_TAB);
    } catch (org.openqa.selenium.UnhandledAlertException e) {
      magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock", 40000);
      testBase.clearCache();
    }
    if (!title.isEmpty()) {
      evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}", title), true);

    } else {
      evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);

    }

    WebElement elSource = evt.waitForAndGetElement(sourceLocator, 2000, 0);
    WebElement elTarget = evt.waitForAndGetElement(targetLocator, 2000, 0);

    info("Get the size of target");
    Dimension size = elTarget.getSize();

    Actions builder = new Actions(testBase.getExoWebDriver().getWebDriver());
    info("Hold the source");
    builder.clickAndHold(elSource).build().perform();
    info("Move the mouse to the middle of the portlet");
    Action actionMove = builder.moveToElement(elTarget).build();
    actionMove.perform();
    info("Move the mouse under the portlet");
    Action actionMove1 = builder.moveByOffset(-(size.width / 2), -(size.height / 2) + heightTarget).build();
    actionMove1.perform();
    info("Drop the source");

    Action actiondrop = builder.release().build();
    actiondrop.perform();

    saveChangesPageEditor();
    info("the container is moved succefully");
  }

  /**
   * Check the positions of containers or portlets before and after changed their
   * position in the layout
   *
   * @param positionFirst is the position before changed
   * @param positionEnd is the position after changed
   */
  public void checkPositions(Object positionFirst, Object positionEnd) {
    info("Verify that positions of element is changed");
    evt.waitForElementNotPresent(positionFirst, 2000, 1);
    evt.waitForAndGetElement(positionEnd, 2000, 1);
    saveChangesPageEditor();
  }

  /**
   * Delete a contain in the layout
   *
   * @param name String
   */
  public void deleteContainer(String name) {
    info("Delete the container");
    try {
      evt.click(ELEMENT_CONTAINER_TAB);
    } catch (org.openqa.selenium.UnhandledAlertException e) {
      magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock", 40000);
      testBase.clearCache();
    }
    if (!name.isEmpty()) {
      evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}", name), true);

      evt.click(ELEMENT_DELETE_CONTAINER_ICON_BY_NAME.replace("${name}", name));
      magAlert.acceptAlert();

      evt.waitForElementNotPresent(ELEMENT_DELETE_CONTAINER_ICON_BY_NAME.replace("${name}", name));
    }
    saveChangesPageEditor();
    info("the container is deleted");
  }

  /**
   * Edit an application with changes about title, width and height
   *
   * @param oldTitle String
   * @param newTitle String
   * @param width String
   * @param height String
   */
  public void editApplication(String oldTitle, String newTitle, String width, String height) {
    // TODO Auto-generated method stub

    info("Edit application");
    evt.mouseOver(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}", oldTitle), true);
    evt.click(ELEMENT_APPLICATION_EDIT_ICON.replace("${name}", oldTitle));
    evt.click(ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_TAB);
    if (!newTitle.isEmpty())
      evt.type(ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_TITLE, newTitle, true);
    if (!width.isEmpty())
      evt.type(ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_WIDTH, width, true);
    if (!height.isEmpty())
      evt.type(ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_HEIGHT, height, true);

  }

  /**
   * Save and close application popup after finishing editing Save and close page
   * editor
   */
  public void saveChangesApplication() {
    info("Save all changes of an application");
    evt.click(ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_SAVE);

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
   * Move an application to new place
   *
   * @param titleSource is the title of applicattion source that will be moved to
   *          new place
   * @param titleTarget is the title of application target that application source
   *          will be followed
   * @param heightTarget is the height of application target
   */
  public void moveApplication(String titleSource, String titleTarget, int heightTarget) {
    info("Move an application to new place");
    evt.click(ELEMENT_APPLICATION_TAB);
    if (!titleSource.isEmpty()) {
      info("titleSource:" + titleSource);
      info("titleTarget:" + titleTarget);
      evt.mouseOver(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}", titleSource), true);

    }
    WebElement elSource = evt.waitForAndGetElement(ELEMENT_APPLICATION_HOLDER_MOVE.replace("${name}", titleSource), 2000, 1);
    WebElement elTarget = evt.waitForAndGetElement(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}", titleTarget), 2000, 0);
    if (elTarget == null)
      elTarget = evt.waitForAndGetElement(ELEMENT_DROP_SOURCE_HAS_LAYOUT, 2000, 1);

    info("Get the size of target");
    Dimension size = elTarget.getSize();

    Actions builder = new Actions(testBase.getExoWebDriver().getWebDriver());
    info("Hold the source:");
    builder.clickAndHold(elSource).build().perform();
    info("Move the mouse to the middle of the portlet");
    Action actionMove = builder.moveToElement(elTarget).build();
    actionMove.perform();
    info("Move the mouse under the portlet");
    Action actionMove1 = builder.moveByOffset(-(size.width / 2), -(size.height / 2) + heightTarget).build();
    actionMove1.perform();
    info("Drop the source");

    Action actiondrop = builder.release().build();
    actiondrop.perform();

    saveChangesPageEditor();
    info("the application is moved succefully");
  }

  /**
   * Delete an application
   *
   * @param name String
   */
  public void deleteApplication(String name) {
    info("Delete the application");
    evt.click(ELEMENT_APPLICATION_TAB);
    if (!name.isEmpty()) {
      evt.mouseOver(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}", name), true);

      evt.click(ELEMENT_APPLICATION_DELETE_ICON.replace("${name}", name));
      magAlert.acceptAlert();

      evt.waitForElementNotPresent(ELEMENT_APPLICATION_DELETE_ICON.replace("${name}", name));
    }
    saveChangesPageEditor();
    info("the container is deleted");
  }

  /**
   * Change to Switch view mode
   *
   * @param verify boolean
   */
  public void switchViewMode(boolean... verify) {
    info("Click on Switch view mode button");
    evt.click(ELEMENT_SWITCH_VIEW_MODE);
    if (verify.length > 0)
      evt.waitForAndGetElement(ELEMENT_SWITCH_VIEW_MODE_NAME_APPLICATION_CLASS, 2000, 0);
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
        // evt.click(ELEMENT_VIEW_PROPERTIES_ADD_PERMISSION_BTN);
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
      // evt.click(ELEMENT_ADD_PERMISSION_SELECTOR_POPUP_GROUP.replace("${group}",
      // groupSelect), 0, true);
      $(byXpath("(//*[@title='${groupSelect}'])[2]".replace("${groupSelect}",groupSelect))).waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
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
    // evt.click(ELEMENT_ADD_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP.replace("${member}",
    // memberShip), 0, true);
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

  /**
   * Reset default values of Page's properties after changed
   *
   * @param title String
   * @param groupPath String
   * @param editPermission String
   * @param isShowMaxWindow boolean
   */
  public void resetValuesProperties(String title, String groupPath, String editPermission, boolean... isShowMaxWindow) {
    if (!title.isEmpty()) {
      info("Reset old name");
      evt.type(ELEMENT_VIEW_PROPERTIES_TITLE, title, true);
    }
    if (!groupPath.isEmpty()) {
      info("remove a group");
      removeGroup(groupPath.toLowerCase());
    }
    if (!editPermission.isEmpty()) {
      info("Remove a meberships");
      deleteEditPermission();
    }
    if (isShowMaxWindow.length > 0) {
      info("UnCheck on show Max window checkbox");
      evt.uncheck(ELEMENT_VIEW_PROPERTIES_SHOW_MAX_WINDOW, 2);
    }
    saveChangeProperties();
  }

  /**
   * Remove a group permission
   *
   * @param group String
   */
  public void removeGroup(String group) {
    info("Click on Delete button of the group:" + group);
    evt.click(ELEMENT_VIEW_PROPERTIES_GROUP_REMOVE_BTN.replace("${group}", group));
    magAlert.acceptAlert();
    info("The group is removed");
    evt.waitForElementNotPresent(ELEMENT_VIEW_PROPERTIES_GROUP_REMOVE_BTN.replace("${group}", group));
  }

  /**
   * Delete edit permission of a page
   */
  public void deleteEditPermission() {
    info("Click on Delete Permission");
    evt.click(ELEMENT_VIEW_PROPERTIES_DELETE_EDIT_PERMISSION_BTN);

  }

  /**
   * function: Edit view properties when edit layout
   *
   * @param newtitle new Name of page you want to edit
   * @param groupId Group Id when select permission
   * @param membership membership when select permission
   */
  public void editViewProperties(String newtitle, String groupId, String membership) {
    evt.waitForAndGetElement(ELEMENT_VIEW_PAGE_PROPERTIES);
    evt.click(ELEMENT_VIEW_PAGE_PROPERTIES);
    info("Edit properties of page");
    if (newtitle.length() > 0)
      evt.type(ELEMENT_VIEWPAGE_PAGETITLE, newtitle, true);
    evt.click(ELEMENT_PERMISSION_SETTING_TAB);
    evt.click(ELEMENT_EDIT_PERMISSION_SETTING);
    setEditPermissions(groupId, membership);
  }

  /**
   * Edit permission when view properties
   *
   * @param groupId String
   * @param membership String
   * @param isAccess boolean
   * @param isEdit boolean
   * @param isMoveApp boolean
   * @param isMoveCon boolean
   */
  public void editPermInViewProperties(String groupId,
                                       String membership,
                                       boolean isAccess,
                                       boolean isEdit,
                                       boolean isMoveApp,
                                       boolean isMoveCon) {
    info("edit permission in view properties");
    evt.waitForAndGetElement(ELEMENT_VIEW_PAGE_PROPERTIES);
    evt.click(ELEMENT_VIEW_PAGE_PROPERTIES, 0, true);
    evt.click(ELEMENT_PERMISSION_SETTING_TAB);
    if (isAccess) {
      evt.check(ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX, 2);
    } else {
      evt.check(ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX, 2);
      evt.uncheck(ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX, 2);
      setAccessPermissions(groupId, membership);
    }
    evt.click(ELEMENT_EDIT_PERMISSION_SETTING);
    if (isEdit) {

    } else {
      setEditPermissions(groupId, membership);
    }
    evt.click(ELEMENT_MOVE_APPS_PERMISSION_SETTING, 0, true);
    if (isMoveApp) {

    } else {
      setMoveAppsPermissions(groupId, membership);
    }
    evt.click(ELEMENT_MOVE_CONTAINERS_PERMISSION_SETTING, 0, true);
    if (isMoveCon) {

    } else {
      setMoveContainersPermissions(groupId, membership);
    }
    evt.click(ELEMENT_SAVE_BTN);
    saveChangesPageEditor();
  }

  /**
   * edit permission in container permission
   *
   * @param cont String
   * @param groupId String
   * @param membership String
   * @param isAccess boolean
   * @param isMoveApp boolean
   * @param isMoveCon boolean
   */
  public void editPermInContainer(String cont,
                                  String groupId,
                                  String membership,
                                  boolean isAccess,
                                  boolean isMoveApp,
                                  boolean isMoveCon) {
    info("set permission for container");

    evt.click(ELEMENT_CONTAINER_TAB, 0, true);
    if (!cont.isEmpty())
      evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}", cont), true);
    else
      evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);
    evt.click(ELEMENT_EDIT_CONTAINER_ICON, 0, true);

    evt.click(ELEMENT_CONTAINER_PERMISSION_SETTING_TAB, 0, true);
    if (isAccess) {

    } else {
      evt.uncheck(ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX, 2);
      setAccessPermissions(groupId, membership);
    }
    evt.click(ELEMENT_SAVE_BTN_2, 0, true);
    saveChangesPageEditor();
  }

  /**
   * function: Edit permission when view properties
   *
   * @param groupId Group Id when select permission
   * @param membership membership when select permission
   */
  public void setupEditPermissions(String groupId, String membership) {
    String membershipToSelect = ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM.replace("${membership}", membership);
    String selectedMembership = ELEMENT_SELECTED_EDIT_PERMISSION_MEMBERSHIP.replace("${membership}", membership);

    info("--Setting edit permission to " + groupId + ", " + membership + "--");
    String[] groups = groupId.split("/");
    evt.click(ELEMENT_PERMISSION_SELECTOR_BUTTON);

    evt.waitForTextPresent("Permission Selector");
    for (String group : groups) {
      String groupToSelect = ELEMENT_SELECT_EDIT_GROUP_ITEM.replace("${group}", group);
      evt.click(groupToSelect);
    }
    evt.click(membershipToSelect);
    evt.waitForAndGetElement(selectedMembership, 3000, 1, 2);
    evt.click(ELEMENT_SAVE_BTN);
    evt.waitForElementNotPresent(ELEMENT_EDIT_PERMISSION_SETTING);
  }

  /**
   * function: Edit permission when view properties
   *
   * @param groupId Group Id when select permission
   * @param membership membership when select permission
   */
  public void setEditPermissions(String groupId, String membership) {
    String membershipToSelect = ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM.replace("${membership}", membership);
    String selectedMembership = ELEMENT_SELECTED_EDIT_PERMISSION_MEMBERSHIP.replace("${membership}", membership);

    info("--Setting edit permission to " + groupId + ", " + membership + "--");
    String[] groups = groupId.split("/");
    evt.click(ELEMENT_SELECT_PERMISSION_BUTTON);

    evt.waitForTextPresent("Permission Selector");
    for (String group : groups) {
      String groupToSelect = ELEMENT_SELECT_EDIT_GROUP_ITEM.replace("${group}", group);
      evt.click(groupToSelect);
    }
    evt.click(membershipToSelect);
    evt.waitForTextNotPresent("Permission Selector");
    evt.waitForAndGetElement(selectedMembership, testBase.getDefaultTimeout(), 1, 2);
    evt.click(ELEMENT_SAVE_BTN);
    evt.waitForElementNotPresent(ELEMENT_EDIT_PERMISSION_SETTING);
    saveChangesPageEditor();
  }

  /**
   * Set access permission
   *
   * @param groupId String
   * @param membership String
   */
  public void setAccessPermissions(String groupId, String membership) {
    String[] groups = groupId.split("/");
    evt.click(ELEMENT_EDIT_PORTLET_FORM_ADD_PERM_BTN, 0, true);
    for (String groupSelect : groups) {
      info("Select group:" + groupSelect);
      evt.click(ELEMENT_ADD_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP.replace("${member}", groupSelect), 0, true);
    }
    evt.click(ELEMENT_ADD_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP.replace("${member}", membership), 0, true);
  }

  /**
   * Set move apps permissions
   * @param groupId String
   * @param membership String
   */
  public void setMoveAppsPermissions(String groupId, String membership) {
    String[] groups = groupId.split("/");
    evt.uncheck(ELEMENT_PAGEEDITOR_MOVE_APPS_PUBLIC_CHECKBOX, 2);
    evt.click(ELEMENT_EDIT_PORTLET_FORM_ADD_PERM_BTN, 0, true);
    for (String groupSelect : groups) {
      info("Select group:" + groupSelect);
      evt.click(ELEMENT_EDIT_PERMISSION_MOVE_APPS_SELECT.replace("${group}", groupSelect), 0, true);
    }
    evt.click(ELEMENT_EDIT_PERMISSION_MOVE_APPS_SELECT.replace("${group}", membership), 0, true);
  }

  /**
   * Set move apps permissions
   * @param groupId String
   * @param membership String
   */
  public void setMoveContainersPermissions(String groupId, String membership) {
    String[] groups = groupId.split("/");
    evt.uncheck(ELEMENT_PAGEEDITOR_MOVE_CONTAINERS_PUBLIC_CHECKBOX, 2);
    evt.click(ELEMENT_EDIT_PORTLET_FORM_ADD_PERM_BTN, 0, true);
    for (String groupSelect : groups) {
      info("Select group:" + groupSelect);
      evt.click(ELEMENT_EDIT_PERMISSION_MOVE_CONTAINERS_SELECT.replace("${group}", groupSelect), 0, true);
    }
    evt.click(ELEMENT_EDIT_PERMISSION_MOVE_CONTAINERS_SELECT.replace("${group}", membership), 0, true);
  }

  /**
   * Select access permission
   * @param group String
   * @param member String
   */
  public void selectAccessPerm(String group, String member) {
    info("select group and membership");
    evt.click(ELEMENT_EDIT_PORTLET_FORM_ADD_PERM_BTN, 0, true);
    if (group.length() > 0 && member.length() > 0) {
      selectGroup(group);
      selectMemberShip(member);
    } else {
      evt.check(ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX, 2);
    }

  }

  /**
   * Verify permission drag drop application
   *
   * @param tabName String
   * @param nameApp String
   * @param appLocator Object
   * @param layoutLocator Object
   * @param isEnable boolean
   */
  public void verifyDragDropAppPerm(String tabName, String nameApp, Object appLocator, Object layoutLocator, boolean isEnable) {
    info("verify drag drop application");
    evt.click(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", tabName), 0, true);
    evt.dragAndDropToObject(appLocator, layoutLocator);
    if (isEnable) {
      evt.waitForAndGetElement(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}", nameApp), 3000, 0);
    } else {
      evt.waitForElementNotPresent(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}", nameApp), 3000, 0);
    }
  }

  /**
   * Verify permission drag drop container
   * @param numRow String
   * @param isVerify boolean
   */
  public void verifyDragDropConPerm(String numRow, boolean isVerify) {
    info("verify drag drop container");
    evt.click(ELEMENT_CONTAINER_TAB);
    info("Add new container: " + numRow);
    try {
      evt.click(ELEMENT_CONTAINER_TAB);
    } catch (org.openqa.selenium.UnhandledAlertException e) {
      magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock", 40000);
      testBase.clearCache();
    }
    evt.click(By.linkText("Rows Layout"));
    evt.dragAndDropToObject(By.id(numRow), By.className("UIRowContainer"));

    evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);
    if (isVerify) {
      evt.waitForAndGetElement(ELEMENT_CONTAINER_TITLE.replace("${title}", "Container"));
    } else {
      evt.waitForElementNotPresent(ELEMENT_CONTAINER_TITLE.replace("${title}", "Container"));
    }
  }

  /**
   * Add an application to a layout with an user not having permission
   *@param tabName String
   * @param nameApp String
   * @param appLocator Object
   * @param layoutLocator Object
   */
  public void addAppWithoutPermission(String tabName, String nameApp, Object appLocator, Object layoutLocator) {
    info("Add an application to the layout");
    evt.click(ELEMENT_APPLICATION_TAB);

    if (!tabName.isEmpty())
      evt.click(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", tabName));
    evt.dragAndDropToObject(appLocator, layoutLocator);
    info("Verify that the application is NOT shown in the layout");
    evt.waitForElementNotPresent(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}", nameApp), 3000, 1);

    saveChangesPageEditor();
  }

  /**
   * Delete a contain in the layout by an user have no permission
   * @param id string
   */
  public void deleteContainerWithoutPermission(String id) {
    info("Delete the container");
    try {
      evt.click(ELEMENT_CONTAINER_TAB);

    } catch (org.openqa.selenium.UnhandledAlertException e) {
      magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock", 40000);
      testBase.clearCache();
    }
    if (!id.isEmpty()) {
      evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID.replace("${id}", id), true, 1);

      evt.waitForElementNotPresent(ELEMENT_DELETE_CONTAINER_ICON_BY_ID.replace("${id}", id), 1);
    }
    saveChangesPageEditor();
  }

  /**
   * Select a container and open Edit form HEAD-HEAD
   *
   * @param containerLocation String
   * @param containerEditLocation - FQA-2759:PLF43 - Write
   *          High Fnc/PLF/Restricted Page Editor/Site Permissions - HEAD
   */
  public void openContainerEditForm(String containerLocation, String containerEditLocation) {
    info("Select Container tab");
    evt.click(ELEMENT_CONTAINER_TAB);
    evt.mouseOver(containerLocation, true);
    evt.waitForAndGetElement(containerEditLocation);
    evt.click(containerEditLocation);
    evt.waitForAndGetElement(ELEMENT_EDITING_CONTAINER_POPUP);
  }

  /**
   * Delete a container without permission
   *
   * @param containerId Object
   * @param containerDeleteId Delete a container without permission
     */
  public void deleteContainerWithoutPermission(String containerId, String containerDeleteId) {
    info("Delete the container");
    try {
      evt.click(ELEMENT_CONTAINER_TAB);

    } catch (org.openqa.selenium.UnhandledAlertException e) {
      magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock", 40000);
      testBase.clearCache();
    }
    if (!containerId.isEmpty()) {
      evt.mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID.replace("${id}", containerId), true, 1);

      evt.waitForElementNotPresent(ELEMENT_DELETE_CONTAINER_ICON_BY_ID.replace("${id}", containerDeleteId), 1);
    }
    saveChangesPageEditor();
  }

}
