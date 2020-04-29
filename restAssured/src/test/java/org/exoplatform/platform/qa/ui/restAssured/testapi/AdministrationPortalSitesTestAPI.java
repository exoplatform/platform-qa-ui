package org.exoplatform.platform.qa.ui.restAssured.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.restAssured.pageobject.AdministrationPortalSitesManagement;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

public class AdministrationPortalSitesTestAPI extends Base {

  AdministrationPortalSitesManagement administrationPortalSitesManagement = new AdministrationPortalSitesManagement(this);

  @Test
  public void testResponseCode() {
    Response resp = RestAssured.given().auth().basic(PLFData.username, PLFData.password).when().get(getExoWebDriver().getBaseUrl() + "rest/private/managed-components/api/sites/");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }

  @Test
  public void test01_addPortalSitesAPI() {

    String portalName = "portalName" + getRandomNumber();
    String type = "site";

    administrationPortalSitesManagement.addPortalSiteAPI(portalName,type,portalName);

  }

  @Test
  public void test02_getAllPortalSitesAPI() {

    administrationPortalSitesManagement.getAllPortalSitesAPI();

  }

  @Test
  public void test03_Add_Get_Delete_PortalSiteAPI() {

    String portalName = "portalName" + getRandomNumber();
    String type = "site";

    info("Add A Portal Site");
    String name= administrationPortalSitesManagement.addPortalSiteAPI(portalName,type,portalName);

    info("Get added Portal Site");
    administrationPortalSitesManagement.getPortalSiteAPI(name);

    info("Delete the Portal Site");
    administrationPortalSitesManagement.deletePortalSiteAPI(name);

  }

  @Test
  public void test04_Put_Delete_PortalSiteAPI() {

    String portalName = "portalName" + getRandomNumber();
    String type = "site";
    String skin = "Entreprise";
    String locale = "en";
    ArrayList<String> accessPermissions = new ArrayList<>();
    accessPermissions.add("platform");
    accessPermissions.add("users");
    //String editPermissions = "[" + "*:/platform/administrators" + "]";

    info("Add A Portal Site");
    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();

    json.put("name", portalName);
    request.body(json.toJSONString());
    Response response = request.post(getExoWebDriver().getBaseUrl() + "rest/private/managed-components/api/sites/" + portalName);
    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Added Portal Site Data are" + data);

    info("Put the added Portal Site");
    administrationPortalSitesManagement.putPortalSiteAPI(portalName,type,portalName,skin,locale,null,null);

    info("Delete the Portal Site");
    administrationPortalSitesManagement.deletePortalSiteAPI(portalName);
  }

}
