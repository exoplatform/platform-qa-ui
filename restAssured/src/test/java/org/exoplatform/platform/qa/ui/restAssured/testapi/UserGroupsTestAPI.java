package org.exoplatform.platform.qa.ui.restAssured.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.restAssured.pageobject.UserGroups;
import org.junit.Assert;
import org.junit.Test;

public class UserGroupsTestAPI extends Base {

  UserGroups userGroups = new UserGroups(this);

  @Test
  public void testResponseCode() {
    Response resp = RestAssured.given().auth().basic(PLFData.username, PLFData.password).when().get(getExoWebDriver().getBaseUrl() + "rest/private/v1/groups/");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }

  @Test
  public void test02_getUserGroupsAPI() {

    userGroups.getUserGroups();

  }

  @Test
  public void test03_getUserGroupsByLabelAPI() {

    userGroups.getUserGroupsByLabel();

  }

  @Test
  public void test04_getUserGroupsByGroupNameAPI() {

    userGroups.getUserGroupsByGroupName();

  }

  @Test
  public void test05_getUserGroupsByDescriptionAPI() {

    userGroups.getUserGroupsByDescription();

  }

}
