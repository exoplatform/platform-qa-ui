package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_LEFT_NAVIGATION_NODE_CHECK;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_NAVIGATION_NODE_CHECK;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACE_NAME_LEFT_NAVIGATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.gatein.pageobject.NavigationManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManageSites;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

@Tag("sniff")
@Tag("plf")
public class PlfNavigationLeftNavigationTestIT extends Base {

  HomePagePlatform     homePagePlatform;

  ManageLogInOut       manageLogInOut;

  SpaceManagement      spaceManagement;

  NavigationToolbar    navigationToolbar;

  PortalManageSites    portalManageSites;

  NavigationManagement navigationManagement;

  @BeforeEach
  public void setupBeforeMethod() {

    info("Start setUpBeforeMethod");
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    portalManageSites = new PortalManageSites(this);
    navigationManagement = new NavigationManagement(this);

    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }


  /*
   * Step Number: 1 Step Name: - Connect to Intranet Step Description: - Login as
   * a user - Connect to Intranet Input Data: Expected Outcome: - The left
   * Navigation is displayed - The "COMPANY" list is displayed with applications
   * in the following order:* Home* Connections* Wiki* Documents* Forums*
   * Calendar* Other personal pages
   */
  @Test
  public void test01_ShowApplicationsInCOMPANYList() {
    info("Test 1: Show applications in COMPANY list");
    info("Verify expected outcome");
    $(ELEMENT_FORUM_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_WIKI_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_HOME_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_CALENDAR_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_CONNECTIONS_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);

  }
  /*
   * Step Number: 1 Step Name: Step 1: Check Left navigation Step Description: -
   * Login as a user - Connect to Intranet Input Data: Expected Outcome: - The
   * Left Navigation is displayed: Company List" and "My Spaces" panels Step
   * number: 2 Step Name: Step 2: Check Left navigation on other portal Step
   * Description: - Connect to other sites. Go to via url: host:port/portal/acme/.
   * Input Data: Expected Outcome: - The Left Navigation isn't displayed
   */

  @Test
  public void test02_DisplayLeftNavigationForSocialIntranet() {
    info("Test 2: Display Left Navigation for Social Intranet");

    $(ELEMENT_FORUM_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_WIKI_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_HOME_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_CALENDAR_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_CONNECTIONS_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);

  }

  /**
   * <li>Case ID:120899.</li>
   * <li>Test Case Name: Display list of spaces ordered by the last browsed.</li>
   * <li>Pre-Condition: more than two spaces exists</li>
   * <li>Post-Condition:</li>
   * <li>Done with OSs and browsers :</li>
   * <li>Generated by rosso at 2015/01/28 14:15:22</li> Step Number: 1 Step Name:
   * Connect to Intranet Step Description: - Login as john - Connect to Intranet
   * Input Data: Expected Outcome: - The Left Navigation is displayed - The "MY
   * SPACES" panel is displayed Step number: 2 Step Name: Open a space Step
   * Description: - Open a space from the list, not the first one Input Data:
   * Expected Outcome: - The space is opened Step number: 3 Step Name: Show last
   * browsed spaces on the top Step Description: - Back to the homepage Input
   * Data: Expected Outcome: - The page is refreshed - In "MY SPACES" The last
   * browsed space jump to the top of the list
   */
  @Test
  public void test03_DisplayListOfSpacesOrderedByTheLastBrowsed() {
    info("Test 3: Display list of spaces ordered by the last browsed");
    String space1 = "space1" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();
    info("Create space 1");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    info("Create space 2");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, space2);

    info("Verify that My space link is shown on left nagivation menu");
    homePagePlatform.goToHomePage();
    $(ELEMENT_MY_SPACE_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);

    info("Open space 1 in the list");
    ELEMENT_SECOND_SPACE_MY_SPACE.waitUntil(Condition.text(space1),Configuration.timeout);
    ELEMENT_SPACE_NAME_LEFT_NAVIGATION.find(byText(space1)).click();

    homePagePlatform.goToHomePage();
    ELEMENT_SECOND_SPACE_MY_SPACE.waitUntil(Condition.text(space2),Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space1,"");
    spaceManagement.deleteSpace(space1,false);
    spaceManagement.searchSpace(space2,"");
    spaceManagement.deleteSpace(space2,false);
  }

  /**
   * <li>Case ID:120897.</li>
   * <li>Test Case Name: Display Subnavigations in Group Navigation panel.</li>
   * Step Number: 1 Step Name: Add a node to group navigation Step Description: -
   * Login as amdin - Connect to Intranet - From Administration/Portal/Group
   * Sites", - Click on Edit navigation of one group - Right Click on a node,
   * Select "add node", input valid data, select page for node and click [Save]
   * Input Data: Expected Outcome: - In the Left Navigation, the Group Navigation
   * is displayed before "MY SPACES" panel - the node is displayed in the panel
   * Step number: 2 Step Name: Add sub -node to the group navigation Step
   * Description: - Add sub -node of the above node by the same way as above Input
   * Data: Expected Outcome: - In the Group Navigation panel, a small button is
   * displayed to fold/unfold sub nodes
   */

  @Test
  public void test04_DisplaySubnavigationsInGroupNavigationPanel() {
    info("Test 4: Display Subnavigations in Group Navigation panel");
    String title = "title" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();

    info("Add a note to left Navigation menu");
    navigationToolbar.goToPotalSites();
    portalManageSites.goToEditNavigation();
    navigationManagement.addNode(title, "");
    navigationManagement.saveNode();
    info("Verify that the node is added");
    ELEMENT_LEFT_NAVIGATION_NODE_CHECK.find(byText(title)).waitUntil(Condition.appears, Configuration.timeout);

    portalManageSites.goToEditNavigation();
    navigationManagement.addNode(title, title2);
    navigationManagement.saveNode();
    executeJavaScript("window.scrollBy(0,-350);", "");
    portalManageSites.goToEditNavigation();
    ELEMENT_NAVIGATION_NODE_CHECK.find(byText(title)).scrollTo().click();
    ELEMENT_NAVIGATION_NODE_CHECK.find(byText(title2)).waitUntil(Condition.appears, Configuration.timeout);
    navigationManagement.deleteNode(title);

  }

}
