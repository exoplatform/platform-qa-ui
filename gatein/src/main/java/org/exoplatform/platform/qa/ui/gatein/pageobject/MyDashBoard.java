package org.exoplatform.platform.qa.ui.gatein.pageobject;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class MyDashBoard {

    private final TestBase testBase;

    public ManageAlert magAlert;

    private ElementEventTestBase evt;

    public MyDashBoard(TestBase testBase) {
        this.testBase = testBase;
        this.magAlert = new ManageAlert(testBase);
        this.evt = testBase.getElementEventTestBase();
    }

}
