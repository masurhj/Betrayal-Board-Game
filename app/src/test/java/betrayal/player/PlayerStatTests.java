package betrayal.player;

import betrayal.player.StatisticType;
import betrayal.player.Player;
import betrayal.player.PlayerStatistic;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerStatTests {


    @Test
    public void testIncreaseSpeedByOnePass() {
        Player player = new Player("test player");
        PlayerStatistic mockSpeed = EasyMock.mock(PlayerStatistic.class);
        player.setSpeedStatistic(mockSpeed);
        int expected = 3;

        EasyMock.expect(mockSpeed.getCurrentIndex()).andReturn(2);
        mockSpeed.setCurrentIndex(3);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSpeed);

        int actual = player.changeStatistic(StatisticType.SPEED, 1);

        EasyMock.verify(mockSpeed);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testIncreaseMightByOnePass() {
        Player player = new Player("test player");
        PlayerStatistic mockMight = EasyMock.mock(PlayerStatistic.class);
        player.setMightStatistic(mockMight);
        int expected = 3;

        EasyMock.expect(mockMight.getCurrentIndex()).andReturn(2);
        mockMight.setCurrentIndex(3);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockMight);

        int actual = player.changeStatistic(StatisticType.MIGHT, 1);

        EasyMock.verify(mockMight);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testIncreaseKnowledgeByOnePass() {
        Player player = new Player("test player");
        PlayerStatistic mockKnowledge = EasyMock.mock(PlayerStatistic.class);
        player.setKnowledgeStatistic(mockKnowledge);
        int expected = 3;

        EasyMock.expect(mockKnowledge.getCurrentIndex()).andReturn(2);
        mockKnowledge.setCurrentIndex(3);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockKnowledge);

        int actual = player.changeStatistic(StatisticType.KNOWLEDGE, 1);

        EasyMock.verify(mockKnowledge);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testIncreaseSanityByOnePass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 3;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(2);
        mockSanity.setCurrentIndex(3);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, 1);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testIncreaseSanityByTwoPass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 4;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(2);
        mockSanity.setCurrentIndex(4);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, 2);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void testIncreaseSanityToMaxPass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 7;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(2);
        mockSanity.setCurrentIndex(7);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, 10);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDecreaseSanityToMinPass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 0;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(2);
        mockSanity.setCurrentIndex(0);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, -7);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void testIncreaseSanityByZeroPass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 2;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(2);
        mockSanity.setCurrentIndex(2);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, 0);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDecreaseSanityByOnePass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 1;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(2);
        mockSanity.setCurrentIndex(1);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, -1);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDecreaseSanityBySixPass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 1;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(7);
        mockSanity.setCurrentIndex(1);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, -6);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testIncreaseSanityBySixPass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 6;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(0);
        mockSanity.setCurrentIndex(6);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, 6);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }



    @Test
    public void testIncreaseSanityBySevenPass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 7;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(0);
        mockSanity.setCurrentIndex(7);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, 7);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testIncreaseSanityByEightPass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 7;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(0);
        mockSanity.setCurrentIndex(7);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, 8);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDecreaseSanityBySevenPass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 0;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(7);
        mockSanity.setCurrentIndex(0);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, -7);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDecreaseSanityByEightPass() {
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        int expected = 0;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(7);
        mockSanity.setCurrentIndex(0);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        int actual = player.changeStatistic(StatisticType.SANITY, -8);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testIllegalStatToChange1() {
        Player player = new Player("test player");
        boolean threwEx = false;

        try {
            player.changeStatistic(StatisticType.NONE, 7);
        } catch (IllegalArgumentException e) {
            threwEx = true;
            Assertions.assertEquals("Invalid stat to change provided: NONE", e.getMessage());
        }

        Assertions.assertEquals(threwEx, true);
    }


    @Test
    public void getPossibleValues(){
        int[] indexes = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        PlayerStatistic ps = new PlayerStatistic(0, indexes);

        int[] possibleIndexes = ps.getPossibleValues();
        for(int i = 1; i < 9; i++){
            Assertions.assertEquals(possibleIndexes[i-1], i);
        }
    }


    @Test
    public void changeStatByInRange(){
        Player player = new Player("test player");
        PlayerStatistic mockSanity = EasyMock.mock(PlayerStatistic.class);
        player.setSanityStatistic(mockSanity);
        boolean expected = true;

        EasyMock.expect(mockSanity.getCurrentIndex()).andReturn(3);
        mockSanity.setCurrentIndex(4);
        EasyMock.expectLastCall().andVoid();

        EasyMock.replay(mockSanity);

        boolean actual = player.changeCurrentSanityIndexBy(1);

        EasyMock.verify(mockSanity);

        Assertions.assertEquals(expected, actual);
    }


}
