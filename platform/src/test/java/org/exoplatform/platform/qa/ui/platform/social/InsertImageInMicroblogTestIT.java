package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

/**
 * Created by dmardassi on 09/11/2017.
 */
@Tag("social")
@Tag("sniff")
public class InsertImageInMicroblogTestIT extends Base{
    NavigationToolbar navigationToolbar;

    AddUsers addUsers;

    ManageLogInOut manageLogInOut;

    HomePagePlatform homePagePlatform;

    ConnectionsManagement connectionsManagement;

    ActivityStream activityStream;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        navigationToolbar = new NavigationToolbar(this);
        homePagePlatform = new HomePagePlatform(this);
        addUsers = new AddUsers(this);
        manageLogInOut = new ManageLogInOut(this);
        connectionsManagement = new ConnectionsManagement(this);
        activityStream = new ActivityStream(this);
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    }

    @Test
    public void test01_InsertPngImage() {
        String activity = "Activity" + getRandomNumber();
        String imagePath = "data/social/Image_png.png";
        String imageName = "Image_png.png";
        activityStream.addActivityWithImageUsingMicroblogFromDesktop(activity,imagePath);
        homePagePlatform.goToDocuments();
        activityStream.verifyInsertedImageExistsInDocumentApp(imageName);
        homePagePlatform.goToHomePage();
        activityStream.deleteActivity(activity);
    }

    @Test
    public void test02_InsertPNGImage() {
        String activity = "Activity" + getRandomNumber();
        String imagePath = "data/social/Image_PNG_Uppercase.PNG";
        String imageName = "Image_PNG_Uppercase.PNG";
        activityStream.addActivityWithImageUsingMicroblogFromDesktop(activity,imagePath);
        homePagePlatform.goToDocuments();
        activityStream.verifyInsertedImageExistsInDocumentApp(imageName);
        homePagePlatform.goToHomePage();
        activityStream.deleteActivity(activity);
    }

    @Test
    public void test03_InsertJpgImage() {
        String activity = "Activity" + getRandomNumber();
        String imagePath = "data/social/Image_jpg.jpg";
        String imageName = "Image_jpg.jpg";
        activityStream.addActivityWithImageUsingMicroblogFromDesktop(activity,imagePath);
        homePagePlatform.goToDocuments();
        activityStream.verifyInsertedImageExistsInDocumentApp(imageName);
        homePagePlatform.goToHomePage();
        activityStream.deleteActivity(activity);
    }

    @Test
    public void test04_InsertJPGImage() {
        String activity = "Activity" + getRandomNumber();
        String imagePath = "data/social/Image_JPG_Uppercase.JPG";
        String imageName = "Image_JPG_Uppercase.JPG";
        activityStream.addActivityWithImageUsingMicroblogFromDesktop(activity,imagePath);
        homePagePlatform.goToDocuments();
        activityStream.verifyInsertedImageExistsInDocumentApp(imageName);
        homePagePlatform.goToHomePage();
        activityStream.deleteActivity(activity);
    }

    @Test
    public void test05_InsertJpegImage() {
        String activity = "Activity" + getRandomNumber();
        String imagePath = "data/social/Image_jpeg.jpeg";
        String imageName = "Image_jpeg.jpeg";
        activityStream.addActivityWithImageUsingMicroblogFromDesktop(activity,imagePath);
        homePagePlatform.goToDocuments();
        activityStream.verifyInsertedImageExistsInDocumentApp(imageName);
        homePagePlatform.goToHomePage();
        activityStream.deleteActivity(activity);
    }

    @Test
    public void test06_InsertJPEGImage() {
        String activity = "Activity" + getRandomNumber();
        String imagePath = "data/social/Image_JPEG_Uppercase.JPEG";
        String imageName = "Image_JPEG_Uppercase.JPEG";
        activityStream.addActivityWithImageUsingMicroblogFromDesktop(activity,imagePath);
        homePagePlatform.goToDocuments();
        activityStream.verifyInsertedImageExistsInDocumentApp(imageName);
        homePagePlatform.goToHomePage();
        activityStream.deleteActivity(activity);
    }

    @Test
    public void test07_InsertGifImage() {
        String activity = "Activity" + getRandomNumber();
        String imagePath = "data/social/Image_gif.gif";
        String imageName = "Image_gif.gif";
        activityStream.addActivityWithImageUsingMicroblogFromDesktop(activity,imagePath);
        homePagePlatform.goToDocuments();
        activityStream.verifyInsertedImageExistsInDocumentApp(imageName);
        homePagePlatform.goToHomePage();
        activityStream.deleteActivity(activity);
    }

    @Test
    public void test08_InsertGIFImage() {
        String activity = "Activity" + getRandomNumber();
        String imagePath = "data/social/Image_GIF_Uppercase.GIF";
        String imageName = "Image_GIF_Uppercase.GIF";
        activityStream.addActivityWithImageUsingMicroblogFromDesktop(activity,imagePath);
        homePagePlatform.goToDocuments();
        activityStream.verifyInsertedImageExistsInDocumentApp(imageName);
        homePagePlatform.goToHomePage();
        activityStream.deleteActivity(activity);
    }

    @Test
    public void test09_InsertImageWithInvalidExtension() {
        String activity = "Activity" + getRandomNumber();
        String imagePath = "data/social/Image_tif.tif";
        String imageName = "Image_tif.tif";
        activityStream.insertImageWithInvalidExtension(activity,imagePath);
        homePagePlatform.goToDocuments();
        activityStream.verifyInsertedImageWinthInvalidExtensionDoNotExistInDocumentApp(imageName);
        homePagePlatform.goToHomePage();
        activityStream.deleteActivity(activity);
    }

    @Test
    public void test10_InsertImageWithSizeMoreThan200Mo() {
        String activity = "Activity" + getRandomNumber();
        String imagePath = "data/social/Image_More_Than_200MO.png";
        String imageName = "Image_More_Than_200MO.png";
        activityStream.insertImageWithSizeMoreThan200Mo(activity,imagePath);
        homePagePlatform.goToDocuments();
        activityStream.verifyInsertedImageWinthInvalidExtensionDoNotExistInDocumentApp(imageName);
        homePagePlatform.goToHomePage();
        activityStream.deleteActivity(activity);
    }
}
