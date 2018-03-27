package org.exoplatform.platform.qa.ui.platform.ecms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.administration.ContentAdministration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_SOURCE_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.PlatformLocator.ELEMENT_WEB_CONTENT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by esmaoui on 27/03/2018.
 */

@Tag("ecms")
@Tag("sniff")
public class ECMSCreateWebContentWithParticularCharacter extends Base {

    HomePagePlatform homePagePlatform;

    ManageLogInOut manageLogInOut;
    ContentAdministration contentAdministration;

    NavigationToolbar navigationToolbar;

    SiteExplorerHome siteExplorerHome;

    CreateNewDocument createNewDoc;
    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");

        homePagePlatform = new HomePagePlatform(this);
        contentAdministration = new ContentAdministration(this);
        navigationToolbar = new NavigationToolbar(this);
        siteExplorerHome = new SiteExplorerHome(this);
        createNewDoc = new CreateNewDocument(this);
        manageLogInOut= new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(username, password);
    }

   /*
    bug EXOGTN-2345
     */

    @Test
    public void test_CreateWebContentWithParticularCharacter(){

        info("Test 3: Create Web Content document");
        info("Create data test");
        String name = "name" + getRandomNumber();
        String content = "content" + getRandomNumber();
        String content2 = "content2" + getRandomNumber();
        info("Finished creating data test");

        navigationToolbar.goToSiteExplorer();
        siteExplorerHome.goToPath("intranet/documents", "Site Management");
        siteExplorerHome.goToAddNewContent();
        info("Create new file document");
        createNewDoc.createNewDoc(CreateNewDocument.selectDocumentType.WEBCONTENT);
        createNewDoc.addNewWebContent(name, content);
        $(ELEMENT_SOURCE_CONTENT).waitUntil(Condition.visible, Configuration.timeout).click();
        $(ELEMENT_CONTENT).waitUntil(Condition.visible, Configuration.timeout).click();
        $(ELEMENT_CONTENT).setValue("&#128522;");
        createNewDoc.saveAndClose();
        siteExplorerHome.verifyContentCreatedSuccessfully(name);
        info("Delete file document");
        navigationToolbar.goToSiteExplorer();
        siteExplorerHome.goToPath("intranet/documents", "Site Management");
        siteExplorerHome.deleteData(name);
    }

}




