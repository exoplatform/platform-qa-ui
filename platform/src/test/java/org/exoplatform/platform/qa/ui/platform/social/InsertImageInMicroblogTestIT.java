package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by dmardassi on 09/11/2017.
 */
@Tag("social")
@Tag("sniff")
public class InsertImageInMicroblogTestIT extends Base{
    NavigationToolbar navigationToolbar;

    AddUsers addUsers;

    ManageLogInOut manageLogInOut;

    HomePagePlatform homePagePlatform;

    ConnectionsManagement connectionsManagement;

    ActivityStream activityStream;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        homePagePlatform = new HomePagePlatform(this);
        addUsers = new AddUsers(this);
        manageLogInOut = new ManageLogInOut(this);
        connectionsManagement = new ConnectionsManagement(this);
        activityStream = new ActivityStream(this);
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    }


    @Test
    public void test01_InsertPngImage() {
        String activity = "Activity" + getRandomNumber();
        String image = "eXo-Platform.png";
        activityStream.addActivityWithImageUsingMicroblogFromDesktop(activity,image);
        activityStream.deleteActivity(activity);

    }
}
