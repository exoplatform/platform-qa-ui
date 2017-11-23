package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class AnswerPage {
  private final TestBase       testBase;

  public NavigationToolbar     navTool;

  public PageCreationWizard    pagMang;

  public PageEditor            pagEditor;

  public HomePagePlatform      hp;

  public ManageAlert           alert;

  public ApplicationRegistry   arPage;

  private ElementEventTestBase evt;

  public AnswerPage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.navTool = new NavigationToolbar(testBase);
    this.pagMang = new PageCreationWizard(testBase);
    this.pagEditor = new PageEditor(testBase);
    this.hp = new HomePagePlatform(testBase);
    this.alert = new ManageAlert(testBase);
    this.arPage = new ApplicationRegistry(testBase);
  }

  /**
   * Create Answer Page
   */
  public void createAnswerPage() {
    info("Show all import application");
    arPage.displayImportApplicaions();
    arPage.importAllApplications();
    hp.goToHomePage();

    info("Create Answer Page");
    navTool.goToAddPage();
    evt.click(ELEMENT_PAGE_UP_LEVEL);
    pagMang.inputPageInfoStep1("Answers", null, null, "Answers", null, null);
    evt.click(ELEMENT_PAGE_NEXT_BUTTON);
    evt.click(ELEMENT_PAGE_NEXT_BUTTON);
    pagEditor.selectApplication("answer-AnswersPortlet", ELEMENT_EDIT_PAGE_PAGE);
    evt.click(ELEMENT_PAGE_EDITOR_VIEW_PAGE_PROPERTIES);
    evt.click(ELEMENT_PERMISSTION_SETTING_TAB);
    evt.check(ELEMENT_PUBLIC_MODE, 2);
    evt.click(ELEMENT_PAGE_EDITOR_SAVE_BUTTON);
    pagEditor.finishEditLayout();

  }

  /**
   * function go to edit answer portlet in answer page
   */
  public void goToEditAnswerPortlet() {
    hp.goToAnswer();
    info("Go to edit answer portlet");
    navTool.goToEditLayout();
    pagEditor.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);

  }

  /**
   * @param isCheckMode Boolean
   */
  public void doPublicMode(Boolean isCheckMode) {
    info("Check public mode or not");
    evt.click(ELEMENT_ANSWER_PERMISSION_TAB);
    if (isCheckMode != null) {
      if (isCheckMode)
        evt.check(ELEMENT_ANSWER_PERMISSION_TAB_PUBLIC_MODE, 2);
      else
        evt.uncheck(ELEMENT_ANSWER_PERMISSION_TAB_PUBLIC_MODE, 2);
    }

  }

  /**
   * function setting display for categories in answer portlet
   *
   * @param categoryScope String
   * @param display Boolean
   */
  public void setDisplayCategoryScoping(String categoryScope, Boolean display) {
    info("setting display for categories in answer portlet");
    String[] cat = categoryScope.split("/");
    evt.clickByJavascript(ELEMENT_CATEGORY_SCOPING_TAB);
    for (int i = 0; i < cat.length; i++) {
      if (display != null) {
        if (display) {
          evt.check(ELEMENT_CATEGORY_IN_SCOPE_TAB.replace("${catName}", cat[i]), 2);
        } else {
          evt.uncheck(ELEMENT_CATEGORY_IN_SCOPE_TAB.replace("${catName}", cat[i]), 2);
        }
      }
    }
    evt.click(ELEMENT_EDIT_SAVE_BUTTON);
    evt.click(ELEMENT_PAGE_OK_BUTTON);
  }

  /**
   * select forum in caltegory
   *
   * @param forumPath (Ex: cat1/forum1)
   */
  public void selectForumCategory(String forumPath) {
    info("select forum in caltegory");
    String[] item = forumPath.split("/");
    if (item.length > 1) {
      for (int i = 0; i < item.length - 1; i++) {
        if (evt.waitForAndGetElement(ELEMENT_CATEGORY_COLLAPSE_ITEM.replace("$name", item[i]), 5000, 0) != null) {
          evt.click(ELEMENT_CATEGORY_COLLAPSE_ITEM.replace("$name", item[i]));
          evt.waitForAndGetElement(ELEMENT_CATEGORY_EXPAND_ITEM.replace("$name", item[i]));
        }
      }
      evt.click(ELEMENT_CATEGORY_NODE_ITEM.replace("$name", item[item.length - 1]));
    }
  }

  /**
   * Function setting discussion in answer portlet
   *
   * @param discuss boolean
   * @param forumPath String
   */
  public void settingDiscussion(Boolean discuss, String forumPath) {
    info("setting discussion in answer portlet");
    evt.click(ELEMENT_DISCUSSION_TAB);
    if (discuss != null) {
      if (discuss) {
        evt.check(ELEMENT_ENABLE_DISCUSSION_CHECKBOX, 2);
        evt.click(ELEMENT_ADD_FORUM);
        selectForumCategory(forumPath);
      } else {
        evt.uncheck(ELEMENT_ENABLE_DISCUSSION_CHECKBOX, 2);
      }
    }
    evt.click(ELEMENT_EDIT_SAVE_BUTTON);
    evt.click(ELEMENT_PAGE_OK_BUTTON);

  }

  /**
   * function setting email template for answser portlet
   *
   * @param tab int
   * @param content String
   */
  public void settingEmailTemplate(int tab, String content) {
    info("setting email template for answser portlet");
    evt.click(ELEMENT_MAIL_NOTIFICATION_TEMPLATE_TAB);
    switch (tab) {
    case 1:
      evt.click(ELEMENT_MAIL_NEW_QUESTION_TAB);
      evt.inputDataToCKEditor(ELEMENT_MAIL_NEW_QUESTION_FRAME, content);
      break;
    case 2:
      evt.click(ELEMENT_MAIL_EDIT_ANSWER_TAB);
      evt.inputDataToCKEditor(ELEMENT_MAIL_EDIT_ANSWER_FRAME, content);
      break;
    case 3:
      evt.click(ELEMENT_MAIL_MOVE_QUESTION_TAB);
      evt.inputDataToCKEditor(ELEMENT_MAIL_MOVE_QUESTION_FRAME, content);
      break;
    case 4:
      evt.click(ELEMENT_MAIL_NEW_QUESTION_TAB);
      evt.click(ELEMENT_NEW_QUESTION_RELOAD_DEFAULT_EMAIL);
      break;
    case 5:
      evt.click(ELEMENT_MAIL_EDIT_ANSWER_TAB);
      evt.click(ELEMENT_EDIT_ANSWER_RELOAD_DEFAULT_EMAIL);
      break;
    case 6:
      evt.click(ELEMENT_MAIL_MOVE_QUESTION_TAB);
      evt.click(ELEMENT_MOVE_QUESTION_RELOAD_DEFAULT_EMAIL);
      break;
    }
    evt.switchToParentWindow();
    evt.click(ELEMENT_EDIT_SAVE_BUTTON);
    evt.click(ELEMENT_PAGE_OK_BUTTON);
  }

  /**
   * function setting display mode in answer portlet
   *
   * @param all Boolean
   * @param date Boolean
   * @param ascending Boolean
   * @param isVote Boolean
   * @param isSubmit Boolean
   * @param rss Boolean
   * @param avatar Boolean
   * @param allowUser  Boolean
   */
  public void settingDisplayMode(Boolean all,
                                 Boolean date,
                                 Boolean ascending,
                                 Boolean isVote,
                                 Boolean isSubmit,
                                 Boolean rss,
                                 Boolean avatar,
                                 Boolean allowUser) {
    info("setting display mode in answer portlet");
    evt.click(ELEMENT_DISPLAY_MODE_TAB);
    if (all != null) {
      if (all) {
        evt.select(ELEMENT_SELECT_DISPLAY_MODE, "All");
      } else {
        evt.select(ELEMENT_SELECT_DISPLAY_MODE, "Approved");
      }
    }
    if (date != null) {
      if (date) {
        evt.select(ELEMENT_SELECT_ORDER_BY_MODE, "Created Date");
      } else {
        evt.select(ELEMENT_SELECT_ORDER_BY_MODE, "Alphabet/Index");
      }
    }
    if (ascending != null) {
      if (ascending) {
        evt.select(ELEMENT_SELECT_DIRECTION_MODE, "Ascending order");
      } else {
        evt.select(ELEMENT_SELECT_DIRECTION_MODE, "Descending order");
      }
    }
    if (isVote != null) {
      if (isVote) {
        evt.check(ELEMENT_ENABLE_VOTE_COMMENT, 2);
      } else {
        evt.uncheck(ELEMENT_ENABLE_VOTE_COMMENT, 2);
      }
    }
    if (rss != null) {
      if (rss) {
        evt.check(ELEMENT_ENABLE_RSS, 2);
      } else {
        evt.uncheck(ELEMENT_ENABLE_RSS, 2);
      }
    }
    if (avatar != null) {
      if (avatar) {
        evt.check(ELEMENT_VIEW_AVATAR, 2);
      } else {
        evt.uncheck(ELEMENT_VIEW_AVATAR, 2);
      }
    }
    if (allowUser != null) {
      if (avatar) {
        evt.check(ELEMENT_POST_QUESTION_IN_ROOT, 2);
      } else {
        evt.uncheck(ELEMENT_POST_QUESTION_IN_ROOT, 2);
      }
    }
    evt.click(ELEMENT_EDIT_SAVE_BUTTON);
    evt.click(ELEMENT_PAGE_OK_BUTTON);
  }
}
