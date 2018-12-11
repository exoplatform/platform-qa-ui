package org.exoplatform.platform.qa.ui.platform.social.functional;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_DATE_ACTIVITY;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_DELETE_POPUP_OK;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_NOTIFICATION_DROPDOWN;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
@Tag("functional")
@Tag("social")
public class ManageSpacesTestIT extends Base {
    NavigationToolbar navigationToolbar;
    ManageLogInOut manageLogInOut;
    HomePagePlatform homePagePlatform;
    SpaceManagement spaceManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut = new ManageLogInOut(this);
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
        spaceManagement = new SpaceManagement(this);
    }

    @Test
    public void test01_CheckElementsInTheManageSpacesTab() {
        info("create some new spaces");
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        info("Test 1: CheckElementsInTheManageSpacesTab");
        navigationToolbar.goToSpaceAdminstration();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceDesa)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconDeleteUser")).waitUntil(Condition.visible, Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
    }

    @Test
    public void test02_CheckThatNothingHappensWhenClickOnSpacesNameAndAvatar() {

        info("create some spaces");
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        info("CheckThatNothingHappensWhenClickOnSpacesNameAndAvatar");
        navigationToolbar.goToSpaceAdminstration();
        $(byAttribute("src", "/eXoSkin/skin/images/system/SpaceAvtDefault.png")).hover().click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);

    }

    @Test
    public void test03_CheckTheDisplayOfTheFilterFieldAboveTheSpaceManagementTable() {

        info("CheckTheDisplayOfTheFilterFieldAboveTheSpaceManagementTable");
        navigationToolbar.goToSpaceAdminstration();
        $(byClassName("showInputSearch")).waitUntil(Condition.visible, Configuration.timeout);
    }


    @Test
    public void test04_SpacesAreFilteredByItsName() {

        info("create some spaces");
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        String spaceNameb = "spaceNameb" + getRandomNumber();
        String spaceDesb = "descriptionb" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
        String spaceNamec = "spaceNamec" + getRandomNumber();
        String spaceDesc = "descriptionc" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamec, spaceDesc);

        info("SpacesAreFilteredByItsName");

        $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, 10000);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/a")).click();
        $(byXpath(" //*[@id=\"f0da4b11-96ff-4d12-9119-5731c979d541\"]/div/div/div/ul/li[1]/a")).click();
        $(byClassName("showInputSearch")).setValue(spaceNamea);
        $(byClassName("uiIconPLF24x24Search")).click();


        $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, 10000);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/a")).click();
        $(byXpath(" //*[@id=\"f0da4b11-96ff-4d12-9119-5731c979d541\"]/div/div/div/ul/li[1]/a")).click();
        $(byClassName("showInputSearch")).setValue(spaceNameb);
        $(byClassName("uiIconPLF24x24Search")).click();

        $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, 10000);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/a")).click();
        $(byXpath(" //*[@id=\"f0da4b11-96ff-4d12-9119-5731c979d541\"]/div/div/div/ul/li[1]/a")).click();
        $(byClassName("showInputSearch")).setValue(spaceNamec);
        $(byClassName("uiIconPLF24x24Search")).click();
    }

    @Test
    public void test05_SpacesAreFilteredByItsDescription() {
        info("create some spaces");
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        String spaceNameb = "spaceNameb" + getRandomNumber();
        String spaceDesb = "descriptionb" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
        String spaceNamec = "spaceNamec" + getRandomNumber();
        String spaceDesc = "descriptionc" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamec, spaceDesc);
        info("SpacesAreFilteredByItsName");
        navigationToolbar.goToSpaceAdminstration();
        $(byClassName("showInputSearch")).setValue("descriptiona");
        $(byClassName("uiIconPLF24x24Search")).setValue("descriptiona");

    }

    @Test
    public void test06_CheckcancelButtonFromTheConfirmationPopup() {
        info("create some spaces");
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        navigationToolbar.goToSpaceAdminstration();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconDeleteUser")).waitUntil(Condition.visible, Configuration.timeout).click();
        $(byClassName("modal-content")).find(byClassName("btn-primary")).click();

    }
    @Test
    public void test07_CheckXButtonFromTheConfirmationPopup() {
        info("create some spaces");
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        navigationToolbar.goToSpaceAdminstration();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconDeleteUser")).waitUntil(Condition.visible, Configuration.timeout).click();
        $(byClassName("modal-content")).find(byClassName("uiIconClose")).click();

    }
    @Test
    public void test08_CheckConfirmationPopupWhileDeleteSpace  () {
        info("create some spaces");
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        navigationToolbar.goToSpaceAdminstration();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconDeleteUser")).waitUntil(Condition.visible, Configuration.timeout).click();
        $(byText("Do you confirm the delete of this space ?")).waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("modal-content")).find(byClassName("btn-primary")).waitUntil(Condition.visible,Configuration.timeout);
        $(byClassName("modal-content")).find(byClassName("btn")).waitUntil(Condition.visible,Configuration.timeout);
        $(byClassName("modal-content")).find(byClassName("uiIconClose")).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
    }

    @Test
    public void test09_CheckButtonsInTheActionsColumn() {
        info("create some spaces");
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        navigationToolbar.goToSpaceAdminstration();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconDeleteUser")).waitUntil(Condition.visible, Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
    }
}