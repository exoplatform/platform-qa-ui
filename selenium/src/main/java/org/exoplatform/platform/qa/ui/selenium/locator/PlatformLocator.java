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

/**
 * Created by mgreau on 14/02/2017.
 */
public final class PlatformLocator {

  // Gmail
  public static final String GMAIL_URL                            = "https://mail.google.com";

  public static final String EMAIL_ADDRESS1                       = "exomailtest01@gmail.com";

  public static final String EMAIL_ADDRESS2                       = "fqaexovn@gmail.com";

  public static final String EMAIL_PASS                           = "exoadmin1";

  public static final String ELEMENT_MAIL_SUBJECT                 = ".//span[contains(.,'${subject}')]";

  public static final By     ELEMENT_DELETE_MAIL                  =
                                                 By.xpath("//*[@id=':ro']/div[2]//*[@class='ar9 T-I-J3 J-J5-Ji']");

  public static final By     ELEMENT_DELETE_MAIL_2                =
                                                   By.xpath(".//*[@class='iH']//*[@data-tooltip='Delete']//*[@class='ar9 T-I-J3 J-J5-Ji']");

  public static final By     ELEMENT_GMAIL_INBOX                  = By.xpath("//a[contains(@title, 'Inbox')]");

  public static final By     ELEMENT_MAIL_CONTENT                 = By.xpath("//*[contains(@class, 'adP adO')]/div");

  public static final By     ELEMENT_GMAIL_USERNAME               = By.id("Email");

  public static final By     ELEMENT_GMAIL_NEXT_BTN               = By.id("next");

  public static final By     ELEMENT_GMAIL_PASS                   = By.id("Passwd");

  public static final By     ELEMENT_GMAIL_SIGN_IN                = By.id("signIn");

  public static final String ELEMENT_GMAIL_TITLE                  =
                                                 "//td/div[@class='xS']//div[@class='xT']//span/b[contains(text(),'{$title}')]";

  public static final By     ELEMENT_GMAIL_COMPOSE                = By.xpath("//div[contains(text(),'COMPOSE')]");

  public static final By     ELEMENT_GMAIL_SHOW_DETAIL            = By.xpath("//img[@aria-label='Show details']");

  public static final String ELEMENT_GMAIL_TO_FIELD               = "//td/span[text()='to:']/../..//span[text()='${to}']";

  public static final By     ELEMENT_GMAIL_SIGNIN_DIFFERENT_ACC   = By.cssSelector(".gb_d.gbii");

  public static final By     ELEMENT_GMAIL_ADD_ACCOUNT            = By.linkText("Add account");

  public static final By     ELEMENT_FIRST_MAIL                   = By.xpath("//tr[1]//span[contains(text(),'Hi')]");

  public static final String ELEMENT_GMAIL_CONTENT                =
                                                   ".//span[contains(.,'\"${title}\" page was modified') or contains(.,'${title}')]";

  public static final By     ELEMENT_GMAIL_SIGN_IN_LINK           =
                                                        By.xpath("//a[@id='gmail-sign-in' and contains(text(),'Sign in')]");

  // page navigation
  public static final By     ELEMENT_NEXT_PAGE                    = By.xpath("//*[@class='uiIconNextArrow']");

  public static final By     ELEMENT_PREVIOUS_PAGE                = By.xpath("//*[@class='uiIconPrevArrow']");

  public static final By     ELEMENT_TOTAL_PAGE                   = By.xpath("//*[@class='pagesTotalNumber']");

  public static final By     ELEMENT_CURRENT_PAGE                 =
                                                  By.xpath("//*[@class='active']/*[contains(@href,'objectId') or contains(@href,'javascript')]");

  public static final String ELEMENT_ANY_PAGE                     = "//*[contains(@href,'ShowPage') and text()='$page']";

  public static final String ELEMENT_PAGINATOR_PAGE_LINK          =
                                                         "//*[@class='pagination uiPageIterator clearfix']//a[contains(Text(),'${number}')]";

  // frame
  public static final By     ELEMENT_FILEFORM_BLANK_CONTENT       = By.xpath("(//iframe[@class='cke_wysiwyg_frame cke_reset'])[1]");

  public static final By     ELEMENT_FILEFORM_BLANK_NAME          = By.id("name");

  // Email notification
  public static final By     ELEMENT_GMAIL_PREVIOUS_EMAIL         = By.xpath(".//*[@class='gE hI']");

  public static final String ELEMENT_GMAIL_CONTENT_LINK_WIKI      = ".//a[contains(@href,'${page}')]";

  // User list popup
  public static final String ELEMENT_USER_CHECKBOX                = "//*[text()='$user']/../..//*[@type='checkbox']";

  public static final By     ELEMENT_SEARCH_USER_INPUT            = By.xpath("//*[@name='Quick Search']");

  public static final By     ELEMENT_QUICK_SEARCH_BUTTON          = By.xpath("//a[@data-original-title='Quick Search']");

  public static final By     ELEMENT_SELECT_SEARCH                = By.name("filter");

  public static final By     ELEMENT_ADD_USERS_BUTTON             = By.xpath("//*[@id='UIUserSelector']//*[text()='Add']");

  // Membership popup
  public static final String ELEMENT_GROUP_MEMBERSHIP_NAME_SELECT =
                                                                  ".//*[@id='UIGroupMembershipSelector']//*[contains(text(),'$groupName')]";

  public static final By     ELEMENT_MEMBERSHIP_POPUP             = By.xpath(".//*[@id='UIGroupMembershipSelector']");

  // Group popup
  public static final String ELEMENT_GROUP_NAME                   = ".//*[@id='UIGroupSelector']//*[contains(text(),'$group')]";

  public static final By     ELEMENT_SELECT_THIS_GROUP            =
                                                       By.xpath(".//*[@id='UIGroupSelector']//*[contains(text(),'Select this Group')]");

  public static final By     ELEMENT_SELECT_GROUP_POPUP           = By.xpath(".//*[@id='UIGroupSelector']");

  // Social NETWORK account
  public static final String SOCIAL_NETWORKS_ACCOUNT_FACEBOOK     = "exomailtest01@gmail.com";

  public static final String SOCIAL_NETWORKS_ACCOUNT_TWITTER      = "exofqatwitter@gmail.com";

  public static final String SOCIAL_NETWORKS_ACCOUNT_LINKEDIN     = "exofqalinkedin@gmail.com";

  public static final String SOCIAL_NETWORKS_ACCOUNT_GOOGLE       = "exofqagplus@gmail.com";

  public static final String SOCIAL_NETWORKS_ACCOUNT_SUSPENDED    = "exosuspend@gmail.com";

  public static final String SOCIAL_NETWORKS_PD                   = "exoadmin1";

  public static final    By  ELEMENT_SOURCE_CONTENT          = By.className("cke_button_label");
  public static final    By  ELEMENT_CONTENT          = By.className("cke_source ");
  public static final    By  ELEMENT_WEB_CONTENT          = By.id("UITabContent");


}
