package utils.synchronize;

import java.io.File;

public class SynchronizeManagerImpl implements SynchronizeManager{
    private SynchronizeTask synchronizeTask = null;
    private long waitTime;
    
    public SynchronizeManagerImpl(long waitTime){
        synchronizeTask = new SynchronizeTask(waitTime);
        synchronizeTask.start();
    }

    @Override
    public void add(File sourceFolder, File targetFolder) {
        SynchronizeFolder synchronizeFolder = new SynchronizeFolder(sourceFolder, targetFolder);
        synchronizeTask.add(synchronizeFolder);
    }

    @Override
    public void remove(File sourceFolder, File targetFolder) {
        SynchronizeFolder synchronizeFolder = new SynchronizeFolder(sourceFolder, targetFolder);
        synchronizeTask.remove(synchronizeFolder);
    }
    
}
