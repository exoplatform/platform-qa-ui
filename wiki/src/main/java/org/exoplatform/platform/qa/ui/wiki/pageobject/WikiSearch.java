package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiSearch {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param dr
   */
  public WikiSearch(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Open space switcher drop down
   */
  public void goToAdvancedSearchSpaceSwitcher() {
    info("Click on Drop donw");
    $(ELEMENET_ADVANCED_SEARCH_DROP_DOWN).click();

  }

  /**
   * Search a space in space switcher
   *
   * @param text
   */
  public void searchSpaces(String text) {
    if (!text.isEmpty()) {
      info("Type a text to search");
      $(ELEMENT_ADVANCED_SEARCH_FILTER).setValue(text);
    }
  }

  /**
   * Advanced search
   *
   * @param location
   */
  public void advancedSearch(String text, String location) {
    if (!location.isEmpty()) {
      goToAdvancedSearchSpaceSwitcher();
      searchSpaces(location);
      info("Select a location");
      // $(ELEMENT_ADVANCED_SEARCH_SPACE_SWITCHER).click();
      $(byClassName("wikis")).find(byText(location)).click();

    }
    if (!text.isEmpty()) {
      info("Type a text to search");
      $(ELEMENT_WIKI_ADVANCED_SEARCH_SEARCH_FIELD).setValue(text);
    }

    info("Click on Search button");
    $(ELEMENT_SEARCH_ADVANCED_SEARCH_BTN).click();

  }

  /**
   * Quick search a page
   *
   * @param text
   */
  public void quickSeach(String text) {
    if (!text.isEmpty()) {
      info("Input a text to the searched field");
      evt.type(ELEMENT_WIKI_SEARCH_FIELD, text, true);
    }
    info("Click on Search button");
    evt.click(ELEMENT_WIKI_QUICK_SEARCH_BTN);

  }

  /**
   * View detail of a page that is listed in searched results page
   *
   * @param page
   * @param contentPage
   */
  public void viewContentOfSearchedPage(String page, String contentPage) {
    if (!page.isEmpty()) {
      info("Click on the page link in searched results list");
      evt.click(ELEMENT_WIKI_SEARCH_RESULT_PAGE_LINK.replace("$page", page));
      evt.waitForAndGetElement(ELEMENT_CONTENT_WIKI_PAGE.replace("$content", contentPage), 3000, 1);
    }
  }

}
