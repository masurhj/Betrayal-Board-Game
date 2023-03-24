package betrayal.rules;

import betrayal.GameState;
import betrayal.player.Player;
import betrayal.player.StatisticType;

import java.util.ArrayList;

public class StatChangeGameRule implements GameRule {
    private StatisticType stat;
    private long value;

    /**
     * Construct the game rule.
     * @param stat
     * @param value
     */
    public StatChangeGameRule(final StatisticType stat, final long value) {
        this.stat = stat;
        this.value = value;
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
        switch (stat) {
            case SPEED: player.changeCurrentSpeedIndexBy(value);
                return true;
            case MIGHT: player.changeCurrentMightIndexBy(value);
                return true;
            case SANITY: player.changeCurrentSanityIndexBy(value);
                return true;
            case KNOWLEDGE: player.changeCurrentKnowledgeIndexBy(value);
                return true;
            default: return false;
        }
    }
}
