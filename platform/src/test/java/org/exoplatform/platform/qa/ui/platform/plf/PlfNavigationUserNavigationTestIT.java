package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONTENT_PEOPLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_SUGGESTION_BOX;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_TOPBAR_AVATAR;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_MY_PROFILE_LINK;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

/**
 * @author eXo
 */
@Tag("plf")
@Tag("sniff")
public class PlfNavigationUserNavigationTestIT extends Base {
  ManageLogInOut manageLogInOut;
  HomePagePlatform homePagePlatform;
  ConnectionsManagement connectionsManagement;
  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform=new HomePagePlatform(this);
    connectionsManagement=new ConnectionsManagement(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }

    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:120903.</li>
   * <li>Test Case Name: User navigate another user's personal pages.</li> /*Step
   * Number: 1 Step Name: Show a list of applications of another user Step
   * Description: - Connect to Intranet - Openpersonal page of another user, by
   * click on the link of his profile's namefrom activity stream, or connection
   * Input Data: Expected Outcome: - The personal page of user is displayed - The
   * Horizontal toolbar is displayed - The list of applications of space are
   * displayed in the following order:* Profile* Activity Stream* Connections*
   * Wiki - Click on each applications, the application will show up in the main
   * page
   */
  @Test
  public void test01_UserNavigateAnotherUsersPersonalPages() {
    info("Test 1: User navigate another user's personal pages");

   homePagePlatform.goToConnections();
   connectionsManagement.searchPeople(PLFData.DATA_USER2,"","","");
    ELEMENT_CONTENT_PEOPLE.find(byText(PLFData.DATA_NAME_USER2)).click();
    ELEMENT_CONTENT_NAME_PROFILE.find(byText(PLFData.DATA_NAME_USER2)).should(Condition.exist);
    waitForAndGetElement(ELEMENT_HORIZONTAL_TOOLBAR);
    waitForAndGetElement(ELEMENT_HORIZONTAL_TOOLBAR_FIRST_APP_PROFILE);
    waitForAndGetElement(ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES);
    waitForAndGetElement(ELEMENT_HORIZONTAL_TOOLBAR_THIRD_APP_CONNECTIONS);
    waitForAndGetElement(ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI);
  }

  /**
   * <li>Case ID:120904.</li>
   * <li>Test Case Name: User navigates in his own personal pages.</li> /*Step
   * Number: 1 Step Name: Show personal applications Step Description: - Login as
   * an user - Connect to Intranet - Mouse over on User name on top navigation,
   * the select "My Profile" link Input Data: Expected Outcome: - The Horizontal
   * toolbar is displayed - The list of applications of space are displayed in the
   * following order:* My Profile* My Activity Stream* My Connections* My Wiki* My
   * Dashboard - Click on each applications, the application will show up in the
   * main page
   */
  @Test
  public void test02_UserNavigatesInHisOwnPersonalPages() {
    info("Test 2: User navigates in his own personal pages");

    click(ELEMENT_TOPBAR_AVATAR);
    click(ELEMENT_MY_PROFILE_LINK);
    ELEMENT_CONTENT_NAME_PROFILE.find(byText(PLFData.DATA_NAME_USER1)).should(Condition.exist);
    waitForAndGetElement(ELEMENT_HORIZONTAL_TOOLBAR);
    waitForAndGetElement(ELEMENT_HORIZONTAL_TOOLBAR_FIRST_APP_PROFILE);
    waitForAndGetElement(ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES);
    waitForAndGetElement(ELEMENT_HORIZONTAL_TOOLBAR_THIRD_APP_CONNECTIONS);
    waitForAndGetElement(ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI);
    waitForAndGetElement(ELEMENT_HORIZONTAL_TOOLBAR_FIFTH_APP_DASHBOARD);
  }
}
