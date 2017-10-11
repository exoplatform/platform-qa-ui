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
import static com.codeborne.selenide.Selenide.$$;

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

  public static final By              ELEMENT_CATEGORY_RIGHT_MOVE_BUTTON                    =
                                                                         By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconMoveCategory']");

  public static final By              ELEMENT_CATEGORY_RIGHT_WATCH_BUTTON                   =
                                                                          By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconWatch']");

  public static final By              ELEMENT_CATEGORY_WATCH_OK_BUTTON                      =
                                                                       By.xpath("//*[contains(text(),'OK') and contains(@onclick,'answers')]");

  public static final By              ELEMENT_CATEGORY_RIGHT_UNWATCH_BUTTON                 =
                                                                            By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconUnWatch']");

  public static final By              ELEMENT_CATEGORY_RIGHT_RSS_BUTTON                     =
                                                                        By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconRss uiIconLightGray']");

  public static final By              ELEMENT_CATEGORY_RIGHT_SUBMIT_QUESTION_BUTTON         =
                                                                                    By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconAnsLightGray uiIconAnsAddNewQuestion']");

  // Category list
  public static final String          ELEMENT_CATEGORY_LIST_ITEM                            =
                                                                 "//*[contains(@class,'faqCategory oncontextmenu')]//*[contains(.,'$category')]";

  public static final By              ELEMENT_CATEGORY_UP_LEVEL                             =
                                                                By.xpath("//*[@class='uiIconUpLevel uiIconLightGray']");

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

  public static final By              ELEMENT_CATEGORY_ADD_FORM_CANCEL_BUTTON               =
                                                                              By.xpath("//*[@id='UICategoryForm']//*[text()='Save']");

  // Export category form
  public static final By              ELEMENT_CATEGORY_EXPORT_FORM                          = By.id("FAQExportForm");

  public static final By              ELEMENT_FILE_NAME_EXPORT                              = By.id("FileName");

  // Import category form
  public static final By              ELEMENT_CATEGORY_IMPORT_FORM                          = By.id("FAQImportForm");

  public static final By              ELEMENT_IMPORT_CATEGORY_INPUT                         = By.name("file");

  public static final String          ELEMENT_ATTACHMENT_FORM_FILE_NAME                     = "//*[text()='$fileName']";

  public static final String          ELEMENT_EVENT_ATTACHMENT                              =
                                                               "//*[@id='UIEventForm']/..//a[@data-original-title='${file}']";

  public static final By              ELEMENT_ATTACHMENT_SAVE_BUTTON                        =
                                                                     By.xpath("//*[@id='FAQImportForm']//*[text()='Save']");

  public static final String          ELEMENT_IMPORT_SUCCESS_MESSAGE                        = "The file has been imported";

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

  public static final By              ELEMENT_CATEGORY_DELETE_CANCEL_BUTTON                 =
                                                                            By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='Cancel']");

  // Move category
  public static final By              ELEMENT_CATEGORY_MOVE_FORM                            = By.id("MoveCategoryForm");

  public static final String          ELEMENT_CATEGORY_MOVE_TARGET_ITEM                     =
                                                                        "//*[@class='uiIconCategory uiIconLightGray']/../..//*[contains(.,'$category')]";

  // Permission tab
  public static final By              ELEMENT_CATEGORY_EDIT_PERM_TAB                        =
                                                                     By.xpath("//*[contains(@data-target,'PermissionTab')]");

  public static final String          ELEMENT_MANAGE_QUESTION_PERM_RESTRICTED               =
                                                                              "//*[contains(.,'$group')]/../td[2]/*[@class='uiCheckbox']/input[@type='checkbox']";

  public static final String          ELEMENT_MANAGE_QUESTION_PERM_MODERATOR                =
                                                                             "//*[contains(.,'$group')]/../td[3]/*[@class='uiCheckbox']/input[@type='checkbox']";

  public static final By              ELEMENT_SELECT_MEMBERSHIP_ICON                        =
                                                                     By.xpath("//*[@class='uiIconMembership uiIconLightGray']");

  public static final String          ELEMENT_SELECT_RIGHT_PARENT_GROUP                     =
                                                                        "//*[contains(@id,'UIMemberShipSelector')]//a[contains(.,'$group')]";

  public static final By              ELEMENT_SELECT_MEMBERSHIP_POPUP                       =
                                                                      By.xpath("//*[contains(@id,'UIPopupWindow')]");

  // Permission
  public static final By              ELEMENT_CATEGORY_TAB_PERMISSIONS                      =
                                                                       By.xpath("//*[contains(@data-toggle,'tab')][contains(.,'Permissions')]");

  public static final By              ELEMENT_CATEGORY_USER_ICON_SELECTOR                   =
                                                                          By.xpath("//*[contains(@class,'uiIconUser')]");

  public static final String          ELEMENT_CATEGORY_LIST_USER                            =
                                                                 ".//*[@id='UIListUsers']/*[contains(.,'$user')]";

  /*******************************************************************************************************************/

  // answer portlets
  public static final By              ELEMENT_ANSWER_PORTLET                                = By.id("UIAnswersContainer");

  public static final By              ELEMENT_SUBMIT_QUESTION                               =
                                                              By.xpath("//*[contains(@class,'uiIconAnsSubmitQuestion')]");

  public static final By              ELEMENT_CATEGORY_BUTTON                               =
                                                              By.xpath("//*[@class='uiIconAnsManageCategory']");

  public static final String          ELEMENT_QUESTION_LIST_ITEM                            =
                                                                 "//*[@class='rightContent']//*[text()='$question']";

  public static final String          ELEMENT_QUESTION_SELECTED_ITEM                        =
                                                                     "//*[contains(@class,'questionSelect')]//*[@class='theContent questionName' and contains(text(),'$question')]";

  public static final By              ELEMENT_MANAGE_QUESTION_BUTTON                        =
                                                                     By.xpath("//*[@class='uiIconAnsManageQuestion']");

  public static final String          ELEMENT_QUESTION_LINK                                 =
                                                            ".//*[contains(@class,'titleWithBorder')]//*[contains(text(),'$question')]";

  // Breadcumb
  public static final By              ELEMENT_CATEGORY_BREADCUMB_HOME                       =
                                                                      By.xpath("//*[@id='UIBreadcumbs']//*[text()='Home']");

  // Quick Search
  public static final By              ELEMENT_QUICK_SEARCH_INPUT                            = By.id("inputValue");

  public static final By              ELEMENT_QUICK_SEARCH_BUTTON                           =
                                                                  By.xpath("//*[@class='uiIconSearch uiIconLightGray']");

  public static final By              ELEENT_QUICK_SEARCH_POPUP                             = By.id("UIResultQuickSearchs");

  public static final String          ELEMENT_QUICK_SEARCH_RESULT_ITEM                      =
                                                                       "//*[@id='UIResultQuickSearchs']//*[contains(text(),'$name')]";

  public static final String          ELEMENT_QUICK_SEARCH_CLOSE                            =
                                                                 "//*[@id='UIResultQuickSearchs']//*[text()='Close']";

  public static final String          ELEMENT_QUICK_SEARCH_ADVANCE_SEARCH_BUTTON            =
                                                                                 "//*[@id='UIResultQuickSearchs']//*[text()='Advanced Search']";

  // Advance search
  public static final By              ELEMENT_ADVANCE_SEARCH_POPUP                          = By.id("AdvanceSearchForm");

  public static final By              ELEMENT_ADVANCE_SEARCH_KEY_INPUT                      = By.id("Text");

  public static final By              ELEMENT_ADVANCE_SEARCH_ALL_RADIO_BUTTON               =
                                                                              By.xpath("//*[@value='categoryAndQuestion']");

  public static final By              ELEMENT_ADVANCE_SEARCH_CATEGORY_RADIO_BUTTON          =
                                                                                   By.xpath("//*[@value='faqCategory']");

  public static final By              ELEMENT_ADVANCE_SEARCH_ENTRY_RADIO_BUTTON             =
                                                                                By.xpath("//*[@value='faqQuestion']");

  public static final String          ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON          =
                                                                                   "//*[@id='AdvanceSearchForm']//*[text()='Search']";

  public static final String          ELEMENT_ADVANCE_SEARCH_ADVANCE_CLOSE_BUTTON           =
                                                                                  "//*[@id='AdvanceSearchForm']//*[text()='Close']";

  public static final String          ELEMENT_ADVANCE_SEARCH_RESULT_ITEM                    =
                                                                         "//*[@id='AdvanceSearchForm']//*[contains(text(),'$name')]";

  public static final By              ELEENT_ADVANCE_SEARCH_CLOSE_RESULT_QUICK_SEARCH       =
                                                                                      By.id("//*[@class='resultQuickSearch']//*[text()='Close']");

  public static final String          ELEMENT_QUESTION_ANSWER_LINK                          =
                                                                   "//*[contains(text(),'$question')]/../../..//*[@class='questionAction']//*[contains(text(),'answer')]";

  public static final By              ELEMENT_QUESTION_RESPONE_FORM                         =
                                                                    By.xpath(".//*[@id='UIResponseForm']");

  // Manage question form
  public static final By              ELEMENT_MANAGE_QUESTION_FORM                          = By.id("FAQQuestionManagerment");

  public static final By              ELEMENT_MANAGE_QUESTION_FORM_ALL_QUESTION_TAB         =
                                                                                    By.xpath("//*[@data-toggle='tab' and text()='All Questions']");

  public static final By              ELEMENT_MANAGE_QUESTION_FORM_OPEN_QUESTION_TAB        =
                                                                                     By.xpath("//*[@data-toggle='tab' and text()='Open Questions']");

  public static final String          ELEMENT_MANAGE_QUESTION_ANSWER_QUESTION               =
                                                                              "//*[text()='$question']/..//*[@data-original-title='answer']";

  public static final String          ELEMENT_MANAGE_QUESTION_EDIT_QUESTION                 =
                                                                            "//*[text()='$question']/..//*[@data-original-title='Edit']";

  public static final String          ELEMENT_MANAGE_QUESTION_DELETE_QUESTION               =
                                                                              "//*[text()='$question']/../..//*[@data-original-title='Delete']";

  public static final String          ELEMENT_MANAGE_QUESTION_APPROVE_QUESTION_CHECKBOX     =
                                                                                        "//*[text()='$question']/..//*[@data-original-title='Approve' or @data-original-title='Disapprove']//*[@id='allDay']";

  public static final String          ELEMENT_MANAGE_QUESTION_ACTIVE_QUESTION_CHECKBOX      =
                                                                                       "//*[text()='$question']/..//*[@data-original-title='Deactivate' or @data-original-title='Activate']//*[@id='allDay']";

  public static final By              ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON                  =
                                                                           By.xpath("//*[@id='UIAnswersPopupAction']//*[text()='Close']");

  // Submit Question form
  public static final By              ELEMENT_SUBMIT_QUESTION_FORM                          = By.id("UIQuestionForm");

  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_TITLE_INPUT              = By.id("QuestionTitle");

  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_DATA_FRAME_INPUT         =
                                                                                    By.xpath("//*[@class='cke_wysiwyg_frame cke_reset']");

  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_LANGUAGE_SELECT_BOX      = By.name("AllLanguages");

  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_DELETE_LANG_BUTTON       =
                                                                                      By.xpath("//*[@name='AllLanguages']/../..//*[@data-original-title='Remove']");

  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_AUTHOR                   = By.id("Author");

  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_EMAIL                    = By.id("EmailAddress");

  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_ATTACHMENT_BUTTON        =
                                                                                     By.xpath("//*[@class='uiIconAttach uiIconLightGray']");

  public static final String          ELEMENT_SUBMIT_QUESTION_FORM_DELETE_ATTACHMENT_BUTTON =
                                                                                            "//*[@data-original-title='$file']/../..//*[@data-original-title='Remove']";

  public static final By              ELEMENT_SUBMIET_QUESTION_APPROVE_CHECKBOX             = By.id("IsApproved");

  public static final By              ELEMENT_SUBMIET_QUESTION_ACTIVE_CHECKBOX              = By.id("IsActivated");

  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON              =
                                                                               By.xpath("//*[@id='UIQuestionForm']//*[text()='Save']");

  public static final By              ELEMENT_SUBMIT_QUESTION_FORM_CANCEL_BUTTON            =
                                                                                 By.xpath("//*[@id='UIQuestionForm']//*[text()='Cancel']");

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

  public static final By              ELEMENT_QUESTION_DELETE_FORM_CANCEL_BUTTON            =
                                                                                 By.xpath("//*[@id='UIDeleteQuestion']//*[text()='Cancel']");

  // Move question form
  public static final By              ELEMENT_QUESTION_MOVE_FORM                            = By.id("FAQMoveQuestion");

  // Send question form
  public static final By              ELEMENT_QUESTION_SEND_FORM                            = By.id("FAQSendMailForm");

  public static final By              ELEMENT_QUESTION_SEND_TO_INPUT                        = By.id("To");

  public static final By              ELEMENT_QUESTION_SEND_SEND_BUTTON                     =
                                                                        By.xpath("//*[@id='FAQSendMailForm']//*[text()='Send']");

  public static final By              ELEMENT_QUESTION_OK_BUTTON                            =
                                                                 By.xpath("//*[contains(@class,'UIPopupWindow')]//a[text()='OK']");

  // Vote question
  public static final String          ELEMENT_QUESTION_RATE_ITEM                            =
                                                                 "//*[@data-original-title='Rate Question']/*[@data-index='$index']";

  public static final String          ELEMENT_QUESTION_RATE_NUMBER                          =
                                                                   "//*[contains(@class,'voteResult')]//*[contains(text(),'$index')]";

  // answer form
  public static final By              ELEMENT_ANSWER_BUTTON                                 =
                                                            By.xpath("//*[@class='questionAction']//*[text()='answer']");

  public static final By              ELEMENT_ANSWER_FORM                                   = By.id("UIResponseForm");

  public static final By              ELEMENT_ANSWER_FORM_DATA_FRAME_INPUT                  =
                                                                           By.xpath("//*[@class='cke_wysiwyg_frame cke_reset']");

  public static final By              ELEMENT_ANSWER_APPROVE_CHECKBOX                       = By.id("QuestionApproved");

  public static final By              ELEMENT_ANSWER_ACTIVATE_CHECKBOX                      = By.id("QuestionShowAnswer");

  public static final By              ELEMENT_ANSWER_RELATED_BUTTON                         =
                                                                    By.xpath("//*[@class='uiIconLightGray uiIconPlus']");

  public static final By              ELEMENT_ANSWER_FORM_SAVE_BUTTON                       =
                                                                      By.xpath(".//*[@id='UIResponseForm']//button[1]");

  public static final By              ELEMENT_ANSWER_FORM_CANCEL_BUTTON                     =
                                                                        By.xpath(".//*[@id='UIResponseForm']//button[2]");

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

  public static final String          ELEMENT_ANSWER_DELETE_BUTTON                          =
                                                                   "//*[@class='responseContainer']//*[contains(@id,'answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconLightGray uiIconTrash']";

  // answer container
  public static final String          ELEMENT_ANSWER_AUTHOR                                 =
                                                            "//*[contains(@id,'answer')]//*[text()='$answer']/../..//*[@class='userName' and contains(text(),'$fullname')]";

  public static final String          ELEMENT_ANSWER_CONTENT                                =
                                                             "//*[contains(@id,'answer')]//*[text()='$answer']";

  // Delete
  public static final By              ELEMENT_ANSWER_DELETE_CONFIRM_POPUP                   = By.id("UIForumPopupConfirmation");

  public static final By              ELEMENT_ANSWER_CONFIRM_DELETE                         =
                                                                    By.xpath("//*[@id='UIForumPopupConfirmation']//*[contains(text(),'Are you sure you want to delete this answer ?')]");

  public static final By              ELEMENT_ANSWER_DELETE_FORM_OK_BUTTON                  =
                                                                           By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='OK']");

  public static final By              ELEMENT_ANSWER_DELETE_FORM_CANCEL_BUTTON              =
                                                                               By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='Cancel']");

  // vote answer
  public static final String          ELEMENT_ANSWER_VOTE_ICON                              =
                                                               "//*[@class='responseContainer']//*[contains(@id,'answer')]//*[text()='$answer']/../../..//*[contains(@id, 'FAQVoteAnswerUp')]";

  public static final String          ELEMENT_ANSWER_UNVOTE_ICON                            =
                                                                 "//*[@class='responseContainer']//*[contains(@id,'answer')]//*[text()='$answer']/../../..//*[contains(@id, 'FAQVoteAnswerDown')]";

  public static final String          ELEMENT_ANSWER_NUMBER_VOTE                            =
                                                                 "//*[@class='responseContainer']//*[contains(@id,'answer')]//*[text()='$answer']/../../..//*[@class='textVoteAnswer']";

  public static final By              ELEMENT_SORT_BY_RATE                                  =
                                                           By.xpath("//a[@data-original-title='Sort Answers by Rate']");

  public static final String          ELEMENT_ANSWER_POSITION_IN_LIST                       =
                                                                      "//*[@class='responseContainer']//*[contains(@id,'answer')][${no}]//*[text()='${answer}']";

  /***********************
   * COMMENT MANANGEMENT
   ****************************************************/
  // Comment form
  public static final By              ELEMENT_COMMENT_BUTTON                                =
                                                             By.xpath("//*[@class='questionAction']//*[text()='Comment']");

  public static final By              ELEMENT_COMMENT_FORM                                  = By.id("UICommentForm");

  public static final By              ELEMENT_COMMENT_FORM_DATA_FRAME_INPUT                 =
                                                                            By.xpath("//*[@class='cke_wysiwyg_frame cke_reset']");

  public static final By              ELEMENT_COMMENT_FORM_SAVE_BUTTON                      =
                                                                       By.xpath("//*[@id='UICommentForm']//*[text()='Save']");

  public static final By              ELEMENT_COMMENT_FORM_CANCEL_BUTTON                    =
                                                                         By.xpath("//*[@id='UICommentForm']//*[text()='Cancel']");

  // More actions
  public static final String          ELEMENT_COMMENT_MORE_ACTION_BUTTON                    =
                                                                         "//*[@class='responseContainer']//*[contains(@id,'Comment')]//*[text()='$comment']/../../../../..//*[@class='uiIconSettings uiIconLightGray']";

  public static final String          ELEMENT_COMMENT_EDIT_BUTTON                           =
                                                                  "//*[@class='responseContainer']//*[contains(@id,'Comment')]//*[text()='$comment']/../../../../..//*[@class='uiIconEdit uiIconLightGray']";

  public static final String          ELEMENT_COMMENT_PROMOTE_TO_ANSWER_BUTTON              =
                                                                               "//*[@class='responseContainer']//*[contains(@id,'Comment')]//*[text()='$comment']/../../../../..//*[@class='uiIconAnsPromte uiIconAnsLightGray']";

  public static final String          ELEMENT_COMMENT_DELETE_BUTTON                         =
                                                                    "//*[@class='responseContainer']//*[contains(@id,'Comment')]//*[text()='$comment']/../../../../..//*[@class='uiIconTrash uiIconLightGray']";

  // COMMENT container
  public static final String          ELEMENT_COMMENT_AUTHOR                                =
                                                             "//*[@class='responseContainer']//*[contains(@id,'Comment')]//*[text()='$comment']/../..//*[@class='userName' and contains(text(),'$fullname')]";

  public static final String          ELEMENT_COMMENT_CONTENT                               =
                                                              "//*[@class='responseContainer']//*[contains(@id,'Comment')]//*[text()='$comment']";

  // Delete
  public static final By              ELEMENT_COMMENT_DELETE_CONFIRM_POPUP                  = By.id("UIForumPopupConfirmation");

  public static final By              ELEMENT_COMMENT_CONFIRM_DELETE                        =
                                                                     By.xpath("//*[@id='UIForumPopupConfirmation']//*[contains(text(),'Are you sure you want to delete this comment ?')]");

  public static final By              ELEMENT_COMMENT_DELETE_FORM_OK_BUTTON                 =
                                                                            By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='OK']");

  public static final By              ELEMENT_COMMENT_DELETE_FORM_CANCEL_BUTTON             =
                                                                                By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='Cancel']");

  /*****************************************
   * FQA HOME PAGE
   **********************************************/
  public static final By              ELEMENT_FAQ_QUESTION_LIST                             =
                                                                By.xpath("//*[@class='questionList']");

  public static final String          ELEMENT_FAQ_CATEGORY                                  =
                                                           "//*[@class='questionList']//*[@data-original-title='$category']";

  public static final String          ELEMENT_FAQ_QUESTION                                  =
                                                           "//*[@class='viewerQuestion']//a[text()='$question']";

  public static final String          ELEMENT_FAQ_ANSWER                                    =
                                                         "//*[@class='viewerAnswer']//*[text()='$answer']";

  public static final SelenideElement ELEMENT_ANSWER_MORE_ACTION                            =
                                                                 $(byClassName("responseContainer")).find(byClassName("uiDropdownWithIcon"));

  public static final SelenideElement ELEMENT_ANSWER_EDIT                                   =
                                                          ELEMENT_ANSWER_MORE_ACTION.find(byClassName("uiIconEdit"));

  public static final SelenideElement ELEMENT_COMMENT_MORE_ACTION                           =
                                                                  $(byClassName("responseContainer")).find(byClassName("uiDropdownWithIcon"));

  public static final SelenideElement ELEMENT_COMMENT_EDIT                                  =
                                                           ELEMENT_COMMENT_MORE_ACTION.find(byClassName("uiIconEdit"));

  public static final String          ELEMENT_ANSWER_QUESTION                               =
                                                              "//*[@id=\"{IdQuestion}\"]/div/div[2]/div[2]/div[2]/div[2]/a[1]";

  public static final SelenideElement ELEMENT_QUESTION_ANSWER_CONTENT_INPUT                 = $(byXpath("/html/body"));

  public static final By ELEMENT_ANSWER_DELETE=byClassName("confirm");
  public static final By ELEMENT_COMMENT_DELETE= byClassName("confirm");

}
