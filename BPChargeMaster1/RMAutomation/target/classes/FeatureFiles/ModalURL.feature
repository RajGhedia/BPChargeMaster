Feature: ModalURL

  @Modal

  Scenario Outline: Check URLs for Modal

    Given The agent is on the main RM home page and I have inputted a new URL
    When The agent select url <NewURL>
    Then site should not crash

    Examples:

      |NewURL|
      |https://www.rightmove.co.uk/property/Leeds.html                                              |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/houses.html                             |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/1-bed-houses.html                       |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/2-bed-houses.html                       |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/3-bed-houses.html                       |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/4-bed-houses.html                       |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/5-bed-houses.html                       |
      |https://www.rightmove.co.uk/property/Leeds.html                                              |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/houses.html                             |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/1-bed-flats.html                        |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/2-bed-flats.htm                         |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/3-bed-flats.html                        |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/4-bed-flats.html                        |
      |https://www.rightmove.co.uk/new-homes-for-sale/Leeds/5-bed-flats.html                        |



