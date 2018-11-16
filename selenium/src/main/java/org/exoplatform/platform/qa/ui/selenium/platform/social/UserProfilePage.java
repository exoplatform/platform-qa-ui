package org.exoplatform.platform.qa.ui.selenium.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_BUTTON_CONFIRM_UPLOAD;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_BUTTON_SAVE_UPLOAD_AVATAR;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_INPUT_UPLOAD_AVATAR;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class UserProfilePage {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param testBase
   */
  public UserProfilePage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Update Current position By QuynhPT
   *
   * @param pos
   */
  public void updateCurrentPosition(String pos) {
    info("Update Current Position");
    if (pos != "" && pos != null) {
      evt.waitForAndGetElement(ELEMENT_EDIT_POSITION);
      evt.click(ELEMENT_EDIT_POSITION);

      evt.click(ELEMENT_EDIT_POSITION_SAVE_BUTTON);

    }
  }

  /**
   * function: Go to Edit profile
   */
  public void goToEditProfile() {
    info("Go to edit profile");
    $(ELEMENT_EDIT_MY_PROFILE_LINK).click();
    $(ELEMENT_EDIT_PROFILE_FORM).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Update About me section
   *
   * @param pos
   */
  public void updateAboutMe(String pos) {
    info("Update About me");
    if (pos != "") {
      $(ELEMENT_ABOUTME_TEXTAREA_EDIT).setValue(pos);
    }
  }

  /**
   * Update Basic information By QuynhPT
   *
   * @param firstName
   * @param lastName
   * @param email
   */
  public void updateBasicInformation(String firstName, String lastName, String email) {
    info("Update basic information");
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());
    if (firstName != "" && firstName != null) {
      info("update firstname");
      $(ELEMENT_FIRST_NAME_TEXTBOX_EDIT).setValue(firstName);
    }
    if (lastName != "" && lastName != null) {
      info("update lastName");
      $(ELEMENT_LAST_NAME_TEXTBOX_EDIT).setValue(lastName);
    }
    if (email != "" && email != null) {
      info("update email");
      $(ELEMENT_EMAIL_TEXTBOX_EDIT).setValue(email);
    }
  }

  /**
   * Change avatar
   *
   */
  public void changeAvatar() {
    info("-- changeAvatar --");

    if (testBase.getBrowser().contains("iexplorer"))
      evt.clickByJavascript(ELEMENT_CHANGE_AVATAR_LINK);
    else
      $(ELEMENT_CHANGE_AVATAR_LINK).click();
    ELEMENT_INPUT_UPLOAD_AVATAR.uploadFromClasspath("testavatar.png");
    ELEMENT_BUTTON_CONFIRM_UPLOAD.click();
    ELEMENT_BUTTON_SAVE_UPLOAD_AVATAR.click();

  }

  /**
   * Update information of contact of a user
   *
   * @param gender
   * @param job
   */
  public void updateGenderJob(String gender, String job) {
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());

    if (gender != "" && gender != null) {
      info("update gender");
      $(ELEMENT_CONTACT_GENDER_SELECTION).selectOptionByValue(gender);
    }
    if (job != "" && job != null) {
      info("update job");
      $(ELEMENT_CONTACT_JOB_TITLE).setValue(job);
    }
  }

  /**
   * Update ims
   *
   * @param type
   * @param ims
   */
  public void updateIms(String type, String ims, int nb) {
    info("Update ims");

    if ($(byId("ims")).find(byClassName("selectInput")).is(Condition.not(Condition.empty))) {
      $(ELEMENT_CONTACT_IMS_ADD_ICON).click();
    }
    if (type != null && !type.isEmpty()) {
      info("select type of ims");
      $(byId("ims")).findAll(byClassName("selectbox")).get(nb).selectOptionByValue(type);
    }
    if (ims != null && !ims.isEmpty()) {
      info("update ims " + ims);
      $(byId("ims")).findAll(byClassName("selectInput")).get(nb).setValue(ims);
      $(byId("ims")).findAll(byClassName("selectInput")).get(nb).click();
    }
  }

  /**
   * @param url
   * @param opParams
   */
  public void updateUrl(String url, Object... opParams) {
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());
    String index = (String) (opParams.length > 0 ? opParams[0] : "0");
    Integer xpathCount = testBase.getElements(ELEMENT_CONTACT_URL_INPUT_LIST).size();
    if (Integer.valueOf(index) >= xpathCount) {
      evt.click(ELEMENT_CONTACT_URL_ADD_ICON);
    }
    if (url != null && !url.isEmpty()) {
      info("update url");
      WebElement input = evt.waitForAndGetElement(By.xpath(ELEMENT_CONTACT_URL_INPUT.replace("${index}", index)),
              testBase.getDefaultTimeout(),
              1);
      Actions action = new Actions(testBase.getExoWebDriver().getWebDriver());
      action.moveToElement(input).click().perform();
      action.sendKeys(url).perform();
      // action.moveToElement(input).sendKeys(url).build().perform();
      // type(ELEMENT_CONTACT_URL_INPUT.replace("${index}", index),url,true);
    }
  }

  /**
   * Update phone
   *
   * @param type
   * @param phone
   */
  public void updatePhone(String type, String phone, int nb) {
    info("Update phone");
    if ($(byId("phones")).find(byClassName("selectInput")).is(Condition.not(Condition.empty))) {
      $(ELEMENT_CONTACT_PHONE_ADD_ICON).click();
    }
    if (type != null && !type.isEmpty()) {
      info("select type of phone");
      $(byId("phones")).findAll(byClassName("selectbox")).get(nb).selectOptionByValue(type);
    }
    if (phone != null && !phone.isEmpty()) {
      info("update phone");
      $(byId("phones")).findAll(byClassName("selectInput")).get(nb).setValue(phone);
      $(byId("phones")).findAll(byClassName("selectInput")).get(nb).click();
    }
  }

  /**
   * update experience
   *
   * @param organization
   * @param jobTitle
   * @param jobDetail
   * @param skill
   * @param startDate
   * @param endDate
   * @param curPos
   * @param opParams
   */

  public void updateExperience(String organization,
                               String jobTitle,
                               String jobDetail,
                               String skill,
                               String startDate,
                               String endDate,
                               Boolean curPos,
                               Object... opParams) {
    String index = (String) (opParams.length > 0 ? opParams[0] : "0");
    Integer xpathCount = testBase.getElements(ELEMENT_EXPERIENCE_LIST).size();
    if (Integer.valueOf(index) >= xpathCount) {
      $(ELEMENT_ADD_MORE_EXP_ICON).click();
    }
    info("-- update experience --");
    if (organization != null && organization != "") {
      $(byXpath(ELEMENT_EXPERIENCE_COMPANY_INPUT.replace("${index}", index))).setValue(organization);
    }
    if (jobTitle != null && jobTitle != "") {
      $(byXpath(ELEMENT_EXPERIENCE_POSITION_INPUT.replace("${index}", index))).setValue(jobTitle);
    }
    if (jobDetail != null && jobDetail != "") {
      $(byXpath(ELEMENT_EXPERIENCE_DESCRIPTION_INPUT.replace("${index}", index))).setValue(jobDetail);
    }
    if (skill != null && skill != "") {
      $(byXpath(ELEMENT_EXPERIENCE_SKILL_INPUT.replace("${index}", index))).setValue(skill);
    }
    if (startDate != null && startDate != "") {
      $(byXpath(ELEMENT_EXPERIENCE_START_DATE_INPUT.replace("${index}", index))).setValue(startDate);
    }
    if (endDate != null && endDate != "") {
      $(byXpath(ELEMENT_EXPERIENCE_END_DATE_INPUT.replace("${index}", index))).setValue(endDate);
    }
    if (curPos != null && curPos) {
      evt.check(byXpath(ELEMENT_EXPERIENCE_CURRENT_CHECKBOX.replace("${index}", index)));
    } else {
      evt.uncheck(byXpath(ELEMENT_EXPERIENCE_CURRENT_CHECKBOX.replace("${index}", index)));
    }
  }

  /**
   * Save or cancle update info
   *
   * @param isSave null or true: save updating false: cancel
   */
  public void saveCancelUpdateInfo(Boolean isSave) {
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());
    if (isSave == null || isSave) {
      info("Save updating information");
      $(ELEMENT_CONTACT_SAVE_BUTTON).click();
      $(ELEMENT_CONTACT_SAVE_BUTTON).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
    } else {
      info("Cancel updating information");
      evt.clickByJavascript(ELEMENT_CONTACT_CANCEL_BUTTON, 2);
    }
  }

  /**
   * Open Activity tab
   */
  public void goToActivity() {
    info("Click on Activity tab");
    $(ELEMETN_ACTIVITY_TAB).click();
  }

  /**
   * Connect in profile page
   *
   * @param user
   */
  public void connectToUserInProfilePage(String user) {
    info("connect to: " + user);
    testBase.getExoWebDriver().getWebDriver().get(testBase.getExoWebDriver().getBaseUrl() + "/intranet/profile/" + user);
    evt.click(ELEMENT_UIMINICONNECTIONS_PORLET_CONNECT_STATUS, 0, true);
    evt.waitForAndGetElement(ELEMENT_UIMINICONNECTIONS_PORLET_CANCEL_STATUS);
    evt.waitForElementNotPresent(ELEMENT_UIMINICONNECTIONS_PORLET_CONNECT_STATUS);
  }

  /**
   * Disconnect in profile page
   *
   * @param user
   */
  public void disconnectInProfilePage(String user) {
    info("disconnect: " + user);
    testBase.getExoWebDriver().getWebDriver().get(testBase.getExoWebDriver().getBaseUrl() + "/intranet/profile/" + user);
    evt.mouseOver(ELEMENT_UIMINICONNECTIONS_PORLET_CONNECTED_STATUS, true);
    evt.waitForAndGetElement(ELEMENT_UIMINICONNECTIONS_PORLET_DISCONNECTED_STATUS).click();
    evt.waitForAndGetElement(ELEMENT_UIMINICONNECTIONS_PORLET_CONNECT_STATUS);
    evt.waitForElementNotPresent(ELEMENT_UIMINICONNECTIONS_PORLET_CONNECTED_STATUS);
  }
}