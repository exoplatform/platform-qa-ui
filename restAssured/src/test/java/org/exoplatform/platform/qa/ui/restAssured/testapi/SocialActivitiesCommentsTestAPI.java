package org.exoplatform.platform.qa.ui.restAssured.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.restAssured.pageobject.SocialActivitiesCommentsManagement;
import org.exoplatform.platform.qa.ui.restAssured.pageobject.SocialActivitiesManagement;
import org.junit.Assert;
import org.junit.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class SocialActivitiesCommentsTestAPI extends Base {

  SocialActivitiesCommentsManagement socialActivitiesCommentsManagement = new SocialActivitiesCommentsManagement(this);

  SocialActivitiesManagement socialActivitiesManagement = new SocialActivitiesManagement(this);

  @Test
  public void testResponseCode() {
    Response resp = RestAssured.given().auth().basic(PLFData.username, PLFData.password).when().get(getExoWebDriver().getBaseUrl() + "rest/private/v1/social/activities/");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }

  @Test
  public void test01_commentAnActivityAPI() {

    String comment = "comment" + getRandomNumber();

    socialActivitiesCommentsManagement.commentAnActivityAPI(socialActivitiesManagement.getLastActivityAPI(), comment);

  }

  @Test
  public void test02_CommentAnActivityThenGetCommentId_API() {

    String comment = "comment" + getRandomNumber();

    info("Comment Last Activity");
    socialActivitiesCommentsManagement.commentAnActivityAPI(socialActivitiesManagement.getLastActivityAPI(), comment);

    info("Get the added comment Id");
    socialActivitiesCommentsManagement.getCommentId_API(socialActivitiesManagement.getLastActivityAPI(), comment);

  }

  @Test
  public void test03_getAllActivityCommentsAPI() {

    socialActivitiesCommentsManagement.getAllActivityComments(socialActivitiesManagement.getLastActivityAPI());

  }

  @Test
  public void test04_CommentAnActivityThenGetCommentIdThenDeleteTheComment_API() {

    String comment = "comment" + getRandomNumber();

    info("Comment Last Activity");
    socialActivitiesCommentsManagement.commentAnActivityAPI(socialActivitiesManagement.getLastActivityAPI(), comment);

    info("Get the added comment Id");
    socialActivitiesCommentsManagement.getCommentId_API(socialActivitiesManagement.getLastActivityAPI(), comment);

    info("Delete the activity comment");
    socialActivitiesCommentsManagement.deleteAddedCommentActivityAPI(socialActivitiesCommentsManagement.getCommentId_API(socialActivitiesManagement.getLastActivityAPI(), comment));

  }


}
