package org.exoplatform.platform.qa.ui.platform.plf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.PortalManagePages;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_WIKI_LINK_PLF;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_ADD_GADGET;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.ELEMENT_POP_UP_WRAPPER;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by esmaoui on 26/03/2018.
 */

@Tag("sniff")
@Tag("plf")
public class PlfMinimizeGadgetInDashboardPageTestIT extends Base {
    NavigationToolbar navigationToolbar;

    HomePagePlatform homePagePlatform;

    ManageLogInOut manageLogInOut;

    PortalManagePages portalManagePages;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        navigationToolbar = new NavigationToolbar(this);
        portalManagePages= new PortalManagePages(this);
        manageLogInOut = new ManageLogInOut(this);

        manageLogInOut = new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(username, password);
    }

  /*
    bug EXOGTN-2334

    This test case is unable to execute due to the drag and drop gadget
     because the target locator is known as the same window as the poop
     gadget content. It is noted and will be discuss in the meetings note
     */

    public void test_MinimizeGadget(){

      String num = getRandomNumber();
      String pageName = "pageName" + num;
      String title = "title" + num;

      navigationToolbar.goToAddPage();
      portalManagePages.addDashboardPage(pageName,title);
      ELEMENT_ADD_GADGET.click();
      $(byId("Collaboration/gadget_Agenda")).dragAndDropTo($(byClassName("uiPopupWrapper")));


  }
}
