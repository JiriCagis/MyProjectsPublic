package algorithm.invertedIndex.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class TotalOccurrence implements WritableComparable<TotalOccurrence> {

    private IntWritable totalCount;
    private List<DocumentOccurrence> documentOccurences;

    //CONSTRUCTORS
    public TotalOccurrence() {
        totalCount = new IntWritable();
        documentOccurences = new ArrayList<>();
    }

    public TotalOccurrence(IntWritable totalCount, List<DocumentOccurrence> documentOccurences) {
        this.totalCount = totalCount;
        this.documentOccurences = documentOccurences;
    }

    //LOGIC
    @Override
    public void write(DataOutput d) throws IOException {
        totalCount.write(d);
        d.writeInt(documentOccurences.size());
        for (DocumentOccurrence documentOccurence : documentOccurences) {
            documentOccurence.write(d);
        }
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        totalCount.readFields(di);
        int listSize = di.readInt();
        documentOccurences = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            DocumentOccurrence docIndex = new DocumentOccurrence();
            docIndex.readFields(di);
            documentOccurences.add(docIndex);
        }
    }

    @Override
    public int compareTo(TotalOccurrence t) {
        return totalCount.compareTo(t.getTotalCount());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(totalCount);
        builder.append(":");
        if (documentOccurences != null) {
            for (DocumentOccurrence documentOccurence : documentOccurences) {
                builder.append(documentOccurence.toString());
            }
        }
        return builder.toString();
    }

    //GETTERS AND SETTERS
    public IntWritable getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(IntWritable totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = new IntWritable(totalCount);
    }

    public List<DocumentOccurrence> getDocumentOccurences() {
        return documentOccurences;
    }

    public void setDocumentOccurences(List<DocumentOccurrence> documentOccurences) {
        this.documentOccurences = documentOccurences;
    }

}
