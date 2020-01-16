/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - Selenium (Legacy Code).
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - Selenium (Legacy Code); if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.selenium.platform;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator;
import org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_MANAGESITES_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_PAGE_CREATION_WIZARD;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

public class NavigationToolbar {

  private final TestBase testBase;

  private ElementEventTestBase evt;

  public NavigationToolbar(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Go to Edit Layout
   */
  public void goToEditLayout() {
    info("--Go to Edit Layout--");

    $(ELEMENT_LINK_EDIT).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_MENU_PAGE_LINK).waitUntil(Condition.visible,Configuration.timeout).hover();
    $(ELEMENT_MENU_EDIT_LAYOUT).waitUntil(Condition.visible,Configuration.timeout).click();
  }

  /**
   * Go to add page form: Edit- Page- Add page
   */
  public void goToAddPage() {
    info("Go to add page form");
    $(ELEMENT_LINK_EDIT).click();
    $(ELEMENT_MENU_PAGE_LINK).hover();
    $(ELEMENT_MENU_ADD_PAGE_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_PAGE_CREATION_WIZARD).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
  }

  /**
   * Go to Manage Sites page: administration- Portal-Sites By QuynhPT
   */
  public void goToPotalSites() {
    info("--Go to Portal-->Sites--");

    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, Configuration.timeout);

    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();

    $(ELEMENT_ADMINISTRATION_PORTAL).waitUntil(Condition.appears, Configuration.timeout);

    $(ELEMENT_ADMINISTRATION_PORTAL).hover();

    $(ELEMENT_ADMINISTRATION_PORTAL_SITES).waitUntil(Condition.appears, Configuration.timeout);

    $(ELEMENT_ADMINISTRATION_PORTAL_SITES).click();

    $(ELEMENT_MANAGESITES_TITLE).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Go to Manage Group Sites page: administration-Portal-Group Sites
   */
  public void goToGroupSites() {
    info("--Go to Portal-->Sites--");
    evt.waitElementAndTryGetElement(ELEMENT_TOOLBAR_ADMINISTRATION);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    $(ELEMENT_ADMINISTRATION_PORTAL).hover();
    $(ELEMENT_ADMINISTRATION_PORTAL_GROUP_SITES).waitUntil(Condition.appears, 10000);
    $(ELEMENT_ADMINISTRATION_PORTAL_GROUP_SITES).click();
    $(byText("Add Navigation")).should(Condition.exist);
  }

