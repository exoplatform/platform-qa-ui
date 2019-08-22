package org.exoplatform.platform.qa.ui.commons.plf;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.CollectionCondition;
import org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

/**
 * @author eXo
 */
@Tag("smoke")

public class PLFNavigationLeftNavigationTestIT extends Base {

  HomePagePlatform homePagePlatform;

  SpaceManagement  spaceManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
  }

  /**
   * <li>Case ID:120898.</li>
   * <li>Test Case Name: Open a Space.</li>
   */
  @Test
  public void test03_OpenASpace() {
    info("Test 3: Open a Space");
    String space1 = "space1" + getRandomNumber();

    /*
     * Step Number: 1 Step Name: Connect to intranet Step Description: - Login as a
     * normal user - Connect to Intranet - Open an application from "COMPANY" Input
     * Data: Expected Outcome: - The left Navigation is displayed - The application
     * is opened
     */
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1 + " - description");

    /*
     * Step number: 2 Step Name: Open a space Step Description: - Open a space from
     * "MY SPACES" Input Data: Expected Outcome: The space is opened in the Home
     * space's stream
     */
    spaceManagement.goToSpaceByUrl(space1);
    $(SocialLocator.ELEMENT_SPACE_PANEL).should(exist);
    SocialLocator.ELEMENT_SPACE_MENU_HOME.should(exist).shouldHave(cssClass("active"));
    SocialLocator.ELEMENT_ACTIVITY_STREAM_PORTLET.should(exist);
    /*
     * Assertions.assertFalse(waitForAndGetElement(ELEMENT_SPACE_PANEL).isDisplayed(
     * )); Assertions.assertFalse(true);
     */
    // $(ELEMENT_SPACE_PANEL).waitUntil(Condition.appears,10000);
    info("Delete spaces");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);

  }

  /**
   * <li>Case ID:120900.</li>
   * <li>Test Case Name: Search space in "MY SPACES".</li>
   * <li>Pre-Condition: spaces exists</li>
   */
  @Test
  public void test05_SearchSpaceInMYSPACES() {
    info("Test 5: Search space in MY SPACES");
    String space1 = "space1" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1 + " - description");

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2 + " - description");
    /*
     * Step Number: 1 Step Name: Connect to intranet Step Description: - Login as an
     * user - Connect to Intranet Input Data: Expected Outcome: - The Left
     * Navigation is displayed - The "MY SPACES" panel is displayed - Search filter
     * is displayed
     */

    /*
     * Step number: 2 Step Name: Search by inputting one letter Step Description: -
     * Input a letter "a" in the search box under My spaces label Input Data:
     * Expected Outcome: - All spaces having a word containing with the inputed
     * letter are displayed
     */
    ELEMENT_SEARCH_SPACE.setValue("spa");
    // waitForAndGetElement(By.xpath(ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}",
    // space1))).isDisplayed();
    // waitForAndGetElement(By.xpath(ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}",
    // space2))).isDisplayed();

//    $(byText(space1)).waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_SEARCH_SPACE_RESULTS.filterBy(exactText(space1)).shouldBe(size(1));
//    $(byText(space2)).waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_SEARCH_SPACE_RESULTS.filterBy(exactText(space2)).shouldBe(size(1));
    /*
     * homePagePlatform.goToHomePage(); type(ELEMENT_SEARCH_SPACE, "ah", false);
     * waitForAndGetElement(ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}", space2),
     * 3000, 0);
     * waitForElementNotPresent(ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}",
     * space1), 3000, 0); /*Step number: 3 Step Name: Search by inputting two
     * letters Step Description: - Input a second letter "b" Input Data: Expected
     * Outcome: - Only spaces containing "ab" are displayed
     */

    info("Delete spaces");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
    spaceManagement.deleteSpace(space2, false);

  }

  @Test
  @Tag("search")
  public void test02_SearchSpaceFromHomeSearchBar() {
    String space = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();
    String space3 = "space" + getRandomNumber();
    info("add 3 spaces");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space3, space3);
    info("search space");
    homePagePlatform.goToHomePage();
    ELEMENT_ICON_SEARCH.click();
    ELEMENT_SEARCH_INPUT.setValue(space2);
    ELEMENT_SEARCH_RESULT.shouldHave(Condition.exactText(space2));
    info("delete data");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    spaceManagement.deleteSpace(space2, false);
    spaceManagement.deleteSpace(space3, false);
  }
}
