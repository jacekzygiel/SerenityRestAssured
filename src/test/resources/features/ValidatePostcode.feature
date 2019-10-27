Feature: Validate postcode

  Scenario: True is returned for a request with valid postcode
    Given the request with valid postcode
    When the request is made with get method to validate postcode
    Then the response with result equal to "true" is returned

  Scenario: False is returned for a request with invalid postcode
    Given the request with invalid postcode
    When the request is made with get method to validate postcode
    Then the response with result equal to "false" is returned

  Scenario: Not found with invalid postocode error is returned for a request without postcode
    Given the request without postcode
    When the request is made with get method to validate postcode
    Then the not found status code with Invalid postcode error is returned