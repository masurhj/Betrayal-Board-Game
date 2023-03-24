package betrayal.rules;

import betrayal.controllers.DiceRoller;
import betrayal.GameState;
import betrayal.player.Player;
import betrayal.player.StatisticType;

import java.util.ArrayList;
import java.util.Random;

public class StatChangeByRollAmountGameRule implements GameRule {
    private StatisticType stat;
    private long numDice;
    private long sign;

    /**
     * Construct the game rule.
     * @param stat
     * @param numDice
     * @param sign
     */
    public StatChangeByRollAmountGameRule(final StatisticType stat, final long numDice, final long sign) {
        this.stat = stat;
        this.numDice = numDice;
        this.sign = sign;
    }

    /**
     * @param state
     * @param parameters
     * @return Whether or not the game rule was applied.
     */
    @Override
    public boolean apply(GameState state, ArrayList<Object> parameters) {
        Player p = state.getCurrentPlayer();
        return this.applyToPlayer(p, state, parameters);
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
        long value = new DiceRoller(new Random()).roll((int) numDice);
        value = value * sign;
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
