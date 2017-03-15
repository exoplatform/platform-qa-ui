package org.exoplatform.platform.qa.ui.selenium.platform.gatein;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ContentList {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ContentList(TestBase testBase) {

    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Select a folder or a content or both in Multiple Content Selector Pane
   * popup
   *
   * @param content
   */
  public void selectFolderContent(String path, String content) {
    evt.waitForAndGetElement(ELEMENT_CONTENT_LIST_ADDPATH_BTN);
    evt.click(ELEMENT_CONTENT_LIST_ADDPATH_BTN);
    Utils.pause(2000);
    String[] arrayPath = path.split("/");
    for (String arrayElement : arrayPath) {
      evt.click(ELEMENT_MULTIPLE_CONTENT_POPUP_NODE_FOLDER.replace("${node}", arrayElement));
    }

    if (content != "" || content != null) {
      evt.waitForAndGetElement(ELEMENT_MULTIPLE_CONTENT_POPUP_FILE.replace("${content}", content));
      // click(ELEMENT_MULTIPLE_CONTENT_POPUP_FILE.replace("${content}",content));
      evt.clickByJavascript(ELEMENT_MULTIPLE_CONTENT_POPUP_FILE.replace("${content}", content), 2);
    }
    Utils.pause(2000);
  }

}
