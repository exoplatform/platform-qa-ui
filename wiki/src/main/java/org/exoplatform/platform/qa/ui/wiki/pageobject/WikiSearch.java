package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiSearch {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase TestBase
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
   * @param text String
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
   * @param location String
   * @param text  String
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
   * @param text String
   */
  public void quickSeach(String text) {
    if (!text.isEmpty()) {
      info("Input a text to the searched field");
      $(ELEMENT_WIKI_SEARCH_FIELD).setValue(text).waitUntil(Condition.visible,Configuration.timeout);
    }
    info("Click on Search button");
    $(ELEMENT_WIKI_QUICK_SEARCH_BTN).click();

  }

  /**
   * View detail of a page that is listed in searched results page
   *
   * @param page String
   * @param contentPage String
   */
  public void viewContentOfSearchedPage(String page, String contentPage) {
    if (!page.isEmpty()) {
      info("Click on the page link in searched results list");
     $(byXpath(ELEMENT_WIKI_SEARCH_RESULT_PAGE_LINK.replace("$page", page))).click();
     $(byXpath(ELEMENT_CONTENT_WIKI_PAGE.replace("$content", contentPage))).waitUntil(Condition.visible,Configuration.timeout);
    }
  }

}
