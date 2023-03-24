package betrayal.gui;

import betrayal.Direction;
import betrayal.GameState;
import betrayal.Token;
import betrayal.controllers.SuppressFBWarnings;
import betrayal.board.Room;
import betrayal.board.RoomFloor;

import java.awt.*;
import java.util.ArrayList;

public class RoomComponent implements ComponentObserver {
    private Room room;
    private char typeChar;
    private int x = 0, y = 0, roomX, roomY;
    private double scale = 1;
    private ArrayList<Direction> doors = new ArrayList<Direction>();
    private ArrayList<Direction> usedDoors = new ArrayList<Direction>();
    private GameState state;
    private boolean drawAvailableDoors = false;

    /**
     * RoomComponent constructor without x and y.
     * @param state
     * @param room
     */
    @SuppressFBWarnings
    public RoomComponent(GameState state, Room room) {
        this.state = state;
        this.room = room;
        setupRoom();
    }

    /**
     * Setup things relating to room drawing.
     */
    public void setupRoom() {
        switch (this.room.getRoomType()) {
            case ITEM:
                typeChar = 'I';
                break;
            case OMEN:
                typeChar = 'O';
                break;
            case EVENT:
                typeChar = 'E';
                break;
            default:
                typeChar = ' ';
                break;
        }
        update();
    }

    /**
     * Called when the room is placed.
     */
    @Override
    public void update() {
        this.doors.clear();
        this.doors.addAll(room.getDoors());
        this.usedDoors.clear();
        this.usedDoors.addAll(room.getUsedDoors());
        roomX = room.getX();
        roomY = room.getY();
    }

    /**
     * Translate the floor's x and y appropriately based on scale and floor.
     */
    public void doFloorTranslate() {
        x = 400 + (100 * roomX);
        y = 390 + (100 * -roomY);
        RoomFloor floorType = room.getFloorType();
        switch (floorType) {
            case UPPER:
                x -= 100 + 100 * (state.getFloor(floorType).getMaxX() - state.getFloor(RoomFloor.GROUND).getMinX());
                return;
            case GROUND:
                x += 100;
                return;
            case BASEMENT:
                x += 300 - 100 * (state.getFloor(floorType).getMinX()) + (100 * state.getFloor(RoomFloor.GROUND).getMaxX());
                return;
            default:
                return;
        }
    }

    /**
     * Paints the room rectangle.
     * @param graphics
     * @param scale
     */
    public void paintComponent(final Graphics graphics, final double scale) {
        this.scale = scale;
        doFloorTranslate();
        int roomSize = (int) (100 * this.scale);
        if (this.drawAvailableDoors) {
            graphics.setColor(Color.ORANGE);
        } else {
            graphics.setColor(Color.BLACK);
        }
        graphics.drawRect((int) (x * this.scale), (int) (y * this.scale), roomSize, roomSize);

        paintDoors(graphics, this.scale);
        if (drawAvailableDoors) {
            paintAvailableDoors(graphics, this.scale);
        }

        drawStrings(graphics, this.scale);
    }

    /**
     * Draw the room name and type character.
     * @param graphics
     * @param scale
     */
    public void drawStrings(final Graphics graphics, final double scale) {
        graphics.setColor(Color.BLACK);
        String roomName = state.getMessageString(room.getName());
        String[] roomNameArr = roomName.split(" ");
        for (int i = 0; i < roomNameArr.length; i++) {
            graphics.drawString(roomNameArr[i], (int) ((x + 4) * scale), (int) ((y + 44) * scale) + (i * 20));
        }
        graphics.drawString("" + typeChar, (int) ((x + 4) * scale), (int) ((y + 95) * scale));

        if (room.getTokens().size() > 0) {
            for (Token token : room.getTokens()) {
                if (token != null) {
                    graphics.drawString(state.getMessageString(token.getName()), (int) ((x + 16) * scale), (int) ((y + 95) * scale));
                }
            }
        }
    }

