package org.exoplatform.platform.qa.ui.answer.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformPermissionLocator.ELEMENT_ADD_USERS_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
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

    $(ELEMENT_CATEGORY_BUTTON).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_CATEGORY_BUTTON).click();
    switch (action) {
    case EDIT:
      info("Edit category");
      $(ELEMENT_CATEGORY_EDIT_BUTTON).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_CATEGORY_EDIT_BUTTON).click();
      $(ELEMENT_CATEGORY_EDIT_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case ADD:
      info("ADD category");
      $(ELEMENT_CATEGORY_ADD_BUTTON).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_CATEGORY_ADD_BUTTON).click();
      $(ELEMENT_CATEGORY_ADD_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case EXPORT:
      info("EXPORT category");
      $(ELEMENT_CATEGORY_EXPORT_BUTTON).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_CATEGORY_EXPORT_BUTTON).click();
      $(ELEMENT_CATEGORY_EXPORT_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case IMPORT:
      info("IMPORT category");
      $(ELEMENT_CATEGORY_IMPORT_BUTTON).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_CATEGORY_IMPORT_BUTTON).click();
      $(ELEMENT_CATEGORY_IMPORT_FORM).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case DELETE:
      info("DELETE category");
      $(ELEMENT_CATEGORY_DELETE_BUTTON).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_CATEGORY_DELETE_BUTTON).click();
      $(ELEMENT_CATEGORY_DELETE_CONFIRM_POPUP).click();
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

    ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + cat)).contextClick();
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
      $(ELEMENT_CATEGORY_RIGHT_DELETE_BUTTON).click();
      $(ELEMENT_CATEGORY_DELETE_CONFIRM_POPUP).waitUntil(Condition.appears, Configuration.timeout);
      break;
    case MOVE:
      info("MOVE category");
      ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + cat)).parent().find(ELEMENT_ICON_MOVE_CATEGORIE).click();
      $(ELEMENT_CATEGORY_MOVE_FORM).waitUntil(Condition.appears, Configuration.timeout);
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
      ELEMENT_LIST_CATEGORIE.find(byLinkText(" " + cat)).parent().parent().find(ELEMENT_ICON_SUBMIT_QUESTION).click();
      $(ELEMENT_SUBMIT_QUESTION_FORM).waitUntil(Condition.appears, Configuration.timeout);
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
   * @param modQues true: check Moderate New Questions false: uncheck Moderate New
   *          Questions
   * @param viewAuthor true: check View Question Authors false: uncheck View
   *          Question Authors
   * @param modAnswer true: check Moderate Answers false: uncheck Moderate Answers
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
      $(ELEMENT_CATEGORY_ADD_CATEGORY_INPUT).setValue(cat);
    }
    info("input category order");
    if (order != null && order != "") {
      $(ELEMENT_CATEGORY_ADD_ORDER_INPUT).setValue(order);
    }
    info("input category des");
    if (des != null && des != "") {
      $(ELEMENT_CATEGORY_ADD_DESCRIPTION_INPUT).setValue(des);
    }
    info("input category moderator question");
    if (modQues != null) {
      if (modQues)
        $(ELEMENT_CATEGORY_ADD_MOD_QUES_CHECKBOX).click();
      else
        $(ELEMENT_CATEGORY_ADD_MOD_QUES_CHECKBOX).click();
    }
    info("input category viewAuthor");
    if (viewAuthor != null) {
      if (viewAuthor)
        $(ELEMENT_CATEGORY_ADD_MOD_VIEW_CHECKBOX).click();
      else
        $(ELEMENT_CATEGORY_ADD_MOD_VIEW_CHECKBOX).click();
    }
    info("input category modAnswer");
    if (modAnswer != null) {
      if (modAnswer)
        $(ELEMENT_CATEGORY_ADD_MOD_ANS_CHECKBOX).click();
      else
        $(ELEMENT_CATEGORY_ADD_MOD_ANS_CHECKBOX).click();
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
    ELEMENT_FORM_MOVE_CATEGORIE.find(byText(target)).doubleClick();
  }

  /**
   * function export category in answer
   *
   * @param fileName string
   */
  public void exportAnswerCategory(String fileName) {
    info("Export category to file " + fileName);
    goToActionOfCategoryFromActionBar(actionCategoryOption.EXPORT);
    $(ELEMENT_FILE_NAME_EXPORT).setValue(fileName);
    button.save();
  }

  /**
   * function import category in answer
   *
   */
  public void importAnswerCategory() {
    info("Import category from file ");
    goToActionOfCategoryFromActionBar(actionCategoryOption.IMPORT);
    $(byClassName("uploadContainer")).find(byClassName("file")).uploadFromClasspath("AnswerCategorie.zip");
    $(byClassName("progressBarFrame")).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    $(ELEMENT_ATTACHMENT_SAVE_BUTTON).click();
    $(byText(ELEMENT_IMPORT_SUCCESS_MESSAGE)).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_CATEGORY_OK_BUTTON).click();
  }

  /**
   * action menu of category
   */
  public enum actionCategoryOption {
    EDIT, ADD, EXPORT, IMPORT, DELETE, MOVE, WATCH, UNWATCH, RSS, SUBMITQUESTION
  }
}
