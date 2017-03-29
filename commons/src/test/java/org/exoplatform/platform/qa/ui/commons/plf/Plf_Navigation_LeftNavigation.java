package org.exoplatform.platform.qa.ui.commons.plf;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.core.context.Smoke;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_RESULT_SEARCH_SPACE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_SEARCH_SPACE;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_NAME_INPUT;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_PANEL;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * @author eXo
 */
public class Plf_Navigation_LeftNavigation extends Base {

  HomePagePlatform homePagePlatform;

  SpaceManagement spaceManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
  }

  /**
   * <li> Case ID:120898.</li>
   * <li> Test Case Name: Open a Space.</li>
   */
  @Test
  @Smoke
  @Tag("smoke")
  public void test03_OpenASpace() {
    info("Test 3: Open a Space");
    /*Step Number: 1
     *Step Name: Connect to intranet
		 *Step Description: 
			- Login as a normal user
			- Connect to Intranet
			- Open an application from "COMPANY"
		 *Input Data: 

		 *Expected Outcome: 
			- The left Navigation is displayed
			- The application is opened*/

		/*Step number: 2
		 *Step Name: Open a space
		 *Step Description: 
			- Open a space from "MY SPACES"
		 *Input Data: 

		 *Expected Outcome: 
			The space is opened in the Home space's stream*/

    homePagePlatform.goToMySpaces();
    waitForAndGetElement(ELEMENT_SPACE_PANEL).isDisplayed();

  }

  /**
   * <li> Case ID:120900.</li>
   * <li> Test Case Name: Search space in "MY SPACES".</li>
   * <li> Pre-Condition: spaces exists</li>
   */
  @Test
  @Smoke
  @Tag("smoke")
  public void test05_SearchSpaceInMYSPACES() {
    info("Test 5: Search space in MY SPACES");
    String space1 = "space1" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1 + " - description");
    $(ELEMENT_SPACE_NAME_INPUT).waitUntil(Condition.disappear,10000);

    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2 + " - description");
    $(ELEMENT_SPACE_NAME_INPUT).waitUntil(Condition.disappear,10000);
		/*Step Number: 1
		 *Step Name: Connect to intranet
		 *Step Description: 
			- Login as an user
			- Connect to Intranet
		 *Input Data: 

		 *Expected Outcome: 
			- The Left Navigation is displayed
			- The "MY SPACES" panel is displayed
			- Search filter is displayed*/

		/*Step number: 2
		 *Step Name: Search by inputting one letter
		 *Step Description: 
			- Input a letter "a" in the search box under My spaces label
		 *Input Data: 

		 *Expected Outcome: 
			- All spaces having a word containing with the inputed letter are displayed*/
    type(ELEMENT_SEARCH_SPACE, "spa");
    waitForAndGetElement(By.xpath(ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}", space1))).isDisplayed();
    waitForAndGetElement(By.xpath(ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}", space2))).isDisplayed();
/*
    homePagePlatform.goToHomePage();

    type(ELEMENT_SEARCH_SPACE, "ah", false);
    waitForAndGetElement(ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}", space2), 3000, 0);
    waitForElementNotPresent(ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}", space1), 3000, 0);
		/*Step number: 3
		 *Step Name: Search by inputting two letters
		 *Step Description: 
			- Input a second letter "b"
		 *Input Data: 

		 *Expected Outcome: 
			- Only spaces containing "ab" are displayed*/
/*
    info("Delete spaces");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);
    spaceManagement.deleteSpace(space2, false);
    */
  }
}
