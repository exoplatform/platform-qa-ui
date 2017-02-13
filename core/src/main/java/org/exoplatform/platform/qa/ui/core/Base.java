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
package org.exoplatform.platform.qa.ui.core;

import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.core.pageobject.Platform;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Base extends TestBase {

  public Base() {
    super.setDriver(getWebDriver());
  }

  @BeforeAll
  public static void setup() {

    final String ipAddress;
    try {
      ipAddress = getIpAddress();

      Configuration.browser = "chrome";
      Configuration.baseUrl = "http://" + ipAddress + ":" + getPLFHTTPPort();
      Configuration.holdBrowserOpen = true;
      //Configuration.remote = "http://" + ipAddress + ":" + getHubHTTPPort() + "/wd/hub";
    } catch (SocketException e) {
      e.printStackTrace();
    }

  }

  private static String getPLFHTTPPort() {
    String defaultPort = "80";

    return defaultPort;
  }

  private static String getHubHTTPPort() {
    String defaultPort = "4444";

    return defaultPort;
  }

  private static String getIpAddress() throws SocketException {
    String ipAddress = null;

    Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
    for (; n.hasMoreElements(); ) {
      NetworkInterface e = n.nextElement();

      Enumeration<InetAddress> a = e.getInetAddresses();
      for (; a.hasMoreElements(); ) {
        InetAddress addr = a.nextElement();
        //Find local address
        if (addr.getHostAddress().contains("192")) {
          ipAddress = addr.getHostAddress();
        }
      }
    }
    return ipAddress;
  }

  @BeforeEach
  public void openPlatform() {
    new Platform().open();
  }

}
