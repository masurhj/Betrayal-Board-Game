package betrayal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    NONE,
    WEST;

    /**
     * Gets a collection of all directions.
     * @return a collection of all directions.
     */
    public static List<Direction> all() {
        return new ArrayList<Direction>(Arrays.asList(Direction.values()));
    }

    /**
     * Gets a list of all directions except for those specified.
     * @param doors the list of directions to exclude.
     * @return a list of all directions except for those specified.
     */
    public static List<Direction> allBut(final Direction... doors) {
        List<Direction> all = Direction.all();
        for (Direction door : doors) {
            all.remove(door);
        }
        return all;
    }

    /**
     * Gets the opposite direction.
     * @return the opposite direction.
     */
    public Direction opposite() {
        switch (this) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            default:
                return null;
        }
    }

    /**
     * Get the clockwise direction.
     * @return the clockwise direction.
     */
    public Direction clockwise() {
        switch (this) {
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            case EAST:
                return SOUTH;
            case WEST:
                return NORTH;
            default:
                return null;
        }
    }

    /**
     * Get the counter-clockwise direction.
     * @return the counter-clockwise direction.
     */
    public Direction counterClockwise() {
        switch (this) {
            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            case WEST:
                return SOUTH;
            default:
                return null;
        }
    }


    /**
     * Returns the value to offset x by based on the door.
     * @return -1, 0, 1
     */
    public int getXModification() {
        switch (this) {
            case WEST:
                return 1;
            case EAST:
                return -1;
            default:
                return 0;
        }
    }

    /**
     * Returns the value to offset y by based on the door.
     * @return -1, 0, 1
     */
    public int getYModification() {
        switch (this) {
            case NORTH:
                return 1;
            case SOUTH:
                return -1;
            default:
                return 0;
        }
    }
}
