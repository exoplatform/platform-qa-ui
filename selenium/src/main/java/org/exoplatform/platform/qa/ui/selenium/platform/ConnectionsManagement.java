package org.exoplatform.platform.qa.ui.selenium.platform;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_CONTENT_NAME_PROFILE;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_NAME_OF_PROFILE_TOP_LEFT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

;

public class ConnectionsManagement {
  private final TestBase       testBase;

  public UserProfilePage       myProf;

  private ElementEventTestBase evt;
  public HomePagePlatform homePagePlatform;

  public ConnectionsManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.myProf = new UserProfilePage(testBase);
    this.homePagePlatform=new HomePagePlatform(testBase);
  }

  /**
   * @param option
   */
  public void goToConnectionTab(selectTabOption option) {
    info("Go to tab");
    switch (option) {
    case ALL:
      info("Go to all tab");
      evt.click(ELEMENT_ALL_CONNECTIONS_TAB, 0, true);
      break;
    case MYCONNECTION:
      info("Go to my connection tab");
      evt.clickByJavascript(ELEMENT_MY_CONNECTIONS_TAB, 0, true);
      break;
    case RECEIVE:
      info("Go to receive tab");
      $(ELEMENT_REQUEST_RECEIVE_CONNECTIONS_TAB).click();
      break;
    case PENDING:
      info("Go to pending tab");
      $(ELEMENT_REQUEST_PENDING_CONNECTIONS_TAB).click();
      break;
    default:
      info("Go to all tab");
      evt.click(ELEMENT_ALL_CONNECTIONS_TAB, 0, true);
      break;
    }

  }

  /**
   * Connect to a user
   * 
   * @param username
   */
  public void connectToAUser(String username, String... name) {
    info("--Connect to a user--");
    info("Click on connect button");
    if (name.length > 0)
      searchPeople(name[0], null, null, null);
    else
      searchPeople(username, null, null, null);
    refresh();
    if ($(ELEMENT_CONNECTION_REVOVE_BTN).is(Condition.exist)) {
      $(ELEMENT_CONNECTION_REVOVE_BTN).click();
    }
    if ($(ELEMENT_CONNECTION_CANCEL_BTN).is(Condition.exist)) {
      $(ELEMENT_CONNECTION_CANCEL_BTN).click();
    }
    $(byText("Connect")).click();
    // evt.waitForAndGetElement(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",
    // username), 2000, 1);
    info("Connected to the user");
  }

  /**
   * Remove a connection of user
   * 
   * @param username
   */
  public void removeConnection(String username) {
    info("--Remove a connection of a user--");
    info("Click on remove button");
    searchPeople(username, null, null, null);
    $(ELEMENT_CONNECTION_REVOVE_BTN).click();
    refresh();
    $(ELEMENT_CONNECTION_REVOVE_BTN).waitUntil(Condition.disappears, Configuration.timeout);
    info("Removed to the user");
  }

  /**
   * Cancel a connection to a user
   * 
   * @param username
   */
  public void cancelConnection(String username) {
    info("--Cancel a connection of a user--");
    info("Click on Cancel button");
    searchPeople(username, null, null, null);
    $(byText("Cancel Request")).click();
    $(byText("Cancel Request")).waitUntil(Condition.disappears, Configuration.timeout);
    info("Canceled to the user");
  }

  /**
   * Ignore a connection that is sent from a user
   * 
   * @param username
   */
  public void ignoreConnection(String username) {
    info("--Ignore a connection of a user--");
    info("Click on Ignore button");
    searchPeople(username, null, null, null);
    ELEMENT_CONTENT_PEOPLE.find(byText(username)).parent().parent().parent().findAll(byClassName("actionLabel")).get(0).click();
    $(byText(username)).parent().parent().parent().find(byText("Ignore")).waitUntil(Condition.disappears,Configuration.timeout);
    info("Connected to the user");
  }

  /**
   * Reset all connections to default status
   * 
   * @param username
   */
  public void resetConnection(String username) {
    searchPeople(username, null, null, null);
    if (evt.waitForAndGetElement(ELEMENT_CONNECTION_REVOVE_BTN, 3000, 0) != null)
      removeConnection(username);
    if (evt.waitForAndGetElement(ELEMENT_CONNECTION_CANCEL_BTN, 3000, 0) != null)
      cancelConnection(username);
    if (evt.waitForAndGetElement(ELEMENT_CONNECTION_IGNORE_BTN.replace("${user}", username), 3000, 0) != null)
      ignoreConnection(username);
  }

  /**
   * Accept a connection from a user in Connection page
   * 
   * @param username
   */
  public void acceptAConnection(String username, String... fullName) {
    info("--Accept a connection of a user--");
    info("Click on Confirm button");
    if (fullName.length > 0)
      searchPeople(fullName[0], null, null, null);
    else
      searchPeople(username, null, null, null);
    $(byText("Confirm")).click();
    $(byText("Confirm")).waitUntil(Condition.disappears, Configuration.timeout);
    info("Accepted to the user");
  }

  /**
   * Function Verify connection
   * 
   * @param username
   * @param accept (true: if user accept invitation)
   */
  public void verifyConnection(String username, Boolean accept) {

    $(ELEMENT_MY_CONNECTIONS_TAB).click();
    // With user confirmed the invitation, user becomes friend and user's name
    // is displayed on user's network list
    searchPeople(username, null, null, null);
    if (accept){

      ELEMENT_CONNECTION_REVOVE_BTN.should(Condition.exist);
    }
    else
      evt.waitForElementNotPresent(ELEMENT_CONNECTION_REVOVE_BTN);
  }

  /**
   * Verify that a request pending is sent to the user
   * 
   * @param userName
   * @param isSent
   */
  public void verifyRequestPending(String userName, Boolean isSent) {
    searchPeople(userName, null, null, null);
    if (isSent) {
      info("Verify that connection request is sent to the user");
      evt.waitForAndGetElement(ELEMENT_CONNECTION_CANCEL_BTN);
      info("The request is sent successfully");
    } else {
      info("Verify that connection request isnot sent to the user");
      evt.waitForElementNotPresent(ELEMENT_CONNECTION_CANCEL_BTN);
      info("The request isnot sent successfully");
    }
  }

  /**
   * Clear search textbox
   */
  public void clearSearchTextbox() {
    info("Clear search textbox");
    evt.click(ELEMENT_ALL_RESULTS);
    evt.type(ELEMENT_NAME_OF_PEOPLE, "", true);
    evt.type(ELEMENT_POSITIONS_OF_PEOPLE, "", true);
    evt.type(ELEMENT_SKILL_OF_PEOPLE, "", true);
  }

  /**
   * Function search people
   * 
   * @param peopleName
   * @param position
   * @param skills
   * @param directory
   */
  public void searchPeople(String peopleName, String position, String skills, String directory) {
    info("-- Searching people ... --");
    if (peopleName != "" && peopleName != null) {

      ELEMENT_NAME_OF_PEOPLE.waitUntil(Condition.appears,Configuration.timeout).setValue(peopleName).pressEnter();
    } else {
      $(ELEMENT_NAME_OF_PEOPLE).setValue("");
    }
    if (position != "" && position != null) {
      $(ELEMENT_POSITIONS_OF_PEOPLE).setValue(position);
    } else {
      $(ELEMENT_POSITIONS_OF_PEOPLE).setValue("");
    }
    if (skills != "" && skills != null) {
      $(ELEMENT_SKILL_OF_PEOPLE).setValue(skills);
    } else {
      $(ELEMENT_SKILL_OF_PEOPLE).setValue("");
    }
    $(ELEMENT_SEARCH_BUTTON).pressEnter();
    refresh();
    $(ELEMENT_SEARCH_BUTTON).pressEnter();
    if (directory != "" && directory != null)
      $(byLinkText(directory)).click();
  }

  /**
   * Go to User
   */
  public void goToUserByFullName(String fullName) {
    info("Go to User profile page");
    searchPeople(fullName, "", "", "");
    ELEMENT_CONTENT_PEOPLE.find(byText(fullName)).click();
    ELEMENT_CONTENT_NAME_PROFILE.find(byText(fullName)).should(Condition.exist);  }

  /**
   * Go to User
   * 
   * @param userName
   */
  public void goToUserByUserName(String userName) {
    info("Go to User profile page");
    searchPeople(userName, "", "", "");
    evt.click(ELEMENT_USER_LINK.replace("" + "${userName}", userName));
    evt.waitForAndGetElement(ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", userName));
  }

  /**
   * Check display of user in Connection
   * 
   * @param user
   * @param isPresent
   * @param opt all, my connections, request pending,...
   */
  public void checkDisplayInConnection(String user, boolean isPresent, selectTabOption opt) {
    goToConnectionTab(opt);
    info("check display of user in Connection");
    if (isPresent)
      evt.waitForAndGetElement(ELEMENT_USER_LINK.replace("${userName}", user), 2000, 0);
    else
      evt.waitForElementNotPresent(ELEMENT_USER_LINK.replace("${userName}", user));
  }

  /**
   * Check search result in Connection
   * 
   * @param user
   * @param isPresent
   * @param opt all, my connections, request pending,...
   */
  public void checkSearchResultInConnection(String user, boolean isPresent, selectTabOption opt) {
    goToConnectionTab(opt);
    info("check display of search result");
    if (isPresent) {
      searchPeople(user, "", "", "");
      evt.waitForAndGetElement(ELEMENT_USER_LINK.replace("${userName}", user), 2000, 0);
    } else {
      searchPeople(user, "", "", "");
      evt.waitForElementNotPresent(ELEMENT_USER_LINK.replace("${userName}", user));
    }
  }

  /**
   * Tab list
   */
  public enum selectTabOption {
    ALL, MYCONNECTION, RECEIVE, PENDING;
  }
}
