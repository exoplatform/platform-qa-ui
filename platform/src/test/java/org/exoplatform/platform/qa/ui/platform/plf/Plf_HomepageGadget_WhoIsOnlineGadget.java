package org.exoplatform.platform.qa.ui.platform.plf;


import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_WHO_ONLINE_ICON_PEOPLE_NUMBER;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_WHO_ON_LINE_GADGET;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;


public class Plf_HomepageGadget_WhoIsOnlineGadget extends Base{
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

    /**
*<li> Case ID:120864.</li>
*<li> Test Case Name: Check display of Who's online gadget.</li>
*<li> Pre-Condition: mary and demo are connected to intranet in different browsers.</li>
*/
@Test
public  void test01_CheckDisplayOfWhosOnlineGadget() {
    info("Test 1: Check display of Who's online gadget");
    //initBrowsers();
    /*Step Number: 1
    *Step Name: Check display of this gadget
    *Step Description:
        - Login as John for example
        - Go to intranet home
        - Check display of Who's online gadget
    *Input Data:

    *Expected Outcome:
        - The gadget is displaying all people (see WhoOnline.png) that are connected to the social intranet: mary, demo*/

    manageLogInOut.signIn(DATA_USER2,"gtngtn");
    manageLogInOut.signIn(DATA_USER1,"gtngtn");
    ELEMENT_WHO_ON_LINE_GADGET.should(Condition.exist);
    //3 is the id of john in who on line gadget
    ELEMENT_WHO_ON_LINE_GADGET.find(byId("3")).should(Condition.exist);
    //closeBrowsers();
 }
    /**
     *<li> Case ID:120865.</li>
     *<li> Test Case Name: Show information of user.</li>
     *<li> Pre-Condition: mary and demo are connected to intranet in different browsers.</li>
     */
    @Test
    public  void test02_ShowInformationOfUser() {
        info("Test 2: Show information of user");
		/*Step Number: 1
		*Step Name: Show information of user
		*Step Description:
			- Login as Demo for ex, go to intranet home page
			- Move the mouse over avatar of Mary at Who's online gadget
		*Input Data:

		*Expected Outcome:
			-A popup with more mary's informations is shown, including avatar, name, title, last activity message (if existed)*/
        manageLogInOut.signIn(DATA_USER2,DATA_PASS);
        manageLogInOut.signIn(DATA_USER1,"gtngtn");
        //3 is the id of mary in who on line gadget
        ELEMENT_WHO_ON_LINE_GADGET.find(byId("3")).hover();
        $(byId("tiptip_content")).find(byText(DATA_NAME_USER2)).should(Condition.exist);

    }
}