Feature: the version can be retrieved
    Scenario: client makes call to GET /temperature/version
    When the client calls /temperature/version
    Then the client receives status code of 200
    And the client receives server version 1.0
