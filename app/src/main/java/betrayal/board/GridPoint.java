package betrayal.board;

import betrayal.Direction;

public final class GridPoint {
    private final int x;
    private final int y;

    public static final GridPoint ORIGIN = new GridPoint(0, 0);

    /**
     * Construct a new point with coordinates (x, y).
     * @param xCoord The x coordinate.
     * @param yCoord The y coordinate.
     */
    public GridPoint(final int xCoord, final int yCoord) {
        this.x = xCoord;
        this.y = yCoord;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof GridPoint)) {
            return false;
        }

        GridPoint gp = (GridPoint) o;
        return x == gp.x && y == gp.y;
    }

    public int hashCode() {
        int result = 17;
        int multiplier = 37;
        result = multiplier * result + x;
        result = multiplier * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Shifts the point in the given direction by the given amount.
     * @param direction The direction to shift in.
     * @param amount The amount to shift.
     * @return A new point shifted in the given direction by the given amount.
     */
    public GridPoint moveDirection(final Direction direction, final int amount) {
        switch (direction) {
            case NORTH:
                return new GridPoint(x, y + amount);
            case SOUTH:
                return new GridPoint(x, y - amount);
            case EAST:
                return new GridPoint(x + amount, y);
            case WEST:
                return new GridPoint(x - amount, y);
            default:
                return null;
        }
    }

    /**
     * Shifts the point in the given direction by one.
     * @param direction The direction to shift in.
     * @return A new point shifted in the given direction by one.
     */
    public GridPoint moveDirection(final Direction direction) {
        return moveDirection(direction, 1);
    }
}
