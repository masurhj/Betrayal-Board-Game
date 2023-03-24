package betrayal.rules;

import betrayal.GameState;
import betrayal.player.Player;

import java.util.ArrayList;

public class RollGameRule implements GameRule {

    private long min;
    private long max = 16;
    private GameRule rule;

    /** Creates a RollGameRule with the provided min and max for the range.
     * @param rule
     * @param min
     * @param max
     */
    public RollGameRule(final GameRule rule, final long min, final long max) {
        this.min = min;
        this.max = max;
        this.rule = rule;
    }

    /** Creates a RollGameRule with the provided min range, and the default max.
     * @param rule
     * @param min
     */
    public RollGameRule(final GameRule rule, final long min) {
        this.min = min;
        this.rule = rule;
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
        int val = (int) parameters.get(0);
        if (val >= min && val <= max) {
            rule.applyToPlayer(player, state, parameters);
            return true;
        }
        return false;
    }
}
