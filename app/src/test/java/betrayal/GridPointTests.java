package betrayal;

import betrayal.board.GridPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GridPointTests {

    @Test
    public void moveNorthTest(){
        GridPoint gp = new GridPoint(0, 0);

        GridPoint actual = gp.moveDirection(Direction.NORTH);

        Assertions.assertEquals(actual.getX(), 0);
        Assertions.assertEquals(actual.getY(), 1);
    }

    @Test
    public void moveEastTest(){
        GridPoint gp = new GridPoint(0, 0);

        GridPoint actual = gp.moveDirection(Direction.EAST);

        Assertions.assertEquals(actual.getX(), 1);
        Assertions.assertEquals(actual.getY(), 0);
    }

    @Test
    public void moveSouthTest(){
        GridPoint gp = new GridPoint(0, 0);

        GridPoint actual = gp.moveDirection(Direction.SOUTH);

        Assertions.assertEquals(actual.getX(), 0);
        Assertions.assertEquals(actual.getY(), -1);
    }

    @Test
    public void moveWestTest(){
        GridPoint gp = new GridPoint(0, 0);

        GridPoint actual = gp.moveDirection(Direction.WEST);

        Assertions.assertEquals(actual.getX(), -1);
        Assertions.assertEquals(actual.getY(), 0);
    }

    @Test
    public void moveInvalidTest(){
        GridPoint gp = new GridPoint(0, 0);

        GridPoint actual = gp.moveDirection(Direction.NONE);

        Assertions.assertEquals(actual, null);
    }


    @Test
    public void hashTest1(){
        GridPoint gp = new GridPoint(1, 1);
        int expected = 23311;
        int actual = gp.hashCode();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @SuppressFBWarnings
    public void equalsTestWrongObj(){
        GridPoint gp1 = new GridPoint(1, 1);
        boolean actual = gp1.equals(new ArrayList<Integer>());
        boolean expected= false;

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void equalsTestFalse(){
        GridPoint gp1 = new GridPoint(1, 1);
        GridPoint gp2 = new GridPoint(0, 0);
        boolean expected = false;
        boolean actual = gp1.equals(gp2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void equalsTestTrue(){
        GridPoint gp1 = new GridPoint(1, 1);
        GridPoint gp2 = new GridPoint(1, 1);
        boolean expected = true;
        boolean actual = gp1.equals(gp2);

        Assertions.assertEquals(expected, actual);
    }


}
