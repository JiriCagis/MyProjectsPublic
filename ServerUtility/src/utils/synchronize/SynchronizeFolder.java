package utils.synchronize;

import java.io.File;
import java.util.Objects;

public class SynchronizeFolder {
    public final File sourceFolder;
    public final File targetFolder;

    public SynchronizeFolder(File sourceFolder, File targetFolder) {
        this.sourceFolder = sourceFolder;
        this.targetFolder = targetFolder;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.sourceFolder);
        hash = 31 * hash + Objects.hashCode(this.targetFolder);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SynchronizeFolder other = (SynchronizeFolder) obj;
        if (!Objects.equals(this.sourceFolder, other.sourceFolder)) {
            return false;
        }
        if (!Objects.equals(this.targetFolder, other.targetFolder)) {
            return false;
        }
        return true;
    }  
}
