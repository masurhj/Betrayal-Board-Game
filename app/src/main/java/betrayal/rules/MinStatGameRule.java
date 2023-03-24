package betrayal.rules;

import betrayal.GameState;
import betrayal.player.Player;
import betrayal.player.StatisticType;

import java.util.ArrayList;

public class MinStatGameRule implements GameRule {
    private StatisticType statType;

    /**
     * @param type
     */
    public MinStatGameRule(StatisticType type) {
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
        switch (statType) {
            case SPEED: player.setCurrentSpeedIndexBy(0);
                return true;
            case MIGHT: player.setCurrentMightIndexBy(0);
                return true;
            case SANITY: player.setCurrentSanityIndexBy(0);
                return true;
            case KNOWLEDGE: player.setCurrentKnowledgeIndexBy(0);
                return true;
            default: return false;
        }
    }
}
