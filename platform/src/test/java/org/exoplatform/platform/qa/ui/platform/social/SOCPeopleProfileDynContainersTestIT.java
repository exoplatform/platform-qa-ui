package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_EDIT_MY_PROFILE_LINK;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
@Tag("social")
@Tag("sniff")
public class SOCPeopleProfileDynContainersTestIT extends Base {
  ManageLogInOut    manageLogInOut;

  NavigationToolbar navigationToolbar;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    manageLogInOut = new ManageLogInOut(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");

  }

  /**
   * <li>Case ID:122962.</li>
   * <li>Test Case Name: Check the layout of Profile page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Go to my profile
   * page Step Description: - Login - Go to User Menu > [My Profile] Input Data:
   * Expected Outcome: Show content of my profile page
    Step number: 2 Step Name: Step 2: Open Edit Layout mode Step Description: -
   * On Admin toolbar, click on Edit>Page>Edit Layout Input Data: Expected
   * Outcome: Page Editor is on the right, page is in edit mode Step number: 3
   * Step Name: Step 3: Check righttop -profile -container in Edit Layout mode
   * Step Description: - In Page Editor, switch to tab Containers - Mouse over
   * righttop -profile -container - Click on Edit Container Input Data: Expected
   * Outcome: - Dynamic column container displays above Connections User Portlet
   * on the right - Container Name is righttop -profile -container Step number: 4
   * Step Name: Step 4: Check rightbottom -profile -container in Edit Layout mode
   * Step Description: - Mouse over rightbottom -profile -container - Click on
   * Edit Container Input Data: Expected Outcome: - Dynamic column container
   * displays above Connections User Portlet on the right - Container Name is
   * rightbottom -profile -container Step number: 5 Step Name: Step 5: Check
   * middle -profile -container in Edit Layout mode Step Description: - Mouse over
   * middle -profile -container - Click on Edit Container Input Data: Expected
   * Outcome: - Dynamic column container displays in the middle - Container Name
   * is middle -profile -container Step number: 6 Step Name: Step 6: Check left
   * -profile -container in Edit Layout mode Step Description: - Mouse over left
   * -profile -container - Click on Edit Container Input Data: Expected Outcome: -
   * Dynamic column container displays on the left - Container Name is left
   * -profile -container Step number: 7 Step Name: Step 7: Check action -profile
   * -container in Edit Layout mode Step Description: - Mouse over action -profile
   * -container - Click on Edit Container Input Data: Expected Outcome: - Dynamic
   * row container displays on the right - Container Name is action -profile
   * -container
   */
  @Test
  public void test01_CheckTheLayoutOfProfilePageAndEditProfilePage() {
    info("Test 1: Check the layout of Profile page");
    String righttop_profile = "righttop-profile-container";
    String rightbottom_profile = "rightbottom-profile-container";
    String middle_profile = "middle-profile-container";
    String left_profile = "left-profile-container";
    String action_editprofile = "action-editprofile-container";
    String left_editprofile = "left-editprofile-container";
    String right_editprofile = "right-editprofile-container";

    navigationToolbar.goToMyProfile();
    sleep(1000);
    navigationToolbar.goToEditLayout();
    info("switch to tab container");
    $(ELEMENT_CONTAINER_TAB).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("goto edit container");
    $(byXpath(ELEMENT_CONTAINER_ID.replace("${id}", righttop_profile))).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_CONTAINER_ID.replace("${id}", rightbottom_profile))).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_CONTAINER_ID.replace("${id}", middle_profile))).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_CONTAINER_ID.replace("${id}", left_profile))).waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ICON_CLOSE_EDIT_LAYOUT.waitUntil(Condition.visible,Configuration.timeout).click();
    navigationToolbar.goToMyProfile();
    info("goto edit profile");
    $(ELEMENT_EDIT_MY_PROFILE_LINK).waitUntil(Condition.visible,Configuration.timeout).click();
    info("goto edit layout");
    navigationToolbar.goToEditLayout();
    info("switch to tab container");
    $(ELEMENT_CONTAINER_TAB).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byXpath(ELEMENT_CONTAINER_ID.replace("${id}", action_editprofile))).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_CONTAINER_ID.replace("${id}", left_editprofile))).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_CONTAINER_ID.replace("${id}", right_editprofile))).waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_ICON_CLOSE_EDIT_LAYOUT.waitUntil(Condition.visible,Configuration.timeout).click();

  }

}
