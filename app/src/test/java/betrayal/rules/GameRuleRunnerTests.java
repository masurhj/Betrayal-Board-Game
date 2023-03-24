package betrayal.rules;

import betrayal.GameState;
import betrayal.player.RollType;
import betrayal.controllers.DiceRoller;
import betrayal.rules.GameRuleRunner;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameRuleRunnerTests {

    @Test
    public void gameRuleRunnerDoRoll(){
        GameRuleRunner grr = new GameRuleRunner();
        GameState mockGS = EasyMock.mock(GameState.class);
        DiceRoller mockRoller = EasyMock.mock(DiceRoller.class);
        int expected = 1;
        EasyMock.expect(mockGS.getDiceRoller()).andReturn(mockRoller);
        EasyMock.expect(mockRoller.roll(1)).andReturn(1);

        EasyMock.replay(mockGS, mockRoller);

        int actual = grr.doRoll(mockGS, RollType.ONE);

        EasyMock.verify(mockGS, mockRoller);

        Assertions.assertEquals(expected, actual);
    }
}
