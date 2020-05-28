package org.exoplatform.platform.qa.ui.restAssured.pageobject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.json.simple.JSONObject;
import org.junit.Assert;

public class UserGroups extends Base {

  private final TestBase testBase;

  /**
   * constructor
   *
   * @param testBase
   */
  public UserGroups(TestBase testBase) {
    this.testBase = testBase;

  }

  public void getUserGroups() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();

    request.body(json.toJSONString());
    Response response = request.get(getExoWebDriver().getBaseUrl() + "rest/private/v1/groups/");
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("UserGroups Data are" + data);

  }

  public void getUserGroupsByLabel() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/groups/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);

    int groupsLabelNumber = response.asString().split("label").length -1;

    System.out.println("User Groups Labels are :");
    for (int i = 1; i <= groupsLabelNumber; i++) {
      String userGroupLabel = response.asString().split("label")[i].split(":")[1].split("\"")[1];

      System.out.println(userGroupLabel);

    }

    System.out.println("User Groups Number is :" + groupsLabelNumber);
  }

  public void getUserGroupsByGroupName() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/groups/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);

    int groupsLabelNumber = response.asString().split("groupName").length -1;

    System.out.println("User Groups Names are :");
    for (int i = 1; i <= groupsLabelNumber; i++) {
      String userGroupLabel = response.asString().split("groupName")[i].split(":")[1].split("\"")[1];

      System.out.println(userGroupLabel);

    }

    System.out.println("User Groups Number is :" + groupsLabelNumber);
  }

  public void getUserGroupsByDescription() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(testBase.getExoWebDriver().getBaseUrl() + "rest/private/v1/groups/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);

    int groupsLabelNumber = response.asString().split("description").length -1;

    System.out.println("User Groups Descriptions are :");
    for (int i = 1; i <= groupsLabelNumber; i++) {
      String userGroupLabel = response.asString().split("description")[i].split(":")[1].split("\"")[1];

      System.out.println(userGroupLabel);

    }

    System.out.println("User Groups Number is :" + groupsLabelNumber);
  }

}
