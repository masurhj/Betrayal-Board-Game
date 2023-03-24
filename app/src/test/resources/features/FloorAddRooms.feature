Feature: Adding (Discovering) Rooms to a Floor
  As a player
  I want to discover new rooms on a floor

  Scenario: Adding the first room to a floor at the origin
    Given an empty floor
    And a room
    When the room is added at (0, 0)
    Then the room should be at (0, 0)

  Scenario: Adding the first room to a floor at a point other than the origin
    Given an empty floor
    And a room
    When the room is added at (1, 2)
    Then the room should not be added
    And the room should not be on the floor

  Scenario: Adding a second room to the east of the first room
    Given a floor with a room with doors on all sides
    And a room with a door on the west side
    When the room is added at (1, 0)
    Then the room should be at (1, 0)
    And there should be test_room at (0, 0)

  Scenario: Adding a second room to the west of the first room
    Given a floor with a room with doors on all sides
    And a room with a door on the east side
    When the room is added at (-1, 0)
    Then the room should be at (-1, 0)
    And there should be test_room at (0, 0)

  Scenario: Adding a second room to the north of the first room
    Given a floor with a room with doors on all sides
    And a room with a door on the south side
    When the room is added at (0, 1)
    Then the room should be at (0, 1)
    And there should be test_room at (0, 0)

  Scenario: Adding a second room to the south of the first room
    Given a floor with a room with doors on all sides
    And a room with a door on the north side
    When the room is added at (0, -1)
    Then the room should be at (0, -1)
    And there should be test_room at (0, 0)

  Scenario: Adding a second room completely disconnected from the first room should fail
    Given a floor with a room with doors on all sides
    And a room
    When the room is added at (1, 1)
    Then the room should not be added
    And the room should not be on the floor

  Scenario: Adding a room where another room already exists should not be possible
    Given a floor with a room with doors on all sides
    And a room with a door on the north side
    When the room is added at (0, 0)
    Then the room should not be added
    And the room should not be on the floor

  Scenario: Adding a room to the floor twice should not be possible
    Given an empty floor
    And a room
    When the room is added at (0, 0)
    Then the room should be at (0, 0)
    When the room is added at (0, 1)
    Then the room should not be added
    But the room should be at (0, 0)

  Scenario: Adding a second room with no door on the south side to the north side of a room should not be possible
    Given a floor with a room with doors on all sides
    And a room with doors on all sides but south
    When the room is added at (0, 1)
    Then the room should not be added
    And the room should not be on the floor

  Scenario: Adding a second room with no door on the north side to the south side of a room should not be possible
    Given a floor with a room with doors on all sides
    And a room with doors on all sides but north
    When the room is added at (0, -1)
    Then the room should not be added
    And the room should not be on the floor

  Scenario: Adding a second room with no door on the west side to the east side of a room should not be possible
    Given a floor with a room with doors on all sides
    And a room with doors on all sides but west
    When the room is added at (1, 0)
    Then the room should not be added
    And the room should not be on the floor

  Scenario: Adding a second room with no door on the east side to the west side of a room should not be possible
    Given a floor with a room with doors on all sides
    And a room with doors on all sides but east
    When the room is added at (-1, 0)
    Then the room should not be added
    And the room should not be on the floor

  Scenario: Adding a second room to the north side of a room with no door on the north side should not be possible
    Given a floor with a room with doors on all sides but north
    And a room with doors on all sides
    When the room is added at (0, 1)
    Then the room should not be added
    And the room should not be on the floor

  Scenario: Adding a second room to the south side of a room with no door on the south side should not be possible
    Given a floor with a room with doors on all sides but south
    And a room with doors on all sides
    When the room is added at (0, -1)
    Then the room should not be added
    And the room should not be on the floor

  Scenario: Adding a second room to the west side of a room with no door on the west side should not be possible
    Given a floor with a room with doors on all sides but west
    And a room with doors on all sides
    When the room is added at (-1, 0)
    Then the room should not be added
    And the room should not be on the floor

  Scenario: Adding a second room to the east side of a room with no door on the east side should not be possible
    Given a floor with a room with doors on all sides but east
    And a room with doors on all sides
    When the room is added at (1, 0)
    Then the room should not be added
    And the room should not be on the floor

  Scenario: Adding a room that can only go in the basement to the ground floor is not allowed
    Given the ground floor
    And a room that is in the basement floor
    When the room is added at (0, 0)
    Then the room should not be added

  Scenario: Adding a room that can only go in the basement or the upper floor to the ground floor is not allowed
    Given the ground floor
    And a room that is in the basement or the upper floor
    When the room is added at (0, 0)
    Then the room should not be added

  Scenario: Trying to get a room at a location that does not exist should throw an error
    Given a floor with a room with doors on all sides
    When the room at (5, 5) is requested
    Then a RoomNotFoundException exception should be thrown