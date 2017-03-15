package org.exoplatform.platform.qa.ui.selenium.platform.gatein;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ContentDetail {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ContentDetail(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Select a folder or a content or both in Multiple Content Selector Pane
   * popup
   * 
   * @param arrayPath
   * @param content
   */
  public void selectFolderContent(String path, String content) {
    evt.waitForAndGetElement(ELEMENT_CONTENT_DETAIL_ADDPATH_BTN);
    evt.click(ELEMENT_CONTENT_DETAIL_ADDPATH_BTN);
    Utils.pause(2000);
    String[] arrayPath = path.split("/");
    for (String arrayElement : arrayPath) {
      evt.click(ELEMENT_SELECT_CONTENT_POPUP_NODE_FOLDER.replace("${node}", arrayElement));
    }
    if (content != "" || content != null) {
      evt.waitForAndGetElement(ELEMENT_SELECT_CONTENT_POPUP_FILE.replace("${content}", content));
      evt.click(ELEMENT_SELECT_CONTENT_POPUP_FILE.replace("${content}", content));
    }
    Utils.pause(2000);
  }

}
