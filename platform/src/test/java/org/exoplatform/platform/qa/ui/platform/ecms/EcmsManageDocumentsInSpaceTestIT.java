package org.exoplatform.platform.qa.ui.platform.ecms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.ecms.pageobject.CreateNewDocument;
import org.exoplatform.platform.qa.ui.ecms.pageobject.DocumentManagement;
import org.exoplatform.platform.qa.ui.ecms.pageobject.SiteExplorerHome;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by exo on 02/05/18.
 */
@Tag("ecms")
@Tag("sniff")
public class EcmsManageDocumentsInSpaceTestIT extends Base {
    SpaceManagement spaceManagement;

    SpaceHomePage spaceHomePage;

    NavigationToolbar navigationToolbar;

    SpaceSettingManagement spaceSettingManagement;

    SiteExplorerHome siteExplorerHome;

    CreateNewDocument createNewDocument;

    ManageLogInOut manageLogInOut;

    HomePagePlatform homePagePlatform;

    DocumentManagement documentManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        manageLogInOut = new ManageLogInOut(this);
        spaceManagement = new SpaceManagement(this);
        spaceHomePage = new SpaceHomePage(this);
        siteExplorerHome = new SiteExplorerHome(this);
        createNewDocument = new CreateNewDocument(this);
        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut.signInCas("john", "gtngtn");
        spaceSettingManagement = new SpaceSettingManagement(this);
        homePagePlatform = new HomePagePlatform(this);
        documentManagement = new DocumentManagement(this);

    }

    @Tag("ECMS-7631")
    @Test
    public void test01_CheckSaveASNewVersionButtonWhenUserNotHaveModifyRight() {
        String space = "space" + getRandomNumber();
        info("Create a space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.goToMemberTabInSpaceSettingTab();
        ELEMENT_INPUT_INVITE_USER.sendKeys(PLFData.DATA_USER2);
        $(ELEMENT_SPACE_BTN_INVITE).click();
        manageLogInOut.signIn(PLFData.DATA_USER2, password);
        homePagePlatform.goToMySpaces();
        spaceManagement.acceptAInvitation(space);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToMySpaces();
        ELEMENT_SPACES_LIST.find(byText(space)).click();
        spaceManagement.goToDocumentTab();
        $(byId("MultiUploadInputFiles")).uploadFromClasspath("eXo-Platform.png");
        ELEMENT_LIST_DOCUMENTS_IN_SPACE.find(byText("eXo-Platform.png")).waitUntil(Condition.visible, Configuration.timeout).click();
        documentManagement.goToPermissions();
        $(byId(ELEMENT_CHECKBOX_MODIFY_PERMISSION.replace("{user}", space))).parent().waitWhile(Condition.not(Condition.visible),
                Configuration.timeout);
        if ($(byId(ELEMENT_CHECKBOX_MODIFY_PERMISSION.replace("{user}", space))).is(Condition.checked))
            $(byId(ELEMENT_CHECKBOX_MODIFY_PERMISSION.replace("{user}", space))).parent().click();
        $(ELEMENT_CLOSE_BTN).click();
        manageLogInOut.signIn(PLFData.DATA_USER2, password);
        homePagePlatform.goToMySpaces();
        ELEMENT_SPACES_LIST.find(byText(space)).click();
        spaceManagement.goToDocumentTab();
        ELEMENT_LIST_DOCUMENTS_IN_SPACE.find(byText("eXo-Platform.png"))
                .waitUntil(Condition.visible, Configuration.timeout)
                .contextClick();
        ELEMENT_BUTTON_UPLOAD_NEW_VERSION.shouldNotBe(Condition.visible);
        ELEMENT_BUTTON_CREATE_NEW_VERSION.shouldNotBe(Condition.visible);
        ELEMENT_LIST_DOCUMENTS_IN_SPACE.find(byText("eXo-Platform.png")).waitUntil(Condition.visible, Configuration.timeout).click();
        ELEMENT_BUTTON_UPLOAD_NEW_VERSION.shouldNotBe(Condition.visible);
        ELEMENT_BUTTON_CREATE_NEW_VERSION.shouldNotBe(Condition.visible);
        manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);

    }

    @Test
    public void test02_CheckScriptNotExecutedWhenFilePathContainsScript() {
        //8357
        String space = "space" + getRandomNumber();
        String folderTitle = "test<script>alert(2);</alert>";
        String uploadedFile ="eXo-Platform.png";
        info("Create a space");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(space, space);
        homePagePlatform.goToDocuments();
        siteExplorerHome.goToAddNewFolder();
        info("Create Folder node");
        spaceManagement.createFolder(folderTitle);
        navigationToolbar.openFolderInDocuments(folderTitle);
        $(byId("MultiUploadInputFiles")).uploadFromClasspath(uploadedFile);
        navigationToolbar.checkFolderUploadedFile(uploadedFile);
        homePagePlatform.goToMySpaces();
        spaceManagement.searchSpace(space);
        spaceManagement.goToSpace(space);
        spaceManagement.checkUploadedFileNotExist();
        homePagePlatform.goToDocuments();
        spaceManagement.deleteFolder(folderTitle);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(space, false);
    }

}
