package algorithm.invertedIndex.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class DocumentOccurrence implements WritableComparable<DocumentOccurrence> {

    private IntWritable documentNumber;
    private IntWritable count;

    //CONSTRUCTORS
    public DocumentOccurrence() {
        documentNumber = new IntWritable();
        count = new IntWritable();
    }

    public DocumentOccurrence(IntWritable documentNumber, IntWritable count) {
        this.documentNumber = documentNumber;
        this.count = count;
    }

    public DocumentOccurrence(int documentNumber, int count) {
        this.documentNumber = new IntWritable(documentNumber);
        this.count = new IntWritable(count);
    }

    //LOGIC
    @Override
    public void write(DataOutput d) throws IOException {
        documentNumber.write(d);
        count.write(d);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        documentNumber.readFields(di);
        count.readFields(di);
    }

    @Override
    public int compareTo(DocumentOccurrence t) {
        return count.compareTo(t.getCount());
    }

    @Override
    public String toString() {
        return "(" + documentNumber + "," + count + ")";
    }

    //GETTERS AND SETTERS  
    public IntWritable getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(IntWritable documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = new IntWritable(documentNumber);
    }

    public IntWritable getCount() {
        return count;
    }

    public void setCount(IntWritable count) {
        this.count = count;
    }

    public void setCount(int count) {
        this.count = new IntWritable(count);
    }
}
