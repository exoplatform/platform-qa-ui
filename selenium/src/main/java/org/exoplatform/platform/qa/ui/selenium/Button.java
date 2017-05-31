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

import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.openqa.selenium.By;

/**
 * This class provides simple way to manage all type of buttons in PLF4
 */
public class Button {

  public final By ELEMENT_SAVE_CLOSE_BUTTON = By.xpath("//*[text()='Save & Close']");

  public final By ELEMENT_SAVE_CLOSE_BUTTON_2 = By.xpath("//*[text()='Save And Close']");

  public final By ELEMENT_OK_BUTTON = By.xpath("//*[text()='OK']");

  public final By ELEMENT_OK_BUTTON_LINK = By.linkText("OK");

  public final By ELEMENT_NO_BUTTON = By.xpath("//*[text()='No']");

  public final By ELEMENT_NO_BUTTON_AUX =
          By.xpath("//*[contains(@class, 'popup')]//*[contains(text(),'No')]");

  public final By ELEMENT_YES_BUTTON = By.xpath("//*[normalize-space(text())='Yes']");

  public final By ELEMENT_YES_BUTTON_AUX =
          By.xpath("//*[contains(@class, 'popup')]//*[contains(text(),'Yes')]");

  public final By ELEMENT_APPLY_FRENCH_BUTTON = By.linkText("Appliquer");

  public final By ELEMENT_APPLY_GERMAN_BUTTON = By.linkText("Anwenden");

  public final By ELEMENT_APPLY_BUTTON = By.xpath("//*[text()='Apply']");

  public final By ELEMENT_DELETE_BUTTON = By.xpath("//*[text()='Delete']");

  public final By ELEMENT_SAVE_BUTTON = By.xpath("//*[text()='Save']");

  public final By ELEMENT_SAVE_BUTTON_POPUP_WINDOWS = By.xpath("//*[contains(@class, 'popup')]//*[text()='Save']");

  public final By ELEMENT_CANCEL_BUTTON = By.xpath("//*[contains(text(),'Cancel')]");

  public final By ELEMENT_CANCEL_BUTTON_AUX = By.xpath("//*[@data-original-title='Cancel']");

  public final By ELEMENT_CLOSE_BUTTON = By.xpath("//*[normalize-space(text())='Close']");

  public final By ELEMENT_ADD_BUTTON = By.xpath("//*[text()='Add']");

  public final By ELEMENT_ADD_BUTTON_DISABLED =
          By.xpath("//*[@id='PermissionTab']//button[@disabled='disabled']");

  public final By ELEMENT_CATEGORY_CANCEL_BUTTON =
          By.xpath("//*[@id='UICategoryForm']//button[contains(text(),'Cancel')]");

  public final By ELEMENT_SELECT_BUTTON = By.xpath("//*[text()='Select']");

  public final By ELEMENT_CONFIRM_BUTTON = By.xpath("//*[text()='Confirm']");

  public final By ELEMENT_CLOSE_WINDOW =
          By.xpath("//*[contains(@class, 'uiIconClose') and @title = 'Close Window']");

  public final By ELEMENT_MOVE_BUTTON = By.xpath("//button[contains(text(), 'Move')]");

  public final By ELEMENT_FINISH_ICON = By.xpath("//a[@title='Finish']");

  public final By ELEMENT_NEXT_BUTTON = By.xpath("//*[text()='Next']");

  public final By ELEMENT_ABORT_BUTTON = By.xpath("//*[text()='Abort']");

  public final By ELEMENT_RESET_BUTTON = By.xpath("//*[text()='Reset']");

  public final By ELEMENT_PREVIOUS_BUTTON = By.xpath("//*[text()='Previous']");

  public final By ELEMENT_CREATE_LINK_BUTTON = By.xpath("//*[text()='Create Link']");

  public final By ELEMENT_CREATE_MACRO_BUTTON = By.xpath("//button[text()='Insert Macro']");

  public final By ELEMENT_APPLY_MACRO_BUTTON = By.xpath("//button[text()='Apply']");

  public final By ELEMENT_INSERT_TABLE = By.xpath("//button[text()='Insert Table']");

  public final By ELEMENT_SETTING_LINK_BUTTON = By.xpath("//*[text()='Link Settings']");

  // ECMS Admin > Category > Add Category
  public final By ELEMENT_PREVIOUS_BUTTON_ADMIN_4 =
          By.xpath("//*[@class='UITaxonomyTreeCreateChild']//*[text()='Previous']");

  public final By ELEMENT_PREVIOUS_BUTTON_ADMIN_3 =
          By.xpath("//*[@class='UIActionTaxonomyManager']//*[text()='Previous']");

  public final By ELEMENT_RESTORE_BUTTON = By.xpath("//*[text()='Restore']");

  // Content explorer
  public final By ELEMENT_REFRESH_BUTTON = By.xpath("//*[text()='refresh']");

