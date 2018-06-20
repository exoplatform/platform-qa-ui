/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - Selenium (Legacy Code).
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - Selenium (Legacy Code); if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.selenium.testbase;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;


/**
 * Created by mgreau on 03/02/2017.
 */
public class LocatorTestBase {

    /* ======= Welcome Screen (Term and Conditions) ===== */
    public static final By ELEMENT_REGISTER_SKIP_BUTTON =
            By.xpath(".//*[@id='UIPortalLoginFormAction']/input[contains(@value,'Skip')]");

    public static final By ELEMENT_REGISTER_YOUR_SOFTWARE_BUTTON =
            By.xpath(".//*[@id='UIPortalLoginFormAction']/a");

    /* ======= Register Screen ===== */
    public static final By ELEMENT_REGISTER_SKIP_BTN =
            By.xpath(".//*[@id='UIPortalLoginFormAction']//*[@name='btnSkip']");

    /* ======= Welcome Screen (Term and Conditions) ===== */
    public static final By ELEMENT_FIRSTNAME_ACCOUNT = By.name("firstNameAccount");

    public static final By ELEMENT_LASTNAME_ACCOUNT = By.name("lastNameAccount");

    public static final By ELEMENT_EMAIL_ACCOUNT = By.name("emailAccount");

    public static final By ELEMENT_CONFIRM_PASS_ACCOUNT = By.name("confirmUserPasswordAccount");

    public static final By ELEMENT_ROOT_PASS_ACCOUNT = By.name("adminPassword");

    public static final By ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT = By.name("confirmAdminPassword");

    public static final By ELEMENT_AGREEMENT_CHECKBOX = By.xpath("//*[@id = 'agreement']");

    public static final By ELEMENT_INPUT_USERNAME = By.name("username");

    public static final String ELEMENT_INPUT_USERNAME_ID = "username";

    public static final By ELEMENT_CONTINUE_BUTTON =
            By.xpath("//button[text()='Continue' and @class='btn active']");

    public static final By ELEMENT_START_BUTTON = By.xpath("//button[text()='Start']");

    public static final By ELEMENT_SUBMIT_BUTTON = By.xpath("//*[text()='Submit']");

    public static final By ELEMENT_INPUT_PASSWORD = By.name("password");

    public static final String ELEMENT_INPUT_PASSWORD_ID = "UIPortalLoginFormControl";

    public static final By ELEMENT_ACCOUNT_NAME_LINK =
            By.xpath("//*[@id='UIUserPlatformToolBarPortlet']/a/img");

    public static final By ELEMENT_PLF_INFORMATION = By.id("platformInfoDiv");

    public static final String ELEMENT_TERM_CONDITION_BOX =
            "//div[@class='header' and text()='Terms and Conditions Agreement']/..";

    public static final By ELEMENT_CONTINUE_BUTTON_DISABLE =
            By.xpath("//button[text()='Continue' and @class='btn inactive']");

    public static final By ELEMENT_TERM_CONDITION_CONTENT =
            By.xpath("//div[@id='AccountSetup' and @class='content']");

    public static final By ELEMENT_ACCOUNT_SETUP =
            By.xpath("//div[@class='header' and text()='Account Setup']");

    public static final By ELEMENT_USER_ADMIN = By.id("adminFirstName");

    public static final By ELEMENT_SKIP_BUTTON = By.xpath("//button[text()='Skip']");

    public static final By ELEMENT_YOUR_ACCOUNT_LABEL =
            By.xpath("//h5[contains(text(), 'Create your account')]");

    public static final By ELEMENT_ADMIN_PASS_LABEL =
            By.xpath("//h5[contains(text(), 'Admin Password')]");

    public static final By ELEMENT_ACCOUNT_ERROR =
            By.xpath("//*[@class='accountSetupError']");

    // SSO Login with OpenAM
    public static final By ELEMENT_INPUT_PASSWORD_OPENAM = By.name("IDToken2");

    public static final By ELEMENT_INPUT_USERNAME_OPENAM = By.name("IDToken1");

    public static final By ELEMENT_SIGN_IN_BUTTON_OPENAM =
            By.xpath("//*[@class='button primary' and @value='Log In']");

    // SSO Login with CAS


    public static final By ELEMENT_INPUT_PASSWORD_CAS = By.id("UIPortalLoginFormControl");

    public static final By ELEMENT_INPUT_USERNAME_CAS = By.id("username");

    public static final SelenideElement ELEMENT_SIGN_IN_BUTTON_CAS =
            $(byXpath("//*[@id=\"UIPortalLoginFormAction\"]/button"));


    // Upload file popup
    public static final By ELEMENT_UPLOAD_SELECT_BUTTON =
            By.xpath("//*[@class='uploadButton']/*[@class='btn']");

    public static final By ELEMENT_UPLOAD_POPUP_FILE =
            By.xpath("//span[@class='PopupTitle popupTitle' and text()='Attach File']");

    public static final By ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_INPUT = By.name("file");

    public static final By ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_SAVE_BUTTON =
            By.xpath(".//*[@id='UIAttachFileForm']//button[text()='Save']");

    public static final String ELEMENT_UPLOAD_POPUP_NAMEFILE =
            "//*[@class='fileNameLabel' and contains(text(),'${fileName}')]";

    public static final By ELEMENT_SAVE_BTN = By.xpath("//*[text()='Save']");

  /* ======== End of Term and conditions ===== */

    public static final By ELEMENT_SKIP_REGISTER_BTN =
            By.xpath(".//*[@id='UIPortalLoginFormAction']//*[@value = 'Skip']");

    public static final By ELEMENT_CONTINUE_BTN =
            By.xpath(".//*[@id='UIPortalLoginFormAction']//*[@value = 'Continue']");
}
