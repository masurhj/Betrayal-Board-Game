package betrayal.gui;

import betrayal.controllers.SuppressFBWarnings;
import betrayal.GameState;

import java.awt.*;

public class HauntComponent implements ComponentObserver {

    private GameState state;
    private int hauntCount = 0;

    /**
     * HauntComponent constructor.
     * @param state
     */
    @SuppressFBWarnings
    public HauntComponent(GameState state) {
        this.state = state;
        this.state.getHaunt().setObserver(this);
    }

    /**
     * Draw the current state of the haunt.
     * @param graphics
     */
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawString("Haunt", 10, 20);
        graphics.drawString("0 1 2 3 4 5 6 7 8 9 10 11 12", 10, 40);
        int offset = 11;
        if (hauntCount == 11) {
            offset = 18;
        } else if (hauntCount == 12) {
            offset = 24;
        }
        graphics.drawString("^", offset + 10 * hauntCount, 52);
    }

    /**
     * Update the haunt count.
     */
    @Override
    public void update() {
        hauntCount = state.getHaunt().getHauntCounter();
    }
}
