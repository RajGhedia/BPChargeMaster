Feature: Garden

  @low @Pod @All
  Scenario: GardenPod Buy
    Given I have applied a Must Have Garden option to my Residential Sales search
    When I land on the No Results page journey for GardenPod
    Then I should see the ‘Remove Must Have Garden’ pod

  @low @Pod @All
  Scenario: GardenPod Letting
    Given I have applied a Must Have Garden option to my Residential Letting search
    When I land on the No Results page journey for GardenPod
    Then I should see the ‘Remove Must Have Garden’ pod
