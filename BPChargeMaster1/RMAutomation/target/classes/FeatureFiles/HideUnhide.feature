Feature: HideUnhide

  @wip @Hide @All
  Scenario: HideUnhide
    Given I am on the search results page
    When I select the X user can hide property
    Then User can not see the property and is face with undo link

