package postcodes.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import postcodes.PostcodesEndpoints;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class NearestPostcodesSteps extends BaseTest {

    private static final Map<String, Object> VALID_GPS_DATA = Stream.of(
            new AbstractMap.SimpleEntry<>("lon", "0.629834723775309"),
            new AbstractMap.SimpleEntry<>("lat", "51.7923246977375"),
            new AbstractMap.SimpleEntry<>("postCodes", new ArrayList<>(Arrays.asList("CM8 1EF", "CM8 1EU", "CM8 1PH", "CM8 1PQ"))))
            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

    private static final List<String> COORDINATES_OUTSIDE_UK = new ArrayList<>(Arrays.asList("50.049683", "19.944544"));

    @Given("the request with valid coordinates to the Nearest Postcodes endpoint")
    public void theRequestWithValidCoordinatesToTheNearestPostcodesEndpoint() {
        SerenityRest.given()
                .baseUri(BASE_URI)
                .pathParam("lon", VALID_GPS_DATA.get("lon"))
                .pathParam("lat", VALID_GPS_DATA.get("lat"));
    }

    @Given("the request with invalid longitude")
    public void theRequestWithInvalidLongitude() {
        SerenityRest.given()
                .baseUri(BASE_URI)
                .pathParam("lon", RandomStringUtils.randomAlphabetic(3))
                .pathParam("lat", VALID_GPS_DATA.get("lat"));
    }

    @Given("the request with valid coordinates outside UK")
    public void theRequestWithValidCoordinatesOutsideUK() {
        SerenityRest.given()
                .baseUri(BASE_URI)
                .pathParam("lon", COORDINATES_OUTSIDE_UK.get(0))
                .pathParam("lat", COORDINATES_OUTSIDE_UK.get(1));
    }

    @Given("the request with invalid latitude")
    public void theRequestWithInvalidLatitude() {
        SerenityRest.given()
                .baseUri(BASE_URI)
                .pathParam("lon", VALID_GPS_DATA.get("lon"))
                .pathParam("lat", RandomStringUtils.randomAlphabetic(3));
    }

    @When("the request is made with get method to nearest postcodes endpoint")
    public void theRequestIsMadeWithGetMethod() {
        SerenityRest.when().get(PostcodesEndpoints.NEAREST.getUrl());
    }

    @Then("the response with correct list of postcodes is returned")
    public void theResponseWithCorrectListOfPostcodesIsReturned() {
        restAssuredThat(response -> response
                .statusCode(HttpStatus.SC_OK)
                .body("result", hasSize(((List) VALID_GPS_DATA.get("postCodes")).size()))
                .body("result[0].postcode", equalTo(((List) VALID_GPS_DATA.get("postCodes")).get(0)))
                .body("result[1].postcode", equalTo(((List) VALID_GPS_DATA.get("postCodes")).get(1)))
                .body("result[2].postcode", equalTo(((List) VALID_GPS_DATA.get("postCodes")).get(2)))
                .body("result[3].postcode", equalTo(((List) VALID_GPS_DATA.get("postCodes")).get(3))));
    }

    @Then("the empty result list is returned")
    public void theEmptyResultListIsReturned() {
        restAssuredThat(response -> response
                .statusCode(HttpStatus.SC_OK)
                .body("result", is(nullValue())));
    }

    @Then("the invalid longitude latitude submitted error is returned")
    public void theInvalidLongitudeLatitudeSubmittedErrorIsReturned() {
        restAssuredThat(response -> response
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("status", equalTo(HttpStatus.SC_BAD_REQUEST))
                .body("error", equalTo("Invalid longitude/latitude submitted")));
    }
}
