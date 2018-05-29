package org.exoplatform.platform.qa.ui.selenium.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_PROJECT_ICON_ADD_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class SpaceManagement {

  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public SpaceManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
  }

  /**
   * Open create space form
   */
  public void goToCreateSpace() {
    info("Open create space form");
    evt.click(ELEMENT_ADD_NEW_SPACE_BUTTON);

    evt.waitForAndGetElement(ELEMENT_ADD_SPACE_FORM);
  }

  /**
   * delete Space
   *
   * @param spaceName name of space
   * @param isVerify true: verify content of confirm msg false: not verify content
   *          of confirm msg
   */
  public void deleteSpace(String spaceName, Boolean isVerify) {
    if ($(byText(spaceName)).is(Condition.exist)) {
      info("Do delete space");
      ELEMENT_SPACES_LIST.find(byText(spaceName)).parent().parent().parent().find(byText("Delete")).click();
      if (isVerify)
        alert.verifyAlertMessage(ELEMENT_SPACE_CONFIRM_DELETE);
      $(ELEMENT_SPACE_DELETE_SPACE_OK_BUTTON).click();
      $(byText(spaceName)).waitUntil(Condition.disappears, Configuration.timeout);
    }

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

    ELEMENT_ADDNEWSPACE_BUTTON.click();
    ELEMENT_SPACE_NAME_INPUT.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_SPACE_NAME_INPUT.setValue(name);
    ELEMENT_SPACE_DESCRIPTION_INPUT.setValue(desc);
    info("Save all changes");
    ELEMENET_SPACE_CREATE_BUTTON.click();
    ELEMENET_SPACE_CREATE_BUTTON.waitUntil(Condition.not(Condition.visible), Configuration.timeout);
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
  public void addNewSpace(String name, String desc, String access, String groups, int... params) {
    int iTimeout = params.length > 0 ? params[0] : testBase.getDefaultTimeout();
    if (evt.waitForAndGetElement(ELEMENT_ADDNEWSPACE_BUTTON, 3000, 0, 2) != null) {
      evt.click(ELEMENT_ADDNEWSPACE_BUTTON);
    } else {
      evt.click(By.xpath("//*[contains(@class, 'uiIconSocSimplePlus')]"));
    }
    evt.waitForAndGetElement(ELEMENT_ADDNEWSPACE_FORM, 3000, 0);
    evt.type(ELEMENT_SPACE_NAME_INPUT, name, true);
    evt.type(ELEMENT_SPACE_DESCRIPTION_INPUT, desc, true);

    if (!access.isEmpty()) {
      goToAccessTabFromPopUp();
      String[] arrayRight = access.split("/");
      if (arrayRight.length > 0) {
        for (String right : arrayRight) {
          info("Select a permission for space:" + right);
          evt.check(ELEMENT_ACCESS_PERMISSION_RADIO.replace("${right}", right), 2);
        }
      } else {
        info("Select a permission for space:" + access);
        evt.check(ELEMENT_ACCESS_PERMISSION_RADIO.replace("${right}", access), 2);
      }

    }

    if (!groups.isEmpty()) {
      goToInviteUserFromGroupTab();
      info("Select a group in the list");
      String[] arrayGroup = groups.split("/");
      if (arrayGroup.length > 0) {
        for (String group : arrayGroup) {
          if (!group.isEmpty()) {
            evt.click(ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX);

            info("Select a group:" + group);
            evt.click(ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_SELECT_GROUP.replace("${name}", group));
          }
        }
      } else {
        info("Select a group:" + groups);
        evt.click(ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_SELECT_GROUP.replace("${name}", groups));
      }
    }

    info("Save all changes");
    evt.click(ELEMENET_SPACE_CREATE_BUTTON);
    evt.waitForAndGetElement(By.linkText(name), iTimeout);
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
   * Open Access tab from add new space popup
   */
  public void goToAccessTabFromPopUp() {
    info("evt.click on Access tab");
    evt.click(ELEMENT_ACCESS_AND_EDIT_TAB_OF_POPUP);
    evt.waitForAndGetElement(ELEMENT_ACCESS_HIDDEN_RADIO, 2000, 2);
    info("The tab is shown");
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
      $(ELEMENT_SPACE_CHANGE_AVATAR_BTN).click();
      $(byClassName("uploadContainer")).find(byClassName("file")).uploadFromClasspath(filepath);

      info("filepath:" + filepath);
      $(ELEMENT_SPACE_UPLOAD_CONFIRM_BTN).click();
      $(ELEMENT_SPACE_UPLOAD_SAVE_BTN).click();

    }

  }

  /**
   * Save change all when edit a space
   */
  public void saveChangesSpace() {
    info("evt.click on Save button");
    $(ELEMENT_SPACE_SAVE_BTN).click();
    info("Save all changes");

  }

  /**
   * Search a space by name or description
   *
   * @param name
   * @param number
   */
  public void searchSpace(String name, String number) {
    info("Waiting my space is shown");
    ELEMENT_MY_SPACE_SEARCH_TEXT.waitUntil(Condition.appears, Configuration.timeout);
    info("Input the space into search text box");
    ELEMENT_MY_SPACE_SEARCH_TEXT.setValue(name);
    info("evt.click on Search button");
    $(ELEMENT_MY_SPACE_SEARCH_BTN).click();

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
    evt.waitForAndGetElement(ELEMENT_MY_SPACE_ALL_SPACES_TAB, 3000, 0);
    evt.click(ELEMENT_MY_SPACE_ALL_SPACES_TAB);

  }

  /**
   * Send a request to a space
   *
   * @param space
   */
  public void sendARequestToASpace(String space, boolean... isVerify) {
    info("Send a request to a space");
    $(byText(space)).parent()
                    .parent()
                    .parent()
                    .find(ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN)
                    .waitUntil(Condition.appears, Configuration.timeout)
                    .click();
    if (isVerify.length > 0) {
      info("Verify that request to join button is hidden and request pending status is shown");
      evt.waitForAndGetElement(ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_PENDING.replace("${space}", space), 3000, 1);
    }
  }

  /**
   * Join to a space
   *
   * @param space
   */
  public void joinToASpace(String space) {
    info("Join to a space");
    evt.waitForAndGetElement(ELEMENT_MY_SPACE_ALL_SPACES_JOIN_BTN.replace("${space}", space), 2000, 0).click();
    info("Verify that join button is hidden and leave button is shown");
    evt.waitForAndGetElement(ELEMENT_SPACE_LEAVE_BTN.replace("${space}", space), 3000, 1);
  }

  /**
   * Open a space in list space
   *
   * @param space
   */
  public void goToSpace(String space) {
    info("evt.click on the title of the space");
    evt.click(ELEMENT_ALL_SPACE_SPACE_NAME.replace("$space", space.toLowerCase()));
    evt.waitForElementNotPresent(ELEMENT_ALL_SPACE_SPACE_NAME.replace("$space", space));
  }

  /**
   * Verify the message when a user accesses to a space if the user is not member
   * of that space
   *
   * @param space
   */
  public void verifyMessageAccessToSpace(String space) {
    info("Verify that");
    evt.waitForAndGetElement(ELEMENT_SPACE_ACCESS_SPACE_REQUEST_JOIN_MESSAGE.replace("$space", space));
  }

  /**
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
    info("evt.click on Accept button of the space");
    $(byText(space)).parent().parent().parent().find(ELEMENT_BUTTON_ACCEPT_SPACE_INVITATION).click();
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
   * Request to join a Space
   *
   * @param space
   */
  public void requestToJoinSpace(String space, boolean... isVerify) {
    info("Request to join a space");
    searchSpace(space, "");
    evt.click(ELEMENT_REQUEST_TO_JOIN_SPACE_BTN.replace("${space}", space));
    if (isVerify.length > 0) {
      evt.waitForAndGetElement(ELEMENT_REQUEST_PENDING.replace("${space}", space), 2000, 1);
    }
    info("Request successfully");
  }

  /**
   * Join to space
   *
   * @param space
   * @param isVerify
   */
  public void joinToSpace(String space, boolean... isVerify) {
    info("Request to join a space");
    searchSpace(space, "");
    evt.click(ELEMENT_MY_SPACE_ALL_SPACES_JOIN_BTN.replace("${space}", space));
    if (isVerify.length > 0) {
      evt.waitForAndGetElement(ELEMENT_SPACE_LEAVE_BTN.replace("${space}", space), 2000, 1);
    }
    info("Request successfully");
  }

  /**
   * Cancel a pending request
   *
   * @param space
   */
  public void cancelPendingRequest(String space) {
    info("evt.click on Cancel button");
    evt.click(ELEMENT_SPACE_CANCEL_BUTTON.replace("${space}", space));
    evt.waitForElementNotPresent(ELEMENT_SPACE_TITLE.replace("${space}", space), 2000, 1);
    info("Cancelling pending request is success");
  }

  /**
   * Open Activity Stream portlet
   */
  public void goToActivityStreamTab() {
    info("Open Activity STream Tab");
    $(ELEMENT_ACTIVITY_STREAM_TAB).click();

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
    $(ELEMENT_WIKI_TAB).click();
    $(ELEMENT_WIKI_HOME_TITLE).waitUntil(Condition.visible, Configuration.timeout);
    info("Wiki portlet is shown");
  }

  public void goToTaskTab() {
    ELEMENT_SPACE_MENU_TAB.find(ELEMENT_TASK_TAB).click();
    ELEMENT_PROJECT_ICON_ADD_PROJECT.waitUntil(Condition.visible, Configuration.timeout);
  }

  /**
   * Open Document tab
   */
  public void goToDocumentTab() {
    info("Open Document Tab");
    $(ELEMENT_DOCUMENT_TAB).click();
    $(ELEMENT_DOCUMENT_FOLDER_ADD_BTN).waitUntil(Condition.visible, Configuration.timeout);
    info("Document portlet is shown");
  }

  /**
   * Open Agenda tab
   */
  public void goToAgendaTab() {
    info("Open Agenda Tab");
    refresh();
    $(ELEMENT_AGENDA_TAB).click();
    $(ELEMENT_AGENDA_EVENT_ADD_BTN).waitUntil(Condition.visible, Configuration.timeout);

    info("Agenda portlet is shown");
  }

  /**
   * Open Member tab
   */
  public void goToMemberTab() {
    info("Open Member Tab");
    evt.click(ELEMENT_MEMBER_TAB);
    evt.waitForAndGetElement(ELEMENT_MEMBER_USER_INFOR, 2000, 0);
    info("Member portlet is shown");
  }

  /**
   * Open user profile portlet of a user
   *
   * @param fullName
   */
  public void goToUserProfilePage(String fullName) {
    info("Open User profile page");
    evt.click(ELEMENT_MEMBER_USER_NAME.replace("${fullName}", fullName));

  }

  /**
   * Verify that a user is a member of the space or not
   *
   * @param fullName is full name of the user
   * @param isDisplay =true if user is a member of the space =false if user is not
   *          a memebr of the space
   */
  public void verifyMember(String fullName, Boolean isDisplay) {
    goToMemberTab();
    if (isDisplay) {
      info("Verify that member is shown in list");
      evt.waitForAndGetElement(ELEMENT_MEMBER_USER_NAME.replace("${fullName}", fullName));
    } else {
      info("Verify that member isnot shown in list");
      evt.waitForElementNotPresent(ELEMENT_MEMBER_USER_NAME.replace("${fullName}", fullName));
    }

  }

  /**
   * Verify that a user is a manager of the space or not
   *
   * @param fullName is full name of the user
   * @param isDisplay =true if user is a manager of the space =false if user is
   *          not a manager of the space
   */
  public void verifyManager(String fullName, Boolean isDisplay) {
    goToMemberTab();
    if (isDisplay) {
      info("Verify that manager is shown in list");
      evt.waitForAndGetElement(ELEMENT_MANAGER_USER_NAME.replace("${fullName}", fullName));
    } else {
      info("Verify that manager isnot shown in list");
      evt.waitForElementNotPresent(ELEMENT_MANAGER_USER_NAME.replace("${fullName}", fullName));
    }

  }

  /**
   * Add a new forlder in Document tab
   */
  public void goToAddNewFolder() {
    info("evt.click on New folder on Action Bar");
    evt.click(ELEMENT_ACTIONBAR_ADDFOLDER);
    info("Verify that Add folder popup is shown");
    evt.waitForAndGetElement(ELEMENT_ADDFOLDERBOX);
    info("The folder is shown successfully");
  }

  /**
   * Create a new folder in Document Tab
   *
   * @param title
   * @param foldertype
   */
  public void createFolder(String title, String foldertype) {
    info("evt.type a title:" + title + " for the folder");
    evt.type(ELEMENT_ADDFOLDER_NAME, title, true);
    if (!foldertype.isEmpty()) {
      info("Select folder evt.type:" + foldertype);
      evt.select(ELEMENT_ADDFOLDER_FOLDERTYPE, foldertype);
    }
    info("evt.click on Create folder button");
    evt.click(ELEMENT_ADDFOLDER_CREATEFOLDERBUTTON);
    info("Verify that the folder is created");
    evt.waitForAndGetElement(ELEMENT_DOCUMENT_FOLDER_NAME.replace("$name", title));
    info("The folder is created successfully");
  }

  /**
   * Open a folder in Document tab
   *
   * @param name
   */
  public void openFolder(String name) {
    info("evt.click on the folder's name");
    Actions action = new Actions(this.testBase.getExoWebDriver().getWebDriver());
    action.moveToElement(evt.waitForAndGetElement(ELEMENT_DOCUMENT_FOLDER_NAME.replace("$name", name))).doubleClick().perform();
    info("Verify that folder is opened");
    evt.waitForAndGetElement(ELMENT_DOCUMENT_FOLDER_ADDRESS.replace("$name", name.toLowerCase()));
    info("the folder is opened");
  }

}
