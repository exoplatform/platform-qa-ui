package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ApplicationRegistry {
  private final TestBase       testBase;

  public NavigationToolbar     navTool;

  public PageEditor            pagEditor;

  public HomePagePlatform      hp;

  public ManageAlert           alert;

  public PortalManagePages     pagMag;

  private ElementEventTestBase evt;

  public ApplicationRegistry(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.navTool = new NavigationToolbar(testBase);
    this.pagEditor = new PageEditor(testBase);
    this.hp = new HomePagePlatform(testBase);
    this.alert = new ManageAlert(testBase);
    this.pagMag = new PortalManagePages(testBase);
  }

  /**
   * Go to Manage Application Page
   */
  public void goToManageApplication() {
    info("--Go to Manage Application Page--");
    evt.waitForAndGetElement(ELEMENT_MANAGE_APPLICATION_BUTTON);
    evt.click(ELEMENT_MANAGE_APPLICATION_BUTTON);
  }

  /**
   * Show import application
   *
   * @param isShow
   */
  public void showImportApplication(Boolean isShow) {
    info("Show import application");
    if (isShow != null) {
      if (isShow) {
        evt.check(ELEMENT_SHOW_IMPORT_APPLICATION, 2);
      } else {
        evt.uncheck(ELEMENT_SHOW_IMPORT_APPLICATION, 2);
      }
    }
  }

  /**
   * Show import application button
   */
  public void displayImportApplicaions() {
    info("Show all import application");
    navTool.goToApplication();
    navTool.goToEditLayout();
    pagEditor.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
    showImportApplication(true);
    evt.click(ELEMENT_EDIT_PORTLET_FORM_SAVE_BUTTON);
    evt.click(ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON);
    pagEditor.finishEditLayout();
    info("Verify that import all application is shown");
    evt.waitForAndGetElement(ELEMENT_IMPORT_ALL_APPLICATION, 3000, 0);
  }

  /**
   * Hide import application button
   */
  public void HideShowImportApplicaion() {
    info("Show all import application");
    /*
     * if ("iexplorer".equals(browser)){ navTool.goToPotalPages();
     * pagMag.editPage("registry","group"); } else{
     */
    navTool.goToApplication();
    navTool.goToEditLayout();
    // }
    pagEditor.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
    showImportApplication(false);
    evt.click(ELEMENT_EDIT_PORTLET_FORM_SAVE_BUTTON);
    evt.click(ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON);
    pagEditor.finishEditLayout();
    info("Verify that import all application is hidden");
    evt.waitForElementNotPresent(ELEMENT_IMPORT_ALL_APPLICATION, 3000, 0);
  }

  /**
   * Import all applications
   */
  public void importAllApplications() {
    info("click on the import Applications button");
    evt.click(ELEMENT_IMPORT_ALL_APPLICATION);
    alert.acceptAlert();
    Utils.pause(2000);
    info("All applications are imported");
  }

  /**
   * Open Gadget page
   */
  public void goToGadgetPage() {
    info("Show Gadget page");
    Utils.pause(3000);
    evt.clickByJavascript(ELEMENT_APPLICATION_GADGETBTN);
    Utils.pause(2000);
  }

  /**
   * Add a application to a category for Portlet type
   *
   * @param category
   * @param nameApp
   */
  public void addApplicationToCategory(String category, String nameApp, String des) {
    info("Click on Add button");
    evt.waitForAndGetElement(ELEMENT_LEFT_PANEL_ADD_APPLICATION_BTN.replace("${category}", category));
    evt.click(ELEMENT_LEFT_PANEL_ADD_APPLICATION_BTN.replace("${category}", category));
    info("Verify that right panel is shown");
    evt.waitForAndGetElement(ELEMENT_RIGHT_PANEL_ADD_APPLICATION_DISPLAY_FILED);
    info("Type the name for the application");
    evt.type(ELEMENT_RIGHT_PANEL_ADD_APPLICATION_DISPLAY_FILED, nameApp, true);
    info("Des:" + des);
    while (evt.waitForAndGetElement(ELEMENT_RIGHT_PANEL_ADD_APPLICATION_NAME.replace("${des}", des).replace("${name}", nameApp),
                                    5000,
                                    0) == null) {

      if (evt.waitForAndGetElement(ELEMENT_PAGING_CONTROL_NEXT_PAGE_ENABLED, 2000, 0) != null)
        evt.click(ELEMENT_PAGING_CONTROL_NEXT_PAGE_ENABLED);
      else
        assert false : "Not found application with the name:" + nameApp;

      if (evt.waitForAndGetElement(ELEMENT_RIGHT_PANEL_ADD_APPLICATION_NAME.replace("${des}", des).replace("${name}", nameApp),
                                   5000,
                                   0) != null)
        break;
    }

    evt.check(ELEMENT_RIGHT_PANEL_ADD_APPLICATION_RADIOBTN.replace("${des}", des).replace("${name}", nameApp), 2);
    info("Save all changes");
    evt.click(ELEMENT_RIGHT_PANEL_ADD_APPLICATION_SAVE_BTN);
    Utils.pause(2000);
    info("Verify that the app is added to correct category");
    evt.waitForAndGetElement(ELEMENT_LEFT_PANEL_APPLICATION_NAME.replace("${category}", category).replace("${application}",
                                                                                                          nameApp),
                             2000,
                             0);
  }

  /**
   * Delete an application of a category
   *
   * @param category
   * @param application
   */
  public void deleteApplication(String application) {
    info("click on Delete button");
    evt.click(ELEMENT_LEFT_PANEL_APPLICATION_DELETE_BTN.replace("${application}", application));
    alert.acceptAlert();
    evt.waitForElementNotPresent(ELEMENT_LEFT_PANEL_APPLICATION_DELETE_BTN.replace("${application}", application));
  }

  /**
   * Check all imported Applications is shown in the left list after click on
   * Import Application button
   *
   * @param categoryList
   * @param nameList
   */
  public void checkImportedApplications(ArrayList<String> categoryList, ArrayList<String> nameList) {
    info("Reading....from Category list");
    for (int i = 0; i < categoryList.size(); i++) {
      info("Category:" + categoryList.get(i));
      info("Application's name:" + nameList.get(i));
      // if the category is not Application category
      if (categoryList.get(i) != categoryList.get(0))
        // if the category is a new category
        if (categoryList.get(i) != categoryList.get(i - 1))
        evt.click(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB.replace("${category}", categoryList.get(i)));
      info("Verify that the imported application is shown in correct category");
      evt.waitForAndGetElement(ELEMENT_LEFT_PANEL_APPLICATION_NAME.replace("${category}", categoryList.get(i))
                                                                  .replace("${application}", nameList.get(i)));
      info("The application:" + nameList.get(i) + " in Category:" + categoryList.get(i) + " was imported");
    }
  }

  /**
   * Add a new simple Category with public permission
   *
   * @param categoryName
   * @param displayName
   * @param des
   */
  public void addACategory(String categoryName, String displayName, String des) {
    info("Click on Add Category button");
    Utils.pause(2000);
    evt.click(ELEMENT_APPLICATION_REGISTRY_ADD_CATEGORY_BTN);
    info("Input category name");
    evt.type(ELEMENT_ADD_CATEGORY_NAME, categoryName, true);
    if (!displayName.isEmpty()) {
      info("Input display name");
      evt.type(ELEMENT_ADD_CATEGORY_DISPLAY_NAME, displayName, true);
    }
    if (!des.isEmpty()) {
      info("Input description");
      evt.type(ELEMENT_ADD_CATEGORY_DESCRIPTION, des, true);
    }
    evt.click(ELEMENT_ADD_CATEGORY_PERMISSION_TAB);
    if (testBase.getBrowser().contains("iexplorer")) {
      evt.clickByJavascript(ELEMENT_ADD_CATEGORY_PERMISSION_PUBLIC_CHECKBOX, 2);
      evt.clickByJavascript(ELEMENT_ADD_CATEGORY_SAVE_BTN, 2);
    } else {
      evt.check(ELEMENT_ADD_CATEGORY_PERMISSION_PUBLIC_CHECKBOX, 2);
      evt.click(ELEMENT_ADD_CATEGORY_SAVE_BTN);
    }
    info("Verify that the new category is added successfully");
    evt.waitForAndGetElement(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB.replace("${category}", categoryName), 2000, 0);
  }

  /**
   * Edit a simple Category
   *
   * @param name
   * @param newDisplayName
   * @param newDes
   */
  public void editCategory(String name, String newDisplayName, String newDes, Object... opParams) {
    String group = (String) (opParams.length > 0 ? opParams[0] : "");
    String member = (String) (opParams.length > 1 ? opParams[1] : "");
    info("Click on Edit button");
    evt.click(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_EDIT_BTN.replace("${category}", name));
    if (!newDisplayName.isEmpty()) {
      info("Input display name");
      evt.type(ELEMENT_ADD_CATEGORY_DISPLAY_NAME, newDisplayName, true);
    }
    if (!newDes.isEmpty()) {
      info("Input description");
      evt.type(ELEMENT_ADD_CATEGORY_DESCRIPTION, newDes, true);
    }
    if (!group.isEmpty() && !member.isEmpty()) {
      info("input permission");
      evt.click(ELEMENT_ADD_CATEGORY_PERMISSION_TAB);
      evt.uncheck(ELEMENT_ADD_CATEGORY_PERMISSION_PUBLIC_CHECKBOX, 2);
      evt.click(ELEMENT_ADD_CATEGORY_ADD_PERMISSION_BTN);
      selectGroupMembership(group, member);
    }
    evt.click(ELEMENT_ADD_CATEGORY_SAVE_BTN);
    Utils.pause(2000);
    info("Verify that the new category is edit successfully");
    evt.waitForAndGetElement(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB.replace("${category}", newDisplayName), 2000, 0);
  }

  /**
   * Delete a category
   *
   * @param nameCategory
   */
  public void deleteCategory(String nameCategory) {
    info("Click on Delete button");
    evt.click(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_DELETE_BTN.replace("${category}", nameCategory));
    alert.acceptAlert();
    info("Verify that the category is deleted");
    evt.waitForElementNotPresent(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_DELETE_BTN.replace("${category}", nameCategory),
                                 2000,
                                 0);
    Utils.pause(3000);
  }

  /**
   * Select a portlet on the left panel
   *
   * @param category
   * @param displayName
   */
  public void selectAPortlet(String category, String displayName, boolean isOpenTab) {
    info("Cick on the category tab");
    if (isOpenTab == true)
      evt.click(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB.replace("${category}", category));
    info("Select an application");
    evt.click(ELEMENT_LEFT_PANEL_APPLICATION_NAME.replace("${category}", category).replace("${application}", displayName));
    Utils.pause(2000);
  }

  /**
   * Checking a viewing detail of a portlet
   *
   * @param displayName
   * @param appName
   * @param des
   */
  public void viewDetailPortlet(String displayName, String appName, String des) {
    info("Check Bread crumb");
    evt.waitForAndGetElement(ELEMENT_DETAIL_PORTLET_BREADCRUMB.replace("${disName}", displayName), 2000, 0);
    info("Check display name, Application name and description");
    evt.waitForAndGetElement(ELEMENT_DETAIL_PORTLET_DISPLAY_NAME.replace("${disName}", displayName), 2000, 0);
    info("Check Application name");
    evt.waitForAndGetElement(ELEMENT_DETAIL_PORTLET_APPLICATION_NAME.replace("${appName}", appName), 2000, 0);
    info("Check description");
    evt.waitForAndGetElement(ELEMENT_DETAIL_PORTLET_DESCRIPTION.replace("${des}", des), 2000, 0);
    info("Check title of Permission table");
    evt.waitForAndGetElement(ELEMENT_PERMISSION_FORM, 2000, 0);
  }

  /**
   * Select group membership
   *
   * @param groupPath path group: (Ex: Organization/Employees)
   * @param membership membership: (Ex: author)
   */
  public void selectGroupMembership(String groupPath, String membership) {
    String[] temp;
    temp = groupPath.split("/");
    for (int i = 0; i < temp.length; i++) {
      evt.click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
    }
    evt.click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership));
  }

  /**
   * Edit portlet permission
   *
   * @param category
   * @param portlet
   */
  public void editPortletPermission(String category, String portlet, String group, String member) {
    selectAPortlet(category, portlet, false);
    info("Input permission");
    evt.click(ELEMENT_ADD_CATEGORY_ADD_PERMISSION_BTN);
    selectGroupMembership(group, member);
    Utils.pause(2000);
    assert testBase.isTextPresent(group) || testBase.isTextPresent(group.toLowerCase());
  }

  /**
   * Delete permission
   *
   * @param group
   */
  public void deletePortletPermission(String group) {
    info("Delete permission of group");
    evt.click(ELEMENT_EDIT_PORTLET_DELETE_PERMISSION_ICON.replace("$group", group), 0, true);
    alert.acceptAlert();
    Utils.pause(1000);
  }
}
