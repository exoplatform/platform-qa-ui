package org.exoplatform.platform.qa.ui.selenium.platform.answer;

import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class AnswerHomePage {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param dr
   */
  public AnswerHomePage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Go to home category
   */
  public void goToHomeCategory() {
    if (evt.waitForAndGetElement(ELEMENT_CATEGORY_BREADCUMB_HOME, 5000, 0) != null) {
      info("Go to home category");
      evt.click(ELEMENT_CATEGORY_BREADCUMB_HOME);
    }
    evt.waitForElementNotPresent(ELEMENT_CATEGORY_BREADCUMB_HOME);
  }

  /**
   * Do quick search
   * 
   * @param key
   */
  public void doQuickSearch(String key) {
    info("Do quick search");
    evt.type(ELEMENT_QUICK_SEARCH_INPUT, key, true);
    evt.click(ELEMENT_QUICK_SEARCH_BUTTON);
    evt.waitForAndGetElement(ELEENT_QUICK_SEARCH_POPUP);
  }

  /**
   * Go to advance search
   */
  public void goToAdvanceSearch() {
    info("Go to advance search");
    evt.type(ELEMENT_QUICK_SEARCH_INPUT, "search", true);
    evt.click(ELEMENT_QUICK_SEARCH_BUTTON);
    evt.waitForAndGetElement(ELEENT_QUICK_SEARCH_POPUP);
    evt.click(ELEMENT_QUICK_SEARCH_ADVANCE_SEARCH_BUTTON);
    evt.waitForAndGetElement(ELEMENT_ADVANCE_SEARCH_POPUP);
  }

  /**
   * Open a detail question
   * 
   * @param question
   */
  public void goToQuestion(String question) {
    info("Click on Question link");
    evt.click(ELEMENT_QUESTION_LINK.replace("$question", question));

  }
}
