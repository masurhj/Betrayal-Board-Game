package betrayal.rules;

import betrayal.*;
import betrayal.board.*;
import betrayal.card.Card;
import betrayal.card.Item;
import betrayal.player.Player;
import betrayal.player.RollType;
import betrayal.rules.GameRule;
import betrayal.rules.StealGameRule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StealGameRuleTests {
    @Test
    public void testStealGameRule() {
        GameState state = new BetrayalGameState();
        Player firstPlayer = new Player("Test1");
        Player secondPlayer = new Player("Test2");
        Item item = new Item("TestStealItem");
        GameRule gameRule = new StealGameRule();
        item.addRemoveGameRule(gameRule);
        item.setRollType(RollType.NONE);

        Item itemToBeStolen = new Item("ItemToBeStolen");

        firstPlayer.pickUp(item);
        secondPlayer.pickUp(itemToBeStolen);

        ArrayList<RoomFloor> floors = new ArrayList<>();
        floors.add(RoomFloor.GROUND);
        ArrayList<Direction> doors = new ArrayList<>();
        doors.add(Direction.NORTH);
        Room room = new Room("TestRoom", "test_room", RoomType.NONE, floors, doors, "test room", false);

        Floor currentFloor = new Floor(RoomFloor.GROUND);
        currentFloor.addRoom(room, new GridPoint(0, 0));

        firstPlayer.setCurrentFloor(RoomFloor.GROUND);
        secondPlayer.setCurrentFloor(RoomFloor.GROUND);
        firstPlayer.setFloor(currentFloor);
        secondPlayer.setFloor(currentFloor);
        ArrayList<Player> players = new ArrayList<>();
        players.add(firstPlayer);
        players.add(secondPlayer);
        state.setPlayers(players);

        Card returnedItem = firstPlayer.drop("TestStealItem", state);
        assertEquals(item, returnedItem);

        returnedItem.remove(state);
        assertTrue(firstPlayer.getInventory().containsKey("ItemToBeStolen"));
    }

    @Test
    public void testStealGameRuleOtherPlayerHasNoItems() {
        GameState state = new BetrayalGameState();
        Player firstPlayer = new Player("Test1");
        Player secondPlayer = new Player("Test2");
        Item item = new Item("TestStealItem");
        GameRule gameRule = new StealGameRule();
        item.addRemoveGameRule(gameRule);
        item.setRollType(RollType.NONE);

        firstPlayer.pickUp(item);

        ArrayList<RoomFloor> floors = new ArrayList<>();
        floors.add(RoomFloor.GROUND);
        ArrayList<Direction> doors = new ArrayList<>();
        doors.add(Direction.NORTH);
        Room room = new Room("TestRoom", "test_room", RoomType.NONE, floors, doors, "test room", false);

        Floor currentFloor = new Floor(RoomFloor.GROUND);
        currentFloor.addRoom(room, new GridPoint(0, 0));

        firstPlayer.setCurrentFloor(RoomFloor.GROUND);
        secondPlayer.setCurrentFloor(RoomFloor.GROUND);
        firstPlayer.setFloor(currentFloor);
        secondPlayer.setFloor(currentFloor);
        ArrayList<Player> players = new ArrayList<>();
        players.add(firstPlayer);
        players.add(secondPlayer);
        state.setPlayers(players);

        Card returnedItem = firstPlayer.drop("TestStealItem", state);
        assertEquals(item, returnedItem);

        returnedItem.remove(state);
        assertTrue(firstPlayer.getInventory().isEmpty());
    }
    @Test
    public void testStealGameRuleNoOtherPlayer() {
        GameState state = new BetrayalGameState();
        Player firstPlayer = new Player("Test1");
        Item item = new Item("TestStealItem");
        GameRule gameRule = new StealGameRule();
        item.addRemoveGameRule(gameRule);
        item.setRollType(RollType.NONE);

        firstPlayer.pickUp(item);

        ArrayList<RoomFloor> floors = new ArrayList<>();
        floors.add(RoomFloor.GROUND);
        ArrayList<Direction> doors = new ArrayList<>();
        doors.add(Direction.NORTH);
        Room room = new Room("TestRoom", "test_room", RoomType.NONE, floors, doors, "test room", false);

        Floor currentFloor = new Floor(RoomFloor.GROUND);
        currentFloor.addRoom(room, new GridPoint(0, 0));

        firstPlayer.setCurrentFloor(RoomFloor.GROUND);
        firstPlayer.setFloor(currentFloor);
        ArrayList<Player> players = new ArrayList<>();
        players.add(firstPlayer);
        state.setPlayers(players);

        Card returnedItem = firstPlayer.drop("TestStealItem", state);
        assertEquals(item, returnedItem);

        returnedItem.remove(state);
        assertTrue(firstPlayer.getInventory().isEmpty());
    }

    @Test
    public void testStealGameRuleOtherPlayerNotInSameRoom() {
        GameState state = new BetrayalGameState();
        Player firstPlayer = new Player("Test1");
        Player secondPlayer = new Player("Test2");
        Item item = new Item("TestStealItem");
        GameRule gameRule = new StealGameRule();
        item.addRemoveGameRule(gameRule);
        item.setRollType(RollType.NONE);

        firstPlayer.pickUp(item);

        ArrayList<RoomFloor> floors = new ArrayList<>();
        floors.add(RoomFloor.GROUND);
        ArrayList<Direction> doors = new ArrayList<>();
        doors.add(Direction.NORTH);
        Room room = new Room("TestRoom", "test_room", RoomType.NONE, floors, doors, "test room", false);
        Room secondRoom = new Room("TestRoom2", "test_room2", RoomType.NONE, floors, doors, "test room 2", false);


        Floor currentFloor = new Floor(RoomFloor.GROUND);
        currentFloor.addRoom(room, new GridPoint(0, 0));
        currentFloor.addRoom(secondRoom, new GridPoint(1, 1));

        firstPlayer.setCurrentFloor(RoomFloor.GROUND);
        secondPlayer.setCurrentFloor(RoomFloor.GROUND);
        firstPlayer.setFloor(currentFloor);
        secondPlayer.setFloor(currentFloor);
        secondPlayer.setLocation(new GridPoint(1, 1));
        ArrayList<Player> players = new ArrayList<>();
        players.add(firstPlayer);
        players.add(secondPlayer);
        state.setPlayers(players);

        Card returnedItem = firstPlayer.drop("TestStealItem", state);
        assertEquals(item, returnedItem);

        returnedItem.remove(state);
        assertTrue(firstPlayer.getInventory().isEmpty());
    }
}
