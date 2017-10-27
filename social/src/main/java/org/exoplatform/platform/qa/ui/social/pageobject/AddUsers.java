package org.exoplatform.platform.qa.ui.social.pageobject;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_GMAIL_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_SELECT_BOX_USERS;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class AddUsers {
  private final TestBase       testBase;

  public ManageAlert           manageAlert;

  private ElementEventTestBase evt;

  public AddUsers(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.manageAlert = new ManageAlert(testBase);
  }

  /**
   * Add en user on the plf
   *
   * @param userName
   * @param Password
   * @param email
   * @param Firstname
   * @param lastName
   */
  public void addUser(String userName, String password, String email, String firstname, String lastName) {
    info("Add an user");
    $(ELEMENT_USERNAME).waitUntil(appears, Configuration.timeout);
    $(ELEMENT_USERNAME).setValue(userName);
    $(ELEMENT_PASSWORD).setValue(password);
    $(ELEMENT_CONFIRM_PASSWORD).setValue(password);
    $(ELEMENT_EMAIL).setValue(email);
    $(ELEMENT_FIRSTNAME).setValue(firstname);
    $(ELEMENT_LASTNAME).setValue(lastName);
    $(ELEMENT_SAVE).click();
    ELEMENT_POPUP_MESSAGE_USER_ADDED.waitUntil(appears, Configuration.timeout);
    $(ELEMENT_CONFIRM_INFORMATION).click();
    info("Finish adding an user");
  }

  public void deleteUser(String username) {
    info("--Deleting user " + username + "--");
    info("--Search user " + username + "--");
    ELEMENT_SELECT_BOX_USERS.selectOptionByValue("userName");
    if (testBase.isTextPresent("Search")) {
      $(ELEMENT_INPUT_SEARCH_USER_NAME).setValue(username);
    }
    ELEMENT_INPUT_SEARCH_USER.click();
    ELEMENT_BUTTON_DELETE_USER.click();
    manageAlert.acceptAlert();
    $(ELEMENT_INPUT_SEARCH_USER_NAME).setValue(username);
    ELEMENT_INPUT_SEARCH_USER.click();
    ELEMENT_POPUP_NO_RESULT_FOUNF.waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_CONFIRM_INFORMATION).click();
  }

  /**
   * function: check content of mail then delete mail
   *
   * @param title title of the page
   * @param opParams if true check it's present, false check if it's not present
   */
  public void checkEmailNotification(String title, Object... opParams) {
    info("Check and delete mail");
    Boolean checkOrNo = (Boolean) (opParams.length > 0 ? opParams[0] : true);
    String parentWindow = testBase.getExoWebDriver().getWebDriver().getWindowHandle();
    info("parentWindow:" + parentWindow);
    for (String windowHandle : testBase.getExoWebDriver().getWebDriver().getWindowHandles()) {
      testBase.getExoWebDriver().getWebDriver().switchTo().window(windowHandle);
      info("driver.title:" + testBase.getExoWebDriver().getWebDriver().getTitle());
    }
    if (checkOrNo == true) {
      evt.waitForAndGetElement(ELEMENT_GMAIL_CONTENT.replace("${title}", title), 30000, 0);
    } else {
      evt.waitForElementNotPresent(ELEMENT_GMAIL_CONTENT.replace("${title}", title), 30000, 0);
    }
  }
}
