package betrayal.board;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Floor implements Iterable<Room> {
    private final Map<GridPoint, Room> rooms;
    private final RoomFloor floor;
    private int minX, maxX;

    /**
     * Constructs a new Floor with no rooms.
     * @param floor
     */
    public Floor(final RoomFloor floor) {
        rooms = new HashMap<>();
        this.floor = floor;
    }

    /**
     * Places the room at the given point. Does not check if the room is allowed to be placed there.
     * @param room The room to place
     * @param point The point to place the room at
     */
    public void addRoom(Room room, GridPoint point) {
        rooms.put(point, room);
        room.setFloor(this);
        if (room.getX() < minX) {
            minX = room.getX();
        } else if (room.getX() > maxX) {
            maxX = room.getX();
        }
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    /**
     * Checks whether a room exists at a given point.
     * @param point The point to check
     * @return Whether a room exists at the given point
     */
    public boolean hasRoomAt(final GridPoint point) {
        return rooms.containsKey(point);
    }

    /**
     * Gets the room at a given point.
     * @param point The location of the room
     * @return The room at the given point
     * @throws RoomNotFoundException If there is no room at the given point
     */
    public Room getRoomAt(final GridPoint point) {
        if (!rooms.containsKey(point)) {
            throw new RoomNotFoundException();
        }

        return rooms.get(point);
    }

    /**
     * Finds the point a room is located at.
     * @param room the room to find
     * @return the point the room is located at
     */
    public GridPoint getRoomLocation(final Room room) {
        for (Map.Entry<GridPoint, Room> roomEntry : rooms.entrySet()) {
            if (rooms.get(roomEntry.getKey()).getRoomId().equals(room.getRoomId())) {
                return roomEntry.getKey();
            }
        }

        throw new RoomNotFoundException();
    }

    /**
     * Checks whether a room is in the floor.
     * @param room The room to check
     * @return Whether the room is in the floor
     */
    public boolean containsRoom(final Room room) {
        return rooms.containsValue(room);
    }

    /**
     * Gets the type of this floor.
     * @return the type of this floor
     */
    public RoomFloor getFloorType() {
        return floor;
    }

    /**
     * Returns an iterator over the rooms in this floor.
     * @return an iterator over the rooms in this floor
     */
    @Override
    public Iterator<Room> iterator() {
        return rooms.values().iterator();
    }

    /**
     * @return string representation of this floor
     */
    @Override
    public String toString() {
        return "Floor: " + floor.toString();
    }

}
