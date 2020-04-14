package org.exoplatform.platform.qa.ui.restAssured.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.restAssured.pageobject.SocialUsersManagement;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;

public class SocialUsersTestAPI extends Base {

  SocialUsersManagement socialUsersManagement = new SocialUsersManagement(this);
  private String username = "testAPI";


  @Test
  public void testResponseCode() {
    Response resp = RestAssured.given().auth().basic(PLFData.username, PLFData.password).when().get(getExoWebDriver().getBaseUrl() + "rest/private/v1/social/users/");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }

  @Test
  public void test01_addUserAPI() {

    String lastName = "user" + getRandomString();
    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    json.put("username", username);
    json.put("firstname", username);
    json.put("lastname", lastName);
    json.put("fullname", username + " " + lastName);
    json.put("email", username + "." + lastName + "@exoplatform.com");

    request.body(json.toJSONString());
    Response response = request.post(getExoWebDriver().getBaseUrl() + "rest/private/v1/social/users/");
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Added User Data are" + data);

  }

  @Test
  public void test02_getUsersAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(getExoWebDriver().getBaseUrl() + "rest/private/v1/social/users/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Users Data are" + data);
  }

  @Test
  public void test03_getLastUserDataAPI() {

    socialUsersManagement.getLastUserAPIByUsername(username);

  }


  @Test
  public void test04_putUserAPI() {

    String newUsername = "user" + getRandomString();
    String newLastName = "user" + getRandomString();

    socialUsersManagement.putUserAPI(username, newUsername, newLastName);

  }

  @Test
  public void test05_deleteUserAPI() {
    socialUsersManagement.deleteUserAPI(username);

  }

}
