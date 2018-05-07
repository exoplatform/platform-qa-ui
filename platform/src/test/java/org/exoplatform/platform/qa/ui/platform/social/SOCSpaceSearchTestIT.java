package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byText;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_SEARCH_SPACE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_SPECIFIC_PANEL;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACES_LIST;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;

@Tag("social")
@Tag("sniff")
public class SOCSpaceSearchTestIT extends Base {
  NavigationToolbar navigationToolbar;

  AddUsers          addUsers;

  ManageLogInOut    manageLogInOut;

  HomePagePlatform  homePagePlatform;

  SpaceManagement   spaceManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:121905.</li>
   * <li>Test Case Name: Search Space.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Access the space
   * page Step Description: - Click MY SPACES on the left panel, or click Join
   * space Input Data: Expected Outcome: - Display the spaces page Step Number: 2
   * Step Name: Step 2: Search by Name/description Step Description: - Input
   * keyword "test" for ex into search text box and click Search button Input
   * Data: Expected Outcome: - Display all results matching with keyword Step
   * Number: 3 Step Name: Step 3: Search by directory Step Description: -Search
   * space by directory: Click on each characters (filter by A -> Z) Input Data:
   * Expected Outcome: - Display all spaces which has name starts by the selected
   * char
   */
  @Test
  public void test01_SearchSpace() {
    info("Test 01: Search Space");
    String space = "cspace" + getRandomNumber();
    String space1 = "1space" + getRandomNumber();
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    info("Create a space");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space, space);
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space, "1");
    ELEMENT_SPACES_LIST.find(byText(space)).shouldBe(Condition.visible);
    ELEMENT_SPACES_LIST.find(byText(space1)).shouldNotBe(Condition.visible);
    spaceManagement.searchByLetterList("C", space);
    ELEMENT_SPACES_LIST.find(byText(space1)).shouldNotBe(Condition.visible);
    spaceManagement.searchByLetterList("All", space);
    homePagePlatform.goToMySpaces();
    spaceManagement.deleteSpace(space, false);
    spaceManagement.deleteSpace(space1, false);

  }

  @BugInPLF("PLF-8006")
  @Test
  public void test02_SearchSpaceWithPatern() {

    String space1 = "space" + getRandomNumber();
    String space2 = "space" + getRandomNumber();
    String space3 = "space" + getRandomNumber();
    String space4 = "space" + getRandomNumber();
    String space5 = "space" + getRandomNumber();
    String space6 = "space" + getRandomNumber();
    String space7 = "space" + getRandomNumber();
    String space8 = "space" + getRandomNumber();
    String space9 = "space" + getRandomNumber();
    String space10 = "space" + getRandomNumber();
    String space11 = "space" + getRandomNumber();
    String space12 = "space" + getRandomNumber();
    String space13 = "space" + getRandomNumber();
    String space14 = "space" + getRandomNumber();
    String space15 = "space" + getRandomNumber();
    String space16 = "space" + getRandomNumber();
    String space17 = "space" + getRandomNumber();
    String space18 = "space" + getRandomNumber();
    String space19 = "space" + getRandomNumber();
    String space20 = "space" + getRandomNumber();
    String space21 = "space" + getRandomNumber();
    String spaceexo1 = "exo" + getRandomNumber();
    String spaceexo2 = "exo" + getRandomNumber();
    String spaceexo3 = "exo" + getRandomNumber();
    String spaceexo4 = "exo" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceexo1, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceexo2, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceexo3, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(spaceexo4, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space2, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space3, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space4, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space5, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space6, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space7, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space8, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space9, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space10, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space11, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space12, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space13, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space14, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space15, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space16, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space17, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space18, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space19, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space20, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space21, "");
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space1, "");
    ELEMENT_SPACES_LIST.find(byText(space1)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space2, "");
    ELEMENT_SPACES_LIST.find(byText(space2)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space3, "");
    ELEMENT_SPACES_LIST.find(byText(space3)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space4, "");
    ELEMENT_SPACES_LIST.find(byText(space4)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space5, "");
    ELEMENT_SPACES_LIST.find(byText(space5)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space6, "");
    ELEMENT_SPACES_LIST.find(byText(space6)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space7, "");
    ELEMENT_SPACES_LIST.find(byText(space7)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space8, "");
    ELEMENT_SPACES_LIST.find(byText(space8)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space9, "");
    ELEMENT_SPACES_LIST.find(byText(space9)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space10, "");
    ELEMENT_SPACES_LIST.find(byText(space10)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space11, "");
    ELEMENT_SPACES_LIST.find(byText(space11)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space12, "");
    ELEMENT_SPACES_LIST.find(byText(space12)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space13, "");
    ELEMENT_SPACES_LIST.find(byText(space13)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space14, "");
    ELEMENT_SPACES_LIST.find(byText(space14)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space15, "");
    ELEMENT_SPACES_LIST.find(byText(space15)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space16, "");
    ELEMENT_SPACES_LIST.find(byText(space16)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space17, "");
    ELEMENT_SPACES_LIST.find(byText(space17)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space18, "");
    ELEMENT_SPACES_LIST.find(byText(space18)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space19, "");
    ELEMENT_SPACES_LIST.find(byText(space19)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space20, "");
    ELEMENT_SPACES_LIST.find(byText(space20)).click();
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space21, "");
    ELEMENT_SPACES_LIST.find(byText(space21)).click();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    ELEMENT_SEARCH_SPACE.setValue("exo");
    ELEMENT_SPECIFIC_PANEL.find(byText(spaceexo1)).shouldBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(spaceexo2)).shouldBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(spaceexo3)).shouldBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(spaceexo4)).shouldBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space1)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space2)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space3)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space4)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space5)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space6)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space7)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space8)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space9)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space10)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space11)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space12)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space13)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space14)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space15)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space16)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space17)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space18)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space19)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space20)).shouldNotBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(space21)).shouldNotBe(Condition.visible);
    homePagePlatform.goToMySpaces();
    spaceManagement.searchSpace(space1, "");
    spaceManagement.deleteSpace(space1, false);
    spaceManagement.searchSpace(space2, "");
    spaceManagement.deleteSpace(space2, false);
    spaceManagement.searchSpace(space3, "");
    spaceManagement.deleteSpace(space3, false);
    spaceManagement.searchSpace(space4, "");
    spaceManagement.deleteSpace(space4, false);
    spaceManagement.searchSpace(space5, "");
    spaceManagement.deleteSpace(space5, false);
    spaceManagement.searchSpace(space6, "");
    spaceManagement.deleteSpace(space6, false);
    spaceManagement.searchSpace(space7, "");
    spaceManagement.deleteSpace(space7, false);
    spaceManagement.searchSpace(space8, "");
    spaceManagement.deleteSpace(space8, false);
    spaceManagement.searchSpace(space9, "");
    spaceManagement.deleteSpace(space9, false);
    spaceManagement.searchSpace(space10, "");
    spaceManagement.deleteSpace(space10, false);
    spaceManagement.searchSpace(space11, "");
    spaceManagement.deleteSpace(space11, false);
    spaceManagement.searchSpace(space12, "");
    spaceManagement.deleteSpace(space12, false);
    spaceManagement.searchSpace(space13, "");
    spaceManagement.deleteSpace(space13, false);
    spaceManagement.searchSpace(space14, "");
    spaceManagement.deleteSpace(space14, false);
    spaceManagement.searchSpace(space15, "");
    spaceManagement.deleteSpace(space15, false);
    spaceManagement.searchSpace(space16, "");
    spaceManagement.deleteSpace(space16, false);
    spaceManagement.searchSpace(space17, "");
    spaceManagement.deleteSpace(space17, false);
    spaceManagement.searchSpace(space18, "");
    spaceManagement.deleteSpace(space18, false);
    spaceManagement.searchSpace(space19, "");
    spaceManagement.deleteSpace(space19, false);
    spaceManagement.searchSpace(space20, "");
    spaceManagement.deleteSpace(space20, false);
    spaceManagement.searchSpace(space21, "");
    spaceManagement.deleteSpace(space21, false);
    spaceManagement.searchSpace(spaceexo1, "");
    spaceManagement.deleteSpace(spaceexo1, false);
    spaceManagement.searchSpace(spaceexo2, "");
    spaceManagement.deleteSpace(spaceexo2, false);
    spaceManagement.searchSpace(spaceexo3, "");
    spaceManagement.deleteSpace(spaceexo3, false);
    spaceManagement.searchSpace(spaceexo4, "");
    spaceManagement.deleteSpace(spaceexo4, false);

  }
}
