package betrayal.controllers;

import betrayal.controllers.DiceRoller;
import betrayal.controllers.Haunt;
import betrayal.controllers.HauntController;
import betrayal.BetrayalGameState;
import betrayal.GameState;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HauntControllerTests {
    @Test
    public void testPerformHauntRollAboveHauntCounter() {
        DiceRoller roller = EasyMock.strictMock(DiceRoller.class);
        EasyMock.expect(roller.roll(8)).andReturn(1);

        EasyMock.replay(roller);
        Haunt haunt = new Haunt(roller);

        GameState state = new BetrayalGameState();
        state.setHauntObject(haunt);

        HauntController controller = new HauntController(state);
        controller.performHauntRoll();
        assertFalse(controller.hasHauntStarted());
        assertEquals(1, controller.getHauntCounter());
    }

    @Test
    public void testPerformHauntRollEqualToHauntCounter() {
        DiceRoller roller = EasyMock.strictMock(DiceRoller.class);
        EasyMock.expect(roller.roll(8)).andReturn(0);

        EasyMock.replay(roller);
        Haunt haunt = new Haunt(roller);
        GameState state = new BetrayalGameState();
        state.setHauntObject(haunt);

        HauntController controller = new HauntController(state);
        controller.performHauntRoll();
        assertFalse(controller.hasHauntStarted());
        assertEquals(1, controller.getHauntCounter());
    }

    @Test
    public void testPerformHauntRollBelowHauntCounter() {
        DiceRoller roller = EasyMock.strictMock(DiceRoller.class);
        EasyMock.expect(roller.roll(8)).andReturn(0);
        EasyMock.expect(roller.roll(8)).andReturn(0);

        EasyMock.replay(roller);
        Haunt haunt = new Haunt(roller);
        GameState state = new BetrayalGameState();
        state.setHauntObject(haunt);

        HauntController controller = new HauntController(state);
        controller.performHauntRoll();
        assertFalse(controller.hasHauntStarted());
        assertEquals(1, controller.getHauntCounter());
        controller.performHauntRoll();
        assertTrue(controller.hasHauntStarted());
        assertEquals(1, controller.getHauntCounter());
    }
}
