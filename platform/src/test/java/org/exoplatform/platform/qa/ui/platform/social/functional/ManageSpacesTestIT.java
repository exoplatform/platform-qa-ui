package org.exoplatform.platform.qa.ui.platform.social.functional;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.core.PLFData.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_NOTIFICATION_DROPDOWN;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_MY_SPACE_SEARCH_BTN;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_MY_SPACE_SEARCH_TEXT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
@Tag("functional")
@Tag("social")
public class ManageSpacesTestIT extends Base {
    NavigationToolbar navigationToolbar;
    ManageLogInOut manageLogInOut;
    HomePagePlatform homePagePlatform;
    SpaceManagement spaceManagement;
    AddUsers addUsers;
    SpaceHomePage spaceHomePage;
    SpaceSettingManagement spaceSettingManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        manageLogInOut = new ManageLogInOut(this);
        homePagePlatform = new HomePagePlatform(this);
        manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
        spaceManagement = new SpaceManagement(this);
        addUsers = new AddUsers(this);
        spaceHomePage = new SpaceHomePage(this);
        spaceSettingManagement = new SpaceSettingManagement(this);
    }

    @Test
    public void test01_CheckTheNewSpacesSubmenuInTheAdministrationMenu() {
        info("Test 1: CheckTheNewSpacesSubmenuInTheAdministrationMenu");
        $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, Configuration.timeout);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        $(byAttribute("href", "/portal/g/:platform:administrators/spacesAdministration")).waitUntil(Condition.visible, Configuration.timeout);

    }

    @Test
    public void test02_CheckElementsInTheManageSpacesTab() {
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
    public void test03_CheckThatNothingHappensWhenClickOnSpacesNameAndAvatar() {

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
    public void test04_CheckTheDisplayOfTheFilterFieldAboveTheSpaceManagementTable() {

        info("CheckTheDisplayOfTheFilterFieldAboveTheSpaceManagementTable");
        navigationToolbar.goToSpaceAdminstration();
        $(byClassName("showInputSearch")).waitUntil(Condition.visible, Configuration.timeout);
    }

    @Test
    public void test05_CheckcancelButtonFromTheConfirmationPopup() {
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
    public void test06_CheckXButtonFromTheConfirmationPopup() {
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
    public void test07_CheckConfirmationPopupWhileDeleteSpace() {
        info("create some spaces");
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconDeleteUser")).waitUntil(Condition.visible, Configuration.timeout).click();
        $(byText("Do you confirm the delete of this space ?")).waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("modal-content")).find(byClassName("btn-primary")).waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("modal-content")).find(byClassName("btn")).waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("modal-content")).find(byClassName("uiIconClose")).click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
    }

    @Test
    public void test08_CheckButtonsInTheActionsColumn() {
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

    @Test
    public void test09_CheckTheExistanceOfTheSpacesInTheManageSpacesTab() {

        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        String spaceNameb = "spaceNameb" + getRandomNumber();
        String spaceDesb = "descriptionb" + getRandomNumber();
        String spaceNamec = "spaceNamec" + getRandomNumber();
        String spaceDesc = "descriptionc" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamec, spaceDesc);
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceNameb)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceNamec)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
        spaceManagement.deleteSpace(spaceNameb, false);
        spaceManagement.deleteSpace(spaceDesc, false);
    }

    @Test
    public void test10_CheckEmptyTableIfNoSpacesHasBeenCreated() {
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        String spaceNameb = "spaceNameb" + getRandomNumber();
        String spaceDesb = "descriptionb" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
        spaceManagement.deleteSpace(spaceNameb, false);
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("uiGrid")).find(byText("Empty data")).parent().parent().waitUntil(Condition.visible, Configuration.timeout);

    }

    @Test
    public void test11_SpaceAdministrationPageWillBeRefreshedOnceDeleteASpace() {
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        String spaceNameb = "spaceNameb" + getRandomNumber();
        String spaceDesb = "descriptionb" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceNameb)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        refresh();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNameb, false);
    }

    @Test
    public void test12_ThelistOfSpaceWillBeUpdatedOnlyWhenRefreshTheSpaceManagementPage() {

        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        String spaceNameb = "spaceNameb" + getRandomNumber();
        String spaceDesb = "descriptionb" + getRandomNumber();
        String spaceNamec = "spaceNamec" + getRandomNumber();
        String spaceDesc = "descriptionc" + getRandomNumber();
        String spaceNamed = "spaceNamed" + getRandomNumber();
        String spaceDesd = "descriptiond" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamec, spaceDesc);
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceNameb)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceNamec)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        switchTo().defaultContent();
        manageLogInOut.signIn(DATA_USER2, DATA_PASS);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamed, spaceDesd);
        spaceHomePage.goToSpaceSettingTab();
        spaceSettingManagement.goToMemberTab();
        ELEMENT_INPUT_INVITE_USER.sendKeys(PLFData.DATA_USER1);
        $(ELEMENT_SPACE_BTN_INVITE).click();
        manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
        homePagePlatform.goToMySpaces();
        spaceManagement.goToInvitationsReceivedTab();
        spaceManagement.acceptAInvitation(spaceNamed);
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("uiGrid")).find(byText(spaceDesd)).parent().parent().waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        refresh();
        $(byClassName("uiGrid")).find(byText(spaceDesd)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
        spaceManagement.deleteSpace(spaceNameb, false);
        spaceManagement.deleteSpace(spaceNamec, false);
        manageLogInOut.signIn(DATA_USER2, DATA_PASS);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamed, false);

    }

    @Test
    public void test13_CheckTheDisplayOfTheSpaceEditPage() {
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout).click();
        switchTo().window(1);
        $(byId("UISpaceSetting")).waitUntil(Condition.visible, Configuration.timeout);
        $(byId("displayName")).setValue(spaceNamea + "zfzff");
        $(byId("description")).setValue(spaceDesa + "zfzff");
        $(ELEMENET_SPACE_UPDATE_SAVE_BTN).click();
        switchTo().window(0);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea + "zfzff", false);

    }

    @Test
    public void test14_CheckThatNothingHappensWhenClickOnSpaceNameAvatar() {
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "descriptiona" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().click();
        $(byClassName("uiGrid")).find(byClassName("avatar")).parent().parent().click();
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);

    }

    @Test
    public void test15_SpacesFilteredByItsDescription() {

        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "spaceDesa" + getRandomNumber();
        String spaceNameb = "spaceNameb" + getRandomNumber();
        String spaceDesb = "descriptionb" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
        homePagePlatform.goToMySpaces();
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("showInputSearch")).waitUntil(Condition.visible, Configuration.timeout);
        info("Input the space into search text box");
        $(byClassName("showInputSearch")).setValue(spaceDesa);
        $(byClassName("showInputSearch")).click();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceNameb)).parent().parent().waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
        spaceManagement.deleteSpace(spaceNameb, false);
    }

    @Test
    public void test16_SpacesFilteredByItsName() {
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "spaceDesa" + getRandomNumber();
        String spaceNameb = "spaceNameb" + getRandomNumber();
        String spaceDesb = "descriptionb" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
        homePagePlatform.goToMySpaces();
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("showInputSearch")).waitUntil(Condition.visible, Configuration.timeout);
        info("Input the space into search text box");
        $(byClassName("showInputSearch")).setValue(spaceNamea);
        $(byClassName("showInputSearch")).click();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceNameb)).parent().parent().waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
        spaceManagement.deleteSpace(spaceNameb, false);
    }

    @Test
    public void test17_CheckTheplaceholderIntheFilterFiled() {
        //this TC cover also case [ID:SM_04b] the filter engine will be done on Space name and Descriptions fields when typing charactersand[ID:SM_04a]
        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "spaceDescription" + getRandomNumber();
        String spaceNameb = "spaceNameb" + getRandomNumber();
        String spaceNamec = "spaceNamec" + getRandomNumber();
        String spaceNamed = "spaced" + getRandomNumber();
        String spaceDesd = "spaceDesd" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesa);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamec, spaceDesa);
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamed, spaceDesd);
        homePagePlatform.goToMySpaces();
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("showInputSearch")).waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("showInputSearch")).setValue(spaceDesa);
        $(byClassName("showInputSearch")).click();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceNamed)).parent().parent().waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceNameb)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceNamec)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea, false);
        spaceManagement.deleteSpace(spaceNameb, false);
        spaceManagement.deleteSpace(spaceNamec, false);
        spaceManagement.deleteSpace(spaceNamed, false);
    }

    @Test
    public void test18_CheckDisplayofPaginationWhenSpaceAdministrationContainsMoreThan30Spaces() {

        ArrayList<String> spaceList = new ArrayList<String>();
        spaceList.add("space" + getRandomNumber());
        spaceList.add("space2" + getRandomNumber());
        spaceList.add("space3" + getRandomNumber());
        spaceList.add("space4" + getRandomNumber());
        spaceList.add("space5" + getRandomNumber());
        spaceList.add("space6" + getRandomNumber());
        spaceList.add("space7" + getRandomNumber());
        spaceList.add("space8" + getRandomNumber());
        spaceList.add("space9" + getRandomNumber());
        spaceList.add("space10" + getRandomNumber());
        spaceList.add("space11" + getRandomNumber());
        spaceList.add("space12" + getRandomNumber());
        spaceList.add("space13" + getRandomNumber());
        spaceList.add("space14" + getRandomNumber());
        spaceList.add("space15" + getRandomNumber());
        spaceList.add("space16" + getRandomNumber());
        spaceList.add("space17" + getRandomNumber());
        spaceList.add("space18" + getRandomNumber());
        spaceList.add("space19" + getRandomNumber());
        spaceList.add("space20" + getRandomNumber());
        spaceList.add("space21" + getRandomNumber());
        spaceList.add("space22" + getRandomNumber());
        spaceList.add("space23" + getRandomNumber());
        spaceList.add("space24" + getRandomNumber());
        spaceList.add("space25" + getRandomNumber());
        spaceList.add("space26" + getRandomNumber());
        spaceList.add("space27" + getRandomNumber());
        spaceList.add("space28" + getRandomNumber());
        spaceList.add("space29" + getRandomNumber());
        spaceList.add("space30" + getRandomNumber());

        for (int i = 0; i < 30; i++) {
            homePagePlatform.goToMySpaces();
            spaceManagement.addNewSpaceSimple(spaceList.get(i), "");
        }
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("pagination")).find(byClassName("active")).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        for (int i = 0; i < 30; i++) {
            homePagePlatform.goToMySpaces();
            spaceManagement.deleteSpace(spaceList.get(i), false);
        }
    }

    @Test
    public void test19_Only30SpacesDisplayedByDefaultPerView() {

        ArrayList<String> spaceList = new ArrayList<String>();
        spaceList.add("space" + getRandomNumber());
        spaceList.add("space2" + getRandomNumber());
        spaceList.add("space3" + getRandomNumber());
        spaceList.add("space4" + getRandomNumber());
        spaceList.add("space5" + getRandomNumber());
        spaceList.add("space6" + getRandomNumber());
        spaceList.add("space7" + getRandomNumber());
        spaceList.add("space8" + getRandomNumber());
        spaceList.add("space9" + getRandomNumber());
        spaceList.add("space10" + getRandomNumber());
        spaceList.add("space11" + getRandomNumber());
        spaceList.add("space12" + getRandomNumber());
        spaceList.add("space13" + getRandomNumber());
        spaceList.add("space14" + getRandomNumber());
        spaceList.add("space15" + getRandomNumber());
        spaceList.add("space16" + getRandomNumber());
        spaceList.add("space17" + getRandomNumber());
        spaceList.add("space18" + getRandomNumber());
        spaceList.add("space19" + getRandomNumber());
        spaceList.add("space20" + getRandomNumber());
        spaceList.add("space21" + getRandomNumber());
        spaceList.add("space22" + getRandomNumber());
        spaceList.add("space23" + getRandomNumber());
        spaceList.add("space24" + getRandomNumber());
        spaceList.add("space25" + getRandomNumber());
        spaceList.add("space26" + getRandomNumber());
        spaceList.add("space27" + getRandomNumber());
        spaceList.add("space28" + getRandomNumber());
        spaceList.add("space29" + getRandomNumber());

        for (int i = 0; i < 29; i++) {
            homePagePlatform.goToMySpaces();
            spaceManagement.addNewSpaceSimple(spaceList.get(i), "");
        }
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("pagination")).find(byClassName("active")).parent().parent().waitUntil(Condition.not(Condition.visible), Configuration.timeout);
        for (int i = 0; i < 29; i++) {
            homePagePlatform.goToMySpaces();
            spaceManagement.deleteSpace(spaceList.get(i), false);
        }
    }

    //Bug
    @Test
    public void test20_SpaceInformationUpdatedOnlyWhenRefreshTheSpaceManagementPage() {

        String spaceNamea = "spaceNamea" + getRandomNumber();
        String spaceDesa = "spaceDesa" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamea, spaceDesa);
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceDesa)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconDeleteUser")).waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiIconEdit")).waitUntil(Condition.visible, Configuration.timeout).click();
        switchTo().window(1);
        $(byId("UISpaceSetting")).waitUntil(Condition.visible, Configuration.timeout);
        $(byId("displayName")).setValue(spaceNamea + "zfzff");
        $(byId("description")).setValue(spaceDesa + "zfzff");
        $(ELEMENET_SPACE_UPDATE_SAVE_BTN).click();
        switchTo().window(0);
        navigationToolbar.goToSpaceAdminstration();
        $(byClassName("uiGrid")).find(byText(spaceNamea)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceDesa)).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        refresh();
        $(byClassName("uiGrid")).find(byText(spaceNamea + "zfzff")).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        $(byClassName("uiGrid")).find(byText(spaceDesa + "zfzff")).parent().parent().waitUntil(Condition.visible, Configuration.timeout);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceNamea + "zfzff", false);
    }
    //Bug
    @Test
    public void test21_CheckTheOrderOfTheCreatedSpaces() {
        String spaceName1 = "spaceNamea" + getRandomNumber();
        String spaceName2 = "spaceNameb" + getRandomNumber();
        String spaceName3 = "spaceNamec" + getRandomNumber();
        String spaceName4 = "spaced" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceName1, "");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceName2, "");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceName3, "");
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceName4, "");
        navigationToolbar.goToSpaceAdminstration();
        $(byText("Manage Spaces")).click();
        $(byClassName("UIRowContainer")).findAll(byClassName("tab-content")).get(0).find(byText(spaceName4)).should(Condition.exist);
      // $(byClassName("UIRowContainer")).findAll(byClassName("tab-content")).get(1).find(byText(spaceName3)).should(Condition.exist);
       // $(byClassName("UIRowContainer")).findAll(byClassName("tab-content")).get(2).find(byText(spaceName2)).should(Condition.exist);
       //$(byClassName("UIRowContainer")).findAll(byClassName("tab-content")).get(3).find(byText(spaceName1)).should(Condition.exist);
        homePagePlatform.goToMySpaces();
        spaceManagement.deleteSpace(spaceName1, false);
        spaceManagement.deleteSpace(spaceName2, false);
        spaceManagement.deleteSpace(spaceName3, false);
        spaceManagement.deleteSpace(spaceName4, false);
    }


}