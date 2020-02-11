package org.exoplatform.platform.qa.ui.answer.pageobject;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class AnswerHomePage {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param testBase
   */
  public AnswerHomePage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Go to home category
   */
  public void goToHomeCategory() {
    refresh();
    if (ELEMENT_ICON_GO_TO_HOME_CATEGORIE.is(Condition.exist)) {
      info("Go to home category");
      ELEMENT_ICON_GO_TO_HOME_CATEGORIE.click();
    }
  }

  /**
   * Do quick search
   * 
   * @param key
   */
  public void doQuickSearch(String key) {
    info("Do quick search");
    $(ELEMENT_QUICK_SEARCH_INPUT).setValue(key);
    $(ELEMENT_QUICK_SEARCH_BUTTON).click();
    $(ELEENT_QUICK_SEARCH_POPUP).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Go to advance search
   */
  public void goToAdvanceSearch() {
    info("Go to advance search");
    $(ELEMENT_QUICK_SEARCH_INPUT).setValue("search");
    $(ELEMENT_QUICK_SEARCH_BUTTON).click();
    $(ELEENT_QUICK_SEARCH_POPUP).waitUntil(Condition.appears, Configuration.timeout);
    $(byXpath(ELEMENT_QUICK_SEARCH_ADVANCE_SEARCH_BUTTON)).click();
    $(ELEMENT_ADVANCE_SEARCH_POPUP).waitUntil(Condition.appears, Configuration.timeout);
  }

}
