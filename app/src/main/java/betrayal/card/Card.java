package betrayal.card;

import betrayal.GameState;
import betrayal.player.RollType;
import betrayal.rules.GameRule;
import betrayal.rules.GameRuleRunner;

import java.util.ArrayList;

public abstract class Card {
    private boolean isUsed = false;
    private String name;
    private String header;
    private String description;
    private RollType rollType;
    private ArrayList<String> rollValues;
    private ArrayList<String> rollDescriptions;
    private ArrayList<GameRule> applyGameRules = new ArrayList<>();
    private ArrayList<GameRule> removeGameRules = new ArrayList<>();

    /**
     * @param newName
     */
    public Card(final String newName) {
        this.name = newName;
    }

    /**
     * Applies the card's apply effects to the gamestate.
     * @param state
     */
    public void use(final GameState state) {
        GameRuleRunner grr = new GameRuleRunner();
        grr.goThroughGameRules(state, applyGameRules, this.rollType);
        this.isUsed = true;
    }

    /**
     * Applies the card's remove effects to the gamestate.
     * @param state
     */
    public void remove(final GameState state) {
        GameRuleRunner grr = new GameRuleRunner();
        grr.goThroughGameRules(state, removeGameRules, this.rollType);
    }

    /**
     * @return Card name.
     */
    public String getName() {
        return this.name;
    };

    /**
     * @return Card header.
     */
    public String getHeader() {
        return this.header;
    };

    /**
     * @return Card description.
     */
    public String getDescription() {
        return this.description;
    };

    /**
     * @return A list of card roll values.
     */
    public ArrayList<String> getRollValues() {
        return new ArrayList<String>(this.rollValues);
    }

    /**
     * @return A list of card roll descriptions.
     */
    public ArrayList<String> getRollDescriptions() {
        return new ArrayList<String>(this.rollDescriptions);
    }

    /**
     * @param newName
     */
    public void setName(final String newName) {
        this.name = newName;
    }

    /**
     * @param newHeader
     */
    public void setHeader(final String newHeader) {
        this.header = newHeader;
    }

    /**
     * @param newDescription
     */
    public void setDescription(final String newDescription) {
        this.description = newDescription;
    }

    /**
     * @param newRollValues
     */
    public void setRollValues(final ArrayList<String> newRollValues) {
        this.rollValues = (ArrayList<String>) newRollValues.clone();
    }

    /**
     * @param newRollDescriptions
     */
    public void setRollDescriptions(final ArrayList<String> newRollDescriptions) {
        this.rollDescriptions = (ArrayList<String>) newRollDescriptions.clone();
    }

    /**
     * Method stub for event controller testing.
     * @return the type of the current card
     */
    public CardType getType() {
        return CardType.NONE;

    }

    /**
     * @param gameRule
     */
    public void addApplyGameRule(final GameRule gameRule) {
        this.applyGameRules.add(gameRule);
    }

    /**
     * @param gameRule
     */
    public void removeApplyGameRule(final GameRule gameRule) {
        this.applyGameRules.remove(gameRule);
    }

    /**
     * Removes all existing apply game rules.
     */
    public void clearApplyGameRules() {
        this.applyGameRules.clear();
    }

    /**
     * @param gameRule
     */
    public void addRemoveGameRule(final GameRule gameRule) {
        this.removeGameRules.add(gameRule);
    }

    /**
     * @param gameRule
     */
    public void removeRemoveGameRule(final GameRule gameRule) {
        this.removeGameRules.remove(gameRule);
    }

    /**
     * Removes all existing apply game rules.
     */
    public void clearRemoveGameRules() {
        this.removeGameRules.clear();
    }

    /**
     * @param rollType
     */
    public void setRollType(RollType rollType) {
        this.rollType = rollType;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
