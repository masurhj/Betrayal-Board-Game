package betrayal.cucumber;

import betrayal.Direction;
import betrayal.GameState;
import betrayal.TestUtilities;
import betrayal.controllers.RoomController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import betrayal.gui.ComponentObserver;
import betrayal.board.*;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FloorTests {

    Floor floor;
    Room room;
    boolean canAddRoom;
    Exception exception = null;
    ComponentObserver observer = new ComponentObserver() {
        @Override
        public void update() {

        }
    };
    RoomController roomController;
    GameState state;

    public FloorTests() {
        this.state = TestUtilities.createEmptyGameState();
        this.roomController = new RoomController(this.state);
    }

    private void addRoom(Room room, GridPoint point) {
        canAddRoom = roomController.canAddRoomAt(floor, room, point);
        if (!canAddRoom) {
            assertThrows(CannotAddRoomException.class, () -> roomController.addRoom(floor, room, point));
        }
        else {
            room.addObserver(observer);
            roomController.addRoom(floor, room, point);
        }
    }

    @Given("an empty floor")
    public void anEmptyFloor() {
        floor = new Floor(RoomFloor.GROUND);
    }

    @Given("the ground floor")
    public void theGroundFloor() {
        floor = new Floor(RoomFloor.GROUND);
    }

    @Given("a room with doors on all sides")
    @Given("a room")
    public void aRoom() {
        room = new Room("Test Room", "test_room", RoomType.LANDING,
                Arrays.asList(RoomFloor.GROUND, RoomFloor.BASEMENT, RoomFloor.UPPER),
                Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
                "test room text", false);
    }

    @Given("a room that is in the {word} floor")
    public void aRoomThatIsOnAFloor(String floor1) {
        room = new Room("Test Room", "test_room", RoomType.LANDING,
                Collections.singletonList(RoomFloor.valueOf(floor1.toUpperCase())),
                Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
                "test room text", false);
    }

    @Given("a room that is in the {word} or the {word} floor")
    public void aRoomThatIsOnFloors(String floor1, String floor2) {
        room = new Room("Test Room", "test_room", RoomType.LANDING,
                Arrays.asList(RoomFloor.valueOf(floor1.toUpperCase()), RoomFloor.valueOf(floor2.toUpperCase())),
                Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
                "test room text", false);
    }

    @When("the room is added at \\({int}, {int})")
    public void aRoomIsAddedAt(Integer xCoord, Integer yCoord) {
        GridPoint point = new GridPoint(xCoord, yCoord);
        addRoom(room, point);
    }

    @Then("the room should be at \\({int}, {int})")
    public void theRoomShouldBeAt(Integer xCoord, Integer yCoord) {
        GridPoint point = new GridPoint(xCoord, yCoord);
        assertEquals(floor.getRoomAt(point), room);
    }

    @Given("a floor with a room with doors on all sides")
    public void anFloorWithARoomWithDoorsOnAllSides() {
        floor = new Floor(RoomFloor.GROUND);
        room = new Room("Test Room", "test_room", RoomType.LANDING,
                Collections.singletonList(RoomFloor.GROUND),
                Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
                "test room text", false);
        room.addObserver(observer);
        addRoom(room, new GridPoint(0, 0));
    }

    @Given("a floor with a room with doors on all sides but {word}")
    public void anFloorWithARoomOnAllSidesBut(String direction) {
        floor = new Floor(RoomFloor.GROUND);
        aRoomWithDoorsOnAllSidesBut(direction);
        addRoom(room, new GridPoint(0, 0));
    }

    @Given("a room with a door on the {word} side")
    public void aRoomWithADoorOnOneSide(String side) {
        Direction door = Direction.valueOf(side.toUpperCase());
        room = new Room("Test Room", "test_room_"+side, RoomType.LANDING,
                Collections.singletonList(RoomFloor.GROUND),
                Collections.singletonList(door),
                "test room text", false);
        room.addObserver(observer);
    }

    @Then("there should be {word} at \\({int}, {int})")
    public void thereShouldBeRoomAt(String roomId, int xCoord, int yCoord) {
        GridPoint point = new GridPoint(xCoord, yCoord);
        assertTrue(floor.hasRoomAt(point));
        Room r = floor.getRoomAt(point);
        room.addObserver(observer);
        assertEquals(r.getRoomId(), roomId);
    }

    @Then("the room should not be added")
    public void theRoomShouldNotBeAdded() {
        assertFalse(canAddRoom);
    }

    @Then("the room should not be on the floor")
    public void theRoomShouldNotBeOnTheFloor() {
        assertFalse(floor.containsRoom(room));
    }

    @Given("a room with doors on all sides but {word}")
    public void aRoomWithDoorsOnAllSidesBut(String direction) {
        room = new Room("Test Room", "test_room_not_"+direction, RoomType.LANDING,
                Collections.singletonList(RoomFloor.GROUND),
                Direction.allBut(Direction.valueOf(direction.toUpperCase())),
                "test room text", false);
        room.addObserver(observer);
    }

    @When("the room at \\({int}, {int}) is requested")
    public void aRoomIsRequestedAt(int xCoord, int yCoord) {
        GridPoint point = new GridPoint(xCoord, yCoord);
        try {
            room = floor.getRoomAt(point);
        }
        catch (Exception e) {
            room = null;
            exception = e;
        }
    }

    @Then("a {word} exception should be thrown")
    public void anExceptionShouldBeThrown(String exceptionType) {
        assertNotNull(exception);
        assertEquals(exceptionType, exception.getClass().getSimpleName());
    }
}