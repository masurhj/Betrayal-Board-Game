package betrayal.board;

import betrayal.Direction;
import betrayal.JSONLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public final class RoomDeckLoader {

    private RoomDeckLoader() { }

    /**
     * Path for the rooms.json file.
     */
    public static final String ROOM_PATH = "/rooms.json";

    /**
     * Reads in the rooms.json file.
     * @return the constructed room desk
     */
    public static RoomDeck readFromFile() {
        JSONArray roomList = JSONLoader.loadFromJSON(ROOM_PATH);

        ArrayList<Room> rooms = new ArrayList<>();

        for (Object o : roomList) {
            Room r = parseRoom((JSONObject) o);
            rooms.add(r);
        }

        RoomDeck deck = new RoomDeck(rooms);

        for (Object o : roomList) {
            linkRooms(deck, (JSONObject) o);
        }

        return deck;
    }

    private static void linkRooms(RoomDeck deck, JSONObject roomData) {
        if (!roomData.containsKey("links")) {
            return;
        }

        String roomId = (String) roomData.get("id");
        Room room = deck.findById(roomId);

        for (Object o : (JSONArray) roomData.get("links")) {
            String linkedRoomId = (String) o;
            Room linkedRoom = deck.findById(linkedRoomId);

            if (room != null && linkedRoom != null) {
                room.addLinkedRoom(linkedRoom);
            }
        }
    }

    private static Room parseRoom(final JSONObject room) {
        String name = (String) room.get("name");
        String id = (String) room.get("id");
        RoomType type = RoomType.valueOf((String) room.get("type"));
        JSONArray floorArray = (JSONArray) room.get("floors");
        RoomFloor[] floors = new RoomFloor[floorArray.size()];
        for (int i = 0; i < floorArray.size(); i++) {
            floors[i] = RoomFloor.valueOf(floorArray.get(i).toString());
        }

        JSONArray doorArray = (JSONArray) room.get("doors");
        Direction[] doors = new Direction[doorArray.size()];
        for (int i = 0; i < doorArray.size(); i++) {
            doors[i] = Direction.valueOf(doorArray.get(i).toString());
        }

        String text = "";
        if (room.containsKey("text")) {
            text = (String) room.get("text");
        }
        boolean outside = false;
        if (room.containsKey("outside")) {
            outside = (boolean) room.get("outside");
        }

        return new Room(name, id, type, Arrays.asList(floors),
                Arrays.asList(doors), text, outside);
    }
}
