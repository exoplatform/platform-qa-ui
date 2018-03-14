package org.exoplatform.platform.qa.ui.selenium.platform.social;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

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
    evt.click(ELEMENT_EDIT_MY_PROFILE_BUTTON);
    evt.waitForAndGetElement(ELEMENT_EDIT_PROFILE_FORM);
  }

  /**
   * Update About me section
   * 
   * @param pos
   */
  public void updateAboutMe(String pos) {
    info("Update About me");
    if (pos != "") {
      evt.type(ELEMENT_ABOUTME_TEXTAREA_EDIT, pos, true);
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
    if (evt.waitForAndGetElement(ELEMENT_EDIT_BASIC_INFORMATION, 5000, 0) != null) {
      evt.click(ELEMENT_EDIT_BASIC_INFORMATION);
    }
    if (firstName != "" && firstName != null) {
      info("update firstname");
      evt.type(ELEMENT_FIRST_NAME_TEXTBOX_EDIT, firstName, true);
    }
    if (lastName != "" && lastName != null) {
      info("update lastName");
      evt.type(ELEMENT_LAST_NAME_TEXTBOX_EDIT, lastName, true);
    }
    if (email != "" && email != null) {
      info("update email");
      evt.type(ELEMENT_EMAIL_TEXTBOX_EDIT, email, true);
    }
  }

  /**
   * Change avatar
   * 
   * @param linkfile : File path of new avatar
   */
  public void changeAvatar(String linkfile) {
    info("-- changeAvatar --");

    if (testBase.getBrowser().contains("iexplorer"))
      evt.clickByJavascript(ELEMENT_CHANGE_AVATAR_LINK);
    else
      evt.click(ELEMENT_CHANGE_AVATAR_LINK);
    evt.click(ELEMENT_SELECT_AVATAR);
    testBase.uploadFileUsingRobot(linkfile);
    evt.clickByJavascript(ELEMENT_CONFIRM);
    evt.waitForElementNotPresent(ELEMENT_CONFIRM);
    evt.clickByJavascript(ELEMENT_SAVE_AVATAR);

  }

  /**
   * Update information of contact of a user
   * 
   * @param gender
   * @param job
   */
  public void updateGenderJob(String gender, String job) {
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());
    if (evt.waitForAndGetElement(ELEMENT_CONTACT_EDIT_ICON, 5000, 0) != null)
      evt.click(ELEMENT_CONTACT_EDIT_ICON);
    if (gender != "" && gender != null) {
      info("update gender");
      evt.select(ELEMENT_CONTACT_GENDER_SELECTION, gender);
    }
    if (job != "" && job != null) {
      info("update job");
      evt.type(ELEMENT_CONTACT_JOB_TITLE, job, true);
    }

  }

  /**
   * Update ims
   * 
   * @param type
   * @param ims
   * @param opParams
   */
  public void updateIms(String type, String ims, Object... opParams) {
    info("Update ims");
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());
    String index = (String) (opParams.length > 0 ? opParams[0] : "0");
    Integer xpathCount = testBase.getElements(ELEMENT_CONTACT_IMS_INPUT_LIST).size();
    if (Integer.valueOf(index) >= xpathCount) {
      evt.click(ELEMENT_CONTACT_IMS_ADD_ICON);
    }
    if (type != null && !type.isEmpty()) {
      info("select type of ims");
      evt.select(ELEMENT_CONTACT_IMS_OPTION.replace("${index}", index), type);
    }
    if (ims != null && !ims.isEmpty()) {
      info("update ims " + ims);
      evt.type(ELEMENT_CONTACT_IMS_INPUT.replace("${index}", index), ims, true);
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
   * @param opParams
   */
  public void updatePhone(String type, String phone, Object... opParams) {
    info("Update phone");
    evt.scrollToBottomPage(this.testBase.getExoWebDriver().getWebDriver());
    String index = (String) (opParams.length > 0 ? opParams[0] : "1");
    Integer xpathCount = testBase.getElements(ELEMENT_CONTACT_PHONE_INPUT_LIST).size();
    if (Integer.valueOf(index) >= xpathCount) {
      evt.click(ELEMENT_CONTACT_PHONE_ADD_ICON);
    }
    if (type != null && !type.isEmpty()) {
      info("select type of phone");
      evt.select(ELEMENT_CONTACT_PHONE_OPTION.replace("${index}", index), type);
    }
    if (phone != null && !phone.isEmpty()) {
      info("update phone");
      evt.type(ELEMENT_CONTACT_PHONE_INPUT.replace("${index}", index), phone, true);
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

      evt.clickByJavascript(ELEMENT_CONTACT_SAVE_BUTTON, 2);

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
    evt.click(ELEMETN_ACTIVITY_TAB);

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
