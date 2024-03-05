package org.headbridge.array;

public abstract class ArrayTraverse {
    protected final int[][] array;

    public ArrayTraverse(int[][] array) {
        this.array = array;
    }

    public abstract void traverse();
}
