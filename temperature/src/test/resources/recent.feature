Feature: the last temperature can be retrieved
    Scenario: client makes call to GET /temperature/recent/{houseId}/{roomId}
    When the client calls /temperature/recent/1/1
    And the client receives server last temperature with value 15.0
