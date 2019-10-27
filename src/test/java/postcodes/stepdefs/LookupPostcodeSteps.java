package postcodes.stepdefs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import postcodes.PostcodesEndpoints;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class LookupPostcodeSteps extends BaseTest {
    @When("the request is made with get method to postcode lookup endpoint")
    public void theRequestIsMadeWithGetMethodToPostcodeLookupEndpoint() {
        SerenityRest.when().get(PostcodesEndpoints.LOOKUP.getUrl());
    }

    @Then("the result with given postcode is returned")
    public void theResultWithGivenPostcodeIsReturned() {
        restAssuredThat(response -> response
                .statusCode(HttpStatus.SC_OK)
                .body("result.postcode", equalTo(VALID_POST_CODES_LIST.get(0))));
    }
}
