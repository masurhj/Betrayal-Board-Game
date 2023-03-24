package betrayal.board;

public enum RoomType {
    /**
     * Nothing happens.
     */
    NONE,
    /**
     * An omen occurs when entering.
     */
    OMEN,
    /**
     * An event occurs when entering.
     */
    EVENT,
    /**
     * Landing to a floor.
     */
    LANDING,
    /**
     * Player draws item when entering room.
     */
    ITEM,
    /**
     * Mystic Elevator.
     */
    MYSTIC_ELEVATOR,

    /**
     * Replacement for null rooms.
     */
    EMPTY
}
