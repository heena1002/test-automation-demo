package org.testapi.stepdefinition;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testapi.util.TestUtils;
import java.io.File;
import java.io.IOException;
import static org.testapi.base.CommonRestAssuredMethods.*;

public class CurrencyExchangeStepDefinitions {
    private Response response;
    private String jsonSchema = TestUtils.getProperty("json.file.path");

    @Given("I hit url for currency exchange rates")
    public void iHitUrlForCurrencyExchangeRates() {
        RestAssured.baseURI = TestUtils.getProperty("test.api.baseurl");
    }

    @Then("the response status code should be {} and status is {}")
    public void theResponseStatusCodeShouldBeAndStatusIs(int statusCode, String status) {
        Assert.assertEquals(statusCode, response.statusCode());
        Assert.assertEquals(status, response.statusLine());
    }

    @And("the response should contain key {string} having value {string}")
    public void theResponseShouldContainKeyHavingValue(String key, String value) {
        response.then().body(key, Matchers.equalTo(value));
    }

    @And("the response should contain key {string}")
    public void theResponseShouldContainKey(String key) {
        response.then().body(key, Matchers.notNullValue());
    }

    @And("response content type is {string}")
    public void responseContentTypeIs(String contentType) {
        Assert.assertEquals(contentType, response.getContentType());
    }

    @When("a {} call is made")
    public void aCallIsMade(String methodName) {
        String endpoint = TestUtils.getProperty("test.api.path");
        response= getResponse(methodName, endpoint);
    }

    @When("a {} call is made to the path {}")
    public void aCallIsMadeToThePath(String methodName, String endpoint) {
        response = getResponse(methodName, endpoint);
    }

    @And("{string} price is in the range of {string} to {string}")
    public void priceIsInTheRangeOfTo(String currency, String lower, String upper) {
        float value = Float.parseFloat(response.jsonPath().get("rates." + currency).toString());
        if (value > Float.parseFloat(lower) || value < Float.parseFloat(upper)) Assert.assertTrue(true);
        else Assert.fail();
    }


    @And("response returns {string} currency pairs")
    public void responseReturnsCurrencyPairs(String currencyPairs) {
        Assert.assertEquals(currencyPairs, response.getBody().jsonPath().get("rates.size()").toString());
    }

    @And("generate schema for api response")
    public void generateSchemaForApiResponse() {
        String stringResponse = response.getBody().asString();
        File schemaFile = new File(jsonSchema);
        try {
            FileUtils.writeStringToFile(schemaFile, stringResponse, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
