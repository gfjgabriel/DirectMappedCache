package com.company;

public class RAM {
    private int [] mem = null;
    private int size = 0;

    public RAM(int size) {
        this.size = size;
        this.mem = new int[size];
    }

    public int Size() { return size; }

    public int Get(int address) throws InvalidAddress {
        CheckAddress(address);
        return mem[address];
    }

    public void Set(int address, int word) throws InvalidAddress {
        CheckAddress(address);
        this.mem[address] = mem[word];
    }

    private void CheckAddress(int address) throws InvalidAddress {
        if (address < 0 || address >= size)
            throw new InvalidAddress(address);
    }
}