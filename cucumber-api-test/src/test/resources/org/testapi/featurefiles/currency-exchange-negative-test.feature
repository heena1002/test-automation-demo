Feature: Test Currency exchange rates api -negative

  Scenario Outline: verify negative test of status code
    Given I hit url for currency exchange rates
    When a <method> call is made to the path <endpoint>
    Then the response status code should be <statusCode> and status is <status>
    Examples:
      | endpoint        | statusCode | status                          | method |
      | /v6/latest/USD1 | 404        | HTTP/1.1 404 Not Found          | get    |
      | /v6/latest/USD  | 405        | HTTP/1.1 405 Method Not Allowed | post   |
