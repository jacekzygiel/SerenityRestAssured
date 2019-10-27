Feature: Random postcode

  Scenario: Random postcode is generated
    Given the request to the random postcodes endpoint
    When the request is made with get method to random postcode
    Then the randomly generated postcode is returned