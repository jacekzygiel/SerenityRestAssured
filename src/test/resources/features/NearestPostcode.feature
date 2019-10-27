Feature: Nearest postcode

  Scenario: A correct list of postcodes is returned for valid coordinates
    Given the request with valid coordinates to the Nearest Postcodes endpoint
    When the request is made with get method to nearest postcodes endpoint
    Then the response with correct list of postcodes is returned

  Scenario: An empty results list is returned for a valida coordinates outside UK
    Given the request with valid coordinates outside UK
    When the request is made with get method to nearest postcodes endpoint
    Then the empty result list is returned

  Scenario: A invalid longitude/latitude submitted error is returned for a request with invalid longitude
    Given the request with invalid longitude
    When the request is made with get method to nearest postcodes endpoint
    Then the invalid longitude latitude submitted error is returned

  Scenario: A invalid longitude/latitude submitted error is returned for a request with invalid latitude
    Given the request with invalid latitude
    When the request is made with get method to nearest postcodes endpoint
    Then the invalid longitude latitude submitted error is returned