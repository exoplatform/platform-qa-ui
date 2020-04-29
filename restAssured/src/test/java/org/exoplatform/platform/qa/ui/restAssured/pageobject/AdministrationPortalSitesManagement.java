package org.exoplatform.platform.qa.ui.restAssured.pageobject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;

public class AdministrationPortalSitesManagement extends Base {

  private final TestBase testBase;

  /**
   * constructor
   *
   * @param testBase
   */
  public AdministrationPortalSitesManagement(TestBase testBase) {
    this.testBase = testBase;

  }

  public String addPortalSiteAPI(String name, String type, String description ) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();

    json.put("name", name);
    json.put("type", type);
    json.put("description", description);

    request.body(json.toJSONString());
    Response response = request.post(testBase.getExoWebDriver().getBaseUrl() + "rest/private/managed-components/api/sites/" + name);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Added Portal Site Data are" + data);

    return name;
  }


  public void putPortalSiteAPI(String name, String type, String description, String skin, String locale, ArrayList<String> accessPermissions, String editPermissions ) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();

    json.put("type", type);
    json.put("description", description);
    json.put("skin", skin);
    json.put("locale", locale);

    /*for (int i =0; i< accessPermissions.size(); i++) {
      json.put("access-permissions", (accessPermissions.get(i)));
    }*/
    //json.put("edit-permissions", editPermissions);


    request.body(json.toJSONString());
    Response response = request.put(testBase.getExoWebDriver().getBaseUrl() + "rest/private/managed-components/api/sites/" + name);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Updated Portal Site Data are" + data);

  }


  public String getPortalSiteAPI(String name) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/managed-components/api/sites/" + name);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Portal Site Data are" + data);

    return name;
  }

  public void getAllPortalSitesAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/managed-components/api/sites/");
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Portal Site Data are" + data);

  }

  public void deletePortalSiteAPI(String name) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/managed-components/api/sites/" + name);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Portal Site Data are deleted" + data);
  }


}
