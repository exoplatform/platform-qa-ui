package org.exoplatform.platform.qa.ui.platform.plf;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_NAME_PROFILE_OF_USERS;
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
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

@Tag("plf")
@Tag("sniff")
public class PlfHomepageGadgetWhoIsOnlineGadgetTestIT extends Base {
  ManageLogInOut manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
    manageLogInOut.signInCas(username, password);
  }


  /**
   * <li>Case ID:120864.</li>
   * <li>Test Case Name: Check display of Who's online gadget.</li>
   * <li>Pre-Condition: mary and demo are connected to intranet in different
   * browsers.</li> Step Number: 1 Step Name: Check display of this gadget Step
   * Description: - Login as John for example - Go to intranet home - Check
   * display of Who's online gadget Input Data: Expected Outcome: - The gadget is
   * displaying all people (see WhoOnline.png) that are connected to the social
   * intranet: mary, demo
   */
  @Test
  public void test01_CheckDisplayOfWhosOnlineGadget() {
    info("Test 1: Check display of Who's online gadget");

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    manageLogInOut.signIn(username, DATA_PASS);
    ELEMENT_WHO_ON_LINE_GADGET.should(Condition.exist);
  }

  /**
   * <li>Case ID:120865.</li>
   * <li>Test Case Name: Show information of user.</li>
   * <li>Pre-Condition: mary and demo are connected to intranet in different
   * browsers.</li> Step Number: 1 Step Name: Show information of user Step
   * Description: - Login as Demo for ex, go to intranet home page - Move the
   * mouse over avatar of Mary at Who's online gadget Input Data: Expected
   * Outcome: -A popup with more mary's informations is shown, including avatar,
   * name, title, last activity message (if existed)
   */
  @Test
  public void test02_ShowInformationOfUser() {
    info("Test 2: Show information of user");

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    manageLogInOut.signIn(username, DATA_PASS);
    // 3 is the id of mary in who on line gadget
    ELEMENT_WHO_ON_LINE_GADGET.hover();
    $(byId("tiptip_content")).should(Condition.exist);

  }

  /**
   * <li>Case ID:120866.</li>
   * <li>Test Case Name: Connect to users.</li>
   * <li>Pre-Condition: mary and demo are connected to intranet in different
   * browsers.</li> Step Number: 1 Step Name: Connect to users Step Description: -
   * Login as John for example - Open intranet home - Hover the mouse on avatar of
   * Mary at Who's Online Gadget - Click on Connect Input Data: Expected Outcome:
   * - Who's online is at the right - Connect button is shown - Connection request
   * is sent to mary Step number: 2 Step Name: Verify if connection request is
   * sent Step Description: - Login as mary - Open intranet home Input Data:
   * Expected Outcome: - Invitation of John is shown at Invitation gadget
   */

  @Test
  public void test03_ConnectToUsers() {
    info("Test 3: Connect to users");

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    manageLogInOut.signIn(username, DATA_PASS);
    // 3 is the id of mary in who on line gadget
    ELEMENT_WHO_ON_LINE_GADGET.scrollTo().find(byId("3")).hover();
    $(byId("tiptip_content")).find(byText(DATA_NAME_USER2)).should(Condition.exist);
    $(ELEMENT_WHO_ONLINE_CONNECT).waitUntil(Condition.appears, Configuration.timeout).click();
    manageLogInOut.signIn(username, DATA_PASS);
    ELEMENT_WHO_ON_LINE_GADGET.find(byId("3")).hover();
    $(ELEMENT_WHO_ONLINE_CANCEL_CONNECT).click();
  }

  /**
   * <li>Case ID:120867.</li>
   * <li>Test Case Name: Access activity's stream of users.</li>
   * <li>Pre-Condition: mary and demo are connected to intranet in different
   * browsers.</li> Step Number: 1 Step Name: Access to activity stream of other
   * users Step Description: - Login as Demo and open intranet home - Click on
   * Avatar of Mary Input Data: Expected Outcome: - Who's online gadget is shown
   * with Mary - Browser redirect to Mary's activity stream
   */
  @Test
  public void test04_AccessActivitysStreamOfUsers() {
    info("Test 4: Access activity's stream of users");

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    manageLogInOut.signIn(username, DATA_PASS);
    ELEMENT_WHO_ON_LINE_GADGET.find(byId("3")).click();
    ELEMENT_NAME_PROFILE_OF_USERS.find(byText(DATA_NAME_USER2)).should(Condition.exist);
    // closeBrowsers();
  }
}
