package org.exoplatform.platform.qa.ui.selenium.platform.chat;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ChatStatus {
  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public ChatStatus(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }
}
