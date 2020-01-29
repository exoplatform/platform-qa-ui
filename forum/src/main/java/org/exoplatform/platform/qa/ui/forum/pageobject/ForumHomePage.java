package org.exoplatform.platform.qa.ui.forum.pageobject;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.ELEMENT_SAVE_BTN;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ManageFileTestBase;

public class ForumHomePage {

  private final TestBase       testBase;

  public ManageAlert           alert;

  public Button                button;

  public ManageFileTestBase    mftb;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param testBase TestBase
   */
  public ForumHomePage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.mftb = new ManageFileTestBase(testBase);
  }

  /**
   * Go to home category
   */
  public void goToHomeCategory() {
    executeJavaScript("window.scrollBy(0,-2000)", "");
    if ($(ELEMENT_CATEGORY_BREADCUMB_HOME).is(Condition.exist)) {
      $(ELEMENT_CATEGORY_BREADCUMB_HOME).click();
    }
  }

  /**
   * select a item in Manage Category Menu By QuynhPT
   *
   * @param item specifAdministrationMenu
   */
  public void selectItemAdministrationMenu(specifAdministrationMenu item) {
    info("Waiting administration menu is shown");
    $(ELEMENT_ACTIONBAR_ADMINISTRATION).should(Condition.appears);
    info("Click on Manage menu");
    $(ELEMENT_ACTIONBAR_ADMINISTRATION).waitUntil(Condition.visible,Configuration.timeout).click();

    switch (item) {
    case SORT_SETTING:
      break;
    case CENSOR_KEYWORDS:
      break;
    case BBCODE:
      info("Click on BBCode link");
      $(ELEMENT_ACTIONBAR_ADMIN_BBCODE).waitUntil(Condition.visible,Configuration.timeout).click();
      break;
    case NOTIFICATIONS:
      break;
    case BANNED_IPS:
      break;
    case PRUNNING:
      break;
    case EXPORT:
      info("Export a category");
      $(ELEMENT_ACTIONBAR_ADMIN_EXPORT).waitUntil(Condition.visible,Configuration.timeout).click();
      break;
    case IMPORT:
      info("Import a category");
      $(ELEMENT_ACTIONBAR_ADMIN_IMPORT).waitUntil(Condition.visible,Configuration.timeout).click();
      break;
    default:
      break;
    }
  }

  /**
   * Go to a detail category in list By QuynhPT
   *
   * @param name String
   */
  public void goToCategory(String name) {
    $(byText(name)).click();
  }

  /**
   * Watch or UnWatch true is for watching false is for un-watching Update QuynhPT
   *
   * @param watch String
   */
  public void watchItem(boolean watch) {
    if (watch) {
      if (evt.waitForAndGetElement(ELEMENT_WATCH, testBase.getDefaultTimeout(), 0) != null) {
        info("Watch item");
        $(ELEMENT_WATCH).click();
        $(byText(MESSAGE_WATCH)).waitUntil(Condition.appears, Configuration.timeout);
        $(ELEMENT_OK_INFOR_POPUP).click();
        $(ELEMENT_UNWATCH).waitUntil(Condition.appears, Configuration.timeout);
        info("Watch item successfully");
      } else {
        info("Not found watch link");
      }
    } else {
      if (evt.waitForAndGetElement(ELEMENT_UNWATCH, testBase.getDefaultTimeout(), 0) != null) {
        info("Unwatch item");
        $(ELEMENT_UNWATCH).click();
        $(byText(MESSAGE_UNWATCH)).waitUntil(Condition.appears, Configuration.timeout);
        $(ELEMENT_OK_INFOR_POPUP).click();
        $(ELEMENT_WATCH).waitUntil(Condition.appears, Configuration.timeout);
        info("Unwatch item successfully");
      } else {
        info("Not found unwatch link");
      }
    }
  }

  /**
   * Open a forum
   *
   * @param name String
   */
  public void goToForum(String name) {
    info("Click on the forum with the name:" + name);
    ELEMENT_CATEGORY_CONTAINER.find(byText(name)).click();
  }

  /**
   * Add a BBcode Update QuynhPT
   *
   * @param tag String
   * @param replacement String
   * @param description String
   * @param example String
   * @param use String
   */
  public void addBBCode(String tag, String replacement, String description, String example, boolean use) {
    selectItemAdministrationMenu(specifAdministrationMenu.BBCODE);
    info("Click on Add button of Add BBCode popup");
    $(ELEMENT_ADMIN_BBCODE_ADDBBCODE).click();
    info("Input new tag");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_TAG).setValue(tag);
    info("Input new replacement");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_REPLACEMENT).setValue(replacement);
    info("Input new description");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_DESCRIPTION).setValue(description);
    info("Input new example");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_EXAMPLE).setValue(example);
    if (use == true)
      $(ELEMENT_BBCODE_USE_OPTION).click();
    info("Click on Save button and save all changes");
    $(ELEMENT_SAVE_BBCODE).click();
    info("Verify that BBcode is created");
    $(byText(tag.toUpperCase())).should(Condition.exist);
    info("Close the popup");
    $(ELEMENT_BBCODE_POPUP_CLOSEBTN).click();
  }

  /**
   * Edit BBcode
   *
   * @param newTag String
   * @param newReplacement String
   * @param newDescription  String
   * @param newExample String
   * @param use boolean
   */
  public void editBBCode(String newTag, String newReplacement, String newDescription, String newExample, boolean use) {
    selectItemAdministrationMenu(specifAdministrationMenu.BBCODE);
    info("Click on Edit BBcode");
    $(ELEMENT_BBCODE_EDITBBCODE).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Input new tag");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_TAG).waitUntil(Condition.visible,Configuration.timeout).setValue(newTag);
    info("Input new replacement");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_REPLACEMENT).waitUntil(Condition.visible,Configuration.timeout).setValue(newReplacement);
    info("Input new description");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_DESCRIPTION).waitUntil(Condition.visible,Configuration.timeout).setValue(newDescription);
    info("Input new example");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_EXAMPLE).waitUntil(Condition.visible,Configuration.timeout).setValue(newExample);
    if (use == true)
      $(ELEMENT_BBCODE_USE_OPTION).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Click on Save button and save all changes");
    $(ELEMENT_EDITSITE_SAVEBTN).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Verify that BBcode is edited with changes");
    $(byText(newTag.toUpperCase())).should(Condition.exist);
    info("Close the popup");
    $(ELEMENT_BBCODE_POPUP_CLOSEBTN).waitUntil(Condition.visible,Configuration.timeout).click();
  }

  /**
   * Delete a BBcode
   *
   * @param tag String
   */
  public void deleteBBcode(String tag) {
    selectItemAdministrationMenu(specifAdministrationMenu.BBCODE);
    info("Click on Delete of the tag");
    $(ELEMENT_BBCODE_DELETEBBCODE).click();
    info("Click on OK buton of Confirm popup");
    $(ELEMENT_BBCODE_CONFIRM_DELETETAG).click();
    info("Verify that BBcode is closed");
    $(byText(tag.toUpperCase())).shouldNot(Condition.exist);
    info("Close the popup");
    $(ELEMENT_BBCODE_POPUP_CLOSEBTN).click();
  }

  /**
   * Bookmark a category and a forum
   *
   * @param name String
   */
  public void bookmark(String name) {
    info("Click on Bookmark link on Action bar");
    $(ELEMENT_ACTIONBAR_BOOKMARK_ICON).click();
    info("Click on Bookmark link on Action bar to open Bookmark popup");
    if($(ELEMENT_ACTIONBAR_BOOKMARK_MANAGER).is(Condition.not(Condition.visible)))
    $(byClassName("moreItem")).click();
    $(ELEMENT_ACTIONBAR_BOOKMARK_MANAGER).click();
    info("Verify that the topic is bookmarked");
    $(byText(name)).should(Condition.exist);
    info("Delete the bookmark of the topic");
    $(ELEMENT_FORUM_BOOKMARK_DELETE).click();
    info("Verify that the bookmark is deleted");
    $(ELEMENT_FORUM_BOOKMARK_NAME).find(byText(name)).shouldNot(Condition.exist);
    info("Close the popup");
    $(ELEMENT_FORUM_BOOKMARK_CLOSE_ICON).click();
  }

  /**
   * Bookmark a topic
   *
   * @param name String
   */
  public void topicbookmark(String name) {
    info("Click on More actions button");
    $(MORE_ACTIONS_TOPIC).click();
    info("Click on Bookmark buton in Topic more actions list");
    $(ELEMENT_TOPIC_BOOKMARK).click();
    info("Click on Bookmark link on Action bar to open Bookmark popup");
    if(ELEMENT_MORE_IN_ACTION_BAR.is(Condition.visible))
      ELEMENT_MORE_IN_ACTION_BAR.click();
    $(ELEMENT_ACTIONBAR_BOOKMARK_MANAGER).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Verify that the topic is bookmarked");
    $(byText(name)).should(Condition.exist);
    info("Delete the bookmark of the topic");
    $(ELEMENT_FORUM_BOOKMARK_DELETE).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Verify that the bookmark is deleted");
    $(ELEMENT_FORUM_BOOKMARK_NAME).find(byText(name)).shouldNot(Condition.exist);
    info("Close the popup");
    $(ELEMENT_FORUM_BOOKMARK_CLOSE_ICON).click();
  }

  /**
   * Export a category from Action bar
   *
   * @param catName String
   * @param fileName String
   */
  public void exportCategory(String catName, String fileName) {
    selectItemAdministrationMenu(specifAdministrationMenu.EXPORT);
    info("Uncheck all category");
    ELEMENT_CHECKBOX_ALL_FORUM_CATEGORIE.click();
    info("Select the category:" + catName);
    ELEMENT_CHECKBOX_SELECT_ONE_FORUM_CATEGORIE.click();
    info("input name");
    $(ELEMENT_FILENAME_INPUT).setValue(fileName);
    info("Save all changes");
    $(ELEMENT_SAVE_BTN).click();
  }

  /**
   * Open a topic
   *
   * @param name String
   */
  public void goToTopic(String name) {
    info("Click on the topic with the name:" + name);
    $(byText(name)).click();
  }

  /**
   * Go to Private Message
   */
  public void goToPrivateMessage() {
    // TODO Auto-generated method stub
    info("Click on Private Message button");
    $(ELEMENT_ACTIONBAR_PRIVATE_MESSAGE).click();
  }

  /**
   * list sublinks in Administration menu
   *
   * @author quynhpt
   */
  public enum specifAdministrationMenu {
    SORT_SETTING, CENSOR_KEYWORDS, NOTIFICATIONS, BBCODE, PRUNNING, BANNED_IPS, EXPORT, IMPORT;
  }

}
