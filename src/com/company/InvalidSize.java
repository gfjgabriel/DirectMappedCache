package com.company;

public class InvalidSize extends Exception {
    private final int size;

    InvalidSize() {
        super();
        this.size = -1;
    }

    InvalidSize(int size) {
        super("invalid size");
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
