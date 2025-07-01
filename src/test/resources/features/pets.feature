Feature: Get pets by status

  Scenario: Get available pets from Swagger Petstore
    Given the Petstore API is available
    When I send GET request for pets with status "available"
    Then I should receive a non-empty list of pets
    And each pet should have an id and a name
