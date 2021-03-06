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

import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.error;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Utils {

  /**
   * Capture the screen of the current graphics device
   *
   * @param fileName input an image name (String)
   */
  public static void captureScreen(String fileName) {
    String path;
    BufferedImage screenCapture;
    try {
      Robot robot = new Robot();
      Rectangle screenSize = getScreenSize();
      screenCapture = robot.createScreenCapture(screenSize);
      // Save as PNG
      String curDir = System.getProperty("user.dir");
      path = curDir + "/target/screenshoot/";
      File f = new File(path);
      if (!f.exists())
        f.mkdir();
      ImageIO.write(screenCapture, "png", new File(path + fileName));

    } catch (AWTException e) {
      error("Failed to capture screenshot");
    } catch (IOException e) {
      path = "Failed to capture screenshot: " + e.getMessage();
    }
  }

  /**
   * the size of the default screen
   *
   * @return the size of the default screen
   */
  public static Rectangle getScreenSize() {
    GraphicsEnvironment graphE = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice graphD = graphE.getDefaultScreenDevice();
    Window displayM = graphD.getFullScreenWindow();
    if (displayM != null)
      return new Rectangle(displayM.getWidth(), displayM.getHeight());
    else
      return new Rectangle(1000, 1000);
  }

  /**
   * get random string
   *
   * @return random string
   */
  public static String getRandomString() {
    char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 6; i++) {
      char c = chars[random.nextInt(chars.length)];
      sb.append(c);
    }
    return sb.toString();
  }

  /**
   * get a list of random numbers
   *
   * @return random numbers
   */
  public static String getRandomNumber() {
    char[] chars = "0123456789".toCharArray();
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 6; i++) {
      char c = chars[random.nextInt(chars.length)];
      sb.append(c);
    }
    return sb.toString();
  }
}
