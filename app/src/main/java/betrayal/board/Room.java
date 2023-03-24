package betrayal.board;

import betrayal.controllers.SuppressFBWarnings;
import betrayal.gui.ComponentObserver;
import betrayal.Direction;
import betrayal.Token;
import betrayal.card.Card;

import java.util.*;
import java.util.stream.Collectors;

public class Room {

    private String name, id, roomText;
    private RoomType roomType;
    private Floor floor;
    private List<RoomFloor> roomFloors = new ArrayList<RoomFloor>();
    private List<Direction> roomDoors = new ArrayList<Direction>();
    private List<Direction> usedDoors = new ArrayList<Direction>();
    private List<Token> tokens = new ArrayList<Token>();
    private boolean outside;
    private ComponentObserver observer;
    private List<Card> cards = new ArrayList<>();


    /**
     * Hashmap of neighbors based on which door they're connected to.
     */
    private HashMap<Direction, Room> neighbors = new HashMap<Direction, Room>();

    private List<Room> linkedRooms = new ArrayList<>();

    /**
     * Room constructor with all the fields.
     * @param name1
     * @param id1
     * @param roomType1
     * @param roomFloors1
     * @param roomDoors1
     * @param roomText1
     * @param outside1
     */
    public Room(final String name1, final String id1, final RoomType roomType1,
                final List<RoomFloor> roomFloors1,
                final List<Direction> roomDoors1,
                final String roomText1, final boolean outside1) {
        this.name = name1;
        this.id = id1;
        this.roomType = roomType1;
        this.roomFloors.addAll(roomFloors1);
        this.roomDoors.addAll(roomDoors1);
        this.roomText = roomText1;
        this.outside = outside1;
    }

    /**
     * Adds a neighboring room on the given door. Does the same
     *      * for the room being added.
     * @param direction
     * @param room
     * @param once
     */
    public void addRoomNeighbor(final Direction direction, final Room room,
                                final boolean once) {
        if (once) {
            room.addRoomNeighbor(direction.opposite(), this, false);
        }
        neighbors.put(direction, room);
        this.roomDoors.remove(direction);
        this.usedDoors.add(direction);
        updateObserver();
    }

    /**
     * Remove a door from the directions list. Used if two rooms are next to each other
     * but don't connect.
     * @param direction
     */
    public void removeDoor(Direction direction) {
        this.roomDoors.remove(direction);
        if (this.observer != null) {
            observer.update();
        }
    }

    /**
     * Returns true if the room has a door in the given direction.
     * @param roomDoor
     * @return boolean
     */
    public boolean roomDoorsContains(final Direction roomDoor) {
        return this.roomDoors.contains(roomDoor);
    }

    /**
     * Gets the doors of the room.
     * @return The doors of the room.
     */
    public Set<Direction> getDoors() {
        return new HashSet<Direction>(this.roomDoors);
    }

    public Set<Direction> getOppositeDoors() {
        ArrayList<Direction> oppositeDoors = new ArrayList<Direction>(roomDoors);
        oppositeDoors.replaceAll(Direction::opposite);
        return new HashSet<Direction>(oppositeDoors);
    }

    public Set<Direction> getUsedDoors() {
        return new HashSet<Direction>(this.usedDoors);
    }

    /**
     * Gets the floors that this room is allowed to be placed on.
     * @return The floors that this room is allowed to be placed on
     */
    public Set<RoomFloor> getFloors() {
        return new HashSet<RoomFloor>(this.roomFloors);
    }

    /**
     * Returns name of room.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns id of room.
     * @return id
     */
    public String getRoomId() {
        return id;
    }

    /**
     * Returns type of room.
     * @return roomType
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Returns descriptor text of room.
     * @return roomText
     */
    public String getRoomText() {
        return roomText;
    }

    /**
     * Returns true if room is outside, false otherwise.
     * @return outside
     */
    public boolean isOutside() {
        return outside;
    }

    /**
     * Returns whether the room is currently in play.
     * @return true if the room is currently in play
     */
    public boolean isInPlay() {
        return this.floor != null && this.floor.containsRoom(this);
    }

    /**
     * Returns size of neighbors.
     * @return size
     */
    public int neighborsSize() {
        return neighbors.size();
    }

    /**
     * X getter.
     * @return x
     */
    public int getX() {
        return this.getLocation().getX();
    }

    /**
     * Y getter.
     * @return y
     */
    public int getY() {
        return this.getLocation().getY();
    }

    /**
     * Returns the location of this room.
     * @return location of this room
     */
    public GridPoint getLocation() {
        return this.floor.getRoomLocation(this);
    }

    /**
     * Set the final floor of the room it was added to.
     * @param floor
     */
    @SuppressFBWarnings
    public void setFloor(final Floor floor) {
        this.floor = floor;
        updateObserver();
    }

    /**
     * Get the final floor.
     * @return finalFloor
     */
    @SuppressFBWarnings
    public Floor getFloor() {
        return floor;
    }

    /**
     * Gets the floor type.
     * @return the floor type
     */
    public RoomFloor getFloorType() {
        return this.floor.getFloorType();
    }

    /**
     * Gets the tokens in a room.
     * @return The room's tokens.
     */
    @SuppressFBWarnings
    public Set<Token> getTokens() {
        return new HashSet<Token>(this.tokens);
    }

    /**
     * Add token to room.
     * @param newToken
     */
    public void addToken(Token newToken) {
        this.tokens.add(newToken);
    }

    /**
     * Rotates the rooms doors.
     * @param clockwise
     */
    public void rotateRoom(final boolean clockwise) {
        for (int i = 0; i < roomDoors.size(); i++) {
            if (clockwise) {
                roomDoors.set(i, roomDoors.get(i).clockwise());
            } else {
                roomDoors.set(i, roomDoors.get(i).counterClockwise());
            }
        }
        updateObserver();
    }

    /**
     * Update the observer.
     */
    public void updateObserver() {
        if (observer != null) {
            observer.update();
        }
    }

    /**
     *
     * this function is used for the BFS used in player movement.
     * @return list of neighbors
     */
    public List<Room> getNeighbors() {
        return new ArrayList<>(this.neighbors.values());
    }

    /**
     * Add the component observer which updates when floor is changed.
     * @param observer
     */
    public void addObserver(final ComponentObserver observer) {
        this.observer = observer;
    }

    /**
     * Returns a list of rooms that are linked to this room.
     * @return list of rooms that are linked
     */
    public List<Room> getLinkedRooms() {
        return this.linkedRooms.stream()
                .filter(Room::isInPlay)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new linked room.
     * @param room the room to be linked to this room
     */
    public void addLinkedRoom(Room room) {
        linkedRooms.add(room);
    }

    /**
     * @return string representation of the room
     */
    @Override
    public String toString() {
        return "Room{ id=" + id + ", floor=" + floor + " }";
    }


    /**
     * Adds the given card to the room's list of cards.
     * @param inputCard
     */
    public void addCard(Card inputCard) {
        cards.add(inputCard);
    }
}
