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
import betrayal.board.Floor;
import betrayal.board.GridPoint;
import betrayal.board.Room;
import betrayal.board.RoomFloor;
import betrayal.player.Player;
import betrayal.player.PlayerStatistic;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerMovementCrossFloorSteps {

    GameState state;

    Floor floor1;

    Floor floor2;

    RoomController roomController;

    MoveController moveController;

    Player player;

    Exception exception = null;

    public PlayerMovementCrossFloorSteps() {
        state = TestUtilities.createEmptyGameState();
        roomController = new RoomController(state);
        moveController = new MoveController(state);
    }

    @Given("a first full {int}x{int} floor")
    public void aFirstFullXFloor(int width, int height) {
        floor1 = TestUtilities.createFloorGrid(width, height, RoomFloor.GROUND, roomController);
        state.setFloor(floor1.getFloorType(), floor1);
    }

    @And("second full {int}x{int} floor linked to the first at \\({int}, {int}) and \\({int}, {int})")
    public void secondFullXFloorLinkedToTheFirstAtAnd(int width, int height, int x1, int y1, int x2, int y2) {
        floor2 = TestUtilities.createFloorGrid(width, height, RoomFloor.UPPER, roomController);
        state.setFloor(floor2.getFloorType(), floor2);
        Room groundLink = floor1.getRoomAt(new GridPoint(x1, y1));
        Room upperLink = floor2.getRoomAt(new GridPoint(x2, y2));

        groundLink.addLinkedRoom(upperLink);
    }

    @And("a player with speed {int} at \\({int}, {int}) on the first floor")
    public void aPlayerWithSpeedAtOnTheFirstFloor(int speed, int xCoord, int yCoord) {
        player = new Player("Mock Player");
        player.setSpeedStatistic(new PlayerStatistic(0, new int[] { speed, speed, speed, speed, speed, speed, speed, speed }));
        player.setFloor(floor1);
        player.setLocation(new GridPoint(xCoord, yCoord));
        player.resetMoveCounter();
    }

    @When("the player moves to \\({int}, {int}) on the second floor")
    public void thePlayerMovesToOnTheSecondFloor(int xCoord, int yCoord) {
        try {
            Room destRoom = floor2.getRoomAt(new GridPoint(xCoord, yCoord));
            moveController.handlePlayerMove(player, destRoom.getRoomId());
        }
        catch (Exception e) {
            exception = e;
        }
    }

    @Then("the player should be at \\({int}, {int}) on the second floor")
    public void thePlayerShouldBeAtOnTheSecondFloor(int xCoord, int yCoord) {
        assertEquals(new GridPoint(xCoord, yCoord), player.getLocation());
        assertEquals(floor2, player.getFloor());
    }

    @And("the player should have {int} remaining moves")
    public void thePlayerShouldHaveRemainingMoves(int moves) {
        assertEquals(moves, player.getMoveCounter());
    }

    @Then("the player should be at \\({int}, {int}) on the first floor")
    public void thePlayerShouldBeAtOnTheFirstFloor(int xCoord, int yCoord) {
        assertEquals(new GridPoint(xCoord, yCoord), player.getLocation());
        assertEquals(floor1, player.getFloor());
    }

    @And("the player should be notified they do not have enough moves")
    public void thePlayerShouldBeNotifiedTheyDoNotHaveEnoughMoves() {
        assertEquals(NotEnoughMovesException.class, exception.getClass());
    }
}
