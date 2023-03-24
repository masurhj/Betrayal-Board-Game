package betrayal.rules;

import betrayal.GameState;
import betrayal.player.Player;
import betrayal.player.StatisticType;

import java.util.ArrayList;

public class ResetStatGameRule implements GameRule {
    private StatisticType statType;

    /**
     * @param type
     */
    public ResetStatGameRule(StatisticType type) {
        this.statType = type;
    }

    /**
     * Applies the game rule to a specified player.
     *
     * @param player
     * @param state
     * @param parameters
     */
    @Override
    public boolean applyToPlayer(Player player, GameState state, ArrayList<Object> parameters) {
        player.resetStatistic(statType);
        return true;
    }
}
