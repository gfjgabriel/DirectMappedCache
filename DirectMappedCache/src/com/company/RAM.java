package com.company;

public class RAM {
    private int [] mem = null;
    private int size = 0;

    public RAM(int size) {
        this.size = size;
        this.mem = new int[size];
    }

    public int Size() { return size; }

    public int [] Get(int address, int [] mem, int cacheSize) throws InvalidAddress {
        CheckAddress(address);
        int addressAux;
        if (address > size - cacheSize){
            addressAux = size - cacheSize;
        } else {
            addressAux = address;
        }
        for (int i = 0; i < cacheSize; i++){
            mem[i] = this.mem[addressAux];
            addressAux++;
        }
        return mem;
    }

    public void Set(int address, int word) throws InvalidAddress {
        CheckAddress(address);
        mem[address] = word;
    }

    private void CheckAddress(int address) throws InvalidAddress {
        if (address < 0 || address >= size)
            throw new InvalidAddress(address);
    }
}