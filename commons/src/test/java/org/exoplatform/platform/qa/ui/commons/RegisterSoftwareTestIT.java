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
package org.exoplatform.platform.qa.ui.commons;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.commons.pageobject.Platform;

public final class RegisterSoftwareTestIT extends Base {

  public RegisterSoftwareTestIT() {
    super();

  }

  /**
   * Skip Register Software. This test should be executed on a PLF instance where
   * this feature has not be skipped more than 3 times.
   */
  @Test
  @Tag("register")
  public void skipRegisterSoftware() {
    Platform platform = new Platform().open();
    assertTrue("Actions for Register Software not found, Be sure to have a fresh PLF installation.",
               platform.isNeededSoftwareRegistration());
    platform.ensureRegisterSoftwareIsSkipped();
    assertFalse("Actions for Register Software should not be found.", platform.isNeededSoftwareRegistration());
  }

}
