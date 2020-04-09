package org.exoplatform.platform.qa.ui.exoTribe;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.exoplatform.platform.qa.ui.commons.BaseTribe;
import org.exoplatform.platform.qa.ui.exoTribe.pageobject.TribeActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.social.pageobject.UserPageBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("tribe")
@Tag("smoke")
@Tag("social")
public class SOCPeopleActivityAddTestIT extends BaseTribe {
  NavigationToolbar     navigationToolbar;

  AddUsers              addUsers;

  ManageLogInOut        manageLogInOut;

  HomePagePlatform      homePagePlatform;

  ConnectionsManagement connectionsManagement;

  ActivityStream        activityStream;

  TribeActivityStream         tribeActivityStream;

  UserPageBase          userPageBase;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    homePagePlatform = new HomePagePlatform(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    connectionsManagement = new ConnectionsManagement(this);
    activityStream = new ActivityStream(this);
    tribeActivityStream = new TribeActivityStream(this);
    manageLogInOut.signInTribeWithGoogle(tribe_mail, atlassian_username, atlassian_password);

  }

  @Test
  public void test03_add_link_without_text() {

    String link = "http://www.google.fr";
    String title = "Google";

    ELEMENT_TAB_ADD_LINK.click();
    ELEMENT_INPUT_LINK.setValue(link);
    ELEMENT_BUTTON_ATTACH_LINK.click();
    $(ELEMENT_COMPOSER_SHARE_BUTTON).waitUntil(Condition.be(Condition.enabled), Configuration.openBrowserTimeoutMs);
    $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
    $(byText(link)).waitUntil(Condition.exist,Configuration.openBrowserTimeoutMs);
    $(byText(link)).click();
    sleep(1000);
    switchTo().window(1);
    assertEquals(title, Selenide.title());
    switchTo().window(0);
    String id=$(byText(link)).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).parent()
            .parent()
            .parent()
            .parent()
            .parent().getAttribute("id").split("ActivityContextBox")[1];
    $(byId(ELEMENT_ACTIVITY_DROPDOWN.replace("{id}", id))).click();
    $(byId(ELEMENT_DELETE_ACTIVITY_LINK.replace("{id}", id))).click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, Configuration.timeout).click();
    $(byText(link)).parent().parent().parent().parent().parent().waitUntil(Condition.disappear, Configuration.timeout);

  }
}
