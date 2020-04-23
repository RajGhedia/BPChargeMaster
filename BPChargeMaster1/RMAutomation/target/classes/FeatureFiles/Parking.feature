Feature: Parking

  @low @Pod @All
  Scenario: ParkingPod Commercial Rent
    Given I have applied a Must Have Parking option to my Commercial Rent search
    When I land on the No Results page for Parking Pod
    Then I should see the ‘Remove Must Have Parking’ pod

  @low @Pod @All
  Scenario: ParkingPod Commercial Sale
    Given I have applied a Must Have Parking option to my Commercial Sale search
    When I land on the No Results page for Parking Pod
    Then I should see the ‘Remove Must Have Parking’ pod