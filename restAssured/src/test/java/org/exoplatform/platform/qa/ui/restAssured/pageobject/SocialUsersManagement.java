package org.exoplatform.platform.qa.ui.restAssured.pageobject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.json.simple.JSONObject;
import org.junit.Assert;

public class SocialUsersManagement extends Base {

  private final TestBase testBase;

  /**
   * constructor
   *
   * @param testBase
   */
  public SocialUsersManagement(TestBase testBase) {
    this.testBase = testBase;

  }

  public String getLastUserAPIByUsername(String username) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/social/users/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Users Data are" + data);

    String id = response.asString().split(username)[1].split("username")[0].split("/identities/")[1].split(",")[0].split("\"")[0];
    Response response2 = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/social/users/" + username);
    int code2 = response2.getStatusCode();
    Assert.assertEquals(code2, 200);
    String data2 = response2.asString();
    System.out.println("Last User Data are" + data2);

    return data;
  }


  public void putUserAPI(String oldUserName, String newUsername, String newLastName) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    json.put("username", newUsername);
    json.put("firstname", newUsername);
    json.put("lastname", newLastName);
    json.put("fullname", newLastName + " " + newLastName);
    json.put("email", newLastName + "." + newLastName + "@exoplatform.com");

    request.body(json.toJSONString());
    Response response = request.put(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/social/users/" + oldUserName);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Updated User Data are" + data);

  }

  public void deleteUserAPI(String username) {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.delete(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/social/users/" + username);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Users Data are deleted" + data);
  }


}
