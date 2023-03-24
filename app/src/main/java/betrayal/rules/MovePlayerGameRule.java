package betrayal.rules;

import betrayal.controllers.SuppressFBWarnings;
import betrayal.GameState;
import betrayal.player.Player;
import betrayal.board.Room;

import java.util.ArrayList;
import java.util.Random;

public class MovePlayerGameRule implements GameRule {

    private String roomID;
    private Random random;

    /**
     * Constructor for game rule if moving player to a room.
     * @param roomID
     */
    public MovePlayerGameRule(String roomID) {
        this.roomID = roomID;
    }

    /**
     * Constructor for game rule if moving player to a random player.
     * @param rand
     */
    @SuppressFBWarnings
    public MovePlayerGameRule(Random rand) {
        this.roomID = null;
        this.random = rand;
    }

    /**
     * Apply the game rule to the game state.
     * @param state
     * @param parameters
     * @return whether or not the gamerule applied.
     */
    @Override
    public boolean apply(GameState state, ArrayList<Object> parameters) {
        Player currentPlayer = state.getCurrentPlayer();
        return this.applyToPlayer(currentPlayer, state, parameters);
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
        if (this.roomID != null) {
            Room destination = state.findRoomById(this.roomID);
            if (destination.isInPlay()) {
                player.setLocation(destination.getLocation());
            }
        } else {
            int playerIndex = random.nextInt(state.getPlayers().size());

            while (state.getCurrentPlayerIndex() == playerIndex && state.getPlayers().size() != 1) {
                playerIndex = random.nextInt(state.getPlayers().size());
            }

            player.setLocation(state.getPlayers().get(playerIndex).getLocation());
            player.setCurrentFloor(state.getPlayers().get(playerIndex).getCurrentFloor());
            player.setFloor(state.getPlayers().get(playerIndex).getFloor());
        }
        return false;
    }
}
