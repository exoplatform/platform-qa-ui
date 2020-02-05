package org.exoplatform.platform.qa.ui.selenium.platform;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformPermissionLocator.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class PlatformPermission {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public PlatformPermission(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Search user by key search
   *
   * @param keySearch
   * @param type type of search 1: search by user name 2: search by first name 3:
   *          search by last name 4: search by email default: search by user name
   */
  public void searchUser(String keySearch, int type) {
    $(ELEMENT_SEARCH_USER_INPUT).setValue(keySearch).pressEnter();
    if( $(ELEMENT_SELECT_SEARCH).is(Condition.visible)) {
      switch (type) {
        case 1: // search by user name
          $(ELEMENT_SELECT_SEARCH).selectOption("User Name");
          break;
        case 2: // search by first name
          $(ELEMENT_SELECT_SEARCH).selectOption("First Name");
          break;
        case 3: // search by last name
          $(ELEMENT_SELECT_SEARCH).selectOption("Last Name");
          break;
        case 4: // search by email
          $(ELEMENT_SELECT_SEARCH).selectOption("Email");
          break;
        default: // search by user name
          $(ELEMENT_SELECT_SEARCH).selectOption("User Name");
          break;
      }
    }
    $(ELEMENT_QUICK_SEARCH_BUTTON).click();
   $(byXpath(ELEMENT_USER_CHECKBOX.replace("${user}", keySearch))).parent().waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Select user permission
   *
   * @param user list of user: john/mary
   * @param type type of search 1: search by user name 2: search by first name 3:
   *          search by last name 4: search by email default: search by user name
   */
  public void selectUserPermission(String user, int type) {
    String[] temp = user.split("/");
    if (temp.length > 0) {
      for (int i = 0; i < temp.length; i++) {
        searchUser(temp[i], type);
        $(byXpath(ELEMENT_USER_CHECKBOX.replace("${user}", temp[i]))).parent().click();
        $(ELEMENT_ADD_USERS_BUTTON).click();
      }
    }
  }

  /**
   * Check display of user selector
   *
   * @param user
   * @param isPresent
   */
  public void checkUserSelector(String user, boolean isPresent) {
    if (isPresent)
      evt.waitForAndGetElement(ELEMENT_USER_LIST.replace("${user}", user));
    else
      evt.waitForElementNotPresent(ELEMENT_USER_LIST.replace("${user}", user));
  }

  /**
   * Select group permission
   *
   * @param grouppath path group: (Ex: Organization/Employees)
   */
  public void selectGroupPermission(String grouppath) {
    String[] temp;
    if($(ELEMENT_SELECT_GROUP_ICON).is(Condition.visible)) {
      $(ELEMENT_SELECT_GROUP_ICON).click();
    }
    $(ELEMENT_SELECT_GROUP_POPUP).waitUntil(Condition.visible, Configuration.timeout);
    temp = grouppath.split("/");
    for (int i = 0; i < temp.length; i++) {
      $(byXpath(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]))).click();
    }
    if ($(ELEMENT_SELECT_THIS_GROUP).is(Condition.visible))
      $(ELEMENT_SELECT_THIS_GROUP).click();
    $(ELEMENT_SELECT_GROUP_POPUP).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Select group membership
   *
   * @param groupPath path group: (Ex: Organization/Employees)
   * @param membership membership: (Ex: author)
   */
  public void selectGroupMembership(String groupPath, String membership) {
    String[] temp;
    $(ELEMENT_SELECT_MEMBERSHIP_ICON).click();
    $(ELEMENT_SELECT_MEMBERSHIP_POPUP).waitUntil(Condition.visible,Configuration.timeout);
    temp = groupPath.split("/");
    for (int i = 0; i < temp.length; i++) {
      $(byXpath(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]))).click();
    }
    $(byXpath(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership))).click();
    $(ELEMENT_SELECT_MEMBERSHIP_POPUP).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Check search result
   *
   * @param keySearch
   * @param isPresent true if it has result false if it doesn't have result
   */
  public void searchUser(String keySearch, boolean isPresent) {
    evt.type(ELEMENT_SEARCH_USER_INPUT, keySearch, true);

    evt.click(ELEMENT_QUICK_SEARCH_BUTTON);
    if (isPresent)
      evt.waitForAndGetElement((ELEMENT_USER_CHECKBOX.replace("${user}", keySearch)), 5000, 1, 2);
    else
      evt.waitForElementNotPresent((ELEMENT_USER_CHECKBOX.replace("${user}", keySearch)), 5000, 1, 2);
  }
}