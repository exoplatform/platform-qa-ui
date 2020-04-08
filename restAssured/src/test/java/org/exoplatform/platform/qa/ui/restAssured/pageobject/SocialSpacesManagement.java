package org.exoplatform.platform.qa.ui.restAssured.pageobject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.json.simple.JSONObject;
import org.junit.Assert;

public class SocialSpacesManagement extends  Base{

  private final TestBase testBase;

  /**
   * constructor
   *
   * @param testBase
   */
  public SocialSpacesManagement(TestBase testBase) {
    this.testBase = testBase;

  }



  public String getLastSpaceAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type" , "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "/rest/private/v1/social/spaces/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Spaces Data are" + data);

    String id = response.asString().split("id")[1].split(":")[1].split("href")[0].split(",")[0].split("\"")[1];
    Response response2 = request.get(testBase.getExoWebDriver().getBaseUrl() + "/rest/private/v1/social/spaces/" +id);
    int code2 = response2.getStatusCode();
    Assert.assertEquals(code2, 200);
    String data2 = response2.asString();
    System.out.println("Last Space Data are" + data2);

    return id;
  }


  public void putSpaceAPI(String id , String title) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type" , "application/json");
    JSONObject json = new JSONObject();
    json.put("displayName", title);
    json.put("description", title);
    json.put("visibility", "private");
    json.put("subscription", "open");

    request.body(json.toJSONString());
    Response response = request.put(testBase.getExoWebDriver().getBaseUrl() + "/rest/private/v1/social/spaces/" +id);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Updated Activity Data are" + data);

  }

  public void deleteSpaceAPI(String id) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type" , "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.delete(testBase.getExoWebDriver().getBaseUrl() + "/rest/private/v1/social/spaces/" +id);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Space Data are deleted" + data);
  }


}
