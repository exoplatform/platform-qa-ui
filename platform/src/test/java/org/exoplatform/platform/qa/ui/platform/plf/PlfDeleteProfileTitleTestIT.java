package org.exoplatform.platform.qa.ui.platform.plf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_EDIT_PROFILE;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by esmaoui on 16/03/2018.
 */

@Tag("sniff")
@Tag("plf")
public class PlfDeleteProfileTitleTestIT extends Base {

     HomePagePlatform homePagePlatform;
     ManageLogInOut manageLogInOut;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut = new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(DATA_USER1 ,DATA_PASS2 );
    }

    /*
    bug SOC-6009
    */
    @Test
   public void test01_Delete_Profile_Title(){

   info("Go to user profile");

   String title = "title" + getRandomNumber();

   homePagePlatform.goToUserProfile();
   $(ELEMENT_EDIT_PROFILE).waitUntil(Condition.appears, Configuration.timeout).click();
   $(ELEMENT_TITLE_INPUT).waitUntil(Condition.appears, Configuration.timeout).click();
   $(ELEMENT_TITLE_INPUT).setValue(title);
   $(ELEMENT_BUTTON_SAVE).waitUntil(Condition.appears, Configuration.timeout).click();
   $(ELEMENT_EDIT_PROFILE).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
   $(ELEMENT_TITLE_INPUT).setValue("");
   $(ELEMENT_BUTTON_SAVE).click();
   executeJavaScript("window.scrollBy(0,150)");
   $(ELEMENT_BUTTON_SAVE).waitUntil(Condition.not(Condition.visible),Configuration.collectionsTimeout);
   $(ELEMENT_ABOUT_ME).shouldNot(Condition.visible);


}

}