package betrayal.gui;

import betrayal.controllers.*;
import betrayal.card.Card;
import betrayal.card.CardType;
import betrayal.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel
        implements MouseListener,
        MouseMotionListener,
        MouseWheelListener,
        ComponentObserver {

    private Point previous;
    private int translateX = 0, translateY = 0,
            initialTranslateX = 0, initialTranslateY = 0;
    private double scale = 1;
    private ArrayList<RoomComponent> roomComponents = new ArrayList<RoomComponent>();
    private ArrayList<PlayerComponent> playerComponents = new ArrayList<PlayerComponent>();
    private HauntComponent hauntComponent;
    private RoomClickedListener roomClickedListener;
    private GameState state;
    private String roomError = "";
    private HauntController hauntController;

    /**
     * GamePanel constructor.
     * @param state
     * @param roomComponents
     */
    @SuppressFBWarnings
    public GamePanel(GameState state, final ArrayList<RoomComponent> roomComponents) {
        this.state = state;
        this.state.addObserver(this);
        this.setLayout(null);
        previous = new Point(500, 390);
        this.roomComponents.addAll(roomComponents);
        CardController cardController = new CardController(new ItemController(state),
                new OmenController(state), new EventController(state));
        MoveController moveController = new MoveController(state);
        roomClickedListener = new RoomClickedListener(this, state, this.roomComponents,
                moveController, new RoomController(state), cardController);
        this.addMouseListener(roomClickedListener);
        hauntComponent = new HauntComponent(this.state);
        hauntController = new HauntController(this.state);
        new GameEndListener(this.state.getPlayers(), hauntController);
        repaint();
    }

    /**
     * Add a playerComponent to the list.
     * @param playerComponent
     */
    public void addPlayerComponent(final PlayerComponent playerComponent) {
        this.playerComponents.add(playerComponent);
    }

    /**
     * Add a RoomComponent to the ArrayList.
     * @param component
     */
    public void addRoomComponent(final RoomComponent component) {
        this.roomComponents.add(component);
        this.roomClickedListener.addRoomComponent(component);
    }

    /**
     * Draw the strings at the top of the panel.
     * @param graphics
     */
    public void paintStrings(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawString(roomError, 400, 40);
    }

    /**
     * Draws the game.
     * @param graphics
     */
    public void paintComponent(final Graphics graphics) {
        // Fill background
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 1300, 880);

        paintStrings(graphics);

        hauntComponent.paintComponent(graphics);

        graphics.translate(translateX, translateY);
        this.roomClickedListener.setTranslation(translateX, translateY);

        for (int i = 0; i < playerComponents.size(); i++) {
            this.playerComponents.get(i).paintComponent(graphics, scale);
        }

        for (int i = 0; i < roomComponents.size(); i++) {
            this.roomComponents.get(i).paintComponent(graphics, scale);
        }
    }

    /**
     * Mouse Clicked.
     * @param e
     */
    @Override
    public void mouseClicked(final MouseEvent e) {

    }

    /**
     * Mouse pressed. Used for panning.
     * @param e
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        previous = e.getPoint();
        initialTranslateX = translateX;
        initialTranslateY = translateY;
    }

    /**
     * Mouse released. Used for panning.
     * @param e
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
        previous = e.getPoint();
        initialTranslateX = translateX;
        initialTranslateY = translateY;
        repaint();
    }

    /**
     * Mouse entered.
     * @param e
     */
    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    /**
     * Mouse exited.
     * @param e
     */
    @Override
    public void mouseExited(final MouseEvent e) {
    }

    /**
     * Mouse dragged. Used for panning.
     * @param e
     */
    @Override
    public void mouseDragged(final MouseEvent e) {
        translateX = initialTranslateX + (int) (e.getX() - previous.getX());
        translateY = initialTranslateY + (int) (e.getY() - previous.getY());
        repaint();
    }

    /**
     * Mouse moved.
     * @param e
     */
    @Override
    public void mouseMoved(final MouseEvent e) {

    }

    /**
     * Mouse Wheel Moved. Used for zooming.
     * @param e
     */
    @Override
    public void mouseWheelMoved(final MouseWheelEvent e) {
        if (0.1 <= scale && scale <= 2) {
            if (e.getPreciseWheelRotation() < 0) {
                scale -= 0.1;
                if (scale < 0.1) {
                    scale = 0.1;
                }
            } else {
                scale += 0.1;
                if (scale > 2) {
                    scale = 2;
                }
            }
            repaint();
        }
    }

    /**
     * Create the JOptionPane for showing the drawn card.
     * @param card
     */
    public void createCardOptionPane(Card card) {
        repaint();
        String cardHeader = java.text.MessageFormat.format("{0}: {1}", card.getType(),
                                state.getMessageString(card.getName()));
        StringBuilder cardDescBuilder = new StringBuilder();
        cardDescBuilder.append(state.getMessageString(card.getHeader()));
        if (!card.getDescription().isEmpty()) {
            cardDescBuilder.append(java.text.MessageFormat.format("\n{0}",
                    state.getMessageString(card.getDescription())));
            if (card.getRollValues().size() > 0) {
                cardDescBuilder.append(java.text.MessageFormat.format("\n{0}:", state.getMessageString("rolls")));
                for (int i = 0; i < card.getRollValues().size(); i++) {
                    cardDescBuilder.append(java.text.MessageFormat.format("\n{0} {1}: {2}",
                            state.getMessageString("rollValue"), card.getRollValues().get(i),
                            state.getMessageString(card.getRollDescriptions().get(i))));
                }
            }
        }
        String cardDesc = cardDescBuilder.toString();
        JOptionPane cardInfoPane = new JOptionPane();
        cardInfoPane.showMessageDialog(null, cardDesc, cardHeader, JOptionPane.PLAIN_MESSAGE);
        if (card.getType() == CardType.OMEN) {
            JOptionPane hauntInfoPane = new JOptionPane();
            String hauntDesc = java.text.MessageFormat.format(state.getMessageString("hauntDesc"),
                    hauntController.getHauntCounter(), hauntController.performHauntRoll(),
                    hauntController.hasHauntStarted());
            String hauntTitle = state.getMessageString("hauntResults");
            hauntInfoPane.showMessageDialog(null, hauntDesc, hauntTitle, JOptionPane.PLAIN_MESSAGE);
        }
        repaint();
    }

    /**
     * Set the proper error string for drawing.
     * @param errorMessage
     */
    public void setError(String errorMessage) {
        roomError = errorMessage;
        repaint();
    }

    /**
     * Used for changing languages.
     */
    @Override
    public void update() {
        repaint();
    }
}
