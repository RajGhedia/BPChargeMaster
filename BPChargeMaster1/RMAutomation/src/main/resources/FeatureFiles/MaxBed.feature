Feature: MaxBed

  @low @Pod @All
  Scenario: MaxBed
    Given I have applied a Max Beds option to my Residential Sales search
    And where there would be results for no max beds option
    When I land on the No Results page for Max Bed
    Then I should see the Max Beds pod



