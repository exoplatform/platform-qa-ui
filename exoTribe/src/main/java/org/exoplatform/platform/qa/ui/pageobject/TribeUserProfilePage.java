package org.exoplatform.platform.qa.ui.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class TribeUserProfilePage {
  private final TestBase testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase
   */
  public TribeUserProfilePage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Update Basic information
   */
  public void updateBasicInformationTribe(String firstName, String lastName, String email, String job) {
    info("Update basic information");

    ELEMENT_CONTACT_INFORMATIONS_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    if (firstName != "" && firstName != null) {
      info("update firstname");
      $(ELEMENT_CONTACT_FIRST_NAME_EDIT_BTN_DW).setValue(firstName);
    }
    if (lastName != "" && lastName != null) {
      info("update lastName");
      $(ELEMENT_CONTACT_LAST_NAME_EDIT_BTN_DW).setValue(lastName);
    }
    if (email != "" && email != null) {
      info("update email");
      $(ELEMENT_CONTACT_EMAIL_EDIT_BTN_DW).setValue(email);
    }
    if (job != "" && job != null) {
      info("update job");
      $(ELEMENT_CONTACT_JOB_TITLE_EDIT_BTN_DW).setValue(job);
    }

    ELEMENT_CONTACT_EDIT_SAVE_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

  public void goToUpdateContactInformationsTribe() {

    ELEMENT_CONTACT_INFORMATIONS_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

  public void addNewPhoneNumber() {

    ELEMENT_ADD_CONTACT_NEW_PHONE_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

  public void enterNewPhoneNumber(PhoneType phoneType, String phone) {

    if (phone != "" && phone != null) {
      info("Select Phone Type");
      selectNewPhoneType(phoneType);
      info("Enter new Phone Number");
      $(ELEMENT_CONTACT_NEW_PHONE_TITLE_EDIT_BTN_DW).setValue(phone);
    }

  }

  public void addNewMessagingInstant() {

    ELEMENT_ADD_CONTACT_NEW_INSTANT_MESSAGING_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

  public void enterNewMessagingInstant(InstantMessagingType instantMessagingType, String instantMessaging) {

    if (instantMessaging != "" && instantMessaging != null) {
      info("Select instantMessaging Type");
      selectNewInstantMessagingType(instantMessagingType);
      info("Enter new instantMessaging information");
      $(ELEMENT_CONTACT_NEW_INSTANT_MESSAGING_TITLE_EDIT_BTN_DW).setValue(instantMessaging);
    }

  }

  public void addNewUrl() {

    ELEMENT_ADD_CONTACT_NEW_URL_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

  public void enterNewUrl(String url) {

    if (url != "" && url != null) {
      info("Add new url");
      $(ELEMENT_CONTACT_NEW_URL_TITLE_EDIT_BTN_DW).setValue(url);
    }

  }

  public void saveUpdatedInformations() {

    ELEMENT_CONTACT_EDIT_SAVE_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

  public void updateContactOtherInformationsTribe(String company, PhoneType phoneType, String phone, InstantMessagingType instantMessagingType, String instantMessaging, String url) {
    info("Update other informations");

    ELEMENT_CONTACT_INFORMATIONS_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

    if (company.equals("")) {
      info("Remove company");
      String companyName = $(ELEMENT_CONTACT_COMPANY_TITLE_EDIT_BTN_DW).getValue();

      for (int i=0; i <= companyName.length(); i++ )
      {
        $(ELEMENT_CONTACT_COMPANY_TITLE_EDIT_BTN_DW).sendKeys(Keys.BACK_SPACE);
      }
    }

    if (company != "" && company != null) {
      info("update company");
      $(ELEMENT_CONTACT_COMPANY_TITLE_EDIT_BTN_DW).setValue(company);
    }

    if (phone != "" && phone != null) {
      info("update phone");
      info("Select Phone Type");
      selectPhoneType(phoneType);
      info("Enter Phone Number");
      $(ELEMENT_CONTACT_PHONE_TITLE_EDIT_BTN_DW).setValue(phone);
    }
    if (instantMessaging != "" && instantMessaging != null) {
      info("update instantMessaging");
      info("Select instantMessaging Type");
      selectInstantMessagingType(instantMessagingType);
      info("Enter instantMessaging information");
      $(ELEMENT_CONTACT_INSTANT_MESSAGING_TITLE_EDIT_BTN_DW).setValue(instantMessaging);
    }
    if (url != "" && url != null) {
      info("update url");
      $(ELEMENT_CONTACT_URL_TITLE_EDIT_BTN_DW).setValue(url);
    }

    ELEMENT_CONTACT_EDIT_SAVE_BTN_DW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();

  }

  public void selectPhoneType(PhoneType phoneType,
                                      String... option) {
    switch (phoneType) {
      case WORK:
        info("Select WORK option");
        ELEMENT_CONTACT_PHONE_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("work");
        break;
      case HOME:
        info("Select HOME option");
        ELEMENT_CONTACT_PHONE_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("home");
        break;
      case OTHER:
        info("Select OTHER option");
        ELEMENT_CONTACT_PHONE_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("other");
        break;

      default:
        info("No option in the list.Please select correct option.");
        break;
    }
  }

  public void selectNewPhoneType(PhoneType phoneType,
                              String... option) {
    switch (phoneType) {
      case WORK:
        info("Select WORK option");
        ELEMENT_CONTACT_NEW_PHONE_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("work");
        break;
      case HOME:
        info("Select HOME option");
        ELEMENT_CONTACT_NEW_PHONE_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("home");
        break;
      case OTHER:
        info("Select OTHER option");
        ELEMENT_CONTACT_NEW_PHONE_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("other");
        break;

      default:
        info("No option in the list.Please select correct option.");
        break;
    }
  }

    public enum PhoneType {
      WORK, HOME, OTHER;
    }

  public void selectInstantMessagingType(InstantMessagingType instantMessagingType,
                              String... option) {
    switch (instantMessagingType) {
      case SKYPE:
        info("Select SKYPE option");
        ELEMENT_CONTACT_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("skype");
        break;
      case MSN:
        info("Select MSN option");
        ELEMENT_CONTACT_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("msn");
        break;
      case GITHUB:
        info("Select GITHUB option");
        ELEMENT_CONTACT_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("github");
        break;
      case FACEBOOK:
        info("Select FACEBOOK option");
        ELEMENT_CONTACT_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("facebook");
        break;
      case OTHER:
        info("Select OTHER option");
        ELEMENT_CONTACT_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("other");
        break;

      default:
        info("No option in the list.Please select correct option.");
        break;
    }
  }

  public void selectNewInstantMessagingType(InstantMessagingType instantMessagingType,
                                         String... option) {
    switch (instantMessagingType) {
      case SKYPE:
        info("Select SKYPE option");
        ELEMENT_CONTACT_NEW_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("skype");
        break;
      case MSN:
        info("Select MSN option");
        ELEMENT_CONTACT_NEW_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("msn");
        break;
      case GITHUB:
        info("Select GITHUB option");
        ELEMENT_CONTACT_NEW_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("github");
        break;
      case FACEBOOK:
        info("Select FACEBOOK option");
        ELEMENT_CONTACT_NEW_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("facebook");
        break;
      case OTHER:
        info("Select OTHER option");
        ELEMENT_CONTACT_NEW_INSTANT_MESSAGING_TYPE_SELECT_EDIT_BTN_DW.waitUntil(Condition.visible, Configuration.collectionsTimeout).selectOptionByValue("other");
        break;

      default:
        info("No option in the list.Please select correct option.");
        break;
    }
  }

  public enum InstantMessagingType {
    SKYPE, MSN, FACEBOOK, GITHUB, OTHER;
  }

  }