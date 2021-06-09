Feature: the temperature by date and house and room can be retrieved
    Scenario: client makes call to GET /temperature/{date}/{date}/{houseId}/{roomId}
    When the client calls /temperature/1622236039/1622236456/1/1
    And the client receives server temperature with value 15.0
