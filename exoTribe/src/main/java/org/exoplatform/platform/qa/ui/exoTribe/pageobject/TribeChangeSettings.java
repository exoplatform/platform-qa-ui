package org.exoplatform.platform.qa.ui.exoTribe.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.exoTribe.exoTribeLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMETN_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class TribeChangeSettings {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public TribeChangeSettings(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Select a language and changed it
   * 
   * @param language
   */
  public void changeLanguage(String language, String applyText) {
    info("Select language and change it");
    $(ELEMENT_CHANGE_LANGUAGE_POPUP_TITLE).waitUntil(Condition.visible, Configuration.timeout);
    $(byXpath(ELEMENT_CHANGELANGUAGE_LANGUAGE.replace("${language}", language))).waitUntil(Condition.visible,Configuration.timeout).click();
    sleep(Configuration.timeout);
    $(byXpath(ELEMENT_AVATAR_CHANGELANGUAGE_APPLY.replace("${text}", applyText))).waitUntil(Condition.visible,Configuration.timeout).click();
  }

  public void tribeEditLanguage(String language) {
    info("Select language and change it");
    $(ELEMENT_TRIBE_EDIT_LANGUAGE).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byXpath(ELEMENT_TRIBE_CHANGE_LANGUAGE.replace("${language}", language))).waitUntil(Condition.visible,Configuration.timeout).click();
  }

  public void tribeEditTimeZone(String timeZone) {
    info("Select Time Zone and change it");
    $(ELEMENT_TRIBE_EDIT_TIME_ZONE).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byXpath(ELEMENT_TRIBE_CHANGE_TIMEZONE.replace("${timeZone}", timeZone))).waitUntil(Condition.visible,Configuration.timeout).click();
  }

  public void tribeEditPassword(String oldPassword, String password) {

    info("Go to Security Interface");
    $(ELEMENT_TRIBE_EDIT_PASSWORD).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    info("Enter the current password");
    $(ELEMENT_TRIBE_OLD_PASSWORD).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(oldPassword);

    info("Enter the new password");
    $(ELEMENT_TRIBE_NEW_PASSWORD).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(password);

    info("Confirm the new password");
    $(ELEMENT_TRIBE_NEW_PASSWORD_CONFIRM).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(password);

  }

  public void tribeEnableDisableNotificationViaMail() {

    evt.check(ELEMENT_TRIBE_NOTIFICATION_VIA_MAIL, 2);

  }

  public void tribeEnableDisableNotificationOnMobile() {

    evt.check(ELEMENT_TRIBE_NOTIFICATION_ON_MOBILE, 2);

  }

  public void tribeEnableDisableNotificationOnSite() {

    evt.check(ELEMENT_TRIBE_NOTIFICATION_ON_SITE, 2);

  }

  public void tribeAcceptEditLanguage() {
    info("Select language and change it");
    ELEMENT_TRIBE_APPLY_CHANGE_LANGUAGE_BUTTON.waitUntil(Condition.visible,Configuration.timeout).click();
  }

  public void tribeCancelEditLanguage() {
    info("Select language and change it");
    ELEMENT_TRIBE_CANCEL_CHANGE_LANGUAGE_BUTTON.waitUntil(Condition.visible,Configuration.timeout).click();
  }

  public void tribeAcceptEditTimeZone() {
    info("Select Time Zone and change it");
    ELEMENT_TRIBE_APPLY_CHANGE_TIMEZONE_BUTTON.waitUntil(Condition.visible,Configuration.timeout).click();
  }

  public void tribeCancelEditTimeZone() {
    info("Select Time Zone and change it");
    ELEMENT_TRIBE_CANCEL_CHANGE_TIMEZONE_BUTTON.waitUntil(Condition.visible,Configuration.timeout).click();
  }

  public void tribeAcceptEditPassword() {
    info("Click on Confirm");
    ELEMENT_TRIBE_CONFIRM_EDIT_PASSWORD.waitUntil(Condition.visible,Configuration.timeout).click();
  }

  public void tribeCancelEditPassword() {
    info("Click on Cancel");
    ELEMENT_TRIBE_CANCEL_EDIT_PASSWORD.waitUntil(Condition.visible,Configuration.timeout).click();
  }

}
