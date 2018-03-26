package org.exoplatform.platform.qa.ui.platform.ecms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.commands.Click;
import com.codeborne.selenide.commands.Should;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.DocumentManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_DATE_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by esmaoui on 23/03/2018.
 */

@Tag("sniff")
@Tag("ecms")
public class ECMSDocumentActivityStreamTestIT extends Base{

    HomePagePlatform homePagePlatform;

    DocumentManagement documentManagement;

    ManageLogInOut manageLogInOut;
    

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        documentManagement = new DocumentManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(DATA_USER2, DATA_PASS );
    }
    /*
    bug INTEG-487
    */
    @Test
    public void test_DocumentActivity() {
        homePagePlatform.goToDocuments();
        documentManagement.goToListView();
        documentManagement.goToPublicFolder();
         refresh();
         $(byId("MultiUploadInputFiles")).uploadFromClasspath("eXo-Platform.png");
         $(byClassName("progress")).waitUntil(visible, Configuration.timeout);
         $(byClassName("progress")).waitUntil(Condition.not(visible), Configuration.timeout);
       refresh();
        ELEMENT_PUBLIC_LIST_VIEW.find(byText("eXo-Platform")).waitUntil(visible, Configuration.timeout);
        refresh();
        homePagePlatform.goToHomePage();
        $(byAttribute("data-original-title", "eXo-Platform.png")).parent()
                .parent()
                .parent()
                .parent()
                .hover()
                .find(byClassName(ELEMENT_DATE_ACTIVITY));

        ELEMENT_FILE_DESCRIPTION.shouldNotBe(visible);
        homePagePlatform.goToDocuments();
        ELEMENT_PUBLIC_LIST_VIEW.find(byText("eXo-Platform"));
        ELEMENT_CHECK.parent().click();
        ELEMENT_BUTTON_DELETE_FIRST.click();
        ELEMENT_BUTTON_CONFIRM_DELETE_FILE.waitUntil(Condition.visible, Configuration.timeout).click();
        ELEMENT_NOTIFICATION.shouldBe(visible);

    }
}
