package org.exoplatform.platform.qa.ui.selenium.locator.onlyOffice;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class OnlyOfficeLocator {

    public static final SelenideElement ELEMENT_EDIT_ONlINE_BUTTON = $(".uiIconEcmsOnlyofficeOpen.uiIconEcmsLightGray.uiIconEdit");
    public static final SelenideElement ELEMENT_EDIT_ONLINE_BUTTON_FROM_PREVIEW = $(".onlyOfficeEditBtn");


    public static final SelenideElement ELEMENT_DOWNLOAD_BUTTON_FROM_AS_TEXT = $(By.xpath("((//*[@class='pull-left statusAction'])[1]//a)[1]"));
    public static final SelenideElement ELEMENT_OPEN_BUTTON_FROM_AS_TEXT = $(By.xpath("((//*[@class='pull-left statusAction'])[1]//a)[2]"));
    public static final SelenideElement ELEMENT_EDIT_ONLINE_BUTTON_FROM_AS_TEXT = $(By.xpath("((//*[@class='pull-left statusAction'])[1]//a)[3]"));


    public static final SelenideElement ELEMENT_DOWNLOAD_BUTTON_FROM_PREVIEW_TEXT = $(".downloadBtn");
    public static final SelenideElement ELEMENT_EDIT_ONLINE_BUTTON_FROM_PREVIEW_TEXT = $(".onlyOfficeEditBtn");
    public static final SelenideElement ELEMENT_OPEN_IN_DOCUMENT_FROM_PREVIEW_TEXT = $(".openBtn");



}