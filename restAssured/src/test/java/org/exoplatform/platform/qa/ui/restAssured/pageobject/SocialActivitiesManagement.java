package org.exoplatform.platform.qa.ui.restAssured.pageobject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.json.simple.JSONObject;
import org.junit.Assert;

public class SocialActivitiesManagement extends  Base{

  private final TestBase testBase;

  /**
   * constructor
   *
   * @param testBase
   */
  public SocialActivitiesManagement(TestBase testBase) {
    this.testBase = testBase;

  }


  public String getLastActivityAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type" , "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "/rest/private/v1/social/activities/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Activities Data are" + data);

    String id = response.asString().split("DEFAULT_ACTIVITY")[0].split("id")[1].split(":")[1].split("title")[0].split(",")[0].split("\"")[1];
    Response response2 = request.get(testBase.getExoWebDriver().getBaseUrl() + "/rest/private/v1/social/activities/" +id);
    int code2 = response2.getStatusCode();
    Assert.assertEquals(code2, 200);
    String data2 = response2.asString();
    System.out.println("First Activity Data are" + data2);

    return id;
  }


  public void putActivityAPI(String id , String title) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type" , "application/json");
    JSONObject json = new JSONObject();
    json.put("title", title);
    json.put("type", "DEFAULT_ACTIVITY");
    json.put("templateParams", "{}");


    request.body(json.toJSONString());
    Response response = request.put(testBase.getExoWebDriver().getBaseUrl() + "/rest/private/v1/social/activities/" +id);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Updated Activity Data are" + data);

  }

  public void deleteActivityAPI(String id) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type" , "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.delete(testBase.getExoWebDriver().getBaseUrl() + "/rest/private/v1/social/activities/" +id);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Activity Data are deleted" + data);
  }


}
