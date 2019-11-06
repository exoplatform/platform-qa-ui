package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("functional")
@Tag("social")
public class ManageSpacesTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  ManageLogInOut         manageLogInOut;

  HomePagePlatform       homePagePlatform;

  SpaceManagement        spaceManagement;

  UserAndGroupManagement userAndGroupManagement;

  AddUsers               addUsers;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    addUsers = new AddUsers(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    spaceManagement = new SpaceManagement(this);
  }

  @Test
  @Tag("sabis")
  public void test01_CheckTheNewSpacesSubmenuInTheAdministrationMenu() {
    info("Test 1: CheckTheNewSpacesSubmenuInTheAdministrationMenu");
    navigationToolbar.goToSpaceAdminstration();
  }

  @Test
  @Tag("sabis")
  public void test02_CheckElementsInTheManageSpaces() {
    info("create some new spaces");
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    info("Test 1: CheckElementsInTheManageSpaces");
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea))
                      .parent()
                      .parent()
                      .find(ELEMENT_DELETESPACE_MANAGE_ICON)
                      .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceDesa))
                      .parent()
                      .parent()
                      .find(ELEMENT_EDITSPACE_MANAGE_ICON)
                      .waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
  }

  @Test
  @Tag("sabis")
  public void test03_CheckThatNothingHappensWhenClickOnSpacesNameAndAvatar() {
    info("create some spaces");
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    info("CheckThatNothingHappensWhenClickOnSpacesNameAndAvatar");
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACEAVATAR_DEFAULT.hover().click();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
  }

  @Test
  @Tag("sabis")
  public void test04_CheckTheDisplayOfTheFilterFieldAboveTheSpaceManagementle() {
    info("CheckTheDisplayOfTheFilterFieldAboveTheSpaceManagementle");
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_SEARCH_ICON.waitUntil(Condition.visible, Configuration.timeout);
  }

  @Test
  @Tag("sabis")
  public void test05_CheckcancelButtonFromTheConfirmationPopup() {
    info("create some spaces");
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea))
                      .parent()
                      .parent()
                      .find(ELEMENT_DELETESPACE_MANAGE_ICON)
                      .waitUntil(Condition.visible, Configuration.timeout)
                      .click();
    ELEMENT_SPACEMANAGEMENT_CANCEL_POPUP.waitUntil(Condition.visible, Configuration.timeout).click();
  }

  @Test
  @Tag("sabis")
  public void test06_CheckXButtonFromTheConfirmationPopup() {
    info("create some spaces");
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea))
                      .parent()
                      .parent()
                      .find(ELEMENT_DELETESPACE_MANAGE_ICON)
                      .waitUntil(Condition.visible, Configuration.timeout)
                      .click();
    ELEMENT_SPACEMANAGEMENT_ClOSE_POPUP.click();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
  }

  @Test
  @Tag("sabis")
  public void test07_CheckConfirmationPopupWhileDeleteSpace() {
    info("create some spaces");
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea))
                      .parent()
                      .parent()
                      .find(ELEMENT_DELETESPACE_MANAGE_ICON)
                      .waitUntil(Condition.visible, Configuration.timeout)
                      .click();
    $(byText("Do you confirm the deletion of this space ?")).waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACEMANAGEMENT_CANCEL_POPUP.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACEMANAGEMENT_POPUP.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACEMANAGEMENT_ClOSE_POPUP.click();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
  }

  @Test
  @Tag("sabis")
  public void test08_CheckButtonsInTheActionsColumn() {
    info("create some spaces");
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea))
                      .parent()
                      .parent()
                      .find(ELEMENT_EDITSPACE_MANAGE_ICON)
                      .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceNamea))
                      .parent()
                      .parent()
                      .find(ELEMENT_DELETESPACE_MANAGE_ICON)
                      .waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
  }

  @Test
  @Tag("sabis")
  public void test09_CheckTheExistanceOfTheSpacesInTheManageSpacesTab() {
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();
    String spaceNamec = "spaceNamec" + getRandomNumber();
    String spaceDesc = "descriptionc" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamec, spaceDesc);
    navigationToolbar.goToSpaceAdminstration();
    $(byText("Manage spaces")).click();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceNameb)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceNamec)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
    spaceManagement.deleteSpace(spaceNameb, false);
    spaceManagement.deleteSpace(spaceDesc, false);
  }

  @Test
  @Tag("sabis")
  public void test10_CheckEmptyTableIfNoSpacesHasBeenCreated() {
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.addUserAdmin(username1, "*");
    manageLogInOut.signIn(username1, password);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
    spaceManagement.deleteSpace(spaceNameb, false);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    ELEMENT_SPACE_GRID.find(byText("No spaces")).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  @Tag("sabis")
  public void test11_SpaceAdministrationPageWillBeRefreshedOnceDeleteASpace() {
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceNameb)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    refresh();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea))
                      .parent()
                      .parent()
                      .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNameb, false);
  }

  @Test
  @Tag("sabis")
  public void test13_CheckTheDisplayOfTheSpaceEditPage() {
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea))
                      .parent()
                      .parent()
                      .find(ELEMENT_EDITSPACE_MANAGE_ICON)
                      .waitUntil(Condition.visible, Configuration.timeout)
                      .click();
    switchTo().window(1);
    ELEMENT_SPACE_SETTING_PAGE.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_NAME_PAGE.setValue(spaceNamea + "zfzff");
    ELEMENT_SPACE_DESCRIPTION_PAGE.setValue(spaceDesa + "zfzff");
    $(ELEMENET_SPACE_UPDATE_SAVE_BTN).click();
    switchTo().window(0);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea + "zfzff", false);
  }

  @Test
  @Tag("sabis")
  public void test14_CheckThatNothingHappensWhenClickOnSpaceNameAvatar() {
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "descriptiona" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    sleep(2000);
    ELEMENT_SPACE_GRID.find(byText(spaceNamea)).parent().parent().click();
    sleep(2000);
    $(byXpath("//div[@class='manageSpaces']/table//td[contains(text(),'${spaceName}')]/img[@class='avatar']".replace("${spaceName}",spaceNamea))).waitUntil(Condition.visible,Configuration.timeout).doubleClick();
    sleep(2000);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
  }

  @Test
  @Tag("sabis")
  public void test15_SpacesFilteredByItsDescription() {
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "spaceDesa" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
    homePagePlatform.goToMySpaces();
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    ELEMENT_SPACE_SEARCH_ICON.waitUntil(Condition.visible, Configuration.timeout);
    info("Input the space into search text box");
    ELEMENT_SPACE_SEARCH_ICON.setValue(spaceDesa);
    ELEMENT_SPACE_GRID.find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceNameb)).parent().parent().waitUntil((Condition.visible), Configuration.timeout);
    sleep(2000);
    executeJavaScript("window.scrollBy(0,-1000)", "");
    ELEMENT_SPACE_SEARCH_ICON.click();
    sleep(Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceNameb)).parent().parent().waitUntil((Condition.visible), Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
    spaceManagement.deleteSpace(spaceNameb, false);
  }

  @Test
  @Tag("sabis")
  public void test16_SpacesFilteredByItsName() {
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "spaceDesa" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceDesb = "descriptionb" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
    homePagePlatform.goToMySpaces();
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    sleep(2000);
    executeJavaScript("window.scrollBy(0,-1000)", "");
    ELEMENT_SPACE_SEARCH_ICON.waitUntil(Condition.visible, Configuration.timeout);
    info("Input the space into search text box");
    ELEMENT_SPACE_SEARCH_ICON.setValue(spaceNamea);
    ELEMENT_SPACE_SEARCH_ICON.click();
    sleep(Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceNameb)).parent().parent().is(Condition.not(Condition.visible));
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
    spaceManagement.deleteSpace(spaceNameb, false);
  }

  @Test
  @Tag("sabis")
  public void test17_CheckTheplaceholderIntheFilterFiled() {
    // this TC cover also case [ID:SM_04b] the filter engine will be done on Space
    // name and Descriptions fields when typing charactersand[ID:SM_04a]
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "spaceDescription" + getRandomNumber();
    String spaceNameb = "spaceNameb" + getRandomNumber();
    String spaceNamec = "spaceNamec" + getRandomNumber();
    String spaceNamed = "spaced" + getRandomNumber();
    String spaceDesd = "spaceDesd" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesa);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamec, spaceDesa);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamed, spaceDesd);
    homePagePlatform.goToMySpaces();
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    sleep(2000);
    executeJavaScript("window.scrollBy(0,-1000)", "");
    ELEMENT_SPACE_SEARCH_ICON.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_SEARCH_ICON.setValue(spaceDesa);
    ELEMENT_SPACE_SEARCH_ICON.click();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceNameb)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceNamec)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea, false);
    spaceManagement.deleteSpace(spaceNameb, false);
    spaceManagement.deleteSpace(spaceNamec, false);
    spaceManagement.deleteSpace(spaceNamed, false);
  }

  @Test
  @Tag("sabis")
  public void test18_CheckDisplayofPaginationWhenSpaceAdministrationContainsMoreThan30Spaces() {
    ArrayList<String> spaceList = new ArrayList<String>();
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space2" + getRandomNumber());
    spaceList.add("space3" + getRandomNumber());
    spaceList.add("space4" + getRandomNumber());
    spaceList.add("space5" + getRandomNumber());
    spaceList.add("space6" + getRandomNumber());
    spaceList.add("space7" + getRandomNumber());
    spaceList.add("space8" + getRandomNumber());
    spaceList.add("space9" + getRandomNumber());
    spaceList.add("space10" + getRandomNumber());
    spaceList.add("space11" + getRandomNumber());
    spaceList.add("space12" + getRandomNumber());
    spaceList.add("space13" + getRandomNumber());
    spaceList.add("space14" + getRandomNumber());
    spaceList.add("space15" + getRandomNumber());
    spaceList.add("space16" + getRandomNumber());
    spaceList.add("space17" + getRandomNumber());
    spaceList.add("space18" + getRandomNumber());
    spaceList.add("space19" + getRandomNumber());
    spaceList.add("space20" + getRandomNumber());
    spaceList.add("space21" + getRandomNumber());
    spaceList.add("space22" + getRandomNumber());
    spaceList.add("space23" + getRandomNumber());
    spaceList.add("space24" + getRandomNumber());
    spaceList.add("space25" + getRandomNumber());
    spaceList.add("space26" + getRandomNumber());
    spaceList.add("space27" + getRandomNumber());
    spaceList.add("space28" + getRandomNumber());
    spaceList.add("space29" + getRandomNumber());
    spaceList.add("space30" + getRandomNumber());
    spaceList.add("space31" + getRandomNumber());
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.addUserAdmin(username1, "*");
    manageLogInOut.signIn(username1, password);
    for (int i = 0; i < 31; i++) {
      homePagePlatform.goToMySpaces();
      spaceManagement.addNewSpaceSimple(spaceList.get(i), "");
    }
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    $(byClassName("pagination")).find(byClassName("active"))
                                .parent()
                                .parent()
                                .waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    for (int i = 0; i < 30; i++) {
      spaceManagement.searchSpace(spaceList.get(i));
      spaceManagement.deleteSpace(spaceList.get(i), false);
    }
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  @Tag("sabis")
  public void test19_Only30SpacesDisplayedByDefaultPerView() {
    ArrayList<String> spaceList = new ArrayList<String>();
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space2" + getRandomNumber());
    spaceList.add("space3" + getRandomNumber());
    spaceList.add("space4" + getRandomNumber());
    spaceList.add("space5" + getRandomNumber());
    spaceList.add("space6" + getRandomNumber());
    spaceList.add("space7" + getRandomNumber());
    spaceList.add("space8" + getRandomNumber());
    spaceList.add("space9" + getRandomNumber());
    spaceList.add("space10" + getRandomNumber());
    spaceList.add("space11" + getRandomNumber());
    spaceList.add("space12" + getRandomNumber());
    spaceList.add("space13" + getRandomNumber());
    spaceList.add("space14" + getRandomNumber());
    spaceList.add("space15" + getRandomNumber());
    spaceList.add("space16" + getRandomNumber());
    spaceList.add("space17" + getRandomNumber());
    spaceList.add("space18" + getRandomNumber());
    spaceList.add("space19" + getRandomNumber());
    spaceList.add("space20" + getRandomNumber());
    spaceList.add("space21" + getRandomNumber());
    spaceList.add("space22" + getRandomNumber());
    spaceList.add("space23" + getRandomNumber());
    spaceList.add("space24" + getRandomNumber());
    spaceList.add("space25" + getRandomNumber());
    spaceList.add("space26" + getRandomNumber());
    spaceList.add("space27" + getRandomNumber());
    spaceList.add("space28" + getRandomNumber());
    spaceList.add("space29" + getRandomNumber());
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.addUserAdmin(username1, "*");
    manageLogInOut.signIn(username1, password);
    for (int i = 0; i < 29; i++) {
      homePagePlatform.goToMySpaces();
      spaceManagement.addNewSpaceSimple(spaceList.get(i), "");
    }
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    $(byClassName("pagination")).find(byClassName("active"))
                                .parent()
                                .parent()
                                .waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    homePagePlatform.goToMySpaces();
    for (int i = 0; i < 29; i++) {
      spaceManagement.searchSpace(spaceList.get(i));
      spaceManagement.deleteSpace(spaceList.get(i), false);
    }
    manageLogInOut.signIn(PLFData.DATA_USER1, PLFData.DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @BugInPLF("SOC-6152")
  @Test
  public void test20_SpaceInformationUpdatedOnlyWhenRefreshTheSpaceManagementPage() {
    String spaceNamea = "spaceNamea" + getRandomNumber();
    String spaceDesa = "spaceDesa" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea))
                      .parent()
                      .parent()
                      .find(ELEMENT_DELETESPACE_MANAGE_ICON)
                      .waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceDesa))
                      .parent()
                      .parent()
                      .find(ELEMENT_EDITSPACE_MANAGE_ICON)
                      .waitUntil(Condition.visible, Configuration.timeout);
    switchTo().window(1);
    ELEMENT_SPACE_SETTING_PAGE.waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_NAME_PAGE.setValue(spaceNamea + "zfzff");
    ELEMENT_SPACE_DESCRIPTION_PAGE.setValue(spaceDesa + "zfzff");
    $(ELEMENET_SPACE_UPDATE_SAVE_BTN).click();
    switchTo().window(0);
    navigationToolbar.goToSpaceAdminstration();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceDesa)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    refresh();
    ELEMENT_SPACE_GRID.find(byText(spaceNamea + "zfzff")).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    ELEMENT_SPACE_GRID.find(byText(spaceDesa + "zfzff")).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceNamea + "zfzff", false);
  }

  @Test
  @Tag("sabis")
  public void test21_CheckTheOrderOfTheCreatedSpaces() {
    String spaceName1 = "spaceNamea" + getRandomNumber();
    String spaceName2 = "spaceNameb" + getRandomNumber();
    String spaceName3 = "spaceNamec" + getRandomNumber();
    String spaceName4 = "spaced" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName1, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName2, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName3, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceName4, "");
    navigationToolbar.goToSpaceAdminstration();
    sleep(2000);
    ELEMENT_SPACE_MANAGEMENT_SECTION.click();
    sleep(2000);
    $(byXpath("//div[@class='manageSpaces']//td[contains(text(),'${spaceName1}')]".replace("{spaceName1}",spaceName1))).exists();
    $(byXpath("//div[@class='manageSpaces']//td[contains(text(),'${spaceName2}')]".replace("{spaceName2}",spaceName2))).exists();
    $(byXpath("//div[@class='manageSpaces']//td[contains(text(),'${spaceName3}')]".replace("{spaceName3}",spaceName3))).exists();
    $(byXpath("//div[@class='manageSpaces']//td[contains(text(),'${spaceName4}')]".replace("{spaceName4}",spaceName4))).exists();
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(spaceName1, false);
    spaceManagement.deleteSpace(spaceName2, false);
    spaceManagement.deleteSpace(spaceName3, false);
    spaceManagement.deleteSpace(spaceName4, false);
  }
}
