package betrayal.controllers;

import betrayal.Direction;
import betrayal.GameState;
import betrayal.board.CannotAddRoomException;
import betrayal.board.Floor;
import betrayal.board.GridPoint;
import betrayal.board.Room;

public class RoomController {

    private final GameState state;

    /**
     * RoomController constructor.
     * @param state GameState
     */
    @SuppressFBWarnings
    public RoomController(GameState state) {
        this.state = state;
    }

    /**
     * Adds a room to a floor at a given point.
     * @param floor The floor to add the room to
     * @param room The room to add
     * @param point The point to add the room at
     */
    public void addRoom(Floor floor, Room room, GridPoint point) {
        if (!canAddRoomAt(floor, room, point)) {
            throw new CannotAddRoomException();
        }
        floor.addRoom(room, point);

        // Get all of the room's neighbors
        for (Direction direction : Direction.all()) {
            GridPoint neighborPoint = point.moveDirection(direction);
            if (!floor.hasRoomAt(neighborPoint)) {
                continue;
            }
            Room neighbor = floor.getRoomAt(neighborPoint);
            if (room.roomDoorsContains(direction) && neighbor.roomDoorsContains(direction.opposite())) {
                room.addRoomNeighbor(direction, neighbor, true);
            } else {
                neighbor.removeDoor(direction.opposite());
                room.removeDoor(direction);
            }
        }

        // Add the room to the map if it doesn't exist yet
        if (state.findRoomById(room.getRoomId()) == null) {
            state.addRoom(room);
        }
    }

    /**
     * Checks whether a room can be added at a given point.
     * @param floor The floor to check
     * @param room The room to add
     * @param point The point to add the room at
     * @return Whether the room can be added at the given point
     */
    public boolean canAddRoomAt(Floor floor, Room room, GridPoint point) {
        // Cannot add a room if it belongs to another floor
        if (!room.getFloors().contains(floor.getFloorType())) {
            return false;
        }

        // Can add a room at the origin if there is no room there
        if (point.equals(GridPoint.ORIGIN) && !floor.hasRoomAt(GridPoint.ORIGIN)) {
            return true;
        }

        // Cannot add room if there is already a room at the point
        // Cannot add room if the room is already in the floor
        if (floor.hasRoomAt(point) || floor.containsRoom(room)) {
            return false;
        }

        if (!doesRoomConnectToFloor(floor, room, point)) {
            return false;
        }

        return true;
    }

    /**
     * Checks whether a room is connected to any other room on the floor using doors.
     * @param floor The floor to check
     * @param roomToAdd The room to add
     * @param targetPoint The point to add the room at
     * @return Whether the room is connected to the floor
     */
    private boolean doesRoomConnectToFloor(Floor floor, Room roomToAdd, GridPoint targetPoint) {
        // Check each door of the room to add to see if it connects to another room
        for (Direction direction : roomToAdd.getDoors()) {
            GridPoint pointToCheck = targetPoint.moveDirection(direction);
            if (!floor.hasRoomAt(pointToCheck)) {
                continue;
            }
            Room roomToCheck = floor.getRoomAt(pointToCheck);
            if (roomToCheck.getDoors().contains(direction.opposite())) {
                return true;
            }
        }

        return false;
    }
}
