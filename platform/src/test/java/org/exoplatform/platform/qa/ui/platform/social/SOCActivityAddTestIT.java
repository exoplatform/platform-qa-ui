package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_DROPDOWN;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_COMPOSER_SHARE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_DELETE_ACTIVITY_LINK;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;

import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;

/**
 * Created by exo on 23/10/17.
 */
@Tag("sniff")
@Tag("social")
public class SOCActivityAddTestIT extends Base {
  ActivityStream activityStream;

  NavigationToolbar navigationToolbar;

  ManageLogInOut manageLogInOut;

  HomePagePlatform homePagePlatform;

  ConnectionsManagement connectionsManagement;


  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    activityStream = new ActivityStream(this);
    navigationToolbar = new NavigationToolbar(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
      manageLogInOut.signOut();
    }
    manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2);
  }

  @Test
  @Tag("sabis")
  public void test02_Upload_File_Without_Text() {
    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.openBrowserTimeoutMs);
    $(ELEMENT_COMPOSER_SHARE_BUTTON).should(Condition.be(Condition.enabled));
    $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
    $(ELEMENT_COMPOSER_SHARE_BUTTON).waitUntil(Condition.disabled,Configuration.timeout);
    String id=$(byAttribute("data-original-title", "eXo-Platform.png")).parent().parent().parent().parent().parent().parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}",id))).waitUntil(Condition.visible,Configuration.timeout).click();
    $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}",id))).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, Configuration.timeout).click();
    $(byAttribute("data-original-title", "eXo-Platform.png")).parent()
                                                             .parent()
                                                             .parent()
                                                             .parent()
                                                             .waitUntil(Condition.disappear, Configuration.openBrowserTimeoutMs);
  }

  @Test
  @Tag("PLF-7912")
  @Tag("sabis")
  public void test02_CheckIconTitleWhenLikeActivity() {
    String activity1 = "activity1" + getRandomNumber();
    ELEMENT_TAB_LINK.waitUntil(Condition.visible,Configuration.timeout).click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.openBrowserTimeoutMs);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile"))
                                                         .waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs)
                                                         .click();
    ELEMENT_ICON_LIKE_IN_PREVIEW_MODE.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    ELEMENT_ICON_LIKE_IN_PREVIEW_MODE.waitUntil(Condition.have(Condition.attribute("data-original-title", "Unlike")),
                                                Configuration.timeout);
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    activityStream.deleteactivity(activity1);

  }
}
