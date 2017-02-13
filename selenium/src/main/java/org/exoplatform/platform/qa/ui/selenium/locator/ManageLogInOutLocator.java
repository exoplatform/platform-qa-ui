/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - Selenium (Legacy Code).
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - Selenium (Legacy Code); if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.selenium.locator;

import org.openqa.selenium.By;

public final class ManageLogInOutLocator {

  public static final By ELEMENT_SIGN_IN_BUTTON = By.xpath("//*[@class='loginButton']/*");

  public static final By ELEMENT_SIGN_OUT_LINK = By.className("uiIconPLFLogout");

  public static final By ELEMENT_DISABLE_USER_ERROR_MES =
          By.xpath(
                  "//*[@class='signinFail'][contains(.,'This user account has been disabled. If you think this is an error, please contact the administrator.')]");

  public static final By ELEMENT_SIGN_IN_FACEBOOK_BUTTON = By.xpath(".//*[@id='login-FACEBOOK']/i");

  public static final By ELEMENT_SIGN_IN_TWITTER_BUTTON = By.xpath(".//*[@id='login-TWITTER']/i");

  public static final By ELEMENT_SIGN_IN_LINKEDIN_BUTTON = By.xpath(".//*[@id='login-LINKEDIN']/i");

  public static final By ELEMENT_SIGN_IN_GOOGLE_BUTTON = By.xpath(".//*[@id='login-GOOGLE']/i");

  public static final By ELEMENT_ACME_LOGIN_LINK = By.xpath(".//*[@id='AcmeWebSiteLogInLogOut']");

  public static final By ELEMENT_ACME_REGISTER_LINK = By.xpath(".//*[@id='AcmeWebSiteRegister']/a");

  public static final By ELEMENT_ACME_REGISTER_NEW_ACCOUNT =
          By.xpath(".//*[@class = 'title' and contains(text(),'Register New Account' )]");

  public static final By ELEMENT_ACME_REGISTER_NEW_ACCOUNT_FACEBOOK =
          By.xpath(".//*[@class='register-button register-FACEBOOK']");

  public static final By ELEMENT_ACME_SIGN_IN_BUTTON =
          By.xpath(".//*[@id='UIPortalComponentLogin']/div[3]/input[1]");

  public static final String
          ELEMENT_USER_STATUS_SWITCHON_BTN =
          ".//*[@id='UIListUsersGird']//*[@title='${userName}']/../..//*[@class='switchBtnLabelOn']";

  public static final By ELEMENT_ACCOUNT_LINK_TO_SOCIAL_FACEBOOK =
          By.xpath(".//*[contains(text(), 'Facebook User Name')]/..//*[contains(text(), 'Link social account')]");

  public static final By ELEMENT_ACCOUNT_LINK_TO_SOCIAL_GOOGLE =
          By.xpath(".//*[contains(text(), 'Google+ User Name')]/..//*[contains(text(), 'Link social account')]");

  public static final By ELEMENT_ACCOUNT_LINK_TO_SOCIAL_LINKEDIN =
          By.xpath(".//*[contains(text(), 'LinkedIn User Name')]/..//*[contains(text(), 'Link social account')]");

  public static final String
          ELEMENT_ACCOUNT_LINK_TO_SOCIAL_INFO =
          ".//*[contains(text(), 'Social network \"$network}\" connected for user \"$account}\"')]";

  // unlink
  public static final By ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_TWITTER =
          By.xpath("//*[contains(text(), 'Twitter User Name')]/..//*[contains(text(), 'Unlink social account')]");

  public static final By ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_GOOGLE =
          By.xpath("//*[contains(text(), 'Google+ User Name')]/..//*[contains(text(), 'Unlink social account')]");

  public static final By ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_FACEBOOK =
          By.xpath("//*[contains(text(), 'Facebook User Name')]/..//*[contains(text(), 'Unlink social account')]");

  public static final By ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_LINKEDIN =
          By.xpath("//*[contains(text(), 'LinkedIn User Name')]/..//*[contains(text(), 'Unlink social account')]");

