package com.testdrip.service;

import com.testdrip.utils.ApiClient;
import com.testdrip.utils.ConfigManager;
import com.testdrip.utils.Endpoints;
import io.restassured.response.Response;

public class SpaceXService {

  private final ApiClient apiClient;

  public SpaceXService() {
    String baseUri = ConfigManager.get("base.uri.spacex");
    this.apiClient = new ApiClient(baseUri);
  }

  public Response getCapsuleBySerial(String capsuleSerial) {
    return apiClient.getWithParam(Endpoints.CAPSULE_BY_SERIAL, "capsule_serial", capsuleSerial);
  }
}
