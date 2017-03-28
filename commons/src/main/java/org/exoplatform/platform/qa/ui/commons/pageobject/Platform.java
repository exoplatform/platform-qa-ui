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
package org.exoplatform.platform.qa.ui.commons.pageobject;

import com.codeborne.selenide.Selenide;

/**
 * Created by mgreau on 23/01/2017.
 */
public class Platform {

  public static final String URI = "/portal";

  public void open() {

    Selenide.open(URI);
    return;

  }

  /**
   * It ensures that, if the license screen appears, the license agreement will
   * be accepted. If this screen doesn't appear, there is no exception.
   */
  public Platform ensureLicenseIsAccepted() {

    boolean alreadySkipped = false;
    try {
      alreadySkipped = !License.element.exists();
    } catch (Exception ex) {
      System.out.print("License skip exception " + ex.getStackTrace());
    }
    if (alreadySkipped == false) {
      System.out.print("Skip the License ");
      new License().accept();
    }


    return this;
  }

  /**
   * Ensure that the Register Software UI is skipped.
   */
  public Platform ensureRegisterSoftwareIsSkipped() {

    boolean alreadySkipped = false;
    try {
      alreadySkipped = !RegisterSoftware.element.exists();
    } catch (Exception ex) {
      System.out.print("RG skip exception " + ex.getStackTrace());
    }
    if (alreadySkipped == false) {
      System.out.print("Skip the UI ");
      new RegisterSoftware().skip();
    }

    return this;

  }

  /**
   * Ensure that the Account Setup UI is skipped.
   */
  public Platform ensureAccountSetupIsSkipped() {

    boolean alreadySkipped = false;
    try {
      alreadySkipped = !AccountSetup.element.exists();
    } catch (Exception ex) {
      System.out.print(" ");
    }
    if (alreadySkipped == false) {
      System.out.print("Skip the Account ");
      new AccountSetup().skip();
    }

    return this;
  }

  public Platform ensureUserIsLoggedIn(){

    boolean alreadyLogged = false;
    try {
      alreadyLogged = new Login().isUserLogged();
    } catch (Exception ex) {
      System.out.print(" ");
    }
    if (alreadyLogged == false) {
      System.out.print("Log in ");
      new Login().signIn();
    }

    return this;


  }

}
