Feature: Player Movement Across Floors
  As a player
  I want to be able to move
  across the board
  to different floors

  Scenario: Player moves a single tile to a different floor
    Given a first full 5x5 floor
    And second full 5x5 floor linked to the first at (3, 3) and (3, 3)
    And a player with speed 1 at (3, 3) on the first floor
    When the player moves to (3, 3) on the second floor
    Then the player should be at (3, 3) on the second floor
    And the player should have 0 remaining moves

  Scenario: Player moves multiple tiles to a different floor
    Given a first full 5x5 floor
    And second full 5x5 floor linked to the first at (3, 3) and (3, 3)
    And a player with speed 2 at (3, 2) on the first floor
    When the player moves to (3, 3) on the second floor
    Then the player should be at (3, 3) on the second floor
    And the player should have 0 remaining moves

  Scenario: Player moves multiple tiles to a different floor
    Given a first full 5x5 floor
    And second full 5x5 floor linked to the first at (3, 3) and (3, 3)
    And a player with speed 2 at (3, 3) on the first floor
    When the player moves to (3, 2) on the second floor
    Then the player should be at (3, 2) on the second floor
    And the player should have 0 remaining moves

  Scenario: Player does not have enough moves left to move to the next floor
    Given a first full 5x5 floor
    And second full 5x5 floor linked to the first at (3, 3) and (3, 3)
    And a player with speed 2 at (0, 3) on the first floor
    When the player moves to (3, 3) on the second floor
    Then the player should be at (0, 3) on the first floor
    And the player should have 2 remaining moves
    And the player should be notified they do not have enough moves

  Scenario: The floor links are not aligned
    Given a first full 5x5 floor
    And second full 5x5 floor linked to the first at (0, 0) and (4, 4)
    And a player with speed 1 at (0, 0) on the first floor
    When the player moves to (4, 4) on the second floor
    Then the player should be at (4, 4) on the second floor