  // Facebook login form
  public static final By ELEMENT_FACEBOOK_LOGIN_FORM_LOGO =
          By.xpath(".//*[@href='https://www.facebook.com/']");

  public static final By ELEMENT_FACEBOOK_LOGIN_FORM_USERNAME = By.xpath(".//*[@id='email']");

  public static final By ELEMENT_FACEBOOK_LOGIN_FORM_PASSWORD = By.xpath(".//*[@id='pass']");

  public static final By ELEMENT_FACEBOOK_LOGIN_FORM_LOGIN_BUTTON = By.xpath(".//*[@id='loginbutton']");

  public static final By ELEMENT_FACEBOOK_LOGIN_FORM_CONTINUE_BUTTON =
          By.xpath(".//*[@id='platformDialogForm']//*[text()='Ok']");

  public static final By ELEMENT_FACEBOOK_LOGIN_FORM_CANCEL_BUTTON =
          By.xpath(".//*[@id='platformDialogForm']/div[2]/table/tbody/tr/td[2]/button[1]");

  public static final By ELEMENT_FACEBOOK_LOGIN_FORM_ACCEPT_BUTTON =
          By.xpath(".//*[@id='platformDialogForm']/div[2]/table/tbody/tr/td[2]/button[2]");

  // Twitter login form
  public static final By ELEMENT_TWITTER_LOGIN_FORM_LOGO =
          By.xpath(".//*[@href='https://twitter.com/home']");

  public static final By ELEMENT_TWITTER_LOGIN_FORM_USERNAME =
          By.xpath(".//*[@id='oauth_form']//*[contains(text(), 'Username or email')]");

  public static final By ELEMENT_TWITTER_LOGIN_FORM_PASSWORD =
          By.xpath(".//*[@id='oauth_form']//*[contains(text(), 'Password')]");

  public static final By ELEMENT_TWITTER_LOGIN_FORM_LOGIN_BUTTON = By.xpath(".//*[@id='allow']");

  public static final By ELEMENT_TWITTER_LOGIN_FORM_CANCEL_BUTTON = By.xpath(".//*[@id='cancel']");

  // Linkedin login form
  public static final By ELEMENT_LINKEDIN_LOGIN_FORM_LOGO =
          By.xpath(".//*[@class='logo' and text()='LinkedIn']");

  public static final By ELEMENT_LINKEDIN_LOGIN_FORM_USERNAME =
          By.xpath(".//*[@id='session_key-oauthAuthorizeForm' and @placeholder='Email']");

  public static final By ELEMENT_LINKEDIN_LOGIN_FORM_PASSWORD =
          By.xpath(".//*[@id='session_password-oauthAuthorizeForm' and @placeholder='Password']");

  public static final By ELEMENT_LINKEDIN_LOGIN_FORM_LOGIN_BUTTON =
          By.xpath(".//input[@value='Allow access' and @name='authorize']");

  // Google login form
  public static final By ELEMENT_GOOGLE_LOGIN_FORM_LOGO =
          By.xpath("//*[contains(text(),'Sign in with your Google Account')]");

  public static final By ELEMENT_GOOGLE_LOGIN_FORM_USERNAME = By.xpath(".//*[@id='Email']");

  public static final By ELEMENT_GOOGLE_LOGIN_FORM_PASSWORD =
          By.xpath(".//*[@id='Passwd' and @placeholder='Password']");

  public static final By ELEMENT_GOOGLE_LOGIN_FORM_NEXT_BUTTON = By.xpath(".//*[@id='next']");

  public static final By ELEMENT_GOOGLE_LOGIN_FORM_LOGIN_BUTTON = By.xpath(".//*[@id='signIn']");

  public static final By ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW = By.xpath(".//*[@id='submit_approve_access']");

  public static final By ELEMENT_GOOGLE_PERMISSION_FORM_DENY = By.xpath(".//*[@id='submit_deny_access']");

