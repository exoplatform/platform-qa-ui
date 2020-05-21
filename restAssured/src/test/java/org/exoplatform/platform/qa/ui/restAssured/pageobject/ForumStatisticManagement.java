package org.exoplatform.platform.qa.ui.restAssured.pageobject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.json.simple.JSONObject;
import org.junit.Assert;

public class ForumStatisticManagement extends Base {

  private final TestBase testBase;

  /**
   * constructor
   *
   * @param testBase
   */
  public ForumStatisticManagement(TestBase testBase) {
    this.testBase = testBase;

  }


  public void getActiveUsersNumberAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/forumsService/forums/statistic/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Forum Statistic Data are" + data);

    String activeUsers = response.asString().split("activeUsers")[1].split("postCount")[0].split(":")[1].split(",")[0].split("\"")[0];
    System.out.println("Active Users Number is " + activeUsers);

  }

  public void getPostsNumberAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/forumsService/forums/statistic/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Forum Statistic Data are" + data);

    String postsNumber = response.asString().split("postCount")[1].split("topicCount")[0].split(":")[1].split(",")[0].split("\"")[0];
    System.out.println("Forum Posts Number is " + postsNumber);

  }


  public void getTopicsNumberAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/forumsService/forums/statistic/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Forum Statistic Data are" + data);

    String topicNumbers = response.asString().split("topicCount")[1].split("membersCount")[0].split(":")[1].split(",")[0].split("\"")[0];
    System.out.println("Forum Topics Number is " + topicNumbers);

  }

  public void getUsersNumberAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/forumsService/forums/statistic/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Forum Statistic Data are" + data);

    String users = response.asString().split("membersCount")[1].split("mostUsersOnline")[0].split(":")[1].split(",")[0].split("\"")[0];
    System.out.println("Users Number is " + users);

  }

  public void getNewUsersNameAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/forumsService/forums/statistic/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Forum Statistic Data are" + data);

    String newUser = response.asString().split("newMembers")[1].split(":")[1].split("\"")[1].split("\"")[0];
    System.out.println("New User Name is " + newUser);

  }

}
