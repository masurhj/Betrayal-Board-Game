package betrayal;

import betrayal.card.CardLoader;
import betrayal.card.Event;
import betrayal.card.Item;
import betrayal.card.Omen;
import betrayal.controllers.DiceRoller;
import betrayal.controllers.Haunt;
import betrayal.controllers.RoomController;
import betrayal.controllers.SuppressFBWarnings;
import betrayal.board.*;
import betrayal.player.Player;
import betrayal.player.PlayerLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class GameStateFactory {

    private final RoomController roomController;
    private final GameState gameState;

    /**
     * Construct a new game state factory.
     */
    public GameStateFactory() {
        this.gameState = new BetrayalGameState();
        this.roomController = new RoomController(this.gameState);
    }

    /**
     * Creates a new game state by loading in needed resources.
     * @return the new game state
     */
    @SuppressFBWarnings
    public GameState build() {
        loadRoomDeck(gameState);
        setupFloors(gameState);
        loadPlayers(gameState);
        loadCards(gameState);
        setupHaunt(gameState);

        gameState.changeResourceBundle("en", "US");

        return gameState;
    }

    private void loadPlayers(GameState gameState) {
        ArrayList<Player> players = PlayerLoader.loadPlayerFromJSON();
        gameState.setPlayers(players);
    }

    private void loadCards(GameState gameState) {
        ArrayList<Event> eventCards = CardLoader.loadEventsFromJSON();
        ArrayList<Item> itemCards = CardLoader.loadItemsFromJSON();
        ArrayList<Omen> omenCards = CardLoader.loadOmensFromJSON();
        ArrayList<Token> tokens = TokenLoader.loadTokensFromJSON();

        Collections.shuffle(eventCards);
        Collections.shuffle(itemCards);
        Collections.shuffle(omenCards);
        Collections.shuffle(tokens);

        gameState.setEventDeck(eventCards);
        gameState.setItemDeck(itemCards);
        gameState.setOmenDeck(omenCards);
        gameState.setTokens(tokens);
    }

    private void loadRoomDeck(GameState gameState) {
        RoomDeck roomDeck = RoomDeckLoader.readFromFile();
        roomDeck.shuffle();
        gameState.setRoomDeck(roomDeck);
    }

    private void setupFloors(GameState gameState) {
        RoomDeck roomDeck = gameState.getRoomDeck();

        // Ground floor
        Floor ground = new Floor(RoomFloor.GROUND);
        Room entranceHall = roomDeck.findById("entrance_hall");
        Room foyer = roomDeck.findById("foyer");
        Room grandStaircase = roomDeck.findById("grand_staircase");
        roomController.addRoom(ground, entranceHall, new GridPoint(0, 0));
        roomController.addRoom(ground, foyer, new GridPoint(-1, 0));
        roomController.addRoom(ground, grandStaircase, new GridPoint(-2, 0));
        gameState.setFloor(RoomFloor.GROUND, ground);

        // Basement
        Floor basement = new Floor(RoomFloor.BASEMENT);
        Room basementLanding = roomDeck.findById("basement_landing");
        Room basementStaircase = roomDeck.findById("stairs_from_basement");
        roomController.addRoom(basement, basementLanding, new GridPoint(0, 0));
        roomController.addRoom(basement, basementStaircase, new GridPoint(-1, 0));
        gameState.setFloor(RoomFloor.BASEMENT, basement);

        // Upper Floor
        Floor upper = new Floor(RoomFloor.UPPER);
        Room upperLanding = roomDeck.findById("upper_landing");
        roomController.addRoom(upper, upperLanding, new GridPoint(0, 0));
        gameState.setFloor(RoomFloor.UPPER, upper);
    }

    private void setupHaunt(GameState gameState) {
        DiceRoller diceRoller = new DiceRoller(new Random());
        Haunt haunt = new Haunt(diceRoller);

        gameState.setHauntObject(haunt);
    }
}
