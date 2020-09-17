package org.exoplatform.platform.qa.ui.selenium.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.junit.Assert;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class SpaceSettingManagement {

  private final TestBase       testBase;

  public ManageAlert           alert;

  public Button                button;

  public PlatformPermission    plfPerm;

  public HomePagePlatform homePagePlatform;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase
   */
  public SpaceSettingManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.plfPerm = new PlatformPermission(testBase);
    this.homePagePlatform= new HomePagePlatform(testBase);
  }

  /**
   * Open member tab
   */
  public void goToMemberTab() {
    info("Open members tab");
    homePagePlatform.refreshUntil($(ELEMENT_SPACE_WIKI_TAB),Condition.visible,1000);
    if($(ELEMENT_SPACE_MENU_MORE).is(Condition.visible))
      $(ELEMENT_SPACE_MENU_MORE).click();
    $(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB).waitUntil(Condition.visible,Configuration.timeout).click();
    $(byClassName("uiGrayLightBox")).waitUntil(Condition.visible, Configuration.timeout);
  }

  public void goToMemberTabInSpaceSettingTab() {
    ELEMENT_SPACE_SETTINGS_MEMBERS_TAB_IN_SETTING_TAB.click();
  }

  /**
   * Invite a user in the space
   *
   * @param userName
   * @param verify is true if want to verify user in invited table. False if don't
   *          want.
   * @param fullName
   */
  public void inviteUser(String userName, boolean verify, String fullName) {

    goToMemberTab();
    info("--Search user ");
    ELEMENT_INPUT_INVITE_USER.click();
    ELEMENT_INPUT_INVITE_USER.sendKeys(userName);
    info("click on Invite button");
    $(byXpath("//*[@id=\"UIUserInvitation\"]/div[2]/div[1]/button")).click();
    if (verify) {
      info("Verify that user is shown in invitation table");
      if (fullName != "" && fullName != null)
        $(byXpath(ELEMENT_SPACE_INVITED_USER_TABLE.replace("${user}", fullName))).waitUntil(Condition.visible, Configuration.timeout);
    }
  }

  /**
   * Change role of a user in the list if role's status is NO, this will change to
   * YES if role's status is YES, this will change to NO
   */
  public void changeRole(String user) {
    info("Open members tab");
    ELEMENT_SPACE_SETTINGS_MEMBERS_TAB_IN_SETTING_TAB.click();
    info("Click on change role button of manager column");
    $(byXpath(ELEMENT_SPACE_CHANGE_ROLE_USER_MEMBER.replace("${user}", user))).click();

  }

    /**
     * Connect to Searched User
     */
    public void connectSearchedUser() {
        if( $(byXpath("//button[text()='Cancel Request']")).isDisplayed()) {
            $(byXpath("//button[text()='Cancel Request']")).waitUntil(Condition.visible, Configuration.timeout).click();
        }
        info("Connect to searched user");
        $(byXpath("//button[text()='Connect']")).waitUntil(Condition.visible, Configuration.timeout).click();
    }

    /**
     * Check User Not Connected
     */
    public void checkUserNotConnected() {
        if( $(byXpath("//button[text()='Connect']")).isDisplayed()) {
            info("User is not connected");
        }
    }

  /**
   * Remove a user in the invited list
   *
   * @param user
   */
  public void removeUser(String user) {
    info("OPen members tab");
    ELEMENT_SPACE_SETTINGS_MEMBERS_TAB_IN_SETTING_TAB.click();
    info("Click on Delete button to remove user");
    $(byText(user)).parent().find(byClassName("uiIconDelete")).click();
    $(byText(user)).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Remove an application
   *
   * @param app
   */
  public void removeApplication(String app) {
    info("Click on Remove icon");
    ELEMENT_LIST_OF_EXISTED_APPLICATION_IN_APPLICATION_TAB.find(byText(app))
            .parent()
            .parent()
            .find(ELEMENT_ICON_DELETE_APPLICATION_FROM_SPACE)
            .waitUntil(Condition.visible,
                    Configuration.openBrowserTimeoutMs).click();
    ELEMENT_LIST_OF_EXISTED_APPLICATION_IN_APPLICATION_TAB.find(byText(app)).waitUntil(Condition.disappears,
            Configuration.openBrowserTimeoutMs);
    info("the application is removed");
  }

  public void removeApplicationDW(String app) {
    info("Click on Remove icon");
    $(byXpath("//*[@id='Application']//*[@title='${app}']/following::*[@class='v-btn__content'][1]//i".replace("${app}",app)))
            .waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs)
            .click();
    ELEMENT_REMOVE_APPLICATION_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byXpath("(//*[@class='v-card__actions']//button)[1]")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("the application is removed");
  }

  /**
   * Accept a pending request to a space
   *
   * @param user is fullName
   */
  public void acceptRequest(String user) {
    info("OPen members tab");
    ELEMENT_SPACE_SETTINGS_MEMBERS_TAB_IN_SETTING_TAB.click();
    info("Click on join button to remove user");
    $(byText(user + " " + user)).parent().find(ELEMENT_ICON_ACCEPT_SPACE_REQUEST_IN_MEMBERS_TAB).click();
    info("Verify that the member is shown in member list");
    $(byText(user + " " + user)).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Open Navigation tab
   */
  public void goToNavigationTab() {
    info("Select Navigation tab");
    $(ELEMENT_SPACE_SETTING_NAVIGATION_TAB).waitUntil(Condition.visible,Configuration.timeout).click();
    info("The tab is opened succcessfully");
    $(ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON).waitUntil(Condition.visible,Configuration.timeout);

  }

  /**
   * Open Application tab
   */
  public void goToApplicationTab() {
    info("Select Application tab");
    $(ELEMENT_SETTINGS_APP_TAB).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_SETTINGS_APP_TAB).click();
    info("The tab is opened succcessfully");
    $(ELEMENT_APPLICATION_TAB_ADD_APPLICATION_BTN).waitUntil(Condition.appears, Configuration.timeout);
  }

  public void goToApplicationTabDW() {
    info("Select Application tab");
    $(ELEMENT_SETTINGS_APP_TAB_DW).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    $(ELEMENT_SETTINGS_APP_TAB_DW).click();
  }

  /**
   * Open Access and Edit tab
   */
  public void goToAccessEditTab() {
    info("Select Application tab");
    if (evt.waitForAndGetElement(ELEMENT_ACCESS_AND_EDIT_TAB, 3000, 0) != null)
      $(ELEMENT_ACCESS_AND_EDIT_TAB).click();
    else
      evt.click(ELEMENT_ACCESS_AND_EDIT_TAB_OF_POPUP);
    info("The tab is opened succcessfully");
    evt.waitForAndGetElement(ELEMENT_ACCESS_HIDDEN_RADIO, 3000, 0);
  }

  /**
   * add a new simple node
   *
   * @param name
   */
  public void addANodeSimple(String name) {
    info("Click on Add node button");
    $(ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON).waitUntil(Condition.visible,Configuration.timeout);
    $(ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON).click();
    info("The popup is shown");
    $(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_NODE_TITLE).waitUntil(Condition.visible,Configuration.timeout);
    info("Input a new name for the node");
    $(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_NAME).setValue(name);
    info("Save all changes");
    $(byXpath("//span[@class='PopupTitle popupTitle' and text()='Add/ Edit Page Node']")).dragAndDropTo($(byXpath("//div[@class='UITableColumnContainer']")));

    $(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE).waitUntil(Condition.visible,Configuration.timeout);
   $(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE).click();
    info("Verify that the node is added successfully");
    $(byXpath(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", name))).waitUntil(Condition.visible,Configuration.timeout);

  }

  /**
   * Add an application
   *
   * @param category
   * @param application
   */
  public void addApplication(String category, String application) {
    info("Click on Add application button");
    executeJavaScript("window.scrollBy(0,-150)");
    $(ELEMENT_APPLICATION_TAB_ADD_APPLICATION_BTN).click();
    info("the popup is shown");
    $(ELEMENT_ADD_APPLICATION_POPUP_TITLE).waitUntil(Condition.appears, Configuration.timeout);
    info("Select a category");
    if (!category.isEmpty())
      $(byId("UIApplicationCategorySelector")).find(byText(category)).click();
    if (!application.isEmpty())
      $(byId("UIApplicationListSelector")).find(byText(application)).parent().parent().find(byClassName("btn-mini")).click();
    info("Close the popup after installed application");
    $(ELEMENT_ADD_APPLICATION_POPUP_CLOSE_BTN).click();
    $(ELEMENT_ADD_APPLICATION_POPUP_TITLE).waitUntil(Condition.disappears, Configuration.timeout);
    info("Check Application added");
      Assert.assertEquals($(byXpath("//div[@class='communityContainer']/strong[text()='${app}']".replace("${app}",application))).getText(),application);
  }

  public void addApplicationDW(String application) {
    info("Click on Add application button");
    sleep(2000);
    $(ELEMENT_APPLICATION_TAB_ADD_APPLICATION_DW).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    $(byXpath("//*[@title='${application}']/following::*[@class='v-btn__content']".replace("${application}",application)))
            .waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs)
            .click();
    info("Check Application added");
    sleep(1000);
    $(byXpath("//*[@id='Application']//*[@title='${app}']/following::*[@class='v-btn__content'][1]//i".replace("${app}",application)))
            .waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

  }

  public void addApplicationTribe(String application) {
    info("Click on Add application button");
    sleep(2000);
    $(ELEMENT_APPLICATION_TAB_ADD_APPLICATION_TRIBE).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    $(byXpath("//*[@title='${application}']/following::*[@class='v-btn__content']".replace("${application}",application)))
            .waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs)
            .click();
    info("Check Application added");
    sleep(1000);
    $(byXpath("//*[@id='Application']//*[@title='${app}']/following::*[@class='v-btn__content'][1]//i".replace("${app}",application)))
            .waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

  }

  /**
   * Edit a node with new label
   *
   * @param nodeName
   * @param label
   */
  public void editANodeSimple(String nodeName, String label) {
    info("Right click on the node");
    $(byXpath(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName))).contextClick();
    info("Select edit link");
    $(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_EDIT).click();
    info("Input a new name for lable field");
    $(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_LABEL).setValue(label);
    info("Save all changes");
    $(ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE).click();
    info("Verify that the node is edited successfully");
    $(byXpath(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", label))).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Set permissions for a space
   *
   * @param arrayRight
   */
  public void setPermissionForSpace(String[] arrayRight) {
    for (String right : arrayRight) {
      info("Select a permission for space:" + right);
      $(byXpath(ELEMENT_ACCESS_PERMISSION_RADIO.replace("${right}", right))).parent().click();
    }
    info("Save all changes");
    if ($(ELEMENT_ACCESS_PERMISSION_SAVE_BTN).is(Condition.visible)) {
      $(ELEMENT_ACCESS_PERMISSION_SAVE_BTN).click();
      $(ELEMENT_ACCESS_INFO_OK_BTN).click();
    } else
      $(ELEMENT_ACCESS_AND_EDIT_TAB_OF_POPUP_CREATE_BTN).click();

  }

  /**
   * Delete a node
   *
   * @param nodeName
   */
  public void deleteANode(String nodeName) {
    info("Right click on the node");
    $(byXpath(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName))).contextClick();
    info("Select delete link");
    $(ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_DELETE).waitUntil(Condition.visible, Configuration.timeout).click();
    alert.acceptAlert();
    info("Verify that the node is deleted successfully");
    $(byXpath(ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", nodeName))).waitUntil(Condition.disappears, Configuration.timeout);
  }

    /**
     * Decline Notification Connect Request
     */
    public void declineNotificationConnectRequest(String userName) {
        ($(byXpath("//div[@class=\"uiDropdownWithIcon dropdown pull-right\"]")).waitUntil(Condition.visible,Configuration.timeout)).click();
        $(byXpath("//div[@class=\"status\"]/a[text()='${user}']/following::div[@class='confirm']/a[text()=\"Refuse\"]".replace("${user}",userName))).waitUntil(Condition.visible,Configuration.timeout).click();

    }
}
