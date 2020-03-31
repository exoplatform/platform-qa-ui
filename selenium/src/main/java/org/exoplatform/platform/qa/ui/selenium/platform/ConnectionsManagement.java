package org.exoplatform.platform.qa.ui.selenium.platform;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_CONTENT_NAME_PROFILE;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.Keys;

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
      $(ELEMENT_REQUEST_RECEIVE_CONNECTIONS_TAB).waitUntil(Condition.visible,Configuration.timeout).click();
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
  public void cancelConnection(String username) throws Exception {
    info("--Cancel a connection of a user--");
    info("Click on Cancel button");
    searchPeople(username, null, null, null);
    $(byText("Cancel Request")).click();
    homePagePlatform.refreshUntil($(byText("Cancel Request")),Condition.not(Condition.visible),1000);
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
    searchPeople(username,null,null,null);
    $(byXpath(ELEMENT_CONNECTION_IGNORE_BTN.replace("${user}",username))).click();
    $(byXpath(ELEMENT_CONNECTION_IGNORE_BTN.replace("${user}",username))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    info("Connected to the user");
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
      $(byXpath(ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username))).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
      homePagePlatform.refreshUntil($(byXpath(ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username))),Condition.not(Condition.visible),1000);
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
    searchPeople(username, null, null, null);
    if (accept){

      ELEMENT_CONNECTION_REVOVE_BTN.should(Condition.exist);
    }
    else
      evt.waitForElementNotPresent(ELEMENT_CONNECTION_REVOVE_BTN);
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

      if ($(ELEMENT_SEARCHED_NAME_OF_PEOPLE).exists())
      {
        int searchedNamelength = $(ELEMENT_SEARCHED_NAME_OF_PEOPLE).getText().length();
        for (int i=0; i<searchedNamelength; i++)
        {
          $(ELEMENT_NAME_OF_PEOPLE).waitUntil(Condition.visible,Configuration.timeout).sendKeys(Keys.BACK_SPACE);
        }
      }
      $(ELEMENT_NAME_OF_PEOPLE).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue("");
      $(ELEMENT_NAME_OF_PEOPLE).waitUntil(Condition.visible,Configuration.timeout).setValue(peopleName);
    } else {
      $(ELEMENT_NAME_OF_PEOPLE).setValue("");
    }
    if (position != "" && position != null) {
      $(ELEMENT_POSITIONS_OF_PEOPLE).waitUntil(Condition.visible,Configuration.timeout).setValue(position);
    } else {
      $(ELEMENT_POSITIONS_OF_PEOPLE).setValue("");
    }
    if (skills != "" && skills != null) {
      $(ELEMENT_SKILL_OF_PEOPLE).waitUntil(Condition.visible,Configuration.timeout).setValue(skills);
    } else {
      $(ELEMENT_SKILL_OF_PEOPLE).setValue("");
    }
    $(ELEMENT_SEARCH_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
    homePagePlatform.refreshUntil($(byClassName("spaceBox")),Condition.visible,1000);
    if (directory != "" && directory != null)
      $(byLinkText(directory)).waitUntil(Condition.appears,Configuration.timeout).click();
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
   * Tab list
   */
  public enum selectTabOption {
    ALL, MYCONNECTION, RECEIVE, PENDING;
  }
}