  // Register new account form
  public static final By ELEMENT_NEW_ACCOUNT_PASSWORD = By.xpath("//*[@name = 'password']");

  public static final By ELEMENT_NEW_ACCOUNT_PASSWORD_CONFIRM = By.xpath("//*[@name = 'password2']");

  public static final By ELEMENT_NEW_ACCOUNT_FIRST_NAME = By.xpath("//*[@name = 'firstName']");

  public static final By ELEMENT_NEW_ACCOUNT_LAST_NAME = By.xpath("//*[@name = 'lastName']");

  public static final By ELEMENT_NEW_ACCOUNT_DISPLAY_NAME = By.xpath("//*[@name = 'displayName']");

  public static final By ELEMENT_NEW_ACCOUNT_EMAIL = By.xpath("//*[@name = 'email']");

  public static final String ELEMENT_NEW_ACCOUNT_VALUE_USERNAME = "//*[@name = 'username' and @value='${value}']";

  public static final String ELEMENT_NEW_ACCOUNT_VALUE_FIRSTNAME = "//*[@name = 'firstName' and @value='${value}']";

  public static final String ELEMENT_NEW_ACCOUNT_VALUE_DISPLAYNAME =
          "//*[@name = 'displayName' and @value='${value}']";

  public static final String ELEMENT_NEW_ACCOUNT_VALUE_EMAIL = "//*[@name = 'email' and @value='${value}']";

  public static final By ELEMENT_NEW_ACCOUNT_POPUP =
          By.xpath("//*[@class='popupTitle center' and contains(text(), 'Register New Account')]");

  public static final By ELEMENT_NEW_ACCOUNT_SUBSCRIBE_AND_LOGIN_BUTTON =
          By.xpath(".//*[@id='UIPortalLoginFormAction']//*[contains(text(), 'Subscribe and Login')]");

  public static final By ELEMENT_DETECT_ACCOUNT_POPUP =
          By.xpath("//*[@class='PopupTitle popupTitle' and text() = 'Existing Account Detected']");

  public static final By ELEMENT_DETECT_ACCOUNT_CONFIRM =
          By.xpath("//*[@class='uiAction uiActionBorder']//*[contains(text(), 'Confirm')]");

  public static final By ELEMENT_DETECT_ACCOUNT_REGISTER =
          By.xpath("//*[@class='uiAction uiActionBorder']//*[contains(text(), 'Register New Account')]");

  public static final String
          ELEMENT_DETECT_ACCOUNT_ERROR_MESSAGE =
          "//*[@class='uiIconColorError']/../..//*[contains(text(), '${message}')]";

  public static final String
          ELEMENT_DETECT_ACCOUNT_MESSAGE =
          "//*[contains(text(),'We have detected that an eXo account already exists for')]//*[contains(text(),'${detectedName}')]/../..//*[contains(text(),'If you would like to use it, please enter your eXo password to confirm')]";

  public static final By ELEMENT_DETECT_ACCOUNT_CLOSE_BTN = By.xpath("//*[@class='uiIconClose pull-right']");

  // Account value
  public static final String ELEMENT_ACCOUNT_PROFILE_FIRST_NAME = ".//*[@id='firstName' and @value='${value}']";

  public static final String ELEMENT_ACCOUNT_PROFILE_LAST_NAME = ".//*[@id='lastName' and @value='${value}']";

  public static final String ELEMENT_ACCOUNT_PROFILE_DISPLAY_NAME = ".//*[@id='displayName' and @value='${value}']";

  public static final String ELEMENT_ACCOUNT_PROFILE_EMAIL = ".//*[@id='email' and @value='${value}']";

  public static final String ELEMENT_ACCOUNT_PROFILE_USERNAME = ".//*[@id='username' and @value='${value}']";

  public static final String ELEMENT_ACCOUNT_PROFILE_EMAIL_ACME = ".//*[@id='emailAddress' and @value='${value}']";

  public static final String ELEMENT_SOCIAL_ACCOUNT_LOGGEDIN = "//*[@title = 'Google Account: ${acount}']";

}
