package betrayal.gui;

import betrayal.*;
import betrayal.card.Event;
import betrayal.card.Item;
import betrayal.card.Omen;
import betrayal.controllers.*;
import betrayal.board.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class RoomClickedListener implements MouseListener {

    private ArrayList<RoomComponent> roomComponents = new ArrayList<RoomComponent>();

    private int translateX, translateY, startingRoomX, startingRoomY, finalRoomX, finalRoomY;
    private boolean beginMove;
    private String currId;
    private GamePanel panel;
    private GameState state;
    private MoveController moveController;
    private RoomController roomController;
    private CardController cardController;

    /**
     * RoomClickedListener constructor.
     * @param panel
     * @param state
     * @param components
     * @param moveController
     * @param roomController
     * @param cardController
     */
    @SuppressFBWarnings
    public RoomClickedListener(GamePanel panel, GameState state, final ArrayList<RoomComponent> components,
                               MoveController moveController, RoomController roomController,
                               CardController cardController) {
        this.panel = panel;
        this.state = state;
        this.moveController = moveController;
        this.roomController = roomController;
        this.cardController = cardController;
        roomComponents.addAll(components);
    }

    /**
     * Set the translation so clicks are properly offset.
     * @param translateX
     * @param translateY
     */
    public void setTranslation(final int translateX, final int translateY) {
        this.translateX = translateX;
        this.translateY = translateY;
    }

    /**
     * Add a roomComponent to the ArrayList.
     * @param component
     */
    public void addRoomComponent(final RoomComponent component) {
        roomComponents.add(component);
    }

    /**
     * Check if mouse is clicked on a room.
     * @param e
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
        panel.setError("");
        // Movement can't happen if the player doesn't have more than 0 moves left.
        if (state.getCurrentPlayer().getMoveCounter() > 0) {
            // Get the starting location of the player.
            startingRoomX = state.getCurrentPlayer().getX();
            startingRoomY = state.getCurrentPlayer().getY();
            // The clicked point will not be accurate unless translated.
            Point clickPoint = new Point(e.getX() - translateX, e.getY() - translateY);
            for (int i = 0; i < roomComponents.size(); i++) {
                roomComponents.get(i).setDrawAvailableDoors(false);
                // If movement has begun and the current room is the selected room
                if (beginMove && roomComponents.get(i).getId().equals(currId)) {
                    // Then draw its doors that can have rooms added
                    roomComponents.get(i).setDrawAvailableDoors(true);
                    // Determine the direction of the door the player clicked on
                    Direction clickedDirection = checkDoorBox(roomComponents.get(i), clickPoint);
                    if (clickedDirection != null) {
                        // Add a room to the graph.
                        addRoom(roomComponents.get(i), clickedDirection);
                    }
                    continue;
                } else if (roomComponents.get(i).getRoomRect().contains(clickPoint)) {
                    roomClicked(roomComponents.get(i));
                }
            }
        }
    }

    /**
     * If a room is clicked, begin movement.
     * @param roomComponent
     */
    public void roomClicked(RoomComponent roomComponent) {
        // If the move hasn't started yet
        if (!beginMove && startingRoomX == roomComponent.getRoomX()
                && startingRoomY == roomComponent.getRoomY()) {
            currId = roomComponent.getId();
            beginMove = true;
            roomComponent.setDrawAvailableDoors(true);
        } else if (beginMove) {
            try {
                // Try to move the player.
                moveController.handlePlayerMove(state.getCurrentPlayer(), roomComponent.getId());
                startingRoomX = state.getCurrentPlayer().getX();
                startingRoomY = state.getCurrentPlayer().getY();
            } catch (RoomNotFoundException err) {
                panel.setError(state.getMessageString("roomsNotConnected"));
            } catch (NotEnoughMovesException err) {
                panel.setError(state.getMessageString("notEnoughMovement"));
            }
            beginMove = false;
        }
    }

    /**
     * Check if the spot clicked contains an available door box.
     * @param component
     * @param mousePoint
     * @return the Direction of the door box
     */
    public Direction checkDoorBox(RoomComponent component, Point mousePoint) {
        Rectangle roomRect = component.getRoomRect();
        for (Rectangle doorBox : component.getAvailableDoorRects()) {
            if (doorBox.contains(mousePoint)) {
                if (doorBox.getY() < roomRect.getY()) {
                    return Direction.NORTH;
                } else if (doorBox.getX() > roomRect.getX()) {
                    return Direction.EAST;
                } else if (doorBox.getY() > roomRect.getY()) {
                    return Direction.SOUTH;
                } else if (doorBox.getX() < roomRect.getX()) {
                    return Direction.WEST;
                }
            }
        }
        return null;
    }

    /**
     * Add a room to the graph.
     * @param component
     * @param direction
     */
    public void addRoom(RoomComponent component, Direction direction) {
        int gridX = component.getRoomX(), gridY = component.getRoomY();
        RoomFloor floorType = component.getRoomFloorType();
        switch (direction) {
            case NORTH:
                gridY++;
                break;
            case SOUTH:
                gridY--;
                break;
            case EAST:
                gridX++;
                break;
            case WEST:
                gridX--;
                break;
            default:
                break;
        }
        Room nextRoom = state.getRoomDeck().drawNextPossibleRoom(floorType);

        if (nextRoom == null) {
            panel.setError("No more valid rooms on this floor.");
        }

        boolean successfulDraw = false;
        int i = 0;
        // Attempt to add a room to the graph.
        while (!successfulDraw) {
            // If the room has been rotated more than 4 times, then it is back to its original position
            // and must not be able to be added, so try again with a new room.
            if (i > 5 || nextRoom.getName().equals(component.getName())) {
                nextRoom = state.getRoomDeck().drawNextPossibleRoom(floorType);
                i = 0;
            }
            try {
                // Add the room.
                if (nextRoom.roomDoorsContains(direction.opposite())) {
                    roomController.addRoom(state.getFloor(floorType), nextRoom, new GridPoint(gridX, gridY));
                    successfulDraw = true;
                } else {
                    // If the room wasn't added, the doors don't line up, so rotate the room until they do.
                    nextRoom.rotateRoom(true);
                    i++;
                }
            } catch (CannotAddRoomException err) {
                // If the room wasn't added, the doors don't line up, so rotate the room until they do.
                nextRoom.rotateRoom(true);
                i++;
            }
        }

        // Create the RoomComponent for the new room
        RoomComponent nextComponent = new RoomComponent(state, nextRoom);
        nextRoom.addObserver(nextComponent);
        panel.addRoomComponent(nextComponent);
        this.addRoomComponent(nextComponent);

        moveController.handlePlayerMove(state.getCurrentPlayer(), nextRoom.getRoomId());

        component.setDrawAvailableDoors(false);
        handleRoomPlacement(nextRoom.getRoomType());
        beginMove = false;
    }

    /**
     * Handle the room placement.
     * @param nextRoomType
     */
    public void handleRoomPlacement(RoomType nextRoomType) {
        switch (nextRoomType) {
            case ITEM:
                Item drawnItem = cardController.drawItem();
                panel.createCardOptionPane(drawnItem);
                break;
            case OMEN:
                Omen drawnOmen = cardController.drawOmen();
                state.getCurrentPlayer().pickUp(drawnOmen);
                panel.createCardOptionPane(drawnOmen);
                break;
            case EVENT:
                Event drawnEvent = cardController.drawEvent();
                panel.createCardOptionPane(drawnEvent);
                break;
            default:
                return;
        }
        state.getCurrentPlayer().emptyMoveCounter();
        state.nextPlayer();
    }

    @Override
    public void mousePressed(final MouseEvent e) {

    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }
}
