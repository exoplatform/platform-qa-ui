package org.exoplatform.platform.qa.ui.social.pageobject;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_SELECT_BOX_USERS;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.junit.Assert;
import org.openqa.selenium.By;

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
   * @param userName String
   * @param password String
   * @param email String
   * @param firstname String
   * @param lastName String
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

  public void addUserTribe(String userName, String password, String email, String firstname, String lastName, String status) {
    info("Add an user");
    ELEMENT_ADD_USER_TRIBE_BTN.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    $(ELEMENT_USERNAME_TRIBE_BTN).waitUntil(appears, Configuration.timeout);
    $(ELEMENT_USERNAME_TRIBE_BTN).setValue(userName);
    $(ELEMENT_PASSWORD_TRIBE_BTN).setValue(password);
    $(ELEMENT_CONFIRM_PASSWORD_TRIBE_BTN).setValue(password);
    $(ELEMENT_EMAIL_TRIBE_BTN).setValue(email);
    $(ELEMENT_FIRSTNAME_TRIBE_BTN).setValue(firstname);
    $(ELEMENT_LASTNAME_TRIBE_BTN).setValue(lastName);
    if(status=="disabled"){
      evt.check(ELEMENT_STATUS_TRIBE_BTN);
      Assert.assertEquals(ELEMENT_STATUS_TRIBE_BTN.getAttribute("aria-checked"),"false");
    }

    $(ELEMENT_SAVE_ADD_USER_TRIBE_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    info("Finish adding an user");

  }

  public void saveAddUserTribe() {
    $(ELEMENT_SAVE_ADD_USER_TRIBE_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

  }

  public void cancelAddUserTribe() {
    $(ELEMENT_CANCEL_ADD_USER_TRIBE_BTN).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();

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

  public void deleteUserDW(String username) {
    info("--Deleting user " + username + "--");
    info("--Search user " + username + "--");

    ELEMENT_USER_TRIBE_SEARCH_TEXT.setValue(username);
    ELEMENT_DELETE_USER_TRIBE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    ELEMENT_CONFIRM_DELETE_USER_TRIBE.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).click();
    sleep(1000);
    testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    sleep(2000);
    ELEMENT_USER_TRIBE_SEARCH_TEXT.waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).setValue(username);
    $(By.xpath("(//*[@class='text-center' and contains(text(),'${user}')])[1]".replace("${user}",username)))
            .waitUntil(Condition.not(Condition.visible),Configuration.openBrowserTimeoutMs);

  }

}
