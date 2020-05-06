package org.exoplatform.platform.qa.ui.platform.ecms;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
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
 * @author eXo
 */
@Tag("sniff")
@Tag("ecms")
public class EcmsAdminTemplatesTestIT extends Base {
  NavigationToolbar     navigationToolbar;
  ContentAdministration contentAdministration;
  ManageLogInOut        manageLogInOut;
  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    contentAdministration = new ContentAdministration(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
  }
  @Test
  @Tag("ECMS-7841")
  public void test02_EditViewDelete_MetadataThenListTemplateThenDocuments() {
    info("Test 1: Edit,view and delete Metadata");
    String title = "title" + getRandomNumber();
    String newName = "newName" + getRandomNumber();
    String superTypes = "exo:metadata";
    String permission = "any";
    String tempName = "tempName" + getRandomNumber();
    String name = "name" + getRandomNumber();
    String content = "content" + getRandomNumber();
    String newTitle = "newTitle" + getRandomNumber();
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.REPOSITORY);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.NODESTYPES);
    contentAdministration.addNodeType(title, superTypes, "true");
    executeJavaScript("window.scrollBy(0,-5500)", "");
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.TEMPLATES);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.METADATA);
    contentAdministration.editeMetadataNameAndPermission(title, newName, permission);
    $(byXpath(ELEMENT_ECM_TEMPLATES_METADATA_FORM_SHOW.replace("{$name}", newName))).click();
    $(byXpath(ELEMENT_ECM_TEMPLATES_METADATA_CHECK_MATADATA_INFORMATION.replace("{$metadata}",
            title))).waitUntil(Condition.visible,
            Configuration.timeout);
    $(ELEMENT_ECM_TEMPLATES_METADATA_CLOSE_VIEW).click();
    contentAdministration.deleteMetadata(title);
    info("Add, Edit and Delete List Template");
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificMainFunctions(ContentAdministration.mainEcmFunctions.TEMPLATES);
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.LIST);
    contentAdministration.addTemplateInList(name, tempName, content);
    contentAdministration.editTemplateNameInList(name, newName);
    contentAdministration.deleteTemplateList(newName);
    info("Add, edit and delete Document Template");
    navigationToolbar.goToContentAdministration();
    contentAdministration.goToSpecificFunctions(ContentAdministration.specificEcmFunctions.DOCUMENTS);
    contentAdministration.addDocumentInTemplates(title, permission);
    $(byText(title)).waitUntil(Condition.appears, Configuration.timeout);
    contentAdministration.editDocumentInTemplates(title, newTitle);
    contentAdministration.deleteCategories(newTitle);
  }
}
