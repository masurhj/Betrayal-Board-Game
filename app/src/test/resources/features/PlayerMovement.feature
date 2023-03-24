Feature: Player Movement
  As a player
  I want to be able to move
  across the board

  Scenario Outline: Player moves one room in any direction
    Given a full 5x5 floor
    And a player with speed <speed> at (<startx>, <starty>)
    When the player attempts to move to (<endx>, <endy>)
    Then the player should be at (<endx>, <endy>)
    And the player should have <endmoves> moves left

    Examples:
    | speed | startx | starty | endx | endy | endmoves | description               |
    | 1     | 3      | 3      | 3    | 4    | 0        | move up 1                 |
    | 1     | 3      | 3      | 3    | 2    | 0        | move down 1               |
    | 1     | 3      | 3      | 2    | 3    | 0        | move left 1               |
    | 1     | 3      | 3      | 4    | 3    | 0        | move right 1              |
    | 2     | 3      | 3      | 3    | 4    | 1        | leftover speed after move |

  Scenario: Player does not have enough moves left
    Given a full 5x5 floor
    And a player with speed 1 at (0, 0)
    When the player attempts to move to (0, 2)
    Then the player should be at (0, 0)
    And the player should have 1 moves left
    And the user should be informed that they do not have enough moves left

  Scenario: Player tries to move to a nonexistent room
    Given a full 5x5 floor
    And a player with speed 1 at (0, 0)
    When the player attempts to move to (-1, 0)
    Then the player should be at (0, 0)
    And the player should have 1 moves left
    And the user should be informed that the room does not exist
