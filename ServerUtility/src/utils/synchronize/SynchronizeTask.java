package utils.synchronize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SynchronizeTask extends Thread {

    private List<SynchronizeFolder> list = null;
    private long waitTime;

    public SynchronizeTask(long waitTime) {
        this.waitTime = waitTime;
        list = Collections.synchronizedList(new LinkedList<SynchronizeFolder>());
    }

    @Override
    public void run() {
        while (true) {
            try {
                List<SynchronizeFolder> safeList = new ArrayList<>();

                synchronized (list) {
                    for (SynchronizeFolder synchronizeFolder : list) {
                        safeList.add(synchronizeFolder);
                    }
                }

                for (SynchronizeFolder synchronizeFolder : safeList) {
                    checkFolder(synchronizeFolder.sourceFolder, synchronizeFolder.targetFolder);
                }
                Thread.sleep(waitTime);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkFolder(File sourceFolder, File destinationFolder) {
        destinationFolder.mkdir();

        File sourceFiles[] = sourceFolder.listFiles();
        File destinationFiles[] = destinationFolder.listFiles();

        for (File sourceFile : sourceFiles) {
            if (sourceFile.isDirectory()) //recurse approach with folders
            {
                checkFolder(sourceFile, new File(destinationFolder, sourceFile.getName()));
                continue;
            }

            File fileInDestination = findFileInDestination(sourceFile, destinationFiles);

            if (fileInDestination == null || sourceFile.lastModified() > fileInDestination.lastModified()) {
                copyFile(sourceFile, destinationFolder);
                System.out.println("Copy file:" + sourceFile.getName() + " to: " + destinationFolder.getAbsolutePath());
            }
        }

    }

    public static File findFileInDestination(File file, File[] files) {
        for (File testFile : files) {
            if (testFile.getName().equals(file.getName())) {
                return testFile;
            }
        }
        return null;
    }

    public static boolean copyFile(File sourceFile, File destinationFolder) {
        File destinationFile = new File(destinationFolder, sourceFile.getName());
        try (FileInputStream in = new FileInputStream(sourceFile); FileOutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[4096];
            int count = 0;
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void add(SynchronizeFolder synchronizeFolder) {
        if (!list.contains(synchronizeFolder)) {
            list.add(synchronizeFolder);
        }
    }

    public void remove(SynchronizeFolder synchronizeFolder) {
        list.remove(synchronizeFolder);
    }

}
