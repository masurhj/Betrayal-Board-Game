package betrayal;

import betrayal.player.RollType;
import betrayal.rules.GameRule;
import betrayal.rules.GameRuleParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public final class TokenLoader {
    private static final String TOKEN_PATH = "/Tokens.json";

    private TokenLoader() {

    }

    /**
     * @return Generated token objects from the JSON array.
     */
    public static ArrayList<Token> loadTokensFromJSON() {
        ArrayList<Token> tokens = new ArrayList<Token>();
        JSONArray tokenList = JSONLoader.loadFromJSON(TOKEN_PATH);

        for (int i = 0; i < tokenList.size(); i++) {
            tokens.add(parseTokenJson(tokenList.get(i)));
        }

        return tokens;
    }

    /**
     * Helper to construct tokens.
     * @param o
     * @return A token.
     */
    private static Token parseTokenJson(Object o) {
        JSONObject tokenObject = (JSONObject) o;
        Token newToken = new Token();
        parseTokenAttributes(newToken, tokenObject);
        return newToken;
    }

    /**
     * Helper to set token values.
     * @param newToken
     * @param tokenObject
     */
    private static void parseTokenAttributes(Token newToken, JSONObject tokenObject) {
        newToken.setName((String) tokenObject.get("Name"));
        newToken.setDescription((String) tokenObject.get("Description"));
        String rollType = (String) tokenObject.get("Roll_Type");
        newToken.setRollType(RollType.valueOf(rollType.toUpperCase()));
        ArrayList<GameRule> parsedUseRules = GameRuleParser.parseGameRulesArray(tokenObject.get("Use_Game_Rules"));
        for (GameRule newRule : parsedUseRules) {
            if (newRule != null) {
                newToken.addUseGameRule(newRule);
            }
        }
    }
}
