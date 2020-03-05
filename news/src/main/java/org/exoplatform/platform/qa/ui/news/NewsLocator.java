package org.exoplatform.platform.qa.ui.news;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class NewsLocator {

  public static final SelenideElement NEWS_COMPOSER_LINK = $(".uiComposer").find(".newsEditorComposerLink");

  public static final SelenideElement NEWS_EDITOR_LINK = $("#newsEditButton");

  public static final SelenideElement NEWS_COMPOSER_POST_BUTTON = $("#newsActivityComposer").find("#newsPost");

  public static final SelenideElement NEWS_EDITOR_UPDATE_BUTTON = $("#newsActivityComposer").find("#newsEdit");

  public static final SelenideElement NEWS_EDITOR_UPDATE_AND_POST_BUTTON = $("#newsActivityComposer").find("#newsUpdateAndPost");

  public static final SelenideElement NEWS_DRAFT_STATUS = $("#newsActivityComposer").find(".draftSavingStatus");

  public static final SelenideElement NEWS_COMPOSER_TITLE_INPUT = $("#newsActivityComposer").find("#newsTitle");

  public static final SelenideElement NEWS_COMPOSER_SUMMARY_INPUT = $("#newsActivityComposer").find("#newsSummary");

  public static final SelenideElement NEWS_COMPOSER_CONTENT_INPUT = $("#newsActivityComposer").find(".cke_wysiwyg_frame");

  public static final SelenideElement NEWS_COMPOSER_ILLUSTRATION_INPUT = $("#newsActivityComposer").find("#uploadedFile");

  public static final SelenideElement NEWS_DETAILS_CONTAINER = $(".newsDetails");

  public static final SelenideElement NEWS_DETAILS_TITLE = NEWS_DETAILS_CONTAINER.find(".newsTitle").find(".newsTitleLink");

  public static final SelenideElement NEWS_DETAILS_BODY = NEWS_DETAILS_CONTAINER.find("#newsBody");

  public static final SelenideElement NEWS_DETAILS_AUTHOR = NEWS_DETAILS_CONTAINER.find(".newsAuthor").find(".newsAuthorName");

  public static final SelenideElement NEWS_DETAILS_UPDATER = NEWS_DETAILS_CONTAINER.find(".newsUpdater").find(".newsUpdaterName");

  public static final SelenideElement NEWS_DETAILS_BUTTON_PIN_UNPIN = NEWS_DETAILS_CONTAINER.find("#newsPinButton");

  public static final SelenideElement NEWS_DETAILS_BUTTON_EDIT = NEWS_DETAILS_CONTAINER.find("#newsEditButton");

  public static final SelenideElement NEWS_DETAILS_BUTTON_SHARE = NEWS_DETAILS_CONTAINER.find("#newsShareButton");

  public static final SelenideElement NEWS_DETAILS_PUBLICATION_DATE = NEWS_DETAILS_CONTAINER.find(".newsInformationValue.newsPostedDate");

  public static final SelenideElement NEWS_DETAILS_ILLUSTRATION = NEWS_DETAILS_CONTAINER.find(".illustrationPicture");

  public static final SelenideElement ELEMENT_GROUPS_PANEL = $(".nodeGroup");

  public static final SelenideElement ELEMENT_USERNAME_INPUT = $(".control-group").find("#username");

  public static final SelenideElement ELEMENT_MEMBERSHIP_SELECT = $(".uiSelectbox").find("#membership");

  public static final SelenideElement ELEMENT_SAVE_BUTTON = $(".uiAction ").find(".btn ");

  public static final SelenideElement ELEMENT_GROUPS_BUTTON = $(".uiGrayLightBox").find(".groupButton");

  public static final SelenideElement ELEMENT_SPACES_BUTTON = ELEMENT_GROUPS_PANEL.find(by("title", "Spaces"));

  public static final SelenideElement ELEMENT_PLATFORM_BUTTON = ELEMENT_GROUPS_PANEL.find(by("title", "Platform"));

  public static final SelenideElement ELEMENT_CONTENT_MANAGEMENT_BUTTON = ELEMENT_GROUPS_PANEL.find(by("title", "Content Management"));

  public static final SelenideElement ELEMENT_USERNAME_PUBLISHER_INPUT = $(".control-group").find(".controls").find("#username");

 public static final SelenideElement CONFIRM_PIN_ARTICLE = $(byXpath ("//div[@class='uiAction uiActionBorder']//a[@class='btn'][1]"));

 public static final SelenideElement ALERT_SUCCESS = $("#messagePin ").find(".alert.alert-success ");

 public static final SelenideElement BLOC_LATEST_NEWS = $(".latestNewsContent ");

 public static final SelenideElement NEWS_PINNED_ARTICLE = $(".contentTitle ");

 public static final SelenideElement NEWS_CREATION_FORM_PINUNPIN_BUTTON = $("#newsPinButton ");

 public static final SelenideElement ELEMENT_SPACE_NAME = $("#informationNews").find(".newsSpace");

}

