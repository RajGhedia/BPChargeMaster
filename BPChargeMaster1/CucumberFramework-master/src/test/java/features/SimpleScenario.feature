#Author: BJSS
Feature: Chargevision

	@web
  Scenario: Validate chargevision search text field
    Given I open a browser
    When I navigate to chargevision page
    Then I validate the search text field