  public final By ELEMENT_RENAME_BUTTON = By.xpath("//*[text()='Rename']");

  public final By ELEMENT_NEXT_PAGE_BUTTON = By.xpath("//*[(@data-original-title or @title)='Next Page']");

  private final TestBase testBase;

  private ElementEventTestBase evt;

  public Button(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Click button confirm
   */
  public void confirm() {
    evt.waitForAndGetElement(ELEMENT_CONFIRM_BUTTON);
    evt.click(ELEMENT_CONFIRM_BUTTON);

  }

  /**
   * Click button save
   */
  public void save() {
    evt.waitForAndGetElement(ELEMENT_SAVE_BUTTON);
    evt.click(ELEMENT_SAVE_BUTTON);

  }

  /**
   * Click button ok
   */
  public void ok() {
    if (evt.waitForAndGetElement(ELEMENT_OK_BUTTON, 5000, 0) != null) {
      evt.click(ELEMENT_OK_BUTTON);
    } else if (evt.waitForAndGetElement(ELEMENT_OK_BUTTON_LINK, 5000, 0) != null) {
      evt.click(ELEMENT_OK_BUTTON_LINK);
    }

  }

  /**
   * Click button no
   */
  public void no() {
    evt.waitForAndGetElement(ELEMENT_NO_BUTTON);
    evt.click(ELEMENT_NO_BUTTON);

  }

  /**
   * Click button yes
   */
  public void yes() {
    if (evt.waitForAndGetElement(ELEMENT_YES_BUTTON, 3000, 0) != null) {
      evt.clickByJavascript(ELEMENT_YES_BUTTON);
    } else if (evt.waitForAndGetElement(ELEMENT_YES_BUTTON_AUX, 3000, 0) != null) {
      evt.clickByJavascript(ELEMENT_YES_BUTTON_AUX);
    }

  }

  /**
   * Click button close
   */
  public void close() {
    evt.waitForAndGetElement(ELEMENT_CLOSE_BUTTON);
    evt.click(ELEMENT_CLOSE_BUTTON);

  }

  /**
   * Click button cancel
   */
  public void cancel() {
    if (evt.waitForAndGetElement(ELEMENT_CANCEL_BUTTON, 3000, 0) != null) {
      evt.click(ELEMENT_CANCEL_BUTTON);
    } else if (evt.waitForAndGetElement(ELEMENT_CANCEL_BUTTON_AUX, 3000, 0) != null) {
      evt.click(ELEMENT_CANCEL_BUTTON_AUX);
    }
  }

  /**
   * Click button add
   */
  public void add() {
    evt.waitForAndGetElement(ELEMENT_ADD_BUTTON);
    evt.click(ELEMENT_ADD_BUTTON);
    evt.waitForElementNotPresent(ELEMENT_ADD_BUTTON);

  }

  /**
   * Click button Save and Close
   */
  public void saveAndClose() {
    if (evt.waitForAndGetElement(ELEMENT_SAVE_CLOSE_BUTTON, 3000, 0) != null) {
      evt.click(ELEMENT_SAVE_CLOSE_BUTTON);
    } else if (evt.waitForAndGetElement(ELEMENT_SAVE_CLOSE_BUTTON_2, 3000, 0) != null) {
      evt.click(ELEMENT_SAVE_CLOSE_BUTTON_2);
    }

  }

  /**
   * Click btton apply
   */
  public void apply() {
    evt.waitForAndGetElement(ELEMENT_APPLY_BUTTON);
    evt.click(ELEMENT_APPLY_BUTTON);
  }

  /**
   * Click button next
   */
  public void next() {
    evt.waitForAndGetElement(ELEMENT_NEXT_BUTTON);
    evt.click(ELEMENT_NEXT_BUTTON);

  }

  /**
   * Click button refresh
   */
  public void refresh() {
    evt.waitForAndGetElement(ELEMENT_REFRESH_BUTTON);
    evt.click(ELEMENT_REFRESH_BUTTON);

  }

  /**
   * Click button rename
   */
  public void rename() {
    evt.waitForAndGetElement(ELEMENT_RENAME_BUTTON);
    evt.click(ELEMENT_RENAME_BUTTON);

  }

  /**
   * click button previous
   */
  public void previous() {
    evt.waitForAndGetElement(ELEMENT_PREVIOUS_BUTTON);
    evt.click(ELEMENT_PREVIOUS_BUTTON);

  }

  /**
   * Click button closeWinow
   */
  public void closeWindow() {
    if (evt.isElementPresent(ELEMENT_CLOSE_WINDOW)) {
      evt.click(ELEMENT_CLOSE_WINDOW);
    } else if (evt.isElementPresent(By.xpath("//*[contains(@class, 'wikiPreviewHeader')]//*[contains(@class, 'uiIconClose')]"))) {
      evt.click(By.xpath("//*[contains(@class, 'wikiPreviewHeader')]//*[contains(@class, 'uiIconClose')]"));
    }

  }

}
