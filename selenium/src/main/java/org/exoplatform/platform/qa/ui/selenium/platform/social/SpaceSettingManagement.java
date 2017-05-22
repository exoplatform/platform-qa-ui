package org.exoplatform.platform.qa.ui.selenium.platform.social;

import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class SpaceSettingManagement {

  private final TestBase       testBase;

  public ManageAlert           alert;


  public Button                button;

  public PlatformPermission    plfPerm;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param testBase
   */
  public SpaceSettingManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.plfPerm = new PlatformPermission(testBase);
  }

  /**
   * function: Search user
   * 
   * @param user (Can be: User name, Last name, First name or Email of the user
   *          you want to search)
   * @param searchOption (Can be: User name, Last name, First name or Email
   *          option corresponding with information you input in "Search")
   */
  public void searchUser(String user, String searchOption) {
    info("--Search user " + user + "--");
    if (evt.isTextPresent("Search")) {
      evt.type(ELEMENT_INPUT_SEARCH_USER_NAME, user, true);
      evt.select(ELEMENT_SELECT_SEARCH_OPTION, searchOption);
    }
    evt.click(ELEMENT_SEARCH_ICON_USERS_MANAGEMENT);
    evt.waitForTextPresent(user);
  }

  /**
   * Open member tab
   */
  public void goToMemberTab() {
    info("Open members tab");
    evt.click(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB);
    evt.waitForAndGetElement(ELEMENT_SPACE_MEMBERS_SELECT_USER, 2000, 1);
  }

  /**
   * Invite a user in the space
   * 
   * @param userName
   * @param verify is true if want to verify user in invited table. False if
   *          don't want.
   * @param fullName
   */
  public void inviteUser(String userName, boolean verify, String fullName) {
    goToMemberTab();
    info("Click on select user button");
    evt.click(ELEMENT_SPACE_MEMBERS_SELECT_USER, 2);
    info("--Search user " + testBase.getUser() + "--");
    evt.type(ELEMENT_SEARCH_INPUT_USER_NAME, userName, true);
    evt.click(ELEMENT_SEARCH_USERS_ICON, 2);
    Utils.pause(2000);
    info("Select a user");
    evt.click(ELEMENT_SPACE_SELECT_USER_IN_FORM.replace("{$name}", userName), 2);
    info("click on Add button");
    evt.click(ELEMENT_ADD, 2);
    info("click on Invite button");
    evt.click(ELEMENT_SPACE_MEMBERS_INVITE, 2);
    if (verify) {
      info("Verify that user is shown in invitation table");
      if (fullName != "" && fullName != null)
        evt.waitForAndGetElement(ELEMENT_SPACE_INVITED_USER_TABLE.replace("${user}", fullName), 2000, 1);
    }
  }

  /**
   * Change role of a user in the list if role's status is NO, this will change
   * to YES if role's status is YES, this will change to NO
   */
  public void changeRole(String user) {
    info("Open members tab");
    evt.click(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB);
    info("Click on change role button of manager column");
    evt.click(ELEMENT_SPACE_CHANGE_ROLE_USER_MEMBER.replace("${user}", user), 2);
    Utils.pause(2000);

  }

  /**
   * Remove a user in the invited list
   * 
   * @param user
   */
  public void removeUser(String user) {
    info("OPen members tab");
    evt.click(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB);
    info("Click on Delete button to remove user");
    evt.click(ELEMENT_SPACE_DELETE_USER_BTN.replace("${user}", user));
    evt.waitForElementNotPresent(ELEMENT_SPACE_DELETE_USER_BTN.replace("${user}", user));
  }

  /**
   * Remove a user in the member list
   * 
   * @param fullName
   */
  public void removeUserFromMemberlist(String fullName) {
    info("OPen members tab");
    evt.click(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB);
    info("Click on Delete button to remove user");
    evt.click(ELEMENT_SPACE_REMOVE_USER_BTN_MEMBER_TABLE.replace("${fullName}", fullName));
    evt.waitForElementNotPresent(ELEMENT_SPACE_REMOVE_USER_BTN_MEMBER_TABLE.replace("${fullName}", fullName), 2000, 1);
  }

  /**
   * Remove an application
   * 
   * @param app
   */
  public void removeApplication(String app) {
    info("Click on Remove icon");
    evt.click(ELEMENT_APPLICATION_TAB_APPLICATION_DELETE_BTN.replace("${app}", app));
    evt.waitForElementNotPresent(ELEMENT_APPLICATION_TAB_APPLICATION_LIST_CONTENT.replace("${app}", app));
    info("the application is removed");
  }

  /**
   * Accept a pending request to a space
   * 
   * @param user is fullName
   */
  public void acceptRequest(String user) {
    info("OPen members tab");
    evt.click(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB);
    info("Click on join button to remove user");
    evt.click(ELEMENT_SPACE_MEMBERS_TAB_VALIDATE_REQUEST_jOINT.replace("${user}", user));
    info("Verify that the member is shown in member list");
    evt.waitForAndGetElement(ELEMENT_SPACE_DELETE_USER_BTN.replace("${user}", user), 2000, 1);
  }

  /**
   * Decline a pending request to a space
   * 
   * @param user
   */
  public void declineRequest(String user) {
    info("OPen members tab");
    evt.click(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB);
    info("Click on join button to remove user");
    evt.click(ELEMENT_SPACE_MEMBERS_TAB_DECLINE_REQUEST_jOINT.replace("${user}", user));
    info("Verify that the member is shown in member list");
    evt.waitForElementNotPresent(ELEMENT_SPACE_DELETE_USER_BTN.replace("${user}", user), 2000, 1);
  }

  /**
   * Delete member of space
   * 
   * @param user
   */
  public void deleteMember(String user) {
    info("OPen members tab");
    evt.click(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB);
    info("Click on delete button to remove user");
    evt.click(ELEMENT_SPACE_DELETE_USER_BTN.replace("${user}", user));
    info("Verify that the member is not shown in member list");
    evt.waitForElementNotPresent(ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}", user));
  }

  /**
   * Delete an app from the top menu of space
   * 
   * @param app
   */
  public void deleteApplications(String app) {
    info("Open Application tab");
    evt.click(ELEMENT_SETTINGS_APP_TAB);
    info("Click on Delete button");
    evt.click(ELEMENT_DELETE_APP_FROM_TOPBAR.replace("{$application}", app));
  }

  /**
   * input name, des into setting tab
   * 
   * @param name name of space
   * @param des description of space
   */
  public void updateInfoSpace(String name, String des) {
    info("Input data to setting tab");
    if (name != null && name != "") {
      info("input a name");
      evt.type(ELEMENT_SPACE_NAME_INPUT, name, true);
    }
    if (des != null && des != "") {
      info("input a description");
      evt.type(ELEMENT_SPACE_DESCRIPTION_INPUT, des, true);
    }
  }

  /**
   * Save all changes for updating information of the space
   */
  public void saveInfoSpace() {
    info("Click on Save button");
    evt.click(ELEMENET_SPACE_UPDATE_SAVE_BTN);
    Utils.pause(2000);
  }

  /**
   * Open Navigation tab
   */
  public void goToNavigationTab() {
    info("Select Navigation tab");
    evt.waitForAndGetElement(ELEMENT_SPACE_SETTING_NAVIGATION_TAB, 3000, 0).click();
    info("The tab is opened succcessfully");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON);
    // waitForAndGetElement(ELEMENT_APPLICATION_TAB_ADD_APPLICATION_BTN,3000,0);
    Utils.pause(1000);
  }

  /**
   * Open Application tab
   */
  public void goToApplicationTab() {
    info("Select Application tab");
    evt.waitForAndGetElement(ELEMENT_SETTINGS_APP_TAB, 3000, 1);
    evt.click(ELEMENT_SETTINGS_APP_TAB);
    info("The tab is opened succcessfully");
    evt.waitForAndGetElement(ELEMENT_SPACE_APPLICATION_TAB_ADD_BTN, 3000, 1);
  }

  /**
   * Open Access and Edit tab
   */
  public void goToAccessEditTab() {
    info("Select Application tab");
    if (evt.waitForAndGetElement(ELEMENT_ACCESS_AND_EDIT_TAB, 3000, 0) != null)
      evt.click(ELEMENT_ACCESS_AND_EDIT_TAB);
    else
      evt.click(ELEMENT_ACCESS_AND_EDIT_TAB_OF_POPUP);
    info("The tab is opened succcessfully");
    evt.waitForAndGetElement(ELEMENT_ACCESS_HIDDEN_RADIO, 3000, 0);
  }

  /**
   * add a new simple node
   * 
   * @param name
   */
  public void addANodeSimple(String name) {
    info("Click on Add node button");
    // waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON,3000,0).click();
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON, testBase.getDefaultTimeout(), 1);
    evt.clickByJavascript(ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON, 2);
    info("The popup is shown");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_NODE_TITLE, 2000, 0);
    info("Input a new name for the node");
    evt.type(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_NAME, name, true);
    info("Save all changes");
    // waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE,2000,0).click();
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE, testBase.getDefaultTimeout(), 1);
    evt.clickByJavascript(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE, 2);
    info("Verify that the node is added successfully");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", name), 3000, 0);
    Utils.pause(1000);
  }

  /**
   * add a new simple child node
   * 
   * @param parentNode
   * @param childNode
   */
  public void addChildrenNodeSimple(String parentNode, String childNode) {
    info("Right click on parent node");
    evt.rightClickOnElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", parentNode));
    info("Click on add new node");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_ADD_NEW_NODE);
    evt.click(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_ADD_NEW_NODE);
    info("The popup is shown");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_NODE_TITLE, 2000, 0);
    info("Input a new name for the node");
    evt.type(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_NAME, childNode, true);
    info("Save all changes");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE, 2000, 0).click();
    info("Verify that the children node is added successfully under parent node");
    evt.click(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", parentNode));
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGAION_ADD_NODE_CHILDREN_UNDER_PARENT.replace("${childrenNode}", childNode)
                                                                                   .replace("${parentNode}", parentNode),
                             3000,
                             0);
    Utils.pause(1000);
  }

  /**
   * Add an application
   * 
   * @param category
   * @param application
   */
  public void addApplication(String category, String application) {
    info("Click on Add application button");
    evt.click(ELEMENT_APPLICATION_TAB_ADD_APPLICATION_BTN);
    info("the popup is shown");
    evt.waitForAndGetElement(ELEMENT_ADD_APPLICATION_POPUP_TITLE, 2000, 1);
    info("Select a category");
    if (!category.isEmpty())
      evt.click(ELEMENT_ADD_APPLICATION_POPUP_CATEGOGY.replace("${category}", category));
    if (!application.isEmpty())
      evt.click(ELEMENT_ADD_APPLICATION_POPUP_APPLICATION_ADD_BTN.replace("${app}", application));
    info("Close the popup after installed application");
    evt.click(ELEMENT_ADD_APPLICATION_POPUP_CLOSE_BTN);
    evt.waitForElementNotPresent(ELEMENT_ADD_APPLICATION_POPUP_TITLE);
  }

  /**
   * Edit a node with new label
   * 
   * @param nodeName
   * @param label
   */
  public void editANodeSimple(String nodeName, String label) {
    info("Right click on the node");
    evt.rightClickOnElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
    info("Select edit link");
    evt.click(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_EDIT);
    info("Input a new name for lable field");
    evt.type(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_LABEL, label, true);
    info("Save all changes");
    evt.click(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE);
    info("Verify that the node is edited successfully");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", label), 3000, 1);
  }

  /**
   * Set permissions for a space
   * 
   * @param arrayRight
   */
  public void setPermissionForSpace(String[] arrayRight) {
    for (String right : arrayRight) {
      info("Select a permission for space:" + right);
      // check(ELEMENT_ACCESS_PERMISSION_RADIO.replace("${right}", right),2);
      evt.clickByJavascript(ELEMENT_ACCESS_PERMISSION_RADIO.replace("${right}", right), 2);
    }
    info("Save all changes");
    if (evt.waitForAndGetElement(ELEMENT_ACCESS_PERMISSION_SAVE_BTN, 3000, 0) != null) {
      evt.click(ELEMENT_ACCESS_PERMISSION_SAVE_BTN);
      evt.click(ELEMENT_ACCESS_INFO_OK_BTN);
    } else
      evt.click(ELEMENT_ACCESS_AND_EDIT_TAB_OF_POPUP_CREATE_BTN);
    Utils.pause(2000);
  }

  /**
   * Set permissions for a space
   * 
   * @param arrayRight
   */
  public void setPermissionForSpaceFromPopup(String[] arrayRight) {
    for (String right : arrayRight) {
      info("Select a permission for space:" + right);
      evt.check(ELEMENT_ACCESS_PERMISSION_RADIO.replace("${right}", right), 2);
    }
  }

  /**
   * Delete a node
   * 
   * @param nodeName
   */
  public void deleteANode(String nodeName) {
    info("Right click on the node");
    // Actions actions = new Actions(driver);
    evt.rightClickOnElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
    /*
     * WebElement el =
     * waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace(
     * "${name}", nodeName), DEFAULT_TIMEOUT, 1);
     * actions.moveToElement(el).contextClick().perform();
     */
    info("Select delete link");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_DELETE, 2000, 0).click();
    alert.acceptAlert();
    info("Verify that the node is deleted successfully");
    evt.waitForElementNotPresent(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
  }

  /**
   * copy a node
   * 
   * @param nodeName
   */
  public void copyANode(String nodeName) {
    info("Copy node ");
    info("Right click on the node");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
    evt.rightClickOnElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
    info("Select copy link");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_COPY_NODE, 2000, 0).click();
    Utils.pause(1000);
  }

  /**
   * cut a node
   * 
   * @param nodeName
   */
  public void cutANode(String nodeName) {
    info("Copy node ");
    info("Right click on the node");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
    evt.rightClickOnElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
    info("Select copy link");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_CUT_NODE, 2000, 0).click();
    Utils.pause(1000);
  }

  /**
   * paste a node
   * 
   * @param nodeName
   */
  public void pasteANode(boolean canPaste, String nodeName) {
    info("paste node");
    info("Right click on the node");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
    evt.rightClickOnElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
    if (canPaste) {
      info("Select paste link");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_PASTE_NODE, 2000, 0).click();
    } else {
      info("can not find paste button");
      evt.waitForElementNotPresent(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_PASTE_NODE, 2000, 0);
    }
    Utils.pause(1000);
  }

  /**
   * Move up a node
   * 
   * @param nodeName
   */
  public void moveUpANode(boolean firstNode, String nodeName, String nodeAboveName, String nodePosition, String abovePosition) {
    info("move up a node ");
    if (firstNode) {
      info("Right click on the node");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
      evt.rightClickOnElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
      info("Select move up link");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_MOVE_UP, 2000, 0).click();
      info("There is nothing happen with the first node");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NODE_POSITION.replace("${nodeName}", nodeName).replace("${position}",
                                                                                                               nodePosition));
    } else {
      info("Check position before move up");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NODE_POSITION.replace("${nodeName}", nodeName).replace("${position}",
                                                                                                               nodePosition));
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NODE_POSITION.replace("${nodeName}", nodeAboveName)
                                                                     .replace("${position}", abovePosition));
      info("Right click on the node");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
      evt.rightClickOnElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
      info("Select move up link");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_MOVE_UP, 2000, 0).click();
      info("Check position after move up");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NODE_POSITION.replace("${nodeName}", nodeName).replace("${position}",
                                                                                                               abovePosition));
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NODE_POSITION.replace("${nodeName}", nodeAboveName)
                                                                     .replace("${position}", nodePosition));
    }
  }

  /**
   * Move down a node
   * 
   * @param nodeName
   */
  public void moveDownANode(boolean lastNode, String nodeName, String nodeUnderName, String nodePosition, String underPosition) {
    info("move up a node ");
    if (lastNode) {
      info("Right click on the node");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
      evt.rightClickOnElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
      info("Select move up link");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_MOVE_DOWN, 2000, 0).click();
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NODE_POSITION.replace("${nodeName}", nodeName).replace("${position}",
                                                                                                               nodePosition));
    } else {
      info("Check position before move down");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NODE_POSITION.replace("${nodeName}", nodeName).replace("${position}",
                                                                                                               nodePosition));
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NODE_POSITION.replace("${nodeName}", nodeUnderName)
                                                                     .replace("${position}", underPosition));
      info("Right click on the node");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
      evt.rightClickOnElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
      info("Select move up link");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_MOVE_DOWN, 2000, 0).click();
      info("Check position after move down");
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NODE_POSITION.replace("${nodeName}", nodeName).replace("${position}",
                                                                                                               underPosition));
      evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NODE_POSITION.replace("${nodeName}", nodeUnderName)
                                                                     .replace("${position}", nodePosition));
    }
  }

  /**
   * go to Edit Node'sPage
   * 
   * @param nodeName
   */
  public void goToEditNodePage(String nodeName) {
    Utils.pause(1000);
    info("Go to Edit Node's Page");
    info("Right click on the node");
    evt.rightClickOnElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName));
    info("Select edit node's page link");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_EDIT_NODE_PAGE, 2000, 0);
    evt.click(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_EDIT_NODE_PAGE);
    Utils.pause(2000);
  }

  /**
   * add node with page selector
   * 
   * @param nodeName
   * @param extendedLabelMode
   * @param languages
   * @param nodeLabel
   * @param pageTitle
   * @param pageTitleSearch
   * @param verifyPage
   * @param verifyNode
   */
  public void addANodeWithPageSelector(String nodeName,
                                       boolean extendedLabelMode,
                                       String languages,
                                       String nodeLabel,
                                       String pageName,
                                       String pageTitle,
                                       String pageTitleSearch,
                                       String type,
                                       boolean verifyPage,
                                       boolean verifyNode) {
    info("Click on Add node button");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON, 3000, 0).click();
    info("The popup is shown");
    evt.waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_NODE_TITLE, 2000, 0);
    evt.type(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_NAME, nodeName, true);
    if (extendedLabelMode) {
      evt.select(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_LANGUAGE, languages);
      Utils.pause(500);
    } else {
      evt.uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE);
    }
    evt.type(ELEMENT_INPUT_LABEL, nodeLabel, true);

    evt.click(ELEMENT_PAGE_SELECTOR_TAB);

    if (pageName != "" && pageTitle != "") {
      info("--Create new page");
      evt.type(ELEMENT_INPUT_PAGE_NAME, pageName, true);
      evt.type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
      evt.click(ELEMENT_CREATE_PAGE_LINK);
      if (verifyPage) {
        evt.waitForElementNotPresent(ELEMENT_CREATE_PAGE_LINK);
      } else {
        return;
      }
    } else {
      info("Select Page");
      Utils.pause(500);
      evt.click(ELEMENT_SEARCH_SELECTOR_PAGE_LINK);
      // @FIXME Dependency problem
      //naviManage.selectPage(pageTitleSearch);
    }
    info("Save to add node");
    Utils.pause(2000);
    evt.click(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE);
    Utils.pause(2000);
  }

  /**
   * Open Invite users from group tab
   */
  public void goToInviteUserFromGroupTab() {
    info("click on the Invite users from group tab");
    evt.click(ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_TAB);
    evt.waitForAndGetElement(ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX, 2000, 2);
    info("The tab is shown");
  }

  /**
   * Select a group
   * 
   * @param arrayGroupPath
   */
  public void selectGroup(String[] arrayGroupPath) {
    info("Select a group in the list");
    for (String group : arrayGroupPath) {
      info("Select a group:" + group);
      evt.click(ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_SELECT_GROUP.replace("${name}", group));
    }
    info("Select the group");
    evt.click(ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_SELECTED_LINK);
    evt.waitForAndGetElement(ELEMENT_SPACE_INVITE_USERS_FROM_GROU_SELECTED_GROUP_INFO, 2000, 1);
  }

  /**
   * Invite users from a group
   * 
   * @param arrayGroupPath
   */
  public void inviteGroup(String[] arrayGroupPath) {
    goToMemberTab();
    info("Click on select group button");
    evt.click(ELEMENT_SPACE_INVITED_GROUP_BTN);
    for (String group : arrayGroupPath) {
      info("Select a group:" + group);
      evt.click(ELEMENT_SPACE_INVITED_GROUP_NAME.replace("$name", group));
    }
    info("Select the group");
    evt.click(ELEMENT_SPACE_INVITED_SELECT_GROUP);
    info("click on Invite button");
    evt.click(ELEMENT_SPACE_MEMBERS_INVITE);
    Utils.pause(2000);
  }

  /**
   * Save all changes
   */
  public void saveChanges() {
    if (evt.waitForAndGetElement(ELEMENT_ACCESS_PERMISSION_SAVE_BTN, 3000, 0) != null) {
      info("Save all changes by click on Save button");
      evt.click(ELEMENT_ACCESS_PERMISSION_SAVE_BTN);
      evt.click(ELEMENT_ACCESS_INFO_OK_BTN);
    } else {
      info("Save all changes by click on Create button");
      evt.click(ELEMENT_ACCESS_AND_EDIT_TAB_OF_POPUP_CREATE_BTN);
    }
    Utils.pause(2000);
  }

  /**
   * Verify member of space
   * 
   * @param fullname
   * @param isDisplay
   */
  public void verifyMember(String fullname, boolean isDisplay) {
    info("OPen members tab");
    evt.click(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB, 0, true);
    if (isDisplay) {
      evt.waitForAndGetElement(ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}", fullname));
    } else {
      evt.waitForElementNotPresent(ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}", fullname));
    }
  }

  /**
   * Verify permission of member
   * 
   * @param fullname
   * @param isDisplay
   */
  public void verifyPermOfMember(String fullname, boolean isDisplay) {
    info("verify permission of member space");
    if (isDisplay) {
      evt.waitForElementNotPresent(ELEMENT_SPACE_CHANGE_ROLE_USER_MEMBER_DISABLE.replace("${user}", fullname));
      evt.waitForAndGetElement(ELEMENT_SPACE_REMOVE_USER_BTN_MEMBER_TABLE.replace("${fullName}", fullname));
    } else {
      evt.waitForAndGetElement(ELEMENT_SPACE_CHANGE_ROLE_USER_MEMBER_DISABLE.replace("${user}", fullname));
      evt.waitForElementNotPresent(ELEMENT_SPACE_REMOVE_USER_BTN_MEMBER_TABLE.replace("${fullName}", fullname));
    }
  }

  /**
   * Check user in Invited Space
   * 
   * @param user
   * @param space
   * @param isPresent
   */
  public void checkUserInInvitedSpace(String user, String space, boolean isPresent) {
    goToMemberTab();
    if (isPresent)
      evt.waitForAndGetElement(ELEMENT_SPACE_INVITED_USER_TABLE.replace("${user}", user));
    else
      evt.waitForElementNotPresent(ELEMENT_SPACE_INVITED_USER_TABLE.replace("${user}", user));
  }

  /**
   * Check user in Member Space
   * 
   * @param user
   * @param space
   * @param isPresent
   */
  public void checkUserInMemberSpace(String user, String space, boolean isPresent) {
    goToMemberTab();
    if (isPresent)
      evt.waitForAndGetElement(ELEMENT_USER_IN_MEMBER_TABLE.replace("${fullName}", user));
    else
      evt.waitForElementNotPresent(ELEMENT_USER_IN_MEMBER_TABLE.replace("${fullName}", user));
  }

  /**
   * Check user selector of Invite Space
   * 
   * @param user
   * @param isPresent
   */
  public void checkUserSelectorInviteSpace(String user, boolean isPresent) {
    goToMemberTab();
    evt.click(ELEMENT_SPACE_MEMBERS_SELECT_USER, 0, true);
    plfPerm.checkUserSelector(user, isPresent);
  }

  /**
   * Check search user of Invite Space
   * 
   * @param user
   * @param isPresent
   */
  public void checkSearchUserInviteSpace(String user, boolean isPresent) {
    goToMemberTab();
    evt.click(ELEMENT_SPACE_MEMBERS_SELECT_USER, 0, true);
    plfPerm.searchUser(user, isPresent);
  }
}
