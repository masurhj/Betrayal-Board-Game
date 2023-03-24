package betrayal.rules;

import betrayal.GameState;
import betrayal.card.Item;
import betrayal.player.Player;

import java.util.ArrayList;

public class StealGameRule implements GameRule {

    /**
     * Applies the game rule to a specified player.
     *
     * @param player
     * @param state
     * @param parameters
     */
    @Override
    public boolean applyToPlayer(Player player, GameState state, ArrayList<Object> parameters) {
        ArrayList<Player> players = state.getPlayers();
        for (Player playerInList : players) {
            if (!playerInList.equals(player) && playerInList.getCurrentRoom().equals(player.getCurrentRoom())) {
                Object[] items = playerInList.getInventory().keySet().toArray();
                if (items.length > 0) {
                    String firstItemName = (String) items[0];

                    Item firstItem = (Item) playerInList.drop(firstItemName, state);
                    player.pickUp(firstItem);
                    return true;
                }
            }
        }
        return false;
    }
}
