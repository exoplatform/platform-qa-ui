package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_CHANGE_PASSWORD_CLOSE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import java.util.ArrayList;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.Dialog;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
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
   * @param userName String
   * @param password String
   * @param email String
   * @param firstname String
   * @param lastName String
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
    $(ELEMENT_SAVE_ADD_USER).waitUntil(visible,Configuration.openBrowserTimeoutMs).click();
    $(byText("You have registered a new account.")).waitUntil(appears, 10000);
    $(byText("OK")).click();
    info("Finish adding an user");
  }

  /**
   * Add many users at the same time
   *
   * @param number is the number of user that wants to create
   * @param password String
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
   * function: changePasswordFor User
   *
   * @param currentPass Current password of user
   * @param newPass new Password of User
   * @param confirmNewPass confirm new pass word of User
   */
  public void changePassWord(String currentPass, String newPass, String confirmNewPass) {
    info("Change password");
    $(ELEMENT_CHANGE_PASSWORD_LINK).waitUntil(visible,Configuration.timeout);
    $(ELEMENT_CHANGE_PASSWORD_LINK).click();
    if (currentPass != null && currentPass != "")
      $(ELEMENT_CURRENT_PASSWOR).setValue(currentPass);
    if (newPass != null && newPass != "")
      $(ELEMENT_NEW_PASSWORD).setValue(newPass);
    if (confirmNewPass != null && confirmNewPass != "")
      $(ELEMENT_CONFIRM_NEW_PASSWORD).setValue(confirmNewPass);
    $(ELEMENT_SAVE_PASSWORD).click();
    $(byText(ELEMENT_MSG_CHANGE_PASS_WORD)).waitUntil(visible,Configuration.timeout);
    $(byXpath(ELEMENT_CLOSE_MESSAGE)).click();
    evt.waitForAndGetElement(ELEMENT_CHANGE_PASSWORD_CLOSE_BUTTON);
    evt.click(ELEMENT_CHANGE_PASSWORD_CLOSE_BUTTON);
    $(ELEMENT_CHANGE_PASSWORD_CLOSE_BUTTON).waitUntil(not(visible),Configuration.timeout);

  }
}
