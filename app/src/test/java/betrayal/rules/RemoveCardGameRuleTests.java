package betrayal.rules;

import betrayal.BetrayalGameState;
import betrayal.GameState;
import betrayal.card.Item;
import betrayal.player.Player;
import betrayal.player.PlayerStatistic;
import betrayal.rules.GameRule;
import betrayal.rules.RemoveCardGameRule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveCardGameRuleTests {

    @Test
    public void testRemoveRandomItem() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));
        Item testItem1 = new Item("Test1");
        Item testItem2 = new Item("Test2");
        p.pickUp(testItem1);
        p.pickUp(testItem2);

        ArrayList<Player> playerArray = new ArrayList<Player>();
        playerArray.add(p);

        gs.setPlayers(playerArray);
        gs.setCurrentPlayer(0);
        assertEquals(2, gs.getCurrentPlayer().getInventory().size());

        //execute
        GameRule gr = new RemoveCardGameRule();
        gr.apply(gs, new ArrayList<Object>());

        //assert
        assertEquals(1, gs.getCurrentPlayer().getInventory().size());
    }

    @Test
    public void testRemoveSpecificItem() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));
        Item testItem1 = new Item("Test1");
        Item testItem2 = new Item("Test2");
        p.pickUp(testItem1);
        p.pickUp(testItem2);

        ArrayList<Player> playerArray = new ArrayList<Player>();
        playerArray.add(p);

        gs.setPlayers(playerArray);
        gs.setCurrentPlayer(0);
        assertEquals(2, gs.getCurrentPlayer().getInventory().size());

        //execute
        GameRule gr = new RemoveCardGameRule("Test1");
        gr.apply(gs, new ArrayList<Object>());

        //assert
        assertEquals(1, gs.getCurrentPlayer().getInventory().size());
        System.out.println(testItem1.getName());
        System.out.println(gs.getCurrentPlayer().getInventory().keySet().toArray()[0]);
        assertEquals(testItem2.getName(), gs.getCurrentPlayer().getInventory().keySet().toArray()[0]);
    }
    @Test
    public void testRemoveRandomItemWithNoItem() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));
        ArrayList<Player> playerArray = new ArrayList<Player>();
        playerArray.add(p);

        gs.setPlayers(playerArray);
        gs.setCurrentPlayer(0);

        //execute
        GameRule gr = new RemoveCardGameRule();
        gr.apply(gs, new ArrayList<Object>());

        //assert
        assertEquals(0, gs.getCurrentPlayer().getInventory().size());
    }
    @Test
    public void testRemoveSpecificItemWithNoItem() {
        //set up
        GameState gs = new BetrayalGameState();

        Player p = new Player("Test");
        int[] valArray = {1,2,3,4,5,6,7,8};
        p.setSpeedStatistic(new PlayerStatistic(5, valArray));
        Item testItem1 = new Item("Test1");
        Item testItem2 = new Item("Test2");
        p.pickUp(testItem1);
        p.pickUp(testItem2);

        ArrayList<Player> playerArray = new ArrayList<Player>();
        playerArray.add(p);

        gs.setPlayers(playerArray);
        gs.setCurrentPlayer(0);
        assertEquals(2, gs.getCurrentPlayer().getInventory().size());

        //execute
        GameRule gr = new RemoveCardGameRule("Test3");
        gr.apply(gs, new ArrayList<Object>());

        //assert
        assertEquals(2, gs.getCurrentPlayer().getInventory().size());
    }
}
