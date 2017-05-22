package org.exoplatform.platform.qa.ui.gatein;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_GROUP_NAME;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalGroupNavigation;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

@Tag("gatein")
@Tag("smoke")
public class GateinGroupNavigationManageNavigationTestIT extends Base {

  HomePagePlatform       homePagePlatform;

  UserAndGroupManagement userAndGroupManagement;

  NavigationToolbar      navigationToolbar;

  PortalGroupNavigation  portalGroupNavigation;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    userAndGroupManagement = new UserAndGroupManagement(this);
    navigationToolbar = new NavigationToolbar(this);
    portalGroupNavigation = new PortalGroupNavigation(this);
  }

  /**
   * Test Case ID:123103. Test Case Name: Show group navigation list Test Case
   * ID:123104. Test Case Name: Add Navigation of group. Test Case ID:123043.
   * Test Case Name: Manage Navigation of group.
   */
  @Test
  public void test01_02_03_Show_Add_ManageGroupNavigation() {
    String groupName = "group-" + getRandomNumber();
    String group1 = "group1-" + getRandomNumber();
    String group2 = "group2-" + getRandomNumber();
    String group3 = "group3-" + getRandomNumber();
    String group4 = "group4-" + getRandomNumber();
    String group5 = "group5-" + getRandomNumber();

    info("Create new group with John");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.goToGroupTab();
    userAndGroupManagement.addGroup(groupName, groupName, groupName, true);

    /*
     * Step Number: 1 Step Name: Show Navigation list for group Step
     * Description: - Go to Administration/Portal/Group Sites Input Data:
     * Expected Outcome: - Show group navigation list
     */
    info("Test Case 01: Show navigation list default of user John");
    navigationToolbar.goToGroupSites();
    waitForAndGetElement(By.xpath(ELEMENT_GROUP_NAME.replace("${groupName}", group1))).isDisplayed();
    waitForAndGetElement(By.xpath(ELEMENT_GROUP_NAME.replace("${groupName}", group2))).isDisplayed();
    waitForAndGetElement(By.xpath(ELEMENT_GROUP_NAME.replace("${groupName}", group3))).isDisplayed();
    waitForAndGetElement(By.xpath(ELEMENT_GROUP_NAME.replace("${groupName}", group4))).isDisplayed();
    waitForAndGetElement(By.xpath(ELEMENT_GROUP_NAME.replace("${groupName}", group5))).isDisplayed();

    /*
     * Step Number: 2 Step Name: Add Navigation of group Step Description: - Go
     * to Administration/ Portal / Group Sites - Click Add Navigation button -
     * Select a navigation in list and click Add navigation icon Input Data:
     * Expected Outcome: - Add Navigation successfully
     */
    info("TestCase 02: Add new navigation for new group");
    portalGroupNavigation.addNewNavigationForGroup(groupName);

    info("Show navigation list after add a new group of user John");
    waitForAndGetElement(By.xpath(ELEMENT_GROUP_NAME.replace("${groupName}", groupName))).isDisplayed();
    waitForAndGetElement(By.xpath(ELEMENT_GROUP_NAME.replace("${groupName}", group1))).isDisplayed();
    waitForAndGetElement(By.xpath(ELEMENT_GROUP_NAME.replace("${groupName}", group2))).isDisplayed();
    waitForAndGetElement(By.xpath(ELEMENT_GROUP_NAME.replace("${groupName}", group3))).isDisplayed();
    waitForAndGetElement(By.xpath(ELEMENT_GROUP_NAME.replace("${groupName}", group4))).isDisplayed();
    waitForAndGetElement(By.xpath(ELEMENT_GROUP_NAME.replace("${groupName}", group5))).isDisplayed();

    /*
     * Step Number: 3 Step Name: Delete Navigation of group Step Description: -
     * Go to Administration/ Portal / Group Sites - Click Delete Navigation
     * button of a Group Input Data: Expected Outcome: - The navigation group is
     * removed successfully
     */
    info("Delete navigation of group");
    portalGroupNavigation.deleteNavigationForGroup(groupName);

    info("Delete group");
    navigationToolbar.goToUsersAndGroupsManagement();
    userAndGroupManagement.goToGroupTab();
    click(By.linkText(groupName));
    userAndGroupManagement.deleteGroup(groupName, true, 60000);
  }
}
