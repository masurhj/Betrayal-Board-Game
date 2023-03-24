package betrayal;

import betrayal.BetrayalGameState;
import betrayal.Direction;
import betrayal.GameState;
import betrayal.controllers.RoomController;
import betrayal.board.*;

import java.util.Arrays;

public class TestUtilities {

    public static Floor createFloorGrid(int xSize, int ySize, RoomFloor roomFloor, RoomController roomController) {
        Floor f = new Floor(roomFloor);
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                Room r = new Room(String.format("Room-%d-%d", x, y),
                        String.format("room-%s-%d-%d", roomFloor.toString().toLowerCase(), x, y),
                        RoomType.NONE,
                        Arrays.asList(roomFloor),
                        Direction.all(),
                        "Room Text Here",
                        false);
                roomController.addRoom(f, r, new GridPoint(x, y));
            }
        }
        return f;
    }

    public static GameState createEmptyGameState() {
        GameState state = new BetrayalGameState();
        state.setRoomDeck(new RoomDeck());
        return state;
    }

}
