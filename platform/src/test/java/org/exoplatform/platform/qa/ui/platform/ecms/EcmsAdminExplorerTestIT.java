package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;

/**
 * Created by ilyes on 11/09/17.
 */
@Tag("ecms")
@Tag("sniff")
public class EcmsAdminExplorerTestIT extends Base {
  ContentAdministration contentAdministration;

  NavigationToolbar     navigationToolbar;

  ManageLogInOut        manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    contentAdministration = new ContentAdministration(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }

  @Test
  public void test02_AddEditDelete_DriveThenViewThenTag() {
    info("Get data test");
    String title = "title" + getRandomNumber();
    String permission = "any";
    String tabName = "tabName" + getRandomNumber();
    String oldPermission = "mary";
    String[] tab = { "Add Category:" };
    ContentAdministration.specificView[] view = { ContentAdministration.specificView.ADMIN };
    ContentAdministration.specificView[] newView = { ContentAdministration.specificView.WEB };
    String[] newV = { "Web" };
    String permission1 = "john";
    String occurences = "1..*";
    String html = "font-size:10px;";
    String oldHtml = "font-size:12px;";

    info("Finished getting data test");
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.EXPLORER);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DRIVES);
    info("Add Drive");
    contentAdministration.addDrives(title, permission, view);
    contentAdministration.editDrives(title, newView);
    $(byXpath(ELEMENT_ECM_EXPLORER_DRIVES_VIEW_OF_VIEWS_LIST.replace("{$name}", title)
                                                            .replace("{$view}", newV[0]))).waitUntil(Condition.visible,
                                                                                                     Configuration.timeout);
    // delete drive
    contentAdministration.deleteDrives(title);
    info("Add, edit, show and delete a View");
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.VIEW);
    info("add a view");
    contentAdministration.addView(title, tabName, tab, oldPermission);
    info("edit a view");
    contentAdministration.editViewPermissionUser(title, "Mary", permission1);
    $(byXpath(ELEMENT_ECM_EXPLORER_VIEW_PERMISSIONS_LIST.replace("{$name}", title)
            .replace("{$permission}", "John"))).waitUntil(Condition.visible,
            Configuration.timeout);
    info("show a view");
    $(byXpath(ELEMENT_ECM_EXPLORER_VIEW_SHOW_A_VIEW_LIST.replace("{$name}", title))).click();
    $(byXpath(ELEMENT_ECM_EXPLORER_NAME_VIEW_SHOW_VIEW.replace("{$name}", title))).waitUntil(Condition.visible,
            Configuration.timeout);
    $(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM).click();
    $(byXpath(ELEMENT_ECM_EXPLORER_TAB_ICONS_LIST_SHOW_VIEW.replace("{$tab}",
            tab[0].subSequence(0, 11)))).waitUntil(Condition.visible,
            Configuration.timeout);
    $(ELEMENT_ECM_EXPLORER_CLOSE_VIEW_MODE).click();
    info("delete a view");
    contentAdministration.deleteView(title);
    info("Add Tags");
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.TAGS);
    contentAdministration.addTags(title, occurences, oldHtml);
    info("Edit Tags");
    contentAdministration.updateTags(title, null, html);
    $(byXpath(ELEMENT_ECM_EXPLORER_TAGS_LIST_CHECK_HTML_CONTENT.replace("{$name}", title)
            .replace("{$html}", html))).waitUntil(Condition.visible,
            Configuration.timeout);
    contentAdministration.deleteTags(title);

  }

}
