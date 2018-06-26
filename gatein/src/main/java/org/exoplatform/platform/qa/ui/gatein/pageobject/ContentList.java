package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_CONTENT_LIST_ADDPATH_BTN;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_MULTIPLE_CONTENT_POPUP_FILE;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_MULTIPLE_CONTENT_POPUP_NODE_FOLDER;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ContentList {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ContentList(TestBase testBase) {

    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Select a folder or a content or both in Multiple Content Selector Pane popup
   *
   * @param content String
   * @param path String
   */
  public void selectFolderContent(String path, String content) {

    $(ELEMENT_CONTENT_LIST_ADDPATH_BTN).waitUntil(Condition.visible, Configuration.timeout).click();
    String[] arrayPath = path.split("/");
    for (String arrayElement : arrayPath) {
      $(byXpath(ELEMENT_MULTIPLE_CONTENT_POPUP_NODE_FOLDER.replace("${node}", arrayElement))).click();
    }

    if (content != "" || content != null) {
      $(byXpath(ELEMENT_MULTIPLE_CONTENT_POPUP_FILE.replace("${content}", content))).waitUntil(Condition.visible,Configuration.timeout);
      $(byXpath(ELEMENT_MULTIPLE_CONTENT_POPUP_FILE.replace("${content}",content))).click();
    }
  }

}
