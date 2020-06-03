package org.exoplatform.platform.qa.ui.restAssured.pageobject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.json.simple.JSONObject;
import org.junit.Assert;

public class SocialActivitiesCommentsManagement extends Base {

  private final TestBase testBase;

  /**
   * constructor
   *
   * @param testBase
   */
  public SocialActivitiesCommentsManagement(TestBase testBase) {
    this.testBase = testBase;

  }


  public String getCommentId_API(String id, String comment) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();

    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/social/activities/" + id + "/comments");
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);

    int i = response.asString().split(comment)[0].split("id").length;


    String commentId = response.asString().split(comment)[0].split("id")[i - 3].split(":")[1].split(",")[0].split("\"")[1];
    Response response2 = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/social/activities/" + commentId);
    int code2 = response2.getStatusCode();
    String data2 = response2.asString();
    System.out.println("Comment Id is: " + commentId);
    System.out.println("Added Comment Data are: " + data2);

    return commentId;
  }

  public void commentAnActivityAPI(String id, String comment) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    json.put("title", comment);
    json.put("body", comment);

    request.body(json.toJSONString());
    Response response = request.post(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/social/activities/" + id + "/comments");
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Comment added is: " + comment);

  }

  public void deleteAddedCommentActivityAPI(String commentId) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();

    request.body(json.toJSONString());
    Response response = request.delete(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/social/activities/" + commentId);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 500);
    String data = response.asString();
    System.out.println("The comment is deleted");

  }

  public void getAllActivityComments(String acrivityId) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/social/activities/" + acrivityId + "/comments");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);

    int commentsNumber = ((response.asString().split("id").length) - 1) / 3;

    System.out.println("User Groups Descriptions are :");
    for (int i = 1; i <= commentsNumber; i++) {

      String commentId = response.asString().split("href")[i].split("identity")[0].split("activities")[1].split("/")[1].split(",")[0].split("\"")[0];
      System.out.println("Activity Comment Id is: " + commentId);

      Response response2 = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/social/activities/" + commentId);
      String comment = response2.asString().split(commentId)[1].split(",")[1].split("title")[1].split(":")[1].split("\"")[1];
      ;
      String data2 = response2.asString();
      int code2 = response2.getStatusCode();
      Assert.assertEquals(code2, 200);
      System.out.println("Activity Comment is: " + comment);
      System.out.println("Activity Comments Data are: " + data2);


    }

  }

}
