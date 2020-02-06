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
      $(byClassName("wikis")).find(byText(location)).click();

    }
    if (!text.isEmpty()) {
      info("Type a text to search");
      $(ELEMENT_WIKI_ADVANCED_SEARCH_SEARCH_FIELD).setValue(text);
    }

    info("Click on Search button");
    $(ELEMENT_SEARCH_ADVANCED_SEARCH_BTN).click();

  }

}
