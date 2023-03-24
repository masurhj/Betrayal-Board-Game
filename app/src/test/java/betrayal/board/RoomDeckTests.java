package betrayal.board;

import betrayal.board.Room;
import betrayal.board.RoomDeck;
import betrayal.board.RoomDeckLoader;
import betrayal.board.RoomFloor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomDeckTests {
    @Test
    public void testRemoveRoom() {
        RoomDeck deck = RoomDeckLoader.readFromFile();
        assertNotEquals(null, deck.findById("grand_staircase"));
        deck.removeRoom(deck.findById("grand_staircase"));
        assertEquals(null, deck.findById("grand_staircase"));
    }

    @Test
    public void testDrawNextAvailableRoom() {
        RoomDeck deck = RoomDeckLoader.readFromFile();
        deck.shuffle();
        Room upperRoom = deck.drawNextPossibleRoom(RoomFloor.UPPER);
        Room groundRoom = deck.drawNextPossibleRoom(RoomFloor.GROUND);
        Room basementRoom = deck.drawNextPossibleRoom(RoomFloor.BASEMENT);
        assertTrue(upperRoom.getFloors().contains(RoomFloor.UPPER));
        assertTrue(groundRoom.getFloors().contains(RoomFloor.GROUND));
        assertTrue(basementRoom.getFloors().contains(RoomFloor.BASEMENT));
    }
}
