package org.exoplatform.platform.qa.ui.platform.forum;


import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


@Tag("forum")
@Tag("smoke")
public class ForumSettings extends Base {

    HomePagePlatform homePagePlatform;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);

    }

    @Test
    public void test01_UserSettings() {
        info("Test 1: User Settings");

        homePagePlatform.goToForum();
        /*Step Number: 1
		 *Step Name: User Setting
		 *Step Description:
		- Click on SettingsChange profile information
		- Click on Profile tab
		- Chang profile informationChange forum settings
		- Click on Forum settings tab
		- Chang forum informationManage my subscription
		- Click on My subscriptions tab
		- Chang subscriptions information
		 *Input Data:
		 *Expected Outcome:
		- User profile is updated
		- Forum is setting successful
		- Allow get Feed URL of category/forum/topic and update email of user watched*/

        $(ELEMENT_ACTIONBAR_SETTINGS).click();
        $(ELEMENT_FORUM_SETTINGS_SCREENNAME).val("testscreen");

        $(ELEMENT_FORUM_SETTINGS_FORUMSETTINGS).click();
        select(ELEMENT_FORUM_SETTINGS_MAXTHREADS, "5", 2);

        $(ELEMENT_FORUM_SETTINGS_MYSUSCRIB).click();
        $(ELEMENT_FORUM_SETTINGS_EMAILADRESS).val("test@test.com");

        $(ELEMENT_FORUM_SETTINGS_UPDATE).click();
        $(ELEMENT_FORUM_SETTINGS_SAVE).click();
        //waitForAndGetElement(By.xpath((forumHomePage.ELEMENT_FORUM_VERIFY_USER).replace("${user}", "UserJohn")));
        $(By.xpath((ELEMENT_FORUM_VERIFY_USER).replace("${user}", "testscreen")));

    }
}
