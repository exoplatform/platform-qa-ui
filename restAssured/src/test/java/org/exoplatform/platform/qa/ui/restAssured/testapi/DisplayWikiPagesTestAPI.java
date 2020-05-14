package org.exoplatform.platform.qa.ui.restAssured.testapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class DisplayWikiPagesTestAPI extends Base {

  public String currentPath;

  public String getCurrentPath() {
    return currentPath;
  }

  public void setCurrentPath(String currentPath) {
    this.currentPath = currentPath;
  }

  public String lastNode;

  public String getLastNode() {
    return lastNode;
  }

  public void setLastNode(String lastNode) {
    this.lastNode = lastNode;
  }

  public String excerpt;

  public String getExcerpt() {
    return excerpt;
  }

  public void setExcerpt(String excerpt) {
    this.excerpt = excerpt;
  }

  public String selectable;

  public String getSelectable() {
    return selectable;
  }

  public void setSelectable(String selectable) {
    this.selectable = selectable;
  }

  public String retricted;

  public String getRetricted() {
    return retricted;
  }

  public void setRetricted(String retricted) {
    this.retricted = retricted;
  }

  public String hasChild;

  public String getHasChild() {
    return hasChild;
  }

  public void setHasChild(String hasChild) {
    this.hasChild = hasChild;
  }

  public String expanded;

  public String getExpanded() {
    return expanded;
  }

  public void setExpanded(String expanded) {
    this.expanded = expanded;
  }

  public String selected;

  public String getSelected() {
    return selected;
  }

  public void setSelected(String selected) {
    this.selected = selected;
  }

  public String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String path;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String nodeType;

  public String getNodeType() {
    return nodeType;
  }

  public void setNodeType(String nodeType) {
    this.nodeType = nodeType;
  }

  public List<Children> getChildrens() {
    return childrens;
  }

  public void setChildrens(List<Children> childrens) {
    this.childrens = childrens;
  }

  private List<Children> childrens;


  public class Children {

    private String currentPath;
    private String lastNode;
    private String excerpt;
    private String selectable;
    private String retricted;
    private String hasChild;
    private String expanded;
    private String selected;
    private String name;
    private String path;
    private String nodeType;

  }

  @Test
  public void testResponseCode() {
    Response resp = RestAssured.given().auth().basic(PLFData.username, PLFData.password).when().get(getExoWebDriver().getBaseUrl() + "rest/private/wiki/tree/ALL?path=portal/intranet/WikiHome/");

    int code = resp.getStatusCode();
    System.out.println("Status code is" + code);
    Assert.assertEquals(code, 200);
  }

  @Test
  public void test02_getWikiPagesAPI() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(getExoWebDriver().getBaseUrl() + "rest/private/wiki/tree/ALL?path=portal/intranet/WikiHome/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);
    String data = response.asString();
    System.out.println("Wiki Pages are" + data);
  }

  @Test
  public void test03_getWikiPageAPIName() {

    RequestSpecification request = RestAssured.given().auth().basic(PLFData.username, PLFData.password);
    request.header("Content-Type", "application/json");
    JSONObject json = new JSONObject();
    request.body(json.toJSONString());
    Response response = request.get(getExoWebDriver().getBaseUrl() + "rest/private/wiki/tree/ALL?path=portal/intranet/WikiHome/");

    int code = response.getStatusCode();
    Assert.assertEquals(code, 200);

    int wikiPagesNumber = response.asString().split("Wiki Home")[1].split("name").length;

    for (int i = 1; i < wikiPagesNumber; i++) {
      String wikiPageName = response.asString().split("Wiki Home")[1].split("name")[i].split("path")[0].split(":")[1].split(",")[0].split("\"")[1];

      System.out.println("Wiki Page Name is " + wikiPageName);

    }

  }

  @Test
  public void test() {

    String json = "{\"results\":[{\"lat\":\"value\",\"lon\":\"value\" }, { \"lat\":\"value\", \"lon\":\"value\"}]}";

    Gson gson = new Gson();
    JsonObject inputObj  = gson.fromJson(json, JsonObject.class);
    JsonObject newObject = new JsonObject() ;
    newObject.addProperty("lat", "newValue");
    newObject.addProperty("lon", "true");
    inputObj.get("results").getAsJsonArray().add(newObject);
    System.out.println(inputObj);


  }

}
