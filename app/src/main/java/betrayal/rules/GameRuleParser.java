package betrayal.rules;

import betrayal.card.CardType;
import betrayal.player.StatisticType;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Random;

public final class GameRuleParser {

    private GameRuleParser() {

    }

    /**
     * Parse an array of game rules.
     * @param o
     * @return parsed game rule array.
     */
    public static ArrayList<GameRule> parseGameRulesArray(Object o) {
        JSONArray gameRuleArray = (JSONArray) o;
        ArrayList<GameRule> parsedRules = new ArrayList<GameRule>();
        for (int i = 0; i < gameRuleArray.size(); i++) {
            JSONArray rule = (JSONArray) gameRuleArray.get(i);
            GameRule newRule = parseRule(rule);
            parsedRules.add(newRule);
        }
        return parsedRules;
    }

    /**
     * Parse a game rule.
     * @param rule
     * @return A constructed game rule.
     */
    public static GameRule parseRule(JSONArray rule) {
        String type = (String) rule.get(0);
        switch (type) {
            case "statChange":
                //params: StatisticType statType, long value
                GameRule statChange = new StatChangeGameRule(getStatType((String) rule.get(1)), (long) rule.get(2));
                return statChange;
            case "roll":
                //params: GameRule rule, long min, long max
                //params: GameRule rule, long min (default max)
                GameRule newRule = parseRule((JSONArray) rule.get(1));
                GameRule rollRule = null;
                if (rule.size() == 3) {
                    rollRule = new RollGameRule(newRule, (long) rule.get(2));
                } else if (rule.size() == 4) {
                    rollRule = new RollGameRule(newRule, (long) rule.get(2), (long) rule.get(3));
                }
                return rollRule;
            case "steal":
                //params: none
                return new StealGameRule();
            case "draw":
                //params: CardType cardType, long numCardsDrawn
                GameRule drawRule = new DrawCardGameRule(CardType.valueOf((String) rule.get(1)), (long) rule.get(2));
                return drawRule;
            case "minStat":
                //params StatisicType statType
                return new MinStatGameRule(StatisticType.valueOf((String) rule.get(1)));
            case "allPlayer":
                return new AllPlayerGameRule(parseRule((JSONArray) rule.get(1)));
            case "move":
                if (rule.size() == 1) {
                    return new MovePlayerGameRule(new Random());
                } else {
                    return new MovePlayerGameRule((String) rule.get(1));
                }
            case "placeToken":
                return new PlaceTokenGameRule((String) rule.get(1));
            case "removeCard":
                if (rule.size() == 1) {
                    return new RemoveCardGameRule();
                } else {
                    return new RemoveCardGameRule((String) rule.get(1));
                }
            case "resetStat":
                return new ResetStatGameRule(getStatType((String) rule.get(1)));
            case "statChangeByAmount":
                return new StatChangeByRollAmountGameRule(getStatType((String) rule.get(1)), ((long) rule.get(2)), ((long) rule.get(3)));
            default:
                return null;
        }
    }

    private static StatisticType getStatType(String stat) {
        return StatisticType.valueOf(stat.toUpperCase());
    }
}
