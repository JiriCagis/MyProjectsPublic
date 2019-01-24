package algorithm.kMeans.data;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Container represent n dimension vector with decimal coordinates.
 * Created by admin on 05/02/16.
 */
public class Vertex implements WritableComparable<Vertex> {
    private float[] coordinates;

    //CONSTRUCTORS
    public Vertex(){
        coordinates = null;
    }

    public Vertex(int size) {
        coordinates = new float[size];
        for (int k = 0; k < coordinates.length; k++) {
            coordinates[k] = 0;
        }

    }

    public Vertex(float[] coordinates) {
        this.coordinates = coordinates;
    }


    //LOGIC
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        int length = 0;
        if (coordinates != null) {
            length = coordinates.length;
        }

        dataOutput.writeInt(length);

        for (int k = 0; k < length; k++) {
            dataOutput.writeFloat(coordinates[k]);
        }
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        int length = dataInput.readInt();
        if(length>0){
            coordinates = new float[length];

            for(int k=0;k<length;k++){
                coordinates[k] = dataInput.readFloat();
            }
        } else {
            coordinates = null;
        }
    }

    public boolean add(Vertex other) {
        float[] result = new float[getSize()];
        float[] coordinates1 = getCoordinates();
        float[] coordinates2 = other.getCoordinates();

        if (coordinates1.length != coordinates2.length) {
            return false;
        }

        for (int k = 0; k < getSize(); k++) {
            result[k] = coordinates1[k] + coordinates2[k];
        }
        setCoordinates(result);
        return true;
    }

    public boolean div(float number) {
        for(int k=0;k<coordinates.length;k++){
            coordinates[k]/=number;
        }
        return true;
    }


    @Override
    public int compareTo(Vertex o) {
        return toString().compareTo(o.toString());
    }

    //GETTERS AND SETTERS

    public float[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (float coordinate : coordinates) {
            builder.append(coordinate + ",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        return builder.toString();
    }
}
