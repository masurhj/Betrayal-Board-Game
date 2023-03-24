package betrayal.board;

import betrayal.Direction;
import betrayal.board.Floor;
import betrayal.board.Room;
import betrayal.board.RoomFloor;
import betrayal.board.RoomType;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import betrayal.gui.ComponentObserver;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    /**
     * Tests rotating room clockwise.
     */
    @Test
    public void testRotateRoomClockwise() {
        ArrayList<Direction> doors = new ArrayList<Direction>();
        doors.add(Direction.NORTH);
        doors.add(Direction.EAST);
        Room r = new Room("Temp Room", "temp_room", RoomType.NONE,
                new ArrayList<RoomFloor>(), doors, "", false);

        r.rotateRoom(true);

        assertTrue(r.roomDoorsContains(Direction.EAST));
        assertTrue(r.roomDoorsContains(Direction.SOUTH));
        assertFalse(r.roomDoorsContains(Direction.NORTH));
        assertFalse(r.roomDoorsContains(Direction.WEST));
    }

    /**
     * Tests rotating room counterclockwise.
     */
    @Test
    public void testRotateRoomCounterClockwise() {
        ArrayList<Direction> doors = new ArrayList<Direction>();
        doors.add(Direction.NORTH);
        doors.add(Direction.EAST);
        Room r = new Room("Temp Room", "temp_room", RoomType.NONE,
                new ArrayList<RoomFloor>(), doors, "", false);

        r.rotateRoom(false);

        assertTrue(r.roomDoorsContains(Direction.WEST));
        assertTrue(r.roomDoorsContains(Direction.NORTH));
        assertFalse(r.roomDoorsContains(Direction.SOUTH));
        assertFalse(r.roomDoorsContains(Direction.EAST));
    }

    /**
     * Tests adding neighbor room.
     */
    @Test
    public void testAddRoomNeighbor() {
        ArrayList<RoomFloor> floors = new ArrayList<RoomFloor>();
        floors.add(RoomFloor.GROUND);

        ArrayList<Direction> doors = new ArrayList<Direction>();
        doors.add(Direction.NORTH);
        doors.add(Direction.SOUTH);
        doors.add(Direction.EAST);
        doors.add(Direction.WEST);
        Room r1 = new Room("Temp Room 1", "temp_room1", RoomType.NONE,
                floors, doors, "", false);
        Room r2 = new Room("Temp Room 2", "temp_room2", RoomType.NONE,
                floors, doors, "", false);
        Room r3 = new Room("Temp Room 3", "temp_room3", RoomType.NONE,
                floors, doors, "", false);

        assertEquals(0, r1.neighborsSize());
        assertEquals(0, r2.neighborsSize());

        r1.addRoomNeighbor(Direction.NORTH, r2, true);

        assertEquals(1, r1.neighborsSize());
        assertEquals(1, r2.neighborsSize());

        r2.addRoomNeighbor(Direction.EAST, r3, true);
    }

    @Test
    public void setFloorWithNullObserver() {
        ArrayList<RoomFloor> floors = new ArrayList<RoomFloor>();
        floors.add(RoomFloor.GROUND);

        ArrayList<Direction> doors = new ArrayList<Direction>();


        Floor mockFloor = EasyMock.mock(Floor.class);

        Room r1 = new Room("Temp Room 1", "temp_room1", RoomType.NONE,
                floors, doors, "", false);

        EasyMock.replay(mockFloor);
        r1.setFloor(mockFloor);
        EasyMock.verify((mockFloor));
        Assertions.assertEquals(mockFloor, r1.getFloor());
    }

    @Test
    public void setFloorWithNonNullObserver() {
        ArrayList<RoomFloor> floors = new ArrayList<RoomFloor>();
        floors.add(RoomFloor.GROUND);

        ArrayList<Direction> doors = new ArrayList<Direction>();


        Floor mockFloor = EasyMock.mock(Floor.class);
        ComponentObserver mockObserver = EasyMock.mock(ComponentObserver.class);
        mockObserver.update();
        EasyMock.expectLastCall().andVoid();
        Room r1 = new Room("Temp Room 1", "temp_room1", RoomType.NONE,
                floors, doors, "", false);

        r1.addObserver(mockObserver);
        EasyMock.replay(mockFloor, mockObserver);
        r1.setFloor(mockFloor);
        EasyMock.verify(mockFloor, mockObserver);

        Assertions.assertEquals(mockFloor, r1.getFloor());

    }




}