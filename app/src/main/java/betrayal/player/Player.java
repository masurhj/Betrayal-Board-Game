package betrayal.player;

import betrayal.GameState;
import betrayal.gui.ComponentObserver;
import betrayal.controllers.SuppressFBWarnings;
import betrayal.board.Floor;
import betrayal.board.GridPoint;
import betrayal.board.Room;
import betrayal.board.RoomFloor;
import betrayal.card.Card;

import java.util.*;

public class Player {

    private String name, height;

    private PlayerColor color;

    private int age, weight;

    private PlayerStatistic speedStatistic, mightStatistic, sanityStatistic, knowledgeStatistic;

    private HashMap<String, Card> inventory;

    private int movesLeftOnThisTurn;

    private GridPoint location;

    private ArrayList<ComponentObserver> observers = new ArrayList<ComponentObserver>();

    private RoomFloor currentFloor;

    private Floor floor;

    private boolean isAlive = true;

    /**
     * @param playerName
     */
    public Player(final String playerName) {
        this.name = playerName;
        inventory = new HashMap<>();
        this.location = new GridPoint(0, 0);
        currentFloor = RoomFloor.GROUND;
    }

    /**
     * Add a ComponentObserver to Player.
     * @param componentObserver
     */
    public void addComponentObserver(final ComponentObserver componentObserver) {
        this.observers.add(componentObserver);
    }

    /**
     * Get the current floor the player is on.
     * @return currentFloor
     */
    public RoomFloor getCurrentFloor() {
        return currentFloor;
    }

    /**
     * @param newWeightLong
     */
    public void setWeight(final long newWeightLong) {
        int newWeight = (int) newWeightLong;
        this.weight = newWeight;
    }

    /**
     * @param newHeight
     */
    public void setHeight(final String newHeight) {
        this.height = newHeight;
    }

    /**
     * @param newAgeLong
     */
    public void setAge(final long newAgeLong) {
        int newAge = (int) newAgeLong;
        this.age = newAge;
    }

    /**
     * @param newColor
     */
    public void setColor(final PlayerColor newColor) {
        this.color = newColor;
    }

    /**
     * @param statistic
     */
    @SuppressFBWarnings
    public void setKnowledgeStatistic(final PlayerStatistic statistic) {
        this.knowledgeStatistic = statistic;
        updateObservers();
    }

    /**
     * @param statistic
     */
    @SuppressFBWarnings
    public void setSanityStatistic(final PlayerStatistic statistic) {
        this.sanityStatistic = statistic;
        updateObservers();
    }

    /**
     * @param statistic
     */
    @SuppressFBWarnings
    public void setMightStatistic(final PlayerStatistic statistic) {
        this.mightStatistic = statistic;
        updateObservers();
    }

    /**
     * @param statistic
     */
    @SuppressFBWarnings
    public void setSpeedStatistic(final PlayerStatistic statistic) {
        this.speedStatistic = statistic;
        updateObservers();
    }

    /**
     * Change the knowledge statistic by the indicated value.
     * @param val
     * @return True if the player is still alive.
     */
    public boolean changeCurrentKnowledgeIndexBy(final long val) {
        updateObservers();
        return changeStatisticBy(this.knowledgeStatistic, val);
    }

    /**
     * Change the sanity statistic by the indicated value.
     * @param val
     * @return True if the player is still alive.
     */
    public boolean changeCurrentSanityIndexBy(final long val) {
        updateObservers();
        return changeStatisticBy(this.sanityStatistic, val);
    }

    /**
     * Change the might statistic by the indicated value.
     * @param val
     * @return True if the player is still alive.
     */
    public boolean changeCurrentMightIndexBy(final long val) {
        updateObservers();
        return changeStatisticBy(this.mightStatistic, val);
    }

    /**
     * Change the speed statistic by the indicated value.
     * @param val
     * @return True if the player is still alive.
     */
    public boolean changeCurrentSpeedIndexBy(final long val) {
        updateObservers();
        return changeStatisticBy(this.speedStatistic, val);
    }

