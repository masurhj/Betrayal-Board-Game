package betrayal.rules;

import betrayal.BetrayalGameState;
import betrayal.Direction;
import betrayal.GameState;
import betrayal.Token;
import betrayal.board.*;
import betrayal.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceTokenGameRuleTests {

    @Test
    public void placeTokenInRoom() {
        //set up
        GameState gs = new BetrayalGameState();

        Room roomForPlayer = new Room("Foyer", "Foyer", RoomType.NONE, (List<RoomFloor>) new ArrayList<RoomFloor>(), (List<Direction>) new ArrayList<Direction>(), "foyer", false);
        RoomDeck deck = new RoomDeck();
        deck.addRoom(roomForPlayer);

        Floor f = new Floor(RoomFloor.GROUND);
        f.addRoom(roomForPlayer, new GridPoint(0,0));

        gs.setFloor(RoomFloor.GROUND, f);
        gs.setRoomDeck(deck);

        Player p = new Player("Test");
        p.setCurrentFloor(RoomFloor.GROUND);
        p.setFloor(f);

        p.setLocation(new GridPoint(0,0));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);
        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        gs.getCurrentPlayer().getCurrentRoom().addToken(new Token());

        //assert
        assertEquals(1, gs.getCurrentPlayer().getCurrentRoom().getTokens().size());
    }
}
