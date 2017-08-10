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
package org.exoplatform.platform.qa.ui.selenium.user;

import java.util.ArrayList;

public class UserDatabase {
  public ArrayList<Integer> type;

  public ArrayList<String>  userName;

  public ArrayList<String>  password;

  public ArrayList<String>  fullName;

  /**
   * UserDatabase
   *
   * @param type
   * @param userName
   * @param password
   * @param fullName
   */
  public UserDatabase(ArrayList<Integer> type,
                      ArrayList<String> userName,
                      ArrayList<String> password,
                      ArrayList<String> fullName) {
    this.type = type;
    this.userName = userName;
    this.password = password;
    this.fullName = fullName;
  }

  /**
   * UserDatabase
   */
  public UserDatabase() {
    type = new ArrayList<Integer>();
    userName = new ArrayList<String>();
    password = new ArrayList<String>();
    fullName = new ArrayList<String>();
  }

  /**
   * setUserData
   *
   * @param userDataFile
   * @param userSheet
   * @param opParams
   * @throws Exception
   */
  public void setUserData(String userDataFile, String userSheet, Object... opParams) throws Exception {
    // Mock (to update)
    String[][] testData = new String[][] { { "X0", "Y0" }, { "X1", "Y1" }, { "X2", "Y2" }, { "X3", "Y3" }, { "X4", "Y4" } };
    // DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
    for (int i = 0; i < testData.length; i++) {
      type.add(Integer.valueOf(testData[i][0]));
      userName.add(testData[i][1]);
      password.add(testData[i][2]);
      fullName.add(testData[i][3]);
    }
  }

  /**
   * Get Full name By Index
   *
   * @param index
   * @return fullName.get(index)
   */
  public String getFullNameByIndex(int index) {
    return fullName.get(index);
  }

  /**
   * Get User name By Index
   *
   * @param index
   * @return userName.get(index);
   */
  public String getUserNameByIndex(int index) {
    return userName.get(index);
  }
}
