package betrayal.card;

public class Item extends Card {

    /**
     * @param name
     */
    public Item(final String name) {
        super(name);
    }

    @Override
    public CardType getType() {
        return CardType.ITEM;
    }
}
