package com.testdrip.steps;

import com.testdrip.service.SpaceXService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.assertj.core.api.Assertions.assertThat;

public class SpaceXCapsulesSteps {

  private static final Logger logger = LogManager.getLogger(SpaceXCapsulesSteps.class);
  private final SpaceXService spaceXService = new SpaceXService();
  private Response response;

  @When("I request capsule details for serial {string}")
  public void iRequestCapsuleDetailsForSerial(String capsuleSerial) {
    logger.info("Requesting capsule info for {}", capsuleSerial);
    response = spaceXService.getCapsuleBySerial(capsuleSerial);
  }

  @Then("the response status code is {int}")
  public void theResponseStatusCodeIs(int expectedStatusCode) {
    assertThat(response.statusCode()).isEqualTo(expectedStatusCode);
    logger.info("✅ Status code OK: {}", expectedStatusCode);
  }

  @Then("the capsule serial in the response is {string}")
  public void theCapsuleSerialInTheResponseIs(String expectedSerial) {
    String actualSerial = response.jsonPath().getString("capsule_serial");
    assertThat(actualSerial).isEqualTo(expectedSerial);
    logger.info("✅ Capsule serial: {}", actualSerial);
  }

  @Then("the capsule status is {string}")
  public void theCapsuleStatusIs(String expectedStatus) {
    String actualStatus = response.jsonPath().getString("status");
    assertThat(actualStatus).isEqualTo(expectedStatus);
    logger.info("✅ Capsule status: {}", actualStatus);
  }

  @Then("the capsule type is {string}")
  public void theCapsuleTypeIs(String expectedType) {
    String actualType = response.jsonPath().getString("type");
    assertThat(actualType).isEqualTo(expectedType);
    logger.info("✅ Capsule type: {}", actualType);
  }
}
