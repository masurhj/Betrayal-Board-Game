package betrayal.board;

import java.util.*;

public class RoomDeck {

    /**
     * The pile of cards to draw from.
     */
    private ArrayList<Room> deck;

    /**
     * Lookup table to quickly find a room by its id.
     * This field contains all of the rooms in the game, not just those in the deck.
     */
    private Map<String, Room> roomsById;

    /**
     * Constructs a new empty room deck.
     */
    public RoomDeck() {
        deck = new ArrayList<Room>();
        roomsById = new HashMap<>();
    }

    /**
     * Constructs a new room deck with the given rooms.
     * @param rooms the rooms to add to the deck
     */
    public RoomDeck(Collection<Room> rooms) {
        this.deck = new ArrayList<>(rooms);
        roomsById = new HashMap<>();
        buildIdMapping();
    }

    private void buildIdMapping() {
        for (Room r : deck) {
            roomsById.put(r.getRoomId(), r);
        }
    }

    /**
     * Returns the room with the given id.
     * @param id the id of the room to return
     * @return the room with the given id
     */
    public Room findById(String id) {
        return roomsById.get(id);
    }

    /**
     * Adds a new room to the deck.
     * @param room the room to add
     */
    public void addRoom(Room room) {
        if (room.isInPlay()) {
            deck.add(room);
        }
        roomsById.put(room.getRoomId(), room);
    }

    /**
     * Removes the given room from the deck.
     * @param room the room to remove
     * @return true if the room was removed, false otherwise
     */
    public boolean removeRoom(Room room) {
        int initialSize = roomsById.size();
        roomsById.remove(room.getRoomId());
        deck.remove(room);
        return initialSize != roomsById.size();
    }

    /**
     * Shuffles the room deck.
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Draw a room which can be placed on the given floor.
     * @param desiredFloor
     * @return the next available room
     */
    public Room drawNextPossibleRoom(RoomFloor desiredFloor) {
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getFloors().contains(desiredFloor)) {
                Room foundRoom = deck.get(i);
                deck.remove(i);
                return foundRoom;
            }
        }
        return null;
    }
}
