Feature: Lookup postcode

  Scenario: Lookup valid postcode return response with queried postcode
    Given the request with valid postcode
    When the request is made with get method to postcode lookup endpoint
    Then the result with given postcode is returned