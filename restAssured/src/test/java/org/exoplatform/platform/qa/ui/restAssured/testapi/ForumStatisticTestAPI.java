package org.exoplatform.platform.qa.ui.restAssured.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.restAssured.pageobject.ForumStatisticManagement;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class ForumStatisticTestAPI extends Base {

  ForumStatisticManagement forumStatisticManagement = new ForumStatisticManagement(this);

  @Test
  public void testResponseCode() {
    Response resp = RestAssured.given().auth().basic(PLFData.username, PLFData.password).when().get(getExoWebDriver().getBaseUrl() + "rest/private/v1/calendar/events/");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }


  @Test
  public void test02_getForumStatisticAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(getExoWebDriver().getBaseUrl() + "rest/private/forumsService/forums/statistic");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Forum Statistic Data are" + data);
  }

  @Test
  public void test03_getActiveUsersNumberAPI() {

    forumStatisticManagement.getActiveUsersNumberAPI();

  }

  @Test
  public void test04_getPostsNumberAPI() {

    forumStatisticManagement.getPostsNumberAPI();

  }

  @Test
  public void test05_getTopicsNumberAPI() {

    forumStatisticManagement.getTopicsNumberAPI();

  }

  @Test
  public void test06_getUsersNumberAPI() {

    forumStatisticManagement.getUsersNumberAPI();

  }

  @Test
  public void test07_getNewUserNameAPI() {

    forumStatisticManagement.getNewUsersNameAPI();

  }

}
