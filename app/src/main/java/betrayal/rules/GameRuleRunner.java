package betrayal.rules;

import betrayal.GameState;
import betrayal.player.RollType;
import betrayal.player.RollTypeManager;

import java.util.ArrayList;

public class GameRuleRunner {

    /**
     * Loops through a list of game rules and applies them to the gamestate.
     * @param state
     * @param gameRules
     * @param rollType
     */
    public void goThroughGameRules(final GameState state, final ArrayList<GameRule> gameRules, final RollType rollType) {
        int rollVal = doRoll(state, rollType);
        ArrayList<Object> params = new ArrayList<>();
        params.add(rollVal);
        for (GameRule gr: gameRules) {
            gr.apply(state, params);
        }
    }


    /**
     * Rolls based on the specification of the card.
     * @param state
     * @param rollType
     * @return returns the result of the roll performed
     */
    public int doRoll(final GameState state, final RollType rollType) {
        RollTypeManager rtm = new RollTypeManager();
        int numDie = rtm.getRollFromType(state, rollType);
        return state.getDiceRoller().roll(numDie);
    }
}
