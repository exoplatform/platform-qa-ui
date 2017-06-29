package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;

import org.exoplatform.platform.qa.ui.selenium.*;
import org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class UserAddManagement {
  private final TestBase         testBase;

  public Dialog                  dialog;

  public ManageAlert             alert;

  public Button                  button;

  private UserAndGroupManagement userAndGroupManage;

  private ElementEventTestBase   evt;

  public UserAddManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.dialog = new Dialog(testBase);
    this.userAndGroupManage = new UserAndGroupManagement(testBase);
    this.button = new Button(testBase);
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

    $(ELEMENT_USERNAME).waitUntil(appears, 10000);
    $(ELEMENT_USERNAME).setValue(userName);
    $(ELEMENT_PASSWORD).setValue(password);
    $(ELEMENT_CONFIRM_PASSWORD).setValue(password);
    $(ELEMENT_EMAIL).setValue(email);
    $(ELEMENT_FIRSTNAME).setValue(firstname);
    $(ELEMENT_LASTNAME).setValue(lastName);
    $(ELEMENT_SAVE_ADD_USER).click();
    $(byText("You have registered a new account.")).waitUntil(appears, 10000);
    $(byText("OK")).click();
    info("Finish adding an user");
  }

  /**
   * Add many users at the same time
   *
   * @param number is the number of user that wants to create
   * @param password
   * @return array
   */
  public ArrayList<String> addUsers(int number, String password) {
    ArrayList<String> array = new ArrayList<String>();
    for (int i = 0; i < number; i++) {
      info("Add new a user");
      String user = getRandomString();
      String email = user + "@gmail.com";
      addUser(user, password, email, user, user);
      info("Add users to user's array");
      array.add(user);
      info("User" + i + ": " + user);
    }
    return array;
  }

  /**
   * function: check content of mail then delete mail
   *
   * @param title title of the page
   * @object if true check it's present, false check if it's not present
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
      evt.waitForAndGetElement(PlatformLocator.ELEMENT_GMAIL_CONTENT.replace("${title}", title), 30000, 1);
    } else {
      evt.waitForElementNotPresent(PlatformLocator.ELEMENT_GMAIL_CONTENT.replace("${title}", title), 30000, 1);
    }
  }

  /**
   * function: changePasswordFor User
   *
   * @param currentPass Current password of user
   * @param newPass new Password of User
   * @param confirmNewPass confirm new pass word of User
   */
  public void changePassWord(String currentPass, String newPass, String confirmNewPass) {
    info("Change password");
    evt.waitForAndGetElement(ELEMENT_CHANGE_PASSWORD_LINK, 2000, 0);
    evt.click(ELEMENT_CHANGE_PASSWORD_LINK);
    if (currentPass != null && currentPass != "")
      evt.type(ELEMENT_CURRENT_PASSWOR, currentPass, true);
    if (newPass != null && newPass != "")
      evt.type(ELEMENT_NEW_PASSWORD, newPass, true);
    if (confirmNewPass != null && confirmNewPass != "")
      evt.type(ELEMENT_CONFIRM_NEW_PASSWORD, confirmNewPass, true);
    evt.click(ELEMENT_SAVE_PASSWORD);
    evt.waitForMessage(ELEMENT_MSG_CHANGE_PASS_WORD);
    evt.click(ELEMENT_CLOSE_MESSAGE);
    button.close();
    evt.waitForElementNotPresent(button.ELEMENT_CLOSE_BUTTON);
  }
}