    /**
     * Paint the available doors.
     * @param graphics
     * @param scale
     */
    private void paintAvailableDoors(Graphics graphics, final double scale) {
        for (Direction door : doors) {
            switch (door) {
                case NORTH:
                    graphics.drawRect((int) (x * scale), (int) ((y - 15) * scale),
                            (int) (100 * scale), (int) (15 * scale));
                    break;
                case EAST:
                    graphics.drawRect((int) ((x + 100) * scale), (int) (y * scale),
                            (int) (15 * scale), (int) (100 * scale));
                    break;
                case SOUTH:
                    graphics.drawRect((int) (x * scale), (int) ((y + 100) * scale),
                            (int) (100 * scale), (int) (15 * scale));
                    break;
                default:
                    graphics.drawRect((int) ((x - 15) * scale), (int) (y * scale),
                            (int) (15 * scale), (int) (100 * scale));
                    break;
            }
        }
    }

    /**
     * Paint the door rectangles.
     * @param graphics
     * @param scale
     */
    private void paintDoors(final Graphics graphics, final double scale) {
        graphics.setColor(Color.ORANGE);
        ArrayList<Direction> directions = new ArrayList<Direction>();
        directions.addAll(doors);
        directions.addAll(usedDoors);
        for (Direction door : directions) {
            switch (door) {
                case NORTH:
                    graphics.fillRect((int) ((x + 20) * scale),
                            (int) ((y - 3) * scale),
                            (int) (60 * scale), (int) (6 * scale));
                    break;
                case EAST:
                    graphics.fillRect((int) ((x + 97) * scale),
                            (int) ((y + 20) * scale),
                            (int) (6 * scale), (int) (60 * scale));
                    break;
                case SOUTH:
                    graphics.fillRect((int) ((x + 20) * scale),
                            (int) ((y + 97) * scale),
                            (int) (60 * scale), (int) (6 * scale));
                    break;
                default:
                    graphics.fillRect((int) ((x - 3) * scale),
                            (int) ((y + 20) * scale),
                            (int) (6 * scale), (int) (60 * scale));
                    break;
            }
        }
    }

    /**
     * Returns the rectangle of the room being drawn.
     * @return roomRect
     */
    public Rectangle getRoomRect() {
        return new Rectangle((int) (x * scale), (int) (y * scale),
                (int) (100 * scale), (int) (100 * scale));
    }

    public ArrayList<Rectangle> getAvailableDoorRects() {
        ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
        doFloorTranslate();
        for (Direction door : doors) {
            switch (door) {
                case NORTH:
                    rects.add(new Rectangle((int) (x * scale), (int) ((y - 15) * scale),
                            (int) (100 * scale), (int) (15 * scale)));
                    break;
                case EAST:
                    rects.add(new Rectangle((int) ((x + 100) * scale), (int) (y * scale),
                            (int) (15 * scale), (int) (100 * scale)));
                    break;
                case SOUTH:
                    rects.add(new Rectangle((int) (x * scale), (int) ((y + 100) * scale),
                            (int) (100 * scale), (int) (15 * scale)));
                    break;
                default:
                    rects.add(new Rectangle((int) ((x - 15) * scale), (int) (y * scale),
                                (int) (15 * scale), (int) (100 * scale)));
                    break;
            }
        }
        return rects;
    }

    /**
     *
     * @return roomX
     */
    public int getRoomX() {
        return roomX;
    }

    /**
     *
     * @return roomY
     */
    public int getRoomY() {
        return roomY;
    }

    /**
     * Used for clicking on rooms.
     * @return roomName
     */
    public String getName() {
        return room.getName();
    }

    public String getId() {
        return room.getRoomId();
    }

    /**
     * Change whether the available doors where rooms can be placed
     * should be drawn.
     * @param drawAvailableDoors
     */
    public void setDrawAvailableDoors(boolean drawAvailableDoors) {
        this.drawAvailableDoors = drawAvailableDoors;
    }

    public RoomFloor getRoomFloorType() {
        return room.getFloorType();
    }
}
