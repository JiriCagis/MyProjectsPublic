package net.homecredit.jobsdev;

import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;


/**
 * Simple tests.
 * Feel free to add your own tests.
 *
 * @author embed.it
 */
@FixMethodOrder(NAME_ASCENDING)
public class YourMazeSolverTest1 {

    private MazeSolver mazeSolver = new YourMazeSolver();

    @Test
    public void empty_maze_nok() {
        boolean exists = mazeSolver.existsPath(new boolean[][]{});
        assert !exists;
    }

    @Test
    public void one_by_one_ok() {
        boolean[][] maze = new boolean[][] {{true}};
        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void one_by_one_nok() {
        boolean[][] maze = new boolean[][] {{false}};
        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void two_by_two_nok() {
        boolean[][] maze = new boolean[][] {
                {false, true },
                {true , false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void two_by_two_ok() {
        boolean[][] maze = new boolean[][] {
                {true , false},
                {true , true }
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void three_by_three_ok() {
        boolean[][] maze = new boolean[][] {
                {true , false, true },
                {true , false, true },
                {true , false, true }
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void three_by_three_nok() {
        boolean[][] maze = new boolean[][] {
                {true , false, true },
                {true , false, true },
                {false, false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void four_by_four_ok() {
        boolean[][] maze = new boolean[][] {
                {true , false, true , false},
                {true , false, true , false},
                {true , false, true , false},
                {true , false, true , false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void four_by_four_nok() {
        boolean[][] maze = new boolean[][] {
                {false, true , true , false},
                {false, true , true , false},
                {false, true , true , false},
                {false, false, false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void five_by_five_rectangle_ok() {
        boolean[][] maze = new boolean[][] {
                {true , true , true , true , true },
                {true , false, false, false, true },
                {true , false, true , false, true },
                {true , false, false, false, true },
                {true , true , true , true , true }
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void five_by_five_stripes_nok() {
        boolean[][] maze = new boolean[][] {
                {true , true , true , true , true },
                {false, false, false, false, false},
                {true , true , true , true , true },
                {false, false, false, false, false},
                {true , true , true , true , true },
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void five_by_five_multiple_in_one_out_ok() {
        boolean[][] maze = new boolean[][] {
                {false, true , false, true , false},
                {false, true , false, true , false},
                {false, true , false, true , false},
                {false, true , true , true , false},
                {false, false, true , false, false},
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }


    @Test
    public void six_by_six_snake_ok() {
        boolean[][] maze = new boolean[][] {
                {false, false, false, false, false, true },
                {true , true , true , true , false, true },
                {true , false, false, true , false, true },
                {true , false, false, true , false, true },
                {true , true , false, true , true , true },
                {false, true , false, false, false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void six_by_six_snake_nok() {
        boolean[][] maze = new boolean[][] {
                {false, false, false, false, false, true },
                {true , true , true , true , false, true },
                {true , false, false, true , false, true },
                {true , false, false, true , false, true },
                {true , true , true , true , true , true },
                {false, false, false, false, false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void seven_by_seven_ok() {
        boolean[][] maze = new boolean[][] {
                {true , false, false, false, false, false, true },
                {true , false, false, false, false, false, true },
                {true , false, false, false, false, false, true },
                {true , false, false, false, false, false, true },
                {true , false, false, false, false, false, true },
                {true , false, false, false, false, false, true },
                {true , true , false, false, false, true , true }
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void seven_by_seven_diagonal_ok() {
        boolean[][] maze = new boolean[][] {
                {true , false, false, false, false, false, false},
                {true , true , false, false, false, false, false},
                {false, true , true , false, false, false, false},
                {false, false, true , true , false, false, false},
                {false, false, false, true , true , false, false},
                {false, false, false, false, true , true , false},
                {false, false, false, false, false, true , true }
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }

    @Test
    public void seven_by_seven_triangle_nok() {
        boolean[][] maze = new boolean[][] {
                {false, true , true , true , true , true , true },
                {false, false, true , true , true , true , true },
                {false, false, false, true , true , true , true },
                {false, false, false, false, true , true , true },
                {false, false, false, false, false, true , true },
                {false, false, false, false, false, false, true },
                {false, false, false, false, false, false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert !exists;
    }

    @Test
    public void seven_by_seven_triangle_stripes_ok() {
        boolean[][] maze = new boolean[][] {
                {true , false, true , false, true , false, true },
                {true , false, true , false, true , false, false},
                {true , false, true , false, true , false, false},
                {true , false, true , false, false, false, false},
                {true , false, true , false, false, false, false},
                {true , false, false, false, false, false, false},
                {true , false, false, false, false, false, false}
        };
        // Mazes.printMaze(maze);

        boolean exists = mazeSolver.existsPath(maze);
        assert exists;
    }
}