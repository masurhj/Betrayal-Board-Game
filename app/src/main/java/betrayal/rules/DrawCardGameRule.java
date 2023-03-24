package betrayal.rules;

import betrayal.*;
import betrayal.card.CardType;
import betrayal.card.Event;
import betrayal.card.Item;
import betrayal.card.Omen;
import betrayal.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class DrawCardGameRule implements GameRule {

    private CardType typeToDraw;
    private long numCardsToDraw;


    /** Constructs a DrawCardGameRule with the passed in card type and the desired number of cards to draw.
     * @param cardType
     * @param i
     */
    public DrawCardGameRule(CardType cardType, long i) {
        this.numCardsToDraw = i;
        this.typeToDraw = cardType;
    }

    /** Applies the game rule to a specified player.
     * @param player
     * @param state
     * @param parameters
     */
    @Override
    public boolean applyToPlayer(Player player, GameState state, ArrayList<Object> parameters) {
        switch (typeToDraw) {
            case EVENT:
                Collection<Event> eventDeck = state.getEventDeck();
                Iterator<Event> eventIterator = eventDeck.iterator();
                ArrayList<Event> eventsToAdd = new ArrayList<>();
                for (int i = 0; i < numCardsToDraw; i++) {
                    Event eventDrawn = eventIterator.next();
                    eventsToAdd.add(eventDrawn);
                }
                for (Event event : eventsToAdd) {
                    eventDeck.remove(event);
                    event.use(state);
                }
                break;
            case ITEM:
                Collection<Item> itemDeck = state.getItemDeck();
                Iterator<Item> itemIterator = itemDeck.iterator();
                ArrayList<Item> itemsToAdd = new ArrayList<>();
                for (int i = 0; i < numCardsToDraw; i++) {
                    Item itemDrawn = itemIterator.next();
                    itemsToAdd.add(itemDrawn);
                }
                for (Item item : itemsToAdd) {
                    player.pickUp(item);
                    itemDeck.remove(item);
                }
                break;
            case OMEN:
                Collection<Omen> omenDeck = state.getOmenDeck();
                Iterator<Omen> omenIterator = omenDeck.iterator();
                ArrayList<Omen> omensToAdd = new ArrayList<>();
                for (int i = 0; i < numCardsToDraw; i++) {
                    Omen omenDrawn = omenIterator.next();
                    omensToAdd.add(omenDrawn);
                }
                for (Omen omen : omensToAdd) {
                    omenDeck.remove(omen);
                    omen.use(state);
                }
                break;
            default:
                break;
        }
        return true;
    }
}
