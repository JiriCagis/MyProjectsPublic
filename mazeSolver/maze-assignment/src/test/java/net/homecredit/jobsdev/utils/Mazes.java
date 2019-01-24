package net.homecredit.jobsdev.utils;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Helper methods.
 *
 * @author embed.it
 */
public final class Mazes {

    // suppress for noninstantiability
    private Mazes() {
        throw new AssertionError();
    }

    /**
     * Prints maze to stdout
     * @param maze  maze to print
     */
    public static void printMaze(boolean[][] maze) {
        for (boolean[] aMaze : maze) {
            for (boolean anAMaze : aMaze) {
                System.out.print((anAMaze ? 1 : 0) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Loads maze from the given file.
     * The file contains only x and y coordinates of open sites, all other points represents walls.
     * @param file  input file
     * @return  maze definition
     */
    public static boolean[][] readMaze(Path file) {
        try (Scanner sc = new Scanner(Files.newBufferedReader(file))) {
            int n = sc.nextInt();

            boolean[][] data = new boolean[n][n];
            while (sc.hasNextInt()) {
                int i = sc.nextInt();
                int j = sc.nextInt();
                data[i][j] = true;
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns path to file inside the given zip archive.
     * @param zipPath   zip archive
     * @param fileName  file inside archive
     * @return  path to file inside archive filesystem
     */
    public static Path getTestInstanceFile(Path zipPath, String fileName) {
        try {
            FileSystem fileSystem =fileSystem = FileSystems.newFileSystem(zipPath, null);
            return fileSystem.getPath("/", fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
