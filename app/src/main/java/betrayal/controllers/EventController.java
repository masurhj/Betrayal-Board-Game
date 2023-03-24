package betrayal.controllers;

import betrayal.card.Event;
import betrayal.GameState;
import betrayal.card.CardType;

import java.util.Collection;

public class EventController {

    private Collection<Event> deck;
    private GameState gameState;

    /**
     * Constructor, takes the event deck from the game state.
     * @param gameState the object that contains the event deck this object uses.
     */
    @SuppressFBWarnings
    public EventController(final GameState gameState) {
        this.gameState = gameState;
        this.deck = gameState.getEventDeck();
    }


    /**
     * Draw the next event.
     * Throws an illegal argument exception if the deck is empty or the card is the wrong type.
     * @return next event card.
     */
    public Event doDraw() {

        if (deck.isEmpty()) {
            throw new EmptyDeckException("Cannot draw a card from an empty deck");
        }
        Event currEvent = deck.iterator().next();

        if (currEvent.getType() != CardType.EVENT) {
            throw new IllegalArgumentException("Invalid card type provided by the event deck.");
        }
        deck.remove(currEvent);
        currEvent.use(gameState);
        return currEvent;
    }


}
