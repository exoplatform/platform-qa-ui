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

  public static final By ELEMENT_SIGN_IN_BUTTON                   = By.xpath("//*[@class='loginButton']/*");

  public static final By ELEMENT_DISABLE_USER_ERROR_MES           =
                                                        By.xpath("//*[@class='signinFail'][contains(.,'This user account has been disabled. If you think this is an error, please contact the administrator.')]");

  public static final By ELEMENT_SIGN_IN_FACEBOOK_BUTTON          = By.xpath(".//*[@id='login-FACEBOOK']/i");

  public static final By ELEMENT_SIGN_IN_TWITTER_BUTTON           = By.xpath(".//*[@id='login-TWITTER']/i");

  public static final By ELEMENT_SIGN_IN_LINKEDIN_BUTTON          = By.xpath(".//*[@id='login-LINKEDIN']/i");

  public static final By ELEMENT_SIGN_IN_GOOGLE_BUTTON            = By.xpath(".//*[@id='login-GOOGLE']/i");

  // Facebook login form
  public static final By ELEMENT_FACEBOOK_LOGIN_FORM_LOGO         = By.xpath(".//*[@href='https://www.facebook.com/']");

  public static final By ELEMENT_FACEBOOK_LOGIN_FORM_USERNAME     = By.xpath(".//*[@id='email']");

  public static final By ELEMENT_FACEBOOK_LOGIN_FORM_PASSWORD     = By.xpath(".//*[@id='pass']");

  public static final By ELEMENT_FACEBOOK_LOGIN_FORM_LOGIN_BUTTON = By.xpath(".//*[@id='loginbutton']");

  // Twitter login form
  public static final By ELEMENT_TWITTER_LOGIN_FORM_LOGO          = By.xpath(".//*[@href='https://twitter.com/home']");

  public static final By ELEMENT_TWITTER_LOGIN_FORM_USERNAME      = By.xpath(".//*[@id='oauth_form']//*[contains(text(), 'Username or email')]");

  public static final By ELEMENT_TWITTER_LOGIN_FORM_PASSWORD      = By.xpath(".//*[@id='oauth_form']//*[contains(text(), 'Password')]");

  public static final By ELEMENT_TWITTER_LOGIN_FORM_LOGIN_BUTTON  = By.xpath(".//*[@id='allow']");

  // Linkedin login form
  public static final By ELEMENT_LINKEDIN_LOGIN_FORM_LOGO         = By.xpath(".//*[@class='logo' and text()='LinkedIn']");

  public static final By ELEMENT_LINKEDIN_LOGIN_FORM_USERNAME     = By.xpath(".//*[@id='session_key-oauthAuthorizeForm' and @placeholder='Email']");

  public static final By ELEMENT_LINKEDIN_LOGIN_FORM_PASSWORD     = By.xpath(".//*[@id='session_password-oauthAuthorizeForm' and @placeholder='Password']");

  public static final By ELEMENT_LINKEDIN_LOGIN_FORM_LOGIN_BUTTON = By.xpath(".//input[@value='Allow access' and @name='authorize']");

  // Google login form
  public static final By ELEMENT_GOOGLE_LOGIN_FORM_LOGO           = By.xpath("//*[contains(text(),'Sign in with your Google Account')]");

  public static final By ELEMENT_GOOGLE_LOGIN_FORM_USERNAME       = By.xpath(".//*[@id='Email']");

  public static final By ELEMENT_GOOGLE_LOGIN_FORM_PASSWORD       = By.xpath(".//*[@id='Passwd' and @placeholder='Password']");

  public static final By ELEMENT_GOOGLE_LOGIN_FORM_NEXT_BUTTON    = By.xpath(".//*[@id='next']");

  public static final By ELEMENT_GOOGLE_LOGIN_FORM_LOGIN_BUTTON   = By.xpath(".//*[@id='signIn']");

}
