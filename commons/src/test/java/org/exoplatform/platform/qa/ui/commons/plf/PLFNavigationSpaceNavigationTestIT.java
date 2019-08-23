package org.exoplatform.platform.qa.ui.commons.plf;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

public class PLFNavigationSpaceNavigationTestIT extends Base {

  HomePagePlatform homePagePlatform;

  SpaceManagement  spaceManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
  }

  /**
   * <li>Case ID:5XX-TC-19722.</li>
   * <li>Test Case Name: Show the space menu</li>
   * <li>Pre-Condition: a space is created</li>
   */
  @Test
  @Tag("smoke")
  public void test01_ShowTheSpaceMenu() {
    info("Test 1: Show the space menu");
    String space1 = "space" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, "Case ID:5XX-TC-19722");

    /*
     * Step Number: 1 Step Name: Show space applications Step Description: - Connect
     * to Intranet - Open a Space Input Data: Expected Outcome: - The Horizontal
     * toolbar is displayed - On the left of the Space toolbar, we display the icon
     * and name of the current space. - All applications dedicated to the space are
     * shown on space menu - Click on each applications, the application will show
     * up in the main page
     */
    info("Verify the expected outcome");

    // Check the space menu portlet exists
    SelenideElement spaceMenuPortlet = $(ELEMENT_SPACE_MENU_PORTLET).should(exist);

    // Check space title is here
    spaceMenuPortlet.find(".spaceMenuNav h3").should(have(exactText(space1)));

    // Check space avatar is here and visible
    spaceMenuPortlet.find(".userAvt img").shouldBe(visible);

    // Check the space menu contains 8 applications
    ElementsCollection spaceMenuApps = ELEMENT_SPACE_MENU_APPLICATIONS.shouldHaveSize(8);

    // Check Home application exists
    ELEMENT_SPACE_MENU_HOME.should(exist);

    // Check Document application exists
    ELEMENT_SPACE_MENU_DOCUMENTS.should(exist);

    // Check Tasks application menu exists
    ELEMENT_SPACE_MENU_TASKS.should(exist);

    // Check Forums application menu exists
    ELEMENT_SPACE_MENU_FORUMS.should(exist);

    // Check Wiki application menu exists
    ELEMENT_SPACE_MENU_WIKI.should(exist);

    // Check Calendar application menu exists
    ELEMENT_SPACE_MENU_CALENDAR.should(exist);

    // Check Space Members application menu exists
    ELEMENT_SPACE_MENU_MEMBERS.should(exist);

    // Check Space Settings application menu exists
    ELEMENT_SPACE_MENU_SETTINGS.should(exist);

    info("Delete the space");
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space1, false);

  }

}
