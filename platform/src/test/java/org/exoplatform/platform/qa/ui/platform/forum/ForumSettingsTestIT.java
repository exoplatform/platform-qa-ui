package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_EDIT_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_EDIT_PAGE_EDITLAYOUT;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_EDIT_PAGE;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

@Tag("forum")
@Tag("sniff")
public class ForumSettingsTestIT extends Base {

  HomePagePlatform        homePagePlatform;

  ForumHomePage           forumHomePage;

  ManageLogInOut          manageLogInOut;

  ForumCategoryManagement forumCategoryManagement;

  ForumForumManagement    forumForumManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    forumHomePage = new ForumHomePage(this);
    manageLogInOut = new ManageLogInOut(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    forumForumManagement = new ForumForumManagement(this);

    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
      manageLogInOut.signInCas(username, password);
  }


  /*
   * Step Number: 1 Step Name: User Setting Step Description: - Click on
   * SettingsChange profile information - Click on Profile tab - Chang profile
   * informationChange forum settings - Click on Forum settings tab - Chang forum
   * informationManage my subscription - Click on My subscriptions tab - Chang
   * subscriptions information Input Data: Expected Outcome: - User profile is
   * updated - Forum is setting successful - Allow get Feed URL of
   * category/forum/topic and update email of user watched
   */
  @Test
  public void test01_UserSettings() {
    info("Test 1: User Settings");

    homePagePlatform.goToForum();

    $(ELEMENT_ACTIONBAR_SETTINGS).click();
    $(ELEMENT_FORUM_SETTINGS_SCREENNAME).val("testscreen");

    $(ELEMENT_FORUM_SETTINGS_FORUMSETTINGS).click();
    select(ELEMENT_FORUM_SETTINGS_MAXTHREADS, "5", 2);

    $(ELEMENT_FORUM_SETTINGS_MYSUSCRIB).click();
    $(ELEMENT_FORUM_SETTINGS_EMAILADRESS).val("test@test.com");

    $(ELEMENT_FORUM_SETTINGS_UPDATE).click();
    $(ELEMENT_FORUM_SETTINGS_SAVE).click();

  }

  /**
   * <li>Case ID:116698.</li>
   * <li>Test Case Name: User management.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1: Manage users Step
   * Description: - Click on Uses - Click Edit icon corresponding with 1
   * userChange user profile - Click on Profile tab - Change informationChange
   * user setting - Click on Setting tab - Change informationBan User - Click on
   * Ban Uses tab - Change informationView all topics of User - Click on Topic
   * tabView add posts by User - Click on Posts tab Input Data: Expected Outcome:
   * User information is updated successfully
   */
  @Test
  public void test02_UserManagement() {
    info("Test 2: User management");

    homePagePlatform.goToForum();
    $(ELEMENT_ACTIONBAR_USER).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_FORUM_USERS_POPUP_SEARCH_FIELD).waitUntil(Condition.visible,2000).setValue(DATA_USER2).waitUntil(Condition.visible,2000).pressEnter();
    ELEMENT_FORUM_EDIT_PROFILE.waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_FORUM_SETTINGS_SCREENNAME);
    $(ELEMENT_FORUM_USERS_FORUMSETTINGS).waitUntil(Condition.visible,2000).click();
    select(ELEMENT_FORUM_SETTINGS_MAXTHREADS, "5", 2);
    $(ELEMENT_FORUM_USERS_BAN).waitUntil(Condition.visible,2000).click();
    $(ELEMENT_FORUM_USERS_TOPICS).waitUntil(Condition.visible,2000).click();
    $(ELEMENT_FORUM_USERS_POSTS).waitUntil(Condition.visible,2000).click();
    $(ELEMENT_FORUM_SETTINGS_SAVE).waitUntil(Condition.visible,2000).click();
    $(ELEMENT_FORUM_CLOSEBTN).waitUntil(Condition.visible,2000).click();

    manageLogInOut.signIn(DATA_USER2, DATA_PASS);
    homePagePlatform.goToForum();
    $(byText(DATA_NAME_USER2)).should(Condition.exist);

  }

  /**
   * <li>Case ID:116691.</li>
   * <li>Test Case Name: Setting Forum portlet.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Setting Forum porlet from
   * edit mode Step Description: - Show/hide category - enable/disable panel -
   * Use/not use ajax Input Data: Expected Outcome: Setting porlet is successful
   * with properties
   */
  @Test
  public void test03_SettingForumPortlet() {
    info("Test 3: Setting Forum portlet");

    String name = "name" + getRandomNumber();

    String name2 = "name2" + getRandomNumber();

    homePagePlatform.goToForum();
    forumCategoryManagement.addCategorySimple(name, "", "");
    forumForumManagement.addForumSimple(name2, "", "");
    $(ELEMENT_EDIT_BUTTON).click();
    $(ELEMENT_EDIT_PAGE).hover();
    $(ELEMENT_EDIT_PAGE_EDITLAYOUT).click();
    $(ELEMENT_PAGEEDITOR_FORUM).hover();
    $(ELEMENT_PAGEEDITOR_EDITELEMENT).click();
    $((byText(name))).parent().parent().find(byClassName("uiCheckbox")).click();
    $(ELEMENT_PAGEEDITOR_SAVEBTN).click();
    $(ELEMENT_PAGEEDITOR_OKBTN).click();
    $(ELEMENT_PAGEEDITOR_CLOSEBTN).click();
    $(ELEMENT_PAGEEDITOR_FINISHLIGHTBTN).click();
    $(byText(name)).shouldNot(Condition.exist);
    ELEMENT_CAT_CONTAINER.find(byText(name)).click();
    forumCategoryManagement.deleteCategory(name);
  }

}
