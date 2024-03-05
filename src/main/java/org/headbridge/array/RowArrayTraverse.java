package org.headbridge.array;

import java.util.Arrays;

public class RowArrayTraverse extends ArrayTraverse {

    public RowArrayTraverse(int[][] array) {
        super(array);
    }

    @Override
    public void traverse() {
        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {
                int compute = array[j][i] * array[j][i];
            }
        }
    }

    @Override
    public String toString() {
        return "RowArrayTraverse{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
