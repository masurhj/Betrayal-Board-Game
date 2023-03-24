package betrayal;

import betrayal.BetrayalGameState;
import betrayal.player.Player;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BetrayalGameStateTests {


    @Test
    public void getPlayerByName(){
        BetrayalGameState bgs = new BetrayalGameState();
        Player playerMock = EasyMock.mock(Player.class);
        ArrayList<Player> players = new ArrayList<>();
        players.add(playerMock);
        bgs.setPlayers(players);

        EasyMock.expect(playerMock.getName()).andReturn("player 1");

        EasyMock.replay(playerMock);

        Player actual = bgs.getPlayerByName("player 1");

        EasyMock.verify(playerMock);

        Assertions.assertEquals(actual, playerMock);
    }

}
