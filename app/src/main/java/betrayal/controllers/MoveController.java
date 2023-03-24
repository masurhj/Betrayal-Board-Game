package betrayal.controllers;

import betrayal.GameState;
import betrayal.gui.ComponentObserver;
import betrayal.board.Floor;
import betrayal.board.GridPoint;
import betrayal.board.Room;
import betrayal.board.RoomNotFoundException;
import betrayal.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveController {

    private final GameState state;
    private ComponentObserver roomInfoObserver;

    /**
     * MoveController constructor.
     * @param state GameState
     */
    @SuppressFBWarnings
    public MoveController(GameState state) {
        this.state = state;
    }

    /**
     * Add observer.
     * @param observer
     */
    public void addObserver(ComponentObserver observer) {
        this.roomInfoObserver = observer;
    }

    /**
     * Update the observer.
     */
    public void updateObserver() {
        if (this.roomInfoObserver != null) {
            this.roomInfoObserver.update();
        }
    }

    /**
     * Attempts to move the player to the given room and floor.
     * @param player The player to move.
     * @param destinationRoom The room to move to.
     */
    public void handlePlayerMove(Player player, String destinationRoom) {
        Room current = player.getCurrentRoom();
        Room destination = state.findRoomById(destinationRoom);

        // Get the distance from the player's current room to the destination room.
        int distance = getDistanceBetweenRooms(current.getRoomId(), destination.getRoomId());

        // Check if the player has enough moves to get there
        if (player.getMoveCounter() < distance) {
            throw new NotEnoughMovesException("Moving from " + current.getName() + " to " + destination.getName()
                    + " requires " + distance + " moves, but only " + player.getMoveCounter() + " are available.");
        }

        GridPoint destLocation = destination.getLocation();
        player.setLocation(destLocation);
        Floor destFloor = destination.getFloor();
        player.setFloor(destFloor);

        player.setMoveCounter(player.getMoveCounter() - distance);
        updateObserver();
    }

    private int getDistanceBetweenRooms(String room1, String room2) {
        Map<String, Integer> distances = getRoomDistances(room1);
        if (distances.containsKey(room2)) {
            return distances.get(room2);
        }
        throw new RoomNotFoundException();
    }

    /**
     * Performs a breadth-first search starting from the given room.
     * @param originId The room to start the search from.
     * @return A map of rooms to their distances from the origin room.
     */
    private Map<String, Integer> getRoomDistances(String originId) {
        Map<String, Integer> distances = new HashMap<>();

        Room origin = state.findRoomById(originId);

        int currentDistance = 0;
        List<Room> roomsToVisit = new ArrayList<>();
        roomsToVisit.add(origin);

        while (!roomsToVisit.isEmpty()) {
            List<Room> nextRoomsToVisit = new ArrayList<>();

            for (Room room : roomsToVisit) {
                if (!distances.containsKey(room.getRoomId())) {
                    distances.put(room.getRoomId(), currentDistance);
                    List<Room> neighbors = room.getNeighbors();
                    List<Room> linkedRooms = room.getLinkedRooms();
                    nextRoomsToVisit.addAll(neighbors);
                    nextRoomsToVisit.addAll(linkedRooms);
                }
            }

            roomsToVisit = nextRoomsToVisit;
            currentDistance++;
        }

        return distances;
    }
}
