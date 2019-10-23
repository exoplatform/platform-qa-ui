package org.exoplatform.platform.qa.ui.selenium.locator.onlyOffice;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class OnlyOfficeLocator {
    public static final SelenideElement ELEMENT_EDIT_ONlINE_BUTTON = $(By.xpath("//*[@class='uiIconEcmsOnlyofficeOpen uiIconEcmsLightGray uiIconEdit']"));
    public static By ELEMENT_EDIT_ONLINE_BUTTON_FROM_PREVIEW = byClassName("onlyOfficeEditBtn");
}
