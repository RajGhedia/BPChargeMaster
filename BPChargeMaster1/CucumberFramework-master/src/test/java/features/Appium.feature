#Appium Test
Feature: Validate Different Options

  @appium
  Scenario: Validate Custom View

    Given I open the application
    When I tap on Email
    Then I validate Custom View
