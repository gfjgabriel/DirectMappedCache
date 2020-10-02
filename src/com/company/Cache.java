package com.company;

import static com.company.Main.*;

public class Cache {

    private RAM ram;
    private  int[][] mem;
    private int size = 0;

    Cache(int size, RAM ram) throws InvalidSize {
        this.ram = ram;
        this.mem = new int[M][K+2];
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

    private void setRam(int address) throws InvalidAddress {
        int antigo = mem[r(address)][TLINHA] | r(address) << nbits_w;
        int cont = 0;
        for (int x = antigo; x < antigo + K ; x++) {
            ram.Set(x, mem[r(address)][cont]);
            cont++;
        }
    }

    private void getRam(int address) throws InvalidAddress {
        int cont = 0;
        int novo = s(address) << nbits_w;
        for (int x = novo; x < novo + K ; x++) {
            mem[r(address)][cont] = ram.Get(x);
            cont++;
        }
        mem[r(address)][TLINHA] = t(address);
        mem[r(address)][MODIF] = 0;
    }

    int Get(int address) throws InvalidAddress {

        if (t(address) != mem[r(address)][TLINHA]) {
            if (mem[r(address)][MODIF] == 1) {
                setRam(address);
            }
            getRam(address);
        }
        return mem[r(address)][w(address)];
    }

    void Set(int address, int word) throws InvalidAddress {

        if (t(address) != mem[r(address)][TLINHA]) {
            if (mem[r(address)][MODIF] == 1) {
                setRam(address);
            }
            getRam(address);
        }
        mem[r(address)][w(address)] = word;
        mem[r(address)][MODIF] = 1;
    }

}
