package betrayal.controllers;

import betrayal.GameState;
import betrayal.gui.ComponentObserver;

public class HauntController {
    private GameState state;

    private ComponentObserver observer;

    /**
     * @param state
     */
    @SuppressFBWarnings
    public HauntController(GameState state) {
        this.state = state;
    }

    /**
     * Add the EndGame observer.
     * @param observer
     */
    public void addObserver(ComponentObserver observer) {
        this.observer = observer;
    }

    /**
     * Update the observer.
     */
    public void updateObserver() {
        if (this.observer != null) {
            this.observer.update();
        }
    }

    /**
     * @return the end game status after performing a haunt roll
     */
    public int performHauntRoll() {
        int result = state.getHaunt().performHauntRoll();
        updateObserver();
        return result;
    }

    public boolean hasHauntStarted() {
        return state.getHaunt().getHasHauntStarted();
    }

    /**
     * @return the haunt counter for the game
     */
    public int getHauntCounter() {
        return state.getHaunt().getHauntCounter();
    }
}
