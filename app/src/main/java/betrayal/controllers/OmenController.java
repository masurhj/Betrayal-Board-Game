package betrayal.controllers;

import betrayal.GameState;
import betrayal.card.CardType;
import betrayal.card.Omen;

import java.util.Collection;

public class OmenController {

    private Collection<Omen> deck;
    private GameState gameState;

    /**
     * Constructor, takes the event deck from the game state.
     * @param gameState the object that contains the event deck this object uses.
     */
    @SuppressFBWarnings
    public OmenController(final GameState gameState) {
        this.gameState = gameState;
        this.deck = gameState.getOmenDeck();
    }


    /**
     * Draw the next event.
     * Throws an illegal argument exception if the deck is empty or the card is the wrong type.
     * @return next event card.
     */
    public Omen doDraw() {

        if (deck.isEmpty()) {
            throw new EmptyDeckException("Cannot draw a card from an empty deck");
        }


        Omen currOmen = deck.iterator().next();

        if (currOmen.getType() != CardType.OMEN) {
            throw new IllegalArgumentException("Invalid card type provided by the omen deck.");
        }
        deck.remove(currOmen);
        return currOmen;
    }


}
