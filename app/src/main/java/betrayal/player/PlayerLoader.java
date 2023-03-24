package betrayal.player;

import betrayal.JSONLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public final class PlayerLoader {

    /**
     * Path of player file.
     */
    private static final String PLAYER_PATH = "/Players.json";

    private PlayerLoader() {
    }

    /**
     * @return a list of players
     */
    public static ArrayList<Player> loadPlayerFromJSON() {
        ArrayList<Player> players = new ArrayList<Player>();
        JSONArray playerList = JSONLoader.loadFromJSON(PLAYER_PATH);

        for (int i = 0; i < playerList.size(); i++) {
            players.add(parsePlayerJson(playerList.get(i)));
        }

        return players;
    }

    /**
     * @param o
     * @return player
     */
    public static Player parsePlayerJson(final Object o) {
        JSONObject playerObject = (JSONObject) o;
        Player p = new Player((String) playerObject.get("Name"));
        p.setAge((Long) playerObject.get("Age"));
        p.setWeight((Long) playerObject.get("Weight"));
        p.setColor(PlayerColor.valueOf(((String) playerObject.get("Color")).toUpperCase()));
        p.setHeight((String) playerObject.get("Height"));
        p.setSpeedStatistic(parsePlayerStatistic(playerObject.get("Speed")));
        p.setMightStatistic(parsePlayerStatistic(playerObject.get("Might")));
        p.setSanityStatistic(parsePlayerStatistic(playerObject.get("Sanity")));
        p.setKnowledgeStatistic(parsePlayerStatistic(playerObject.get("Knowledge")));
        p.resetMoveCounter();
        return p;
    }

    /**
     * @param o
     * @return player statistic
     */
    public static PlayerStatistic parsePlayerStatistic(final Object o) {
        JSONArray playerStatisticArray = (JSONArray) o;
        //the last element in the array is the index
        int index = 0;
        int[] array = new int[8];
        for (int i = 1; i < playerStatisticArray.size(); i++) {
            long longVal = (Long) playerStatisticArray.get(i);
            int val = (int) longVal;
            array[i - 1] = val;
        }

        long longVal = (Long) playerStatisticArray.get(0);
        int val = (int) longVal;
        index = val;

        return new PlayerStatistic(index, array);
    }
}
