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
package org.exoplatform.platform.qa.ui.selenium.testbase;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.logger.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;

public class ManageFileTestBase {

  private final TestBase testBase;

  protected String siteExpDrivePath;

  protected String siteExpPathPath;

  protected String siteExpContentTypeFilePath;

  protected String videoLinksFilePath;

  protected String dataTestFilePath;

  protected String userDataFilePath;

  protected String userInfoFilePath;

  protected String mailSuffixFilePath;

  protected String userSearchOptionFilePath;

  protected String attachmentFilePath;

  protected String texboxFilePath;

  protected String permissionPath;

  protected String linkPath;

  protected String changLangDataPath;

  protected String contactIMFilePath;

  protected String contactPhoneFilePath;

  protected String activityMesFilePath;

  protected String conStatusFilePath;

  protected String chatStatusFilePath;

  protected String groupsCalenderFilePath;

  protected String remoteGadgetDataFilePath;

  protected String languageFilePath;

  protected String appGateinDataFilePath;

  protected String getStartFilePath;

  protected String gadgetFilePath;

  protected String containerFilePath;

  protected String appLayoutFilePath;

  protected String groupNameDataFilePath;

  protected String appListGateinFilePath;

  protected String appAddGateinFilePath;

  protected String createNewGateinFilePath;

  protected String categoriesGateinFilePath;

  protected String pageMagListFilePath;

  protected String portalDefaultFilePath;

  protected String portalPermisGroupFilePath;

  protected String portalPermisMemFilePath;

  protected String gateinDefaultGroupsFilePath;

  protected String gateinNodesFilePath;

  // Social
  protected String spaceUIFilePath;

  protected String spaceVisibleFilePath;

  protected String spaceRegistrationFilePath;

  protected String spaceappFilePath;

  protected String spaceNavigationDefaultNodesFilePath;

  protected String spaceGroupsFilePath;

  protected String spaceWarnMessageFilePath;

  // Calendar
  protected String calGroupNameFilePath;

  protected String calTabNameFilePath;

  protected String calCommentsFilePath;

  protected String calRemoteFilePath;

  // Task Management
  protected String colorNamefilePath;

  protected String notiIntranetFilePath;

  protected String notiEmailFilePath;

  protected String actCommentFilePath;

  protected String notiCatFilePath;

  protected String notiFormatEmailFilePath;

  protected String notiLabelFilePath;

  protected String notiMessageFilePath;

  // Wiki
  protected String imageLinksFilePath;

  protected String wikiTemplateFilePath;

  protected String wikiMessageFilePath;

  protected String wikiRichTextFilePath;

  protected String wikiWarningsFilePath;

  protected String sourceTextEffectFilePath;

  protected String wikiMacroFilePath;

  // Permission
  protected String permisGroupFilePath;

  protected String permisMemFilePath;

  private Scanner scanner;

  public ManageFileTestBase(TestBase testBase) {
    this.testBase = testBase;
    initFilesPath();
  }

  /**
   * setClipboardData
   *
   * @param string
   */
  public static void setClipboardData(String string) {
    // StringSelection is a class that can be used for copy and paste
    // operations.
    StringSelection stringSelection = new StringSelection(string);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
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
    Logger.info("absolutePath:" + absolutePath);
    return absolutePath;
  }

  /**
   * function: check a file existed in folder
   *
   * @param file file name (eg: export.zip)
   * @return true -> file exist false-> file is not exist
   */
  public boolean checkFileExisted(String file) {
    String pathFile = System.getProperty("user.dir") + "/src/main/resources/TestData/" + file;
    boolean found = false;

    if (new File(pathFile).isFile()) {
      found = true;
    }
    Logger.info("File exists: " + file + " is " + found);
    return found;
  }

  /**
   * function delete file in folder test output
   *
   * @param file file name
   */
  public void deleteFile(String file) {
    String fs = File.separator;
    String pathFile = System.getProperty("user.dir") + "/src/main/resources/TestData/" + file;
    pathFile = pathFile.replace("/", fs).replace("\\", fs);
    File Files = new File(pathFile);
    if (checkFileExisted(file)) {
      Files.setWritable(true);
      Files.delete();
    }
    if (checkFileExisted(file) == false) {
      Logger.info("Delete file successfully");
    } else
      Logger.info("Have error when delete file");
  }

