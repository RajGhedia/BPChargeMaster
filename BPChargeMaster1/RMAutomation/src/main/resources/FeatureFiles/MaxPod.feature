
Feature: MaxPod


  Scenario Outline: There is a limit of one price increment buy,rent,Overseas,commercial

    Given I am on the No Results page
    And The <Site> is
    When I have checked that the property value allows only one price increment
    Then I should see any Max Pod displayed with a suggestion

    Examples:

      |Site|
      |https://www.qa.rightmove.com/property-to-rent/find.html?locationIdentifier=REGION%5E87490&minBedrooms=5&maxPrice=400&sortType=1&includeLetAgreed=false   |
      |https://www.qa.rightmove.com/property-to-rent/find.html?locationIdentifier=REGION%5E1217&minBedrooms=6&maxPrice=400&includeLetAgreed=false               |
      |https://www.qa.rightmove.com/overseas-property-for-sale/Franche-Comte.html?minBedrooms=1&maxPrice=60000&sortType=2&currencyCode=GBP"                     |