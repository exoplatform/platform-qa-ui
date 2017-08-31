package org.exoplatform.platform.qa.ui.platform.plf;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.*;


@Tag("sniff")
@Tag("plf")
public class PlfNavigationLeftNavigationTestIT extends Base {
ManageLogInOut manageLogInOut;
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
    manageLogInOut=new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
            manageLogInOut.signOut();
        }
        manageLogInOut.signInCas(username, password);
    }

    @AfterEach
    public void signout() {
        manageLogInOut.signOut();
    }
    @Test
    public  void test01_ShowApplicationsInCOMPANYList() {
        info("Test 1: Show applications in COMPANY list");
		/*Step Number: 1
		 *Step Name: - Connect to Intranet
		 *Step Description:
			- Login as a user
			- Connect to Intranet
		 *Input Data:

		 *Expected Outcome:
			- The left Navigation is displayed
			- The "COMPANY" list is displayed with applications in the following order:* Home* Connections* Wiki* Documents* Forums* Calendar* Other personal pages*/
        info("Verify expected outcome");
        $(ELEMENT_FORUM_LINK_PLF).waitUntil(Condition.appears,Configuration.timeout);
        $(ELEMENT_WIKI_LINK_PLF).waitUntil(Condition.appears,Configuration.timeout);
        $(ELEMENT_HOME_LINK_PLF).waitUntil(Condition.appears,Configuration.timeout);
        $(ELEMENT_CALENDAR_LINK_PLF).waitUntil(Condition.appears,Configuration.timeout);
        $(ELEMENT_CONNECTIONS_LINK_PLF).waitUntil(Condition.appears,Configuration.timeout);

    }

    @Test
    public  void test02_DisplayLeftNavigationForSocialIntranet() {
        info("Test 2: Display Left Navigation for Social Intranet");
		/*Step Number: 1
		 *Step Name: Step 1: Check Left navigation
		 *Step Description:
			- Login as a user
			- Connect to Intranet
		 *Input Data:

		 *Expected Outcome:
			- The Left Navigation is displayed: Company List" and "My Spaces" panels*/

		/*Step number: 2
		 *Step Name: Step 2: Check Left navigation on other portal
		 *Step Description:
			- Connect to other sites. Go to via url: host:port/portal/acme/.
		 *Input Data:

		 *Expected Outcome:
			- The Left Navigation isn't displayed*/

        $(ELEMENT_FORUM_LINK_PLF).waitUntil(Condition.appears,Configuration.timeout);
        $(ELEMENT_WIKI_LINK_PLF).waitUntil(Condition.appears,Configuration.timeout);
        $(ELEMENT_HOME_LINK_PLF).waitUntil(Condition.appears,Configuration.timeout);
        $(ELEMENT_CALENDAR_LINK_PLF).waitUntil(Condition.appears,Configuration.timeout);
        $(ELEMENT_CONNECTIONS_LINK_PLF).waitUntil(Condition.appears,Configuration.timeout);

    }

}
