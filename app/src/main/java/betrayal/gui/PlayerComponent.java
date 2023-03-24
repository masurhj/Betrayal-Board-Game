package betrayal.gui;

import betrayal.controllers.SuppressFBWarnings;
import betrayal.GameState;
import betrayal.player.Player;
import betrayal.board.RoomFloor;

import java.awt.*;

public class PlayerComponent implements ComponentObserver {

    private Player player;

    private RoomFloor currentFloor;

    private int moveCounter = 0;

    private Color color;

    private int x, y, colorOffsetX, colorOffsetY, locationX, locationY;

    private GameState state;

    /**
     * PlayerComponent constructor.
     * @param state
     * @param player
     */
    @SuppressFBWarnings
    public PlayerComponent(final GameState state, final Player player) {
        this.state = state;
        this.player = player;
        doColorLogic();
        update();
    }

    /**
     * Based on the color of the player, set the circle color
     * and the offset for where they will be drawn in the room.
     */
    public void doColorLogic() {
        colorOffsetX = 0;
        colorOffsetY = 0;
        switch (player.getColor()) {
            case YELLOW:
                color = Color.YELLOW;
                break;
            case PURPLE:
                color = new Color(102, 51, 153);
                colorOffsetX = 1;
                break;
            case GREEN:
                color = Color.GREEN;
                colorOffsetX = 2;
                break;
            case RED:
                color = Color.RED;
                colorOffsetY = 1;
                break;
            case BLUE:
                color = Color.BLUE;
                colorOffsetX = 1;
                colorOffsetY = 1;
                break;
            case WHITE:
                this.color = Color.LIGHT_GRAY;
                colorOffsetX = 2;
                colorOffsetY = 1;
                break;
            default:
                this.color = Color.BLACK;
        }
    }

    /**
     * Update the player's location.
     */
    @Override
    public void update() {
        locationX = player.getX();
        locationY = player.getY();
        currentFloor = player.getCurrentFloor();
        moveCounter = player.getMoveCounter();
    }

    /**
     * Translate the drawing based on where the player is.
     */
    public void doLocationTranslate() {
        x = 405 + (100 * locationX) + (33 * colorOffsetX);
        y = 395 + (100 * -locationY) + (60 * colorOffsetY);

        switch (currentFloor) {
            case UPPER:
                x -= 100 + 100 * (state.getFloor(currentFloor).getMaxX() - state.getFloor(RoomFloor.GROUND).getMinX());
                return;
            case GROUND:
                x += 100;
                return;
            case BASEMENT:
                x += 300 - 100 * (state.getFloor(currentFloor).getMinX()) + (100 * state.getFloor(RoomFloor.GROUND).getMaxX());
                return;
            default:
                return;
        }
    }

    /**
     * Draw the circle.
     * @param graphics
     * @param scale
     */
    public void paintComponent(final Graphics graphics, final double scale) {
        graphics.setColor(color);
        doLocationTranslate();
        int diameter = (int) (25 * scale);
        graphics.fillOval((int) (x * scale), (int) (y * scale), diameter, diameter);
        if (color.equals(Color.YELLOW)) {
            graphics.setColor(Color.BLACK);
        } else {
            graphics.setColor(Color.WHITE);
        }
        graphics.drawString(moveCounter + "", (int) ((x + 9) * scale), (int) ((y + 17) * scale));
    }
}
