package betrayal.card;

import betrayal.JSONLoader;
import betrayal.player.RollType;
import betrayal.rules.GameRule;
import betrayal.rules.GameRuleParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public final class CardLoader {

    private static final String ITEM_PATH = "/Items.json";
    private static final String EVENT_PATH = "/Events.json";
    private static final String OMEN_PATH = "/Omens.json";

    private CardLoader() {

    }

    /**
     * @return A list of item objects from Items.json.
     */
    public static ArrayList<Item> loadItemsFromJSON() {
        ArrayList<Item> items = new ArrayList<Item>();
        JSONArray itemList = JSONLoader.loadFromJSON(ITEM_PATH);

        for (int i = 0; i < itemList.size(); i++) {
            items.add(parseItemJson(itemList.get(i)));
        }

        return items;
    }

    /**
     * @return A list of omen objects from Omens.json.
     */
    public static ArrayList<Omen> loadOmensFromJSON() {
        ArrayList<Omen> omens = new ArrayList<Omen>();
        JSONArray omenList = JSONLoader.loadFromJSON(OMEN_PATH);

        for (int i = 0; i < omenList.size(); i++) {
            omens.add(parseOmenJson(omenList.get(i)));
        }

        return omens;
    }

    /**
     * @return A list of event objects from Events.json.
     */
    public static ArrayList<Event> loadEventsFromJSON() {
        ArrayList<Event> events = new ArrayList<Event>();
        JSONArray eventList = JSONLoader.loadFromJSON(EVENT_PATH);

        for (int i = 0; i < eventList.size(); i++) {
            events.add(parseEventJson(eventList.get(i)));
        }
        return events;
    }

    /**
     * @param newCard
     * @param object
     */
    private static void parseCardAttributes(final Card newCard, final JSONObject object) {
        newCard.setHeader((String) object.get("Header"));
        newCard.setDescription((String) object.get("Description"));
        newCard.setRollValues(parseStringArray(object.get("Roll_Value")));
        newCard.setRollDescriptions(parseStringArray(object.get("Roll_Description")));
        String rollTypeString = ((String) object.get("Roll_Type"));
        newCard.setRollType(RollType.valueOf(rollTypeString));
        for (GameRule newRule : GameRuleParser.parseGameRulesArray(object.get("Apply_Game_Rules"))) {
            if (newRule != null) {
                newCard.addApplyGameRule(newRule);
            }
        }
        for (GameRule newRule : GameRuleParser.parseGameRulesArray(object.get("Remove_Game_Rules"))) {
            if (newRule != null) {
                newCard.addRemoveGameRule(newRule);
            }
        }

    }

    /**
     * @param o
     * @return A list of the parsed JSON array.
     */
    private static ArrayList<String> parseStringArray(final Object o) {
        JSONArray stringArray = (JSONArray) o;
        ArrayList<String> parsedArray = new ArrayList<String>();
        for (int i = 0; i < stringArray.size(); i++) {
            String curString = (String) stringArray.get(i);
            parsedArray.add(curString);
        }
        return parsedArray;
    }

    /**
     * @param o
     * @return A parsed item object.
     */
    private static Item parseItemJson(final Object o) {
        JSONObject itemObject = (JSONObject) o;
        Item newItem = new Item((String) itemObject.get("Name"));
        parseCardAttributes(newItem, itemObject);
        //card type specific parses here
        return newItem;
    }


    /**
     * @param o
     * @return A parsed omen object.
     */
    private static Omen parseOmenJson(final Object o) {
        JSONObject omenObject = (JSONObject) o;
        Omen newOmen = new Omen((String) omenObject.get("Name"));
        parseCardAttributes(newOmen, omenObject);
        //card type specific parses here
        return newOmen;
    }

    /**
     * @param o
     * @return A parsed event object.
     */
    private static Event parseEventJson(final Object o) {
        JSONObject eventObject = (JSONObject) o;
        Event newEvent = new Event((String) eventObject.get("Name"));
        parseCardAttributes(newEvent, eventObject);
        //card type specific parses here
        return newEvent;
    }
}
