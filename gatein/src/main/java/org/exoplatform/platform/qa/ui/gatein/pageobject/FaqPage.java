package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class FaqPage {
  private final TestBase       testBase;

  public NavigationToolbar     navTool;

  public PageCreationWizard    pagMang;

  public PageEditor            pagEditor;

  public HomePagePlatform      hp;

  public ManageAlert           alert;

  public ApplicationRegistry   arPage;

  private ElementEventTestBase evt;

  public FaqPage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.navTool = new NavigationToolbar(testBase);
    this.pagMang = new PageCreationWizard(testBase);
    this.pagEditor = new PageEditor(testBase);
    this.hp = new HomePagePlatform(testBase);
    this.alert = new ManageAlert(testBase);
    this.arPage = new ApplicationRegistry(testBase);
  }

  /**
   * Create FAQ Page
   */
  public void createFaqPage() {
    info("Show all import application");
    arPage.displayImportApplicaions();
    arPage.importAllApplications();
    hp.goToHomePage();

    info("Create FAQ Page");
    navTool.goToAddPage();
    evt.click(ELEENT_NODE_NAME.replace("$name", "Home"));
    pagMang.inputPageInfoStep1("FAQ", null, null, "FAQ", null, null);
    evt.click(ELEMENT_PAGE_NEXT_BUTTON);
    evt.click(ELEMENT_PAGE_NEXT_BUTTON);
    pagEditor.selectApplication("answer-FAQPortlet", ELEMENT_EDIT_PAGE_PAGE);
    evt.click(ELEMENT_PAGE_EDITOR_VIEW_PAGE_PROPERTIES);
    evt.click(ELEMENT_PERMISSTION_SETTING_TAB);
    evt.check(ELEMENT_PUBLIC_MODE, 2);
    evt.click(ELEMENT_PAGE_EDITOR_SAVE_BUTTON);
    pagEditor.finishEditLayout();
  }

  /**
   * function go to edit FAQ portlet in FAQ page
   */
  public void goToEditFaqPortlet() {
    hp.goToFaq();
    info("Go to edit FAQ portlet");
    navTool.goToEditLayout();
    pagEditor.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
  }

  /**
   * setting template
   *
   * @param data String
   */
  public void settingFAQTemplate(String data) {
    evt.click(ELEMENT_FAQ_EDIT_TEMPLATE_TAB);
    evt.type(ELEMENT_FAQ_EDIT_TEMP_INPUT, data, false);
    evt.click(ELEMENT_EDIT_SAVE_BUTTON);
    evt.click(ELEMENT_PAGE_OK_BUTTON);
  }
}
