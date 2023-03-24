package betrayal.rules;

import betrayal.BetrayalGameState;
import betrayal.Direction;
import betrayal.GameState;
import betrayal.board.*;
import betrayal.player.Player;
import betrayal.rules.GameRule;
import betrayal.rules.MovePlayerGameRule;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MovePlayerGameRuleTest {
    @Test
    public void testMovePlayerToFoyer() {
        //set up
        GameState gs = new BetrayalGameState();

        Room r = new Room("Foyer", "Foyer", RoomType.NONE, (List<RoomFloor>) new ArrayList<RoomFloor>(), (List<Direction>) new ArrayList<Direction>(), "foyer", false);
        RoomDeck deck = new RoomDeck();
        deck.addRoom(r);

        Floor f = new Floor(RoomFloor.GROUND);
        f.addRoom(r, new GridPoint(0, 0));

        gs.setFloor(RoomFloor.GROUND, f);
        gs.setRoomDeck(deck);

        Player p = new Player("Test");
        p.setCurrentFloor(RoomFloor.GROUND);
        p.setFloor(f);

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new MovePlayerGameRule("Foyer");
        ArrayList<Object> params = new ArrayList<>();
        gr.apply(gs, params);

        //assert
        assertEquals(p.getCurrentRoom().getName(), "Foyer");
    }

    @Test
    public void testMovePlayerToUnfoundRoom() {
        //set up
        GameState gs = new BetrayalGameState();

        Room r = new Room("Foyer", "Foyer", RoomType.NONE, (List<RoomFloor>) new ArrayList<RoomFloor>(), (List<Direction>) new ArrayList<Direction>(), "foyer", false);
        RoomDeck deck = new RoomDeck();
        deck.addRoom(r);

        Floor f = new Floor(RoomFloor.UPPER);
        f.addRoom(r, new GridPoint(3, 0));

        Room r2 = new Room("Upper Landing", "upperLanding", RoomType.NONE, (List<RoomFloor>) new ArrayList<RoomFloor>(), (List<Direction>) new ArrayList<Direction>(), "upper landing", false);
        deck.addRoom(r2);

        Floor f2 = new Floor(RoomFloor.UPPER);
        f2.addRoom(r2, new GridPoint(3, 0));

        gs.setFloor(RoomFloor.GROUND, f);
        gs.setFloor(RoomFloor.UPPER, f2);
        gs.setRoomDeck(deck);

        Player p = new Player("Test");
        p.setCurrentFloor(RoomFloor.UPPER);
        p.setFloor(f2);

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new MovePlayerGameRule("Foyer");
        ArrayList<Object> params = new ArrayList<>();
        gr.apply(gs, params);

        //assert
        assertEquals(r2, p.getCurrentRoom());
    }

    @Test
    public void testMovePlayerToOtherPlayer() {
        GameState gs = new BetrayalGameState();

        Floor f = new Floor(RoomFloor.GROUND);
        f.addRoom(new Room("TestRoom1", "t_1", RoomType.NONE, (List<RoomFloor>) new ArrayList<RoomFloor>(), (List<Direction>) new ArrayList<Direction>(), "test", false), new GridPoint(0, 0));
        f.addRoom(new Room("TestRoom2", "t_2", RoomType.NONE, (List<RoomFloor>) new ArrayList<RoomFloor>(), (List<Direction>) new ArrayList<Direction>(), "test", false), new GridPoint(1, 0));

        Player p1 = new Player("Test1");
        p1.setLocation(new GridPoint(0, 0));
        p1.setCurrentFloor(RoomFloor.GROUND);
        p1.setFloor(f);

        Player p2 = new Player("Test2");
        p2.setLocation(new GridPoint(1, 0));
        p2.setCurrentFloor(RoomFloor.GROUND);
        p2.setFloor(f);

        ArrayList<Player> parray = new ArrayList<>();
        parray.add(p1);
        parray.add(p2);

        gs.setPlayers(parray);
        gs.setFloor(RoomFloor.GROUND, f);

        Random rand = EasyMock.mock(Random.class);
        EasyMock.expect(rand.nextInt(2)).andReturn(1);
        EasyMock.replay(rand);

        GameRule gr = new MovePlayerGameRule(rand);
        gr.apply(gs, new ArrayList<>());

        assertEquals(RoomFloor.GROUND, p1.getCurrentFloor());
        assertEquals(new GridPoint(1,0), p1.getLocation());

        EasyMock.verify(rand);
    }
}
