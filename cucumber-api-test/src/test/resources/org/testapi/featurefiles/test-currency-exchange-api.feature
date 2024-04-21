Feature: Test Currency exchange rates api

  Background: define config
  Given I hit url for currency exchange rates
  When a get call is made
  Then the response status code should be 200 and status is HTTP/1.1 200 OK

  Scenario: Verify when get call is made, success is returned in the response and status code = 200
    And the response should contain key "result" having value "success"
    And the response should contain key "base_code" having value "USD"
    And the response should contain key "rates"

  Scenario: Verify response content type is json
    And response content type is "application/json"

  Scenario: Verify the USD price against the AED and make sure the prices are in range on 3.6 – 3.7
    And "AED" price is in the range of "3.6" to "3.7"

  Scenario: Verify that 162 currency pairs are returned by the API
    And response returns "162" currency pairs

  Scenario: Generate a schema for the api response
    And generate schema for api response
