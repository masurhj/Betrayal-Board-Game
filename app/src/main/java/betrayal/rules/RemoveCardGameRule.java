package betrayal.rules;

import betrayal.card.Card;
import betrayal.GameState;
import betrayal.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class RemoveCardGameRule implements GameRule {
    private String cardName = "NONE";
    private static final Random RANDOM = new Random();

    /**
     * Constructor for removing a random card.
     */
    public RemoveCardGameRule() {

    }

    /**
     * Constructor for removing a specific card.
     * @param cardName
     */
    public RemoveCardGameRule(String cardName) {
        this.cardName = cardName;
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
        HashMap<String, Card> playerInventory = player.getInventory();
        Set<String> items = playerInventory.keySet();
        int size = playerInventory.size();
        String itemNameToRemove = "";

        if (size == 0) {
            return false;
        }

        if (cardName == "NONE") {
            int numToRemove = RANDOM.nextInt(size);
            int i = 0;

            for (String curItemName: items) {
                if (i == numToRemove) {
                    itemNameToRemove = curItemName;
                }
                i++;
            }
        }

        if (state.getCurrentPlayer().getInventory().containsKey(cardName)) {
            itemNameToRemove = cardName;
        }

        if (itemNameToRemove.equals("")) {
            return false;
        }

        player.drop(itemNameToRemove, state);
        //: need to remove item from room
        return true;
    }
}
