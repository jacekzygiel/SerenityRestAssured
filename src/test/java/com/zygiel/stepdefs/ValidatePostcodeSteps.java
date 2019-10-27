package com.zygiel.stepdefs;

import com.zygiel.PostcodesEndpoints;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;


public class ValidatePostcodeSteps extends BaseTest {

    @Given("the request with valid postcode")
    public void theRequestWithValid() {
        validatePostCodeRequest(VALID_POST_CODES_LIST.get(0));
    }

    @Given("the request with invalid postcode")
    public void theRequestWithInvalidPostcode() {
        validatePostCodeRequest(getRandomInvalidPostCode());
    }

    @Given("the request without postcode")
    public void theRequestWithoutPostcode() {
        validatePostCodeRequest("");
    }

    @When("the request is made with get method to validate postcode")
    public void theRequestIsMadeWithGetMethodToValidatePostcode() {
        SerenityRest.when().get(PostcodesEndpoints.VALIDATE.getUrl());
    }

    @Then("the response with result equal to {string} is returned")
    public void theResponseWithResultEqualToIsReturned(String expectedResult) {
        restAssuredThat(response -> response
                .statusCode(HttpStatus.SC_OK)
                .body("result", equalTo(Boolean.valueOf(expectedResult))));
    }

    @Then("the not found status code with Invalid postcode error is returned")
    public void theNotFoundStatusCodeWithInvalidPostcodeErrorIsReturned() {
        restAssuredThat(response ->  response
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("error", equalTo("Invalid postcode")));
    }

    private void validatePostCodeRequest(String postCode) {
        SerenityRest.given()
                .baseUri(BASE_URI)
                .pathParam("postcode", postCode);
    }
}
