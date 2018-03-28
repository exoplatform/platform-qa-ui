package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by esmaoui on 27/03/2018.
 */

@Tag("social")
@Tag("sniff")
public class SpecialisationOfDollarInActivityMessageTestIT extends Base {
    NavigationToolbar navigationToolbar;

    ManageLogInOut manageLogInOut;

    HomePagePlatform homePagePlatform;

    ActivityStream activityStream;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut = new ManageLogInOut(this);
        activityStream = new ActivityStream(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(username, password);
    }

    /*
    bug SOC-5646
    */

    @Test
    public void test_SpecialisationOfDollarInActivityMessage(){

        homePagePlatform.goToHomePage();
        info("add an activity with $ and check that is added succesfully");
        activityStream.addActivity("$ {val}", "");
        info("delete activity");

       activityStream.deleteactivity("$ {val}");



    }


}
