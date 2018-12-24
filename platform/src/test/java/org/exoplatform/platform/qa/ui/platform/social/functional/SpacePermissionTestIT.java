package org.exoplatform.platform.qa.ui.platform.social.functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("functional")
@Tag("social")
public class SpacePermissionTestIT extends Base {
    NavigationToolbar navigationToolbar;
    ManageLogInOut manageLogInOut;
    HomePagePlatform homePagePlatform;
    SpaceManagement spaceManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut = new ManageLogInOut(this);
        homePagePlatform = new HomePagePlatform(this);
        spaceManagement = new SpaceManagement(this);
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    }


    @Test
    public void test01_CheckPermissionTableInTheSpaceManagementPage() {
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Permissions")).click();
        $(byText("Create spaces")).parent().parent().find(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Manage spaces")).parent().parent().find(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout);
    }

    /**
     * <li>This test case cover Case ID:SP_02, ID:SP_03,ID:SP_07 </li>
     */
    @Test
    public void test02_CheckTheFirstColumnPermissions() {
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Permissions")).click();
        $(byText("Create spaces")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Manage spaces")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Create spaces")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Ability to create spaces")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Ability to edit spaces")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Create spaces")).parent().parent().find(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Manage spaces")).parent().parent().find(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout);

    }

    @Test
    public void test03_CheckTheChangeOfEditbuttonWhenClickingOnIt() {
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Permissions")).click();
        $(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout).click();
        $(byClassName("uiIconSave")).waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconClose")).waitUntil(Condition.visible, Configuration.timeout);
    }

    @Test
    public void test04_NoAssignmentDisplayedWhenNoPermission() {
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Permissions")).click();
        $(byText("Create spaces")).parent().parent().find(byText("No assignement")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Manage spaces")).parent().parent().find(byText("No assignement")).waitUntil(Condition.visible, Configuration.timeout);

    }
    //Bug
    @Test
    public void test05_TheAdministratorCanOnlyEditASinglePermissionAtSameTime(){

        navigationToolbar.goToSpaceAdminstration();
        $(byText("Permissions")).click();
        $(byText("Create spaces")).parent().parent().find(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Manage spaces")).parent().parent().find(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout);
        $(byText("Create spaces")).parent().parent().find(byClassName("uiIconEdit")).click();
        $(byText("Manage spaces")).parent().parent().find(byClassName("uiIconEdit")).waitUntil(Condition.disabled, Configuration.timeout);
    }
}