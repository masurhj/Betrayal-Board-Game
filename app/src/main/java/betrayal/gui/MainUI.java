package betrayal.gui;

import betrayal.controllers.SuppressFBWarnings;
import betrayal.GameState;
import betrayal.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MainUI extends JFrame {

    private StartPanel startPanel;
    private PlayerPanel playerPanel;
    private GamePanel gamePanel;
    private PlayerInfoPanel playerInfoPanel;
    private int playerCount;
    private ArrayList<RoomComponent> roomComponents = new ArrayList<RoomComponent>();
    private ArrayList<PlayerComponent> playerComponents = new ArrayList<PlayerComponent>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private GameState state;

    /**
     * MainUI constructor.
     * @param state
     * @param roomComponents
     * @param players
     * @param playerComponents
     */
    @SuppressFBWarnings
    public MainUI(GameState state, ArrayList<RoomComponent> roomComponents,
                  ArrayList<Player> players, ArrayList<PlayerComponent> playerComponents) {
        this.state = state;
        this.setName("Betrayal at House on the Hill");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.roomComponents.addAll(roomComponents);
        this.players.addAll(players);
        this.playerComponents.addAll(playerComponents);
        setupInfoPanel();
        startPanel = new StartPanel(this, this.state);
        playerPanel = new PlayerPanel(this, this.state);
        gamePanel = new GamePanel(this.state, this.roomComponents);
        this.add(startPanel);
        this.pack();
    }

    /**
     * Runs the player character selection panel.
     * @param playerCount
     */
    public void playerSelect(final int playerCount) {
        this.playerCount = playerCount;
        playerPanel.setMaxCharacters(playerCount);
        this.remove(startPanel);
        this.repaint();
        this.setSize(1300, 880);
        this.setResizable(false);
        this.add(playerPanel);
        playerPanel.addMouseListener(playerPanel);
    }

    /**
     * Starts the game.
     * @param characterIndices
     */
    public void startGame(final HashMap<Integer, Integer> characterIndices) {
        setupPlayers(characterIndices);

        this.remove(playerPanel);

        setupInfoPanel();
        setupGamePanel();
    }

    /**
     * Setup the playerComponents and change the state's current players.
     * @param characterIndices
     */
    public void setupPlayers(final HashMap<Integer, Integer> characterIndices) {
        ArrayList<Player> playersInGame = new ArrayList<Player>();
        for (int i = 0; i < 12; i++) {
            if (characterIndices.containsKey(i)) {
                playersInGame.add(players.get(characterIndices.get(i)));
                gamePanel.addPlayerComponent(playerComponents.get(characterIndices.get(i)));
            }
        }

        state.setPlayers(playersInGame);
    }

    /**
     * Setup the PlayerInfoPanel.
     */
    public void setupInfoPanel() {
        playerInfoPanel = new PlayerInfoPanel(state);
        playerInfoPanel.setPreferredSize(new Dimension(880, 180));
        for (int i = 0; i < players.size(); i++) {
            players.get(i).addComponentObserver(playerInfoPanel);
        }
    }

    /**
     * Setup the GamePanel.
     */
    public void setupGamePanel() {
        this.repaint();
        this.setPreferredSize(new Dimension(1600, 880));
        this.setResizable(false);
        this.add(playerInfoPanel, BorderLayout.NORTH);
//        this.add(roomInfoPanel, BorderLayout.EAST);
        this.add(gamePanel);
        this.setVisible(true);
        gamePanel.addMouseListener(gamePanel);
        gamePanel.addMouseMotionListener(gamePanel);
        gamePanel.addMouseWheelListener(gamePanel);
    }
}
