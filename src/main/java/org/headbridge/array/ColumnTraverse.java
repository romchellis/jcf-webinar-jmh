package org.headbridge.array;

import java.util.Arrays;

public class ColumnTraverse extends ArrayTraverse {

    public ColumnTraverse(int[][] array) {
        super(array);
    }

    @Override
    public void traverse() {
        for (int column = 0; column < array[0].length; column++) {
            for (var row : array) {
                int compute = row[column] * row[column];
            }
        }
    }

    @Override
    public String toString() {
        return "ColumnTraverse{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
