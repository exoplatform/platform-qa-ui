package org.exoplatform.platform.qa.ui.selenium.locator.news;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public final class NewsLocator {

  public static final SelenideElement ELEMENT_EDIT_BUTTON_EDIT                     = $(byId("newsEditButton"));

  public static final SelenideElement ELEMENT_EDIT_PIN_CHECKBOX                    = $(byId("pinArticle"));

  public static final SelenideElement ELEMENT_EDIT_BUTTON_UPDATE                   = $(byId("newsEdit"));

  public static final SelenideElement ELEMENT_NEWS_SUMMARY                         = $(byId("newsSummary"));

  public static final By              ELEMENT_DETAILS_SPAN_SUMMARY                 = By.xpath("//div[@id='newsSummary']/span");

  public static final By              ELEMENT_SPACE_NEWS_TAB                       =
                                                             By.xpath("//div[@id='ActivityComposerExt']/div[2]/a[@class='uinewsactivitycomposer']");

  public static final By              ELEMENT_SPACE_NEWS_CREATION_FORM             = By.id("newsActivityComposer");

  public static final By              ELEMENT_SPACE_NEWS_CREATION_FORM_INPUT_TITLE =
                                                                                   By.xpath("//*[@id='newsActivityComposer']//*[@id='newsForm']//*[@class='newsFormWrapper']//*[@class='newsFormInputAttachement']//*[@class='newsFormInput']//*[@class='formInputGroup']//*[@id='newsTitle']");

  public static final By              ELEMENT_SPACE_NEWS_CREATION_FORM_BUTTON_POST = By.xpath("//button[@id='newsPost']");

  public static final String          ELEMENT_SPACE_NEWS_ACTIVITY_INPUT_TITLE      =
                                                                              "//div[@class='newsTitle']/a[text()='${newsName}']";

  public static final SelenideElement ELEMENT_SPACE_NEWS_ACTIVITY_INPUT_CONTENT    =
                                                                                $(byId("cke_newsContent")).find(byClassName("cke_wysiwyg_frame"));
}
