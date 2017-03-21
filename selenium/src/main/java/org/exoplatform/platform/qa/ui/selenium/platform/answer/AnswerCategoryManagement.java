package org.exoplatform.platform.qa.ui.selenium.platform.answer;

import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformPermissionLocator.ELEMENT_ADD_USERS_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class AnswerCategoryManagement {
  private final TestBase       testBase;

  public AnswerHomePage        aHome;

  public QuestionManagement    qMang;

  public ManageAlert           alert;

  public Button                button;

  public PlatformPermission    plfPerm;

  private ElementEventTestBase evt;

  public AnswerCategoryManagement(TestBase testBase) {
    this.testBase = testBase;
    this.aHome = new AnswerHomePage(testBase);
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.qMang = new QuestionManagement(testBase);
    this.plfPerm = new PlatformPermission(testBase);
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Execute action of category from action bar: EDIT, ADD, EXPORT, IMPORT
   * 
   * @param action action that needs to be done
   */
  public void goToActionOfCategoryFromActionBar(actionCategoryOption action) {
    info("Select action from menu");
    Utils.pause(2000);
    evt.waitForAndGetElement(ELEMENT_CATEGORY_BUTTON, testBase.getDefaultTimeout(), 1);
    evt.click(ELEMENT_CATEGORY_BUTTON);
    switch (action) {
    case EDIT:
      info("Edit category");
      evt.waitForAndGetElement(ELEMENT_CATEGORY_EDIT_BUTTON, testBase.getDefaultTimeout(), 1);
      evt.click(ELEMENT_CATEGORY_EDIT_BUTTON);
      evt.waitForAndGetElement(ELEMENT_CATEGORY_EDIT_FORM);
      break;
    case ADD:
      info("ADD category");
      evt.waitForAndGetElement(ELEMENT_CATEGORY_ADD_BUTTON, testBase.getDefaultTimeout(), 1);
      evt.click(ELEMENT_CATEGORY_ADD_BUTTON);
      evt.waitForAndGetElement(ELEMENT_CATEGORY_ADD_FORM);
      break;
    case EXPORT:
      info("EXPORT category");
      evt.waitForAndGetElement(ELEMENT_CATEGORY_EXPORT_BUTTON, testBase.getDefaultTimeout(), 1);
      evt.click(ELEMENT_CATEGORY_EXPORT_BUTTON);
      evt.waitForAndGetElement(ELEMENT_CATEGORY_EXPORT_FORM);
      break;
    case IMPORT:
      info("IMPORT category");
      evt.waitForAndGetElement(ELEMENT_CATEGORY_IMPORT_BUTTON, testBase.getDefaultTimeout(), 1);
      evt.click(ELEMENT_CATEGORY_IMPORT_BUTTON);
      evt.waitForAndGetElement(ELEMENT_CATEGORY_IMPORT_FORM);
      break;
    case DELETE:
      info("DELETE category");
      evt.waitForAndGetElement(ELEMENT_CATEGORY_DELETE_BUTTON, testBase.getDefaultTimeout(), 1);
      evt.click(ELEMENT_CATEGORY_DELETE_BUTTON);
      evt.waitForAndGetElement(ELEMENT_CATEGORY_DELETE_CONFIRM_POPUP);
      break;
    default:
      info("Do nothing");
      break;
    }
  }

  /**
   * Execute action of category from right click: EDIT, ADD, EXPORT, IMPORT,
   * DELETE, MOVE, WATCH, RSS, SUBMITQUESTION
   * 
   * @param cat name of category
   * @param action action that needs to be done
   */
  public void goToActionOfCategoryFromRightClick(String cat, actionCategoryOption action) {
    info("Select action from menu");
    evt.rightClickOnElement(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
    switch (action) {
    case EDIT:
      info("Edit category");
      evt.click(ELEMENT_CATEGORY_RIGHT_EDIT_BUTTON);
      evt.waitForAndGetElement(ELEMENT_CATEGORY_EDIT_FORM);
      break;
    case ADD:
      info("ADD category");
      evt.click(ELEMENT_CATEGORY_RIGHT_ADD_BUTTON);
      evt.waitForAndGetElement(ELEMENT_CATEGORY_ADD_FORM);
      break;
    case EXPORT:
      info("EXPORT category");
      evt.click(ELEMENT_CATEGORY_RIGHT_EXPORT_BUTTON);
      evt.waitForAndGetElement(ELEMENT_CATEGORY_EXPORT_FORM);
      break;
    case IMPORT:
      info("IMPORT category");
      evt.click(ELEMENT_CATEGORY_RIGHT_IMPORT_BUTTON);
      evt.waitForAndGetElement(ELEMENT_CATEGORY_IMPORT_FORM);
      break;
    case DELETE:
      info("DELETE category");
      evt.click(ELEMENT_CATEGORY_RIGHT_DELETE_BUTTON);
      evt.waitForAndGetElement(ELEMENT_CATEGORY_DELETE_CONFIRM_POPUP);
      break;
    case MOVE:
      info("MOVE category");
      evt.click(ELEMENT_CATEGORY_RIGHT_MOVE_BUTTON);
      evt.waitForAndGetElement(ELEMENT_CATEGORY_MOVE_FORM);
      break;
    case WATCH:
      info("WATCH category");
      evt.click(ELEMENT_CATEGORY_RIGHT_WATCH_BUTTON);
      evt.click(ELEMENT_CATEGORY_WATCH_OK_BUTTON);
      break;
    case UNWATCH:
      info("UNWATCH category");
      evt.click(ELEMENT_CATEGORY_RIGHT_UNWATCH_BUTTON);
      break;
    case SUBMITQUESTION:
      info("SUBMITQUESTION category");
      evt.click(ELEMENT_CATEGORY_RIGHT_SUBMIT_QUESTION_BUTTON);
      evt.waitForAndGetElement(ELEMENT_SUBMIT_QUESTION_FORM);
      break;
    case RSS:
      info("RSS category");
      evt.click(ELEMENT_CATEGORY_RIGHT_RSS_BUTTON);
      break;
    default:
      info("Do nothing");
      break;
    }
  }

  /**
   * Input data to setting tab of category form
   * 
   * @param cat category name
   * @param order order of category
   * @param des description of category
   * @param modQues true: check Moderate New Questions false: uncheck Moderate
   *          New Questions
   * @param viewAuthor true: check View Question Authors false: uncheck View
   *          Question Authors
   * @param modAnswer true: check Moderate Answers false: uncheck Moderate
   *          Answers
   */
  public void inputDataToSettingTab(String cat,
                                    String order,
                                    String des,
                                    Boolean modQues,
                                    Boolean viewAuthor,
                                    Boolean modAnswer) {
    info("Input data to setting tab of category form");
    info("input category name");
    if (cat != null && cat != "") {
      evt.type(ELEMENT_CATEGORY_ADD_CATEGORY_INPUT, cat, true);
    }
    info("input category order");
    if (order != null && order != "") {
      evt.type(ELEMENT_CATEGORY_ADD_ORDER_INPUT, cat, true);
    }
    info("input category des");
    if (des != null && des != "") {
      evt.type(ELEMENT_CATEGORY_ADD_DESCRIPTION_INPUT, cat, true);
    }
    info("input category moderator question");
    if (modQues != null) {
      if (modQues)
        evt.check(ELEMENT_CATEGORY_ADD_MOD_QUES_CHECKBOX, 2);
      else
        evt.uncheck(ELEMENT_CATEGORY_ADD_MOD_QUES_CHECKBOX, 2);
    }
    info("input category viewAuthor");
    if (viewAuthor != null) {
      if (viewAuthor)
        evt.check(ELEMENT_CATEGORY_ADD_MOD_VIEW_CHECKBOX, 2);
      else
        evt.uncheck(ELEMENT_CATEGORY_ADD_MOD_VIEW_CHECKBOX, 2);
    }
    info("input category modAnswer");
    if (modAnswer != null) {
      if (modAnswer)
        evt.check(ELEMENT_CATEGORY_ADD_MOD_ANS_CHECKBOX, 2);
      else
        evt.uncheck(ELEMENT_CATEGORY_ADD_MOD_ANS_CHECKBOX, 2);
    }
  }

  /**
   * delete Category
   * 
   * @param cat
   */
  public void deleteCategory(String cat) {
    info("Delete category");
    goToActionOfCategoryFromActionBar(actionCategoryOption.DELETE);
    assert evt.getText(ELEMENT_CATEGORY_DELETE_CONFIRM)
              .contains(ELEMENT_CATEGORY_DELETE_CONFIRM_MSG) : "Message is wrong. Actual msg is "
                  + testBase.getText(ELEMENT_CATEGORY_DELETE_CONFIRM);
    evt.click(ELEMENT_CATEGORY_DELETE_OK_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
  }

  /**
   * move Category to target
   * 
   * @param target target category (Ex: cat1/cat2/cat3...)
   */
  public void moveCategory(String target) {
    info("Move category");
    String[] nodes = target.split("/");
    if (nodes.length > 1) {
      for (int i = 0; i < nodes.length - 2; i++) {
        evt.click(ELEMENT_CATEGORY_MOVE_TARGET_ITEM.replace("$category", nodes[i]));
      }
    }
    evt.doubleClickOnElement(ELEMENT_CATEGORY_MOVE_TARGET_ITEM.replace("$category", nodes[nodes.length - 1]));
  }

  /**
   * function export category in answer
   * 
   * @param fileName
   */
  public void exportAnswerCategory(String fileName) {
    info("Export category to file " + fileName);
    goToActionOfCategoryFromActionBar(actionCategoryOption.EXPORT);
    evt.type(ELEMENT_FILE_NAME_EXPORT, fileName, true);
    button.save();
  }

  /**
   * function import category in answer
   * 
   * @param path
   */
  public void importAnswerCategory(String path) {
    info("Import category from file " + path);
    String[] links = path.split("/");
    goToActionOfCategoryFromActionBar(actionCategoryOption.IMPORT);
    WebElement eFile = evt.waitForAndGetElement(ELEMENT_IMPORT_CATEGORY_INPUT, testBase.getDefaultTimeout(), 1, 2);
    ((JavascriptExecutor) testBase.getSeleniumDriver()).executeScript("arguments[0].style.display = 'block';", eFile);
    eFile.sendKeys(testBase.getAbsoluteFilePath(path));
    evt.waitForAndGetElement(ELEMENT_ATTACHMENT_FORM_FILE_NAME.replace("$fileName", links[links.length - 1]));
    evt.switchToParentWindow();
    evt.click(ELEMENT_ATTACHMENT_SAVE_BUTTON);
    alert.verifyAlertMessage(ELEMENT_IMPORT_SUCCESS_MESSAGE);
    evt.click(ELEMENT_CATEGORY_OK_BUTTON);
  }

  /**
   * <<<<<<< HEAD Set permission
   * 
   * @param cat
   * @param group
   * @param isRestriected
   * @param isMod
   */
  public void setPermission(String cat, String group, boolean isRestricted, boolean isMod) {
    goToActionOfCategoryFromActionBar(actionCategoryOption.EDIT);
    evt.click(ELEMENT_CATEGORY_EDIT_PERM_TAB, 0, true);
    selectGroupMembership(group, "*");
    evt.click(ELEMENT_ADD_USERS_BUTTON);
    if (isRestricted)
      evt.check(By.xpath(ELEMENT_MANAGE_QUESTION_PERM_RESTRICTED.replace("$group", group)), 2);
    if (isMod)
      evt.check(By.xpath(ELEMENT_MANAGE_QUESTION_PERM_MODERATOR.replace("$group", group)), 2);
    evt.click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
  }

  /**
   * Select group membership
   */
  public void selectGroupMembership(String groupPath, String mem) {
    info("select group membership");
    String[] temp;
    evt.click(ELEMENT_SELECT_MEMBERSHIP_ICON);
    evt.waitForAndGetElement(ELEMENT_SELECT_MEMBERSHIP_POPUP);
    temp = groupPath.split("/");
    for (int i = 0; i < temp.length; i++) {
      evt.click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
    }
    evt.click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", mem));
    evt.waitForElementNotPresent(ELEMENT_SELECT_MEMBERSHIP_POPUP);
  }

  /**
   * create category
   * 
   * @param cat
   */
  public void createCategory(String cat) {
    goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
    inputDataToSettingTab(cat, null, cat, null, null, null);
    evt.click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
    evt.waitForAndGetElement(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
  }

  /**
   * check accessibility of category
   * 
   * @param cat
   * @param isAccess
   * @param ques
   */
  public void checkAccessibilityOfCat(String cat, boolean isAccess, String... ques) {
    if (isAccess) {
      evt.click(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat), 0, true);
      for (String q : ques) {
        evt.waitForAndGetElement(By.xpath(ELEMENT_QUESTION_LIST_ITEM.replace("$question", q)));
      }
    } else
      evt.waitForElementNotPresent(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
  }

  /**
   * Check user list
   * 
   * @param user
   * @param isPresent
   */
  public void checkPermUserList(String user, boolean isPresent) {
    goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
    evt.click(ELEMENT_CATEGORY_TAB_PERMISSIONS, 0, true);
    Utils.pause(500);
    evt.click(ELEMENT_CATEGORY_USER_ICON_SELECTOR, 0, true);
    if (isPresent) {
      evt.waitForAndGetElement(ELEMENT_CATEGORY_LIST_USER.replace("$user", user));
    } else {
      evt.waitForElementNotPresent(ELEMENT_CATEGORY_LIST_USER.replace("$user", user));
    }
  }

  /**
   * action menu of category
   */
  public enum actionCategoryOption {
    EDIT, ADD, EXPORT, IMPORT, DELETE, MOVE, WATCH, UNWATCH, RSS, SUBMITQUESTION
  }
}
