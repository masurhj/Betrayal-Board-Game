package betrayal.card;

public class Omen extends Card {

    /**
     * @param name
     */
    public Omen(final String name) {
        super(name);
    }

    @Override
    public CardType getType() {
        return CardType.OMEN;
    }
}
