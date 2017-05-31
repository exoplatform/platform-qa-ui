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
package org.exoplatform.platform.qa.ui.selenium;

import org.exoplatform.platform.qa.ui.selenium.logger.Logger;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;

public class Dialog {

  // Dialog warning
  public final String
          ELEMENT_POPUP_WARNING =
          "//*[@class='UIPopupWindow UIDragObject uiPopup']//*[@class='warningIcon' and contains(text(),'${message}')]";

  // Close Message
  public final String ELEMENT_MESSAGE_TEXT =
          "//li[@class='MessageContainer']/span[contains(@class, 'PopupIcon')]";

  public final String ELEMENT_MESSAGE_DIALOG_CLOSE_ICON_IE = ELEMENT_MESSAGE_TEXT + "/../../../../../..//a";

  public final String
          ELEMENT_MESSAGE_DIALOG_CLOSE_ICON =
          "//div[contains(@class, 'UIPopupWindow') and contains(@style, 'visibility: visible')]//a[contains(@class, 'uiIconClose')]";

  // ECMS > Admin > Repository > Manage Lock Tabs
  public final String
          MESSAGE_UNLOCK_WITHOUT_PERMISSION =
          "You do not have permission to unlock this node. Please contact the administrator to get the correct right.";

  public final String MESSAGE_CANNOT_DELETE_ADMIN_GROUP = "Cannot delete the group *:/platform/administrators.";

  // ECMS > Delete a document
  public final By ELEMENT_DELETE_IN_DIALOG =
          By.xpath("//*[contains(@class, 'uiAction')]//*[text()='Delete']");

  public final By ELEMENT_CANCEL_IN_DIALOG =
          By.xpath("//*[contains(@class, 'uiAction')]//*[text()='Cancel']");

  private final TestBase testBase;

  public ManageAlert alt;

  private ElementEventTestBase evt;

  public Dialog(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alt = new ManageAlert(testBase);
  }

  /**
   * Close Message Dialog
   */
  public void closeMessageDialog() {
    Logger.info("--Closing message dialog--");
    if (testBase.ieFlag) {
     $(ELEMENT_MESSAGE_DIALOG_CLOSE_ICON_IE).click();
    } else {
      $(byTitle("Close Window")).click();
    }

  }

  /**
   * Click button Delete in Dialog
   */
  public void deleteInDialog() {
    evt.waitForAndGetElement(ELEMENT_DELETE_IN_DIALOG);
    evt.click(ELEMENT_DELETE_IN_DIALOG);

  }
}
