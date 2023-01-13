#Test case 1
Feature: Search results for valid String used as a search parameter
  Scenario: Check if we get non-empty search result when a relevant String is used as a search parameter
    Given a valid String (Latin or Cyrillic) relevant to the domain
    When I perform GET request to https://www.vodafone.ua/api/search/ with the specified keyword
    Then the request is performed successfully (with status code 200), content type is JSON, the response body contains data items related to the keyword
