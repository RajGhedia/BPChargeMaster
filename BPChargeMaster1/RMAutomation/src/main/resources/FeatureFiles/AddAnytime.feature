Feature: Add any time pod

 @low @Pod @All
  Scenario: AddanytimeBuy
    Given I have applied an ‘Added to site’ option to my Residential buy search in the last 24hour
    When I land on the No Results page
    Then I should see the ‘Remove Added to Site’ pod

  @low @Pod @All
  Scenario: AddanytimeRent
    Given I have applied an ‘Added to site’ option to my Residential rent search in the last 24hour
    When I land on the No Results page
    Then I should see the ‘Remove Added to Site’ pod

  @low @Pod @All
  Scenario: AddanytimeOversea
    Given I have applied can ‘Added to site’ option to my Oversea Buy search in the last 24hour
    When I land on the No Results page
    Then I should see the ‘Remove Added to Site’ pod

  @low @Pod @All
  Scenario: AddanytimeCommercialBuy
    Given I have applied an ‘Added to site’ option to my Commercial Buy search in the last 24hour
    When I land on the No Results page
    Then I should see the ‘Remove Added to Site’ pod

  @low @Pod @All
  Scenario: AddanytimeCommercialRent
    Given I have applied an ‘Added to site’ option to my Commercial rent search in the last 24hour
    When I land on the No Results page
    Then I should see the ‘Remove Added to Site’ pod