package betrayal.controllers;

import betrayal.*;
import betrayal.card.Item;
import betrayal.controllers.ItemController;
import betrayal.player.Player;
import betrayal.player.PlayerStatistic;
import betrayal.player.RollType;
import betrayal.player.StatisticType;
import betrayal.rules.StatChangeGameRule;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class ItemControllerTests {

    @Test
    public void testItemControllerDrawItem() {
        GameState state = EasyMock.strictMock(BetrayalGameState.class);
        Player mockPlayer = EasyMock.strictMock(Player.class);
        Collection<Item> itemDeck = EasyMock.strictMock(Collection.class);
        Iterator<Item> deckIterator = EasyMock.strictMock(Iterator.class);
        Item testItem = new Item("Bell");
        EasyMock.expect(state.getItemDeck()).andReturn(itemDeck);
        EasyMock.expect(itemDeck.iterator()).andReturn(deckIterator);
        EasyMock.expect(deckIterator.next()).andReturn(testItem);
        EasyMock.expect(itemDeck.remove(testItem)).andReturn(true);
        EasyMock.expect(state.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.replay(state, itemDeck, deckIterator);
        ItemController itemController = new ItemController(state);

        Item item = itemController.doDraw();
        assertEquals(testItem, item);

        EasyMock.verify(state, itemDeck, deckIterator);
    }

    @Test
    public void testUseItem() {
        GameState state = new BetrayalGameState();
        Item test = new Item("Test");
        StatChangeGameRule gameRule = new StatChangeGameRule(StatisticType.SANITY, 1);
        test.addApplyGameRule(gameRule);
        test.setRollType(RollType.valueOf("SANITY"));
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player("James");
        players.add(player);
        player.setSanityStatistic(new PlayerStatistic(3, new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
        player.pickUp(test);
        state.setPlayers(players);

        ItemController controller = new ItemController(state);

        assertEquals(4, player.getSanityValue());
        controller.useItem(test);
        assertEquals(5, player.getSanityValue());
    }
}
