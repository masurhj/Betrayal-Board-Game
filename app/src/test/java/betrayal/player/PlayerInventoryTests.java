package betrayal.player;
import betrayal.BetrayalGameState;
import betrayal.GameState;
import betrayal.board.Room;
import betrayal.card.Card;
import betrayal.card.Item;
import betrayal.player.Player;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class PlayerInventoryTests {


    @Test
    public void testPickUpLegitItemEmptyInventory() {
        Player player = new Player("test player");
        Item mockItem = EasyMock.mock(Item.class);
        boolean expected = true;

        EasyMock.expect(mockItem.getName()).andReturn("test item");
        EasyMock.replay(mockItem);

        boolean actual = player.pickUp(mockItem);

        EasyMock.verify(mockItem);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(player.getInventory().get("test item"), mockItem);
    }

    @Test
    public void testPickUpNonLegitItemEmptyInventory() {
        Player player = new Player("test player");
        Item mockItem = EasyMock.mock(Item.class);
        boolean expected = false;

        EasyMock.expect(mockItem.getName()).andReturn("EMPTY_ITEM");
        EasyMock.replay(mockItem);

        boolean actual = player.pickUp(mockItem);

        EasyMock.verify(mockItem);
        Assertions.assertEquals(expected, actual);
        Assertions.assertNotEquals(player.getInventory().get("EMPTY_ITEM"), mockItem);
    }

    @Test
    public void testPickUpDuplicateItemEmptyInventory() {
        Player player = new Player("test player");
        Item mockItem = EasyMock.mock(Item.class);
        boolean expected = false;

        EasyMock.expect(mockItem.getName()).andReturn("test item");
        EasyMock.expect(mockItem.getName()).andReturn("test item");
        EasyMock.replay(mockItem);

        player.pickUp(mockItem);
        boolean actual = player.pickUp(mockItem);

        EasyMock.verify(mockItem);

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(player.getInventory().get("test item"), mockItem);
    }

    @Test
    public void testPickUpMultipleItemLegit() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        Item mockItem2 = EasyMock.mock(Item.class);

        boolean expected = true;

        EasyMock.expect(mockItem1.getName()).andReturn("test item 1");
        EasyMock.expect(mockItem2.getName()).andReturn("test item 2");
        EasyMock.replay(mockItem1);
        EasyMock.replay(mockItem2);


        boolean actual1 = player.pickUp(mockItem1);
        boolean actual2 = player.pickUp(mockItem2);

        EasyMock.verify(mockItem1, mockItem2);

        Assertions.assertEquals(expected, actual1);
        Assertions.assertEquals(expected, actual2);

        Assertions.assertEquals(player.getInventory().get("test item 1"), mockItem1);
        Assertions.assertEquals(player.getInventory().get("test item 2"), mockItem2);
    }

    @Test
    public void useItemLegit() {
        GameState testState = EasyMock.mock(BetrayalGameState.class);
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 1", mockItem1);


        mockItem1.use(testState);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockItem1, testState);

        player.use("test item 1", testState);

        EasyMock.verify(mockItem1, testState);
    }


    @Test
    public void useItemNotExist() {
        GameState testState = EasyMock.mock(BetrayalGameState.class);
        Player player = new Player("test player");
        boolean threwException = false;
        EasyMock.replay(testState);
        try {
            player.use("test item 1", testState);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        Assertions.assertTrue(threwException);
        EasyMock.verify(testState);
    }

    @Test
    public void useWrongItem1() {
        GameState testState = EasyMock.mock(BetrayalGameState.class);
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 0", mockItem1);
        boolean threwException = false;

        EasyMock.replay(mockItem1, testState);

        try {
            player.use("test item 1", testState);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1, testState);
        Assertions.assertTrue(threwException);
    }
    @Test
    public void useWrongItem2() {
        GameState testState = EasyMock.mock(BetrayalGameState.class);
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item ", mockItem1);
        boolean threwException = false;

        EasyMock.replay(mockItem1, testState);

        try {
            player.use("test item 1", testState);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1, testState);
        Assertions.assertTrue(threwException);
    }

    @Test
    public void useWrongItem3() {
        GameState testState = EasyMock.mock(BetrayalGameState.class);
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 11", mockItem1);
        boolean threwException = false;

        EasyMock.replay(mockItem1, testState);

        try {
            player.use("test item 1", testState);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1, testState);
        Assertions.assertTrue(threwException);
    }

    @Test
    public void useWrongItem4() {
        GameState testState = EasyMock.mock(BetrayalGameState.class);
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 1", mockItem1);
        boolean threwException = false;

        EasyMock.replay(mockItem1, testState);

        try {
            player.use("", testState);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1);
        Assertions.assertTrue(threwException);
    }

    @Test
    public void testDropOneItemLegitEmpty() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        GameState state = EasyMock.mock(BetrayalGameState.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 1", mockItem1);
        Item expected = mockItem1;
        mockItem1.remove(state);

        EasyMock.replay(mockItem1);

        Card actual = player.drop("test item 1", state);

        EasyMock.verify(mockItem1);

        Assertions.assertEquals(expected, actual);
        Assertions.assertFalse(playerMap.containsKey("test item 1"));
    }


    @Test
    public void testDropOneItemNotExistEmpty() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        GameState state = EasyMock.mock(BetrayalGameState.class);
        HashMap<String, Card> playerMap = player.getInventory();
        boolean threwException = false;

        EasyMock.replay(mockItem1);

        try {
            player.drop("test item 1", state);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }

        EasyMock.verify(mockItem1);
        Assertions.assertTrue(threwException);
        Assertions.assertFalse(playerMap.containsKey("test item 1"));
    }


    @Test
    public void testDropOneItemNotExistNotEmpty() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        Item mockItem2 = EasyMock.mock(Item.class);
        GameState state = EasyMock.mock(BetrayalGameState.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("dont call this", mockItem2);
        boolean threwException = false;

        EasyMock.replay(mockItem1, mockItem2);

        try {
            player.drop("test item 1", state);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }

        EasyMock.verify(mockItem1, mockItem2);
        Assertions.assertTrue(threwException);
        Assertions.assertFalse(playerMap.containsKey("test item 1"));
        Assertions.assertTrue(playerMap.containsKey("dont call this"));
    }

    @Test
    public void testDropOneItemLegitNotEmpty() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        Item mockItem2 = EasyMock.mock(Item.class);
        GameState state = EasyMock.mock(BetrayalGameState.class);

        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 1", mockItem1);
        playerMap.put("test item 2", mockItem2);
        Item expected = mockItem1;
        mockItem1.remove(state);

        EasyMock.replay(mockItem1);

        Card actual = player.drop("test item 1", state);

        EasyMock.verify(mockItem1);

        Assertions.assertEquals(expected, actual);
        Assertions.assertFalse(playerMap.containsKey("test item 1"));
        Assertions.assertTrue(playerMap.containsKey("test item 2"));

    }

    @Test
    public void dropWrongItem1() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        GameState state = EasyMock.mock(BetrayalGameState.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 0", mockItem1);
        boolean threwException = false;

        EasyMock.replay(mockItem1);

        try {
            player.drop("test item 1", state);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1);
        Assertions.assertTrue(threwException);
    }

    @Test
    public void dropWrongItem2() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        GameState state = EasyMock.mock(BetrayalGameState.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item ", mockItem1);
        boolean threwException = false;

        EasyMock.replay(mockItem1);

        try {
            player.drop("test item 1", state);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1);
        Assertions.assertTrue(threwException);
    }

    @Test
    public void dropWrongItem3() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        GameState state = EasyMock.mock(BetrayalGameState.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 11", mockItem1);
        boolean threwException = false;

        EasyMock.replay(mockItem1);

        try {
            player.drop("test item 1", state);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1);
        Assertions.assertTrue(threwException);
    }

    @Test
    public void dropWrongItem4() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        GameState state = EasyMock.mock(BetrayalGameState.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 0", mockItem1);
        boolean threwException = false;

        EasyMock.replay(mockItem1);

        try {
            player.drop("0", state);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1);
        Assertions.assertTrue(threwException);
    }

    @Test
    public void testDropToRoomOneItemLegitEmpty() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 1", mockItem1);


        Room mockRoom = EasyMock.mock(Room.class);
        mockRoom.addCard(mockItem1);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockItem1, mockRoom);

        player.dropToRoom("test item 1", mockRoom);

        EasyMock.verify(mockItem1, mockRoom);

        Assertions.assertFalse(playerMap.containsKey("test item 1"));
    }

    @Test
    public void testDropToRoomOneItemNotExistEmpty() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        boolean threwException = false;

        Room mockRoom = EasyMock.mock(Room.class);

        EasyMock.replay(mockItem1, mockRoom);
        try {
            player.dropToRoom("test item 1", mockRoom);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }

        EasyMock.verify(mockItem1, mockRoom);
        Assertions.assertTrue(threwException);
        Assertions.assertFalse(playerMap.containsKey("test item 1"));
    }

    @Test
    public void testDropToRoomOneItemNotExistNotEmpty() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        Item mockItem2 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("dont call this", mockItem2);
        boolean threwException = false;

        Room mockRoom = EasyMock.mock(Room.class);


        EasyMock.replay(mockItem1, mockItem2, mockRoom);

        try {
            player.dropToRoom("test item 1", mockRoom);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }

        EasyMock.verify(mockItem1, mockItem2, mockRoom);
        Assertions.assertTrue(threwException);
        Assertions.assertFalse(playerMap.containsKey("test item 1"));
        Assertions.assertTrue(playerMap.containsKey("dont call this"));
    }

    @Test
    public void testDropToRoomOneItemLegitNotEmpty() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        Item mockItem2 = EasyMock.mock(Item.class);

        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 1", mockItem1);
        playerMap.put("test item 2", mockItem2);

        Room mockRoom = EasyMock.mock(Room.class);
        mockRoom.addCard(mockItem1);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockItem1, mockRoom);

        player.dropToRoom("test item 1", mockRoom);

        EasyMock.verify(mockItem1);

        Assertions.assertFalse(playerMap.containsKey("test item 1"));
        Assertions.assertTrue(playerMap.containsKey("test item 2"));

    }


    @Test
    public void dropToRoomWrongItem1() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 0", mockItem1);
        boolean threwException = false;
        Room mockRoom = EasyMock.mock(Room.class);

        EasyMock.replay(mockItem1, mockRoom);

        try {
            player.dropToRoom("test item 1", mockRoom);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1, mockRoom);
        Assertions.assertTrue(threwException);
    }

    @Test
    public void dropToRoomWrongItem2() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item ", mockItem1);
        boolean threwException = false;
        Room mockRoom = EasyMock.mock(Room.class);

        EasyMock.replay(mockItem1, mockRoom);


        try {
            player.dropToRoom("test item 1", mockRoom);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1, mockRoom);
        Assertions.assertTrue(threwException);
    }

    @Test
    public void dropToRoomWrongItem3() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 11", mockItem1);
        boolean threwException = false;
        Room mockRoom = EasyMock.mock(Room.class);

        EasyMock.replay(mockItem1, mockRoom);

        try {
            player.dropToRoom("test item 1", mockRoom);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1, mockRoom);
        Assertions.assertTrue(threwException);
    }

    @Test
    public void dropToRoomWrongItem4() {
        Player player = new Player("test player");
        Item mockItem1 = EasyMock.mock(Item.class);
        HashMap<String, Card> playerMap = player.getInventory();
        playerMap.put("test item 0", mockItem1);
        boolean threwException = false;
        Room mockRoom = EasyMock.mock(Room.class);

        EasyMock.replay(mockItem1, mockRoom);

        try {
            player.dropToRoom("0", mockRoom);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        EasyMock.verify(mockItem1, mockRoom);
        Assertions.assertTrue(threwException);
    }


}