  /**
   * cutPasteFileFromOutputToTestData
   *
   * @param fileName
   */
  public void cutPasteFileFromOutputToTestData(String fileName) {
    String fs = File.separator;
    String source = System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput/" + fileName;
    source = source.replace("/", fs);
    // directory where file will be copied
    String target = System.getProperty("user.dir") + "/src/main/resources/TestData/";
    target = target.replace("/", fs);

    // name of source file
    File sourceFile = new File(source);
    String name = sourceFile.getName();

    File targetFile = new File(target + name);

    // copy file from one location to other
    // try {
    // @TODO fix it by added a dependency to commons-io?
    // FileUtils.copyFile(sourceFile, targetFile);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // delete file in TestOutput
    deleteFile("TestOutput/" + fileName);
  }

  /**
   * Import a Category
   *
   * @param pathFile
   * @param fileName
   */
  public void importFile(String pathFile, String fileName) {
    Logger.info("Attach a file");
    testBase.click(LocatorTestBase.ELEMENT_UPLOAD_SELECT_BUTTON);
    uploadFileUsingRobot(pathFile + "/" + fileName);
    testBase.waitForAndGetElement(LocatorTestBase.ELEMENT_UPLOAD_POPUP_NAMEFILE.replace("${fileName}", fileName));
    testBase.click(LocatorTestBase.ELEMENT_SAVE_BTN);
    Utils.pause(2000);
  }

  /**
   * This function returns a absolute path from a relative path
   *
   * @param relativeFilePath
   * @return - FQA-2092: Run and check calendar sniff on IE and FF
   */
  public String getAbsoluteFilePath(String relativeFilePath) {
    String fs = File.separator;
    String curDir = System.getProperty("user.dir");
    String absolutePath = curDir + "/src/main/resources/" + relativeFilePath;
    absolutePath = absolutePath.replace("/", fs).replace("\\", fs);
    ;
    return absolutePath;
  }

