package org.exoplatform.platform.qa.ui.calendar.pageobject;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class TaskManagement {
  private final TestBase       testBase;

  public PlatformPermission    pPer;

  private ElementEventTestBase evt;

  // CalendarHomePage cHome;
  public TaskManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.pPer = new PlatformPermission(testBase);

  }
}
