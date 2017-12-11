package org.exoplatform.platform.qa.ui.platform.ecms;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_NAME_USER1;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.ecms.pageobject.DocumentPreview;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

public class EcmsDocumentPreviewTestIT extends Base {

  DocumentPreview  documentPreview;

  ActivityStream   activityStream;

  ManageLogInOut   manageLogInOut;

  HomePagePlatform homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    documentPreview = new DocumentPreview(this);
    activityStream = new ActivityStream(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:112333.</li>
   * <li>Test Case Name: Check the reader on the activity stream</li> Step Number:
   * 1 Step Name: Step 1. Share a PDF file activity. Step Description: - Connect
   * to Intranet Homepage - Attach a PDF file - Click on the button Share Input
   * Data: Expected Outcome: - An activity of the PDF file is displayed on the
   * stream Step Number: 2 Step Name: Step 2. View the attached file. Step
   * Description: - Click on the link "View" Input Data: Expected Outcome: - The
   * reader is displayed with a file content - Actions are displayed on the top
   * bar of the reader - On the right panel, the title of the file, the username
   * and the comment box are displayed
   */
  @Test
  public void test01_CheckReaderOnActivityStream() {
    info("Test 1: Check the reader on the activity stream");
    String file = "uploadFile.pdf";
    String activity1 = "activity1" + getRandomNumber();
    info("Share a pdf file activity");
    ELEMENT_TAB_LINK.click();
    refresh();
    ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
    ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("data/ecms/uploadFile.pdf");
    ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
    activityStream.addActivity(activity1, "");
    String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    info("Verify that the activity is shown on AS");
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).shouldHave(Condition.text(file));
    info("Open Preview mode by clicking on View link");
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).hover();
    $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).find(byClassName("infoFile"))
                                                         .waitUntil(Condition.visible, Configuration.timeout)
                                                         .click();
    info("The preview is shown successfully");
    info("Actions are displayed on the top bar of the reader");
    $(ELEMENT_ACTIONS_SIDERBAR_TOGGLE_BTN).waitUntil(Condition.visible, Configuration.timeout);
    ;
    $(ELEMENT_ACTIONS_SEARCH_BTN).waitUntil(Condition.visible, Configuration.timeout);
    ;
    $(ELEMENT_ACTIONS_ARROW_UP_BTN).waitUntil(Condition.visible, Configuration.timeout);
    ;
    $(ELEMENT_ACTIONS_ARROW_DOWN_BTN).waitUntil(Condition.visible, Configuration.timeout);
    ;
    $(ELEMENT_ACTIONS_PAGE_INPUT_NUMBER_BOX).waitUntil(Condition.visible, Configuration.timeout);
    ;
    $(ELEMENT_ACTIONS_ZOOM_OUT_BTN).waitUntil(Condition.visible, Configuration.timeout);
    ;
    $(ELEMENT_ACTIONS_ZOOM_IN_BTN).waitUntil(Condition.visible, Configuration.timeout);
    ;
    $(ELEMENT_ACTIONS_SCALE_SELECT_BOX).waitUntil(Condition.visible, Configuration.timeout);
    ;
    $(ELEMENT_ACTIONS_DOWNLOAD_BTN).waitUntil(Condition.visible, Configuration.timeout);
    ;
    $(ELEMENT_ACTIONS_FULLSCREEN_BTN).waitUntil(Condition.visible, Configuration.timeout);
    ;
    $(ELEMENT_ACTIONS_TOOLS_BTN).waitUntil(Condition.visible, Configuration.timeout);
    ;
    info("On the right panel, the title of the file,the username and the comment box are displayed");
    $(byXpath(ELEMENT_RIGHT_PANEL_PROFILE_AVATAR.replace("${fullName}", DATA_NAME_USER1))).waitUntil(Condition.visible,
                                                                                                     Configuration.timeout);
    ;
    $(byXpath(ELEMENT_RIGHT_PANEL_PROFILE_NAME_LINK.replace("${firstName}", DATA_USER1))).waitUntil(Condition.visible,
                                                                                                    Configuration.timeout);
    ;
    $(ELEMENT_RIGHT_PANEL_COMMENT_INPUT_BOX).waitUntil(Condition.visible, Configuration.timeout);
    ;
    $(ELEMENT_RIGHT_PANEL_COMMENT_AREA_BOX_WITH_NO_COMMENT).waitUntil(Condition.visible, Configuration.timeout);
    ;
    ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
    info("delete data");
    homePagePlatform.goToDocuments();
    ELEMENT_ICON_DEFAULT_VIEW.click();
    $(byText("Public")).click();
    $(byText("Activity Stream Documents")).waitUntil(Condition.visible, Configuration.timeout).click();
    ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(file)).contextClick();
    ELEMENT_POPUP_RIGHT_MENU_CONTAINER.find(ELEMENT_DELETE_BUTTON_IN_RIGHT_CLICK_CONTAINER).click();
    ELEMENT_BUTTON_CONFIRM_DELETE_FILE.waitUntil(Condition.visible, Configuration.timeout).click();

  }

}
