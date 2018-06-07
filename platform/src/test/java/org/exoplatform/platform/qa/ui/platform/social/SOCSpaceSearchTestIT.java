package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byText;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_SEARCH_SPACE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_SPECIFIC_PANEL;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_SPACES_LIST;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import com.codeborne.selenide.Condition;
import java.util.ArrayList;
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

  @Tag("PLF-8006")
  @Test
  public void test02_SearchSpaceWithPatern() {
    ArrayList<String> spaceList = new ArrayList<String>();
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
    spaceList.add("space" + getRandomNumber());
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
    for (int i = 0; i < 21; i++) {
      homePagePlatform.goToMySpaces();
      spaceManagement.addNewSpaceSimple(spaceList.get(i), "");
    }
    for (int i = 0; i < 21; i++) {
      homePagePlatform.goToMySpaces();
      spaceManagement.searchSpace(spaceList.get(i), "");
      ELEMENT_SPACES_LIST.find(byText(spaceList.get(i))).click();
    }

    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    ELEMENT_SEARCH_SPACE.setValue("exo");
    ELEMENT_SPECIFIC_PANEL.find(byText(spaceexo1)).shouldBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(spaceexo2)).shouldBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(spaceexo3)).shouldBe(Condition.visible);
    ELEMENT_SPECIFIC_PANEL.find(byText(spaceexo4)).shouldBe(Condition.visible);
    for (int i = 0; i < 21; i++) {
      ELEMENT_SPECIFIC_PANEL.find(byText(spaceList.get(i))).shouldNotBe(Condition.visible);
    }
    homePagePlatform.goToMySpaces();
    for (int i = 0; i < 21; i++) {
      spaceManagement.searchSpace(spaceList.get(i), "");
      spaceManagement.deleteSpace(spaceList.get(i), false);
    }
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
