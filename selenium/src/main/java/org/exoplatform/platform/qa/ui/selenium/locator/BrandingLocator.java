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

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public final class BrandingLocator {

  public static final By ELEMENT_UPLOAD_LINK                              = By.name("file");

  public static final By ELEMENT_PLF_BRANDINGPAGE                         =
                                                  By.xpath("//*[@class='uiBreadcumbsNavigations']//*[text()='Branding']");

  public static final By ELEMENT_BANDING_PAGE_SELECT_LOGO                 = By.xpath(".//h4[text()='Select Logo']");

  public static final By ELEMENT_BANDING_PAGE_SELECT_NAVIGATION_BAR_STYLE =
                                                                          By.xpath(".//h4[text()='Select Navigation Bar Style']");

  public static final By ELEMENT_BANDING_PAGE_PREVIEW                     = By.xpath(".//h4[text()='Preview']");

  // Theme selection
  public static final By ELEMENT_PLF_BRANDING_SELECTTHEME                 = By.xpath("//*[@class='btn dropdown-toggle']");

  public static final By ELEMENT_PLF_BRANDING_THEMEDARK                   =
                                                        By.xpath("//*[@class='OptionItem' and text()='Dark']");

  public static final By ELEMENT_PLF_BRANDING_THEMELIGHT                  =
                                                         By.xpath("//*[@class='OptionItem' and text()='Light']");

  // Displayed top bar
  public static final By ELEMENT_PLF_BRANDING_TOPBAR_THEMELIGHT           =
                                                                By.xpath("//*[@class='UIContainer UIToolbarContainer  UIToolbarContainerLight']");

  public static final By ELEMENT_PLF_BRANDING_TOPBAR_THEMEDARK            =
                                                               By.xpath("//*[@class='UIContainer UIToolbarContainer  UIToolbarContainerLight']");

  public static final By ELEMENT_PLF_BRANDING_TOPBAR_LOGO                 =
                                                          By.xpath("//*[@alt='Home' and contains(@src, 'logo_preview.png')]");

  // Button
  public static final By ELEMENT_BUTTON_SAVE                              = By.xpath("//*[text()='Save']");

  public static final By ELEMENT_BUTTON_CANCEL                            = By.xpath(".//*[@id='cancel']");

  public static final By ELEMENT_BUTTON_UPLOAD                            = By.xpath("//*[@id='btUpload']");

  public static final SelenideElement ELEMENT_BUTTON_APPLY =$(byId("save"));

  public static final SelenideElement ELEMENT_UPLOAD_LOGO=$(byId("btUpload")).parent().parent().find(byId("file"));

}
