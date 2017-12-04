package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_CONTENT_DETAIL_ADDPATH_BTN;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_SELECT_CONTENT_POPUP_FILE;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_SELECT_CONTENT_POPUP_NODE_FOLDER;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ContentDetail {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ContentDetail(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Select a folder or a content or both in Multiple Content Selector Pane popup
   *
   * @param path String
   * @param content String
   */
  public void selectFolderContent(String path, String content) {
    $(byClassName("uiIconAddPath")).click();
    String[] arrayPath = path.split("/");
    for (String arrayElement : arrayPath) {
      $(byId("LeftWorkspace")).find(byText(arrayElement)).click();
    }
    if (content != "" || content != null) {
      $(byXpath(ELEMENT_SELECT_CONTENT_POPUP_FILE.replace("${content}", content))).waitUntil(Condition.visible,Configuration.timeout).click();
    }
  }

}
