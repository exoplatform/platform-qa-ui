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

public final class PlatformPermissionLocator {

  public static final By     ELEMENT_SELECT_USER_ICON1         = By.xpath("//*[contains(@class,'uiIconSelectUser')]");

  public static final By     ELEMENT_SELECT_GROUP_ICON         = By.xpath("//*[@class='uiIconGroup uiIconLightGray']");

  public static final By     ELEMENT_SELECT_MEMBERSHIP_ICON    = By.xpath("//*[@class='uiIconMembership uiIconLightGray']");

  // User permission
  public static final String ELEMENT_USER_CHECKBOX             = "//*[text()='${user}']/../..//*[@type='checkbox']";

  public static final String ELEMENT_USER_LIST                 = "//*[@id='UIListUsers']//*[contains(.,'${user}')]";

  public static final By     ELEMENT_ADD_USERS_BUTTON          = By.xpath("//*[@id='UIUserSelector']//*[text()='Add']");

  public static final By     ELEMENT_SEARCH_USER_INPUT         = By.id("Quick Search");

  public static final By     ELEMENT_QUICK_SEARCH_BUTTON       = By.xpath("//a[@data-original-title='Quick Search']/i");

  public static final By     ELEMENT_SELECT_SEARCH             = By.name("filter");

  public static final By     ELEMENT_USER_CLOSE_BUTTON         = By.xpath("//*[@id='UIUserSelector']//*[contains(@class,'btn')][contains(.,'Close')]");

  // Group permission
  public static final By     ELEMENT_SELECT_GROUP_POPUP        = By.id("UIPopupGroupMembershipSelector");

  public static final By     ELEMENT_SELECT_THIS_GROUP         = By.linkText("Select this Group");

  public static final String ELEMENT_SELECT_RIGHT_PARENT_GROUP = "//*[contains(@id,'UIGroupMember')]//a[contains(.,'$group')]";

  // Membership permission
  public static final By     ELEMENT_SELECT_MEMBERSHIP_POPUP   = By.xpath("//*[contains(@id,'UIGroupMember')]");

}
