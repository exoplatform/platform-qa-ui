package org.exoplatform.platform.qa.ui.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_MY_PROFILE_LINK;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOPBAR_AVATAR;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.ELEMENT_COMMENT_EDIT;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_ICON_STATUS;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_PROJECT_ICON_ADD_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TribeSpaceManagement {

  private final TestBase testBase;

  public ManageAlert alert;

  public HomePagePlatform homePagePlatform;

  private ElementEventTestBase evt;

  public TribeSpaceManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.homePagePlatform = new HomePagePlatform(testBase);
    this.alert = new ManageAlert(testBase);
  }

  /**
   * delete Space
   *
   * @param spaceName name of space
   * @param isVerify  true: verify content of confirm msg false: not verify content
   *                  of confirm msg
   */
  public void deleteSpace(String spaceName, Boolean isVerify) {
    if ($(byText(spaceName)).is(Condition.exist)) {
      info("Do delete space");
      searchSpace(spaceName);
      ELEMENT_SPACES_LIST.find(byText(spaceName)).parent().parent().parent().find(byText("Supprimer")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
      if (isVerify)
        alert.verifyAlertMessage(ELEMENT_SPACE_TRIBE_CONFIRM_DELETE);
      $(ELEMENT_TRIBE_DELETE_SPACE_OK_BUTTON).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
      ELEMENT_SPACES_LIST.find(byText(spaceName)).waitUntil(Condition.disappear, 200000);
    }
  }


  public void deleteTribeSpace(String spaceName) {
      info("Do delete space");
      searchSpace(spaceName);
      sleep(1000);
      $(ELEMENT_TRIBE_MANAGE_SPACE_BUTTON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
      $(ELEMENT_TRIBE_REMOVE_SPACE_BUTTON).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
      $(ELEMENT_TRIBE_REMOVE_SPACE_OK_BUTTON).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
      ELEMENT_NO_SPACES_LIST.waitUntil(Condition.exist,500000);

  }

  public void searchSpaceViaRecentSpaces(String spaceName) {

    sleep(1000);
    ELEMENT_TRIBE_LAST_VISITED_SPACES_CHECK_ORDER.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).hover();
    ELEMENT_TRIBE_RECENT_SPACES.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    sleep(2000);
    ELEMENT_TRIBE_FILTER_SPACES_SEARCH.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).sendKeys(spaceName);
    sleep(2000);
    $(byXpath(ELEMENT_SELECTED_LAST_VISITED_SPACE.replace("${space}",spaceName))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

  }

  public void accessToTheSearchedSpace(String spaceName) {

    $(byXpath(ELEMENT_SELECTED_LAST_VISITED_SPACE.replace("${space}",spaceName))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    sleep(3000);
    $(byXpath("//*[@class='pl-2 align-self-center brandingContainer space']//*[contains(text(),'${spaceName}')]".replace("${spaceName}",spaceName)))
            .waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

  }

  public void selectSpaceFromLastVisitedSpaces(String spaceName) {

    ELEMENT_TRIBE_LAST_VISITED_SPACES_CHECK_ORDER.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).hover();
    sleep(1000);
    $(byXpath(ELEMENT_SELECTED_LAST_VISITED_SPACE.replace("${space}",spaceName))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    sleep(3000);
    $(byXpath("//*[@class='pl-2 align-self-center brandingContainer space']//*[contains(text(),'${spaceName}')]".replace("${spaceName}",spaceName)))
            .waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

  }

  /**
   * Leave a space
   *
   * @param space
   */
  public void leaveSpace(String space) {
    info("Do leave space");
    $(byXpath(ELEMENT_SPACE_LEAVE_BTN.replace("${space}", space))).click();
    $(byXpath(ELEMENT_SPACE_LEAVE_BTN.replace("${space}", space))).shouldNot(Condition.visible);
  }

  /**
   * Create quickly a new space
   *
   * @param name : Space name
   * @param desc : Space description
   */
  public void addNewSpaceSimple(String name, String desc, int... params) {
    ELEMENT_ADDNEWSPACE_TRIBE_BTN.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SPACE_NAME_INPUT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(name);
    ELEMENT_SPACE_DESCRIPTION_INPUT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(desc);
    info("Save all changes");
    ELEMENET_SPACE_CREATE_TRIBE_BUTTON.waitUntil(Condition.visible, 80000).click();
    ELEMENET_SPACE_CREATE_TRIBE_BUTTON.waitUntil(Condition.not(Condition.visible), Configuration.openBrowserTimeoutMs + Configuration.openBrowserTimeoutMs);
  }

  /**
   * Add a new space
   *
   * @param name
   * @param desc
   * @param access
   * @param groups
   * @param params
   */
  public void addNewSpaceTribe(String name, String desc, String access, String hidden, ArrayList<String> groups, int... params) {
    if ($(ELEMENT_ADDNEWSPACE_SECOND_TRIBE_BUTTON).waitUntil(Condition.visible, Configuration.timeout) != null) {
      $(ELEMENT_ADDNEWSPACE_SECOND_TRIBE_BUTTON).click();
    }
    $(ELEMENT_ADDNEWSPACE_SECOND_TRIBE_FORM).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_SPACE_DETAILS_TRIBE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    $(ELEMENT_SPACE_NAME_SECOND_TRIBE_INPUT).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(name);
    $(ELEMENT_SPACE_DESCRIPTION_SECOND_TRIBE_INPUT).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(desc);
    $(byXpath("(//*[@class='layout column']//*[@class='v-btn__content'])[1]")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SPACE_ACCESS_TRIBE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

    if (hidden == "Yes") {
      if ($(byXpath("//*[@class='v-input--selection-controls__input']/input[@type='checkbox' and @aria-checked='false']")).exists()) {
        evt.check(byXpath("//*[@class='v-input--selection-controls__input']"));

      }
    }
    if (hidden == "No") {
      if ($(byXpath("//*[@class='v-input--selection-controls__input']/input[@type='checkbox' and @aria-checked='true']")).exists()) {
        evt.check(byXpath("//*[@class='v-input--selection-controls__input']"));
      }
    }
    if (!access.isEmpty()) {
      info("Select a permission for space:" + access);

      if(access=="Open"){
        evt.check(byXpath("//input[@value='open']"));
      }

      if(access=="Validation"){
        evt.check(byXpath("//input[@value='validation']"));
      }

      if(access=="Closed"){
        evt.check(byXpath("//input[@value='closed']"));
        ;
      }
    }

    $(byXpath("(//*[@class='layout column']//*[@class='v-btn__content'])[3]")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_INVITE_USERS_TRIBE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    if (groups!= null) {
      for (int i = 0; i < groups.size(); i++) {
        sleep(2000);
        ELEMENT_SPACE_INPUT_USER_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
        ELEMENT_SPACE_INPUT_USER_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(groups.get(i));
        $(byXpath("//*[@class='v-list-item__title text-truncate identitySuggestionMenuItemText' and contains(text(),'${group}')]".replace("${group}", groups.get(i)))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
        }
    }
    info("Save all changes");
    ELEMENT_CREATE_SPACE_TRIBE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byXpath("//*[@class='pl-2 align-self-center brandingContainer space']//*[contains(text(),'${spaceName}')]"
            .replace("${spaceName}",name))).waitUntil(Condition.visible,60000);

  }

  public void addNewSpace(String name, String desc, String access, String hidden, ArrayList<String> groups, int... params) {
    if ($(ELEMENT_ADDNEWSPACE_SECOND_TRIBE_BUTTON).waitUntil(Condition.visible, Configuration.timeout) != null) {
      $(ELEMENT_ADDNEWSPACE_SECOND_TRIBE_BUTTON).click();
    }
    $(ELEMENT_ADDNEWSPACE_SECOND_TRIBE_FORM).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    ELEMENT_SPACE_DETAILS_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    $(ELEMENT_SPACE_NAME_SECOND_TRIBE_INPUT).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(name);
    $(ELEMENT_SPACE_DESCRIPTION_SECOND_TRIBE_INPUT).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(desc);
    $(byXpath("(//*[@class='layout column']//*[@class='v-btn__content' and contains(text(),'Continue')])[1]")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_SPACE_ACCESS_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);

    if (hidden == "Yes") {
      if ($(byXpath("//*[@class='v-input--selection-controls__input']/input[@type='checkbox' and @aria-checked='false']")).exists()) {
        evt.check(byXpath("//*[@class='v-input--selection-controls__input']"));

      }
    }
    if (hidden == "No") {
      if ($(byXpath("//*[@class='v-input--selection-controls__input']/input[@type='checkbox' and @aria-checked='true']")).exists()) {
        evt.check(byXpath("//*[@class='v-input--selection-controls__input']"));
      }
    }
    if (!access.isEmpty()) {
      info("Select a permission for space:" + access);

      if(access=="Open"){
        evt.check(byXpath("//input[@value='open']"));
      }

      if(access=="Validation"){
        evt.check(byXpath("//input[@value='validation']"));
      }

      if(access=="Closed"){
        evt.check(byXpath("//input[@value='closed']"));
        ;
      }
    }

    $(byXpath("(//*[@class='layout column']//*[@class='v-btn__content' and contains(text(),'Continue')])[2]")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_INVITE_USERS_DW.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs);
    if (groups!= null) {
      for (int i = 0; i < groups.size(); i++) {
        sleep(2000);
        ELEMENT_SPACE_INPUT_USER_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
        ELEMENT_SPACE_INPUT_USER_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(groups.get(i));
        $(byXpath("//*[@class='v-list-item__title text-truncate identitySuggestionMenuItemText' and contains(text(),'${group}')]".replace("${group}", groups.get(i)))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
      }
    }
    info("Save all changes");
    ELEMENT_CREATE_SPACE_TRIBE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(byXpath("//*[@class='pl-2 align-self-center brandingContainer space']//*[contains(text(),'${spaceName}')]"
            .replace("${spaceName}",name))).waitUntil(Condition.visible,60000);

  }

  /**
   * Update Space Description
   */
  public void updateSpaceDescription(String desc) {
    info("Update Space Description ");
    ELEMENT_SPACE_DESCRIPTION_INPUT.clear();
    ELEMENT_SPACE_DESCRIPTION_INPUT.setValue(desc);
    info("Save all changes");
    ELEMENET_SPACE_SAVE_BUTTON.waitUntil(Condition.visible, 6000).click();
    $(byXpath("//div[@class='uiAction uiActionBorder']/a")).waitUntil(Condition.visible, Configuration.timeout).click();
    refresh();
  }

  /**
   * Open Invite users from group tab
   */
  public void goToInviteUserFromGroupTab() {
    info("evt.click on the Invite users from group tab");
    evt.click(ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_TAB);
    evt.waitForAndGetElement(ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX, 2000, 2);
    info("The tab is shown");
  }

  /**
   * Assign User as a manager
   */
  public void assignUnassignUserAsManager(String oneUser, ArrayList<String> manyUsers) {

    if (oneUser != null) {
      $(ELEMENT_SPACE_MEMBERS).waitUntil(Condition.visible, Configuration.timeout).click();
      final String assignOrRemoveManagerRole = "(//table[@class='uiGrid table  table-hover table-striped']//th[3]/following::tr[@id='existingUsersTable']/td[text()='${user}']/following::div[@class='uiSwitchBtn'])[1]";
      $(byXpath(assignOrRemoveManagerRole.replace("${user}", oneUser))).waitUntil(Condition.visible, Configuration.timeout).click();
    }
    if (manyUsers != null) {
      $(ELEMENT_SPACE_MEMBERS).waitUntil(Condition.visible, Configuration.timeout).click();

      final String assignOrRemoveManagerRole = "(//table[@class='uiGrid table  table-hover table-striped']//th[3]/following::tr[@id='existingUsersTable']/td[text()='${user}']/following::div[@class='uiSwitchBtn'])[1]";
      $(byXpath(assignOrRemoveManagerRole.replace("${user}", manyUsers.get(0)))).waitUntil(Condition.visible, Configuration.timeout).click();
      $(byXpath(assignOrRemoveManagerRole.replace("${user}", manyUsers.get(2)))).waitUntil(Condition.visible, Configuration.timeout).click();
    }

  }

  /**
   * Go to Home Space
   */
  public void goToHomeSpace() {
    $(By.xpath("//i[@class='uiIconAppSpaceHomePage uiIconDefaultApp']")).waitUntil(Condition.visible, Configuration.timeout).click();
  }

  /**
   * Edit a Space
   *
   * @param space
   * @param newName
   * @param newDes
   * @param isChangeAvatar
   * @param filepath
   */
  public void editSpaceSimple(String space, String newName, String newDes, boolean isChangeAvatar, String filepath) {
    info("evt.click on Edit button of the space");
    if ($(byXpath(ELEMENT_SPACE_EDIT_BTN.replace("${space}", space))).is(Condition.visible))
      $(byXpath(ELEMENT_SPACE_EDIT_BTN.replace("${space}", space))).click();
    if ($(ELEMENT_SPACE_NAME_INPUT).is(Condition.not(Condition.visible)))
      $(ELEMENT_SPACE_EDIT_SETTING_TAB).click();
    if (!newName.isEmpty()) {
      info("Input new name");
      $(ELEMENT_SPACE_NAME_INPUT).setValue(newName);
    }
    if (!newDes.isEmpty()) {
      info("Input new description");
      $(ELEMENT_SPACE_DESCRIPTION_INPUT).setValue(newDes);
    }
    if (isChangeAvatar == true) {
      info("evt.click on change picture button");
      $(ELEMENT_SPACE_CHANGE_AVATAR_BTN).waitUntil(Condition.visible,Configuration.timeout).click();
      $(byId("UIPopupAvatarUploader")).find(byClassName("file")).uploadFromClasspath(filepath);

      info("filepath:" + filepath);
      sleep(2000);
      $(ELEMENT_SPACE_UPLOAD_CONFIRM_BTN).click();
      $(ELEMENT_SPACE_UPLOAD_SAVE_BTN).waitUntil(Condition.visible,Configuration.timeout).click();
    }
  }

  /**
   * Save change all when edit a space
   */
  public void saveChangesSpace() {
    info("evt.click on Save button");
    sleep(2000);
    (ELEMENT_SPACE_SAVE_BTN).click();
    sleep(2000);
    info("Save all changes");
  }

  /**
   * Check Avatar User Dimensions
   */
  public void checkAvatarUserDimensions() {
    final String ELEMENT_AVATAR_IMAGE_HEIGHT = String.valueOf($(byXpath("//ul[@id=\"spaceManagers\"]/li/a/img")).getSize().getHeight());
    Assert.assertEquals("Avatar Image Height is not " + ELEMENT_AVATAR_IMAGE_HEIGHT + " px", "30", ELEMENT_AVATAR_IMAGE_HEIGHT);
    final String ELEMENT_AVATAR_IMAGE_WIDTH = String.valueOf($(byXpath("//ul[@id=\"spaceManagers\"]/li/a/img")).getSize().getWidth());
    Assert.assertEquals("Avatar Image Width is not " + ELEMENT_AVATAR_IMAGE_WIDTH + " px", "30", ELEMENT_AVATAR_IMAGE_WIDTH);
  }

  /**
   * Check Description Padding Dimensions
   */
  public void checkDescriptionPortletPadding() {
    Assert.assertEquals("The line height of space description is not 20 px", "20px", ELEMENT_SPACE_DESCRIPTION.getCssValue("line-height"));
    Assert.assertEquals("The font size of space description is not 14 px", "14px", ELEMENT_SPACE_DESCRIPTION.getCssValue("font-size"));
    Assert.assertEquals("The color of space description is not #333333", "#333333", getCSSColor(ELEMENT_SPACE_DESCRIPTION));
    Assert.assertEquals("The font weight of space description is not 400", "400", ELEMENT_SPACE_DESCRIPTION.getCssValue("font-weight"));
    Assert.assertEquals("The font family of space description is not Helvetica, arial, sans-serif", "Helvetica, arial, sans-serif", ELEMENT_SPACE_DESCRIPTION.getCssValue("font-family"));
  }

  /**
   * Get CSS Color Value
   */
  public static String getCSSColor(SelenideElement spaceElement) {
    String[] hexValue = spaceElement.getCssValue("color").replace("rgba(", "").replace(")", "").split(",");
    hexValue[0] = hexValue[0].trim();
    int hexValue1 = Integer.parseInt(hexValue[0]);
    hexValue[1] = hexValue[1].trim();
    int hexValue2 = Integer.parseInt(hexValue[1]);
    hexValue[2] = hexValue[2].trim();
    int hexValue3 = Integer.parseInt(hexValue[2]);
    String color = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
    return color;
  }

  /**
   * Check Space Description and Space Manager Name
   */
  public void checkSpaceNameAndDescriptionSpaceManagerNameTitleEventNameAndDateAndToolTips(String spaceDesa, String oneManagerName, ArrayList<String> managersNames, String space, String titleEvent, String dateEvent, String toolTips) {
    if (spaceDesa != null) {
      info("Space Description is : " + spaceDesa);
      ELEMENT_SPACE_DESCRIPTION.waitUntil(Condition.visible, Configuration.timeout);
      assertEquals(spaceDesa, ELEMENT_SPACE_DESCRIPTION.getText());
    }
    if (oneManagerName != null) {
      info("Space Manager is : " + oneManagerName);
      assertEquals(oneManagerName, ELEMENT_SPACE_MANAGER_NAME.getText());
    }

    if (managersNames != null) {
      final String ELEMENT_SPACE_MANAGER = ("//div[@id='spaceManagersList']/ul/li/a[@href and contains(text(),'${user}')]");
      if (managersNames.get(0) != null && $(byXpath(ELEMENT_SPACE_MANAGER.replace("${user}", managersNames.get(0)))).waitUntil(Condition.appears, Configuration.timeout).isDisplayed()) {
        assertEquals(managersNames.get(0), $(byXpath(ELEMENT_SPACE_MANAGER.replace("${user}", managersNames.get(0)))).getText());
      } else {
        $(byXpath(ELEMENT_SPACE_MANAGER)).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
      }
      if (managersNames.get(1) != null && $(byXpath(ELEMENT_SPACE_MANAGER.replace("${user}", managersNames.get(1)))).waitUntil(Condition.appears, Configuration.timeout).isDisplayed()) {
        assertEquals(managersNames.get(1), $(byXpath(ELEMENT_SPACE_MANAGER.replace("${user}", managersNames.get(1)))).getText());
      } else {
        $(byXpath(ELEMENT_SPACE_MANAGER)).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
      }
      if (managersNames.get(2) != null && $(byXpath(ELEMENT_SPACE_MANAGER.replace("${user}", managersNames.get(2)))).waitUntil(Condition.appears, Configuration.timeout).isDisplayed()) {
        assertEquals(managersNames.get(2), $(byXpath(ELEMENT_SPACE_MANAGER.replace("${user}", managersNames.get(2)))).getText());
      } else {
        $(byXpath(ELEMENT_SPACE_MANAGER)).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
      }
    }
    if (space != null) {
      final SelenideElement ELEMENT_SPACE_NAME = $(byXpath("//div[@id='CalendarContainer']/div[@class='eventTitle']/span[@id and contains(text(),'$Space')]".replace("$Space", space)));
      ELEMENT_SPACE_NAME.waitUntil(Condition.visible, Configuration.timeout).exists();
      info("Space Name is : " + space);
      assertEquals(space, ELEMENT_SPACE_NAME.getText());
    }
    if (titleEvent != null) {
      final SelenideElement ELEMENT_TITLE_EVENT_NAME = $(byXpath("//div[@class='pull-left eventSummary']/a[contains(text(),'$TitleEvent')]".replace("$TitleEvent", titleEvent)));
      ELEMENT_TITLE_EVENT_NAME.waitUntil(Condition.visible, Configuration.timeout);
      info("Title Event is : " + titleEvent);
      assertEquals(titleEvent, ELEMENT_TITLE_EVENT_NAME.getText());
    }
    if (dateEvent != null) {
      final String[] ELEMENT_CURRENT_DATE = $(byXpath("//div[@class='currentDateContainer']/center/a")).getText().split(": ");
      info("Created Event Month is : ");
      assertEquals(dateEvent.substring(5, 7), ELEMENT_CURRENT_DATE[1].substring(0, 2));
      info("Created Event day is : ");
      if (dateEvent.length()==18) {
        if($(byXpath("//center//*[contains(text(),'Yesterday')]")).isDisplayed()) {
          assertEquals(dateEvent.substring(9, 10), ELEMENT_CURRENT_DATE[1].substring(3, 4));
          info("Created Event Year is : ");
          assertEquals(dateEvent.substring(0, 4), ELEMENT_CURRENT_DATE[1].substring(5, 9));
        }
          else{
            assertEquals(dateEvent.substring(9, 10), ELEMENT_CURRENT_DATE[1].substring(3, 4));
            info("Created Event Year is : ");
            assertEquals(dateEvent.substring(0, 4), ELEMENT_CURRENT_DATE[1].substring(6, 10));
        }
      } else {
        if($(byXpath("//center//*[contains(text(),'Yesterday')]")).isDisplayed()) {
          assertEquals(dateEvent.substring(8, 10), ELEMENT_CURRENT_DATE[1].substring(3, 5));
          info("Created Event Year is : ");
          assertEquals(dateEvent.substring(0, 4), ELEMENT_CURRENT_DATE[1].substring(6, 10));
        }
        else{
          assertEquals(dateEvent.substring(8, 10), ELEMENT_CURRENT_DATE[1].substring(3, 5));
          info("Created Event Year is : ");
          assertEquals(dateEvent.substring(0, 4), ELEMENT_CURRENT_DATE[1].substring(6, 10));
        }
      }

    }
    if (toolTips != null) {
      info("Left arrow of the event calendar");
      $(byXpath("//a[@class='actionIcon prevDate pull-left']")).waitUntil(Condition.visible, Configuration.timeout);
      info("Right arrow of the event calendar");
      $(byXpath("//i[@class='uiIconMiniArrowRight uiIconLightGray']")).waitUntil(Condition.visible, Configuration.timeout);
    }
  }

  /**
   * Check Manager Title Dimensions
   */
  public void checkManagerTitleDimensions() {
    Assert.assertEquals("The line height of space manager name is not 20px", "20px", ELEMENT_SPACE_MANAGER_NAME.getCssValue("line-height"));
    Assert.assertEquals("The font size of space manager name is not 14px", "14px", ELEMENT_SPACE_MANAGER_NAME.getCssValue("font-size"));
    Assert.assertEquals("The color of space manager name is not #333333", "#333333", getCSSColor(ELEMENT_SPACE_MANAGER_NAME));
    Assert.assertEquals("The font weight of space manager name is not 400", "400", ELEMENT_SPACE_MANAGER_NAME.getCssValue("font-weight"));
    Assert.assertEquals("The font family of space manager name is not Helvetica, arial, sans-serif", "Helvetica, arial, sans-serif", ELEMENT_SPACE_MANAGER_NAME.getCssValue("font-family"));
  }

  /**
   * Check Who's Online Portlet Dimensions
   */
  public void checkWhoIsOnlinePortletDimensions(String onlineUser) {
    final SelenideElement onlineUserInSpace = $(byXpath("//h6[text()=\"Who's Online?\"]/following::img[@src=\"/portal/rest/v1/social/users/${onlineUserSpace}/avatar\"]".replace("${onlineUserSpace}", onlineUser)));
    Assert.assertEquals("The line height of Online User is not 20px", "20px", onlineUserInSpace.getCssValue("line-height"));
    Assert.assertEquals("The font size of Online User is not 14px", "14px", onlineUserInSpace.getCssValue("font-size"));
    Assert.assertEquals("The font weight of Online User is not 400", "400", onlineUserInSpace.getCssValue("font-weight"));
    Assert.assertEquals("The font family of Online User is not Helvetica, arial, sans-serif", "Helvetica, arial, sans-serif", onlineUserInSpace.getCssValue("font-family"));
  }

  /**
   * Check Calendar Portlet
   */
  public void checkCalendarPortletInSpaceHomePage() {
    final SelenideElement calendarCheck = $(byXpath("//div[@class='calendarPortlet']"));
    Assert.assertEquals("The line height of Calendar Portlet is not 20px", "20px", calendarCheck.getCssValue("line-height"));
    Assert.assertEquals("The font size of Calendar Portlet is not 14px", "14px", calendarCheck.getCssValue("font-size"));
    Assert.assertEquals("The color of Calendar Portlet is not #333333", "#333333", getCSSColor(calendarCheck));
    Assert.assertEquals("The font weight of Calendar Portlet is not 400", "400", calendarCheck.getCssValue("font-weight"));
    Assert.assertEquals("The font family of Calendar Portlet is not Helvetica, arial, sans-serif", "Helvetica, arial, sans-serif", calendarCheck.getCssValue("font-family"));
  }

  /**
   * Check No Settings Button Display For Calendar
   */
  public void checkNoSettingsButtonDisplayForCalendar() {
    info("Calendar Settings Button is not displayed");
    final SelenideElement settingsButton = $(byXpath("//a[@class='settingsLink actionIcon pull-right']"));
    settingsButton.shouldNot(Condition.visible);
  }

  /**
   * Change Status
   */
  public void changeStatus(String status) {
    ELEMENT_CHAT_ICON_STATUS.waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
    switch (status) {
      case "Available":
        ELEMENT_USER_STATUS_AVAILABLE.waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
        homePagePlatform.refreshUntil($(byXpath("//a[@class='dropdown-toggle user-available']")), Condition.visible, 700);
        break;
      case "Do not disturb":
        ELEMENT_USER_STATUS_DONOTDISTURB.waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
        break;
      case "Away":
        ELEMENT_USER_STATUS_AWAY.waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
        break;
      case "Invisible":
        ELEMENT_USER_STATUS_INVISIBLE.waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
        break;
    }
  }

  /**
   * Edit User Profile
   */
  public void editUserProfile(String jobTitle) {
    $(ELEMENT_TOPBAR_AVATAR).waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_MY_PROFILE_LINK).waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_COMMENT_EDIT).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byXpath("//input[@id='position']")).waitUntil(Condition.visible, Configuration.timeout).setValue(jobTitle);
    $(byXpath("//div[@class=\"uiAction\"]/button[text()='Save']")).waitUntil(Condition.visible, Configuration.timeout).click();
    refresh();
  }

  /**
   * Check Online User Job Title
   */
  public void checkOnlineUserJobTitle(String onlineUser, String jobTitle) {
    info("Check job title of: " + onlineUser);
    $(byXpath("//h6[text()=\"Who's Online?\"]/following::img[@src=\"/portal/rest/v1/social/users/${onlineUserSpace}/avatar\"]".replace("${onlineUserSpace}", onlineUser))).waitUntil(Condition.visible, Configuration.timeout).hover();
    final String userJobTitle = ($(byXpath("//td[@id=\"profileName\"]/div"))).waitUntil(Condition.visible, Configuration.timeout).getText();
    Assert.assertEquals("Online User job " + jobTitle + " is not displayed", jobTitle, userJobTitle);
  }

  /**
   * Check Online Users
   */
  public void checkOnlineUsers(String onlineUser) {
    info("Online User is displayed: " + onlineUser);
    final SelenideElement onlineUserInSpace = $(byXpath("//h6[text()=\"Who's Online?\"]/following::img[@src=\"/portal/rest/v1/social/users/${onlineUserSpace}/avatar\"]".replace("${onlineUserSpace}", onlineUser)));
    onlineUserInSpace.waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Check Not Online Users
   */
  public void checkNotOnlineUsers(String onlineUser) {
    info("Online User is not displayed: " + onlineUser);
    final SelenideElement onlineUserInSpace = $(byXpath("//h6[text()=\"Who's Online?\"]/following::img[@src=\"/portal/rest/v1/social/users/${onlineUserSpace}/avatar\"]".replace("${onlineUserSpace}", onlineUser)));
    onlineUserInSpace.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
  }

  /**
   * Search a space by name or description
   *
   * @param name
   * @param number
   */
  public void searchSpace(String name, String... number) {
    info("Waiting my space is shown");
    ELEMENT_SPACES_TRIBE_SEARCH_TEXT.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs + Configuration.collectionsTimeout);
    info("Input the space into search text box");
    ELEMENT_SPACES_TRIBE_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).clear();
    ELEMENT_SPACES_TRIBE_SEARCH_TEXT.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(name);
  }

  /**
   * Access to the searched space
   */
  public void accessToSearchedSpace() {
    sleep(2000);
    ELEMENT_SEARCHED_SPACE_TRIBE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
  }

  public void joinSpaceDW() {
    ELEMENT_JOIN_SPACE_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
  }
  /**
   * Edit Layout
   */
  public void editLayout(String action) {
    if ($(ELEMENT_EDIT_BUTTON).isDisplayed()) {
      $(ELEMENT_EDIT_BUTTON).waitUntil(Condition.visible, Configuration.timeout).click();
      $(ELEMENT_EDIT_PAGE).hover();
      $(ELEMENT_EDIT_PAGE_EDITLAYOUT).waitUntil(Condition.visible, Configuration.timeout).click();
      switch (action) {
        case "Add":
          $(byXpath("(//div[@class='txtLeft'])[2]")).dragAndDropTo($(byXpath(("(//div[@class='portletLayoutDecorator' and contains(text(),'Space Menu')]/following::div[@class=\"portletLayoutDecorator\"])[1]"))));
          refresh();
          $(byXpath("//a[@class='uiIconSave uiIconDarkGray pull-right']")).waitUntil(Condition.visible, Configuration.timeout).click();
          $(byXpath("//h5[@class=\"portletName\" and contains(text(),'ECM Admin')]")).exists();
          break;

        case "Delete":
          $(ELEMENT_ECM_ADMIN_PORTLET_LAYOUT).hover();
          ELEMENT_DELETE_ECM_ADMIN_PAGE_EDITLAYOUT.waitUntil(Condition.visible, Configuration.timeout).click();
          switchTo().alert().accept();
          $(byXpath("//a[@class='uiIconSave pull-right uiIconDarkGray']")).waitUntil(Condition.visible, Configuration.timeout).click();
          goToHomeSpace();
          $(byXpath("//h5[@class=\"portletName\" and contains(text(),'ECM Admin')]")).shouldNot(Condition.visible);
          break;
      }

    } else {
      $(ELEMENT_EDIT_BUTTON).shouldNot(Condition.visible);
    }
  }

  /**
   * evt.click on an alpha in the list
   *
   * @param alpha
   * @param name
   */
  public void searchByLetterList(String alpha, String name) {
    info("Waiting my space is shown");
    $(byXpath(ELEMENT_MY_SPACE_LETTER_LIST.replace("${alpha}", alpha))).waitUntil(Condition.visible, Configuration.timeout);
    info("evt.click on the alpha");
    $(byXpath(ELEMENT_MY_SPACE_LETTER_LIST.replace("${alpha}", alpha))).click();
    info("Verify that the space is shown in the search result");
    $(byXpath(ELEMENT_MY_SPACE_SEARCH_RESULT.replace("${name}", name))).waitUntil(Condition.visible, Configuration.timeout);
  }

  /**
   * Open Invitations received tab
   */
  public void goToInvitationsReceivedTab() {
    info("Open Invitation Received tab");
    $(ELEMENT_MY_SPACE_INVITATION_RECEIVED).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_MY_SPACE_INVITATION_RECEIVED).click();
  }

  /**
   * Open All Spaces tab
   */
  public void goToAllSpacesTab() {
    info("Open All spaces tab");
    $(ELEMENT_MY_SPACE_ALL_SPACES_TAB).waitUntil(Condition.visible, Configuration.timeout);
    $(ELEMENT_MY_SPACE_ALL_SPACES_TAB).click();
  }

  /**
   * Send a request to a space
   *
   * @param space
   */
  public void sendARequestToASpace(String space, boolean... isVerify) {
    info("Send a request to a space");
    searchSpace(space);
    sleep(2000);
    if ($(byXpath("//button[@type='button' and text()='Request to Join']")).exists()) {
      $(byText(space)).parent()
              .parent()
              .parent()
              .find(ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN)
              .waitUntil(Condition.visible, Configuration.timeout)
              .click();
    }
    if ($(byXpath("//button[@type='button' and text()='Join']")).exists()) {
      $(byText(space)).parent()
              .parent()
              .parent()
              .find(ELEMENT_MY_SPACE_JOIN_BTN)
              .waitUntil(Condition.visible, Configuration.timeout)
              .click();
    }
    if (isVerify.length > 0) {
      info("Verify that request to join button is hidden and request pending status is shown");
      $(byXpath(ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_PENDING.replace("${space}", space))).waitUntil(Condition.visible, Configuration.timeout);
    }
  }

  /**
   * Open a space in list space
   *
   * @param space
   */
  public void goToSpace(String space) {
    info("Click on the title of the space");
    searchSpace(space);
    $(byXpath(ELEMENT_ALL_SPACE_SPACE_NAME.replace("$space", space.toLowerCase()))).click();
    $(byXpath(ELEMENT_ALL_SPACE_SPACE_NAME.replace("$space", space))).waitUntil(Condition.not(Condition.visible),
            Configuration.timeout);
  }

  /**
   * Verify the message when a user accesses to a space if the user is not member
   * of that space
   *
   * @param space
   */
  public void verifyMessageAccessToSpace(String space) {
    info("Verify that");
    $(By.xpath(ELEMENT_SPACE_ACCESS_SPACE_REQUEST_JOIN_MESSAGE.replace("$space", space))).waitUntil(Condition.visible,
            Configuration.timeout);
  }

  /**
   * /**
   * Open request pending tab
   */
  public void goToRequestPendingTab() {
    info("Open Request pending tab");
    $(ELEMENT_MY_SPACE_REQUEST_PENDING_TAB).click();
  }

  /**
   * Accept an invitation of the space
   *
   * @param space
   */
  public void acceptAInvitation(String space) {
    info("Open invitation received tab");
    goToInvitationsReceivedTab();
    searchSpace(space);
    info("evt.click on Accept button of the space");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_ACCEPT_SPACE_INVITATION).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    info("Verify that the user joijed to the space");
    ELEMENT_LIST_MY_SPACES_IN_LEFT_NAVIGATION.find(byText(space)).should(Condition.exist);
  }

  /**
   * Ignore an invitation of the space
   *
   * @param space
   */
  public void ignoreAInvitation(String space) {
    info("Open invitation received tab");
    goToInvitationsReceivedTab();
    info("evt.click on Ignore button of the space");
    $(byXpath(ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}", space))).click();
    info("Verify that the user didn't join to the space");
    $(byXpath(ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",
            space))).waitUntil(Condition.not(Condition.visible),
            Configuration.timeout);
  }

  /**
   * Open Activity Stream portlet
   */
  public void goToActivityStreamTab() {
    info("Open Activity STream Tab");
    homePagePlatform.refreshUntil($(ELEMENT_ACTIVITY_STREAM_TAB), Condition.visible, 1000);
    sleep(Configuration.timeout);
    $(ELEMENT_ACTIVITY_STREAM_TAB).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byClassName("cke_wysiwyg_frame")).waitUntil(Condition.visible, Configuration.timeout);
    info("Activity STream portlet is shown");
  }

  /**
   * Open Forum portlet
   */
  public void goToForumTab() {
    info("Open forum Tab");
    evt.click(ELEMENT_FORUM_TAB);
    evt.waitForAndGetElement(ELEMENT_FORUM_START_BUTTON_UP, 2000, 0);
    info("Forum portlet is shown");
  }

  /**
   * Open Wiki tab
   */
  public void goToWikiTab() {
    info("Open Wiki Tab");
    executeJavaScript("window.scrollBy(0,-150)");
    homePagePlatform.refreshUntil($(ELEMENT_WIKI_TAB), Condition.visible, 1000);
    $(ELEMENT_WIKI_TAB).click();
    $(ELEMENT_WIKI_HOME_TITLE).waitUntil(Condition.visible, Configuration.timeout);
    info("Wiki portlet is shown");
  }

  public void goToWikiTabDW(String space) {
    info("Open Wiki Tab");
    if(!$(byXpath("//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/wiki']".replace("{space}",space)))
            .isDisplayed())
    {
      $(byXpath("//*[@class='v-slide-group__next']//i")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    }
    $(byXpath("//*[@id='MiddleToolBar']//*[@href='/portal/g/:spaces:{space}/{space}/wiki']".replace("{space}",space)))
            .waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs)
            .click();
    $(ELEMENT_WIKI_HOME_TITLE).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    info("Wiki portlet is shown");
  }

  public void checkThatSpaceTabsAreDisplayedInOrderDW(String space) {

    info("Top Bar Tabs after Space Creation are displayed in order");

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","1"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_HOME_SPACE_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","2"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_DOCUMENTS_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","3"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_TASKS_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","4"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_FORUM_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","5"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_WIKI_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","6"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_MEMBERS_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","7"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_SETTINGS_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));


  }

  public void checkThatSpaceTabsAreDisplayedInOrder(String space) {

    info("Top Bar Tabs after Space Creation are displayed in order");

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","1"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_HOME_SPACE_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","2"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_DOCUMENTS_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","3"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_TASKS_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","4"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_FORUM_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","5"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_WIKI_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","6"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_CALENDAR_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","7"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_MEMBERS_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));

    Assert.assertTrue($(byXpath(ELEMENT_SPACE_TABS_TOP_BAR_ORDER.replace("{i}","8"))).getAttribute("href")
            .contains($(byXpath(ELEMENT_SPACE_SETTINGS_TAB_TOP_BAR.replace("{space}",space))).getAttribute("href")));


  }

  public void goBackToSettingsTabDW() {
    info("Open Wiki Tab");
    $(byXpath("//*[@class='v-toolbar__content']//*[@class='v-btn__content']"))
            .waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs)
            .click();
  }

  public void goToTaskTab() {
    homePagePlatform.refreshUntil(ELEMENT_SPACE_MENU_TAB.find(ELEMENT_TASK_TAB), Condition.visible, 1000);
    ELEMENT_SPACE_MENU_TAB.find(ELEMENT_TASK_TAB).click();
    ELEMENT_PROJECT_ICON_ADD_PROJECT.waitUntil(Condition.visible, Configuration.timeout);
  }

  /**
   * Open Document tab
   */
  public void goToDocumentTab() {
    info("Open Document Tab");
    homePagePlatform.refreshUntil($(ELEMENT_DOCUMENT_TAB), Condition.visible, 1000);
    $(ELEMENT_DOCUMENT_TAB).click();
    $(ELEMENT_DOCUMENT_FOLDER_ADD_BTN).waitUntil(Condition.visible, Configuration.timeout);
    info("Document portlet is shown");
  }

  /**
   * Open Agenda tab
   */
  public void goToAgendaTab() {
    info("Open Agenda Tab");
    homePagePlatform.refreshUntil($(ELEMENT_AGENDA_TAB), Condition.visible, 1000);
    $(ELEMENT_AGENDA_TAB).click();
    $(ELEMENT_AGENDA_EVENT_ADD_BTN).waitUntil(Condition.visible, Configuration.timeout);

    info("Agenda portlet is shown");
  }

  /**
   * Open Member tab
   */
  public void goToMemberTab() {
    info("Open members tab");
    $(ELEMENT_SPACE_WIKI_TAB).waitUntil(Condition.visible, Configuration.timeout);
    if ($(ELEMENT_SPACE_MENU_MORE).is(Condition.visible))
      $(ELEMENT_SPACE_MENU_MORE).click();
    $(ELEMENT_SPACE_SETTINGS_MEMBERS_TAB).click();
    $(byClassName("uiGrayLightBox")).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Create a new folder in Document Tab
   *
   * @param title
   */
  public void createFolder(String title) {
    info("Type a title:" + title + " for the folder");
    $(ELEMENT_ADDFOLDER_NAME).setValue(title);
    info("click on Create folder button");
    $(ELEMENT_ADDFOLDER_CREATEFOLDERBUTTON).waitUntil(Condition.visible, Configuration.timeout).click();
    info("Verify that the folder is created");
    $(byXpath(ELEMENT_DOCUMENT_FOLDER_NAME.replace("$name", title))).isDisplayed();
    info("The folder is created successfully");
  }

  /**
   * Delete Folder
   */
  public void deleteFolder(String folderTitle) {
    info("Check the folder to delete");
    $(byXpath(ELEMENT_DOCUMENT_FOLDER_CHECK.replace("${file}", folderTitle))).waitUntil(Condition.visible, Configuration.timeout).click();
    info("Delete the folder");
    $(byXpath("(//i[@class='uiIconEcmsDelete'])[1]")).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byXpath("//button[@type='button' and text()='Delete']")).waitUntil(Condition.visible, Configuration.timeout).click();
    info("The folder is deleted successfully");
    $(byXpath(ELEMENT_DOCUMENT_FOLDER_CHECK.replace("${file}", folderTitle))).shouldNot(Condition.visible);
  }
}