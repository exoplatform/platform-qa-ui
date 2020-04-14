package org.exoplatform.platform.qa.ui.restAssured.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.restAssured.pageobject.SocialSpacesManagement;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;

public class SocialSpacesTestAPI extends Base {

  SocialSpacesManagement socialSpacesManagement = new SocialSpacesManagement(this);

  @Test
  public void testResponseCode() {
    Response resp = RestAssured.given().auth().basic(PLFData.username, PLFData.password).when().get(getExoWebDriver().getBaseUrl() + "rest/private/v1/social/spaces/");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }

  @Test
  public void test01_addSpaceAPI() {
    String space = "space" + getRandomNumber();

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    json.put("displayName", space);
    json.put("description", space);
    json.put("visibility", "private");
    json.put("subscription", "open");

    request.body(json.toJSONString());
    Response response = request.post(getExoWebDriver().getBaseUrl() + "rest/private/v1/social/spaces/");
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Added Space Data are" + data);

  }

  @Test
  public void test02_getSpacesAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(getExoWebDriver().getBaseUrl() + "rest/private/v1/social/spaces/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Spaces Data are" + data);
  }

  @Test
  public void test03_getLastSpaceDataAPI() {

    socialSpacesManagement.getLastSpaceAPI();

  }


  @Test
  public void test04_putSpaceAPI() {

    String space = "space" + getRandomNumber();

    socialSpacesManagement.putSpaceAPI(socialSpacesManagement.getLastSpaceAPI(), space);

  }

  @Test
  public void test05_deleteSpaceAPI() {
    socialSpacesManagement.deleteSpaceAPI(socialSpacesManagement.getLastSpaceAPI());

  }

}