  /**
   * Get a File Content
   *
   * @param filePath
   * @return fileContent
   */
  public String getFileContent(String filePath) {
    String path = getAbsoluteFilePath(filePath);
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(path);
    } catch (FileNotFoundException e) {
      Logger.error("Failed to find location of... " + filePath);
    }
    scanner = new Scanner(fis, "UTF-8");
    String inputStreamString = scanner.useDelimiter("\\A").next();
    return inputStreamString;
  }

  /**
   * Get a file name from current Url
   *
   * @param driver
   * @param params
   * @return fileName
   */
  public String getFileNameFromCurrentUrl(WebDriver driver, Object... params) {
    Boolean extension = (Boolean) (params.length > 0 ? params[0] : false);

    String currentUrl = driver.getCurrentUrl();
    File file = new File(currentUrl);
    String fileNameWithExt = file.getName();

    if (extension) {
      int position = fileNameWithExt.lastIndexOf(".");
      String fileNameWithOutExt = null;
      if (position >= 0) {
        fileNameWithOutExt = fileNameWithExt.substring(0, position);
      } else {
        fileNameWithOutExt = fileNameWithExt;
      }
      return fileNameWithOutExt;
    } else {
      return fileNameWithExt;
    }
  }

  /**
   * Attach file in attach popup
   *
   * @param pathFile
   * @param fileName
   */
  public void attachFile(String pathFile, String fileName, final int timeout, WebDriver driver) {
    Logger.info("Attach a file");
    WebElement element = testBase.waitForAndGetElement(LocatorTestBase.ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_INPUT, timeout, 1, 2);
    ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", element);
    Logger.info("Get the file to attach");
    element.sendKeys(getAbsoluteFilePath(pathFile + fileName));
    Logger.info("Verify that the file is attached");
    testBase.waitForAndGetElement(LocatorTestBase.ELEMENT_UPLOAD_POPUP_NAMEFILE.replace("${fileName}", fileName));
    Logger.info("The file is attached successfully");
    Logger.info("Click on Save button");
    testBase.click(LocatorTestBase.ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_SAVE_BUTTON);
    Utils.pause(2000);
  }

  /**
   * Upload file using AutoIt
   *
   * @param file
   */
  public void uploadFileUsingAutoIt(String file) {
    Logger.info("Upload file using AutoIt");
    String fs = File.separator;
    String path = getAbsoluteFilePath("TestData\\attachFile.exe") + " " + getAbsoluteFilePath(file.replace("/", fs));
    try {
      Logger.info(path);
      Runtime.getRuntime().exec(path);
      Logger.info("done upload");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Download file using autoit
   *
   * @param file
   */
  public void downloadFileUsingAutoIt(String file) {
    Logger.info("Download file using AutoIt");
    String download = "TestData\\downloadIE9.exe";
    String fs = File.separator;
    String pathDownload = getAbsoluteFilePath(download);
    try {
      Process proc = Runtime.getRuntime()
                            .exec(pathDownload + " " + getAbsoluteFilePath("TestData" + fs + "TestOutput" + fs + file));
      InputStream is = proc.getInputStream();
      int retCode = 0;
      while (retCode != -1) {
        retCode = is.read();
        Logger.info("Now Exiting");
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Download file using Robot class
   *
   * @param element
   * @throws AWTException
   * @throws InterruptedException
   */
  public void downloadFileUsingRobot(WebElement element) throws AWTException, InterruptedException {
    Logger.info("Upload file using Robot");
    Robot robot = new Robot();

    // Get the focus on the element..don't use click since it stalls the
    // seleniumWebDriver
    element.sendKeys("");

    // simulate pressing enter
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);

    // Wait for the download manager to open
    Utils.pause(2000);
    // Switch to download manager tray via Alt+N
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_N);
    robot.keyRelease(KeyEvent.VK_N);
    robot.keyRelease(KeyEvent.VK_ALT);

    // Press S key to save
    robot.keyPress(KeyEvent.VK_S);
    robot.keyRelease(KeyEvent.VK_S);
    Thread.sleep(2000);

    // Switch back to download manager tray via Alt+N
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_N);
    robot.keyRelease(KeyEvent.VK_N);
    robot.keyRelease(KeyEvent.VK_ALT);

    // Tab to X exit key
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_TAB);

    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_TAB);

    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_TAB);

    // Press Enter to close the Download Manager
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
  }

  /**
   * Download file using Robot class via URL download link
   *
   * @throws AWTException
   * @throws InterruptedException
   */
  public void downloadFileUsingRobotViaURL() throws AWTException, InterruptedException {
    Logger.info("Upload file using Robot");
    Robot robot = new Robot();

    // simulate pressing enter
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);

    // Wait for the download manager to open
    Utils.pause(2000);
    // Switch to download manager tray via Alt+N
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_N);
    robot.keyRelease(KeyEvent.VK_N);
    robot.keyRelease(KeyEvent.VK_ALT);

    // Press S key to save
    robot.keyPress(KeyEvent.VK_S);
    robot.keyRelease(KeyEvent.VK_S);
    Thread.sleep(2000);

    // Switch back to download manager tray via Alt+N
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_N);
    robot.keyRelease(KeyEvent.VK_N);
    robot.keyRelease(KeyEvent.VK_ALT);

    // Tab to X exit key
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_TAB);

    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_TAB);

    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_TAB);

    // Press Enter to close the Download Manager
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
  }

  /**
   * uploadFileUsingRobot
   *
   * @param fileLocation
   */
  public void uploadFileUsingRobot(String fileLocation) {
    Utils.pause(3000);
    Logger.info("Upload file using Robot");
    String fs = File.separator;
    String path = getAbsoluteFilePath(fileLocation.replace("/", fs));
    Logger.info("path in uploadRobot:" + path);
    try {
      Robot robot = new Robot();
      robot.delay(1000);
      robot.keyPress(KeyEvent.VK_DOWN);
      robot.keyRelease(KeyEvent.VK_DOWN);
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_A);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_A);
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_X);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_X);
      // Setting clipboard with file location
      setClipboardData(path);
      // native key strokes for CTRL, V and ENTER keys

      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.delay(1000);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      Utils.pause(1000);
    } catch (Exception exp) {
      exp.printStackTrace();
    }
  }

  /**
   * uploadFileUsingRobot using for Document preview
   *
   * @param fileLocation
   */
  public void uploadFileUsingRobotDocumentPreview(String fileLocation) {
    Utils.pause(3000);
    Logger.info("Upload file using Robot");
    String fs = File.separator;
    // String path=getAbsoluteFilePath(fileLocation.replace("/", fs));
    String path = getAbsoluteFilePathFromFile(fileLocation.replace("/", fs));
    Logger.info("path in uploadRobot:" + path);
    try {
      Robot robot = new Robot();
      robot.delay(1000);
      robot.keyPress(KeyEvent.VK_DOWN);
      robot.keyRelease(KeyEvent.VK_DOWN);
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_A);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_A);
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_X);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_X);
      // Setting clipboard with file location
      this.setClipboardData(path);
      // native key strokes for CTRL, V and ENTER keys

      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.delay(1000);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      Utils.pause(1000);
    } catch (Exception exp) {
      exp.printStackTrace();
    }
  }

  private void initFilesPath() {

    siteExpDrivePath = System.getProperty("siteExpDrivePath");
    siteExpPathPath = System.getProperty("siteExpPathPath");
    siteExpContentTypeFilePath = System.getProperty("siteExpContentTypeFilePath");

    dataTestFilePath = System.getProperty("dataTestPath");

    videoLinksFilePath = System.getProperty("videoLinksFilePath");

    userDataFilePath = System.getProperty("userDataFilePath");
    userInfoFilePath = System.getProperty("userInfoFilePath");
    mailSuffixFilePath = System.getProperty("mailSuffixFilePath");
    userSearchOptionFilePath = System.getProperty("userSearchOptionFilePath");
    wikiRichTextFilePath = System.getProperty("wikiRichTextFilePath");
    attachmentFilePath = System.getProperty("attachmentFilePath");
    texboxFilePath = System.getProperty("texboxFilePath");
    languageFilePath = System.getProperty("languageFilePath");
    permissionPath = System.getProperty("permissionPath");
    wikiTemplateFilePath = System.getProperty("wikiTemplateFilePath");

    changLangDataPath = System.getProperty("changLangDataPath");
    remoteGadgetDataFilePath = System.getProperty("remoteGadgetDataFilePath");
    appGateinDataFilePath = System.getProperty("appGateinDataFilePath");
    getStartFilePath = System.getProperty("getStartFilePath");
    wikiMessageFilePath = System.getProperty("wikiMessageFilePath");
    gateinDefaultGroupsFilePath = System.getProperty("gateinDefaultGroupsFilePath");
    gateinNodesFilePath = System.getProperty("gateinNodesFilePath");

    linkPath = System.getProperty("linkPath");

    gadgetFilePath = System.getProperty("gadgetFilePath");
    containerFilePath = System.getProperty("containerFilePath");
    appLayoutFilePath = System.getProperty("appLayoutFilePath");
    groupNameDataFilePath = System.getProperty("groupNameDataFilePath");
    appListGateinFilePath = System.getProperty("appListGateinFilePath");
    appAddGateinFilePath = System.getProperty("appAddGateinFilePath");
    createNewGateinFilePath = System.getProperty("createNewGateinFilePath");
    categoriesGateinFilePath = System.getProperty("categoriesGateinFilePath");
    pageMagListFilePath = System.getProperty("pageMagListFilePath");
    portalDefaultFilePath = System.getProperty("portalDefaultFilePath");
    portalPermisGroupFilePath = System.getProperty("portalPermisGroupFilePath");
    portalPermisMemFilePath = System.getProperty("portalPermisMemFilePath");

    conStatusFilePath = System.getProperty("conStatusFilePath");
    contactIMFilePath = System.getProperty("contactIMFilePath");
    contactPhoneFilePath = System.getProperty("contactPhoneFilePath");
    activityMesFilePath = System.getProperty("activityMesFilePath");
    chatStatusFilePath = System.getProperty("chatStatusFilePath");

    // Social
    spaceUIFilePath = System.getProperty("spaceUIFilePath");
    spaceVisibleFilePath = System.getProperty("spaceVisibleFilePath");
    spaceRegistrationFilePath = System.getProperty("spaceRegistrationFilePath");
    spaceappFilePath = System.getProperty("spaceappFilePath");
    spaceGroupsFilePath = System.getProperty("spaceGroupsFilePath");
    spaceWarnMessageFilePath = System.getProperty("spaceWarnMessageFilePath");
    notiEmailFilePath = System.getProperty("notiEmailFilePath");
    notiIntranetFilePath = System.getProperty("notiIntranetFilePath");
    spaceNavigationDefaultNodesFilePath = System.getProperty("spaceNavigationDefaultNodesFilePath");
    actCommentFilePath = System.getProperty("actCommentFilePath");
    notiCatFilePath = System.getProperty("notiCatFilePath");
    notiFormatEmailFilePath = System.getProperty("notiFormatEmailFilePath");
    notiLabelFilePath = System.getProperty("notiLabelFilePath");
    notiMessageFilePath = System.getProperty("notiMessageFilePath");

    // Calendar
    calGroupNameFilePath = System.getProperty("calGroupNameFilePath");
    calTabNameFilePath = System.getProperty("calTabNameFilePath");
    calCommentsFilePath = System.getProperty("calCommentsFilePath");
    calRemoteFilePath = System.getProperty("calRemoteFilePath");

    // Task Management
    colorNamefilePath = System.getProperty("colorNamefilePath");

    // Wiki
    imageLinksFilePath = System.getProperty("imageLinksFilePath");
    wikiWarningsFilePath = System.getProperty("wikiWarningsFilePath");
    sourceTextEffectFilePath = System.getProperty("sourceTextEffectFilePath");
    wikiMacroFilePath = System.getProperty("wikiMacroFilePath");

    // Permission
    permisGroupFilePath = System.getProperty("permisGroupFilePath");
    permisMemFilePath = System.getProperty("permisMemFilePath");

    if (userDataFilePath == null)
      userDataFilePath = DefaultDataTestBase.DEFAULT_USERFILEURL;
    if (userInfoFilePath == null)
      userInfoFilePath = DefaultDataTestBase.DEFAULT_USERINFOURL;
    if (mailSuffixFilePath == null)
      mailSuffixFilePath = DefaultDataTestBase.DEFAULT_MAILSUFFIXURL;
    if (userSearchOptionFilePath == null)
      userSearchOptionFilePath = DefaultDataTestBase.DEFAULT_USERSEARCHOPTIONURL;
    if (wikiRichTextFilePath == null)
      wikiRichTextFilePath = DefaultDataTestBase.DEFAULT_WIKIRICHTEXTFILEURL;
    if (attachmentFilePath == null)
      attachmentFilePath = DefaultDataTestBase.DEFAULT_ATTACHMENTFILEURL;
    if (wikiTemplateFilePath == null)
      wikiTemplateFilePath = DefaultDataTestBase.DEFAULT_WIKITEMPLATEFILEURL;
    if (texboxFilePath == null)
      texboxFilePath = DefaultDataTestBase.DEFAULT_TEXTBOXFILEURL;
    if (languageFilePath == null)
      languageFilePath = DefaultDataTestBase.DEFAULT_LANGUAGEURL;
    if (permissionPath == null)
      permissionPath = DefaultDataTestBase.DEFAULT_PERMISSIONURL;

    if (contactIMFilePath == null)
      contactIMFilePath = DefaultDataTestBase.DEFAULT_CONTACTIMURL;
    if (contactPhoneFilePath == null)
      contactPhoneFilePath = DefaultDataTestBase.DEFAULT_CONTACTPHONEURL;
    if (activityMesFilePath == null)
      activityMesFilePath = DefaultDataTestBase.DEFAULT_ACTIVITYMESSAGEURL;
    if (conStatusFilePath == null)
      conStatusFilePath = DefaultDataTestBase.DEFAULT_CONNECTSTATUSURL;
    if (changLangDataPath == null)
      changLangDataPath = DefaultDataTestBase.DEFAULT_CHANGELANGUADATAURL;
    if (remoteGadgetDataFilePath == null)
      remoteGadgetDataFilePath = DefaultDataTestBase.DEFAULT_REMOTEGADGETURL;
    if (appGateinDataFilePath == null)
      appGateinDataFilePath = DefaultDataTestBase.DEFAULT_APPGATEINURL;
    if (getStartFilePath == null)
      getStartFilePath = DefaultDataTestBase.DEFAULT_GETTINGSTARTEDURL;
    if (wikiMessageFilePath == null)
      wikiMessageFilePath = DefaultDataTestBase.DEFAULT_WIKIMESSAGEURL;
    if (linkPath == null)
      linkPath = DefaultDataTestBase.DEFAULT_LINKSURL;
    if (gateinDefaultGroupsFilePath == null)
      gateinDefaultGroupsFilePath = DefaultDataTestBase.DEFAULT_GATEINDEFAULTGROUPSURL;
    if (gateinNodesFilePath == null)
      gateinNodesFilePath = DefaultDataTestBase.DEFAULT_GATEINNODESURL;

    if (gadgetFilePath == null)
      gadgetFilePath = DefaultDataTestBase.DEFAULT_GADGETURL;
    if (containerFilePath == null)
      containerFilePath = DefaultDataTestBase.DEFAULT_CONTAINERURL;
    if (appLayoutFilePath == null)
      appLayoutFilePath = DefaultDataTestBase.DEFAULT_APPLAYOUTURL;
    if (groupNameDataFilePath == null)
      groupNameDataFilePath = DefaultDataTestBase.DEFAULT_GROUPNAMEURL;

    if (appListGateinFilePath == null)
      appListGateinFilePath = DefaultDataTestBase.DEFAULT_APPLISTGETINURL;
    if (appAddGateinFilePath == null)
      appAddGateinFilePath = DefaultDataTestBase.DEFAULT_APPADDGATEINURL;

    if (createNewGateinFilePath == null)
      createNewGateinFilePath = DefaultDataTestBase.DEFAULT_CREATENEWGATEINURL;
    if (categoriesGateinFilePath == null)
      categoriesGateinFilePath = DefaultDataTestBase.DEFAULT_CATEGORIGATEINURL;
    if (pageMagListFilePath == null)
      pageMagListFilePath = DefaultDataTestBase.DEFAULT_PAGE_MANAGEMENT_LIST_URL;

    if (portalDefaultFilePath == null)
      portalDefaultFilePath = DefaultDataTestBase.DEFAULT_PORTAL_DEFAULT_GATEIN_URL;
    if (portalPermisGroupFilePath == null)
      portalPermisGroupFilePath = DefaultDataTestBase.DEFAULT_PORTAL_PERMISSION_GROUP_URL;
    if (portalPermisMemFilePath == null)
      portalPermisMemFilePath = DefaultDataTestBase.DEFAULT_PORTAL_PERMISSION_MEMBERSHIPS_URL;

    if (siteExpDrivePath == null)
      siteExpDrivePath = DefaultDataTestBase.DEFAULT_SITEEXPLORERDRIVE;
    if (siteExpPathPath == null)
      siteExpPathPath = DefaultDataTestBase.DEFAULT_SITEEXPLORERPATH;
    if (siteExpContentTypeFilePath == null)
      siteExpContentTypeFilePath = DefaultDataTestBase.DEFAULT_SITE_EXPLORER_CONTENT_TYPE_PATH;
    if (dataTestFilePath == null)
      dataTestFilePath = DefaultDataTestBase.DEFAULT_DATATESTPATH;

    if (videoLinksFilePath == null)
      videoLinksFilePath = DefaultDataTestBase.DEFAULT_VIDEO_EMBBED_LINKS_PATH;
    if (chatStatusFilePath == null)
      chatStatusFilePath = DefaultDataTestBase.DEFAULT_CHATSTATUS_URL;

    // Social
    if (spaceUIFilePath == null)
      spaceUIFilePath = DefaultDataTestBase.DEFAULT_SPACE_UI_URL;
    if (spaceVisibleFilePath == null)
      spaceVisibleFilePath = DefaultDataTestBase.DEFAULT_SPACEVISIBLEFILEURL;
    if (spaceRegistrationFilePath == null)
      spaceRegistrationFilePath = DefaultDataTestBase.DEFAULT_SPACEREGISTRATIONFILEURL;
    if (spaceappFilePath == null)
      spaceappFilePath = DefaultDataTestBase.DEFAULT_SPACEAPPLICATIONURL;
    if (spaceNavigationDefaultNodesFilePath == null)
      spaceNavigationDefaultNodesFilePath = DefaultDataTestBase.DEFAULT_SPACE_NAVIGATION_DEFAULT_NODES_URL
              + DefaultDataTestBase.DEFAULT_SPACE_NAVIGATION_DEFAULT_NODES_URL;
    if (spaceGroupsFilePath == null)
      spaceGroupsFilePath = DefaultDataTestBase.DEFAULT_SPACE_GROUPS_URL;
    if (spaceWarnMessageFilePath == null)
      spaceWarnMessageFilePath = DefaultDataTestBase.DEFAULT_SPACE_WARNING_MESSAGE_URL;
    if (notiEmailFilePath == null)
      notiEmailFilePath = DefaultDataTestBase.DEFAULT_NOTIFICATION_EMAIL_URL;
    if (notiIntranetFilePath == null)
      notiIntranetFilePath = DefaultDataTestBase.DEFAULT_NOTIFICATION_INTRANET_URL;
    if (actCommentFilePath == null)
      actCommentFilePath = DefaultDataTestBase.DEFAULT_ACTIVITY_COMMENT_URL;
    if (notiCatFilePath == null)
      notiCatFilePath = DefaultDataTestBase.DEFAULT_NOTIFICATION_CATEGORY_URL;
    if (notiFormatEmailFilePath == null)
      notiFormatEmailFilePath = DefaultDataTestBase.DEFAULT_NOTIFICATION_FORMAT_EMAIL_URL;
    if (notiLabelFilePath == null)
      notiLabelFilePath = DefaultDataTestBase.DEFAULT_NOTIFICATION_LABLE_URL;
    if (notiMessageFilePath == null)
      notiMessageFilePath = DefaultDataTestBase.DEFAULT_NOTIFICATION_MESSAGE_URL;

    // Calendar
    if (calGroupNameFilePath == null)
      calGroupNameFilePath = DefaultDataTestBase.DEFAULT_CALENDAR_GROUP_NAME_URL;
    if (calTabNameFilePath == null)
      calTabNameFilePath = DefaultDataTestBase.DEFAULT_CALENDAR_TAB_NAME_URL;
    if (calCommentsFilePath == null)
      calCommentsFilePath = DefaultDataTestBase.DEFAULT_CALENDAR_COMMENTS_URL;
    if (calRemoteFilePath == null)
      calRemoteFilePath = DefaultDataTestBase.DEFAULT_CALENDAR_REMOTE_URL;

    // Task Management
    if (colorNamefilePath == null)
      colorNamefilePath = DefaultDataTestBase.DEFAULT_COLOR_FILE_URL;

    // Wiki
    if (imageLinksFilePath == null)
      imageLinksFilePath = DefaultDataTestBase.DEFAULT_WIKI_IMAGE_LINKS_FILE_URL;
    if (wikiWarningsFilePath == null)
      wikiWarningsFilePath = DefaultDataTestBase.DEFAULT_WIKI_WARNING_FILE_URL;
    if (sourceTextEffectFilePath == null)
      sourceTextEffectFilePath = DefaultDataTestBase.DEFAULT_SOURCE_TEXT_EFFECT;
    if (wikiMacroFilePath == null)
      wikiMacroFilePath = DefaultDataTestBase.DEFAULT_WIKI_MACRO_FILE_URL;
    // Permission
    if (permisGroupFilePath == null)
      permisGroupFilePath = DefaultDataTestBase.DEFAULT_PERMISSION_GROUP_FILE_URL;
    if (permisMemFilePath == null)
      permisMemFilePath = DefaultDataTestBase.DEFAULT_PERMISSION_MEMBERSHIP_FILE_URL;

    userDataFilePath = getAbsoluteFilePath(userDataFilePath);
    userInfoFilePath = getAbsoluteFilePath(userInfoFilePath);
    mailSuffixFilePath = getAbsoluteFilePath(mailSuffixFilePath);
    userSearchOptionFilePath = getAbsoluteFilePath(userSearchOptionFilePath);
    wikiRichTextFilePath = getAbsoluteFilePath(wikiRichTextFilePath);
    attachmentFilePath = getAbsoluteFilePath(attachmentFilePath);
    texboxFilePath = getAbsoluteFilePath(texboxFilePath);
    languageFilePath = getAbsoluteFilePath(languageFilePath);
    permissionPath = getAbsoluteFilePath(permissionPath);
    wikiTemplateFilePath = getAbsoluteFilePath(wikiTemplateFilePath);

    changLangDataPath = getAbsoluteFilePath(changLangDataPath);
    remoteGadgetDataFilePath = getAbsoluteFilePath(remoteGadgetDataFilePath);
    appGateinDataFilePath = getAbsoluteFilePath(appGateinDataFilePath);
    getStartFilePath = getAbsoluteFilePath(getStartFilePath);
    wikiMessageFilePath = getAbsoluteFilePath(wikiMessageFilePath);

    contactIMFilePath = getAbsoluteFilePath(contactIMFilePath);
    contactPhoneFilePath = getAbsoluteFilePath(contactPhoneFilePath);
    activityMesFilePath = getAbsoluteFilePath(activityMesFilePath);
    conStatusFilePath = getAbsoluteFilePath(conStatusFilePath);
    chatStatusFilePath = getAbsoluteFilePath(chatStatusFilePath);

    siteExpDrivePath = getAbsoluteFilePath(siteExpDrivePath);
    siteExpPathPath = getAbsoluteFilePath(siteExpPathPath);
    siteExpContentTypeFilePath = getAbsoluteFilePath(siteExpContentTypeFilePath);

    dataTestFilePath = getAbsoluteFilePath(dataTestFilePath);
    linkPath = getAbsoluteFilePath(linkPath);
    gateinDefaultGroupsFilePath = getAbsoluteFilePath(gateinDefaultGroupsFilePath);
    gateinNodesFilePath = getAbsoluteFilePath(gateinNodesFilePath);

    gadgetFilePath = getAbsoluteFilePath(gadgetFilePath);
    containerFilePath = getAbsoluteFilePath(containerFilePath);
    appLayoutFilePath = getAbsoluteFilePath(appLayoutFilePath);

    appListGateinFilePath = getAbsoluteFilePath(appListGateinFilePath);
    appAddGateinFilePath = getAbsoluteFilePath(appAddGateinFilePath);
    createNewGateinFilePath = getAbsoluteFilePath(createNewGateinFilePath);
    categoriesGateinFilePath = getAbsoluteFilePath(categoriesGateinFilePath);
    pageMagListFilePath = getAbsoluteFilePath(pageMagListFilePath);
    portalDefaultFilePath = getAbsoluteFilePath(portalDefaultFilePath);
    portalPermisGroupFilePath = getAbsoluteFilePath(portalPermisGroupFilePath);
    portalPermisMemFilePath = getAbsoluteFilePath(portalPermisMemFilePath);
    groupNameDataFilePath = getAbsoluteFilePath(groupNameDataFilePath);
    videoLinksFilePath = getAbsoluteFilePath(videoLinksFilePath);

    // social
    spaceUIFilePath = getAbsoluteFilePath(spaceUIFilePath);
    spaceappFilePath = getAbsoluteFilePath(spaceappFilePath);
    spaceVisibleFilePath = getAbsoluteFilePath(spaceVisibleFilePath);
    spaceRegistrationFilePath = getAbsoluteFilePath(spaceRegistrationFilePath);
    spaceNavigationDefaultNodesFilePath = getAbsoluteFilePath(spaceNavigationDefaultNodesFilePath);
    spaceGroupsFilePath = getAbsoluteFilePath(spaceGroupsFilePath);
    spaceWarnMessageFilePath = getAbsoluteFilePath(spaceWarnMessageFilePath);
    notiEmailFilePath = getAbsoluteFilePath(notiEmailFilePath);
    notiIntranetFilePath = getAbsoluteFilePath(notiIntranetFilePath);
    actCommentFilePath = getAbsoluteFilePath(actCommentFilePath);
    notiCatFilePath = getAbsoluteFilePath(notiCatFilePath);
    notiFormatEmailFilePath = getAbsoluteFilePath(notiFormatEmailFilePath);
    notiLabelFilePath = getAbsoluteFilePath(notiLabelFilePath);
    notiMessageFilePath = getAbsoluteFilePath(notiMessageFilePath);

    // Calendar
    calGroupNameFilePath = getAbsoluteFilePath(calGroupNameFilePath);
    calTabNameFilePath = getAbsoluteFilePath(calTabNameFilePath);
    calCommentsFilePath = getAbsoluteFilePath(calCommentsFilePath);
    calRemoteFilePath = getAbsoluteFilePath(calRemoteFilePath);

    // Task Management
    colorNamefilePath = getAbsoluteFilePath(colorNamefilePath);

    // wIKI
    imageLinksFilePath = getAbsoluteFilePath(imageLinksFilePath);
    wikiWarningsFilePath = getAbsoluteFilePath(wikiWarningsFilePath);
    sourceTextEffectFilePath = getAbsoluteFilePath(sourceTextEffectFilePath);
    wikiMacroFilePath = getAbsoluteFilePath(wikiMacroFilePath);
    // Permission
    permisGroupFilePath = getAbsoluteFilePath(permisGroupFilePath);
    permisMemFilePath = getAbsoluteFilePath(permisMemFilePath);

  }
}