    /**
     * Change a statistic by the indicated value.
     * @param ps
     * @param indexOffset
     * @return True if the player is still alive.
     */
    private boolean changeStatisticBy(final PlayerStatistic ps, final long indexOffset) {
        int curIndex = ps.getCurrentIndex();

        if (curIndex + indexOffset > 7) {
            ps.setCurrentIndex(7);
            updateObservers();
            return true;
        }

        if (curIndex + indexOffset < 0) {
            ps.setCurrentIndex(0);
            isAlive = false;
            updateObservers();
            return false;
        }

        ps.setCurrentIndex((int) (curIndex + indexOffset));
        updateObservers();
        return true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    /**
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * @return height
     */
    public String getHeight() {
        return this.height;
    }

    /**
     * @return color
     */
    public PlayerColor getColor() {
        return this.color;
    }

    /**
     * @return weight
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * @return speed value
     */
    public int getSpeedValue() {
        return this.speedStatistic.getCurrentValue();
    }

    /**
     * @return speed index
     */
    public int getSpeedIndex() {
        return this.speedStatistic.getCurrentIndex();
    }

    /**
     * @return might value
     */
    public int getMightValue() {
        return this.mightStatistic.getCurrentValue();
    }

    /**
     * @return might index
     */
    public int getMightIndex() {
        return this.mightStatistic.getCurrentIndex();
    }

    /**
     * @return sanity value
     */
    public int getSanityValue() {
        return this.sanityStatistic.getCurrentValue();
    }

    /**
     * @return sanity index
     */
    public int getSanityIndex() {
        return this.sanityStatistic.getCurrentIndex();
    }

    /**
     * @return knowledge value
     */
    public int getKnowledgeValue() {
        return this.knowledgeStatistic.getCurrentValue();
    }

    /**
     * @return knowledge index
     */
    public int getKnowledgeIndex() {
        return this.knowledgeStatistic.getCurrentIndex();
    }


    /**
     *
     * @param statToChange might, knowledge, speed, or sanity
     * @param toChangeBy int that can be positive or negative that will be added/subtracted from the target stat
     * @return new value of the target stat
     *
     * If the stat to change is not one of the four valid options, an illegal argument exception is thrown
     */
    public int changeStatistic(final StatisticType statToChange, final int toChangeBy) {

        PlayerStatistic currStat;

        if (statToChange == StatisticType.MIGHT) {
            currStat = this.mightStatistic;
        } else if (statToChange == StatisticType.KNOWLEDGE) {
            currStat = this.knowledgeStatistic;
        } else if (statToChange == StatisticType.SPEED) {
            currStat = this.speedStatistic;
        } else if (statToChange == StatisticType.SANITY) {
            currStat = this.sanityStatistic;
        } else {
            throw new IllegalArgumentException("Invalid stat to change provided: " + statToChange);
        }

        int newIndex = currStat.getCurrentIndex() + toChangeBy;

        //If we allow players to die, then when newIndex <= 0 a death workflow will be activated.
        if (newIndex > 7) {
            newIndex = 7;
        } else if (newIndex < 0) {
            newIndex = 0;
        }

        currStat.setCurrentIndex(newIndex);
        return newIndex;
    }

    /**
     *
     * @param card
     * @return whether the item was successfully picked up
     * An item will not be picked up and added to a player's inventory if they either already have the item
     * or if the item is "null" which we represent with the name EMPTY_ITEM such that we don't actually pass null.
     */
    public boolean pickUp(final Card card) {
        String itemName = card.getName();

        if (itemName != "EMPTY_ITEM" && !inventory.containsKey(itemName)) {
            inventory.put(itemName, card);
            updateObservers();
        } else {
            return false;
        }
        return true;
    }

    /**
     * @param itemName defines the item to use
     * @param gameState the GameState
     * @return the item object that was dropped
     *  The function attempts to activate the drop the targeted item.
     *  If the item is not in the player's inventory then this function will throw an IllegalArgumentException
     */
    public Card drop(final String itemName, final GameState gameState) {
        if (!inventory.containsKey(itemName)) {
            throw new IllegalArgumentException("Player " + this.name + " does not have item " + itemName);
        }

        Card outputCard = inventory.get(itemName);
        inventory.remove(itemName);
        outputCard.remove(gameState);

        return outputCard;
    }

    /**
     * Drops the given item into the target room.
     * @param itemName name of the item to drop.
     * @param room the room to drop the item into.
     * @return
     */
    public void dropToRoom(final String itemName, Room room) {
        if (!inventory.containsKey(itemName)) {
            throw new IllegalArgumentException("Player " + this.name + " does not have item " + itemName);
        }
        Card droppedCard = inventory.get(itemName);
        inventory.remove(itemName);

        room.addCard(droppedCard);
    }

    /**
     * @param itemName defines the item to use
     * @param state
     *  The function attempts to activate the item's effects, if the item exists in the player's inventory.
     *  If the item is not in the player's inventory then this function will throw an IllegalArgumentException
     */
    public void use(final String itemName, GameState state) {
        if (!inventory.containsKey(itemName)) {
            throw new IllegalArgumentException("Player " + this.name + " does not have item " + itemName);
        }
        inventory.get(itemName).use(state);
        updateObservers();
    }


    /**
     *
     * @return player's item map
     */
    @SuppressFBWarnings
    public HashMap<String, Card> getInventory() {
        return inventory;
    }

    /**
     * Resets how far the player is able to move for the rest of this turn.
     */
    public void resetMoveCounter() {
        movesLeftOnThisTurn = speedStatistic.getCurrentValue();
        updateObservers();
    }

    /**
     *  Makes the player no longer able to move for the rest of this turn.
     */
    public void emptyMoveCounter() {
        movesLeftOnThisTurn = 0;
        updateObservers();
    }

    /**
     * Update the observers.
     */
    public void updateObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update();
        }
    }

    /**
     * Return the X of the location.
     * @return location X
     */
    public int getX() {
        return location.getX();
    }

    /**
     * Return the Y of the location.
     * @return location Y
     */
    public int getY() {
        return location.getY();
    }

     /**
     *
     * @return the number of moves this player has left this turn
     */
    public int getMoveCounter() {
        return movesLeftOnThisTurn;
    }

    /**
     * @param moves the number of moves this player has left this turn
     */
    public void setMoveCounter(int moves) {
        movesLeftOnThisTurn = moves;
        updateObservers();
    }

    /**
     * @return the player's location on the floor
     */
    public GridPoint getLocation() {
        return location;
    }

    /**
     * Sets the player's location on the floor.
     * @param location the new location
     */
    public void setLocation(GridPoint location) {
        this.location = location;
        updateObservers();
    }

    /**
     * @return the floor the player is on
     */
    @SuppressFBWarnings
    public Floor getFloor() {
        return floor;
    }

    /**
     * Sets the floor the player is on.
     * @param floor the new floor
     */
    @SuppressFBWarnings
    public void setFloor(Floor floor) {
        this.floor = floor;
        this.currentFloor = floor.getFloorType();
        updateObservers();
    }

    /**
     * Set the current floor of the player.
     * @param floorType
     */
    public void setCurrentFloor(RoomFloor floorType) {
        this.currentFloor = floorType;
        updateObservers();
    }

    /**
     * @return the room the player is currently in
     */
    public Room getCurrentRoom() {
        return this.floor.getRoomAt(this.location);
    }

    /**
     * @param statType
     */
    public void resetStatistic(StatisticType statType) {
        switch (statType) {
            case KNOWLEDGE:
                knowledgeStatistic.resetStatistic();
                break;
            case MIGHT:
                mightStatistic.resetStatistic();
                break;
            case SANITY:
                sanityStatistic.resetStatistic();
                break;
            case SPEED:
                speedStatistic.resetStatistic();
                break;
            default:
                break;
        }
        updateObservers();
    }

    /**
     * @param i
     */
    public void setCurrentSpeedIndexBy(int i) {
        speedStatistic.setCurrentIndex(i);
    }

    /**
     * @param i
     */
    public void setCurrentMightIndexBy(int i) {
        mightStatistic.setCurrentIndex(i);
    }

    /**
     * @param i
     */
    public void setCurrentSanityIndexBy(int i) {
        sanityStatistic.setCurrentIndex(i);
    }

    /**
     * @param i
     */
    public void setCurrentKnowledgeIndexBy(int i) {
        knowledgeStatistic.setCurrentIndex(i);
    }
}
