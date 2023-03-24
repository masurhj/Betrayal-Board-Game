package betrayal.controllers;

import betrayal.gui.ComponentObserver;

public final class Haunt {
    private int hauntCounter = 0;
    private boolean hasHauntStarted = false;
    private static final int MAX_HAUNT_COUNT = 12;
    private static final int NUM_HAUNT_ROLL_DICE = 8;
    private final DiceRoller diceRoller;
    private ComponentObserver observer;

    /**
     * Constructs the Haunt Object and stores a die roller instance for performing a haunt roll.
     * @param roller
     */
    public Haunt(final DiceRoller roller) {
        this.diceRoller = roller;
    }

    /**
     * Set the Haunt Observer.
     * @param observer
     */
    public void setObserver(ComponentObserver observer) {
        this.observer = observer;
    }

    /**
     * Return the haunt counter.
     * @return hauntCounter.
     */
    public int getHauntCounter() {
        return this.hauntCounter;
    }

    /**
     * Increase the haunt counter to a max of 12.
     */
    public void increaseHauntCounter() {
        if (this.hauntCounter + 1 > MAX_HAUNT_COUNT) {
            throw new IllegalStateException("Cannot increase haunt counter");
        }
        this.hauntCounter++;
        if (this.observer != null) {
            this.observer.update();
        }
    }

    /**
     * Performs the haunt roll.
     * Sets the haunt status to true if player rolls below the haunt counter.
     * Increases the haunt counter if the player rolls higher than the haunt counter.
     * @return the result of the roll.
     */
    public int performHauntRoll() {
        int playerRoll = diceRoller.roll(NUM_HAUNT_ROLL_DICE);
        if (hauntCounter > playerRoll) {
            hasHauntStarted = true;
        } else {
            this.increaseHauntCounter();
        }
        if (this.observer != null) {
            this.observer.update();
        }
        return playerRoll;
    }

    /**
     * Returns whether the haunt has started.
     * @return The haunt status
     */
    public boolean getHasHauntStarted() {
        return this.hasHauntStarted;
    }
}
