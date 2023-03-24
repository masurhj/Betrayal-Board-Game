package betrayal.controllers;

import java.util.Random;

public class DiceRoller {
    private static int maxNumDice = 8;
    private static int topBoundDiceRoll = 3;
    private Random random;

    /**
     * DiceRoller constructor.
     * @param random
     */
    @SuppressFBWarnings
    public DiceRoller(final Random random) {
        this.random = random;
    }

    /**
     * Rolls the requested number of dice.
     *
     * @param numDice
     * @return sum of dice rolls.
     * */
    public int roll(final int numDice) {
        if (numDice < 0 || numDice > maxNumDice) {
            throw new IllegalArgumentException("Invalid number of dice.");
        }
        int result = 0;
        for (int i = 0; i < numDice; i++) {
            result += random.nextInt(topBoundDiceRoll);
        }
        return result;
    }
}
