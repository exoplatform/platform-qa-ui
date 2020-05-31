package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiPageInformation {

  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase TestBase
   */
  public WikiPageInformation(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
  }

  /**
   * Add a relation to a page
   *
   * @param location String
   * @param page String
   */
  public void addRelations(String location, String page) {
    info("Click on Drop down");
    $(byXpath("(//div[@class='uiAction']/button)[1]")).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_ADD_RELATED_PAGE_POPUP_DROPDOWN).waitUntil(Condition.visible,Configuration.timeout).click();
    if(!location.isEmpty()){
      info("Select a location");
      $(byXpath(ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION
              .replace("${location}",location))).waitUntil(Condition.visible,Configuration.timeout).click();
    }
    if(!page.isEmpty()){
      info("Select a page in the list");
      $(byXpath(ELEMENT_ADD_RELATED_POPUP_CONTENT
              .replace("${page}",page))).waitUntil(Condition.visible,Configuration.timeout).click();
    }
  }

    /**
     * Open add related page popup
     */
    public void goToAddRelations() {
        info("Click on Add more relations");
        $(ELEMENT_PAGE_INFO_ADD_MORE_RELATIONS).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
        $(ELEMENT_ADD_RELATED_PAGE_POPUP_TITLE).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
        info("Add related page popup is shown");
    }

    /**
     * Open Page History
     */
    public void goToPageHistory() {
        info("Click on View page info button");
        $(ELEMENT_PAGE_INFO_VIEW_PAGE_INFO_BTN).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
        $(ELEMENT_PAGE_INFO_VIEW_PAGE_INFO_BTN).click();
        info("Page history is shown");
        $(ELEMENT_WIKI_PAGE_PAGE_HISTORY_TITLE).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    }

    /**
     * View version's content of a Wiki page
     *
     * @param num int
     */
    public void viewVersion(int num) {
        info("--View a version of a page--");
        $(byXpath(ELEMENT_PAGE_INFO_RECENT_CHANGES_VERSION.replace("$num", String.valueOf(num)))).click();
        $(ELEMENT_PAGE_INFOR_VIEW_CONTENT_TITLE).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * view content of next version
     */
    public void viewConentVersionByNextArrow() {
        if ($(ELEMENT_VIEW_VERSION_NEXT_BTN, 2000).is(Condition.visible)) {
            info("Click on Next button");
            $(ELEMENT_VIEW_VERSION_NEXT_BTN).click();
        }

    }

    /**
     * View current version from View Content page
     */
    public void viewCurrentVersion() {
        info("View current version");
        $(ELEMENT_PAGE_INFO_VIEW_CONTENT_CURRENT_VERSION_LINK).click();

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
            $(byText(reversion1)).parent().parent().parent().find(byClassName("uiCheckbox")).click();

        }
        if (!reversion2.isEmpty()) {
            info("Select reversion 2");
            $(byText(reversion2)).parent().parent().parent().find(byClassName("uiCheckbox")).click();
        }
        info("Click on Compare button");
        $(ELEMENT_WIKI_PAGE_PAGE_HISTORY_COMPARE_BTN).click();

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
            $(ELEMENT_REVISION_LINK).click();
        }
        testBase.getExoWebDriver().getWebDriver().navigate().refresh();

        $(byXpath(versionLink)).click();

    }

    /**
     * Change compare versions of Compare version page
     *
     * @param firstNumberVersion  first version to compare
     * @param secondNumberVersion second version to compare
     */
    public void changeCompareVersions(String firstNumberVersion, String secondNumberVersion) {

        $(byXpath(ELEMENT_CHANGES_COMPARE_VERSION.replace("${1stNumber}", firstNumberVersion).replace("${2ndNumber}",
                secondNumberVersion))).click();
        $(byXpath(ELEMENT_CHANGES_COMPARE_VERSION.replace("${1stNumber}", firstNumberVersion)
                .replace("${2ndNumber}", secondNumberVersion))).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    }
}
