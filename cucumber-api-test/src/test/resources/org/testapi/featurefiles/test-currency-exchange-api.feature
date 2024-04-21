Feature: Test Currency exchange rates api

  Background: define config
    Given the base url "https://open.er-api.com"

  Scenario: Verify when get call is made, success is returned in the response and status code = 200
    When a get call is made to the path /v6/latest/USD
    Then the response status code should be 200 and status is HTTP/1.1 200 OK
    And the response should contain key "result" having value "success"
    And the response should contain key "rates"

Scenario Outline: verify status code when api endpoint is not found
  When a <method> call is made to the path <endpoint>
  Then the response status code should be <statusCode> and status is <status>
  Examples:
    | endpoint        | statusCode | status                          | method |
    | /v6/latest/USD1 | 404        | HTTP/1.1 404 Not Found          | get    |
    | /v6/latest/USD  | 405        | HTTP/1.1 405 Method Not Allowed | post   |


  Scenario: Verify response content type is json
  When a get call is made to the path /v6/latest/USD
  Then the response status code should be 200 and status is HTTP/1.1 200 OK
  And response content type is "application/json"

  Scenario: Verify the USD price against the AED and make sure the prices are in range on 3.6 – 3.7
    When a get call is made to the path /v6/latest/USD
    Then the response status code should be 200 and status is HTTP/1.1 200 OK
    And "AED" price is in the range of "3.6" to "3.7"

    Scenario: Verify that 162 currency pairs are returned by the API
      When a get call is made to the path /v6/latest/USD
      Then the response status code should be 200 and status is HTTP/1.1 200 OK
      And response returns "162" currency pairs

