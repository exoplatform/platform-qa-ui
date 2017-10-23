package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_COMPOSER_SHARE_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;

/**
 * Created by exo on 23/10/17.
 */
@Tag("smoke")
@Tag("social")
public class SOCActivityAddTestIT extends Base {

  @Test
  public void test02_Upload_File_Without_Text() {
    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    $(ELEMENT_COMPOSER_SHARE_BUTTON).should(Condition.be(Condition.enabled));
    $(ELEMENT_COMPOSER_SHARE_BUTTON).click();
    $(byAttribute("data-original-title", "eXo-Platform.png")).parent()
                                                             .parent()
                                                             .parent()
                                                             .parent()
                                                             .find(byClassName(ELEMENT_DATE_ACTIVITY))
                                                             .hover();
    $(byAttribute("data-original-title", "eXo-Platform.png")).parent()
                                                             .parent()
                                                             .parent()
                                                             .parent()
                                                             .find(ELEMENT_ICON_DELETE_ACTIVITY)
                                                             .click();
    ELEMENT_DELETE_POPUP_OK.waitUntil(Condition.visible, Configuration.timeout).click();
    $(byAttribute("data-original-title", "eXo-Platform.png")).parent()
                                                             .parent()
                                                             .parent()
                                                             .parent()
                                                             .waitUntil(Condition.disappears, Configuration.timeout);
  }

}
