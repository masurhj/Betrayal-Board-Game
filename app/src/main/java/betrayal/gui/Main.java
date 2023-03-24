package betrayal.gui;

import betrayal.GameState;
import betrayal.GameStateFactory;
import betrayal.board.Floor;
import betrayal.board.Room;
import betrayal.board.RoomFloor;
import betrayal.player.Player;

import java.util.ArrayList;

public final class Main {
    /**
     * Construct the game object.
     */
    private Main() {
        GameState gameState = new GameStateFactory().build();

        ArrayList<RoomComponent> roomComponents = new ArrayList<RoomComponent>();

        for (RoomFloor rFloor : RoomFloor.values()) {
            Floor floor = gameState.getFloor(rFloor);
            for (Room room : floor) {
                RoomComponent rc = new RoomComponent(gameState, room);
                room.addObserver(rc);
                roomComponents.add(rc);
            }
        }

        ArrayList<Player> players = gameState.getPlayers();

        ArrayList<PlayerComponent> playerComponents = new ArrayList<PlayerComponent>();


        for (int i = 0; i < 12; i++) {
            PlayerComponent component = new PlayerComponent(gameState, players.get(i));
            players.get(i).addComponentObserver(component);
            players.get(i).setFloor(gameState.getFloor(RoomFloor.GROUND));
            playerComponents.add(component);
        }

        new MainUI(gameState, roomComponents, players, playerComponents);
    }

    /**
     * Start the game.
     * @param args
     */
    public static void main(final String[] args) {
        new Main();
    }
}
