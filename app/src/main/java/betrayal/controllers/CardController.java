package betrayal.controllers;

import betrayal.card.Event;
import betrayal.card.Item;
import betrayal.card.Omen;

public class CardController {
    private ItemController itemController;
    private OmenController omenController;
    private EventController eventController;

    /**
     * Construct the CardController.
     * @param itemController
     * @param omenController
     * @param eventController
     */
    public CardController(ItemController itemController, OmenController omenController,
                          EventController eventController) {
        this.itemController = itemController;
        this.omenController = omenController;
        this.eventController = eventController;
    }

    /**
     * Draw an item.
     * @return next item in deck
     */
    public Item drawItem() {
        return itemController.doDraw();
    }

    /**
     * Draw an omen.
     * @return next omen in deck
     */
    public Omen drawOmen() {
        return omenController.doDraw();
    }

    /**
     * Draw an event.
     * @return next event in deck
     */
    public Event drawEvent() {
        return eventController.doDraw();
    }
}
