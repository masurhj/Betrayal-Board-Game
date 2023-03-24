package betrayal.player;

import betrayal.GameState;

public class RollTypeManager {

    /**
     * @param gs
     * @param val
     * @return the number of dice for the required roll
     */
    public int getRollFromType(GameState gs, RollType val) {
        if (val == null) {
            return 0;
        }

        switch (val) {
            case SANITY: return gs.getCurrentPlayer().getSanityValue();
            case KNOWLEDGE: return gs.getCurrentPlayer().getKnowledgeValue();
            case SPEED: return gs.getCurrentPlayer().getSpeedValue();
            case MIGHT: return gs.getCurrentPlayer().getMightValue();
            case PHYSICAL: return Math.max(gs.getCurrentPlayer().getMightValue(), gs.getCurrentPlayer().getSpeedValue());
            case MENTAL: return Math.max(gs.getCurrentPlayer().getKnowledgeValue(), gs.getCurrentPlayer().getSanityValue());
            case MAXTRAIT: return Math.max(gs.getCurrentPlayer().getSanityValue(), Math.max(gs.getCurrentPlayer().getKnowledgeValue(), Math.max(gs.getCurrentPlayer().getSpeedValue(), gs.getCurrentPlayer().getMightValue())));
            case NONE: return 0;
            case ONE: return 1;
            case TWO: return 2;
            case THREE: return 3;
            case FOUR: return 4;
            case FIVE: return 5;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            default: break;
        }

        return 0;

    }
}
