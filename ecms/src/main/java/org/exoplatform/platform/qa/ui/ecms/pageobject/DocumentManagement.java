package org.exoplatform.platform.qa.ui.ecms.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.TestBase;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;

/**
 * Created by ilyes on 30/10/17.
 */
public class DocumentManagement {
    public TestBase testBase;

    public DocumentManagement(TestBase testBase) {
        this.testBase = testBase;
    }

    public void goToListView() {
        ELEMENT_ICON_LIST_VIEW.click();
    }

    public void goToIconsView() {
        ELEMENT_ICON_DEFAULT_VIEW.click();
    }

    public void goToPublicFolder() {
        $(ELEMENT_PUBLIC_FOLDER).click();

    }

    public void createFolder(String path, String folder, Boolean righclick) {
        ELEMENT_INPUT_PATH.setValue(path).waitUntil(Condition.visible,2000).pressEnter();
        $(ELEMENT_ACCOUNT_NAME_LINK).waitUntil(Condition.visible,2000).click();
        if (righclick) {
            ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.waitUntil(Condition.visible, Configuration.timeout).contextClick();
            $(byId("JCRContextMenu")).find(ELEMENT_BUTTON_ADD_FOLDAR).waitUntil(Condition.visible,2000).click();
        } else {
            ELEMENT_ACTION_BAR_MENU.find(ELEMENT_BUTTON_ADD_FOLDAR)
                    .parent()
                    .waitUntil(Condition.visible, Configuration.timeout)
                    .click();
        }
        ELEMENT_INPUT_NAME_FOLDER.setValue(folder);
        ELEMENT_BUTTON_CONFIRM_ADD_FOLDER.waitUntil(Condition.visible,2000).click();
        ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(folder)).waitUntil(Condition.visible, Configuration.collectionsTimeout);
    }

    public void goToFileFolder() {
        $(byXpath("//*[@data-original-title='Go to folder']")).waitUntil(Condition.visible, Configuration.timeout).click();

    }

    public void goBackToPreviousPath() {
        $(byXpath(ELEMENT_GO_BACK_PREVIOUS_PATH)).waitUntil(Condition.visible, Configuration.timeout).click();

    }

    public void deleteFolder(String path, String folder, Boolean rightClick) {
        ELEMENT_INPUT_PATH.setValue(path).pressEnter();
        if (rightClick) {
            $(ELEMENT_ACCOUNT_NAME_LINK).click();
            ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(folder)).contextClick();
            ELEMENT_POPUP_RIGHT_MENU_CONTAINER.find(ELEMENT_DELETE_BUTTON_IN_RIGHT_CLICK_CONTAINER).click();
        } else {
            if (ELEMENT_LIST_DOCUMENT_FOLDER.find(byText(folder))
                    .parent()
                    .parent()
                    .parent()
                    .find(ELEMENT_ICON_CHECKBOX)
                    .is(Condition.not(Condition.selected))) {
                ELEMENT_LIST_DOCUMENT_FOLDER.find(byText(folder)).parent().parent().parent().find(ELEMENT_ICON_CHECKBOX).parent().click();
            }
            ELEMENT_BUTTON_DELETE_FOLDER_DOCUMENT.click();
        }
        ELEMENT_BUTTON_CONFIRM_DELETE.waitUntil(Condition.visible, Configuration.timeout).click();
        $(byText(folder)).waitUntil(Condition.not(Condition.visible), Configuration.timeout);

    }

    public void renameFolderInAdminView(String path, String folder, String newFolder, Boolean rightClick) {
        ELEMENT_INPUT_PATH.setValue(path).pressEnter();
        if (rightClick) {
            $(ELEMENT_ACCOUNT_NAME_LINK).click();
            ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(folder)).contextClick();
            ELEMENT_POPUP_RIGHT_MENU_CONTAINER.find(ELEMENT_RENAME_BUTTON_IN_RIGHT_CLICK).click();
        } else {
            if (ELEMENT_LIST_DOCUMENT_FOLDER.find(byText(folder))
                    .parent()
                    .parent()
                    .parent()
                    .find(ELEMENT_ICON_CHECKBOX)
                    .is(Condition.not(Condition.selected))) {
                ELEMENT_LIST_DOCUMENT_FOLDER.find(byText(folder)).parent().parent().parent().find(ELEMENT_ICON_CHECKBOX).parent().click();
            }
            ELEMENT_BUTTON_RENAME_FOLDER.click();
        }
        ELEMENT_INPUT_REMANE_FOLDER.setValue(newFolder);
        ELEMENT_BUTTON_CONFIRM_RENAME.click();

    }

    public void deleteFile(String path, String file, Boolean rightClick) {
        ELEMENT_INPUT_PATH.setValue(path).pressEnter();
        if (rightClick) {
            ELEMENT_LIST_FOLDER_IN_DEFAULT_VIEW.find(byText(file)).contextClick();
            ELEMENT_POPUP_RIGHT_MENU_CONTAINER.find(ELEMENT_DELETE_BUTTON_IN_RIGHT_CLICK_CONTAINER).click();
        } else {
            if (ELEMENT_LIST_DOCUMENT_FOLDER.find(byText(file))
                    .parent()
                    .parent()
                    .parent()
                    .find(ELEMENT_ICON_CHECKBOX)
                    .is(Condition.not(Condition.selected))) {
                ELEMENT_LIST_DOCUMENT_FOLDER.find(byText(file)).parent().parent().parent().find(ELEMENT_ICON_CHECKBOX).parent().click();
            }
            ELEMENT_BUTTON_DELETE_FOLDER_DOCUMENT.click();
        }
        ELEMENT_BUTTON_CONFIRM_DELETE_FILE.waitUntil(Condition.visible, Configuration.timeout).click();

    }

    public void goToPermissions() {
        if (ELEMENT_BUTTON_PERMISSION.is(Condition.not(Condition.visible))) {
            $(ELEMENT_ACTIONBAR_MORE).click();
            ELEMENT_BUTTON_PERMISSION_IN_MORE_DROP_DOWN_MENU.click();
        } else {
            ELEMENT_BUTTON_PERMISSION.click();
        }
    }
}
