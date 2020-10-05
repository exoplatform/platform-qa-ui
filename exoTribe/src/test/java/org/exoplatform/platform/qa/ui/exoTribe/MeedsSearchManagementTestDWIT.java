package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.pageobject.TribeUserProfilePage;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_USER_SEARCH_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_ICON_SEARCH;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_SEARCH_INPUT_DW;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("tribe")
@Tag("tribeMeedsSearch")
public class MeedsSearchManagementTestDWIT extends BaseTribe {
  NavigationToolbar navigationToolbar;

  AddUsers addUsers;

  ManageLogInOut manageLogInOut;

  ConnectionsManagement connectionsManagement;

  HomePagePlatform homePagePlatform;

  TribeUserProfilePage tribeUserProfilePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    tribeUserProfilePage = new TribeUserProfilePage(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signInTribe(tribe_username1, tribe_password1);

  }

  @Test
  public void test01_CheckThatPeopleCardsAreDisplayed() {

    ELEMENT_ICON_SEARCH.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SEARCH_INPUT_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(tribe_user.split(" ")[1]);

    info("The Searched user " + tribe_username + " is displayed");
    $(byXpath(ELEMENT_USER_SEARCH_TITLE.replace("${user}", tribe_user))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();

    info("The Searched user " + tribe_username4 + " is displayed");
    $(byXpath(ELEMENT_USER_SEARCH_TITLE.replace("${user}", tribe_user4))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).isDisplayed();
    Assert.assertEquals($(byXpath(ELEMENT_USER_SEARCH_TITLE.replace("${user}", tribe_user4))).getAttribute("href"), getExoWebDriver().getBaseUrl() + "portal/dw/profile/" + tribe_username4);

    homePagePlatform.goToSnapshotPageTribeViaUrl();

  }

}
