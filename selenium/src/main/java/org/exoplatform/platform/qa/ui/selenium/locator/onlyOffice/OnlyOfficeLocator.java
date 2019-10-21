package org.exoplatform.platform.qa.ui.selenium.locator.onlyOffice;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class OnlyOfficeLocator {

    public static final By ELEMENT_EDIT_ONlINE_BUTTON =
            By.xpath("//*[@class='uiIconEcmsOnlyofficeOpen uiIconEcmsLightGray uiIconEdit']");
    public static final SelenideElement ELEMENT_EDIT_ONLINE_BUTTON_FROM_AS = $(byXpath("//i[@class='uiIconEcmsOnlyofficeOpen uiIconEcmsLightGray uiIconEdit']"));
    public static final SelenideElement ELEMENT_EDIT_ONLINE_BUTTON_FROM_PREVIEW = $(byXpath("//div[@class='onlyOfficeEditBtn']//i[@class='uiIconEcmsOnlyofficeOpen uiIconEcmsLightGray uiIconEdit']"));


}
