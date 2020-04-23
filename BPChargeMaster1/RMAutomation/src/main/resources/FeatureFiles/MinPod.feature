
Feature: MinPod

  @high @Pod @All
  Scenario Outline: There is a limit of one price increment buy,rent,Overseas,commercial

    Given I am on the No Results page
    And The <Site> is
    When I have checked that the property value allows only one price increment
    Then I should see any Min Pod displayed with a suggestion

    Examples:

    |Site|
    |https://www.qa.rightmove.com/property-for-sale/find.html?locationIdentifier=REGION%5E662&minBedrooms=3&maxPrice=300000&minPrice=300000&includeSSTC=false                                                              |
    |https://www.qa.rightmove.com/property-to-rent/find.html?locationIdentifier=REGION%5E85235&maxPrice=5000&minPrice=4000&sortType=2&propertyTypes=detached&secondaryDisplayPropertyType=detachedshouses&includeSSTC=false|
    |https://www.qa.rightmove.com/overseas-property-for-sale/Rhone.html?minBedrooms=2&maxPrice=2500000&minPrice=2500000&sortType=2&propertyTypes=house&currencyCode=SEK                                                    |
    |https://www.qa.rightmove.com/overseas-property-for-sale/Apulia.html?minBedrooms=4&maxPrice=110000&minPrice=110000&sortType=2&currencyCode=USD                                                                         |

