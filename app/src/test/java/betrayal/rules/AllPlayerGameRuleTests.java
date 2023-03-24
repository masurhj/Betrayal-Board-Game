package betrayal.rules;

import betrayal.BetrayalGameState;
import betrayal.GameState;
import betrayal.player.StatisticType;
import betrayal.player.Player;
import betrayal.player.PlayerStatistic;
import betrayal.rules.AllPlayerGameRule;
import betrayal.rules.GameRule;
import betrayal.rules.StatChangeGameRule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllPlayerGameRuleTests {
    @Test
    public void testSpeedStatToMax() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));

        Player p2 = new Player("Test");
        int[] valArray2 = {1,2,3,4,5,6,7,8};
        p2.setSpeedStatistic(new PlayerStatistic(5, valArray2));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);
        parray.add(p2);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SPEED, 2);
        GameRule allPlayersGameRule = new AllPlayerGameRule(gr);
        ArrayList<Object> params = new ArrayList<>();

        allPlayersGameRule.apply(gs, params);

        //assert
        assertEquals(7, p.getSpeedIndex());
        assertEquals(8 , p.getSpeedValue());
        assertEquals(7, p2.getSpeedIndex());
        assertEquals(8 , p2.getSpeedValue());
    }
}
