package org.exoplatform.platform.qa.ui.selenium.platform;

import static org.exoplatform.platform.qa.ui.selenium.locator.QuickSearchResultLocator.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX;

import org.openqa.selenium.Keys;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class QuickSearchResult {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public QuickSearchResult(TestBase testBase) {

    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Search a text
   * 
   * @param textSearch
   */
  public void search(String textSearch) {
    if (!textSearch.isEmpty()) {
      evt.waitForAndGetElement(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).sendKeys(textSearch);

      testBase.getExoWebDriver().getWebDriver().findElement(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).sendKeys(Keys.ENTER);
    } else
      assert false : "Not input a text to search";
  }

}
