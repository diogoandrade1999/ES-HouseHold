Feature: the temperature by date can be retrieved
    Scenario: client makes call to GET /temperature/{date}/{date}
    When the client calls /temperature/1622236039/1622236456
    And the client receives server temperature with value 15.0
