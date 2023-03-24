package betrayal.card;

public class Event extends Card {

    /**
     * @param name
     */
    public Event(final String name) {
        super(name);
    }

    @Override
    public CardType getType() {
        return CardType.EVENT;
    }
}
