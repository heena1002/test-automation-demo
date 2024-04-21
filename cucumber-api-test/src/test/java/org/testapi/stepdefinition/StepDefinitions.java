package org.testapi.stepdefinition;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class StepDefinitions {
    private Response response;

    @Given("the base url {string}")
    public void the_base_url(String baseURL) {
        RestAssured.baseURI = baseURL;
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
        response.then().body(key,Matchers.notNullValue());
    }

    @And("response content type is {string}")
    public void responseContentTypeIs(String contentType) {
        Assert.assertEquals(contentType, response.getContentType());
    }


    @When("a {} call is made to the path {}")
    public void aCallIsMadeToThePath(String methodName, String endpoint) {
        if(methodName.equals("get")) {
            response = RestAssured.get(endpoint);
        }else if(methodName.equals("post")) {
            response = RestAssured.post(endpoint);
        }
    }

    @And("{string} price is in the range of {string} to {string}")
    public void priceIsInTheRangeOfTo(String currency, String lower, String upper) {
        float value = Float.parseFloat(response.jsonPath().get("rates."+currency).toString());
        if(value>Float.parseFloat(lower) || value<Float.parseFloat(upper) ) Assert.assertTrue(true);
        else Assert.fail();
    }


    @And("response returns {string} currency pairs")
    public void responseReturnsCurrencyPairs(String currencyPairs) {
        Assert.assertEquals(currencyPairs,response.getBody().jsonPath().get("rates.size()").toString());
    }


}
