package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class PageEditor {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public PageEditor(TestBase testBase) {
    this.evt = testBase.getElementEventTestBase();
    this.testBase = testBase;
  }

  /**
   * Edit a Portlet with locator of the portlet
   *
   * @param locatorPortlet Object
   */
  public void goToEditPortlet(Object locatorPortlet) {
    info("Go to edit portlet");

    evt.mouseOver(locatorPortlet, true);
    evt.click(ELEMENT_EDIT_PORTLET_ICON);
    evt.waitForAndGetElement(ELEMENT_EDIT_PORTLET_FORM);
  }
}
