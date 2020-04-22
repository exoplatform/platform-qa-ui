package org.exoplatform.platform.qa.ui.restAssured.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.restAssured.pageobject.SocialRelationshipsManagement;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;


public class SocialRelationshipsTestAPI extends Base {

  SocialRelationshipsManagement socialRelationshipsManagement = new SocialRelationshipsManagement(this);

  @Test
  public void testResponseCode() {
    Response resp = RestAssured.given().auth().basic(PLFData.username, PLFData.password).when().get(getExoWebDriver().getBaseUrl() + "rest/private/v1/social/usersRelationships/");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }

  @Test
  public void test02_getUserRelationshipsAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(getExoWebDriver().getBaseUrl() + "rest/private/v1/social/usersRelationships/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("User Relationships Data are" + data);
  }

  @Test
  public void test03_getFirstUserRelationshipIdAPI() {

    socialRelationshipsManagement.getFirstUserRelationshipIdAPI();
  }


  @Test
  public void test04_putUserRelationshipsStatusAPI() {

    String pendingStatus = "PENDING";
    String confirmedStatus = "CONFIRMED";


    socialRelationshipsManagement.putFirstUserRelationshipStatusAPI(socialRelationshipsManagement.getFirstUserRelationshipIdAPI(),confirmedStatus);

  }

  @Test
  public void test05_deleteUserRelationshipsAPI() {
    socialRelationshipsManagement.deleteUserRelationshipsAPI(socialRelationshipsManagement.getFirstUserRelationshipIdAPI());
  }

}
