package com.company;

public class Cache {

    private RAM ram;
    private  int[] mem = null;
    private int size = 0;
    private int indexMin;
    private int indexMax;

    public Cache(int size, RAM ram) throws InvalidSize {
        this.ram = ram;
        this.mem = new int[size];
        setSize(size);
    }

    public int Size() { return size; }

    private void setSize(int size) throws InvalidSize {
        if (ram.Size() >= size) {
            this.size = size;
        } else {
            throw new InvalidSize(size);
        }
    }

    public int Get(int address) throws InvalidAddress {
        return CheckAddress(address);
    }

    public int hit(int address) {
        return mem[address-indexMin];
    }

    public int miss(int address) throws InvalidAddress {
        mem = ram.Get(address, mem, size);
        return mem[address-indexMin];
    }

    public void Set(int address, int word) throws InvalidAddress {
        ram.Set(address, word);
    }

    private int CheckAddress(int address) throws InvalidAddress {
        if(indexMax == 0) {
            setNewIndexes(address);
            return miss(address);
        } else if (address < indexMin || address >= indexMax){
            setNewIndexes(address);
            return miss(address);
        }
        return hit(address);
    }

    private void setNewIndexes(int address){
        if (address > ram.Size() - size){
            indexMin = ram.Size() - size;
            indexMax = ram.Size() - 1;
        } else {
            indexMin = address;
            indexMax = address + size;
        }
    }

}
