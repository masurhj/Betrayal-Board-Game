package betrayal;

import betrayal.card.Event;
import betrayal.card.Item;
import betrayal.card.Omen;
import betrayal.controllers.DiceRoller;
import betrayal.controllers.Haunt;
import betrayal.controllers.SuppressFBWarnings;
import betrayal.gui.ComponentObserver;
import betrayal.board.Floor;
import betrayal.board.RoomDeck;
import betrayal.board.RoomFloor;
import betrayal.player.Player;
import betrayal.player.PlayerDoesNotExistException;

import java.text.MessageFormat;
import java.util.*;

@SuppressFBWarnings
public class BetrayalGameState implements GameState {
    private ArrayList<Player> activePlayers = new ArrayList<Player>();
    private int currentPlayer = 0;
    private DiceRoller roller = new DiceRoller(new Random());
    private Haunt haunt = new Haunt(roller);
    private RoomDeck roomDeck = new RoomDeck();
    private Collection<Omen> omenDeck;
    private Collection<Item> itemDeck;
    private Collection<Event> eventDeck;
    private HashMap<String, Token> tokenMap = new HashMap<>();
    private HashMap<RoomFloor, Floor> gameFloors = new HashMap<RoomFloor, Floor>();
    private ArrayList<ComponentObserver> observers = new ArrayList<ComponentObserver>();
    private ResourceBundle messages;
    private static final String MESSAGE_RESOURCE = "messages";

    /**
     * @param name
     * @return the player with the desired name, or throws a runtime error saying that the desired player doesn't exist
     */
    @Override
    public Player getPlayerByName(final String name) {
        for (Player player : activePlayers) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        String errorMessage = new MessageFormat(messages.getLocale().toString()).format("Could not find player with the name: {0,name}");
        throw new PlayerDoesNotExistException(errorMessage);
    }

    /**
     * @return the list of active players in the game
     */
    @Override
    public ArrayList<Player> getPlayers() {
        return activePlayers;
    }

    /**
     * @return the current player
     */
    @Override
    public Player getCurrentPlayer() {
        return activePlayers.get(currentPlayer);
    }

    /**
     * @return the current haunt object
     */
    @Override
    public int getCurrentPlayerIndex() {
        return currentPlayer;
    }

    /**
     * @return the state's version of the haunt object
     */
    @Override
    public Haunt getHaunt() {
        return haunt;
    }

    /**
     * @return the deck of rooms remaining
     */
    @Override
    public RoomDeck getRoomDeck() {
        return roomDeck;
    }

    /**
     * @return the items that have not yet been played
     */
    @Override
    public Collection<Item> getItemDeck() {
        return itemDeck;
    }

    /**
     * @return the omens that have not yet been played
     */
    @Override
    public Collection<Omen> getOmenDeck() {
        return omenDeck;
    }

    /**
     * @return the events that have not yet been played
     */
    @Override
    public Collection<Event> getEventDeck() {
        return eventDeck;
    }

    /**
     * @param floor
     * @return the Floor object for the requested floor
     */
    @Override
    public Floor getFloor(final RoomFloor floor) {
        return gameFloors.get(floor);
    }

    /** Sets the list of active players.
     * @param playersToAdd
     */
    @Override
    public void setPlayers(final ArrayList<Player> playersToAdd) {
        this.activePlayers = new ArrayList<>(playersToAdd);
    }

    /** Sets the current player in the game to the desired index.
     * @param i
     */
    @Override
    public void setCurrentPlayer(final int i) {
        currentPlayer = i;
    }

    /**
     * Rotates the current player to the next player in the game.
     */
    @Override
    public void nextPlayer() {
        activePlayers.get(currentPlayer).resetMoveCounter();
        int nextPlayer = currentPlayer + 1;
        if (nextPlayer == activePlayers.size()) {
            nextPlayer = 0;
        }
        setCurrentPlayer(nextPlayer);
        updateObservers();
    }

    /** Sets the haunt object to be used by the game.
     * @param haunt
     */
    @Override
    public void setHauntObject(final Haunt haunt) {
        this.haunt = haunt;
    }

    /** Sets the room deck for the game.
     * @param deck
     */
    @Override
    public void setRoomDeck(final RoomDeck deck) {
        this.roomDeck = deck;
    }

    /** Sets the item deck for the game.
     * @param itemDeck
     */
    @Override
    public void setItemDeck(final Collection<Item> itemDeck) {
        this.itemDeck = new ArrayList<Item>(itemDeck);
    }

    /** Sets the omen deck for the game.
     * @param omenDeck
     */
    @Override
    public void setOmenDeck(final Collection<Omen> omenDeck) {
        this.omenDeck = new ArrayList(omenDeck);
    }

    /** Sets the event deck for the game.
     * @param eventDeck
     */
    @Override
    public void setEventDeck(final Collection<Event> eventDeck) {
        this.eventDeck = new ArrayList<>(eventDeck);
    }

    @Override
    public void setTokens(final Collection<Token> tokens) {
        for (Token t : tokens) {
            String name = t.getName();
            this.tokenMap.put(name, t);
        }
    }

    /** Sets the Floor object for the desired floor in the game.
     * @param floorName
     * @param floor
     */
    @Override
    public void setFloor(final RoomFloor floorName, final Floor floor) {
        this.gameFloors.put(floorName, floor);
    }

    /**
     * Updates all the observers set to observe the betrayal game state.
     */
    @Override
    public void updateObservers() {
        for (ComponentObserver observer : observers) {
            observer.update();
        }
    }

    /** Adds an observer to the list of observers.
     * @param observer
     */
    @Override
    public void addObserver(final ComponentObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Adds a token to the room of the current player.
     * @param tokenName
     */
    public void addTokenToCurrentPlayerRoom(String tokenName) {
        Token newToken = this.tokenMap.get(tokenName);
        this.getCurrentPlayer().getCurrentRoom().addToken(newToken);
    }

    /**
     * @param key
     * @return the string with the given key from the resource bundle.
     */
    @Override
    public String getMessageString(final String key) {
        return messages.getString(key);
    }

    /** Changes the resource bundle used to store the strings within the game.
     * @param language
     * @param region
     */
    @Override
    public void changeResourceBundle(final String language, final String region) {
        Locale l = new Locale(language, region);
        messages = ResourceBundle.getBundle(MESSAGE_RESOURCE, l);
        updateObservers();
    }

    /**
     * @return the state's instance of the dice roller.
     */
    @Override
    public DiceRoller getDiceRoller() {
        return this.roller;
    }

    /** Sets the state's version of the dice roller.
     * @param roller
     */
    @Override
    public void setDiceRoller(final DiceRoller roller) {
        this.roller = roller;
    }
}
