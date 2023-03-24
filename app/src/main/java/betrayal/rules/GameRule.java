package betrayal.rules;

import betrayal.GameState;
import betrayal.player.Player;

import java.util.ArrayList;

public interface GameRule {

    /** Applies the apply effect to the game state.
     * @param state
     * @param parameters
     * @return whether or not the gamerule applied.
     */
    default boolean apply(GameState state, ArrayList<Object> parameters) {
        Player currentPlayer = state.getCurrentPlayer();
        return this.applyToPlayer(currentPlayer, state, parameters);
    }


    /** Applies the game rule to a specified player.
     * @param player
     * @param state
     * @param parameters
     * @return whether or not the gamerule applied
     */
    boolean applyToPlayer(Player player, GameState state, ArrayList<Object> parameters);
}
