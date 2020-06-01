package org.exoplatform.platform.qa.ui.platform.plf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.NavigationManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManageSites;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byId;
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
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("sniff")
@Tag("plf")
public class PlfNavigationLeftNavigationTestIT extends Base {

  HomePagePlatform homePagePlatform;

  ManageLogInOut manageLogInOut;

  SpaceManagement spaceManagement;

  NavigationToolbar navigationToolbar;

  PortalManageSites portalManageSites;

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

  @Test
  public void test01_DisplaySubnavigationsInGroupNavigationPanelThenListOfSpacesOrderedByTheLastBrowsed() {

    String space1 = "space1" + getRandomNumber();
    String space2 = "space2" + getRandomNumber();
    String title = "title" + getRandomNumber();
    String title2 = "title2" + getRandomNumber();

    info("Check display of Who's online gadget");
    ELEMENT_WHO_ON_LINE_GADGET.should(Condition.exist);
    info("Show information of user");
    ELEMENT_WHO_ON_LINE_GADGET.hover();
    $(byId("tiptip_content")).should(Condition.exist);

    info("Display Left Navigation for Social Intranet");
    $(ELEMENT_FORUM_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_WIKI_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_HOME_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_CALENDAR_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_CONNECTIONS_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);

    info("Display Subnavigations in Group Navigation panel");
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

    info("Display list of spaces ordered by the last browsed");
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
    ELEMENT_SECOND_SPACE_MY_SPACE.waitUntil(Condition.hasText(space1), Configuration.timeout);
    ELEMENT_SPACE_NAME_LEFT_NAVIGATION.find(byText(space1)).click();

    homePagePlatform.goToHomePage();
    ELEMENT_SECOND_SPACE_MY_SPACE.waitUntil(Condition.hasText(space2), Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space1, "");
    spaceManagement.deleteSpace(space1, false);
    spaceManagement.searchSpace(space2, "");
    spaceManagement.deleteSpace(space2, false);

  }

}
