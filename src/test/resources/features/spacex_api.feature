# src/test/resources/features/spacex_capsules.feature
Feature: SpaceX Capsules API
  As a user of the SpaceX Capsules API
  I want to retrieve details about specific capsules

  Scenario: Retrieve details for a specific capsule by serial
    Given the SpaceX API base URI "https://api.spacexdata.com/v3"
    When I request capsule details for serial "C112"
    Then the response status code is 200
    And the capsule serial in the response is "C112"
    And the capsule status is "active"
    And the capsule type is "Dragon 1.1"