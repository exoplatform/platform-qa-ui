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

import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

@SuppressWarnings("deprecation")
public class DownloadFileControl {

  public static WebDriver driver;

  public DownloadFileControl(WebDriver driverObject) {
    driver = driverObject;
  }

  /**
   * Add cookies
   *
   * @return cookieStore
   */
  private static CookieStore seleniumCookiesToCookieStore() {

    Set<Cookie> seleniumCookies = driver.manage().getCookies();
    CookieStore cookieStore = new BasicCookieStore();

    for (Cookie seleniumCookie : seleniumCookies) {
      BasicClientCookie basicClientCookie = new BasicClientCookie(seleniumCookie.getName(), seleniumCookie.getValue());
      basicClientCookie.setDomain(seleniumCookie.getDomain());
      basicClientCookie.setExpiryDate(seleniumCookie.getExpiry());
      basicClientCookie.setPath(seleniumCookie.getPath());
      cookieStore.addCookie(basicClientCookie);
    }

    return cookieStore;
  }

  /**
   * Check download link
   *
   * @param downloadUrl
   * @throws Exception
   */
  public void downloadFile(String downloadUrl) throws Exception {

    CookieStore cookieStore = seleniumCookiesToCookieStore();
    HttpResponse response;
    int httpStatusCode;
    try (DefaultHttpClient httpClient = new DefaultHttpClient()) {
      httpClient.setCookieStore(cookieStore);

      HttpGet httpGet = new HttpGet(downloadUrl);
      System.out.println("Downloding file form: " + downloadUrl);
      response = httpClient.execute(httpGet);
    }
    httpStatusCode = response.getStatusLine().getStatusCode();
    System.out.println("httpStatusCode:" + httpStatusCode);

    if (httpStatusCode == 200) {
      assert true;
      System.out.println("Downloded successfully");
    } else {
      assert false;
      System.out.println("Download failed!");
    }

  }
}
