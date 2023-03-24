package betrayal;

import betrayal.card.Event;
import betrayal.card.Item;
import betrayal.card.Omen;
import betrayal.controllers.DiceRoller;
import betrayal.controllers.Haunt;
import betrayal.gui.ComponentObserver;
import betrayal.board.Floor;
import betrayal.board.Room;
import betrayal.board.RoomDeck;
import betrayal.board.RoomFloor;
import betrayal.player.Player;

import java.util.ArrayList;
import java.util.Collection;

public interface GameState {
    /**
     * @param name
     * @return the player object that has the provided name
     */
    Player getPlayerByName(String name);

    /**
     * @return the list of players in the game
     */
    ArrayList<Player> getPlayers();

    Player getCurrentPlayer();

    /**
     * @return the index of the current player
     */
    int getCurrentPlayerIndex();

    /**
     * @return the haunt object used to perform haunt rolls
     */
    Haunt getHaunt();

    /**
     * @return the room deck with undiscovered rooms
     */
    RoomDeck getRoomDeck();

    /**
     * @return the item deck with items that have not been distributed
     */
    Collection<Item> getItemDeck();

    /**
     * @return the omen deck with omens that have not been drawn
     */
    Collection<Omen> getOmenDeck();

    /**
     * @return the event deck with events that have not been drawn
     */
    Collection<Event> getEventDeck();

    /**
     * @param floor
     * @return the floor object with the associated name
     */
    Floor getFloor(RoomFloor floor);

    /**
     * @param playersToAdd
     */
    void setPlayers(ArrayList<Player> playersToAdd);

    void setCurrentPlayer(int i);

    /**
     * Rotates the current player to the next one in succession.
     */
    void nextPlayer();

    /**
     * @param haunt
     */
    void setHauntObject(Haunt haunt);

    /**
     * @param deck
     */
    void setRoomDeck(RoomDeck deck);

    /**
     * @param itemDeck
     */
    void setItemDeck(Collection<Item> itemDeck);

    /**
     * @param omenDeck
     */
    void setOmenDeck(Collection<Omen> omenDeck);

    /**
     * @param eventDeck
     */
    void setEventDeck(Collection<Event> eventDeck);

    /**
     * @param tokens
     */
    void setTokens(Collection<Token> tokens);

    /**
     * @param floorName
     * @param floor
     */
    void setFloor(RoomFloor floorName, Floor floor);

    /**
     * Update the observers.
     */
    void updateObservers();

    /**Adds an observer to the list of observers.
     * @param observer
     */
    void addObserver(ComponentObserver observer);

    /**
     * Adds a token to the room of the current player.
     * @param tokenName
     */
    void addTokenToCurrentPlayerRoom(String tokenName);

    /** Translates string with resource bundle.
     * @param key
     * @return The translated string
     */
    String getMessageString(String key);


    /** Changes the language for the gamestate.
     * @param language
     * @param region
     */
    void changeResourceBundle(String language, String region);

    DiceRoller getDiceRoller();
    void setDiceRoller(DiceRoller roller);

    /**
     * Find a room by its ID.
     * @param id the ID of the room to find
     * @return the room with the given ID
     */
    default Room findRoomById(String id) {
        return this.getRoomDeck().findById(id);
    }

    /**
     * Adds a new room to the current game.
     * @param room the room to add
     */
    default void addRoom(Room room) {
        this.getRoomDeck().addRoom(room);
    }
}
