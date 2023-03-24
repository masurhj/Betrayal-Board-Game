package betrayal.rules;

import betrayal.BetrayalGameState;
import betrayal.GameState;
import betrayal.player.StatisticType;
import betrayal.player.Player;
import betrayal.player.PlayerStatistic;
import betrayal.rules.GameRule;
import betrayal.rules.MinStatGameRule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinStatGameRuleTests {
    @Test
    public void testMinimizeSpeedStatistic() {
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
        GameRule gr = new MinStatGameRule(StatisticType.SPEED);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(1, gs.getCurrentPlayer().getSpeedValue());
    }

    @Test
    public void testMinimizeMightStatistic() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setMightStatistic(new PlayerStatistic(5, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new MinStatGameRule(StatisticType.MIGHT);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getMightIndex());
        assertEquals(1, gs.getCurrentPlayer().getMightValue());
    }

    @Test
    public void testMinimizeSanityStatistic() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSanityStatistic(new PlayerStatistic(5, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new MinStatGameRule(StatisticType.SANITY);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getSanityIndex());
        assertEquals(1, gs.getCurrentPlayer().getSanityValue());
    }

    @Test
    public void testMinimizeKnowledgeStatistic() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setKnowledgeStatistic(new PlayerStatistic(5, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new MinStatGameRule(StatisticType.KNOWLEDGE);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getKnowledgeIndex());
        assertEquals(1, gs.getCurrentPlayer().getKnowledgeValue());
    }

    @Test
    public void testMinimizeIllegalStatistic() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setKnowledgeStatistic(new PlayerStatistic(5, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new MinStatGameRule(StatisticType.INVALID);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(5, gs.getCurrentPlayer().getKnowledgeIndex());
        assertEquals(6, gs.getCurrentPlayer().getKnowledgeValue());
    }
}
