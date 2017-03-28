package org.exoplatform.platform.qa.ui.forum.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ForumPermission {

  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param dr
   */
  public ForumPermission(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
  }

  /**
   * select permission in category
   *
   * @param isMod
   * @param isStartTop
   * @param isPostReply
   * @param isViewPost
   */
  public void selectPermInCategory(boolean isMod, boolean isStartTop, boolean isPostReply, boolean isViewPost) {
    info("select permission");
    if (isMod) {
      evt.check(ELEMENT_PERM_MOD_CHECKBOX, 2);
    } else {
      evt.uncheck(ELEMENT_PERM_MOD_CHECKBOX, 2);
    }
    if (isStartTop) {
      evt.check(ELEMENT_PERM_STARTTOP_CHECKBOX, 2);
    } else {
      evt.uncheck(ELEMENT_PERM_STARTTOP_CHECKBOX, 2);
    }
    if (isPostReply) {
      evt.check(ELEMENT_PERM_POSTREPLY_CHECKBOX, 2);
    } else {
      evt.uncheck(ELEMENT_PERM_POSTREPLY_CHECKBOX, 2);
    }
    if (isViewPost) {
      evt.check(ELEMENT_PERM_VIEWPOST_CHECKBOX, 2);
    } else {
      evt.uncheck(ELEMENT_PERM_VIEWPOST_CHECKBOX, 2);
    }
  }

  /**
   * Select permission group membership
   *
   * @param groupPath
   * @param member
   * @param isMod
   * @param isStartTop
   * @param isPostReply
   * @param isViewPost
   */
  public void selectPermGroupMember(String groupPath,
                                    String member,
                                    boolean isMod,
                                    boolean isStartTop,
                                    boolean isPostReply,
                                    boolean isViewPost) {
    String[] temp;
    info("select group membership");
    evt.click(ELEMENT_PERM_TAB, 0, true);
    evt.click(ELEMENT_PERM_ROLE_ICON, 0, true);
    evt.waitForAndGetElement(ELEMENT_SELECT_MEMBERSHIP_POPUP);
    temp = groupPath.split("/");
    for (int i = 0; i < temp.length; i++) {
      evt.click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]), 0, true);
    }
    evt.click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", member), 0, true);
    evt.waitForElementNotPresent(ELEMENT_SELECT_MEMBERSHIP_POPUP);
    evt.click(ELEMENT_PERM_ADD_BTN, 0, true);
    selectPermInCategory(isMod, isStartTop, isPostReply, isViewPost);
  }

  /**
   * Select permission group membership
   *
   * @param groupPath
   * @param member
   */
  public void selectPermGroupMemberRestricted(String groupPath, String member) {
    String[] temp;
    info("select group membership");
    evt.click(ELEMENT_RESTRICTED_ROLE_ICON, 0, true);
    evt.waitForAndGetElement(ELEMENT_RESTRICTED_SELECT_MEMBERSHIP_POPUP);
    temp = groupPath.split("/");
    for (int i = 0; i < temp.length; i++) {
      evt.click(ELEMENT_RESTRICTED_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]), 0, true);
    }
    evt.click(ELEMENT_RESTRICTED_SELECT_RIGHT_PARENT_GROUP.replace("$group", member), 0, true);
    evt.waitForElementNotPresent(ELEMENT_RESTRICTED_SELECT_MEMBERSHIP_POPUP);
  }

  /**
   * select permission in topic
   *
   * @param isView
   * @param isPost
   */
  public void selectPermInTopic(boolean isPost, boolean isView) {
    info("select permission");
    if (isPost) {
      evt.check(ELEMENT_PERM_MOD_CHECKBOX, 2);
    } else {
      evt.uncheck(ELEMENT_PERM_MOD_CHECKBOX, 2);
    }
    if (isView) {
      evt.check(ELEMENT_PERM_STARTTOP_CHECKBOX, 2);
    } else {
      evt.uncheck(ELEMENT_PERM_STARTTOP_CHECKBOX, 2);
    }
  }

  /**
   * Select permission group membership in topic
   *
   * @param groupPath
   * @param member
   * @param isView
   * @param isPost
   */
  public void selectPermGroupMemberInTopic(String groupPath, String member, boolean isView, boolean isPost) {
    String[] temp;
    info("select group membership");
    evt.click(ELEMENT_PERM_TAB, 0, true);
    evt.click(ELEMENT_PERM_ROLE_ICON, 0, true);
    evt.waitForAndGetElement(ELEMENT_SELECT_MEMBERSHIP_POPUP);
    temp = groupPath.split("/");
    for (int i = 0; i < temp.length; i++) {
      evt.click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]), 0, true);
    }
    evt.click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", member), 0, true);
    evt.waitForElementNotPresent(ELEMENT_SELECT_MEMBERSHIP_POPUP);
    evt.click(ELEMENT_PERM_ADD_BTN, 0, true);
    selectPermInTopic(isView, isPost);
  }

  /**
   * Select permission group membership
   *
   * @param groupPath
   * @param member
   */
  public void selectPermGroupMemberMes(String groupPath, String member) {
    String[] temp;
    info("select group membership");
    evt.click(ELEMENT_MESSAGE_ROLE_ICON, 0, true);
    evt.waitForAndGetElement(ELEMENT_MESSAGE_SELECT_MEMBERSHIP_POPUP);
    temp = groupPath.split("/");
    for (int i = 0; i < temp.length; i++) {
      evt.click(ELEMENT_MESSAGE_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]), 0, true);
    }
    evt.click(ELEMENT_MESSAGE_SELECT_RIGHT_PARENT_GROUP.replace("$group", member), 0, true);
    evt.waitForElementNotPresent(ELEMENT_MESSAGE_SELECT_MEMBERSHIP_POPUP);
  }
}
