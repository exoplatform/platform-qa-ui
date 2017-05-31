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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.error;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class Utils {

  /**
   * Capture the screen of the current graphics device
   *
   * @param fileName input an image name (String)
   */
  public static void captureScreen(String fileName) {
    String path;
    BufferedImage screenCapture;
    // Thread.sleep(3000);
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
   * Simulating keyboard presses
   *
   * @param firstKey send the first key (type: KeyEvent)
   * @param params   send the second key (type: KeyEvent)
   */
  public static void javaSimulateKeyPress(int firstKey, Object... params) {
    int secondKey = (Integer) (params.length > 0 ? params[0] : KeyEvent.VK_ENTER);
    int thirdKey = (Integer) (params.length > 1 ? params[1] : KeyEvent.VK_ENTER);
    try {
      Robot robot = new Robot();
      // Simulate a key press
      robot.keyPress(firstKey);
      if (params.length > 0)
        robot.keyPress(secondKey);
      if (params.length > 1)
        robot.keyPress(thirdKey);
      if (params.length > 0)
        robot.keyRelease(secondKey);
      robot.keyRelease(firstKey);
      if (params.length > 1)
        robot.keyRelease(thirdKey);

    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

  /**
   * ipV4 of local machine
   *
   * @return ipV4 of local machine
   */
  public static String getIPOfLocal() {
    info("Get IP of localhost");
    String interName = "";
    Map<String, String> inter = getInterfaces();
    for (String key : inter.keySet()) {
      if (key.contains("eth")) {
        interName = inter.get(key);
        break;
      }
    }
    info(interName);
    return interName;
  }

  /**
   * map of interface name and Ip of local machine
   *
   * @return map of interface name and Ip of local machine
   */
  public static Map<String, String> getInterfaces() {
    Map<String, String> inter = new HashMap<String, String>();
    String IP = "";
    try {
      Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();

      while (e.hasMoreElements()) {
        NetworkInterface ni = (NetworkInterface) e.nextElement();
        info("Net interface: " + ni.getName());

        Enumeration<InetAddress> e2 = ni.getInetAddresses();
        while (e2.hasMoreElements()) {
          InetAddress ip = (InetAddress) e2.nextElement();
          if (!ip.isLinkLocalAddress()) {
            IP = ip.getHostAddress();
          }
        }
        info("IP address: " + IP.toString());
        inter.put(ni.getName(), IP.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return inter;
  }

  /**
   * This function returns a absolute path from a relative path that get from
   * excel file
   *
   * @param relativeFilePath
   * @return absolutePath
   */
  public static String getAbsoluteFilePathFromFile(String relativeFilePath) {
    String curDir = System.getProperty("user.home");
    String absolutePath = curDir + relativeFilePath;
    info("absolutePath:" + absolutePath);
    return absolutePath;
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

  /**
   * Create a String list by size
   *
   * @param name is the name of array's members
   * @param size
   * @return value
   */
  public static ArrayList<String> getListData(String name, int size) {
    ArrayList<String> array = new ArrayList<String>();
    for (int i = 1; i < size; i++) {
      String item = name + " " + String.valueOf(i);
      array.add(item);
    }
    return array;
  }
}
