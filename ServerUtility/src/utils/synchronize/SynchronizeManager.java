package utils.synchronize;

import java.io.File;

/**
 * Purpose this manager is synchronize folders, you can define source 
 * folder and target folder. When data are change in source folder, manager
 * automatically move change to target folder in define time.
 * @author adminuser
 */
public interface SynchronizeManager {
    void add(File sourceFolder, File targetFolder);
    void remove(File sourceFolder, File targetFolder);
}
