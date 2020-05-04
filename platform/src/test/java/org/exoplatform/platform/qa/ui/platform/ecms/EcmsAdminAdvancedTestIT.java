package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;

/**
 * @author ilyes
 */

@Tag("sniff")
@Tag("ecms")
public class EcmsAdminAdvancedTestIT extends Base {
  NavigationToolbar     navigationToolbar;

  ContentAdministration contentAdministration;

  ManageLogInOut        manageLogInOut;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    contentAdministration = new ContentAdministration(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(username, password);

  }

  @Test
  @Tag("ECMS-7819")
  public void test01_AddEditDelete_ActionTypeThenQueryThenScriptThenCategory() {
    info("Get the data test");
    String title = "title" + getRandomNumber();
    String Newtitle = "Newtitle" + getRandomNumber();
    String Newtitle2 = "Newtitle" + getRandomNumber();
    String title1 = "title" + getRandomNumber();
    String title2 = "title" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String script = "script" + "script" + getRandomNumber() + ".groovy";
    String type = "SQL";
    String permission = "any";
    String name = "name" + getRandomNumber();
    String lifeCycle = "Content Addition";
    String nameAction = "nameAction" + getRandomNumber();
    String targetPath = "root";
    String workspace = "knowledge";

    info("Add Action Type");
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.ADVANCED);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.ACTIONS);
    contentAdministration.addActionType(title, "", "");
    info("Verify that the title is replaced");
    $(byXpath(ELEMENT_ECM_ACTION_LIST.replace("{$name}", title))).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs);
    info("Edit Action Type");
    contentAdministration.editActionType(title, Newtitle, "", "");
    info("Delete Action Type");
    contentAdministration.deleteAction(Newtitle);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.QUERIES);
    info("Add Query");
    contentAdministration.addQueries(title1, "", "", permission);
    info("Edit Query");
    contentAdministration.editQueries(title1, type, "", "");
    info("Verify that the query is edited with new title");
    $(byXpath(ELEMENT_ECM_ADVANCED_QUERIES_TYPE_LIST.replace("{$name}", title1).replace("{$type}",
            "sql"))).waitUntil(Condition.visible,
            Configuration.openBrowserTimeoutMs);
    info("Delete Query");
    contentAdministration.deleteQueries(title1);
    info("Add Script");
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.SCRIPTS);

    contentAdministration.addScripts(title2, content, script);
    info("Verify that the script is added in the list");
    $(byXpath(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}", title2))).waitUntil(Condition.visible, Configuration.timeout);
    info("Edit Script");
    contentAdministration.EditScripts(title2, Newtitle2, "", "");
    info("Delete Script");
    contentAdministration.deleteScripts(Newtitle2);
    info("Add Category");
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.CATEGORIES);
    contentAdministration.addCategories(name, nameAction, lifeCycle, targetPath);
    info("Edit Categories");
    contentAdministration.editCategories(name, "", "", "", workspace, targetPath);
    info("Verify that the category is edited with new changes");
    $(byXpath(ELEMENT_ECM_ADVANCED_CATEGORIES_WORKSPACE_LIST.replace("{$name}",
            name)
            .replace("{$workspace}", workspace))).waitUntil(Condition.visible,
            Configuration.timeout);
    info("Delete Categories");
    contentAdministration.deleteCategories(name);

  }

}
