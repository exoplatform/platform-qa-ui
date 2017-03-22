package org.exoplatform.platform.qa.ui.selenium.platform.wiki;

import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiPageInformation {
  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param dr
   */
  public WikiPageInformation(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
  }

  /**
   * Add a relation to a page
   * 
   * @param location
   * @param page
   */
  public void addRelations(String location, String page) {
    info("Click on Drop down");
    evt.click(ELEMENT_ADD_RELATED_PAGE_POPUP_DROPDOWN);
    Utils.pause(2000);
    if (!location.isEmpty()) {
      info("Select a location");
      evt.click(ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION.replace("${location}", location));
    }
    if (!page.isEmpty()) {
      info("Select a page in the list");
      evt.click(ELEMENT_ADD_RELATED_POPUP_CONTENT.replace("${page}", page));
    }
    info("Save all changes");
    evt.click(ELEMENT_ADD_RELATED_POPUP_SELECT_BTN);
  }

  /**
   * Open add related page popup
   */
  public void goToAddRelations() {
    info("Click on Add more relations");
    evt.click(ELEMENT_PAGE_INFO_ADD_MORE_RELATIONS);
    evt.waitForAndGetElement(ELEMENT_ADD_RELATED_PAGE_POPUP_TITLE);
    info("Add related page popup is shown");
  }

  /**
   * Open Page History
   */
  public void goToPageHistory() {
    info("Click on View page info button");
    evt.waitForAndGetElement(ELEMENT_PAGE_INFO_VIEW_PAGE_INFO_BTN, 2000, 0).click();
    info("Page history is shown");
    evt.waitForAndGetElement(ELEMENT_WIKI_PAGE_PAGE_HISTORY_TITLE, 2000, 0);
  }

  /**
   * Delete a relation of a page
   * 
   * @param relation
   */
  public void deleteRelation(String relation) {
    info("Click on Delete button");
    evt.waitForAndGetElement(ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN.replace("${name}", relation), 2000, 0);
    evt.click(ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN.replace("${name}", relation));
    alert.acceptAlert();
    evt.waitForElementNotPresent(ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN.replace("${name}", relation));
    info("The relation is deleted");
  }

  /**
   * Delete a relation of a page
   * 
   * @param relation
   */
  public void deleteRelationWithCancelDeleting(String relation) {
    info("Click on Delete button");
    evt.waitForAndGetElement(ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN.replace("${name}", relation), 2000, 0);
    evt.click(ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN.replace("${name}", relation));
    alert.cancelAlert();
    evt.waitForAndGetElement(ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN.replace("${name}", relation));
    info("The relation isnot deleted");
  }

  /**
   * Verify that related page is viewed
   * 
   * @param page
   */
  public void viewRelatedPageContent(String page) {
    info("Click on related page");
    evt.click(ELEMENT_PAGE_INFO_RELATED_PAGE_LINK.replace("$page", page));
    info("Verify that related page's content is shown");
    evt.waitForAndGetElement(ELEMENT_WIKI_HOME_PAGE_TITLE.replace("${title}", page));
  }

  /**
   * View version's content of a Wiki page
   * 
   * @param num
   */
  public void viewVersion(int num) {
    info("--View a version of a page--");
    evt.click(ELEMENT_PAGE_INFO_RECENT_CHANGES_VERSION.replace("$num", String.valueOf(num)));
    evt.waitForAndGetElement(ELEMENT_PAGE_INFOR_VIEW_CONTENT_TITLE);
  }

  /**
   * view content of next version
   */
  public void viewConentVersionByNextArrow() {
    if (evt.waitForAndGetElement(ELEMENT_VIEW_VERSION_NEXT_BTN, 2000, 0) != null) {
      info("Click on Next button");
      evt.click(ELEMENT_VIEW_VERSION_NEXT_BTN);
    }
    Utils.pause(2000);

  }

  /**
   * View content of previous version
   */
  public void viewContentVersionByPreviousArrow() {
    if (evt.waitForAndGetElement(ELEMENT_VIEW_VERSION_PREVIOUS_BTN, 2000, 0) != null) {
      info("Click on Previous button");
      evt.click(ELEMENT_VIEW_VERSION_PREVIOUS_BTN);
    }
    Utils.pause(2000);
  }

  /**
   * View current version from View Content page
   */
  public void viewCurrentVersion() {
    info("View current version");
    evt.click(ELEMENT_PAGE_INFO_VIEW_CONTENT_CURRENT_VERSION_LINK);
    Utils.pause(2000);
  }

  /**
   * Compare 2 reversion
   * 
   * @param reversion1 as v.1
   * @param reversion2 as v.2
   */
  public void compareTwoReversion(String reversion1, String reversion2) {
    if (!reversion1.isEmpty()) {
      info("Select reversion 1");
      evt.check(ELEMENT_WIKI_PAGE_PAGE_HISTORY_CHECKBOX.replace("${reversion}", reversion1), 2);
    }
    if (!reversion2.isEmpty()) {
      info("Select reversion 2");
      evt.check(ELEMENT_WIKI_PAGE_PAGE_HISTORY_CHECKBOX.replace("${reversion}", reversion2), 2);
    }
    info("Click on Compare button");
    evt.click(ELEMENT_WIKI_PAGE_PAGE_HISTORY_COMPARE_BTN);

  }

  /**
   * Restore version of a Wiki page
   * 
   * @param version number of version
   */
  public void restoreVersion(String version) {
    info("--Restore a version of a page--");
    String versionLink = ELEMENT_RESTORE_LINK.replace("{$version}", version);
    if (evt.isTextPresent("Page History")) {
      info("-- You are currently in the revision page --");
    } else {
      evt.click(ELEMENT_REVISION_LINK);
    }
    Utils.pause(500);
    testBase.getSeleniumDriver().navigate().refresh();
    Utils.pause(2000);
    evt.click(versionLink);
    Utils.pause(1000);
  }

  /**
   * Change compare versions of Compare version page
   * 
   * @param firstNumberVersion first version to compare
   * @param secondNumberVersion second version to compare
   */
  public void changeCompareVersions(String firstNumberVersion, String secondNumberVersion) {
    Utils.pause(1000);
    evt.click(ELEMENT_CHANGES_COMPARE_VERSION.replace("${1stNumber}", firstNumberVersion).replace("${2ndNumber}",
                                                                                                  secondNumberVersion));
    evt.waitForElementNotPresent(ELEMENT_CHANGES_COMPARE_VERSION.replace("${1stNumber}", firstNumberVersion)
                                                                .replace("${2ndNumber}", secondNumberVersion));
  }
}
