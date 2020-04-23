Feature: Side Bar all channel and Mobile devices.

  @low @All
  Scenario: Check footer link
    Given I am on the footer link
    When I selected a footer link
    Then Link should be collapsable

  @low @All
  Scenario: Check Hyperlinks
    Given I am on the footer link
    When I selected a footer link
    Then Hyperlinks should remain as hyperlinks

  @low @All
  Scenario: Check Plain text
    Given I am on the footer link
    When I selected a footer link
    Then Plain text should remain as Plain text