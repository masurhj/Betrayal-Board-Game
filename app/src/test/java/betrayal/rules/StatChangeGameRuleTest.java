package betrayal.rules;

import betrayal.BetrayalGameState;
import betrayal.GameState;
import betrayal.player.StatisticType;
import betrayal.player.Player;
import betrayal.player.PlayerStatistic;
import betrayal.rules.GameRule;
import betrayal.rules.StatChangeGameRule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StatChangeGameRuleTest {

    @Test
    public void testSpeedStatToMax() {
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
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(7, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(8 , gs.getCurrentPlayer().getSpeedValue());

    }

    @Test
    public void testSpeedStatAboveMax() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(6, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SPEED, 2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(7, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(8 , gs.getCurrentPlayer().getSpeedValue());
    }

    @Test
    public void testSpeedStatToMin() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(2, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SPEED, -2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(1 , gs.getCurrentPlayer().getSpeedValue());
    }

    @Test
    public void testSpeedStatBelowMin() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(1, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SPEED, -2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getSpeedIndex());
        assertEquals(1, gs.getCurrentPlayer().getSpeedValue());
    }

    @Test
    public void testMightStatToMax() {
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
        GameRule gr = new StatChangeGameRule(StatisticType.MIGHT, 2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(7, gs.getCurrentPlayer().getMightIndex());
        assertEquals(8 , gs.getCurrentPlayer().getMightValue());

    }

    @Test
    public void testMightStatAboveMax() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setMightStatistic(new PlayerStatistic(6, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.MIGHT, 2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(7, gs.getCurrentPlayer().getMightIndex());
        assertEquals(8 , gs.getCurrentPlayer().getMightValue());
    }

    @Test
    public void testMightStatToMin() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setMightStatistic(new PlayerStatistic(2, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.MIGHT, -2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getMightIndex());
        assertEquals(1 , gs.getCurrentPlayer().getMightValue());
    }

    @Test
    public void testMightStatBelowMin() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setMightStatistic(new PlayerStatistic(1, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.MIGHT, -2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getMightIndex());
        assertEquals(1, gs.getCurrentPlayer().getMightValue());
    }

    @Test
    public void testSanityStatToMax() {
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
        GameRule gr = new StatChangeGameRule(StatisticType.SANITY, 2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(7, gs.getCurrentPlayer().getSanityIndex());
        assertEquals(8 , gs.getCurrentPlayer().getSanityValue());

    }

    @Test
    public void testSanityStatAboveMax() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSanityStatistic(new PlayerStatistic(6, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SANITY, 2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(7, gs.getCurrentPlayer().getSanityIndex());
        assertEquals(8 , gs.getCurrentPlayer().getSanityValue());
    }

    @Test
    public void testSanityStatToMin() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSanityStatistic(new PlayerStatistic(2, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SANITY, -2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getSanityIndex());
        assertEquals(1 , gs.getCurrentPlayer().getSanityValue());
    }

    @Test
    public void testSanityStatBelowMin() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSanityStatistic(new PlayerStatistic(1, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.SANITY, -2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getSanityIndex());
        assertEquals(1, gs.getCurrentPlayer().getSanityValue());
    }

    @Test
    public void testKnowledgeStatToMax() {
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
        GameRule gr = new StatChangeGameRule(StatisticType.KNOWLEDGE, 2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(7, gs.getCurrentPlayer().getKnowledgeIndex());
        assertEquals(8 , gs.getCurrentPlayer().getKnowledgeValue());

    }

    @Test
    public void testKnowledgeStatAboveMax() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setKnowledgeStatistic(new PlayerStatistic(6, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.KNOWLEDGE, 2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(7, gs.getCurrentPlayer().getKnowledgeIndex());
        assertEquals(8 , gs.getCurrentPlayer().getKnowledgeValue());
    }

    @Test
    public void testKnowledgeStatToMin() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setKnowledgeStatistic(new PlayerStatistic(2, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.KNOWLEDGE, -2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getKnowledgeIndex());
        assertEquals(1 , gs.getCurrentPlayer().getKnowledgeValue());
    }

    @Test
    public void testKnowledgeStatBelowMin() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setKnowledgeStatistic(new PlayerStatistic(1, valArray));

        ArrayList<Player> parray = new ArrayList<Player>();
        parray.add(p);

        gs.setPlayers(parray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new StatChangeGameRule(StatisticType.KNOWLEDGE, -2);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);

        //assert
        assertEquals(0, gs.getCurrentPlayer().getKnowledgeIndex());
        assertEquals(1, gs.getCurrentPlayer().getKnowledgeValue());
    }
}

