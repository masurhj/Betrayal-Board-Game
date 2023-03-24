package betrayal.controllers;

import betrayal.GameState;
import betrayal.card.Item;

import java.util.Collection;
import java.util.Iterator;

public class ItemController {
    private final GameState state;

    /**
     * Initializes an instance of the ItemController.
     * @param state
     */
    @SuppressFBWarnings
    public ItemController(final GameState state) {
        this.state = state;
    }

    /**
     * Draws the item at the top of the game's item deck and returns it.
     * @return the item drawn from the item deck stored in the game state
     */
    public Item doDraw() {
        Collection<Item> deck = state.getItemDeck();
        Iterator<Item> itemIterator = deck.iterator();

        Item nextItem = itemIterator.next();
        deck.remove(nextItem);
        state.getCurrentPlayer().pickUp(nextItem);
        return nextItem;
    }

    /**
     * @param itemToUse
     */
    public void useItem(Item itemToUse) {
        itemToUse.use(state);
    }
}
