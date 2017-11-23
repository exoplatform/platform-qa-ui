package org.exoplatform.platform.qa.ui.ecms.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class DocumentPreview {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public DocumentPreview(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Close Preview mode and Back to Activity stream by pressing ECS
   */
  public void closeByPressECS() {
    info("press ESC key");
    Actions action = new Actions(this.testBase.getExoWebDriver().getWebDriver());
    action.sendKeys(Keys.ESCAPE).build().perform();
    action.release();

    evt.waitForElementNotPresent(ELEMENT_PREVIEW_MODE_CROSS_ICON, 3000, 1);
  }

  /**
   * Close Preview mode and Back to Activity stream by clicking Cross (X) icon
   */
  public void closeByClickCrossIcon() {
    info("Close preview mode by clicking on Cross (X) icon");
    evt.waitForAndGetElement(ELEMENT_PREVIEW_MODE_CROSS_ICON, 3000, 1);
    evt.click(ELEMENT_PREVIEW_MODE_CROSS_ICON);
    evt.waitForElementNotPresent(ELEMENT_PREVIEW_MODE_CROSS_ICON, 3000, 1);
  }

  /**
   * Close Preview mode by clicking on Background
   */
  public void closeByClickBackground() {
    info("Click on background");
    info("Get a pixel on Black background");
    WebElement el = evt.waitForAndGetElement(ELEMENT_PREVIEW_MODE, 2000, 0);
    Point dis = el.getLocation();
    int x = dis.getX() + 20;
    int y = dis.getY() + 50;
    info("x is:" + x);
    info("y is:" + y);
    info("click on black background of Display area");
    Actions action = new Actions(this.testBase.getExoWebDriver().getWebDriver());
    action.moveToElement(el, 0, 0).moveByOffset(x, y).click().build().perform();

    info("Verify that the preview is closed");
    evt.waitForElementNotPresent(ELEMENT_PREVIEW_MODE, 3000, 1);
  }

  /**
   * Check properties of shadow mask display
   * @param wd_br int
   * @param wd_preview int
   */
  public void shadowMask(int wd_preview, int wd_br) {
    info("Check shadow Mask");
    if (wd_preview >= wd_br)
      assert true;
    else
      assert false : ("The shadow mask doesn't occupie the whole area of the browser window.");
  }

  /**
   * Collapse Comment area
   */
  public void collapseCommentArea() {
    info("Collapse comment area");
    evt.waitForAndGetElement(ELEMENT_COMMENT_COLLAPSE_ICON, 2000, 0);
    evt.click(ELEMENT_COMMENT_COLLAPSE_ICON);
    evt.waitForAndGetElement(ELEMENT_COMMENT_EXPAND_ICON, 2000, 1);
    info("Collapse comment area is successfully");
  }

  /**
   * Expand Comment area
   */
  public void expandCommentArea() {
    info("Expand comment area");
    evt.waitForAndGetElement(ELEMENT_COMMENT_EXPAND_ICON, 2000, 1);
    evt.click(ELEMENT_COMMENT_EXPAND_ICON);
    evt.waitForAndGetElement(ELEMENT_COMMENT_COLLAPSE_ICON, 2000, 1);
    info("Expand comment area is successfully");
  }

  /**
   * press Enter to add comments to Comment area
   * 
   * @param text String
   * @param number is the number of comments that are added to the area
   */
  public void addComment(String text, int number) {
    info("Start to add a comment to Comment area");
    for (int i = 0; i < number; i++) {
      info("Input a text into input comment field");
      evt.waitForAndGetElement(ELEMENT_COMMENT_INPUT_FIELD, 2000, 1);
      evt.type(ELEMENT_COMMENT_INPUT_FIELD, text, true);

      info("Press Enter to add a comment to Comment area");
      Actions action = new Actions(testBase.getExoWebDriver().getWebDriver());
      action.sendKeys(Keys.ENTER).build().perform();
    }
    info("Finish adding a comment to Comment area");
  }

}
