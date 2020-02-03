/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - Selenium (Legacy Code).
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - Selenium (Legacy Code); if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.selenium.locator.answer;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import com.codeborne.selenide.SelenideElement;
public class AnswerLocator {
  /*******************************
   * ANSWER CATEGORY MANAGEMENT
   **********************************************************/
  // Action of category from action bar
  public static final By              ELEMENT_CATEGORY_EDIT_BUTTON                          =
          By.xpath("//*[@class='uiIconEditCategory']");
  public static final By              ELEMENT_CATEGORY_ADD_BUTTON                           =
          By.xpath("//*[@class='uiIconAddCategory']");
  public static final By              ELEMENT_CATEGORY_IMPORT_BUTTON                        =
          By.xpath("//*[@class='uiIconImport']");
  public static final By              ELEMENT_CATEGORY_EXPORT_BUTTON                        =
          By.xpath("//*[@class='uiIconExport']");
  public static final By              ELEMENT_CATEGORY_DELETE_BUTTON                        =
          By.xpath("//*[@class='uiIconDeleteCategory']");
  // Action of category from right click
  public static final By              ELEMENT_CATEGORY_RIGHT_EDIT_BUTTON                    =
          By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconEditCategory']");
  public static final By              ELEMENT_CATEGORY_RIGHT_ADD_BUTTON                     =
          By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconAddCategory']");
  public static final By              ELEMENT_CATEGORY_RIGHT_IMPORT_BUTTON                  =
          By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[contains(@class,'faqCategory oncontextmenu') and not (contains(@style,'display: block'))]//*[@class='uiIconLightGray uiIconImport']");
  public static final By              ELEMENT_CATEGORY_RIGHT_EXPORT_BUTTON                  =
          By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconExport']");
  public static final By              ELEMENT_CATEGORY_RIGHT_DELETE_BUTTON                  =
          By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconDeleteCategory']");
  public static final By              ELEMENT_CATEGORY_RIGHT_WATCH_BUTTON                   =
          By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconWatch']");
  public static final By              ELEMENT_CATEGORY_WATCH_OK_BUTTON                      =
          By.xpath("//*[contains(text(),'OK') and contains(@onclick,'answers')]");
  public static final By              ELEMENT_CATEGORY_RIGHT_UNWATCH_BUTTON                 =
          By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconUnWatch']");
  public static final By              ELEMENT_CATEGORY_RIGHT_RSS_BUTTON                     =
          By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconRss uiIconLightGray']");
  // Category list
  public static final String          ELEMENT_CATEGORY_LIST_ITEM                            =
          "//*[contains(@class,'faqCategory oncontextmenu')]//*[contains(.,'$category')]";
  // Edit category form
  public static final By              ELEMENT_CATEGORY_EDIT_FORM                            = By.id("EditCategoryForm");
  // Add category form
  public static final By              ELEMENT_CATEGORY_ADD_FORM                             = By.id("SubCategoryForm");
  public static final By              ELEMENT_CATEGORY_ADD_CATEGORY_INPUT                   = By.id("eventCategoryName");
  public static final By              ELEMENT_CATEGORY_ADD_ORDER_INPUT                      = By.id("index");
  public static final By              ELEMENT_CATEGORY_ADD_DESCRIPTION_INPUT                = By.id("description");
  public static final By              ELEMENT_CATEGORY_ADD_MOD_QUES_CHECKBOX                = By.id("moderatequestions");
  public static final By              ELEMENT_CATEGORY_ADD_MOD_VIEW_CHECKBOX                = By.id("ViewAuthorInfor");
  public static final By              ELEMENT_CATEGORY_ADD_MOD_ANS_CHECKBOX                 = By.id("moderateAnswers");
  public static final By              ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON                 =
          By.xpath("//*[@id='UICategoryForm']//*[text()='Save']");
  // Export category form
  public static final By              ELEMENT_CATEGORY_EXPORT_FORM                          = By.id("FAQExportForm");
  public static final By              ELEMENT_FILE_NAME_EXPORT                              = By.id("FileName");
  // Import category form
  public static final By              ELEMENT_CATEGORY_IMPORT_FORM                          = By.id("FAQImportForm");
  public static final String          ELEMENT_ATTACHMENT_FORM_FILE_NAME                     = "//*[text()='$fileName']";
  public static final By              ELEMENT_ATTACHMENT_SAVE_BUTTON                        =

