package betrayal.controllers;

import betrayal.card.Event;
import betrayal.GameState;
import betrayal.controllers.EmptyDeckException;
import betrayal.controllers.EventController;
import betrayal.card.Card;
import betrayal.card.CardType;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

public class EventControllerTests {

    @Test
    public void drawLegitimateEvent(){
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Event mockCard = EasyMock.mock(Event.class);
        Collection<Event> eventDeck = new ArrayList<>();
        eventDeck.add(mockCard);

        EasyMock.expect(gameStateMock.getEventDeck()).andReturn(eventDeck);
        mockCard.use(gameStateMock);
        EasyMock.expectLastCall().andVoid();
        EasyMock.expect(mockCard.getType()).andReturn(CardType.EVENT);


        EasyMock.replay(gameStateMock,
                mockCard);

        EventController ec = new EventController(gameStateMock);
        Card actual = ec.doDraw();

        EasyMock.verify(gameStateMock,
                mockCard);

        Assertions.assertEquals(actual, mockCard);
        Assertions.assertEquals(0, eventDeck.size());
    }

    @Test
    public void drawLegitimateEventBiggerDeck(){
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Event mockCard = EasyMock.mock(Event.class);
        Event mockCard2 = EasyMock.mock(Event.class);
        Collection<Event> eventDeck = new ArrayList<>();
        eventDeck.add(mockCard);
        eventDeck.add(mockCard2);

        EasyMock.expect(gameStateMock.getEventDeck()).andReturn(eventDeck);
        mockCard.use(gameStateMock);
        EasyMock.expectLastCall().andVoid();
        EasyMock.expect(mockCard.getType()).andReturn(CardType.EVENT);


        EasyMock.replay(gameStateMock, mockCard, mockCard2);

        EventController ec = new EventController(gameStateMock);
        Card actual = ec.doDraw();

        EasyMock.verify(gameStateMock, mockCard, mockCard2);

        Assertions.assertEquals(actual, mockCard);
        Assertions.assertEquals(1, eventDeck.size());
    }


    @Test
    public void drawEventEmptyDeck(){
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Collection<Event> eventDeck = new ArrayList<>();

        EasyMock.expect(gameStateMock.getEventDeck()).andReturn(eventDeck);

        EasyMock.replay(gameStateMock);

        EventController ec = new EventController(gameStateMock);
        Assertions.assertThrows(EmptyDeckException.class, () -> ec.doDraw());

        EasyMock.verify(gameStateMock);

        Assertions.assertEquals(0, eventDeck.size());
    }

    @Test
    public void drawIlligitimateEvent(){
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Event mockCard = EasyMock.mock(Event.class);
        Collection<Event> eventDeck = new ArrayList<>();
        eventDeck.add(mockCard);

        EasyMock.expect(gameStateMock.getEventDeck()).andReturn(eventDeck);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.OMEN);


        EasyMock.replay(gameStateMock,
                mockCard);

        EventController ec = new EventController(gameStateMock);
        Assertions.assertThrows(IllegalArgumentException.class, () -> ec.doDraw());

        EasyMock.verify(gameStateMock,
                mockCard);

        Assertions.assertEquals(1, eventDeck.size());
    }

    @Test
    public void drawIlligitimateItem(){
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Event mockCard = EasyMock.mock(Event.class);
        Collection<Event> eventDeck = new ArrayList<>();
        eventDeck.add(mockCard);

        EasyMock.expect(gameStateMock.getEventDeck()).andReturn(eventDeck);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.ITEM);


        EasyMock.replay(gameStateMock,
                mockCard);

        EventController ec = new EventController(gameStateMock);
        Assertions.assertThrows(IllegalArgumentException.class, () -> ec.doDraw());

        EasyMock.verify(gameStateMock,
                mockCard);

        Assertions.assertEquals(1, eventDeck.size());
    }

    @Test
    public void drawIlligitimateEmptyCard(){
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Event mockCard = EasyMock.mock(Event.class);
        Collection<Event> eventDeck = new ArrayList<>();
        eventDeck.add(mockCard);

        EasyMock.expect(gameStateMock.getEventDeck()).andReturn(eventDeck);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.NONE);


        EasyMock.replay(gameStateMock,
                mockCard);

        EventController ec = new EventController(gameStateMock);
        Assertions.assertThrows(IllegalArgumentException.class, () -> ec.doDraw());

        EasyMock.verify(gameStateMock,
                mockCard);

        Assertions.assertEquals(1, eventDeck.size());
    }

}
