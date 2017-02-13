/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - Core.
 *
 * eXo PLF:: QA - UI - Core is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - Core software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - Core; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.core.pageobject;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

/**
 * Created by mgreau on 23/01/2017.
 */
public class RegisterSoftware {

  /** This Element is here only for the Registration Software Page */
  public static SelenideElement element = $(By.className("plf-registration"));

  public RegisterSoftware() {

  }

  /**
   * Skip the Software Registration screen.
   * 
   * @return
   */
  public RegisterSoftware skip() {

    // Click on the label because the checkbox is not visible
    // '<input class="checkbox" id="agreement" name="checktc"
    // onclick="toggleState();" type="checkbox" value="false"
    // displayed:false></input>'

    $(By.name("btnSkip")).click();
    $(By.name("setupbutton")).click();

    return this;
  }

}
