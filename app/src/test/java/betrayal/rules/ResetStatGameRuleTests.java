package betrayal.rules;

import betrayal.BetrayalGameState;
import betrayal.GameState;
import betrayal.player.StatisticType;
import betrayal.player.Player;
import betrayal.player.PlayerStatistic;
import betrayal.rules.GameRule;
import betrayal.rules.ResetStatGameRule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResetStatGameRuleTests {
    @Test
    public void testResetKnowledgeStat() {
        GameState state = new BetrayalGameState();
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player("Test");
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        player.setKnowledgeStatistic(new PlayerStatistic(4, values));

        player.changeCurrentKnowledgeIndexBy(-2);
        players.add(player);
        state.setPlayers(players);

        GameRule rule = new ResetStatGameRule(StatisticType.KNOWLEDGE);
        ArrayList<Object> params = new ArrayList<>();

        rule.apply(state, params);
        assertEquals(4, player.getKnowledgeIndex());
        assertEquals(5, player.getKnowledgeValue());
    }

    @Test
    public void testResetSanityStat() {
        GameState state = new BetrayalGameState();
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player("Test");
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        player.setSanityStatistic(new PlayerStatistic(4, values));

        player.changeCurrentSanityIndexBy(-2);
        players.add(player);
        state.setPlayers(players);

        GameRule rule = new ResetStatGameRule(StatisticType.SANITY);
        ArrayList<Object> params = new ArrayList<>();

        rule.apply(state, params);
        assertEquals(4, player.getSanityIndex());
        assertEquals(5, player.getSanityValue());
    }

    @Test
    public void testResetSpeedStat() {
        GameState state = new BetrayalGameState();
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player("Test");
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        player.setSpeedStatistic(new PlayerStatistic(4, values));

        player.changeCurrentSpeedIndexBy(-2);
        players.add(player);
        state.setPlayers(players);

        GameRule rule = new ResetStatGameRule(StatisticType.SPEED);
        ArrayList<Object> params = new ArrayList<>();

        rule.apply(state, params);
        assertEquals(4, player.getSpeedIndex());
        assertEquals(5, player.getSpeedValue());
    }

    @Test
    public void testResetMightStat() {
        GameState state = new BetrayalGameState();
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player("Test");
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        player.setMightStatistic(new PlayerStatistic(4, values));

        player.changeCurrentMightIndexBy(-2);
        players.add(player);
        state.setPlayers(players);

        GameRule rule = new ResetStatGameRule(StatisticType.MIGHT);
        ArrayList<Object> params = new ArrayList<>();

        rule.apply(state, params);
        assertEquals(4, player.getMightIndex());
        assertEquals(5, player.getMightValue());
    }
}