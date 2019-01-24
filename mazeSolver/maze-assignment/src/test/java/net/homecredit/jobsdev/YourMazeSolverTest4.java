package net.homecredit.jobsdev;

import net.homecredit.jobsdev.utils.Mazes;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Test instances are in zip file {@link YourMazeSolverTest4#TEST_DATA_ARCHIVE}. Each instance was randomly generated.
 * Instances of these types will play the most important factor in evaluation of your implementation.
 * Execution may take tens of seconds or minutes.
 * Feel free to add your own tests.
 * <p>
 * !! The {@link YourMazeSolverTest4#TEST_DATA_ARCHIVE} is located in separate git branch. !!
 *
 * @author embed.it
 */
@RunWith(Parameterized.class)
public class YourMazeSolverTest4 {

    private final String BASE_DIR = "src/test/resources";
    private final Path TEST_DATA_ARCHIVE = Paths.get(BASE_DIR, "test_instances_2000-3000.zip");


    @Parameterized.Parameters(name = "test {index}: file {0}, paths exists {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                { "input_2000.txt", true },
                { "input_2000_nok.txt", false },
                { "input_3000.txt", true },
                { "input_3000_nok.txt", false },
                { "input_3500.txt", true },
                { "input_3500_nok.txt", false },
                { "input_4000.txt", true },
                { "input_4000_nok.txt", false },
                { "input_5000.txt", true },
                { "input_5000_nok.txt", false }
        });
    }

    @Parameterized.Parameter
    public String fileName;

    @Parameterized.Parameter(1)
    public boolean pathExistsExpected;

    private MazeSolver mazeSolver = new YourMazeSolver();


    @Ignore("Unignore when you are ready, this will take a lot of time")
    @Test
    public void test() throws IOException {
        Path path = Mazes.getTestInstanceFile(TEST_DATA_ARCHIVE, fileName);
        boolean[][] maze = Mazes.readMaze(path);

        boolean exists = mazeSolver.existsPath(maze);
        assertEquals(pathExistsExpected, exists);
    }
}
