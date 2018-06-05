package org.exoplatform.platform.qa.ui.platform.gatein;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManageSites;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.DownloadFileControl.driver;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_NEW_PORTAL_ADD;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_NEW_PORTAL_SWITCH;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("gatein")
@Tag("sniff")
public class GateinManageManagePortalTestIT extends Base {
    ManageLogInOut manageLogInOut;
    PortalManageSites portalManageSites;
    NavigationToolbar navigationToolbar;
    @BeforeEach
    public void setUpBeforeMethod() throws Exception {
        manageLogInOut = new ManageLogInOut(this);
        portalManageSites = new PortalManageSites(this);
        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    }

    /**
     * Test case ID: 123059
     * Test case name: Create new site
     * Test case ID: 123061
     * Test case name: Change using portal
     * Test case ID: 123060
     * Test case name: Delete portal
     * Step Number: 1
     * Step Name: Step 1: Create new portal
     * Step Description:
     * - Choose Administration/Portal/Sites/Add new portal
     * - Input some fields required
     * - Select Access/Edit permission
     * - Click Save
     * Input Data:
     * Expected Outcome:
     * - New portal is created successfully
     */

    @Test
    public void test01_AddPortal() {
        String portalName = "portalName" + getRandomNumber();
        String editGroupId = "Platform/Administration";
        String editMembership = "*";
        String language = "English";
        info("Test Case 01: Add new portal");
        Map<String, String> permissions = null;
        navigationToolbar.goToPotalSites();
        portalManageSites.addNewPortal(portalName, null, null, language, null, "Always", true, permissions, editGroupId, editMembership);

        $(byXpath(ELEMENT_NEW_PORTAL_ADD.replace("${portalName}", portalName))).waitUntil(Condition.visible, Configuration.timeout);
        navigationToolbar.goToPotalSites();
        portalManageSites.deletePortal(portalName);
    }

    /*Step Number: 2
      *Step Name: Step 2: Change using portal
	  *Step Description:
	  *- Create new some portals
	  *- Switch between some portals by input directly name of portal on URL ( ex:  http://localhost:8080/portal/{name_of_portal}
	  *Input Data:
+	  *Expected Outcome:
+	  *- The switched portal is changed successfully
      */

    @Test
    public void test02_SwitchToNewPortal() {
        String portalName = "portalName" + getRandomNumber();
        String editGroupId = "Platform/Administration";
        String editMembership = "*";
        String language = "English";
        Map<String, String> permissions = null;
        navigationToolbar.goToPotalSites();
        portalManageSites.addNewPortal(portalName, null, null, language, null, "Always", true, permissions, editGroupId, editMembership);

        $(byXpath(ELEMENT_NEW_PORTAL_ADD.replace("${portalName}", portalName))).waitUntil(Condition.visible, Configuration.timeout);
        open(Configuration.baseUrl+"/portal/"+portalName);
        $(byId("PAGEBODY-VIEW-BLOCK")).waitUntil(Condition.visible,Configuration.timeout);
        open(Configuration.baseUrl);
        navigationToolbar.goToPotalSites();
        refresh();
        portalManageSites.deletePortal(portalName);


}}


