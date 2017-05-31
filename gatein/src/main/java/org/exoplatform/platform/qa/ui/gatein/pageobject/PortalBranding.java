package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_BUTTON_UPLOAD;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_UPLOAD_LINK;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ManageFileTestBase;

public class PortalBranding {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  private ManageFileTestBase   mftb;

  public PortalBranding(TestBase testBase) {

    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.mftb = new ManageFileTestBase(testBase);
  }

  public PortalBranding uploadFile(String link, Object... params) {
    info("Upload a file to Site Explorer");
    Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
    if (evt.waitForAndGetElement(ELEMENT_BUTTON_UPLOAD, testBase.getDefaultTimeout(), 0) == null) {
      info("wrong page");
    }

    WebElement upload = evt.waitForAndGetElement(ELEMENT_UPLOAD_LINK, testBase.getDefaultTimeout(), 1, 2);
    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript(
                                                                                   "arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; "
                                                                                       + "arguments[0].style.width = '1px'; arguments[0].style.opacity = 1",
                                                                                   upload);
    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript(
                                                                                   "arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible'",
                                                                                   upload);

    info("Select a file to upload");
    upload.sendKeys(mftb.getAbsoluteFilePath(link));
    info("Upload file " + mftb.getAbsoluteFilePath(link));
    info("Switch to Parent window");
    evt.switchToParentWindow();
    if (verify) {

      evt.waitForAndGetElement(By.xpath("//*[@id='PreviewImg']"));
    }
    info("Upload file successfully");

    return new PortalBranding((TestBase) testBase.getExoWebDriver().getWebDriver());
  }
}
