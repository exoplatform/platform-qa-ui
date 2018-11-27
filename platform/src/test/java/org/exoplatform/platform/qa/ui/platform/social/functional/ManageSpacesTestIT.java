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
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
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
        String spaceNameb = "spaceNameb" + getRandomNumber();
        String spaceDesb = "descriptionb" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNameb, spaceDesb);
        String spaceNamec = "spaceNamec" + getRandomNumber();
        String spaceDesc = "descriptionc" + getRandomNumber();
        homePagePlatform.goToMySpaces();
        spaceManagement.addNewSpaceSimple(spaceNamec, spaceDesc);
        info("Test 1: CheckElementsInTheManageSpacesTab");
        $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, 10000);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/a")).click();
        $(byXpath("//*[@id=\"4f91fdf7-ce43-4f17-9809-955c21cd6251\"]/div/div/div/ul/li[1]/a")).click();
        $(byXpath("//*[@id=\"4f91fdf7-ce43-4f17-9809-955c21cd6251\"]/div/div/div/div/div/div/table/tr[1]/th[1]")).waitUntil(Condition.visible, Configuration.timeout);
        $(byAttribute("src", "/eXoSkin/skin/images/system/SpaceAvtDefault.png")).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath("//*[@id=\"4f91fdf7-ce43-4f17-9809-955c21cd6251\"]/div/div/div/div/div/div/table/tr[1]/th[2]")).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath("//*[@id=\"4f91fdf7-ce43-4f17-9809-955c21cd6251\"]/div/div/div/div/div/div/table/tr[1]/th[3]")).waitUntil(Condition.visible, Configuration.timeout);
        $(byXpath("//*[@id=\"4f91fdf7-ce43-4f17-9809-955c21cd6251\"]/div/div/div/div/div/div/table/tr[3]/td[2]")).waitUntil(Condition.visible, Configuration.timeout);
    }

    @Test
    public void test02_CheckThatNothingHappensWhenClickOnSpacesNameAndAvatar(){

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

        info("CheckThatNothingHappensWhenClickOnSpacesNameAndAvatar");
        $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, 10000);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/a")).click();
        $(byXpath("//*[@id=\"4f91fdf7-ce43-4f17-9809-955c21cd6251\"]/div/div/div/ul/li[1]/a")).click();
        $(byXpath("//*[@id=\"4f91fdf7-ce43-4f17-9809-955c21cd6251\"]/div/div/div/div/div/div/table/tr[1]/th[1]")).waitUntil(Condition.visible, Configuration.timeout);
        $(byAttribute("src", "/eXoSkin/skin/images/system/SpaceAvtDefault.png")).click();
        $(byXpath("//*[@id=\"4f91fdf7-ce43-4f17-9809-955c21cd6251\"]/div/div/div/div/div/div/table")).find(byText(spaceNamec));
        $(byText(spaceNamec)).click();
    }

    @Test
    public void test02_CheckTheDisplayOfTheFilterFieldAboveTheSpaceManagementTable () {

        info("CheckTheDisplayOfTheFilterFieldAboveTheSpaceManagementTable");

        $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.appears, 10000);
        $(ELEMENT_TOOLBAR_ADMINISTRATION).click();
        $(byXpath("//*[@id=\"UISetupPlatformToolBarPortlet\"]/ul/li[1]/a")).click();
        $(byXpath("//*[@id=\"4f91fdf7-ce43-4f17-9809-955c21cd6251\"]/div/div/div/ul/li[1]/a")).click();
        $(byXpath("//*[@id=\"4f91fdf7-ce43-4f17-9809-955c21cd6251\"]/div/div/div/div/div/div/div[1]/input")).waitUntil(Condition.exist, Configuration.timeout);
    }}