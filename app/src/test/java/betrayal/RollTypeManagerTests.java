package betrayal;

import betrayal.GameState;
import betrayal.player.RollType;
import betrayal.player.RollTypeManager;
import betrayal.player.Player;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RollTypeManagerTests {

    @Test
    public void getSanityRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 7;
        EasyMock.expect(mockGS.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getSanityValue()).andReturn(expected);
        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.SANITY);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getKnowledgeRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 7;
        EasyMock.expect(mockGS.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getKnowledgeValue()).andReturn(expected);
        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.KNOWLEDGE);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getSpeedRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 7;
        EasyMock.expect(mockGS.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getSpeedValue()).andReturn(expected);
        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.SPEED);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getMightRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 7;
        EasyMock.expect(mockGS.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getMightValue()).andReturn(expected);
        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.MIGHT);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getOneRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 1;

        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.ONE);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getTwoRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 2;

        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.TWO);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getThreeRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 3;

        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.THREE);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getFourRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 4;

        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.FOUR);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getFiveRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 5;

        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.FIVE);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getSixRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 6;

        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.SIX);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getSevenRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 7;

        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.SEVEN);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void getInvalidRoll() {
        GameState mockGS = EasyMock.mock(GameState.class);
        Player mockPlayer = EasyMock.mock(Player.class);
        RollTypeManager rollManager = new RollTypeManager();
        int expected = 0;

        EasyMock.replay(mockPlayer, mockGS);

        int actual = rollManager.getRollFromType(mockGS, RollType.INVALID);

        EasyMock.verify(mockPlayer, mockGS);
        Assertions.assertEquals(expected, actual);
    }





}