  /**
   * Go to Manage Sites page: administration-Portal-Pages
   */
  public void goToPotalPages() {
    info("-- Go to Page Management page --");
    if ($(byText("No result found.")).is(Condition.exist)) {
      $(byText("OK")).click();
    }
    $(ELEMENT_LINK_SETUP).click();
    $(ELEMENT_ADMINISTRATION_PORTAL).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_ADMINISTRATION_PORTAL).click();
    info("Page Managements is shown successfully");
  }

  /**
   * function: Go to Users and Group management (administration - Users -
   * Groups and Roles)
   */
  public void goToUsersAndGroupsManagement() {
    info("--Go to Users and groups management--");
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_ADMINISTRATION_COMMUNITY.hover();
    ELEMENT_ADMINISTRATION_MANAGE_COMMUNITY.waitUntil(Condition.visible,Configuration.timeout).click();
  }

  /**
   * function: Go to Users and Group management (administration - Community -
   * Manage Community)
   */
  public void goToCommunityManagement() {
    info("--Go to Users and groups management--");
    evt.click(ELEMENT_TOOLBAR_ADMINISTRATION);
    evt.mouseOver(ELEMENT_ADMINISTRATION_USERS, true);
    if (evt.waitForAndGetElement(ELEMENT_GROUP_AND_ROLE_LINK, 2000, 0) != null)
      evt.click(ELEMENT_GROUP_AND_ROLE_LINK);
    else {
      testBase.getExoWebDriver()
              .getWebDriver()
              .get(testBase.getExoWebDriver().getBaseUrl() + "/g/:platform:administrators/administration/management");
    }
  }

  /**
   * Select a link in User menu
   *
   * @param link
   */
  public void selectALinkOfUserMenu(specifUserToolBar link) {
    openUserMenu();
    switch (link) {
      case MY_PROFILE:
        evt.click(ELEMENT_MY_PROFILE_LINK);

        evt.waitForAndGetElement(SocialLocator.ELEMENT_MY_PROFILE_TAB, 3000, 1);
        break;
      case MY_ACTIVITY:
        info("Go to Activities of User");
        $(ELEMENT_ACTIVITIES_LINK).waitUntil(Condition.appears, Configuration.timeout);
        $(ELEMENT_ACTIVITIES_LINK).click();
        $(ELEMENT_ACTIVITIES_PORTLET).waitUntil(Condition.appears, Configuration.timeout);
        break;
      case MY_CONNECTIONS:
        evt.click(ELEMENT_MY_CONNECTION_LINK);
        break;
      case MY_WIKI:
        $(ELEMENT_MY_WIKI_LINK).click();
        break;
      case MY_DASHBOARD:
        evt.click(ELEMENT_MY_DASHBOARD_LINK);
        break;
      case MY_NOTIFICATION:
        $(ELEMENT_MY_NOTIFICATIONS_LINK).click();
        break;
      case SETTINGS:
        $(ELEMENT_MY_SETTINGS_LINK).click();
        break;
      case CHANGE_LANGUAGE:
        break;
    }
  }

  /**
   * Open User menu by evt.click on username
   */
  public void openUserMenu() {
    info("--Open User Menu--");
    evt.waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK);
    evt.click(ELEMENT_ACCOUNT_NAME_LINK);
  }

  /**
   * Go to Adinistration-Content-Site Explorer
   */
  public void goToSiteExplorer() {
    info("-- Go to site explorer home page --");
    if (testBase.getExoWebDriver().isIEDriver()) {
      $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, Configuration.timeout);
      $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    } else
      $(ELEMENT_LINK_SETUP).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Element " + ELEMENT_MENU_CONTENT_LINK + "... is displayed");
    sleep(Configuration.timeout);
    do {
      $(ELEMENT_MENU_CONTENT_LINK).hover();
    } while (!$(ELEMENT_MENU_SITE_EXPLORER).exists());
    $(ELEMENT_MENU_SITE_EXPLORER).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Site Explorer is shown successfully");
  }

  /**
   * Go to Edit-Page-SEO
   */
  public void goToSEO() {
    info("Go to SEO page");
    info("Click on Edit button");
    evt.click(ELEMENT_LINK_EDIT);
    info("Hover over on Page link");
    evt.mouseOver(ELEMENT_MENU_PAGE_LINK, true);
    info("Click on Seo Menu");
    $(ELEMENT_MENU_SEO_LINK).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
  }

  /**
   * Go to Edit Content
   */
  public void goToEditContent() {
    info("Go to Edit content");
    $(ELEMENT_LINK_EDIT).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_LINK_EDIT).click();
    $(ELEMENT_EDIT_CONTENT).click();
  }

  /**
   * Go to Un-edit content Edit-Uncheck Content
   */
  public void goToUnEditContent() {
    info("Go to un Edit content");
    $(ELEMENT_LINK_EDIT).waitUntil(Condition.visible, Configuration.timeout).click();
    if ($(ELEMENT_EDIT_CONTENT_CHECK).is(Condition.visible))
      $(ELEMENT_EDIT_CONTENT_CHECK).click();
  }

  /**
   * Open Change language popup Username-Change Language
   */
  public void goToChangeLanguage() {
    info("Open Change Language popup");
    $(ELEMENT_TOPBAR_AVATAR).waitUntil(Condition.visible, Configuration.collectionsTimeout).click();
    $(ELEMENT_AVATAR_CHANGELANGUAGE).waitUntil(Condition.visible, Configuration.timeout).click();
  }

  /**
   * Go to content administration
   */
  public void goToContentAdministration() {
    info("Go to content administration");
    info("Base url is " + testBase.getExoWebDriver().getBaseUrl());
    String url = testBase.getExoWebDriver().getBaseUrl() + "/g/:platform:web-contributors/wcmAdmin";
    info("base url of content admin is " + url);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[3]/a")).hover();
    ELEMENT_LINK_CONTENT_ADMIN.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_LINK_CONTENT_ADMIN.click();
  }

  /**
   * Go to Space Administration
   */
  public void goToSpaceAdminstration() {
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    sleep(2000);
    ELEMENT_ADMINISTRATION_SPACES.hover();
    ELEMENT_SPACE_ADMIN_LINK.click();
    sleep(2000);
    ELEMENT_SPACE_ADMIN_PAGE.waitUntil(Condition.visible, Configuration.timeout);
  }

  /**
   * Go to IDE page
   */
  public void goToIDE() {
    info("-- Go to IDE home page --");
    for (int repeat = 0; ; repeat++) {
      if (repeat > 1) {
        evt.mouseOverAndClick(ELEMENT_LINK_SETUP);
        break;
      }
      evt.mouseOver(ELEMENT_LINK_SETUP, true);
      if (evt.waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_IDE, 5000, 0) != null) {
        info("Element " + ELEMENT_ADMINISTRATION_PORTAL_IDE + "... is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
    }
    evt.mouseOverAndClick(ELEMENT_ADMINISTRATION_PORTAL_IDE);
  }

  /**
   * Go to Application home page
   */
  public void goToApplication() {
    info("-- Go to Application home page --");

    for (int repeat = 0; ; repeat++) {
      if (repeat > 1) {
        evt.mouseOverAndClick(ELEMENT_LINK_SETUP);
        break;
      }
      evt.clickByJavascript(ELEMENT_LINK_SETUP, 2);
      if (evt.waitForAndGetElement(ELEMENT_ADMINISTRATION_APPLICATION, 5000, 0) != null) {
        info("Element " + ELEMENT_ADMINISTRATION_APPLICATION + "... is displayed");
        break;
      }
      info("Retry...[" + repeat + "]");
    }
    evt.mouseOverAndClick(ELEMENT_ADMINISTRATION_APPLICATION);
  }

  /**
   * Go to Banding page
   */
  public void goToBanding() {
    info("-- Go to Banding page --");
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    $(ELEMENT_ADMINISTRATION_PORTAL).hover();
    $(ELEMENT_ADMINISTRATION_PORTAL_BRANDING).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_ADMINISTRATION_PORTAL_BRANDING).click();
  }

  /**
   * Go to create wiki page from the toolbars
   *
   * @param location
   */
  public void goToCreateWikiPage(String location) {
    info("Go to create wiki page");
    $(ELEMENT_ADD_TOOTLBAR).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_ADD_WIKI_TOOLBAR).waitUntil(Condition.visible,Configuration.timeout).click();
    if (!location.isEmpty()) {
      $(ELEMENT_ADD_WIKI_SET_LOCATION).waitUntil(Condition.visible,Configuration.timeout).click();
      $(ELEMENT_ADD_WIKI_CHOOSE_LOCATION.replace("{$location}", location)).waitUntil(Condition.visible,Configuration.timeout).click();
    }
    $(ELEMENT_NEXT_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
  }

  /**
   * Go to add a pool from the toolbar
   *
   * @param location
   * @param forum
   */
  public void goToAddPoll(String location, String forum) {
    info("Go to add poll from tootlbar");
    $(ELEMENT_ADD_TOOTLBAR).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    info("Click on Poll link");
    $(ELEMENT_ADD_POOL_TOOLBAR).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs).click();
    if (location != "" && location != null) {
      info("Set location for the poll");
      $(ELEMENT_ADD_POLL_SET_LOCATION).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    }
    info("evt.click on Next button");
    $(ELEMENT_NEXT_BUTTON).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Select a forum for poll");
    $(ELEMENT_SELECT_FORUM_COMBOBOX).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    $(byText(forum)).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    info("Click on next button");
    $(ELEMENT_NEXT_BUTTON).waitUntil(Condition.enabled, Configuration.openBrowserTimeoutMs);
    $(ELEMENT_NEXT_BUTTON).click();
  }

  /**
   * Add a topic from the toolbar
   *
   * @param location
   * @param forum
   */
  public void goToAddTopic(String location, String forum) {
    info("Go to add a topic from toolbar");
    $(ELEMENT_ADD_TOOTLBAR).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_ADD_TOPIC_TOOLBAR).waitUntil(Condition.visible,Configuration.timeout).click();
    if (location != "") {
      $(ELEMENT_ADD_POLL_SET_LOCATION).waitUntil(Condition.visible,Configuration.timeout).click();
    }
    info("evt.click on Next button");
    $(ELEMENT_NEXT_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Select a forum for topic");
    if (ELEMENT_SELECT_FORUM_COMBOBOX.is(Condition.exist)) {
      ELEMENT_SELECT_FORUM_COMBOBOX.waitUntil(Condition.appears, Configuration.timeout).click();
      $(byText(forum)).waitUntil(Condition.appears, Configuration.timeout).click();
      info("Click on next button");
      $(ELEMENT_NEXT_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
    }
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
  }

  /**
   * Add an event or a task from the toolbar
   *
   * @param name
   * @param from
   * @param to
   * @param calendar
   */
  public void goToAddEvent(String name, String from, String to, String calendar) {
    $(ELEMENT_ADD_TOOTLBAR).click();
    $(ELEMENT_ADD_EVENT_CLASS_TOOLBAR).click();

    $(ELEMENT_ADD_TITLE).setValue(name);
    $(ELEMENT_SAVE_BUTTON).click();
  }

  public void gotoAddTask(String name) {
    $(ELEMENT_ADD_TOOTLBAR).waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_ADD_TASK_CLASS_TOOLBAR.waitUntil(Condition.visible,Configuration.timeout).click();
    ELEMENT_TASK_ADD_TITLE.waitUntil(Condition.visible,Configuration.timeout).setValue(name);
    ELEMENT_TASK_BUTTON_ADD.waitUntil(Condition.visible,Configuration.timeout).click();
  }

  /**
   * Go to upload file fron the toolbar
   *
   * @param drive
   * @param pathInDrive
   * @param linkFile
   */
  public void goToUploadFile(String drive, String pathInDrive, String linkFile) {
    info("-- Upload file --");
    evt.click(ELEMENT_ADD_TOOTLBAR);
    evt.click(ELEMENT_UPLOAD_FILE_TOOLBAR);
    if (drive != "") {
    }
    if (pathInDrive != "") {
    } else {
      evt.click(NavigationToolBarLocator.ELEMENT_UPLOAD_FILE_TOOLBAR_PERSONNAL_DOCUMENTS);
    }
    WebElement frame = evt.waitForAndGetElement(ELEMENT_UPLOAD_FILE_FRAME_XPATH);
    testBase.getExoWebDriver().getWebDriver().switchTo().frame(frame);
    info(testBase.getAbsoluteFilePath(linkFile));
    ((JavascriptExecutor) testBase.getExoWebDriver()
            .getWebDriver()).executeScript("document.getElementsByTagName('input')[0].style.display = 'block';");
    testBase.getExoWebDriver()
            .getWebDriver()
            .findElement(ELEMENT_ACTIVITY_UPLOAD_POPUP_UPLOAD_BUTTON)
            .sendKeys(testBase.getAbsoluteFilePath(linkFile));
    evt.switchToParentWindow();
    info("Upload finished");
  }

  /**
   * Open search administration
   */
  public void goToAdminSearch() {
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    $(ELEMENT_MENU_CONTENT_LINK).hover();
    $(ELEMENT_SEARCH_LINK).click();
  }

  /**
   * Open a folder in Documents
   *
   * @param name
   */
  public void openFolderInDocuments(String name) {
    info("Click on the folder's name");
    $(byXpath("//span[@class='nodeName' and text()='$name']".replace("$name", name))).waitUntil(Condition.visible, Configuration.timeout).click();
    info("Verify that folder is opened");
    $(byXpath("//a[@data-original-title='Personal Documents']/following::a[contains(text(),'$name')]".replace("$name", name))).waitUntil(Condition.visible, Configuration.timeout).isDisplayed();
    info("The folder is opened");
  }

  /**
   * Open quick search on toolbar
   */
  public void goToQuickSearch() {
    info("Click on Quick search icon");
    $(ELEMENT_TOOLBAR_QUICKSEARCH).click();

  }

  /**
   * Open My profile page
   */
  public void goToMyProfile() {
    selectALinkOfUserMenu(specifUserToolBar.MY_PROFILE);
  }

  /**
   * Open My dashboard
   */
  public void goToMyDashboard() {
    selectALinkOfUserMenu(specifUserToolBar.MY_DASHBOARD);
  }

  /**
   * Go to My activities
   */
  public void goToMyActivities() {
    selectALinkOfUserMenu(specifUserToolBar.MY_ACTIVITY);
  }

  /**
   * Open My dashboard
   */
  public void goToMySettings() {
    selectALinkOfUserMenu(specifUserToolBar.SETTINGS);
  }

  /**
   * Open My Connection
   */
  public void goToMyConnection() {
    selectALinkOfUserMenu(specifUserToolBar.MY_CONNECTIONS);
  }

  /**
   * Go to My wiki page
   */
  public void goToMyWiki() {
    selectALinkOfUserMenu(specifUserToolBar.MY_WIKI);
  }

  /**
   * Go to email notification
   */
  public void goToAdminNotifications() {
    info("Go to email notifications");
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    $(ELEMENT_ADMINISTRATION_PORTAL).hover();
    $(ELEMENT_ADMINISTRATION_PORTAL_EMAIL_NOTIFICATIONS).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_ADMINISTRATION_PORTAL_EMAIL_NOTIFICATIONS).click();
  }

  /**
   * Go to add an user
   */
  public void goToAddUser() {
    info("Go to add user page");
    info("Click on administration icon");
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
    ELEMENT_ADMINISTRATION_COMMUNITY.waitUntil(Condition.appears, Configuration.openBrowserTimeoutMs);
    ELEMENT_ADMINISTRATION_COMMUNITY.hover();
    ELEMENT_ADMINISTRATION_ADD_USERS.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
  }

  public void goToManageCommunity() {
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, 10000);
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(Configuration.timeout);
    ELEMENT_ADMINISTRATION_COMMUNITY.waitUntil(Condition.appears, 10000);
    ELEMENT_ADMINISTRATION_COMMUNITY.hover();
    ELEMENT_ADMINISTRATION_MANAGE_COMMUNITY.waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
  }

  /**
   * Open Notification list
   */
  public void goToNotificationList() {
    info("Click on Notification icon");
    $(ELEMENT_TOOLBAR_NOTIFICATION_LIST).click();
    info("Notification list is shown");
    $(ELEMENT_NOTIFICATION_DROPDOWN).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * Open My Notifications
   */
  public void goToMyNotifications() {
    selectALinkOfUserMenu(specifUserToolBar.MY_NOTIFICATION);
  }

  /**
   * Open Intranet Notification
   */
  public void goToIntranetNotification() {
    info("Go to Intranet Notification");
    $(ELEMENT_INTRANET_NOTIFICATION_BELL).click();
    $(ELEMENT_NOTIFICATION_DROPDOWN).waitUntil(Condition.visible, Configuration.timeout);
    info("The elemnt is shown successfully");
  }

  /**
   * Go to Edit/Site/Layout
   */
  public void goToEditSiteLayout() {
    info("Go to Edit layout form");
    for (int repeat = 0; ; repeat++) {
      if (repeat > 1) {
        evt.mouseOverAndClick(ELEMENT_LINK_EDIT);
        break;
      }
      evt.mouseOver(ELEMENT_LINK_EDIT, true);
      if (evt.waitForAndGetElement(ELEMENT_MENU_EDIT_SITES, 5000, 0) != null) {
        info("-- Click Site menu --");
        evt.mouseOver(ELEMENT_MENU_EDIT_SITES, true);
        if (evt.waitForAndGetElement(ELEMENT_MENU_EDIT_SITE_LAYOUT, 5000, 0) != null) {
          evt.click(ELEMENT_MENU_EDIT_SITE_LAYOUT);
          break;
        }
      } else {
        String editPageRequest = "ajaxGet(eXo.env.server.createPortalURL('UIWorkingWorkspace', 'EditInline', true))";
        info("editPageRequest:" + editPageRequest);
        ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript(editPageRequest);
        break;
      }
      info("Retry...[" + repeat + "]");
    }
  }

  /**
   * go to Edit-Site-Navigation
   */
  public void goToEditSiteNavigation() {
    info("Go to Edit layout form");
    int repeat = 0;
    while (evt.waitForAndGetElement(ELEMENT_NAVIGATION_MANAGE_POPUP, 5000, 0) == null) {
      if (repeat > 5)
        break;
      if (evt.waitForAndGetElement(ELEMENT_MENU_EDIT_SITES_NAV, 5000, 0) != null)
        break;
      info("Click on Edit link");
      evt.click(ELEMENT_LINK_EDIT);
      info("-- Click Site menu --");
      evt.mouseOver(ELEMENT_MENU_EDIT_SITES, true);
      info("Click on Navigation link");
      evt.click(ELEMENT_MENU_EDIT_SITES_NAV);
      repeat++;
    }
  }

  /**
   * go to Edit-Site-Add Site
   */
  public void goToEditSiteAddSite() {
    info("Go to Edit layout form");
    int repeat = 0;
    while (evt.waitForAndGetElement(ELEMENT_ADDSITE_MANAGE_POPUP, 5000, 0) == null) {
      if (repeat > 5)
        break;
      if (evt.waitForAndGetElement(ELEMENT_ADDSITE_MANAGE_POPUP, 5000, 0) != null)
        break;
      info("Click on Edit link");
      evt.click(ELEMENT_LINK_EDIT);
      info("-- Click Site menu --");
      evt.mouseOver(ELEMENT_MENU_EDIT_SITES, true);
      info("Click Add Site link");
      evt.click(ELEMENT_MENU_EDIT_ADDSITE);
      repeat++;
    }
  }

  /**
   * verify edit page
   *
   * @param title
   * @param isEnable
   */
  public void verifyEditPagePerm(String title, boolean isEnable) {
    info("verify edit page permission");
    /*
     * @FIXME
     * portMg.openPage(testBase.getExoWebDriver().getBaseUrl()+"/intranet/home/"+
     * title );
     */
    if (isEnable) {
      evt.click(ELEMENT_LINK_EDIT, 0, true);
      evt.mouseOver(ELEMENT_MENU_PAGE_LINK, true);
      evt.waitForAndGetElement(ELEMENT_MENU_EDIT_LAYOUT);
    } else {
      evt.waitForElementNotPresent(ELEMENT_LINK_EDIT);
      evt.waitForElementNotPresent(ELEMENT_MENU_EDIT_LAYOUT);
    }
  }

  /**
   * verify edit site
   *
   * @param title
   * @param isEnable
   */
  public void verifyEditSitePerm(String title, boolean isEnable) {
    info("verify edit stie permission");
    testBase.getExoWebDriver().getWebDriver().get(testBase.getExoWebDriver().getBaseUrl() + "/" + title);
    if (isEnable) {
      evt.click(ELEMENT_LINK_EDIT, 0, true);
      evt.mouseOver(ELEMENT_MENU_SITE_LINK, true);
      evt.waitForAndGetElement(ELEMENT_MENU_LAYOUT);
    } else {
      evt.waitForElementNotPresent(ELEMENT_MENU_LAYOUT);
    }
  }

  /**
   * verify Column Name
   */
  public void verifyColumnName(String columnName) {
    info("Check column name called" + columnName);
    Assert.assertEquals($(byXpath("//div[contains(text(),'${columnName}')]".replace("${columnName}", columnName))).getText(), columnName);
  }

  /**
   * check uploaded file in folder
   */
  public void checkFolderUploadedFile(String uploadedFile) {
    info("Check uploaded file in folder" + uploadedFile);
    $(byXpath(ELEMENT_DOCUMENT_FOLDER_UPLOADED_FILE.replace("${file}", uploadedFile))).isDisplayed();
    info("CLick on uploaded file" + uploadedFile);
    $(byXpath(ELEMENT_DOCUMENT_FOLDER_UPLOADED_FILE.replace("${file}", uploadedFile))).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byXpath("//i[@id='MultiUploadClose']")).waitUntil(Condition.visible, Configuration.timeout).click();
  }

  /**
   * copy Url To Clipboard
   */
  public void copyUrlToClipboard(String uploadedFile) {
    info("Check uploaded file in folder" + uploadedFile);
    $(byXpath(ELEMENT_DOCUMENT_FOLDER_CHECK.replace("${file}", uploadedFile))).waitUntil(Condition.visible, Configuration.timeout).click();
    refresh();
    info("Copy Url to clipboard");
    $(byXpath("//a[@data-toggle='dropdown' and text()='More  '] ")).waitUntil(Condition.visible, Configuration.timeout).click();
    COPY_URL_TO_CLIPBOARD_BUTTON.click();
    executeJavaScript("window.open();");
    switchTo().window(0);
    $(byXpath("//input[@name='address']")).waitUntil(Condition.visible, Configuration.timeout).clear();
    $(byXpath("//input[@name='address']")).waitUntil(Condition.visible, Configuration.timeout).sendKeys(Keys.CONTROL + "v");
    String copyUrl = $(byXpath("//input[@name='address']")).getValue();
    switchTo().window(1);
    open(copyUrl);
    sleep(3000);
    $(byXpath("//div[@id='content']/embed[@id='plugin' and @src='${copyUrl}']".replace("${copyUrl}", copyUrl))).isDisplayed();
    switchTo().window(0);
    $(byXpath("//input[@name='address']")).waitUntil(Condition.visible, Configuration.timeout).clear();
  }

  /**
   * rename Uploaded File
   */
  public void renameUploadedFile(String uploadedFile, String uploadedFileNewName) {
    info("Check uploaded file in folder" + uploadedFile);
    $(byXpath(ELEMENT_DOCUMENT_FOLDER_CHECK.replace("${file}", uploadedFile))).waitUntil(Condition.visible, Configuration.timeout).click();
    info("Rename uploaded file");
    $(ELEMENT_SITEEXPLORER_ACTION_RENAME).waitUntil(Condition.visible, Configuration.timeout).click();
    $(ELEMENT_SITEEXPLORER_RENAME_FIELD).waitUntil(Condition.visible, Configuration.timeout).setValue(uploadedFileNewName);
    $(ELEMENT_SITEEXPLORER_RENAME_SAVE).waitUntil(Condition.visible, Configuration.timeout).click();
  }

  /**
   * Search File
   */
  public void searchFile(String firstUploadedFile) {
    refresh();
    $(byXpath("//input[@id='simpleSearch']")).waitUntil(Condition.visible, Configuration.timeout).setValue(firstUploadedFile);
    if ($(byXpath("//span[@class='warningIcon' and contains(text(),'You are at the root folder.')]")).isDisplayed()) {
      do {
        $(byXpath("//a[@class='btn' and text()='OK']")).waitUntil(Condition.visible, Configuration.timeout).click();
      } while (!$(byXpath("//span[@class='warningIcon' and contains(text(),'You are at the root folder.')]")).isDisplayed());
    }
    sleep(2000);
    $(ELEMENT_QUICK_SEARCH_BUTTON).waitUntil(Condition.visible, Configuration.timeout).sendKeys(Keys.ENTER);
    sleep(2000);
  }

  /**
   * List sublink in user menu
   */
  public enum specifUserToolBar {
    MY_PROFILE, MY_ACTIVITY, MY_CONNECTIONS, MY_WIKI, MY_DASHBOARD, MY_NOTIFICATION, SETTINGS, CHANGE_LANGUAGE;
  }
}
