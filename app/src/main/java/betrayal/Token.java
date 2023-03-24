package betrayal;

import betrayal.player.RollType;
import betrayal.rules.GameRule;
import betrayal.rules.GameRuleRunner;

import java.util.ArrayList;

public class Token {
    private String name;
    private String description;
    private RollType rollType;
    private ArrayList<GameRule> useGameRules = new ArrayList<>();


    /**
     * Applies the game rules that trigger when the token is interacted with.
     * @param state
     */
    public void use(GameState state) {
        GameRuleRunner grr = new GameRuleRunner();
        grr.goThroughGameRules(state, useGameRules, rollType);
    }

    /**
     * @return Name of token.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Description of token.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the roll type.
     * @param rollType
     */
    public void setRollType(RollType rollType) {
        this.rollType = rollType;
    }

    /**
     * Adds a game rule that triggers when the item is used.
     * @param rule
     */
    public void addUseGameRule(GameRule rule) {
        this.useGameRules.add(rule);
    }
}
