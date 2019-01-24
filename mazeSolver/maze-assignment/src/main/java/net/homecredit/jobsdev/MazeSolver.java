package net.homecredit.jobsdev;

/**
 * Decides if a maze is valid - contains at least one path from top to bottom.
 * Takes care only of top and bottom sites! Possible connections from/to left and right sites are irrelevant.
 *
 * Expect only n by n matrix.
 *
 * @author embed.it
 */
public interface MazeSolver {

    /**
     * Tests the given maze if there is a path from top to bottom. The given maze is always non null and rectangular.
     * Do not throw any exception.
     * @param mazeMatrix    maze definition, you can modify it
     * @return  true if some path exists otherwise false.
     */
    boolean existsPath(boolean[][] mazeMatrix);

}
