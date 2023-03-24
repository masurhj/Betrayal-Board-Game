package betrayal.cucumber;

import betrayal.GameState;
import betrayal.TestUtilities;
import betrayal.controllers.MoveController;
import betrayal.controllers.NotEnoughMovesException;
import betrayal.controllers.RoomController;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import betrayal.board.*;
import betrayal.player.Player;
import betrayal.player.PlayerStatistic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerMovementSteps {

    GameState state;
    Player player;
    Floor floor;
    MoveController moveController;
    RoomController roomController;
    Exception exception = null;

    public PlayerMovementSteps() {
        this.state = TestUtilities.createEmptyGameState();
        this.moveController = new MoveController(this.state);
        this.roomController = new RoomController(this.state);
    }

    private Floor createFloorGrid(int xSize, int ySize) {
        return TestUtilities.createFloorGrid(xSize, ySize, RoomFloor.GROUND, this.roomController);
    }


    @Given("a full {int}x{int} floor")
    public void aFull5x5Board(Integer xSize, Integer ySize) {
        floor = createFloorGrid(xSize, ySize);
        state.setFloor(floor.getFloorType(), floor);
    }

    @And("a player with speed {int} at \\({int}, {int})")
    public void aPlayerWithSpeedAt(int playerSpeed, int xCoord, int yCoord) {
        player = new Player("Mock Player");
        player.setSpeedStatistic(new PlayerStatistic(0,
                        new int[] { playerSpeed, playerSpeed, playerSpeed, playerSpeed, playerSpeed, playerSpeed, playerSpeed, playerSpeed }));
        player.setFloor(floor);
        player.setLocation(new GridPoint(xCoord, yCoord));
        player.resetMoveCounter();
    }

    @When("the player attempts to move to \\({int}, {int})")
    public void thePlayerAttemptsToMoveTo(int xCoord, int yCoord) {
        try {
            Room destRoom = floor.getRoomAt(new GridPoint(xCoord, yCoord));
            moveController.handlePlayerMove(player, destRoom.getRoomId());
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the player should be at \\({int}, {int})")
    public void thePlayerShouldBeAt(int xCoord, int yCoord) {
        assertEquals(new GridPoint(xCoord, yCoord), player.getLocation());
        assertEquals(floor, player.getFloor());
    }

    @And("the player should have {int} moves left")
    public void thePlayerShouldHaveMovesLeft(int movesLeft) {
        assertEquals(movesLeft, player.getMoveCounter());
    }

    @And("the user should be informed that they do not have enough moves left")
    public void theUserShouldBeInformedThatTheyDoNotHaveEnoughMovesLeft() {
        assertNotNull(exception);
        assertEquals(NotEnoughMovesException.class, exception.getClass());
    }

    @And("the user should be informed that the room does not exist")
    public void theUserShouldBeInformedThatTheRoomDoesNotExist() {
        assertNotNull(exception);
        assertEquals(RoomNotFoundException.class, exception.getClass());
    }
}
