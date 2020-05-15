package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

@Tag("functional")
@Tag("social")
public class SpacePermissionTestIT extends Base {
  NavigationToolbar navigationToolbar;

  ManageLogInOut    manageLogInOut;

  HomePagePlatform  homePagePlatform;

  SpaceManagement   spaceManagement;

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
  @Tag("sabis")
  public void test01_CheckFormatWhenAddGroupForPermission() {
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_PERMISSIONS_SECTION.click();

    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byClassName("uiIconEdit"))
            .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_MANAGEMENT_SECTION.parent()
            .parent()
            .parent()
            .find(byClassName("uiIconEdit"))
            .waitUntil(Condition.visible, Configuration.timeout);

    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_MANAGEMENT_SECTION.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_PERMISSIONS_SECTION_Ability_SPACE.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_PERMISSIONS_SECTION_Ability_EDIT_SPACE.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byClassName("uiIconEdit"))
            .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_MANAGEMENT_SECTION.parent()
            .parent()
            .parent()
            .find(byClassName("uiIconEdit"))
            .waitUntil(Condition.visible, Configuration.timeout);

    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
                                            .parent()
                                            .find(byText("*:/platform/users"))
                                            .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText("Manage spaces"))
                      .parent()
                      .parent()
                      .find(byText("No assignment"))
                      .waitUntil(Condition.visible, Configuration.timeout);

    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byClassName("uiIconEdit"))
            .waitUntil(Condition.visible, Configuration.timeout)
            .click();
    ELEMENT_PERMISSIONS_SECTION_Ability_SPACE.parent()
            .parent()
            .find(byClassName("selectize-control"))
            .find(byAttribute("type", "text"))
            .click();
    ELEMENT_PERMISSIONS_SECTION_Ability_SPACE.parent()
            .parent()
            .find(byClassName("selectize-control"))
            .find(byAttribute("type", "text"))
            .sendKeys("admin");
    homePagePlatform.refreshUntil($(byText("Create spaces")), Condition.visible, 1000);
    ELEMENT_PERMISSIONS_SECTION_Ability_SPACE.parent()
            .parent()
            .find(byClassName("selectize-control"))
            .find(byAttribute("type", "text"))
            .pressEnter();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent().parent().find(byClassName("uiIconSave")).click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byText("*:/platform/administrators"))
            .waitUntil(Condition.visible, Configuration.timeout);

    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_PERMISSIONS_SECTION.click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byClassName("uiIconEdit"))
            .waitUntil(Condition.visible, Configuration.timeout)
            .click();
    ELEMENT_PERMISSIONS_SECTION_Ability_SPACE.parent()
            .parent()
            .find(byClassName("selectize-control"))
            .find(byAttribute("type", "text"))
            .click();
    ELEMENT_PERMISSIONS_SECTION_Ability_SPACE.parent()
            .parent()
            .find(byClassName("selectize-control"))
            .find(byAttribute("type", "text"))
            .sendKeys("users");
    homePagePlatform.refreshUntil($(byText("Create spaces")), Condition.visible, 1000);
    ELEMENT_PERMISSIONS_SECTION_Ability_SPACE.parent()
            .parent()
            .find(byClassName("selectize-control"))
            .find(byAttribute("type", "text"))
            .pressEnter();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent().parent().find(byClassName("uiIconSave")).click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byText("*:/platform/users"))
            .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byClassName("uiIconEdit"))
            .waitUntil(Condition.visible, Configuration.timeout)
            .click();
    ELEMENT_PERMISSIONS_SECTION_Ability_SPACE.parent()
            .parent()
            .find(byClassName("selectize-control"))
            .find(byAttribute("type", "text"))
            .click();
    ELEMENT_PERMISSIONS_SECTION_Ability_SPACE.parent()
            .parent()
            .find(byClassName("selectize-control"))
            .find(byAttribute("type", "text"))
            .sendKeys("administrators");
    homePagePlatform.refreshUntil($(byText("Create spaces")), Condition.visible, 1000);
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byClassName("uiIconClose"))
            .waitUntil(Condition.visible, Configuration.timeout);

    ELEMENT_PERMISSIONS_SECTION_Ability_SPACE.parent()
            .parent()
            .find(byClassName("selectize-control"))
            .find(byAttribute("type", "text"))
            .pressEnter();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent().parent().find(byClassName("uiIconSave")).click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byText("*:/platform/administrators"))
            .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byText("*:/platform/users"))
            .waitUntil(Condition.visible, Configuration.timeout);

    info("Check That Group Of User Can Delete Editable User Section From Permission");
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byClassName("uiIconEdit"))
            .waitUntil(Condition.visible, Configuration.timeout)
            .click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .find(byAttribute("data-value", "*:/platform/administrators"))
            .find(byClassName("remove"))
            .waitUntil(Condition.visible, Configuration.timeout)
            .click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent().parent().find(byClassName("uiIconSave")).click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
            .parent()
            .parent()
            .find(byText("*:/platform/administrators"))
            .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
  }

}
