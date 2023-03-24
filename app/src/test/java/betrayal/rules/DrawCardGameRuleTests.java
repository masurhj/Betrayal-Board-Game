package betrayal.rules;

import betrayal.*;
import betrayal.card.CardType;
import betrayal.card.Event;
import betrayal.card.Item;
import betrayal.card.Omen;
import betrayal.player.Player;
import betrayal.rules.DrawCardGameRule;
import betrayal.rules.GameRule;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DrawCardGameRuleTests {
    @Test
    public void testDrawSingleCard() {
        GameState state = new BetrayalGameState();
        ArrayList<Player> players = new ArrayList<>();
        Player testPlayer = new Player("Test");
        players.add(testPlayer);
        state.setPlayers(players);

        ArrayList<Item> items = new ArrayList<>();
        Item firstItem = new Item("First Item");
        items.add(firstItem);
        state.setItemDeck(items);

        GameRule ruleUnderTest = new DrawCardGameRule(CardType.ITEM, 1);
        ArrayList<Object> params = new ArrayList<>();

        ruleUnderTest.apply(state, params);
        assertTrue(testPlayer.getInventory().containsKey("First Item"));
    }

    @Test
    public void testDrawMultipleCards() {
        GameState state = new BetrayalGameState();
        ArrayList<Player> players = new ArrayList<>();
        Player testPlayer = new Player("Test");
        players.add(testPlayer);
        state.setPlayers(players);

        ArrayList<Item> items = new ArrayList<>();
        Item firstItem = new Item("First Item");
        Item secondItem = new Item("Second Item");
        items.add(firstItem);
        items.add(secondItem);
        state.setItemDeck(items);

        GameRule ruleUnderTest = new DrawCardGameRule(CardType.ITEM, 2);
        ArrayList<Object> params = new ArrayList<>();

        ruleUnderTest.apply(state, params);
        assertTrue(testPlayer.getInventory().containsKey("First Item"));
        assertTrue(testPlayer.getInventory().containsKey("Second Item"));
    }

    @Test
    public void testDrawMultipleOmenCards() {
        GameState state = new BetrayalGameState();
        ArrayList<Player> players = new ArrayList<>();
        Player testPlayer = new Player("Test");
        players.add(testPlayer);
        state.setPlayers(players);

        ArrayList<Omen> omens = new ArrayList<>();
        Omen firstOmen = EasyMock.mock(Omen.class);
        Omen secondOmen = EasyMock.mock(Omen.class);
        omens.add(firstOmen);
        omens.add(secondOmen);
        state.setOmenDeck(omens);

        firstOmen.use(state);
        EasyMock.expectLastCall().andVoid();
        secondOmen.use(state);
        EasyMock.expectLastCall().andVoid();
        EasyMock.replay(firstOmen, secondOmen);

        GameRule ruleUnderTest = new DrawCardGameRule(CardType.OMEN, 2);
        ArrayList<Object> params = new ArrayList<>();

        ruleUnderTest.apply(state, params);

        EasyMock.verify(firstOmen, secondOmen);
        assertFalse(testPlayer.getInventory().containsKey("First omen"));
        assertFalse(testPlayer.getInventory().containsKey("Second omen"));
    }


    @Test
    public void testDrawMultipleEventCards() {
        GameState state = new BetrayalGameState();
        ArrayList<Player> players = new ArrayList<>();
        Player testPlayer = new Player("Test");
        players.add(testPlayer);
        state.setPlayers(players);

        ArrayList<Event> events = new ArrayList<>();
        Event firstEvent = EasyMock.mock(Event.class);
        Event secondEvent = EasyMock.mock(Event.class);
        events.add(firstEvent);
        events.add(secondEvent);
        state.setEventDeck(events);

        firstEvent.use(state);
        EasyMock.expectLastCall().andVoid();
        secondEvent.use(state);
        EasyMock.expectLastCall().andVoid();
        EasyMock.replay(firstEvent, secondEvent);

        GameRule ruleUnderTest = new DrawCardGameRule(CardType.EVENT, 2);
        ArrayList<Object> params = new ArrayList<>();

        ruleUnderTest.apply(state, params);

        EasyMock.verify(firstEvent, secondEvent);

        assertFalse(testPlayer.getInventory().containsKey("First event"));
        assertFalse(testPlayer.getInventory().containsKey("Second event"));
    }

    @Test
    public void testDrawSingleEventCard() {
        GameState state = new BetrayalGameState();
        ArrayList<Player> players = new ArrayList<>();
        Player testPlayer = new Player("Test");
        players.add(testPlayer);
        state.setPlayers(players);

        ArrayList<Event> events = new ArrayList<>();
        Event firstEvent = EasyMock.mock(Event.class);
        events.add(firstEvent);
        state.setEventDeck(events);

        firstEvent.use(state);
        EasyMock.expectLastCall().andVoid();
        EasyMock.replay(firstEvent);

        GameRule ruleUnderTest = new DrawCardGameRule(CardType.EVENT, 1);
        ArrayList<Object> params = new ArrayList<>();

        ruleUnderTest.apply(state, params);

        EasyMock.verify(firstEvent);

        assertFalse(testPlayer.getInventory().containsKey("First event"));

    }

    @Test
    public void testDrawSingleOmenCard() {
        GameState state = new BetrayalGameState();
        ArrayList<Player> players = new ArrayList<>();
        Player testPlayer = new Player("Test");
        players.add(testPlayer);
        state.setPlayers(players);

        ArrayList<Omen> omens = new ArrayList<>();
        Omen firstOmen = EasyMock.mock(Omen.class);
        omens.add(firstOmen);
        state.setOmenDeck(omens);

        firstOmen.use(state);
        EasyMock.expectLastCall().andVoid();
        EasyMock.replay(firstOmen);

        GameRule ruleUnderTest = new DrawCardGameRule(CardType.OMEN, 1);
        ArrayList<Object> params = new ArrayList<>();

        ruleUnderTest.apply(state, params);
        EasyMock.verify(firstOmen);

        assertFalse(testPlayer.getInventory().containsKey("First omen"));
    }
}