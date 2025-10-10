package com.testdrip.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiClient {

  private final String baseUri;

  public ApiClient(String baseUri) {
    this.baseUri = baseUri;
  }

  public Response get(String path) {
    return RestAssured
        .given()
        .baseUri(baseUri)
        .accept(ContentType.JSON)
        .log().uri()
        .when()
        .get(path)
        .then()
        .log().body()
        .extract()
        .response();
  }

  public Response getWithParam(String path, String paramName, String paramValue) {
    return RestAssured
        .given()
        .baseUri(baseUri)
        .pathParam(paramName, paramValue)
        .accept(ContentType.JSON)
        .log().uri()
        .when()
        .get(path)
        .then()
        .log().body()
        .extract()
        .response();
  }
}
