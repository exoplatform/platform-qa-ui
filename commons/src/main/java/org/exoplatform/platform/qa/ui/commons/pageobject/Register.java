package org.exoplatform.platform.qa.ui.commons.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class Register {
  public final SelenideElement container = $(By.id("Register_Page"));

  public Register() {
  }


  public boolean isUserRegistered(){
    return $(byClassName("confirm-mail-container")).waitUntil(Condition.visible, Configuration.timeout).exists();
  }

  /**
   * register with a specific User and Password
   *
   * @param firstName the firstname to use to register a new account
   * @param lastName the lastname to use to register a new account
   * @param lang the language of the user
   * @param password the password associated with the user
   * @param email the email of the user
   * @param tvaNum the tva number of the user (or its enterprise)
   */
  public Register newRegister(final String firstName, final String lastName, final String password, final String lang,
                              final String email, final String tvaNum) {
    $(byId("emailAddress")).setValue(email);
    $(byId("confirmEmailAddress")).setValue(email);
    $(byId("firstName")).setValue(firstName);
    $(byId("lastName")).setValue(lastName);
    if (StringUtils.isNotBlank(lang)) {
      $(byId("user.language")).setValue(lang);
    }
    $(byId("password")).setValue(password);
    $(byId("tvaNum")).setValue(tvaNum);
    $(byId("validateTvaButton")).click();
    SelenideElement captchaElement = $(byId("captcha"));
    captchaElement.waitUntil(Condition.appears, Configuration.timeout);
    executeJavaScript("window.alert('Please enter the CAPTCHA')");
    captchaElement.click();
    $(byClassName("subscribe")).waitUntil(Condition.enabled, Configuration.timeout).click();
    return this;
  }

  public boolean isButtonEnabled() {
    SelenideElement button = $(byClassName("subscribe"));
    return button.has(Condition.enabled);
  }

  public Register skipRegister() {
    $(byClassName("alreadyMember")).find(By.tagName("a")).click();
    return this;
  }

}
