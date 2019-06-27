package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
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
  public void test01_CheckPermissionTableInTheSpaceManagementPage() {
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
  }

  /**
   * <li>This test case cover Case ID:SP_02, ID:SP_03,ID:SP_07</li>
   */
  @Test
  public void test02_CheckTheFirstColumnPermissions() {
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_PERMISSIONS_SECTION.click();
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

  }

  @Test
  public void test03_CheckTheChangeOfEditbuttonWhenClickingOnIt() {
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_PERMISSIONS_SECTION.click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
                                            .parent()
                                            .find(byClassName("uiIconEdit"))
                                            .waitUntil(Condition.visible, Configuration.timeout)
                                            .click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
                                            .parent()
                                            .find(byClassName("uiIconEdit"))
                                            .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
                                            .parent()
                                            .find(byClassName("uiIconSave"))
                                            .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
                                            .parent()
                                            .find(byClassName("uiIconClose"))
                                            .waitUntil(Condition.visible, Configuration.timeout)
                                            .click();
  }

  @Test
  public void test04_NoAssignmentDisplayedWhenNoPermission() {
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_PERMISSIONS_SECTION.click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
                                            .parent()
                                            .find(byText("*:/platform/users"))
                                            .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText("Manage spaces"))
                      .parent()
                      .parent()
                      .find(byText("No assignment"))
                      .waitUntil(Condition.visible, Configuration.timeout);
  }

  @Test
  public void test05_CheckFormatWhenAddGroupForPermission() {
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
  }

  // Ce cas de test couvre et sp11 et sp07
  @Test
  public void test07_CheckTheThirdColumnOfPermissionsTable() {
    info("create some spaces");
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
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
  }

  @Test
  public void test08_CheckTheChangeOfEditButtonWhenClickingOnIt() {
    info("create some spaces");
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_PERMISSIONS_SECTION.click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent().parent().waitUntil(Condition.visible, Configuration.timeout);
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
    homePagePlatform.refreshUntil($(byText("Create spaces")), Condition.visible, 1000);
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
                                            .parent()
                                            .find(byClassName("uiIconSave"))
                                            .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent()
                                            .parent()
                                            .find(byClassName("uiIconClose"))
                                            .waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);

  }

  // Ce cas de test couvre sp09 et sp09a
  @Test
  public void test09_SaveButtonWillSaveTheEditDoneByUser() {
    info("create some spaces");
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_PERMISSIONS_SECTION.click();
    ELEMENT_PERMISSIONS_SECTION_CREATE_SPACE.parent().parent().waitUntil(Condition.visible, Configuration.timeout);
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
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
  }

  @Test
  public void test10_CheckThatGroupOfUserCanDeleteEditableUserSectionFromPermission() {
    info("create some spaces");
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
  @Test
  @Tag("Soc-6246")
  public  void  test11_CheckUserCanNotAccessSpaceAfterBeeingDeletedFromSpaceMebers()
  {
    String username=getRandomString();
    String email=username+"@t.t";
    String pass="123456";
    String spaceName=getRandomString();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName,spaceName);
    navigationToolbar.goToAddUser();
    AddUsers addUsers=new AddUsers(this);
    addUsers.addUser(username,pass,email,username,username);
    navigationToolbar.goToUsersAndGroupsManagement();
    UserAndGroupManagement userAndGroupManagement= new UserAndGroupManagement(this);
    userAndGroupManagement.goToGroupTab();
    userAndGroupManagement.selectGroup("Spaces/"+spaceName);
    userAndGroupManagement.addUsersToGroup(username,"*",false,false);
    manageLogInOut.signIn(username,pass);
    homePagePlatform.goToMySpaces();
    spaceManagement.goToSpace(spaceName);
    manageLogInOut.signIn(DATA_USER1,DATA_PASS2);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.goToGroupTab();
    userAndGroupManagement.selectGroup("Spaces/"+spaceName);
    userAndGroupManagement.removeUser(username);
    manageLogInOut.signIn(username,pass);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(spaceName);
    $(byXpath(ELEMENT_MY_SPACE_SEARCH_RESULT_NUMBER.replace("${number}","0"))).shouldHave(Condition.text("0"));




  }
}
