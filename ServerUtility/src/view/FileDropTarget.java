package view;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import javax.swing.JTextField;

/**
 * Drop file path to text edit.
 * @author adminuser
 */
public class FileDropTarget extends DropTarget {

    JTextField textField;

    public FileDropTarget(JTextField textFieldForUpdate) {
        this.textField = textFieldForUpdate;
    }

    @Override
    public synchronized void drop(DropTargetDropEvent evt) {
        try {
            evt.acceptDrop(DnDConstants.ACTION_COPY);
            List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
            if (droppedFiles.size() > 0) {
                textField.setText(droppedFiles.get(0).getAbsolutePath());
            }
        } catch (Exception ex) {
            // exception when not drop file
        }
    }
}
