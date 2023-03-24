package betrayal.controllers;

import betrayal.controllers.DiceRoller;
import betrayal.controllers.Haunt;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HauntTests {
    @Test
    public void testHauntCounterAtZero() {
        DiceRoller roller = EasyMock.strictMock(DiceRoller.class);
        EasyMock.replay(roller);
        Haunt haunt = new Haunt(roller);
        assertEquals(0, haunt.getHauntCounter());
    }

    @Test
    public void testHauntCounterIncreaseToOne() {
        DiceRoller roller = EasyMock.strictMock(DiceRoller.class);
        EasyMock.replay(roller);
        Haunt haunt = new Haunt(roller);
        haunt.increaseHauntCounter();
        assertEquals(1, haunt.getHauntCounter());
    }

    @Test
    public void testHauntCounterIncreaseTo12() {
        DiceRoller roller = EasyMock.strictMock(DiceRoller.class);
        EasyMock.replay(roller);
        Haunt haunt = new Haunt(roller);
        for (int i = 0; i < 12; i++) {
            haunt.increaseHauntCounter();
        }
        assertEquals(12, haunt.getHauntCounter());
    }

    @Test
    public void testHauntCounterIncreaseTo13() {
        DiceRoller roller = EasyMock.strictMock(DiceRoller.class);
        EasyMock.replay(roller);
        Haunt haunt = new Haunt(roller);
        for (int i = 0; i < 13; i++) {
            if (i == 12) {
                assertThrows(IllegalStateException.class, () -> haunt.increaseHauntCounter());
                break;
            }
            haunt.increaseHauntCounter();
        }
        assertEquals(12, haunt.getHauntCounter());
    }

    @Test
    public void testInitialHauntStatus() {
        DiceRoller roller = EasyMock.strictMock(DiceRoller.class);
        EasyMock.replay(roller);
        Haunt haunt = new Haunt(roller);
        assertFalse(haunt.getHasHauntStarted());
    }

    @Test
    public void testPerformHauntRollAboveHauntCounter() {
        DiceRoller roller = EasyMock.strictMock(DiceRoller.class);
        EasyMock.expect(roller.roll(8)).andReturn(1);

        EasyMock.replay(roller);
        Haunt haunt = new Haunt(roller);
        haunt.performHauntRoll();
        assertFalse(haunt.getHasHauntStarted());
        assertEquals(1, haunt.getHauntCounter());
    }

    @Test
    public void testPerformHauntRollEqualToHauntCounter() {
        DiceRoller roller = EasyMock.strictMock(DiceRoller.class);
        EasyMock.expect(roller.roll(8)).andReturn(0);

        EasyMock.replay(roller);
        Haunt haunt = new Haunt(roller);
        haunt.performHauntRoll();
        assertFalse(haunt.getHasHauntStarted());
        assertEquals(1, haunt.getHauntCounter());
    }

    @Test
    public void testPerformHauntRollBelowHauntCounter() {
        DiceRoller roller = EasyMock.strictMock(DiceRoller.class);
        EasyMock.expect(roller.roll(8)).andReturn(0);
        EasyMock.expect(roller.roll(8)).andReturn(0);

        EasyMock.replay(roller);
        Haunt haunt = new Haunt(roller);
        haunt.performHauntRoll();
        assertFalse(haunt.getHasHauntStarted());
        assertEquals(1, haunt.getHauntCounter());
        haunt.performHauntRoll();
        assertTrue(haunt.getHasHauntStarted());
        assertEquals(1, haunt.getHauntCounter());
    }
}
