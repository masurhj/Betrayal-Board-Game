package betrayal;

import betrayal.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DirectionTests {

    @Test
    public void getAll(){
        List<Direction> allDirs = Direction.all();

        Assertions.assertTrue(allDirs.contains(Direction.EAST));
        Assertions.assertTrue(allDirs.contains(Direction.WEST));
        Assertions.assertTrue(allDirs.contains(Direction.NORTH));
        Assertions.assertTrue(allDirs.contains(Direction.SOUTH));
        Assertions.assertTrue(allDirs.contains(Direction.NONE));
    }

    @Test
    public void allButEast(){
        List<Direction> allDirs = Direction.allBut(Direction.EAST);

        Assertions.assertFalse(allDirs.contains(Direction.EAST));
        Assertions.assertTrue(allDirs.contains(Direction.WEST));
        Assertions.assertTrue(allDirs.contains(Direction.NORTH));
        Assertions.assertTrue(allDirs.contains(Direction.SOUTH));
        Assertions.assertTrue(allDirs.contains(Direction.NONE));
    }

    @Test
    public void oppositeSouth() {
        Assertions.assertEquals(Direction.SOUTH.opposite(), Direction.NORTH);
    }

    @Test
    public void oppositeWest() {
        Assertions.assertEquals(Direction.WEST.opposite(), Direction.EAST);
    }

    @Test
    public void oppositeNone() {
        Assertions.assertEquals(Direction.NONE.opposite(), null);
    }


    @Test
    public void counterClockwiseSouth() {
        Assertions.assertEquals(Direction.SOUTH.counterClockwise(), Direction.EAST);
    }

    @Test
    public void counterClockwiseWest() {
        Assertions.assertEquals(Direction.WEST.counterClockwise(), Direction.SOUTH);
    }

    @Test
    public void counterClockwiseNone() {
        Assertions.assertEquals(Direction.NONE.counterClockwise(), null);
    }


    @Test
    public void clockwiseSouth() {
        Assertions.assertEquals(Direction.SOUTH.clockwise(), Direction.WEST);
    }

    @Test
    public void clockwiseWest() {
        Assertions.assertEquals(Direction.WEST.clockwise(), Direction.NORTH);
    }

    @Test
    public void clockwiseNone() {
        Assertions.assertEquals(Direction.NONE.clockwise(), null);
    }


    @Test
    public void westModifyX() {
        Assertions.assertEquals(Direction.WEST.getXModification(), 1);
    }

    @Test
    public void eastModifyX() {
        Assertions.assertEquals(Direction.EAST.getXModification(), -1);
    }

    @Test
    public void northModifyX() {
        Assertions.assertEquals(Direction.NORTH.getXModification(), 0);
    }


    @Test
    public void westModifyY() {
        Assertions.assertEquals(Direction.WEST.getYModification(), 0);
    }

    @Test
    public void southModifyY() {
        Assertions.assertEquals(Direction.SOUTH.getYModification(), -1);
    }

    @Test
    public void northModifyY() {
        Assertions.assertEquals(Direction.NORTH.getYModification(), 1);
    }


}
