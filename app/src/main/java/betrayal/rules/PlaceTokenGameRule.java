package betrayal.rules;

import betrayal.GameState;
import betrayal.player.Player;

import java.util.ArrayList;

public class PlaceTokenGameRule implements GameRule {
    private String tokenName;

    /**
     * Stores the string to search the token.
     * @param tokenToAdd
     */
    public PlaceTokenGameRule(String tokenToAdd) {
        this.tokenName = tokenToAdd;
    }

    @Override
    public boolean apply(GameState state, ArrayList<Object> parameters) {
        state.addTokenToCurrentPlayerRoom(tokenName);
        return true;
    }

    @Override
    public boolean applyToPlayer(Player player, GameState state, ArrayList<Object> parameters) {
        return false;
    }
}
