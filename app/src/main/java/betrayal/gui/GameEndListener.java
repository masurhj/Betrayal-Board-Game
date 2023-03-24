package betrayal.gui;

import betrayal.controllers.HauntController;
import betrayal.controllers.SuppressFBWarnings;
import betrayal.player.Player;

import javax.swing.*;
import java.util.ArrayList;

public class GameEndListener implements ComponentObserver {

    private ArrayList<Player> players;

    private HauntController hauntController;

    /**
     * GameEndListener constructor.
     * @param players
     * @param hauntController
     */
    @SuppressFBWarnings
    public GameEndListener(ArrayList<Player> players, HauntController hauntController) {
        this.players = players;
        this.hauntController = hauntController;
        for (int i = 0; i < this.players.size(); i++) {
            this.players.get(i).addComponentObserver(this);
        }
        this.hauntController.addObserver(this);
    }

    @Override
    public void update() {
        if (this.hauntController.hasHauntStarted()) {
            endGame(true);
        } else if (checkPlayerDeath()) {
            endGame(false);
        }
    }

    /**
     * End the game.
     * @param victory
     */
    @SuppressFBWarnings
    public void endGame(boolean victory) {
        JOptionPane endGamePane = new JOptionPane();
        String header, desc;
        if (victory) {
            header = "Victory";
            desc = "The haunt has started, and the game is over.";
        } else {
            header = "Loss";
            desc = "One of the players has died, and the game is over.";
        }
        int result = endGamePane.showConfirmDialog(null, desc, header, JOptionPane.DEFAULT_OPTION);
        if (result == 0) {
            System.exit(0);
        }
    }

    /**
     * Check if any of the players are dead.
     * @return true if any players are dead
     */
    public boolean checkPlayerDeath() {
        for (int i = 0; i < players.size(); i++) {
            if (!players.get(i).isAlive()) {
                return true;
            }
        }
        return false;
    }
}
