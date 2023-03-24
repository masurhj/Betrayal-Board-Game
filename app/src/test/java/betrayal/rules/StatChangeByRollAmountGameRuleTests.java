package betrayal.rules;

import betrayal.BetrayalGameState;
import betrayal.GameState;
import betrayal.player.StatisticType;
import betrayal.player.Player;
import betrayal.player.PlayerStatistic;
import betrayal.rules.GameRule;
import betrayal.rules.StatChangeByRollAmountGameRule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatChangeByRollAmountGameRuleTests {
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
        int oldVal = p.getSpeedIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.SPEED, 2, 1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getSpeedIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, 1));

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
        int oldVal = p.getSpeedIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.SPEED, 2, 1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getSpeedIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, 1));
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
        int oldVal = p.getSpeedIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.SPEED, 2, -1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getSpeedIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, -1));
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
        int oldVal = p.getSpeedIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.SPEED, 2, -1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getSpeedIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, -1));
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
        int oldVal = p.getMightIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.MIGHT, 2, 1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getMightIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, 1));

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
        int oldVal = p.getMightIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.MIGHT, 2, 1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getMightIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, 1));
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
        int oldVal = p.getMightIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.MIGHT, 2, -1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getMightIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, -1));
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
        int oldVal = p.getMightIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.MIGHT, 2, -1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getMightIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, -1));
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
        int oldVal = p.getSanityIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.SANITY, 2, 1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getSanityIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, 1));

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
        int oldVal = p.getSanityIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.SANITY, 2, 1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getSanityIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, 1));
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
        int oldVal = p.getSanityIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.SANITY, 2, -1);
        ArrayList<Object> params = new ArrayList<>();
        gr.apply(gs, params);
        int newVal = p.getSanityIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, -1));
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
        int oldVal = p.getSanityIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.SANITY, 2, -1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getSanityIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, -1));
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
        int oldVal = p.getKnowledgeIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.KNOWLEDGE, 2, 1);
        ArrayList<Object> params = new ArrayList<>();
        int newVal = p.getKnowledgeIndex();

        gr.apply(gs, params);

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, 1));

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
        int oldVal = p.getKnowledgeIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.KNOWLEDGE, 2, 1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getKnowledgeIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, 1));
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
        int oldVal = p.getKnowledgeIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.KNOWLEDGE, 2, -1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getKnowledgeIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, -1));
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
        int oldVal = p.getKnowledgeIndex();
        GameRule gr = new StatChangeByRollAmountGameRule(StatisticType.KNOWLEDGE, 2, -1);
        ArrayList<Object> params = new ArrayList<>();

        gr.apply(gs, params);
        int newVal = p.getKnowledgeIndex();

        //assert
        assertEquals(true, changedByDieAmount(oldVal, newVal, -1));
    }

    private boolean changedByDieAmount(int oldVal, int changedVal, int sign){
        if(sign == 1 && changedVal <= changedVal + 2){
            return true;
        }
        if(sign == -1 && changedVal >= changedVal - 2){
            return true;
        }
        return false;
    }
}
