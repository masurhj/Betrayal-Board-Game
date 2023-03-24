package betrayal.rules;

import betrayal.GameState;
import betrayal.player.Player;

import java.util.ArrayList;

public class AllPlayerGameRule implements GameRule {
    private GameRule rule;

    /**
     * @param rule
     */
    public AllPlayerGameRule(GameRule rule) {
        this.rule = rule;
    }

    /**
     * Applies the apply effect to the game state.
     *
     * @param state
     * @param parameters
     * @return whether or not the gamerule applied.
     */
    @Override
    public boolean apply(GameState state, ArrayList<Object> parameters) {
        for (Player player : state.getPlayers()) {
            rule.applyToPlayer(player, state, parameters);
        }
        return true;
    }

    /**
     * Applies the game rule to a specified player.
     *
     * @param player
     * @param state
     * @param parameters
     * @return whether or not the gamerule applied
     */
    @Override
    public boolean applyToPlayer(Player player, GameState state, ArrayList<Object> parameters) {
        return false;
    }
}
