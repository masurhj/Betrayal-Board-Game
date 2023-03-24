package betrayal.controllers;

import betrayal.controllers.DiceRoller;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiceRollerTests {
    @Test
    public void testRollNegativeOneDie() {
        Random random = EasyMock.strictMock(Random.class);
        EasyMock.replay(random);
        DiceRoller roller = new DiceRoller(random);
        assertThrows(IllegalArgumentException.class, () -> roller.roll(-1));

        EasyMock.verify(random);
    }

    @Test
    public void testRollZeroDice() {
        Random random = EasyMock.strictMock(Random.class);
        EasyMock.replay(random);
        DiceRoller roller = new DiceRoller(random);
        assertEquals(0, roller.roll(0));
        EasyMock.verify(random);
    }

    @Test
    public void testRollEightDiceWithResultOf16() {
        Random random = EasyMock.strictMock(Random.class);
        EasyMock.expect(random.nextInt(3)).andReturn(2);
        EasyMock.expect(random.nextInt(3)).andReturn(2);
        EasyMock.expect(random.nextInt(3)).andReturn(2);
        EasyMock.expect(random.nextInt(3)).andReturn(2);
        EasyMock.expect(random.nextInt(3)).andReturn(2);
        EasyMock.expect(random.nextInt(3)).andReturn(2);
        EasyMock.expect(random.nextInt(3)).andReturn(2);
        EasyMock.expect(random.nextInt(3)).andReturn(2);

        EasyMock.replay(random);
        DiceRoller roller = new DiceRoller(random);

        assertEquals(16, roller.roll(8));
        EasyMock.verify(random);
    }

    @Test
    public void testRollEightDiceWithResultOfZero() {
        Random random = EasyMock.strictMock(Random.class);
        EasyMock.expect(random.nextInt(3)).andReturn(0);
        EasyMock.expect(random.nextInt(3)).andReturn(0);
        EasyMock.expect(random.nextInt(3)).andReturn(0);
        EasyMock.expect(random.nextInt(3)).andReturn(0);
        EasyMock.expect(random.nextInt(3)).andReturn(0);
        EasyMock.expect(random.nextInt(3)).andReturn(0);
        EasyMock.expect(random.nextInt(3)).andReturn(0);
        EasyMock.expect(random.nextInt(3)).andReturn(0);

        EasyMock.replay(random);
        DiceRoller roller = new DiceRoller(random);

        assertEquals(0, roller.roll(8));
        EasyMock.verify(random);
    }

    @Test
    public void testRollNineDice() {
        Random random = EasyMock.strictMock(Random.class);
        EasyMock.replay(random);
        DiceRoller roller = new DiceRoller(random);
        assertThrows(IllegalArgumentException.class, () -> roller.roll(9));
        EasyMock.verify(random);
    }
}
