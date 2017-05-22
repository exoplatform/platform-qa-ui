package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_DISABLE_USER_DROP_BOX;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class DisableUser {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  private PlatformBase         plf;

  public DisableUser(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.plf = new PlatformBase(testBase);
  }

  /**
   * Select status's type
   *
   * @param type is a value as: Enable,Disable and All
   */
  public void selectStatus(typeStatus type) {
    switch (type) {
    case Enabled:
      plf.selectOption(ELEMENT_DISABLE_USER_DROP_BOX, type.toString().toUpperCase());
      break;
    case Disabled:
      break;
    case All:
      break;
    }
  }

  /**
   * Define status's types as Enable,Disable and All
   */
  public enum typeStatus {
    Enabled, Disabled, All;
  }
}
