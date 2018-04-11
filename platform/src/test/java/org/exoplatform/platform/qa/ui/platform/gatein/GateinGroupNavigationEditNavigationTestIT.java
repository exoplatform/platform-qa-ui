package org.exoplatform.platform.qa.ui.platform.gatein;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.NavigationManagement;
		import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalGroupNavigation;
		import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
		import org.junit.jupiter.api.BeforeEach;
		import org.junit.jupiter.api.Tag;
		import org.junit.jupiter.api.Test;

		import com.codeborne.selenide.Condition;

		import org.exoplatform.platform.qa.ui.commons.Base;
		import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManageSites;
		import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

@Tag("gatein")
@Tag("sniff")

public class GateinGroupNavigationEditNavigationTestIT extends Base {
	ManageLogInOut manageLogInOut;
	NavigationManagement navigationmanagement;
	PortalManageSites portalManageSites;
	NavigationToolbar navigationToolbar;
    PortalGroupNavigation portalGroupNavigation;



	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");

		manageLogInOut = new ManageLogInOut(this);
		navigationmanagement = new NavigationManagement(this);
		portalManageSites = new PortalManageSites(this);
		navigationToolbar = new NavigationToolbar(this);
		portalGroupNavigation = new PortalGroupNavigation(this);
		manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");

	}


	@Test
	public void test01EditPriorityForGroupNavigation() {

		String groupAdmin="Administration";
		String groupAdminOldPosition = ELEMENT_GROUP_NAVIGATION_POSITION.replace("${index}", "1").replace("${groupTitle}", groupAdmin);
		String groupAdminNewPosition = ELEMENT_GROUP_NAVIGATION_POSITION.replace("${index}", "2").replace("${groupTitle}", groupAdmin);

		info("Go to Group Sites");
		navigationToolbar.goToGroupSites();

		info("Verify position of Administration before change order");

		$(byXpath(groupAdminOldPosition)).should(Condition.exist);



		portalGroupNavigation.editPriorityForGroup(groupAdmin, "2");

		info ("Verify position of Administration after changing order");

		$(byXpath(groupAdminNewPosition)).should(Condition.exist);
		manageLogInOut.signIn(DATA_USER1, "gtngtn");
		navigationToolbar.goToGroupSites();

		//Verify position of Administration after SignOut and SignIn

		$(byXpath(groupAdminOldPosition)).shouldNot(Condition.exist);

		info("Reset order of navigation list");
		portalGroupNavigation.editPriorityForGroup(groupAdmin, "1");
		$(byXpath(groupAdminOldPosition)).should(Condition.exist);

	}

}

