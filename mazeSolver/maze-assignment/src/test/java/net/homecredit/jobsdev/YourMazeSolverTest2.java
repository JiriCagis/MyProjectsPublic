package net.homecredit.jobsdev;

import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * A little bigger test instances.
 * Feel free to add your own tests.
 *
 * @author embed.it
 */
@FixMethodOrder(NAME_ASCENDING)
public class YourMazeSolverTest2 {

    private MazeSolver mazeSolver = new YourMazeSolver();

    @Test
    public void eight_by_eight_ok() {
        boolean[][] maze = new boolean[][] {
                {false, false, true , true , true , false, false, false},
                {true , false, false, true , true , true , true , true },
                {true , true , true , false, false, true , true , false},
                {false, false, true , true , false, true , true , true },
                {false, true , true , true , false, true , true , false},
                {false, true , false, false, false, false, true , true },
                {true , false, true , false, true , true , true , true },
                {false, false, false, false, false, true , false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void eight_by_eight_nok() {
        boolean[][] maze = new boolean[][] {
                {false, false, true , true , false, true , false, false},
                {true , true , true , true , true , false, false, true },
                {true , true , false, false, true , true , false, false},
                {false, false, true , true , true , true , true , false},
                {true , false, false, false, false, false, true , true },
                {false, true , false, true , true , true , false, false},
                {false, true , true , false, true , true , false, true },
                {true , false, true , false, false, false, true , false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void ten_by_ten_ok() {
        boolean[][] maze = new boolean[][] {
                {false, false, false, true , true , false, true , true , false, true },
                {false, true , true , false, true , true , false, true , false, true },
                {true , true , false, true , true , false, true , true , true , true },
                {false, true , true , true , true , true , true , true , false, true },
                {false, true , false, false, true , false, true , true , false, false},
                {true , false, false, true , false, false, true , true , true , false},
                {true , false, false, true , true , true , false, false, true , true },
                {false, false, true , true , false, false, false, false, false, true },
                {true , false, true , true , false, true , true , true , false, true },
                {true , true , false, false, false, false, false, true , false, true }
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void ten_by_ten_nok() {
        boolean[][] maze = new boolean[][] {
                {false, false, false, true , true , false, true , true , false, true },
                {false, true , true , false, true , true , false, true , false, true },
                {true , true , false, true , true , false, true , true , true , true },
                {false, true , true , true , true , true , true , true , false, true },
                {false, true , false, false, true , false, true , true , false, false},
                {true , false, false, true , false, false, true , true , true , false},
                {true , false, false, true , true , true , false, false, true , true },
                {false, false, true , true , false, false, false, false, false, true },
                {true , false, true , true , false, true , true , true , false, true },
                {true , true , false, false, false, false, false, true , false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void ten_by_ten_2_ok() {
        boolean[][] maze = new boolean[][] {
                {true , false, true , false, true , true , false, false, true , false},
                {true , true , true , false, false, false, true , true , true , true },
                {true , false, false, false, true , false, true , true , false, false},
                {false, true , false, true , true , false, true , true , true , true },
                {true , true , true , true , true , true , false, true , true , false},
                {true , true , true , false, true , true , false, true , true , true },
                {false, true , false, false, true , true , true , true , true , true },
                {true , true , true , false, false, true , false, true , false, false},
                {false, true , true , true , false, true , true , true , false, false},
                {true , false, true , true , false, true , false, false, false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void ten_by_ten_2_nok() {
        boolean[][] maze = new boolean[][] {
                {true , false, true , false, true , true , false, false, false, false},
                {true , true , true , false, false, false, true , true , true , true },
                {true , false, false, false, true , false, true , true , false, false},
                {false, true , false, true , true , false, true , true , true , true },
                {true , true , true , true , true , true , false, true , true , false},
                {true , true , true , false, true , true , false, true , true , true },
                {false, true , false, false, true , true , true , true , true , true },
                {true , true , true , false, false, true , false, true , false, false},
                {false, true , true , true , false, true , true , true , false, false},
                {true , false, true , true , false, true , false, false, false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void fifteen_by_fifteen_ok() {
        boolean[][] maze = new boolean[][] {
                {false, false, false, false, false, true , true , false, false, true , true , false, true , true , false},
                {true , false, false, false, true , true , false, false, false, true , false, true , false, false, true },
                {false, true , false, true , true , true , true , true , false, true , true , true , false, true , true },
                {true , true , false, true , true , false, false, false, false, false, true , false, true , false, true },
                {true , true , true , false, true , true , true , true , true , true , false, false, true , false, true },
                {false, true , false, true , true , true , true , true , true , true , true , true , false, true , true },
                {false, false, true , true , true , true , true , true , true , false, false, true , true , true , true },
                {false, true , false, false, false, false, false, true , true , true , false, false, true , true , false},
                {false, true , true , false, false, true , true , true , true , false, true , true , true , false, true },
                {true , true , true , true , true , true , true , true , true , true , false, true , true , true , true },
                {false, true , true , false, false, false, true , true , false, false, true , false, false, true , false},
                {false, true , false, false, true , false, true , false, false, false, true , false, true , false, true },
                {true , true , true , true , false, true , false, true , true , true , false, true , true , false, true },
                {true , false, true , true , false, false, true , true , true , false, false, true , true , true , true },
                {false, false, true , false, false, true , true , true , false, true , true , false, false, true , true }
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void fifteen_by_fifteen_nok() {
        boolean[][] maze = new boolean[][] {
                {true , true , true , true , false, true , false, true , false, true , true , false, true , false, true },
                {false, true , false, true , true , true , true , false, false, false, false, true , false, false, true },
                {false, true , false, false, true , false, true , false, true , false, true , false, false, true , false},
                {true , true , false, false, false, true , false, false, false, true , false, true , true , true , false},
                {false, true , false, false, true , false, true , true , false, false, true , true , true , true , false},
                {false, true , true , false, false, true , true , false, false, true , false, true , true , true , false},
                {true , true , true , true , true , false, false, true , true , true , true , true , true , true , true },
                {true , false, true , false, false, true , false, true , false, true , true , true , true , true , true },
                {false, false, true , true , true , true , false, true , false, false, true , true , false, true , false},
                {false, true , true , true , false, true , true , true , true , true , true , true , true , false, false},
                {true , false, true , true , false, true , true , true , true , false, true , true , true , true , true },
                {true , true , false, false, true , false, false, false, true , true , true , true , true , true , false},
                {true , true , true , true , false, true , true , false, false, false, true , false, true , false, true },
                {false, true , true , true , true , true , true , true , true , true , false, false, false, false, true },
                {true , false, true , false, true , true , true , true , true , true , false, true , true , true , false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void fifteen_by_fifteen_2_ok() {
        boolean[][] maze = new boolean[][] {
                {false, false, false, true , true , false, true , true , false, false, false, true , false, false, true },
                {false, false, true , false, false, true , true , true , false, false, true , true , true , false, false},
                {true , true , false, false, true , false, false, false, false, true , false, true , true , true , false},
                {false, true , true , true , false, false, false, true , true , false, false, true , true , true , false},
                {false, true , true , false, false, false, true , false, false, false, false, false, false, true , false},
                {true , true , true , false, false, false, false, true , true , true , false, true , true , true , false},
                {true , false, true , false, false, false, false, false, false, true , true , true , false, true , false},
                {true , true , false, true , false, true , true , true , true , false, false, true , false, true , false},
                {false, true , false, false, true , true , false, true , false, true , true , true , true , false, false},
                {false, false, true , false, true , false, true , true , false, true , true , true , true , true , false},
                {false, false, true , false, false, false, true , true , true , false, false, false, true , false, true },
                {true , true , false, true , false, true , false, true , true , false, false, true , true , true , false},
                {true , true , false, false, true , true , false, false, true , true , false, true , true , false, false},
                {false, true , true , false, true , false, true , true , false, false, true , true , false, false, false},
                {true , true , false, false, true , false, false, false, true , false, true , true , true , false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void fifteen_by_fifteen_2_nok() {
        boolean[][] maze = new boolean[][] {
                {false, false, false, true , true , false, true , true , false, false, false, true , false, false, true },
                {false, false, true , false, false, true , true , true , false, false, true , true , true , false, false},
                {true , true , false, false, true , false, false, false, false, true , false, true , true , true , false},
                {false, true , true , true , false, false, false, true , true , false, false, true , true , true , false},
                {false, true , true , false, false, false, true , false, false, false, false, false, false, true , false},
                {true , true , true , false, false, false, false, true , true , true , false, true , true , true , false},
                {true , false, true , false, false, false, false, false, false, true , true , true , false, true , false},
                {true , true , false, true , false, true , true , true , true , false, false, true , false, true , false},
                {false, true , false, false, true , true , false, true , false, true , true , true , true , false, false},
                {false, false, true , false, true , false, true , true , false, true , true , true , true , true , false},
                {false, false, true , false, false, false, true , true , true , false, false, false, true , false, true },
                {true , true , false, true , false, true , false, true , true , false, false, true , true , true , false},
                {true , true , false, false, true , true , false, false, true , true , false, true , true , false, false},
                {false, true , true , false, true , false, true , true , false, false, true , false, false, false, false},
                {true , true , false, false, true , false, false, false, true , false, true , true , true , false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }




    @Test
    public void twenty_by_twenty_ok() {
        boolean[][] maze = new boolean[][] {
                {true , true , true , true , true , true , false, false, true , false, true , true , false, true , true , true , true , true , false, false},
                {true , true , true , true , false, false, true , true , true , true , false, false, true , true , true , true , true , true , true , false},
                {false, true , false, false, false, true , true , true , false, false, false, false, true , true , true , false, true , true , true , true },
                {true , false, true , true , true , false, false, true , true , true , true , true , false, true , true , true , true , true , true , true },
                {true , true , false, true , true , false, true , false, true , false, true , false, true , true , false, false, false, true , false, false},
                {false, false, true , true , false, true , true , true , false, false, true , false, false, false, true , false, true , false, true , true },
                {false, false, true , true , true , true , true , true , false, true , true , true , true , false, false, true , true , false, true , true },
                {true , false, true , true , false, true , true , false, true , true , true , true , true , true , true , false, false, true , true , true },
                {true , true , true , true , true , false, true , true , true , true , false, false, true , false, false, true , true , false, true , false},
                {false, true , false, true , false, false, false, false, false, false, true , false, false, true , false, true , true , false, false, true },
                {true , false, false, true , true , true , true , true , true , true , false, false, false, true , true , false, true , false, true , true },
                {true , true , true , true , false, true , false, true , false, true , true , true , true , true , false, true , true , true , true , false},
                {true , false, false, false, true , false, true , true , true , true , false, true , false, true , false, true , false, true , true , false},
                {true , true , false, true , false, true , true , true , true , true , true , true , false, true , false, false, true , false, true , false},
                {false, true , true , false, false, true , false, false, false, true , true , true , true , true , true , false, true , true , true , true },
                {true , true , true , false, false, false, true , true , true , false, true , false, true , true , true , false, true , true , true , true },
                {false, true , false, false, true , true , true , true , true , false, false, true , true , true , false, false, true , true , true , false},
                {false, false, true , true , false, true , true , false, true , true , true , true , true , true , true , false, true , true , false, true },
                {true , false, true , true , false, false, false, true , false, true , true , false, false, false, false, true , true , false, true , false},
                {true , true , false, true , true , true , false, false, true , true , false, true , true , true , true , true , false, true , true , false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void twentyfive_by_twentyfive_nok() {
        boolean[][] maze = new boolean[][] {
                {true , true , false, true , true , true , false, false, true , false, true , true , true , true , true , false, false, false, true , false, true , true , false, true , false},
                {true , true , true , false, false, false, false, true , true , false, true , true , true , false, false, true , true , true , true , true , true , true , false, true , true },
                {false, false, false, false, true , false, true , true , true , false, true , false, true , false, true , true , true , false, true , true , true , false, true , true , true },
                {true , true , false, true , true , false, true , true , true , false, false, false, false, true , true , false, false, false, true , false, true , false, true , true , true },
                {false, true , true , false, true , true , true , false, false, true , true , true , true , false, true , true , true , false, false, false, false, true , false, true , true },
                {true , false, false, false, true , true , true , false, false, true , true , false, false, true , false, true , true , true , false, false, true , false, false, true , false},
                {true , true , false, true , true , false, false, false, false, true , false, false, false, false, false, true , true , false, false, true , false, false, true , true , true },
                {false, true , true , true , false, false, false, true , false, true , true , false, true , false, true , true , true , false, true , false, true , false, true , true , true },
                {true , true , true , true , false, true , false, false, false, true , false, false, true , true , true , true , true , false, true , true , true , true , false, true , false},
                {true , true , false, true , false, false, false, false, false, false, true , false, true , true , true , true , false, true , false, true , true , true , true , true , false},
                {true , false, false, true , false, true , true , true , true , false, true , false, true , false, true , false, false, true , false, false, true , true , true , true , true },
                {false, true , true , false, false, true , false, false, true , false, true , true , false, true , false, true , false, true , true , false, true , false, true , true , false},
                {true , true , false, true , true , false, true , true , true , false, true , true , true , false, true , true , false, false, true , true , false, false, true , false, false},
                {true , true , true , false, true , false, false, true , true , true , false, true , true , false, false, false, true , false, true , false, false, false, true , false, false},
                {true , true , true , false, false, true , false, true , true , true , false, true , false, true , true , false, true , false, true , false, true , true , true , false, false},
                {false, true , false, true , true , true , false, false, true , true , false, true , false, true , true , true , true , true , false, false, false, true , true , false, true },
                {false, false, true , true , false, true , true , true , true , true , false, true , false, true , true , false, true , true , true , true , true , true , true , true , false},
                {false, true , true , true , true , false, false, false, true , true , true , false, false, true , true , false, true , false, false, true , true , true , true , false, true },
                {false, true , true , true , true , false, false, true , true , false, true , false, false, true , true , true , false, true , false, false, true , false, false, true , true },
                {true , true , false, true , false, true , false, true , true , true , false, true , false, true , false, true , false, false, false, false, true , true , false, false, true },
                {true , true , true , false, false, false, true , false, false, true , true , true , false, false, false, false, true , true , false, false, true , true , true , true , false},
                {true , false, true , false, true , true , false, true , true , true , false, false, false, false, true , true , true , true , true , true , false, true , false, false, false},
                {true , true , true , true , true , true , true , false, true , false, true , false, true , true , false, false, true , true , true , false, false, false, false, false, false},
                {true , false, false, true , false, true , false, true , true , false, true , false, true , false, false, false, true , false, false, true , true , false, false, true , true },
                {false, true , true , false, true , false, false, false, false, true , false, true , true , false, false, true , true , true , true , false, true , true , false, true , true }
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void fifty_by_fifty_ok() {
        boolean[][] maze = new boolean[][] {
                {true , false, true , false, true , false, true , true , true , true , false, false, false, true , false, true , true , false, true , false, true , true , false, false, false, false, false, true , false, false, true , false, false, true , true , true , true , false, true , true , true , true , true , false, false, false, true , true , true , false},
                {true , true , false, false, true , false, true , false, false, true , true , false, true , false, true , false, false, false, false, false, true , false, true , false, false, true , false, true , true , false, true , true , false, true , false, false, true , true , false, true , true , false, true , true , true , false, true , true , true , false},
                {false, true , true , false, false, true , true , true , true , true , false, false, false, false, false, true , true , false, true , true , false, true , true , true , true , true , false, true , true , false, true , false, true , false, true , true , false, true , false, false, false, true , true , false, true , true , true , true , false, true },
                {true , true , false, false, false, false, false, true , true , false, true , true , false, false, true , false, false, true , true , false, false, true , false, true , false, true , true , true , true , true , false, false, false, true , false, true , false, false, false, true , false, false, true , true , false, true , true , false, false, false},
                {false, false, true , true , false, false, true , true , false, true , false, true , true , true , false, true , false, true , true , false, true , false, true , false, false, true , false, true , true , true , true , true , true , true , true , true , true , false, false, true , true , false, true , true , true , true , true , true , true , true },
                {true , false, false, false, false, false, false, false, true , true , true , true , false, false, false, true , true , false, true , false, false, true , false, true , false, true , false, false, true , true , false, true , true , false, true , false, true , true , true , false, true , false, true , false, true , true , true , true , true , true },
                {true , false, true , true , false, false, true , true , true , true , true , false, true , false, true , false, false, true , false, false, false, false, true , true , true , true , true , true , true , false, false, true , true , false, true , false, false, true , true , false, false, false, true , false, true , true , false, false, false, true },
                {false, true , false, false, true , true , true , true , false, true , false, true , false, true , false, false, false, false, true , true , true , false, true , true , false, true , false, true , false, false, false, false, false, false, false, false, true , true , true , false, false, true , true , false, false, false, true , false, false, false},
                {true , false, true , true , true , false, false, true , false, false, false, true , true , true , true , true , false, false, false, true , false, true , false, false, true , true , false, false, true , true , false, true , true , false, false, true , true , false, false, false, false, false, true , true , false, false, true , true , false, false},
                {true , true , true , true , true , true , true , false, false, true , false, false, true , true , true , false, true , true , true , false, false, true , true , true , false, true , true , false, false, false, true , true , true , false, true , true , true , false, false, false, false, true , false, false, false, false, true , false, true , false},
                {true , false, true , false, false, true , true , true , false, true , true , false, true , false, true , true , true , false, false, false, true , true , true , true , false, true , false, true , true , true , true , true , false, true , false, true , true , true , false, true , false, true , true , true , true , true , false, true , true , false},
                {true , false, false, false, false, false, true , true , false, true , true , true , true , false, false, true , true , false, false, true , true , true , false, false, true , false, false, false, false, true , true , true , false, true , false, false, false, true , false, false, true , true , true , false, false, true , true , true , true , false},
                {true , false, false, true , false, false, true , false, true , false, true , false, false, true , true , true , true , true , true , true , false, false, true , false, true , true , false, false, false, false, false, true , true , true , false, true , true , true , false, true , true , true , false, true , true , true , false, false, true , false},
                {false, true , true , false, false, true , false, true , false, false, true , false, false, false, false, true , true , false, false, true , false, true , true , false, true , true , true , false, false, true , false, true , false, false, false, true , true , true , false, false, false, false, true , true , true , false, true , true , true , true },
                {true , true , true , false, true , true , false, false, false, true , true , false, true , true , true , true , true , true , false, false, false, false, true , false, false, false, false, true , true , false, false, false, false, false, false, false, true , true , true , true , true , false, false, true , true , false, false, false, true , true },
                {false, false, true , false, false, true , false, false, true , false, true , true , false, true , false, true , true , true , true , true , false, true , true , true , true , true , false, false, false, true , true , false, false, true , false, true , true , false, true , true , false, true , true , true , false, false, false, false, false, false},
                {false, false, false, true , true , true , false, true , true , false, true , true , true , true , true , false, false, false, false, false, true , true , true , false, false, false, true , true , true , false, false, false, false, true , false, true , true , true , true , false, true , false, false, false, false, true , true , false, true , true },
                {false, true , true , false, true , true , true , false, true , true , false, false, true , false, true , true , true , false, false, true , true , true , false, true , true , true , true , false, true , true , false, false, false, false, false, true , true , true , true , false, false, true , true , false, true , false, false, true , true , true },
                {true , true , false, false, true , true , true , true , true , false, false, true , true , false, false, false, true , false, false, false, true , false, true , false, false, false, false, true , false, true , false, true , false, true , true , false, true , false, true , true , true , true , true , false, false, true , true , true , true , true },
                {true , false, true , false, true , true , true , true , true , true , true , true , false, false, true , false, true , true , true , false, true , true , false, false, true , true , true , false, true , true , false, true , false, false, false, true , true , false, false, true , true , true , true , true , true , false, false, false, true , true },
                {true , true , true , true , true , false, true , true , true , true , false, true , true , true , false, false, true , true , true , true , true , true , true , false, false, false, true , false, false, false, false, true , true , false, false, true , false, false, true , true , true , true , true , false, true , true , true , false, false, false},
                {true , false, true , false, false, false, false, true , true , false, false, false, false, true , true , false, true , true , true , true , false, false, true , true , false, false, false, false, false, false, false, true , true , true , true , true , false, true , false, false, false, true , true , true , true , false, true , true , true , true },
                {false, false, true , true , false, true , true , true , false, true , true , true , true , true , true , true , false, false, true , false, true , true , true , true , false, false, true , true , true , true , true , true , false, true , false, false, true , true , true , false, false, true , false, true , false, true , true , false, false, true },
                {true , true , true , false, true , true , true , false, false, true , false, false, false, true , false, false, true , true , true , true , false, false, true , false, false, false, true , true , false, false, false, false, false, true , true , true , false, false, true , false, false, false, false, true , true , true , true , false, true , false},
                {false, false, true , false, true , true , true , true , true , false, true , true , false, false, true , true , true , false, false, false, true , false, true , true , true , false, false, false, true , true , true , true , true , true , false, true , false, true , false, true , true , true , false, true , true , true , false, false, false, true },
                {true , false, true , true , false, false, false, true , true , true , true , true , false, true , true , true , true , true , true , false, true , true , true , true , true , true , false, false, true , false, true , true , true , false, true , false, true , true , false, true , true , true , false, false, true , true , true , true , false, true },
                {false, false, true , true , true , false, true , false, true , false, true , true , false, true , false, true , false, true , false, false, false, true , false, false, false, true , true , false, true , false, true , true , true , true , false, true , true , true , false, true , false, true , true , false, false, true , true , true , false, false},
                {true , true , true , true , true , true , false, false, false, false, false, false, true , true , false, true , false, false, true , false, true , false, true , true , true , true , true , false, true , true , true , true , true , true , false, true , false, false, true , false, true , false, false, true , true , true , true , false, true , true },
                {false, true , false, false, false, true , false, true , true , true , false, false, false, true , false, true , false, false, false, true , false, true , true , true , false, false, false, true , true , false, true , true , true , true , false, true , true , true , true , false, true , false, false, true , false, true , false, false, true , false},
                {true , true , true , true , false, true , true , false, true , false, true , false, false, true , true , true , true , true , true , true , false, false, true , true , false, false, false, false, true , true , true , true , false, true , true , false, false, true , true , false, false, false, false, true , true , false, true , true , false, true },
                {true , false, true , true , true , true , true , false, true , true , true , false, true , true , true , true , true , false, true , false, false, true , true , false, true , true , true , true , true , true , false, false, true , true , false, false, true , true , true , true , false, false, true , true , false, true , false, true , true , false},
                {false, false, true , false, false, true , false, false, false, true , false, true , false, true , false, true , true , true , false, false, true , true , true , false, true , true , false, false, true , true , true , false, true , true , false, true , false, false, false, true , true , true , true , true , true , true , false, false, true , false},
                {false, true , false, false, false, true , false, true , false, true , true , false, false, true , true , false, false, false, true , true , true , false, true , true , true , true , false, true , false, true , true , false, false, false, true , true , true , false, false, true , false, true , true , true , false, true , false, true , false, true },
                {true , false, true , true , false, false, true , true , false, false, true , false, true , false, true , true , false, true , true , true , true , true , true , true , false, true , false, true , true , false, true , true , false, false, true , false, false, false, false, true , true , false, false, false, false, false, false, false, false, true },
                {false, true , true , true , false, false, false, true , true , true , false, true , true , false, true , true , false, false, false, false, true , true , true , true , true , false, true , true , true , true , false, true , false, false, false, true , false, true , true , false, true , false, false, false, true , true , true , true , true , true },
                {true , true , true , false, true , false, true , true , false, true , true , true , true , true , true , false, false, true , true , true , false, true , true , true , false, false, false, false, true , false, true , true , false, false, true , true , false, true , true , false, false, true , false, true , true , true , true , false, true , true },
                {true , true , false, true , false, false, true , false, true , false, true , true , true , true , false, false, true , true , false, true , false, false, false, false, false, false, true , false, false, true , true , false, true , false, false, true , true , false, false, false, true , false, false, false, true , true , false, true , false, true },
                {false, false, false, false, false, false, true , true , true , true , false, false, false, true , true , true , false, true , false, false, false, true , false, false, true , true , false, true , false, true , false, true , false, true , true , true , false, true , false, true , true , true , true , false, true , false, false, true , true , true },
                {true , true , false, false, true , true , true , false, true , true , false, false, false, true , true , true , false, true , false, true , false, true , true , true , true , false, false, false, false, false, true , true , false, false, false, true , true , true , true , true , false, false, true , true , false, true , true , true , true , true },
                {true , true , true , false, true , true , false, true , true , false, false, false, false, true , true , false, true , true , true , true , true , true , true , false, true , false, false, true , true , true , true , true , false, false, false, true , false, false, false, false, true , false, true , true , false, true , true , false, true , true },
                {true , false, true , false, true , false, true , true , true , false, true , true , false, true , true , true , true , true , true , false, true , true , true , false, true , true , true , false, false, false, false, true , true , false, true , false, false, true , true , true , true , false, true , false, false, true , false, true , true , false},
                {true , false, true , true , true , true , true , true , false, true , true , true , false, true , true , false, false, false, false, true , true , false, false, false, true , true , false, false, true , true , true , true , false, true , true , false, false, true , true , false, true , false, false, true , false, false, true , false, false, false},
                {false, false, false, true , false, false, true , true , true , false, true , true , true , true , true , false, false, true , true , true , true , true , false, false, true , true , true , false, false, false, true , false, false, false, false, true , false, false, false, true , false, true , false, false, false, true , true , true , true , true },
                {true , true , false, true , false, true , false, false, false, true , true , true , false, false, true , true , false, true , false, false, true , true , false, true , true , true , true , false, true , false, true , false, false, false, false, true , false, true , false, false, true , true , false, false, true , false, false, false, false, true },
                {false, false, false, false, false, true , false, false, true , true , true , true , true , true , true , true , false, false, true , true , false, false, false, true , true , false, false, true , true , true , true , false, true , true , true , false, false, false, true , false, false, true , false, true , false, false, true , false, false, false},
                {false, false, false, false, false, false, true , true , false, true , true , false, false, true , true , true , true , true , false, false, false, false, true , true , false, false, false, false, false, true , true , true , false, true , false, false, true , true , true , false, true , false, false, false, false, true , true , true , true , true },
                {true , true , true , true , true , false, true , false, true , true , false, false, true , true , false, true , false, true , false, true , true , false, true , false, false, true , false, false, false, false, true , true , true , true , false, true , false, false, true , true , true , true , true , true , true , true , false, false, false, true },
                {true , true , true , false, false, true , false, true , true , false, true , false, false, false, false, true , true , true , false, false, false, true , true , true , true , false, true , true , true , true , false, true , false, false, false, false, true , true , true , true , false, true , false, true , false, false, false, false, false, false},
                {true , true , false, true , true , false, true , false, false, true , false, true , true , true , false, true , false, true , true , true , true , true , true , true , true , false, false, true , true , false, false, true , true , false, false, true , true , true , true , false, true , true , false, true , false, false, false, true , true , true },
                {false, true , false, true , true , true , true , false, true , true , false, true , false, false, true , true , true , true , false, true , true , false, false, true , false, false, true , true , true , false, false, true , false, false, true , true , false, true , true , false, true , false, true , true , false, false, true , false, true , false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }
}