          By.xpath("//*[@id='FAQImportForm']//*[text()='Save']");
  public static final String          ELEMENT_IMPORT_SUCCESS_MESSAGE                        = "The file has been imported.";
  public static final By              ELEMENT_CATEGORY_OK_BUTTON                            =
          By.xpath("//*[contains(@class,'UIPopupWindow')]//a[text()='OK']");
  // Delete category
  public static final By              ELEMENT_CATEGORY_DELETE_CONFIRM_POPUP                 = By.id("UIForumPopupConfirmation");
  public static final String          ELEMENT_CATEGORY_DELETE_CONFIRM_MSG                   =
          "Are you sure you want to delete this category ?";
  public static final By              ELEMENT_CATEGORY_DELETE_CONFIRM                       =
          By.xpath("//*[@id='UIForumPopupConfirmation']//*[contains(@class, 'confirmationIcon')]");
  public static final By              ELEMENT_CATEGORY_DELETE_OK_BUTTON                     =
          By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='OK']");
  // Move category
  public static final By              ELEMENT_CATEGORY_MOVE_FORM                            = By.id("MoveCategoryForm");
  public static final String          ELEMENT_CATEGORY_MOVE_TARGET_ITEM                     =
          "//*[@class='uiIconCategory uiIconLightGray']/../..//*[contains(.,'$category')]";
  /*******************************************************************************************************************/
  // answer portlets
  public static final By              ELEMENT_ANSWER_PORTLET                                = By.id("UIAnswersContainer");
  public static final By              ELEMENT_SUBMIT_QUESTION                               =
          By.xpath("//*[contains(@class,'uiIconAnsSubmitQuestion')]");
  public static final By              ELEMENT_CATEGORY_BUTTON                               =
          By.xpath("//*[@class='uiIconAnsManageCategory']");
  public static final String          ELEMENT_QUESTION_LIST_ITEM                            =
          "//*[@class='rightContent']//*[text()='$question']";
  public static final By              ELEMENT_MANAGE_QUESTION_BUTTON                        =
          By.xpath("//*[@class='uiIconAnsManageQuestion']");
  // Quick Search
  public static final By              ELEMENT_QUICK_SEARCH_INPUT                            = By.id("inputValue");
  public static final By              ELEMENT_QUICK_SEARCH_BUTTON                           =
          By.xpath("//*[@class='uiIconSearch uiIconLightGray']");
  public static final By              ELEENT_QUICK_SEARCH_POPUP                             = By.id("UIResultQuickSearchs");
  public static final String          ELEMENT_QUICK_SEARCH_CLOSE                            =
                                                                 "//*[@id=\"UIResultQuickSearchs\"]/div/div[2]/button";




  public static final String          ELEMENT_QUICK_SEARCH_ADVANCE_SEARCH_BUTTON            =
          "//*[@id='UIResultQuickSearchs']//*[text()='Advanced Search']";
  // Advance search
  public static final By              ELEMENT_ADVANCE_SEARCH_POPUP                          = By.id("AdvanceSearchForm");
  public static final By              ELEMENT_ADVANCE_SEARCH_KEY_INPUT                      = By.id("Text");
  public static final String          ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON          =
          "//*[@id='AdvanceSearchForm']//*[text()='Search']";
  public static final String          ELEMENT_ADVANCE_SEARCH_ADVANCE_CLOSE_BUTTON           =
          "//*[@id='AdvanceSearchForm']//*[text()='Close']";
  // Manage question form
  public static final By              ELEMENT_MANAGE_QUESTION_FORM                          = By.id("FAQQuestionManagerment");
  public static final By              ELEMENT_MANAGE_QUESTION_FORM_OPEN_QUESTION_TAB        =
          By.xpath("//*[@data-toggle='tab' and text()='Open Questions']");
  public static final String          ELEMENT_MANAGE_QUESTION_ACTIVE_QUESTION_CHECKBOX      =
          "//*[text()='$question']/..//*[@data-original-title='Deactivate' or @data-original-title='Activate']//*[@id='allDay']";
  public static final By              ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON                  =
          By.xpath("//*[@id='UIAnswersPopupAction']//*[text()='Close']");
  // Submit Question form
  public static final By              ELEMENT_SUBMIT_QUESTION_FORM                          = By.id("UIQuestionForm");
  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_TITLE_INPUT              = By.id("QuestionTitle");
  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_LANGUAGE_SELECT_BOX      = By.name("AllLanguages");
  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_ATTACHMENT_BUTTON        =
          By.xpath("//*[@class='uiIconAttach uiIconLightGray']");
  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON              =
          By.xpath("//*[@id='UIQuestionForm']//*[text()='Save']");
  public static final By              ELEMENT_QUESTION_FILE_INPUT                           = By.xpath("//*[@name='file']");
  // Attach file form
  public static final By              ELEMENT_ATTACH_SAVE_BUTTON                            =
          By.xpath("//form[@id='UIAttachmentForm']//*[text()='Save']");
  public static final String          ELEMENT_ATTACH_FILE_NAME                              =
          "//*[@data-original-title='$fileName']";
  // More actions
  public static final By              ELEMENT_QUESTION_MORE_ACTION_BUTTON                   =
          By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconSettings')]");
  public static final By              ELEMENT_QUESTION_PRINT_BUTTON                         =
          By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconPrint')]");
  public static final By              ELEMENT_QUESTION_EDIT_BUTTON                          =
          By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconEdit')]");
  public static final By              ELEMENT_QUESTION_MOVE_BUTTON                          =
          By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconMove')]");
  public static final By              ELEMENT_QUESTION_SEND_BUTTON                          =
          By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconAnsSentMail')]");
  public static final By              ELEMENT_QUESTION_DELETE_BUTTON                        =
          By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconTrash')]");
  // Question menu action
  public static final String          ELEMENT_QUESTION_COMMENT                              =
          "//*[@class='rightContent']//*[text()='$question']//ancestor::*[@class='rightContent']//*[@class='uiIconComment uiIconLightGray']";
  public static final String          ELEMENT_QUESTION_ANSWER                               =
          "//*[@class='rightContent']//*[text()='$question']//ancestor::*[@class='rightContent']//*[@class='uiIconAnsAnswer uiIconLightGray']";
  public static final String          ELEMENT_QUESTION_EDIT                                 =
          "//*[@id=\"{idEdit}Context\"]/li[3]/a";
  public static final SelenideElement ELEMENT_QUESTION_DELETE                               =
          $(byId("UIQuestions")).find(byClassName("uiIconTrash"));
  public static final String          ELEMENT_QUESTION_MOVE                                 =
          "//*[@class='rightContent']//*[text()='$question']//ancestor::*[@class='rightContent']//*[@class='uiIconMove uiIconLightGray']";
  public static final String          ELEMENT_QUESTION_SEND                                 =
          "//*[@class='rightContent']//*[text()='$question']//ancestor::*[@class='rightContent']//*[@class='uiIconAnsSentMail uiIconLightGray']";
  // Comment question form
  public static final By              ELEMENT_QUESTION_COMMENT_FORM                         = By.id("UICommentForm");
  // answer question form
  public static final By              ELEMENT_QUESTION_ANSWER_FORM                          = By.id("UIAnswersPopupWindow");
  // Edit question form
  public static final By              ELEMENT_QUESTION_EDIT_FORM                            = By.id("UIQuestionForm");
  // Delete question form
  public static final By              ELEMENT_QUESTION_DELETE_FORM                          = By.id("UIDeleteQuestion");
  public static final By              ELEMENT_QUESTION_CONFIRM_DELETE                       =
          By.xpath("//*[@id='UIDeleteQuestion']//*[contains(text(),'Are you sure you want to delete this question and its answers?')]");
  public static final By              ELEMENT_QUESTION_DELETE_FORM_OK_BUTTON                =
          By.xpath("//*[@id='UIDeleteQuestion']//*[text()='OK']");
  // Move question form
  public static final By              ELEMENT_QUESTION_MOVE_FORM                            = By.id("FAQMoveQuestion");
  // Send question form
  public static final By              ELEMENT_QUESTION_SEND_FORM                            = By.id("FAQSendMailForm");
  // answer form
  public static final By              ELEMENT_ANSWER_BUTTON                                 =
          By.xpath("//*[@class='questionAction']//*[text()='Answer']");
  public static final By              ELEMENT_ANSWER_FORM                                   = By.id("UIResponseForm");
  public static final By              ELEMENT_ANSWER_APPROVE_CHECKBOX                       = By.xpath("//*[@id=\"UIResponseForm\"]/div[2]/div[4]/div/span");
  public static final By              ELEMENT_ANSWER_ACTIVATE_CHECKBOX                      = By.xpath("//*[@id=\"UIResponseForm\"]/div[2]/div[5]/div/span");
  public static final By              ELEMENT_ANSWER_FORM_SAVE_BUTTON                       =
          By.xpath(".//*[@id='UIResponseForm']//button[1]");
  // More actions
  public static final String          ELEMENT_ANSWER_MORE_ACTION_BUTTON                     =
          "//*[@class='responseContainer']//*[contains(@id,'answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconSettings uiIconLightGray']";
  public static final String          ELEMENT_ANSWER_EDIT_BUTTON                            =
          "//*[@class='responseContainer']//*[contains(@id,'answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconLightGray uiIconEdit']";
  public static final String          ELEMENT_ANSWER_APPROVE_BUTTON                         =
          "//*[@class='responseContainer']//*[contains(@id,'answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconLightGray uiIconEdit']";
  public static final String          ELEMENT_ANSWER_DISAPPROVE_BUTTON                      =
          "//*[@class='responseContainer']//*[contains(@id,'answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconAnsLightGray uiIconAnsDisapprove']";
  public static final String          ELEMENT_ANSWER_ACTIVE_BUTTON                          =
          "//*[@class='responseContainer']//*[contains(@id,'answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconAnsLightGray uiIconAnsEnable']";
  public static final String          ELEMENT_ANSWER_DEACTIVE_BUTTON                        =
          "//*[@class='responseContainer']//*[contains(@id,'answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconAnsLightGray uiIconAnsEnable']";
  // Delete
  public static final By              ELEMENT_ANSWER_DELETE_CONFIRM_POPUP                   = By.id("UIForumPopupConfirmation");
  public static final By              ELEMENT_ANSWER_DELETE_FORM_OK_BUTTON                  =
          By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='OK']");
  public static final By              ELEMENT_SORT_BY_RATE                                  =
          By.xpath("//a[@data-original-title='Sort Answers by Rate']");
  /***********************
   * COMMENT MANANGEMENT
   ****************************************************/
  // Comment form
  public static final By              ELEMENT_COMMENT_BUTTON                                =
          By.xpath("//*[@class='questionAction']//*[text()='Comment']");
  public static final By              ELEMENT_COMMENT_FORM                                  = By.id("UICommentForm");
  public static final By              ELEMENT_COMMENT_FORM_SAVE_BUTTON                      =
          By.xpath("//*[@id='UICommentForm']//*[text()='Save']");
  // Delete
  public static final By              ELEMENT_COMMENT_DELETE_CONFIRM_POPUP                  = By.id("UIForumPopupConfirmation");
  public static final By              ELEMENT_COMMENT_DELETE_FORM_OK_BUTTON                 =
          By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='OK']");
  /*****************************************
   * FQA HOME PAGE
   **********************************************/
  public static final By              ELEMENT_FAQ_QUESTION_LIST                             =
          By.xpath("//*[@class='questionList']");
  public static final SelenideElement ELEMENT_ANSWER_MORE_ACTION                            =
          $(byClassName("responseContainer")).find(byClassName("uiDropdownWithIcon"));
  public static final SelenideElement ELEMENT_ANSWER_EDIT                                   =
          ELEMENT_ANSWER_MORE_ACTION.find(byClassName("uiIconEdit"));
  public static final By ELEMENT_COMMENT_MORE_ACTION                           =
          byClassName("uiDropdownWithIcon");
  public static final By ELEMENT_COMMENT_EDIT                                  =
          byClassName("uiIconEdit");
  public static final SelenideElement ELEMENT_QUESTION_ANSWER_CONTENT_INPUT                 = $(byXpath("/html/body"));
  public static final SelenideElement ELEMENT_RATE_QUESTION_1_UNVOTED=$(byId("faqVoteSpace")).findAll(byClassName("unvoted")).get(0);
  public static final SelenideElement ELEMENT_RATE_QUESTION_2_UNVOTED=$(byId("faqVoteSpace")).findAll(byClassName("unvoted")).get(1);
  public static final SelenideElement ELEMENT_RATE_QUESTION_3_UNVOTED=$(byId("faqVoteSpace")).findAll(byClassName("unvoted")).get(2);
  public static final SelenideElement ELEMENT_RATE_QUESTION_4_UNVOTED=$(byId("faqVoteSpace")).findAll(byClassName("unvoted")).get(3);
  public static final SelenideElement ELEMENT_RATE_QUESTION_5_UNVOTED=$(byId("faqVoteSpace")).findAll(byClassName("unvoted")).get(4);
  public static final SelenideElement ELEMENT_RATE_QUESTION_1_VOTED=$(byClassName("voteResult")).findAll(byClassName("voted")).get(0);
  public static final SelenideElement ELEMENT_RATE_QUESTION_2_VOTED=$(byClassName("voteResult")).findAll(byClassName("voted")).get(1);
  public static final SelenideElement ELEMENT_RATE_QUESTION_3_VOTED=$(byClassName("voteResult")).findAll(byClassName("voted")).get(2);
  public static final SelenideElement ELEMENT_RATE_QUESTION_4_VOTED=$(byClassName("voteResult")).findAll(byClassName("voted")).get(3);
  public static final SelenideElement ELEMENT_RATE_QUESTION_5_VOTED=$(byClassName("voteResult")).findAll(byClassName("voted")).get(4);
  public static final SelenideElement ELEMENT_CONTAINER_QUESTIONS_IN_MANAGE_QUESTION=$(byId("QuestionManagerment-tab"));
  public static final By ELEMENT_ICON_EDIT_QUESTION_IN_MANAGE_QUESTION=byClassName("uiIconEdit");
  public static final By ELEMENT_ICON_DELETE_QUESTION_IN_MANAGE_QUESTION=byClassName("uiIconDelete");
  public static final By ELEMENT_CHECBOX_IN_MANAGE_QUESTION=byId("allDay");

  public static final By ELEMENT_ANSWER_DELETE=byClassName("confirm");

  public static final By              ELEMENT_ICON_MORE_ACTIONS_QUESTION                    = byClassName("uiIconSettings");

  public static final SelenideElement ELEMENT_TABLE_RESULT_SEARCH                           = $(byId("ResultQuickSearch"));

  public static final SelenideElement ELEMENT_POPUB_MANAGE_QUESTION                         = $(byId("UIAnswersPopupWindow"));

  public static final By              ELEMENT_CHECKBOX_ACTIVATE_UNACTIVATE_QUESTION         = byId("allDay");

  public static final SelenideElement ELEMENT_QUESTION_CONTAINER                            = $(byClassName("questionContainer"));

  public static final SelenideElement ELEMENT_LIST_CATEGORIE                                = $(byId("UICategories"));

  public static final SelenideElement ELEMENT_ICON_GO_TO_HOME_CATEGORIE                     = $(byClassName("uiIconUpLevel"));

  public static final SelenideElement ELEMENT_FORM_MOVE_CATEGORIE                           = $(byId("UIMoveCategoryForm"));

  public static final SelenideElement ELEMENT_CONTENT_FIRST_ANSWER_IN_QUESTION=$(byClassName("responseContainer")).findAll(byClassName("answerContent")).get(0);
  public static final SelenideElement ELEMENT_CONTENT_SECOND_ANSWER_IN_QUESTION=$(byClassName("responseContainer")).findAll(byClassName("answerContent")).get(1);
  public static final SelenideElement ELEMENT_CONTENT_THIRD_ANSWER_IN_QUESTION=$(byClassName("responseContainer")).findAll(byClassName("answerContent")).get(2);
  public static final By ELEMENT_ICON_LIKE_ANSWER=byClassName("uiIconAnsThumbUpMini");
  public static final By ELEMENT_ICON_UNLIKE_ANSWER=byClassName("uiIconAnsThumbDownMini");
  public static final SelenideElement ELEMENT_LIST_OF_ANSWERS=$(byId("SetWidthContent"));
  public static final By ELEMENT_ICON_PROMOTE_COMMENT= byClassName("uiIconAnsPromte");
  public static final SelenideElement ELEMENT_TAB_OPEN_QUESTION_IN_MANAGE_QUESTION=$(byId("QuestionNotAnswered-tab"));
  public static final By ELEMENT_ICON_ANSWER_QUESTION_IN_MANAGE_QUESTION=byClassName("uiIconAnsAnswer");
  public static final By ELEMENT_ICON_MOVE_CATEGORIE=byClassName("uiIconMoveCategory");

  public static final By ELEMENT_ICON_SUBMIT_QUESTION=byClassName("uiIconAnsAddNewQuestion");

}
