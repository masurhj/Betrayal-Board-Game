package betrayal.rules;

import betrayal.BetrayalGameState;
import betrayal.GameState;
import betrayal.player.StatisticType;
import betrayal.player.Player;
import betrayal.player.PlayerStatistic;
import betrayal.rules.GameRule;
import betrayal.rules.RollGameRule;
import betrayal.rules.StatChangeGameRule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RollGameRuleTests {
    @Test
    public void testRollGameRuleInRangeZeroTo3() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SPEED, 2);
        GameRule rollGameRule = new RollGameRule(gr, 0, 3);
        ArrayList<Object> params = new ArrayList<>();
        params.add(2);
        rollGameRule.apply(gs, params);

        assertEquals(7, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(8 , gs.getCurrentPlayer().getSpeedValue());
    }

    @Test
    public void testRollGameRuleInRangeZeroTo3AtZero() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SPEED, 2);
        GameRule rollGameRule = new RollGameRule(gr, 0, 3);
        ArrayList<Object> params = new ArrayList<>();
        params.add(0);
        rollGameRule.apply(gs, params);

        assertEquals(7, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(8 , gs.getCurrentPlayer().getSpeedValue());
    }

    @Test
    public void testRollGameRuleInRangeZeroTo3At3() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SPEED, 2);
        GameRule rollGameRule = new RollGameRule(gr, 0, 3);
        ArrayList<Object> params = new ArrayList<>();
        params.add(3);
        rollGameRule.apply(gs, params);

        assertEquals(7, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(8 , gs.getCurrentPlayer().getSpeedValue());
    }

    @Test
    public void testRollGameRuleOutOfRangeZeroTo3() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SPEED, 2);
        GameRule rollGameRule = new RollGameRule(gr, 0, 3);
        ArrayList<Object> params = new ArrayList<>();
        params.add(4);
        rollGameRule.apply(gs, params);

        assertEquals(5, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(6 , gs.getCurrentPlayer().getSpeedValue());
    }

    @Test
    public void testRollGameRuleInRangeGreaterThanZeroAtZero() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SPEED, 2);
        GameRule rollGameRule = new RollGameRule(gr, 0);
        ArrayList<Object> params = new ArrayList<>();
        params.add(0);
        rollGameRule.apply(gs, params);

        assertEquals(7, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(8, gs.getCurrentPlayer().getSpeedValue());
    }

    @Test
    public void testRollGameRuleInRangeGreaterThanZero() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SPEED, 2);
        GameRule rollGameRule = new RollGameRule(gr, 0);
        ArrayList<Object> params = new ArrayList<>();
        params.add(3);
        rollGameRule.apply(gs, params);

        assertEquals(7, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(8, gs.getCurrentPlayer().getSpeedValue());
    }

    @Test
    public void testRollGameRuleOutOfRangeGreaterThan3() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SPEED, 2);
        GameRule rollGameRule = new RollGameRule(gr, 3);
        ArrayList<Object> params = new ArrayList<>();
        params.add(2);
        rollGameRule.apply(gs, params);

        assertEquals(5, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(6, gs.getCurrentPlayer().getSpeedValue());
    }
}