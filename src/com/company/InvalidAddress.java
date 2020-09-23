package com.company;

class InvalidAddress extends Exception {
    private final int address;

    InvalidAddress() {
        super();
        this.address = -1;
    }

    InvalidAddress(int address) {
        super("invalid address");
        this.address = address;
    }

    public int getAddress() {
        return address;
    }
}
