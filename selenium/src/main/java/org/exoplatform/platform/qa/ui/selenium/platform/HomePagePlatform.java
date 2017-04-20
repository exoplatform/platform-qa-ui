package org.exoplatform.platform.qa.ui.selenium.platform;

import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONNECTION_EVERYONE_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.ELEMENT_ANSWER_PORTLET;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.ELEMENT_FAQ_QUESTION_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.ELEMENT_CALENDAR_WORKING_PANEL;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_FORUM_PORTLET;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import static com.codeborne.selenide.Selenide.$;

public class HomePagePlatform {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public HomePagePlatform(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Go to Wiki portlet
   */
  public void goToWiki() {
    info("--Go to Wiki--");
    Utils.pause(2000);
    evt.waitForAndGetElement(ELEMENT_WIKI_LINK_PLF, testBase.getDefaultTimeout(), 1);
    evt.click(ELEMENT_WIKI_LINK_PLF);
    Utils.pause(2000);
  }

  /**
   * Go to Documents
   */
  public void goToDocuments() {
    info("--Go to Documents--");
    evt.click(ELEMENT_DOCUMENTS_LINK_PLF);
    Utils.pause(2000);
  }

  /**
   * Go to Home page
   */
  public void goToHomePage() {
      info("Click on Home link of intranet page");
      $(ELEMENT_HOME_LINK_PLF).click();
  }

  /**
   * Go to Home Calendar Page
   */
  public void goToCalendarPage() {
    info("-- Go to calendar home page --");
    Utils.pause(3000);
    evt.waitForAndGetElement(ELEMENT_CALENDAR_LINK_PLF, testBase.getDefaultTimeout(), 1);
    info("click on Calendar link");
    evt.click(ELEMENT_CALENDAR_LINK_PLF);
    info("Verify that Calendar page is shown");
    evt.waitForAndGetElement(ELEMENT_CALENDAR_WORKING_PANEL);
    info("The calendar page is shown successfully");
  }

  /**
   * Go to my spaces
   */
  public void goToMySpaces() {
    info("-- Go to my spaces --");
    ELEMENT_MY_SPACE_LINK_PLF.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_MY_SPACE_LINK_PLF.click();
  }

  /**
   * Go to All space list
   */
  public void goToAllSpace() {
    info("Click on Join a space link");
    evt.click(ELEMENT_ALL_SPACE_JOIN_LINK, 0, true);
    Utils.pause(2000);
  }

  /**
   * Go to answer page
   */
  public void goToAnswer() {
    info("-- Go to answer page --");
    Utils.pause(2000);
    evt.waitForAndGetElement(ELEMENT_ANSWER_LINK_PLF);
    evt.click(ELEMENT_ANSWER_LINK_PLF, 0, true);
    if (evt.waitForAndGetElement(ELEMENT_ANSWER_PORTLET, 5000, 0) == null)
      testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    evt.waitForAndGetElement(ELEMENT_ANSWER_PORTLET);
    Utils.pause(2000);
  }

  /**
   * Go to forum page
   */
  public void goToForum() {
    do {
      info("-- Go to forum page --");
      info("Click on Forum link");
      evt.click(ELEMENT_FORUM_LINK_PLF);
      info("Verify that the forum portlet is shown");
    } while (evt.waitForAndGetElement(ELEMENT_FORUM_PORTLET, testBase.getDefaultTimeout(), 1) == null);
    info("The forum portlet is shown successfully");
  }

  /**
   * Go to faq page
   */
  public void goToFaq() {
    info("Base url is " + testBase.getExoWebDriver().getBaseUrl());
    String url = testBase.getExoWebDriver().getBaseUrl() + "/intranet/home/FAQ";
    info("-- Go to FAQ page --");
    testBase.getExoWebDriver().getWebDriver().get(url);
    evt.waitForAndGetElement(ELEMENT_FAQ_QUESTION_LIST);
  }

  /**
   * Open Connections page
   */
  public void goToConnections() {
    info("--Go to Connections page---");
    Utils.pause(2000);
    info("Click on Connection link");
    evt.click(ELEMENT_CONNECTIONS_LINK_PLF, 2);
    info("Verify that the connections portlet is shown");
    evt.waitForAndGetElement(ELEMENT_CONNECTION_EVERYONE_TITLE, 2000, 0);
    info("The connections portlet is shown successfully");
  }

  /**
   * Go to a space
   *
   * @param space
   */
  public void goToSpecificSpace(String space) {
    info("Go to space " + space);
    evt.click(ELEMENT_SPECIFIC_PANEL.replace("{$space}", space), 2);
    Utils.pause(3000);
  }

  /**
   * Open friend profile page
   *
   * @param username
   */
  public void goToFriendProfilePage(String username) {
    info("Go to Friend profile page");
    testBase.getExoWebDriver().getWebDriver().get(testBase.getExoWebDriver().getBaseUrl() + "/intranet/profile/" + username);
    Utils.pause(2000);
  }

  /**
   * Select display mode for AS
   *
   * @param type as My Activities, All Activities....
   */
  public void selectDisplayMode(displayModeType type) {
    info("Open drop menu");
    evt.click(ELEMENT_HOMEPAGE_DROP_MENU_ARROW);
    switch (type) {
    case My_Activities:
      info("Select My Activities");
      evt.click(ELEMENT_HOMEPAGE_DROP_MENU_MY_ACTIVITIES);
      break;
    case All_Activities:
      info("Select All Activities");
      evt.click(ELEMENT_HOMEPAGE_DROP_MENU_ALL_ACTIVITIES);
      break;
    case My_Spaces:
      info("Select My Spaces");
      evt.click(ELEMENT_HOMEPAGE_DROP_MENU_MY_SPACES);
      break;
    case Connections:
      info("Select Connections");
      evt.click(ELEMENT_HOMEPAGE_DROP_MENU_CONNECTIONS);
      break;
    }
  }

  /**
   * Check display of user in Invitation gadget
   *
   * @param user
   * @param isPresent
   */
  public void checkDisplayInInvitationGadget(String user, boolean isPresent) {
    info("check display of user in Invitation");
    goToHomePage();
    if (isPresent)
      evt.waitForAndGetElement(ELEMENT_INVITATIONS_PEOPLE_AVATAR.replace("${name}", user));
    else
      evt.waitForElementNotPresent(ELEMENT_INVITATIONS_PEOPLE_AVATAR.replace("${name}", user));
  }

  /**
   * Load More Activities
   */
  public void loadMoreActivities() {
    Utils.pause(2000);
    info("Load more activities");
    evt.waitForAndGetElement(ELEMENT_PLF_HOMEPAGE_LOAD_MORE_BUTTON, testBase.getDefaultTimeout(), 1);
    evt.click(ELEMENT_PLF_HOMEPAGE_LOAD_MORE_BUTTON);
  }

  /**
   * Check display of user in Suggestion gadget
   *
   * @param user
   * @param isPresent
   */
  public void checkDisplayInSuggestionGadget(String user, boolean isPresent) {
    info("check display of user in Suggestion");
    goToHomePage();
    if (isPresent)
      evt.waitForAndGetElement(ELEMENT_SUGGESTION_NAME.replace("${name}", user));
    else
      evt.waitForElementNotPresent(ELEMENT_SUGGESTION_NAME.replace("${name}", user));
  }

  /**
   * Define display mode's type of the AS as My Activities,All Activities,...
   */
  public enum displayModeType {
    My_Activities, All_Activities, My_Spaces, Connections;
  }

}
