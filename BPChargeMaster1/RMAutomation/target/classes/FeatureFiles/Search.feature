Feature: Search

  @All

  Scenario Outline: Search for areas in the UK

    Given I am on the main RM home page
    When I select a search <Area>
    Then I enter data

    Examples:

     |Area|
     |Tw19 7bu|
     |Kt1 4bu |
     |London|
     |Birmingham |
     |York |
     |1234234234234536456363|
     |MAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMITMAXLIMIT|
     |SQL STUFF ASK Before Running|
     |<html?>    |





