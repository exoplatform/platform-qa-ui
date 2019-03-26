package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_COMPOSER_SHARE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by exo on 23/10/17.
 */
@Tag("functional")
@Tag("social")
public class SOCActivityAddTestIT extends Base {
    ActivityStream activityStream;
    ManageLogInOut manageLogInOut;
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        activityStream = new ActivityStream(this);
        manageLogInOut=new ManageLogInOut(this);
        manageLogInOut.signInCas(DATA_USER1, "gtngtn");
    }

    @Test
    public void test02_Upload_File_Without_Text() {
        ELEMENT_TAB_LINK.click();
        refresh();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.appears, Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        $(ELEMENT_COMPOSER_SHARE_BUTTON).should(Condition.be(Condition.enabled));
        $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
        $(byAttribute("data-original-title", "eXo-Platform.png")).parent().parent().parent().parent().parent().parent().parent().find(byClassName(ELEMENT_DATE_ACTIVITY)).hover();
        $(byAttribute("data-original-title", "eXo-Platform.png")).parent()
                .parent()
                .parent()
                .parent().parent().parent().parent()
                .find(ELEMENT_ICON_DELETE_ACTIVITY)
                .click();
        ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, Configuration.timeout).click();
        $(byAttribute("data-original-title", "eXo-Platform.png")).parent()
                .parent()
                .parent()
                .parent()
                .waitUntil(Condition.disappears, Configuration.timeout);
    }

    @Test
    @Tag("PLF-7912")
    public void test02_CheckIconTitleWhenLikeActivity() {
        String activity1 = "activity1" + getRandomNumber();
        ELEMENT_TAB_LINK.click();
        refresh();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        activityStream.addActivity(activity1, "");
        String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile"))
                .waitUntil(Condition.visible, Configuration.timeout)
                .click();
        ELEMENT_ICON_LIKE_IN_PREVIEW_MODE.click();
        ELEMENT_ICON_LIKE_IN_PREVIEW_MODE.waitUntil(Condition.have(Condition.attribute("data-original-title", "Unlike")),
                Configuration.timeout);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
                          activityStream.deleteactivity(activity1);

    }
}
