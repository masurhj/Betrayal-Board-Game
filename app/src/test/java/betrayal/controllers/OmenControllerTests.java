package betrayal.controllers;

import betrayal.GameState;
import betrayal.controllers.EmptyDeckException;
import betrayal.controllers.OmenController;
import betrayal.card.Card;
import betrayal.card.CardType;
import betrayal.card.Omen;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collection;

public class OmenControllerTests {

    @Test
    public void drawLegitimateOmen() {
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Omen mockCard = EasyMock.mock(Omen.class);
        Collection<Omen> omenDeck = new ArrayList<>();
        omenDeck.add(mockCard);

        EasyMock.expect(gameStateMock.getOmenDeck()).andReturn(omenDeck);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.OMEN);


        EasyMock.replay(gameStateMock,
                mockCard);

        OmenController oc = new OmenController(gameStateMock);
        Card actual = oc.doDraw();

        EasyMock.verify(gameStateMock,
                mockCard);

        Assertions.assertEquals(actual, mockCard);
        Assertions.assertEquals(0, omenDeck.size());
    }


    @Test
    public void drawLegitimateOmenBiggerDeck() {
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Omen mockCard = EasyMock.mock(Omen.class);
        Omen mockCard2 = EasyMock.mock(Omen.class);

        Collection<Omen> omenDeck = new ArrayList<>();
        omenDeck.add(mockCard);
        omenDeck.add(mockCard2);

        EasyMock.expect(gameStateMock.getOmenDeck()).andReturn(omenDeck);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.OMEN);


        EasyMock.replay(gameStateMock,
                mockCard, mockCard2);

        OmenController oc = new OmenController(gameStateMock);
        Card actual = oc.doDraw();

        EasyMock.verify(gameStateMock,
                mockCard, mockCard2);

        Assertions.assertEquals(actual, mockCard);
        Assertions.assertEquals(1, omenDeck.size());
    }


    @Test
    public void drawIlligitimateEmptyCard(){
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Omen mockCard = EasyMock.mock(Omen.class);
        Collection<Omen> omenDeck = new ArrayList<>();
        omenDeck.add(mockCard);

        EasyMock.expect(gameStateMock.getOmenDeck()).andReturn(omenDeck);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.NONE);


        EasyMock.replay(gameStateMock,
                mockCard);

        OmenController oc = new OmenController(gameStateMock);
        Assertions.assertThrows(IllegalArgumentException.class, () -> oc.doDraw());

        EasyMock.verify(gameStateMock,
                mockCard);

        Assertions.assertEquals(1, omenDeck.size());
    }

    @Test
    public void drawIlligitimateEventCard(){
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Omen mockCard = EasyMock.mock(Omen.class);
        Collection<Omen> omenDeck = new ArrayList<>();
        omenDeck.add(mockCard);

        EasyMock.expect(gameStateMock.getOmenDeck()).andReturn(omenDeck);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.EVENT);


        EasyMock.replay(gameStateMock,
                mockCard);

        OmenController oc = new OmenController(gameStateMock);
        Assertions.assertThrows(IllegalArgumentException.class, () -> oc.doDraw());

        EasyMock.verify(gameStateMock,
                mockCard);

        Assertions.assertEquals(1, omenDeck.size());
    }

    @Test
    public void drawIlligitimateItemCard(){
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Omen mockCard = EasyMock.mock(Omen.class);
        Collection<Omen> omenDeck = new ArrayList<>();
        omenDeck.add(mockCard);

        EasyMock.expect(gameStateMock.getOmenDeck()).andReturn(omenDeck);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.ITEM);


        EasyMock.replay(gameStateMock,
                mockCard);

        OmenController oc = new OmenController(gameStateMock);
        Assertions.assertThrows(IllegalArgumentException.class, () -> oc.doDraw());

        EasyMock.verify(gameStateMock,
                mockCard);

        Assertions.assertEquals(1, omenDeck.size());
    }

    @Test
    public void drawIlligitimateEmptyDeck(){
        GameState gameStateMock = EasyMock.mock(GameState.class);
        Omen mockCard = EasyMock.mock(Omen.class);
        Collection<Omen> omenDeck = new ArrayList<>();

        EasyMock.expect(gameStateMock.getOmenDeck()).andReturn(omenDeck);



        EasyMock.replay(gameStateMock,
                mockCard);

        OmenController oc = new OmenController(gameStateMock);
        Assertions.assertThrows(EmptyDeckException.class, () -> oc.doDraw());

        EasyMock.verify(gameStateMock,
                mockCard);

        Assertions.assertEquals(0, omenDeck.size());
    }

}
