package org.exoplatform.platform.qa.ui.news;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NewsLocator {

  public static final SelenideElement NEWS_COMPOSER_LINK = $(".uiComposer").find(".newsEditorComposerLink");

  public static final SelenideElement NEWS_EDITOR_LINK = $("#newsEditButton");

  public static final SelenideElement NEWS_COMPOSER_POST_BUTTON = $("#newsActivityComposer").find("#newsPost");

  public static final SelenideElement NEWS_EDITOR_UPDATE_BUTTON = $("#newsActivityComposer").find("#newsEdit");

  public static final SelenideElement NEWS_EDITOR_UPDATE_AND_POST_BUTTON = $("#newsActivityComposer").find("#newsUpdateAndPost");

  public static final SelenideElement NEWS_COMPOSER_TITLE_INPUT = $("#newsActivityComposer").find("#newsTitle");

  public static final SelenideElement NEWS_COMPOSER_SUMMARY_INPUT = $("#newsActivityComposer").find("#newsSummary");

  public static final SelenideElement NEWS_COMPOSER_CONTENT_INPUT = $("#newsActivityComposer").find(".cke_wysiwyg_frame");

  public static final SelenideElement NEWS_DETAILS_CONTAINER = $(".newsDetails");

  public static final SelenideElement NEWS_DETAILS_TITLE = NEWS_DETAILS_CONTAINER.find(".newsTitle").find(".newsTitleLink");

  public static final SelenideElement NEWS_DETAILS_BODY = NEWS_DETAILS_CONTAINER.find(".newsBody");
}
